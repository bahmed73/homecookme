import twitter4j.*;
import twitter4j.http.*;

class TweetUrlController {

	static final String MY_TOGGLE = "TweetUrl";
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def myTweetMarkService
	def twitterService
	String myHash
	String userName
	def tweetUrlInstanceList
	def tweetUrlInstanceTotal
	Integer displaySend
	List myHashes
	Integer isFaceBookUser
	def facebookConnectService
	Integer autoTweet
	
    def index = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
    	redirect(action: "list", params: params)
    }

    def list = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
        params.max = Math.min( params.max ? params.max.toInteger() : 5,  100)
        if (params.offset == null) {
        	params.offset = 0
        }
        Integer paramInt = Integer.valueOf(params.offset) 
        myHash = myTweetMarkService.getHashString(session.userId)    
        tweetUrlInstanceList = myTweetMarkService.getTweetUrls(session.userId, paramInt, params.max)
        tweetUrlInstanceTotal = myTweetMarkService.getTweetUrlCount(session.userId)
        displaySend = myTweetMarkService.getTweet(session.userId)
        myHashes = myTweetMarkService.getMyHashes(session.userId)
        userName = session.userName
        isFaceBookUser = myTweetMarkService.isFaceBookUser(session.userId)
        autoTweet = myTweetMarkService.getAutoTweet(session.userId)
    }

    def create = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetUrlInstance = new TweetUrl()
        tweetUrlInstance.properties = params
        return [tweetUrlInstance: tweetUrlInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetUrlInstance = new TweetUrl(params)
        if (tweetUrlInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tweetUrl.label', default: 'TweetUrl'), tweetUrlInstance.id])}"
            redirect(action: "show", id: tweetUrlInstance.id)
        }
        else {
            render(view: "create", model: [tweetUrlInstance: tweetUrlInstance])
        }
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetUrlInstance = TweetUrl.get(params.id)
        if (!tweetUrlInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tweetUrl.label', default: 'TweetUrl'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tweetUrlInstance: tweetUrlInstance]
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetUrlInstance = TweetUrl.get(params.id)
        if (!tweetUrlInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tweetUrl.label', default: 'TweetUrl'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tweetUrlInstance: tweetUrlInstance]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetUrlInstance = TweetUrl.get(params.id)
        if (tweetUrlInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tweetUrlInstance.version > version) {
                    
                    tweetUrlInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tweetUrl.label', default: 'TweetUrl')] as Object[], "Another user has updated this TweetUrl while you were editing")
                    render(view: "edit", model: [tweetUrlInstance: tweetUrlInstance])
                    return
                }
            }
            tweetUrlInstance.properties = params
            if (!tweetUrlInstance.hasErrors() && tweetUrlInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tweetUrl.label', default: 'TweetUrl'), tweetUrlInstance.id])}"
                redirect(action: "show", id: tweetUrlInstance.id)
            }
            else {
                render(view: "edit", model: [tweetUrlInstance: tweetUrlInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tweetUrl.label', default: 'TweetUrl'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetUrlInstance = TweetUrl.get(params.id)
        if (tweetUrlInstance) {
            try {
                tweetUrlInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tweetUrl.label', default: 'TweetUrl'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tweetUrl.label', default: 'TweetUrl'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tweetUrl.label', default: 'TweetUrl'), params.id])}"
            redirect(action: "list")
        }
    }
    
    def deleteMyTweetUrl = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot delete tweet urls at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			if (params.id != null) {
				def returnVal = myTweetMarkService.deleteTweetUrl(session.userId, Long.valueOf(params.id))
				
				if (returnVal == MyTweetMarkService.SUCCESS) {
					flash.message = "Successfully removed tweet url.";
				} else {
					flash.message = "Cannot remove tweet urls at this time.  Please try again later.";
				}
			}
			
			redirect(action:'list',controller:'tweetUrl')
		}
	}
    
    def calculateUrls = {
    	if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
    	
    	try {
			if (session.twitter != null) {
				twitterService.processTweets(session.twitter, session.userId, session.userName)
			} 
		} catch (Exception ee) {
			System.out.println("Error inserting followers");
			ee.printStackTrace();
			flash.message = "Error extracting tweets.";
		}
		
		redirect(action:'list',controller:'tweetUrl')
    }
}
