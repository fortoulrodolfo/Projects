<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	include '../conexiones/conexion.php';
	require_once "../libreria/administrarSala.php";
	
	$sala= new administrarSala($conec,$link);
	
	
	if(isset($_POST["operacionSala"])){
		
			if($_POST["operacionSala"]== 'Registrar'){
					$sala->registrarSala($_POST["codigo_sala"],$_POST["nombre_sala"],$_POST["status_sala"],$_POST["unidad_sala"]);
					}
			
			if($_POST["operacionSala"]== 'Actualizar'){
					 $sala->actualizarSala($_POST["codigo_sala"],$_POST["nombre_sala"],$_POST["status_sala"],$_POST["unidad_sala"]);
				 }
			if($_POST["operacionSala"]== 'Eliminar'){
					 $sala->eliminarSala($_POST["codigo_sala"]);
				 }
	}
	
	if(isset($_GET["buscar"])){
		
				$sala->buscarSala($_GET["buscar"]);
				
		}
	
	if(isset($_GET["salas"])){
		
			$sala->cargarSalas($_GET["activa"]);
				
		}
	
	
?>