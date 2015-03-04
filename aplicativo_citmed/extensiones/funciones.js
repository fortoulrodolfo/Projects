	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function getParameter(){
		goforit();
		var url = location.href;
		var index = url.indexOf("?");
		index = url.indexOf("err",index) + "err".length;
		if (url.charAt(index) == "="){
			var result = url.indexOf("&",index);
			if (result == -1){
				result = url.length;
			}
			if (url.substring(index + 1,result) == "corrCon"){
				alert("Clave restablecida correctamente.");
			}else if (url.substring(index + 1,result) == "noCon"){
				alert("Error al restablecer la contraseña.");
			}else if (url.substring(index + 1,result) == "corrInh"){
				alert("El usuario ha sido inhabilitado correctamente.");
			}else if (url.substring(index + 1,result) == "noInh"){
				alert("Error al inhabilitar el usuario.");
			}
		}
	} 

	/* Función que obtiene el URL de la página que está siendo llamada.
	 */
	function obtenerURL(){
		var url = location.href;
		var index = url.indexOf("aplicativo_");
		return(url.substring(0,index));
	}
	
	/* Función que comprueba el navegador utilizado por el usuario.
	 */
	function comprobar(a){
		var navegador = navigator.appName 
		if (navegador == "Microsoft Internet Explorer"){
			if (a == "ray"){
				window.open("http://imagenes.clinicaracas.com"); 
			}
		}else{
			alert("Necesita Microsoft Internet Explorer para ejecutar esta función.");
		}
	}

	/* Función que, al presionar una tecla en específico, determina si la misma
	 * es admitida, premitiendo solo inclusión de letras.
	 */
	function soloLetras(e) {
		tecla = (document.all) ? e.keyCode : e.which;
		if (tecla == 8) return true;
		patron =/[a-zA-ZñÑ\s]/;
		te = String.fromCharCode(tecla);
		return patron.test(te);
	}
	
	
	/* Función que, al presionar una tecla en específico, determina si la misma
	 * es admitida, premitiendo solo inclusión de numeros.
	 */
	function soloNumeros(e)
	{
	var keynum = window.event ? window.event.keyCode : e.which;
	if ((keynum == 8) || (keynum == 46))
	return true;
	 
	return /\d/.test(String.fromCharCode(keynum));
	}

	/* Función que convierte la clave en formato MD5.
	 */
	function chequearClave(clave){
		clave.value = hex_md5(clave.value);
	}
	
	
	/*************************************************************************/
	/*      INICIO DE LA DEFINICIÓN DE LA FECHA Y HORA DE LA PÁGINA WEB      */
	/*************************************************************************/
	
	var dayarray = new Array("Domingo", "Lunes", "Martes", "Miércoles", 
							 "Jueves","Viernes","Sábado")
	var montharray = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", 
							   "Junio", "Julio", "Agosto", "Septiembre", 
							   "Octubre","Noviembre","Diciembre")
				
	function getthedate(){
		var mydate = new Date() 
		var year = mydate.getYear() 
		
		if(year < 1000) 
			year += 1900 
		
		var day = mydate.getDay() 
		var month = mydate.getMonth() 
		var daym = mydate.getDate() 
		
		if (daym < 10) 
			daym = "" + daym 

		var hours = mydate.getHours() 
		var minutes = mydate.getMinutes() 
		var seconds = mydate.getSeconds() 
		var dn = "AM" 
		
		if (hours >= 12) 
			dn="PM" 
			
		if (hours > 12){ 
			hours = hours - 12 
		}

		{
			d = new Date(); 
			Time24H = new Date(); 
			Time24H.setTime(d.getTime() + (d.getTimezoneOffset()*60000) + 3600000); 
		} 
		
		if(hours == 0) 
			hours = 12 

		if(minutes <= 9) 
			minutes = "0" + minutes 
		
		if (seconds <= 9) 
			seconds = "0" + seconds 

		var cdate = dayarray[day] + " " + daym + " de " + montharray[month] + " de " + 
					year + "<br>" + hours + ":" + minutes + ":" + seconds + " " + dn + "" 

		if (document.all) 
			document.all.clock.innerHTML = "<br>" + cdate 
		else if (document.getElementById) 
			document.getElementById("clock").innerHTML = cdate 
		else 
			document.write(cdate) 
	} 

	if (!document.all && !document.getElementById) 
		getthedate() 
		
	/* Función que chequea la fecha actual, para impedir que se realicen consultas
	 * con fechas posteriores a ésta.
	 */
	function fechaActual(){
		var date = new Date();
		var dia = date.getDate();
		var mes = date.getMonth() + 1;
		if (mes < 10){
			mes = "0" + mes;
		}
		var anho = date.getFullYear();
		var fechaSelec = document.getElementById("fecinf").value;
		var diaSelec = fechaSelec.substring(0,2);
		var mesSelec = fechaSelec.substring(3,5);
		var anhoSelec = fechaSelec.substring(6,10);
		
		if (anhoSelec > anho){
			alert("La fecha seleccionada es mayor a la fecha actual");
			document.getElementById("fecinf").value = "";
		}else if (anhoSelec == anho){
			if (mesSelec > mes){
				alert("La fecha seleccionada es mayor a la fecha actual");
				document.getElementById("fecinf").value = "";
			}else if (mesSelec == mes){
				if (diaSelec > dia){
					alert("La fecha seleccionada es mayor a la fecha actual");
					document.getElementById("fecinf").value = "";
				}
			}
		}
	}

	function goforit(){
		if (document.all || document.getElementById) 
		setInterval("getthedate()",1000) 
	} 

	/*************************************************************************/
	/*        FIN DE LA DEFINICIÓN DE LA FECHA Y HORA DE LA PÁGINA WEB       */
	/*************************************************************************/
	
	/*************************************************************************/
	/*                           FUNCIONES DE AJAX                           */
	/*************************************************************************/
	
	
	/*********************     DEFINICIÓN DE EVENTOS     *********************/

	/* Se añade el evento principal que sucede al cargar la página web, en el 
	 * cual se generan todas las funciones pertinentes, que necesitan 
	 * comunicación mediante AJAX.
	 */
	addEvent(window,'load',inicializarEventos,false);

	/* Se generan los eventos principales de la página web.
	 */
	function inicializarEventos(){
		addEvent(document.getElementById('formConsultaUsu'), 'submit', enviarDatos, false);
		addEvent(document.getElementById('limpiar'), 'click', enviarLimpieza, false);
	}

	/* Definición de la variable que maneja la conexión con el servidor 
	 * utilizando AJAX.
	 */
	var conexion1;
	
	
	/*******************     LIMPIEZA DE LA PÁGINA WEB     *******************/
	
	/* Se chequea el tipo de navegador utilizado y se llama a la función 
	 * encargada de solicitar la limpieza al servidor.
	 */
	function enviarLimpieza(e){
		respConsultaUsu.innerHTML = "";
		document.getElementById("login").value = "";
		document.getElementById("cedula").value = "";
		document.getElementById("nombre").value = "";
	}
	
	
	/************     BÚSQUEDA DE PACIENTES Y ORDENAR BÚSQUEDA     ***********/
	
	/* Se chequea el tipo de navegador utilizado y se llama a la función 
	 * encargada de enviar los datos de búsqueda de pacientes al servidor.
	 */
	function enviarDatos(e){
		if (window.event)
			window.event.returnValue=false;
		else
			if (e)
				e.preventDefault();
		enviarFormulario();
	}

	/* Función que construye la cadena con la cual se pasan los datos de la 
	 * búsqueda de pacientes al servidor, para realizar la consulta.
	 */
	function retornarDatos(){
		var login = document.getElementById('login').value;
		var cedula = document.getElementById('cedula').value;
		var nombre = document.getElementById('nombre').value;
		cad = 'login='+encodeURIComponent(login) + 
			  '&cedula='+encodeURIComponent(cedula) +
			  '&nombre='+encodeURIComponent(nombre);
		return cad;
	}
	
	/* Función en la que se envía el formulario de consulta de los pacientes, 
	 * al servidor, para realizar la consulta a la base de datos.
	 */
	function enviarFormulario() {
		conexion1 = crearXMLHttpRequest();
		conexion1.onreadystatechange = procesarEventos;
		conexion1.open('POST','consulta.php', true);
		conexion1.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		conexion1.send(retornarDatos());  
	}

	/* Función que envía al servidor la solicitud para ordenar la consulta 
	 * realizada.
	 */
	function cargarOrden(url) {
		if(url==''){
			return;
		}
		conexion1 = crearXMLHttpRequest();
		conexion1.onreadystatechange = procesarEventos;
		conexion1.open("GET", url, true);
		conexion1.send(null);
	}

	/* Función en la cual se obtienen los datos devueltos por el servidor, 
	 * plasmándolos en el lugar establecido para ello y añadiendo los eventos
	 * correspondientes al ordenamiento de la consulta de pacientes.
	 */
	function procesarEventos(){
		var respConsultaUsu = document.getElementById("respConsultaUsu");
		if(conexion1.readyState == 4){
			respConsultaUsu.innerHTML = conexion1.responseText;
		}else {
			respConsultaUsu.innerHTML = '<img src="images/cargBlanc.gif">';
		}
	}

	
	/**********************     FUNCIONES GENÉRICAS     **********************/
	
	/* Función en la cual se añade un evento particular a una variable 
	 * definida.
	 */
	function addEvent(elemento,nomevento,funcion,captura){
		if (elemento.attachEvent){
			elemento.attachEvent('on'+nomevento,funcion);
			return true;
		}else  
			if (elemento.addEventListener){
				elemento.addEventListener(nomevento,funcion,captura);
				return true;
			}else
				return false;
	}

	/* Se crea la petición XML-HTTP para el manejo de conexiones asíncronas 
	 * con el servidor.
	 */
	function crearXMLHttpRequest() {
		var xmlHttp=null;
		if (window.ActiveXObject) 
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		else 
			if (window.XMLHttpRequest) 
				xmlHttp = new XMLHttpRequest();
		return xmlHttp;
	}