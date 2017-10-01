

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>UsersViral List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/">Home</a></span>
            <span class="menuButton"><g:link url="[action:'myTweetMarks', controller:'myTweetMark']">myTweetMarks</g:link></span>
            <span class="menuButton"><g:link url="[action:'list', controller:'categories']">Categories</g:link></span>
            <span class="menuButton"><g:link url="[action:'list', controller:'country']">Country</g:link></span>
            <span class="menuButton"><g:link url="[action:'list', controller:'marketing']">Marketing</g:link></span>
            <span class="menuButton"><g:link url="[action:'list', controller:'myCategory']">MyCategory</g:link></span>
            <span class="menuButton"><g:link url="[action:'list', controller:'myTweetMark']">MyTweetMark</g:link></span>
            <span class="menuButton"><g:link url="[action:'list', controller:'users']">Users</g:link></span>
            <span class="menuButton"><g:link url="[action:'list', controller:'usersViral']">Viral</g:link></span>
            <span class="menuButton"><g:link url="[action:'list', controller:'adminAccess']">Access</g:link></span>
        </div>
        <div class="body">
            <h1>UsersViral List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="userId" title="User Id" />
                        
                   	        <g:sortableColumn property="createTime" title="Create Time" />
                        
                   	        <g:sortableColumn property="updateTime" title="Update Time" />
                        
                   	        <g:sortableColumn property="lastTimeAddressImported" title="Last Time Address Imported" />
                        
                   	        <g:sortableColumn property="totalAddressImported" title="Total Address Imported" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${usersViralInstanceList}" status="i" var="usersViralInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${usersViralInstance.id}">${fieldValue(bean:usersViralInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:usersViralInstance, field:'userId')}</td>
                        
                            <td>${fieldValue(bean:usersViralInstance, field:'createTime')}</td>
                        
                            <td>${fieldValue(bean:usersViralInstance, field:'updateTime')}</td>
                        
                            <td>${fieldValue(bean:usersViralInstance, field:'lastTimeAddressImported')}</td>
                        
                            <td>${fieldValue(bean:usersViralInstance, field:'totalAddressImported')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${usersViralInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
