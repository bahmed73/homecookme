class RetentionSummary {

	Integer id
	Date createTime
	Integer totalRegistrations
	Integer totalLogins
	Integer totalLogins5orless
	Integer totalLogins6to10
	Integer totalLogins11to15
	Integer totalLogins16to20
	Integer totalLogins21to25
	Integer totalLogins26to30
	
	static constraints = {
		id(nullable:true)
		createTime(nullable:false)
		totalRegistrations(nullable:true)
		totalLogins(nullable:true)
		totalLogins5orless(nullable:true)
		totalLogins6to10(nullable:true)
		totalLogins11to15(nullable:true)
		totalLogins16to20(nullable:true)
		totalLogins21to25(nullable:true)
		totalLogins26to30(nullable:true)
	}
	
	static mapping = {
		version false
		table 'retention_summary'
		id generator:'sequence', params:[sequence:'retention_summary_seq']
		columns {
			totalLogins5orless column: 'total_logins_5_or_less'
			totalLogins6to10 column: 'total_logins_6_to_10'
			totalLogins11to15 column: 'total_logins_11_to_15'
			totalLogins16to20 column: 'total_logins_16_to_20'
			totalLogins21to25 column: 'total_logins_21_to_25'
			totalLogins26to30 column: 'total_logins_26_to_30'
		}
	}
}
