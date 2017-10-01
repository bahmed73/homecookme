
        <div style="width:800px;">
        
        <g:if test="${viewSummaryList}">
      	<div style="float:right;width:350px;">
      	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
          google.load("visualization", "1", {packages:["corechart"]});
          google.setOnLoadCallback(drawChart);
          function drawChart() {
            var data = new google.visualization.DataTable();
            data.addColumn('date', 'Date');
            data.addColumn('number', 'Followers');
            data.addColumn('number', 'Following');
            data.addRows(${viewSummaryInstanceTotal});
            
            <g:each in="${viewSummaryList}" status="i" var="viewSummaryMapInstance">
            data.setValue(${i}, 0, 
            	new Date(<g:formatDate format="yyyy" date="${viewSummaryMapInstance.createTime}" />,
            	<g:formatDate format="MM" date="${viewSummaryMapInstance.createTime}" />-1,
            	<g:formatDate format="dd" date="${viewSummaryMapInstance.createTime}" />)
            );
            data.setValue(${i}, 1, ${viewSummaryMapInstance.followersCount});
            data.setValue(${i}, 2, ${viewSummaryMapInstance.friendsCount});
            </g:each>

            var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
            chart.draw(data, {width: 340, height: 240, legend: 'top', title: 'Metrics & Analytics'});
          }
        </script>
        <div id="chart_div"></div>
        </div>
  		</g:if>
  		
  		
        <g:if test="${products}">
        <div style="float:left;width:450px;">
  		<h4><img src="${createLinkTo(dir:'images', file:'products.jpg')}" width="40" height="40" class="profile_border_thick"> Products:</h4>
	  	</div>
	  	<div style="float:left;width:450px;font-size:28px;">
	  	<g:each in="${products}" status="j" var="product">
	  	<div style="float:left;width:50px;">${product.quantity}</div> 
		
		<div style="float:left;width:200px;">
		<b>${product.name}</b>
	  	</div>
		
		<div style="float:left;width:50px;color:#7CBA53;"><b>${product.price}</b></div> 
		        	
	    <br class="clear"/>
	        	
      	</g:each>
  		</div>
  		<br class="clear"/>
  		  
      	</g:if>
        
      	
        <g:if test="${markets}">
      	<div style="float:left;width:450px;">
      	<h4><img src="${createLinkTo(dir:'images', file:'farmersmarket-icon.jpg')}" width="40" height="40" class="profile_border_thick"> Markets:</h4>
  	</div>
  	<br class="clear"/>
      	<div style="float:left;width:450px">
      	<g:each in="${markets}" status="j" var="market">
	        	<div style="float:left;width:30px">
	        		  
			  		<g:link action="show" controller="farmerMarket" id="${market.id}"><img src="/images/MARKETPIC_${market.id}-02" class="profile_border" width="30" height="30"></g:link>
			  			
	        	</div>
	        	
	        	<div style="float:left;width:20px">&nbsp;</div> 
	        	
	        	<div style="float:left;width:400px;font-size:16px;">
	        	<g:link action="show" controller="farmerMarket" params="[id: market.id]">
	    		${market.title} 
	        	</g:link>
	        	</div>
	        	
	        	<br class="clear"/>
	        	
      	</g:each>
  		</div>
  	
  		<g:if test="${marketMap}">
      	<div style="float:right;width:350px;">
  		<img src="${marketMap}">
  		</div>
  		</g:if>
  		
      	<br class="clear"/>
      	<div class="update-separator-grey">&nbsp;</div>   
        </g:if>
        
        <g:if test="${lastPost}">
        <div style="float:left;width:450px;">
		<h2>Favorite item basket</h2> 
		</div>
		<br class="clear"/>
		<div style="float:left;width:450px;font-size:16px;color:#6b9144;">
        ${lastPost.description}
		</div>
		
		<br class="clear"/> 
		<div class="update-separator-grey">&nbsp;</div>
		
		</g:if>
		
		
        
        
		
        <div>
        <h4><img src="${createLinkTo(dir:'images', file:'Blog.jpg')}" width="40" height="40" class="profile_border_thick"> Blogs:</h4> 
		</div>
		
        <g:each in="${blogs}" status="i" var="myBlog">
        
             
            <div style="float:left;width:750px;">
            <h3><g:link action="show" controller="blog" id="${myBlog.id}">${myBlog.title}</g:link></h3>
            <g:formatDate type="datetime" style="MEDIUM" date="${myBlog.createTime}"/> 
            </div>    
            
        	<br>
            <div style="float:left;width:750px;font-size:16px;">
            ${myBlog.description}
            </div>
            
            <br class="clear"/>
            
        </g:each>
        <br class="clear"/>
        <div class="update-separator-grey">&nbsp;</div>
         
			<div>
			<h4><img src="${createLinkTo(dir:'images', file:'recipes-icon.jpg')}" width="40" height="40" class="profile_border_thick"> Recipes:</h4> 
			</div>
			
			<g:each in="${recipes}" status="i" var="myRecipe">
            
                <g:def var="recipe" value="${myRecipe.recipe}"/>
                <g:def var="comments" value="${myRecipe.comments}"/>
                <g:def var="recipeVar" value="${myRecipe.id}"/>
                  
                <div style="float:left;width:750px;">
                <h3><g:link action="show" controller="recipe" id="${recipe.id}">${recipe.title}</g:link></h3>
                <g:formatDate type="datetime" style="MEDIUM" date="${recipe.createTime}"/> 
                </div>    
                <br>
                <div style="float:left;width:750px;font-size:16px;">
                ${recipe.description}
                </div>
                
                <div id="slidedown_demo2${recipe.id}" style="display:none; height:110px; background:#EFEFEF; float:left;width:350px;border:1px solid #c5e2f8;">
                
                <div style="text-align:center;float:right;width:100;">
		  				<a href="#" class="secondary-action-link" onclick="$('slidedown_demo2${recipe.id}').hide(); return false;">
			            			Cancel
			            		</a>
		  				</div>
				  <div style="float:left;width=350;">
				    <g:form action="saveComment" controller="comment" method="post" >
				    <input type="hidden" id="contentId" name="contentId" value="${recipe.id}" />
                    <input type="hidden" id="contentType" name="contentType" value="Recipe" />
                    <div>
    				review: 
    				</div>
    				<div>
    				<input type="text" id="comment" name="description" value=""/>
    				</div>
    				<div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
				    </g:form>
				  </div>
				</div>
				<br class="clear"/>
				<div>
				<a href="#" onclick="Effect.SlideDown('slidedown_demo2${recipe.id}'); return false;">Add review&nbsp;&raquo;</a>
                </div>
                
                <br class="clear"/>
                    
                    
                <g:each in="${comments}" status="j" var="comment">
                    <div style="float:left;width:350" class="comment">
                    <b>"${comment.comment.description}"</b> <br>
                    	<div style="float:left;width:50">
                    	<g:def var="userVar" value="${comment.user.userName}"/>
                    	<g:link mapping="personLink" params="[userName: userVar]">
			        		<img src="${comment.user.profilePhoto}" width="48" height="48"> 
			            	</g:link>
			        	</div> &nbsp;
			        </div>
			        <div style="float:left;width:350;font-size:10px;">
		        		<b>reviewed</b>&nbsp;on&nbsp;<g:formatDate type="datetime" style="MEDIUM" date="${comment.comment.createTime}"/>  
                    </div>
                    <!--
                	<div style="float:right;width:50">
                    	<g:if test="${session.userId==comment.userId}">
                    	<g:link class="smaller-secondary-action-link" action="deleteComment" controller="comment" params="[id: comment.comment.id]">
                    		Remove Review&nbsp;&raquo; 
                    	</g:link>
                    	</g:if>
                    </div>
                    -->
                    <br class="clear"/>
                </g:each>
                
            </g:each>

            <br class="clear"/>
            <div class="update-separator-grey">&nbsp;</div>
			
            <div>
            <h4><img src="${createLinkTo(dir:'images', file:'links.jpg')}" width="40" height="40" class="profile_border_thick"> Links:</h4> 
			</div>
			
            <g:each in="${myCategories}" status="i" var="myCategory">
			<div style="float:left;width:750px;">
				<h3>${myCategory.name}</h3>
			</div>
			<br class="clear"/>
			
			<g:each in="${myCategory.tweets}" status="j" var="myTweets">
			
            	<div style="float:left;width:750px;font-size:16px;">           	
                	<a href="${myTweets.url}" target="_blank">${myTweets.url}</a>
             	</div>
                        		
                <br class="clear"/>        		
                <div style="float:left;width:750px;font-size:16px;">   
                	<b>${myTweets.description}</b>
                </div>
                <br class="clear"/>        	
            </g:each> 
                        
            
		</g:each>
		<br class="clear"/>
		<div class="update-separator-grey">&nbsp;</div>
		
		<g:if test="${recommendations}">
      	<div style="float:left;width:800px;">
	        <div>
	    	<h2>Recommendations</h2>
	    	Recommendations are based on food basket preferences.
	        </div>
	        <g:each in="${recommendations}" status="j" var="recommendation">
	    			
	                	<div style="float:left;width:200px;height:70px;">  
	                		<a href="/${recommendation.userName}"><img src="${recommendation.profilePhoto}" width="48" height="48" class="profile_border"> </a><br> 
	                		<a href="/${recommendation.userName}"><g:if test="${recommendation.businessName}">${recommendation.businessName}</g:if><g:else>${recommendation.userName}</g:else></a>
	                 	</div>
	                            		 
	        </g:each>
	    </div>
	        
	    <br class="clear"/>
	    <div class="update-separator-grey">&nbsp;</div>  
        </g:if>
        
      	<g:if test="${subscribers}">
      	<div style="float:left;width:800px;">
	        <div>
	    	<h2>Subscribers</h2>
	        </div>
	        <g:each in="${subscribers}" status="j" var="subscriber">
	    			
	                	<div style="float:left;width:200px;height:70px;">  
	                		<a href="/${subscriber.userName}"><img src="${subscriber.profilePhoto}" width="48" height="48" class="profile_border"> </a><br> 
	                		<a href="/${subscriber.userName}"><g:if test="${subscriber.firstName}">${subscriber.firstName} ${subscriber.lastName}</g:if><g:else>${subscriber.userName}</g:else></a>
	                 	</div>
	                            		 
	        </g:each>
	    </div>
	        
	    <br class="clear"/>
	    <div class="update-separator-grey">&nbsp;</div>  
        </g:if>
		<div style="float:left;width:750px;">
		<div class="fb-like" data-send="true" data-width="450" data-show-faces="true"></div>
		<div class="fb-comments" data-href="http://www.homecook.me" data-num-posts="1" data-width="750"></div>
      	</div>
      	<br class="clear"/>
        
            <a href="#" onclick="Effect.ScrollTo('article_top'); return false;">Go to top.</a>

        </div>
