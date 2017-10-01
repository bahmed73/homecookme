

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Marketing List</title>
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
            <h1>Marketing List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="status" title="Status" />
                        
                   	        <g:sortableColumn property="createTime" title="Create Time" />
                        
                   	        <g:sortableColumn property="marketingType" title="Marketing Type" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${marketingInstanceList}" status="i" var="marketingInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${marketingInstance.id}">${fieldValue(bean:marketingInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:marketingInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:marketingInstance, field:'status')}</td>
                        
                            <td>${fieldValue(bean:marketingInstance, field:'createTime')}</td>
                        
                            <td>${fieldValue(bean:marketingInstance, field:'marketingType')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Marketing.count()}" />
            </div>
        </div>
    </body>
</html>
