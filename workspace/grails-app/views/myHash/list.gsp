

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>MyHash List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New MyHash</g:link></span>
        </div>
        <div class="body">
            <h1>MyHash List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="userId" title="User Id" />
                        
                   	        <g:sortableColumn property="status" title="Status" />
                        
                   	        <g:sortableColumn property="createTime" title="Create Time" />
                        
                   	        <g:sortableColumn property="hash" title="Hash" />
                        
                   	        <g:sortableColumn property="updateTime" title="Update Time" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${myHashInstanceList}" status="i" var="myHashInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${myHashInstance.id}">${fieldValue(bean:myHashInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:myHashInstance, field:'userId')}</td>
                        
                            <td>${fieldValue(bean:myHashInstance, field:'status')}</td>
                        
                            <td>${fieldValue(bean:myHashInstance, field:'createTime')}</td>
                        
                            <td>${fieldValue(bean:myHashInstance, field:'hash')}</td>
                        
                            <td>${fieldValue(bean:myHashInstance, field:'updateTime')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${myHashInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
