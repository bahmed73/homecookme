

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>FeatureToggle List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New FeatureToggle</g:link></span>
        </div>
        <div class="body">
            <h1>FeatureToggle List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="createTime" title="Create Time" />
                        
                   	        <g:sortableColumn property="status" title="Status" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${featureToggleInstanceList}" status="i" var="featureToggleInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${featureToggleInstance.id}">${fieldValue(bean:featureToggleInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:featureToggleInstance, field:'createTime')}</td>
                        
                            <td>${fieldValue(bean:featureToggleInstance, field:'status')}</td>
                        
                            <td>${fieldValue(bean:featureToggleInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${featureToggleInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
