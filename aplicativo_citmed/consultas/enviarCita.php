<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	include '../conexiones/conexion.php';
		
	
	require_once "../libreria/administrarCita.php";
	
	$cita= new administrarCita($conec,$link);
	
	
	if(isset($_POST["operacionCita"])){
		
			if(isset($_POST["anestesiologo"])){
				$anestesiologo = $_POST["anestesiologo"];
				}
			else
				{
				$anestesiologo = "off";
					}
		
			if($_POST["operacionCita"]== 'Registrar'){
					$cita->registrarCita($_POST["sala_cita"],$_POST["fecha_inicio_cita"],$_POST["hora_inicio_cita"],$_POST["hora_fin_cita"],
											$_POST["tipo_estudio_select"], $_POST["proced_select"],$_POST["equipos_cita"], $_POST["id_medico"],
											$_POST["tipo_id"],$_POST["id_paciente"], $_POST["tipo_paciente_select"], $_POST["hab_paciente"], $_POST["forma_pago"],$anestesiologo,
											$_POST["participante1"],$_POST["participante2"], $_POST["observaciones"],
											 $_POST["status_select"], 'rodolfo', $_POST["tipo_id_medico"]);
					}
					
			if($_POST["operacionCita"]== 'RegistrarNP'){
					$cita->registrarCita($_POST["sala_cita"],$_POST["fecha_inicio_cita"],$_POST["hora_inicio_cita"],$_POST["hora_fin_cita"],
											$_POST["tipo_estudio_select"], $_POST["proced_select"], $_POST["equipos_cita"], $_POST["id_medico"],
											$_POST["tipo_id"],$_POST["id_paciente"], $_POST["tipo_paciente_select"], $_POST["hab_paciente"], $_POST["forma_pago"],$anestesiologo,
											$_POST["participante1"],$_POST["participante2"], $_POST["observaciones"],
											 $_POST["status_select"], 'rodolfo', $_POST["tipo_id_medico"]);
					}
			
			if($_POST["operacionCita"]== 'Actualizar'){
					 $cita->actualizarCita($_POST["codigo_cita"],$_POST["sala_cita"],$_POST["fecha_inicio_cita"],$_POST["hora_inicio_cita"],$_POST["hora_fin_cita"],
											$_POST["tipo_estudio_select"], $_POST["proced_select"],$_POST["equipos_cita"], $_POST["id_medico"],
											$_POST["tipo_id"],$_POST["id_paciente"], $_POST["tipo_paciente_select"], $_POST["hab_paciente"], $_POST["forma_pago"],$anestesiologo,
											$_POST["participante1"],$_POST["participante2"], $_POST["observaciones"],
											 $_POST["status_select"], 'rodolfo', $_POST["tipo_id_medico"]);
				 }
			if($_POST["operacionCita"]== 'Eliminar'){
					 $cita->eliminarCita($_POST["codigo_cita"], $_POST["status_select"], $_POST["fecha_inicio_cita"]);
				 }
	}
	
	if(isset($_GET["buscar"])){
		
				$cita->buscarCita($_GET["buscar"]);
				
		}
		
	if(isset($_GET["citas"])){
		
				$cita->cargarCitas();
				
		}
	
	if(isset($_GET["salasCitas"])){
		
				$cita->cargarSalasCitas($_GET["sala_cita"],$_GET["fecha"],$_GET["hora_inicio"],$_GET["hora_fin"]);
				
		}
	
	if(isset($_GET["salasEquipo"])){
		
				$cita->cargarSalasEquipo($_GET["sala_cita"],$_GET["codigo_proced"],$_GET["fecha"],$_GET["hora_inicio"],$_GET["hora_fin"]);
				
		}
		
	if(isset($_GET["salasEquipoNP"])){
		
				$cita->cargarSalasEquipoNP($_GET["codigo_proced"]);
				
		}
	
	if(isset($_GET["salasCitasMedico"])){
		
				$cita->cargarSalasMedicos($_GET["tipo_id"],$_GET["codigo_medico"],$_GET["fecha"],$_GET["hora_inicio"],$_GET["hora_fin"]);
				
		}
	
	if(isset($_GET["salasCitasPaciente"])){
		
				$cita->cargarSalasPaciente($_GET["tipo_id"],$_GET["codigo_paciente"],$_GET["fecha"],$_GET["hora_inicio"],$_GET["hora_fin"]);
				
		}
		
	if(isset($_GET["cargarLibro"])){
		 	
					$cita->cargarCitasLibro($_GET["fecha_inicio"], $_GET["fecha_final"], $_GET["status_cita"], $_GET["sala"], $_GET["proced"], $_GET["equipo"],
					 $_GET["forma"], $_GET["aneste"], $_GET["medico"], $_GET["tipo_id"], $_GET["paciente"], $_GET["tipo_paciente"], $_GET["tipo_id_medico"]);
			
		}
		
	if(isset($_GET["cargarLibroBitacora"])){
		 	
					$cita->cargarCitasLibroBitacora($_GET["fecha_inicio"], $_GET["fecha_final"], $_GET["status_cita"], $_GET["sala"], $_GET["proced"], 
					 $_GET["forma"], $_GET["aneste"], $_GET["medico"], $_GET["tipo_id"], $_GET["paciente"], $_GET["tipo_paciente"], $_GET["tipo_id_medico"]);
			
		}
		
	
		
?>