package app

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import grails.converters.JSON
import grails.converters.deep.JSON

@Path('/api/biz')
class BizResource {
	def myTweetMarkService
	
    @GET
    @Produces('application/json')
    String getBizRepresentation(@QueryParam('biz') String biz) {
    	try {
	    	def user = myTweetMarkService.getMobileUser(biz) // Get results. render results as JSON
	    	
	    	return user as JSON
	    	//or render converter as JSON
	    	
		} catch (Exception e) {
			e.printStackTrace()
			System.out.println("Exception in /api/biz: " + e.getMessage())
			return
		}
    }
    
}
