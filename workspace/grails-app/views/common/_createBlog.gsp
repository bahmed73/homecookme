<div>
			<h2>Create Blog</h2>
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
            
              
            <div style="float:left;font-size:14px" class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                </div>
          
</g:form>
