class FarmerMarket {
	static searchable = true
	
	Integer id
	String title
	Integer status
	Date createTime
	String marketPhoto
	String address
	String city
	String state
	String description
	String timings
	String marketAdmin
	String googleMap
	String facebookLike
	
	static constraints = {
		id(nullable:true)
		title(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
		marketPhoto(nullable:true)
		address(nullable:false)
		city(nullable:false)
		state(nullable:false)
		description(nullable:false)
		timings(nullable:false)
		marketAdmin(nullable:true)
		googleMap(nullable:true)
		facebookLike(nullable:true)
	}
	
	static mapping = {
		version false
		table 'farmer_market'
		cache true
		id generator:'sequence', params:[sequence:'farmer_market_seq']
	}
}
