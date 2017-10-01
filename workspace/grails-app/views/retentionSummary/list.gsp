

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>RetentionSummary List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/">Home</a></span>
            <span class="menuButton"><g:link url="[action:'myTweetMarks', controller:'myTweetMark']">myTweetMarks</g:link></span>
            <span class="menuButton"><g:link url="[action:'list', controller:'viralSummary']">Viral Summary</g:link></span>
            
        </div>
        <div>
        <h1>Retention Co-efficient, Login and Registration Comparison</h1>
        <img src="http://chart.apis.google.com/chart?cht=lc&chd=t:40,60,60,45,47,75,70,72&chs=250x100" title="Retention co-efficient"/>
        <img src="http://chart.apis.google.com/chart?cht=bhs&chco=4D89F9,C6D9FD&chd=t:10,50,60,80,40|50,60,100,40,20&chs=250x100" title="Registration Comparison"/>
        <img src="http://chart.apis.google.com/chart?cht=p3&chd=t:50,20,60,30,5&chs=250x100&chl=<1 login|<5 logins|<10 logins|<20 logins|other" title="Login Comparison"/>
        
        <br> 
        </div>
        <div class="body">
            <h1>RetentionSummary List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="createTime" title="Create Time" />
                        
                   	        <g:sortableColumn property="totalRegistrations" title="Total Registrations" />
                        
                   	        <g:sortableColumn property="totalLogins" title="Total Logins" />
                        
                   	        <g:sortableColumn property="totalLogins5orless" title="Total Logins5orless" />
                        
                   	        <g:sortableColumn property="totalLogins6to10" title="Total Logins6to10" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${retentionSummaryInstanceList}" status="i" var="retentionSummaryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${retentionSummaryInstance.id}">${fieldValue(bean:retentionSummaryInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:retentionSummaryInstance, field:'createTime')}</td>
                        
                            <td>${fieldValue(bean:retentionSummaryInstance, field:'totalRegistrations')}</td>
                        
                            <td>${fieldValue(bean:retentionSummaryInstance, field:'totalLogins')}</td>
                        
                            <td>${fieldValue(bean:retentionSummaryInstance, field:'totalLogins5orless')}</td>
                        
                            <td>${fieldValue(bean:retentionSummaryInstance, field:'totalLogins6to10')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${retentionSummaryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
