<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	include '../conexiones/conexion.php';
	require_once "../libreria/administrarProcedimientoCompuesto.php";
	
	$compuesto= new administrarProcedimientoCompuesto($conec,$link);
	
	
	if(isset($_POST["operacionCompuesto"])){
		
		if(isset($_POST["procedimiento_compuesto2"])){
		
			if($_POST["operacionCompuesto"]== 'Registrar'){
					$compuesto->registrarProcedimientoCompuesto($_POST["codigo_compuesto"],$_POST["nombre_compuesto"],$_POST["tiempo_limpieza"],$_POST["procedimiento_compuesto2"]);
					}
			if($_POST["operacionCompuesto"]== 'Actualizar'){
					 $compuesto->actualizarProcedimientoCompuesto($_POST["codigo_compuesto"],$_POST["nombre_compuesto"],$_POST["tiempo_limpieza"],$_POST["procedimiento_compuesto2"]);
					}
					
		}
		else
		{
			$procedimientos=array();
			if($_POST["operacionCompuesto"]== 'Registrar'){
					$compuesto->registrarProcedimientoCompuesto($_POST["codigo_compuesto"],$_POST["nombre_compuesto"],$_POST["tiempo_limpieza"],$procedimientos);
					}
			if($_POST["operacionCompuesto"]== 'Actualizar'){
					 $compuesto->actualizarProcedimientoCompuesto($_POST["codigo_compuesto"],$_POST["nombre_compuesto"],$_POST["tiempo_limpieza"],$procedimientos);
					}
			
			}
			
		if($_POST["operacionCompuesto"]== 'Eliminar'){
					 $compuesto->eliminarProcedimientoCompuesto($_POST["codigo_compuesto"]);
				 	}
	}
	
	if(isset($_GET["buscar"])){
		
				$compuesto->buscarProcedimientoCompuesto($_GET["buscar"]);
				
		}
	
	if(isset($_GET["cargarCompuesto"])){
		
				$compuesto->cargarProcedimientoCompuesto();
				
		}
		
	
		
		
?>