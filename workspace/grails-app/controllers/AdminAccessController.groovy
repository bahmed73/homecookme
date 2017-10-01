

class AdminAccessController {
    
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
        [ adminAccessInstanceList: AdminAccess.list( params ), adminAccessInstanceTotal: AdminAccess.count() ]
    }

    def show = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def adminAccessInstance = AdminAccess.get( params.id )

        if(!adminAccessInstance) {
            flash.message = "AdminAccess not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ adminAccessInstance : adminAccessInstance ] }
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def adminAccessInstance = AdminAccess.get( params.id )
        if(adminAccessInstance) {
            try {
                adminAccessInstance.delete(flush:true)
                flash.message = "AdminAccess ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "AdminAccess ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "AdminAccess not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def adminAccessInstance = AdminAccess.get( params.id )

        if(!adminAccessInstance) {
            flash.message = "AdminAccess not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ adminAccessInstance : adminAccessInstance ]
        }
    }

    def update = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def adminAccessInstance = AdminAccess.get( params.id )
        if(adminAccessInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(adminAccessInstance.version > version) {
                    
                    adminAccessInstance.errors.rejectValue("version", "adminAccess.optimistic.locking.failure", "Another user has updated this AdminAccess while you were editing.")
                    render(view:'edit',model:[adminAccessInstance:adminAccessInstance])
                    return
                }
            }
            adminAccessInstance.properties = params
            if(!adminAccessInstance.hasErrors() && adminAccessInstance.save()) {
                flash.message = "AdminAccess ${params.id} updated"
                redirect(action:show,id:adminAccessInstance.id)
            }
            else {
                render(view:'edit',model:[adminAccessInstance:adminAccessInstance])
            }
        }
        else {
            flash.message = "AdminAccess not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def adminAccessInstance = new AdminAccess()
        adminAccessInstance.properties = params
        return ['adminAccessInstance':adminAccessInstance]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def adminAccessInstance = new AdminAccess(params)
        if(!adminAccessInstance.hasErrors() && adminAccessInstance.save()) {
            flash.message = "AdminAccess ${adminAccessInstance.id} created"
            redirect(action:show,id:adminAccessInstance.id)
        }
        else {
            render(view:'create',model:[adminAccessInstance:adminAccessInstance])
        }
    }
}
