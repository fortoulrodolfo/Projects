// JavaScript Document
function get_lsd(id){
	return document.getElementById(id).value;
	}

function limpiar_lsd(obj){
	document.getElementById(obj).value="";
	}

function objetoAjax(){
	var xmlhttp=false;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
		   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
  		}
	}
	if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
		xmlhttp = new XMLHttpRequest();
		}
	return xmlhttp;
	}

function imprimir_lsd(div,msj){
	document.getElementById(div).innerHTML=msj;	
	}

function mostrar_lsd(div){
	document.getElementById(div).style.display="block";
	}

function ocultar_lsd(div){
	document.getElementById(div).style.display="none";
	}

function set_lsd(obj,valor){
	document.getElementById(obj).value=valor;	
	}

var accion = {
	"url" 		: null,
	"salida"	: null,
	"motivo"	: null,
	"v1"	 	: null,
	"v2" 		: null,
	"v3" 		: null,
	"v4" 		: null,
	"v5" 		: null,
	"v6" 		: null,
	"v7" 		: null,
	"v8" 		: null,
	"v9" 		: null,
	"v10" 		: null,
	"v11" 		: null,
	"v12" 		: null,
	"cantidad"  	: 12,
	"url_neta"	: "",
	"error"		: function(msj){
	document.getElementById('error').innerHTML=msj;
	},
		"vacio":function(x){
			if(x.length>0){
				return true;
				}else{
					return false;	
				}
	},
		"ejecutar":function(){
			url_x = this.url_neta+this.url+".php";
			div = this.salida;
			var ajax=objetoAjax();
			ajax.open("POST",url_x,true);
			ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			if(this.cantidad==1){
				ajax.send("accion="+this.motivo+"&v1="+this.v1);
				}else if(this.cantidad==2){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2);
				}else if(this.cantidad==3){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2+"&v3="+this.v3);	
				}else if(this.cantidad==4){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2+"&v3="+this.v3+"&v4="+this.v4);	
				}else if(this.cantidad==5){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2+"&v3="+this.v3+"&v4="+this.v4+"&v5="+this.v5);	
				}else if(this.cantidad==6){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2+"&v3="+this.v3+"&v4="+this.v4+"&v5="+this.v5+"&v6="+this.v6);	
				}else if(this.cantidad==7){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2+"&v3="+this.v3+"&v4="+this.v4+"&v5="+this.v5+"&v6="+this.v6+"&v7="+this.v7);	
				}else if(this.cantidad==8){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2+"&v3="+this.v3+"&v4="+this.v4+"&v5="+this.v5+"&v6="+this.v6+"&v7="+this.v7+"&v8="+this.v8);
				}else if(this.cantidad==9){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2+"&v3="+this.v3+"&v4="+this.v4+"&v5="+this.v5+"&v6="+this.v6+"&v7="+this.v7+"&v8="+this.v8+"&v9="+this.v9);
				}else if(this.cantidad==10){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2+"&v3="+this.v3+"&v4="+this.v4+"&v5="+this.v5+"&v6="+this.v6+"&v7="+this.v7+"&v8="+this.v8+"&v9="+this.v9+"&v10="+this.v10);
				}else if(this.cantidad==11){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2+"&v3="+this.v3+"&v4="+this.v4+"&v5="+this.v5+"&v6="+this.v6+"&v7="+this.v7+"&v8="+this.v8+"&v9="+this.v9+"&v10="+this.v10+"&v11="+this.v11);
				}else if(this.cantidad==12){
					ajax.send("accion="+this.motivo+"&v1="+this.v1+"&v2="+this.v2+"&v3="+this.v3+"&v4="+this.v4+"&v5="+this.v5+"&v6="+this.v6+"&v7="+this.v7+"&v8="+this.v8+"&v9="+this.v9+"&v10="+this.v10+"&v11="+this.v11+"&v12="+this.v12);
				}
					ajax.onreadystatechange=function(){
						if(ajax.readyState<4){
							document.getElementById(div).innerHTML="Cargando ...";
							}
						if (ajax.readyState==4){	
							document.getElementById(div).innerHTML=ajax.responseText;
							}
						}
				}
			}
function accion_lsd(url,motivo,parametro,salida){
	var x    =  accion;
	x.url    = url;
	x.motivo = motivo;
	x.v1 	 = parametro[0];
	x.v2 	 = parametro[1];
	x.v3 	 = parametro[2];
	x.v4 	 = parametro[3];
	x.v5 	 = parametro[4];
	x.v6 	 = parametro[5];
	x.v7 	 = parametro[6];
	x.v8 	 = parametro[7];
	x.v9 	 = parametro[8];
	x.v10 	 = parametro[9];
	x.v11 	 = parametro[10];
	x.v12 	 = parametro[11];
	x.salida = salida; 
	x.ejecutar();
	}
	
function cargar_pag_lsd(pagina,capa,tipo){
    var ajax;
    ajax = objetoAjax();
    if (tipo==0){
	    url_x = pagina+".html";
    }else if (tipo==1){
	    url_x = pagina+".php";
    }
    ajax.open("POST", url_x, true);
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	ajax.onreadystatechange = function() {
		if (ajax.readyState==1){
			document.getElementById(capa).innerHTML = " Aguarde por favor...";
			     }
		if (ajax.readyState == 4) {
		   	document.getElementById(capa).innerHTML=ajax.responseText; 
		     }}
			 ajax.send(null);
			 }