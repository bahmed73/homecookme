

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit AdminAccess</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">AdminAccess List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New AdminAccess</g:link></span>
        </div>
        <div class="body">
            <h1>Edit AdminAccess</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${adminAccessInstance}">
            <div class="errors">
                <g:renderErrors bean="${adminAccessInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${adminAccessInstance?.id}" />
                <input type="hidden" name="version" value="${adminAccessInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime">Create Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:adminAccessInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${adminAccessInstance?.createTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status">Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:adminAccessInstance,field:'status','errors')}">
                                    <input type="text" id="status" name="status" value="${fieldValue(bean:adminAccessInstance,field:'status')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:adminAccessInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:adminAccessInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="updateTime">Update Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:adminAccessInstance,field:'updateTime','errors')}">
                                    <g:datePicker name="updateTime" value="${adminAccessInstance?.updateTime}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
