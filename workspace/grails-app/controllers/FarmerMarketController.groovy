import com.google.code.facebookapi.*;
import grails.converters.*

class FarmerMarketController {

	static final String MY_TOGGLE = "FarmerMarket";
	
	def myTweetMarkService
	def twitterService
	Integer displaySend
	Integer isFaceBookUser
	def facebookConnectService
	def user
	def farmerMarketInstance
	def farmerMarketInstanceList
	def farmerMarketInstanceTotal
	def farmerMarketInstanceListAll
	def subscribed
	def users
	def marketInstance
	def marketMap
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        redirect(action: "list", params: params)
    }

    def list = {
		params.max = Math.min( params.max ? params.max.toInteger() : 5,  100)
        if (params.offset == null) {
        	params.offset = 0
        }
        Integer paramInt = Integer.valueOf(params.offset)
        farmerMarketInstanceListAll = myTweetMarkService.getMarkets()
        farmerMarketInstanceList = myTweetMarkService.getMarketsWithUserInfo(paramInt, params.max)
        farmerMarketInstanceTotal = myTweetMarkService.getMarketCount()
        marketMap = myTweetMarkService.getMarketsMap(farmerMarketInstanceList)
        	
        if (session.userId != null) {
        	displaySend = myTweetMarkService.getTweet(session.userId)
        	isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
        	user = myTweetMarkService.getUser(session.userId)
        }
        session.profile = false
    }
	
	def all = {
		
        if (session.userId != null) {
        	displaySend = myTweetMarkService.getTweet(session.userId)
        	isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
        	user = myTweetMarkService.getUser(session.userId)
        }
        session.profile = false
    }

    def create = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		session.profile = false
        user = myTweetMarkService.getUser(session.userId)
    }

	def map = {
		session.profile = false
		if (session.userId != null) {
        	displaySend = myTweetMarkService.getTweet(session.userId)
        	isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
        	user = myTweetMarkService.getUser(session.userId)
        }
    }
	
    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def farmerMarketInstance = new FarmerMarket(params)
        if (farmerMarketInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'farmerMarket.label', default: 'FarmerMarket'), farmerMarketInstance.id])}"
            redirect(action: "show", id: farmerMarketInstance.id)
        }
        else {
            render(view: "create", model: [farmerMarketInstance: farmerMarketInstance])
        }
    }

    def show = {
		try {
			farmerMarketInstanceListAll = myTweetMarkService.getMarkets()
	        farmerMarketInstance = FarmerMarket.get(params.id)
	        if (!farmerMarketInstance) {
	        	flash.message = "Market not found."
	                redirect(action:'myTweetMarks',controller:'myTweetMark')
	                return
	        }
	        else {
	        	if (session.userId != null) {
		        	displaySend = myTweetMarkService.getTweet(session.userId)
		            isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
		            
					//(viewer, profile).  check whether the viewer is subscribed to profile, if viewer logged in.
					subscribed = myTweetMarkService.isUserInMarket(session.userId, Integer.valueOf(params.id))
					user = myTweetMarkService.getUser(session.userId)
				} else {
					//default to user is not subscribed to give them a chance to subscribe.
					subscribed = false
				}
	        	users = myTweetMarkService.getMarketUsers(Integer.valueOf(params.id))
	        	session.profile = false
	        }
		} catch (Exception e) {
			e.printStackTrace()
			flash.message = "Market not found."
			redirect(action:'myTweetMarks',controller:'myTweetMark')
            return
		}
    }

    def edit = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		user = myTweetMarkService.getUser(session.userId)
        marketInstance = FarmerMarket.get(params.id)
        if (!marketInstance) {
            flash.message = "Farmer market not found."
            redirect(action:'myTweetMarks',controller:'myTweetMark')
        }
        else {
    		displaySend = myTweetMarkService.getTweet(session.userId)
            isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
            user = myTweetMarkService.getUser(session.userId)
            session.profile = false
        }
    }

	def update = {
		System.out.println("inside update")
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		def farmerMarketInstance = new FarmerMarket()
        farmerMarketInstance.properties = params
        
        if (params.id != null) {
        	farmerMarketInstance.id = Long.valueOf(params.id)
        }
        
		def update = (farmerMarketInstance.id==null)?false:true;
		
		def returnVal;
		
		try {
			returnVal = myTweetMarkService.createMyFarmerMarket(session.userId, farmerMarketInstance, update)
			
			def transferFile = request.getFile('myFile')
		    if(transferFile != null && !transferFile.empty) {
		    	println "file is not empty, transferring"
		    	String fileName
		    	
		    	switch (grails.util.Environment.current) {
		    	case grails.util.Environment.TEST:
		    		fileName = "C:\\Users\\home\\homecook\\app\\web-app\\images\\USERPIC_"+farmerMarketInstance.id
		    		//DEV transfer file not allowed!!!  WARNING!!!
		    		//File file = new File(fileName)
		    		//transferFile.transferTo( file )
		    		//myTweetMarkService.uploadPhoto(session.userId, file)
		    		break
		    	case grails.util.Environment.PRODUCTION:
		    		fileName = MyTweetMarkService.FILE_STORAGE_PREFIX+farmerMarketInstance.id
		    		//only tranfer file in production.  
		    		File file = new File(fileName)
		    		transferFile.transferTo( file )
		    		myTweetMarkService.uploadPhoto(farmerMarketInstance.id, file)
		    		break
		    	}
		    	
		    }    
		    else {
		       println "file is empty"
		    }
		} catch (Exception e) {
			e.printStackTrace()
			flash.message = "Problem storing farmer market.  Please try 'typing in' details, rather 'copy and paste'."
				redirect(action:'myTweetMarks',controller:'myTweetMark')
				return
		}
        if (returnVal == MyTweetMarkService.SUCCESS) {
        	if (myTweetMarkService.getTweet(session.userId) == MyTweetMarkService.ON) {
				if (myTweetMarkService.isFaceBookUser(session.userId)) {
					try {
						if (facebookConnectService.isLoggedIn(request)) {
							FacebookJsonRestClient client = facebookConnectService.getFacebookClient()
							if (client.users_hasAppPermission(Permission.STATUS_UPDATE)) {
								client.users_setStatus("I added a new #farmers #market on http://www.homecook.me/" + session.userName, false);
							}
						} 
					} catch (Exception e) {
						System.out.println("Exception caught obtaining facebook")
						e.printStackTrace()
					}
				} else if (session.twitter != null) {
					def status = "New #food #farmersmarket on http://www.homecook.me/farmerMarket/show/" + farmerMarketInstance.id + " #" + farmerMarketInstance.city + " #" + farmerMarketInstance.state + " #" + farmerMarketInstance.title 
					if (status.length() > 140) {
						status = status.substring(0,140)
					}
					try {
						session.twitter.updateStatus(status)
					} catch (Exception e) {
						e.printStackTrace()
					}
					
				} 
			}
			flash.message = "Successfully updated farmers market.";
		} else {
			flash.message = "Cannot update farmers market at this time.  Please try again later.";
		}
		redirect(action:'myTweetMarks',controller:'myTweetMark')
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def farmerMarketInstance = FarmerMarket.get(params.id)
        if (farmerMarketInstance) {
            try {
                farmerMarketInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'farmerMarket.label', default: 'FarmerMarket'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'farmerMarket.label', default: 'FarmerMarket'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'farmerMarket.label', default: 'FarmerMarket'), params.id])}"
            redirect(action: "list")
        }
    }
	
	
	def ajaxGetMarkets = {
		def results = FarmerMarket.findAllByStatus(MyTweetMarkService.ACTIVE, [sort:"title", order:"asc"] )
		def newResults = new ArrayList()
		
		if (results!=null && results.size()>0) {
			for (int i=0; i<results.size(); i++) {
				if (results.get(i).timings.toLowerCase().contains(params.day.toLowerCase())) {
					newResults.add(results.get(i))
				}
			}
		} 
		render newResults as JSON
	}
}
