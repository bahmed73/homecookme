import com.danga.MemCached.*
import ij.ImagePlus;
import java.util.*;

class MyTweetMarkService {

	static MemCachedClient mcc = null 
	static SockIOPool pool = null
	
	boolean transactional = true
    static final Integer MYCATEGORY_LIMIT = 5
    static final Integer MYTWEETMARK_LIMIT = 5
    
    static final Integer DISABLE = 0
    static final Integer ACTIVE = 1
    static final Integer DELETE = 2
    
    static final Integer FAILURE = 0
    static final Integer SUCCESS = 1
    
    static final Integer DEFAULT_MARKETING = 0
    static final Integer DEFAULT_COUNTRY = 1 //US
    static final String DEFAULT_IP = "" //nothing
   
    static com.maxmind.geoip.LookupService locationService = null
	static com.maxmind.geoip.Location location = null
	
	
	//using USERS_NAME for sign on.  it would be useful for high retention users
    static final String PREFIX_USERS_NAME = "USERS_NAME_"
    
	static final String PREFIX_MYCATEGORY = "MYCATEGORY_"
	static final String PREFIX_MYTWEETMARK = "MYTWEETMARK_"
	
	static final Integer ON = 1;
	static final Integer OFF = 0;
	static final Integer READ_ONLY = 2;
	
	//green 59a228
	//
	def searchableService
	def imagingService
	def mailService
	
	public static final String POST_TYPE = "Post"
	public static final String RECIPE_TYPE = "Recipe"
	
	static final String FILE_STORAGE_PREFIX = "/usr/share/apache-tomcat-6.0.20/webapps/ROOT/images/MARKETPIC_"
	//static final String FILE_STORAGE_PREFIX = "C:\\Users\\home\\mytweetmark\\app\\web-app\\images\\USERPIC_"
	
	static final String HOME_REFERER = "http://www.homecook.me/myTweetMark/myTweetMarks"
	static final String SEARCH_REFERER = "http://www.homecook.me/myTweetMark/searchMyTweetmarks"
	static final String POST_REFERER = "http://www.homecook.me/post/list"
	static final String HOME_REFERER_2 = "http://homecook.me/myTweetMark/myTweetMarks"
	static final String SEARCH_REFERER_2 = "http://homecook.me/myTweetMark/searchMyTweetmarks"
	static final String POST_REFERER_2 = "http://homecook.me/post/list"	
	
	def FACEBOOK_API_KEY = "73fa872f37177ce62aa78043accbe004"
	def FACEBOOK_SECRET = "90bff07f0e654e9abb65b09cac77710b"
	
	//String[] spamKeywords = {"\<script\>", "\</script\>"}
	
	
    /**
     * Administrator functions 
     */
     
    def isAdmin(String userName) {
    	
    	if (userName.equals("bilal")) {
    		return true
    	} else {
    		def adminAccess = AdminAccess.findWhere(name:userName,status:ACTIVE)
    		
    		if (adminAccess != null) {
    			return true
    		}
    	}
    	
    	return false
    }
     
    /**
     * The following methods are for dealing with create/delete mycategory and mytweetmark.  Update category method is for phase II
     */
    
     def createMyCategory(Long userId, MyCategory categoryObj) {
     	//assuming categoryId is provided
     	categoryObj.status 		= ACTIVE
     	categoryObj.userId 		= userId
     	
     	categoryObj.createTime  = new Date()
     	categoryObj.updateTime  = new Date()
     	
     	categoryObj.numMyTweets 	= 0
     	
     	if(!categoryObj.hasErrors() && categoryObj.save()) {
     		 incrementUserCategoryCount(categoryObj.userId)
             return SUCCESS
     	} else {
             return FAILURE
         }
     }
     
    //creates new category for the user and returns a mycategory id
    def createMyCategory(Long userId, Integer categoryId) {
    	
    	//first check how many categories currently the user has.  if 5, then no more categories.
    	
    	Integer numCategories = getNumUserCategories(userId)

    	if (numCategories >= 5) {
    		//TODO really should be throwing the right exceptions rather generic error
    		return FAILURE
    	}
    	def myCategory = new MyCategory(userId:userId, categoryId:categoryId, status:ACTIVE, createTime:new Date(), updateTime:new Date())
    	
    	if (myCategory.save()) {
    		//increment user category count
    		incrementUserCategoryCount(myCategory.userId)
    		return SUCCESS
    	} else {
    		return FAILURE
    	}
    }
    
    //deletes user category (status=delete) and all the mytweetmarks within (status=delete)
    def deleteMyCategory(Long userId, int categoryId) {
    	Integer returnVal = FAILURE
    	
    	def categoryObj = getMyCategory(userId, categoryId)
    	
    	if (categoryObj != null) {
    		categoryObj.status = DELETE
    		if  (!categoryObj.hasErrors() && categoryObj.save()) {
    			returnVal = SUCCESS
    		} else {
    			returnVal = FAILURE
    		}
    		
        	//make sure to delete all the myTweetMarks within myCategory
    		if (returnVal == SUCCESS) {
	    		List myTweetMarks = getMyTweetMarksByCategory(userId, categoryId)
	    		
	    		if (myTweetMarks != null && !myTweetMarks.isEmpty())
	    		for (int i=0; i<myTweetMarks.size(); i++) {
	    			deleteMyTweetMark(myTweetMarks.get(i))
	    		}
    		}
        	
        	//also decrement the total category count for the user
        	decrementUserCategoryCount(userId)
    	}
    	return returnVal
    }
    
    //get myCategory by Id
    def getMyCategory(Long userId, Integer categoryId) {
    	//TODO checking status
    	//return MyCategory.findWhere(userId:userId, categoryId:categoryId)
    	return MyCategory.findWhere(userId:userId, categoryId:categoryId, status:ACTIVE)
    }
    
    //get user categories
    def getMyCategories(Long userId) {
    	//return MyCategory.findAllWhere(userId:userId, status:ACTIVE)
    	return MyCategory.findAllByUserIdAndStatus(userId, ACTIVE, [sort:"createTime",order:"desc"])
    }
    
    def getNumCategoryTweets(Long userId, Integer categoryId) {
    	def myCategory = getMyCategory(userId, categoryId)
    	
    	if (myCategory != null) {
    		return myCategory.numMyTweets
    	} 
    }
    
    def incrementNumCategoryTweets(Long userId, Integer categoryId) {
    	def myCategory = getMyCategory(userId, categoryId)
    	
    	if (myCategory != null) {
    		myCategory.numMyTweets++
    		if  (!myCategory.hasErrors() && myCategory.save()) {
    			return SUCCESS
    		} else {
    			return FAILURE
    		}
    	}
    	
    	return FAILURE
    }
    
    def decrementNumCategoryTweets(Long userId, Integer categoryId) {
    	def myCategory = getMyCategory(userId, categoryId)
    	
    	if (myCategory != null) {
    		myCategory.numMyTweets--
    		if  (!myCategory.hasErrors() && myCategory.save()) {
    			return SUCCESS
    		} else {
    			return FAILURE
    		}
    	}
    	
    	return FAILURE
    }

    //get user categories, also fill in mytweetmarks using expandos or mop
    def getMyCategoriesWithMyTweetMarks(Long userId) {
		log.info("inside getMyCategoriesWithMyTweetMarks")
    	List returnCategoryWithMyTweetMarks = new ArrayList()
    	List categories = getMyCategories(userId)
    	
    	if (categories != null && !categories.isEmpty()) {
    		for (int i = 0; i < categories.size(); i++) {
    			//get all the mytweetmarks for the category
    			
    			List myTweetMarks = getMyTweetMarksByCategory(userId, categories.get(i).categoryId)
    			
    			if (getCategory(categories.get(i).categoryId)!=null) {
    				def expandoCategory = new Expando()
    				expandoCategory.name = getCategory(categories.get(i).categoryId).name
	    			expandoCategory.categoryId = categories.get(i).categoryId
	    			expandoCategory.tweets = myTweetMarks
	    			returnCategoryWithMyTweetMarks.add(expandoCategory)
    			}
    		}
    	}
    	
    	return returnCategoryWithMyTweetMarks
    }
    
    //TODO need to implement for data to be displayed for user
    def getMyCategoriesWithMyTweetMarks(String userName) {
    	println "inside getMyCategoriesWithMyTweetMarks : $userName"
    	def user
    	try {
    		user = memcachedGet(PREFIX_USERS_NAME+userName)
    	} catch (Exception e) {
    		log.error("Error accessing memcached: key " + PREFIX_USERS_NAME+userName)
    	}
    	
     	if (user == null) {
    		
    		log.info("User not found in cache, get it from db : " + userName)
    		println "User not found in cache, get it from db : $userName"
    		user = Users.findByUserName(userName)
    		
    		if (user == null) {
    			log.info("user doesn't exist in db. " + userName) 
    			println "user doesn't exist in db. $userName"
    			return null
    		} else {
    			log.info("let's put the user in cache : " + userName)
    			println "let's put the user in cache : $userName" 
    			try {
    				memcachedSet(PREFIX_USERS_NAME+userName, user)
    			} catch (Exception e) {
    				log.error("Error setting memcached: key " + PREFIX_USERS_NAME+userName)
    				println "Error setting memcached: key $PREFIX_USERS_NAME$userName"
    			}
    		}
    	} else {
    		log.info("User found in cache : " + userName)
    		println "User found in cache : $userName" 
    	}
    	return getMyCategoriesWithMyTweetMarks(user.id)
    }
    
    def createMyTweetMark(Long userId, MyTweetMark myTweetMark) {
       	if (checkForSpam(myTweetMark.description)) {
       		return FAILURE
       	}

     	//assuming categoryId is provided
     	myTweetMark.status 		= ACTIVE
     	myTweetMark.userId		= userId
     	myTweetMark.createTime  = new Date()
     	myTweetMark.updateTime  = new Date()
     	
     	if(!myTweetMark.hasErrors() && myTweetMark.save()) {
     		incrementNumCategoryTweets(myTweetMark.userId, myTweetMark.categoryId)
             return SUCCESS
     	} else {
             return FAILURE
         }
    }
    
    //creates new mytweetmark for the user.  Need a userid, category and a URL.  Should return a mytweetmark ID.  later we can add description
    def createMyTweetMark(MyTweetMark myTweetMark) {
    	//first check how many mytweetmarks currently the user has, for the mycategory.  if 5, then no more mytweetmarks.
    	
    	Integer numCategoryTweets = getNumCategoryTweets(myTweetMark.userId, myTweetMark.categoryId)
    	
    	if (numCategoryTweets >= 5) {
    		//TODO really should be throwing the right exceptions rather generic error
    		return FAILURE
    	}

    	def myTweetMarkObj = new MyTweetMark(userId:myTweetMark.userId, categoryId:myTweetMark.categoryId, url:myTweetMark.url, status:ACTIVE, createTime:new Date(), updateTime:new Date())
    	
    	if (myTweetMarkObj.save()) {
    		//increment tweetmark count in mycategory
    		incrementNumCategoryTweets(myTweetMark.userId, myTweetMark.categoryId)
    		return myTweetMarkObj.id
    	} else {
    		return FAILURE
    	}
    }
    
    //takes the mytweetmark object and extract userid, mytweetmark id to delete (status) the object.
    def deleteMyTweetMark(Long userId, Integer categoryId, Integer id) {
    	def myTweetMarkObj = getMyTweetMark(id)
    	
    	if (myTweetMarkObj != null) {
    		myTweetMarkObj.status = DELETE
    		if  (!myTweetMarkObj.hasErrors() && myTweetMarkObj.save()) {
    			//decrement tweetmark count in mycategory
    			decrementNumCategoryTweets(userId, categoryId)
    			return SUCCESS
    		} else {
    			return FAILURE
    		}
    	}
    	
    	return FAILURE
    }

    //takes the mytweetmark object and extract userid, mytweetmark id to delete (status) the object.
    def deleteMyTweetMark(MyTweetMark myTweetMark) {
    	def myTweetMarkObj = getMyTweetMark(myTweetMark.id)
    	
    	if (myTweetMarkObj != null) {
    		myTweetMarkObj.status = DELETE
    		if  (!myTweetMarkObj.hasErrors() && myTweetMarkObj.save()) {
    			//decrement tweetmark count in mycategory
    			decrementNumCategoryTweets(myTweetMark.userId, myTweetMark.categoryId)
    			return SUCCESS
    		} else {
    			return FAILURE
    		}
    	}
    	
    	return FAILURE
    }
    
    //takes the mytweetmark object and update the object.  used by phase II implementation when we add description for mytweetmark
    def updateMyTweetMark(MyTweetMark myTweetMark) {
    	if(myTweetMark.save()) {
            return SUCCESS
        } else {
            return FAILURE
        }
    }
    
    //get user mytweetmarks.  returns List
    def getMyTweetMarks(Long userId) {
    	//TODO checking active status
    	//return MyTweetMark.findAllByUserId(userId, [order:"asc"])
    	return MyTweetMark.findAllWhere(userId:userId, status:ACTIVE)
    }
    
    //get a mytweetmark object
    def getMyTweetMark(Long id) {
    	//TODO checking active
    	//return MyTweetMark.get(id)
    	return MyTweetMark.findWhere(id:id, status:ACTIVE)
    }
    
    //get user mytweetmarks, via category.  returns List
    def getMyTweetMarksByCategory(Long userId, Integer categoryId) {
    	//TODO checking for status
    	//return MyTweetMark.findAllWhere(userId:userId, categoryId:categoryId)
    	return MyTweetMark.findAllWhere(userId:userId, categoryId:categoryId, status:ACTIVE)
    }
    
    def getMyTweetMarksWithUserInfo(int offset, int max) {
 	   log.info("inside getMyTweetMarksWithUserInfo")
 	   
    		List returnMyTweetmarkWithUserInfo = new ArrayList()
    		//List posts = Post.list(max:50, order:"desc")
    		List myTweetMarks = MyTweetMark.findAllByStatus(ACTIVE, [max:max, offset:offset, sort:"createTime", order:"desc"] )

    		if (myTweetMarks != null && !myTweetMarks.isEmpty()) {
    		for (int i = 0; i < myTweetMarks.size(); i++) {
    			
    			def user = getUser(myTweetMarks.get(i).userId)
    			
    			def expandoMyTweetMark = new Expando()
    			expandoMyTweetMark.myTweetMark = myTweetMarks.get(i)
    			expandoMyTweetMark.user = user
    			returnMyTweetmarkWithUserInfo.add(expandoMyTweetMark)
    		}
    	}
    	
    	return returnMyTweetmarkWithUserInfo
    }
    
    def getMyTweetMarkCount() {
 	   return MyTweetMark.countByStatus(ACTIVE)

    }
    
    /**
     * User centric methods
     */
    
     //method used by login.  look up the user in the cache, if not there, read from db and put in cache.  also make sure password matches
    def verifyUser(String userName, String password) {
    	def user
    	try {
    		def key = PREFIX_USERS_NAME+userName
    		println "search key: $key"
    		user = memcachedGet(PREFIX_USERS_NAME+userName)
    	} catch (Exception e) {
    		log.error("Error accessing memcached: key " + PREFIX_USERS_NAME+userName)
    		e.printStackTrace()
    	}
    	
     	if (user == null) {
    		
    		log.info("User not found in cache, get it from db : " + userName)
    		println "User not found in cache, get it from db : $userName"
    		user = Users.findWhere(userName:userName, password:password, status:ACTIVE)
    		
    		if (user == null) {
    			log.info("user doesn't exist in db. " + userName) 
    			println "user doesn't exist in db. $userName"
    			return null
    		} else {
    			log.info("let's put the user in cache : " + userName)
    			println "let's put the user in cache : $userName" 
    			try {
    				def putKey = PREFIX_USERS_NAME+userName
    				println "put key: $putKey"
    				memcachedSet(PREFIX_USERS_NAME+userName, user)
    			} catch (Exception e) {
    				log.error("Error setting memcached: key " + PREFIX_USERS_NAME+userName)
    				println "Error setting memcached: key $PREFIX_USERS_NAME$userName"
    				e.printStackTrace()
    			}
    		}
    	} else {
    		log.info("User found in cache : " + userName)
    		println "User found in cache : $userName" 
    	}
    	
    	//found the user, match the password
    	if (!user.password.equals(password)) {
    		log.info("User password didn't match : " + userName)
    		println "User password didn't match : $userName"
    		try {
    			memcachedDelete(PREFIX_USERS_NAME+userName)
    		} catch (Exception e) {
    			log.error("Error deleting memcached: key " + PREFIX_USERS_NAME+userName)
    			println "Error deleting memcached: key $PREFIX_USERS_NAME$userName"
    			e.printStackTrace()
    		}
    		return null
    	}
    	return user
    }
    
    def createUser(Users userObj) {
    	//assuming userName, firstName, lastName, email and password is populated.  only userName, email and password are required.  
    	userObj.status = ACTIVE
    	userObj.marketingId = DEFAULT_MARKETING
    	
    	userObj.countryId = getUserCountry(userObj.ipAddress)
    	
    	userObj.createTime = new Date()
    	userObj.updateTime = new Date()
    	userObj.lastLogin = new Date()
    	userObj.loginTimes = 0
    	userObj.numCategories = 0
    	
    	if(!userObj.hasErrors() && userObj.save()) {
            return SUCCESS
    	} else {
            return FAILURE
        }
    }
    
    def getUser(Long userId) {
    	return Users.findWhere(id:userId, status:ACTIVE)
    	
    }
    
    def getUser(String userName) {
    	return Users.findWhere(userName:userName, status:ACTIVE)
    	
    }
    
    def updateLoginStats(Long userId) {
    	def user = getUser(userId)
    	
    	if (user != null) {
    		user.lastLogin = new Date()
    		user.loginTimes++
    		user.id = userId
    		
    		if(!user.hasErrors() && user.save()) {
                return true
            }
            else {
                return false
            }
    	}
    }
    
    def getNumUserCategories(Long userId) {
    	return getUser(userId).numCategories
    }
    
    def decrementUserCategoryCount(Long userId) {
    	def user = getUser(userId)
    	
    	if (user != null) {
    		user.numCategories--
    		if  (!user.hasErrors() && user.save()) {
    			return SUCCESS
    		} else {
    			return FAILURE
    		}
    	}
    	
    	return FAILURE
    }
    
    def incrementUserCategoryCount(Long userId) {
    	def user = getUser(userId)
    	
    	if (user != null) {
    		user.numCategories++
    		if  (!user.hasErrors() && user.save()) {
    			return SUCCESS
    		} else {
    			return FAILURE
    		}
    	}
    	
    	return FAILURE
    }
    
    //this method to be used for disabling user.  it will disable all content belonging to this user.  admin_access, blog, comment, mycategory, mytweetmark, post, users
    def disableUser(String userName) {
    	Users user = getUser(userName)
    	
    	if (user != null) {
    		user.status = DISABLE
    		
    		if(!user.hasErrors() && user.save()) {
    			System.out.println("user disabled : " + userName)
    			//if it worked, let's do disable other features for the user
    			AdminAccess adminAccess = AdminAccess.findWhere(name:userName)
    			
    			if (adminAccess != null) {
    				adminAccess.status = DISABLE
    				if(!adminAccess.hasErrors() && adminAccess.save()) {
    					System.out.println("admin access disabled: " + userName)
    				}
    			}
    			
    			//only get blogs that are active by the user
    			def blogs = Blog.findAllWhere(userId:user.id, status:ACTIVE)
    			
    			if (blogs != null && !blogs.isEmpty()) {
    				for (int i=0; i<blogs.size(); i++) {
    					blogs.get(i).status = DISABLE
    					if(!blogs.get(i).hasErrors() && blogs.get(i).save()) {
        					System.out.println("blog disabled: " + blogs.get(i).id)
        				}
    				}
    			}
    			
    			//only get Comments that are active by the user
    			def comments = Comment.findAllWhere(userId:user.id, status:ACTIVE)
    			
    			if (comments != null && !comments.isEmpty()) {
    				for (int i=0; i<comments.size(); i++) {
    					comments.get(i).status = DISABLE
    					if(!comments.get(i).hasErrors() && comments.get(i).save()) {
        					System.out.println("comment disabled: " + comments.get(i).id)
        				}
    				}
    			}
    			
    			//only get mycategory that are active by the user
    			def myCategories = MyCategory.findAllWhere(userId:user.id, status:ACTIVE)
    			
    			if (myCategories != null && !myCategories.isEmpty()) {
    				for (int i=0; i<myCategories.size(); i++) {
    					myCategories.get(i).status = DISABLE
    					if(!myCategories.get(i).hasErrors() && myCategories.get(i).save()) {
        					System.out.println("myCategories disabled: " + myCategories.get(i).userId)
        				}
    				}
    			}
    			
    			//only get mytweetmark that are active by the user
    			def myTweetmarks = MyTweetMark.findAllWhere(userId:user.id, status:ACTIVE)
    			
    			if (myTweetmarks != null && !myTweetmarks.isEmpty()) {
    				for (int i=0; i<myTweetmarks.size(); i++) {
    					myTweetmarks.get(i).status = DISABLE
    					if(!myTweetmarks.get(i).hasErrors() && myTweetmarks.get(i).save()) {
        					System.out.println("myTweetmarks disabled: " + myTweetmarks.get(i).userId)
        				}
    				}
    			}
    			
    			//only get posts that are active by the user
    			def posts = Post.findAllWhere(userId:user.id, status:ACTIVE)
    			
    			if (posts != null && !posts.isEmpty()) {
    				for (int i=0; i<posts.size(); i++) {
    					posts.get(i).status = DISABLE
    					if(!posts.get(i).hasErrors() && posts.get(i).save()) {
        					System.out.println("posts disabled: " + posts.get(i).userId)
        				}
    				}
    			}
    		}
    	}
    }
    
    def activeUser(String userName) {
    	Users user = Users.findWhere(userName:userName,status:DISABLE)
    	
    	if (user != null) {
    		user.status = ACTIVE
    		
    		if(!user.hasErrors() && user.save()) {
    			System.out.println("user active : " + userName)
    			//if it worked, let's do active other features for the user
    			AdminAccess adminAccess = AdminAccess.findWhere(name:userName)
    			
    			if (adminAccess != null) {
    				adminAccess.status = ACTIVE
    				if(!adminAccess.hasErrors() && adminAccess.save()) {
    					System.out.println("admin access active: " + userName)
    				}
    			}
    			
    			//only get blogs that are active by the user
    			def blogs = Blog.findAllWhere(userId:user.id, status:DISABLE)
    			
    			if (blogs != null && !blogs.isEmpty()) {
    				for (int i=0; i<blogs.size(); i++) {
    					blogs.get(i).status = ACTIVE
    					if(!blogs.get(i).hasErrors() && blogs.get(i).save()) {
        					System.out.println("blog active: " + blogs.get(i).id)
        				}
    				}
    			}
    			
    			//only get Comments that are active by the user
    			def comments = Comment.findAllWhere(userId:user.id, status:DISABLE)
    			
    			if (comments != null && !comments.isEmpty()) {
    				for (int i=0; i<comments.size(); i++) {
    					comments.get(i).status = ACTIVE
    					if(!comments.get(i).hasErrors() && comments.get(i).save()) {
        					System.out.println("comment active: " + comments.get(i).id)
        				}
    				}
    			}
    			
    			//only get mycategory that are active by the user
    			def myCategories = MyCategory.findAllWhere(userId:user.id, status:DISABLE)
    			
    			if (myCategories != null && !myCategories.isEmpty()) {
    				for (int i=0; i<myCategories.size(); i++) {
    					myCategories.get(i).status = ACTIVE
    					if(!myCategories.get(i).hasErrors() && myCategories.get(i).save()) {
        					System.out.println("myCategories active: " + myCategories.get(i).userId)
        				}
    				}
    			}
    			
    			//only get mytweetmark that are active by the user
    			def myTweetmarks = MyTweetMark.findAllWhere(userId:user.id, status:DISABLE)
    			
    			if (myTweetmarks != null && !myTweetmarks.isEmpty()) {
    				for (int i=0; i<myTweetmarks.size(); i++) {
    					myTweetmarks.get(i).status = ACTIVE
    					if(!myTweetmarks.get(i).hasErrors() && myTweetmarks.get(i).save()) {
        					System.out.println("myTweetmarks active: " + myTweetmarks.get(i).userId)
        				}
    				}
    			}
    			
    			//only get posts that are active by the user
    			def posts = Post.findAllWhere(userId:user.id, status:DISABLE)
    			
    			if (posts != null && !posts.isEmpty()) {
    				for (int i=0; i<posts.size(); i++) {
    					posts.get(i).status = ACTIVE
    					if(!posts.get(i).hasErrors() && posts.get(i).save()) {
        					System.out.println("posts active: " + posts.get(i).userId)
        				}
    				}
    			}
    		}
    	}
    }
    
    def getTweet(Long userId) {
    	def user = getUser(userId)
    	
    	if (user != null) {
	    	Integer tweet = user.tweet
	    	
	    	if (tweet == null) {
	    		return ON
	    	} else {
	    		return tweet
	    	}
    	}
    	return ON
    }
    
    def setTweet(Long userId, int tweet) {
    	def user = getUser(userId)
    	
    	if (user != null) {
    		user.tweet = tweet
    		
    		if(!user.hasErrors() && user.save()) {
    			return SUCCESS
    		} 
    	}
    	return FAILURE 
    }
    
    //this method checks if a facebook user is already in place.
    def getUserByFacebookUid(String uid) {
    	return Users.findWhere(facebookUid:uid)
    }
    /**
     * The following methods are for extracting category and countries
     * 
     */
     
    def getCountries() {
    	 return Country.getAll()
    }
    
    def getCountryByCode(String twoLetterCode) {
    	Country country = Country.findWhere(code:twoLetterCode)
    	
    	if (country != null) {
    		//found the country
    		return country.id
    	} else {
    		log.info("Country not found for two letter code: " + twoLetterCode + ", setting default country: " + DEFAULT_COUNTRY)
    		return DEFAULT_COUNTRY
    	}
    }
    
    //TODO fill in user country from geo ip lookup
    def getUserCountry(String ip) {
    	//first lets look in geo ip and get the two digit country code.  we then need to find an internal country id, from the two digit code
    	
    	String twoLetter = getCountryByIp(ip)
    	
    	if (twoLetter == null || twoLetter.equals("")) {
    		log.info("country not found for ip: " + ip + ", setting default country: " + DEFAULT_COUNTRY)
    		return DEFAULT_COUNTRY
    	} else {
    		return getCountryByCode(twoLetter)
    	}
    }
    
    def getCategories() {
    	 return Categories.findAllByStatus(ACTIVE, [sort:"orderBy", order:"asc"])
    }
    
    def getCategory(int id) {
    	return Categories.get(id)
    }
    
    /**
     * Invite centric methods
     */
     
    def getContacts(String email, String password) {
    	List<octazen.addressbook.Contact> listContacts = null
    	try {
			listContacts = octazen.addressbook.SimpleAddressBookImporter.fetchContacts(email,password)
			
		} catch (Exception e) {
			log.error(e.printStackTrace())
		}
		return listContacts
    }
    
    def updateViralAddressImported(Long userId, Integer contactsSize) {
    	def viralObj = UsersViral.findWhere(userId:userId)
    	
    	//find out whether user viral object exists, if not create it.  then update last contacts size and then increment total contacts size
    	if (!viralObj) {
    		log.info("viralObj not found, creating new")
    		viralObj = new UsersViral()
    		viralObj.userId = userId
    		viralObj.createTime = new Date()
    		viralObj.updateTime = new Date()
    		viralObj.lastTimeAddressImported = contactsSize
    		viralObj.totalAddressImported = contactsSize
    		if  (!viralObj.hasErrors() && viralObj.save()) {
        		log.info("no error creating viral object")
    			return SUCCESS
    		} else {
    			log.error("error creating viral object")
    			return FAILURE
    		}
    		
    	} else {
    		log.info("viralObj found")
    		viralObj.lastTimeAddressImported = contactsSize
    		viralObj.updateTime = new Date()
    		viralObj.totalAddressImported+=contactsSize
    		if  (!viralObj.hasErrors() && viralObj.save()) {
        		log.info("no error updating viral object")
    			return SUCCESS
    		} else {
    			log.error("error creating viral object")
    			return FAILURE
    		}
    	}
    	
    }
    
    def processInvites(Long userId, String userName) {
    	return sendSelfMyTweetMark(userId, userName)
    }
    
    def sendSelfMyTweetMark(Long userId, String userName) {
		List myCategories = getMyCategoriesWithMyTweetMarks(userId)
		
		if (myCategories != null && !myCategories.isEmpty()) {
			def bodyStr = "<b>" + userName + "'s mytweetmarks ;)</b><br><br>"
			for (int i=0; i<myCategories.size(); i++) {
				bodyStr+="<i>" + myCategories.get(i).name + "</i><br>"
				List tweets = myCategories.get(i).tweets
				if (tweets != null && !tweets.isEmpty()) {
					for (int j=0; j<tweets.size(); j++) {
						bodyStr+=tweets.get(j).url+"<br>"
						if (tweets.get(j).description != null && !tweets.get(j).description.isEmpty()) {
							bodyStr+=tweets.get(j).description+"<br>"
						}
					}
				} else {
					bodyStr+="Empty<br>"
				}
				bodyStr+="<br>"
			}
			bodyStr += "<br>Thank you for using www.mytweetmark.com :)<br>"
			log.info("$bodyStr")
			return bodyStr
		}
    }
    
    /*
     * The following methods are for geo ip lookup
     */
     
     //GetCountryByIp returns a two digit code
     def getCountryByIp(String ip) { 
    	 try {
    		 if (locationService ==null) {
    			 synchronized(locationService) {
    				 locationService = new com.maxmind.geoip.LookupService("/usr/share/maxmind/GeoIP.dat")
    			 }
    		 }
    		 
    		 if (location == null) {
    			 location = locationService.getLocation(ip)
    		 }
    		 
    		 if (location != null) {
    			 //could also return postalCode and region
    			 return location.countryCode()
    		 } else {
    			 return ""
    		 }
    	 } catch (Exception e) {
    		 log.error("Error accessing geoip lookup")
    	 } 
    	 
    	 return ""
     }
    
     /*
      * Tracking calls
      */
      
      //This method gets called everytime main index.gsp is called.  Very important method for tracking.
      def trackReferer(String referer) {

     	log.info("inside trackReferer")
     	
     	if (referer!=null && !referer.equals("")) {
 	    	def internalRefererInstance = new InternalReferer()
 	    	log.info("REFERER:" + referer)
 	    	
 	    	internalRefererInstance.url=referer
 	    	internalRefererInstance.createTime=new Date()
 	    	InternalReferer.withTransaction { s ->
	 	        if(!internalRefererInstance.hasErrors() && internalRefererInstance.save()) {
	 	            log.info("referer saved")
	 	        }
 	    	}
     	}
     }
     
     def trackExternalIp(String ip) {
    	 log.info("inside trackExternalIp")
      	
      	if (ip!=null && !ip.equals("")) {
  	    	def externalIpInstance = new ExternalIp()
  	    	log.info("IP:" + ip)
  	    	
  	    	externalIpInstance.ip=ip
  	    	externalIpInstance.createTime=new Date()
  	        if(!externalIpInstance.hasErrors() && externalIpInstance.save()) {
  	            log.info("ip saved")
  	        }
      	}
     }
     
     def trackProfileReferer(String url, String referer) {

    	 try {

	      	if (referer!=null && !referer.equals(HOME_REFERER) && !referer.equals(SEARCH_REFERER) && !referer.equals(POST_REFERER) && !referer.equals("") && url!=null && !url.equals("") && !referer.equals(HOME_REFERER_2) && !referer.equals(SEARCH_REFERER_2) && !referer.equals(POST_REFERER_2)) {
	  	    	def profileRefererInstance = new ProfileReferer()
	  	    	profileRefererInstance.name=url
	  	    	profileRefererInstance.refererUrl=referer
	  	    	profileRefererInstance.createTime=new Date()
	  	        if(!profileRefererInstance.hasErrors() && profileRefererInstance.save()) {
	  	            System.out.println("profileRefererInstance saved")
	  	        } else {
	  	        	System.out.println("profileRefererInstance not saved")
	  	        }
	      	}
    	 } catch (Exception e) {
    		 e.printStackTrace()
    	 }
      }
     
     /*
      * Memcached calls
      */
      
      def memcachedClientInit() {

		 synchronized(mcc) {
			try { 
				 //server list and weights
				String[] servers =
					{
					  "74.208.44.114:1124"
					};
	
				//Integer[] weights = { new Integer(1) };
	
				// grab an instance of our connection pool
				pool = SockIOPool.getInstance();
	
				// set the servers and the weights
				pool.setServers( servers );
				//pool.setWeights( weights );
	
				// set some basic pool settings
				// 5 initial, 5 min, and 250 max conns
				// and set the max idle time for a conn
				// to 6 hours
				pool.setInitConn( 1 );
				pool.setMinConn( 1 );
				pool.setMaxConn( 10 );
				pool.setMaxIdle( 1000 * 60 * 60 * 6 );
	
				// set the sleep for the maint thread
				// it will wake up every x seconds and
				// maintain the pool size
				pool.setMaintSleep( 30 );
	
				// set some TCP settings
				// disable nagle
				// set the read timeout to 3 secs
				// and don't set a connect timeout
				pool.setNagle( false );
				pool.setSocketTO( 3000 );
				pool.setSocketConnectTO( 0 );
	
				// initialize the connection pool
				pool.initialize();
	
	
				// lets set some compression on for the client
				// compress anything larger than 64k
				mcc.setCompressEnable( true );
				mcc.setCompressThreshold( 64 * 1024 );
				println "Memcached Initialized!"
			} catch (Exception e) {
				println "Exception caught: Memcached Initialized!"
				e.printStackTrace()
			}
		 }
     }
     
     def memcachedGet(String key) {
    	 if (mcc == null) {
    		 mcc = new MemCachedClient()
    		 memcachedClientInit()
    	 }
    	 return mcc.get(key)
     }
     
     def memcachedSet(String key, Object) {
    	 if (mcc == null) {
    		 mcc = new MemCachedClient()
    		 memcachedClientInit()
    	 }
    	 mcc.set(key, Object)
     }
     
     def memcachedDelete(String key) {
    	 if (mcc == null) {
    		 mcc = new MemCachedClient()
    		 memcachedClientInit()
    	 }
    	 mcc.delete(key)
     }
     
     def memcachedGetMulti(String[] keys) {
    	 if (mcc == null) {
    		 mcc = new MemCachedClient()
    		 memcachedClientInit()
    	 }
    	 //returns Map<Object>
    	 return mcc.getMulti(keys)
     }
     
     def memcachedFlushAll() {
    	 if (mcc == null) {
    		 mcc = new MemCachedClient()
    		 memcachedClientInit()
    	 }
    	 mcc.flushAll()
     }
     
     /*
      * Methods around feature toggle, turn on and off
      */
      
      def isFeatureToggleOn(String name) {
    	  
    	 def featureToggle = FeatureToggle.findWhere(name:name)
  		
		 if (featureToggle != null && featureToggle.status != OFF) {
		    return true
		 } else {
			return false
		 }
      }
     
      def isFeatureToggleReadOnly(String name) {
    	  
     	 def featureToggle = FeatureToggle.findWhere(name:name)
   		
 		 if (featureToggle != null && featureToggle.status == READ_ONLY) {
 		    return true
 		 } else {
 			return false
 		 }
       }
      
      /*
       * Search calls
       */
       
      def searchMarket(String searchMarket) {
    	  def searchResult = searchableService.search(searchMarket, defaultOperator: "or")
    	  def returnResults = new ArrayList()
    	  
    	  for (i in 0..<searchResult.results.size()) {
    		  if (searchResult.results[i] instanceof FarmerMarket) {
    			  def market = searchResult.results[i]
    			  
    			  if (market.status == ACTIVE) {
    				  returnResults.add(market)
    			  }
    		  }
    	  }
    	  return returnResults
      }
       def search(String searchTerm) {
    	  def searchResult = searchableService.search(searchTerm, defaultOperator: "or")
    	  
    	  def returnResults = new ArrayList()
    	  def checkIds = new ArrayList()
    	  
    	  for (i in 0..<searchResult.results.size()) {
    		  if (searchResult.results[i] instanceof Users) {
    			  Integer userId = searchResult.results[i].id
    			  
    			  if (userId != null) {
	    			  if (!doesRecordExist(userId, checkIds)) {
	    				  def user = getUser(userId)
	    				  if (user != null) {
	    					  returnResults.add(user)
	    					  checkIds.add(userId)
	    				  }
	    			  }
    			  }
    		  } else if (searchResult.results[i] instanceof MyTweetMark) {
    			  
    			  Integer userId = searchResult.results[i].userId
    			  
    			  if (userId != null) {
	    			  if (!doesRecordExist(userId, checkIds)) {
	    				  def user = getUser(userId)
	    				  if (user != null) {
	    					  returnResults.add(user)
	    					  checkIds.add(userId)
	    				  }
	    			  }
    			  }
    		  } else if (searchResult.results[i] instanceof Post) {
    			  
    			  Integer userId = searchResult.results[i].userId
    			  
    			  if (userId != null) {
	    			  if (!doesRecordExist(userId, checkIds)) {
	    				  def user = getUser(userId)
	    				  if (user != null) {
	    					  returnResults.add(user)
	    					  checkIds.add(userId)
	    				  }
	    			  }
    			  }
    		  } else if (searchResult.results[i] instanceof Blog) {
    			  
    			  Integer userId = searchResult.results[i].userId
    			  
    			  if (userId != null) {
	    			  if (!doesRecordExist(userId, checkIds)) {
	    				  def user = getUser(userId)
	    				  if (user != null) {
	    					  returnResults.add(user)
	    					  checkIds.add(userId)
	    				  }
	    			  }
    			  }
    		  } else if (searchResult.results[i] instanceof Recipe) {
    			  
    			  Integer userId = searchResult.results[i].userId
    			  
    			  if (userId != null) {
	    			  if (!doesRecordExist(userId, checkIds)) {
	    				  def user = getUser(userId)
	    				  if (user != null) {
	    					  returnResults.add(user)
	    					  checkIds.add(userId)
	    				  }
	    			  }
    			  }
    		  } else if (searchResult.results[i] instanceof Product) {
    			  
    			  Integer userId = searchResult.results[i].userId
    			  
    			  if (userId != null) {
	    			  if (!doesRecordExist(userId, checkIds)) {
	    				  def user = getUser(userId)
	    				  if (user != null) {
	    					  returnResults.add(user)
	    					  checkIds.add(userId)
	    				  }
	    			  }
    			  }
    		  }
    	  }
    	  return returnResults
      }
      
      def doesRecordExist(Integer id, List results) {
    	  if (!results.isEmpty()) {
	    	  for (int i = 0; i< results.size(); i++) {
	    		 if (id == results.get(i)) {
	    			 return true;
	    		 }
	    	  }
    	  }
    	  return false;
      }
      
      /**
       * The following methods are for dealing with posts
       */
      
       def createMyPost(Long userId, Post postObj) {
    	   //check here whether the data has any invalid characters.
       	if (checkForSpam(postObj.description)) {
       		return FAILURE
       	}

       	postObj.status 		= ACTIVE
       	postObj.userId 		= userId
       	
       	postObj.createTime  = new Date()
       	
       	if(!postObj.hasErrors() && postObj.save()) {
       		   return SUCCESS
       	} else {
               return FAILURE
           }
       }
      
       def getPosts(Long userId) {
	       	//return Post.findAllByUserIdAndStatus(userId, ACTIVE, [sort:"createTime",order:"desc"])
	       	
	       	log.info("inside getPosts")
	   		List returnPostsWithComments = new ArrayList()
	   		List posts = Post.findAllByUserIdAndStatus(userId, ACTIVE, [sort:"createTime",order:"desc"])
	
	   		if (posts != null && !posts.isEmpty()) {
	   			for (int i = 0; i < posts.size(); i++) {
	   			
		   			def comments = getComments(posts.get(i).id, POST_TYPE)
		   			List expandoComments = new ArrayList()
		   			if (comments != null && !comments.isEmpty()) {
		   				for (int j=0; j < comments.size(); j++) {
		   					def expandoComment = new Expando()
			   				expandoComment.comment = comments.get(j)
			   				expandoComment.user = getUser(comments.get(j).userId)
			   				expandoComments.add(expandoComment)
		   				}
		   				
		   			}
		   			def expandoPost = new Expando()
		   			expandoPost.post = posts.get(i)
		   			expandoPost.comments = expandoComments
		   			returnPostsWithComments.add(expandoPost)
	   			}
	   		}
	   	
	       	return returnPostsWithComments
       }
       
//     takes the post object and extract userid, mytweetmark id to delete (status) the object.
       def deletePost(Long userId, Long id) {
    	   
       	Post postObj = getPost(id)
       	
       	if (postObj != null) {
       		
       		if (postObj.userId != userId) {
       			return FAILURE
       		}
       		postObj.status = DELETE
       		
       		if  (!postObj.hasErrors() && postObj.save()) {
       			return SUCCESS
       		} else {
       			return FAILURE
       		}
       	}
       	
       	return FAILURE
       }

       def getPost(Long id) {
       	return Post.findWhere(id:id, status:ACTIVE)
       }
       
       def getPostsWithUserInfo(int offset, int max) {
    	   log.info("inside getPostsWithUserInfo")
    	   
       		List returnPostsWithUserInfo = new ArrayList()
       		//List posts = Post.list(max:50, order:"desc")
       		List posts = Post.findAllByStatus(ACTIVE, [max:max, offset:offset, sort:"createTime", order:"desc"] )

       		if (posts != null && !posts.isEmpty()) {
       		for (int i = 0; i < posts.size(); i++) {
       			
       			def user = getUser(posts.get(i).userId)
       			
       			def expandoPost = new Expando()
       			expandoPost.post = posts.get(i)
       			expandoPost.user = user
       			
       			def comments = getComments(posts.get(i).id, POST_TYPE)
	   			List expandoComments = new ArrayList()
	   			if (comments != null && !comments.isEmpty()) {
	   				for (int j=0; j < comments.size(); j++) {
	   					def expandoComment = new Expando()
		   				expandoComment.comment = comments.get(j)
		   				expandoComment.user = getUser(comments.get(j).userId)
		   				expandoComments.add(expandoComment)
	   				}
	   				
	   			}
       			
       			expandoPost.comments = expandoComments
       			returnPostsWithUserInfo.add(expandoPost)
       		}
       	}
       	
       	return returnPostsWithUserInfo
       }
       
       def getPostCount() {
    	   return Post.countByStatus(ACTIVE)

       }
       
       def getLastPost(Long userId) {
     	  return Post.findByUserIdAndStatus(userId, ACTIVE, [max:1, sort:"createTime", order:"desc"])
     	    
       }
       
       /**
        * The following methods are for dealing with file storage
        */
        
        def uploadPhoto(Long userId, File file) {
        	println "inside uploadPhoto"
        	
        	String fileName = file.getAbsolutePath()
        	
        	println "inside $fileName"
        	
        	ImagePlus image = ImagingService.openImagePlus(fileName);
        	try {
        		ImagingService.generate100x100(image, fileName, true);
        		ImagingService.generate50x50(image, fileName, true);
        	} catch (Exception e) {
        		
        		log.error("uploadPhoto: error generating 100x100 image")
        		println "uploadPhoto: error generating 100x100 image"
        		e.printStackTrace()
        		
        	}
  	      
        }
       
        def uploadPhoto(Long userId, String url) {
        	println "inside uploadPhoto"
        	
        	println "url $url"
        	
        	ImagePlus image = ImagingService.openImagePlusUrl(url);
        	try {
        		String outputOrig = FILE_STORAGE_PREFIX + userId
        		String output100 = FILE_STORAGE_PREFIX + userId + "-01"
        		String output50 = FILE_STORAGE_PREFIX + userId + "-02"
        		ImagingService.generateImage100(image, output100, true) 
        		ImagingService.generateImage50(image, output50, true) 
        		ImagingService.generateImage650(image, outputOrig, true) 
        	} catch (Exception e) {
        		
        		log.error("uploadPhoto: error generating 100x100 image")
        		println "uploadPhoto: error generating 100x100 image"
        		e.printStackTrace()
        		
        	}
  	      
        }
        
        def uploadPhoto(Integer marketId, File file) {
        	println "inside uploadPhoto"
        	
        	String fileName = file.getAbsolutePath()
        	
        	println "inside $fileName"
        	
        	ImagePlus image = ImagingService.openImagePlus(fileName);
        	try {
        		ImagingService.generate100x100(image, fileName, true);
        		ImagingService.generate50x50(image, fileName, true);
        	} catch (Exception e) {
        		
        		log.error("uploadPhoto: error generating 100x100 image")
        		println "uploadPhoto: error generating 100x100 image"
        		e.printStackTrace()
        		
        	}
  	      
        }
       /** 
        * The following methods are dealing with comments
        */
        
        def getCommentsWithUserInfo(Long contentId, String contentType) {
        	def comments = getComments(contentId, RECIPE_TYPE)
   			List expandoComments = new ArrayList()
   			if (comments != null && !comments.isEmpty()) {
   				for (int j=0; j < comments.size(); j++) {
   					def expandoComment = new Expando()
	   				expandoComment.comment = comments.get(j)
	   				expandoComment.user = getUser(comments.get(j).userId)
	   				expandoComments.add(expandoComment)
   				}
   				return expandoComments
   			}
        	return null
       	}
        def getComments(Long contentId, String contentType) {
    	    Comment.findAllWhere(contentId:contentId,contentType:contentType,status:ACTIVE)
        }
       
        def getCommentsByUserId(Long userId) {
    	    def comments = Comment.findAllByUserIdAndStatus(userId, ACTIVE, [sort:"createTime", order:"desc"])
    	    def returnComments = new ArrayList()
    	    
    	    if (comments != null && !comments.isEmpty()) {
    	    	for (int i=0; i<comments.size(); i++) {
    	    		if (comments.get(i).contentType.equals(POST_TYPE) ) {
    	    			def post = getPost(comments.get(i).contentId)
    	    			
    	    			if (post != null && post.userId!=userId) {
    	    				def postUser = getUser(post.userId)
    	    				if (postUser != null) {
    	    					def expandoComment = new Expando()
    	    					expandoComment.comment = comments.get(i)
    	    	       			expandoComment.user = postUser
    	    	       			expandoComment.post = post
    	    	       			returnComments.add(expandoComment)
    	    				}
    	    			}
    	    		}
    	    	}
    	    }
    	    return returnComments
        }
       
        def createMyComment(Long userId, Comment comment) {
        	//check here whether the data has any invalid characters.
        	if (checkForSpam(comment.description)) {
        		return FAILURE
        	}
        	
        	comment.status 		= ACTIVE
        	comment.userId		= userId
        	comment.createTime  = new Date()
        	comment.updateTime  = new Date()
         	
         	if(!comment.hasErrors() && comment.save()) {
         		 return SUCCESS
         	} else {
         		comment.errors.each {
      	          println it
      	    }
                 return FAILURE
             }
        }
        
//      takes the comment object and extract userid to delete (status) the object.
        def deleteComment(Long userId, Long id) {
     	   
        	Comment commentObj = getComment(id)
        	
        	if (commentObj != null) {
        		
        		if (commentObj.userId != userId) {
        			return FAILURE
        		}
        		commentObj.status = DELETE
        		
        		if  (!commentObj.hasErrors() && commentObj.save()) {
        			return SUCCESS
        		} else {
        			return FAILURE
        		}
        	}
        	
        	return FAILURE
        }

        def getComment(Long id) {
           	return Comment.findWhere(id:id, status:ACTIVE)
           }
        
        /** 
         * Twitter centric methods
         */
         
         def registerTwitterer(String userName, String password) {
        	try {
        		String photoImageUrl = null 
        		Users user = new Users()
        		user.userName = userName
        		user.password = password
        		user.profilePhoto = photoImageUrl
        		createUser(user)
        		
        		if (user.profilePhoto != null && !user.profilePhoto.isEmpty()) {
	        		try {
	        			uploadPhoto(user.id, user.profilePhoto)
	        		} catch (Exception e) {
	        			System.out.println("Couldn't process image: " + user.profilePhoto)
	            		e.printStackTrace()
	        		}
        		}
        		return user.id
        		
        	} catch (Exception e) {
        		System.out.println("Failed login: " + userName)
        		e.printStackTrace()
        		return FAILURE
        	}
        }
        
         /** 
          * The following methods are dealing with blogs
          */
          
          def getBlogs(Long userId) {
      	    return Blog.findAllWhere(userId:userId,status:ACTIVE)
          }
         
          def createMyBlog(Long userId, Blog blog, boolean update) {
        	if (checkForSpam(blog.description)) {
         		return FAILURE
         	}
    	  
           	if (checkForSpam(blog.title)) {
           		return FAILURE
           	}

        	  blog.status 		= ACTIVE
        	  blog.userId		= userId
        	  blog.updateTime  = new Date()

        	  if (!update) {
          		blog.createTime  = new Date()
          	}
        	  
           	if(!blog.hasErrors() && blog.save()) {
           		 return SUCCESS
           	} else {
                   return FAILURE
               }
          }
          
//        takes the blog object and extract userid to delete (status) the object.
          def deleteBlog(Long userId, Long id) {
       	   
          	Blog blogObj = getBlog(id)
          	
          	if (blogObj != null) {
          		
          		if (blogObj.userId != userId) {
          			return FAILURE
          		}
          		blogObj.status = DELETE
          		
          		if  (!blogObj.hasErrors() && blogObj.save()) {
          			return SUCCESS
          		} else {
          			return FAILURE
          		}
          	}
          	
          	return FAILURE
          }

          def getBlog(Long id) {
             	return Blog.findWhere(id:id, status:ACTIVE)
             }
          
          
      /*
       * Spam methods
       */
       
       def checkForSpam(String content) {
    	  if (content != null) {
	    	  if (content.contains("<script") || content.contains("</script>")) {
	    		  return true
	    	  }
    	  }
    	  
    	  return false
      }
      
      /*
       * User Views 
       */
       
       def incrementViews(Long userId) {
    	  Views userView = Views.findWhere(userId:userId)
    	  
    	  if (userView != null) {
    		  userView.num++
    		  if  (!userView.hasErrors() && userView.save()) {
    			  return SUCCESS
    		  } else {
    			  return FAILURE
    		  }
    	  } else {
    		  userView = new Views()
    		  userView.createTime = new Date()
    		  userView.userId = userId
    		  userView.num = 1
    		  if  (!userView.hasErrors() && userView.save()) {
    			  return SUCCESS
    		  } else {
    			  return FAILURE
    		  }
    	  }
    	  return FAILURE
      }
       
      def getViews(Long userId) {
    	  Views userView = Views.findWhere(userId:userId)
    	  if (userView != null) {
    		  return userView.num
    	  } else {
    		  return 0
    	  }
      }
      
      def createMyHash(Long userId, MyHash myHash) {
     	if (checkForSpam(myHash.hash)) {
     		return FAILURE
     	}

     	myHash.status 		= ACTIVE
     	myHash.userId		= userId
     	myHash.createTime  = new Date()
     	myHash.updateTime  = new Date()
       	
       	if(!myHash.hasErrors() && myHash.save()) {
               return SUCCESS
       	} else {
               return FAILURE
           }
      }
      
      def getHash(Long id) {
       	return MyHash.findWhere(id:id, status:ACTIVE)
       }

      def getHashString(Long userId) {
    	  MyHash myHashObj = MyHash.findByUserIdAndStatus(userId, ACTIVE, [max:1, sort:"createTime", order:"desc"])
    	  
    	  if (myHashObj != null) {
    		  return myHashObj.hash
    	  } else {
    		  return ""
    	  }
      }
      
      def getMyHashes(Long userId) {
    	  return MyHash.findAllByUserIdAndStatus(userId, ACTIVE, [sort:"createTime", order:"desc"]) 
      }
      
//    takes the hash object and extract userid to delete (status) the object.
      def deleteHash(Long userId, Long id) {
   	   
      	MyHash hashObj = getHash(id)
      	
      	if (hashObj != null) {
      		
      		if (hashObj.userId != userId) {
      			return FAILURE
      		}
      		hashObj.status = DELETE
      		
      		if  (!hashObj.hasErrors() && hashObj.save()) {
      			return SUCCESS
      		} else {
      			return FAILURE
      		}
      	}
      	
      	return FAILURE
      }

      /*
       * Facebook methods
       */
       
      def isFaceBookUser(Long userId) {
    	  def user = getUser(userId)
    	  
    	  if (user != null) {
    		  if (user.facebookUid!=null && !user.facebookUid.isEmpty() && !user.facebookUid.equals("")) {
    			  return ON
    		  }
    	  }
    	  
    	  return OFF
      }
      
      def isFaceBookUser(String userName) {
    	  def user = getUser(userName)
    	  
    	  if (user != null) {
    		  if (user.facebookUid!=null && !user.facebookUid.isEmpty() && !user.facebookUid.equals("")) {
    			  return ON
    		  }
    	  }
    	  
    	  return OFF
      }
      
      def getUserPermission(Long userId) {
    	  def user = getUser(userId)
    	  
    	  if (user != null) {
    		  return user.permission
    	  }
    	  
    	  return null
      }
      
      def allowFacebook(Long userId) {
    	  def user = getUser(userId)
      	
      		if (user != null) {
      		user.permission = ON
      		if  (!user.hasErrors() && user.save()) {
      			return SUCCESS
      		} else {
      			return FAILURE
      		}
      	}
      	
      	return FAILURE
      }
      
      /*
       * Tweet Urls code
       */
       
      def getTweetUrls(long userId, int offset, int max) {
   	   log.info("inside getTweetUrls")
   	   return TweetUrl.findAllByUserIdAndStatus(userId, ACTIVE, [max:max, offset:offset, sort:"createTime", order:"desc"] )
      }
      
      def getTweetUrlCount(long userId) {
   	   return TweetUrl.countByUserIdAndStatus(userId, ACTIVE)
      }
      
//    takes the tweet url object and extract userid, mytweetmark id to delete (status) the object.
      def deleteTweetUrl(Long userId, Long id) {
   	   
      	TweetUrl tweetUrlObj = getTweetUrl(id)
      	
      	if (tweetUrlObj != null) {
      		
      		if (tweetUrlObj.userId != userId) {
      			return FAILURE
      		}
      		tweetUrlObj.status = DELETE
      		
      		if  (!tweetUrlObj.hasErrors() && tweetUrlObj.save()) {
      			return SUCCESS
      		} else {
      			return FAILURE
      		}
      	}
      	
      	return FAILURE
      }

      def getTweetUrl(Long id) {
         	return TweetUrl.findWhere(id:id, status:ACTIVE)
         }
      
      def getLastTweetUrl(Long userId) {
    	  return TweetUrl.findByUserIdAndStatus(userId, ACTIVE, [max:1, sort:"statusId", order:"desc"])
    	  
      }
      
      /*
       * Tweet Friends code
       */
       
      def getTweetFriends(long userId, int offset, int max) {
   	   log.info("inside getTweetFriends")
   	   return TweetFriend.findAllByUserIdAndStatus(userId, ACTIVE, [max:max, offset:offset, sort:"createTime", order:"desc"] )
      }
      
      def getTweetFriendCount(long userId) {
   	   return TweetFriend.countByUserIdAndStatus(userId, ACTIVE)
      }
      
//    takes the tweet url object and extract userid, mytweetmark id to delete (status) the object.
      def deleteTweetFriend(Long userId, Long id) {
   	   
      	TweetFriend tweetFriendObj = getTweetFriend(id)
      	
      	if (tweetFriendObj != null) {
      		
      		if (tweetFriendObj.userId != userId) {
      			return FAILURE
      		}
      		tweetFriendObj.status = DELETE
      		
      		if  (!tweetFriendObj.hasErrors() && tweetFriendObj.save()) {
      			return SUCCESS
      		} else {
      			return FAILURE
      		}
      	}
      	
      	return FAILURE
      }

      def getTweetFriend(Long id) {
         	return TweetFriend.findWhere(id:id, status:ACTIVE)
         }
      
      def getLastTweetFriend(Long userId) {
    	  return TweetFriend.findByUserIdAndStatus(userId, ACTIVE, [max:1, sort:"statusId", order:"desc"])
    	  
      }
      
      /*
       * Auto Tweet on and off
       */
       
       def getAutoTweet(Long userId) {
       	def user = getUser(userId)
       	
       	if (user != null) {
   	    	Integer autoTweet = user.autoTweet
   	    	
   	    	if (autoTweet == null) {
   	    		return OFF
   	    	} else {
   	    		return autoTweet
   	    	}
       	}
       	return OFF
       }
       
       def setAutoTweet(Long userId, int tweet) {
       	def user = getUser(userId)
       	
       	if (user != null) {
       		user.autoTweet = tweet
       		
       		if(!user.hasErrors() && user.save()) {
       			return SUCCESS
       		} 
       	}
       	return FAILURE 
       }
       
       /**
        * The following methods are for dealing with recipes
        */
       
        def createMyRecipe(Long userId, Recipe recipeObj, boolean update, def location) {
        	System.out.println("createMyRecipe, userid = " + userId)
     	   //check here whether the data has any invalid characters.
        	if (checkForSpam(recipeObj.title) || checkForSpam(recipeObj.description)) {
        		return FAILURE
        	}
        	recipeObj.status 		= ACTIVE
        	recipeObj.userId 		= userId
        	
        	if (!update) {
        		recipeObj.createTime  = new Date()
        	}
        	recipeObj.updateTime  = new Date()
        	
        	if (location != null) {
        		recipeObj.geoCountry = location.countryName
        		recipeObj.geoCity = location.city
        		recipeObj.geoPostalCode = location.postalCode
        		recipeObj.geoLatitude = location.latitude
        		recipeObj.geoLongitude = location.longitude
        	}
        	
        	if(!recipeObj.hasErrors() && recipeObj.save()) {
        		   return SUCCESS
        	} else {
        		recipeObj.errors.each {
        	          println it
        	    }

                return FAILURE
            }
        }
       
        def getRecipes(Long userId) {
 	       	//return Post.findAllByUserIdAndStatus(userId, ACTIVE, [sort:"createTime",order:"desc"])
 	       	
 	       	log.info("inside getRecipes")
 	   		List returnRecipesWithComments = new ArrayList()
 	   		List recipes = Recipe.findAllByUserIdAndStatus(userId, ACTIVE, [sort:"createTime",order:"desc"])
 	
 	   		if (recipes != null && !recipes.isEmpty()) {
 	   			for (int i = 0; i < recipes.size(); i++) {
 	   			
 		   			def comments = getComments(recipes.get(i).id, RECIPE_TYPE)
 		   			List expandoComments = new ArrayList()
 		   			if (comments != null && !comments.isEmpty()) {
 		   				for (int j=0; j < comments.size(); j++) {
 		   					def expandoComment = new Expando()
 			   				expandoComment.comment = comments.get(j)
 			   				expandoComment.user = getUser(comments.get(j).userId)
 			   				expandoComments.add(expandoComment)
 		   				}
 		   				
 		   			}
 		   			def expandoRecipe = new Expando()
 		   			expandoRecipe.recipe = recipes.get(i)
 		   			expandoRecipe.comments = expandoComments
 		   			returnRecipesWithComments.add(expandoRecipe)
 	   			}
 	   		}
 	   	
 	       	return returnRecipesWithComments
        }
        
//      takes the post object and extract userid, mytweetmark id to delete (status) the object.
        def deleteRecipe(Long userId, Long id) {
     	   
        	Recipe recipeObj = getRecipe(id)
        	
        	if (recipeObj != null) {
        		
        		if (recipeObj.userId != userId) {
        			return FAILURE
        		}
        		recipeObj.status = DELETE
        		
        		if  (!recipeObj.hasErrors() && recipeObj.save()) {
        			return SUCCESS
        		} else {
        			return FAILURE
        		}
        	}
        	
        	return FAILURE
        }

        def getRecipe(Long id) {
        	return Recipe.findWhere(id:id, status:ACTIVE)
        }
        
        def getRecipesWithUserInfo(int offset, int max) {
     	   log.info("inside getRecipesWithUserInfo")
     	   
        		List returnRecipesWithUserInfo = new ArrayList()
        		//List posts = Post.list(max:50, order:"desc")
        		List recipes = Recipe.findAllByStatus(ACTIVE, [max:max, offset:offset, sort:"createTime", order:"desc"] )

        		if (recipes != null && !recipes.isEmpty()) {
        		for (int i = 0; i < recipes.size(); i++) {
        			
        			def user = getUser(recipes.get(i).userId)
        			
        			if (user != null) {
	        			def expandoRecipe = new Expando()
	        			expandoRecipe.recipe = recipes.get(i)
	        			expandoRecipe.user = user
	        			
	        			def comments = getComments(recipes.get(i).id, RECIPE_TYPE)
	 	   				List expandoComments = new ArrayList()
	 	   				if (comments != null && !comments.isEmpty()) {
	 	   					for (int j=0; j < comments.size(); j++) {
	 	   						def expandoComment = new Expando()
		 		   				expandoComment.comment = comments.get(j)
		 		   				expandoComment.user = getUser(comments.get(j).userId)
		 		   				expandoComments.add(expandoComment)
	 	   					}
	 	   				}
	        			
	        			expandoRecipe.comments = expandoComments
	        			returnRecipesWithUserInfo.add(expandoRecipe)
        			}
        		}
        	}
        	
        	return returnRecipesWithUserInfo
        }
        
        def getRecipeCount() {
     	   return Recipe.countByStatus(ACTIVE)
        }
        
        def getRecipesCloud() {
        	def recipes = Recipe.findAllByStatus(ACTIVE, [max:100, sort:"title",order:"asc"])
        	
        	/*if (recipes!=null && !recipes.isEmpty()) {
        		for (int i=0; i<recipes.size(); i++) {
        			recipes.get(i).addTag(recipes.get(i).title)
        			System.out.println("adding recipe: " + recipes.get(i).title)
        			System.out.println("tags = " + recipes.get(i).tags)
                	
        		}
        	}*/
        	return recipes;
        }
        
        def getRecipeEmailBody(Long id) {
        	def recipe = Recipe.get(id)
        	
        	if (recipe != null) {
        		def recipeBody = "<b>Title: " + recipe.title + "</b><br><br>"
        		recipeBody += "Description: " + recipe.description + "<br><br>"
        		recipeBody += "Ingredients: " + recipe.ingredients + "<br><br>"
        		recipeBody += "Preparation: " + recipe.preparation + "<br><br>"
        		recipeBody += "Serving Recommendation: " + recipe.servingRecommendation + "<br><br>"
        		recipeBody += "Source: " + recipe.source + "<br><br>"
        		recipeBody += "Story: " + recipe.story + "<br><br>"
        		
        		recipeBody += "<br>Thank you for using http://www.homecook.me<br><br>"
        		return recipeBody
        	}
        	return null
        }
        
        def getSubscriptionNotification(Users user) {
        	if (user != null) {
        		return "<b>A new user subscribed to you on http://www.homecook.me/"+user.userName+" At @mytweetmark, we are always finding optimized ways to help our businesses reach more customers.  Who is this new person?  Please login today and communicate with your customers and measure the impact.  Improve your product quality by customer feedback and generate more sales.  Take your business brand in your own hands!</b>"
        	} else {
        		return "<b>A new user subscribed to you on http://www.homecook.me so please visit and get to know them! At @mytweetmark, we are always finding optimized ways to help our businesses reach more customers.  Who is this new person?  Please login today and communicate with your customers and measure the impact.  Improve your product quality by customer feedback and generate more sales.  Take your business brand in your own hands!</b>"
        	}
        }
        
        /*
         * subscribe/unsubscribe methods
         */
        def subscribe(Long userId, Long subscribeId) {
        	def subscribed = Subscribed.findByUserIdAndSubscribedId(userId, subscribeId)
        	
        	if (subscribed != null) {
        		if (subscribed.status!=ACTIVE) {
        			subscribed.status = ACTIVE
        			if  (subscribed.hasErrors() || !subscribed.save()) {
    	    			return FAILURE
    	    		}
        		}
        	} else {
        		subscribed = new Subscribed()
	        	subscribed.userId = userId
	        	subscribed.subscribedId = subscribeId
	        	subscribed.createTime = new Date()
	        	subscribed.status = ACTIVE
	        	
	        	if  (subscribed.hasErrors() || !subscribed.save()) {
	    			return FAILURE
	    		}
        	}
        	
        	def subscriber = Subscriber.findByUserIdAndSubscriberId(subscribeId, userId)
        	
        	if (subscriber != null) {
        		if (subscriber.status!=ACTIVE) {
        			subscriber.status = ACTIVE
        			if  (subscriber.hasErrors() || !subscriber.save()) {
    	    			return FAILURE
    	    		}
        		}
        	} else {
	        	subscriber = new Subscriber()
	        	subscriber.userId = subscribeId
	        	subscriber.subscriberId = userId
	        	subscriber.createTime = new Date()
	        	subscriber.status = ACTIVE
	        	
	        	if  (subscriber.hasErrors() || !subscriber.save()) {
	    			return FAILURE
	    		}
        	}
        	def user = getUser(subscribeId)
        	
        	if (user != null && user.email!=null && !user.email.isEmpty()) {
	        	mailService.sendMail {
					   to user.email
					   subject "Wow, a new user subscribed to you on @homecookme!"
					   html getSubscriptionNotification(user)
					}
        	}
        	return SUCCESS
        }
        
        def unsubscribe(Long userId, Long subscribeId) {
        	def subscribed = Subscribed.findByUserIdAndSubscribedId(userId, subscribeId)
        	
        	if (subscribed != null) {
        		subscribed.status = DELETE
        		
        		if  (subscribed.hasErrors() || !subscribed.save()) {
        			return FAILURE
        		}
        	}
        	
        	def subscriber = Subscriber.findByUserIdAndSubscriberId(subscribeId, userId)
        	
        	if (subscriber != null) {
        		subscriber.status = DELETE
        		
        		if  (subscriber.hasErrors() || !subscriber.save()) {
        			return FAILURE
        		}
        	}
        }
        
        def isUserSubscribed(Long userId, Long subscribeId) {
        	def subscribed = Subscribed.findByUserIdAndSubscribedId(userId, subscribeId)
        	
        	if (subscribed == null || subscribed.status != ACTIVE) {
        		return false
        	} else {
        		return true
        	}
        }
        
        def getSubscribers(Long userId) {
        	def subscribers = Subscriber.findAllByUserIdAndStatus(userId, ACTIVE)
        	
        	if (subscribers != null && !subscribers.isEmpty()) {
        		def userSubscribers = new ArrayList()
        		
        		for (int i=0; i<subscribers.size(); i++) {
        			def user = Users.get(subscribers.get(i).subscriberId)
        			
        			if (user != null) {
        				userSubscribers.add(user)
        			}
        		}
        		return userSubscribers
        	}
        	return null
        }
        
        def subscribeAllUsers(Users user) {
        	try {
        		def users = Users.findAllByStatus(ACTIVE)
        		
        		if (users != null) {
        			def i=0
        			def userSize = users.size()
        			if (userSize > 50)
        				userSize = 50
	        		while (i<userSize) {
	        			i++
	        			java.util.Random randomGenerator = new java.util.Random()
			        	int randomInt = randomGenerator.nextInt(users.size())
			        	def userB = users.get(randomInt)
			        	
	        			subscribe(user.id, userB)
	        			System.out.println("user A is subscribing to user B")
	        			System.out.println("user A:" + user.userName)
	        			System.out.println("user B:" + userB.userName)
	        		}
        		}
        	} catch (Exception e) {
        		e.printStackTrace()
        		System.out.println("Exception in subscribeAllUsers: " + user.userName)
        	}
        }
        /*
         * joinMarket/unjoinMarket methods
         */
        def joinMarket(Long userId, Integer marketId) {
        	def marketUser = MarketUser.findByUserIdAndMarketId(userId, marketId)
        	
        	if (marketUser != null) {
        		if (marketUser.status!=ACTIVE) {
        			marketUser.status = ACTIVE
        			if  (marketUser.hasErrors() || !marketUser.save()) {
    	    			return FAILURE
    	    		}
        		}
        	} else {
        		marketUser = new MarketUser()
        		marketUser.userId = userId
        		marketUser.marketId = marketId
        		marketUser.createTime = new Date()
        		marketUser.status = ACTIVE
	        	
	        	if  (marketUser.hasErrors() || !marketUser.save()) {
	    			return FAILURE
	    		}
        	}
        	
        	return SUCCESS
        }
        
        def unjoinMarket(Long userId, Integer marketId) {
        	def marketUser = MarketUser.findByUserIdAndMarketId(userId, marketId)
        	
        	if (marketUser != null) {
        		marketUser.status = DELETE
        		
        		if  (marketUser.hasErrors() || !marketUser.save()) {
        			return FAILURE
        		}
        	}
        	
        }
        
        def isUserInMarket(Long userId, Integer marketId) {
        	def marketUser = MarketUser.findByUserIdAndMarketId(userId, marketId)
        	
        	if (marketUser == null || marketUser.status != ACTIVE) {
        		return false
        	} else {
        		return true
        	}
        }
        
        def getMarketUsers(Integer marketId) {
        	def marketUsers = MarketUser.findAllByMarketIdAndStatus(marketId, ACTIVE)
        	
        	if (marketUsers != null && !marketUsers.isEmpty()) {
        		def users = new ArrayList()
        		
        		for (int i=0; i<marketUsers.size(); i++) {
        			def user = Users.get(marketUsers.get(i).userId)
        			
        			if (user != null) {
        				users.add(user)
        			}
        		}
        		return users
        	}
        	return null
        }
        
        def getUserMarkets(Long userId) {
        	def userMarkets = MarketUser.findAllByUserIdAndStatus(userId, ACTIVE)
        	
        	if (userMarkets != null && !userMarkets.isEmpty()) {
        		def markets = new ArrayList()
        		
        		for (int i=0; i<userMarkets.size(); i++) {
        			def market = FarmerMarket.get(userMarkets.get(i).marketId)
        			
        			if (market != null) {
        				markets.add(market)
        			}
        		}
        		return markets
        	}
        	return new ArrayList()
        }
        
        def getMarketsWithUserInfo(int offset, int max) {
      	   log.info("inside getMarketsWithUserInfo")
      	   
         	List returnMarketsWithUserInfo = new ArrayList()
         	//List posts = Post.list(max:50, order:"desc")
         	List markets = FarmerMarket.findAllByStatus(ACTIVE, [max:max, offset:offset, sort:"title", order:"asc"] )

         	if (markets != null && !markets.isEmpty()) {
	         	for (int i = 0; i < markets.size(); i++) {
	         			
         			def users = getMarketUsers(markets.get(i).id)
         			
         			def expandoMarket = new Expando()
         			expandoMarket.market = markets.get(i)
         			expandoMarket.users = users
         			
         			returnMarketsWithUserInfo.add(expandoMarket)
  	   			}
         	}
         	
      	   return returnMarketsWithUserInfo
         }
         
        def getMarkets() {
       	   log.info("inside getMarkets")
       	   
          	//List posts = Post.list(max:50, order:"desc")
          	List markets = FarmerMarket.findAllByStatus(ACTIVE, [sort:"title", order:"asc"] )

          	
       	   return markets
          }
        
         def getMarketCount() {
      	   return FarmerMarket.countByStatus(ACTIVE)
         }
         
         def createMyFarmerMarket(Long userId, FarmerMarket farmerMarket, boolean update) {
         	if (checkForSpam(farmerMarket.description)) {
          		return FAILURE
          	}
     	  
            	if (checkForSpam(farmerMarket.title)) {
            		return FAILURE
            	}

            	farmerMarket.status 		= ACTIVE
            	
            	if (!update) {
         		 farmerMarket.createTime  = new Date()
         		}
         	  
            	if(!farmerMarket.hasErrors() && farmerMarket.save()) {
            		 return SUCCESS
            	} else {
                    return FAILURE
                }
           }
         
         
         def createMyProduct(Long userId, Product productObj) {
            	if (checkForSpam(productObj.name)) {
            		return FAILURE
            	}

          	productObj.status 		= ACTIVE
          	productObj.userId		= userId
          	productObj.createTime  = new Date()
          	productObj.updateTime  = new Date()
          	
          	if(!productObj.hasErrors() && productObj.save()) {
                  return SUCCESS
          	} else {
                  return FAILURE
              }
         }
         
         //takes the mytweetmark object and extract userid, mytweetmark id to delete (status) the object.
         def deleteMyProduct(Long userId, Integer id) {
         	def productObj = getMyProduct(id)
         	
         	if (productObj != null) {
         		productObj.status = DELETE
         		if  (!productObj.hasErrors() && productObj.save()) {
         			return SUCCESS
         		} else {
         			return FAILURE
         		}
         	}
         	
         	return FAILURE
         }
         
         def getMyProduct(Long id) {
         	return Product.findWhere(id:id, status:ACTIVE)
         }
         
         def getMyProducts(Long userId) {
      	   	log.info("inside getMyProducts")
      	   	return Product.findAllByUserIdAndStatus(userId, ACTIVE, [sort:"createTime", order:"desc"])
         }
         
         def getProductsWithUserInfo(int offset, int max) {
        	   log.info("inside getProductsWithUserInfo")
        	   
           	List returnProductsWithUserInfo = new ArrayList()
           	//List posts = Post.list(max:50, order:"desc")
           	List products = Product.findAllByStatus(ACTIVE, [max:max, offset:offset, sort:"name", order:"asc"] )

           	if (products != null && !products.isEmpty()) {
  	         	for (int i = 0; i < products.size(); i++) {
  	         			
           			def user = getUser(products.get(i).userId)
           			if (user != null) {
           				def expandoProduct = new Expando()
           				expandoProduct.product = products.get(i)
           				expandoProduct.user = user
           			
           				returnProductsWithUserInfo.add(expandoProduct)
           			}
    	   		}
           	}
           	
        	   return returnProductsWithUserInfo
           }
         
         def getProducts() {
      	   log.info("inside getProducts")
      	   
         	List products = Product.findAllByStatus(ACTIVE, [sort:"name", order:"asc"] )

         	return products
         }
           
           def getProductCount() {
        	   return Product.countByStatus(ACTIVE)
           }
           
           def getMarketsMap(def marketsList) {
        	   def returnStr = "http://maps.google.com/maps/api/staticmap?center=oakland,ca&zoom=7&size=700x400"
        	   
        	   if (marketsList != null) {
	        	   for (int i=0; i<marketsList.size(); i++) {
	        		   def city = marketsList.get(i).market.city
	        		   def newCity = ""
	        		   if (city.contains(" "))
	        		   {
	        			 StringTokenizer tokenizer = new StringTokenizer(city)
	        			 while (tokenizer.hasMoreTokens()) {
	        				 newCity += tokenizer.nextToken() 
	        				 
	        				 if (tokenizer.hasMoreTokens()) {
	        					 newCity += "+"
	        				 }
	        			 }
	        			 city = newCity
	        		   }
	        		   returnStr += "&markers=color:blue|label:M|"+city+","+marketsList.get(i).market.state
	        	   }
	        	   returnStr+="&sensor=false"
	        	   System.out.println(returnStr)
	        	   return returnStr
        	   }
        	   return ""
           }
           
           def getUserMarketsMap(long userId) {
        	   def markets = getUserMarkets(userId)
        	   
        	   def returnStr = "http://maps.google.com/maps/api/staticmap?center=oakland,ca&zoom=7&size=350x350"
            	   
        	   if (markets != null) {
	        	   for (int i=0; i<markets.size(); i++) {
	        		   def city = markets.get(i).city
	        		   def newCity = ""
	        		   if (city.contains(" "))
	        		   {
	        			 StringTokenizer tokenizer = new StringTokenizer(city)
	        			 while (tokenizer.hasMoreTokens()) {
	        				 newCity += tokenizer.nextToken() 
	        				 
	        				 if (tokenizer.hasMoreTokens()) {
	        					 newCity += "+"
	        				 }
	        			 }
	        			 city = newCity
	        		   }
	        		   returnStr += "&markers=color:blue|label:M|"+city+","+markets.get(i).state
	        	   }
	        	   returnStr+="&sensor=false"
	        	   System.out.println(returnStr)
	        	   return returnStr
        	   }
        	   return ""
           }
           
           //REST methods for mobile
                      
           def getMobileUser(String name) {
         	   
        	   log.info("inside getMobileUser")
        	   
        	   try {
	         	   def user = getUser(name)
	         	   /*def expandoUser = new Expando()
	         	   expandoUser.user = user
	         	   expandoUser.markets = getUserMarkets(user.id)
	         	   expandoUser.products = getMyProducts(user.id)
	         	   return expandoUser*/
	         	   return user;
        	   } catch (Exception e) {
        		   System.out.println("Exception in getMobileUser" + e.getMessage())
        		   e.printStackTrace()
        		   return null
        	   }
            }
           
           def getMobileMarket(String marketId) {
         	   
        	   log.info("inside getMobileMarket")
        	   
        	   try {
	         	   def market = FarmerMarket.get(Integer.valueOf(marketId))
	         	   /*def expandoMarket = new Expando()
	         	   expandoMarket.market = market
	         	   expandoMarket.users = getMarketUsers(market.id)
	         	   return expandoMarket*/
	         	   return market
        	   } catch (Exception e) {
        		   System.out.println("Exception in getMobileMarket" + e.getMessage())
        		   e.printStackTrace()
        		   return null
        	   }
            }
           
           def getMobileProduct(String productId) {
         	   
        	   log.info("inside getMobileProduct")
        	   
        	   try {
	         	   def product = Product.get(Long.valueOf(productId))
	         	   
	         	   return product
        	   } catch (Exception e) {
        		   System.out.println("Exception in getMobileProduct" + e.getMessage())
        		   e.printStackTrace()
        		   return null
        	   }
            }
}
