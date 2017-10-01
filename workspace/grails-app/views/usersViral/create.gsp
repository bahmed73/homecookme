

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create UsersViral</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">UsersViral List</g:link></span>
        </div>
        <div class="body">
            <h1>Create UsersViral</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${usersViralInstance}">
            <div class="errors">
                <g:renderErrors bean="${usersViralInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userId">User Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:usersViralInstance,field:'userId','errors')}">
                                    <input type="text" id="userId" name="userId" value="${fieldValue(bean:usersViralInstance,field:'userId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime">Create Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:usersViralInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${usersViralInstance?.createTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="updateTime">Update Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:usersViralInstance,field:'updateTime','errors')}">
                                    <g:datePicker name="updateTime" value="${usersViralInstance?.updateTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastTimeAddressImported">Last Time Address Imported:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:usersViralInstance,field:'lastTimeAddressImported','errors')}">
                                    <input type="text" id="lastTimeAddressImported" name="lastTimeAddressImported" value="${fieldValue(bean:usersViralInstance,field:'lastTimeAddressImported')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalAddressImported">Total Address Imported:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:usersViralInstance,field:'totalAddressImported','errors')}">
                                    <input type="text" id="totalAddressImported" name="totalAddressImported" value="${fieldValue(bean:usersViralInstance,field:'totalAddressImported')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastInvitesSent">Last Invites Sent:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:usersViralInstance,field:'lastInvitesSent','errors')}">
                                    <input type="text" id="lastInvitesSent" name="lastInvitesSent" value="${fieldValue(bean:usersViralInstance,field:'lastInvitesSent')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalInvitesSent">Total Invites Sent:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:usersViralInstance,field:'totalInvitesSent','errors')}">
                                    <input type="text" id="totalInvitesSent" name="totalInvitesSent" value="${fieldValue(bean:usersViralInstance,field:'totalInvitesSent')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalInvitesOpened">Total Invites Opened:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:usersViralInstance,field:'totalInvitesOpened','errors')}">
                                    <input type="text" id="totalInvitesOpened" name="totalInvitesOpened" value="${fieldValue(bean:usersViralInstance,field:'totalInvitesOpened')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalInvitesClicked">Total Invites Clicked:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:usersViralInstance,field:'totalInvitesClicked','errors')}">
                                    <input type="text" id="totalInvitesClicked" name="totalInvitesClicked" value="${fieldValue(bean:usersViralInstance,field:'totalInvitesClicked')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalInvitesAccepted">Total Invites Accepted:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:usersViralInstance,field:'totalInvitesAccepted','errors')}">
                                    <input type="text" id="totalInvitesAccepted" name="totalInvitesAccepted" value="${fieldValue(bean:usersViralInstance,field:'totalInvitesAccepted')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
