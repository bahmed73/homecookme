import com.google.code.facebookapi.*;

class MyTweetMarkController {
    
	static final String MY_TOGGLE = "MyTweetMark";
	
	def myTweetMarkService
	def twitterService
	def facebookConnectService
	
	def beforeInterceptor = {
		
	}
	
	List myCategories
	List searchResults
	String photoUrl
	String firstName
	String lastName
	String userName
	List posts
	List blogs
	List recipes
	String searchTerm
	Long views
	String myHash
	Integer displaySend
	List myHashes
	def myTweetMarkInstanceList 
	def myTweetMarkInstanceTotal
	List categories
	Integer isFaceBookUser
	Integer permission
	List tweetUrls
	List tweetFriends
	def profileRefererInstanceTotal
	def profileRefererMap
	Integer autoTweet
	def user
	def commentPosts
	def lastPost
	def subscribed
	def subscribers
	def markets
	def products
	def marketMap
	def farmerMarketInstanceListAll
	def recommendations
	def viewSummaryInstanceTotal
	def viewSummaryList
	
	//TODO refactor all user objects in one call, user object to minimize query permission, isFaceBookUser and displaySend
	
	def process = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    	}
		MyTweetMarkService.serviceMethod()
	}
	
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
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
        params.max = Math.min( params.max ? params.max.toInteger() : 5,  100)
        if (params.offset == null) {
        	params.offset = 0
        }
        Integer paramInt = Integer.valueOf(params.offset) 
        myHash = myTweetMarkService.getHashString(session.userId)    
        myTweetMarkInstanceList = myTweetMarkService.getMyTweetMarksWithUserInfo(paramInt, params.max)
        myTweetMarkInstanceTotal = myTweetMarkService.getMyTweetMarkCount()
        displaySend = myTweetMarkService.getTweet(session.userId)
        myHashes = myTweetMarkService.getMyHashes(session.userId)
        userName = session.userName
        isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
        autoTweet = myTweetMarkService.getAutoTweet(session.userId)
    }

    def profile = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
		    flash.message = "Please login as administrator to do this operation.";
		    redirect(uri:"/")
		}
        if(!params.max) params.max = 10
        [ myTweetMarkInstanceList: MyTweetMark.list( params ) ]
    }
    
	def show = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
		    flash.message = "Please login as administrator to do this operation.";
		    redirect(uri:"/")
		}
        def myTweetMarkInstance = MyTweetMark.get( params.id )

        if(!myTweetMarkInstance) {
            flash.message = "MyTweetMark not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ myTweetMarkInstance : myTweetMarkInstance ] }
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
		    flash.message = "Please login as administrator to do this operation.";
		    redirect(uri:"/")
		}
        def myTweetMarkInstance = MyTweetMark.get( params.id )
        if(myTweetMarkInstance) {
            myTweetMarkInstance.delete()
            flash.message = "MyTweetMark ${params.id} deleted"
            redirect(action:list)
        }
        else {
            flash.message = "MyTweetMark not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
		    flash.message = "Please login as administrator to do this operation.";
		    redirect(uri:"/")
		}
        def myTweetMarkInstance = MyTweetMark.get( params.id )

        if(!myTweetMarkInstance) {
            flash.message = "MyTweetMark not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ myTweetMarkInstance : myTweetMarkInstance ]
        }
    }

    def update = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
		    flash.message = "Please login as administrator to do this operation.";
		    redirect(uri:"/")
		}
        def myTweetMarkInstance = MyTweetMark.get( params.id )
        if(myTweetMarkInstance) {
            myTweetMarkInstance.properties = params
            if(!myTweetMarkInstance.hasErrors() && myTweetMarkInstance.save()) {
                flash.message = "MyTweetMark ${params.id} updated"
                redirect(action:show,id:myTweetMarkInstance.id)
            }
            else {
                render(view:'edit',model:[myTweetMarkInstance:myTweetMarkInstance])
            }
        }
        else {
            flash.message = "MyTweetMark not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
		if (session.userName == null) {
		    flash.message = "Please login as administrator to do this operation.";
		    redirect(uri:"/")
		}
        def myTweetMarkInstance = new MyTweetMark()
        myTweetMarkInstance.properties = params
        return ['myTweetMarkInstance':myTweetMarkInstance]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
		    flash.message = "Please login as administrator to do this operation.";
		    redirect(uri:"/")
		}
        def myTweetMarkInstance = new MyTweetMark(params)
        if(!myTweetMarkInstance.hasErrors() && myTweetMarkInstance.save()) {
            flash.message = "MyTweetMark ${myTweetMarkInstance.id} created"
            redirect(action:show,id:myTweetMarkInstance.id)
        }
        else {
            render(view:'create',model:[myTweetMarkInstance:myTweetMarkInstance])
        }
    }
	
	def myTweetMarks = {
		//lets first get all the categories for the user.  for each category, let's fill in mytweetmarks using Expandos.  Let's then return the results to the view
		//the view should use the _results page to display them.  _results should iterate through mycategories.  For each category, use the expandos to pull out tweetmarks
        
		if (!myTweetMarkService.isFeatureToggleOn(MY_TOGGLE)) {
			flash.message = "MyTweetMark not available.  Contact Us and email the details of the error.";
    		redirect(uri:"/")
    		return
		} else if (session.userName != null) {
			try {
				user = myTweetMarkService.getUser(session.userId)
				myCategories = myTweetMarkService.getMyCategoriesWithMyTweetMarks(session.userId)
				if (user != null) {
					session.profile = false
					//posts = myTweetMarkService.getPosts(session.userId)
					blogs = myTweetMarkService.getBlogs(session.userId)
					recipes = myTweetMarkService.getRecipes(user.id)
					views = myTweetMarkService.getViews(user.id)
					displaySend = myTweetMarkService.getTweet(user.id)
					isFaceBookUser = myTweetMarkService.isFaceBookUser(user.id)
					permission = myTweetMarkService.getUserPermission(user.id)
					lastPost = myTweetMarkService.getLastPost(user.id)
					
					try {
						if (lastPost != null && !lastPost.description.isEmpty()) {
							recommendations = myTweetMarkService.search(lastPost.description)
						}
					} catch (Exception re) {
						re.printStackTrace()
					}
					
					categories = myTweetMarkService.getCategories()
					subscribers = myTweetMarkService.getSubscribers(user.id)
					markets = myTweetMarkService.getUserMarkets(user.id)
					products = myTweetMarkService.getMyProducts(user.id)
					viewSummaryInstanceTotal = ViewSummary.countByUserId(user.id)
				
					if (viewSummaryInstanceTotal > 30) {
						viewSummaryInstanceTotal = 30;
					}
		        
					viewSummaryList = ViewSummary.findAllByUserId(user.id, [max:30, sort:"createTime", order:"desc"])
					
					//commentPosts = myTweetMarkService.getCommentsByUserId(session.userId)
					render(view:"smyTweetMarks", model:user)
					return
				}
			} catch (Exception e) {
				e.printStackTrace()
				flash.message = "MyTweetMark not available.  Contact Us and email the details of the error.";
	    		redirect(uri:"/")
	    		return
			}
		} 
			
		flash.message = "User not authenticated";
		redirect(uri:"/")
		
	}
	
	def userTweetMarks = {
			
		if (!myTweetMarkService.isFeatureToggleOn(MY_TOGGLE)) {
			flash.message = "MyTweetMark not available.  Contact Us and email the details of the error.";
    		redirect(uri:"/")
    		return
		} else {
			try {
				user = myTweetMarkService.getUser(params.userName)
				
				if (user == null) {
					flash.message = "Cannot find user: $params.userName";
					redirect(action:'myTweetMarks',controller:'myTweetMark')
					return
				} else {
					//posts = myTweetMarkService.getPosts(user.id)
					blogs = myTweetMarkService.getBlogs(user.id)
					recipes = myTweetMarkService.getRecipes(user.id)
					myTweetMarkService.incrementViews(user.id)
					views = myTweetMarkService.getViews(user.id)
					isFaceBookUser = myTweetMarkService.isFaceBookUser(user.id)
					myTweetMarkService.trackProfileReferer(userName, request.getHeader("REFERER"))
					//commentPosts = myTweetMarkService.getCommentsByUserId(user.id)
					session.profile = true
					lastPost = myTweetMarkService.getLastPost(user.id)
					try {
						if (lastPost != null && !lastPost.description.isEmpty()) {
							recommendations = myTweetMarkService.search(lastPost.description)
						}
					} catch (Exception re) {
						re.printStackTrace()
					}
					
					myCategories = myTweetMarkService.getMyCategoriesWithMyTweetMarks(user.id)
					
					if (session.userName != null) {
						//(viewer, profile).  check whether the viewer is subscribed to profile, if viewer logged in.
						subscribed = myTweetMarkService.isUserSubscribed(session.userId, user.id)
					} else {
						//default to user is not subscribed to give them a chance to subscribe.
						subscribed = false
					}
					subscribers = myTweetMarkService.getSubscribers(user.id)
					markets = myTweetMarkService.getUserMarkets(user.id)
					products = myTweetMarkService.getMyProducts(user.id)
					marketMap = myTweetMarkService.getUserMarketsMap(user.id)
					viewSummaryInstanceTotal = ViewSummary.countByUserId(user.id)
				
					if (viewSummaryInstanceTotal > 30) {
						viewSummaryInstanceTotal = 30;
					}
		        
					viewSummaryList = ViewSummary.findAllByUserId(user.id, [max:30, sort:"createTime", order:"desc"])
				}
				render(view:"smyTweetMarks", model:user)
			} catch (Exception e) {
				e.printStackTrace()
				flash.message = "MyTweetMark not available.  Contact Us and email the details of the error.";
	    		redirect(uri:"/")
	    		return
			}
		}
	}
	
	def idTweetMarks = {
		
		if (!myTweetMarkService.isFeatureToggleOn(MY_TOGGLE)) {
			flash.message = "MyTweetMark not available.  Contact Us and email the details of the error.";
    		redirect(uri:"/")
    		return
		} else {
		
			try {
				user = myTweetMarkService.getUser(Integer.valueOf(params.id))
				
				if (user == null) {
					flash.message = "Cannot find user: $params.id";
					redirect(action:'myTweetMarks',controller:'myTweetMark')
					return
				} else {
					//posts = myTweetMarkService.getPosts(user.id)
					blogs = myTweetMarkService.getBlogs(user.id)
					recipes = myTweetMarkService.getRecipes(user.id)
					myTweetMarkService.incrementViews(user.id)
					views = myTweetMarkService.getViews(user.id)
					isFaceBookUser = myTweetMarkService.isFaceBookUser(user.id)
					myTweetMarkService.trackProfileReferer(user.userName, request.getHeader("REFERER"))
					//commentPosts = myTweetMarkService.getCommentsByUserId(user.id)
					session.profile = true
					lastPost = myTweetMarkService.getLastPost(user.id)
					try {
						if (lastPost != null && !lastPost.description.isEmpty()) {
							recommendations = myTweetMarkService.search(lastPost.description)
						}
					} catch (Exception re) {
						re.printStackTrace()
					}
					myCategories = myTweetMarkService.getMyCategoriesWithMyTweetMarks(user.id)
					
					if (session.userName != null) {
						//(viewer, profile).  check whether the viewer is subscribed to profile, if viewer logged in.
						subscribed = myTweetMarkService.isUserSubscribed(session.userId, user.id)
					} else {
						//default to user is not subscribed to give them a chance to subscribe.
						subscribed = false
					}
					subscribers = myTweetMarkService.getSubscribers(user.id)
					markets = myTweetMarkService.getUserMarkets(user.id)
					products = myTweetMarkService.getMyProducts(user.id)
					marketMap = myTweetMarkService.getUserMarketsMap(user.id)
					viewSummaryInstanceTotal = ViewSummary.countByUserId(user.id)
				
					if (viewSummaryInstanceTotal > 30) {
						viewSummaryInstanceTotal = 30;
					}
		        
					viewSummaryList = ViewSummary.findAllByUserId(user.id, [max:30, sort:"createTime", order:"desc"])
				}
				render(view:"smyTweetMarks", model:user)
			} catch (Exception e) {
				e.printStackTrace()
				flash.message = "MyTweetMark not available.  Contact Us and email the details of the error.";
	    		redirect(uri:"/")
	    		return
			}
			
		}
	}
	
	def saveMyTweetMark = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot save my tweetmarks at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			def tweetMarkObj = new MyTweetMark(params)
			if (myTweetMarkService.createMyTweetMark(session.userId, tweetMarkObj) == MyTweetMarkService.SUCCESS) {
				String categoryName = null;
				Categories category = Categories.findWhere(id:tweetMarkObj.categoryId)
				if (category != null) {
					categoryName = category.name + " #food #link"
				} else {
					categoryName = "#food #links"
				}
				
				if (myTweetMarkService.getTweet(session.userId) == MyTweetMarkService.ON) {
					if (myTweetMarkService.isFaceBookUser(session.userId)) {
						try {
							if (facebookConnectService.isLoggedIn(request)) {
								FacebookJsonRestClient client = facebookConnectService.getFacebookClient()
								if (client.users_hasAppPermission(Permission.STATUS_UPDATE)) {
									client.users_setStatus("sharing my " + categoryName + " on http://www.homecook.me/" + session.userName, false);
								}
							} 
						} catch (Exception e) {
							System.out.println("Exception caught obtaining facebook")
							e.printStackTrace()
						}
					} else if (session.twitter != null) {
						session.twitter.updateStatus("My #" + categoryName + " on http://www.homecook.me/" + session.userName)
			    		
					} 
					
				}
	    		flash.message = categoryName + " created successfully";
			} else {
				flash.message = "link creation failure";
			}
			
			redirect(action:'myTweetMarks',controller:'myTweetMark')
		}
	}
    
	def deleteMyTweetMark = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot save my tweetmarks at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			if (params.categoryId != null && params.id != null) {
				myTweetMarkService.deleteMyTweetMark(session.userId, Integer.valueOf(params.categoryId), Integer.valueOf(params.id))
			}
			
			redirect(action:'myTweetMarks',controller:'myTweetMark')
		}
	}
	
	def searchMyTweetmarks = {
		if (!myTweetMarkService.isFeatureToggleOn(MY_TOGGLE)) {
			flash.message = "MyTweetMark not available.  Contact Us and email the details of the error.";
    		redirect(uri:"/")
    		return
		} else {
		
			def searchParam = params.search
			searchTerm = searchParam
			if (searchParam == null || (searchParam.length() == 0)) {
				flash.message = "Please provide a search term."
				redirect(action:'/')
				return
				
			} else {
			
				try {
					searchResults = myTweetMarkService.search(searchParam)
					farmerMarketInstanceListAll = myTweetMarkService.getMarkets()
				} catch (Exception e) {
					flash.message = "Error executing search."
					redirect(action:'myTweetMarks')
				}
			}
    	}
	}
}
