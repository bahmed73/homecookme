import com.google.code.facebookapi.*;

class PostController {

	static final String MY_TOGGLE = "Post";
	
	def myTweetMarkService
	def twitterService
	String myHash
	String userName
	def postInstanceList
	def postInstanceTotal
	Integer displaySend
	List myHashes
	Integer isFaceBookUser
	def facebookConnectService
	Integer autoTweet
	def user
	
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
        params.max = Math.min( params.max ? params.max.toInteger() : 5,  100)
        if (params.offset == null) {
        	params.offset = 0
        }
        Integer paramInt = Integer.valueOf(params.offset) 
        postInstanceList = myTweetMarkService.getPostsWithUserInfo(paramInt, params.max)
        postInstanceTotal = myTweetMarkService.getPostCount()
        displaySend = myTweetMarkService.getTweet(session.userId)
        isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
        user = myTweetMarkService.getUser(session.userId)
        session.profile = false
    }

    def show = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def postInstance = Post.get( params.id )

        if(!postInstance) {
            flash.message = "Post not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ postInstance : postInstance ] }
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def postInstance = Post.get( params.id )
        if(postInstance) {
            try {
                postInstance.delete(flush:true)
                flash.message = "Post ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Post ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Post not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def postInstance = Post.get( params.id )

        if(!postInstance) {
            flash.message = "Post not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ postInstance : postInstance ]
        }
    }

    def update = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def postInstance = Post.get( params.id )
        if(postInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(postInstance.version > version) {
                    
                    postInstance.errors.rejectValue("version", "post.optimistic.locking.failure", "Another user has updated this Post while you were editing.")
                    render(view:'edit',model:[postInstance:postInstance])
                    return
                }
            }
            postInstance.properties = params
            if(!postInstance.hasErrors() && postInstance.save()) {
                flash.message = "Post ${params.id} updated"
                redirect(action:show,id:postInstance.id)
            }
            else {
                render(view:'edit',model:[postInstance:postInstance])
            }
        }
        else {
            flash.message = "Post not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
		if (session.userName == null) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def postInstance = new Post()
        postInstance.properties = params
        return ['postInstance':postInstance]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def postInstance = new Post(params)
        if(!postInstance.hasErrors() && postInstance.save()) {
            flash.message = "Post ${postInstance.id} created"
            redirect(action:show,id:postInstance.id)
        }
        else {
            render(view:'create',model:[postInstance:postInstance])
        }
    }
	
	def saveMyPost = {
		
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
		
		def postObj = new Post(params)
		
		def sessionDesc = session.description
		def description = postObj.description
		if (description!=null) {
			if (description.equals("Oops, can't save same basket again")) {
				render "Oops, can't save this basket"
				return
			} else if (description.equals("Basket stored!")) {
				render "Oops, can't save this basket"
				return
			} else if (description.equals("Oops, can't save this basket")) {
				render "Oops, can't save this basket"
				return
			} else if (description.equals("Favorite item basket")) {
				render "Oops, can't save this basket"
				return
			} else if (description.equals("")) {
				render "Oops, can't save this basket"
				return
			} else if (sessionDesc!=null) {
				if (sessionDesc.equals(description)) {
					render "Oops, can't save same basket again"
					return
				} 
			} 
		} else {
			render "Oops, can't save this basket"
			return
		}
		if (myTweetMarkService.createMyPost(session.userId, postObj) == MyTweetMarkService.SUCCESS) {
			session.description = postObj.description
			if (myTweetMarkService.getTweet(session.userId) == MyTweetMarkService.ON) {
				if (myTweetMarkService.isFaceBookUser(session.userId)) {
					try {
						if (facebookConnectService.isLoggedIn(request)) {
							FacebookJsonRestClient client = facebookConnectService.getFacebookClient()
							if (client.users_hasAppPermission(Permission.STATUS_UPDATE)) {
								client.users_setStatus("My #localfood #farmersmarket basket on http://www.homecook.me/" + session.userName+ " has " + postObj.description, false);
							}
						} 
					} catch (Exception e) {
						System.out.println("Exception caught obtaining facebook")
						e.printStackTrace()
					}
				} else if (session.twitter != null) {
					def status = "My #localfood #farmersmarket basket on http://www.homecook.me/" + session.userName + " has " + postObj.description
					
					if (status.length() > 140) {
						status = status.substring(0,140)
					}
					session.twitter.updateStatus(status)
		    		
				} 
				
			}
			try {
				def recommendations = myTweetMarkService.search(session.description)
				
				if (recommendations != null && !recommendations.isEmpty()) {
					for (int i = 0; i< recommendations.size(); i++) {
						if (!myTweetMarkService.isUserSubscribed(session.userId, recommendations.get(i).id)) {
							myTweetMarkService.subscribe(session.userId, recommendations.get(i).id)
						}
					}
					
				}
			} catch (Exception re) {
				re.printStackTrace()
			}
		} else {
			render "Oops, can't save this basket"
			return
		}
		
		render "Basket stored!"
	}
	
	def deleteMyPost = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot delete posts at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			if (params.id != null) {
				def returnVal = myTweetMarkService.deletePost(session.userId, Long.valueOf(params.id))
				
				if (returnVal == MyTweetMarkService.SUCCESS) {
					flash.message = "Successfully removed basket.";
				} else {
					flash.message = "Cannot remove basket at this time.  Please try again later.";
				}
			}
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			//redirect(controller:'post',action:"list")
		}
	}
}
