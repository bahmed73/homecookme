<g:javascript library="scriptaculous" />    
<div class="body">

	<div style="float:left;width:700px; height:65px; background:#c5e2f8; border:1px solid #ddffcc;">
		<h1>My Twitter Bookmarks</h1> 
		First time user might only import past 24 hours of bookmarks from twitter.  If so, please come back again.
	</div>
	<br class="clear"/>
	<div class="update-separator-grey">&nbsp;</div>
	<br class="clear"/>
	<div class="paginateButtons">
                <g:paginate total="${tweetUrlInstanceTotal}" />
            </div>  
    <br class="clear"/>
<div class="update-separator-grey">&nbsp;</div>
	
	<div class="list">
						<br class="clear"/>
				<g:each in="${tweetUrlInstanceList}" status="j" var="tweetInstance">
				<div class="update-separator">&nbsp;</div>
                	<div style="float:left;width:150">  
                		<b>URL: </b>          	
                    	<a href="${tweetInstance.url}" target="_blank">${tweetInstance.url}</a>
                 	</div>
                    <div style="float:right;width:100">
	                    <g:def var="tweetId" value="${tweetInstance.id}"/>
	            		<g:link class="smaller-secondary-action-link" action="deleteMyTweetUrl" controller="tweetUrl" params="[id: tweetId]">
	            			Remove Tweet
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
                <g:paginate total="${tweetUrlInstanceTotal}" />
            </div>  
            <br class="clear"/>
</div>