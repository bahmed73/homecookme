<br>
<div style="float: right; width:200px;font-size:8px;">

<div style="float: left; width: 150px; font-size:10px;color:#5ea92b;">
<br class="clear"/>
<span style="font-size:18px;">All markets: </span>
<br>
</div>

<div style="float: left; width: 150px; font-size:8px;color:#5ea92b;">
Monday: 
</div>
<br>
<g:each in="${farmerMarketInstanceListAll}" status="i" var="farmerMarketInstMonday">

<g:if test="${farmerMarketInstMonday.timings.toLowerCase().contains('mon')}">
<div style="float:left;font-size:10px;width:200px;">
<g:link action="show" controller="farmerMarket" id="${farmerMarketInstMonday.id}">${farmerMarketInstMonday.title}</g:link>
</div>

<br>
</g:if>

</g:each>

<br class="clear"/>
<div class="update-separator">&nbsp;</div>

<div style="float: left; width: 150px; font-size:10px;color:#5ea92b;">
Tuesday: 
</div>
<br>

<g:each in="${farmerMarketInstanceListAll}" status="i" var="farmerMarketInstTuesday">

<g:if test="${farmerMarketInstTuesday.timings.toLowerCase().contains('tue')}">
<div style="float:left;font-size:10px;width:200px;">
<g:link action="show" controller="farmerMarket" id="${farmerMarketInstTuesday.id}">${farmerMarketInstTuesday.title}</g:link>
</div>

<br>
</g:if>

</g:each>

<br class="clear"/>
<div class="update-separator">&nbsp;</div>

<div style="float: left; width: 150px; font-size:10px;color:#5ea92b;">
Wednesday: 
</div>
<br>

<g:each in="${farmerMarketInstanceListAll}" status="i" var="farmerMarketInstWednesday">

<g:if test="${farmerMarketInstWednesday.timings.toLowerCase().contains('wed')}">
<div style="float:left;font-size:10px;width:200px;">
<g:link action="show" controller="farmerMarket" id="${farmerMarketInstWednesday.id}">${farmerMarketInstWednesday.title}</g:link>
</div>

<br>
</g:if>

</g:each>

<br class="clear"/>
<div class="update-separator">&nbsp;</div>

<div style="float: left; width: 150px; font-size:10px;color:#5ea92b;">
Thursday: 
</div>
<br>

<g:each in="${farmerMarketInstanceListAll}" status="i" var="farmerMarketInstThursday">

<g:if test="${farmerMarketInstThursday.timings.toLowerCase().contains('thu')}">
<div style="float:left;font-size:10px;width:200px;">
<g:link action="show" controller="farmerMarket" id="${farmerMarketInstThursday.id}">${farmerMarketInstThursday.title}</g:link>
</div>

<br>
</g:if>

</g:each>

<br class="clear"/>
<div class="update-separator">&nbsp;</div>

<div style="float: left; width: 150px; font-size:10px;color:#5ea92b;">
Friday: 
</div>
<br>

<g:each in="${farmerMarketInstanceListAll}" status="i" var="farmerMarketInstFriday">

<g:if test="${farmerMarketInstFriday.timings.toLowerCase().contains('fri')}">
<div style="float:left;font-size:10px;width:200px;">
<g:link action="show" controller="farmerMarket" id="${farmerMarketInstFriday.id}">${farmerMarketInstFriday.title}</g:link>
</div>

<br>
</g:if>

</g:each>

<br class="clear"/>
<div class="update-separator">&nbsp;</div>

<div style="float: left; width: 150px; font-size:10px;color:#5ea92b;">
Saturday: 
</div>
<br>

<g:each in="${farmerMarketInstanceListAll}" status="i" var="farmerMarketInstSaturday">

<g:if test="${farmerMarketInstSaturday.timings.toLowerCase().contains('sat')}">
<div style="float:left;font-size:10px;width:200px;">
<g:link action="show" controller="farmerMarket" id="${farmerMarketInstSaturday.id}">${farmerMarketInstSaturday.title}</g:link>
</div>

<br>
</g:if>

</g:each>

<br class="clear"/>
<div class="update-separator">&nbsp;</div>

<div style="float: left; width: 150px; font-size:10px;color:#5ea92b;">
Sunday: 
</div>
<br>

<g:each in="${farmerMarketInstanceListAll}" status="i" var="farmerMarketInstSunday">

<g:if test="${farmerMarketInstSunday.timings.toLowerCase().contains('sun')}">
<div style="float:left;font-size:10px;width:200px;">
<g:link action="show" controller="farmerMarket" id="${farmerMarketInstSunday.id}">${farmerMarketInstSunday.title}</g:link>
</div>

<br>
</g:if>

</g:each>

<br class="clear"/>
<div class="update-separator-grey">&nbsp;</div>
</div>