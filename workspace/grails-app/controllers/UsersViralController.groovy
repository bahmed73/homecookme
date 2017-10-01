

class UsersViralController {
    
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
        [ usersViralInstanceList: UsersViral.list( params ), usersViralInstanceTotal: UsersViral.count() ]
    }

    def show = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def usersViralInstance = UsersViral.get( params.id )

        if(!usersViralInstance) {
            flash.message = "UsersViral not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ usersViralInstance : usersViralInstance ] }
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def usersViralInstance = UsersViral.get( params.id )
        if(usersViralInstance) {
            try {
                usersViralInstance.delete(flush:true)
                flash.message = "UsersViral ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "UsersViral ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "UsersViral not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def usersViralInstance = UsersViral.get( params.id )

        if(!usersViralInstance) {
            flash.message = "UsersViral not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ usersViralInstance : usersViralInstance ]
        }
    }

    def update = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def usersViralInstance = UsersViral.get( params.id )
        if(usersViralInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(usersViralInstance.version > version) {
                    
                    usersViralInstance.errors.rejectValue("version", "usersViral.optimistic.locking.failure", "Another user has updated this UsersViral while you were editing.")
                    render(view:'edit',model:[usersViralInstance:usersViralInstance])
                    return
                }
            }
            usersViralInstance.properties = params
            if(!usersViralInstance.hasErrors() && usersViralInstance.save()) {
                flash.message = "UsersViral ${params.id} updated"
                redirect(action:show,id:usersViralInstance.id)
            }
            else {
                render(view:'edit',model:[usersViralInstance:usersViralInstance])
            }
        }
        else {
            flash.message = "UsersViral not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def usersViralInstance = new UsersViral()
        usersViralInstance.properties = params
        return ['usersViralInstance':usersViralInstance]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
        def usersViralInstance = new UsersViral(params)
        if(!usersViralInstance.hasErrors() && usersViralInstance.save()) {
            flash.message = "UsersViral ${usersViralInstance.id} created"
            redirect(action:show,id:usersViralInstance.id)
        }
        else {
            render(view:'create',model:[usersViralInstance:usersViralInstance])
        }
    }
}
