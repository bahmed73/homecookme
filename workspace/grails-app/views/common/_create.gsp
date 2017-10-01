<div>
			<h2>Create Recipe</h2>
			</div>
			<br class="clear"/> 
			<div class="update-separator">&nbsp;</div>
			
<g:form method="post" enctype="multipart/form-data">
<div>
              <div style="float:left;width:50" class="post"><h4>Title:</h4></div>
              upto 100 characters.<br>
			  <div>
              	<input maxlength="100" name="title" size="20" type="text" value="" />
                <br />
              </div>
            

              <div style="float:left;width:50" class="post"><h4>Description:</h4></div>
              upto 5000 characters.<br>
              <div>
              	<textarea name="description" cols=40 rows=6></textarea>
                <br />
              </div>
            
              <div style="float:left;width:50" class="post"><h4>Category:</h4></div>
  				<div>
  				<g:select optionKey="id" optionValue="name" name="categoryId" from="${categories}"/>
  				</div>
  				
              <div style="float:left;width:50" class="post"><h4>Serving Size:</h4></div>
              upto 50 characters.<br>
              <div>
              	<input maxlength="50" name="servingSize" size="20" type="text" value="" />
                <br />
              </div>
           
              <div style="float:left;width:50" class="post"><h4>Ingredients:</h4></div>
              upto 1000 characters.<br>
              <div>
              	<textarea name="ingredients" cols=40 rows=6></textarea>
                <br />
              </div>

              <div style="float:left;width:50" class="post"><h4>Preparation:</h4></div>
              upto 1000 characters.<br>
              <div>
              	<textarea name="preparation" cols=40 rows=6></textarea>
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Recipe Source:</h4></div>
              upto 2048 characters.<br>
              <div>
              	<input maxlength="2048" name="source" size="20" type="text" value="" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Serving Recommendation:</h4></div>
              upto 50 characters.<br>
              <div>
              	<input maxlength="50" name="servingRecommendation" size="20" type="text" value="" />
                <br />
              </div>
            
              <div style="float:left;width:50" class="post"><h4>Story:</h4></div>
              upto 5000 characters.<br>
              <div>
              	<textarea name="story" cols=40 rows=6></textarea>
                <br />
              </div>
            <!--<div style="float:left;width:50" class="post"><h4>Upload Profile Image (JPG):</h4></div>
                                
                                <div>
                                    <input type="file" name="myFile" />
                                </div>-->
                   

            <div style="float:left;font-size:14px" class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                </div>
          
</g:form>
