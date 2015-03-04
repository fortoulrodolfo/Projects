<?php
	session_start();
		include '../config.php';
		include '../modelo/Class_Informe.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Informe();
		if(isset($_GET['codg'])){
			
		$de->ingresos_ini_pro();
			}
		
?>
