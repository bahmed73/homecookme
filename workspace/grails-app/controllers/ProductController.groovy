import com.google.code.facebookapi.*;
import grails.converters.*

class ProductController {

static final String MY_TOGGLE = "Product";
	
	def myTweetMarkService
	def twitterService
	Integer displaySend
	Integer isFaceBookUser
	def facebookConnectService
	def user
	def productInstance
	def productInstanceList
	def productInstanceTotal
	def users
	def productInstanceListAll
	
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
		params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        if (params.offset == null) {
        	params.offset = 0
        }
        Integer paramInt = Integer.valueOf(params.offset) 
        productInstanceListAll = myTweetMarkService.getProducts()
        productInstanceList = myTweetMarkService.getProductsWithUserInfo(paramInt, params.max)
        productInstanceTotal = myTweetMarkService.getProductCount()
        
        if (session.userId != null) {
        	displaySend = myTweetMarkService.getTweet(session.userId)
        	isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
        	user = myTweetMarkService.getUser(session.userId)
        }
        session.profile = false
    }

    def create = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def productInstance = new Product()
        productInstance.properties = params
        return [productInstance: productInstance]
    }

    def save = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def productInstance = new Product(params)
        if (productInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'product.label', default: 'Product'), productInstance.id])}"
            redirect(action: "show", id: productInstance.id)
        }
        else {
            render(view: "create", model: [productInstance: productInstance])
        }
    }

    def show = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def productInstance = Product.get(params.id)
        if (!productInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
        else {
            [productInstance: productInstance]
        }
    }

    def edit = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def productInstance = Product.get(params.id)
        if (!productInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [productInstance: productInstance]
        }
    }

    def update = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def productInstance = Product.get(params.id)
        if (productInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (productInstance.version > version) {
                    
                    productInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'product.label', default: 'Product')] as Object[], "Another user has updated this Product while you were editing")
                    render(view: "edit", model: [productInstance: productInstance])
                    return
                }
            }
            productInstance.properties = params
            if (!productInstance.hasErrors() && productInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'product.label', default: 'Product'), productInstance.id])}"
                redirect(action: "show", id: productInstance.id)
            }
            else {
                render(view: "edit", model: [productInstance: productInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
		if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
    		flash.message = "Please login as administrator to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        def productInstance = Product.get(params.id)
        if (productInstance) {
            try {
                productInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def saveMyProduct = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
		
		
		def productObj = new Product(params)
		if (myTweetMarkService.createMyProduct(session.userId, productObj) == MyTweetMarkService.SUCCESS) {
			
			if (myTweetMarkService.getTweet(session.userId) == MyTweetMarkService.ON) {
				if (myTweetMarkService.isFaceBookUser(session.userId)) {
					try {
						if (facebookConnectService.isLoggedIn(request)) {
							FacebookJsonRestClient client = facebookConnectService.getFacebookClient()
							if (client.users_hasAppPermission(Permission.STATUS_UPDATE)) {
								client.users_setStatus("Our #food: #" + productObj.name + " #quantity: " + productObj.quantity + " #price: " + productObj.price + " " + "find more on http://www.homecook.me/" + session.userName + " #localfood #realfood #farmersmarket", false);
							}
						} 
					} catch (Exception e) {
						System.out.println("Exception caught obtaining facebook")
						e.printStackTrace()
					}
				} else if (session.twitter != null) {
					def status = "Our #food: #" + productObj.name + " #quantity: " + productObj.quantity + " #price: " + productObj.price + " " + "find more on http://www.homecook.me/" + session.userName + " #localfood #realfood #farmersmarket"
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
    		flash.message = productObj.name + " created successfully";
		} else {
			flash.message = "product creation failure";
		}
		
		redirect(action:'myTweetMarks',controller:'myTweetMark')
	
	}
    
	def deleteProduct = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    	}
		
		if (params.id != null) {
			myTweetMarkService.deleteMyProduct(session.userId, Integer.valueOf(params.id))
		}
		
		redirect(action:'myTweetMarks',controller:'myTweetMark')
		
	}
	
	def ajaxGetProducts = {
		def results = Product.findAllByStatus(MyTweetMarkService.ACTIVE, [sort:"name", order:"asc"] )
		def newResults = new ArrayList()
		
		if (results!=null && results.size()>0) {
			for (int i=0; i<results.size(); i++) {
				if (results.get(i).name!=null) {
					if (results.get(i).name.toLowerCase().startsWith(params.alpha.toLowerCase())) {
						newResults.add(results.get(i))
					}
				}
			}
		} 
		render newResults as JSON
	}
}
