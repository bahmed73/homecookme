class TweetFriend {
	static searchable = true
	Long id
	Long userId
	Long statusId
	String friendName
	Integer status
	Date createTime
	String tweet
	String profilePhoto
	
	static constraints = {
		id(nullable:true)
		userId(nullable:false)
		statusId(nullable:false)
		friendName(nullable:false, maxSize:100)
		status(nullable:false)
		createTime(nullable:false)
		tweet(nullable:false, maxSize:140)
		profilePhoto(nullable:true, maxSize:2048)
	}
	
	static mapping = {
		version false
		table 'tweet_friend'
		id generator:'sequence', params:[sequence:'tweet_friend_seq']
	}
}
