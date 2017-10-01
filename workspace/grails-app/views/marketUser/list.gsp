

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'marketUser.label', default: 'MarketUser')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'marketUser.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="userId" title="${message(code: 'marketUser.userId.label', default: 'User Id')}" />
                        
                            <g:sortableColumn property="marketId" title="${message(code: 'marketUser.marketId.label', default: 'Market Id')}" />
                        
                            <g:sortableColumn property="status" title="${message(code: 'marketUser.status.label', default: 'Status')}" />
                        
                            <g:sortableColumn property="createTime" title="${message(code: 'marketUser.createTime.label', default: 'Create Time')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${marketUserInstanceList}" status="i" var="marketUserInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${marketUserInstance.id}">${fieldValue(bean: marketUserInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: marketUserInstance, field: "userId")}</td>
                        
                            <td>${fieldValue(bean: marketUserInstance, field: "marketId")}</td>
                        
                            <td>${fieldValue(bean: marketUserInstance, field: "status")}</td>
                        
                            <td><g:formatDate date="${marketUserInstance.createTime}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${marketUserInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
