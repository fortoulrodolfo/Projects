<?php
	session_start();
		include '../config.php';
		include '../modelo/Class_Informe.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Informe();
		if(isset($_GET['codf'])){
			
			if($_GET['codf']=='1'){
			
			$de->cargar_iniciativas();
			
				}
			if($_GET['codf']=='2'){
			
			$de->cargar_proyectos();
			
				}
			if($_GET['codf']=='3'){
			
			$de->cargar_todo();
			
				}
			}
		
?>
