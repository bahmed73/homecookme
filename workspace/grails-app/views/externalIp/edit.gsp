

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ExternalIp</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ExternalIp List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ExternalIp</g:link></span>
        </div>
        <div class="body">
            <h1>Edit ExternalIp</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${externalIpInstance}">
            <div class="errors">
                <g:renderErrors bean="${externalIpInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${externalIpInstance?.id}" />
                <input type="hidden" name="version" value="${externalIpInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ip">Ip:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:externalIpInstance,field:'ip','errors')}">
                                    <input type="text" id="ip" name="ip" value="${fieldValue(bean:externalIpInstance,field:'ip')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime">Create Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:externalIpInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${externalIpInstance?.createTime}" precision="minute" ></g:datePicker>
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
