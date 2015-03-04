<?php
	
	header('Content-Type: text/html; charset=utf-8');
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	
	
	
	if(isset($_GET["buscarMedico"])){
	
	$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
	$link = $conexiones->link;
	$userBD = $conexiones->seguridad->usuario;
	$claveBD = $conexiones->seguridad->contrasena;
	$servicioBD = $conexiones->seguridad->servicio;
	
	/* Se lleva a cabo la conexión con la base de datos.
		 */
	$conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');
		
			if ($conec){
				
					$query = "SELECT t_usuario.usu_login,
								t_persona.per_nacionalidad,
								t_persona.per_identificacion,
								t_persona.per_nombre1,
								t_persona.per_nombre2,
								t_persona.per_apellido1,
								t_persona.per_apellido2
								from sistemas.t_medico, sistemas.t_usuario, sistemas.t_persona
								where t_medico.med_per_cod = t_usuario.usu_cod
								and t_persona.per_cod = t_medico.med_per_cod
								and t_persona.per_cod = t_usuario.usu_cod
								and t_medico.estatus = 'A' 
							    and t_persona.per_nacionalidad = '".$_GET["tipo_id"]."'
								and t_persona.per_identificacion = '".$_GET["id"]."'";
							
					$stmt = oci_parse($conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo json_encode($row);;
			}
			else {
				echo "Error al conectar con la Base de Datos";
				}
				
				
		}
	
	if(isset($_GET["buscarPaciente"])){
	
	/*$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
	$link = $conexion0->link;
	$userBD = $conexion0->seguridad->usuario;
	$claveBD = $conexion0->seguridad->contrasena;
	$servicioBD = $conexion0->seguridad->servicio;
	
	/* Se lleva a cabo la conexión con la base de datos.
		 */
	/*$conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');
		
				  $query = "select PACIDE,
							  PACTID,
							  PACAP1,
							  PACAP2,
							  PACNOM,
							  PACSEX,
							  PACNAC,
							  PACTEL,
							  PACZON,
							  PACTUS
							  from abpac
							  where pacide = '".$_GET["tipo_id"]."'
							  and pactid = '".$_GET["id"]."'";
							
					$stmt = oci_parse($conec, $query);
					oci_execute($stmt);
					$row = oci_fetch_array ($stmt, OCI_NUM);
					echo json_encode($row);*/
					echo "[]";
				
		}

		
		
?>