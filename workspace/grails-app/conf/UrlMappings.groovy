class UrlMappings {
    static mappings = {
      name personLink: "/$userName"(controller:'myTweetMark', action:'userTweetMarks'){
    	  constraints {
    		  
    	  }
      }
      name idLink: "/id/$id"(controller:'myTweetMark', action:'idTweetMarks'){
    	  constraints {
    		  
    	  }
      }
      name xd_receiver: "/xd_receiver.htm"(uri:'/xd_receiver.htm'){
    	  constraints {
    		  
    	  }
      }
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
      "/plugins/facebook-connect-0.1/xd_receiver.htm"(view:'/index.gsp'){
    	  constraints {
			 // apply constraints here
		  }
	  }
      "/"(view:'index'){
	      constraints {
			 // apply constraints here
		  }
	  }
      "500"(view:'/error')
	}
}
