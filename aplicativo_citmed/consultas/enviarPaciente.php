<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	include '../conexiones/conexion.php';
	require_once "../libreria/administrarPaciente.php";
	
	$paciente= new administrarPaciente($conec,$link);
	
	
	if(isset($_POST["operacionPaciente"])){
		
		
			if($_POST["operacionPaciente"]== 'Registrar'){
					$paciente->registrarPaciente($_POST["tipo_id"],$_POST["codigo_paciente"],$_POST["nombre_paciente"],$_POST["apellido1_paciente"],$_POST["apellido2_paciente"],$_POST["sexo_paciente"],$_POST["fnac_paciente"],$_POST["telf_paciente"], $_POST["fecha_cita"]);
					}
			if($_POST["operacionPaciente"]== 'Actualizar'){
					 $paciente->actualizarPaciente($_POST["tipo_id"],$_POST["codigo_paciente"],$_POST["nombre_paciente"],$_POST["apellido1_paciente"],$_POST["apellido2_paciente"],$_POST["sexo_paciente"],$_POST["fnac_paciente"],$_POST["telf_paciente"]);
					}
			if($_POST["operacionPaciente"]== 'Eliminar'){
					 $paciente->eliminarPaciente($_POST["tipo_id"],$_POST["codigo_paciente"]);
				 }
	}
	
	if(isset($_GET["tipo_id"]) && isset($_GET["codigo_paciente"])){
		
				$paciente->buscarpaciente($_GET["tipo_id"],$_GET["codigo_paciente"]);
				
		}
		
	if(isset($_GET["registrar"])){
		
				$paciente->registrarPacienteSERVINTE($_GET["tipo_id"],$_GET["codigo_paciente"],$_GET["nombre_paciente"],$_GET["apellido1_paciente"],$_GET["apellido2_paciente"],$_GET["sexo_paciente"],$_GET["fnac_paciente"],$_GET["telf_paciente"]);
				
		}
		
		
?>