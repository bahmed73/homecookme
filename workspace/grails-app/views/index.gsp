<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
<html>
	<head>
		<meta name="description" content="homecook.me allows local food businesses to promote themselves and gain larger reach and audience in the farmers market.  Also gain followers and business leads from Twitter & Facebook social plugins. Yelp connectors and Google maps." />
<meta name="keywords" content="local, local food, food, local business, farmers, farmers market, farmer market, farmers markets, market, markets, farm, farm market, business, farm business, organic, health, nutrient,california local markets,california markets,local farmers market,local farmers markets,local farmer markets,local market,local markets,local farmers, farmers,local california,california,local, local business, chef, food chef, featured chef, food, home cook, cook, home, recipe, home recipe, food recipe, cook recipe, spiritual, spirit, blogger, socialmedia, socialmedia blogger, social media blogger, facebook, facebook feed, twitter, twitter update, twitter, twitter hash, twitter hashtag, hash, hashtag,viral,mytweetmark,mytweetmark.com,myhash, brand,share posts,post information,organize bookmarks,share bookmarks,share knowledge,organize bookmarks,categorize bookmarks,email bookmarks, share bookmarks and posts, share posts and bookmarks, share with friends,tweet,twitter" />
<meta http-equiv="window-target" content="_top" />
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="PUBLIC">
<g:if test="${!request.getRequestURL().toString().contains('http://www') && !request.getRequestURL().toString().contains('localhost')}">
<meta http-equiv="refresh" content="0;URL=http://www.homecook.me"/>
</g:if>
<meta name="alexaVerifyID" content="VpoWwrQH0Ofv8g4v09xyWOiACxs" />
	<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
	<title>Promote your local food business and farmers markets.</title>
	<link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'skel-noscript.css')}" />
	<link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'style.css')}" />
	<link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css', file:'style-desktop.css')}" />

<LINK REL="SHORTCUT ICON"
       HREF="${createLinkTo(dir:'images', file:'homecook-icon.ico')}">
<link rel="icon" href="${createLinkTo(dir:'images', file:'homecook-icon.ico')}"/>
<g:javascript library="scriptaculous" />    

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

		<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,900,300italic" rel="stylesheet" />
		<link href='http://fonts.googleapis.com/css?family=Alegreya+SC:700,400|Alegreya+Sans' rel='stylesheet' type='text/css'>
		<script src="${createLinkTo(dir:'js', file:'jquery.min.js')}"></script>
		<script src="${createLinkTo(dir:'js', file:'jquery.dropotron.min.js')}"></script>
		<script src="${createLinkTo(dir:'js', file:'config.js')}"></script>
		<script src="${createLinkTo(dir:'js', file:'skel.min.js')}"></script>
		<script src="${createLinkTo(dir:'js', file:'skel-panels.min.js')}"></script>
		<!--<noscript>
			<link rel="stylesheet" href="${createLinkTo(dir:'css', file:'skel-noscript.css')}" />
			<link rel="stylesheet" href="${createLinkTo(dir:'css', file:'style.css')}" />
			<link rel="stylesheet" href="${createLinkTo(dir:'css', file:'style-desktop.css')}" />
		</noscript>-->
		<!--[if lte IE 8]><script src="js/html5shiv.js"></script><link rel="stylesheet" href="css/ie8.css" /><![endif]-->
	</head>
	<body class="homepage" onload="<g:if test='${flash.message}'> 
  $('fade_message').fade({ duration: 5.0, from: 5, to: 0 }); return false;
  </g:if>
  ">
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

<% session.ipAddress=request.getRemoteAddr()%>
<% session.referer=request.getHeader("REFERER")%>
<span id="oneforty.i.2394"  > </span>
<a href="http://www.homecook.me"><img src="${createLinkTo(dir:'images',file:'homecookme-logo-300.png')}" border='0'></a>
		<!-- Header Wrapper -->
			<div id="header-wrapper">
				<div class="container">
					
					<div class="row">
						<div class="12u">

							<!-- Banner -->
								<section id="banner">
									<a href="http://www.homecook.me/farmerMarket/list">
										<span class="image image-full"><img src="${createLinkTo(dir:'images', file:'homepage1.jpg')}" alt="" /></span>
										<header>
											<h2>Homecook.me</h2>
											<!--span class="byline">welcome!</span-->
										</header>
									</a>
									<section id="search">
								<!--<input type="text" placeholder="Search" size="35" style="border:none">-->
<g:form method="post" url="[action:'searchMyTweetmarks',controller:'myTweetMark']">
		  <div>
		          Search: <input type="text" name="search" size="25" value="">
		  </div>
<div>
			
			<g:actionSubmitImage value="Search" action="searchMyTweetmarks" src="${createLinkTo(dir:'images', file:'search-new.png')}"/>
			</div>
		  </g:form>
								</section>
								</section>
								
						</div>
					</div>
					
								<!-- Nav -->
										<nav id="nav">
											<ul>
												<li class="current_page_item"><a href="http://www.homecook.me/product/list">Products</a></li>
												<li><a href="http://www.homecook.me/farmerMarket/list">Markets</a></li>
												<!--<li><a href="left-sidebar.html">Login</a></li>-->
											</ul>
										</nav>
							<!-- Intro -->
							<div class="row">
						<div class="12u">
								<section>
									<div>
										<div class="row">
											<div class="6u">
													<section class="box">
													<header>
														<h2>Market your local businesses and farmers markets on Twitter through us!</h2>
													</header>
													<p>Homecook.me allows local business and farmers markets to create their social media profile. Include markets, products, price, blog, recipes, contact information. On twitter, tweets are generated to bring online traffic to the business. The business can be searched on google. As a customer, register to connect with your favorite farmers market business. <a href="http://www.mediabistro.com/alltwitter/farmers-markets-going-high-tech-with-twitter_b1976" >Continue Reading...</a></p>
													<div style="float:left;width:255px;">
													<g:link url="[action:'verifyToken',controller:'users']"><img src="${createLinkTo(dir:'images', file:'Sign-in-with-Twitter-lighter.png')}" title="Login"/></g:link><br>
													&nbsp
													<fb:login-button onlogin="verifyFBLogin();"></fb:login-button>
													</div>
												</section>
											</div>
											<div class="6u">
												<section class="box">
													<a href="http://www.foundersspace.com/contributors/bilal-ahmed-founder-of-mytweetmark-homecookme/" class="image image-full"><img src="${createLinkTo(dir:'images', file:'bilal1-1.jpg')}" alt="Woman with flowers smiling at Farmers Market" /></a>
													<!--header>
														<h3>Aptent veroeros et aliquam</h3>
														<span class="byline">Posted 45 minutes ago</span>
													</header>
													<p>Lorem ipsum dolor sit amet sit veroeros sed et blandit consequat sed veroeros lorem et blandit  adipiscing feugiat phasellus tempus hendrerit, tortor vitae mattis tempor, sapien sem feugiat sapien, id suscipit magna felis nec elit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos lorem ipsum dolor sit amet.</p>
													<footer class="actions">
														<a href="#" class="button fa fa-file-text">Continue Reading</a>
														<a href="#" class="button alt fa fa-comment">33 comments</a>
													</footer-->
												</section>
											</div>
										</div>
									</div>
								</section>

						</div>
					</div>
				</div>
			</div>
		
		<!-- Main Wrapper -->
			<div id="main-wrapper">
				<div class="container">
					<div class="row">
						<div class="12u">
							
							<!-- Portfolio -->
								<section>
									<h2>Also see:</h2>
									
									<div>
										<div class="row">
											<div class="4u" >
												<section class="box">
													<a href="http://www.homecook.me/farmerMarket/all" class="image image-full"><img src="${createLinkTo(dir:'images', file:'customer1.jpg')}" alt="" /></a>
												
													<p><a href="http://www.homecook.me/farmerMarket/all">Alphabetical customer listing</a></p>
												
												</section>
											</div>
											<div class="4u">
												<section class="box">
													<a href="http://www.homecook.me/homecookme" class="image image-full"><img src="${createLinkTo(dir:'images', file:'customer3.jpg')}" alt="" /></a>
													<p><a href="http://www.homecook.me/homecookme">Local food API and programming</a></p>
												</section>
											</div>
											<div class="4u">
												<section class="box">
													<a href="http://www.mytweetmark.com" class="image image-full"><img src="${createLinkTo(dir:'images', file:'customer2.jpg')}" alt="" /></a>
													<p><a href="http://www.mytweetmark.com">Twitter marketing tool</a></p>
												</section>
											</div>
										</div>
										
											
					</div>
					<div class="row">
						<div class="12u">

							<!-- Blog >
								<section>
									<header class="major">
										<h2>The Blog</h2>
									</header>
									<div>
										<div class="row">
											<div class="6u">
												<section class="box">
													<a href="http://facebook.com/homecookme" class="image image-full"><img src="${createLinkTo(dir:'images', file:'pic08.jpg')}" alt="" /></a>
													<header>
														<h3>Magna tempus consequat lorem</h3>
														<span class="byline">Posted 45 minutes ago</span>
													</header>
													<p>Lorem ipsum dolor sit amet sit veroeros sed et blandit consequat sed veroeros lorem et blandit  adipiscing feugiat phasellus tempus hendrerit, tortor vitae mattis tempor, sapien sem feugiat sapien, id suscipit magna felis nec elit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos lorem ipsum dolor sit amet.</p>
													<footer class="actions">
														<a href="#" class="button fa fa-file-text">Continue Reading</a>
														<a href="#" class="button alt fa fa-comment">33 comments</a>
													</footer>
												</section>
											</div>
											<div class="6u">
												<section class="box">
													<a href="http://facebook.com/homecookme" class="image image-full"><img src="images/pic09.jpg" alt="" /></a>
													<header>
														<h3>Aptent veroeros et aliquam</h3>
														<span class="byline">Posted 45 minutes ago</span>
													</header>
													<p>Lorem ipsum dolor sit amet sit veroeros sed et blandit consequat sed veroeros lorem et blandit  adipiscing feugiat phasellus tempus hendrerit, tortor vitae mattis tempor, sapien sem feugiat sapien, id suscipit magna felis nec elit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos lorem ipsum dolor sit amet.</p>
													<footer class="actions">
														<a href="#" class="button fa fa-file-text">Continue Reading</a>
														<a href="#" class="button alt fa fa-comment">33 comments</a>
													</footer>
												</section>
											</div>
										</div>
									</div>
								</section>
							
						</div>
					</div>
				</div>
			</div-->

		<!-- Footer Wrapper -->
			<div id="footer-wrapper">
				
				<!-- Footer -->
					<!--section id="footer" class="container">
						<div class="row">
							<div class="8u">

								<section>
									<header>
										<h2>Blandit nisl adipiscing</h2>
									</header>
									<ul class="dates">
										<li>
											<span class="date">Jan <strong>27</strong></span>
											<h3><a href="#">Lorem dolor sit amet veroeros</a></h3>
											<p>Ipsum dolor sit amet veroeros consequat blandit ipsum phasellus lorem consequat etiam.</p>
										</li>
										<li>
											<span class="date">Jan <strong>23</strong></span>
											<h3><a href="#">Ipsum sed blandit nisl consequat</a></h3>
											<p>Blandit phasellus lorem ipsum dolor tempor sapien tortor hendrerit adipiscing feugiat lorem.</p>
										</li>
										<li>
											<span class="date">Jan <strong>15</strong></span>
											<h3><a href="#">Magna tempus lorem feugiat</a></h3>
											<p>Dolore consequat sed phasellus lorem sed etiam nullam dolor etiam sed amet sit consequat.</p>
										</li>
										<li>
											<span class="date">Jan <strong>12</strong></span>
											<h3><a href="#">Dolore tempus ipsum feugiat nulla</a></h3>
											<p>Feugiat lorem dolor sed nullam tempus lorem ipsum dolor sit amet nullam consequat.</p>
										</li>
										<li>
											<span class="date">Jan <strong>10</strong></span>
											<h3><a href="#">Blandit tempus aliquam?</a></h3>
											<p>Feugiat sed tempus blandit tempus adipiscing nisl lorem ipsum dolor sit amet dolore.</p>
										</li>
									</ul>
								</section>
							
							</div>
							<div class="4u">
							
								<section>
									<header>
										<h2>What's this all about?</h2>
									</header>
									<a href="http://facebook.com/homecookme" class="image image-full"><img src="images/pic10.jpg" alt="" /></a>
									<p>
										This is <a href="http://html5up.net/Homecook.me/">Homecook.me</a> a free, fully responsive HTML5 site template by 
										<a href="http://n33.co">AJ</a> for <a href="http://html5up.net/">HTML5 UP</a> It's released for free under
										the <a href="http://html5up.net/license/">CCA</a> license so feel free to use it for any personal or commercial project &ndash;
										just don't forget to credit us! <strong>PS:</strong> The awesome placeholder artwork used in this design is courtesy of <a href="http://facebook.com/homecookme/">Homecookme</a>.
									</p>
									<footer>
										<a href="#" class="button">Find out more</a>
									</footer>
								</section>
							
							</div>
						</div-->
						<div class="row">
							<div class="4u">
<section>
									<header>
										<h2>Connect with us</h2>
									</header>
									<ul class="social">
										<li><a class="fa fa-facebook solo" href="https://www.facebook.com/homecook.me"><span>Facebook</span></a></li>
										<li><a class="fa fa-twitter solo" href="https://twitter.com/homecookme"><span>Twitter</span></a></li>
										<li><a class="fa fa-linkedin solo" href="http://www.linkedin.com/company/mytweetmark.com"><span>LinkedIn</span></a></li>
										<!--li><a class="fa fa-google-plus solo" href="#"><span>Google+</span></a></li-->
									</ul>
									<ul class="contact">
										<li>
											<h3>Address</h3>
											<p>
												1200 Arguello Blvd.<br />
																							San Francisco, CA 94122
											</p>
										</li>
										<li>
											<h3>Mail</h3>
											<p><a href="#">bilal@mytweetmark.com</a></p>
										</li>
										<!--li>
											<h3>Phone</h3>
											<p>(800) 000-0000</p>
										</li-->
									</ul>
								</section>
								

							</div>
							<div class="4u">

								<section>
									<header>
										<h2>More about us</h2>
									</header>
									<ul class="divided">
										<li><a href="http://www.homecook.me/images/survey.doc">Survey</a></li>
										<li><a href="http://www.slideshare.net/mytweetmark/homecookme-local-food-and-farmers-market-presentation">Slides</a></li>
										<li><a href="http://www.homecook.me/users/privacy">Privacy</a></li>
										<li><a href="http://www.homecook.me/users/tos">Terms</a></li>
									</ul>
								</section>

							</div>
							<div class="4u">
							<section>
									<header>
										<h2>And more...</h2>
									</header>
									<ul class="divided">
										<li><a href="http://www.foundersspace.com/news-announcements/startup-splotlight-mytweetmark/">On FoundersSpace</a></li>
										<li><a href="https://twitter.com/mytweetmark">mytweetmark on Twitter</a></li>
										<li><a href="https://angel.co/mytweetmark">On AngelList</a></li>
										<li><a href="http://blog.drspirit.com/2013/04/30/food-and-conscious.aspx">Food and Conscious</a></li>
										<!--li><a href="#">Sem feugiat sapien id suscipit magna felis nec</a></li>
										<li><a href="#">Elit class aptent taciti sociosqu ad litora</a></li-->
									</ul>
								</section>
								
							
							</div>
						</div>
						<div class="row">
							<div class="12u">
							
								<!-- Copyright -->
									<div id="copyright">
										<ul class="links">
											<li>&copy; 2014 Homecook.me 	</li>
											<!--li>Images: <a href="http://facebook.com/homecookme">Homecookme</a></li>
											<li>Design: <a href="http://dalliusdesign.com">Dallius</a></li-->
										</ul>
									</div>

							</div>
						</div>
					</section>
				
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