

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ViralSummary List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="/">Home</a></span>
            <span class="menuButton"><g:link url="[action:'myTweetMarks', controller:'myTweetMark']">myTweetMarks</g:link></span>
            <span class="menuButton"><g:link url="[action:'list', controller:'retentionSummary']">Retention Summary</g:link></span>
        </div>
        <div>
        <h1>Viral Factor, by Country and Email Domain Distributions</h1>
        <img src="http://chart.apis.google.com/chart?cht=p3&chd=t:60,40,20,30,5&chs=250x100&chl=US|UK|AUS|CAN|MEX" title="Major Country Distributions"/>
        <img src="http://chart.apis.google.com/chart?cht=lc&chd=t:40,60,60,45,47,75,70,72&chs=250x100" title="Viral Factor"/>
        <img src="http://chart.apis.google.com/chart?cht=p3&chd=t:50,20,60,30,5&chs=250x100&chl=yahoo|AOL|hotmail|gmail|AIM" title="Major Email Domain Distributions"/>
        <br> 
        <h1>Age, Language and Gender Distributions</h1>
        <img src="http://chart.apis.google.com/chart?cht=p3&chd=t:50,30,50,40,30&chs=250x100&chl=<15|<20|<25|<30|others" title="Age Distributions"/>
        <img src="http://chart.apis.google.com/chart?cht=p3&chd=t:50,20,60,30,5&chs=250x100&chl=english|french|german|spanish|italian" title="Language Distributions"/>
        <img src="http://chart.apis.google.com/chart?cht=p3&chd=t:55,45&chs=250x100&chl=male|female" title="Gender Distributions"/>
        <br> 
        </div>
        <div class="body">
            <h1>ViralSummary List</h1>
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
                        
                   	        <g:sortableColumn property="totalAddressBookScrapedSize" title="Total Address Book Scraped Size" />
                        
                   	        <g:sortableColumn property="totalSendSize" title="Total Send Size" />
                        
                   	        <g:sortableColumn property="totalOpened" title="Total Opened" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${viralSummaryInstanceList}" status="i" var="viralSummaryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${viralSummaryInstance.id}">${fieldValue(bean:viralSummaryInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:viralSummaryInstance, field:'createTime')}</td>
                        
                            <td>${fieldValue(bean:viralSummaryInstance, field:'totalRegistrations')}</td>
                        
                            <td>${fieldValue(bean:viralSummaryInstance, field:'totalAddressBookScrapedSize')}</td>
                        
                            <td>${fieldValue(bean:viralSummaryInstance, field:'totalSendSize')}</td>
                        
                            <td>${fieldValue(bean:viralSummaryInstance, field:'totalOpened')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${viralSummaryInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
