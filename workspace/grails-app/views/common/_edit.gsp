<g:hasErrors bean="${usersInstance}">
            <div class="errors">
                <g:renderErrors bean="${usersInstance}" as="list" />
            </div>
            </g:hasErrors>

<div>
			<h2>Settings</h2>
			Tell us something about yourself.  
			</div>
			<br class="clear"/> 
			<div class="update-separator">&nbsp;</div>
			
<g:form method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${usersInstance?.id}" />
<div>
<div style="float:left;width:50" class="post"><h4>Profile Color:</h4></div>

<div style="float:left;width:30px;height:30px; background:#FFEFEF; border:1px solid #c5e2f8;color:#7cba53">
&nbsp;
</div>
<div style="float:left;width:40px;font-size:10px">
<g:radio name="color" value="FFEFEF"/><br>Red
</div>

<div style="float:left;width:30px;height:30px; background:#FFF0DF; border:1px solid #c5e2f8;color:#7cba53">
&nbsp;
</div>
<div style="float:left;width:40px;font-size:10px">
<g:radio name="color" value="FFF0DF"/><br>Orange
</div>

<div style="float:left;width:30px;height:30px; background:#FFFFDF; border:1px solid #c5e2f8;color:#7cba53">
&nbsp;
</div>
<div style="float:left;width:40px;font-size:10px">
<g:radio name="color" value="FFFFDF"/><br>Yellow
</div>

<div style="float:left;width:30px;height:30px; background:#EFFFF1; border:1px solid #c5e2f8;color:#7cba53">
&nbsp;
</div>
<div style="float:left;width:40px;font-size:10px">
<g:radio name="color" value="EFFFF1"/><br>Green
</div>

<div style="float:left;width:30px;height:30px; background:#EFEFEF; border:1px solid #c5e2f8;color:#7cba53">
&nbsp;
</div>
<div style="float:left;width:40px;font-size:10px">
<g:radio name="color" value="EFEFEF"/><br>Blue
</div>

<div style="float:left;width:30px;height:30px; background:#C2CAEF; border:1px solid #c5e2f8;color:#7cba53">
&nbsp;
</div>
<div style="float:left;width:40px;font-size:10px">
<g:radio name="color" value="C2CAEF"/><br>Big Blue
</div>

<div style="float:left;width:30px;height:30px; background:#E4C2EF; border:1px solid #c5e2f8;color:#7cba53">
&nbsp;
</div>
<div style="float:left;width:40px;font-size:10px">
<g:radio name="color" value="E4C2EF"/><br>Purple<br>
</div>
<br class="clear"/> 
<div class="update-separator">&nbsp;</div>

              <div style="float:left;width:50" class="post"><h4>First Name:</h4></div>
              upto 50 characters.<br>
			  <div>
              	<input maxlength="50" name="firstName" size="20" type="text" value="${fieldValue(bean:usersInstance,field:'firstName')}" />
                <br />
              </div>
            

              <div style="float:left;width:50" class="post"><h4>Last Name:</h4></div>
              upto 50 characters.<br>
              <div>
              	<input maxlength="50" name="lastName" size="20" type="text" value="${fieldValue(bean:usersInstance,field:'lastName')}" />
                <br />
              </div>
            
              <div style="float:left;width:50" class="post"><h4>Business Name:</h4></div>
              upto 50 characters.<br>
              <div>
              	<input maxlength="50" name="businessName" size="20" type="text" value="${fieldValue(bean:usersInstance,field:'businessName')}" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Address:</h4></div>
              upto 100 characters.<br>
              <div>
              	<input maxlength="100" name="businessAddress" size="20" type="text" value="${fieldValue(bean:usersInstance,field:'businessAddress')}" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Phone:</h4></div>
              upto 50 characters.<br>
              <div>
              	<input maxlength="50" name="businessPhone" size="20" type="text" value="${fieldValue(bean:usersInstance,field:'businessPhone')}" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Password:</h4></div>
              upto 100 characters.<br>
              <div>
              	<input maxlength="100" name="password" size="20" type="password" value="${fieldValue(bean:usersInstance,field:'password')}" />
                <br />
              </div>
           

              <div style="float:left;width:50" class="post"><h4>Email:</h4></div>
              upto 100 characters.<br>
              <div>
              	<input maxlength="100" name="email" size="20" type="text" value="${fieldValue(bean:usersInstance,field:'email')}" />
                <br />
              </div>
            
              <div style="float:left;width:50" class="post"><h4>Website:</h4></div>
              upto 2048 characters, link.<br>
              <div>
              	<input maxlength="2048" name="website" size="20" type="text" value="${fieldValue(bean:usersInstance,field:'website')}" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>Blog:</h4></div>
              upto 2048 characters, link.<br>
              <div>
              	<input maxlength="2048" name="blog" size="20" type="text" value="${fieldValue(bean:usersInstance,field:'blog')}" />
                <br />
              </div>
              
              <div style="float:left;width:50" class="post"><h4>About Me:</h4></div>
              upto 150 characters.<br>
              <div>
              <input id="eBann" name="aboutMe" value="${usersInstance?.aboutMe}" size="20" maxlength="150" onKeyUp="toCount('eBann','sBann','{CHAR} characters left',150);"/>
              
    			<br>
    			<span id="sBann" class="minitext">150 characters left.</span>	
              	
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
