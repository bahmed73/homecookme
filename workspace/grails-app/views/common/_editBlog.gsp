<div>
			<h2>Edit Blog</h2>
			</div>
			<br class="clear"/> 
			<div class="update-separator">&nbsp;</div>
			
<g:form method="post" enctype="multipart/form-data">
<div>
			  <input type="hidden" name="id" value="${blogInstance?.id}" />
			  <input type="hidden" name="createTime" value="${blogInstance?.createTime}" />
              <div style="float:left;width:50" class="post"><h4>Title:</h4></div>
              upto 100 characters.<br>
			  <div>
              	<input maxlength="100" name="title" size="20" type="text" value="${blogInstance?.title}" />
                <br />
              </div>
            

              <div style="float:left;width:50" class="post"><h4>Description:</h4></div>
              upto 5000 characters.<br>
              <div>
              <g:textArea name="description" value="${blogInstance?.description}" rows="6" cols="40"/>
                <br />
              </div>
            
              
            
            <div style="float:left;font-size:14px" class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                </div>
          
</g:form>
