

class MyHashController {
    
	static final String MY_TOGGLE = "MyHash";
	
	def myTweetMarkService
	def twitterService
	
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
        [ myHashInstanceList: MyHash.list( params ), myHashInstanceTotal: MyHash.count() ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def myHashInstance = MyHash.get( params.id )

        if(!myHashInstance) {
            flash.message = "MyHash not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ myHashInstance : myHashInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def myHashInstance = MyHash.get( params.id )
        if(myHashInstance) {
            try {
                myHashInstance.delete(flush:true)
                flash.message = "MyHash ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "MyHash ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "MyHash not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def myHashInstance = MyHash.get( params.id )

        if(!myHashInstance) {
            flash.message = "MyHash not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ myHashInstance : myHashInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def myHashInstance = MyHash.get( params.id )
        if(myHashInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(myHashInstance.version > version) {
                    
                    myHashInstance.errors.rejectValue("version", "myHash.optimistic.locking.failure", "Another user has updated this MyHash while you were editing.")
                    render(view:'edit',model:[myHashInstance:myHashInstance])
                    return
                }
            }
            myHashInstance.properties = params
            if(!myHashInstance.hasErrors() && myHashInstance.save()) {
                flash.message = "MyHash ${params.id} updated"
                redirect(action:show,id:myHashInstance.id)
            }
            else {
                render(view:'edit',model:[myHashInstance:myHashInstance])
            }
        }
        else {
            flash.message = "MyHash not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def myHashInstance = new MyHash()
        myHashInstance.properties = params
        return ['myHashInstance':myHashInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def myHashInstance = new MyHash(params)
        if(!myHashInstance.hasErrors() && myHashInstance.save()) {
            flash.message = "MyHash ${myHashInstance.id} created"
            redirect(action:show,id:myHashInstance.id)
        }
        else {
            render(view:'create',model:[myHashInstance:myHashInstance])
        }
    }
	
	def saveMyHash = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot save my hash at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			def hashObj = new MyHash(params)
			if (myTweetMarkService.createMyHash(session.userId, hashObj) == MyTweetMarkService.SUCCESS) {
				flash.message = "MyHash created successfully";
			} else {
				flash.message = "MyHash creation failure";
			}
			
			redirect(action:'myTweetMarks',controller:'myTweetMark')
		}
	}
	
	def deleteMyHash = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot delete hashes at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			if (params.id != null) {
				def returnVal = myTweetMarkService.deleteHash(session.userId, Long.valueOf(params.id))
				
				if (returnVal == MyTweetMarkService.SUCCESS) {
					flash.message = "Successfully removed hash.";
				} else {
					flash.message = "Cannot remove hash at this time.  Please try again later.";
				}
			}
			
			redirect(action:'myTweetMarks',controller:'myTweetMark')
		}
	}
}
