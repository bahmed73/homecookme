

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create Viral</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Viral List</g:link></span>
        </div>
        <div class="body">
            <h1>Create Viral</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${viralInstance}">
            <div class="errors">
                <g:renderErrors bean="${viralInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime">Create Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${viralInstance?.createTime}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="updateTime">Update Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralInstance,field:'updateTime','errors')}">
                                    <g:datePicker name="updateTime" value="${viralInstance?.updateTime}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastTimeAddressImported">Last Time Address Imported:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralInstance,field:'lastTimeAddressImported','errors')}">
                                    <input type="text" id="lastTimeAddressImported" name="lastTimeAddressImported" value="${fieldValue(bean:viralInstance,field:'lastTimeAddressImported')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalAddressImported">Total Address Imported:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralInstance,field:'totalAddressImported','errors')}">
                                    <input type="text" id="totalAddressImported" name="totalAddressImported" value="${fieldValue(bean:viralInstance,field:'totalAddressImported')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastInvitesSent">Last Invites Sent:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralInstance,field:'lastInvitesSent','errors')}">
                                    <input type="text" id="lastInvitesSent" name="lastInvitesSent" value="${fieldValue(bean:viralInstance,field:'lastInvitesSent')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalInvitesSent">Total Invites Sent:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralInstance,field:'totalInvitesSent','errors')}">
                                    <input type="text" id="totalInvitesSent" name="totalInvitesSent" value="${fieldValue(bean:viralInstance,field:'totalInvitesSent')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalInvitesOpened">Total Invites Opened:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralInstance,field:'totalInvitesOpened','errors')}">
                                    <input type="text" id="totalInvitesOpened" name="totalInvitesOpened" value="${fieldValue(bean:viralInstance,field:'totalInvitesOpened')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalInvitesClicked">Total Invites Clicked:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralInstance,field:'totalInvitesClicked','errors')}">
                                    <input type="text" id="totalInvitesClicked" name="totalInvitesClicked" value="${fieldValue(bean:viralInstance,field:'totalInvitesClicked')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalInvitesAccepted">Total Invites Accepted:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viralInstance,field:'totalInvitesAccepted','errors')}">
                                    <input type="text" id="totalInvitesAccepted" name="totalInvitesAccepted" value="${fieldValue(bean:viralInstance,field:'totalInvitesAccepted')}" />
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
