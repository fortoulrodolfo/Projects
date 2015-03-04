<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	include '../conexiones/conexion.php';
	require_once "../libreria/administrarSecretaria.php";
	
	$secretaria= new administrarSecretaria($conec,$link);
	
	
	if(isset($_POST["operacionSecretaria"])){
		
		if(isset($_POST["medico_secretaria2"])){
		
			if($_POST["operacionSecretaria"]== 'Registrar'){
					$secretaria->registrarSecretaria($_POST["tipo_id_secre"],$_POST["codigo_secretaria"],$_POST["nombre_secretaria"],$_POST["apellido_secretaria"],$_POST["status_secretaria"], $_POST["medico_secretaria2"]);
					}
			
			if($_POST["operacionSecretaria"]== 'Actualizar'){
					 $secretaria->actualizarSecretaria($_POST["tipo_id_secre"],$_POST["codigo_secretaria"],$_POST["nombre_secretaria"],$_POST["apellido_secretaria"],$_POST["status_secretaria"], $_POST["medico_secretaria2"]);
				 }
				 
				 }
		else{
			$medico_secretaria=array();
			if($_POST["operacionSecretaria"]== 'Registrar'){
					$secretaria->registrarSecretaria($_POST["tipo_id_secre"],$_POST["codigo_secretaria"],$_POST["nombre_secretaria"],$_POST["apellido_secretaria"],$_POST["status_secretaria"], $medico_secretaria);
					}
			
			if($_POST["operacionSecretaria"]== 'Actualizar'){
					 $secretaria->actualizarSecretaria($_POST["tipo_id_secre"],$_POST["codigo_secretaria"],$_POST["nombre_secretaria"],$_POST["apellido_secretaria"],$_POST["status_secretaria"], $medico_secretaria);
				 }
				 
			
			}
			if($_POST["operacionSecretaria"]== 'Eliminar'){
					 $secretaria->eliminarSecretaria($_POST["tipo_id_secre"],$_POST["codigo_secretaria"]);
				 }
	}
	
	if(isset($_GET["buscar"])){
		
				$secretaria->buscarSecretaria($_GET["tipo_id_secre"],$_GET["id_secre"]);
				
		}
		
	if(isset($_GET["buscarMedico"])){
		
				$secretaria->buscarMedicoSecretaria($_GET["tipo_medico"],$_GET["id_medico"],$_GET["tipo_secre"],$_GET["id_secre"]);
				
		}
		
	if(isset($_GET["cargarSecretaria"])){
		
				$secretaria->cargarSecretarias();
				
		}
		
		
?>