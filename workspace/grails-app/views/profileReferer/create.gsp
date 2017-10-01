

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ProfileReferer</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProfileReferer List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ProfileReferer</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${profileRefererInstance}">
            <div class="errors">
                <g:renderErrors bean="${profileRefererInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="url">Url:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:profileRefererInstance,field:'url','errors')}">
                                    <input type="text" id="url" name="url" value="${fieldValue(bean:profileRefererInstance,field:'url')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="refererUrl">Referer Url:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:profileRefererInstance,field:'refererUrl','errors')}">
                                    <input type="text" id="refererUrl" name="refererUrl" value="${fieldValue(bean:profileRefererInstance,field:'refererUrl')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime">Create Time:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:profileRefererInstance,field:'createTime','errors')}">
                                    <g:datePicker name="createTime" value="${profileRefererInstance?.createTime}" precision="minute" ></g:datePicker>
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
