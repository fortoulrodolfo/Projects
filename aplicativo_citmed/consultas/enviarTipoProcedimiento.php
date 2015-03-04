<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	include '../conexiones/conexion.php';
	require_once "../libreria/administrarTipoProcedimiento.php";
	
	$tipo= new administrarTipoProcedimiento($conec,$link);
	
	
	if(isset($_POST["operacionTipo"])){
		
			if($_POST["operacionTipo"]== 'Registrar'){
					$tipo->registrarTipo($_POST["codigo_tipo"],$_POST["nombre_tipo"]);
					}
			
			if($_POST["operacionTipo"]== 'Actualizar'){
					 $tipo->actualizarTipo($_POST["codigo_tipo"],$_POST["nombre_tipo"]);
				 }
			if($_POST["operacionTipo"]== 'Eliminar'){
					 $tipo->eliminarTipo($_POST["codigo_tipo"]);
				 }
	}
	
	if(isset($_GET["buscar"])){
		
				$tipo->buscarTipo($_GET["buscar"]);
				
		}
	
	if(isset($_GET["cargarTipos"])){
		
				$tipo->cargarTiposProcedimiento();
				
		}
		
		
?>