<div class="rounded" style="float: left; width:700px;border:3px solid #ccc;background-color:#000000;">

<div style="float: left; width: 150px; font-size:14px;color:#5ea92b;">
<br class="clear"/>
<g:link url="[action:'list',controller:'product']"><img border="0" src="${createLinkTo(dir:'images', file:'products-new.png')}" title="Products"></g:link>
<br>
<br>
</div>

<g:each in="${productInstanceList}" status="i" var="productMarketInstance">
<g:def var="product" value="${productMarketInstance.product}"/>
<div style="float:left;font-size:20px;">
${product.name}
</div>

<div style="float:left;color:#7CBA53;width:30px;">
&nbsp;
</div>
</g:each>

<br class="clear"/>

</div>