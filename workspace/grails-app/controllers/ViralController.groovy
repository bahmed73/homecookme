class ViralController {
    
	def myTweetMarkService
	
    def index = { 
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		
		redirect(action:list,params:params) 
	}

    // the delete, save and update actions only accept POST requests
    def static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        if(!params.max) params.max = 10
        [ viralInstanceList: Viral.list( params ) ]
    }

    def show = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def viralInstance = Viral.get( params.id )

        if(!viralInstance) {
            flash.message = "Viral not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ viralInstance : viralInstance ] }
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def viralInstance = Viral.get( params.id )
        if(viralInstance) {
            viralInstance.delete()
            flash.message = "Viral ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Viral not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def viralInstance = Viral.get( params.id )

        if(!viralInstance) {
            flash.message = "Viral not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ viralInstance : viralInstance ]
        }
    }

    def update = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def viralInstance = Viral.get( params.id )
        if(viralInstance) {
            viralInstance.properties = params
            if(!viralInstance.hasErrors() && viralInstance.save()) {
                flash.message = "Viral ${params.id} updated"
                redirect(action:show,id:viralInstance.id)
            }
            else {
                render(view:'edit',model:[viralInstance:viralInstance])
            }
        }
        else {
            flash.message = "Viral not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def viralInstance = new Viral()
        viralInstance.properties = params
        return ['viralInstance':viralInstance]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def viralInstance = new Viral(params)
        if(!viralInstance.hasErrors() && viralInstance.save()) {
            flash.message = "Viral ${viralInstance.id} created"
            redirect(action:show,id:viralInstance.id)
        }
        else {
            render(view:'create',model:[viralInstance:viralInstance])
        }
    }
}
