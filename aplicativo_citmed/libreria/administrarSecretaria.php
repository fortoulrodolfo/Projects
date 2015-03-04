<?php
	class administrarSecretaria{
		
		private $conec, $link;
		
		function administrarSecretaria($conec,$link){
			$this->conec=$conec;
			$this->link=$link;
			}
			
		function buscarSecretaria($tipo, $codigo){
			
			if ($this->conec){
					
					$medicos = array();
				
					$query = "SELECT CITMED.T_SECRETARIA.TIPO_ID, CITMED.T_SECRETARIA.ID_SECRETARIA, CITMED.T_SECRETARIA.NOMBRE_SECRETARIA, CITMED.T_SECRETARIA.APELLIDO_SECRETARIA,CITMED.T_SECRETARIA.STATUS_SECRETARIA
							FROM CITMED.T_SECRETARIA WHERE CITMED.T_SECRETARIA.ID_SECRETARIA='".$codigo."' AND CITMED.T_SECRETARIA.TIPO_ID='".$tipo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					$row = oci_fetch_array ($stmt, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_MEDICO_SECRETARIA.ID_MEDICO, CITMED.T_MEDICO_SECRETARIA.TIPO_ID_MEDICO
					FROM CITMED.T_MEDICO_SECRETARIA WHERE CITMED.T_MEDICO_SECRETARIA.ID_SECRETARIA='".$codigo."' AND CITMED.T_MEDICO_SECRETARIA.TIPO_ID_SECRETARIA='".$tipo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					
					while($row2 = oci_fetch_array ($stmt2, OCI_NUM)){
						$id=$row2[1].'-'.$row2[0];
						$medicos[] = array("id"=>$id);
						
						}
						
					$secretaria=array("datos"=>$row, "medicos"=> $medicos);
								
					echo json_encode($secretaria);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		function buscarSecretaria2($tipo,$codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_SECRETARIA.TIPO_ID, CITMED.T_SECRETARIA.ID_SECRETARIA, CITMED.T_SECRETARIA.NOMBRE_SECRETARIA, CITMED.T_SECRETARIA.APELLIDO_SECRETARIA,CITMED.T_SECRETARIA.STATUS_SECRETARIA
							FROM CITMED.T_SECRETARIA WHERE CITMED.T_SECRETARIA.ID_SECRETARIA='".$codigo."' AND CITMED.T_SECRETARIA.TIPO_ID='".$tipo."'";	
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					return $row;
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		
		function registrarSecretaria($tipo,$codigo,$nombre,$apellido,$status,$medicos){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$secretaria = $this->buscarSecretaria2($tipo,$codigo);
				
				if ($secretaria == NULL){
					
				
					$query = "INSERT INTO CITMED.T_SECRETARIA (ID_SECRETARIA, NOMBRE_SECRETARIA, APELLIDO_SECRETARIA, STATUS_SECRETARIA, TIPO_ID) VALUES ('".$codigo."', '".$nombre."','".$apellido."','".$status."','".$tipo."')";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					if(count($medicos)>0){
						
						foreach ($medicos as $medico) {
							$arre=explode("-",$medico);
							$query2 = "INSERT INTO CITMED.T_MEDICO_SECRETARIA (TIPO_ID_MEDICO, ID_MEDICO, TIPO_ID_SECRETARIA, ID_SECRETARIA) VALUES ('".$arre[0]."','".$arre[1]."','".$tipo."','".$codigo."')";		  
							$stmt2 = oci_parse($this->conec, $query2);
							oci_execute($stmt2);
						}
						
					}
					
					echo "<script>
						alert('La secretaria fue registrada Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarSecretaria.php';
					  </script>";
					
					}
				
				else {
					
					echo "<script>
						alert('El codigo de la secretaria ya existe. Introduzca uno diferente');
						window.history.back();
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
		function actualizarSecretaria($tipo, $codigo,$nombre,$apellido,$status,$medicos){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$secretaria = $this->buscarSecretaria2($tipo, $codigo);
				
				if ($secretaria != NULL){
					
					
					$query = "UPDATE CITMED.T_SECRETARIA SET CITMED.T_SECRETARIA.NOMBRE_SECRETARIA = '".$nombre."',CITMED.T_SECRETARIA.APELLIDO_SECRETARIA = '".$apellido."',CITMED.T_SECRETARIA.STATUS_SECRETARIA = '".$status."' WHERE CITMED.T_SECRETARIA.ID_SECRETARIA = '".$codigo."' AND CITMED.T_SECRETARIA.TIPO_ID = '".$tipo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					$query3 = "DELETE FROM CITMED.T_MEDICO_SECRETARIA WHERE CITMED.T_MEDICO_SECRETARIA.ID_SECRETARIA = '".$codigo."' AND CITMED.T_MEDICO_SECRETARIA.TIPO_ID_SECRETARIA = '".$tipo."'";		  
					$stmt3 = oci_parse($this->conec, $query3);
					oci_execute($stmt3);
					
					if(count($medicos)>0){
						
						foreach ($medicos as $medico) {
							$arre=explode("-",$medico);
							$query2 = "INSERT INTO CITMED.T_MEDICO_SECRETARIA (TIPO_ID_MEDICO, ID_MEDICO, TIPO_ID_SECRETARIA, ID_SECRETARIA) VALUES ('".$arre[0]."','".$arre[1]."','".$tipo."','".$codigo."')";		  
							$stmt2 = oci_parse($this->conec, $query2);
							oci_execute($stmt2);
						}
						
					}
						echo "<script>
						alert('La Secretaria fue modificada Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarSecretaria.php';
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function eliminarSecretaria($tipo, $codigo){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$secretaria = $this->buscarSecretaria2($tipo,$codigo);
				
				if ($secretaria != NULL){
				
					$query1 = "SELECT CITMED.T_MEDICO_SECRETARIA.ID_SECRETARIA, CITMED.T_MEDICO_SECRETARIA.TIPO_ID_SECRETARIA
					 FROM CITMED.T_MEDICO_SECRETARIA WHERE CITMED.T_MEDICO_SECRETARIA.ID_SECRETARIA='".$codigo."' AND CITMED.T_MEDICO_SECRETARIA.TIPO_ID_SECRETARIA='".$tipo."'";		  
					$stmt1 = oci_parse($this->conec, $query1);
					oci_execute($stmt1);
					$medico = oci_fetch_array ($stmt1, OCI_NUM);
						
					if($medico!= NULL){
						
							echo "<script>
							alert('La secretaria no se puede eliminar ya que esta asociada a Medicos');
							window.location.href='".$this->link."citmed/administrarSistema/registrarSecretaria.php';
							</script>";
						
						}
					else
						{
										$query = "DELETE FROM CITMED.T_SECRETARIA WHERE CITMED.T_SECRETARIA.ID_SECRETARIA = '".$codigo."' AND CITMED.T_SECRETARIA.TIPO_ID='".$tipo."'";		  
										$stmt = oci_parse($this->conec, $query);
										oci_execute($stmt);
										
											echo "<script>
											alert('La secretaria fue eliminada Exitosamente');
											window.location.href='".$this->link."citmed/administrarSistema/registrarSecretaria.php';
											 </script>";
							
						
						}
					
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function cargarSecretarias(){
			
			if ($this->conec){
					$query = "SELECT  CITMED.T_SECRETARIA.TIPO_ID, CITMED.T_SECRETARIA.ID_SECRETARIA, CITMED.T_SECRETARIA.NOMBRE_SECRETARIA, CITMED.T_SECRETARIA.APELLIDO_SECRETARIA, CITMED.T_SECRETARIA.STATUS_SECRETARIA
					 FROM CITMED.T_SECRETARIA";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$secretarias = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$secretarias[] = array("tipo"=>$row[0], "id"=>$row[1], "nombre" => $row[2], "apellido"=>$row[3], "status"=>$row[4] );
						
						}
								
					echo json_encode($secretarias);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
				
		function buscarMedicoSecretaria($tipo_medico, $id_medico, $tipo_secre, $id_secre){
			
			if ($this->conec){
					
					$query = "SELECT CITMED.T_MEDICO_SECRETARIA.TIPO_ID_SECRETARIA, CITMED.T_MEDICO_SECRETARIA.ID_SECRETARIA, CITMED.T_MEDICO_SECRETARIA.ID_MEDICO, CITMED.T_MEDICO_SECRETARIA.TIPO_ID_MEDICO
					FROM CITMED.T_MEDICO_SECRETARIA WHERE CITMED.T_MEDICO_SECRETARIA.ID_SECRETARIA='".$id_secre."' AND CITMED.T_MEDICO_SECRETARIA.TIPO_ID_SECRETARIA='".$tipo_secre."' AND CITMED.T_MEDICO_SECRETARIA.ID_MEDICO='".$id_medico."' AND CITMED.T_MEDICO_SECRETARIA.TIPO_ID_MEDICO='".$tipo_medico."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo json_encode($row);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		}
		
?>