class Subscribed implements Serializable {
	Long userId
	Integer subscribedId
	Integer status
	Date createTime
		
	static constraints = {
		userId(nullable:false)
		subscribedId(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
	}
	
	static mapping = {
		version false
		table 'subscribed'
		id composite: ['userId', 'subscribedId']
	}

}
