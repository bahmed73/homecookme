            <br class="clear"/>
			<div class="update-separator">&nbsp;</div>
			
			<div style="float:left;width:700px; height:30px; background:#c5e2f8; border:1px solid #ddffcc;">
			<h1>Current Important Trackings: </h1>
			</div>
            <g:link url="[action:'list', controller:'users']">Users</g:link><br>
            <g:link url="[action:'list', controller:'internalReferer']">Internal Referer</g:link><br>
            <g:link url="[action:'list', controller:'externalIp']">External Ip</g:link><br>
            
            <div style="float:left;width:700px; height:30px; background:#c5e2f8; border:1px solid #ddffcc;">
            <h1>Administrator Functions: </h1>
            </div>
            <g:link url="[action:'list', controller:'categories']">Categories</g:link><br>
            <g:link url="[action:'list', controller:'country']">Country</g:link><br>
            <g:link url="[action:'list', controller:'marketing']">Marketing</g:link><br>
            <g:link url="[action:'list', controller:'myCategory']">My Category</g:link><br>
            <g:link url="[action:'list', controller:'myTweetMark']">My Tweet Marks</g:link><br>
            <g:link url="[action:'list', controller:'usersViral']">Users Viral</g:link><br>
            <g:link url="[action:'list', controller:'adminAccess']">Admin Access</g:link><br>
            
            <div style="float:left;width:700px; height:30px; background:#c5e2f8; border:1px solid #ddffcc;">
            <h1>Dashboards: </h1>
            </div>
            <g:link url="[action:'list', controller:'viralSummary']">Viral Summary</g:link><br>
            <g:link url="[action:'list', controller:'retentionSummary']">Retention Summary</g:link><br>
            <g:link url="[action:'list', controller:'featureToggle']">Feature Toggle</g:link><br>
            
            <div style="float:left;width:700px; height:30px; background:#c5e2f8; border:1px solid #ddffcc;">
            <h1>Administrator Toggles: </h1>
            </div>
            <div style="align:left:width:50">
            <g:form action="disableUser" controller="users" method="post" >
    				
    				<div>
    				Disable User: 
    				</div>
    				<div>
    				<input type="text" id="disableUser" name="userName" value=""/>
    				</div>
    				<br />
    				<div style="align:left;width:50" class="buttons">
                    <span class="button"><input class="save" type="submit" value="Disable User" /></span>
                </div>
    				</g:form>
            </div>
            <br class="clear"/>
            <div style="align:left;width:50">
            <g:form action="activeUser" controller="users" method="post" >
    				
    				<div>
    				Active User: 
    				</div>
    				<div>
    				<input type="text" id="activeUser" name="userName" value=""/>
    				</div>
    				<br />
    				<div style="align:left;width:50" class="buttons">
                    <span class="button"><input class="save" type="submit" value="Active User" /></span>
                </div>
    				</g:form>
            </div>