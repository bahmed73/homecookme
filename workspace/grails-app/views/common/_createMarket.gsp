<div>
			<h2>Create Farmers Market</h2>
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
            

              <div style="float:left;width:50" class="post"><h4>Address:</h4></div>
              upto 100 characters.<br>
			  <div>
              	<input maxlength="100" name="address" size="20" type="text" value="" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>City:</h4></div>
              upto 50 characters.<br>
			  <div>
              	<input maxlength="50" name="city" size="20" type="text" value="" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>State:</h4></div>
              upto 2 characters (CA, TX, NY, AZ, NC, etc).<br>
			  <div>
              	<input maxlength="2" name="state" size="20" type="text" value="" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Timings:</h4></div>
              upto 100 characters.<br>
			  <div>
              	<input maxlength="100" name="timings" size="20" type="text" value="" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Market Admin:</h4></div>
              upto 100 characters.<br>
			  <div>
              	<input maxlength="100" name="marketAdmin" size="20" type="text" value="" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Google Map URL (static):</h4></div>
              upto 2048 characters.<br>
			  <div>
              	<input maxlength="2048" name="googleMap" size="20" type="text" value="" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Facebook Like (iframe):</h4></div>
              upto 2048 characters.<br>
			  <div>
              	<input maxlength="2048" name="facebookLike" size="20" type="text" value="" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Description:</h4></div>
              upto 5000 characters.<br>
              <div>
              	<textarea name="description" cols=40 rows=6></textarea>
                <br />
              </div>
            
              <div style="float:left;width:50" class="post"><h4>Upload Market Image (JPG):</h4></div>
              Please be patient while the file uploads.<br>
              <div>
                  <input type="file" name="myFile" />
              </div>
              
            <div style="float:left;font-size:14px" class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                </div>
          
</g:form>
