<div class="body">

	<div>
		<h2>Latest Recipes</h2> 
		<div class="comment">
		<b>These are the latest recipes shared by everyone.</b>
		</div>
	</div>
	<br class="clear"/>
	<div class="paginateButtons">
                <g:paginate total="${blogInstanceTotal}" />
            </div>  
    <br class="clear"/>
<div class="update-separator-grey">&nbsp;</div>
	
	<div class="list">
		<g:each in="${blogInstanceList}" status="i" var="blogInstance">
			<g:def var="blog" value="${blogInstance.blog}"/>
        	<g:def var="user" value="${blogInstance.user}"/>
        	<g:def var="comments" value="${blogInstance.comments}"/>
        	<g:def var="blogVar" value="${blog.id}"/>
        	
        	<div style="float:left;width:50">
        		<g:link url="/$user.userName">
        		<img src="${user.profilePhoto}" width="48" height="48"> 
            	</g:link>
        	</div>
        	<div style="float:left;width:150" class="post">
        		<b>post by <g:link url="/$user.userName">${user.userName}</g:link>: </b> <g:formatDate format="yyyy-MM-dd hh:mm:ss" date="${postInstance.post.createTime}"/> <h3>${postInstance.post.description}</h3>
        	</div>
        	
        	<div style="float:right;width:50">
        		<g:if test="${session.userId==post.userId}">
                	<g:link action="deleteMyPost" controller="post" params="[id: post.id]">
                		Remove Thought&nbsp;&raquo;
                	</g:link>
            	</g:if>
        	</div>
        	<br class="clear"/>
        	
        	<div style="float:left;width:50">
        		
            	<!--<g:link action="create" controller="comment" params="[contentId: post.id]">
            		Add comment&nbsp;&raquo;
            	</g:link>-->
            	<div id="slidedown_demo2${postVar}" style="display:none; width:220px; height:110px; background:#ddffcc; float:left;width=350;">
                
                <div style="text-align:center;float:right;width:100;">
		  				<a href="#" class="secondary-action-link" onclick="$('slidedown_demo2${postVar}').hide(); return false;">
			            			Cancel
			            		</a>
		  				</div>
				  <div style="float:left;width=350;">
				    <g:form action="saveComment" controller="comment" method="post" >
				    <input type="hidden" id="contentId" name="contentId" value="${postVar}" />
                    <input type="hidden" id="contentType" name="contentType" value="Post" />
                    <div>
    				comment: 
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
				<a href="#" onclick="Effect.SlideDown('slidedown_demo2${postVar}'); return false;">Add comment&nbsp;&raquo;</a>
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
		        	</div> &nbsp;<b>commented on</b>&nbsp;
        			<g:formatDate format="yyyy-MM-dd hh:mm:ss" date="${comment.comment.createTime}"/>  
                </div>
                <div style="float:right;width:50">
                	<g:if test="${session.userId==comment.comment.userId}">
	                	<g:link action="deleteComment" controller="comment" params="[id: comment.comment.id]">
	                		Remove Comment&nbsp;&raquo; 
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
                <g:paginate total="${postInstanceTotal}" />
            </div>  
            <br class="clear"/>
</div>