<?php
	class administrarProcedimiento{
		
		private $conec, $link;
		
		function administrarProcedimiento($conec,$link){
			$this->conec=$conec;
			$this->link=$link;
			}
			
		function buscarProcedimiento($codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.ID_TIPO_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.ID_SERVICIO, CITMED.T_PROCEDIMIENTO_BASICO.TIPO_ESTUDIO , CITMED.T_PROCEDIMIENTO_BASICO.TIEMPO_LIMPIEZA
					 FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo json_encode($row);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function buscarProcedimientoCita($codigo){
			
			if ($this->conec){
				
					$query1 = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO , CITMED.T_PROCEDIMIENTO_BASICO.TIEMPO_LIMPIEZA, CITMED.T_PROCEDIMIENTO_BASICO.ID_TIPO_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.ID_SERVICIO, CITMED.T_PROCEDIMIENTO_BASICO.TIPO_ESTUDIO
					 FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO='".$codigo."'";		  
					$stmt1 = oci_parse($this->conec, $query1);
					oci_execute($stmt1);
					$row1 = oci_fetch_array ($stmt1, OCI_NUM);
					
					if ($row1!= NULL){
						
						echo json_encode($row1);
					
					}
					else{
						
						$query2 = "SELECT DISTINCT CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP, CITMED.T_PROCEDIMIENTO_COMPUESTO.NOMBRE_PROCEDIMIENTO_COMP,
									(SELECT SUM(CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO) FROM CITMED.T_PROCED_BASICO_COMPUESTO,
									CITMED.T_PROCEDIMIENTO_BASICO, CITMED.T_PROCEDIMIENTO_COMPUESTO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO=CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO
									AND CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP=CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO_COMP AND CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP='".$codigo."') as TIEMPO_DURACION,
									 CITMED.T_PROCEDIMIENTO_COMPUESTO.TIEMPO_LIMPIEZA
									FROM CITMED.T_PROCEDIMIENTO_COMPUESTO, 
									  CITMED.T_PROCED_BASICO_COMPUESTO, CITMED.T_PROCEDIMIENTO_BASICO 
									  WHERE CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO_COMP=CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP
									  AND CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP='".$codigo."'";		  
						$stmt2 = oci_parse($this->conec, $query2);
						oci_execute($stmt2);
						$row2 = oci_fetch_array ($stmt2, OCI_NUM);
						echo json_encode($row2);
							}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
			function buscarProcedimientoEquipoCita($codigo){
			
			if ($this->conec){
					
					$procedimientos = array();
					
					$query1 = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO
					FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO='".$codigo."'";		  
					$stmt1 = oci_parse($this->conec, $query1);
					oci_execute($stmt1);
					$row1 = oci_fetch_array ($stmt1, OCI_NUM);
					
					if ($row1!= NULL){
						
						echo json_encode($row1);
					
					}
					else{
						
						$query2 = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO, CITMED.T_PROCED_BASICO_COMPUESTO.ORDEN_PROCEDIMIENTO
									FROM CITMED.T_PROCED_BASICO_COMPUESTO, CITMED.T_PROCEDIMIENTO_BASICO 
									  WHERE CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO=CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO
									  AND  CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO_COMP='".$codigo."' ORDER BY CITMED.T_PROCED_BASICO_COMPUESTO.ORDEN_PROCEDIMIENTO";		  
						$stmt2 = oci_parse($this->conec, $query2);
						oci_execute($stmt2);
						while($row2 = oci_fetch_array ($stmt2, OCI_NUM)){
						
						$procedimientos[] = array("id"=>$row2[0],"nombre"=>$row2[1], "orden"=> $row2[2]);
						
						}
						echo json_encode($procedimientos);
							}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}				
		
		
		function buscarProcedimiento2($codigo){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO
					 FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP
					 FROM CITMED.T_PROCEDIMIENTO_COMPUESTO WHERE CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP ='".$codigo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					$row2 = oci_fetch_array ($stmt2, OCI_NUM);
					
					if($row !=NULL){
						return $row;
						}
					else{
						return $row2;
						}
					
					
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		
		
		function registrarProcedimiento($codigo,$nombre,$duracion, $id_tipo_proced, $id_servicio, $estudio, $limpieza){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$Procedimiento = $this->buscarProcedimiento2($codigo);
				
				if ($Procedimiento == NULL){
				
					$query = "INSERT INTO CITMED.T_PROCEDIMIENTO_BASICO (ID_PROCEDIMIENTO, NOMBRE_PROCEDIMIENTO, DURACION_PROCEDIMIENTO, ID_TIPO_PROCEDIMIENTO, ID_SERVICIO, TIPO_ESTUDIO, TIEMPO_LIMPIEZA)
					 VALUES ('".$codigo."', '".$nombre."','".$duracion."','".$id_tipo_proced."','".$id_servicio."','".$estudio."','".$limpieza."')";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El Procedimiento fue registrado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarProcedimiento.php';
					  </script>";
					
					}
				
				else {
					
					echo "<script>
						alert('El codigo del Procedimiento o Procedimiento Compuesto ya existe. Introduzca uno diferente');
						window.history.back();
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
				
		function actualizarProcedimiento($codigo,$nombre,$duracion, $id_tipo_proced, $id_servicio, $estudio, $limpieza){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$Procedimiento = $this->buscarProcedimiento2($codigo);
				
				if ($Procedimiento != NULL){
				
					$query = "UPDATE CITMED.T_PROCEDIMIENTO_BASICO SET CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO = '".$nombre."', CITMED.T_PROCEDIMIENTO_BASICO.DURACION_PROCEDIMIENTO = '".$duracion."',
					 CITMED.T_PROCEDIMIENTO_BASICO.ID_TIPO_PROCEDIMIENTO = '".$id_tipo_proced."', CITMED.T_PROCEDIMIENTO_BASICO.ID_SERVICIO = '".$id_servicio."', CITMED.T_PROCEDIMIENTO_BASICO.TIPO_ESTUDIO = '".$estudio."', CITMED.T_PROCEDIMIENTO_BASICO.TIEMPO_LIMPIEZA = '".$limpieza."'
					 WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO = '".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					echo "<script>
						alert('El Procedimiento fue modificado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarProcedimiento.php';
					  </script>";
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function eliminarProcedimiento($codigo){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$procedimiento = $this->buscarProcedimiento2($codigo);
				
				if ($procedimiento != NULL){
				
					$query1 = "SELECT CITMED.T_CITA.ID_PROCEDIMIENTO
					 FROM CITMED.T_CITA WHERE CITMED.T_CITA.ID_PROCEDIMIENTO='".$codigo."'";		  
					$stmt1 = oci_parse($this->conec, $query1);
					oci_execute($stmt1);
					$cita = oci_fetch_array ($stmt1, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO
					 FROM CITMED.T_PROCED_BASICO_COMPUESTO WHERE CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO='".$codigo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					$compuesto = oci_fetch_array ($stmt2, OCI_NUM);
						
					if($cita!= NULL){
						
							echo "<script>
							alert('El  procedimiento no se puede eliminar ya que posee asociaciones de Citas');
							window.location.href='".$this->link."citmed/administrarSistema/registrarProcedimiento.php';
							</script>";
						
						}
					else
						{
							if($compuesto!= NULL){
								
								echo "<script>
								alert('El  procedimiento no se puede eliminar ya que posee asociaciones de Procedimientos Compuestos');
								window.location.href='".$this->link."citmed/administrarSistema/registrarProcedimiento.php';
								</script>";
								}
							else{
										$query = "DELETE FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO = '".$codigo."'";		  
										$stmt = oci_parse($this->conec, $query);
										oci_execute($stmt);
										
											echo "<script>
											alert('El procedimiento fue eliminado Exitosamente');
											window.location.href='".$this->link."citmed/administrarSistema/registrarProcedimiento.php';
											 </script>";
							
							}
						}
					
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function cargarProcedimiento(){
			
			if ($this->conec){
				
				
					$query = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.TIPO_ESTUDIO
					 FROM CITMED.T_PROCEDIMIENTO_BASICO";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$procedimiento = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$procedimiento[] = array("id"=>$row[0], "nombre" => $row[1], "estudio" => $row[2]);
						
						}
								
					echo json_encode($procedimiento);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
		function cargarProcedimientoEstudio($estudio){
			
			if ($this->conec){
				
					$query = "SELECT CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO, CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO
					 FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.TIPO_ESTUDIO='".$estudio."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$procedimiento = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$procedimiento[] = array("id"=>$row[0], "nombre" => $row[1]);
						
						}
								
					echo json_encode($procedimiento);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
					
				
					
		}
		
?>