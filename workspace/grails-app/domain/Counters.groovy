class Counters {
	Long userId
	Integer subscribed
	Integer subscriber
	Date createTime
	Integer status 
	
	static constraints = {
		userId(nullable:false)
		subscribed(nullable:true)
		subscriber(nullable:true)
		createTime(nullable:false)
		status(nullable:false)
	}
	
	static mapping = {
		version false
		table 'counters'
	}
}
