<g:javascript library="prototype" />
<g:javascript>
function updateAlpha(e) {
	// The response comes back as a bunch-o-JSON
	var products = eval("(" + e.responseText + ")")	// evaluate JSON
	var myDiv = document.getElementById('myDiv2')
	myDiv.innerHTML=""
			
	if (products) {
		
		// Rebuild the select
		for (var i=0; i < products.length; i++) {
			var product = products[i]
			
			myDiv.innerHTML+="<div style='float:left;width:150px;font-size:14px;margin:5px;'><a href='/id/" + product.userId + "' title='price:"+product.price+"/quantity:"+product.quantity+": Click for more info.'>" + product.name+"</a></div><br>"
			
		}
	}
}


// This is called when the page loads to initialize city
${remoteFunction(controller:"product", action:"ajaxGetProducts", params:"'alpha=A'", onComplete:"updateAlpha(e)")}

</g:javascript>

<div class="rounded" style="float: right; width:280px;font-size:18px;border:3px solid #ccc;"">

<div style="float: left; width: 150px;color:#5ea92b;">
<br class="clear"/>
<span style="font-size:18px;">Alphabetical Products: </span>
<br>
</div>

<div style="float: left; width: 150px;margin:5px;">
<select name="alpha" id="alpha" onchange="${remoteFunction(controller:'product',action:'ajaxGetProducts',params:'\'alpha=\' + escape(this.value)', 
            onComplete:'updateAlpha(e)')}">
<option>A</option>
<option>B</option>
<option>C</option>
<option>D</option>
<option>E</option>
<option>F</option>
<option>G</option>
<option>H</option>
<option>I</option>
<option>J</option>
<option>K</option>
<option>L</option>
<option>M</option>
<option>N</option>
<option>O</option>
<option>P</option>
<option>Q</option>
<option>R</option>
<option>S</option>
<option>T</option>
<option>U</option>
<option>V</option>
<option>X</option>
<option>W</option>
<option>Y</option>
<option>Z</option>

</select>
</div>
<div id="myDiv2">
</div>

<br class="clear"/>


</div>