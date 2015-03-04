<?php
	session_start();
		include '../config.php';
		include '../modelo/Class_Proyecto.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Proyecto();
		if(isset($_GET['codf'])){
		$de->mostrar_costo();
			}
		
?>
