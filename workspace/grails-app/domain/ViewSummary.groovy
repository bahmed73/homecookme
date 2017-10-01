class ViewSummary {

	Long id
	Date createTime
	Long num
	Long userId
	Integer followersCount
	Integer friendsCount
	
	static constraints = {
		id(nullable:true)
		num(nullable:false)
		createTime(nullable:false)
		userId(nullable:false)
		followersCount(nullable:true)
		friendsCount(nullable:true)
	}
	
	static mapping = {
		version false
		table 'view_summary'
		id generator:'sequence', params:[sequence:'view_summary_seq']
	}
}
