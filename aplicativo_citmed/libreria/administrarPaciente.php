<?php
	class administrarPaciente{
		
		private $conec, $link;
		
		function administrarPaciente($conec,$link){
			$this->conec=$conec;
			$this->link=$link;
			}
			
		function buscarPaciente($tipo_id, $codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_PACIENTE.TIPO_ID,CITMED.T_PACIENTE.ID_PACIENTE, CITMED.T_PACIENTE.NOMBRE_PACIENTE, CITMED.T_PACIENTE.APELLIDO1_PACIENTE, CITMED.T_PACIENTE.APELLIDO2_PACIENTE, CITMED.T_PACIENTE.SEXO_PACIENTE, TO_CHAR(CITMED.T_PACIENTE.FECHA_NAC_PACIENTE, 'YYYY-MM-DD'), CITMED.T_PACIENTE.TELEFONO_PACIENTE
					 FROM CITMED.T_PACIENTE WHERE CITMED.T_PACIENTE.ID_PACIENTE='".$codigo."' AND CITMED.T_PACIENTE.TIPO_ID='".$tipo_id."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo json_encode($row);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		function buscarPaciente2($tipo_id, $codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_PACIENTE.TIPO_ID,CITMED.T_PACIENTE.ID_PACIENTE, CITMED.T_PACIENTE.NOMBRE_PACIENTE, CITMED.T_PACIENTE.APELLIDO1_PACIENTE, CITMED.T_PACIENTE.APELLIDO2_PACIENTE, CITMED.T_PACIENTE.SEXO_PACIENTE, CITMED.T_PACIENTE.FECHA_NAC_PACIENTE, CITMED.T_PACIENTE.TELEFONO_PACIENTE
					 FROM CITMED.T_PACIENTE WHERE CITMED.T_PACIENTE.ID_PACIENTE='".$codigo."' AND CITMED.T_PACIENTE.TIPO_ID='".$tipo_id."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					return $row;
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		
		function registrarPaciente($tipo_id, $codigo, $nombre, $apellido1, $apellido2, $genero, $fecha_nac, $telf, $fecha_cita){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$paciente = $this->buscarPaciente2($tipo_id, $codigo);
				
				if ($paciente == NULL){
					
				
					$query = "INSERT INTO CITMED.T_PACIENTE (TIPO_ID, ID_PACIENTE, NOMBRE_PACIENTE, APELLIDO1_PACIENTE, APELLIDO2_PACIENTE, SEXO_PACIENTE, FECHA_NAC_PACIENTE, TELEFONO_PACIENTE) VALUES ('".$tipo_id."','".$codigo."', '".$nombre."','".$apellido1."', '".$apellido2."', '".$genero."',to_timestamp('$fecha_nac', 'YYYY-MM-DD'), '".$telf."')";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					if($fecha_cita=="null"){
					
						echo "<script>
							alert('El Paciente fue registrado Exitosamente');
							window.location.href='".$this->link."citmed/registrarPaciente.php';
						  </script>";
					}
					else{
						
						echo "<script>
							alert('El Paciente fue registrado Exitosamente');
							window.location.href='".$this->link."citmed/administrarCitas/registrarCita.php?fecha=$fecha_cita';
						  </script>";
						}
					
					}
				
				else {
					
					echo "<script>
						alert('El codigo del Paciente ya existe. Introduzca uno diferente');
						window.history.back();
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function registrarPacienteSERVINTE($tipo_id, $codigo, $nombre, $apellido1, $apellido2, $genero, $fecha_nac, $telf){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$paciente = $this->buscarPaciente2($tipo_id, $codigo);
				
				if ($paciente == NULL){
					
					$query = "INSERT INTO CITMED.T_PACIENTE (TIPO_ID, ID_PACIENTE, NOMBRE_PACIENTE, APELLIDO1_PACIENTE, APELLIDO2_PACIENTE, SEXO_PACIENTE, FECHA_NAC_PACIENTE, TELEFONO_PACIENTE) VALUES ('".$tipo_id."','".$codigo."', '".$nombre."','".$apellido1."', '".$apellido2."', '".$genero."',to_timestamp('$fecha_nac', 'YYYY-MM-DD'), '".$telf."')";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo $row;
					
					}
				
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
		function actualizarPaciente($tipo_id, $codigo, $nombre, $apellido1, $apellido2, $genero, $fecha_nac, $telf){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$paciente = $this->buscarPaciente2($tipo_id, $codigo);
				
				if ($paciente != NULL){
				
					$query = "UPDATE CITMED.T_PACIENTE SET CITMED.T_PACIENTE.NOMBRE_PACIENTE = '".$nombre."', CITMED.T_PACIENTE.APELLIDO1_PACIENTE = '".$apellido1."', 
					CITMED.T_PACIENTE.APELLIDO2_PACIENTE = '".$apellido2."', CITMED.T_PACIENTE.SEXO_PACIENTE = '".$genero."', CITMED.T_PACIENTE.FECHA_NAC_PACIENTE = to_timestamp('$fecha_nac', 'DD-MM-YYYY'), CITMED.T_PACIENTE.FECHA_NAC_PACIENTE = '".$telf."' WHERE CITMED.T_PACIENTE.ID_PACIENTE='".$codigo."' AND CITMED.T_PACIENTE.TIPO_ID='".$tipo_id."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El Paciente fue modificado Exitosamente');
						window.location.href='".$this->link."citmed/registrarPaciente.php';
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function eliminarPaciente($tipo_id,$codigo){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$paciente = $this->buscarPaciente2($tipo_id, $codigo);
				
				if ($paciente != NULL){
				
					$query1 = "SELECT CITMED.T_CITA.ID_PACIENTE, CITMED.T_CITA.TIPO_ID
					 FROM CITMED.T_CITA WHERE CITMED.T_CITA.ID_PACIENTE='".$codigo."' AND CITMED.T_CITA.TIPO_ID='".$tipo_id."'";		  
					$stmt1 = oci_parse($this->conec, $query1);
					oci_execute($stmt1);
					$cita = oci_fetch_array ($stmt1, OCI_NUM);
						
					if($cita!= NULL){
						
							echo "<script>
							alert('El paciente no se puede eliminar ya que esta asociaciones en Citas');
							window.location.href='".$this->link."citmed/registrarPaciente.php';
							</script>";
						
						}
					else
						{
										$query = "DELETE FROM CITMED.T_PACIENTE WHERE CITMED.T_PACIENTE.ID_PACIENTE='".$codigo."' AND CITMED.T_PACIENTE.TIPO_ID='".$tipo_id."'";		  
										$stmt = oci_parse($this->conec, $query);
										oci_execute($stmt);
										
											echo "<script>
											alert('El paciente fue eliminado Exitosamente');
											window.location.href='".$this->link."citmed/registrarPaciente.php';
											 </script>";
							
						
						}
					
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		}
		
?>