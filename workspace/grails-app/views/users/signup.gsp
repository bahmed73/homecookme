<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

  <head>
        
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="en-us" http-equiv="Content-Language" />
<meta content="Social bookmarking fot Twitter via categorization" name="description" />
<meta content="no" http-equiv="imagetoolbar" />
<meta content="width = 780" name="viewport" />
<meta content="4FTTxY4uvo0RZTMQqIyhh18HsepyJOctQ+XTOu1zsfE=" name="verify-v1" />
<meta content="1" name="page" />

<meta content="n" name="session-loggedin" />
<meta content="" name="page-user-screen_name" />
    <title id="page_title">homecook.me - Share bookmarks, posts and blogs on twitter! - Sign Up</title>
    <LINK REL="SHORTCUT ICON"
       HREF="${createLinkTo(dir:'images', file:'mytweetmark-icon.ico')}">
<link href="https://s3.amazonaws.com/twitter_production/a/1250203207/images/twitter_57.png" rel="apple-touch-icon" />
    
    <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'twitter-https.css')}" />
    <!--<link href="https://s3.amazonaws.com/twitter_production/a/1250203207/stylesheets/twitter-https.css" media="screen" rel="stylesheet" type="text/css" />-->
    <style type="text/css">
      
        body { background: #9AE4E8 url('https://s3.amazonaws.com/twitter_production/a/1250203207/images/bg.gif') fixed no-repeat top left; }
      
      
    </style>
    <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'signupflow-https.css')}" />
      <!--<link href="https://s3.amazonaws.com/twitter_production/a/1250203207/stylesheets/signupflow-https.css" media="screen, projection" rel="stylesheet" type="text/css" />-->

  </head>

  <body class="account firefox-windows" id="new">    
    <div id="dim-screen"></div>
    <ul id="accessibility" class="offscreen">
  <li><a href="#content" accesskey="0">Skip past navigation</a></li>
  <li>On a mobile phone? Check out <a href="http://m.twitter.com/">m.twitter.com</a>!</li>
  <li><a href="#footer" accesskey="2">Skip to navigation</a></li>

    <li><a href="#signin">Skip to sign in form</a></li>
</ul>

    

    <div id="container" class="subpage">
            <span id="loader" style="display:none"><img alt="Loader" src="https://s3.amazonaws.com/twitter_production/a/1250203207/images/loader.gif" /></span>
      <div id="header" class="no-nav">
  <a href="http://www.homecook.me/" title="homecook.me / Sign up!" accesskey="1" id="logo">
          <img src="${createLinkTo(dir:'images',file:'twitter-hash-logo-for-fluid2-qh.jpg')}">    
  </a>
  </div>




      
      <div class="content-bubble-arrow"></div>
      
        <table cellspacing="0" class="columns">
          <tbody>
            <tr>
              <td id="content" class="round-left column wide">
                                <div class="wrapper">
                  
                  



<div class="content-heading">
  <div class="heading">
    <p class="sign-in">
    Already have a user name? <g:link url="/">Sign in</g:link>.
    </p>
    <!--<h2>Join the Conversation</h2>
    <p>
      Already use Twitter on your phone? <a href="#">Finish signup now</a>.
    </p>-->

  </div>
</div>

<div id="signup-form" class="">
<g:if test="${flash.message}">
<div class="message">${flash.message}</div>
</g:if>
<g:form method="post" url="[action:'processsignup',controller:'users']">
  <div style="margin:0;padding:0">
  </div>    
    <input id="follow" name="follow" type="hidden" />
    <fieldset>
      <table class="input-form">
        <tr>
          <th>
            <label for="first_name">First name</label>

          </th>
          <td class="col-field">
            <input autocomplete="off" class="text_field" id="first_name" name="firstName" size="30" tabindex="1" type="text" />
          </td>
          <td class="col-help">
            <div class="label-box info">
              Enter your first name
            </div>
            <div class="label-box good">

              Ok
            </div>
            <div class="label-box error">
                          </div>
          </td>
        </tr>
        <tr>
          <th>
            <label for="last_name">Last name</label>

          </th>
          <td class="col-field">
            <input autocomplete="off" class="text_field" id="last_name" name="lastName" size="30" tabindex="1" type="text" />
          </td>
          <td class="col-help">
            <div class="label-box info">
              Enter your last name
            </div>
            <div class="label-box good">

              Ok
            </div>
            <div class="label-box error">
                          </div>
          </td>
        </tr>
        <tr class="screen-name">
          <th>
            <label for="user_screen_name">Username</label>

          </th>
          <td class="col-field">
            <input autocomplete="off" class="text_field" id="user_screen_name" maxlength="15" name="userName" size="15" tabindex="2" type="text" />
          </td>
          <td class="col-help">
            <div class="label-box info">
              <span id="screen_name_info">Pick a unique name on myTweetMark.</span>
              <span id="avail_screenname_check_indicator" style="display:none">

                <img alt="Indicator_arrows_circle" src="https://s3.amazonaws.com/twitter_production/a/1250203207/images/indicator_arrows_circle.gif" /> Checking availability...
              </span>
            </div>
            <div class="label-box good">
              Ok
            </div>
            <div class="label-box error">
              
            </div>
          </td>

        </tr>
        <tr>
          <th></th>
          <td colspan="2">
            <span id="screen_name_url">
              Your URL: http://drspirit.com/<strong id="signup_username_url">USERNAME</strong>
            </span>
          </td>

        </tr>
        <tr>
          <th>
            <label for="user_password">Password</label>
          </th>
          <td class="col-field">
            
              <input autocomplete="off" class="text_field" id="user_user_password" name="password" size="30" tabindex="3" type="password" />
            
          </td>

          <td class="col-help">
            <div class="label-box password-meter">
              <span>6 characters or more (be tricky!)</span>
            </div>
          </td>
        </tr>
        <tr class="email">
          <th>

            <label for="user_email">Email</label>
          </th>
          <td class="col-field">
            <input autocomplete="off" class="text_field" id="user_email" name="email" size="30" tabindex="4" type="text" />
          </td>
          <td class="col-help">
            <div class="label-box info">
              <span id="email_info">In case you forget something</span>

              <span id="avail_email_check_indicator" style="display:none">
                <img alt="Indicator_arrows_circle" src="https://s3.amazonaws.com/twitter_production/a/1250203207/images/indicator_arrows_circle.gif" /> Checking availability...
              </span>
            </div>
            <div class="label-box good">
              Ok
            </div>
            <div class="label-box error">
              
            </div>

          </td>
        </tr>
        <!--
        <tr class="email-updates">
          <th></th>
          <td colspan="2" class="col-field">
            <div id="scoop"><input id="user_send_email_newsletter" name="user[send_email_newsletter]" tabindex="5" type="checkbox" value="1" /><input name="user[send_email_newsletter]" type="hidden" value="0" />
              <label for="user_send_email_newsletter">
                I want the inside scoop&mdash;please send me email updates!
              </label>

            </div>
          </td>
        </tr>
        
        
          <tr class="captcha">
            <th>
              Type the words above
            </th>
            <td class="col-field" colspan="2">
              <script>
                var RecaptchaOptions = {
                   theme: 'custom',
                   lang: 'en',
                   custom_theme_widget: 'recaptcha_widget'
                };
              </script>

              <div id="recaptcha_widget" class="clearfix">
                <div id="recaptcha_data">
                  <div id="recaptcha_image"></div>
                  <input type="text" id="recaptcha_response_field" name="recaptcha_response_field" class="text_field" tabindex="6">
                </div>
                <div id="recaptcha_controls">
                  <h3>Can't read this?</h3>
                  <ul>

                    <li class="reload"><a href="javascript:Recaptcha.reload()">Get two new words</a></li>
                    <li class="recaptcha_only_if_image"><a href="javascript:Recaptcha.switch_type('audio')">Hear a set of words</a></li>
                    <li class="recaptcha_only_if_audio"><a href="javascript:Recaptcha.switch_type('image')">Type the words you hear</a></li>
                  </ul>
                  <p id="recaptcha_powered">
                    Powered by reCAPTCHA.
                  </p>
                  <p id="recaptcha_help">

                    <a href="javascript:Recaptcha.showhelp()">Help</a>
                  </p>
                </div>
              </div>
              <script type="text/javascript" src="https://api-secure.recaptcha.net/challenge?k=6LfbTAAAAAAAAE0hk8Vnfd1THHnn9lJuow6fgulO&lang=en"></script>

              
            </td>
          </tr> -->

        
        <tr>
          <th></th>
          <td colspan="2"><g:actionSubmit class="flow-button green-arrow" value="Create Account" action="processsignup"/></td>
        </tr>
      </table>
    </fieldset>
  </g:form></div>



  


                </div>
                
<div id="content-footer">
  <div id="content-footer-header"><img alt="Brownbird_left" height="48" src="https://s3.amazonaws.com/twitter_production/a/1250203207/images/brownbird_left.png" width="48" /></div>
  <div id="content-footer-content">
    <p>
      By clicking on 'Create my account' above, you confirm that you accept the  <g:link url="[action:'tos',controller:'users']">Terms of Service</g:link>.
    </p>
  </div>

</div>
              </td>
              
            </tr>
          </tbody>
        </table>
      

      


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

  </body>

</html>
