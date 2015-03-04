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
			header("Location: ".$direccion."sesion/index.php"); 
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
?>