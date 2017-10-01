import twitter4j.*;
import twitter4j.http.*;
import java.util.*;

class ProductsJob {
	static triggers = {
	    simple name: 'mySimpleTrigger4', startDelay: 60000, repeatInterval: 1000*60*60*1l,repeatCount:0  
	  }
    //def timeout = 1000*60*60*2l // execute job once in 2 hours

    def searchTerms = ["celebrity", "business", "blogger", "marketing", "media", "writer", "entrepreneur", "technology", "artist", "socialmedia", "localfood", "farmersmarket", "smallfarm", "agchat", "realfood", "buylocal", "organic", "natural", "local", "farmersmarkets"]
    def myTweetMarkService
    def twitterService

    def execute() {
        // execute task
        System.out.println("Inside ProductsJob")
    	Date currentTime = new Date();
    	System.out.println("Job start time: " + currentTime.toString())
        while (1) {
        	try {
		        def products = Product.findAllByStatus(1)
		        
		        if (products != null && !products.isEmpty()) {
		        	java.util.Random randomGenerator = new java.util.Random()
		        	int randomInt = randomGenerator.nextInt(products.size())
		        	System.out.println("Now processing product: " + randomInt)
		        	def user = myTweetMarkService.getUser(products.get(randomInt).userId)
		        	
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
		        		
		        		def updateStatus = "Our #" + searchTerms[randomInt2] + " #food product, "  
		        		def updateStatus2 = ": Find at http://www.homecook.me/"+user.userName		        		
		        		def product = Product.get(products.get(randomInt).id)
		        		
		        		if (product != null) {
		        			updateStatus += product.name
		        		}
		        		
		        		updateStatus+=updateStatus2
		        		try {
		        			Twitter twitter = twitterService.getTwitterWithOauth(user.userName)
		        			if (twitter != null) {
		        				if (updateStatus.length() > 140) {
		    						updateStatus = updateStatus.substring(0,140)
		    					}
		        				twitter.updateStatus(updateStatus)
		        			}
	    					Thread.sleep(14400*1000)
	    				} catch (Exception e) {
	    					e.printStackTrace()
	    					Thread.sleep(14400*1000)
	    				}
		        	}
		        }
        	} catch (Exception esp) {
        		esp.printStackTrace()
        		Thread.sleep(14400*1000)
        	}
        }
    }
}
