

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Users List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton">><g:link class="home" url="/">Home</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New</g:link></span>
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
            <h1>Users List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="userName" title="User Name" />
                        
                   	        <g:sortableColumn property="email" title="Email" />
                        
                        	<g:sortableColumn property="createTime" title="Create Time" />
                        	
                   	        <g:sortableColumn property="status" title="Status" />
                        
                   	        <g:sortableColumn property="marketingId" title="Marketing Id" />
                   	        
                   	        <g:sortableColumn property="lastLogin" title="Last Login" />
                        
                   	        <g:sortableColumn property="loginTimes" title="Login Times" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${usersInstanceList}" status="i" var="usersInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${usersInstance.id}">${fieldValue(bean:usersInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:usersInstance, field:'userName')}</td>
                        
                            <td>${fieldValue(bean:usersInstance, field:'email')}</td>
                        
                        	<td>${fieldValue(bean:usersInstance, field:'createTime')}</td>
                        
                            <td>${fieldValue(bean:usersInstance, field:'status')}</td>
                        
                            <td>${fieldValue(bean:usersInstance, field:'marketingId')}</td>
                        
                        	<td>${fieldValue(bean:usersInstance, field:'lastLogin')}</td>
                        
                            <td>${fieldValue(bean:usersInstance, field:'loginTimes')}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Users.count()}" />
            </div>
        </div>
    </body>
</html>
