class Product {
	static searchable = true
	Long id
	Long userId
	Integer status
	Date createTime
	String name
	String price
	String quantity
	Date updateTime
	
	static constraints = {
		id(nullable:true)
		userId(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
		name(nullable:false)
		price(nullable:false)
		quantity(nullable:false)
		updateTime(nullable:false)
	}
	
	static mapping = {
		version false
		table 'product'
		id generator:'sequence', params:[sequence:'product_seq']
	}
}
