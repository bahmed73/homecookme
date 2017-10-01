class Country {
	Integer id
	String code
	String name
	Integer status
	Date createTime
	Integer orderBy
	
	static constraints = {
		id(nullable:false)
		name(nullable:false)
		code(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
		orderBy(nullable:false)
	}
	
	static mapping = {
		version false
		table 'country'
		cache true
	}
}
