

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>MyCategory List</title>
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
            <h1>MyCategory List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="userId" title="User Id" />
                        
                   	        <g:sortableColumn property="categoryId" title="Category Id" />
                        
                   	        <g:sortableColumn property="status" title="Status" />
                        
                   	        <g:sortableColumn property="createTime" title="Create Time" />
                        
                   	        <g:sortableColumn property="updateTime" title="Update Time" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${myCategoryInstanceList}" status="i" var="myCategoryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${myCategoryInstance.id}">${fieldValue(bean:myCategoryInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:myCategoryInstance, field:'userId')}</td>
                        
                            <td>${fieldValue(bean:myCategoryInstance, field:'categoryId')}</td>
                        
                            <td>${fieldValue(bean:myCategoryInstance, field:'status')}</td>
                        
                            <td>${fieldValue(bean:myCategoryInstance, field:'createTime')}</td>
                        
                            <td>${fieldValue(bean:myCategoryInstance, field:'updateTime')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
           <div class="paginateButtons">
                <g:paginate total="${myCategoryInstanceTotal}" />
            </div>  
        </div>
    </body>
</html>
