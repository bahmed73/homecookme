

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tweetUrl.label', default: 'TweetUrl')}" />
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
            <g:hasErrors bean="${tweetUrlInstance}">
            <div class="errors">
                <g:renderErrors bean="${tweetUrlInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userId"><g:message code="tweetUrl.userId.label" default="User Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetUrlInstance, field: 'userId', 'errors')}">
                                    <g:textField name="userId" value="${fieldValue(bean: tweetUrlInstance, field: 'userId')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="statusId"><g:message code="tweetUrl.statusId.label" default="Status Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetUrlInstance, field: 'statusId', 'errors')}">
                                    <g:textField name="statusId" value="${fieldValue(bean: tweetUrlInstance, field: 'statusId')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="url"><g:message code="tweetUrl.url.label" default="Url" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetUrlInstance, field: 'url', 'errors')}">
                                    <g:textField name="url" maxlength="140" value="${tweetUrlInstance?.url}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status"><g:message code="tweetUrl.status.label" default="Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetUrlInstance, field: 'status', 'errors')}">
                                    <g:textField name="status" value="${fieldValue(bean: tweetUrlInstance, field: 'status')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime"><g:message code="tweetUrl.createTime.label" default="Create Time" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetUrlInstance, field: 'createTime', 'errors')}">
                                    <g:datePicker name="createTime" precision="day" value="${tweetUrlInstance?.createTime}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tweet"><g:message code="tweetUrl.tweet.label" default="Tweet" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetUrlInstance, field: 'tweet', 'errors')}">
                                    <g:textField name="tweet" maxlength="140" value="${tweetUrlInstance?.tweet}" />
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
