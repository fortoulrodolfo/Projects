<?php

	session_start();
	
	header('Content-Type: text/html; charset=utf-8');
	
	/* Se destruye la sesión en caso de intentar acceder a inicioSesion.php de
	 * manera directa.
	 */
	session_destroy();
	
	/* Se carga el link de acceso a la aplicación, utilizado para las 
	 * redirecciones internas.
	 */
	$conexiones = simplexml_load_file('../conexiones/conexiones.xml');	
	$link = $conexiones->link;
	
	
	/* Se determina si el usuario y la contraseña fueron establecidos.
	 */
	
	if ((isset($_POST["usuario"])) && (isset($_POST["clave"]))){
		/* Se obtienen los datos de conexiones desde el archivo de configuración.
		 */
		$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
		$userBD = $conexiones->seguridad->usuario;
		$claveBD = $conexiones->seguridad->contrasena;
		$servicioBD = $conexiones->seguridad->servicio;

		/* Se lleva a cabo la conexión de la base de datos.
		 */
		$conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');
	    
		if ($conec){
		     
			/* Consulta sobre los usuarios en el sistemas para comprobar la contraseña.
			 */
			$query = "SELECT U.USU_LOGIN,
							 U.USU_PASSWORD,
							 TO_CHAR(U.USU_FECHA_CADUCA, 'DD-MM-YYYY'),
							 U.USU_INTERVALO_CADUCA,
							 P.PER_NOMBRE1 || ' ' || P.PER_NOMBRE2 || ' ' || P.PER_APELLIDO1 || ' ' ||
							 P.PER_APELLIDO2
						FROM T_USUARIO U, T_PERSONA P
					   WHERE U.USU_PER_COD = P.PER_COD
						 AND U.USU_LOGIN = '".str_replace("ñ", "Ñ",strtoupper($_POST["usuario"]))."'";
			
			/* Se ejecuta la consulta.
			 */
			$stmt = oci_parse($conec, $query);
			oci_execute($stmt);
			
			/* Se obtiene una fila de la consulta, debido a que los usuarios son únicos.
			 */
			$row = oci_fetch_array ($stmt, OCI_NUM);
			
			if ($row != ""){
				$clave = $row[1];
				if (($row[2] == "") || (strtotime($row[2]) <= strtotime(date("d-m-Y")))){
					$caducado = true;
					$intervalo = $row[3];
				}
			}
			
			/* Se cierra la conexión a la base de datos.
			 */
			oci_close($conec);				
		}

		/* Se verifica que la clave del usuario coincida con la clave introducida.
		 */
		if ($clave <> $_POST["clave"]){
			header("Location: ".$link."citmed/aplicativo_sesion/index.php?err=uscl");
		}else{
			/* Se crea la nueva sesión, almacenando los datos del usuario
			 */
			session_start();
			$_SESSION["usuario"] = strtolower($_POST["usuario"]);
			$_SESSION["nombre"] = $row[4];
			$_SESSION["contrasena"] = $_POST["clave"];
			$_SESSION["caducado"] = $caducado;
			$_SESSION["intervalo"] = $intervalo;
			
			/* Se redirige a la página principal de la aplicación.
			 */
			header("Location: ".$link."citmed/index.php");
		}
	}else{
		header("Location: ".$link."citmed/aplicativo_sesion/index.php");
	}
?>

