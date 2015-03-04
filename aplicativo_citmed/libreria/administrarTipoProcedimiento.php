<?php
	class administrarTipoProcedimiento{
		
		private $conec, $link;
		
		function administrarTipoProcedimiento($conec,$link){
			$this->conec=$conec;
			$this->link=$link;
			}
			
		function buscarTipo($codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_TIPO_PROCEDIMIENTO.ID_TIPO_PROCEDIMIENTO, CITMED.T_TIPO_PROCEDIMIENTO.NOMBRE_TIPO_PROCEDIMIENTO 
					 FROM CITMED.T_TIPO_PROCEDIMIENTO WHERE CITMED.T_TIPO_PROCEDIMIENTO.ID_TIPO_PROCEDIMIENTO='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo json_encode($row);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		function buscarTipo2($codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_TIPO_PROCEDIMIENTO.ID_TIPO_PROCEDIMIENTO
					 FROM CITMED.T_TIPO_PROCEDIMIENTO WHERE CITMED.T_TIPO_PROCEDIMIENTO.ID_TIPO_PROCEDIMIENTO='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					return $row;
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		
		function registrarTipo($codigo,$nombre){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$Tipo = $this->buscarTipo2($codigo);
				
				if ($Tipo == NULL){
				
					$query = "INSERT INTO CITMED.T_TIPO_PROCEDIMIENTO (ID_TIPO_PROCEDIMIENTO, NOMBRE_TIPO_PROCEDIMIENTO)
					 VALUES ('".$codigo."', '".$nombre."')";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El Tipo de procedimiento fue registrado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarTipoProcedimiento.php';
					  </script>";
					
					}
				
				else {
					
					echo "<script>
						alert('El codigo del Tipo Procedimiento ya existe. Introduzca uno diferente');
						window.history.back();
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
		function actualizarTipo($codigo,$nombre){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$Tipo = $this->buscarTipo2($codigo);
				
				if ($Tipo != NULL){
				
					$query = "UPDATE CITMED.T_TIPO_PROCEDIMIENTO SET CITMED.T_TIPO_PROCEDIMIENTO.NOMBRE_TIPO_PROCEDIMIENTO = '".$nombre."' 
					WHERE CITMED.T_TIPO_PROCEDIMIENTO.ID_TIPO_PROCEDIMIENTO = '".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El Tipo de Procedimiento fue modificado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarTipoProcedimiento.php';
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function eliminarTipo($codigo){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$tipo = $this->buscarTipo2($codigo);
				
				if ($tipo != NULL){
				
					
					$query2 = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_TIPO_PROCEDIMIENTO
					 FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_TIPO_PROCEDIMIENTO='".$codigo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					$procedimiento = oci_fetch_array ($stmt2, OCI_NUM);
					
							if($procedimiento!= NULL){
								
									echo "<script>
									alert('El tipo de procedimiento no se puede eliminar ya que posee asociaciones de Procedimientos');
									window.location.href='".$this->link."citmed/administrarSistema/registrarTipoProcedimiento.php';
									</script>";
								
								}
							
							else{
								
									$query = "DELETE FROM CITMED.T_TIPO_PROCEDIMIENTO WHERE CITMED.T_TIPO_PROCEDIMIENTO.ID_TIPO_PROCEDIMIENTO = '".$codigo."'";		  
										$stmt = oci_parse($this->conec, $query);
										oci_execute($stmt);
										
											echo "<script>
											alert('El tipo de procedimiento fue eliminado Exitosamente');
											window.location.href='".$this->link."citmed/administrarSistema/registrarTipoProcedimiento.php';
											 </script>";
							}
						
					
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function cargarTiposProcedimiento(){
			
			if ($this->conec){
				
				
					$query = "SELECT CITMED.T_TIPO_PROCEDIMIENTO.ID_TIPO_PROCEDIMIENTO, CITMED.T_TIPO_PROCEDIMIENTO.NOMBRE_TIPO_PROCEDIMIENTO
					 FROM CITMED.T_TIPO_PROCEDIMIENTO";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$tipos = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$tipos[] = array("id"=>$row[0], "nombre" => $row[1]);
						
						}
								
					echo json_encode($tipos);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
					
		}
		
?>