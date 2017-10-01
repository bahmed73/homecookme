<div class="rounded" style="width:1000px;height:100px;border:1px solid #ccc;background-image:url(${createLinkTo(dir:'images', file:'black_background_tile.jpg')});">
<a href="http://www.homecook.me"><img src="${createLinkTo(dir:'images',file:'homecookme-logo-300.png')}" border='0'></a>
<div id="topnav">
<g:if test="${session.userName!=null}">
<g:link url="/"><img border="0" src="${createLinkTo(dir:'images', file:'home-new.png')}" title="Home"></g:link>
<g:link url="[action:'myTweetMarks',controller:'myTweetMark']"><img border="0" src="${createLinkTo(dir:'images', file:'profile-new.png')}" title="Profile"></g:link>
<g:link url="[action:'edit',controller:'users']"><img border="0" src="${createLinkTo(dir:'images', file:'settings-new.png')}" title="Settings"></g:link>
<g:link url="[action:'create',controller:'recipe']"><img border="0" src="${createLinkTo(dir:'images', file:'addrecipe-new.png')}" title="Add Recipe"></g:link>
<g:link url="[action:'create',controller:'blog']"><img border="0" src="${createLinkTo(dir:'images', file:'addblog-new.png')}" title="Add Blog"></g:link>
<g:link url="[action:'create',controller:'farmerMarket']" title="Create farmers market"><img border="0" src="${createLinkTo(dir:'images', file:'addmarket-new.png')}" ></g:link>
<g:link url="[action:'list',controller:'farmerMarket']"><img border="0" src="${createLinkTo(dir:'images', file:'markets-new.png')}" title="Markets"></g:link>
<g:link url="[action:'list',controller:'product']"><img border="0" src="${createLinkTo(dir:'images', file:'products-new.png')}" title="Products"></g:link>
<g:link url="[action:'list',controller:'recipe']"><img border="0" src="${createLinkTo(dir:'images', file:'recipes-new.png')}" title="Recipes"></g:link>
<g:link url="[action:'logout',controller:'users']" id="sign_out_link" onClick="onLogout();"><img border="0" src="${createLinkTo(dir:'images', file:'signout-new.png')}" title="Sign Out"></g:link>
          </g:if>
		          <g:if test="${session.userName==null}">
		          <g:link url="/"><img border="0" src="${createLinkTo(dir:'images', file:'home-new.png')}" title="Home"></g:link>
		          <g:link url="[action:'list',controller:'farmerMarket']"><img border="0" src="${createLinkTo(dir:'images', file:'markets-new.png')}" title="Markets"></g:link>
		          <g:link url="[action:'list',controller:'product']"><img border="0" src="${createLinkTo(dir:'images', file:'products-new.png')}" title="Products"></g:link>
		          <br class="clear"/>
		          <br class="clear"/>
				   
		          </g:if>
		          </div>
		          </div>	