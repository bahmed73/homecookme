

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'marketUser.label', default: 'MarketUser')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${marketUserInstance}">
            <div class="errors">
                <g:renderErrors bean="${marketUserInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userId"><g:message code="marketUser.userId.label" default="User Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: marketUserInstance, field: 'userId', 'errors')}">
                                    <g:textField name="userId" value="${fieldValue(bean: marketUserInstance, field: 'userId')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="marketId"><g:message code="marketUser.marketId.label" default="Market Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: marketUserInstance, field: 'marketId', 'errors')}">
                                    <g:textField name="marketId" value="${fieldValue(bean: marketUserInstance, field: 'marketId')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status"><g:message code="marketUser.status.label" default="Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: marketUserInstance, field: 'status', 'errors')}">
                                    <g:textField name="status" value="${fieldValue(bean: marketUserInstance, field: 'status')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime"><g:message code="marketUser.createTime.label" default="Create Time" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: marketUserInstance, field: 'createTime', 'errors')}">
                                    <g:datePicker name="createTime" precision="day" value="${marketUserInstance?.createTime}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
