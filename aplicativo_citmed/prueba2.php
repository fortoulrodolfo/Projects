<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" > 
<head> 
  <title>Double List Box</title> 
<script language="JavaScript" type="text/javascript">

function addOption(theSel, theText, theValue)
{
  var newOpt = new Option(theText, theValue);
  var selLength = theSel.length;
  theSel.options[selLength] = newOpt;
}

function deleteOption(theSel, theIndex)
{ 
  var selLength = theSel.length;
  if(selLength>0)
  {
    theSel.options[theIndex] = null;
  }
}

function moveOptions(theSelFrom, theSelTo)
{
  
  var selLength = theSelFrom.length;
  var selectedText = new Array();
  var selectedValues = new Array();
  var selectedCount = 0;
  
  var i;
  
  // Find the selected Options in reverse order
  // and delete them from the 'from' Select.
  for(i=selLength-1; i>=0; i--)
  {
    if(theSelFrom.options[i].selected)
    {
      selectedText[selectedCount] = theSelFrom.options[i].text;
      selectedValues[selectedCount] = theSelFrom.options[i].value;
      deleteOption(theSelFrom, i);
      selectedCount++;
    }
  }
  
  // Add the selected text/values in reverse order.
  // This will add the Options to the 'to' Select
  // in the same order as they were in the 'from' Select.
  for(i=selectedCount-1; i>=0; i--)
  {
    addOption(theSelTo, selectedText[i], selectedValues[i]);
  }
  
}

</script>


  <style type="text/css"> 
    .btn {width:90px;} 
  </style> 
</head> 
<body> 
<form action="" method="post">
<table border="0">
	<tr>
		<td>
			<select name="sel1" size="10" multiple="multiple">
			<option value="1">Left1</option>
			<option value="2">Left2</option>
			<option value="3">Left3</option>
			<option value="4">Left4</option>
			<option value="5">Left5</option>
			</select>
		</td>
		<td align="center" valign="middle">
			<input type="button" value="Asignar"
			 onclick="moveOptions(this.form.sel1, this.form.sel2);" /><br />
			<input type="button" value="Quitar"
			 onclick="moveOptions(this.form.sel2, this.form.sel1);" />
		</td>
		<td>
			<select name="sel2" size="10" multiple="multiple">
			<option value="1">Right1</option>
			<option value="2">Right2</option>
			<option value="3">Right3</option>
			<option value="4">Right4</option>
			<option value="5">Right5</option>
			</select>
		</td>
	</tr>
</table>
</form>
</body> 
</html>
