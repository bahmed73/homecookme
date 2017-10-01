class FeatureToggle {

	Integer id
	Date createTime
	Integer status
	String name
	
	static constraints = {
		id(nullable:true)
		createTime(nullable:false)
		status(nullable:false)
		name(nullable:false)
	}
	
	static mapping = {
		version false
		table 'feature_toggle'
		id generator:'sequence', params:[sequence:'feature_toggle_seq']
		cache true
	}
}
