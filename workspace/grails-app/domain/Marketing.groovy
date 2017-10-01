class Marketing {
	Integer id
	String name
	Integer status
	Integer marketingType
	Date createTime
	
	static constraints = {
		id(nullable:false)
		name(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
		marketingType(nullable:false)
	}
	
	static mapping = {
		version false
		table 'marketing'
		cache true
	}
}
