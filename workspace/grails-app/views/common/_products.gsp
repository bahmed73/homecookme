<div style="width:650px;float:left;">

<g:if test="${productInstanceTotal>10}">
<div class="paginateButtons" style="font-size:14px;width:630px;">
                <g:paginate total="${productInstanceTotal}" />
            </div>  
    <br class="clear"/>
</g:if>
    <div class="update-separator">&nbsp;</div>
	
	<div class="list">
		<g:each in="${productInstanceList}" status="i" var="productMarketInstance">
			<g:def var="product" value="${productMarketInstance.product}"/>
        	<g:def var="user" value="${productMarketInstance.user}"/>
        	<br class="clear"/>
        	
        	<div style="float:left;width:30px">
        	<g:link url="/$user.userName">
    		<img src="${user.profilePhoto}" width="30" height="30" class="profile_border"> 
        	</g:link>
	    	</div>
	    	
	    	<div style="float:left;width:30px">&nbsp;</div> 
	    	
	    	<div style="float:left;width:200px;font-size:17px;">
	    	<g:link url="/$user.userName">
			${user.userName} 
	    	</g:link>
	    	</div>
	    	
        	<div style="float:left;width:200px;">
        	<b>${product.name}</b>
          	</div>
        	
        	
        	<div style="float:left;width:50px;">${product.quantity}</div> 
        	
        	<div style="float:left;width:30px">&nbsp;</div> 
        	
        	<div style="float:left;width:50px;color:#7CBA53;"><b>${product.price}</b></div> 
        	
        	<g:if test="${user.aboutMe}">
	    	<br class="clear"/>
	    	<div style="float:left;width:650px;margin-left:60px;">
	    	<i>${user.aboutMe}</i>
	    	</div>
	    	</g:if>
	    	
	    	<g:if test="${user.website}">
	    	<br class="clear"/>
	    	<div style="float:left;width:650px;margin-left:60px;">
	    	<g:link url="${user.website}" target="_blank">
			${user.website} 
	    	</g:link>
	    	</div>
	    	</g:if>
	    	
	    	<g:if test="${user.blog}">
	    	<br class="clear"/>
	    	<div style="float:left;width:650px;margin-left:60px;">
	    	<g:link url="${user.blog}" target="_blank">
			${user.blog} 
	    	</g:link>
	    	</div>
	    	</g:if>
	    	
	    	<g:if test="${user.businessName}">
	    	<div style="float:left;width:650px;margin-left:60px;">
	    	<b>${user.businessName}</b>
	    	</div>
	    	</g:if>
	        
	    	<g:if test="${user.businessPhone}">
	        <div style="float:left;width:650px;margin-left:60px;">
	        ${user.businessPhone}
	        </div>
	        </g:if>
	        
	        <g:if test="${user.businessAddress}">
	        <div style="float:left;width:650px;margin-left:60px;">
	        ${user.businessAddress}
	        </div>
	        </g:if>
	        
	    	<br class="clear"/>
	    	<br class="clear"/>
        	<div class="update-separator-grey">&nbsp;</div> 
		</g:each>
     </div>
     <br class="clear"/>
     <g:if test="${productInstanceTotal>10}">
     <div class="paginateButtons" style="font-size:14px;width:630px;">
                 <g:paginate total="${productInstanceTotal}" />
             </div>  
     <br class="clear"/>
 </g:if>

</div>