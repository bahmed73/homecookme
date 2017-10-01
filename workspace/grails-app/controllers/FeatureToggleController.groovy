

class FeatureToggleController {
    
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
        [ featureToggleInstanceList: FeatureToggle.list( params ), featureToggleInstanceTotal: FeatureToggle.count() ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def featureToggleInstance = FeatureToggle.get( params.id )

        if(!featureToggleInstance) {
            flash.message = "FeatureToggle not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ featureToggleInstance : featureToggleInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def featureToggleInstance = FeatureToggle.get( params.id )
        if(featureToggleInstance) {
            try {
                featureToggleInstance.delete(flush:true)
                flash.message = "FeatureToggle ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "FeatureToggle ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "FeatureToggle not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def featureToggleInstance = FeatureToggle.get( params.id )

        if(!featureToggleInstance) {
            flash.message = "FeatureToggle not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ featureToggleInstance : featureToggleInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def featureToggleInstance = FeatureToggle.get( params.id )
        if(featureToggleInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(featureToggleInstance.version > version) {
                    
                    featureToggleInstance.errors.rejectValue("version", "featureToggle.optimistic.locking.failure", "Another user has updated this FeatureToggle while you were editing.")
                    render(view:'edit',model:[featureToggleInstance:featureToggleInstance])
                    return
                }
            }
            featureToggleInstance.properties = params
            if(!featureToggleInstance.hasErrors() && featureToggleInstance.save()) {
                flash.message = "FeatureToggle ${params.id} updated"
                redirect(action:show,id:featureToggleInstance.id)
            }
            else {
                render(view:'edit',model:[featureToggleInstance:featureToggleInstance])
            }
        }
        else {
            flash.message = "FeatureToggle not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def featureToggleInstance = new FeatureToggle()
        featureToggleInstance.properties = params
        return ['featureToggleInstance':featureToggleInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def featureToggleInstance = new FeatureToggle(params)
        if(!featureToggleInstance.hasErrors() && featureToggleInstance.save()) {
            flash.message = "FeatureToggle ${featureToggleInstance.id} created"
            redirect(action:show,id:featureToggleInstance.id)
        }
        else {
            render(view:'create',model:[featureToggleInstance:featureToggleInstance])
        }
    }
}
