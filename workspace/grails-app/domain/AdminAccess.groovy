class AdminAccess {

	Integer id
	Date createTime
	Integer status
	String name
	Date updateTime
	
	static constraints = {
		id(nullable:true)
		createTime(nullable:false)
		status(nullable:false)
		name(nullable:false)
		updateTime(nullable:false)
	}
	
	static mapping = {
		version false
		table 'admin_access'
		id generator:'sequence', params:[sequence:'admin_access_seq']
		cache true
	}
}
