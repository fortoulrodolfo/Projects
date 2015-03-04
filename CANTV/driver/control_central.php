<?php
	session_start();
		include '../modelo/Class_sql.php';
		include_once '../modelo/Class_Central.php';
	/*
 		* To change this template, choose Tools | Templates
 		* and open the template in the editor.
 	*/
$u=new Class_Central();
	if(isset($_POST['accion'])){
    	if($_POST['accion']=="cargae"){
        	$u->combo_estados();
    		}
			
		if($_POST['accion']=="cargam"){
        	$u->combo_municipio();
    		}
			
    	if($_POST['accion']=="cargac"){
        	$u->combo_central();
    		}
	}
?>