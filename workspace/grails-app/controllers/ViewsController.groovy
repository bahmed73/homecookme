

class ViewsController {
    
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
        [ viewsInstanceList: Views.list( params ), viewsInstanceTotal: Views.count() ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viewsInstance = Views.get( params.id )

        if(!viewsInstance) {
            flash.message = "Views not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ viewsInstance : viewsInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viewsInstance = Views.get( params.id )
        if(viewsInstance) {
            try {
                viewsInstance.delete(flush:true)
                flash.message = "Views ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Views ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Views not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viewsInstance = Views.get( params.id )

        if(!viewsInstance) {
            flash.message = "Views not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ viewsInstance : viewsInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viewsInstance = Views.get( params.id )
        if(viewsInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(viewsInstance.version > version) {
                    
                    viewsInstance.errors.rejectValue("version", "views.optimistic.locking.failure", "Another user has updated this Views while you were editing.")
                    render(view:'edit',model:[viewsInstance:viewsInstance])
                    return
                }
            }
            viewsInstance.properties = params
            if(!viewsInstance.hasErrors() && viewsInstance.save()) {
                flash.message = "Views ${params.id} updated"
                redirect(action:show,id:viewsInstance.id)
            }
            else {
                render(view:'edit',model:[viewsInstance:viewsInstance])
            }
        }
        else {
            flash.message = "Views not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viewsInstance = new Views()
        viewsInstance.properties = params
        return ['viewsInstance':viewsInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def viewsInstance = new Views(params)
        if(!viewsInstance.hasErrors() && viewsInstance.save()) {
            flash.message = "Views ${viewsInstance.id} created"
            redirect(action:show,id:viewsInstance.id)
        }
        else {
            render(view:'create',model:[viewsInstance:viewsInstance])
        }
    }
}
