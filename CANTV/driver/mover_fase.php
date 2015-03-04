<?php
	session_start();
		include '../config.php';
		include '../modelo/Class_Seguimiento.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Seguimiento();
		if(isset($_GET['codf'])){
		$de->mover_codigo();
			}
		
?>
