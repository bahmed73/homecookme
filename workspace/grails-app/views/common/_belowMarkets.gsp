<div class="rounded" style="float: left; width:700px;font-size:14px;border:3px solid #ccc;background-color:#000000;">

<div style="float: left; width: 150px; color:#5ea92b;">
<br class="clear"/>
<g:link url="[action:'list',controller:'farmerMarket']"><img border="0" src="${createLinkTo(dir:'images', file:'markets-new.png')}" title="Markets"></g:link>
<br>
<br>
</div>

<g:each in="${farmerMarketInstanceList}" status="i" var="farmerMarketInstance">
<g:def var="market" value="${farmerMarketInstance.market}"/>
<div style="float:left;font-size:20px;">
${market.title}
</div>

<div style="float:left;color:#7CBA53;width:30px;">
&nbsp;
</div>
</g:each>

<br class="clear"/>

<img src="${marketMap}">
</div>