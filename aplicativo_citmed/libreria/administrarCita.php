<?php
	
	class administrarCita{
		
		private $conec, $link;
		
		function administrarCita($conec,$link){
			$this->conec=$conec;
			$this->link=$link;
			}
			
		function buscarCita($codigo){
			
			if ($this->conec){
				
				$equipos = array();
				
					$query = "SELECT CITMED.T_CITA.ID_CITA,
					 TO_CHAR(CITMED.T_CITA.FECHA_INICIO_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					 TO_CHAR(CITMED.T_CITA.FECHA_FIN_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					 CITMED.T_CITA.STATUS_CITA,
					 TO_CHAR(CITMED.T_CITA.FECHA_STATUS_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					 CITMED.T_CITA.STATUS_PACIENTE_CITA,
					 CITMED.T_CITA.HABITACION_PACIENTE,
					 CITMED.T_CITA.ID_PROCEDIMIENTO,
					 CITMED.T_PROCEDIMIENTO_BASICO.TIPO_ESTUDIO,
					 CITMED.T_CITA.ID_MEDICO,
				     CITMED.T_MEDICO.NOMBRE1_MEDICO,
					 CITMED.T_MEDICO.APELLIDO1_MEDICO, 
					 CITMED.T_CITA.ID_PACIENTE,
					 CITMED.T_PACIENTE.NOMBRE_PACIENTE,
					 CITMED.T_PACIENTE.APELLIDO1_PACIENTE,
					 CITMED.T_CITA.ID_SALA,
					 CITMED.T_SALA.NOMBRE_SALA, 
					 CITMED.T_CITA.OBSERVACIONES_CITA,
					 CITMED.T_CITA.USUARIO_CITA, 
					 CITMED.T_CITA.FORMA_PAGO_CITA, 
					 CITMED.T_CITA.ANESTESIOLOGO_CITA,
					 CITMED.T_CITA.PARTICIPANTE1_CITA, 
					 CITMED.T_CITA.PARTICIPANTE2_CITA, 
					 CITMED.T_CITA.TIPO_ID, 
					 CITMED.T_CITA.TIPO_ID_MEDICO, 
					 (SELECT CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO= CITMED.T_CITA.ID_PROCEDIMIENTO UNION SELECT CITMED.T_PROCEDIMIENTO_COMPUESTO.NOMBRE_PROCEDIMIENTO_COMP FROM CITMED.T_PROCEDIMIENTO_COMPUESTO WHERE CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP= CITMED.T_CITA.ID_PROCEDIMIENTO)
					 FROM CITMED.T_CITA, CITMED.T_PROCEDIMIENTO_BASICO, CITMED.T_MEDICO, CITMED.T_PACIENTE, CITMED.T_SALA 
					 WHERE CITMED.T_CITA.ID_CITA='".$codigo."' 
					 AND CITMED.T_CITA.ID_MEDICO=CITMED.T_MEDICO.ID_MEDICO AND CITMED.T_CITA.ID_PACIENTE=CITMED.T_PACIENTE.ID_PACIENTE AND CITMED.T_CITA.TIPO_ID = CITMED.T_PACIENTE.TIPO_ID AND CITMED.T_CITA.TIPO_ID_MEDICO = CITMED.T_MEDICO.TIPO_ID
					 AND CITMED.T_CITA.ID_SALA=CITMED.T_SALA.ID_SALA";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_CITA_EQUIPO.ID_EQUIPO
					FROM CITMED.T_CITA_EQUIPO WHERE CITMED.T_CITA_EQUIPO.ID_CITA='".$codigo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					
					while($row2 = oci_fetch_array ($stmt2, OCI_NUM)){
						
						$equipos[] = array("id"=>$row2[0]);
						
						}
						
					$citas=array("datos"=>$row, "equipos"=> $equipos);
								
					echo json_encode($citas);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		function buscarCita2($codigo){
			
			if ($this->conec){
				
				
					$query = "SELECT CITMED.T_CITA.ID_CITA
					 FROM CITMED.T_CITA
					 WHERE CITMED.T_CITA.ID_CITA='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					return json_encode($row);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		
		function cargarCitas(){
			
			if ($this->conec){
					
					// Require our Event class and datetime utilities
					require dirname(__FILE__) . '/utils.php';
					
					// Parse the timezone parameter if it is present.
					$timezone = null;
		
					$query = "SELECT CITMED.T_CITA.ID_CITA,
					TO_CHAR(CITMED.T_CITA.FECHA_INICIO_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					TO_CHAR(CITMED.T_CITA.FECHA_FIN_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					CITMED.T_CITA.ID_PACIENTE,
					CITMED.T_PACIENTE.NOMBRE_PACIENTE,
					CITMED.T_PACIENTE.APELLIDO1_PACIENTE,
					CITMED.T_CITA.ID_MEDICO,
					CITMED.T_MEDICO.NOMBRE1_MEDICO,
					CITMED.T_MEDICO.APELLIDO1_MEDICO,
					CITMED.T_CITA.ID_PROCEDIMIENTO,
					(SELECT CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO= CITMED.T_CITA.ID_PROCEDIMIENTO UNION SELECT CITMED.T_PROCEDIMIENTO_COMPUESTO.NOMBRE_PROCEDIMIENTO_COMP FROM CITMED.T_PROCEDIMIENTO_COMPUESTO WHERE CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP= CITMED.T_CITA.ID_PROCEDIMIENTO),
				    CITMED.T_CITA.ID_SALA,
					CITMED.T_CITA.STATUS_CITA 
					FROM CITMED.T_CITA, CITMED.T_PACIENTE, CITMED.T_MEDICO
					WHERE CITMED.T_CITA.ID_PACIENTE=CITMED.T_PACIENTE.ID_PACIENTE AND CITMED.T_CITA.TIPO_ID=CITMED.T_PACIENTE.TIPO_ID AND CITMED.T_CITA.ID_MEDICO=CITMED.T_MEDICO.ID_MEDICO AND CITMED.T_CITA.TIPO_ID_MEDICO = CITMED.T_MEDICO.TIPO_ID  AND CITMED.T_CITA.STATUS_CITA<>'Cancelada'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$citas = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						if($row[12] == "Agendada"){
							$color="";
							}
						else{
								if($row[12] == "ProcedNP"){
									$color="red";
									}
								else{
									$color="green";
									}
							}
						$title= nl2br("Paciente: ".$row[3]." ".$row[4]." ".$row[5]." Dr(a): ".$row[7]." ".$row[8]." Proced.: ".$row[10]);
						$citas[] = array("id"=>$row[0], "title" => $title, "start" => $row[1], "end" => $row[2], "allDay" => false, "resources" => $row[11],"backgroundColor" => $color);
						
						}
					
					// Read and parse our events JSON file into an array of event data arrays.
					$json = json_encode($citas);
					$input_arrays = json_decode($json, true);
					
					// Accumulate an output array of event data arrays.
					$output_arrays = array();
					foreach ($input_arrays as $array) {
					
						// Convert the input array into a useful Event object
						$event = new Event($array, $timezone);
					
						// If the event is in-bounds, add it to the output	
						$output_arrays[] = $event->toArray();
							
						}
					
					// Send JSON to the client.
					echo json_encode($output_arrays);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		
		function registrarCita($sala, $fecha_cita, $hora_inicio, $hora_fin, $tipo_estudio, $proced, $equipos, $medico, $tipo_id_pac, $id_paciente,
								$tipo_paciente, $hab_paciente, $forma_pago, $anestesiologo, $participante1, $participante2, $observ, $status_cita, $usuario, $tipo_id_medico){
			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$fecha_inicio = $fecha_cita . " " . $hora_inicio;
				$fecha_fin = $fecha_cita . " " . $hora_fin;
				date_default_timezone_set('America/Caracas');
				$fecha_status= date("d-m-Y h:i:s",time());
				
				$query = "INSERT INTO CITMED.T_CITA (ID_CITA, FECHA_INICIO_CITA, FECHA_FIN_CITA, STATUS_CITA, FECHA_STATUS_CITA, STATUS_PACIENTE_CITA, HABITACION_PACIENTE,
								 ID_PROCEDIMIENTO, ID_MEDICO, ID_PACIENTE, ID_SALA, OBSERVACIONES_CITA, USUARIO_CITA, FORMA_PAGO_CITA, ANESTESIOLOGO_CITA, PARTICIPANTE1_CITA, PARTICIPANTE2_CITA, TIPO_ID, TIPO_ID_MEDICO)
					 VALUES (SQ_CITA_CODIGO.NextVal,to_timestamp('$fecha_inicio', 'DD-MM-YYYY HH24:MI:SS'),to_timestamp('$fecha_fin', 'DD-MM-YYYY HH24:MI:SS'),'".$status_cita."', 
					 to_timestamp('$fecha_status', 'DD-MM-YYYY HH24:MI:SS'),'".$tipo_paciente."','".$hab_paciente."','".$proced."'
					 ,'".$medico."', '".$id_paciente."','$sala','".$observ."','".$usuario."','".$forma_pago."','".$anestesiologo."','".$participante1."','".$participante2."','".$tipo_id_pac."','".$tipo_id_medico."')";			
					   
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					
					
					$query2 = "SELECT SQ_CITA_CODIGO.CURRVAL FROM DUAL";
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					$row = oci_fetch_array ($stmt2, OCI_NUM);
					
					
					if(count($equipos)>0){
						
						
							$procedimientos = array();
							
							
							$query3 = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO,
										   CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.TIEMPO_LIMPIEZA
										   FROM CITMED.T_PROCEDIMIENTO_BASICO 
										   WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO ='".$proced."'";		  
									$stmt3 = oci_parse($this->conec, $query3);
									oci_execute($stmt3);
									$row3 = oci_fetch_array ($stmt3, OCI_NUM);
							if($row3!=NULL){
									$procedimientos[] = array("id"=>$row3[0],"nombre"=>$row3[1], "duracion"=> $row3[2], "limpieza"=> $row3[3]);
							}
							else{
							
									$query3 = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO,
											   CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_COMPUESTO.TIEMPO_LIMPIEZA
											   FROM CITMED.T_PROCED_BASICO_COMPUESTO, CITMED.T_PROCEDIMIENTO_BASICO, CITMED.T_PROCEDIMIENTO_COMPUESTO 
											   WHERE CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO=CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO AND  CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP=CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO_COMP
											   AND CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO_COMP='".$proced."' ORDER BY CITMED.T_PROCED_BASICO_COMPUESTO.ORDEN_PROCEDIMIENTO";		  
									$stmt3 = oci_parse($this->conec, $query3);
									oci_execute($stmt3);
								
										while($row3 = oci_fetch_array ($stmt3, OCI_NUM)){
											$procedimientos[] = array("id"=>$row3[0],"nombre"=>$row3[1], "duracion"=> $row3[2], "limpieza"=> $row3[3]);
										}
									
								}
						
						$arre=explode(",",$equipos);
						$i=0;
						$j=count($procedimientos);
						$horaInicial=$hora_inicio;
						$horaFinal=$hora_fin;
						$segundos_horaInicial=strtotime($horaInicial);
						$segundos_horaFinal=strtotime($horaFinal);
						
						foreach ($arre as $equipo) {
							
							if(($i+1)!= $j){
								$minutoAnadir=$procedimientos[$i]["duracion"];
								}
							else{
								$minutoAnadir=$procedimientos[$i]["duracion"]+$procedimientos[$i]["limpieza"];
								}
				
							$segundos_minutoAnadir=$minutoAnadir*60;
							$nuevaHora=date("H:i",$segundos_horaInicial+$segundos_minutoAnadir);

							$fecha_inicio2 = $fecha_cita . " " . $horaInicial;
							$fecha_fin2 = $fecha_cita . " " .$nuevaHora;
							
							$query1 = "INSERT INTO CITMED.T_CITA_EQUIPO (ID_CITA, ID_EQUIPO, HORA_INICIO, HORA_FIN)
									 VALUES ('".$row[0]."','".$equipo."',to_timestamp('$fecha_inicio2', 'DD-MM-YYYY HH24:MI:SS'),to_timestamp('$fecha_fin2', 'DD-MM-YYYY HH24:MI:SS'))";			
							$stmt1 = oci_parse($this->conec, $query1);
							oci_execute($stmt1);
							
							$horaInicial=$nuevaHora;
							$segundos_horaInicial=strtotime($horaInicial);
							++$i;
						
						}
						
					} 
					
					$this->registrarCitaBitacora($row[0], $sala, $fecha_cita, $hora_inicio, $hora_fin, $fecha_status, $proced, $medico, $tipo_id_pac, $id_paciente,
								$tipo_paciente, $hab_paciente, $forma_pago, $anestesiologo, $participante1, $participante2, $observ, $status_cita, $usuario, $tipo_id_medico);
					
					
					echo "<script>
						alert('La cita fue registrada Exitosamente');
						window.location.href='".$this->link."citmed/administrarCitas/registrarCita.php?fecha=".$fecha_cita."';
					  </script>";
				
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
			
			
		function actualizarCita($codigo,$sala, $fecha_cita, $hora_inicio, $hora_fin, $tipo_estudio, $proced, $equipos, $medico, $tipo_id_pac, $id_paciente,
								$tipo_paciente, $hab_paciente, $forma_pago, $anestesiologo, $participante1, $participante2, $observ, $status_cita, $usuario, $tipo_id_medico){
			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				
				$fecha_inicio = $fecha_cita . " " . $hora_inicio;
				$fecha_fin = $fecha_cita . " " . $hora_fin;
				date_default_timezone_set('America/Caracas');
				$fecha_status= date("d-m-Y h:i:s",time());
			
				$query = "UPDATE CITMED.T_CITA SET CITMED.T_CITA.FECHA_INICIO_CITA=to_timestamp('$fecha_inicio', 'DD-MM-YYYY HH24:MI:SS'), CITMED.T_CITA.FECHA_FIN_CITA=to_timestamp('$fecha_fin', 'DD-MM-YYYY HH24:MI:SS'),
						 CITMED.T_CITA.STATUS_CITA='".$status_cita."', CITMED.T_CITA.FECHA_STATUS_CITA=to_timestamp('$fecha_status', 'DD-MM-YYYY HH24:MI:SS'), CITMED.T_CITA.STATUS_PACIENTE_CITA= '".$tipo_paciente."', CITMED.T_CITA.HABITACION_PACIENTE='".$hab_paciente."',
						 CITMED.T_CITA.ID_PROCEDIMIENTO='".$proced."', CITMED.T_CITA.ID_MEDICO='".$medico."', CITMED.T_CITA.ID_PACIENTE='".$id_paciente."',
						 CITMED.T_CITA.ID_SALA='$sala', CITMED.T_CITA.OBSERVACIONES_CITA='".$observ."', CITMED.T_CITA.USUARIO_CITA='".$usuario."', CITMED.T_CITA.FORMA_PAGO_CITA='".$forma_pago."',
						 CITMED.T_CITA.ANESTESIOLOGO_CITA='".$anestesiologo."', CITMED.T_CITA.PARTICIPANTE1_CITA='".$participante1."', CITMED.T_CITA.PARTICIPANTE2_CITA='".$participante2."', CITMED.T_CITA.TIPO_ID='".$tipo_id_pac."', CITMED.T_CITA.TIPO_ID_MEDICO='".$tipo_id_medico."'
					  	 WHERE CITMED.T_CITA.ID_CITA = '".$codigo."'";			
					   
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
				$query3 = "DELETE FROM CITMED.T_CITA_EQUIPO WHERE CITMED.T_CITA_EQUIPO.ID_CITA = '".$codigo."'";		  
					$stmt3 = oci_parse($this->conec, $query3);
					oci_execute($stmt3);
					
					if(count($equipos)>0){
						
						
							$procedimientos = array();
							
							
							$query3 = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO,
										   CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.TIEMPO_LIMPIEZA
										   FROM CITMED.T_PROCEDIMIENTO_BASICO 
										   WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO ='".$proced."'";		  
									$stmt3 = oci_parse($this->conec, $query3);
									oci_execute($stmt3);
									$row3 = oci_fetch_array ($stmt3, OCI_NUM);
							if($row3!=NULL){
									$procedimientos[] = array("id"=>$row3[0],"nombre"=>$row3[1], "duracion"=> $row3[2], "limpieza"=> $row3[3]);
							}
							else{
							
									$query3 = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO,
											   CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_COMPUESTO.TIEMPO_LIMPIEZA
											   FROM CITMED.T_PROCED_BASICO_COMPUESTO, CITMED.T_PROCEDIMIENTO_BASICO, CITMED.T_PROCEDIMIENTO_COMPUESTO 
											   WHERE CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO=CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO AND  CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP=CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO_COMP
											   AND CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO_COMP='".$proced."' ORDER BY CITMED.T_PROCED_BASICO_COMPUESTO.ORDEN_PROCEDIMIENTO";		  
									$stmt3 = oci_parse($this->conec, $query3);
									oci_execute($stmt3);
								
										while($row3 = oci_fetch_array ($stmt3, OCI_NUM)){
											$procedimientos[] = array("id"=>$row3[0],"nombre"=>$row3[1], "duracion"=> $row3[2], "limpieza"=> $row3[3]);
										}
									
								}
						
						$arre=explode(",",$equipos);
						$i=0;
						$j=count($procedimientos);
						$horaInicial=$hora_inicio;
						$horaFinal=$hora_fin;
						$segundos_horaInicial=strtotime($horaInicial);
						$segundos_horaFinal=strtotime($horaFinal);
						
						foreach ($arre as $equipo) {
							
							if(($i+1)!= $j){
								$minutoAnadir=$procedimientos[$i]["duracion"];
								}
							else{
								$minutoAnadir=$procedimientos[$i]["duracion"]+$procedimientos[$i]["limpieza"];
								}
				
							$segundos_minutoAnadir=$minutoAnadir*60;
							$nuevaHora=date("H:i",$segundos_horaInicial+$segundos_minutoAnadir);

							$fecha_inicio2 = $fecha_cita . " " . $horaInicial;
							$fecha_fin2 = $fecha_cita . " " .$nuevaHora;
							
							$query1 = "INSERT INTO CITMED.T_CITA_EQUIPO (ID_CITA, ID_EQUIPO, HORA_INICIO, HORA_FIN)
									 VALUES ('".$codigo."','".$equipo."',to_timestamp('$fecha_inicio2', 'DD-MM-YYYY HH24:MI:SS'),to_timestamp('$fecha_fin2', 'DD-MM-YYYY HH24:MI:SS'))";			
							$stmt1 = oci_parse($this->conec, $query1);
							oci_execute($stmt1);
							
							$horaInicial=$nuevaHora;
							$segundos_horaInicial=strtotime($horaInicial);
							++$i;
						
						}
						
					} 
					
				
				$this->registrarCitaBitacora($codigo, $sala, $fecha_cita, $hora_inicio, $hora_fin, $fecha_status, $proced, $medico, $tipo_id_pac, $id_paciente,
								$tipo_paciente, $hab_paciente, $forma_pago, $anestesiologo, $participante1, $participante2, $observ, $status_cita, $usuario, $tipo_id_medico);
					
					
					echo "<script>
						alert('La cita fue modificada Exitosamente');
						window.location.href='".$this->link."citmed/administrarCitas/registrarCita.php?fecha=".$fecha_cita."';
					  </script>";
				
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
		function eliminarCita($codigo, $status, $fecha_cita){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
					if($status=='Agendada'){
						
						date_default_timezone_set('America/Caracas');
						$fecha_status= date("d-m-Y h:i:s",time());
						
						$query = "SELECT CITMED.T_CITA.ID_CITA,
						  TO_CHAR(CITMED.T_CITA.FECHA_INICIO_CITA, 'DD-MM-YYYY HH24:MI:SS'),
						  TO_CHAR(CITMED.T_CITA.FECHA_FIN_CITA, 'DD-MM-YYYY HH24:MI:SS'), 
						  CITMED.T_CITA.STATUS_CITA,
						  TO_CHAR(CITMED.T_CITA.FECHA_STATUS_CITA, 'DD-MM-YYYY HH24:MI:SS'),
						  CITMED.T_CITA.STATUS_PACIENTE_CITA,
						  CITMED.T_CITA.HABITACION_PACIENTE,
						  CITMED.T_CITA.ID_PROCEDIMIENTO,
						  CITMED.T_CITA.ID_MEDICO,
						  CITMED.T_MEDICO.NOMBRE1_MEDICO,
						  CITMED.T_MEDICO.APELLIDO1_MEDICO,
						  CITMED.T_CITA.ID_PACIENTE,
						  CITMED.T_PACIENTE.NOMBRE_PACIENTE,
						  CITMED.T_PACIENTE.APELLIDO1_PACIENTE,
						  CITMED.T_CITA.ID_SALA,
						  CITMED.T_SALA.NOMBRE_SALA,
						  CITMED.T_CITA.OBSERVACIONES_CITA,
						  CITMED.T_CITA.USUARIO_CITA,
						  CITMED.T_CITA.FORMA_PAGO_CITA,
						  CITMED.T_CITA.ANESTESIOLOGO_CITA,
						  CITMED.T_CITA.PARTICIPANTE1_CITA,
						  CITMED.T_CITA.PARTICIPANTE2_CITA,
						  CITMED.T_CITA.TIPO_ID,
						  CITMED.T_CITA.TIPO_ID_MEDICO
						  FROM CITMED.T_CITA, CITMED.T_MEDICO, CITMED.T_PACIENTE, CITMED.T_SALA 
						  WHERE CITMED.T_CITA.ID_CITA='".$codigo."'
						  AND CITMED.T_CITA.ID_MEDICO=CITMED.T_MEDICO.ID_MEDICO AND CITMED.T_CITA.ID_PACIENTE=CITMED.T_PACIENTE.ID_PACIENTE AND CITMED.T_CITA.TIPO_ID = CITMED.T_PACIENTE.TIPO_ID AND CITMED.T_CITA.TIPO_ID_MEDICO = CITMED.T_MEDICO.TIPO_ID
						  AND CITMED.T_CITA.ID_SALA=CITMED.T_SALA.ID_SALA";		  
						  $stmt = oci_parse($this->conec, $query);
						  oci_execute($stmt);
						  $row = oci_fetch_array ($stmt, OCI_NUM);
						  
						  $date1 = date_create($row[1]);
						  $date2 = date_create($row[2]);
						  $fecha= date_format($date1, 'd-m-Y');
						  $fecha2= date_format($date1, 'H:i:s');
						  $fecha3= date_format($date2, 'H:i:s');
						  $status_cita="Eliminada";
						  
						  if(!isset($row[6])){
							  $row[6]="";
							  }
						  if(!isset($row[20])){
							  $row[20]="";
							  }
						  if(!isset($row[21])){
							  $row[21]="";
							  }
						 
						  $this->registrarCitaBitacora($row[0], $row[14], $fecha, $fecha2,$fecha3, $row[4], $row[7], $row[8], $row[22], $row[11],
								$row[5], $row[6], $row[18], $row[19], $row[20], $row[21], $row[10], $status_cita, $row[17], $row[23]);
					
							
						  $query3 = "DELETE FROM CITMED.T_CITA_EQUIPO WHERE CITMED.T_CITA_EQUIPO.ID_CITA = '".$codigo."'";		  
						  $stmt3 = oci_parse($this->conec, $query3);
						  oci_execute($stmt3);	
								
						  $query1 = "DELETE FROM CITMED.T_CITA WHERE CITMED.T_CITA.ID_CITA = '".$codigo."'";		  
						  $stmt1 = oci_parse($this->conec, $query1);
						  oci_execute($stmt1);
										
						   echo "<script>
								alert('La Cita fue eliminado Exitosamente');
								window.location.href='".$this->link."citmed/administrarCitas/registrarCita.php?fecha=".$fecha_cita."';
							</script>";
							
					}
					else{
						echo "<script>
							alert('No se puede eliminar una cita despues de confirmada, puede Cancelarla.');
							window.location.href='".$this->link."citmed/administrarCitas/registrarCita.php?fecha=".$fecha_cita."';
						</script>";
						
						}
					
					}
			
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function cargarSalasCitas($codigo_sala,$fecha_cita,$hora_inicio,$hora_fin){
			
			if ($this->conec){
				
					$fecha_inicio = $fecha_cita . " " . $hora_inicio;
					$fecha_fin = $fecha_cita . " " . $hora_fin;
					
					$query = "SELECT CITMED.T_CITA.ID_CITA,
					TO_CHAR(CITMED.T_CITA.FECHA_INICIO_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					TO_CHAR(CITMED.T_CITA.FECHA_FIN_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					CITMED.T_CITA.ID_PACIENTE,
					CITMED.T_PACIENTE.NOMBRE_PACIENTE,
					CITMED.T_PACIENTE.APELLIDO1_PACIENTE,
					CITMED.T_CITA.ID_MEDICO,
					CITMED.T_MEDICO.NOMBRE1_MEDICO,
					CITMED.T_MEDICO.APELLIDO1_MEDICO,
					CITMED.T_CITA.ID_PROCEDIMIENTO,
					CITMED.T_CITA.ID_SALA,
					CITMED.T_SALA.NOMBRE_SALA,
					CITMED.T_CITA.STATUS_CITA 
					FROM CITMED.T_CITA,
					CITMED.T_PACIENTE, CITMED.T_MEDICO,
					CITMED.T_SALA
					WHERE CITMED.T_CITA.ID_PACIENTE=CITMED.T_PACIENTE.ID_PACIENTE 
					AND CITMED.T_CITA.TIPO_ID=CITMED.T_PACIENTE.TIPO_ID AND CITMED.T_CITA.TIPO_ID_MEDICO = CITMED.T_MEDICO.TIPO_ID 
					AND CITMED.T_CITA.ID_MEDICO=CITMED.T_MEDICO.ID_MEDICO
					AND CITMED.T_CITA.STATUS_CITA<>'Cancelada' AND CITMED.T_CITA.ID_SALA=CITMED.T_SALA.ID_SALA 
					AND CITMED.T_CITA.ID_SALA = '".$codigo_sala."' AND CITMED.T_SALA.STATUS_SALA = 'Activa' AND CITMED.T_CITA.FECHA_INICIO_CITA BETWEEN to_timestamp('$fecha_inicio', 'DD-MM-YYYY HH24:MI:SS') AND to_timestamp('$fecha_fin', 'DD-MM-YYYY HH24:MI:SS') ";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$citas = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$citas[] = array("id"=>$row[0], "start" => $row[1], "end" => $row[2], "resources" => $row[10]);
						
						}
						
					echo json_encode($citas);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		function cargarSalasEquipo($codigo_sala,$codigo_proced,$fecha_cita,$hora_inicio,$hora_fin){
			
			if ($this->conec){
				
					$fecha_inicio = $fecha_cita . " " . $hora_inicio;
					$fecha_fin = $fecha_cita . " " . $hora_fin;
					
					$query = "SELECT CITMED.T_EQUIPO.ID_EQUIPO, CITMED.T_EQUIPO.NOMBRE_EQUIPO, CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO
					FROM CITMED.T_EQUIPO, CITMED.T_EQUIPO_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO
					WHERE CITMED.T_EQUIPO.ID_SALA = '".$codigo_sala."' AND CITMED.T_EQUIPO.EQUIPO_FIJO='Fijo' AND CITMED.T_EQUIPO.STATUS_EQUIPO='Activo' AND CITMED.T_EQUIPO.ID_EQUIPO=CITMED.T_EQUIPO_PROCEDIMIENTO.ID_EQUIPO
					AND CITMED.T_EQUIPO_PROCEDIMIENTO.ID_PROCEDIMIENTO='".$codigo_proced."' AND CITMED.T_EQUIPO_PROCEDIMIENTO.ID_PROCEDIMIENTO=CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$equipo_fijo = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$equipo_fijo[] = array("id"=>$row[0], "nombre" => $row[1], "duracion" => $row[2]);
						
						}
						
					$query = "SELECT CITMED.T_EQUIPO.ID_EQUIPO, CITMED.T_EQUIPO.NOMBRE_EQUIPO, CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO
					FROM CITMED.T_EQUIPO, CITMED.T_EQUIPO_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO
					WHERE CITMED.T_EQUIPO.ID_SALA = '".$codigo_sala."' AND CITMED.T_EQUIPO.EQUIPO_FIJO='Movil' AND CITMED.T_EQUIPO.STATUS_EQUIPO='Activo' AND CITMED.T_EQUIPO.ID_EQUIPO=CITMED.T_EQUIPO_PROCEDIMIENTO.ID_EQUIPO
					AND CITMED.T_EQUIPO_PROCEDIMIENTO.ID_PROCEDIMIENTO='".$codigo_proced."' AND CITMED.T_EQUIPO_PROCEDIMIENTO.ID_PROCEDIMIENTO=CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$equipo_movil = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$equipo_movil[] = array("id"=>$row[0], "nombre" => $row[1], "duracion" => $row[2]);
						
						}
						
					$query = "SELECT CITMED.T_EQUIPO.ID_EQUIPO, CITMED.T_EQUIPO.NOMBRE_EQUIPO, CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO
					FROM CITMED.T_EQUIPO, CITMED.T_EQUIPO_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO
					WHERE CITMED.T_EQUIPO.EQUIPO_FIJO='Movil' AND CITMED.T_EQUIPO.STATUS_EQUIPO='Activo' AND CITMED.T_EQUIPO.ID_EQUIPO=CITMED.T_EQUIPO_PROCEDIMIENTO.ID_EQUIPO
					AND CITMED.T_EQUIPO_PROCEDIMIENTO.ID_PROCEDIMIENTO='".$codigo_proced."' AND CITMED.T_EQUIPO_PROCEDIMIENTO.ID_PROCEDIMIENTO=CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$movil = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$movil[] = array("id"=>$row[0], "nombre" => $row[1], "duracion" => $row[2]);
						
						}
						
					$query = "SELECT CITMED.T_CITA_EQUIPO.ID_CITA,CITMED.T_CITA_EQUIPO.ID_EQUIPO,TO_CHAR(CITMED.T_CITA_EQUIPO.HORA_INICIO, 'DD-MM-YYYY HH24:MI:SS'),TO_CHAR(CITMED.T_CITA_EQUIPO.HORA_FIN, 'DD-MM-YYYY HH24:MI:SS'), CITMED.T_CITA.STATUS_CITA 
					FROM CITMED.T_CITA_EQUIPO, CITMED.T_CITA
					WHERE CITMED.T_CITA_EQUIPO.ID_CITA=CITMED.T_CITA.ID_CITA AND CITMED.T_CITA.STATUS_CITA <> 'Cancelada' AND CITMED.T_CITA.STATUS_CITA <> 'ProcedNP' AND CITMED.T_CITA_EQUIPO.HORA_INICIO BETWEEN to_timestamp('$fecha_inicio', 'DD-MM-YYYY HH24:MI:SS') AND to_timestamp('$fecha_fin', 'DD-MM-YYYY HH24:MI:SS') ";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$citas = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$citas[] = array("cita"=>$row[0], "equipo" => $row[1], "inicio" => $row[2], "fin" => $row[3]);
						
						}
						
					$equipos=array("sala_fijo"=>$equipo_fijo, "sala_movil" =>$equipo_movil, "movil"=>$movil, "citas"=>$citas);
						
					echo json_encode($equipos);
					
					
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function cargarSalasEquipoNP($codigo_proced){
			
			if ($this->conec){
					
					$query = "SELECT CITMED.T_EQUIPO.ID_EQUIPO, CITMED.T_EQUIPO.NOMBRE_EQUIPO
					FROM CITMED.T_EQUIPO, CITMED.T_EQUIPO_PROCEDIMIENTO
					WHERE CITMED.T_EQUIPO.ID_EQUIPO=CITMED.T_EQUIPO_PROCEDIMIENTO.ID_EQUIPO AND CITMED.T_EQUIPO.STATUS_EQUIPO='Activo'
					AND CITMED.T_EQUIPO_PROCEDIMIENTO.ID_PROCEDIMIENTO='".$codigo_proced."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$equipos = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$equipos[] = array("id"=>$row[0], "nombre" => $row[1]);
						
						}
						
					echo json_encode($equipos);
					
					
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function cargarSalasMedicos($tipo_id,$codigo_medico,$fecha_cita,$hora_inicio,$hora_fin){
			
			if ($this->conec){
				
					$fecha_inicio = $fecha_cita . " " . $hora_inicio;
					$fecha_fin = $fecha_cita . " " . $hora_fin;
					
					$query = "SELECT CITMED.T_CITA.ID_CITA,
					TO_CHAR(CITMED.T_CITA.FECHA_INICIO_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					TO_CHAR(CITMED.T_CITA.FECHA_FIN_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					CITMED.T_CITA.ID_PACIENTE,
					CITMED.T_PACIENTE.NOMBRE_PACIENTE,
					CITMED.T_PACIENTE.APELLIDO1_PACIENTE,
					CITMED.T_CITA.ID_MEDICO,
					CITMED.T_MEDICO.NOMBRE1_MEDICO,
					CITMED.T_MEDICO.APELLIDO1_MEDICO,
					CITMED.T_CITA.ID_PROCEDIMIENTO,
					CITMED.T_CITA.ID_SALA,
					CITMED.T_SALA.NOMBRE_SALA,
					CITMED.T_CITA.STATUS_CITA 
					FROM CITMED.T_CITA,CITMED.T_PACIENTE,CITMED.T_MEDICO,
					CITMED.T_SALA
					WHERE CITMED.T_CITA.ID_PACIENTE=CITMED.T_PACIENTE.ID_PACIENTE AND CITMED.T_CITA.TIPO_ID=CITMED.T_PACIENTE.TIPO_ID 
					AND CITMED.T_CITA.ID_MEDICO=CITMED.T_MEDICO.ID_MEDICO AND CITMED.T_CITA.TIPO_ID_MEDICO = CITMED.T_MEDICO.TIPO_ID
					AND CITMED.T_CITA.STATUS_CITA<>'Cancelada' AND CITMED.T_CITA.ID_SALA=CITMED.T_SALA.ID_SALA 
					AND CITMED.T_CITA.ID_MEDICO = '".$codigo_medico."' AND CITMED.T_SALA.STATUS_SALA = 'Activa' AND CITMED.T_CITA.TIPO_ID_MEDICO='".$tipo_id."' 
					AND CITMED.T_CITA.FECHA_INICIO_CITA BETWEEN to_timestamp('$fecha_inicio', 'DD-MM-YYYY HH24:MI:SS') AND to_timestamp('$fecha_fin', 'DD-MM-YYYY HH24:MI:SS') ";		  
					
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$citas = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$citas[] = array("id"=>$row[0], "start" => $row[1], "end" => $row[2], "resources" => $row[10]);
						
						}
						
					echo json_encode($citas);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function cargarSalasPaciente($tipo_id,$codigo_paciente,$fecha_cita,$hora_inicio,$hora_fin){
			
			if ($this->conec){
				
					$fecha_inicio = $fecha_cita . " " . $hora_inicio;
					$fecha_fin = $fecha_cita . " " . $hora_fin;
					
					$query = "SELECT CITMED.T_CITA.ID_CITA,
					TO_CHAR(CITMED.T_CITA.FECHA_INICIO_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					TO_CHAR(CITMED.T_CITA.FECHA_FIN_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					CITMED.T_CITA.ID_PACIENTE,CITMED.T_PACIENTE.NOMBRE_PACIENTE,
					CITMED.T_PACIENTE.APELLIDO1_PACIENTE,
					CITMED.T_CITA.ID_MEDICO,
					CITMED.T_MEDICO.NOMBRE1_MEDICO,
					CITMED.T_MEDICO.APELLIDO1_MEDICO,
					CITMED.T_CITA.ID_PROCEDIMIENTO,
					CITMED.T_CITA.ID_SALA, 
					CITMED.T_SALA.NOMBRE_SALA, 
					CITMED.T_CITA.STATUS_CITA 
					FROM CITMED.T_CITA, CITMED.T_PACIENTE, CITMED.T_MEDICO, CITMED.T_SALA
					WHERE CITMED.T_CITA.ID_PACIENTE=CITMED.T_PACIENTE.ID_PACIENTE AND CITMED.T_CITA.TIPO_ID=CITMED.T_PACIENTE.TIPO_ID 
					AND CITMED.T_CITA.ID_MEDICO=CITMED.T_MEDICO.ID_MEDICO AND CITMED.T_CITA.TIPO_ID_MEDICO = CITMED.T_MEDICO.TIPO_ID
					AND CITMED.T_CITA.STATUS_CITA<>'Cancelada' AND CITMED.T_CITA.ID_SALA=CITMED.T_SALA.ID_SALA  AND CITMED.T_SALA.STATUS_SALA = 'Activa'
					AND CITMED.T_CITA.ID_PACIENTE = '".$codigo_paciente."' AND CITMED.T_CITA.TIPO_ID='".$tipo_id."' 
					AND CITMED.T_CITA.FECHA_INICIO_CITA BETWEEN to_timestamp('$fecha_inicio', 'DD-MM-YYYY HH24:MI:SS') AND to_timestamp('$fecha_fin', 'DD-MM-YYYY HH24:MI:SS') ";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$citas = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$citas[] = array("id"=>$row[0], "start" => $row[1], "end" => $row[2], "resources" => $row[11]);
						
						}
						
					echo json_encode($citas);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
				
		function cargarCitasLibro($fecha_inicio, $fecha_final, $status, $sala, $proced, $equipo, $forma, $aneste, $medico, $tipo_id, $paciente, $tipo_paciente, $tipo_id_medico){
			
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_CITA.ID_CITA,
					TO_CHAR(CITMED.T_CITA.FECHA_INICIO_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					TO_CHAR(CITMED.T_CITA.FECHA_FIN_CITA, 'DD-MM-YYYY HH24:MI:SS'),
					CITMED.T_CITA.ID_PACIENTE,
					CITMED.T_PACIENTE.NOMBRE_PACIENTE,
					CITMED.T_PACIENTE.APELLIDO1_PACIENTE,
					CITMED.T_CITA.ID_MEDICO,
					CITMED.T_MEDICO.NOMBRE1_MEDICO,
					CITMED.T_MEDICO.APELLIDO1_MEDICO,
					CITMED.T_CITA.ID_PROCEDIMIENTO,
					(SELECT CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO= CITMED.T_CITA.ID_PROCEDIMIENTO UNION SELECT CITMED.T_PROCEDIMIENTO_COMPUESTO.NOMBRE_PROCEDIMIENTO_COMP FROM CITMED.T_PROCEDIMIENTO_COMPUESTO WHERE CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP= CITMED.T_CITA.ID_PROCEDIMIENTO),
					CITMED.T_CITA.ID_SALA,
					CITMED.T_SALA.NOMBRE_SALA,
					CITMED.T_CITA.STATUS_CITA,
					CITMED.T_CITA.FORMA_PAGO_CITA,
					CITMED.T_CITA.ANESTESIOLOGO_CITA,
					CITMED.T_CITA.STATUS_PACIENTE_CITA,
					CITMED.T_CITA.TIPO_ID,
					CITMED.T_CITA.TIPO_ID_MEDICO,
					CITMED.T_EQUIPO.NOMBRE_EQUIPO
					FROM CITMED.T_CITA, CITMED.T_PACIENTE, CITMED.T_MEDICO,CITMED.T_SALA, CITMED.T_EQUIPO, CITMED.T_CITA_EQUIPO
					WHERE CITMED.T_CITA.TIPO_ID=CITMED.T_PACIENTE.TIPO_ID AND CITMED.T_CITA.TIPO_ID_MEDICO = CITMED.T_MEDICO.TIPO_ID
					AND CITMED.T_CITA.ID_PACIENTE=CITMED.T_PACIENTE.ID_PACIENTE AND CITMED.T_CITA.ID_SALA=CITMED.T_SALA.ID_SALA 
					AND CITMED.T_CITA.TIPO_ID=CITMED.T_PACIENTE.TIPO_ID AND CITMED.T_CITA.ID_MEDICO=CITMED.T_MEDICO.ID_MEDICO
					AND CITMED.T_CITA_EQUIPO.ID_EQUIPO=CITMED.T_EQUIPO.ID_EQUIPO AND CITMED.T_CITA_EQUIPO.ID_CITA=CITMED.T_CITA.ID_CITA";		  
					
					if($fecha_inicio!=""){
						$query= $query. " AND CITMED.T_CITA.FECHA_INICIO_CITA >= to_timestamp('$fecha_inicio', 'DD-MM-YYYY HH24:MI:SS')";
						
						}
						
					if($fecha_final!=""){
						$query= $query. " AND CITMED.T_CITA.FECHA_FIN_CITA <= to_timestamp('$fecha_final', 'DD-MM-YYYY HH24:MI:SS')";
						
						}
						
					if($status!=""){
						$query= $query. " AND CITMED.T_CITA.STATUS_CITA = '".$status."'";
						
						}
						
					if($sala!=""){
						$query= $query. " AND CITMED.T_CITA.ID_SALA = '".$sala."'";
						
						}
						
					if($proced!=""){
						$query= $query. " AND CITMED.T_CITA.ID_PROCEDIMIENTO = '".$proced."'";
						
						}
						
					if($equipo!=""){
						$query= $query. " AND CITMED.T_CITA_EQUIPO.ID_EQUIPO = '".$equipo."'";
						
						}
						
					if($forma!=""){
						$query= $query. " AND CITMED.T_CITA.FORMA_PAGO_CITA = '".$forma."'";
						
						}
						
					if($aneste!=""){
						$query= $query. " AND CITMED.T_CITA.ANESTESIOLOGO_CITA = '".$aneste."'";
						
						}
						
					if($tipo_id_medico!=""){
						$query= $query. " AND CITMED.T_CITA.TIPO_ID_MEDICO = '".$tipo_id_medico."'";
						}
						
					if($medico!=""){
						$query= $query. " AND CITMED.T_CITA.ID_MEDICO = '".$medico."'";
						}
						
					if($tipo_id!=""){
						$query= $query. " AND CITMED.T_CITA.TIPO_ID = '".$tipo_id."'";
						}
						
					if($paciente!=""){
						$query= $query. " AND CITMED.T_CITA.ID_PACIENTE = '".$paciente."'";
						}
						
					if($tipo_paciente!=""){
						$query= $query. " AND CITMED.T_CITA.STATUS_PACIENTE_CITA = '".$tipo_paciente."'";
						}
					
					$query=$query." ORDER BY CITMED.T_CITA.FECHA_INICIO_CITA";
					
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$citas = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						if($row[15]!= "on"){
							$anes="No";
							}
						else
						{
							$anes="Si";
							}
						
						$nombre_paciente= $row[4]." ".$row[5];
						$nombre_medico = $row[7]." ".$row[8]; 
						
						$date1 = date_create($row[1]);
						$date2 = date_create($row[2]);
						$fecha= date_format($date1, 'd-m-Y');
						$fecha2= date_format($date1, 'G:ia');
						$fecha3= date_format($date2, 'G:ia');
						
						$citas[] = array("Cita"=>$row[0],"Fecha"=>$fecha,"Inicio Cita"=>$fecha2, "Final Cita" => $fecha3, "Status" => $row[13], "Sala" => $row[11],
						 "Procedimiento" => $row[10], "Equipo" => $row[19], "Pago" => $row[14], "Aneste." => $anes,
						  "Medico" => $nombre_medico, "Paciente" => $nombre_paciente, "Tipo" => $row[16]);
						
						}
					
					echo json_encode($citas);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
		function registrarCitaBitacora($cita, $sala, $fecha_cita, $hora_inicio, $hora_fin, $fecha_status, $proced, $medico, $tipo_id_pac, $id_paciente,
								$tipo_paciente, $hab_paciente, $forma_pago, $anestesiologo, $participante1, $participante2, $observ, $status_cita, $usuario, $tipo_id_medico){
			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$fecha_inicio = $fecha_cita . " " . $hora_inicio;
				$fecha_fin = $fecha_cita . " " . $hora_fin;
				
				$query = "INSERT INTO CITMED.T_CITA_BITACORA (ID_CITA_BITACORA, FECHA_INICIO_CITA_BITACORA, FECHA_FIN_CITA_BITACORA, STATUS_CITA_BITACORA, FECHA_STATUS_CITA_BITACORA, STATUS_PACIENTE_CITA_BITACORA, HABITACION_PACIENTE_BITACORA,
								 ID_PROCEDIMIENTO_BITACORA, ID_MEDICO_BITACORA, ID_PACIENTE_BITACORA, ID_SALA_BITACORA, OBSERVACIONES_CITA_BITACORA, USUARIO_CITA_BITACORA, ID_CITA, FORMA_PAGO_CITA_BITACORA, ANESTESIOLOGO_CITA_BITACORA, PARTICIPANTE1_CITA_BITACORA, PARTICIPANTE2_CITA_BITACORA, TIPO_ID_BITACORA, TIPO_ID_MEDICO_BITACORA)
					 VALUES (SQ_CITA_BITACORA_CODIGO.NextVal,to_timestamp('$fecha_inicio', 'DD-MM-YYYY HH24:MI:SS'),to_timestamp('$fecha_fin', 'DD-MM-YYYY HH24:MI:SS'),'".$status_cita."', 
					 to_timestamp('$fecha_status', 'DD-MM-YYYY HH24:MI:SS'),'".$tipo_paciente."','".$hab_paciente."', '".$proced."'
					 ,'".$medico."', '".$id_paciente."','$sala','".$observ."','".$usuario."','".$cita."','".$forma_pago."','".$anestesiologo."','".$participante1."','".$participante2."','".$tipo_id_pac."','".$tipo_id_medico."')";			
					   
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
				
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
				
			function cargarCitasLibroBitacora($fecha_inicio, $fecha_final, $status, $sala, $proced, $forma, $aneste, $medico, $tipo_id, $paciente, $tipo_paciente, $tipo_id_medico){
			
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_CITA_BITACORA.ID_CITA_BITACORA,
					TO_CHAR(CITMED.T_CITA_BITACORA.FECHA_INICIO_CITA_BITACORA, 'DD-MM-YYYY HH24:MI:SS'),
					TO_CHAR(CITMED.T_CITA_BITACORA.FECHA_FIN_CITA_BITACORA, 'DD-MM-YYYY HH24:MI:SS'),
					CITMED.T_CITA_BITACORA.ID_PACIENTE_BITACORA,
					CITMED.T_PACIENTE.NOMBRE_PACIENTE,
					CITMED.T_PACIENTE.APELLIDO1_PACIENTE,
					CITMED.T_CITA_BITACORA.ID_MEDICO_BITACORA,
					CITMED.T_MEDICO.NOMBRE1_MEDICO,
					CITMED.T_MEDICO.APELLIDO1_MEDICO,
					CITMED.T_CITA_BITACORA.ID_PROCEDIMIENTO_BITACORA,
					(SELECT CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO= CITMED.T_CITA_BITACORA.ID_PROCEDIMIENTO_BITACORA UNION SELECT CITMED.T_PROCEDIMIENTO_COMPUESTO.NOMBRE_PROCEDIMIENTO_COMP FROM CITMED.T_PROCEDIMIENTO_COMPUESTO WHERE CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP= CITMED.T_CITA_BITACORA.ID_PROCEDIMIENTO_BITACORA),
					CITMED.T_CITA_BITACORA.ID_SALA_BITACORA,
					CITMED.T_SALA.NOMBRE_SALA,
					CITMED.T_CITA_BITACORA.STATUS_CITA_BITACORA,
					CITMED.T_CITA_BITACORA.FORMA_PAGO_CITA_BITACORA,
					CITMED.T_CITA_BITACORA.ANESTESIOLOGO_CITA_BITACORA,
					CITMED.T_CITA_BITACORA.STATUS_PACIENTE_CITA_BITACORA,
					CITMED.T_CITA_BITACORA.TIPO_ID_BITACORA,
					CITMED.T_CITA_BITACORA.ID_CITA,
					TO_CHAR(CITMED.T_CITA_BITACORA.FECHA_STATUS_CITA_BITACORA, 'DD-MM-YYYY HH24:MI:SS'),
					CITMED.T_CITA_BITACORA.USUARIO_CITA_BITACORA,
					CITMED.T_CITA_BITACORA.TIPO_ID_MEDICO_BITACORA
					FROM CITMED.T_CITA_BITACORA, CITMED.T_PACIENTE, CITMED.T_MEDICO, CITMED.T_SALA
					WHERE CITMED.T_CITA_BITACORA.TIPO_ID_BITACORA=CITMED.T_PACIENTE.TIPO_ID AND CITMED.T_CITA_BITACORA.TIPO_ID_MEDICO_BITACORA=CITMED.T_MEDICO.TIPO_ID AND CITMED.T_CITA_BITACORA.ID_PACIENTE_BITACORA=CITMED.T_PACIENTE.ID_PACIENTE AND CITMED.T_CITA_BITACORA.ID_SALA_BITACORA=CITMED.T_SALA.ID_SALA  AND CITMED.T_CITA_BITACORA.ID_MEDICO_BITACORA=CITMED.T_MEDICO.ID_MEDICO";		  
					
					
					if($fecha_inicio!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.FECHA_INICIO_CITA_BITACORA >= to_timestamp('$fecha_inicio', 'DD-MM-YYYY HH24:MI:SS')";
						
						}
						
					if($fecha_final!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.FECHA_FIN_CITA_BITACORA <= to_timestamp('$fecha_final', 'DD-MM-YYYY HH24:MI:SS')";
						
						}
						
					if($status!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.STATUS_CITA_BITACORA = '".$status."'";
						
						}
						
					if($sala!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.ID_SALA_BITACORA = '".$sala."'";
						
						}
						
					if($proced!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.ID_PROCEDIMIENTO_BITACORA = '".$proced."'";
						
						}
						
					if($forma!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.FORMA_PAGO_CITA_BITACORA = '".$forma."'";
						
						}
						
					if($aneste!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.ANESTESIOLOGO_CITA_BITACORA = '".$aneste."'";
						
						}
					
					if($tipo_id_medico!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.TIPO_ID_MEDICO_BITACORA = '".$tipo_id_medico."'";
						}
					
					if($medico!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.ID_MEDICO_BITACORA = '".$medico."'";
						}
						
					if($tipo_id!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.TIPO_ID_BITACORA = '".$tipo_id."'";
						}
						
					if($paciente!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.ID_PACIENTE_BITACORA = '".$paciente."'";
						}
						
					if($tipo_paciente!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.STATUS_PACIENTE_CITA_BITACORA = '".$tipo_paciente."'";
						}
					
					$query=$query." ORDER BY CITMED.T_CITA_BITACORA.FECHA_INICIO_CITA_BITACORA";
					
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$citas = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						if($row[15]!= "on"){
							$anes="No";
							}
						else
						{
							$anes="Si";
							}
						
						$nombre_paciente= $row[4]." ".$row[5];
						$nombre_medico = $row[7]." ".$row[8]; 
						
						$date1 = date_create($row[1]);
						$date2 = date_create($row[2]);
						$fecha= date_format($date1, 'd-m-Y');
						$fecha2= date_format($date1, 'G:ia');
						$fecha3= date_format($date2, 'G:ia');
						
						$citas[] = array("Cod.Cita"=>$row[18],"Fecha"=>$fecha,"Inicio Cita"=>$fecha2, "Final Cita" => $fecha3, "Status" => $row[13], "Sala" => $row[12],
						 "Procedimiento" => $row[10], "Pago" => $row[14], "Aneste." => $anes,
						  "Medico" => $nombre_medico, "Paciente" => $nombre_paciente, "Tipo" => $row[16], "Fecha Status" => $row[19], "Usuario" => $row[20]);
						
						}
					
					echo json_encode($citas);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}

		
		}
?>