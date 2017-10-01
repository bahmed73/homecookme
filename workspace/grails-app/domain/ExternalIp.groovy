class ExternalIp {

	Integer id
	String ip
	Date createTime
	
	static constraints = {
		id(nullable:true)
		ip(nullable:false)
		createTime(nullable:false)
	}
	
	static mapping = {
		version false
		table 'external_ip'
		id generator:'sequence', params:[sequence:'external_ip_seq']
	}
}
