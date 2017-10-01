<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">

  <head>
       
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="en-us" http-equiv="Content-Language" />
<meta http-equiv="Cache-Control" content="No-Cache" />
<meta name="description" content="mytweetmark.com allows you to share categorized bookmarks through a simple link!  Information and knowledge is key, so rather than having multitudes of bookmarks everywhere, you can store them all right here :)" />
<meta name="keywords" content="friends,tweet,twitter,knowledge,share,bookmark,organize,categorize,mytweetmark,mytweetmark.com" />
<meta http-equiv="window-target" content="_top" />
    <title id="page_title">homecook.me / Home</title>
    <LINK REL="SHORTCUT ICON"
       HREF="${createLinkTo(dir:'images', file:'mytweetmark-icon.ico')}">

    
    <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'mytweetmark.css')}" />
    <style type="text/css">
      
        body {
  body { background: #9AE4E8 url('http://a2.twimg.com/a/1250203207/images/bg.gif') fixed no-repeat top left; }


a,
#content tr.hentry:hover a,
body#profile #content div.hentry:hover a,
#side .stats a:hover span.stats_count,
#side div.user_icon a:hover,
li.verified-profile a:hover,
#side .promotion .definition strong {
  color: #2FC2EF;
}

body,
ul#tabMenu li a, #side .section h1,
#side .stat a,
#side .stats a span.stats_count,
#side div.section-header h1,
#side div.user_icon a,
#side div.user_icon a:hover,
#side div.section-header h3.faq-header,
ul.sidebar-menu li.active a,
li.verified-profile a,
#side .promotion a {
  color: #666666;
}

#side_base {
  border-left:1px solid #181A1E;
  background-color: #252429;
}

ul.sidebar-menu li.active a,
ul.sidebar-menu li a:hover,
#side div#custom_search.active,
#side .promotion,
.notify div {
  background-color: #3D3C41;
}

#side .actions,
#side .promo {
  border: 1px solid #181A1E;
}

#side div.section-header h3 {
  border-bottom: 1px solid #181A1E;
}

#side hr {
  background: #181A1E;
  color: #181A1E;
}

ul.sidebar-menu li.loading a {
  background: #3D3C41 url('http://a1.twimg.com/a/1250103249/images/spinner.gif') no-repeat 171px 0.5em !important;
}

#side .collapsible h2.sidebar-title {
  background: transparent url('http://a2.twimg.com/a/1250103249/images/toggle_up_light.png') no-repeat center right !important;
}

#side .collapsible.collapsed h2.sidebar-title {
  background: transparent url('http://a1.twimg.com/a/1250103249/images/toggle_down_light.png') no-repeat center right !important;
}      
      
    </style>
    
  </head>

  <body class="timeline firefox-windows" id="home">    
  
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

    <div id="dim-screen"></div>
    <ul id="accessibility" class="offscreen">
  <li><a href="#content" accesskey="0">Skip past navigation</a></li>

  <li>On a mobile phone? Check out <a href="http://m.twitter.com/">m.twitter.com</a>!</li>
  <li><a href="#footer" accesskey="2">Skip to navigation</a></li>
      <li><a href="#tabMenu" accesskey="3">Jump to the sidebar</a></li>
    <li><a href="#signin">Skip to sign in form</a></li>
</ul>

    

    <div id="container" class="subpage">

            <span id="loader" style="display:none"><img alt="Loader" src="http://a0.twimg.com/a/1250103249/images/loader.gif" /></span>
      <div id="header">
  <a href="http://homecook.me/" title="homecook.me / Home" accesskey="1" id="logo">
          <img src="${createLinkTo(dir:'images',file:'twitter-hash-logo-for-fluid2-qh.jpg')}">
  </a>
            
      <ul class="top-navigation round">

          <!--<li><g:link url="/app">Home</g:link></li>-->
          <g:if test="${session.userName!=null}">
          <li><g:link url="[action:'myTweetMarks',controller:'myTweetMark']">Profile</g:link></li>
          <g:if test="${session.fuid==null}">
          <li><g:link url="[action:'list',controller:'tweetFriend']">TweetFriends</g:link></li>
          <li><g:link url="[action:'list',controller:'tweetUrl']">TweetUrls</g:link></li>
          </g:if>
          <li><g:link url="[action:'list',controller:'myTweetMark']">Bookmarks</g:link></li>
          <!--<li><g:link url="[action:'list',controller:'post']">Posts</g:link></li>-->
          <li><g:link url="[action:'list',controller:'profileReferer']">Analytics</g:link></li>
		  <li><g:link url="[action:'logout',controller:'users']" id="sign_out_link" onClick="onLogout();">Sign out</g:link></li>
		  <g:form controller="users">
                    <input type="hidden" name="id" value="${session.userId}" />
                    <span class="button"><g:actionSubmit class="edit" value="edit" /></span>
                </g:form>
          </g:if>
          <g:if test="${session.userName==null}">
          <li><g:link url="/">Sign in</g:link></li>
          </g:if>
      </ul>
     </div>




      
      <div class="content-bubble-arrow"></div>
      
        <table cellspacing="0" class="columns">

          <tbody>
            <tr>
              <td id="content" class="round-left column">
                                <div class="wrapper">
                  
        <div class="body">
        <g:if test="${flash.message}">
        <br class="clear"/>
            <div class="message">${flash.message}</div>
            </g:if>
        <br class="clear"/>
        <br class="clear"/>
        <g:render template="/common/tweetFriends"/>
        
        </div>
        
        <g:if test="${session.isAdmin==true}">
        <div class="body">
        <g:render template="/common/admin"/>    
        </div>
        </g:if>

                              </td>

              
                <g:render template="/common/side"/>
              
            </tr>
          </tbody>
        </table>
      

      
  <div id="footer"  class="round">

    <h3 class="offscreen">Footer</h3>
      
    
    <g:render template="/common/footer"/>
  </div>



      <hr />

    </div>

    
    <div id="notifications"></div>
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-12556538-1");
pageTracker._trackPageview();
} catch(err) {}</script>

<g:facebookConnectJavascript base="http://www.homecook.me" />
  </body>

</html>
