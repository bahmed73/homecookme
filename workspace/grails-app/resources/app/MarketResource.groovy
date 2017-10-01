package app

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import grails.converters.JSON
import grails.converters.deep.JSON

@Path('/api/market')
class MarketResource {
	def myTweetMarkService
	
    @GET
    @Produces('application/json')
    String getMarketRepresentation(@QueryParam('marketId') String marketId) {
    	try {
	    	def market = myTweetMarkService.getMobileMarket(marketId) // Get results. render results as JSON
	    	return market as JSON
	    	//or render converter as JSON
	    	
		} catch (Exception e) {
			e.printStackTrace()
			System.out.println("Exception in /api/market: " + e.getMessage())
			return
		}
    }
    
}
