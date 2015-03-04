<?php
class Class_Central{
//put your code here
	function Class_Central(){
		}
	
	function registrar(){
		$sql = "insert into central (nombre_central, cccc, ubcc, seriales, idregion, idestado, idmunicipio) values ('$_POST[central]', '$_POST[cccc]', '$_POST[ubcc]', '$_POST[seriales]', '$_POST[region]', '$_POST[estado]', '$_POST[municipio]')";
		$a = mysql_query($sql) or die(mysql_error());
        	if ($a != ""){
            	echo "<script>
						alert('¡Central Registrada Con Exito!')
        				location.href='central.php'
        		  	  </script>";
				  	}
			}
	
	function combo_region(){
        $sql = "select * from region";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)){
            	$html = "<option value='$da[idregion]'>$da[nombre_region]</option>";
            echo $html;
			}
    	}
	
	function combo_estados(){
        $sql = "select * from estado e,region_estado r where e.idestado=r.idestado and r.idregion=$_POST[v1]";
        	echo "<select name='estado' id='estado' onchange='carga_mun()'>
                  <option selected='selected'>Seleccione</option>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idestado]'>" . utf8_encode($da['nombre_estado']) . "</option>";
            echo $html;
				}
        	echo "</select>";
		}
	
	function combo_municipio(){
        $sql = "select * from municipio m,region_estado_municipio r where m.idmunicipio=r.idmunicipio and r.idestado=$_POST[v1]";
        echo "<select name='municipio' id='municipio' onchange='carga_ce()'>
              <option selected='selected'>Seleccione</option>";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)){
            	$html = "<option value='$da[idmunicipio]'>" . utf8_encode($da[nombre_municipio]) . "</option>";
            echo $html;
				}
        	echo "</select>";
			}
}
?>