class InternalReferer {

	Integer id
	String url
	Date createTime
	
	static constraints = {
		id(nullable:true)
		url(nullable:false)
		createTime(nullable:false)
	}
	
	static mapping = {
		version false
		table 'internal_referer'
		id generator:'sequence', params:[sequence:'internal_referer_seq']
	}
}
