<br>
<div style="float: right; width:200px;font-size:9px;">

<div style="float: left; width: 150px; font-size:9px;color:#5ea92b;">
<br class="clear"/>
<span style="font-size:18px;">All products: </span>
<br>
</div>

<g:each in="${productInstanceListAll}" status="i" var="productMarketInstance">
<div style="float:left;font-size:9px;width:200px;">
${productMarketInstance.name}
</div>
<br>
</g:each>

<br class="clear"/>
<div class="update-separator">&nbsp;</div>

</div>