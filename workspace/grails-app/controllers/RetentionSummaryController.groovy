

class RetentionSummaryController {
    
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
        [ retentionSummaryInstanceList: RetentionSummary.list( params ), retentionSummaryInstanceTotal: RetentionSummary.count() ]
    }

    def show = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def retentionSummaryInstance = RetentionSummary.get( params.id )

        if(!retentionSummaryInstance) {
            flash.message = "RetentionSummary not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ retentionSummaryInstance : retentionSummaryInstance ] }
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def retentionSummaryInstance = RetentionSummary.get( params.id )
        if(retentionSummaryInstance) {
            try {
                retentionSummaryInstance.delete(flush:true)
                flash.message = "RetentionSummary ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "RetentionSummary ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "RetentionSummary not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def retentionSummaryInstance = RetentionSummary.get( params.id )

        if(!retentionSummaryInstance) {
            flash.message = "RetentionSummary not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ retentionSummaryInstance : retentionSummaryInstance ]
        }
    }

    def update = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def retentionSummaryInstance = RetentionSummary.get( params.id )
        if(retentionSummaryInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(retentionSummaryInstance.version > version) {
                    
                    retentionSummaryInstance.errors.rejectValue("version", "retentionSummary.optimistic.locking.failure", "Another user has updated this RetentionSummary while you were editing.")
                    render(view:'edit',model:[retentionSummaryInstance:retentionSummaryInstance])
                    return
                }
            }
            retentionSummaryInstance.properties = params
            if(!retentionSummaryInstance.hasErrors() && retentionSummaryInstance.save()) {
                flash.message = "RetentionSummary ${params.id} updated"
                redirect(action:show,id:retentionSummaryInstance.id)
            }
            else {
                render(view:'edit',model:[retentionSummaryInstance:retentionSummaryInstance])
            }
        }
        else {
            flash.message = "RetentionSummary not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def retentionSummaryInstance = new RetentionSummary()
        retentionSummaryInstance.properties = params
        return ['retentionSummaryInstance':retentionSummaryInstance]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def retentionSummaryInstance = new RetentionSummary(params)
        if(!retentionSummaryInstance.hasErrors() && retentionSummaryInstance.save()) {
            flash.message = "RetentionSummary ${retentionSummaryInstance.id} created"
            redirect(action:show,id:retentionSummaryInstance.id)
        }
        else {
            render(view:'create',model:[retentionSummaryInstance:retentionSummaryInstance])
        }
    }
}
