class ViralSummary {
	Integer id
	Date createTime
	Integer totalRegistrations
	Integer totalAddressBookScrapedSize
	Integer totalSendSize
	Integer totalOpened
	Integer totalClicked
	Integer totalRegistered
	String isSelfReg
	String isDayTotal
	Integer marketingId
	Integer countryId
	Integer categoryId
	Integer duration
	
	static constraints = {
		id(nullable:true)
		createTime(nullable:false)
		totalRegistrations(nullable:true)
		totalAddressBookScrapedSize(nullable:true)
		totalSendSize(nullable:true)
		totalOpened(nullable:true)
		totalClicked(nullable:true)
		totalRegistered(nullable:true)
		isSelfReg(nullable:true)
		isDayTotal(nullable:true)
		marketingId(nullable:true)
		countryId(nullable:true)
		categoryId(nullable:true)
		duration(nullable:true)
	}
	
	static mapping = {
		version false
		table 'viral_summary'
		id generator:'sequence', params:[sequence:'viral_summary_seq']
	}
}