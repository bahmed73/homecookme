

class ExternalIpController {
    
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
        [ externalIpInstanceList: ExternalIp.list( params ), externalIpInstanceTotal: ExternalIp.count() ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def externalIpInstance = ExternalIp.get( params.id )

        if(!externalIpInstance) {
            flash.message = "ExternalIp not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ externalIpInstance : externalIpInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def externalIpInstance = ExternalIp.get( params.id )
        if(externalIpInstance) {
            try {
                externalIpInstance.delete(flush:true)
                flash.message = "ExternalIp ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ExternalIp ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ExternalIp not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def externalIpInstance = ExternalIp.get( params.id )

        if(!externalIpInstance) {
            flash.message = "ExternalIp not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ externalIpInstance : externalIpInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def externalIpInstance = ExternalIp.get( params.id )
        if(externalIpInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(externalIpInstance.version > version) {
                    
                    externalIpInstance.errors.rejectValue("version", "externalIp.optimistic.locking.failure", "Another user has updated this ExternalIp while you were editing.")
                    render(view:'edit',model:[externalIpInstance:externalIpInstance])
                    return
                }
            }
            externalIpInstance.properties = params
            if(!externalIpInstance.hasErrors() && externalIpInstance.save()) {
                flash.message = "ExternalIp ${params.id} updated"
                redirect(action:show,id:externalIpInstance.id)
            }
            else {
                render(view:'edit',model:[externalIpInstance:externalIpInstance])
            }
        }
        else {
            flash.message = "ExternalIp not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def externalIpInstance = new ExternalIp()
        externalIpInstance.properties = params
        return ['externalIpInstance':externalIpInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def externalIpInstance = new ExternalIp(params)
        if(!externalIpInstance.hasErrors() && externalIpInstance.save()) {
            flash.message = "ExternalIp ${externalIpInstance.id} created"
            redirect(action:show,id:externalIpInstance.id)
        }
        else {
            render(view:'create',model:[externalIpInstance:externalIpInstance])
        }
    }
}
