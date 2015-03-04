// JavaScript Document
function entrar(){
	f=document.inputs;
	if(validar_vacio()==true){
		 accion_lsd("../driver/control_principal", "login", [get_lsd('usuario'),get_lsd('contraseña')], "per");
		 }
	}

function validar_vacio(){
	usuario=document.getElementById('usuario').value;
	contraseña=document.getElementById('contraseña').value;
	bandera=true;
	if(usuario=="") {
		alert("Campo Usuario No Puede ir Vacio");
		bandera=false;
		}
		
	if(contraseña=="") {
		alert("Campo Contraseña No Puede Ir Vacio");
		bandera=false;
		}
	return bandera;
	}
        
function cerrar(){
	location.href="../driver/ishorcut.php";
	}
	
function alertas_consultores(){
	
	var c=1;
	$.ajax({
		type: "GET",
		url: "../driver/alerta_consultores.php?",
		data: 'alerta='+c,
		success: function(response){
			},
		error: function(response){
			
			
		},
		
		async: true,
	});
	
	}