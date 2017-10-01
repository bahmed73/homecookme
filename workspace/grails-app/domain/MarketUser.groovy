class MarketUser implements Serializable {
	Long userId
	Integer marketId
	Integer status
	Date createTime
		
	static constraints = {
		userId(nullable:false)
		marketId(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
	}
	
	static mapping = {
		version false
		table 'market_user'
		id composite: ['userId', 'marketId']
	}

}
