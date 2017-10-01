
<div>
              
			  <div style="float:left;width:500" class="post">
              	<h4>${recipeInstance.title}</h4>
              	<div class="fb-like" data-send="true" data-width="450" data-show-faces="true"></div>
              	<div class="fb-comments" data-href="http://www.homecook.me" data-num-posts="10" data-width="420"></div>
              	<br />
              </div>
            
              <div id="slidedown_demo2${recipeInstance.id}" style="display:none; height:110px; background:#EFEFEF; float:left;width:300px;border:1px solid #c5e2f8;">
              
              <div style="text-align:center;float:right;width:100;">
		  				<a href="#" class="secondary-action-link" onclick="$('slidedown_demo2${recipeInstance.id}').hide(); return false;">
			            			Cancel
			            		</a>
		  				</div>
				  <div style="float:left;width:350px;">
				    <g:form action="saveComment" controller="comment" method="post" >
				    <input type="hidden" id="contentId" name="contentId" value="${recipeInstance.id}" />
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
				<a href="#" onclick="Effect.SlideDown('slidedown_demo2${recipeInstance.id}'); return false;">Add review&nbsp;&raquo;</a>
              </div>
              
              <br class="clear"/>

              <div style="float:left;width:50" class="post"><h4>Category:</h4></div>
              <div style="float:left;width:500;font-size:16px;color:#7CBA53" class="post">
            	<b>${category.name}</b>
              <br />
            </div>
            
              <div style="float:left;width:50" class="post"><h4>Description:</h4></div>
              <div style="float:left;width:500" class="post">
              	${recipeInstance.description}
                <br />
              </div>
            	
              <div style="float:left;width:50" class="post"><h4>Serving Size:</h4></div>
              <div style="float:left;width:500" class="post">
            	${recipeInstance.servingSize}
              <br />
            </div>
           
              <div style="float:left;width:50" class="post"><h4>Ingredients:</h4></div>
              <div style="float:left;width:500" class="post">
            	${recipeInstance.ingredients}
              <br />
            </div>

              <div style="float:left;width:50" class="post"><h4>Preparation:</h4></div>
              <div style="float:left;width:500" class="post">
            	${recipeInstance.preparation}
              <br />
            </div>
              
              <div style="float:left;width:50" class="post"><h4>Recipe Source:</h4></div>
              <div style="float:left;width:500" class="post">
            	<a href="${recipeInstance.source}" target="_blank">${recipeInstance.source}</a>
              <br />
            </div>
              
              <div style="float:left;width:50" class="post"><h4>Serving Recommendation:</h4></div>
              <div style="float:left;width:500" class="post">
            	${recipeInstance.servingRecommendation}
              <br />
            </div>
            
              <div style="float:left;width:50" class="post"><h4>Story:</h4></div>
              <div style="float:left;width:500" class="post">
            	${recipeInstance.story}
              <br />
            </div>
            
            <g:if test="${comments}">
	            <div>
				<h2>Recipe reviews</h2> 
				</div>
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
	        	<div style="float:right;width:50">
	            	<g:if test="${session.userId==comment.userId}">
	            	<g:link class="smaller-secondary-action-link" action="deleteComment" controller="comment" params="[id: comment.comment.id]">
	            		Remove Review&nbsp;&raquo; 
	            	</g:link>
	            	</g:if>
	            </div>
	            <br class="clear"/>
	            </g:each>
	        </g:if>
	        
	        <br class="clear"/> 
			<div class="update-separator-grey">&nbsp;</div>
			
	            <a href="#" onclick="Effect.ScrollTo('article_top'); return false;">Go to top.</a>
</div>          
