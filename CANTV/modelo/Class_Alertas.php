<?php
	class Class_Alertas{
		
		function Class_Alertas(){
			}
			
			
		function actualizar_alertas(){
			$sql = "select * from propuesta";
			$a = mysql_query($sql) or die(mysql_error());
			while ($da = mysql_fetch_assoc($a)) {
				$con=$da['fecha_consultor'];
				$lo=$da['fecha_actualizacion_nueva'];
				
				$f_ini=$da['fecha_inicio_fase'];
				
				//Variables Tiempo (SubFases & Fases)
				$dat= date("Y-m-d",strtotime("$lo+1 month"));
				$dat2= date("Y-m-d",strtotime("$lo+2 month"));
				$dat_con=date("Y-m-d",strtotime("$con+1 month"));
				$dat_inic_1=date("Y-m-d",strtotime("$f_ini+1 month"));
				$dat_inic_2=date("Y-m-d",strtotime("$f_ini+2 month"));
				$dat_inic_3=date("Y-m-d",strtotime("$f_ini+3 month"));
				$dat_inic_5=date("Y-m-d",strtotime("$f_ini+5 month"));
				
			
				/*condiciones posibles hasta ahora de las subfases y el consultor*/
				//CONSULTOR NO HA ACTUALIZADO
				if (date("Y-m-d")>$dat_con){
					$sql2 = "UPDATE propuesta SET alerta_fase='4' , alerta_tipo_fase='4' where idpropuesta=$da[idpropuesta]";
       				 $a2 = mysql_query($sql2) or die(mysql_error());
					}
				else{
					
					//SUB_FASES
					//ACTUALIZACION HECHA MAYOR A UN MES, MENOR A DOS MESES, SUB_FASES IGUALES
				if (date("Y-m-d")>$dat && date("Y-m-d")<$dat2 && $da['idtipo_fase']==$da['idtipo_fase_actualizada']){
					$sql2 = "UPDATE propuesta SET alerta_tipo_fase='2' where idpropuesta=$da[idpropuesta]";
       				 $a2 = mysql_query($sql2) or die(mysql_error());
					}
					//ACTUALIZACION HECHA MAYOR A DOS MESES, SUB_FASES IGUALES
				if (date("Y-m-d")>$dat2 && $da['idtipo_fase']==$da['idtipo_fase_actualizada']){
					$sql2 = "UPDATE propuesta SET alerta_tipo_fase='3' where idpropuesta=$da[idpropuesta]";
       				 $a2 = mysql_query($sql2) or die(mysql_error());
					}
					//ACTUALIZACION HECHA MENOR A UN MES, SUB_FASES IGUALES
				if (date("Y-m-d")<$dat && $da['idtipo_fase']==$da['idtipo_fase_actualizada']){
					$sql2 = "UPDATE propuesta SET alerta_tipo_fase='1' where idpropuesta=$da[idpropuesta]";
       				 $a2 = mysql_query($sql2) or die(mysql_error());
					}
					//ACTUALIZACION HECHA MENOR A UN MES, SUB_FASES DISTINTAS
				if (date("Y-m-d")<$dat && $da['idtipo_fase']!=$da['idtipo_fase_actualizada']){
					$sql2 = "UPDATE propuesta SET alerta_tipo_fase='1' where idpropuesta=$da[idpropuesta]";
       				 $a2 = mysql_query($sql2) or die(mysql_error());
					}
					
				 	//FASES:
					
					//FASE 2 - FECHA ACTUALIZACION MENOR A 3 MESES, FASE 2
				if (date("Y-m-d")<$dat_inic_3 && $da['id_fase']=='2'){
					$sql3 = "UPDATE propuesta SET alerta_fase='1' where idpropuesta=$da[idpropuesta]";
       				 $a23= mysql_query($sql3) or die(mysql_error());
					}
					//FASE 2 - FECHA ACTUALIZACION ENTRE 3 y 5 MESES, FASE 2
				if ($dat_inic_3<date("Y-m-d") && date("Y-m-d")<$dat_inic_5 && $da['id_fase']=='2'){
					$sql3 = "UPDATE propuesta SET alerta_fase='2' where idpropuesta=$da[idpropuesta]";
       				 $a3 = mysql_query($sql3) or die(mysql_error());
					}
					//FASE 2 - FECHA ACTUALIZACION MAYOR A 5 MESES, FASE 2
				if (date("Y-m-d")>$dat_inic_5 && $da['id_fase']=='2'){
					$sql3 = "UPDATE propuesta SET alerta_fase='3' where idpropuesta=$da[idpropuesta]";
       				 $a3 = mysql_query($sql3) or die(mysql_error());
					}
					
					//FASE 3 - FECHA ACTUALIZACION MENOR A 1 MES, FASE 3
				if (date("Y-m-d")<$dat_inic_1 && $da['id_fase']=='3'){
					$sql3 = "UPDATE propuesta SET alerta_fase='1' where idpropuesta=$da[idpropuesta]";
       				 $a3 = mysql_query($sql3) or die(mysql_error());
					}
					//FASE 3 - FECHA ACTUALIZACION ENTRE 1 y 2 MESES, FASE 3
				if ($dat_inic_1<date("Y-m-d") && date("Y-m-d")<$dat_inic_2 && $da['id_fase']=='3'){
					$sql3 = "UPDATE propuesta SET alerta_fase='2' where idpropuesta=$da[idpropuesta]";
       				 $a3 = mysql_query($sql3) or die(mysql_error());
					}
					//FASE 3 - FECHA ACTUALIZACION MAYOR A 2 MESES, FASE 3
				if (date("Y-m-d")>$dat_inic_2 && $da['id_fase']=='3'){
					$sql3 = "UPDATE propuesta SET alerta_fase='3' where idpropuesta=$da[idpropuesta]";
       				 $a3 = mysql_query($sql3) or die(mysql_error());
					}
					
					
					//FASE 4 - FECHA ACTUALIZACION MENOR A 1 MES, FASE 4
				if (date("Y-m-d")<$dat_inic_1 && $da['id_fase']=='4'){
					$sql3 = "UPDATE propuesta SET alerta_fase='1' where idpropuesta=$da[idpropuesta]";
       				 $a3 = mysql_query($sql3) or die(mysql_error());
					}
					
					//FASE 4 - FECHA ACTUALIZACION ENTRE 1 y 2 MESES, FASE 4
				if ($dat_inic_1<date("Y-m-d") && date("Y-m-d")<$dat_inic_2 && $da['id_fase']=='4'){
					$sql3 = "UPDATE propuesta SET alerta_fase='2' where idpropuesta=$da[idpropuesta]";
       				 $a3 = mysql_query($sql3) or die(mysql_error());
					}
					//FASE 4 - FECHA ACTUALIZACION MAYOR A 2 MESES, FASE 4
				if (date("Y-m-d")>$dat_inic_2 && $da['id_fase']=='4'){
					$sql3 = "UPDATE propuesta SET alerta_fase='3' where idpropuesta=$da[idpropuesta]";
       				 $a3 = mysql_query($sql3) or die(mysql_error());
					}
					
				 
				 
				}
			
			}
		}
		
	}
?>
