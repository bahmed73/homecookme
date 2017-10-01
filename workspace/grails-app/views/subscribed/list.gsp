

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'subscribed.label', default: 'Subscribed')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'subscribed.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="userId" title="${message(code: 'subscribed.userId.label', default: 'User Id')}" />
                        
                            <g:sortableColumn property="subscribedId" title="${message(code: 'subscribed.subscribedId.label', default: 'Subscribed Id')}" />
                        
                            <g:sortableColumn property="status" title="${message(code: 'subscribed.status.label', default: 'Status')}" />
                        
                            <g:sortableColumn property="createTime" title="${message(code: 'subscribed.createTime.label', default: 'Create Time')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${subscribedInstanceList}" status="i" var="subscribedInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${subscribedInstance.id}">${fieldValue(bean: subscribedInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: subscribedInstance, field: "userId")}</td>
                        
                            <td>${fieldValue(bean: subscribedInstance, field: "subscribedId")}</td>
                        
                            <td>${fieldValue(bean: subscribedInstance, field: "status")}</td>
                        
                            <td><g:formatDate date="${subscribedInstance.createTime}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${subscribedInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
