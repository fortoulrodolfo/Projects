	/* Función que, al presionar una tecla en específico, determina si la misma
	 * es admitida, permitiendo solo inclusión de números.
	 */
	function soloNumeros(e) {
		tecla = (document.all) ? e.keyCode : e.which;
		if (tecla == 8) return true;
		patron =/[0-9\s]/;
		te = String.fromCharCode(tecla);
		return patron.test(te);
	}

	/* Función que, al presionar una tecla en específico, determina si la misma
	 * es admitida, permitiendo solo inclusión de letras.
	 */
	function soloLetras(e) {
		tecla = (document.all) ? e.keyCode : e.which;
		if (tecla == 8) return true;
		patron =/[a-zñA-ZÑ\s]/;
		te = String.fromCharCode(tecla);
		return patron.test(te);
	}

	/* Determina si la nueva clave introducida tiene al menos 3 caracteres para
	 * habilitar el botón de continuar, en la opción de cambio de contraseña.
	 */
	function cuantosCaracteres(){
		if (document.getElementById('nueva').value.length > 0){
			document.getElementById('cambiar').disabled = false;
		}else{
			document.getElementById('cambiar').disabled = true;
		}
	}

	/* Función que convierte la clave en formato MD5 antes de enviarla al 
	 * servidor.
	 */
	function chequearClave(clave){
		clave.value = hex_md5(clave.value);
	}