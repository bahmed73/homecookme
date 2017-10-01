class UsersViral implements Serializable {
	Long userId
	Date createTime
	Date updateTime
	Integer lastTimeAddressImported
	Integer totalAddressImported
	Integer lastInvitesSent
	Integer totalInvitesSent
	Integer totalInvitesOpened
	Integer totalInvitesClicked
	Integer totalInvitesAccepted
	
	static constraints = {
		userId(nullable:false)
		createTime(nullable:false)
		updateTime(nullable:false)
		lastTimeAddressImported(nullable:true)
		totalAddressImported(nullable:true)
		lastInvitesSent(nullable:true)
		totalInvitesSent(nullable:true)
		totalInvitesOpened(nullable:true)
		totalInvitesClicked(nullable:true)
		totalInvitesAccepted(nullable:true)
		
	}
	
	static mapping = {
		version false
		table 'users_viral'
		id composite: ['userId', 'createTime']
	}
}
