class MyTweetMark {
	static searchable = true
	Long id
	Integer categoryId
	Long userId
	String url
	Integer status
	Date createTime
	Date updateTime
	String description
	
	static constraints = {
		id(nullable:true)
		categoryId(nullable:false)
		userId(nullable:false)
		url(nullable:false,url:true, maxSize:2056)
		status(nullable:false)
		createTime(nullable:false)
		updateTime(nullable:false)
		description(nullable:true, maxSize:512)
	}
	
	static mapping = {
		version false
		table 'mytweetmark'
		id generator:'sequence', params:[sequence:'mytweetmark_seq']
	}
}
