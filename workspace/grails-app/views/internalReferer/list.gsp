

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>InternalReferer List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New InternalReferer</g:link></span>
        </div>
        <div class="body">
            <h1>InternalReferer List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="url" title="Url" />
                        
                   	        <g:sortableColumn property="createTime" title="Create Time" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${internalRefererInstanceList}" status="i" var="internalRefererInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${internalRefererInstance.id}">${fieldValue(bean:internalRefererInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:internalRefererInstance, field:'url')}</td>
                        
                            <td>${fieldValue(bean:internalRefererInstance, field:'createTime')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${internalRefererInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
