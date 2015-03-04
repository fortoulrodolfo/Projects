<?php
		include '/Class_Alertas.php';
	/*
		* To change this template, choose Tools | Templates
		* and open the template in the editor.
	*/
	/**
		* Description of Class_Seguimiento
 		*
 		* @author root
*/
	
	
class Class_Seguimiento{
//put your code here
	function Class_Seguimiento(){
		}
	
	function registrar(){
		$sql = "insert into propuesta (codigo_arc, fecha_solicitud, fecha_entrega, fecha_aceptacion, fecha_culminacion, idtipo_fase, idusuario) values ('$_POST[fecha_solicitud]', '$_POST[fecha_solicitud]', '$_POST[fecha_entrega]', '$_POST[fecha_aceptacion]', '$_POST[fecha_culminacion]', '$_POST[tipo_fase]','$_SESSION[ce]')";
        $a = mysql_query($sql) or die(mysql_error());
        if ($a != ""){
            echo "<script>
					alert('¡Fase Registrada Con Exito!')
        			location.href='fases_proyecto.php'
        		  </script>";
				  }
			}
	
	function codigo_arc(){ 
		$sql = "select * from propuesta p where p.idusuario = '".$_SESSION['cedula']."'";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idpropuesta]'>$da[codigo_arc]</option>";
            echo $html;
			}
		}
		
	function obtener_proyecto(){ 
		$sql = "select i.nombre_institucion, p.rif_institucion,m.nombre_macro_proyecto,p.idstatus_proyecto,p.id_fase_actualizacion,p.idtipo_fase_actualizada, SUM(lp.costo_venta) as suma_costo,SUM(lp.ingreso_unico_anual)as suma_ingreso, p.costo_real,p.ingreso_real, p.alerta_fase, p.alerta_tipo_fase, p.fecha_actualizacion_nueva, p.fecha_aceptacion, p.fecha_entrega, p.fecha_culminacion from propuesta p, institucion i, macro_proyecto m, macro_linea_propuesta lp, status_proyecto s, fase_proyecto f, tipo_fase tf where p.idpropuesta ='".$_GET['codp']."' and p.idpropuesta=lp.idpropuesta and p.rif_institucion=i.rif_institucion and p.idmacro_proyecto=m.idmacro_proyecto and p.idstatus_proyecto=s.idstatus_proyecto and p.id_fase=f.id_fase and p.idtipo_fase=tf.idtipo_fase GROUP BY p.idpropuesta";
		
		$data=array();
		
		$a = mysql_query($sql) or die(mysql_error());
		$da = mysql_fetch_assoc($a);
		
		$data[0]="$da[nombre_institucion]";
		$data[1]="$da[rif_institucion]";
		$data[2]="$da[nombre_macro_proyecto]";
		$data[3]="$da[idstatus_proyecto]";
		$data[4]="$da[id_fase_actualizacion]";
		$data[5]="$da[idtipo_fase_actualizada]";
		$data[6]="$da[suma_costo]";
		$data[7]="$da[suma_ingreso]";
		$data[8]="$da[costo_real]";
		$data[9]="$da[ingreso_real]";
		$data[10]="$da[alerta_fase]";
		$data[11]="$da[alerta_tipo_fase]";
		$data[12]="$da[fecha_actualizacion_nueva]";
		$data[13]="$da[fecha_aceptacion]";
		$data[14]="$da[fecha_entrega]";
		$data[15]="$da[fecha_culminacion]";
		
        	echo json_encode($data);
		
		}
  
	function combo_subfase()
	{
        $sql = "select * from  fase_proyecto";
		$a = mysql_query($sql) or die(mysql_error());
		
        	while ($da = mysql_fetch_assoc($a)) 
			{
           		$html = "<optgroup label='$da[nombre_fase]'></optgroup>";
				echo $html;
				
				 $sql2 = "select * from tipo_fase where id_tipof=$da[id_fase]";
				 $a2 = mysql_query($sql2) or die(mysql_error());
				 
				 while ($da2 = mysql_fetch_assoc($a2))
				  {
					 $html2="<option value='$da2[idtipo_fase]'>$da2[nombre_tipo_fase]</option>";
					 echo $html2;
					 }
            	
        	}
    	}
		function combo_fase(){
        $sql = "select * from  fase_proyecto";
		$a = mysql_query($sql) or die(mysql_error());
		
        	while ($da = mysql_fetch_assoc($a)) 
			{
           		$html ="<option value='$da[id_fase]'>$da[nombre_fase]</option>";
				echo $html;
        	}
    	}
		
		function mover_codigo(){
			
        $sql = "select * from tipo_fase where idtipo_fase ='".$_GET['codf']."' ";
		$a = mysql_query($sql) or die(mysql_error());
        $da = mysql_fetch_assoc($a);
		$cod="$da[id_tipof]";
            echo  $cod;
        	
    	}
		
		function status(){
        $sql = "select * from status_proyecto";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
           		$html = "<option value='$da[idstatus_proyecto]'>$da[nombre_status_proyecto]</option>";
            echo $html;
        	}
    	}
		
		function actualizar_segui(){
			
		$cod=$_POST['id_fase_anterior'];
		$cod_act=$_POST['idfase2'];
		$cod_tipo=$_POST['fas'];
		$cod_tipo2=$_POST['idtipo_fase_anterior'];
		$fec_acep=$_POST['fecha_acep'];
		$fec_entre=$_POST['fecha_entre'];
		$fec_cul=$_POST['fecha_cul'];
			
		if ($cod_tipo == $cod_tipo2){
		$sql="UPDATE propuesta SET fecha_consultor = NOW(),idtipo_fase = $_POST[idtipo_fase_anterior], idtipo_fase_actualizada = $_POST[fas], id_fase = $_POST[id_fase_anterior], id_fase_actualizacion = $_POST[idfase2], idstatus_proyecto = $_POST[sta], costo_real='$_POST[costReal]', ingreso_real='$_POST[ingReal]' WHERE idpropuesta = $_POST[codigo_arc]";
		}
		else {
		$sql="UPDATE propuesta SET  fecha_actualizacion_nueva = NOW(),fecha_consultor = NOW(), idtipo_fase = $_POST[idtipo_fase_anterior], idtipo_fase_actualizada = $_POST[fas], id_fase = $_POST[id_fase_anterior], id_fase_actualizacion = $_POST[idfase2], idstatus_proyecto = $_POST[sta], costo_real='$_POST[costReal]', ingreso_real='$_POST[ingReal]' WHERE idpropuesta = $_POST[codigo_arc]";
		}
		
		$a = mysql_query($sql) or die(mysql_error());
		
		
		if($cod<>$cod_act){
			$sql2="UPDATE propuesta SET fecha_inicio_fase = NOW() where  idpropuesta=$_POST[codigo_arc]";
			$a2 = mysql_query($sql2) or die(mysql_error());
			}
			
		if(empty($fec_acep) && $cod_tipo>'12' && $cod_tipo<'19'){
			$sql2="UPDATE propuesta SET fecha_aceptacion = NOW() where idpropuesta=$_POST[codigo_arc]";
			$a2 = mysql_query($sql2) or die(mysql_error());
			}
			
		if(empty($fec_entre) && $cod_tipo>='19' && $cod_tipo<='21'){
			$sql2="UPDATE propuesta SET fecha_entrega = NOW() where idpropuesta=$_POST[codigo_arc]";
			$a2 = mysql_query($sql2) or die(mysql_error());
			}
			
		if(empty($fec_cul) && $cod_tipo=='22'){
			$sql2="UPDATE propuesta SET fecha_culminacion = NOW() where idpropuesta=$_POST[codigo_arc]";
			$a2 = mysql_query($sql2) or die(mysql_error());
			}
			
			$al = new Class_Alertas;
			$al->actualizar_alertas();
		
		
			echo "<script>alert('¡Proyecto Actualizado Con exito!')
			window.reload();
			</script>";
			
			}
		
	}
?>