<?php
	
	session_start();
	
	header('Content-Type: text/html; charset=utf-8');
	
	/* Se carga el link de acceso a la aplicación, utilizado para las 
	 * redirecciones internas.
	 */
	$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
	$link = $conexiones->link;
	
	/* Se chequea que el nombre de usuario esté disponible. En caso contrario, 
	 * se destruye la sesión, redirigiendo a la página de login.
	 */
	if (!isset($_SESSION["usuario"])){
		session_destroy();
		header("Location: ".$link."sesion/index.php"); 
	}
	
	/* Se chequea si la contraseña del usuario ha caducado
	 */
	if (isset($_SESSION["caducado"])){
		if ($_SESSION["caducado"] == false){
			header("Location: ".$link."general/index.php");
		}else{
			if ($_SESSION["contrasena"] != $_POST["anterior"]){
				header("Location: ".$link."sesion/cambioPass.php?err=ant");
			}else if($_POST["nueva"] != $_POST["nuevaAg"]){
				header("Location: ".$link."sesion/cambioPass.php?err=nue");
			}else{
				/* Se obtienen los datos de conexiones desde el archivo de configuración.
				 */
				$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
				$userBD = $conexiones->sistemas->usuario;
				$claveBD = $conexiones->sistemas->contrasena;
				$servicioBD = $conexiones->sistemas->servicio;
				
				/* Se lleva a cabo la conexión de la base de datos.
				 */
				$conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');
				
				if ($conec){
					/* Se define la nueva fecha de caducidad, que será $_SESSION["intervalo"] días más
					 * que la fecha de hoy.
					 */
					$fecha = date('d-m-Y');
					$nuevafecha = strtotime ('+'.$_SESSION["intervalo"].' day', strtotime($fecha));
					$nuevafecha = date ('d/m/Y' , $nuevafecha);
					
					/* Consulta para actualizar la clave y la fecha de caducidad de la contraseña del
					 * usuario.
					 */
					$query = "UPDATE T_USUARIO U
								 SET U.USU_FECHA_CADUCA = '".$nuevafecha."',
									 U.USU_PASSWORD = '".$_POST["nueva"]."' 
							   WHERE U.USU_LOGIN = '".str_replace("ñ", "Ñ", strtoupper($_SESSION["usuario"]))."'";
							  
					$stmt = oci_parse($conec, $query);
					$ejecucion = oci_execute($stmt, OCI_DEFAULT);
					
					/* Se chequea la correctitud de la ejecución de la consulta.
					 */
					if($ejecucion){ 
						oci_commit($conec); 
						$_SESSION["caducado"] = false;
						oci_close($conec);
						
						/* Se redirige a la página principal de la aplicación.
						 */
						header("Location: ".$link."general/index.php");
					}else{
						oci_rollback($conec);
						oci_close($conec);
						header("Location: ".$link."sesion/cambioPass.php");
					}
				}else{
					header("Location: ".$link."sesion/cambioPass.php");
				}
			}
		}
	}else{
		header("Location: ".$link."sesion/index.php");
	}
?>