package app

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import grails.converters.JSON
import grails.converters.deep.JSON

@Path('/api/markets')
class MarketsResource {
	def myTweetMarkService
	
    @GET
    @Produces('application/json')
    String getMarketsRepresentation(@QueryParam('searchMarket') String searchMarket) {
		try {
	    	def markets = myTweetMarkService.searchMarket(searchMarket) // Get results. render results as JSON
	    	return markets as JSON
	    	//or render converter as JSON
	    	
		} catch (Exception e) {
			e.printStackTrace()
			System.out.println("Exception in /api/markets: " + e.getMessage())
			return
		}
    }
    
}
