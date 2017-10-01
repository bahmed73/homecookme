import twitter4j.*;
import twitter4j.http.*;

class ViewSummaryJob {
	static triggers = {
	    simple name: 'mySimpleTrigger2', startDelay: 60000, repeatInterval: 1000*60*60*24l, repeatCount:0    
	  }
    //def timeout = 1000*60*60*2l // execute job once in 2 hours

    def myTweetMarkService
    def twitterService
    
    def execute() {
    	System.out.println("Inside ViewSummaryJob")
    	
    	Date currentTime = new Date();
    	System.out.println("Job start time: " + currentTime.toString())
        // execute task
        
        
        while (1) {
        	def views = Views.findAll()
            
	        if (views != null && !views.isEmpty()) {
	        	for (int i=0; i<views.size(); i++) {
	        		try {
	        			ViewSummary summary = new ViewSummary()
		        		summary.userId = views.get(i).userId
		        		summary.createTime = new Date()
		        		summary.num = views.get(i).num
		        		
		        		
		        		def user = myTweetMarkService.getUser(views.get(i).userId)
		        		Twitter twitter = null
		        		
		        		if (user != null) {
		        			
		        			twitter = twitterService.getTwitterWithOauth(user.userName)
		        			System.out.println("Processing user: " + user.userName)
		        			if (twitter != null) {
			        			User twitterUser = twitter.showUser(user.userName)
			        			
		        				if (twitterUser != null) {
		        					summary.followersCount = twitterUser.getFollowersCount()
		          			  		summary.friendsCount = twitterUser.getFriendsCount()
		          			  		
		        					user.followersCount = twitterUser.getFollowersCount()
		          			  		user.friendsCount = twitterUser.getFriendsCount()
		          			  		user.profilePhoto = twitterUser.getProfileImageURL().toString()
		          			  		user.backgroundUrl = twitterUser.getProfileBackgroundImageUrl().toString()
		        				}
			        			 
		        			}
		        			if  (user.hasErrors() || !user.save()) {
			        			System.out.println("Error storing userid:" + views.get(i).userId)
			        		}
		        			
		        			
		        		}
		        		
	        			if  (summary.hasErrors() || !summary.save()) {
		        			System.out.println("Error storing view summary:" + views.get(i).userId)
		        		}
	        			
		        		
		        		
	        		} catch (Exception e) {
	        			e.printStackTrace()
	        			System.out.println("caught exception: userid = " + views.get(i).userId)
	        		}
	        	}
	        } else {
	        	System.out.println("Didn't find any views")
	        }
        	Thread.sleep(86400*1000)
        }
    }
}
