

class ViralSummaryController {
    
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
        [ viralSummaryInstanceList: ViralSummary.list( params ), viralSummaryInstanceTotal: ViralSummary.count() ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viralSummaryInstance = ViralSummary.get( params.id )

        if(!viralSummaryInstance) {
            flash.message = "ViralSummary not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ viralSummaryInstance : viralSummaryInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viralSummaryInstance = ViralSummary.get( params.id )
        if(viralSummaryInstance) {
            try {
                viralSummaryInstance.delete(flush:true)
                flash.message = "ViralSummary ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ViralSummary ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ViralSummary not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viralSummaryInstance = ViralSummary.get( params.id )

        if(!viralSummaryInstance) {
            flash.message = "ViralSummary not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ viralSummaryInstance : viralSummaryInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viralSummaryInstance = ViralSummary.get( params.id )
        if(viralSummaryInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(viralSummaryInstance.version > version) {
                    
                    viralSummaryInstance.errors.rejectValue("version", "viralSummary.optimistic.locking.failure", "Another user has updated this ViralSummary while you were editing.")
                    render(view:'edit',model:[viralSummaryInstance:viralSummaryInstance])
                    return
                }
            }
            viralSummaryInstance.properties = params
            if(!viralSummaryInstance.hasErrors() && viralSummaryInstance.save()) {
                flash.message = "ViralSummary ${params.id} updated"
                redirect(action:show,id:viralSummaryInstance.id)
            }
            else {
                render(view:'edit',model:[viralSummaryInstance:viralSummaryInstance])
            }
        }
        else {
            flash.message = "ViralSummary not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viralSummaryInstance = new ViralSummary()
        viralSummaryInstance.properties = params
        return ['viralSummaryInstance':viralSummaryInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viralSummaryInstance = new ViralSummary(params)
        if(!viralSummaryInstance.hasErrors() && viralSummaryInstance.save()) {
            flash.message = "ViralSummary ${viralSummaryInstance.id} created"
            redirect(action:show,id:viralSummaryInstance.id)
        }
        else {
            render(view:'create',model:[viralSummaryInstance:viralSummaryInstance])
        }
    }
}
