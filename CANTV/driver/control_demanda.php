<?php
	session_start();
		include '../modelo/Class_sql.php';
		include_once '../modelo/Class_Demanda.php';
	/*
 		* To change this template, choose Tools | Templates
 		* and open the template in the editor.
 	*/
$u=new Class_Demanda();
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
			
    	if($_POST['accion']=="cargacc"){
        	$u->combo_ccc();
    		}
			
    	if($_POST['accion']=="cargas"){
        	$u->combo_tiposer();
    		}
			
    	if($_POST['accion']=="cargass"){
        	$u->combo_subtiposer();
    		}
			 
    	if($_POST['accion']=="cargap"){
        	$u->combo_plan();
    		}
		
		if($_POST['accion']=="cargardis"){
        	$u->combo_dis_central();
    		}
		}
		
		
		
		
?>