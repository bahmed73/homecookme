<br class="clear"/>
<div class="update-separator-grey">&nbsp;</div>
<div class="body">

	<div class="paginateButtons">
                <g:paginate total="${recipeInstanceTotal}" />
            </div>  
    <br class="clear"/>
<div class="update-separator-grey">&nbsp;</div>
	
	<div class="list">
		<g:each in="${recipeInstanceList}" status="i" var="recipeInstance">
			<g:def var="recipe" value="${recipeInstance.recipe}"/>
        	<g:def var="user" value="${recipeInstance.user}"/>
        	<g:def var="comments" value="${recipeInstance.comments}"/>
        	<g:def var="recipeVar" value="${recipe.id}"/>
        	
        	<div style="float:left;width:50">
        		<g:link url="/$user.userName">
        		<img src="${user.profilePhoto}" width="48" height="48"> 
            	</g:link>
        	</div>
        	<div style="float:left;width:150" class="post">
        		<b>recipe by <g:link url="/$user.userName">${user.userName}</g:link>: </b> <g:formatDate type="datetime" style="MEDIUM" date="${recipeInstance.recipe.createTime}"/> <h3><g:link action="show" controller="recipe" id="${recipeInstance.recipe.id}">${recipeInstance.recipe.title}</g:link></h3>
        	</div>
        	
        	<div style="float:right;width:50">
        		<g:if test="${session.userId==recipe.userId}">
                	<g:link action="deleteMyRecipe" controller="recipe" params="[id: recipe.id]">
                		Remove Recipe&nbsp;&raquo;
                	</g:link>
            	</g:if>
        	</div>
        	<br class="clear"/>
        	
        	<div style="float:left;width:50">
        		
            	<div id="slidedown_demo2${recipeVar}" style="display:none; height:110px; background:#EFEFEF; float:left;width:300px;border:1px solid #c5e2f8;">
                
                <div style="text-align:center;float:right;width:100;">
		  				<a href="#" class="secondary-action-link" onclick="$('slidedown_demo2${recipeVar}').hide(); return false;">
			            			Cancel
			            		</a>
		  				</div>
				  <div style="float:left;width:350px;">
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
				<a href="#" onclick="Effect.SlideDown('slidedown_demo2${recipeVar}'); return false;">Add review&nbsp;&raquo;</a>
                </div>
                
                <br class="clear"/>
        	</div>
        	<br class="clear"/>
        	
        	<g:each in="${comments}" status="j" var="comment">
            	<div style="float:left;width:150" class="comment">
            	<b>"${comment.comment.description}"</b> <br>
                	<div style="float:left;width:50">
		        		<g:link url="/${comment.user.userName}">
		        		<img src="${comment.user.profilePhoto}" width="24" height="24"> 
		            	</g:link>
		        	</div> &nbsp;<b>reviewed on</b>&nbsp;
		        	<g:formatDate type="datetime" style="MEDIUM" date="${comment.comment.createTime}"/>   
                </div>
                <div style="float:right;width:50">
                	<g:if test="${session.userId==comment.comment.userId}">
	                	<g:link action="deleteComment" controller="comment" params="[id: comment.comment.id]">
	                		Remove Review&nbsp;&raquo; 
	                	</g:link>
                	</g:if>
                </div>
             	<br class="clear"/>   
             </g:each>
                        
        	<div class="update-separator-grey">&nbsp;</div> 
		</g:each>
     </div>
     <br class="clear"/>
     <div class="paginateButtons">
                <g:paginate total="${recipeInstanceTotal}" />
            </div>  
            <br class="clear"/>
</div>