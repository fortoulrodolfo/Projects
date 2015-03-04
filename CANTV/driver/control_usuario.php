<?php
	session_start();
		include '../modelo/Class_sql.php';
		include_once '../modelo/Usuario.php';
	/*
 		* To change this template, choose Tools | Templates
 		* and open the template in the editor.
 	*/
	$u=new Usuario();
		if(isset($_POST['accion'])){
    		if($_POST['accion']=="cargarse"){
        		$u->listar_sector();
    			}
		}
?>