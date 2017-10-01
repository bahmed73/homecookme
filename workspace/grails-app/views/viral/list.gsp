

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Viral List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Viral</g:link></span>
        </div>
        <div class="body">
            <h1>Viral List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="createTime" title="Create Time" />
                        
                   	        <g:sortableColumn property="updateTime" title="Update Time" />
                        
                   	        <g:sortableColumn property="lastTimeAddressImported" title="Last Time Address Imported" />
                        
                   	        <g:sortableColumn property="totalAddressImported" title="Total Address Imported" />
                        
                   	        <g:sortableColumn property="lastInvitesSent" title="Last Invites Sent" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${viralInstanceList}" status="i" var="viralInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${viralInstance.id}">${fieldValue(bean:viralInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:viralInstance, field:'createTime')}</td>
                        
                            <td>${fieldValue(bean:viralInstance, field:'updateTime')}</td>
                        
                            <td>${fieldValue(bean:viralInstance, field:'lastTimeAddressImported')}</td>
                        
                            <td>${fieldValue(bean:viralInstance, field:'totalAddressImported')}</td>
                        
                            <td>${fieldValue(bean:viralInstance, field:'lastInvitesSent')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Viral.count()}" />
            </div>
        </div>
    </body>
</html>
