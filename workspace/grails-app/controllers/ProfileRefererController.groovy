

class ProfileRefererController {
    
	def myTweetMarkService
	def profileRefererInstanceList
	def profileRefererInstanceTotal
	def profileRefererMap
	
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
    	if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        if (params.offset == null) {
        	params.offset = 0
        }
        Integer paramInt = Integer.valueOf(params.offset) 
        
        profileRefererInstanceList = ProfileReferer.findAllByName(session.userName, [max:params.max, sort:"createTime", order:"desc", offset:paramInt])
        profileRefererInstanceTotal = ProfileReferer.countByName(session.userName)
        
        def profileRefererList = ProfileReferer.findAllByName(session.userName)
        
        if (profileRefererList != null && !profileRefererList.isEmpty()) {
	        profileRefererMap = new HashMap<String, Integer>()
	        for (int i=0; i<profileRefererList.size(); i++ ) {
	        	def referer
	        	if (profileRefererList.get(i).refererUrl.contains("http://")) {
	        		referer = profileRefererList.get(i).refererUrl.substring(7)
	        	}
	        	
	        	def index = referer.indexOf('/')
	        	
	        	if (index > 0) {
	        		referer = referer.substring(0, index)
	        	}
	        	
	        	if (profileRefererMap.get(referer)!=null) {
	        		//exists, increment count
	        		def countRef = profileRefererMap.get(referer)
	        		profileRefererMap.remove(referer)
	        		profileRefererMap.put(referer, countRef+1)
	        	} else {
	        		profileRefererMap.put(referer, 1)
	        	}
	        }
        }
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def profileRefererInstance = ProfileReferer.get( params.id )

        if(!profileRefererInstance) {
            flash.message = "ProfileReferer not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ profileRefererInstance : profileRefererInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def profileRefererInstance = ProfileReferer.get( params.id )
        if(profileRefererInstance) {
            try {
                profileRefererInstance.delete(flush:true)
                flash.message = "ProfileReferer ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "ProfileReferer ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "ProfileReferer not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def profileRefererInstance = ProfileReferer.get( params.id )

        if(!profileRefererInstance) {
            flash.message = "ProfileReferer not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ profileRefererInstance : profileRefererInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def profileRefererInstance = ProfileReferer.get( params.id )
        if(profileRefererInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(profileRefererInstance.version > version) {
                    
                    profileRefererInstance.errors.rejectValue("version", "profileReferer.optimistic.locking.failure", "Another user has updated this ProfileReferer while you were editing.")
                    render(view:'edit',model:[profileRefererInstance:profileRefererInstance])
                    return
                }
            }
            profileRefererInstance.properties = params
            if(!profileRefererInstance.hasErrors() && profileRefererInstance.save()) {
                flash.message = "ProfileReferer ${params.id} updated"
                redirect(action:show,id:profileRefererInstance.id)
            }
            else {
                render(view:'edit',model:[profileRefererInstance:profileRefererInstance])
            }
        }
        else {
            flash.message = "ProfileReferer not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def profileRefererInstance = new ProfileReferer()
        profileRefererInstance.properties = params
        return ['profileRefererInstance':profileRefererInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def profileRefererInstance = new ProfileReferer(params)
        if(!profileRefererInstance.hasErrors() && profileRefererInstance.save()) {
            flash.message = "ProfileReferer ${profileRefererInstance.id} created"
            redirect(action:show,id:profileRefererInstance.id)
        }
        else {
            render(view:'create',model:[profileRefererInstance:profileRefererInstance])
        }
    }
}
