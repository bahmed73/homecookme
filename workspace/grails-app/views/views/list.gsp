

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Views List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Views</g:link></span>
        </div>
        <div class="body">
            <h1>Views List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="num" title="Num" />
                        
                   	        <g:sortableColumn property="createTime" title="Create Time" />
                        
                   	        <g:sortableColumn property="userId" title="User Id" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${viewsInstanceList}" status="i" var="viewsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${viewsInstance.id}">${fieldValue(bean:viewsInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:viewsInstance, field:'num')}</td>
                        
                            <td>${fieldValue(bean:viewsInstance, field:'createTime')}</td>
                        
                            <td>${fieldValue(bean:viewsInstance, field:'userId')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${viewsInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
