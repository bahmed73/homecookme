import twitter4j.*;
import twitter4j.http.*;
import twitter4j.auth.*;
import java.io.*;

class TwitterService {

	/*static public Twitter adminTwitter
	
	static {
		adminTwitter = new Twitter("mytweetmark", "formtek")
		adminTwitter.setHttpConnectionTimeout(300)
	}*/
	
	//TODO either call the below something else, or make sure not to call static references everywhere, especially dealing with users.  look at authenticateViaAPI
	boolean transactional = false
    static final String consumerKey = "YjwXi6rmenXEKBiQzcSaQ"
    static final  String consumerSecret = "nkc5KwvuH0ccMX2fJGd4fC0mhlA9Rtp9YBB9z8XqfA"
    static final String requestTokenUrl = "http://twitter.com/oauth/request_token"
    static final accessTokenUrl = "http://twitter.com/oauth/access_token"
    static final authorizeUrl = "http://twitter.com/oauth/authorize"
    static final callbackUrl = "http://www.homecook.me/users/authenticate"
    static final String OAUTH_COOKIE = "USERNAME" 
    
    def myTweetMarkService
    def cookieService
    
	//authenticates and returns an image.  blow me :)  just kidding.  i should clean this up.  TODO
	/*public String authenticateViaAPI(String twitterID, String twitterPassword) {
		
		Twitter localTwitter = new Twitter(twitterID, twitterPassword)
		localTwitter.setHttpConnectionTimeout(300)
		User user = localTwitter.verifyCredentials()
		System.out.println("url = " + user.getProfileImageURL())
		return user.getProfileImageURL()
	}*/
	
	//this is step one of twitter oauth.  create the authorization url, and return it.  
	//TODO make sure to forward to correct URLs from this point on
	public AccessToken verifyToken() {
		//first check whether we have the userName in the cookies.  If so, use that to obtain access token.
		if (cookieService.get(OAUTH_COOKIE) != null) {
			AccessToken token = loadAccessToken(cookieService.get(OAUTH_COOKIE))
			if (token != null) {
				return token
			}
		}
		return null
	}
	
	public String getAuthURL() {
		Twitter twitter = getTwitterObj()
		if (twitter != null) {
			System.out.println("twitter initialized")
			RequestToken requestToken = twitter.getOAuthRequestToken();
			String authorizationUrl = requestToken.getAuthorizationURL();
			return authorizationUrl

		} else {
			System.out.println("twitter is null")
			return null
		}
	}
		
	
	public boolean updateStatus(int useId, String statusMessage) {
		Twitter twitter = getTwitterObj()
		AccessToken accessToken = loadAccessToken(useId);
	    twitter.setAccessToken(accessToken);
	    Status status = twitter.updateStatus(statusMessage);
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");

	}
	
	
	public def search(String term) {
		Twitter twitter = new TwitterFactory().getInstance()
	    Query query = new Query(term);
	    QueryResult result = twitter.search(query);
	    return result.getTweets()

	}
	
	public def userTimeline(String userName) {
		Twitter twitter = new TwitterFactory().getInstance()
	    return twitter.getUserTimeline(userName);
	}
	
	def setOAuthAccessToken(AccessToken accessToken, String userName, String password, String photoURL, String backgroundUrl, String token, String tokenSecret, int friendsCount, int followersCount) {
		
		Users user = myTweetMarkService.getUser(userName)
		
		if (user == null) {
			user = new Users()
			user.profilePhoto = photoURL
			user.backgroundUrl = backgroundUrl
	        user.userName = userName
	        user.password = password
	        
	        user.friendsCount = friendsCount
        	user.followersCount = followersCount
	        
			
			myTweetMarkService.createUser(user)
    		
    		/*if (user.profilePhoto != null && !user.profilePhoto.isEmpty()) {
    			user = myTweetMarkService.getUser(userName)
	    		
        		try {
        			myTweetMarkService.uploadPhoto(user.id, user.profilePhoto)
        		} catch (Exception e) {
        			System.out.println("Couldn't process image: " + user.profilePhoto)
            		e.printStackTrace()
            		return MyTweetMarkService.FAILURE
        		}
    		}*/
    		
	        
		} else {
			
			if (myTweetMarkService.isFaceBookUser(userName)) {
				return MyTweetMarkService.FAILURE
			}
			
			user.profilePhoto = photoURL
			user.backgroundUrl = backgroundUrl
			user.friendsCount = friendsCount
	        user.followersCount = followersCount
	        
	        /*if(!user.hasErrors() && user.save()) {
	            return MyTweetMarkService.SUCCESS
	    	} else {
	            return MyTweetMarkService.FAILURE
	        }*/
		}
		
		//store in the db by userId, access token
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = null;
	        byte[] buf;
	        try {
	            oos = new ObjectOutputStream(baos);
	            oos.writeObject(accessToken);
	            oos.flush();
	            buf = baos.toByteArray();
	            user.oauthObj = buf
	        } finally {
	            if (oos != null) {
	                oos.close();
	            }
	            baos.close();
	        }
        
		} catch (Exception e) {
			System.out.println("ERROR: setOAuthAccessToken")
			e.printStackTrace()
			System.out.println(e.getMessage())
		}
		
        if(!user.hasErrors() && user.save()) {
            return MyTweetMarkService.SUCCESS
    	} else {
            return MyTweetMarkService.FAILURE
        }
        
		return MyTweetMarkService.SUCCESS
		
	}
	
	private AccessToken loadAccessToken(String userName){
		
		Users user = myTweetMarkService.getUser(userName) 
		
		if (user != null) {
			Twitter twitter = getTwitterObj()
			
		    AccessToken accessToken = twitter.getOAuthAccessToken(user.token, user.tokenSecret)
		    return AccessToken
		} else {
			return null
		}
	}
	
	public Twitter getTwitterWithOauth(String userName) {
		Users user = myTweetMarkService.getUser(userName) 
		
		if (user != null) {
			AccessToken accessToken = getAccessToken(user.oauthObj)
		    if (accessToken != null) {
		    	try {
		    		Twitter twitter = new TwitterFactory().getInstance()
		    		twitter.setOAuthConsumer(consumerKey, consumerSecret)
		    		twitter.setOAuthAccessToken(accessToken)
		    		return twitter
		    	} catch (Exception e) {
		    		System.out.println("ERROR: getTwitterWithOauth")
		    		e.printStackTrace()
		    		System.out.println(e.getMessage())
		    	}
		    }
		} else {
			return null
		}
		return null
	}
	
	private AccessToken getAccessToken(byte[] bytea) throws Exception{
		try {
	        if (bytea != null && bytea.length>0) {
	            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytea)); 
	            
	            Object obj = ois.readObject();
	            ois.close();
	            
	            return (AccessToken)obj;
	        }
		} catch (Exception e) {
			System.out.println("ERROR: getAccessToken")
			e.printStackTrace()
			System.out.println(e.getMessage())
		}
        return null;
    }
	


	/*public Twitter getTwitterObj() {
		TwitterFactory factory = new TwitterFactory()
		Twitter twitter = factory.getOAuthAuthorizedInstance(consumerKey, consumerSecret)
		//twitter.setHttpConnectionTimeout(300)
		//twitter.setOAuthConsumer(consumerKey, consumerSecret);
		return twitter
	}*/
	
	public Twitter getTwitterObj() {
		Twitter twitter = new TwitterFactory().getInstance()
		//twitter.setHttpConnectionTimeout(300)
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		return twitter
	}
	
	def processTweets(Twitter twitter, Long userId, String userName) {
		System.out.println("inside processTweets")
		
		try {
			TweetUrl lastStatus = myTweetMarkService.getLastTweetUrl(userId)
			def status
			if (lastStatus == null) {
				status = twitter.getUserTimeline(userName)
			} else {
				Paging paging = new Paging(lastStatus.statusId)
				status = twitter.getUserTimeline(userName, paging)
			}
			
			if (status != null && !status.isEmpty()) {
				System.out.println("status found");
				for (int i=0; i<status.size(); i++) {
					def statusText = status.get(i).getText()
					def origStatus = status.get(i).getText()
					if (statusText != null && !statusText.isEmpty()) {
						
						if (statusText.contains("http://") || statusText.contains("https://")) {
							
							def index = statusText.indexOf("http://")
							
							if (index > -1) {
								statusText = statusText.substring(index)
							} else {
								index = statusText.indexOf("https://")
								
								if (index > -1) {
									statusText = statusText.substring(index)
								}
							}
							StringTokenizer tokenizer = new StringTokenizer(statusText)
							
							if (tokenizer != null) {
								if (tokenizer.hasMoreTokens()) {
									//extract the url and insert into tweeturl.  then store in the db.
									def url = tokenizer.nextToken()
									TweetUrl tweetUrl = new TweetUrl()
									tweetUrl.createTime = new Date()
									tweetUrl.userId = userId
									tweetUrl.url=url
									tweetUrl.status=MyTweetMarkService.ACTIVE
									tweetUrl.tweet=origStatus
									tweetUrl.statusId=status.get(i).getId()
									if(tweetUrl.hasErrors() || !tweetUrl.save()) {
										System.out.println("didn't save properly")
									} 
							     }
							}
						}
					}
				}
			} else {
				System.out.println("No status found.")
			}
		} catch (Exception e) {
			System.out.println("Error in processTweets")
			e.printStackTrace()
		}
		
		try {
			twitter.updateStatus("My imported bookmarks from #twitter http://www.homecook.me/" + userName+ " #" + myTweetMarkService.getHashString(userId))
		} catch (Exception ee) {
			ee.printStackTrace()
		}
	}
	
	def processFriends(Twitter twitter, Long userId, String userName) {
		System.out.println("inside processFriends")
		
		try {
			TweetFriend lastFriend = myTweetMarkService.getLastTweetFriend(userId)
			def status
			if (lastFriend == null) {
				status = twitter.getUserTimeline(userName)
			} else {
				Paging paging = new Paging(lastFriend.statusId)
				status = twitter.getUserTimeline(userName, paging)
			}
			
			if (status != null && !status.isEmpty()) {
				System.out.println("status found");
				for (int i=0; i<status.size(); i++) {
					def statusText = status.get(i).getText()
					def origStatus = status.get(i).getText()
					if (statusText != null && !statusText.isEmpty()) {
						
						while (statusText.contains("@")) {
							
							def index = statusText.indexOf("@")
							
							if (index > -1) {
								statusText = statusText.substring(index+1)
							} 
							StringTokenizer tokenizer = new StringTokenizer(statusText)
							//TODO need to extract all @ in a status, remove duplicates, don't duplicate in db either.  pull their photo and username.
							//User user = twitter.showUser(userName)
							if (tokenizer != null) {
								if (tokenizer.hasMoreTokens()) {
									//extract the url and insert into tweeturl.  then store in the db.
									def friendName = tokenizer.nextToken()
									TweetFriend tweetFriend = new TweetFriend()
									tweetFriend.createTime = new Date()
									tweetFriend.userId = userId
									tweetFriend.friendName=friendName
									tweetFriend.status=MyTweetMarkService.ACTIVE
									tweetFriend.tweet=origStatus
									tweetFriend.statusId=status.get(i).getId()
									User twitterUser = twitter.showUser(friendName)
									if (twitterUser != null) {
										tweetFriend.profilePhoto = twitterUser.getProfileImageURL().toString()
									}
									if(tweetFriend.hasErrors() || !tweetFriend.save()) {
										System.out.println("tweetfriend didn't save properly")
									} 
							     }
							}
						}
					}
				}
			} else {
				System.out.println("No status found.")
			}
		} catch (Exception e) {
			System.out.println("Error in processFriends")
			e.printStackTrace()
		}
		
		try {
			twitter.updateStatus("My twitter contacts http://www.homecook.me/" + userName+ " #" + myTweetMarkService.getHashString(userId))
		} catch (Exception ee) {
			ee.printStackTrace()
		}
	}
}
