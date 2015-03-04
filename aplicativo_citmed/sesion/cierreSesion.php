<?php 
	/* Se destruye la sesión que se creó. 
	 */
	session_start(); 
	session_destroy();
	
	header('Content-Type: text/html; charset=utf-8');
	
	/* Se carga el link de acceso a la aplicación, utilizado para las 
	 * redirecciones internas.
	 */
	$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
	$link = $conexiones->link;
	
	/* Se redirige a la página del menú principal.
	 */
	header("Location: ".$link."citmed/index.php");
?>