<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	include '../conexiones/conexion.php';
	require_once "../libreria/administrarEquipo.php";
	
	$equipo= new administrarEquipo($conec,$link);
	
	
	if(isset($_POST["operacionEquipo"])){
		
		if(isset($_POST["proced_equipo2"])){
		
			if($_POST["operacionEquipo"]== 'Registrar'){
					$equipo->registrarEquipo($_POST["codigo_equipo"],$_POST["nombre_equipo"],$_POST["status_equipo"],$_POST["fijo_equipo"],$_POST["sala_equipo"],$_POST["proced_equipo2"]);
					}
			
			if($_POST["operacionEquipo"]== 'Actualizar'){
					 $equipo->actualizarEquipo($_POST["codigo_equipo"],$_POST["nombre_equipo"],$_POST["status_equipo"],$_POST["fijo_equipo"],$_POST["sala_equipo"],$_POST["proced_equipo2"]);
				 }
		
		}
		else
		{
			$procedimientos=array();
			if($_POST["operacionEquipo"]== 'Registrar'){
					$equipo->registrarEquipo($_POST["codigo_equipo"],$_POST["nombre_equipo"],$_POST["status_equipo"],$_POST["fijo_equipo"],$_POST["sala_equipo"],$procedimientos);
					}
			
			if($_POST["operacionEquipo"]== 'Actualizar'){
					 $equipo->actualizarEquipo($_POST["codigo_equipo"],$_POST["nombre_equipo"],$_POST["status_equipo"],$_POST["fijo_equipo"],$_POST["sala_equipo"],$procedimientos);
				 }
			
		}
		
		if($_POST["operacionEquipo"]== 'Eliminar'){
					 $equipo->eliminarEquipo($_POST["codigo_equipo"]);
				 }
	}
	
	if(isset($_GET["buscar"])){
		
				$equipo->buscarEquipo($_GET["buscar"]);
				
		}
		
	if(isset($_GET["cargarEquipos"])){
		
				$equipo->cargarEquipo();
				
		}
		
	
?>