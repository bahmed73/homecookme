<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">

  <head>
        <script type="text/javascript">

</script>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="en-us" http-equiv="Content-Language" />
<meta content="Social bookmarking fot Twitter via categorization" name="description" />
<meta content="no" http-equiv="imagetoolbar" />
<meta content="width = 780" name="viewport" />
<meta content="4FTTxY4uvo0RZTMQqIyhh18HsepyJOctQ+XTOu1zsfE=" name="verify-v1" />
<meta content="" name="page" />

<meta content="y" name="session-loggedin" />
<meta content="65585979" name="session-userid" />
<meta content="mytweetmark" name="session-user-screen_name" />
    <title id="page_title">homecook.me / Invite</title>
    <LINK REL="SHORTCUT ICON"
        HREF="${createLinkTo(dir:'images', file:'mytweetmark-icon.ico')}">
<link href="http://a1.twimg.com/a/1250203207/images/twitter_57.png" rel="apple-touch-icon" />
    
    
    <style type="text/css">
      
        
      
      
    </style>
      <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'signupflow.css')}" />

  </head>

  <body class="invitations firefox-windows" id="contacts_on_twitter">    
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
            <span id="loader" style="display:none"><img alt="Loader" src="http://a0.twimg.com/a/1250203207/images/loader.gif" /></span>
      <div id="header">
  <a href="http://www.homecook.me/" title="homecook.me / Invite" accesskey="1" id="logo">
          <img src="${createLinkTo(dir:'images',file:'twitter-hash-logo-for-fluid2-qh.jpg')}">    
  </a>

            
      <ul class="top-navigation round">
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
      <!--<li><g:link url="[action:'signup',controller:'users']">Sign up!</g:link></li>-->
      <li><g:link url="/">No need to sign up!  Login with your Twitter account.  Click here :)</g:link></li>
      </g:if>
          
              </ul>
      </div>




      
      <div class="content-bubble-arrow"></div>
      
        <table cellspacing="0" class="columns">
          <tbody>
            <tr>
              <td id="content" class="round-left column wide">
              <g:form method="post" url="[action:'processContacts',controller:'users']">  
  				<div style="margin:0;padding:0"></div>
                <div class="wrapper">

                  
                  




<img alt="Green_continue_button_small" class="offscreen" src="http://a2.twimg.com/a/1250203207/images/green_continue_button_small.gif" />

<div class="content-heading with-tabs">
  <div class="heading">
	<br>
    <h2>Sweet, select which friends..</h2>

    
  </div>

<!--  
    <ul id="contacts-tabs" class="tabMenu">

  <li id="twitter-search-tab" >
    <a href="/invitations/find_on_twitter">Find on Twitter</a>
  </li>
  <li id="service-search-tab" class="active">
    <a href="/invitations/find_on_other_networks">Find on other networks</a>
  </li>
    <li id="email-tab" >
    <a href="/invitations/invite_by_email">Invite by email</a>

  </li>
    <li id="suggestions-tab" >
    <a href="/invitations/suggestions">Suggested Users</a>
  </li>
</ul>
-->
<div class="tab"></div>

    <p class="subheading">
      Select the people you'd like to send your mytweetmarks ;)
    </p>

  
</div>




  <input type="hidden" name="service" value=""/>

  <div class="people-chooser clearfix">
    <div class="people-chooser-list">
      <fieldset>
      <!--
        <div class="select-all-people">

          <input type="checkbox" name="select_all" id="select-all" checked="checked"/>
          <span>Select All</span>
        </div>
        -->
        <div class="table-wrapper">
          <table id="contacts-table" style="height: 190px">
          <g:each in="${contacts}" status="i" var="myContact">
          <tr class="contact">
  <td>
  	<g:checkBox name="contacts" checked="false" value="${myContact.email}"/>
  </td>
  

  <td class="name">
    <strong>${myContact.name}</strong><br />
    <span>${myContact.name}</span> <span style="color: #666;">&lt;${myContact.email}&gt;</span>
  </td>
  <td class="about"></td>
</tr>
          </g:each>
          </table>
        </div>
      </fieldset>
    </div>
    <div class="people-chooser-nav clearfix">

      

<div id="people-chooser-icons">
  <!--<h3 >Show :)</h3>
  
  <p class="icons ">
    
      
      
      <img alt="bahmed73" class="icon optionally-following" height="24" id="icon-18169329" src="http://a1.twimg.com/profile_images/228117258/Photo_103_mini.jpg" width="24" />
    
      
      
      <img alt="Ashton" class="icon optionally-following" height="24" id="icon-5387412" src="http://a1.twimg.com/profile_images/18326712/fist_mini.jpg" width="24" />

      
      
      <img alt="Ali Sultan" class="icon optionally-following" height="24" id="icon-49235360" src="http://a3.twimg.com/profile_images/274185097/Upclose_mini.jpg" style="display: none;" width="24" />
    
      
      
      <img alt="Brian Woods" class="icon optionally-following" height="24" id="icon-79353" src="http://a1.twimg.com/profile_images/16924832/1718764a4076657406b339843256m_mini.jpg" style="display: none;" width="24" />
    
     
  </p>-->
  <p class="info">

    
      Show your friends how cool your collection of mytweetmarks is!  <!--You get myTweetMark <i>credit</i> for every friend you <i>invite</i> with which you can <i>buy</i> important items.  -->Good luck!    
  </p>
</div>

    </div>
  </div>




                </div>
                
    <div id="content-footer">

      <div id="content-footer-header"><img alt="Brownbird_left" height="48" src="http://a3.twimg.com/a/1250203207/images/brownbird_left.png" width="48" /></div>
      <div id="content-footer-content">
                  <p class="nav-button">
            <input alt="follow" class="flow-button green-arrow-small" name="commit" type="submit" value="Share" />
          </p>
          <p class="skip"> &nbsp; </p>
              </div>
    </div>

  </g:form>
              </td>
              
            </tr>
          </tbody>
        </table>
      

      
  <div id="footer" style="width:100%" class="round">
    <h3 class="offscreen">Footer</h3>
      
    
    <g:render template="/common/footer"/>
  </div>



      <hr />

    </div>

    

<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-12556538-1");
pageTracker._trackPageview();
} catch(err) {}</script>
    <div id="notifications"></div>

    <g:facebookConnectJavascript base="http://www.homecook.me" />
  </body>

</html>
