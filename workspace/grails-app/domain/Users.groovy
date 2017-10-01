class Users implements Serializable {
	static searchable = true
	Long id
	String userName
	String password
	String email
	Integer status
	Integer marketingId
	Integer countryId
	Date createTime
	Date updateTime
	String ipAddress
	Integer numCategories
	String firstName
	String lastName
	Date lastLogin
	Integer loginTimes
	String profilePhoto
	Integer tweet
	String token
	String tokenSecret
	String facebookUid
	Integer permission
	Integer autoTweet
	String website
	String blog
	String color
	String aboutMe
	Integer followersCount
	Integer friendsCount
	String businessName
	String businessAddress
	String businessPhone
	String facebookProfile
	String backgroundUrl
	
	byte[] oauthObj 
	
	static constraints = {
		id(nullable:true)
		userName(nullable:false,unique:true,maxSize:100)
		password(nullable:true,maxSize:100)
		email(nullable:true,email:true,maxSize:100)
		status(nullable:false)
		marketingId(nullable:true)
		countryId(nullable:true)
		createTime(nullable:false)
		updateTime(nullable:false)
		ipAddress(nullable:true)
		numCategories(nullable:true)
		firstName(nullable:true,maxSize:100)
		lastName(nullable:true,maxSize:100)
		lastLogin(nullable:true)
		loginTimes(nullable:true)
		profilePhoto(nullable:true, url:true)
		tweet(nullable:true)
		token(nullable:true,unique:true)
		tokenSecret(nullable:true)
		facebookUid(nullable:true)
		permission(nullable:true)
		autoTweet(nullable:true)
		website(nullable:true, url:true)
		blog(nullable:true, url:true)
		color(nullable:true)
		aboutMe(nullable:true)
		followersCount(nullable:true)
		friendsCount(nullable:true)
		businessName(nullable:true)
		businessAddress(nullable:true)
		businessPhone(nullable:true)
		oauthObj(nullable:true)
		facebookProfile(nullable:true)
		backgroundUrl(nullable:true)
	}
	
	static mapping = {
		version false
		table 'users'
		id generator:'sequence', params:[sequence:'users_seq']
	}
}
