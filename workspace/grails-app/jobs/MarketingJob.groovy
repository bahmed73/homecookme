import twitter4j.*;
import twitter4j.http.*;
import java.util.*;

class MarketingJob {
	static triggers = {
	    simple name: 'mySimpleTrigger3', startDelay: 60000, repeatInterval: 1000*60*60*1l,repeatCount:0  
	  }
    //def timeout = 1000*60*60*2l // execute job once in 2 hours

    def searchTerms = ["celebrity", "business", "blogger", "marketing", "media", "writer", "entrepreneur", "technology", "artist", "socialmedia", "localfood", "farmersmarket", "smallfarm", "agchat", "realfood", "buylocal", "organic", "natural", "local", "farmersmarkets"]
    def myTweetMarkService
    def twitterService

    def execute() {
        // execute task
        System.out.println("Inside MarketingJob")
    	Date currentTime = new Date();
    	System.out.println("Job start time: " + currentTime.toString())
        while (1) {
        	try {
		        def marketUsers = MarketUser.findAllByStatus(1)
		        
		        if (marketUsers != null && !marketUsers.isEmpty()) {
		        	java.util.Random randomGenerator = new java.util.Random()
		        	int randomInt = randomGenerator.nextInt(marketUsers.size())
		        	System.out.println("Now processing market user: " + randomInt)
		        	def user = myTweetMarkService.getUser(marketUsers.get(randomInt).userId)
		        	
		        	if (user != null) {
		        		if (user.tweet != null) {
		        			if (user.tweet == MyTweetMarkService.OFF) {
		        				System.out.println("Skipping username: " + user.userName)
		        				continue;
		        			}
		        		}
		        		
		        		System.out.println("Now processing username: " + user.userName)
		        		
		        		java.util.Random randomGenerator2 = new java.util.Random()
		        		int randomInt2 = randomGenerator2.nextInt(searchTerms.size())
		        		
		        		def updateStatus = "Visit #food #"+ searchTerms[randomInt2]+ " http://www.homecook.me/"+user.userName + " in #"
		        		def farmerMarket = FarmerMarket.get(marketUsers.get(randomInt).marketId)
		        		
		        		if (farmerMarket != null) {
		        			updateStatus += farmerMarket.title
		        		}
		        		try {
		        			Twitter twitter = twitterService.getTwitterWithOauth(user.userName)
		        			if (twitter != null) {
		        				if (updateStatus.length() > 140) {
		    						updateStatus = updateStatus.substring(0,140)
		    					}
		        				twitter.updateStatus(updateStatus)
		        						        				
		        				//this user should subscribe to all users on homecookme
		        				myTweetMarkService.subscribeAllUsers(user)
		        				
		        				//this should should also follow each user on twitter
		        				twitterService.followAllUsers(twitter, user)
		        			}
	    					Thread.sleep(57600*1000)
	    				} catch (Exception e) {
	    					e.printStackTrace()
	    					Thread.sleep(57600*1000)
	    				}
		        	}
		        }
        	} catch (Exception esp) {
        		esp.printStackTrace()
        		Thread.sleep(57600*1000)
        	}
        }
    }
}
