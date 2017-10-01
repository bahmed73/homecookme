<div style="width:650px;float:left;">

<g:if test="${farmerMarketInstanceTotal>5}">
<div class="paginateButtons" style="font-size:14px;width:630px;">
           <g:paginate total="${farmerMarketInstanceTotal}" />
       </div>  
       <br class="clear"/>
</g:if>

	
	<div class="list">
		<g:each in="${farmerMarketInstanceList}" status="i" var="farmerMarketInstance">
			<g:def var="market" value="${farmerMarketInstance.market}"/>
        	<g:def var="users" value="${farmerMarketInstance.users}"/>
        	
        	<div style="float:left;width:70px">
        	
		  		<g:link action="show" controller="farmerMarket" id="${market.id}"><img src="/images/MARKETPIC_${market.id}-02" class="profile_border" width="48" height="48"></g:link>
		  			
        	
        	<g:if test="${session.userName=='homecookme' || session.userName=='mytweetmark' || session.userName=='bilal'}"><g:link action="edit" controller="farmerMarket" id="${market.id}">edit</g:link></g:if>
        	<g:elseif test="${market.marketAdmin}">
        	<g:if test="${session.userName==market.marketAdmin}"><g:link action="edit" controller="farmerMarket" id="${market.id}">edit</g:link></g:if>
        	</g:elseif>
        	</div>
        	<div style="float:left;width:500px">
        		<h3><g:link action="show" controller="farmerMarket" id="${market.id}">${market.title}</g:link></h3>
        		<h4>${market.address}</h4> 
        		<h4>${market.city}</h4>
        		<h4>${market.timings}</h4>
        	</div>
        	
        	<br class="clear"/>
        	
        	<div style="float:left;width:600px;">
    		<h4>Our businesses in this market:</h4>
    	</div>
        	<div style="float:left;width:650px;margin:20px;">
        	<g:each in="${users}" status="j" var="user">
	        	<div style="float:left;width:30px">
		        	<g:link url="/$user.userName">
		    		<img src="${user.profilePhoto}" width="30" height="30" class="profile_border"> 
		        	</g:link>
	        	</div>
	        	
	        	<div style="float:left;width:30px">&nbsp;</div> 
	        	
	        	<div style="float:left;width:300px;font-size:17px;">
	        	<g:link url="/$user.userName">
	    		${user.userName} 
	        	</g:link>
	        	</div>
	        	
	        	
	        	<br class="clear"/>
	        	
        	</g:each>
    		</div>
    	
    		<br class="clear"/>
        	<br class="clear"/>
        	
		</g:each>
     </div>
     <br class="clear"/>
     <g:if test="${farmerMarketInstanceTotal>5}">
     <div class="paginateButtons" style="font-size:14px;width:630px;">
     	<g:paginate total="${farmerMarketInstanceTotal}" />
     </div>  
            <br class="clear"/>
     </g:if>
            <div class="update-separator">&nbsp;</div>
            <g:link url="[action:'map',controller:'farmerMarket']"><b>Yelp reviews, maps and helpful links</b></g:link>
            <br class="clear"/>

</div>