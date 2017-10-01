<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">

  <head>
        <script type="text/javascript">
//<![CDATA[
var page={};var onCondition=function(D,C,A,B){D=D;A=A?Math.min(A,5):5;B=B||100;if(D()){C()}else{if(A>1){setTimeout(function(){onCondition(D,C,A-1,B)},B)}}};
//]]>
</script>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="en-us" http-equiv="Content-Language" />
<meta name="description" content="homecook.me allows local food businesses to promote themselves and gain larger reach and audience in the market place.  Also gain followers and business leads from Twitter & Facebook social plugins." />
<meta name="keywords" content="local, local business, chef, food chef, featured chef, food, home cook, cook, home, recipe, home recipe, food recipe, cook recipe, spiritual, spirit, blogger, socialmedia, socialmedia blogger, social media blogger, facebook, facebook feed, twitter, twitter update, twitter, twitter hash, twitter hashtag, hash, hashtag,viral,mytweetmark,mytweetmark.com,myhash, brand,share posts,post information,organize bookmarks,share bookmarks,share knowledge,organize bookmarks,categorize bookmarks,email bookmarks, share bookmarks and posts, share posts and bookmarks, share with friends,tweet,twitter" />

    <title id="page_title">homecook.me / homecook Privacy Policy</title>
    <LINK REL="SHORTCUT ICON"
       HREF="${createLinkTo(dir:'images', file:'homecook-icon.ico')}">
    <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'twitter.css')}" />
    <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'post.css')}" />
<link rel="icon" href="${createLinkTo(dir:'images', file:'homecook-icon.ico')}"/>
    
    <link href="https://s3.amazonaws.com/twitter_production/a/1253301564/stylesheets/twitter-https.css" media="screen" rel="stylesheet" type="text/css" />
<link href="https://s3.amazonaws.com/twitter_production/a/1253301564/stylesheets/forgot_password-https.css" media="screen" rel="stylesheet" type="text/css" />
        <style type="text/css">
      
        
      
    </style>
      <link href="https://s3.amazonaws.com/twitter_production/a/1253301564/stylesheets/internal-https.css" media="screen, projection" rel="stylesheet" type="text/css" />
  <link href="https://s3.amazonaws.com/twitter_production/a/1253301564/stylesheets/terms-https.css" media="screen, projection" rel="stylesheet" type="text/css" />

  </head>

  <body class="help firefox-windows" id="privacy">
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
  
      <script type="text/javascript">
//<![CDATA[
if (window.top !== window.self) {document.write = "";window.top.location = window.self.location; setTimeout(function(){document.body.innerHTML='';},1);window.self.onload=function(evt){document.body.innerHTML='';};}
//]]>
</script>
    
    
    <div id="dim-screen"></div>
    <ul id="accessibility" class="offscreen">
  <li><a href="#content" accesskey="0">Skip past navigation</a></li>
  <li>On a mobile phone? Check out <a href="http://m.twitter.com/">m.twitter.com</a>!</li>

  <li><a href="#footer" accesskey="2">Skip to navigation</a></li>
    <li><a href="#signin">Skip to sign in form</a></li>
</ul>

    

    <div id="container" class="subpage">
            <span id="loader" style="display:none"><img alt="Loader" src="https://s3.amazonaws.com/twitter_production/a/1253301564/images/loader.gif" /></span>
      <div id="header">
  <a href="http://homecook.me/" title="homecook / Home" accesskey="1" id="logo">
          <img src="${createLinkTo(dir:'images',file:'HOMECOOKlogo-250.gif')}">   
  </a>

            <form method="post" id="sign_out_form" action="/sessions/destroy" style="display:none;">
        <input name="authenticity_token" value="f39feed1f26a32b0e9503f29c056b56c47b2e75d" type="hidden" />
      </form>

      
      
      <ul class="top-navigation round">
                  <g:if test="${session.userName!=null}">
          <li><g:link url="[action:'myTweetMarks',controller:'myTweetMark']"><b>Profile</b></g:link></li>
          <li><g:link url="[action:'list',controller:'post']"><b>Thoughts</b></g:link></li>
          
		  <li><g:link url="[action:'logout',controller:'users']" id="sign_out_link" onClick="onLogout();"><b>Sign out</b></g:link></li>
		  <g:form controller="users">
                    <input type="hidden" name="id" value="${session.userId}" />
                    <span class="button"><g:actionSubmit class="edit" value="edit" /></span>
                </g:form>
          </g:if>
          <g:if test="${session.userName==null}">
          <!--<li><g:link url="[action:'signup',controller:'users']">Sign up!</g:link></li>-->
          <li><g:link url="/">Login with your Twitter or Facebook account.</g:link></li>
          </g:if>
              </ul>

      </div>




      

      
        <div class="content-bubble-arrow"></div>
      
      
        <table cellspacing="0" class="columns">
          <tbody>
            <tr>
              <td id="content" class="round-left column wide">
                                <div class="wrapper">

                  
                  
<style type="text/css">
  p { width: 95% }
</style>

<div>
 <br>                 
 <br>                 
         
  <h2>homecook Privacy Policy</h2>

  <p><em>This Privacy Policy is effective as of August 14, 2009.</em></p>

  <p>homecook provides this Privacy Policy to inform users of our policies and procedures regarding the collection, use and disclosure of personally identifiable information received from users of this website, located at <a href="http://www.homecook.com" target="_blank">www.homecook.com</a> ("Site" ) or collected through our social networking service, including via SMS, WAP and Instant Messaging ("Service" ). This Privacy Policy may be updated from time to time for any reason; each version will apply to information collected while it was in place.  We will notify you of any material changes to our Privacy Policy by posting the new Privacy Policy on our Site.  You are advised to consult this Privacy Policy regularly for any changes.</p>

  <p>By using our Site you are consenting to our processing of your information as set forth in this Privacy Policy now and as amended by us.  "Processing"  means using cookies on a computer or using or touching information in any way, including, but not limited to, collecting, storing, deleting, using, combining and disclosing information, all of which activities will take place in the United States. If you reside outside the U.S. your personally identifiable information will be transferred to the U.S., and processed and stored there under U.S. privacy standards. By visiting our Site and providing information to us, you consent to such transfer to, and processing in, the US.</p>

  <h3>Information Collection and Use</h3>

  <p>Our primary goals in collecting personally identifiable information are to provide you with the product and services made available through the Site, including, but not limited, to the Service, to communicate with you, and to manage your registered user account, if you have one.</p>

  <p><b>Information Collected Upon Registration</b>. If you desire to have access to certain restricted sections of the Site, you will be required to become a registered user, and to submit certain personally identifiable information to homecook. This happens in a number of instances, such as when you sign up for the Service, or if you desire to receive marketing materials and information. Personally identifiable information that we may collect in such instances may include your IP address, full user name, password, email address, city, time zone, telephone number, and other information that you decide to provide us with, or that you decide to include in your public profile.  Your twitter username password is kept very secure.  If you change your password on twitter, please make sure to update it in your profile records.  We will make tweets on your behalf so your friends can enjoy your knowledge.  </p>

  <p><b>Additional Information</b> Your full user name and your photo, if you decide to upload one, are displayed to people in the homecook network to enable you to connect with people on homecook, as specified in your privacy settings. Once a member, you may provide additional information in the profile section, including but not limited to your bio, your location, as well as your personal web site, if you have one. Providing additional information beyond what is required at registration is entirely optional, but enables you to better identify yourself and find new friends and opportunities in the homecook system. If you activate the mobile phone options per the Terms of Service at <g:link class="nav" url="[action:'tos',controller:'users']">Terms</g:link>, we will collect your cellular phone number account information. You will receive notifications on your cellular phone account in a number of cases, such as when a homecook member adds you as a friend or sends you a message. If you contact us by email through the Site, we may keep a record of your contact information and correspondence, and may use your email address, and any information that you provide to us in your message, to respond to you.</p>

  <p><b>Use of Contact Information</b> In addition, we may use your contact information to market to you, and provide you with information about, our products and services, including but not limited to our Service.  If you decide at any time that you no longer wish to receive such information or communications from us, please follow the unsubscribe instructions provided in any of the communications.</p>

  <p><b>Log Data</b> When you visit the Site, our servers automatically record information that your browser sends whenever you visit a website ("Log Data" ). This Log Data may include information such as your IP address, browser type or the domain from which you are visiting, the web-pages you visit, the search terms you use, and any advertisements on which you click. For most users accessing the Internet from an Internet service provider the IP address will be different every time you log on. We use Log Data to monitor the use of the Site and of our Service, and for the Site"™s technical administration. We do not associate your IP address with any other personally identifiable information to identify you personally, except in case of violation of the Terms of Service</p>

  <h3>Cookies</h3>
  <p>Like many websites, we also use "cookie"  technology to collect additional website usage data and to improve the Site and our service.  A cookie is a small data file that we transfer to your computer"™s hard disk.  We do not use cookies to collect personally identifiable information.  homecook may use both session cookies and persistent cookies to better understand how you interact with the Site and our Service, to monitor aggregate usage by our users and web traffic routing on the Site, and to improve the Site and our services. A session cookie enables certain features of the Site and our service and is deleted from your computer when you disconnect from or leave the Site.  A persistent cookie remains after you close your browser and may be used by your browser on subsequent visits to the Site.  Persistent cookies can be removed by following your web browser help file directions.  Most Internet browsers automatically accept cookies.  You can instruct your browser, by editing its options, to stop accepting cookies or to prompt you before accepting a cookie from the websites you visit.</p>

  <h3>Information Sharing and Disclosure</h3>

  <p><b>Service Providers</b> We engage certain trusted third parties to perform functions and provide services to us, including, without limitation, hosting and maintenance, customer relationship, database storage and management, and direct marketing campaigns. We will share your personally identifiable information with these third parties, but only to the extent necessary to perform these functions and provide such services, and only pursuant to binding contractual obligations requiring such third parties to maintain the privacy and security of your data.</p>

  <p><b>Compliance with Laws and Law Enforcement</b> homecook cooperates with government and law enforcement officials or private parties to enforce and comply with the law.  We may disclose any information about you to government or law enforcement officials or private parties as we, in our sole discretion, believe necessary or appropriate to respond to claims, legal process (including subpoenas), to protect the property and rights of homecook or a third party, the safety of the public or any person, to prevent or stop any illegal, unethical, or legally actionable activity, or to comply with the law.</p>

  <p><b>Business Transfers</b> homecook may sell, transfer or otherwise share some or all of its assets, including your personally identifiable information, in connection with a merger, acquisition, reorganization or sale of assets or in the event of bankruptcy.  You will have the opportunity to opt out of any such transfer if the new entity&#39;s planned processing of your information differs materially from that set forth in this Privacy Policy.</p>

  <h3>Security</h3>
  <p>homecook is very concerned about safeguarding the confidentiality of your personally identifiable information.  We employ administrative, physical and electronic measures designed to protect your information from unauthorized access.</p>

  <p>We will make any legally-required disclosures of any breach of the security, confidentiality, or integrity of your unencrypted electronically stored personal data to you via email or conspicuous posting on this Site in the most expedient time possible and without unreasonable delay, consistent with (i) the legitimate needs of law enforcement or (ii) any measures necessary to determine the scope of the breach and restore the reasonable integrity of the data system.</p>

  <h3>Links to Other Websites</h3>
  <p>Our Site contains links to other websites.  The fact that we link to a website is not an endorsement, authorization or representation of our affiliation with that third party.  We do not exercise control over third party websites.  These other websites may place their own cookies or other files on your computer, collect data or solicit personally identifiable information from you.  Other sites follow different rules regarding the use or disclosure of the personally identifiable information you submit to them.  We encourage you to read the privacy policies or statements of the other websites you visit.</p>

  <h3>Our Policy Towards Children</h3>
  <p>The Site is not directed to persons under 13.  We do not knowingly collect personally identifiable information from children under 13. If we become aware that a child under 13 has provided us with personal identifiable Information, we will delete such information from our files.</p>

</div>

                </div>
                              </td>
              
            </tr>
          </tbody>
        </table>
      

      
  <div id="footer"
       style="width:100%"
   class="round">
      <h3 class="offscreen">Footer</h3>

      <g:render template="/common/footer"/>
  </div>



      <hr />

    </div>

    
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.3.0/jquery.min.js" type="text/javascript"></script>

<script src="https://s3.amazonaws.com/twitter_production/a/1253301564/javascripts/twitter.js?1253308045" type="text/javascript"></script>
<script src="https://s3.amazonaws.com/twitter_production/a/1253301564/javascripts/lib/jquery.tipsy.min.js?1253308045" type="text/javascript"></script>
<script type="text/javascript">
//<![CDATA[
      page.controller_name = 'HelpController';
      page.action_name = 'privacy';
      twttr.form_authenticity_token = 'f39feed1f26a32b0e9503f29c056b56c47b2e75d';
      // FIXME: Reconcile with the kinds on the Status model.
      twttr.statusKinds = {
        UPDATE: 1,
        SHARE: 2
      };
      
    
//]]>
</script>

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
