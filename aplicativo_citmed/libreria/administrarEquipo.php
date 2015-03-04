<?php
	class administrarEquipo{
		
		private $conec, $link;
		
		function administrarEquipo($conec,$link){
			$this->conec=$conec;
			$this->link=$link;
			}
			
		function buscarEquipo($codigo){
			
			if ($this->conec){
				
				$procedimientos = array();
				
					$query = "SELECT CITMED.T_EQUIPO.ID_EQUIPO, CITMED.T_EQUIPO.NOMBRE_EQUIPO, CITMED.T_EQUIPO.STATUS_EQUIPO, CITMED.T_EQUIPO.EQUIPO_FIJO, CITMED.T_EQUIPO.ID_SALA
					 FROM CITMED.T_EQUIPO WHERE CITMED.T_EQUIPO.ID_EQUIPO='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_EQUIPO_PROCEDIMIENTO.ID_PROCEDIMIENTO
					FROM CITMED.T_EQUIPO_PROCEDIMIENTO WHERE CITMED.T_EQUIPO_PROCEDIMIENTO.ID_EQUIPO='".$codigo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					
					while($row2 = oci_fetch_array ($stmt2, OCI_NUM)){
						
						$procedimientos[] = array("id"=>$row2[0]);
						
						}
						
					$equipo=array("datos"=>$row, "procedimientos"=> $procedimientos);
								
					echo json_encode($equipo);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		function buscarEquipo2($codigo){
			
			if ($this->conec){
				
					$query =  "SELECT CITMED.T_EQUIPO.ID_EQUIPO, CITMED.T_EQUIPO.NOMBRE_EQUIPO, CITMED.T_EQUIPO.STATUS_EQUIPO, CITMED.T_EQUIPO.EQUIPO_FIJO, CITMED.T_EQUIPO.ID_SALA
					 FROM CITMED.T_EQUIPO WHERE CITMED.T_EQUIPO.ID_EQUIPO='".$codigo."'";			  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					return $row;
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		
		function registrarEquipo($codigo,$nombre,$status,$fijo,$sala,$procedimientos){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$Equipo = $this->buscarEquipo2($codigo);
				
				if ($Equipo == NULL){
				
					$query = "INSERT INTO CITMED.T_EQUIPO (ID_EQUIPO, NOMBRE_EQUIPO,STATUS_EQUIPO,EQUIPO_FIJO,ID_SALA) 
					VALUES ('".$codigo."', '".$nombre."','".$status."','".$fijo."','".$sala."')";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					
					if(count($procedimientos)>0){
					foreach ($procedimientos as $procedimiento) {
							$query2 = "INSERT INTO CITMED.T_EQUIPO_PROCEDIMIENTO (ID_PROCEDIMIENTO, ID_EQUIPO) VALUES ('".$procedimiento."','".$codigo."')";		  
							$stmt2 = oci_parse($this->conec, $query2);
							oci_execute($stmt2);
						}
					}
					
					echo "<script>
						alert('El Equipo fue registrado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarEquipo.php';
					  </script>";
					
					}
				
				else {
					
					echo "<script>
						alert('El codigo del Equipo ya existe. Introduzca uno diferente');
						window.history.back();
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
		function actualizarEquipo($codigo,$nombre,$status,$fijo,$sala,$procedimientos){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$equipo = $this->buscarEquipo2($codigo);
				
				if ($equipo != NULL){
				
					$query = "UPDATE CITMED.T_EQUIPO SET CITMED.T_EQUIPO.NOMBRE_EQUIPO = '".$nombre."', CITMED.T_EQUIPO.STATUS_EQUIPO = '".$status."', CITMED.T_EQUIPO.EQUIPO_FIJO = '".$fijo."', CITMED.T_EQUIPO.ID_SALA = '".$sala."'
					 WHERE CITMED.T_EQUIPO.ID_EQUIPO = '".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					$query3 = "DELETE FROM CITMED.T_EQUIPO_PROCEDIMIENTO WHERE CITMED.T_EQUIPO_PROCEDIMIENTO.ID_EQUIPO = '".$codigo."'";		  
					$stmt3 = oci_parse($this->conec, $query3);
					oci_execute($stmt3);
					if(count($procedimientos)>0){
					foreach ($procedimientos as $procedimiento) {
							$query2 = "INSERT INTO CITMED.T_EQUIPO_PROCEDIMIENTO (ID_PROCEDIMIENTO, ID_EQUIPO) VALUES ('".$procedimiento."','".$codigo."')";			  
							$stmt2 = oci_parse($this->conec, $query2);
							oci_execute($stmt2);
						}
					}
					
					echo "<script>
						alert('El Equipo fue modificado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarEquipo.php';
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function eliminarEquipo($codigo){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$equipo = $this->buscarEquipo2($codigo);
				
				if ($equipo != NULL){
				
					$query1 = "SELECT CITMED.T_CITA_EQUIPO.ID_EQUIPO
					 FROM CITMED.T_CITA_EQUIPO WHERE CITMED.T_CITA_EQUIPO.ID_EQUIPO='".$codigo."'";		  
					$stmt1 = oci_parse($this->conec, $query1);
					oci_execute($stmt1);
					$cita = oci_fetch_array ($stmt1, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_EQUIPO_PROCEDIMIENTO.ID_EQUIPO
					 FROM CITMED.T_EQUIPO_PROCEDIMIENTO WHERE CITMED.T_EQUIPO_PROCEDIMIENTO.ID_EQUIPO='".$codigo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					$proced = oci_fetch_array ($stmt2, OCI_NUM);
						
					if($cita!= NULL){
						
							echo "<script>
							alert('El equipo no se puede eliminar ya que posee asociaciones de Citas');
							window.location.href='".$this->link."citmed/administrarSistema/registrarEquipo.php';
							</script>";
						
						}
					elseif($proced!= NULL)
						{
							
							echo "<script>
							alert('El equipo no se puede eliminar ya que posee asociaciones de Procedimientos');
							window.location.href='".$this->link."citmed/administrarSistema/registrarEquipo.php';
							</script>";
							
							}
							else{
										$query = "DELETE FROM CITMED.T_EQUIPO WHERE CITMED.T_EQUIPO.ID_EQUIPO = '".$codigo."'";		  
										$stmt = oci_parse($this->conec, $query);
										oci_execute($stmt);
										
											echo "<script>
											alert('El equipo fue eliminado Exitosamente');
											window.location.href='".$this->link."citmed/administrarSistema/registrarEquipo.php';
											 </script>";
							
						
						}
					
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		function cargarEquipo(){
			
			if ($this->conec){
				
				
					$query = "SELECT CITMED.T_EQUIPO.ID_EQUIPO, CITMED.T_EQUIPO.NOMBRE_EQUIPO, CITMED.T_EQUIPO.STATUS_EQUIPO, CITMED.T_EQUIPO.EQUIPO_FIJO,
					 CITMED.T_SALA.NOMBRE_SALA
					 FROM CITMED.T_EQUIPO, CITMED.T_SALA WHERE CITMED.T_EQUIPO.ID_SALA=CITMED.T_SALA.ID_SALA
					 ";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$equipos = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$equipos[] = array("id"=>$row[0], "nombre" => $row[1], "status" => $row[2], "fijo" => $row[3], "sala" => $row[4]);
						
						}
								
					echo json_encode($equipos);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
					
		
		
		}
		
?>