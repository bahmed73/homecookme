<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>

<meta name="description" content="homecook.me allows local food businesses to promote themselves and gain larger reach and audience in the market place.  Also gain followers and business leads from Twitter & Facebook social plugins." />
<meta name="keywords" content="local, local business, chef, food chef, featured chef, food, home cook, cook, home, recipe, home recipe, food recipe, cook recipe, spiritual, spirit, blogger, socialmedia, socialmedia blogger, social media blogger, facebook, facebook feed, twitter, twitter update, twitter, twitter hash, twitter hashtag, hash, hashtag,viral,mytweetmark,mytweetmark.com,myhash, brand,share posts,post information,organize bookmarks,share bookmarks,share knowledge,organize bookmarks,categorize bookmarks,email bookmarks, share bookmarks and posts, share posts and bookmarks, share with friends,tweet,twitter" />
<meta http-equiv="window-target" content="_top" />

	<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
	<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="PUBLIC">
	<title>Show Recipe</title>
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
  // Additional JS functions here
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '10150118447770257', // App ID
      channelUrl : '//www.homecook.me/images/channel.html', // Channel File
      status     : true, // check login status
      cookie     : true, // enable cookies to allow the server to access the session
      xfbml      : true  // parse XFBML
    });

    // Additional init code here

	FB.getLoginStatus(function(response) {
	  if (response.status === 'connected') {
	    // connected
	    doNothing()
	  } else if (response.status === 'not_authorized') {
	    // not_authorized
	    doNothing()
	  } else {
	    // not_logged_in
	    doNothing()
	  }
	 });
  };

  // Load the SDK Asynchronously
  (function(d){
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/en_US/all.js";
     ref.parentNode.insertBefore(js, ref);
   }(document));
   
   function verifyFBLogin() {
    FB.login(function(response) {
        if (response.authResponse) {
            // connected
            onConnect()
        } else {
            // cancelled
            doNothing()
        }
    });
	}

	function doNothing() {
	}
	
	function onConnect() {
    	FB.api('/me?fields=id, name, username, first_name, last_name, picture', function(response) {
        	var url = "/users/handleFacebook?userName="+response.username+"&firstName="+response.first_name+"&lastName="+response.last_name+"&profilePhoto="+response.picture.data.url+"&facebookUid="+response.id
            parent.window.location = url
        });
    }
		
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
<div style="background-image:url('${createLinkTo(dir:'images',file:'background-middle-homecook.jpg')}');">
			<div id="centerBody">

			<g:render template="/common/navBar2"/>

	<g:if test="${flash.message}">		
	        <br class="clear"/>
            <div class="flashbody">
			${flash.message}
			</div>
            </g:if>


<div style="width: 900px; margin-top: 0px;">
<g:if test="${user}">
<div style="float: left; width: 500px; padding: 20px 25px 20px 0px; ">
<br class="clear"/>
        <g:if test="${user.profilePhoto}">
        <img src="${user.profilePhoto}" width="48" height="48" class="profile_border">
        <br>
        </g:if>
        <g:link url="/${user.userName}"><h3>${user.userName}</h3></g:link>
          

</div>

<div style="border-right: 1px solid #CCCCCC; float: left; height: 150px; margin-top: 20px;"></div>

<div style="float: left; width:300px; padding: 20px 20px 20px 20px;">

<div class="promotion round" style="align:center">
<b>Share on facebook:</b>
<br>
<a name="fb_share" type="button_count" href="http://www.facebook.com/sharer.php">Share</a><script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript"></script>
</div>

</div>

<br style="clear: both;"/>
</g:if>
<g:if test="${session.profile==false}">


		<!--<p style="margin-left: 97px;"><g:link url="${createLinkTo(dir:'images', file:'tip1.html')}" class="lbOn">Need Help?</g:link></p>-->
		 <div style="width:650px;margin-left: 97px;"> 
		  
		<g:render template="/common/showBlog"/>
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
