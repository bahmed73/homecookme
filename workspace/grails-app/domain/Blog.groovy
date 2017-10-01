class Blog {
	static searchable = true
	Long id
	Long userId
	Integer status
	Date createTime
	String description
	Date updateTime
	String title
	
	static constraints = {
		id(nullable:true)
		userId(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
		description(nullable:false, maxSize:5000)
		updateTime(nullable:false)
		title(nullable:false, maxSize:100)
	}
	
	static mapping = {
		version false
		table 'blog'
		id generator:'sequence', params:[sequence:'blog_seq']
	}
}
