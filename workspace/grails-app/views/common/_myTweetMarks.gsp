<g:javascript library="scriptaculous" />    
<div class="body">

	<div style="float:left;width:700px; height:45px; background:#c5e2f8; border:1px solid #ddffcc;">
		<h1>Community Bookmarks</h1> 
		<div class="comment">
		<!--<b>New to mytweetmark?  Click profile on top right, create some content and share :)</b>-->
		</div>
	</div>
	<br class="clear"/>
	<div class="update-separator-grey">&nbsp;</div>
	<br class="clear"/>
	<div class="paginateButtons">
                <g:paginate total="${myTweetMarkInstanceTotal}" />
            </div>  
    <br class="clear"/>
<div class="update-separator-grey">&nbsp;</div>
	
	<div class="list">
		<g:each in="${myTweetMarkInstanceList}" status="i" var="myTweetMarkInstance">
			<g:def var="myTweetMark" value="${myTweetMarkInstance.myTweetMark}"/>
        	<g:def var="user" value="${myTweetMarkInstance.user}"/>
        	
        	<div style="float:left;width:50">
        		<g:link url="/$user.userName">
        		<img src="${user.profilePhoto}" width="48" height="48"> 
            	</g:link>
        	</div>
        	<div style="float:left;width:150" class="post">
        		<b>myTweetMark by <g:link url="/$user.userName">${user.userName}</g:link>: </b> <g:formatDate format="yyyy-MM-dd hh:mm:ss" date="${myTweetMarkInstance.myTweetMark.createTime}"/> - <a href="${myTweetMarkInstance.myTweetMark.url}">${myTweetMarkInstance.myTweetMark.url}</a>
        	</div>
        	
        	<br class="clear"/>
        	
        	<div style="float:left;width:150" class="comment">
        		${myTweetMark.description}
        	</div>
        	<br class="clear"/>
        	<div class="update-separator-grey">&nbsp;</div> 
		</g:each>
     </div>
     <br class="clear"/>
     <div class="paginateButtons">
                <g:paginate total="${myTweetMarkInstanceTotal}" />
            </div>  
            <br class="clear"/>
</div>