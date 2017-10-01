package app

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import grails.converters.JSON
import grails.converters.deep.JSON

@Path('/api/product')
class ProductResource {
	def myTweetMarkService
	
    @GET
    @Produces('application/json')
    String getProductRepresentation(@QueryParam('productId') String productId) {
		try {
	    	def product = myTweetMarkService.getMobileProduct(productId) // Get results. render results as JSON
	    	return product as JSON
	    	//or render converter as JSON
	    	
		} catch (Exception e) {
			e.printStackTrace()
			System.out.println("Exception in /api/product: " + e.getMessage())
			return
		}
    }
    
}
