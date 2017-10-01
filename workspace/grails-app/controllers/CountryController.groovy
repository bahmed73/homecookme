class CountryController {
    
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
        [ countryInstanceList: Country.list( params ) ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def countryInstance = Country.get( params.id )

        if(!countryInstance) {
            flash.message = "Country not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ countryInstance : countryInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def countryInstance = Country.get( params.id )
        if(countryInstance) {
            countryInstance.delete()
            flash.message = "Country ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "Country not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def countryInstance = Country.get( params.id )

        if(!countryInstance) {
            flash.message = "Country not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ countryInstance : countryInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
    		
        def countryInstance = Country.get( params.id )
        if(countryInstance) {
            countryInstance.properties = params
            if(!countryInstance.hasErrors() && countryInstance.save()) {
                flash.message = "Country ${params.id} updated"
                redirect(action:show,id:countryInstance.id)
            }
            else {
                render(view:'edit',model:[countryInstance:countryInstance])
            }
        }
        else {
            flash.message = "Country not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def countryInstance = new Country()
        countryInstance.properties = params
        return ['countryInstance':countryInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def countryInstance = new Country(params)
        if(!countryInstance.hasErrors() && countryInstance.save()) {
            flash.message = "Country ${countryInstance.id} created"
            redirect(action:show,id:countryInstance.id)
        }
        else {
            render(view:'create',model:[countryInstance:countryInstance])
        }
    }
}
