class SubscribedController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def myTweetMarkService
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
        [subscribedInstanceList: Subscribed.list(params), subscribedInstanceTotal: Subscribed.count()]
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscribedInstance = new Subscribed()
        subscribedInstance.properties = params
        return [subscribedInstance: subscribedInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscribedInstance = new Subscribed(params)
        if (subscribedInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'subscribed.label', default: 'Subscribed'), subscribedInstance.id])}"
            redirect(action: "show", id: subscribedInstance.id)
        }
        else {
            render(view: "create", model: [subscribedInstance: subscribedInstance])
        }
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscribedInstance = Subscribed.get(params.id)
        if (!subscribedInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subscribed.label', default: 'Subscribed'), params.id])}"
            redirect(action: "list")
        }
        else {
            [subscribedInstance: subscribedInstance]
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscribedInstance = Subscribed.get(params.id)
        if (!subscribedInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subscribed.label', default: 'Subscribed'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [subscribedInstance: subscribedInstance]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscribedInstance = Subscribed.get(params.id)
        if (subscribedInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (subscribedInstance.version > version) {
                    
                    subscribedInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'subscribed.label', default: 'Subscribed')] as Object[], "Another user has updated this Subscribed while you were editing")
                    render(view: "edit", model: [subscribedInstance: subscribedInstance])
                    return
                }
            }
            subscribedInstance.properties = params
            if (!subscribedInstance.hasErrors() && subscribedInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'subscribed.label', default: 'Subscribed'), subscribedInstance.id])}"
                redirect(action: "show", id: subscribedInstance.id)
            }
            else {
                render(view: "edit", model: [subscribedInstance: subscribedInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subscribed.label', default: 'Subscribed'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscribedInstance = Subscribed.get(params.id)
        if (subscribedInstance) {
            try {
                subscribedInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'subscribed.label', default: 'Subscribed'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'subscribed.label', default: 'Subscribed'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subscribed.label', default: 'Subscribed'), params.id])}"
            redirect(action: "list")
        }
    }
    
    def subscribe = {
    	if (session.userName == null) {
	    	flash.message = "Please login to do this operation.";
	    	redirect(uri:"/")
	    	return
	    } 
    	
    	try {
    		myTweetMarkService.subscribe(session.userId, Long.valueOf(params.id))
    		flash.message = "subscribed successfully."
    		redirect(action:'myTweetMarks',controller:'myTweetMark')
    		return
    	} catch (Exception e) {
    		e.printStackTrace()
    		flash.message = "Error subscribing to user."
    		redirect(action:'myTweetMarks',controller:'myTweetMark')
    		return
    	}
    }
    
    def unsubscribe = {
    	if (session.userName == null) {
	    	flash.message = "Please login to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
    	
    	try {
    		myTweetMarkService.unsubscribe(session.userId, Long.valueOf(params.id))
    		flash.message = "unsubscribed successfully."
    		redirect(action:'myTweetMarks',controller:'myTweetMark')
    	} catch (Exception e) {
    		e.printStackTrace()
    		flash.message = "Error unsubscribing user."
    		redirect(action:'myTweetMarks',controller:'myTweetMark')
    		return
    	}
    }
}
