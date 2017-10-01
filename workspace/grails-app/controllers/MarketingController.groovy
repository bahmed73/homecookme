class MarketingController {
    
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
        [ marketingInstanceList: Marketing.list( params ) ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def marketingInstance = Marketing.get( params.id )

        if(!marketingInstance) {
            flash.message = "Marketing not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ marketingInstance : marketingInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def marketingInstance = Marketing.get( params.id )
        if(marketingInstance) {
            marketingInstance.delete()
            flash.message = "Marketing ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Marketing not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def marketingInstance = Marketing.get( params.id )

        if(!marketingInstance) {
            flash.message = "Marketing not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ marketingInstance : marketingInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def marketingInstance = Marketing.get( params.id )
        if(marketingInstance) {
            marketingInstance.properties = params
            if(!marketingInstance.hasErrors() && marketingInstance.save()) {
                flash.message = "Marketing ${params.id} updated"
                redirect(action:show,id:marketingInstance.id)
            }
            else {
                render(view:'edit',model:[marketingInstance:marketingInstance])
            }
        }
        else {
            flash.message = "Marketing not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def marketingInstance = new Marketing()
        marketingInstance.properties = params
        return ['marketingInstance':marketingInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def marketingInstance = new Marketing(params)
        if(!marketingInstance.hasErrors() && marketingInstance.save()) {
            flash.message = "Marketing ${marketingInstance.id} created"
            redirect(action:show,id:marketingInstance.id)
        }
        else {
            render(view:'create',model:[marketingInstance:marketingInstance])
        }
    }
}
