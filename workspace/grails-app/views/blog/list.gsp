<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>

<meta name="description" content="Let's share our wealth of important knowledge with everyone!" />
<meta name="keywords" content="spirit,spiritual,global,analytics, metrics, referrer, referer, blogger, socialmedia, socialmedia blogger, social media blogger, facebook, facebook feed, twitter, twitter update, twitter, twitter hash, twitter hashtag, hash, hashtag,viral,mytweetmark,mytweetmark.com,myhash, brand,share posts,post information,organize bookmarks,share bookmarks,share knowledge,organize bookmarks,categorize bookmarks,email bookmarks, share bookmarks and posts, share posts and bookmarks, share with friends,tweet,twitter" />
<meta http-equiv="window-target" content="_top" />

	<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
	<title>homecook.me - Let's share our wealth of important knowledge with everyone!</title>
<LINK REL="SHORTCUT ICON"
       HREF="${createLinkTo(dir:'images', file:'homecook-icon.jpg')}">
		<link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'post.css')}" />
		    
    <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'mytweetmark.css')}" />

		<link rel="icon" href="${createLinkTo(dir:'images', file:'homecook-icon.jpg')}"/>
<g:javascript library="scriptaculous" />    
</head>

<body>
	
<div id="fb-root"></div>
<script>
  window.fbAsyncInit = function() {
    // init the FB JS SDK
    FB.init({
      appId      : '10150118447770257', // App ID from the App Dashboard
      channelUrl : '//www.homecook.me/images/channel.html', // Channel File for x-domain communication
      status     : true, // check the login status upon init?
      cookie     : true, // set sessions cookies to allow your server to access the session?
      xfbml      : true  // parse XFBML tags on this page?
    });

    // Additional initialization code such as adding Event Listeners goes here

  };

  // Load the SDK's source Asynchronously
  (function(d){
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/en_US/all.js";
     ref.parentNode.insertBefore(js, ref);
   }(document));
   
   function onLogout() {
	<g:if test="${session.userName!=null}">
	<g:if test="${session.fuid!=null}">
		FB.logout(function(response) {
  		// user is now logged out
  		parent.window.location = "/"
		});
	</g:if>
	</g:if>
	}
</script>

			<div id="centerBody">

			
			<div id="drspirit_logo"><a href="http://www.homecook.me"><img src="${createLinkTo(dir:'images',file:'HOMECOOKlogo-250.gif')}" border='0'></a></div>
			

			<div id="topnav">
				


			<ul>
			
				<g:if test="${session.userName!=null}">
					<li class='first'><g:link url="[action:'myTweetMarks',controller:'myTweetMark']"><b>Profile</b></g:link></li>
					<li class='first'><g:link url="[action:'list',controller:'post']"><b>Thoughts</b></g:link></li>
				
					<li><g:link url="[action:'logout',controller:'users']" id="sign_out_link" onClick="onLogout();"><b>Sign out</b></g:link></li>
			          <g:form controller="users">
			                    <input type="hidden" name="id" value="${session.userId}" />
			                    <span class="button"><g:actionSubmit class="edit" value="edit" /></span>
			                </g:form>
		          </g:if>
		          <g:if test="${session.userName==null}">
		          <li><g:link url="/">Login with your Twitter or Facebook account.</g:link></li>
		          </g:if>
		
			
				
			</ul>


			</div>
			

	<g:if test="${flash.message}">		
	        <br class="clear"/>
            <div class="flashbody">
			${flash.message}
			</div>
            </g:if>


<div style="width: 900px; margin-top: 0px;">

<div style="float: left; width: 500px; padding: 20px 25px 20px 0px; ">
<br class="clear"/>
        <g:if test="${user.profilePhoto}">
        <img src="${user.profilePhoto}" 
        <g:if test="${isFaceBookUser == 1}">
        	width="48" height="48" 
        	</g:if>
         class="profile_border">
        <br>
        </g:if>
        <g:link url="/${user.userName}"><h3>${user.userName}</h3></g:link>
          

</div>

<div style="border-right: 1px solid #CCCCCC; float: left; height: 50px; margin-top: 20px;"></div>

<div style="float: left; width:300px; padding: 20px 20px 20px 20px;">
	
	<g:if test="${displaySend==0}">
		<div class="promotion round" style="align:center;width:150">
		  <g:link action="sendMyTweets" controller="users">
			            		<img src="${createLinkTo(dir:'images', file:'turnon.jpg')}" title="Send my tweets or feeds"/>
			            	</g:link>
		<br>
		Tweets and feeds are currently off.  
		
		</div>
		</g:if>
		<g:else>
		<div class="promotion round" style="float:center">
		  <g:link action="dontSendMyTweets" controller="users">
			            		<img src="${createLinkTo(dir:'images', file:'turnoff.jpg')}" title="Don't send my tweets or feeds"/>
			            	</g:link>
		<br>
		Tweets and feeds are currently on.  

		</div>
	</g:else>  
		
	
</div>
<br style="clear: both;"/>
<g:if test="${session.profile==false}">


		<!--<p style="margin-left: 97px;"><g:link url="${createLinkTo(dir:'images', file:'tip1.html')}" class="lbOn">Need Help?</g:link></p>-->
		 <div style="width:650px;margin-left: 97px;"> 
		<g:render template="/common/blogs"/>
		</div>
		</div>
        </g:if>



</p>

	
	<br style="clear: both;" id="final_centerbody_clear"/>
	</div>
		<div id="footer">
	<div class="footer_content" style="padding-left: 20px;">

		
		<g:render template="/common/footer"/>
  </div>
		<div style="clear: both; height: 1px;">&nbsp;</div>
	</div>

	<div style="margin-top: 10px;">
		<br style="clear: both;"/>		
	</div>
</div>

<g:facebookConnectJavascript base="http://www.homecook.me" />

<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-13272104-1");
pageTracker._trackPageview();
} catch(err) {}</script>

		</body>

</html>
