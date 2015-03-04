<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	include '../conexiones/conexion.php';
	require_once "../libreria/administrarServicio.php";
	
	$servicio= new administrarServicio($conec,$link);
	
	
	if(isset($_POST["operacionServicio"])){
		
			if($_POST["operacionServicio"]== 'Registrar'){
					$servicio->registrarServicio($_POST["codigo_servicio"],$_POST["nombre_servicio"],$_POST["validar_equipo"]);
					}
			
			if($_POST["operacionServicio"]== 'Actualizar'){
					 $servicio->actualizarServicio($_POST["codigo_servicio"],$_POST["nombre_servicio"],$_POST["validar_equipo"]);
				 }
			if($_POST["operacionServicio"]== 'Eliminar'){
					 $servicio->eliminarServicio($_POST["codigo_servicio"]);
				 }
	}
	
	if(isset($_GET["buscar"])){
		
				$servicio->buscarServicio($_GET["buscar"]);
				
		}
		
	if(isset($_GET["buscarValidarEquipo"])){
		
				$servicio->buscarValidarEquipo($_GET["buscarValidarEquipo"]);
				
		}
		
	if(isset($_GET["cargarUnidad"])){
		
				$servicio->cargarUnidad();
				
		}
		
		//|| isset($_POST["nombre_servicio"]) || isset($_POST["validar_equipo"])
?>