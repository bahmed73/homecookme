import twitter4j.*;
import twitter4j.http.*;
import java.util.*;

class AutoTweetJob {
	static triggers = {
	    simple name: 'mySimpleTrigger', startDelay: 600000, repeatInterval: 1000*60*60*1l,repeatCount:0  
	  }
    //def timeout = 1000*60*60*2l // execute job once in 2 hours

    def searchTerms = ["celebrity", "business", "blogger", "marketing", "media", "writer", "entrepreneur", "technology", "artist", "socialmedia", "localfood", "farmersmarket", "smallfarm", "agchat", "realfood", "buylocal", "organic", "natural", "local", "farmersmarkets"]
    def myTweetMarkService
    def twitterService
    
    def execute() {
    	System.out.println("Inside AutoTweetJob")
    	Date currentTime = new Date();
    	System.out.println("Job start time: " + currentTime.toString())
        // execute task
        
        Twitter twitter = twitterService.getTwitterWithOauth("homecookme")
        
        if (twitter != null) {
        	System.out.println("Got twitter object for AutoTweetJob")
        	
        	def markets = myTweetMarkService.getMarketsWithUserInfo(0,124)
        	
        	if (markets != null && !markets.isEmpty()) {
        		
        		while (1) {
	        		java.util.Random randomGenerator = new java.util.Random()
		        	int randomInt = randomGenerator.nextInt(markets.size())
		        	System.out.println("Now processing market: " + randomInt)
		        	def tweetMarket = markets.get(randomInt)
		        	
	        		
	    			def market = tweetMarket.market
	    			def users = tweetMarket.users
	        			
	    			java.util.Random randomGenerator2 = new java.util.Random()
	        		int randomInt2 = randomGenerator2.nextInt(searchTerms.size())
	        		
	    			String updateStatus = "#farmersmarket "+market.title+ " cc #food #" +  searchTerms[randomInt2] + " "
	    			String updateStatus2 = "#farmersmarket #realfood #localfood #" + market.title + " http://www.homecook.me/farmerMarket/show/"+market.id+" "
					System.out.println("updateStatus = " + updateStatus)
						
					if (users != null && !users.isEmpty()) {
						for (int j=0; j<users.size(); j++) {
							if (updateStatus.length()<141) {
								updateStatus+="@"+users.get(j).userName+" "
							} else 
								break;
						}
					}
					if (updateStatus.length() > 140) {
						updateStatus = updateStatus.substring(0,140)
					}
					if (updateStatus2.length() > 140) {
						updateStatus2 = updateStatus2.substring(0,140)
					}
					try {
						twitter.updateStatus(updateStatus)
						twitter.updateStatus(updateStatus2)
						Thread.sleep(3600*1000*8)
							
					} catch (Exception ex) {
						ex.printStackTrace()
						Thread.sleep(3600*1000*8)
					}
        		} 
        		
        	}
        	
        }
	}
    
	
	def getRecipes() {
		
		def allRecipes = new ArrayList()
    	
        //hotandspicyfood user(7)
        def recipes = Recipe.findAllByUserId(7)
        allRecipes.addAll(recipes)
        
        //soulsoups user(5)
        recipes = Recipe.findAllByUserId(5)
	    allRecipes.addAll(recipes)
	      
	    //childrecipes()11
	    recipes = Recipe.findAllByUserId(11)
		allRecipes.addAll(recipes)
		    
		//yogacuisine 
		recipes = Recipe.findAllByUserId(12)
		allRecipes.addAll(recipes)
	
        
		//seafoodcraving user(14)
		recipes = Recipe.findAllByUserId(14)
		allRecipes.addAll(recipes)
	
       
		//my_indian_food user(35)
		recipes = Recipe.findAllByUserId(35)
		allRecipes.addAll(recipes)
        
       
		//my_chinese_food user(36)
		recipes = Recipe.findAllByUserId(36)
		allRecipes.addAll(recipes)
        
        
		//my_mexican_food user(37)
		recipes = Recipe.findAllByUserId(37)
		allRecipes.addAll(recipes)
        
       
		//mediterranean_f user(38)
		recipes = Recipe.findAllByUserId(38)
		allRecipes.addAll(recipes)
        
       
		//my_japanesefood user(39)
		recipes = Recipe.findAllByUserId(39)
		allRecipes.addAll(recipes)
        
       
		//my_vegetarian user(40)
		recipes = Recipe.findAllByUserId(40)
		allRecipes.addAll(recipes)
        
        
		//my_amercianfood user(41)
		recipes = Recipe.findAllByUserId(41)
		allRecipes.addAll(recipes)
        
        
		//my_italian_food user(42)
		recipes = Recipe.findAllByUserId(42)
		allRecipes.addAll(recipes)
        
        
		//my_french_food user(43)
		recipes = Recipe.findAllByUserId(43)
		allRecipes.addAll(recipes)
        
        
		//my_arabic_food user(44)
		recipes = Recipe.findAllByUserId(44)
		allRecipes.addAll(recipes)
        
        
		//my_german_food user(45)
		recipes = Recipe.findAllByUserId(45)
		allRecipes.addAll(recipes)
		
		return allRecipes
	}
}