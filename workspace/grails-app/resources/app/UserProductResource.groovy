package app

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import grails.converters.JSON
import grails.converters.deep.JSON

@Path('/api/userProduct')
class UserProductResource {
	def myTweetMarkService
	
    @GET
    @Produces('application/json')
    String getUserProductRepresentation(@QueryParam('userId') String userId) {
		try {
	    	def results = myTweetMarkService.getMyProducts(Long.valueOf(userId)) // Get results. render results as JSON
	    	return results as JSON
	    	//or render converter as JSON
	    	
		} catch (Exception e) {
			e.printStackTrace()
			System.out.println("Exception in /api/userProduct: " + e.getMessage())
			return
		}
    }
    
}
