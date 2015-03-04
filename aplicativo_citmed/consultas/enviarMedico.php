<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	include '../conexiones/conexion.php';
	require_once "../libreria/administrarMedico.php";
	
	$medico= new administrarMedico($conec,$link);
	
	
	if(isset($_POST["operacionMedico"])){
		
			if($_POST["operacionMedico"]== 'Registrar'){
					$medico->registrarMedico($_POST["tipo_id"],$_POST["codigo_medico"],$_POST["nombre_medico"],$_POST["status_medico"],$_POST["unidad_medico"]);
					}
			
			if($_POST["operacionMedico"]== 'Actualizar'){
					 $medico->actualizarMedico($_POST["tipo_id"],$_POST["codigo_medico"],$_POST["status_medico"],$_POST["unidad_medico"]);
				 }
			if($_POST["operacionMedico"]== 'Eliminar'){
					 $medico->eliminarMedico($_POST["tipo_id"],$_POST["codigo_medico"]);
				 }
	}
	
	if(isset($_GET["buscar"])){
		
				$medico->buscarMedico($_GET["tipo_id"], $_GET["codigo"]);
				
		}
	
	if(isset($_GET["cargarMedico"])){
		
				$medico->cargarMedicos();
				
		}
		
	if(isset($_GET["cargarTablaMedico"])){
		
				$medico->cargarTablaMedicos();
				
		}
		
		
?>