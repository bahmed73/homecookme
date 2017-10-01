import com.google.code.facebookapi.*;

class MyCategoryController {
    
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
    def static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        if(!params.max) params.max = 100
        [ myCategoryInstanceList: MyCategory.list( params ), myCategoryInstanceTotal: MyCategory.count() ]
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def myCategoryInstance = MyCategory.get( params.id )

        if(!myCategoryInstance) {
            flash.message = "MyCategory not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ myCategoryInstance : myCategoryInstance ] }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def myCategoryInstance = MyCategory.get( params.id )
        if(myCategoryInstance) {
            myCategoryInstance.delete()
            flash.message = "MyCategory ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "MyCategory not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def myCategoryInstance = MyCategory.get( params.id )

        if(!myCategoryInstance) {
            flash.message = "MyCategory not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ myCategoryInstance : myCategoryInstance ]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def myCategoryInstance = MyCategory.get( params.id )
        if(myCategoryInstance) {
            myCategoryInstance.properties = params
            if(!myCategoryInstance.hasErrors() && myCategoryInstance.save()) {
                flash.message = "MyCategory ${params.id} updated"
                redirect(action:show,id:myCategoryInstance.id)
            }
            else {
                render(view:'edit',model:[myCategoryInstance:myCategoryInstance])
            }
        }
        else {
            flash.message = "MyCategory not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
    	if (session.userName == null) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def myCategoryInstance = new MyCategory()
        myCategoryInstance.properties = params
        return ['myCategoryInstance':myCategoryInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
        def myCategoryInstance = new MyCategory(params)
        if(!myCategoryInstance.hasErrors() && myCategoryInstance.save()) {
            flash.message = "MyCategory ${myCategoryInstance.id} created"
            redirect(action:show,id:myCategoryInstance.id)
        }
        else {
            render(view:'create',model:[myCategoryInstance:myCategoryInstance])
        }
    }
	
	def saveMyCategory = {
		println "inside saveMyCategory"
		println "$params.categoryId"
		
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
		
		def categoryObj = new MyCategory(params)
		if (myTweetMarkService.createMyCategory(session.userId, categoryObj) == MyTweetMarkService.SUCCESS) {
			String categoryName = null;
			Categories category = Categories.findWhere(id:categoryObj.categoryId)
			if (category != null) {
				categoryName = category.name + " category"
			} else {
				categoryName = "bookmark category"
			}
			
			if (myTweetMarkService.getTweet(session.userId) == MyTweetMarkService.ON) {
				if (myTweetMarkService.isFaceBookUser(session.userId)) {
					try {
						if (facebookConnectService.isLoggedIn(request)) {
							FacebookJsonRestClient client = facebookConnectService.getFacebookClient()
							if (client.users_hasAppPermission(Permission.STATUS_UPDATE)) {
								client.users_setStatus("creating a new " + categoryName + " for food on http://www.homecook.me/" + session.userName, false);
							}
						} 
					} catch (Exception e) {
						System.out.println("Exception caught obtaining facebook")
						e.printStackTrace()
					}
				} else if (session.twitter != null) {
					session.twitter.updateStatus("My #" + categoryName + " for #food on http://www.homecook.me/" + session.userName)
		    		
				} 
			}
			flash.message = categoryName + " for food created successfully";
		} else {
			flash.message = "My Category creation failure";
		}
		
		redirect(action:'myTweetMarks',controller:'myTweetMark')
	}
	
	def deleteMyCategory = {
		println "inside deleteMyCategory"
		println "$params.categoryId"
		
		if (params.categoryId != null) {
			myTweetMarkService.deleteMyCategory(session.userId, Integer.valueOf(params.categoryId))
		}
		redirect(action:'myTweetMarks',controller:'myTweetMark')
	}
}
