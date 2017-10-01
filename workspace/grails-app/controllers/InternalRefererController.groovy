

class InternalRefererController {
    
	def myTweetMarkService
	
    def index = { 
			if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    		flash.message = "Please login as administrator to do this operation.";
	    		redirect(uri:"/")
	    	}
			redirect(action:list,params:params) 
			}

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ internalRefererInstanceList: InternalReferer.list( params ), internalRefererInstanceTotal: InternalReferer.count() ]
    }

    def show = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def internalRefererInstance = InternalReferer.get( params.id )

        if(!internalRefererInstance) {
            flash.message = "InternalReferer not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ internalRefererInstance : internalRefererInstance ] }
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def internalRefererInstance = InternalReferer.get( params.id )
        if(internalRefererInstance) {
            try {
                internalRefererInstance.delete(flush:true)
                flash.message = "InternalReferer ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "InternalReferer ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "InternalReferer not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def internalRefererInstance = InternalReferer.get( params.id )

        if(!internalRefererInstance) {
            flash.message = "InternalReferer not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ internalRefererInstance : internalRefererInstance ]
        }
    }

    def update = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def internalRefererInstance = InternalReferer.get( params.id )
        if(internalRefererInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(internalRefererInstance.version > version) {
                    
                    internalRefererInstance.errors.rejectValue("version", "internalReferer.optimistic.locking.failure", "Another user has updated this InternalReferer while you were editing.")
                    render(view:'edit',model:[internalRefererInstance:internalRefererInstance])
                    return
                }
            }
            internalRefererInstance.properties = params
            if(!internalRefererInstance.hasErrors() && internalRefererInstance.save()) {
                flash.message = "InternalReferer ${params.id} updated"
                redirect(action:show,id:internalRefererInstance.id)
            }
            else {
                render(view:'edit',model:[internalRefererInstance:internalRefererInstance])
            }
        }
        else {
            flash.message = "InternalReferer not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def internalRefererInstance = new InternalReferer()
        internalRefererInstance.properties = params
        return ['internalRefererInstance':internalRefererInstance]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def internalRefererInstance = new InternalReferer(params)
        if(!internalRefererInstance.hasErrors() && internalRefererInstance.save()) {
            flash.message = "InternalReferer ${internalRefererInstance.id} created"
            redirect(action:show,id:internalRefererInstance.id)
        }
        else {
            render(view:'create',model:[internalRefererInstance:internalRefererInstance])
        }
    }
    
    
    
}
