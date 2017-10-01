<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
<head>

<meta name="description" content="homecook.me allows local food businesses to promote themselves and gain larger reach and audience in the farmers market.  Also gain followers and business leads from Twitter & Facebook social plugins. Yelp connectors and Google maps." />
<meta name="keywords" content="local, local food, food, local business, farmers, farmers market, farmer market, farmers markets, market, markets, farm, farm market, business, farm business, organic, health, nutrient,california local markets,california markets,local farmers market,local farmers markets,local farmer markets,local market,local markets,local farmers, farmers,local california,california,local, local business, chef, food chef, featured chef, food, home cook, cook, home, recipe, home recipe, food recipe, cook recipe, spiritual, spirit, blogger, socialmedia, socialmedia blogger, social media blogger, facebook, facebook feed, twitter, twitter update, twitter, twitter hash, twitter hashtag, hash, hashtag,viral,mytweetmark,mytweetmark.com,myhash, brand,share posts,post information,organize bookmarks,share bookmarks,share knowledge,organize bookmarks,categorize bookmarks,email bookmarks, share bookmarks and posts, share posts and bookmarks, share with friends,tweet,twitter" />
<meta http-equiv="window-target" content="_top" />

	<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
	<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="PUBLIC">
	<title>${user.userName} - local food business <g:if test="${user.businessName}">
    : ${user.businessName}
    </g:if></title>
<LINK REL="SHORTCUT ICON"
       HREF="${createLinkTo(dir:'images', file:'homecook-icon.ico')}">
		<link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'post.css')}" />
		    
    <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'mytweetmark.css')}" />

		<link rel="icon" href="${createLinkTo(dir:'images', file:'homecook-icon.ico')}"/>
<g:javascript library="scriptaculous" />    
</head>

<body class="timeline firefox-windows" id="home">  

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

<div style="background-image:url('${user.backgroundUrl}');">
<div id="centerBody">
			
				
		<g:render template="/common/navBar2"/>	

	<g:if test="${flash.message}">		
	        <br class="clear"/>
            <div class="flashbody">
			${flash.message}
			</div>
            </g:if>
	<div style="width: 1000px; margin-top: 0px;">
	
	
    <br class="clear"/>
	<br class="clear"/>
		
		
		
		
			
			
		<div class="rounded" style="float: left; width: 400px; padding: 20px 25px 20px 0px;height:400px;float:left;font-size:16px;border:1px solid #ccc;zoom:1;margin:5px;background-color:#000000;">
			
	        <g:if test="${isFaceBookUser == 0}">
	        <div style="margin-left:20px;">
	        	<div style="float:left;width:30px">
	        		<g:link url="/$user.userName">
	        			<img src="${user.profilePhoto}" width="30" height="30"> 
	        		</g:link>
	        	</div>
    	
	        	<div style="float:left;width:30px">&nbsp;</div> 
	        	<h3><a href="http://www.twitter.com/${user.userName}" target="_blank">${user.userName}</a></h3>
	        	<g:if test="${user.followersCount}">
	        		<div style="float:left;width:30px">&nbsp;</div>
	        		<div style="float:left;width:130px"><b>Followers: ${user.followersCount}</b></div>
	        	</g:if>
	        	<g:if test="${user.friendsCount}">
	        		<div style="float:left;width:30px">&nbsp;</div>
	        		<div style="float:left;width:130px"><b>Following: ${user.friendsCount}</b></div>
	        	</g:if>
	        </div>
	        </g:if>
	        <g:else>
	        <div style="margin-left:20px;">
        	<div style="float:left;width:30px">
        		<g:link url="/$user.userName">
        			<img src="${user.profilePhoto}" width="30" height="30"> 
        		</g:link>
        	</div>
	
        	<div style="float:left;width:30px">&nbsp;</div>
        	<g:if test="${user.facebookProfile}">
        	<h3><a href="${user.facebookProfile}" target="_blank">${user.userName}</a></h3>
        	</g:if>
        	
        </div>
	        </g:else>
	        	
			<div class="rounded" style="margin:40px;width:335px;height:300px;border:1px solid #ccc;zoom:1;">
				<div style="margin:20px;">
			        <g:if test="${user.businessName}">
			        <h4>${user.businessName}</h4>
			        </g:if>
			        <g:if test="${user.businessPhone}">
			        ${user.businessPhone}<br>
			        </g:if>
			        <g:if test="${user.businessAddress}">
			        ${user.businessAddress}<br>
			        </g:if>
			        <g:if test="${user.firstName}">
			        ${user.firstName} ${user.lastName}<br>
			        </g:if>
			        <g:if test="${user.website}">
			        <a href="${user.website}" target="_blank">${user.website}</a><br>
			        </g:if>
			        <g:if test="${user.blog}">
			        <a href="${user.blog}" target="_blank">${user.blog}</a><br>
			        </g:if>
			        <g:if test="${views}">
			        <span style="color:#7CBA53;"><b>Page viewed: ${views} times</b></span><br>
			        </g:if>
			        <br>
			        <g:if test="${user.aboutMe}">
			        About me: <i>${user.aboutMe}</i><br>
			        </g:if>
		        </div>
		        
	        </div>
	        <g:if test="${session.userName!=null}">
	        <div style="margin:20px;font-size:10px;">
	        <i>TIP: Go to <g:link url="[action:'edit',controller:'users']"><b>Settings</b></g:link> and enter profile information.</i>
	        </div>
	        </g:if>
			
		</div>
		
		<div style="border-right: 0px solid #F2B202; float: left; height: 300px; width:50px; margin-top: 20px;"></div>
		
		<g:if test="${session.userId==user.id}">
		<div style="margin:20px;float: left; width:450px;">
		
		<g:if test="${displaySend==0}">
		<div class="promotion round" style="align:center;width:150">
		<br>
		  <g:link action="sendMyTweets" controller="users">
			            		<img src="${createLinkTo(dir:'images', file:'turnon-new.png')}" title="Send my tweets or feeds"/>
			            	</g:link>
		<br>
		<br>
				
		</div>
		</g:if>
		<g:else>
		<div class="promotion round" style="float:center">
		<br>
		  <g:link action="dontSendMyTweets" controller="users">
			            		<img src="${createLinkTo(dir:'images', file:'turnoff-new.png')}" title="Don't send my tweets or feeds"/>
			            	</g:link>
		<br>
		<br>
		
		</div>
		</g:else> 
		
		<div class="rounded" style="float: left; width: 350px; padding: 20px 25px 20px 0px;height:220px;border:1px solid #ccc;zoom:1;">
		
		<div>
		<h2>Favorite item(s) basket</h2> 
		</div>
		<g:if test="${lastPost}">
        <div style="font-size:16px;">
        ${lastPost.description}
		</div>
		<br class="clear"/> 
		</g:if>
		
		<div class="rounded" style="float:left;width:300px;height:30px; background:#EFEFEF; border:1px solid #c5e2f8;font-size:14px;color:#7cba53">
			<mytweetmark:postEditInPlace id="description"
			url="[controller: 'post', action: 'saveMyPost']"
			rows="1"
			cols="30"
			paramName="description">Favorite item basket</mytweetmark:postEditInPlace>
		</div>

		<br>
		<br>
		<br class="clear"/> 
		<br class="clear"/> 
		<br class="clear"/> 
		<br>

		
		<div class="update-separator-grey">&nbsp;</div>  
		Enter item(s) you like to purchase at your farmers&#39; market, separated by space.
		
		
		</div >
		<br>
		<br>
		
			
			<div style="float: left;margin:10px;">
			  <g:form method="post" url="[action:'searchMyTweetmarks',controller:'myTweetMark']">
			  <div>
			          <b>Search</b>: <input type="text" name="search" value="">
			  </div>
				<div>
				<g:actionSubmitImage value="Search" action="searchMyTweetmarks" src="${createLinkTo(dir:'images', file:'search-new.png')}"/>
				</div>
			  </g:form>
			</div>
			
			
		</div>
		</g:if>	
		<g:else>
		
		<div style="margin:20px;float: left; width:450px; padding: 20px 20px 20px 0px;">
		<!--<div style="float:right;width:250px;">
		<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
		<input type="hidden" name="cmd" value="_s-xclick">
		<input type="hidden" name="hosted_button_id" value="GVJX9MM4JEMEC">
		<input type="image" src="https://www.paypal.com/en_US/i/btn/btn_subscribeCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
		<img alt="" border="0" src="https://www.paypal.com/en_US/i/scr/pixel.gif" width="1" height="1">
		</form>
		</div>-->
		<g:if test="${user.id!=session.userId}">
        <g:if test="${!subscribed}">
		<div style="align:left;width:200px;">
		  <g:link action="subscribe" controller="subscribed" id="${user.id}">
			            		<img src="${createLinkTo(dir:'images', file:'subscribe.gif')}" title="Subscribe to this user to receive notifications and offers"/>
			            	</g:link>
			            	
		</div>
		<br style="clear: both;"/>
		</g:if>
		<g:else>
		<div style="float:left;width:200px;">
		  <g:link action="unsubscribe" controller="subscribed" id="${user.id}">
			            		<img src="${createLinkTo(dir:'images', file:'unsubscribe.gif')}" title="Unsubscribe to this user"/>
			            	</g:link>
			            	
		</div>
		<br style="clear: both;"/>
		</g:else>
		
		</g:if>
		<g:if test="${isFaceBookUser == 0}">
		<div style="float:left;width:450px;">
        <script src="http://widgets.twimg.com/j/2/widget.js"></script>
			<script>
			new TWTR.Widget({
			  version: 2,
			  type: 'profile',
			  rpp: 3,
			  interval: 6000,
			  width: 450,
			  height: 250,
			  theme: {
			    shell: {
			      background: '#000000',
			      color: '#ffba00'
			    },
			    tweets: {
			      background: '#ffffff',
			      color: '#ffba00',
			      links: '#be972f'
			    }
			  },
			  features: {
			    scrollbar: true,
			    loop: true,
			    live: true,
			    hashtags: true,
			    timestamp: true,
			    avatars: true,
			    behavior: 'default'
			  }
			}).render().setUser('${user.userName}').start();
			</script>
			</div>
			</g:if>
        
		</div>
		</g:else>
			
		<br style="clear: both;"/>
		<g:if test="${session.profile==false}">
			<!--<p style="margin-left: 97px;"><g:link url="${createLinkTo(dir:'images', file:'tip1.html')}" class="lbOn">Need Help?</g:link></p>-->
			  <div style="width:800px;margin-left: 97px;">
			<g:render template="/common/sresults"/>
			</div>
	        </g:if>
	        <g:if test="${session.profile==true}">
	        <div style="width:800px;margin-left: 97px;">
	        <g:render template="/common/sprofileResults"/>
	        </div>
        	</g:if>    
	
	</div>
</div>

	<br style="clear: both;" id="final_centerbody_clear"/>
</div>
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
