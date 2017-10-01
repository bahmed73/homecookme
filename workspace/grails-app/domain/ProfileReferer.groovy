class ProfileReferer {

	Integer id
	String name
	String refererUrl
	Date createTime
	
	static constraints = {
		id(nullable:true)
		name(nullable:false)
		refererUrl(nullable:false)
		createTime(nullable:false)
	}
	
	static mapping = {
		version false
		table 'profile_referer'
		id generator:'sequence', params:[sequence:'profile_referer_seq']
		refererUrl column:'referer_url'
		name column:'name'
		createTime column:'create_time'

	}
}
