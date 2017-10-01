

class CategoriesController {
    
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
        [ categoriesInstanceList: Categories.list( params ), categoriesInstanceTotal: Categories.count() ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def categoriesInstance = Categories.get( params.id )

        if(!categoriesInstance) {
            flash.message = "Categories not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ categoriesInstance : categoriesInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def categoriesInstance = Categories.get( params.id )
        if(categoriesInstance) {
            try {
                categoriesInstance.delete(flush:true)
                flash.message = "Categories ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Categories ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Categories not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def categoriesInstance = Categories.get( params.id )

        if(!categoriesInstance) {
            flash.message = "Categories not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ categoriesInstance : categoriesInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def categoriesInstance = Categories.get( params.id )
        if(categoriesInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(categoriesInstance.version > version) {
                    
                    categoriesInstance.errors.rejectValue("version", "categories.optimistic.locking.failure", "Another user has updated this Categories while you were editing.")
                    render(view:'edit',model:[categoriesInstance:categoriesInstance])
                    return
                }
            }
            categoriesInstance.properties = params
            if(!categoriesInstance.hasErrors() && categoriesInstance.save()) {
                flash.message = "Categories ${params.id} updated"
                redirect(action:show,id:categoriesInstance.id)
            }
            else {
                render(view:'edit',model:[categoriesInstance:categoriesInstance])
            }
        }
        else {
            flash.message = "Categories not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def categoriesInstance = new Categories()
        categoriesInstance.properties = params
        return ['categoriesInstance':categoriesInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    	
        def categoriesInstance = new Categories(params)
        if(!categoriesInstance.hasErrors() && categoriesInstance.save()) {
            flash.message = "Categories ${categoriesInstance.id} created"
            redirect(action:show,id:categoriesInstance.id)
        }
        else {
            render(view:'create',model:[categoriesInstance:categoriesInstance])
        }
    }
}
