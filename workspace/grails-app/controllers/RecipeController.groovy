import com.google.code.facebookapi.*;

class RecipeController {

static final String MY_TOGGLE = "Recipe";
	
	def myTweetMarkService
	def twitterService
	def recipeInstanceList
	def recipeInstanceTotal
	Integer displaySend
	Integer isFaceBookUser
	def facebookConnectService
	def user
	def categories
	def recipeInstance
	def category
	def recipesCloud
	def comments
	def geoIpService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        redirect(action: "list", params: params)
    }

    def list = {
		
        params.max = Math.min( params.max ? params.max.toInteger() : 5,  100)
        if (params.offset == null) {
        	params.offset = 0
        }
        Integer paramInt = Integer.valueOf(params.offset) 
        recipeInstanceList = myTweetMarkService.getRecipesWithUserInfo(paramInt, params.max)
        recipeInstanceTotal = myTweetMarkService.getRecipeCount()
        recipesCloud = myTweetMarkService.getRecipesCloud()
        
        if (session.userId != null) {
        	displaySend = myTweetMarkService.getTweet(session.userId)
        	isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
        	user = myTweetMarkService.getUser(session.userId)
        }
        session.profile = false
    }

    def create = {
		System.out.println("inside create")
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		categories = myTweetMarkService.getCategories()
		session.profile = false
        user = myTweetMarkService.getUser(session.userId)
        //return ['categories':categories]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def recipeInstance = new Recipe(params)
        if (recipeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'recipe.label', default: 'Recipe'), recipeInstance.id])}"
            redirect(action: "show", id: recipeInstance.id)
        }
        else {
            render(view: "create", model: [recipeInstance: recipeInstance])
        }
    }

    def show = {
        recipeInstance = Recipe.get(params.id)
        if (!recipeInstance) {
            flash.message = "Recipe not found."
            redirect(action:'myTweetMarks',controller:'myTweetMark')
        } else {
        	category = Categories.get(recipeInstance.categoryId)
        	if (session.userId != null) {
	        	displaySend = myTweetMarkService.getTweet(session.userId)
	            isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
	            
	        }
        	user = myTweetMarkService.getUser(recipeInstance.userId)
        	recipesCloud = myTweetMarkService.getRecipes(recipeInstance.userId)
            session.profile = false
            comments = myTweetMarkService.getCommentsWithUserInfo(recipeInstance.id, MyTweetMarkService.RECIPE_TYPE)
        }
    }

    def edit = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		user = myTweetMarkService.getUser(session.userId)
        recipeInstance = Recipe.get(params.id)
        if (!recipeInstance) {
            flash.message = "Recipe not found."
            redirect(action:'myTweetMarks',controller:'myTweetMark')
        }
        else {
    		categories = myTweetMarkService.getCategories()
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
		def recipeInstance = new Recipe()
        recipeInstance.properties = params
        
        if (params.id != null) {
        	recipeInstance.id = Long.valueOf(params.id)
        }
        
		def update = (recipeInstance.id==null)?false:true;
		
		def location = geoIpService.getLocation(request.getRemoteAddr())
        
		def returnVal;
		
		try {
			returnVal = myTweetMarkService.createMyRecipe(session.userId, recipeInstance, update, location)
		} catch (Exception e) {
			e.printStackTrace()
			flash.message = "Problem storing recipe.  Please try 'typing in' recipe, rather 'copy and paste'."
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
								client.users_setStatus("My #food #recipe on http://www.homecook.me/" + session.userName, false);
							}
						} 
					} catch (Exception e) {
						System.out.println("Exception caught obtaining facebook")
						e.printStackTrace()
					}
				} else if (session.twitter != null) {
					def status = "My #food #recipe on http://www.homecook.me/" + session.userName + " #" + recipeInstance.title
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
			flash.message = "Successfully updated recipe.";
		} else {
			flash.message = "Cannot update recipe at this time.  Please try again later.";
		}
		redirect(action:'myTweetMarks',controller:'myTweetMark')
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def recipeInstance = Recipe.get(params.id)
        if (recipeInstance) {
            try {
                recipeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'recipe.label', default: 'Recipe'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'recipe.label', default: 'Recipe'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'recipe.label', default: 'Recipe'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def deleteMyRecipe = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot delete recipes at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			if (params.id != null) {
				def returnVal = myTweetMarkService.deleteRecipe(session.userId, Long.valueOf(params.id))
				
				if (returnVal == MyTweetMarkService.SUCCESS) {
					flash.message = "Successfully removed recipe.";
				} else {
					flash.message = "Cannot remove recipe at this time.  Please try again later.";
				}
			}
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			//redirect(controller:'post',action:"list")
		}
	}
	
	def emailMyRecipe = {
		log.info("inside emailMyRecipe")
		if (params.email != null && !params.email.isEmpty() && params.id != null && !params.id.isEmpty()) {
			def recipeBody = myTweetMarkService.getRecipeEmailBody(Integer.valueOf(params.id))
			recipeInstance = Recipe.get(Integer.valueOf(params.id))
	    		sendMail {
					to params.email
					subject recipeInstance.title
					//body 'sucka!'
					html recipeBody
					//html g.render(template:"myMailTemplate")
				}
	    		flash.message = "recipe sent to " + params.email;
			} else {
				flash.message = "Email or recipe not found.";
			}
			if (session.userName!=null) {
				redirect(controller:'myTweetMark',action:"myTweetMarks")
			} else {
				redirect(uri:"/")
			}
		}
	
}
