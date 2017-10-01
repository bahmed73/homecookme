<td id="side_base" class="column round-right">
                                  
                  <div id="side">
                      <div id="profile" class="section">
                              
</div>

<g:if test="${session.profile==false}">

<div>
  
  

<div>
<g:if test="${displaySend==0}">
<div class="promotion round" style="align:center;width:150">
  <g:link action="sendMyTweets" controller="users">
	            		<img src="${createLinkTo(dir:'images', file:'tweet.jpg')}" title="Send my tweets or feeds"/>
	            	</g:link>
Tweets/feeds currently off

</div>
</g:if>
<g:else>
<div class="promotion round" style="float:center">
  <g:link action="dontSendMyTweets" controller="users">
	            		<img src="${createLinkTo(dir:'images', file:'notweet.jpg')}" title="Don't send my tweets or feeds"/>
	            	</g:link>
Tweets/feeds currently on

</div>
</g:else>  
<br>
  <hr/>


</g:if>  




<br>
<hr/>


<div>
   
  <g:form method="post" url="[action:'searchMyTweetmarks',controller:'myTweetMark']">
  <div class="promotion round">
          <b>Search</b>: <input type="text" name="search" value="">
  </div>
	<div>
	<br>
	<g:actionSubmit class="flow-button green-arrow-small" value="Search" action="searchMyTweetmarks"/>
	</div>
  </g:form>
  <hr/>
  <br>
</div>
  


  

      
    <div id="trends" class="collapsible">

      
        
      
    </div>
  

<g:if test="${session.profile==true}">
<div class="promotion round" style="align:center">
<b>Retweet:</b>
<br>
  <script type="text/javascript" src="http://tweetmeme.com/i/scripts/button.js"></script>
<hr/>
  <br>
<b>Share on facebook:</b>
<br>
<a name="fb_share" type="button_count" href="http://www.facebook.com/sharer.php">Share</a><script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript"></script>
</div>
</g:if>

                  </div>
                                </td>