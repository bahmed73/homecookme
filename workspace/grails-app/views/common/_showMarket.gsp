
<div style="width:700px;float:left;">
<br class="clear"/>
			  <div style="float:left;width:700px" >
			  
			  	<div style="float:left;width:100px">
			  	<g:link action="show" controller="farmerMarket" id="${farmerMarketInstance.id}"><img src="/images/MARKETPIC_${farmerMarketInstance.id}-01" class="profile_border"></g:link>
			  	</div>
			  	<div style="float:left;width:30px">
			  	&nbsp;
			  	</div>
			  	<div style="float:left;width:420px;font-size:20px;">
			  	${farmerMarketInstance.title}
			  	</div>
              	<g:if test="${session.userName=='homecookme' || session.userName=='mytweetmark' || session.userName=='bilal'}"><g:link action="edit" controller="farmerMarket" id="${farmerMarketInstance.id}">edit</g:link></g:if>
            	<g:elseif test="${farmerMarketInstance.marketAdmin}">
            	<g:if test="${session.userName==farmerMarketInstance.marketAdmin}"><g:link action="edit" controller="farmerMarket" id="${farmerMarketInstance.id}">edit</g:link></g:if>
            	</g:elseif>	
              	
		        <g:if test="${!subscribed}">
	              <div style="float:left;width:150px;">
	                <g:link action="joinMarket" controller="marketUser" id="${farmerMarketInstance.id}">
	              	            		<img src="${createLinkTo(dir:'images', file:'joinmarket-new.png')}" title="Add your local business to this Market"/>
	              	            	</g:link>
	              	            	<br class="clear"/>
    
	              </div>
	              </g:if>
	              <g:else>
	              <div style="float:left;width:150px;">
	                <g:link action="unjoinMarket" controller="marketUser" id="${farmerMarketInstance.id}">
	              	            		<img src="${createLinkTo(dir:'images', file:'unjoinmarket-new.png')}" title="Remove your local business from this Market"/>
	              	            	</g:link>
	              	            	<br class="clear"/>
	              </div>
	              </g:else>
	              
	              <br class="clear"/>
	              
				<h3>Google map</h3>
				
				  
				<!-- ++Begin Map Search Control Wizard Generated Code++ -->
				  <!--
				  // Created with a Google AJAX Search Wizard
				  // http://code.google.com/apis/ajaxsearch/wizards.html
				  -->

				  <!--
				  // The Following div element will end up holding the map search control.
				  // You can place this anywhere on your page
				  -->
				  <div id="mapsearch">
				    <span style="color:#676767;font-size:11px;margin:10px;padding:4px;">Loading...</span>
				  </div>

				  <!-- Maps Api, Ajax Search Api and Stylesheet
				  // Note: If you are already using the Maps API then do not include it again
				  //       If you are already using the AJAX Search API, then do not include it
				  //       or its stylesheet again
				  //
				  // The Key Embedded in the following script tags is designed to work with
				  // the following site:
				  // http://www.homecook.me
				  -->
				  <script src="http://maps.google.com/maps?file=api&v=2&key=ABQIAAAA7OSOMZvsvx8w_yrj6JY0oRTjLr6g19LddbVc9TuFsQYMr2nKFBRZxC_WL-1n7n37bUxhQAOjqQSQVg"
				    type="text/javascript"></script>
				  <script src="http://www.google.com/uds/api?file=uds.js&v=1.0&source=uds-msw&key=ABQIAAAA7OSOMZvsvx8w_yrj6JY0oRTjLr6g19LddbVc9TuFsQYMr2nKFBRZxC_WL-1n7n37bUxhQAOjqQSQVg"
				    type="text/javascript"></script>
				  <style type="text/css">
				    @import url("http://www.google.com/uds/css/gsearch.css");
				  </style>

				  <!-- Map Search Control and Stylesheet -->
				  <script type="text/javascript">
				    window._uds_msw_donotrepair = true;
				  </script>
				  <script src="http://www.google.com/uds/solutions/mapsearch/gsmapsearch.js?mode=new"
				    type="text/javascript"></script>
				  <style type="text/css">
				    @import url("http://www.google.com/uds/solutions/mapsearch/gsmapsearch.css");
				  </style>

				  <style type="text/css">
				    .gsmsc-mapDiv {
				      height : 275px;
				    }

				    .gsmsc-idleMapDiv {
				      height : 275px;
				    }

				    #mapsearch {
				      width : 365px;
				      margin: 10px;
				      padding: 4px;
				    }
				  </style>
				  <script type="text/javascript">
				    function LoadMapSearchControl() {

				      var options = {
				            zoomControl : GSmapSearchControl.ZOOM_CONTROL_ENABLE_ALL,
				            title : "${farmerMarketInstance.title}",
				            url : '<g:createLink action="show" controller="farmerMarket" id="${farmerMarketInstance.id}"/>',
				            idleMapZoom : GSmapSearchControl.ACTIVE_MAP_ZOOM,
				            activeMapZoom : GSmapSearchControl.ACTIVE_MAP_ZOOM
				            }

				      new GSmapSearchControl(
				            document.getElementById("mapsearch"),
				            "${farmerMarketInstance.address} ${farmerMarketInstance.city} ${farmerMarketInstance.state}",
				            options
				            );

				    }
				    // arrange for this function to be called during body.onload
				    // event processing
				    GSearch.setOnLoadCallback(LoadMapSearchControl);
				  </script>
				<!-- ++End Map Search Control Wizard Generated Code++ -->

			  
              	
              </div>
            
              <div style="align:left;width:150px" ><h4>Description:</h4></div>
              <div style="float:left;width:700px;font-size:16px;" >
              <mytweetmark:convertURLtoLink text="${farmerMarketInstance.description}">
       			</mytweetmark:convertURLtoLink>
                <br />
                <br />
              </div>
            	
              <div style="align:left;width:150px" ><h4>Address:</h4></div>
              <div style="float:left;width:700px;font-size:16px;" >
              	${farmerMarketInstance.address}
                <br />
                ${farmerMarketInstance.city}
                <br />
                ${farmerMarketInstance.state}
                <br /><br />
              </div>
              
              <div style="align:left;width:150px" ><h4>Timings:</h4></div>
              <div style="float:left;width:700px;font-size:16px;" >
              	${farmerMarketInstance.timings}
                <br /><br />
              </div>
            
              
              
          	<div style="float:left;width:500px;">
      		<h4>Our businesses in this market:</h4>
      	</div>
          	<div style="float:left;width:700px;font-size:16px;margin:20px;">
          	<g:each in="${users}" status="j" var="auser">
  	        	<div style="float:left;width:30px">
  		        	<g:link url="/$auser.userName">
  		    		<img src="${auser.profilePhoto}" width="30" height="30" class="profile_border"> 
  		        	</g:link>
  	        	</div>
  	        	
  	        	<div style="float:left;width:30px">&nbsp;</div> 
  	        	
  	        	<div style="float:left;width:300px;font-size:16px;">
  	        	<g:link url="/$auser.userName">
  	    		<b>${auser.userName}</b> 
  	        	</g:link>
  	        	</div>
  	        	
  	        	<g:if test="${auser.aboutMe}">
		    	<br>
		    	<div style="float:left;width:700px;">
		    	<i>${auser.aboutMe}</i> 
		    	</div>
		    	</g:if>
		    		        	
		    	
  	        	<g:if test="${auser.website}">
  	        	<br>
  	        	<div style="float:left;width:700px;">
  	        	<g:link url="${auser.website}" target="_blank">
  	    		${auser.website} 
  	        	</g:link>
  	        	</div>
  	        	</g:if>
  	        	
  	        	<g:if test="${auser.blog}">
  	        	<br>
  	        	<div style="float:left;width:700px;">
  	        	<g:link url="${auser.blog}" target="_blank">
  	    		${auser.blog} 
  	        	</g:link>
  	        	</div>
  	        	</g:if>
  	        	
  	        	<br class="clear"/>
  	        	<br class="clear"/>
          	</g:each>
      		</div>
      	
      	  	
              
			  <g:if test="${farmerMarketInstance.facebookLike!=null}">
			  <br class="clear"/> 
				
				<h3>Facebook fan page/Yelp</h3>
				<g:if test="${!farmerMarketInstance.facebookLike.isEmpty() && farmerMarketInstance.facebookLike.contains('iframe')}">
				${farmerMarketInstance.facebookLike}
				</g:if>
				<g:elseif test="${!farmerMarketInstance.facebookLike.isEmpty()}">
				<mytweetmark:convertURLtoLink text="${farmerMarketInstance.facebookLike}">
       			</mytweetmark:convertURLtoLink>
				</g:elseif>
			  </g:if>
				<br class="clear"/> 
				<div class="fb-like" data-send="true" data-width="450" data-show-faces="true"></div>
				<div class="fb-comments" data-href="http://www.homecook.me" data-num-posts="10" data-width="600"></div>
              	<br />
	            <a href="#" onclick="Effect.ScrollTo('article_top'); return false;">Go to top.</a>
</div>          
