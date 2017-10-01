import com.google.code.facebookapi.*;

class BlogController {
    
	static final String MY_TOGGLE = "Blog";
	
	def myTweetMarkService
	def twitterService
	Integer displaySend
	Integer isFaceBookUser
	def facebookConnectService
	def user
	def blogInstance
	def geoIpService
	
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
        [ blogInstanceList: Blog.list( params ), blogInstanceTotal: Blog.count() ]
    }

    def show = {
		blogInstance = Blog.get(params.id)
        if (!blogInstance) {
            flash.message = "Blog not found."
            redirect(action:'myTweetMarks',controller:'myTweetMark')
            return
        } else {
        	if (session.userId != null) {
	        	displaySend = myTweetMarkService.getTweet(session.userId)
	            isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
	            
	        }
        	user = myTweetMarkService.getUser(blogInstance.userId)
        	session.profile = false
        }
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def blogInstance = Blog.get( params.id )
        if(blogInstance) {
            try {
                blogInstance.delete(flush:true)
                flash.message = "Blog ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "Blog ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "Blog not found with id ${params.id}"
            redirect(action:list)
        }
    }

	def edit = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		user = myTweetMarkService.getUser(session.userId)
        blogInstance = Blog.get(params.id)
        if (!blogInstance) {
            flash.message = "Blog not found."
            redirect(action:'myTweetMarks',controller:'myTweetMark')
        }
        else {
    		displaySend = myTweetMarkService.getTweet(session.userId)
            isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
            user = myTweetMarkService.getUser(session.userId)
            session.profile = false
        }
    }

	def update = {
		System.out.println("inside update")
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		def blogInstance = new Blog()
        blogInstance.properties = params
        
        if (params.id != null) {
        	blogInstance.id = Long.valueOf(params.id)
        }
        
		def update = (blogInstance.id==null)?false:true;
		
		def returnVal;
		
		try {
			returnVal = myTweetMarkService.createMyBlog(session.userId, blogInstance, update)
		} catch (Exception e) {
			e.printStackTrace()
			flash.message = "Problem storing blog.  Please try 'typing in' blog, rather 'copy and paste'."
				redirect(action:'myTweetMarks',controller:'myTweetMark')
				return
		}
        if (returnVal == MyTweetMarkService.SUCCESS) {
        	if (myTweetMarkService.getTweet(session.userId) == MyTweetMarkService.ON) {
				if (myTweetMarkService.isFaceBookUser(session.userId)) {
					try {
						if (facebookConnectService.isLoggedIn(request)) {
							FacebookJsonRestClient client = facebookConnectService.getFacebookClient()
							if (client.users_hasAppPermission(Permission.STATUS_UPDATE)) {
								client.users_setStatus("My #food #blog on http://www.homecook.me/" + session.userName, false);
							}
						} 
					} catch (Exception e) {
						System.out.println("Exception caught obtaining facebook")
						e.printStackTrace()
					}
				} else if (session.twitter != null) {
					def status = "My #farmersmarket #socialmedia #tech #blog on http://www.homecook.me/" + session.userName + " #" + blogInstance.title + " cc #fb"
					if (status.length() > 140) {
						status = status.substring(0,140)
					}
					try {
						session.twitter.updateStatus(status)
					} catch (Exception e) {
						e.printStackTrace()
					}
					
				} 
			}
			flash.message = "Successfully updated blog.";
		} else {
			flash.message = "Cannot update blog at this time.  Please try again later.";
		}
		redirect(action:'myTweetMarks',controller:'myTweetMark')
    }

	def create = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		session.profile = false
        user = myTweetMarkService.getUser(session.userId)
        //return ['categories':categories]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def blogInstance = new Blog(params)
        if(!blogInstance.hasErrors() && blogInstance.save()) {
            flash.message = "Blog ${blogInstance.id} created"
            redirect(action:show,id:blogInstance.id)
        }
        else {
            render(view:'create',model:[blogInstance:blogInstance])
        }
    }
	
	def saveMyBlog = {
		println "inside saveMyBlog"
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	} else {
		
			if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
				flash.message = "Cannot save blogs at this time.  Please try again later.";
				redirect(action:'myTweetMarks',controller:'myTweetMark')
				return
			} else {
			
				def blogObj = new Blog(params)
				if (myTweetMarkService.createMyBlog(session.userId, blogObj) == MyTweetMarkService.SUCCESS) {
					if (myTweetMarkService.getTweet(session.userId) == MyTweetMarkService.ON) {
						if (myTweetMarkService.isFaceBookUser(session.userId)) {
							try {
								if (facebookConnectService.isLoggedIn(request)) {
									FacebookJsonRestClient client = facebookConnectService.getFacebookClient()
									if (client.users_hasAppPermission(Permission.STATUS_UPDATE)) {
										client.users_setStatus("My #food #recipe on http://www.homecook.me/" + session.userName, false);
									}
								} 
							} catch (Exception e) {
								System.out.println("Exception caught obtaining facebook")
								e.printStackTrace()
							}
						} else if (session.twitter != null) {
							session.twitter.updateStatus("My #food #recipe on http://www.homecook.me/" + session.userName)
				    		
						} 
					}
					flash.message = "Food recipe created successfully";
				} else {
					flash.message = "Food recipe creation failure";
				}
				
				redirect(action:'myTweetMarks',controller:'myTweetMark')
			}
    	}
	}
	
	def deleteMyBlog = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot delete blogs at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			if (params.id != null) {
				def returnVal = myTweetMarkService.deleteBlog(session.userId, Long.valueOf(params.id))
				
				if (returnVal == MyTweetMarkService.SUCCESS) {
					flash.message = "Successfully removed blog.";
				} else {
					flash.message = "Cannot remove blog at this time.  Please try again later.";
				}
			}
			
			redirect(action:'myTweetMarks',controller:'myTweetMark')
		}
	}
}
