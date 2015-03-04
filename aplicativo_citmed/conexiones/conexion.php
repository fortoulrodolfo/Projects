<?php

	$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
	$link = $conexiones->link;
	$userBD = $conexiones->sistemas->usuario;
	$claveBD = $conexiones->sistemas->contrasena;
	$servicioBD = $conexiones->sistemas->servicio;
	
	/* Se lleva a cabo la conexión con la base de datos.
		 */
	$conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');
?>