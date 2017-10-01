

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tweetFriend.label', default: 'TweetFriend')}" />
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
            <g:hasErrors bean="${tweetFriendInstance}">
            <div class="errors">
                <g:renderErrors bean="${tweetFriendInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userId"><g:message code="tweetFriend.userId.label" default="User Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetFriendInstance, field: 'userId', 'errors')}">
                                    <g:textField name="userId" value="${fieldValue(bean: tweetFriendInstance, field: 'userId')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="statusId"><g:message code="tweetFriend.statusId.label" default="Status Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetFriendInstance, field: 'statusId', 'errors')}">
                                    <g:textField name="statusId" value="${fieldValue(bean: tweetFriendInstance, field: 'statusId')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="friendName"><g:message code="tweetFriend.friendName.label" default="Friend Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetFriendInstance, field: 'friendName', 'errors')}">
                                    <g:textField name="friendName" maxlength="100" value="${tweetFriendInstance?.friendName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status"><g:message code="tweetFriend.status.label" default="Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetFriendInstance, field: 'status', 'errors')}">
                                    <g:textField name="status" value="${fieldValue(bean: tweetFriendInstance, field: 'status')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="createTime"><g:message code="tweetFriend.createTime.label" default="Create Time" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetFriendInstance, field: 'createTime', 'errors')}">
                                    <g:datePicker name="createTime" precision="day" value="${tweetFriendInstance?.createTime}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tweet"><g:message code="tweetFriend.tweet.label" default="Tweet" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tweetFriendInstance, field: 'tweet', 'errors')}">
                                    <g:textField name="tweet" maxlength="140" value="${tweetFriendInstance?.tweet}" />
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
