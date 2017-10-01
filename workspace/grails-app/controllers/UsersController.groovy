import twitter4j.*;
import twitter4j.http.*;
import twitter4j.auth.*;
import com.google.code.facebookapi.*;

class UsersController {
    
	static final String MY_TOGGLE = "Users";
	
	static final String LOGIN_TOGGLE = "Login";
	static final String INVITE_TOGGLE = "Invite";
	
	def myTweetMarkService
	def twitterService
	def facebookConnectService
	
	def beforeInterceptor = {
		
	}
	
	List contacts
	Integer invitesSent
	String myHash
	Integer displaySend
	Users usersInstance
	def userName
	Integer isFaceBookUser
	Integer autoTweet
	
    def index = { 
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	} else {
    		redirect(action:list,params:params) 
    	}
	}

    // the delete, save and update actions only accept POST requests
    def static allowedMethods = [delete:'POST', save:'POST', update:'POST']

	//all the administrator functions around user access should check for admin privileges
    def list = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    }
	    	
        if(!params.max) params.max = 10
        [ usersInstanceList: Users.list( params ) ]
    }

    def show = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
		    flash.message = "Please login as administrator to do this operation.";
		    redirect(uri:"/")
		}
			
        def usersInstance = Users.get( params.id )

        if(!usersInstance) {
            flash.message = "Users not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ usersInstance : usersInstance ] }
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
		    flash.message = "Please login as administrator to do this operation.";
		    redirect(uri:"/")
		}
		
        def usersInstance = Users.get( params.id )
        if(usersInstance) {
            usersInstance.delete()
            flash.message = "Users ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Users not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		if (session.userId == null) {
			flash.message = "Please login to do this operation.";
			redirect(uri:"/")
			return
		} else {
			def usersInstance = Users.get( session.userId )
	
			if(!usersInstance) {
	            flash.message = "User not found."
	            redirect(controller:'myTweetMark',action:"myTweetMarks")
	            return
	        }
	        else {
	        	displaySend = myTweetMarkService.getTweet(usersInstance.id)
	    		isFaceBookUser = myTweetMarkService.isFaceBookUser(usersInstance.id)
	    		return [ usersInstance : usersInstance ]
	        }
		}
    }

    def update = {
		if (session.userName == null) {
			flash.message = "Please login to do this operation.";
			redirect(uri:"/")
			return
		}
			
		//update the file
		def transferFile = request.getFile('myFile')
	    if(transferFile != null && !transferFile.empty) {
	    	println "file is not empty, transferring"
	    	String fileName
	    	
	    	switch (grails.util.Environment.current) {
	    	case grails.util.Environment.TEST:
	    		fileName = "C:\\Users\\home\\mytweetmark\\app\\web-app\\images\\USERPIC_"+session.userId
	    		//DEV transfer file not allowed!!!  WARNING!!!
	    		//File file = new File(fileName)
	    		//transferFile.transferTo( file )
	    		//myTweetMarkService.uploadPhoto(session.userId, file)
	    		break
	    	case grails.util.Environment.PRODUCTION:
	    		fileName = MyTweetMarkService.FILE_STORAGE_PREFIX+session.userId
	    		//only tranfer file in production.  
	    		File file = new File(fileName)
	    		transferFile.transferTo( file )
	    		myTweetMarkService.uploadPhoto(session.userId, file)
	    		break
	    	}
	    	
	    }    
	    else {
	       println "file is empty"
	    }

		println "now updating the user"
		//update the user
		try {
	        def usersInstance = Users.get( params.id )
	        if(usersInstance) {
	            usersInstance.properties = params
	            if(!usersInstance.hasErrors() && usersInstance.save()) {
	            	flash.message = "User updated"
	                redirect(controller:'myTweetMark',action:"myTweetMarks")
	                return
	            }
	            else {
	            	flash.message = "User not updated"
	            	redirect(controller:'myTweetMark',action:"myTweetMarks")
	            	return
	            }
	        }
	        else {
	            flash.message = "Users not found with id ${params.id}"
	            redirect(controller:'myTweetMark',action:"myTweetMarks")
	            return
	        }
		} catch (Exception e) {
			e.printStackTrace()
			flash.message = "Problem storing user.  Please try 'typing in' about me, rather 'copy and paste'."
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		}
    }

    def create = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
			
        def usersInstance = new Users()
        usersInstance.properties = params
        return ['usersInstance':usersInstance]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
			
        def usersInstance = new Users(params)
        if(!usersInstance.hasErrors() && usersInstance.save()) {
            flash.message = "Users ${usersInstance.id} created"
            redirect(action:show,id:usersInstance.id)
        }
        else {
            render(view:'create',model:[usersInstance:usersInstance])
        }
    }
    
    def handleLogin = {
    	log.info("inside handleLogin")
    	
    	if (!myTweetMarkService.isFeatureToggleOn(MY_TOGGLE)) {
			flash.message = "Users not available.  Contact Us and email the details of the error.";
    		redirect(uri:"/")
    		return
		} else if (session.userName != null) {
			//redirect(controller:'myTweetMark',action:"myTweetMarks")
			redirect(controller:'post',action:"list")
    	} else if (params.userName ==  null || params.userName.isEmpty()) {
    		flash.message = "User name is empty: ${params.userName}";
    		redirect(uri:"/")
    	} else if (params.password ==  null || params.password.isEmpty()) {
    		flash.message = "password is empty: ${params.password}";
    		redirect(uri:"/")
    	} else {
    	
    		//first check to see whether the checkbox is on for twitter user.
    	
	    	//def user = Users.findByUserNameAndPassword(params.userName, params.password)
	    	def user = myTweetMarkService.verifyUser(params.userName, params.password)
	    	if (!user) {
	    		
	    			if (params.userName.contains("@")) {
	    				flash.message = "user name cannot contain @.";
		    			redirect(uri:"/")
		    			return
	    			} else {
		    			int returnCode = myTweetMarkService.registerTwitterer(params.userName, params.password)
						if (returnCode != MyTweetMarkService.FAILURE) {
							session.userName = params.userName
							session.password = params.password
							session.userId = returnCode
							session.ipAddress = request.getRemoteAddr()
				    		
							if (myTweetMarkService.isFaceBookUser(session.userId)) {
								try {
									if (facebookConnectService.isLoggedIn(request)) {
										FacebookJsonRestClient client = facebookConnectService.getFacebookClient()
										if (client.users_hasAppPermission(Permission.STATUS_UPDATE)) {
											client.users_setStatus("joining http://www.homecook.me/" + session.userName+ " to share bookmarks, posts and blogs.", false);
										}
									} 
								} catch (Exception e) {
									System.out.println("Exception caught obtaining facebook")
									e.printStackTrace()
								}
							} else if (session.twitter != null) {
								session.twitter.updateStatus("I just joined http://www.homecook.me/" + session.userName+ " to share bookmarks, posts and blogs.")
					    		
							} 
							
							if (myTweetMarkService.isAdmin(params.userName)) {
				    			session.isAdmin = true
				    		} else {
				    			session.isAdmin = false
				    		}
							flash.message = "User registered successfully :)";
							redirect(controller:'myTweetMark',action:"myTweetMarks")
							//redirect(controller:'post',action:"list")
							//redirect(controller:'blog',action:"list")
							return
						} else {
							flash.message = "User not found for userName.  Please also verify password: ${params.userName}";
			    			redirect(uri:"/")
			    			return
						}
	    			}
	    		/*} else {
	    			flash.message = "User not found for userName.  Please also verify password: ${params.userName}";
	    			redirect(uri:"/")
	    			return
	    		}*/
	    		
	    	} else {
	    		session.userName = params.userName
	    		session.password = params.password
	    		session.userId = user.id
	    		session.email = user.email
	    		session.ipAddress = request.getRemoteAddr()
	    		
	    		
	    		if (myTweetMarkService.isAdmin(params.userName)) {
	    			session.isAdmin = true
	    		} else {
	    			session.isAdmin = false
	    		}
	    		
	    		if (!myTweetMarkService.isFeatureToggleOn(LOGIN_TOGGLE)) {
	    			if (session.isAdmin) {
	    				flash.message = "Login not available.  Allowing admin access only.";
	    				redirect(controller:'myTweetMark',action:"myTweetMarks")
	    			} else {
	    				flash.message = "Login not available.  Contact Us and email the details of the error.";
	    				redirect(controller:'users',action:"logout")
	    			}
	    		} else {
	    			myTweetMarkService.updateLoginStats(user.id)
	    			redirect(controller:'myTweetMark',action:"myTweetMarks")
					//redirect(controller:'post',action:"list")
					//redirect(controller:'blog',action:"list")
	    		}
	    	}
    	}
    }
    
    def logout = {
    	
    	if (session.userName != null) {
    		session.userName = null
    		session.password = null
    		session.userId = null
    		session.isAdmin = false
    		session.email = null
    		session.ipAddress = null
    		session.description = null
    		session.twitter = null
    		session.requestToken = null
    		session.fuid = null
    	}
    	
    	if (session.facebookUid == null) { 
    		redirect(uri:"/")
    	}
    }
	
	def invite = {
		log.info("inside invite")
		if (session.userName == null) {
			flash.message = "Please login first!";
			redirect(uri:"/")
			return
		} else if (!myTweetMarkService.isFeatureToggleOn(INVITE_TOGGLE)) {
			if (session.isAdmin) {
				flash.message = "Invite not available.  Allowing admin access only.";
			} else {
				flash.message = "Invite not available.  Contact Us and email the details of the error.";
				redirect(controller:'myTweetMark',action:"myTweetMarks")
				return
			}
		} 
		
		if (params.email != null && params.password != null && !params.email.isEmpty() && !params.password.isEmpty()) {
			contacts = myTweetMarkService.getContacts(params.email, params.password)
			
			if (contacts != null && !contacts.isEmpty()) {
				try {
					myTweetMarkService.updateViralAddressImported(session.userId, contacts.size())
					return contacts
				} catch (Exception e) {
					flash.message = "No Contacts found.  Please check and try again.";
					redirect(controller:'myTweetMark',action:"myTweetMarks")
				}
			} else {
				flash.message = "No Contacts found.  Please check and try again.";
				redirect(controller:'myTweetMark',action:"myTweetMarks")
			}
		} else {
			flash.message = "No Contacts found.  Please try again.";
			redirect(controller:'myTweetMark',action:"myTweetMarks")
		}
	}

	def signup = {
    	log.info("inside signup")
		
		return
	}

	def processsignup = {
    	log.info("inside processsignup")
    	log.info("$params.firstName")
    	log.info("$params.lastName")
    	log.info("$params.email")
    	log.info("$params.password")
    	log.info("$params.userName")
		
		def userObj = new Users(params)
    	
    	//lets store the ip of the user register
    	if (session.ipAddress == null || session.ipAddress.equals("")) {
    		userObj.ipAddress = request.getRemoteAddr()
    		session.ipAddress = userObj.ipAddress
    	} else {
    		userObj.ipAddress = session.ipAddress
    	}
    	
		if (myTweetMarkService.createUser(userObj) == MyTweetMarkService.SUCCESS) {
			session.userName = userObj.userName
			session.password = userObj.password
			session.userId = userObj.id
			if (myTweetMarkService.isAdmin(params.userName)) {
    			session.isAdmin = true
    		} else {
    			session.isAdmin = false
    		}
			flash.message = "User registered successfully.";
			redirect(controller:'myTweetMark',action:"myTweetMarks")
		} else {
			flash.message = "Error registering user!";
			redirect(controller:'users',action:"signup")
		}
	}

	def emailMyTweetMarks = {
    	log.info("inside emailMyTweetMarks")
    	if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	} else {
    		def myTweetMarks = myTweetMarkService.processInvites(session.userId, session.userName)
    		def user = myTweetMarkService.getUser(session.userId)
    		if (user.email != null && !user.email.isEmpty()) {
	    		sendMail {
					to user.email
					subject "Latest mytweetmarks are here!"
					//body 'sucka!'
					html myTweetMarks
					//html g.render(template:"myMailTemplate")
				}
	    		flash.message = "mytweetmarks sent to self.";
    		} else {
    			flash.message = "Email is not found.  Click edit to set it.";
    		}
    		redirect(controller:'myTweetMark',action:"myTweetMarks")
    	}
	}

	def processContacts = { InviteParams inviteParams -> 
		log.info("inside processContacts")	
    	
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	} else {
    		if (inviteParams != null) {
    			String[] contacts 
    			
    			if (inviteParams.contacts instanceof String) {
    				contacts = new String[1]
    				contacts[0] = inviteParams.contacts
    			} else if (inviteParams.contacts instanceof String[]) {
    				contacts = inviteParams.contacts
    			} 
    			
    			if (contacts != null) {
    				invitesSent = contacts.length
    				
    				if (contacts.length> 0) {
    					def myTweetMarks = myTweetMarkService.processInvites(session.userId, session.userName)
						String subjectStr = session.userName + "'s myTweetMarks.  Check it out :)"
    						
    					for (int i=0; i< contacts.length; i++) {
    						String toAddress = contacts[i]
    						
    						try {
	    			    		sendMail {
	    							to toAddress
	    							subject subjectStr
	    							//body 'sucka!'
	    							html myTweetMarks
	    							//html g.render(template:"myMailTemplate")
	    						}
    						} catch (Exception e) {
    							log.error(e)
    						}
    					}
    					flash.message = invitesSent + " contacts emailed!";
    					
    				} else {
    					flash.message = "Did not find any contacts.";
    				}
    			} else {
    				flash.message = "Did not find any contacts.";
    			}
    		}
    		redirect(controller:'myTweetMark',action:"myTweetMarks")
    	}
	}
	
	def tos = {
		log.info("inside tos")
	}
	
	def privacy = {
		log.info("inside privacy")
	}
	
	def settings = {
		log.info("inside settings")
		redirect(controller:'myTweetMark',action:"myTweetMarks")
	}
	
	def disableUser = {
			System.out.println("inside disableUser")
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot disable user at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			if (params.userName != null) {
				myTweetMarkService.disableUser(params.userName)
			}
			
			redirect(action:'myTweetMarks',controller:'myTweetMark')
		}
	}
	
	def activeUser = {
			System.out.println("inside activeUser")
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot disable user at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			if (params.userName != null) {
				myTweetMarkService.activeUser(params.userName)
			}
			
			redirect(action:'myTweetMarks',controller:'myTweetMark')
		}
	}
	
	//this code to be called when the user hits the twitter button from the homepage
	def verifyToken = {
			/*System.out.println("Inside verifyToken")
			AccessToken token = twitterService.verifyToken()
			
			if (token != null) {
				//TODO we have the access token, verify the credentials again, and then set the right properties in session for successful user login
				Twitter twitterObj = twitterService.getTwitterObj()
				twitterObj.setAccessToken(token)
				
				String verifyUser
				try {
					verifyUser = twitterObj.verifyCredentials().getId()
				} catch (Exception e) {
					System.out.println("caught exception retrieving user id")
					flash.message = "Invalid Token.";
					redirect(uri:"/")
					return
				}
				if (verifyUser != null) {
				
					session.twitter = twitterObj
					
					session.userName = twitterObj.getUserId()
		    		session.password = twitterObj.getPassword()
		    		Users user = myTweetMarkService.getUser(twitterObj.getUserId())
		    		session.userId = user.id
		    		session.ipAddress = request.getRemoteAddr()
		    		response.setCookie(TwitterService.OAUTH_COOKIE,session.userName,604800)
		    		
		    		if (myTweetMarkService.isAdmin(params.userName)) {
		    			session.isAdmin = true
		    		} else {
		    			session.isAdmin = false
		    		}
					
					redirect(action:'myTweetMarks',controller:'myTweetMark')
					return
					
				} else {
					flash.message = "Invalid Token.";
					redirect(uri:"/")
					return
				}
				return
			} else {*/
				try {
					Twitter twitterObj = twitterService.getTwitterObj()
					RequestToken twitterRequestToken = twitterObj.getOAuthRequestToken(TwitterService.callbackUrl)
					session.token = twitterRequestToken.getToken()
					session.tokenSecret = twitterRequestToken.getTokenSecret()
					session.twitter = twitterObj
					session.requestToken = twitterRequestToken
					String authURL = twitterService.getAuthURL()
					redirect(url:twitterRequestToken.getAuthorizationURL())
					return
				} catch (Exception e) {
					e.printStackTrace()
					flash.message = "Error accessing twitter.  Please contact support.";
					session.twitter = null
	    			session.requeastToken = null
					redirect(uri:"/")
					return
				}
				//redirect(uri:"/")
			//}
	}
	
	def authenticate = {
    		if (!session.requestToken) {
    			flash.message = "User twitter request token not available.";
    			session.twitter = null
	    		session.requeastToken = null
    			redirect(uri:"/")
    			return
    		} else {
    			
	    		try {
	    			AccessToken accessToken = session.twitter.getOAuthAccessToken(session.requestToken, params.oauth_verifier)
	    			def twitterUser = session.twitter.verifyCredentials()
	    			
	    			//if (twitterService.setOAuthAccessToken(session.twitter, session.token, session.tokenSecret) == MyTweetMarkService.FAILURE) {
	    			if (twitterService.setOAuthAccessToken(accessToken, twitterUser.screenName, "", twitterUser.getProfileImageURL().toString(), twitterUser.getProfileBackgroundImageUrl().toString(), session.token, session.tokenSecret, twitterUser.getFriendsCount(), twitterUser.getFollowersCount()) == MyTweetMarkService.FAILURE) {
	    				flash.message = "A facebook user already exists with this username.  Please try another account.";
	    				session.twitter = null
		    			session.requeastToken = null
						redirect(uri:"/")
						return
	    			}
	    				
					session.userName = twitterUser.screenName
		    		Users user = myTweetMarkService.getUser(session.userName)
		    		session.userId = user.id
		    		session.ipAddress = request.getRemoteAddr()
		    		//response.setCookie(TwitterService.OAUTH_COOKIE,session.userName,604800)
		    		
		    		if (myTweetMarkService.isAdmin(params.userName)) {
		    			session.isAdmin = true
		    		} else {
		    			session.isAdmin = false
		    		}
					
					try {
						if (session.twitter != null) {
							session.twitter.createFriendship("mytweetmark", true)
							session.twitter.createFriendship("homecookme", true)
							session.twitter.enableNotification("mytweetmark")
							session.twitter.enableNotification("homecookme")
							TwitterService.adminTwitter.createFriendship(session.userName)
							//TwitterService.adminTwitter.sendDirectMessage(session.userName, "Awesome to connect with you :)")
						}
					} catch (Exception ee) {
						System.out.println("Error inserting followers");
						ee.printStackTrace();
						//redirect(controller:'myTweetMark',action:"myTweetMarks")
						//return
					}
					
					redirect(controller:'myTweetMark',action:"myTweetMarks")
					//redirect(controller:'blog',action:"list")
	    			return
	    			
	    		} catch (Exception e) {
	    			e.printStackTrace()
	    			flash.message = "Error accessing token.";
	    			session.twitter = null
	    			session.requeastToken = null
					redirect(uri:"/")
					return
	    		}
	
			} 
	}
	
	def statusUpdate = {
		System.out.println("update = " + params.update)
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	} else {
    		if (session.twitter != null) {
    			session.twitter.updateStatus(params.update+ " #" + myTweetMarkService.getHashString(session.userId))
    		} 
    		flash.message = "Status updated successfully.";
    		redirect(controller:'myTweetMark',action:"myTweetMarks")
    	}
	}
	
	def sendMyTweets = {
    		if (session.userName == null) {
        		flash.message = "Please login to do this operation.";
        		redirect(uri:"/")
        		return
        	} else {
        		myTweetMarkService.setTweet(session.userId, MyTweetMarkService.ON)
        		flash.message = "Send my tweets.";
        		redirect(controller:'myTweetMark',action:"myTweetMarks")
        	}
	}
	
	def dontSendMyTweets = {
    		if (session.userName == null) {
        		flash.message = "Please login to do this operation.";
        		redirect(uri:"/")
        		return
        	} else {
        		myTweetMarkService.setTweet(session.userId, MyTweetMarkService.OFF)
        		flash.message = "Don't send my tweets.";
        		redirect(controller:'myTweetMark',action:"myTweetMarks")
        		
        	}
	}
    
    def handleFacebook = {
    		if (params.facebookUid != null) {
    			Users user = myTweetMarkService.getUserByFacebookUid(params.facebookUid)
    			
    			if (user == null) {
    				
    				def usersInstance = new Users()
    				usersInstance.properties = params
    				usersInstance.ipAddress = request.getRemoteAddr()
    				if (params.userName == null || params.userName.isEmpty() || params.userName.equals("undefined") || params.userName.equals("null")) {
    					usersInstance.userName = params.facebookUid
    				}
    				
    				//lets also check if a user with this username already exists,
    				Users existUser = myTweetMarkService.getUser(usersInstance.userName)
    				
    				if (existUser != null) {
    					flash.message = "Problem registering user.  User already exists with this username.";
						//redirect(controller:'post',action:"list")
						redirect(controller:'myTweetMark',action:"myTweetMarks")
						//redirect(controller:'blog',action:"list")
						return
    				}
    				
    				if (myTweetMarkService.createUser(usersInstance) == MyTweetMarkService.SUCCESS) {
    					/*if (usersInstance.profilePhoto != null && !usersInstance.profilePhoto.isEmpty()) {
    						try {
    		        			myTweetMarkService.uploadPhoto(usersInstance.id, usersInstance.profilePhoto)
    		        		} catch (Exception e) {
    		        			e.printStackTrace()
    		        		}
    	        		}*/
    					
    					session.userName = usersInstance.userName
						session.userId = usersInstance.id
						session.ipAddress = request.getRemoteAddr()
						session.fuid = params.facebookUid
			    		
						if (myTweetMarkService.isAdmin(usersInstance.userName)) {
			    			session.isAdmin = true
			    		} else {
			    			session.isAdmin = false
			    		}
						flash.message = "User successfully logged in through facebook.";
						//redirect(controller:'users',action:"edit")
						redirect(controller:'myTweetMark',action:"myTweetMarks")
						//redirect(controller:'blog',action:"list")
						return
    				} else {
    					flash.message = "Problem registering user.  User already exists with this username.";
						//redirect(controller:'post',action:"list")
						redirect(controller:'myTweetMark',action:"myTweetMarks")
						//redirect(controller:'blog',action:"list")
						return
    				}
    				
    			} else {
    				user.profilePhoto = params.profilePhoto
    				user.facebookProfile = params.facebookProfile
    				
    				if (params.userName != null && !params.userName.isEmpty() && !params.userName.equals("undefined") && !params.userName.equals("null")) {
    					
    					if (myTweetMarkService.getUser(params.userName) == null) {
    						//covers the case if the user changed their name on facebook and still the username is available on mytweetmark, then give it to the user.
    						user.userName = params.userName
    					}
    				}
    				
    				if(!user.hasErrors() && user.save()) {
    		            System.out.println("user profile photo reset")
    		        }
    				
    				//old user
    				session.userName = user.userName
    				session.userId = user.id
    				session.fuid = user.facebookUid
    				
    				session.ipAddress = request.getRemoteAddr()
    	    		
    	    		if (myTweetMarkService.isAdmin(user.userName)) {
    	    			session.isAdmin = true
    	    		} else {
    	    			session.isAdmin = false
    	    		}
    	    		
    	    		if (!myTweetMarkService.isFeatureToggleOn(LOGIN_TOGGLE)) {
    	    			if (session.isAdmin) {
    	    				flash.message = "Login not available.  Allowing admin access only.";
    	    				redirect(controller:'myTweetMark',action:"myTweetMarks")
    	    				//redirect(controller:'blog',action:"list")
    	    				return
    	    			} else {
    	    				flash.message = "Login not available.  Contact Us and email the details of the error.";
    	    				redirect(controller:'users',action:"logout")
    	    				return
    	    			}
    	    		} else {
    	    			flash.message = "User successfully logged in through facebook.";
    	    			myTweetMarkService.updateLoginStats(user.id)
    	    			//redirect(controller:'users',action:"edit")
    	    			redirect(controller:'myTweetMark',action:"myTweetMarks")
    	    			//redirect(controller:'blog',action:"list")
    					return
    	    		}
    				
    			}
    		} else {
    			flash.message = "Could not connect through facebook connect.";
    			redirect(controller:'myTweetMark',action:"myTweetMarks")
        		//redirect(controller:'blog',action:"list")
        		return
    		}
    }
    
    def allowFacebook = {
    		
    		if (session.userName != null) {
	    		if (myTweetMarkService.allowFacebook(session.userId) == MyTweetMarkService.SUCCESS) {
	    			flash.message = "Facebook permissions saved.";
	    		} else {
	    			flash.message = "Could not save facebook permissions.";
	    		}
    		}
    		redirect(controller:'myTweetMark',action:"myTweetMarks")
    		return
    }
    
    def autoTweetOn = {
    		if (session.userName == null) {
        		flash.message = "Please login to do this operation.";
        		redirect(uri:"/")
        		return
        	} else {
        		myTweetMarkService.setAutoTweet(session.userId, MyTweetMarkService.ON)
        		flash.message = "Auto tweet on.";
        		redirect(controller:'myTweetMark',action:"myTweetMarks")
        	}
	}
	
	def autoTweetOff = {
    		if (session.userName == null) {
        		flash.message = "Please login to do this operation.";
        		redirect(uri:"/")
        		return
        	} else {
        		myTweetMarkService.setAutoTweet(session.userId, MyTweetMarkService.OFF)
        		flash.message = "Auto tweet off.";
        		redirect(controller:'myTweetMark',action:"myTweetMarks")
        		
        	}
	}
}
