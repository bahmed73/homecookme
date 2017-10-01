class Views {

	Long id
	Date createTime
	Long num
	Long userId
	
	static constraints = {
		id(nullable:true)
		num(nullable:false)
		createTime(nullable:false)
		userId(nullable:false)
	}
	
	static mapping = {
		version false
		table 'views'
		id generator:'sequence', params:[sequence:'views_seq']
	}
}
