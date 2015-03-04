<?php
	session_start();
		include '../config.php';
		include '../modelo/Class_Data.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Data();
		if(isset($_GET['codf'])){
		$de->mostrar_localidad();
			}
		
?>
