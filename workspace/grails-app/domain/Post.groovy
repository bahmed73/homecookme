class Post {
	static searchable = true
	Long id
	Long userId
	Integer status
	Date createTime
	String description
	
	static constraints = {
		id(nullable:true)
		userId(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
		description(nullable:false, maxSize:500)
	}
	
	static mapping = {
		version false
		table 'post'
		id generator:'sequence', params:[sequence:'post_seq']
	}
}
