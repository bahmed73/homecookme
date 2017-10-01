

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'counters.label', default: 'Counters')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'counters.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="userId" title="${message(code: 'counters.userId.label', default: 'User Id')}" />
                        
                            <g:sortableColumn property="subscribed" title="${message(code: 'counters.subscribed.label', default: 'Subscribed')}" />
                        
                            <g:sortableColumn property="subscriber" title="${message(code: 'counters.subscriber.label', default: 'Subscriber')}" />
                        
                            <g:sortableColumn property="createTime" title="${message(code: 'counters.createTime.label', default: 'Create Time')}" />
                        
                            <g:sortableColumn property="status" title="${message(code: 'counters.status.label', default: 'Status')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${countersInstanceList}" status="i" var="countersInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${countersInstance.id}">${fieldValue(bean: countersInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: countersInstance, field: "userId")}</td>
                        
                            <td>${fieldValue(bean: countersInstance, field: "subscribed")}</td>
                        
                            <td>${fieldValue(bean: countersInstance, field: "subscriber")}</td>
                        
                            <td><g:formatDate date="${countersInstance.createTime}" /></td>
                        
                            <td>${fieldValue(bean: countersInstance, field: "status")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${countersInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
