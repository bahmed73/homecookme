import com.google.code.facebookapi.*;

class CommentController {
    
	static final String MY_TOGGLE = "Comment";
	
	def myTweetMarkService
	def twitterService
	def facebookConnectService
	
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
        [ commentInstanceList: Comment.list( params ), commentInstanceTotal: Comment.count() ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def commentInstance = Comment.get( params.id )

        if(!commentInstance) {
            flash.message = "Comment not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ commentInstance : commentInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def commentInstance = Comment.get( params.id )
        if(commentInstance) {
            try {
                commentInstance.delete(flush:true)
                flash.message = "Comment ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Comment ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Comment not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def commentInstance = Comment.get( params.id )

        if(!commentInstance) {
            flash.message = "Comment not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ commentInstance : commentInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def commentInstance = Comment.get( params.id )
        if(commentInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(commentInstance.version > version) {
                    
                    commentInstance.errors.rejectValue("version", "comment.optimistic.locking.failure", "Another user has updated this Comment while you were editing.")
                    render(view:'edit',model:[commentInstance:commentInstance])
                    return
                }
            }
            commentInstance.properties = params
            if(!commentInstance.hasErrors() && commentInstance.save()) {
                flash.message = "Comment ${params.id} updated"
                redirect(action:show,id:commentInstance.id)
            }
            else {
                render(view:'edit',model:[commentInstance:commentInstance])
            }
        }
        else {
            flash.message = "Comment not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
    	if (session.userName == null) {
			flash.message = "Please login to do this operation.";
			redirect(uri:"/")
		}
        def commentInstance = new Comment()
        commentInstance.properties = params
        return ['commentInstance':commentInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
			flash.message = "Please login as administrator to do this operation.";
			redirect(uri:"/")
		}
        def commentInstance = new Comment(params)
        if(!commentInstance.hasErrors() && commentInstance.save()) {
            flash.message = "Comment ${commentInstance.id} created"
            redirect(action:show,id:commentInstance.id)
        }
        else {
            render(view:'create',model:[commentInstance:commentInstance])
        }
    }
	
	def saveComment = {
		println "inside saveComment"
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	} else {
		
			if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
				flash.message = "Cannot save comments at this time.  Please try again later.";
				redirect(action:'myTweetMarks',controller:'myTweetMark')
				return
			} else {
			
				def commentObj = new Comment(params)
				if (myTweetMarkService.createMyComment(session.userId, commentObj) == MyTweetMarkService.SUCCESS) {
					if (myTweetMarkService.getTweet(session.userId) == MyTweetMarkService.ON) {
						if (myTweetMarkService.isFaceBookUser(session.userId)) {
							try {
								if (facebookConnectService.isLoggedIn(request)) {
									FacebookJsonRestClient client = facebookConnectService.getFacebookClient()
									if (client.users_hasAppPermission(Permission.STATUS_UPDATE)) {
										client.users_setStatus("writing a review on a recipe on http://www.homecook.me/" + session.userName + " " + commentObj.description, false);
									}
								} 
							} catch (Exception e) {
								System.out.println("Exception caught obtaining facebook")
								e.printStackTrace()
							}
						} else if (session.twitter != null) {
							def status = "I wrote a #review on a #recipe on http://www.homecook.me/" + session.userName + " " + commentObj.description
							if (status.length() > 140) {
								status = status.substring(0,140)
							}
							session.twitter.updateStatus(status)
				    		
						} 
					}
					flash.message = "Review created successfully";
				} else {
					flash.message = "Review creation failure";
				}
				
				//redirect(action:'list',controller:'post')
				redirect(action:'myTweetMarks',controller:'myTweetMark')
			}
    	}
	}
	
	def deleteComment = {
		println "deleteComment"
		
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot delete comments at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			if (params.id != null) {
				println "comment id: $params.id"
				def returnVal = myTweetMarkService.deleteComment(session.userId, Long.valueOf(params.id))
				
				if (returnVal == MyTweetMarkService.SUCCESS) {
					flash.message = "Successfully removed comment.";
				} else {
					flash.message = "Cannot remove comment at this time.  Please try again later.";
				}
			}
			
			//redirect(action:'list',controller:'post')
			redirect(action:'myTweetMarks',controller:'myTweetMark')
		}
	}
}
