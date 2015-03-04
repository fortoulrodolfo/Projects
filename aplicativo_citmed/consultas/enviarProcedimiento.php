<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	include '../conexiones/conexion.php';
	require_once "../libreria/administrarProcedimiento.php";
	
	$procedimiento= new administrarProcedimiento($conec,$link);
	
	
	if(isset($_POST["operacionProcedimiento"])){
		
			if($_POST["operacionProcedimiento"]== 'Registrar'){
					$procedimiento->registrarProcedimiento($_POST["codigo_procedimiento"],$_POST["nombre_procedimiento"],$_POST["duracion_procedimiento"],$_POST["tipo_proced"],$_POST["unidad_proced"],$_POST["tipo_estudio"],$_POST["tiempo_limpieza"]);
					}
			
			if($_POST["operacionProcedimiento"]== 'Actualizar'){
					 $procedimiento->actualizarProcedimiento($_POST["codigo_procedimiento"],$_POST["nombre_procedimiento"],$_POST["duracion_procedimiento"],$_POST["tipo_proced"],$_POST["unidad_proced"],$_POST["tipo_estudio"],$_POST["tiempo_limpieza"]);
				 }
			if($_POST["operacionProcedimiento"]== 'Eliminar'){
					 $procedimiento->eliminarProcedimiento($_POST["codigo_procedimiento"]);
				 }
	}
	
	if(isset($_GET["buscar"])){
		
				$procedimiento->buscarProcedimiento($_GET["buscar"]);
				
		}
		
	if(isset($_GET["buscarCita"])){
		
				$procedimiento->buscarProcedimientoCita($_GET["buscarCita"]);
				
		}
	
	if(isset($_GET["buscarEquipoCita"])){
		
				$procedimiento->buscarProcedimientoEquipoCita($_GET["buscarEquipoCita"]);
				
		}
	
	if(isset($_GET["cargarProcedimientos"])){
		
				$procedimiento->cargarProcedimiento();
				
		}
		
	if(isset($_GET["cargarEstudio"])){
		
				$procedimiento->cargarProcedimientoEstudio($_GET["cargarEstudio"]);
				
		}
		
	
		
		
?>