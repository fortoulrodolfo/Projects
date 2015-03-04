<?php
	/* Se inicia la sesión.
	 */
	session_start();
	
	header('Content-Type: text/html; charset=utf-8');
	

	
	/*************************************************************************/
	/*                               FUNCIONES                               */
	/*************************************************************************/
	
	
	/* Se chequea que el nombre de usuario esté determinado. En caso contrario, 
	 * se destruye la sesión, redirigiendo a la página de login.
	 */
	function chequearUsuario($direccion){
		if (!isset($_SESSION["usuario"])){
			session_destroy();
			header("Location: ".$direccion."citmed/index.php"); 
		}
	}
	
	/* Se chequea que el usuario sea un medico y se hace el login 
	 * automatico
	 */
	function chequearMedico($user, $clave, $servicio){
	
		$conec = oci_connect($user, $clave, $servicio, 'AL32UTF8');
			
			if (isset($_SESSION["usuario"]) && isset($_SESSION["contrasena"])){
				
				if($conec){
			
					$query = "SELECT U.USU_COD, S.US_AUTORIZACION1,S.US_NIVEL_COD,S.ESTATUS
									FROM T_USUARIO U,T_USUARIO_SISTEMA S
									WHERE U.USU_COD = S.US_USU_COD
									AND S.US_APP_COD = '00000038'						
									AND U.USU_LOGIN = '".str_replace("ñ", "Ñ",strtoupper($_SESSION["usuario"]))."'";
						
						
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
						
						if ($permisoCitmed < 1){//SIN PERMISO PARA CITMED
							header("Location: ../aplicativo_citmed/index.php?err=citmed");//usuario sin permiso en el APP CITMED
						}
						else{									
							$_SESSION["autorizacionOpciones"] = $s_us_autorizacion2;																													
							}	
					}			
				else{
				$e = oci_error();
				echo json_encode($e);
				}
		}
	}
	
	/* Se chequea si la contraseña del usuario ha caducado.
	 */
	function chequearCaducado($direccion){
		if (isset($_SESSION["caducado"])){
			if ($_SESSION["caducado"] == false){
				unset($_SESSION["contrasena"]);
				unset($_SESSION["intervalo"]);
				header("Location: ".$direccion."general/index.php");
			}
		}
	}
	
	/* Chequea que exista algún error en el inicio de sesión.
	 */
	function errores(){
		/* En caso de existir, se le indica al usuario el error cometido.
		 * 		-usno: Usuario inexistente.
		 *		-usm1: Más de un usuario con el mismo nombre.
		 *		-usin: Usuario inactivo.
		 *		-uscl: La contraseña no coincide con el usuario introducido.
		 */
		if (isset($_GET["err"])){
			if ($_GET["err"] == "usno"){
				echo '<font size="3" color="#FF0000">Nombre de usuario inexistente.</font>';
			}else if ($_GET["err"] == "usm1"){
				echo '<font size="3" color="#FF0000">Existe más de un usuario con el mismo nombre.</font>';
			}else if ($_GET["err"] == "usin"){
				echo '<font size="3" color="#FF0000">El usuario se encuentra inactivo.</font>';
			}else if ($_GET["err"] == "uscl"){
				echo '<font size="3" color="#FF0000">La contraseña no coincide con el usuario introducido.</font>';
			}else if ($_GET["err"] == "citmed"){
				echo '<font size="3" color="#FF0000">Usuario no tiene acceso a CITMED</font>';
			}
		}else{
			echo '<font size="3" color="#FFFFFF">.</font>';
		}
	}
	
	/* Función que chequea los errores en el cambio de contraseña.
	 */
	function nuevaCont(){
		/* En caso de existir, se le indica al usuario el error cometido.
		 * 		-ant: La contraseña anterior no coincide con la actual.
		 *		-nue: La nueva contraseña no se repitió de forma correcta.
		 */
		if (isset($_GET["err"])){
			if ($_GET["err"] == "ant"){
				echo '. Contraseña anterior incorrecta.';
			}else if ($_GET["err"] == "nue"){
				echo '. Error al repetir la nueva contraseña.';
			}
		}
	}
	
	/* Función en la que se indican las operaciones disponibles según el tipo
	 * de usuario en la UED.
	 */
	function indicarOperacionesUED($direccion, $opcion){
		
		if ($opcion=="Principal"){
		
		if ($_SESSION["autorizacionOpciones"][2] != 0){
			   
				echo  '<button id="sesion" type="button" class="link-style" style="width:400px;"
						onClick="window.location=\''.$direccion.'citmed/administrarSistema/menuOpciones.php\'">
						Administrar Sistema
						</button><br>';
				}
				
		if ($_SESSION["autorizacionOpciones"][3] != 0){	
		
				echo '<button id="sesion" type="button" class="link-style" style="width:400px;"
						 onClick="window.location=\''.$direccion.
						 'citmed/registrarPaciente.php\'">
						Administrar Paciente
						</button><br>';
				}
				
		if ($_SESSION["autorizacionOpciones"][4] != 0){		
                
				echo '<button id="sesion" type="button" class="link-style" style="width:400px;"
						onClick="window.location=\''.$direccion.'citmed/administrarCitas/fechaCalendario.php\'">
						Administrar Citas
						</button><br>';
				}
				
		if ($_SESSION["autorizacionOpciones"][6] != 0){		
                echo '<button id="sesion" type="button" class="link-style" style="width:400px;"
						 onClick="window.location=\''.$direccion.'citmed/consultaReporte/menuOpciones.php\'">
					Consultas y Reportes
				</button>
				<br>';
				}
				
				echo'<br><br>';
				
		}else if($opcion=="Administrar"){
			
			echo  '<button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/administrarSistema/registrarServicio.php\'">
					Servicios
				</button><br>
                <button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/administrarSistema/registrarSala.php\'">
					Salas
				</button><br>
				<button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/administrarSistema/registrarTipoProcedimiento.php\'">
					Tipo-Procedimientos
				</button><br>
				<button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/administrarSistema/registrarProcedimiento.php\'">
					Procedimientos
				</button><br>
				<button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/administrarSistema/registrarProcedimientoCompuesto.php\'">
					Procedimientos Compuestos
				</button><br>
				<button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/administrarSistema/registrarEquipo.php\'">
					Equipos
				</button><br>
				<button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/administrarSistema/registrarMedico.php\'">
					Medicos
				</button><br>
                <button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/administrarSistema/registrarSecretaria.php\'">
					Secretarias
				</button>
				<br><br><br>';
				
			}
			else if($opcion=="Reporte"){
			
			echo  '<button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/consultaReporte/libroCitas.php\'">
					Consultar Libro de Citas
				</button><br>
                <button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/consultaReporte/#\'">
					Generar Reportes
				</button><br>
                <button id="sesion" type="button" class="link-style" style="width:400px;"
		onClick="window.location=\''.$direccion.'citmed/consultaReporte/libroCitasBitacora.php\'">
					Consultar Bitacora
				</button>
				<br><br><br>';
				
			}
		
	}
	
?>