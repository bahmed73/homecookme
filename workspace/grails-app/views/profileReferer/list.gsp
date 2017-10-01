

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Traffic Referrer</title>
        
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["piechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = new google.visualization.DataTable();
        
        data.addColumn('string', 'Referrer');
        data.addColumn('number', 'Amount(Count)');
        data.addRows(${profileRefererInstanceTotal});
        
        <g:each in="${profileRefererMap}" status="i" var="profileRefererMapInstance">
        data.setValue(${i}, 0, '${profileRefererMapInstance.key}');
        data.setValue(${i}, 1, ${profileRefererMapInstance.value});
        </g:each>

        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, {width: 400, height: 240, is3D: true, title: 'Referrer Distribution Chart'});
      }
    </script>
        
        
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link controller="myTweetMark" action="myTweetMarks">Profile</g:link></span>
        </div>
        <br class="clear"/> 
		
        <div class="body">
			<div style="float:left;width:700px; height:60px; background:#c5e2f8; border:1px solid #ddffcc;">
            <h1>Your Referrers</h1>
			<div class="comment">
				(Referrer is where your users are clicking on mytweetmark links from)
			</div>

		</div>
		<br class="clear"/> 
		<br class="clear"/> 
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div id="chart_div"></div>
        	
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="refererUrl" title="Referrer" />
                        
                   	        <g:sortableColumn property="createTime" title="Time" />
                        
                        </tr>
<br class="clear"/> 
                    </thead>
                    <tbody>
                    <g:each in="${profileRefererInstanceList}" status="i" var="profileRefererInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${fieldValue(bean:profileRefererInstance, field:'refererUrl')}</td>
                        
                            <td>${fieldValue(bean:profileRefererInstance, field:'createTime')}</td>
                        
                        </tr>
<br class="clear"/> 
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${profileRefererInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
