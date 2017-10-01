<g:javascript library="scriptaculous" />    
<div class="body">

	<div style="float:left;width:700px; height:65px; background:#c5e2f8; border:1px solid #ddffcc;">
		<h1>My Twitter Friends</h1> 
		First time user might only import past 24 hours of friends from twitter.  If so, please come back again.
	</div>
	<br class="clear"/>
	<div class="update-separator-grey">&nbsp;</div>
	<br class="clear"/>
	<div class="paginateButtons">
                <g:paginate total="${tweetFriendInstanceTotal}" />
            </div>  
    <br class="clear"/>
<div class="update-separator-grey">&nbsp;</div>
	
	<div class="list">
						<br class="clear"/>
				<g:each in="${tweetFriendInstanceList}" status="j" var="tweetInstance">
				<div class="update-separator">&nbsp;</div>
                	<div style="float:left;width:150">  
                		<a href="http://www.twitter.com/${tweetInstance.friendName}" target="_blank"><img src="${tweetInstance.profilePhoto}"> </a> <a href="http://www.twitter.com/${tweetInstance.friendName}" target="_blank">${tweetInstance.friendName}</a>
                 	</div>
                    <div style="float:right;width:100">
	                    <g:def var="tweetId" value="${tweetInstance.id}"/>
	            		<g:link class="smaller-secondary-action-link" action="deleteMyTweetFriend" controller="tweetFriend" params="[id: tweetId]">
	            			Remove Friend
	            		</g:link>
                    </div>       		
                            		
                    <br class="clear"/>        		
                    <div style="float:left;width:150">   
                    	<b>Tweet: </b>      	
                    	${tweetInstance.tweet}
                    </div>
                    <br class="clear"/>    
                    <div class="update-separator-grey">&nbsp;</div>    	
                </g:each> 
            </div>
      <div class="paginateButtons">
                <g:paginate total="${tweetFriendInstanceTotal}" />
            </div>  
            <br class="clear"/>
</div>