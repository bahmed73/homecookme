

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show RetentionSummary</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">RetentionSummary List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New RetentionSummary</g:link></span>
        </div>
        <div class="body">
            <h1>Show RetentionSummary</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:retentionSummaryInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Create Time:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:retentionSummaryInstance, field:'createTime')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Registrations:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:retentionSummaryInstance, field:'totalRegistrations')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Logins:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:retentionSummaryInstance, field:'totalLogins')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Logins5orless:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:retentionSummaryInstance, field:'totalLogins5orless')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Logins6to10:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:retentionSummaryInstance, field:'totalLogins6to10')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Logins11to15:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:retentionSummaryInstance, field:'totalLogins11to15')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Logins16to20:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:retentionSummaryInstance, field:'totalLogins16to20')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Logins21to25:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:retentionSummaryInstance, field:'totalLogins21to25')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Logins26to30:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:retentionSummaryInstance, field:'totalLogins26to30')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${retentionSummaryInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
