class MyTweetMarkTagLib {
	static namespace = 'mytweetmark'
	
	def postEditInPlace = { attrs, body ->
		def rows = attrs.rows ? attrs.rows : 0;
		def cols = attrs.cols ? attrs.cols : 0;
		def id = attrs.remove('id')
		out << "<span id='${id}'>"
		out << body()
		out << "</span>"
		out << "<script type='text/javascript'>"
		out << "new Ajax.InPlaceEditor('${id}','"
		out << createLink(attrs)
		out << "',{"
		out << "okText:'set basket',"
		out << "savingkText:'Saving favorite basket ...',"
		out << "onComplete:function(transport,element){window.location.reload();},"
		if(rows)
			out << "rows:${rows},"
		if(cols)
			out << "cols:${cols},"
		if(attrs.paramName) {
			out << """callback: function(form, value) {
				return '${attrs.paramName}=' + escape(value) }"""
		}
		out << "});"
		out << "</script>"
	}
	
	def convertURLtoLink = { attrs, body ->
		def text = attrs.text ? attrs.text : "";
		def newText = ""
		def remainText = ""
		def url = ""
		def returnText = ""
		def firstPass = false
		
		while (text.contains("http")) {
			firstPass = true
			def index = text.indexOf("http")
			newText = text.substring(0, index)
			remainText = text.substring(index)
			
			def index2 = remainText.indexOf(" ")
			if (index2 > -1) {
				url = remainText.substring(0, index2)
				text = remainText.substring(index2)
				
			} else {
				url=remainText
				text=""
			}
			
			returnText+=newText+"<a href='"+url+"' target='_blank'>"+url+"</a>"
			
			if (!text.contains("http") && !text.equals("")) {
				returnText+=text
			}
		}
		
		if (!firstPass && !text.contains("http") && !text.equals("")) {
			returnText=text
		}
		
		out << returnText
		
	}
}
