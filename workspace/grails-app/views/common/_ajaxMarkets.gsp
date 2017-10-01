<g:javascript library="prototype" />
<g:javascript>
function updateDay(e) {
	// The response comes back as a bunch-o-JSON
	var titles = eval("(" + e.responseText + ")")	// evaluate JSON
	var myDiv = document.getElementById('myDiv')
	myDiv.innerHTML=""
			
	if (titles) {
		
		// Rebuild the select
		for (var i=0; i < titles.length; i++) {
			var title = titles[i]
			
			myDiv.innerHTML+="<div style='float:left;width:150px;font-size:14px;margin:5px;'><a href='/farmerMarket/show/" + title.id + "' title='Click to view local businesses within the farmers market.'>" + title.title+"</a></div><br>"
			
		}
	}
}


// This is called when the page loads to initialize city
${remoteFunction(controller:"farmerMarket", action:"ajaxGetMarkets", params:"'day=Mon'", onComplete:"updateDay(e)")}

</g:javascript>
<div class="rounded" style="float: right; width:280px;font-size:18px;border:3px solid #ccc;">

<div style="float: left; width: 150px;color:#5ea92b;">
<br class="clear"/>
<span style="font-size:18px;">Markets by day: </span>
<br>
</div>

<div style="float: left; width: 150px;margin:5px;">
<select name="title" id="title" onchange="${remoteFunction(controller:'farmerMarket',action:'ajaxGetMarkets',params:'\'day=\' + escape(this.value)', 
            onComplete:'updateDay(e)')}">
<option>Mon</option>
<option>Tue</option>
<option>Wed</option>
<option>Thu</option>
<option>Fri</option>
<option>Sat</option>
<option>Sun</option>
</select>
</div>
<div id="myDiv">
</div>

<br class="clear"/>


</div>