<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>

<meta name="description" content="homecook.me allows local food businesses to promote themselves and gain larger reach and audience in the market place.  Also gain followers and business leads from Twitter & Facebook social plugins." />
<meta name="keywords" content="local, local business, chef, food chef, featured chef, food, home cook, cook, home, recipe, home recipe, food recipe, cook recipe, spiritual, spirit, blogger, socialmedia, socialmedia blogger, social media blogger, facebook, facebook feed, twitter, twitter update, twitter, twitter hash, twitter hashtag, hash, hashtag,viral,mytweetmark,mytweetmark.com,myhash, brand,share posts,post information,organize bookmarks,share bookmarks,share knowledge,organize bookmarks,categorize bookmarks,email bookmarks, share bookmarks and posts, share posts and bookmarks, share with friends,tweet,twitter" />
<meta http-equiv="window-target" content="_top" />

	<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
	<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="PUBLIC">
	<title>Edit Recipe</title>
<LINK REL="SHORTCUT ICON"
       HREF="${createLinkTo(dir:'images', file:'homecook-icon.ico')}">
		<link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'post.css')}" />
		    
    <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'mytweetmark.css')}" />

		<link rel="icon" href="${createLinkTo(dir:'images', file:'homecook-icon.ico')}"/>
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
<div>
			<div id="centerBody">

			
			<g:render template="/common/navBar"/>
			

	<g:if test="${flash.message}">		
	        <br class="clear"/>
            <div class="flashbody">
			${flash.message}
			</div>
            </g:if>


<div style="width: 900px; margin-top: 0px;">

<div style="float: left; width: 500px; padding: 20px 25px 20px 0px; ">
<br class="clear"/>
        <g:if test="${user && user.profilePhoto}">
        <img src="${user.profilePhoto}" width="48" height="48" class="profile_border">
        <br>
        </g:if>
        <g:link url="/${user.userName}"><h3>${user.userName}</h3></g:link>
          

</div>

<!--<div style="border-right: 1px solid #CCCCCC; float: left; height: 50px; margin-top: 20px;"></div>

<div style="float: left; width:300px; padding: 20px 20px 20px 20px;">
	
	<g:if test="${displaySend==0}">
		<div class="promotion round" style="align:center;width:150">
		  <g:link action="sendMyTweets" controller="users">
			            		<img src="${createLinkTo(dir:'images', file:'turnon.gif')}" title="Send my tweets or feeds"/>
			            	</g:link>
		<br>
		Tweets and feeds are currently off.  
		
		</div>
		</g:if>
		<g:else>
		<div class="promotion round" style="float:center">
		  <g:link action="dontSendMyTweets" controller="users">
			            		<img src="${createLinkTo(dir:'images', file:'turnoff.gif')}" title="Don't send my tweets or feeds"/>
			            	</g:link>
		<br>
		Tweets and feeds are currently on.  

		</div>
	</g:else>  
		
	
</div>-->
<br style="clear: both;"/>
<g:if test="${session.profile==false}">


		<!--<p style="margin-left: 97px;"><g:link url="${createLinkTo(dir:'images', file:'tip1.html')}" class="lbOn">Need Help?</g:link></p>-->
		 <div style="width:650px;margin-left: 97px;"> 
		<g:render template="/common/editRecipe"/>
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
