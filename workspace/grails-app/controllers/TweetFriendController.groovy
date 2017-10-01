import twitter4j.*;
import twitter4j.http.*;

class TweetFriendController {

	static final String MY_TOGGLE = "TweetFriend";
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def myTweetMarkService
	def twitterService
	String myHash
	String userName
	def tweetFriendInstanceList
	def tweetFriendInstanceTotal
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
        tweetFriendInstanceList = myTweetMarkService.getTweetFriends(session.userId, paramInt, params.max)
        tweetFriendInstanceTotal = myTweetMarkService.getTweetFriendCount(session.userId)
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
        def tweetFriendInstance = new TweetFriend()
        tweetFriendInstance.properties = params
        return [tweetFriendInstance: tweetFriendInstance]
    }

    def save = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetFriendInstance = new TweetFriend(params)
        if (tweetFriendInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tweetFriend.label', default: 'TweetFriend'), tweetFriendInstance.id])}"
            redirect(action: "show", id: tweetFriendInstance.id)
        }
        else {
            render(view: "create", model: [tweetFriendInstance: tweetFriendInstance])
        }
    }

    def show = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetFriendInstance = TweetFriend.get(params.id)
        if (!tweetFriendInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tweetFriend.label', default: 'TweetFriend'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tweetFriendInstance: tweetFriendInstance]
        }
    }

    def edit = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetFriendInstance = TweetFriend.get(params.id)
        if (!tweetFriendInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tweetFriend.label', default: 'TweetFriend'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tweetFriendInstance: tweetFriendInstance]
        }
    }

    def update = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetFriendInstance = TweetFriend.get(params.id)
        if (tweetFriendInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tweetFriendInstance.version > version) {
                    
                    tweetFriendInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tweetFriend.label', default: 'TweetFriend')] as Object[], "Another user has updated this TweetFriend while you were editing")
                    render(view: "edit", model: [tweetFriendInstance: tweetFriendInstance])
                    return
                }
            }
            tweetFriendInstance.properties = params
            if (!tweetFriendInstance.hasErrors() && tweetFriendInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tweetFriend.label', default: 'TweetFriend'), tweetFriendInstance.id])}"
                redirect(action: "show", id: tweetFriendInstance.id)
            }
            else {
                render(view: "edit", model: [tweetFriendInstance: tweetFriendInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tweetFriend.label', default: 'TweetFriend'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
    	if (session.userName == null || !myTweetMarkService.isAdmin(session.userName)) {
	    	flash.message = "Please login as administrator to do this operation.";
	    	redirect(uri:"/")
	    	return
	    }
        def tweetFriendInstance = TweetFriend.get(params.id)
        if (tweetFriendInstance) {
            try {
                tweetFriendInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tweetFriend.label', default: 'TweetFriend'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tweetFriend.label', default: 'TweetFriend'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tweetFriend.label', default: 'TweetFriend'), params.id])}"
            redirect(action: "list")
        }
    }
    
    def deleteMyTweetFriend = {
		if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
		
		if (myTweetMarkService.isFeatureToggleReadOnly(MY_TOGGLE)) {
			flash.message = "Cannot delete tweet friends at this time.  Please try again later.";
			redirect(action:'myTweetMarks',controller:'myTweetMark')
			return
		} else {
		
			if (params.id != null) {
				def returnVal = myTweetMarkService.deleteTweetFriend(session.userId, Long.valueOf(params.id))
				
				if (returnVal == MyTweetMarkService.SUCCESS) {
					flash.message = "Successfully removed tweet friend.";
				} else {
					flash.message = "Cannot remove tweet friends at this time.  Please try again later.";
				}
			}
			
			redirect(action:'list',controller:'tweetFriend')
		}
	}
    
    def calculateFriends = {
    	if (session.userName == null) {
    		flash.message = "Please login to do this operation.";
    		redirect(uri:"/")
    		return
    	}
    	
    	try {
			if (session.twitter != null) {
				twitterService.processFriends(session.twitter, session.userId, session.userName)
			} 
		} catch (Exception ee) {
			System.out.println("Error inserting followers");
			ee.printStackTrace();
			flash.message = "Error extracting tweets.";
		}
		
		redirect(action:'list',controller:'tweetFriend')
    }
}
