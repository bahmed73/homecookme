class TweetUrl {
	static searchable = true
	Long id
	Long userId
	Long statusId
	String url
	Integer status
	Date createTime
	String tweet
	
	static constraints = {
		id(nullable:true)
		userId(nullable:false)
		statusId(nullable:false)
		url(nullable:false, maxSize:140)
		status(nullable:false)
		createTime(nullable:false)
		tweet(nullable:false, maxSize:140)
	}
	
	static mapping = {
		version false
		table 'tweet_url'
		id generator:'sequence', params:[sequence:'tweet_url_seq']
	}
}
