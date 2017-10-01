class MyCategory implements Serializable {
	Long userId
	Integer categoryId
	Integer status
	Date createTime
	Date updateTime
	Integer numMyTweets
	
	static constraints = {
		userId(nullable:false)
		categoryId(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
		updateTime(nullable:false)
		numMyTweets(nullable:true)
		
	}
	
	static mapping = {
		version false
		table 'mycategory'
		id composite: ['userId', 'categoryId']
	}

}
