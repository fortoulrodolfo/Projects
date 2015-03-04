<?php
	class administrarServicio{
		
		private $conec, $link;
		
		function administrarServicio($conec,$link){
			$this->conec=$conec;
			$this->link=$link;
			}
			
		function buscarServicio($codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_SERVICIO.ID_SERVICIO, CITMED.T_SERVICIO.NOMBRE_SERVICIO, CITMED.T_SERVICIO.VALIDAR_EQUIPO
					 FROM CITMED.T_SERVICIO WHERE CITMED.T_SERVICIO.ID_SERVICIO='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo json_encode($row);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
				}
				
		function buscarValidarEquipo($codigo_sala){
			
			if ($this->conec){
				
					$query = "SELECT DISTINCT CITMED.T_SERVICIO.ID_SERVICIO, CITMED.T_SERVICIO.NOMBRE_SERVICIO, CITMED.T_SERVICIO.VALIDAR_EQUIPO
					 FROM CITMED.T_SERVICIO, CITMED.T_SALA WHERE CITMED.T_SERVICIO.ID_SERVICIO=CITMED.T_SALA.ID_SERVICIO AND CITMED.T_SALA.ID_SALA='".$codigo_sala."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo json_encode($row);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function buscarServicio2($codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_SERVICIO.ID_SERVICIO, CITMED.T_SERVICIO.NOMBRE_SERVICIO, CITMED.T_SERVICIO.VALIDAR_EQUIPO
					 FROM CITMED.T_SERVICIO WHERE CITMED.T_SERVICIO.ID_SERVICIO='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					return $row;
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		
		function registrarServicio($codigo,$nombre,$validar){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$servicio = $this->buscarServicio2($codigo);
				
				if ($servicio == NULL){
				
					$query = "INSERT INTO CITMED.T_SERVICIO (ID_SERVICIO, NOMBRE_SERVICIO, VALIDAR_EQUIPO) VALUES ('".$codigo."', '".$nombre."','".$validar."')";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El servicio fue registrado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarServicio.php';
					  </script>";
					
					}
				
				else {
					
					echo "<script>
						alert('El codigo del servicio ya existe. Introduzca uno diferente');
						window.history.back();
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
		function actualizarServicio($codigo,$nombre,$validar){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$servicio = $this->buscarServicio2($codigo);
				
				if ($servicio != NULL){
				
					$query = "UPDATE CITMED.T_SERVICIO SET CITMED.T_SERVICIO.NOMBRE_SERVICIO = '".$nombre."', CITMED.T_SERVICIO.VALIDAR_EQUIPO = '".$validar."' WHERE CITMED.T_SERVICIO.ID_SERVICIO = '".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El servicio fue modificado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarServicio.php';
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function eliminarServicio($codigo){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$servicio = $this->buscarServicio2($codigo);
				
				if ($servicio != NULL){
					
					
					$query1 = "SELECT CITMED.T_SALA.ID_SERVICIO
					 FROM CITMED.T_SALA WHERE CITMED.T_SALA.ID_SERVICIO='".$codigo."'";		  
					$stmt1 = oci_parse($this->conec, $query1);
					oci_execute($stmt1);
					$sala = oci_fetch_array ($stmt1, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_MEDICO.ID_SERVICIO
					 FROM CITMED.T_MEDICO WHERE CITMED.T_MEDICO.ID_SERVICIO='".$codigo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					$medico = oci_fetch_array ($stmt2, OCI_NUM);
					
					$query3 = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_SERVICIO
					 FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_SERVICIO='".$codigo."'";		  
					$stmt3 = oci_parse($this->conec, $query3);
					oci_execute($stmt3);
					$procedimiento = oci_fetch_array ($stmt3, OCI_NUM);
					
					if($sala!= NULL){
						
							echo "<script>
							alert('El servicio no se puede eliminar ya que posee asociaciones de Salas');
							window.location.href='".$this->link."citmed/administrarSistema/registrarServicio.php';
							</script>";
						
						}
					else
						{
						
							if($medico!= NULL){
								
									echo "<script>
									alert('El servicio no se puede eliminar ya que posee asociaciones de Medicos');
									window.location.href='".$this->link."citmed/administrarSistema/registrarServicio.php';
									</script>";
								
								}
							
							else{
								
									if($procedimiento!= NULL){
													
											echo "<script>
											alert('El servicio no se puede eliminar ya que posee asociaciones de Procedimientos');
											window.location.href='".$this->link."citmed/administrarSistema/registrarServicio.php';
											</script>";
										
										}
										
									else{
								
										$query = "DELETE FROM CITMED.T_SERVICIO WHERE CITMED.T_SERVICIO.ID_SERVICIO = '".$codigo."'";		  
										$stmt = oci_parse($this->conec, $query);
										oci_execute($stmt);
										
											echo "<script>
											alert('El servicio fue eliminado Exitosamente');
											window.location.href='".$this->link."citmed/administrarSistema/registrarServicio.php';
											 </script>";
									}
							}
						
						}
					
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
			function cargarUnidad(){
			
			if ($this->conec){
				
				
					$query = "SELECT CITMED.T_SERVICIO.ID_SERVICIO, CITMED.T_SERVICIO.NOMBRE_SERVICIO
					 FROM CITMED.T_SERVICIO";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$unidad = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$unidad[] = array("id"=>$row[0], "nombre" => $row[1]);
						
						}
								
					echo json_encode($unidad);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		}
		
?>