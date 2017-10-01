package app

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import grails.converters.JSON
import grails.converters.deep.JSON

@Path('/api/search')
class SearchResource {
	def myTweetMarkService
	
    @GET
    @Produces('application/json')
    String getSearchRepresentation(@QueryParam('search') String search) {
		try {
	    	def results = myTweetMarkService.search(search) // Get results. render results as JSON
	    	return results as JSON
	    	//or render converter as JSON
	    	
		} catch (Exception e) {
			e.printStackTrace()
			System.out.println("Exception in /api/search: " + e.getMessage())
			return
		}
    }
    
}
