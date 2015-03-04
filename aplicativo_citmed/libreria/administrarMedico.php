<?php
	class administrarMedico{
		
		private $conec, $link;
		
		function administrarMedico($conec,$link){
			$this->conec=$conec;
			$this->link=$link;
			}
			
		function buscarMedico($tipo, $codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_MEDICO.TIPO_ID, CITMED.T_MEDICO.ID_MEDICO, CITMED.T_MEDICO.NOMBRE1_MEDICO, CITMED.T_MEDICO.NOMBRE2_MEDICO,CITMED.T_MEDICO.APELLIDO1_MEDICO,CITMED.T_MEDICO.APELLIDO2_MEDICO,
					CITMED.T_MEDICO.STATUS_MEDICO,CITMED.T_MEDICO.ID_SERVICIO
					 FROM CITMED.T_MEDICO WHERE CITMED.T_MEDICO.ID_MEDICO='".$codigo."' AND CITMED.T_MEDICO.TIPO_ID='".$tipo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo json_encode($row);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		function buscarMedico2($tipo,$codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_MEDICO.TIPO_ID, CITMED.T_MEDICO.ID_MEDICO, CITMED.T_MEDICO.NOMBRE1_MEDICO, CITMED.T_MEDICO.NOMBRE2_MEDICO,CITMED.T_MEDICO.APELLIDO1_MEDICO,CITMED.T_MEDICO.APELLIDO2_MEDICO,
					CITMED.T_MEDICO.STATUS_MEDICO,CITMED.T_MEDICO.ID_SERVICIO
					 FROM CITMED.T_MEDICO WHERE CITMED.T_MEDICO.ID_MEDICO='".$codigo."' AND CITMED.T_MEDICO.TIPO_ID='".$tipo."'";	
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					return $row;
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		
		function registrarMedico($tipo, $codigo,$nombre,$status,$unidad){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$medico = $this->buscarMedico2($tipo,$codigo);
				
				if ($medico == NULL){
				
					$query = "INSERT INTO CITMED.T_MEDICO (TIPO_ID, ID_MEDICO, NOMBRE1_MEDICO, STATUS_MEDICO, ID_SERVICIO) VALUES ('".$tipo."', '".$codigo."', '".$nombre."','".$status."','".$unidad."')";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El Medico fue registrado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarMedico.php';
					  </script>";
					
					}
				
				else {
					
					echo "<script>
						alert('El codigo del Medico ya existe. Introduzca uno diferente');
						window.history.back();
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
		function actualizarMedico($tipo, $codigo,$status,$unidad){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$medico = $this->buscarMedico2($tipo,$codigo);
				
				if ($medico != NULL){
				
					$query = "UPDATE CITMED.T_MEDICO SET CITMED.T_MEDICO.STATUS_MEDICO = '".$status."', CITMED.T_MEDICO.ID_SERVICIO = '".$unidad."' WHERE CITMED.T_MEDICO.ID_MEDICO = '".$codigo."' AND CITMED.T_MEDICO.TIPO_ID='".$tipo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El Medico fue modificado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarMedico.php';
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function eliminarMedico($tipo, $codigo){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$medico = $this->buscarMedico2($tipo,$codigo);
				
				if ($medico != NULL){
				
					$query1 = "SELECT CITMED.T_CITA.ID_MEDICO, CITMED.T_CITA.TIPO_ID_MEDICO
					 FROM CITMED.T_CITA WHERE CITMED.T_CITA.ID_MEDICO='".$codigo."' AND CITMED.T_CITA.TIPO_ID_MEDICO='".$tipo."'";		  
					$stmt1 = oci_parse($this->conec, $query1);
					oci_execute($stmt1);
					$cita = oci_fetch_array ($stmt1, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_MEDICO_SECRETARIA.ID_MEDICO, CITMED.T_MEDICO_SECRETARIA.TIPO_ID_MEDICO
					 FROM CITMED.T_MEDICO_SECRETARIA WHERE CITMED.T_MEDICO_SECRETARIA.ID_MEDICO='".$codigo."' AND CITMED.T_MEDICO_SECRETARIA.TIPO_ID_MEDICO='".$tipo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					$secretaria = oci_fetch_array ($stmt2, OCI_NUM);
						
					if($cita!= NULL){
						
							echo "<script>
							alert('El medico no se puede eliminar ya que posee asociaciones de Citas');
							window.location.href='".$this->link."citmed/administrarSistema/registrarMedico.php';
							</script>";
						
						}
					else
						{
							if($secretaria!= NULL){
						
							echo "<script>
							alert('El medico no se puede eliminar ya que posee secretarias asociadas');
							window.location.href='".$this->link."citmed/administrarSistema/registrarMedico.php';
							</script>";
						
						}
						else
							{	
										$query = "DELETE FROM CITMED.T_MEDICO WHERE CITMED.T_MEDICO.ID_MEDICO = '".$codigo."' AND CITMED.T_MEDICO.TIPO_ID='".$tipo."'";		  
										$stmt = oci_parse($this->conec, $query);
										oci_execute($stmt);
										
											echo "<script>
											alert('El Medico fue eliminado Exitosamente');
											window.location.href='".$this->link."citmed/administrarSistema/registrarMedico.php';
											 </script>";
							
							}
						}
					
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function cargarMedicos(){
			
			if ($this->conec){
					$query = "SELECT CITMED.T_MEDICO.TIPO_ID, CITMED.T_MEDICO.ID_MEDICO, CITMED.T_MEDICO.NOMBRE1_MEDICO, CITMED.T_MEDICO.NOMBRE2_MEDICO,CITMED.T_MEDICO.APELLIDO1_MEDICO,CITMED.T_MEDICO.APELLIDO2_MEDICO
					 FROM CITMED.T_MEDICO WHERE CITMED.T_MEDICO.STATUS_MEDICO='Activo'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$meicos = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						$id= $row[0]."-".$row[1];
						$medicos[] = array("id"=>$id, "nombre" => $row[2]." ".$row[4] );
						
						}
								
					echo json_encode($medicos);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function cargarTablaMedicos(){
			
			if ($this->conec){
					$query = "SELECT CITMED.T_MEDICO.TIPO_ID, CITMED.T_MEDICO.ID_MEDICO, CITMED.T_MEDICO.NOMBRE1_MEDICO, CITMED.T_MEDICO.NOMBRE2_MEDICO,CITMED.T_MEDICO.APELLIDO1_MEDICO,CITMED.T_MEDICO.APELLIDO2_MEDICO,
					CITMED.T_MEDICO.ID_SERVICIO, CITMED.T_MEDICO.STATUS_MEDICO
					 FROM CITMED.T_MEDICO";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$medicos = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$medicos[] = array("tipo"=>$row[0], "id"=>$row[1], "nombre" => $row[2]." ".$row[4], "unidad"=>$row[6], "status"=>$row[7] );
						
						}
								
					echo json_encode($medicos);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		}
		
?>