import com.google.code.facebookapi.*;

class MarketUserController {

	static final String MY_TOGGLE = "MarketUser";
	
	def myTweetMarkService
	def twitterService
	def facebookConnectService
	
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
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [marketUserInstanceList: MarketUser.list(params), marketUserInstanceTotal: MarketUser.count()]
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def marketUserInstance = new MarketUser()
        marketUserInstance.properties = params
        return [marketUserInstance: marketUserInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def marketUserInstance = new MarketUser(params)
        if (marketUserInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'marketUser.label', default: 'MarketUser'), marketUserInstance.id])}"
            redirect(action: "show", id: marketUserInstance.id)
        }
        else {
            render(view: "create", model: [marketUserInstance: marketUserInstance])
        }
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def marketUserInstance = MarketUser.get(params.id)
        if (!marketUserInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'marketUser.label', default: 'MarketUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            [marketUserInstance: marketUserInstance]
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def marketUserInstance = MarketUser.get(params.id)
        if (!marketUserInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'marketUser.label', default: 'MarketUser'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [marketUserInstance: marketUserInstance]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def marketUserInstance = MarketUser.get(params.id)
        if (marketUserInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (marketUserInstance.version > version) {
                    
                    marketUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'marketUser.label', default: 'MarketUser')] as Object[], "Another user has updated this MarketUser while you were editing")
                    render(view: "edit", model: [marketUserInstance: marketUserInstance])
                    return
                }
            }
            marketUserInstance.properties = params
            if (!marketUserInstance.hasErrors() && marketUserInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'marketUser.label', default: 'MarketUser'), marketUserInstance.id])}"
                redirect(action: "show", id: marketUserInstance.id)
            }
            else {
                render(view: "edit", model: [marketUserInstance: marketUserInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'marketUser.label', default: 'MarketUser'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def marketUserInstance = MarketUser.get(params.id)
        if (marketUserInstance) {
            try {
                marketUserInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'marketUser.label', default: 'MarketUser'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'marketUser.label', default: 'MarketUser'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'marketUser.label', default: 'MarketUser'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def joinMarket = {
    	if (session.userName == null) {
	    	flash.message = "Please login to do this operation.";
	    	redirect(uri:"/")
	    	return
	    } else {
	    	Users existUser = myTweetMarkService.getUser(session.userName)
	    	
	    	if (existUser.email == null || existUser.email.isEmpty()) {
	    		flash.message = "Please set your email to join a market.";
	    		session.profile = false
	    		redirect(action:'edit',controller:'users')
		    	return
	    	}
	    }
    	
    	myTweetMarkService.joinMarket(session.userId, Integer.valueOf(params.id))
    	
    	if (myTweetMarkService.getTweet(session.userId) == MyTweetMarkService.ON) {
				if (myTweetMarkService.isFaceBookUser(session.userId)) {
					try {
						if (facebookConnectService.isLoggedIn(request)) {
							FacebookJsonRestClient client = facebookConnectService.getFacebookClient()
							if (client.users_hasAppPermission(Permission.STATUS_UPDATE)) {
								client.users_setStatus("I just added my #business to #local #farmers & #food #market on http://www.homecook.me/" + session.userName, false);
							}
						} 
					} catch (Exception e) {
						System.out.println("Exception caught obtaining facebook")
						e.printStackTrace()
					}
				} else if (session.twitter != null) {
					def status = "I just added my #localfood #realfood #farmersmarket #business to http://www.homecook.me/" + session.userName + " cc #socialmedia #tech #fb" 
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
    	flash.message = "joined market successfully."
        redirect(action:'list',controller:'farmerMarket')
    }
	
	def unjoinMarket = {
    	if (session.userName == null) {
	    	flash.message = "Please login to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
    	
    	myTweetMarkService.unjoinMarket(session.userId, Integer.valueOf(params.id))
    	
    	flash.message = "unjoined successfully."
        redirect(action:'list',controller:'farmerMarket')
    }
}
