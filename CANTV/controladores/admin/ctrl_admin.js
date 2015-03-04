// JavaScript Document
function registrar_inst(){
	var f=document.form1;
	if(validar_vacio()==true){}
		if(confirm){
			f.submit;}
		}

function registrar_cent(){
	var f=document.form1;
	if(validar_vacio2()==true){}
		if(confirm){
			f.submit;}
		}
	
function validar_vacio(){
	institucion=document.getElementById('institucion').value;
	rif=document.getElementById('rif').value;
	bandera=true;
	
	if(institucion==""|| rif==""){
		alert ("No Dejar Campos En Blanco");
		bandera= false;
		}
		return bandera;
	}
	
function validar_vacio2(){
	'$_POST[central]', '$_POST[cccc]', '$_POST[ubcc]', '$_POST[seriales]', '$_POST[region]', '$_POST[estado]', '$_POST[municipio]'
	central=document.getElementById('central').value;
	cccc=document.getElementById('cccc').value;
	ubcc=document.getElementById('ubcc').value;
	seriales=document.getElementById('seriales').value;
	bandera=true;
	
	if(central==""|| cccc==""|| ubcc==""|| seriales==""){
		alert ("No Dejar Campos En Blanco");
		bandera= false;
		}
		return bandera;
	}
	
function cancelar(){
	location.href="../../index.php";
	}