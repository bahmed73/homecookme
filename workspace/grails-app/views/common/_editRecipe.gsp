<div>
			<h2>Edit Recipe</h2>
			</div>
			<br class="clear"/> 
			<div class="update-separator">&nbsp;</div>
			
<g:form method="post" enctype="multipart/form-data">
<div>
			  <input type="hidden" name="id" value="${recipeInstance?.id}" />
			  <input type="hidden" name="createTime" value="${recipeInstance?.createTime}" />
              <div style="float:left;width:50" class="post"><h4>Title:</h4></div>
              upto 100 characters.<br>
			  <div>
              	<input maxlength="100" name="title" size="20" type="text" value="${recipeInstance?.title}" />
                <br />
              </div>
            

              <div style="float:left;width:50" class="post"><h4>Description:</h4></div>
              upto 5000 characters.<br>
              <div>
              <g:textArea name="description" value="${recipeInstance?.description}" rows="6" cols="40"/>
                <br />
              </div>
            
              <div style="float:left;width:50" class="post"><h4>Category:</h4></div>
				<div>
				<g:select optionKey="id" optionValue="name" name="categoryId" from="${categories}" value="${recipeInstance?.categoryId}"/>
				</div>

              <div style="float:left;width:50" class="post"><h4>Serving Size:</h4></div>
              upto 50 characters.<br>
              <div>
              	<input maxlength="50" name="servingSize" size="20" type="text" value="${recipeInstance?.servingSize}" />
                <br />
              </div>
           
              <div style="float:left;width:50" class="post"><h4>Ingredients:</h4></div>
              upto 1000 characters.<br>
              <div>
              <g:textArea name="ingredients" value="${recipeInstance?.ingredients}" rows="6" cols="40"/>
                <br />
              </div>

              <div style="float:left;width:50" class="post"><h4>Preparation:</h4></div>
              upto 1000 characters.<br>
              <div>
              <g:textArea name="preparation" value="${recipeInstance?.preparation}" rows="6" cols="40"/>
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Recipe Source:</h4></div>
              upto 2048 characters.<br>
              <div>
              	<input maxlength="2048" name="source" size="20" type="text" value="${recipeInstance?.source}" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Serving Recommendation:</h4></div>
              upto 50 characters.<br>
              <div>
              	<input maxlength="50" name="servingRecommendation" size="20" type="text" value="${recipeInstance?.servingRecommendation}" />
                <br />
              </div>
            
              <div style="float:left;width:50" class="post"><h4>Story:</h4></div>
              upto 5000 characters.<br>
              <div>
              <g:textArea name="story" value="${recipeInstance?.story}" rows="6" cols="40"/>
              	
                <br />
              </div>
            
            <div style="float:left;font-size:14px" class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                </div>
          
</g:form>
