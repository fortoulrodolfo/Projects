<?php
	class administrarProcedimientoCompuesto{
		
		private $conec, $link;
		
		function administrarProcedimientoCompuesto($conec,$link){
			$this->conec=$conec;
			$this->link=$link;
			}
			
		function buscarProcedimientoCompuesto($codigo){
			
			if ($this->conec){
				
				$procedimientos = array();
				
					$query = "SELECT CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP, CITMED.T_PROCEDIMIENTO_COMPUESTO.NOMBRE_PROCEDIMIENTO_COMP, CITMED.T_PROCEDIMIENTO_COMPUESTO.TIEMPO_LIMPIEZA
							FROM CITMED.T_PROCEDIMIENTO_COMPUESTO WHERE CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP='".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					$row = oci_fetch_array ($stmt, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO, CITMED.T_PROCED_BASICO_COMPUESTO.ORDEN_PROCEDIMIENTO
					FROM CITMED.T_PROCED_BASICO_COMPUESTO WHERE CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO_COMP='".$codigo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					
					while($row2 = oci_fetch_array ($stmt2, OCI_NUM)){
						
						$procedimientos[] = array("id"=>$row2[0], "orden"=> $row2[1]);
						
						}
						
					$compuesto=array("datos"=>$row, "procedimientos"=> $procedimientos);
								
					echo json_encode($compuesto);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		function buscarProcedimientoCompuesto2($codigo){
			
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
				
		
		
		function registrarProcedimientoCompuesto($codigo,$nombre,$limpieza,$procedimientos){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$compuesto = $this->buscarProcedimientoCompuesto2($codigo);
				
				if ($compuesto == NULL){
				
					$query = "INSERT INTO CITMED.T_PROCEDIMIENTO_COMPUESTO (ID_PROCEDIMIENTO_COMP, NOMBRE_PROCEDIMIENTO_COMP, TIEMPO_LIMPIEZA)
					 VALUES ('".$codigo."', '".$nombre."','".$limpieza."')";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					if(count($procedimientos)>0){
						$i=0;
						foreach ($procedimientos as $procedimiento) {
								
								if($i<count($procedimientos)){
									$query2 = "INSERT INTO CITMED.T_PROCED_BASICO_COMPUESTO (ID_PROCEDIMIENTO, ID_PROCEDIMIENTO_COMP, ORDEN_PROCEDIMIENTO) VALUES ('".$procedimiento."','".$codigo."','".$i."')";			  
									$stmt2 = oci_parse($this->conec, $query2);
									oci_execute($stmt2);
									++$i;
									}
							}
					}
					
					echo "<script>
						alert('El Procedimiento Compuesto fue registrado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarProcedimientoCompuesto.php';
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
				
				
		function actualizarProcedimientoCompuesto($codigo,$nombre,$limpieza,$procedimientos){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$compuesto = $this->buscarProcedimientoCompuesto2($codigo);
				
				if ($compuesto != NULL){
				
					$query = "UPDATE CITMED.T_PROCEDIMIENTO_COMPUESTO SET CITMED.T_PROCEDIMIENTO_COMPUESTO.NOMBRE_PROCEDIMIENTO_COMP = '".$nombre."', CITMED.T_PROCEDIMIENTO_COMPUESTO.TIEMPO_LIMPIEZA = '".$limpieza."'
					 WHERE CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP = '".$codigo."'";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					
					$query3 = "DELETE FROM CITMED.T_PROCED_BASICO_COMPUESTO WHERE CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO_COMP = '".$codigo."'";		  
					$stmt3 = oci_parse($this->conec, $query3);
					oci_execute($stmt3);
					if(count($procedimientos)>0){
						$i=0;
						foreach ($procedimientos as $procedimiento) {
								
								if($i<count($procedimientos)){
									$query2 = "INSERT INTO CITMED.T_PROCED_BASICO_COMPUESTO (ID_PROCEDIMIENTO, ID_PROCEDIMIENTO_COMP, ORDEN_PROCEDIMIENTO) VALUES ('".$procedimiento."','".$codigo."','".$i."')";			  
									$stmt2 = oci_parse($this->conec, $query2);
									oci_execute($stmt2);
									++$i;
									}
							}
					}
					echo "<script>
						alert('El Procedimiento Compuesto fue modificado Exitosamente');
						window.location.href='".$this->link."citmed/administrarSistema/registrarProcedimientoCompuesto.php';
					  </script>";
					
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function eliminarProcedimientoCompuesto($codigo){

			/* En caso de que la conexión se haya logrado satisfactoriamente.
			 */
			
			if ($this->conec){
				
				$compuesto = $this->buscarProcedimientoCompuesto2($codigo);
				
				if ($compuesto != NULL){
				
					$query1 = "SELECT CITMED.T_CITA.ID_PROCEDIMIENTO
					 FROM CITMED.T_CITA WHERE CITMED.T_CITA.ID_PROCEDIMIENTO='".$codigo."'";		  
					$stmt1 = oci_parse($this->conec, $query1);
					oci_execute($stmt1);
					$cita = oci_fetch_array ($stmt1, OCI_NUM);
					
					$query2 = "SELECT CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO
					 FROM CITMED.T_PROCED_BASICO_COMPUESTO WHERE CITMED.T_PROCED_BASICO_COMPUESTO.ID_PROCEDIMIENTO_COMP='".$codigo."'";		  
					$stmt2 = oci_parse($this->conec, $query2);
					oci_execute($stmt2);
					$procedimientos = oci_fetch_array ($stmt2, OCI_NUM);
						
					if($cita!= NULL){
						
							echo "<script>
							alert('El  Procedimiento Compuesto no se puede eliminar ya que posee asociaciones de Citas');
							window.location.href='".$this->link."citmed/administrarSistema/registrarProcedimientoCompuesto.php';
							</script>";
						
						}
					else
						{	
							if($procedimientos!=NULL){
								
								echo "<script>
								alert('El  Procedimiento Compuesto no se puede eliminar ya que posee asociaciones de Procedimientos Basicos');
								window.location.href='".$this->link."citmed/administrarSistema/registrarProcedimientoCompuesto.php';
								</script>";
								
								}
								
							else{
												$query = "DELETE FROM CITMED.T_PROCEDIMIENTO_COMPUESTO WHERE CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP = '".$codigo."'";		  
												$stmt = oci_parse($this->conec, $query);
												oci_execute($stmt);
												
													echo "<script>
													alert('El Procedimiento Compuesto fue eliminado Exitosamente');
													window.location.href='".$this->link."citmed/administrarSistema/registrarProcedimientoCompuesto.php';
													 </script>";
									
									}
						}
					
					}
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
		
		
		function cargarProcedimientoCompuesto(){
			
			if ($this->conec){
				
				
					$query = "SELECT CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP, CITMED.T_PROCEDIMIENTO_COMPUESTO.NOMBRE_PROCEDIMIENTO_COMP
					 FROM CITMED.T_PROCEDIMIENTO_COMPUESTO";		  
					$stmt = oci_parse($this->conec, $query);
					oci_execute($stmt);
					$compuesto = array();
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						$compuesto[] = array("id"=>$row[0], "nombre" => $row[1]);
						
						}
								
					echo json_encode($compuesto);
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
						
					
				}
				
					
				
					
		}
		
?>