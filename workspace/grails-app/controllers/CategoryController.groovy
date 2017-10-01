class CategoryController {
    
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
        [ categoryInstanceList: Category.list( params ) ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    		
        def categoryInstance = Category.get( params.id )

        if(!categoryInstance) {
            flash.message = "Category not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ categoryInstance : categoryInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    		
        def categoryInstance = Category.get( params.id )
        if(categoryInstance) {
            categoryInstance.delete()
            flash.message = "Category ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Category not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    		
        def categoryInstance = Category.get( params.id )

        if(!categoryInstance) {
            flash.message = "Category not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ categoryInstance : categoryInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    		
        def categoryInstance = Category.get( params.id )
        if(categoryInstance) {
            categoryInstance.properties = params
            if(!categoryInstance.hasErrors() && categoryInstance.save()) {
                flash.message = "Category ${params.id} updated"
                redirect(action:show,id:categoryInstance.id)
            }
            else {
                render(view:'edit',model:[categoryInstance:categoryInstance])
            }
        }
        else {
            flash.message = "Category not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    		
        def categoryInstance = new Category()
        categoryInstance.properties = params
        return ['categoryInstance':categoryInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    		
        def categoryInstance = new Category(params)
        if(!categoryInstance.hasErrors() && categoryInstance.save()) {
            flash.message = "Category ${categoryInstance.id} created"
            redirect(action:show,id:categoryInstance.id)
        }
        else {
            render(view:'create',model:[categoryInstance:categoryInstance])
        }
    }
}
