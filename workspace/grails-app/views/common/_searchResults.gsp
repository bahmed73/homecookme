<div style="float: left; width: 600px; margin:20px; padding: 20px 25px 20px 0px; ">
<br class="clear"/>

<div style="float: left;margin:10px;">
<br>
<br>
<g:form method="post" url="[action:'searchMyTweetmarks',controller:'myTweetMark']">
<div>
        <b>Search</b>: <input type="text" name="search" value="">
</div>
	<div>
	<br>
	<g:actionSubmitImage value="Search" action="searchMyTweetmarks" src="${createLinkTo(dir:'images', file:'searchbutton.jpg')}"/>
	</div>
</g:form>
<br>
<br>
</div>
	
    <br class="clear"/>
    <g:each in="${searchResults}" status="i" var="result">
        <div style="float:left;width:50">
        	<g:if test="${result.profilePhoto}">
        	<g:link url="/$result.userName">
        	<img src="${result.profilePhoto}" width="48" height="48" class="profile_border"> 
        	</g:link>
        	&nbsp;
        	</g:if>
        	<g:link url="/$result.userName">${result.userName}</g:link> 
        </div>
        
        <g:if test="${result.aboutMe}">
    	<br>
    	<div style="float:left;width:600px">
    	<i>${result.aboutMe}</i> 
    	</div>
    	</g:if>
    	
    	<g:if test="${result.website}">
    	<br>
    	<div style="float:left;width:600px">
    	<g:link url="${result.website}" target="_blank">
		${result.website} 
    	</g:link>
    	</div>
    	</g:if>
    	<g:if test="${result.businessAddress}">
    	<br>
    	<div style="float:left;width:600px">
    	${result.businessAddress} 
    	</div>
    	</g:if>
    	<g:if test="${result.businessPhone}">
    	<br>
    	<div style="float:left;width:600px">
    	${result.businessPhone} 
    	</div>
    	</g:if>
        <br class="clear"/>
        
    </g:each>
    <br class="clear"/>
</div>
