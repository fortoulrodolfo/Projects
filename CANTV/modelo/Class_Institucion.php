<?php

class Class_Institucion{
	function Class_Institucion(){
		}
	
	function registrar(){
		$sql = "insert into institucion (rif_institucion, nombre_institucion) values ('$_POST[rif]', '$_POST[institucion]')";
			$a = mysql_query($sql) or die(mysql_error());
       
	    $sql = "insert into cartera_instituciones (rif_institucion, idcedula) values ('$_POST[rif]', '$_POST[usuario]')";
			$a = mysql_query($sql) or die(mysql_error());
        		if ($a != ""){
            		echo "<script>
        					alert('¡Institucion Registrada Con Exito!')
        					location.href='institucion.php'
        				  </script>";
						  }
					}
	
	function combo_usuarios(){
		$sql = "select * from usuario";
			$a = mysql_query($sql) or die(mysql_error());
        		while ($da = mysql_fetch_assoc($a)) {
					$nombre= '' . utf8_encode($da['nombre']) . '';
					$html = "<option value='$da[idcedula]'>$nombre</option>";
            	echo $html;
				}
    		}
			
	function combo_usuarios_consultor(){
		$sql = "SELECT * from usuario WHERE idtipo_usuario = '4'";
			$a = mysql_query($sql) or die(mysql_error());
        		while ($da = mysql_fetch_assoc($a)) {
            		$html = "<option value='$da[idcedula]'>".utf8_encode($da[nombre])."</option>";
            	echo $html;
				}
    		}
			
	function cartera_instituciones(){
		$sql = "SELECT (SELECT institucion.rif_institucion FROM institucion WHERE institucion.rif_institucion=ci.rif_institucion) 
		AS rif_inst, (SELECT institucion.nombre_institucion FROM institucion WHERE institucion.rif_institucion=ci.rif_institucion) 
		AS nombre_inst FROM cartera_instituciones ci WHERE ci.idcedula='$_POST[cedula_inst]'";
         echo "<table class='tdem'>   
            <tr class='title'>
            	<td>R.I.F.</td>
            	<td>Nombre Institucion</td>
				<td></td>
            </tr>";
        $a = mysql_query($sql) or die(mysql_error());
			
        	while ($da = mysql_fetch_assoc($a)) {
        	echo "<tr>
					<td><input type='hidden' name='rif[]' value='$da[rif_inst]'>".utf8_encode($da['rif_inst'])."</td>
					<td>".utf8_encode($da['nombre_inst'])."</td>
					<td><input type='checkbox' name='act[]' id='act' value='$da[rif_inst]'></td>
				</tr>";
			}
			echo "</table>";
		}
		
	function actualizar_cartera(){
		$chkbox = $_POST['act'];
		$rif = $_POST['rif'];
		for($i=0; $i<sizeof($rif); $i++){
			if(isset($chkbox[$i])){
			$sql = "UPDATE cartera_instituciones SET idcedula='$_POST[combo_usuario]' WHERE rif_institucion='$chkbox[$i]'";
				$a = mysql_query($sql) or die(mysql_error());
			}
		}
		echo "<script>alert('¡Cartera Actualizada Con exito!')</script>";
	}
	
	function cargar_datos_consultor(){
		$sql = "SELECT idcedula,nombre FROM usuario WHERE idcedula='$_POST[combo_usuario]'";
			$a = mysql_query($sql) or die(mysql_error());
			while ($da = mysql_fetch_assoc($a)) {
        	echo "C.I.: <input type='text' value='$da[idcedula]' id='cedula' name='cedula'>
				  Nombre: <input type='text' value='$da[nombre]' id='nombre' name='nombre'>";
		}
	}

}
?>