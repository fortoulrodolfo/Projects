<?php
	class administrarSala{
		
		private $conec, $link;
		
		function administrarSala($conec,$link){
			$this->conec=$conec;
			$this->link=$link;
			}
			
		function buscarSala($codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_SALA.ID_SALA, CITMED.T_SALA.NOMBRE_SALA, CITMED.T_SALA.STATUS_SALA, CITMED.T_SALA.ID_SERVICIO
					 FROM CITMED.T_SALA WHERE CITMED.T_SALA.ID_SALA='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo json_encode($row);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		function buscarSala2($codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_SALA.ID_SALA, CITMED.T_SALA.NOMBRE_SALA, CITMED.T_SALA.STATUS_SALA,CITMED.T_SALA.ID_SERVICIO
					 FROM CITMED.T_SALA WHERE CITMED.T_SALA.ID_SALA='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					return $row;
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		
		function registrarSala($codigo,$nombre,$status,$unidad){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$Sala = $this->buscarSala2($codigo);
				
				if ($Sala == NULL){
				
					$query = "INSERT INTO CITMED.T_SALA (ID_SALA, NOMBRE_SALA, STATUS_SALA, ID_SERVICIO) VALUES ('".$codigo."', '".$nombre."','".$status."','".$unidad."')";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El Sala fue registrado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarSala.php';
					  </script>";
					
					}
				
				else {
					
					echo "<script>
						alert('El codigo del Sala ya existe. Introduzca uno diferente');
						window.history.back();
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
		function actualizarSala($codigo,$nombre,$status,$unidad){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$Sala = $this->buscarSala2($codigo);
				
				if ($Sala != NULL){
				
					$query = "UPDATE CITMED.T_SALA SET CITMED.T_SALA.NOMBRE_SALA = '".$nombre."', CITMED.T_SALA.STATUS_SALA = '".$status."', CITMED.T_SALA.ID_SERVICIO = '".$unidad."'  WHERE CITMED.T_SALA.ID_SALA = '".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El Sala fue modificado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarSala.php';
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function eliminarSala($codigo){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$sala = $this->buscarSala2($codigo);
				
				if ($sala != NULL){
				
					$query1 = "SELECT CITMED.T_EQUIPO.ID_SALA
					 FROM CITMED.T_EQUIPO WHERE CITMED.T_EQUIPO.ID_SALA='".$codigo."'";		  
					$stmt1 = oci_parse($this->conec, $query1);
					oci_execute($stmt1);
					$equipo = oci_fetch_array ($stmt1, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_CITA.ID_SALA
					 FROM CITMED.T_CITA WHERE CITMED.T_CITA.ID_SALA='".$codigo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					$cita = oci_fetch_array ($stmt2, OCI_NUM);
					
					
					if($equipo!= NULL){
						
							echo "<script>
							alert('La sala no se puede eliminar ya que posee asociaciones de Equipos');
							window.location.href='".$this->link."citmed/administrarSistema/registrarSala.php';
							</script>";
						
						}
					else
						{
						
							if($cita!= NULL){
								
									echo "<script>
									alert('La sala no se puede eliminar ya que posee asociaciones de Citas');
									window.location.href='".$this->link."citmed/administrarSistema/registrarSala.php';
									</script>";
								
								}
							
							else{
								
									$query = "DELETE FROM CITMED.T_SALA WHERE CITMED.T_SALA.ID_SALA = '".$codigo."'";		  
										$stmt = oci_parse($this->conec, $query);
										oci_execute($stmt);
										
											echo "<script>
											alert('La sala fue eliminada Exitosamente');
											window.location.href='".$this->link."citmed/administrarSistema/registrarSala.php';
											 </script>";
							}
						
						}
					
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
			
			function cargarSalas($activa){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_SALA.ID_SALA,CITMED.T_SALA.NOMBRE_SALA FROM CITMED.T_SALA ";	
					
					if($activa!=""){
						$query= $query. " WHERE CITMED.T_SALA.STATUS_SALA = 'Activa'";
						}
						
					$query= $query. "ORDER BY CITMED.T_SALA.ID_SALA";  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$salas = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$salas[] = array("id"=>$row[0], "name" => $row[1]);
						
						}
								
					echo json_encode($salas);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
			
		}
		
?>