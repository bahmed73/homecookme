

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create RetentionSummary</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">RetentionSummary List</g:link></span>
        </div>
        <div class="body">
            <h1>Create RetentionSummary</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${retentionSummaryInstance}">
            <div class="errors">
                <g:renderErrors bean="${retentionSummaryInstance}" as="list" />
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
                                <td valign="top" class="value ${hasErrors(bean:retentionSummaryInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${retentionSummaryInstance?.createTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalRegistrations">Total Registrations:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:retentionSummaryInstance,field:'totalRegistrations','errors')}">
                                    <input type="text" id="totalRegistrations" name="totalRegistrations" value="${fieldValue(bean:retentionSummaryInstance,field:'totalRegistrations')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalLogins">Total Logins:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:retentionSummaryInstance,field:'totalLogins','errors')}">
                                    <input type="text" id="totalLogins" name="totalLogins" value="${fieldValue(bean:retentionSummaryInstance,field:'totalLogins')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalLogins5orless">Total Logins5orless:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:retentionSummaryInstance,field:'totalLogins5orless','errors')}">
                                    <input type="text" id="totalLogins5orless" name="totalLogins5orless" value="${fieldValue(bean:retentionSummaryInstance,field:'totalLogins5orless')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalLogins6to10">Total Logins6to10:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:retentionSummaryInstance,field:'totalLogins6to10','errors')}">
                                    <input type="text" id="totalLogins6to10" name="totalLogins6to10" value="${fieldValue(bean:retentionSummaryInstance,field:'totalLogins6to10')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalLogins11to15">Total Logins11to15:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:retentionSummaryInstance,field:'totalLogins11to15','errors')}">
                                    <input type="text" id="totalLogins11to15" name="totalLogins11to15" value="${fieldValue(bean:retentionSummaryInstance,field:'totalLogins11to15')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalLogins16to20">Total Logins16to20:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:retentionSummaryInstance,field:'totalLogins16to20','errors')}">
                                    <input type="text" id="totalLogins16to20" name="totalLogins16to20" value="${fieldValue(bean:retentionSummaryInstance,field:'totalLogins16to20')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalLogins21to25">Total Logins21to25:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:retentionSummaryInstance,field:'totalLogins21to25','errors')}">
                                    <input type="text" id="totalLogins21to25" name="totalLogins21to25" value="${fieldValue(bean:retentionSummaryInstance,field:'totalLogins21to25')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalLogins26to30">Total Logins26to30:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:retentionSummaryInstance,field:'totalLogins26to30','errors')}">
                                    <input type="text" id="totalLogins26to30" name="totalLogins26to30" value="${fieldValue(bean:retentionSummaryInstance,field:'totalLogins26to30')}" />
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
