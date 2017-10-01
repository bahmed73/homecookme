class Comment {
	static searchable = true
	Long id
	Long userId
	Integer status
	Date createTime
	Date updateTime
	String contentType
	Long contentId
	String description
	
	static constraints = {
		id(nullable:true)
		userId(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
		contentType(nullable:false)
		updateTime(nullable:false)
		contentId(nullable:false)
		description(nullable:false)
	}
	
	static mapping = {
		version false
		table 'comment'
		id generator:'sequence', params:[sequence:'comment_seq']
	}
}
