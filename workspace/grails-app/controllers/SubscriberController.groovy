class SubscriberController {

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
        [subscriberInstanceList: Subscriber.list(params), subscriberInstanceTotal: Subscriber.count()]
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscriberInstance = new Subscriber()
        subscriberInstance.properties = params
        return [subscriberInstance: subscriberInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscriberInstance = new Subscriber(params)
        if (subscriberInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'subscriber.label', default: 'Subscriber'), subscriberInstance.id])}"
            redirect(action: "show", id: subscriberInstance.id)
        }
        else {
            render(view: "create", model: [subscriberInstance: subscriberInstance])
        }
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscriberInstance = Subscriber.get(params.id)
        if (!subscriberInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subscriber.label', default: 'Subscriber'), params.id])}"
            redirect(action: "list")
        }
        else {
            [subscriberInstance: subscriberInstance]
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscriberInstance = Subscriber.get(params.id)
        if (!subscriberInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subscriber.label', default: 'Subscriber'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [subscriberInstance: subscriberInstance]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscriberInstance = Subscriber.get(params.id)
        if (subscriberInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (subscriberInstance.version > version) {
                    
                    subscriberInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'subscriber.label', default: 'Subscriber')] as Object[], "Another user has updated this Subscriber while you were editing")
                    render(view: "edit", model: [subscriberInstance: subscriberInstance])
                    return
                }
            }
            subscriberInstance.properties = params
            if (!subscriberInstance.hasErrors() && subscriberInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'subscriber.label', default: 'Subscriber'), subscriberInstance.id])}"
                redirect(action: "show", id: subscriberInstance.id)
            }
            else {
                render(view: "edit", model: [subscriberInstance: subscriberInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subscriber.label', default: 'Subscriber'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def subscriberInstance = Subscriber.get(params.id)
        if (subscriberInstance) {
            try {
                subscriberInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'subscriber.label', default: 'Subscriber'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'subscriber.label', default: 'Subscriber'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'subscriber.label', default: 'Subscriber'), params.id])}"
            redirect(action: "list")
        }
    }
}
