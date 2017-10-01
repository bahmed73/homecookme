

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Viral</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Viral List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Viral</g:link></span>
        </div>
        <div class="body">
            <h1>Show Viral</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Create Time:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralInstance, field:'createTime')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Update Time:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralInstance, field:'updateTime')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Last Time Address Imported:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralInstance, field:'lastTimeAddressImported')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Address Imported:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralInstance, field:'totalAddressImported')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Last Invites Sent:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralInstance, field:'lastInvitesSent')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Invites Sent:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralInstance, field:'totalInvitesSent')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Invites Opened:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralInstance, field:'totalInvitesOpened')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Invites Clicked:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralInstance, field:'totalInvitesClicked')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Invites Accepted:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viralInstance, field:'totalInvitesAccepted')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${viralInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
