class Recipe {
	static searchable = true
	Long id
	Long userId
	Integer status
	Date createTime
	String description
	Date updateTime
	String title
	Integer categoryId
	Date cookingTime
	Date prepTime
	String servingSize
	String ingredients 
	String preparation 
	String source
	String servingRecommendation
	String story
	String geoCountry
	String geoCity
	String geoPostalCode
	String geoLatitude
	String geoLongitude
	
	static constraints = {
		id(nullable:true)
		userId(nullable:false)
		status(nullable:false)
		createTime(nullable:false)
		description(nullable:false, maxSize:5000)
		updateTime(nullable:false)
		title(nullable:false, maxSize:100)
		categoryId(nullable:true)
		cookingTime(nullable:true)
		prepTime(nullable:true)
		servingSize(nullable:true)
		ingredients(nullable:true)
		preparation(nullable:true)
		source(nullable:true)
		servingRecommendation(nullable:true)
		story(nullable:true)
		geoCountry(nullable:true)
		geoCity(nullable:true)
		geoPostalCode(nullable:true)
		geoLatitude(nullable:true)
		geoLongitude(nullable:true)
	}
	
	static mapping = {
		version false
		table 'recipe'
		id generator:'sequence', params:[sequence:'recipe_seq']
	}
}
