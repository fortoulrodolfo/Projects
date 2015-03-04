<?php
	session_start();
	header('Content-Type: text/html; charset=utf-8');
	// Se destruye la sesión en caso de intentar acceder a inicioSesion.php de manera directa	 
	session_destroy();
	
	// Se carga el link de acceso a la aplicación, utilizado para las redirecciones internas	 
	$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
	$link = $conexiones->link;
	
	// Se determina si el usuario y la contraseña fueron establecidos	
	if (((isset($_POST["usuario"]))) && (isset($_POST["clave"])) && (trim($_POST["usuario"] <> "")) && (trim($_POST["clave"] <> ""))){		
		
		// Se obtienen los datos de conexion a SISTEMAS
		$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
		$userBD = $conexiones->seguridad->usuario;
		$claveBD = $conexiones->seguridad->contrasena;
		$servicioBD = $conexiones->seguridad->servicio;		
		
		// Se obtienen los datos de conexion a DIRECTORIO
		$userBD_dir = $conexiones->directorio->usuario;
		$claveBD_dir = $conexiones->directorio->contrasena;
		$servicioBD_dir = $conexiones->directorio->servicio;					

		//Se establece conexion		
		$conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');			
		  
		if ($conec){			
			 
			$numfilas = 0; 
			$query = "SELECT U.USU_COD,
							 U.USU_LOGIN,
							 U.USU_PASSWORD,
							 U.ESTATUS,
							 TO_CHAR(U.USU_FECHA_CADUCA, 'DD-MM-YYYY'),							 
							 U.USU_INTERVALO_CADUCA,
							 U.USU_PER_COD,							
							 P.PER_COD,
							 P.PER_NOMBRE1,
							 P.PER_NOMBRE2,
							 P.PER_APELLIDO1,
							 P.PER_APELLIDO2,
							 P.PER_NACIONALIDAD,
							 P.PER_IDENTIFICACION,
							 P.PER_CORREO,
							 P.ESTATUS,
							 P.PER_EXTERNO,
							 P.PER_COM_COD,
							 P.PER_COD_EXTERNO
						FROM T_USUARIO U,T_PERSONA P
						WHERE U.USU_PER_COD = P.PER_COD					  
					    AND U.USU_LOGIN = '".str_replace("ñ", "Ñ",strtoupper($_POST["usuario"]))."'";
						
			$stmt = oci_parse($conec, $query);
			oci_execute($stmt);																			
			oci_close($conec);			
			
			while ($row = oci_fetch_array ($stmt, OCI_NUM)) { //variables #SIN_NUM
				$numFilas++;
				$u_usu_cod = $row[0];
				$u_usu_login = $row[1];
				$u_password = $row[2];
				$u_estatus = $row[3];
				$u_usu_fecha_caduca = $row[4];
				$u_usu_intervalo = $row[5];
				$u_usu_per_cod = $row[6];
				$p_per_cod = $row[7];
				$p_per_nombre1 = $row[8];
				$p_per_nombre2 = $row[9];
				$p_per_apellido1 = $row[10];
				$p_per_apellido2 = $row[11];
				$p_per_nacionalidad = $row[12];
				$p_per_identificacion = $row[13];
				$p_per_correo = $row[14];
				$p_estatus = $row[15];
				$p_per_externo = $row[16];
				$p_per_com_cod = $row[17];
				$p_per_cod_externo = $row[18];				
			}// fin while
			
			session_start();
			
			$_SESSION["contrasena"] = $_POST["clave"];						
			$_SESSION["intervalo"] = $u_usu_intervalo;
			$_SESSION["correo"] = $p_per_correo;
			$_SESSION["nac"] = $p_per_nacionalidad;
			$_SESSION["cedula"] = $p_per_identificacion;
			$_SESSION["nombre"] = $p_per_nombre1." ".$p_per_nombre2." ".$p_per_apellido1;//nombre y apellidos concatenados de la tabla persona	
			$_SESSION["usuario"] = str_replace("Ñ", "ñ",strtolower($_POST["usuario"]));
						
			$query = "SELECT U.USU_COD, S.US_AUTORIZACION1,S.US_NIVEL_COD,S.ESTATUS
						FROM T_USUARIO U,T_USUARIO_SISTEMA S
						WHERE U.USU_COD = S.US_USU_COD
						AND S.US_APP_COD = '00000038'						
					    AND U.USU_LOGIN = '".str_replace("ñ", "Ñ",strtoupper($_POST["usuario"]))."'";
			
			$conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');
			$stmt = oci_parse($conec, $query);
			oci_execute($stmt);					
					
			while ($row = oci_fetch_array ($stmt, OCI_NUM)) { //variables #2
				
				 $u_usu_cod2 = $row[0];	
				 $s_us_autorizacion2 = $row[1];	
				 $s_us_nivel_cod2 = $row[2];	
				 $s_estatus2 = $row[3];	
			}			
			oci_execute($stmt);			
			$permisoCitmed = oci_fetch_all($stmt,$query);;
			oci_close($conec);
												
			if($numFilas < 1) {//no existe usuario
					header("Location: ".$link."citmed/index.php?err=usno");
				}elseif ($numfilas > 1){//mas de un usuario con el mismo login
					header("Location: ".$link."citmed/index.php?err=usm1");			
				}else{//usuario encontrado
					if ($u_password <> $_POST["clave"]){//clave diferente												
						header("Location: ".$link."citmed/index.php?err=uscl");//clave no coincide con el usuario
					}else{//usuario existe y validado					
						if($u_estatus == "I"){ //valida estatus usuario
							header("Location: ".$link."citmed/index.php?err=usin");//usuario inactivo
						}elseif (($u_usu_fecha_caduca == "") || (strtotime($u_usu_fecha_caduca)) <= strtotime(date("d-m-Y")) || ($u_estatus == 'V')){							
							$_SESSION["caducado"] = true;
							$_SESSION["autorizacionOpciones"] = $s_us_autorizacion2;
							header("Location: ".$link."citmed/sesion/cambioPass.php");//usuario caducado o cambio de clave
						}elseif ($u_estatus <> "A"){							
							header("Location: ".$link."citmed/index.php?err=usts");//usuario sin estatus										
						}else{
								if ($permisoCitmed < 1){//SIN PERMISO PARA CITMED
									header("Location: ".$link."citmed/index.php?err=citmed");//usuario sin permiso en el APP CITMED
								}else{									
									$_SESSION["autorizacionOpciones"] = $s_us_autorizacion2;									
									
									if($p_per_externo <> "N"){ //SI EXTERNO																				
												
												$query = "SELECT ME.NOMBRE_CORTO, 
													 ME.N_COLEGIO_MEDICO, 
													 ME.MSAS, 
													 ME.RUTA_FIRMA, 
													 ME.NOMBRE_FIRMA
													 FROM T_MEDICO_EXTERNO ME
													 WHERE ME.COD_CARNET = '".$p_per_cod_externo."'";																		
										
  									    $conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');
										$stmt = oci_parse($conec, $query);
										oci_execute($stmt);
										
										while ($row = oci_fetch_array ($stmt, OCI_NUM)) { //variables #4
											$_SESSION["cedulaMedico"] = preg_replace("/[^0-9]/", "", $p_per_identificacion);
											$_SESSION["nombreCortoMedico"] = $row[0];
											$_SESSION["colegioMedico"] = $row[1];
											$_SESSION["msasMedico"] = $row[2];
											$_SESSION["rutaMedico"] = $row[3];
											$_SESSION["firmaMedico"] = $row[4];
											//$_SESSION["especialidad"] = $row[6];											 										
										}
										oci_execute($stmt);
										$countMedicoExterno = oci_fetch_all($stmt,$query);
										oci_close($conec);
										
										if ($countMedicoExterno > 0){
											$hono_carnet = $p_per_cod_externo;
											$_SESSION["clave"] = $hono_carnet;

											//variables sin saber
											$_SESSION["justcons"] = "SI";
											$_SESSION["autorizacion"] = "SI";
											$_SESSION["terceros"] = "SI";		
											$_SESSION["permission"] = "OK";
											$_SESSION["conshono"] = "SI";																														
											$_SESSION["hasEntrado"] = false;											
													
											header("Location: ".$link."citmed/index2.php");//Menu de Opciones										
										}else{
										
											$hono_carnet = $p_per_cod_externo;
											$_SESSION["clave"] = $hono_carnet;
											header("Location: ".$link."citmed/index2.php");//Menu de Opciones										
										}
																			
									}else{// NO EXTERNO
											$query = "SELECT M.MED_CARNET,M.ESTATUS 
														FROM T_MEDICO M
														WHERE M.MED_PER_COD = '".$p_per_cod."'";
														
											$conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');
											$stmt = oci_parse($conec, $query);
											oci_execute($stmt);
											while ($row = oci_fetch_array ($stmt, OCI_NUM)) { //variables #3
												 $m_med_carnet3 = $row[0];
												 $m_estatus_3 = $row[1];
											}		
											oci_execute($stmt);											
											$countMedico = oci_fetch_all($stmt,$query);
											oci_close($conec);																	
											
											if ($countMedico > 0){											
												$conec = oci_connect($userBD_dir, $claveBD_dir, $servicioBD_dir, 'AL32UTF8');
												$query = "SELECT CEDULA, 
													 NOMBRE_CORTO, 
													 N_COLEGIO_MEDICO, 
													 MSAS, 
													 RUTA_FIRMA, 
													 NOMBRE_FIRMA, 
													 COD_ESPECIALIDAD 
													 FROM T_MEDICO 
													 WHERE COD_CARNET = '".$m_med_carnet3."'";
																								
												$stmt = oci_parse($conec, $query);
												oci_execute($stmt);																												
												while ($row = oci_fetch_array ($stmt, OCI_NUM)) {
													$_SESSION["cedulaMedico"] = preg_replace("/[^0-9]/", "", $row[0]);
													$_SESSION["nombreCortoMedico"] = $row[1];
													$_SESSION["colegioMedico"] = $row[2];
													$_SESSION["msasMedico"] = $row[3];
													$_SESSION["rutaMedico"] = $row[4];
													$_SESSION["firmaMedico"] = $row[5];
													$_SESSION["especialidad"] = $row[6];
												}
												oci_close($conec);
												
												$_SESSION["clave"] = $m_med_carnet3;	
												//variables sin saber
												$_SESSION["justcons"] = "SI";
												$_SESSION["autorizacion"] = "SI";
												$_SESSION["terceros"] = "SI";		
												$_SESSION["permission"] = "OK";
												$_SESSION["conshono"] = "SI";																														
												$_SESSION["hasEntrado"] = false;											
												
												header("Location: ".$link."citmed/index2.php");//Menu de Opciones																							
											}else{
												//header("Location: ".$link."citmed/index.php?err=noapp");//Usuario no puede utilizar el APP
											}// fin if medico																				
									}//fin if NO EXTERNO																
								}//PERMISO PARA EL CITMED
						
						}//fin valida estatus usuario					
					}//fin if usuario existe y validado
				}//fin if usuario encontrado
		
		
		
		}//fin connec
		
	}else{
		header("Location: ".$link."citmed/index.php");		
	}//fin if isset usuario y clave	
?>

