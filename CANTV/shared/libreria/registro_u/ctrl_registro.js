// JavaScript Document
function registrar_u(){
	var f=document.form1;
	if(validar_vacio()==true){}
		{
	f.submit();}
		}
	
function validar_vacio(){
	cedula=document.getElementById('cedula').value;
	nombre=document.getElementById('nombre').value;
	apellido=document.getElementById('apellido').value;
	numero=document.getElementById('numero').value;
	codigo=document.getElementById('codigo').value;
	email=document.getElementById('email').value;
	contraseña=document.getElementById('contraseña').value;
	sector=document.getElementById('sector').value;
	region=document.getElementById('region').value;
	tipo_usuario=document.getElementById('tipo_usuario').value;
	
	bandera=true;
	
	if(cedula==""|| nombre==""|| apellido==""|| numero==""|| codigo==""|| email==""|| contraseña==""|| sector==""|| region==""|| tipo_usuario==""){
		alert ("No Dejar Campos En Blanco");
		bandera= false;
		}
		if (validar_contraseña()==false){
			return false
			}
		return bandera;
		}

function validar_contraseña(){
	contraseña1=document.getElementById('contraseña').value;
	contraseña2=document.getElementById('confirmar_contraseña').value;
	if(contraseña1!=contraseña2){
		alert("Contraseñas Deben Ser Iguales");
		return false;
		}else{
			return true;
			}
	}
	
function cancelar(){
	location.href="../../index.php";
	}