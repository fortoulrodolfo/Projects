<?php
		include '../config.php';
		include '../modelo/Class_Alertas.php';
		
		$de = new Class_Alertas();
		
		if(isset($_GET['alerta'])){
		$de->actualizar_alertas();
		}
		
		
?>
