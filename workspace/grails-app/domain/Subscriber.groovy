class Subscriber implements Serializable {
	Long userId
	Integer subscriberId
	Integer status
	Date createTime
		
	static constraints = {
		userId(nullable:false)
		subscriberId(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
	}
	
	static mapping = {
		version false
		table 'subscriber'
		id composite: ['userId', 'subscriberId']
	}

}
