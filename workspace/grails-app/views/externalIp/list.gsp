

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ExternalIp List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ExternalIp</g:link></span>
        </div>
        <div class="body">
            <h1>ExternalIp List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="ip" title="Ip" />
                        
                   	        <g:sortableColumn property="createTime" title="Create Time" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${externalIpInstanceList}" status="i" var="externalIpInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${externalIpInstance.id}">${fieldValue(bean:externalIpInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:externalIpInstance, field:'ip')}</td>
                        
                            <td>${fieldValue(bean:externalIpInstance, field:'createTime')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${externalIpInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
