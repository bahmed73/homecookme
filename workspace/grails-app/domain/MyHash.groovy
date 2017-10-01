class MyHash {
	static searchable = true
	Long id
	Long userId
	Integer status
	Date createTime
	String hash
	Date updateTime
	
	static constraints = {
		id(nullable:true)
		userId(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
		hash(nullable:false, maxSize:30)
		updateTime(nullable:false)
	}
	
	static mapping = {
		version false
		table 'my_hash'
		id generator:'sequence', params:[sequence:'my_hash_seq']
	}
}
