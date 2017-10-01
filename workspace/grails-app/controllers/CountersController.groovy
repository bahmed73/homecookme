class CountersController {

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
        [countersInstanceList: Counters.list(params), countersInstanceTotal: Counters.count()]
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def countersInstance = new Counters()
        countersInstance.properties = params
        return [countersInstance: countersInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def countersInstance = new Counters(params)
        if (countersInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'counters.label', default: 'Counters'), countersInstance.id])}"
            redirect(action: "show", id: countersInstance.id)
        }
        else {
            render(view: "create", model: [countersInstance: countersInstance])
        }
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def countersInstance = Counters.get(params.id)
        if (!countersInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'counters.label', default: 'Counters'), params.id])}"
            redirect(action: "list")
        }
        else {
            [countersInstance: countersInstance]
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def countersInstance = Counters.get(params.id)
        if (!countersInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'counters.label', default: 'Counters'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [countersInstance: countersInstance]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def countersInstance = Counters.get(params.id)
        if (countersInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (countersInstance.version > version) {
                    
                    countersInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'counters.label', default: 'Counters')] as Object[], "Another user has updated this Counters while you were editing")
                    render(view: "edit", model: [countersInstance: countersInstance])
                    return
                }
            }
            countersInstance.properties = params
            if (!countersInstance.hasErrors() && countersInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'counters.label', default: 'Counters'), countersInstance.id])}"
                redirect(action: "show", id: countersInstance.id)
            }
            else {
                render(view: "edit", model: [countersInstance: countersInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'counters.label', default: 'Counters'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def countersInstance = Counters.get(params.id)
        if (countersInstance) {
            try {
                countersInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'counters.label', default: 'Counters'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'counters.label', default: 'Counters'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'counters.label', default: 'Counters'), params.id])}"
            redirect(action: "list")
        }
    }
}
