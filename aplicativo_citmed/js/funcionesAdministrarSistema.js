	/*************************************************************************/
	/*                               FUNCIONES JS                            */
	/*************************************************************************/
	
	
	
	
	/*************************************************************************/
	/*                   INICIO FUNCIONES DE JS PARA SERVICIO                */
	/*************************************************************************/
	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function buscarServicio(){
		
		var codigo_servicio;
		var rows = document.getElementById("tabla_servicios").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
		for (i = 0; i < rows.length; i++) {
				var oCells = document.getElementById("tabla_servicios").rows.item(i).cells;
				if (oCells[0].firstChild.checked) {
					codigo_servicio=rows[i].cells[1].innerHTML;
				}
			}
		servicio = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarServicio.php?",
		data: 'buscar='+codigo_servicio,
		success: function(response){
			
			servicio=eval("(" + response + ")");
			
			if(servicio.length>0){
					
					window.location.href="#close";
					document.getElementById("codigo_servicio").value=servicio[0];
					document.getElementById("nombre_servicio").value=servicio[1];
					
					for(k=0 ; k<document.getElementById("validar_equipo").length ; k++){
						var v = document.getElementById("validar_equipo").options[k].value;
						if(v==servicio[2]){
							document.getElementById("validar_equipo").options[k].selected=true;
							}
						};
					
					if (document.getElementById("operacionServicio").value=="Actualizar"){
						document.getElementById("nombre_servicio").disabled=false;
						document.getElementById("validar_equipo").disabled=false;
					}
			
			}
			else {
				
					alert("Debe seleccionar un servicio para continuar");
					
				}
			
			},
			error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
				
			},
			
			async: true,
		});
	
	}
	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function mostrarFormularioServicio(id){

				if (id=="registrarServicio"){
					document.getElementById('titulo').innerHTML="Registrar Servicio";
					document.getElementById("btnGuardar").value="Guardar";
					document.getElementById('operacionServicio').value="Registrar";
					document.getElementById("btnBuscar").hidden=true;
					document.getElementById("nombre_servicio").disabled=false;
					document.getElementById("validar_equipo").disabled=false;
					limpiarServicio();
					}
				if (id=="modificarServicio"){
						document.getElementById('titulo').innerHTML="Actualizar Servicio";
						document.getElementById("btnGuardar").value="Guardar";
						document.getElementById('operacionServicio').value="Actualizar";
						document.getElementById("btnBuscar").hidden=false;
						limpiarServicio();
						}
				 if (id=="eliminarServicio"){
						 document.getElementById('titulo').innerHTML="Eliminar Servicio";
						 document.getElementById("btnGuardar").value="Eliminar";
						 document.getElementById('operacionServicio').value="Eliminar";
						 document.getElementById("btnBuscar").hidden=false;
						 limpiarServicio();
						 }
					
		}			 					

	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function validarVacioServicio(formulario){
		
		
		if(formulario.codigo_servicio.value== "" || formulario.nombre_servicio.value== "" || formulario.validar_equipo.value== "" ){
			 alert('Debes completar todos los campos') ; 
			 return false ; 
			}
		else {
			return true;
			}
		
		}	
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function limpiarServicio(){
		
		document.getElementById("codigo_servicio").value="";
		document.getElementById("nombre_servicio").value="";
		document.getElementById("validar_equipo").options[0].selected=true;
		document.getElementById("codigo_servicio").readOnly=false;
		
		if(document.getElementById("operacionServicio").value != "Registrar"){
			document.getElementById("codigo_servicio").readOnly=true;
			document.getElementById("nombre_servicio").disabled=true;
			document.getElementById("validar_equipo").disabled=true;
			}
					
		}
		
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function cargarUnidad(id){
		
			unidad= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarServicio.php?",
			data: 'cargarUnidad=unidad',
			success: function(response){
				
				unidad=eval("(" + response + ")");
				
				if(unidad.length > 0){
		
					for(var i=0; unidad.length>i;++i){
			  			
			  			document.getElementById(id).options[i+1]=new Option(unidad[i].nombre, unidad[i].id);
						}
					
					}
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: true,
			});
				
				
		}	
		
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
		
		function cargarServicios(){
		
			servicio= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarServicio.php?",
			data: 'cargarUnidad=unidad',
			success: function(response){
				
				servicio=eval("(" + response + ")");
				
				if(servicio.length > 0){
					
					var tabla   = document.getElementById("tabla_servicios");
					var tblBody = document.createElement("tbody");
					
					var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("th");
							  celda1.setAttribute("align","center");
							  var textoCelda1 = " ";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
						
							  var celda2 = document.createElement("th");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode("Codigo");
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("th");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode("Unidad");
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
		
					for(var i=0; i<servicio.length; ++i){
			  			// Crea las hileras de la tabla
						var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("td");
							  celda1.setAttribute("align","center");
							  var textoCelda1 = "<input type='radio' name='seleccion_servicio' value=''>";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
						
							  var celda2 = document.createElement("td");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode(servicio[i].id);
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("td");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode(servicio[i].nombre);
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
						}
					
					
					// posiciona el <tbody> debajo del elemento <table>
					  tabla.appendChild(tblBody);
					  
					   $('tr').click(
							function() {
								$('input[type=radio]',this).attr('checked','checked');
							})
							}
					
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: true,
			});
				
				
		}		 								 					

	/*************************************************************************/
	/*                   FIN FUNCIONES DE JS PARA SERVICIO                   */
	/*************************************************************************/
	
	
	
	
	
	
	/*************************************************************************/
	/*             INICIO FUNCIONES DE JS PARA PROCEDIMIENTOS                */
	/*************************************************************************/


	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function buscarProcedimiento(){
		
		var codigo_procedimiento;
		var rows = document.getElementById("tabla_procedimientos").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
		for (i = 0; i < rows.length; i++) {
				var oCells = document.getElementById("tabla_procedimientos").rows.item(i).cells;
				if (oCells[0].firstChild.checked) {
					codigo_procedimiento=rows[i].cells[1].innerHTML;
				}
			}
		procedimiento = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarProcedimiento.php?",
		data: 'buscar='+codigo_procedimiento,
		success: function(response){
			
			procedimiento=eval("(" + response + ")");
			
			if(procedimiento.length>0){
				
					window.location.href="#close";
					document.getElementById("codigo_procedimiento").value=procedimiento[0];
					document.getElementById("nombre_procedimiento").value=procedimiento[1];
					document.getElementById("duracion_procedimiento").value=procedimiento[2]
					document.getElementById("tiempo_limpieza").value=procedimiento[6];
					
					for(k=0 ; k<document.getElementById("unidad_proced").length ; k++){
						var v = document.getElementById("unidad_proced").options[k].value;
						if(v==procedimiento[4]){
							document.getElementById("unidad_proced").options[k].selected=true;
							}
						};
						
					for(j=0 ; j<document.getElementById("tipo_proced").length ; j++){
						var t = document.getElementById("tipo_proced").options[j].value;
						if(t==procedimiento[3]){
							document.getElementById("tipo_proced").options[j].selected=true;
							}
						};
						
					for(h=0 ; h<document.getElementById("tipo_estudio").length ; h++){
						var n = document.getElementById("tipo_estudio").options[h].value;
						if(n==procedimiento[5]){
							document.getElementById("tipo_estudio").options[h].selected=true;
							}
						};
					
					if (document.getElementById("operacionProcedimiento").value=="Actualizar"){
						document.getElementById("nombre_procedimiento").disabled=false;
						document.getElementById("duracion_procedimiento").disabled=false;
						document.getElementById("unidad_proced").disabled=false;
						document.getElementById("tipo_proced").disabled=false;
						document.getElementById("tipo_estudio").disabled=false;
						document.getElementById("tiempo_limpieza").disabled=false;
					}
					
			}
			else {
				
					alert("Debe seleccionar un Procedimiento para continuar");
					
				}
			
			},
			error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
				
			},
			
			async: true,
		});
	
	}
	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function mostrarFormularioProcedimiento(id){

				if (id=="registrarProcedimiento"){
					document.getElementById('titulo').innerHTML="Registrar Procedimiento";
					document.getElementById("btnGuardar").value="Guardar";
					document.getElementById('operacionProcedimiento').value="Registrar";
					document.getElementById("btnBuscar").hidden=true;
					document.getElementById("nombre_procedimiento").disabled=false;
					document.getElementById("duracion_procedimiento").disabled=false;
					document.getElementById("unidad_proced").disabled=false;
					document.getElementById("tipo_proced").disabled=false;
					document.getElementById("tipo_estudio").disabled=false;
					document.getElementById("tiempo_limpieza").disabled=false;
					document.getElementById("codigo_procedimiento").readOnly=false;
					limpiarProcedimiento();
					}
				if (id=="modificarProcedimiento"){
						document.getElementById('titulo').innerHTML="Actualizar Procedimiento";
						document.getElementById("btnGuardar").value="Guardar";
						document.getElementById('operacionProcedimiento').value="Actualizar";
						document.getElementById("btnBuscar").hidden=false;
						limpiarProcedimiento();
						}
				 if (id=="eliminarProcedimiento"){
						 document.getElementById('titulo').innerHTML="Eliminar Procedimiento";
						 document.getElementById("btnGuardar").value="Eliminar";
						 document.getElementById('operacionProcedimiento').value="Eliminar";
						 document.getElementById("btnBuscar").hidden=false;
						 limpiarProcedimiento();
						 }
					
		}			 					

	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function validarVacioProcedimiento(formulario){
		
		
		if(formulario.codigo_procedimiento.value== "" || formulario.nombre_procedimiento.value== "" || formulario.duracion_procedimiento.value== ""
		|| formulario.tipo_proced.value== "" || formulario.unidad_proced.value== "" || formulario.tipo_estudio.value== "" || formulario.tiempo_limpieza.value== "" ){
			 alert('Debes completar todos los campos') ; 
			 return false ; 
			}
		else {
			return true;
			}
		
		}	
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function limpiarProcedimiento(){
		
		document.getElementById("codigo_procedimiento").value="";
		document.getElementById("codigo_procedimiento").readOnly=false;
		document.getElementById("nombre_procedimiento").value="";
		document.getElementById("unidad_proced").options[0].selected=true;
		document.getElementById("tipo_proced").options[0].selected=true;
		document.getElementById("tipo_estudio").options[0].selected=true;
		document.getElementById("duracion_procedimiento").value="";
		document.getElementById("tiempo_limpieza").value="";
				
		if(document.getElementById("operacionProcedimiento").value != "Registrar"){
			document.getElementById("codigo_procedimiento").readOnly=true;
			document.getElementById("nombre_procedimiento").disabled=true;
			document.getElementById("duracion_procedimiento").disabled=true;
			document.getElementById("unidad_proced").disabled=true;
			document.getElementById("tipo_proced").disabled=true;
			document.getElementById("tipo_estudio").disabled=true;
			document.getElementById("tiempo_limpieza").disabled=true;
			}
					
		}
		
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */

		
		
	function cargarProcedimiento(id){
		
			procedimiento= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarProcedimiento.php?",
			data: 'cargarProcedimientos=proced',
			success: function(response){
				
				procedimiento=eval("(" + response + ")");
				
				if(procedimiento.length > 0){
		
					for(var i=0; procedimiento.length>i;++i){
			  			var nombre = procedimiento[i].nombre + " "+ procedimiento[i].estudio ;
			  			document.getElementById(id).options[i]=new Option(nombre, procedimiento[i].id);
						}
					
					}
				else{
					document.getElementById(id).options[0]=new Option("No hay Procedimientos", "");			
					}
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: false,
			});

	
	}
	
	
	
	function cargarProcedimientosCompuestos(id){
		
		procedimiento = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarProcedimientoCompuesto.php?",
		data: 'cargarCompuesto=compuesto',
		success: function(response){
			procedimiento=eval("(" + response + ")");
			
				if(procedimiento.length > 0){
					
					var j=document.getElementById(id).length-1;
					for(var i=0; procedimiento.length>i; ++i){
			  			
			  			document.getElementById(id).options[j+1]=new Option( procedimiento[i].nombre,  procedimiento[i].id);
						
						}
					
					}
				else{
				document.getElementById(id).options[0]=new Option("No hay Procedimientos", "");			
				}
			
			},
			error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
				
			},
			
			async: false,
		});
	
		
		}
		
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
		
		function cargarProcedimientos(){
		
			procedimiento= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarProcedimiento.php?",
			data: 'cargarProcedimientos=proced',
			success: function(response){
				
				procedimiento=eval("(" + response + ")");
				
				if(procedimiento.length > 0){
					
					var tabla   = document.getElementById("tabla_procedimientos");
					var tblBody = document.createElement("tbody");
					
					var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("th");
							  var textoCelda1 = " ";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
						
							  var celda2 = document.createElement("th");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode("Codigo");
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("th");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode("Nombre");
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							  var celda4 = document.createElement("th");
							  celda4.setAttribute("align","center");
							  var textoCelda4 = document.createTextNode("Estudio");
							  celda4.appendChild(textoCelda4);
							  hilera.appendChild(celda4);
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
		
					for(var i=0; i<procedimiento.length; ++i){
			  			// Crea las hileras de la tabla
						var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("td");
							  celda1.setAttribute("align","center");
							  var textoCelda1 = "<input type='radio' name='seleccion_procedimientos' value=''>";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
						
							  var celda2 = document.createElement("td");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode(procedimiento[i].id);
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("td");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode(procedimiento[i].nombre);
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							  var celda4 = document.createElement("td");
							  celda4.setAttribute("align","center");
							  var textoCelda4 = document.createTextNode(procedimiento[i].estudio);
							  celda4.appendChild(textoCelda4);
							  hilera.appendChild(celda4);
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
						}
					
					
					// posiciona el <tbody> debajo del elemento <table>
					  tabla.appendChild(tblBody);
					  
					   $('tr').click(
							function() {
								$('input[type=radio]',this).attr('checked','checked');
							})
							}
					
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: true,
			});
				
				
		}		 				
		
		
		/*************************************************************************/
		/*             FIN FUNCIONES DE JS PARA PROCEDIMIENTOS                   */
		/*************************************************************************/
		
		
		
		
		
		
		
		/*************************************************************************/
		/*                  INICIO FUNCIONES DE JS PARA SALA                     */
		/*************************************************************************/


		

	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	 
	function buscarSala(){
		var codigo_sala;
		var rows = document.getElementById("tabla_salas").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
		for (i = 0; i < rows.length; i++) {
				var oCells = document.getElementById("tabla_salas").rows.item(i).cells;
				if (oCells[0].firstChild.checked) {
					codigo_sala=rows[i].cells[1].innerHTML;
				}
			}
		sala = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarSala.php?",
		data: 'buscar='+codigo_sala,
		success: function(response){
			
			sala=eval("(" + response + ")");
			
			if(sala.length>0){
				
					window.location.href="#close";
					document.getElementById("codigo_sala").value=sala[0];
					document.getElementById("nombre_sala").value=sala[1];
					
					for(k=0 ; k<document.getElementById("status_sala").length ; k++){
						var v = document.getElementById("status_sala").options[k].value;
						if(v==sala[2]){
							document.getElementById("status_sala").options[k].selected=true;
							}
						};
					
					for(j=0 ; j<document.getElementById("unidad_sala").length ; j++){
						var v = document.getElementById("unidad_sala").options[j].value;
						if(v==sala[3]){
							document.getElementById("unidad_sala").options[j].selected=true;
							}
						};
					
					if (document.getElementById("operacionSala").value=="Actualizar"){
						document.getElementById("nombre_sala").disabled=false;
						document.getElementById("status_sala").disabled=false;
						document.getElementById("unidad_sala").disabled=false;
					}
					
			
			}
			else {
				
					alert("Debe seleccionar una sala para continuar");
				}
			
			},
			error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
				
			},
			
			async: true,
		});
	
	}
	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function mostrarFormularioSala(id){

				if (id=="registrarSala"){
					document.getElementById('titulo').innerHTML="Registrar Sala";
					document.getElementById("btnGuardar").value="Guardar";
					document.getElementById('operacionSala').value="Registrar";
					document.getElementById("btnBuscar").hidden=true;
					document.getElementById("nombre_sala").disabled=false;
					document.getElementById("status_sala").disabled=false;
					document.getElementById("unidad_sala").disabled=false;
					limpiarSala();
					}
				if (id=="modificarSala"){
						document.getElementById('titulo').innerHTML="Actualizar Sala";
						document.getElementById("btnGuardar").value="Guardar";
						document.getElementById('operacionSala').value="Actualizar";
						document.getElementById("btnBuscar").hidden=false;
						limpiarSala();
						}
				 if (id=="eliminarSala"){
						 document.getElementById('titulo').innerHTML="Eliminar Sala";
						 document.getElementById("btnGuardar").value="Eliminar";
						 document.getElementById('operacionSala').value="Eliminar";
						 document.getElementById("btnBuscar").hidden=false;
						 limpiarSala();
						 }
					
		}			 					

	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function validarVacioSala(formulario){
		
		
		if(formulario.codigo_sala.value== "" || formulario.nombre_sala.value== "" || formulario.status_sala.value== "" || formulario.unidad_sala.value== "" ){
			 alert('Debes completar todos los campos') ; 
			 return false ; 
			}
		else {
			return true;
			}
		
		}	
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function limpiarSala(){
		
		document.getElementById("codigo_sala").value="";
		document.getElementById("codigo_sala").readOnly=false;
		document.getElementById("nombre_sala").value="";
		document.getElementById("status_sala").options[0].selected=true;
		document.getElementById("unidad_sala").options[0].selected=true;
		
		if(document.getElementById("operacionSala").value != "Registrar"){
			document.getElementById("codigo_sala").readOnly=true;
			document.getElementById("nombre_sala").disabled=true;
			document.getElementById("status_sala").disabled=true;
			document.getElementById("unidad_sala").disabled=true;
			}
					
		}
		
		
			/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function cargarSala(id, activa){
			
			unidad= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarSala.php?",
			data: 'salas=salas&activa='+activa,
			success: function(response){

				sala=eval("(" + response + ")");
				
				if(sala.length > 0){
		
					for(var i=0; sala.length>i;++i){
			  			
			  			document.getElementById(id).options[i+1]=new Option(sala[i].name, sala[i].id);
						}
					
					}
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: true,
			});
				
				
		}	
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
		
		function cargarSalas(){
			var activa="";
			sala= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarSala.php?",
			data: 'salas=salas&activa='+activa,
			success: function(response){
				
				sala=eval("(" + response + ")");
				
				if(sala.length > 0){
					
					var tabla   = document.getElementById("tabla_salas");
					var tblBody = document.createElement("tbody");
					
					var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("th");
							  var textoCelda1 = " ";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
						
							  var celda2 = document.createElement("th");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode("Codigo");
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("th");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode("Nombre");
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
		
					for(var i=0; i<sala.length; ++i){
			  			// Crea las hileras de la tabla
						var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("td");
							  celda1.setAttribute("align","center");
							  var textoCelda1 = "<input type='radio' name='seleccion_sala' value=''>";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
						
							  var celda2 = document.createElement("td");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode(sala[i].id);
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("td");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode(sala[i].name);
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
						}
					
					
					// posiciona el <tbody> debajo del elemento <table>
					  tabla.appendChild(tblBody);
					  
					   $('tr').click(
							function() {
								$('input[type=radio]',this).attr('checked','checked');
							})
							}
					
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: true,
			});
				
				
		}		 								 							 			
		
		
		
			
		
		/*************************************************************************/
		/*                  FIN FUNCIONES DE JS PARA SALA                        */
		/*************************************************************************/






	
		
		/*************************************************************************/
		/*             INICIO FUNCIONES DE JS PARA TIPOS PROCED.                 */
		/*************************************************************************/


		

	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function buscarTipo(){
		var codigo_tipo;
		var rows = document.getElementById("tabla_tipos").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
		for (i = 0; i < rows.length; i++) {
				var oCells = document.getElementById("tabla_tipos").rows.item(i).cells;
				if (oCells[0].firstChild.checked) {
					codigo_tipo=rows[i].cells[1].innerHTML;
				}
			}
		tipo = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarTipoProcedimiento.php?",
		data: 'buscar='+codigo_tipo,
		success: function(response){
			
			tipo=eval("(" + response + ")");
			
			if(tipo.length>0){
					
					window.location.href="#close";
					document.getElementById("codigo_tipo").value=tipo[0];
					document.getElementById("nombre_tipo").value=tipo[1];
					
					
					if (document.getElementById("operacionTipo").value=="Actualizar"){
						document.getElementById("nombre_tipo").disabled=false;
					}
					
			
			}
			else {
				
					alert("El tipo asociado a ese codigo no existe");
					document.getElementById("codigo_tipo").value="";
					document.getElementById("codigo_tipo").focus();
				}
			
			},
			error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
				
			},
			
			async: true,
		});
	
	}
	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function mostrarFormularioTipo(id){

				if (id=="registrarTipo"){
					document.getElementById('titulo').innerHTML="Registrar Tipo Procedimiento";
					document.getElementById("btnGuardar").value="Guardar";
					document.getElementById('operacionTipo').value="Registrar";
					document.getElementById("btnBuscar").hidden=true;
					document.getElementById("nombre_tipo").disabled=false;
					limpiarTipo();
					}
				if (id=="modificarTipo"){
						document.getElementById('titulo').innerHTML="Actualizar Tipo Procedimiento";
						document.getElementById("btnGuardar").value="Guardar";
						document.getElementById('operacionTipo').value="Actualizar";
						document.getElementById("btnBuscar").hidden=false;
						limpiarTipo();
						}
				 if (id=="eliminarTipo"){
						 document.getElementById('titulo').innerHTML="Eliminar Tipo Procedimiento";
						 document.getElementById("btnGuardar").value="Eliminar";
						 document.getElementById('operacionTipo').value="Eliminar";
						 document.getElementById("btnBuscar").hidden=false;
						 limpiarTipo();
						 }
					
		}			 					

	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function validarVacioTipo(formulario){
		
		
		if(formulario.codigo_tipo.value== "" || formulario.nombre_tipo.value== ""){
			 alert('Debes completar todos los campos') ; 
			 return false ; 
			}
		else {
			return true;
			}
		
		}	
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function limpiarTipo(){
		
		document.getElementById("codigo_tipo").value="";
		document.getElementById("nombre_tipo").value="";
		document.getElementById("codigo_tipo").readOnly=false;
		
		if(document.getElementById("operacionTipo").value != "Registrar"){
			document.getElementById("nombre_tipo").disabled=true;
			document.getElementById("codigo_tipo").readOnly=true;
			}
					
		}			 
		
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function cargarTiposProced(id){
		
			tipos= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarTipoProcedimiento.php?",
			data: 'cargarTipos=tipo',
			success: function(response){
				
				tipos=eval("(" + response + ")");
				
				if(tipos.length > 0){
		
					for(var i=0; tipos.length>i;++i){
			  			
			  			document.getElementById(id).options[i+1]=new Option(tipos[i].nombre, tipos[i].id);
						}
					
					}
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: true,
			});
			
				
		}	
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
		
		function cargarTipos(){
		
			tipo= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarTipoProcedimiento.php?",
			data: 'cargarTipos=tipo',
			success: function(response){
				
				tipo=eval("(" + response + ")");
				
				if(tipo.length > 0){
					
					var tabla   = document.getElementById("tabla_tipos");
					var tblBody = document.createElement("tbody");
					
					var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("th");
							  var textoCelda1 = " ";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
						
							  var celda2 = document.createElement("th");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode("Codigo");
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("th");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode("Nombre");
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
		
					for(var i=0; i<tipo.length; ++i){
			  			// Crea las hileras de la tabla
						var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("td");
							  celda1.setAttribute("align","center");
							  var textoCelda1 = "<input type='radio' name='seleccion_tipos' value=''>";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
						
							  var celda2 = document.createElement("td");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode(tipo[i].id);
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("td");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode(tipo[i].nombre);
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
						}
					
					
					// posiciona el <tbody> debajo del elemento <table>
					  tabla.appendChild(tblBody);
					  
					   $('tr').click(
							function() {
								$('input[type=radio]',this).attr('checked','checked');
							})
							}
					
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: true,
			});
				
				
		}		 								 	
					
					
					
		
		/*************************************************************************/
		/*             FIN FUNCIONES DE JS PARA TIPOS PROCED.                    */
		/*************************************************************************/


		
		
			
		
		/*************************************************************************/
		/*                  INICIO FUNCIONES DE JS PARA EQUIPO                   */
		/*************************************************************************/


		

	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	 
	function buscarEquipo(){
		
		var codigo_equipo;
		var rows = document.getElementById("tabla_equipos").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
		for (i = 0; i < rows.length; i++) {
				var oCells = document.getElementById("tabla_equipos").rows.item(i).cells;
				if (oCells[0].firstChild.checked) {
					codigo_equipo=rows[i].cells[1].innerHTML;
				}
			}
		equipo = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarEquipo.php?",
		data: 'buscar='+codigo_equipo,
		success: function(response){

			equipo=eval("(" + response + ")");
			
			if(equipo.datos[0]!= null){
					
					window.location.href="#close";
					limpiarEquipo();
					document.getElementById("codigo_equipo").value=equipo.datos[0];
					document.getElementById("nombre_equipo").value=equipo.datos[1];
					
					for(k=0 ; k<document.getElementById("status_equipo").length ; k++){
						var v = document.getElementById("status_equipo").options[k].value;
						if(v==equipo.datos[2]){
							document.getElementById("status_equipo").options[k].selected=true;
							}
						};
					
					for(j=0 ; j<document.getElementById("fijo_equipo").length ; j++){
						var v = document.getElementById("fijo_equipo").options[j].value;
						if(v==equipo.datos[3]){
							document.getElementById("fijo_equipo").options[j].selected=true;
							}
						};
					for(i=0 ; i<document.getElementById("sala_equipo").length ; i++){
						var v = document.getElementById("sala_equipo").options[i].value;
						if(v==equipo.datos[4]){
							document.getElementById("sala_equipo").options[i].selected=true;
							}
						};
					
					for(i = document.getElementById("proced_equipo1").length - 1; i>=0; i--){
						var v = document.getElementById("proced_equipo1").options[i].value;
							
							for(j=0 ; j<equipo.procedimientos.length ; j++){
								if(v==equipo.procedimientos[j].id){
									agregarOption(document.getElementById("proced_equipo2"), document.getElementById("proced_equipo1").options[i].text, document.getElementById("proced_equipo1").options[i].value);
									document.getElementById("proced_equipo1").remove(i);
									}
							}
							
						};
					
					if (document.getElementById("operacionEquipo").value=="Actualizar"){
						document.getElementById("nombre_equipo").disabled=false;
						document.getElementById("status_equipo").disabled=false;
						document.getElementById("sala_equipo").disabled=false;
						document.getElementById("fijo_equipo").disabled=false;
						document.getElementById("proced_equipo1").disabled=false;
						document.getElementById("proced_equipo2").disabled=false;
					}
					
			}
			else {
				
					alert("Seleccione un Equipo para continuar");
					
				}
			
			},
			error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
				
			},
			
			async: true,
		});
	
	}
	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function mostrarFormularioEquipo(id){

				if (id=="registrarEquipo"){
					document.getElementById('titulo').innerHTML="Registrar Equipo";
					document.getElementById("btnGuardar").value="Guardar";
					document.getElementById('operacionEquipo').value="Registrar";
					document.getElementById("btnBuscar").hidden=true;
					document.getElementById("nombre_equipo").disabled=false;
					document.getElementById("status_equipo").disabled=false;
					document.getElementById("sala_equipo").disabled=false;
					document.getElementById("fijo_equipo").disabled=false;
					limpiarEquipo();
					}
				if (id=="modificarEquipo"){
						document.getElementById('titulo').innerHTML="Actualizar Equipo";
						document.getElementById("btnGuardar").value="Guardar";
						document.getElementById('operacionEquipo').value="Actualizar";
						document.getElementById("btnBuscar").hidden=false;
						limpiarEquipo();
						}
				 if (id=="eliminarEquipo"){
						 document.getElementById('titulo').innerHTML="Eliminar Equipo";
						 document.getElementById("btnGuardar").value="Eliminar";
						 document.getElementById('operacionEquipo').value="Eliminar";
						 document.getElementById("btnBuscar").hidden=false;
						 limpiarEquipo();
						 }
					
		}			 					

	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function validarVacioEquipo(formulario){
		
		
		if(formulario.codigo_equipo.value== "" || formulario.nombre_equipo.value== "" || formulario.status_equipo.value== "" || formulario.sala_equipo.value== ""
		|| formulario.fijo_equipo.value== "" ){
			 alert('Debes completar todos los campos') ; 
			 return false ; 
			}
		else {
			for(k=0 ; k<document.getElementById("proced_equipo2").length ; k++){
								document.getElementById("proced_equipo2").options[k].selected=true;
					}
			return true;
			}
		
		}	
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function limpiarEquipo(){
		
		document.getElementById("codigo_equipo").value="";
		document.getElementById("codigo_equipo").readOnly=false;
		document.getElementById("nombre_equipo").value="";
		document.getElementById("status_equipo").options[0].selected=true;
		document.getElementById("sala_equipo").options[0].selected=true;
		document.getElementById("fijo_equipo").options[0].selected=true;
		document.getElementById("proced_equipo1").disabled=false;
		document.getElementById("proced_equipo2").disabled=false;
		cargarProcedimiento('proced_equipo1');
		document.getElementById("proced_equipo2").options.length=0;
		
		
		if(document.getElementById("operacionEquipo").value != "Registrar"){
			document.getElementById("codigo_equipo").readOnly=true;
			document.getElementById("nombre_equipo").disabled=true;
			document.getElementById("status_equipo").disabled=true;
			document.getElementById("sala_equipo").disabled=true;
			document.getElementById("fijo_equipo").disabled=true;
			document.getElementById("proced_equipo1").disabled=true;
			document.getElementById("proced_equipo2").disabled=true;
			
			
			}
					
		}
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
		
		function cargarEquipos(){
		
			equipo= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarEquipo.php?",
			data: 'cargarEquipos=equipos',
			success: function(response){
				
				equipo=eval("(" + response + ")");
				
				if(equipo.length > 0){
					
					var tabla   = document.getElementById("tabla_equipos");
					var tblBody = document.createElement("tbody");
					
					var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("th");
							  var textoCelda1 = " ";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
						
							  var celda2 = document.createElement("th");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode("Codigo");
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("th");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode("Nombre");
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							  var celda4 = document.createElement("th");
							  celda4.setAttribute("align","center");
							  var textoCelda4 = document.createTextNode("Posición");
							  celda4.appendChild(textoCelda4);
							  hilera.appendChild(celda4);
							  
							  var celda5 = document.createElement("th");
							  celda5.setAttribute("align","center");
							  var textoCelda5 = document.createTextNode("Sala");
							  celda5.appendChild(textoCelda5);
							  hilera.appendChild(celda5);
							  
							  var celda7 = document.createElement("th");
							  celda7.setAttribute("align","center");
							  var textoCelda7 = document.createTextNode("Status");
							  celda7.appendChild(textoCelda7);
							  hilera.appendChild(celda7);
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
		
					for(var i=0; i<equipo.length; ++i){
			  			// Crea las hileras de la tabla
						var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("td");
							  celda1.setAttribute("align","center");
							  var textoCelda1 = "<input type='radio' name='seleccion_equipos' value=''>";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
						
							  var celda2 = document.createElement("td");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode(equipo[i].id);
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("td");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode(equipo[i].nombre);
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							  var celda4 = document.createElement("td");
							  celda4.setAttribute("align","center");
							  var textoCelda4 = document.createTextNode(equipo[i].fijo);
							  celda4.appendChild(textoCelda4);
							  hilera.appendChild(celda4);
							  
							  var celda5 = document.createElement("td");
							  celda5.setAttribute("align","center");
							  var textoCelda5 = document.createTextNode(equipo[i].sala);
							  celda5.appendChild(textoCelda5);
							  hilera.appendChild(celda5);
							  
							  var celda7 = document.createElement("td");
							  celda7.setAttribute("align","center");
							  var textoCelda7 = document.createTextNode(equipo[i].status);
							  celda7.appendChild(textoCelda7);
							  hilera.appendChild(celda7);
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
						}
					
					
					// posiciona el <tbody> debajo del elemento <table>
					  tabla.appendChild(tblBody);
					  
					   $('tr').click(
							function() {
								$('input[type=radio]',this).attr('checked','checked');
							})
							}
					
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: true,
			});
				
				
		}
		
		
		
		
		
		/*************************************************************************/
		/*                  FIN FUNCIONES DE JS PARA EQUIPO                      */
		/*************************************************************************/


				
		
		
		/*************************************************************************/
		/*                  INICIO FUNCIONES DE JS PARA MEDICO                   */
		/*************************************************************************/


		

	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	 
	function buscarMedico(){
		
		if(document.getElementById("operacionMedico").value!= "Registrar"){
		
					var codigo_medico;
					var tipo_id;
					var rows = document.getElementById("tabla_medicos").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
					for (i = 0; i < rows.length; i++) {
							var oCells = document.getElementById("tabla_medicos").rows.item(i).cells;
							if (oCells[0].firstChild.checked) {
								tipo_id=rows[i].cells[1].innerHTML;
								codigo_medico=rows[i].cells[2].innerHTML;
							}
						}
						
					medico = new Array();
					$.ajax({
					type: "GET",
					url: "../consultas/enviarMedico.php?",
					data: 'buscar=cargar&tipo_id='+tipo_id+"&codigo="+codigo_medico,
					success: function(response){
						medico=eval("(" + response + ")");
						if(medico.length>0){
							
								window.location.href="#close";
								document.getElementById("codigo_medico").value=medico[1];
								document.getElementById("nombre_medico").value=medico[2]+ " " + medico[3]+ " " + medico[4]+ " " + medico[5];
								
								for(k=0 ; k<document.getElementById("tipo_id").length ; k++){
									var v = document.getElementById("tipo_id").options[k].value;
									if(v==medico[0]){
										document.getElementById("tipo_id").options[k].selected=true;
										}
									};
								
								for(k=0 ; k<document.getElementById("status_medico").length ; k++){
									var v = document.getElementById("status_medico").options[k].value;
									if(v==medico[6]){
										document.getElementById("status_medico").options[k].selected=true;
										}
									};
								
								for(j=0 ; j<document.getElementById("unidad_medico").length ; j++){
									var v = document.getElementById("unidad_medico").options[j].value;
									if(v==medico[7]){
										document.getElementById("unidad_medico").options[j].selected=true;
										}
									};
								
								
								if (document.getElementById("operacionMedico").value=="Actualizar"){
									document.getElementById("status_medico").disabled=false;
									document.getElementById("unidad_medico").disabled=false;	
								}	
						
						}
						else {
							
								alert("Debe seleccionar un Medico para continuar");
							}
						
						},
						error:function (xhr, ajaxOptions, thrownError){
						alert(xhr.status);
						alert(thrownError);
							
						},
						
						async: true,
					});
				}
				else{
					
						var tipo_id = document.getElementById('tipo_id').value;
					    var id = document.getElementById('codigo_medico').value;
						medico = new Array();
						$.ajax({
						type: "GET",
						url: "../consultas/consultasSERVINTE.php?",
						data: 'buscarMedico=buscar&tipo_id='+tipo_id+"&id="+id,
						success: function(response){
							medico=eval("(" + response + ")");
							
							if(medico[0]!=null){
								   
									document.getElementById("nombre_medico").value=medico[3]+ " " + medico[5];	
								}
							else{
								alert("El medico no existe en Servinte");
								}
							},
							error:function (xhr, ajaxOptions, thrownError){
							alert(xhr.status);
							alert(thrownError);
								
							},
							
							async: true,
						});
					}
								
	}
	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function mostrarFormularioMedico(id){

				if (id=="registrarMedico"){
					document.getElementById('titulo').innerHTML="Registrar Medico";
					document.getElementById("btnGuardar").value="Guardar";
					document.getElementById('operacionMedico').value="Registrar";
					document.getElementById("btnBuscarSVT").hidden=false;
					document.getElementById("btnBuscar").hidden=true;
					document.getElementById("status_medico").disabled=false;
					document.getElementById("nombre_medico").readOnly=true;
					document.getElementById("unidad_medico").disabled=false;
					limpiarMedico();
					}
				if (id=="modificarMedico"){
						document.getElementById('titulo').innerHTML="Actualizar Medico";
						document.getElementById("btnGuardar").value="Guardar";
						document.getElementById("btnBuscarSVT").hidden=true;
						document.getElementById("btnBuscar").hidden=false;
						document.getElementById('operacionMedico').value="Actualizar";
						document.getElementById("btnBuscar").hidden=false;
						limpiarMedico();
						}
				 if (id=="eliminarMedico"){
						 document.getElementById('titulo').innerHTML="Eliminar Medico";
						 document.getElementById("btnGuardar").value="Eliminar";
						 document.getElementById("btnBuscarSVT").hidden=true;
						 document.getElementById("btnBuscar").hidden=false;
						 document.getElementById('operacionMedico').value="Eliminar";
						 document.getElementById("btnBuscar").hidden=false;
						 limpiarMedico();
						 }
					
		}			 					

	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function validarVacioMedico(formulario){
		
		
		if(formulario.codigo_medico.value== "" || formulario.nombre_medico.value== "" || formulario.status_medico.value== "" || formulario.unidad_medico.value== ""){
			 alert('Debes completar todos los campos') ; 
			 return false ; 
			}
		else {
			document.getElementById("tipo_id").disabled=false;
			return true;
			}
		
		}	
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function limpiarMedico(){
		
		document.getElementById("codigo_medico").value="";
		document.getElementById("codigo_medico").readOnly=false;
		document.getElementById("nombre_medico").value="";
		document.getElementById("status_medico").options[0].selected=true;
		document.getElementById("unidad_medico").options[0].selected=true;
		document.getElementById("tipo_id").options[0].selected=true;
		document.getElementById("tipo_id").disabled=false;
	
		if(document.getElementById("operacionMedico").value != "Registrar"){
			document.getElementById("codigo_medico").readOnly=true;
			document.getElementById("nombre_medico").readOnly=true;
			document.getElementById("status_medico").disabled=true;
			document.getElementById("unidad_medico").disabled=true;
			document.getElementById("tipo_id").disabled=true;
			}
					
		}
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function cargarMedico(id){
		
			medicos= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarMedico.php?",
			data: 'cargarMedico=cargar',
			success: function(response){
				
				medicos=eval("(" + response + ")");
				
				
					if(medicos.length > 0){
			
						for(var i=0; medicos.length>i;++i){
							
							document.getElementById(id).options[i]=new Option(medicos[i].nombre, medicos[i].id);
							}
						
						}
					else {
						document.getElementById(id).options[0]=new Option("No hay Medicos", "");
						
						}
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: false,
			});
			
				
		}	
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
		
		function cargarMedicos(){
		
			medico= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarMedico.php?",
			data: 'cargarTablaMedico=cargar',
			success: function(response){
				
				medico=eval("(" + response + ")");
				
				if(medico.length > 0){
					
					var tabla   = document.getElementById("tabla_medicos");
					var tblBody = document.createElement("tbody");
					
					var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("th");
							  var textoCelda1 = " ";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
							  
							   var celda7 = document.createElement("th");
							  celda7.setAttribute("align","center");
							  var textoCelda7 = document.createTextNode("Tipo");
							  celda7.appendChild(textoCelda7);
							  hilera.appendChild(celda7);
						
							  var celda2 = document.createElement("th");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode("Codigo");
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("th");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode("Nombre");
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							  var celda4 = document.createElement("th");
							  celda4.setAttribute("align","center");
							  var textoCelda4 = document.createTextNode("Cod. Unidad");
							  celda4.appendChild(textoCelda4);
							  hilera.appendChild(celda4);
							  
							  var celda5 = document.createElement("th");
							  celda5.setAttribute("align","center");
							  var textoCelda5 = document.createTextNode("Status");
							  celda5.appendChild(textoCelda5);
							  hilera.appendChild(celda5);
							  
							
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
		
					for(var i=0; i<medico.length; ++i){
			  			// Crea las hileras de la tabla
						var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("td");
							  celda1.setAttribute("align","center");
							  var textoCelda1 = "<input type='radio' name='seleccion_medicos' value=''>";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
							  
							  var celda7 = document.createElement("td");
							  celda7.setAttribute("align","center");
							  var textoCelda7 = document.createTextNode(medico[i].tipo);
							  celda7.appendChild(textoCelda7);
							  hilera.appendChild(celda7);
						
							  var celda2 = document.createElement("td");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode(medico[i].id);
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("td");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode(medico[i].nombre);
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							  var celda4 = document.createElement("td");
							  celda4.setAttribute("align","center");
							  var textoCelda4 = document.createTextNode(medico[i].unidad);
							  celda4.appendChild(textoCelda4);
							  hilera.appendChild(celda4);
							  
							  var celda5 = document.createElement("td");
							  celda5.setAttribute("align","center");
							  var textoCelda5 = document.createTextNode(medico[i].status);
							  celda5.appendChild(textoCelda5);
							  hilera.appendChild(celda5);
							  
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
						}
					
					
					// posiciona el <tbody> debajo del elemento <table>
					  tabla.appendChild(tblBody);
					  
					   $('tr').click(
							function() {
								$('input[type=radio]',this).attr('checked','checked');
							})
							}
					
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: true,
			});
				
				
		}
		
		
		
		/*************************************************************************/
		/*                  FIN FUNCIONES DE JS PARA MEDICO                      */
		/*************************************************************************/


	
	
	
		/*************************************************************************/
		/*              INICIO FUNCIONES DE JS PARA SECRETARIA                   */
		/*************************************************************************/


		

	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	 
	function buscarSecretaria(){
		
		var tipo_id;
		var codigo_secretaria;
		var rows = document.getElementById("tabla_secretarias").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
		for (i = 0; i < rows.length; i++) {
				var oCells = document.getElementById("tabla_secretarias").rows.item(i).cells;
				if (oCells[0].firstChild.checked) {
					tipo_id=rows[i].cells[1].innerHTML;
					codigo_secretaria=rows[i].cells[2].innerHTML;
				}
			}
		secretaria = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarSecretaria.php?",
		data: 'buscar=buscar&tipo_id_secre='+tipo_id+'&id_secre='+codigo_secretaria,
		success: function(response){
			
			secretaria=eval("(" + response + ")");
			
			if(secretaria.datos.length>0){
					
					window.location.href="#close";
					limpiarSecretaria();
					for(k=0 ; k<document.getElementById("tipo_id_secre").length ; k++){
						var v = document.getElementById("tipo_id_secre").options[k].value;
						if(v==secretaria.datos[0]){
							document.getElementById("tipo_id_secre").options[k].selected=true;
							}
						};
					
					document.getElementById("codigo_secretaria").value=secretaria.datos[1];
					document.getElementById("nombre_secretaria").value=secretaria.datos[2];
					document.getElementById("apellido_secretaria").value=secretaria.datos[3];
					
					for(k=0 ; k<document.getElementById("status_secretaria").length ; k++){
						var v = document.getElementById("status_secretaria").options[k].value;
						if(v==secretaria.datos[4]){
							document.getElementById("status_secretaria").options[k].selected=true;
							}
						};
							
					for(i = document.getElementById("medico_secretaria1").length - 1; i>=0; i--){
						var v = document.getElementById("medico_secretaria1").options[i].value;
							
							for(j=0 ; j<secretaria.medicos.length ; j++){
								if(v==secretaria.medicos[j].id){
									agregarOption(document.getElementById("medico_secretaria2"), document.getElementById("medico_secretaria1").options[i].text, document.getElementById("medico_secretaria1").options[i].value);
									document.getElementById("medico_secretaria1").remove(i);
									}
							}
							
						};
					
					if (document.getElementById("operacionSecretaria").value=="Actualizar"){
						document.getElementById("nombre_secretaria").disabled=false;
						document.getElementById("apellido_secretaria").disabled=false;
						document.getElementById("status_secretaria").disabled=false;
						document.getElementById("medico_secretaria1").disabled=false;
						document.getElementById("medico_secretaria2").disabled=false;
						
					}
					
					
			}
			else {
				
					alert("Seleccione una secretaria para continuar");
					
				}
			
			},
			error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
				
			},
			
			async: true,
		});
	
	}
	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function mostrarFormularioSecretaria(id){

				if (id=="registrarSecretaria"){
					document.getElementById('titulo').innerHTML="Registrar Secretaria";
					document.getElementById("btnGuardar").value="Guardar";
					document.getElementById("btnBuscar").hidden=true;
					document.getElementById('operacionSecretaria').value="Registrar";
					document.getElementById("codigo_secretaria").readOnly=false;
					document.getElementById("status_secretaria").disabled=false;
					document.getElementById("nombre_secretaria").disabled=false;
					document.getElementById("apellido_secretaria").disabled=false;
					document.getElementById("medico_secretaria1").disabled=false;
					document.getElementById("medico_secretaria2").disabled=false;
					limpiarSecretaria();
					}
				if (id=="modificarSecretaria"){
						document.getElementById('titulo').innerHTML="Actualizar Secretaria";
						document.getElementById("btnGuardar").value="Guardar";
						document.getElementById('operacionSecretaria').value="Actualizar";
						document.getElementById("btnBuscar").hidden=false;
						limpiarSecretaria();
						}
				 if (id=="eliminarSecretaria"){
						 document.getElementById('titulo').innerHTML="Eliminar Secretaria";
						 document.getElementById("btnGuardar").value="Eliminar";
						 document.getElementById('operacionSecretaria').value="Eliminar";
						 document.getElementById("btnBuscar").hidden=false;
						 limpiarSecretaria();
						 }
					
		}			 					

	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function validarVacioSecretaria(formulario){
		
		if(formulario.codigo_secretaria.value== "" || formulario.nombre_secretaria.value== "" || formulario.status_secretaria.value== "" || formulario.apellido_secretaria.value== ""
		){
			 alert('Debes completar todos los campos') ; 
			 return false ; 
			}
		else {
				document.getElementById("tipo_id_secre").disabled=false;
			
				for(k=0 ; k<document.getElementById("medico_secretaria2").length ; k++){
								document.getElementById("medico_secretaria2").options[k].selected=true;
					}
			
			return true;
			}
		
		}	
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function limpiarSecretaria(){
		
		document.getElementById("codigo_secretaria").value="";
		document.getElementById("codigo_secretaria").readOnly=false;
		document.getElementById("nombre_secretaria").value="";
		document.getElementById("apellido_secretaria").value="";
		document.getElementById("status_secretaria").options[0].selected=true;
		document.getElementById("medico_secretaria2").options.length=0;
		document.getElementById("tipo_id_secre").options[0].selected=true;
		document.getElementById("tipo_id_secre").disabled=false;
		cargarMedico('medico_secretaria1');
	
		if(document.getElementById("operacionSecretaria").value != "Registrar"){
			document.getElementById("codigo_secretaria").readOnly=true;
			document.getElementById("nombre_secretaria").disabled=true;
			document.getElementById("apellido_secretaria").disabled=true;
			document.getElementById("status_secretaria").disabled=true;
			document.getElementById("medico_secretaria1").disabled=true;
			document.getElementById("medico_secretaria2").disabled=true;
			document.getElementById("tipo_id_secre").disabled=true;
			}
					
		}
		
		
	function agregarOption(theSel, theText, theValue){
		  var newOpt = new Option(theText, theValue);
		  var selLength = theSel.length;
		  theSel.options[selLength] = newOpt;
		}
		
		
	function quitarOption(theSel, theIndex){ 
		  var selLength = theSel.length;
		  if(selLength>0)
		  {
			theSel.options[theIndex] = null;
		  }
		}
		
	function moverSelect(theSelFrom, theSelTo){
		  
		  var selLength = theSelFrom.length;
		  var selectedText = new Array();
		  var selectedValues = new Array();
		  var selectedCount = 0;
		  
		  var i;
		  
		  // Find the selected Options in reverse order
		  // and delete them from the 'from' Select.
		  for(i=selLength-1; i>=0; i--)
		  {
			if(theSelFrom.options[i].selected)
			{
			  selectedText[selectedCount] = theSelFrom.options[i].text;
			  selectedValues[selectedCount] = theSelFrom.options[i].value;
			 
			  quitarOption(theSelFrom, i);
			  selectedCount++;
			}
		  }
		  
		  // Add the selected text/values in reverse order.
		  // This will add the Options to the 'to' Select
		  // in the same order as they were in the 'from' Select.
		  for(i=selectedCount-1; i>=0; i--)
		  {
			agregarOption(theSelTo, selectedText[i], selectedValues[i]);
		  }
		  
		}
		
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
		
		function cargarSecretarias(){
			secretaria= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarSecretaria.php?",
			data: 'cargarSecretaria=cargar',
			success: function(response){
				
				secretaria=eval("(" + response + ")");
				
				if(secretaria.length > 0){
					
					var tabla   = document.getElementById("tabla_secretarias");
					var tblBody = document.createElement("tbody");
					
					var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("th");
							  var textoCelda1 = " ";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
							  
							  var celda = document.createElement("th");
							  celda.setAttribute("align","center");
							  var textoCelda = document.createTextNode("Tipo");
							  celda.appendChild(textoCelda);
							  hilera.appendChild(celda);
						
							  var celda2 = document.createElement("th");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode("Cedula");
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("th");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode("Nombre");
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							  var celda4 = document.createElement("th");
							  celda4.setAttribute("align","center");
							  var textoCelda4 = document.createTextNode("Apellido");
							  celda4.appendChild(textoCelda4);
							  hilera.appendChild(celda4);
							  
							  var celda5 = document.createElement("th");
							  celda5.setAttribute("align","center");
							  var textoCelda5 = document.createTextNode("Status");
							  celda5.appendChild(textoCelda5);
							  hilera.appendChild(celda5);
							  
							
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
		
					for(var i=0; i<secretaria.length; ++i){
			  			// Crea las hileras de la tabla
						var hilera = document.createElement("tr");
						
							  var celda1 = document.createElement("td");
							  celda1.setAttribute("align","center");
							  var textoCelda1 = "<input type='radio' name='seleccion_secretarias' value=''>";
							  celda1.innerHTML = textoCelda1;
							  hilera.appendChild(celda1);
							  
							  var celda = document.createElement("td");
							  celda.setAttribute("align","center");
							  var textoCelda = document.createTextNode(secretaria[i].tipo);
							  celda.appendChild(textoCelda);
							  hilera.appendChild(celda);
						
							  var celda2 = document.createElement("td");
							  celda2.setAttribute("align","center");
							  var textoCelda2 = document.createTextNode(secretaria[i].id);
							  celda2.appendChild(textoCelda2);
							  hilera.appendChild(celda2);
						
							  
							  var celda3 = document.createElement("td");
							  celda3.setAttribute("align","center");
							  var textoCelda3 = document.createTextNode(secretaria[i].nombre);
							  celda3.appendChild(textoCelda3);
							  hilera.appendChild(celda3);
							  
							  var celda4 = document.createElement("td");
							  celda4.setAttribute("align","center");
							  var textoCelda4 = document.createTextNode(secretaria[i].apellido);
							  celda4.appendChild(textoCelda4);
							  hilera.appendChild(celda4);
							  
							  var celda5 = document.createElement("td");
							  celda5.setAttribute("align","center");
							  var textoCelda5 = document.createTextNode(secretaria[i].status);
							  celda5.appendChild(textoCelda5);
							  hilera.appendChild(celda5);
							  
							  
							// agrega la hilera al final de la tabla (al final del elemento tblbody)
							  tblBody.appendChild(hilera);
						}
					
					
					// posiciona el <tbody> debajo del elemento <table>
					  tabla.appendChild(tblBody);
					  
					   $('tr').click(
							function() {
								$('input[type=radio]',this).attr('checked','checked');
							})
							}
					
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: true,
			});
				
				
		}

	
		/*************************************************************************/
		/*              FIN FUNCIONES DE JS PARA SECRETARIA                      */
		/*************************************************************************/





		/*************************************************************************/
		/*                   INICIO FUNCIONES DE JS PARA COMPUESTO                */
		/*************************************************************************/
		
		
		/* Función para obtener los parámetros pasados por el url.
		 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
		 * aplicación de honorarios médicos.
		 */
		function buscarCompuesto(){
			
			var codigo_compuesto;
			var rows = document.getElementById("tabla_compuestos").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
			for (i = 0; i < rows.length; i++) {
					var oCells = document.getElementById("tabla_compuestos").rows.item(i).cells;
					if (oCells[0].firstChild.checked) {
						codigo_compuesto=rows[i].cells[1].innerHTML;
					}
				}
			compuesto = new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarProcedimientoCompuesto.php?",
			data: 'buscar='+codigo_compuesto,
			success: function(response){
				
				compuesto=eval("(" + response + ")");
				
				if(compuesto.datos.length>0){
						
						limpiarCompuesto();
						window.location.href="#close";
						
						document.getElementById("codigo_compuesto").value=compuesto.datos[0];
						document.getElementById("nombre_compuesto").value=compuesto.datos[1];
						document.getElementById("tiempo_limpieza").value= compuesto.datos[2];
						
						for(i=0; i<document.getElementById("procedimiento_compuesto1").length; i++){
							var v = document.getElementById("procedimiento_compuesto1").options[i].value;
									for(j=0 ; j<compuesto.procedimientos.length ; j++){
										if(v==compuesto.procedimientos[j].id){
											document.getElementById("procedimiento_compuesto2").options[compuesto.procedimientos[j].orden]=new Option(document.getElementById("procedimiento_compuesto1").options[i].text, document.getElementById("procedimiento_compuesto1").options[i].value);
											}
									}
						};
						
						for(i = document.getElementById("procedimiento_compuesto1").length - 1; i>=0; i--){
							var v = document.getElementById("procedimiento_compuesto1").options[i].value;
									for(j=0 ; j<compuesto.procedimientos.length ; j++){
										if(v==compuesto.procedimientos[j].id){
											document.getElementById("procedimiento_compuesto1").remove(i);
											}
									}
						};
						
						
						if (document.getElementById("operacionCompuesto").value=="Actualizar"){
							document.getElementById("nombre_compuesto").disabled=false;
							document.getElementById("procedimiento_compuesto1").disabled=false;
							document.getElementById("procedimiento_compuesto2").disabled=false;
							document.getElementById("tiempo_limpieza").disabled=false;
						}
				
				}
				else {
					
						alert("Debe seleccionar un compuesto para continuar");
						
					}
				
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: false,
			});
		
		}
		
		/* Función para obtener los parámetros pasados por el url.
		 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
		 * aplicación de honorarios médicos.
		 */
		function mostrarFormularioCompuesto(id){
	
					if (id=="registrarCompuesto"){
						document.getElementById('titulo').innerHTML="Registrar Procedimiento Compuesto";
						document.getElementById("btnGuardar").value="Guardar";
						document.getElementById('operacionCompuesto').value="Registrar";
						document.getElementById("btnBuscar").hidden=true;
						document.getElementById("nombre_compuesto").disabled=false;
						document.getElementById("tiempo_limpieza").disabled=false;
						document.getElementById("procedimiento_compuesto1").disabled=false;
						document.getElementById("procedimiento_compuesto1").disabled=false;
						limpiarCompuesto();
						}
					if (id=="modificarCompuesto"){
							document.getElementById('titulo').innerHTML="Actualizar Procedimiento Compuesto";
							document.getElementById("btnGuardar").value="Guardar";
							document.getElementById('operacionCompuesto').value="Actualizar";
							document.getElementById("btnBuscar").hidden=false;
							limpiarCompuesto();
							}
					 if (id=="eliminarCompuesto"){
							 document.getElementById('titulo').innerHTML="Eliminar Procedimiento Compuesto";
							 document.getElementById("btnGuardar").value="Eliminar";
							 document.getElementById('operacionCompuesto').value="Eliminar";
							 document.getElementById("btnBuscar").hidden=false;
							 limpiarCompuesto();
							 }
						
			}			 					
	
	
			
		
		/* Función para obtener los parámetros pasados por el url.
		 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
		 * aplicación de honorarios médicos.
		 */
		function validarVacioCompuesto(formulario){
			
			
			if(formulario.codigo_compuesto.value== "" || formulario.nombre_compuesto.value== "" || formulario.tiempo_limpieza.value== "" ){
				 alert('Debes completar todos los campos') ; 
				 return false ; 
				}
			else {
				
				for(k=0 ; k<document.getElementById("procedimiento_compuesto2").length ; k++){
								document.getElementById("procedimiento_compuesto2").options[k].selected=true;
					}
				return true;
				}
			
			}	
			
			/* Función para obtener los parámetros pasados por el url.
		 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
		 * aplicación de honorarios médicos.
		 */
		function limpiarCompuesto(){
			
			document.getElementById("codigo_compuesto").value="";
			document.getElementById("nombre_compuesto").value="";
			document.getElementById("tiempo_limpieza").value="";
			document.getElementById("procedimiento_compuesto2").options.length=0;
			cargarProcedimiento('procedimiento_compuesto1');
			document.getElementById("codigo_compuesto").readOnly=false;
			
			if(document.getElementById("operacionCompuesto").value != "Registrar"){
				document.getElementById("tiempo_limpieza").disabled=true;
				document.getElementById("codigo_compuesto").readOnly=true;
				document.getElementById("nombre_compuesto").disabled=true;
				document.getElementById("procedimiento_compuesto1").disabled=true;
				document.getElementById("procedimiento_compuesto2").disabled=true;
				}
						
			}
			
			
			/* Función para obtener los parámetros pasados por el url.
		 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
		 * aplicación de honorarios médicos.
		 */
		function cargarCompuesto(id){
			
				compuesto= new Array();
				$.ajax({
				type: "GET",
				url: "../consultas/enviarProcedimientoCompuesto.php?",
				data: 'cargarCompuesto=cargar',
				success: function(response){
					
					compuesto=eval("(" + response + ")");
					
					if(compuesto.length > 0){
			
						for(var i=0; compuesto.length>i;++i){
							
							document.getElementById(id).options[i+1]=new Option(compuesto[i].nombre, compuesto[i].id);
							}
						
						}
					},
					error:function (xhr, ajaxOptions, thrownError){
					alert(xhr.status);
					alert(thrownError);
						
					},
					
					async: true,
				});
					
					
			}	
			
			
			/* Función para obtener los parámetros pasados por el url.
		 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
		 * aplicación de honorarios médicos.
		 */
			
			function cargarCompuestos(){
			
				compuesto= new Array();
				$.ajax({
				type: "GET",
				url: "../consultas/enviarProcedimientoCompuesto.php?",
				data: 'cargarCompuesto=cargar',
				success: function(response){
					
					compuesto=eval("(" + response + ")");
					
					if(compuesto.length > 0){
						
						var tabla   = document.getElementById("tabla_compuestos");
						var tblBody = document.createElement("tbody");
						
						var hilera = document.createElement("tr");
							
								  var celda1 = document.createElement("th");
								  celda1.setAttribute("align","center");
								  var textoCelda1 = " ";
								  celda1.innerHTML = textoCelda1;
								  hilera.appendChild(celda1);
							
								  var celda2 = document.createElement("th");
								  celda2.setAttribute("align","center");
								  var textoCelda2 = document.createTextNode("Codigo");
								  celda2.appendChild(textoCelda2);
								  hilera.appendChild(celda2);
							
								  
								  var celda3 = document.createElement("th");
								  celda3.setAttribute("align","center");
								  var textoCelda3 = document.createTextNode("Nombre");
								  celda3.appendChild(textoCelda3);
								  hilera.appendChild(celda3);
								  
								// agrega la hilera al final de la tabla (al final del elemento tblbody)
								  tblBody.appendChild(hilera);
			
						for(var i=0; i<compuesto.length; ++i){
							// Crea las hileras de la tabla
							var hilera = document.createElement("tr");
							
								  var celda1 = document.createElement("td");
								  celda1.setAttribute("align","center");
								  var textoCelda1 = "<input type='radio' name='seleccion_compuesto' value=''>";
								  celda1.innerHTML = textoCelda1;
								  hilera.appendChild(celda1);
							
								  var celda2 = document.createElement("td");
								  celda2.setAttribute("align","center");
								  var textoCelda2 = document.createTextNode(compuesto[i].id);
								  celda2.appendChild(textoCelda2);
								  hilera.appendChild(celda2);
							
								  
								  var celda3 = document.createElement("td");
								  celda3.setAttribute("align","center");
								  var textoCelda3 = document.createTextNode(compuesto[i].nombre);
								  celda3.appendChild(textoCelda3);
								  hilera.appendChild(celda3);
								  
								// agrega la hilera al final de la tabla (al final del elemento tblbody)
								  tblBody.appendChild(hilera);
							}
						
						
						// posiciona el <tbody> debajo del elemento <table>
						  tabla.appendChild(tblBody);
						  
						   $('tr').click(
								function() {
									$('input[type=radio]',this).attr('checked','checked');
								})
								}
						
					},
					error:function (xhr, ajaxOptions, thrownError){
					alert(xhr.status);
					alert(thrownError);
						
					},
					
					async: true,
				});
					
					
			}		 								 					
	
		/*************************************************************************/
		/*                   FIN FUNCIONES DE JS PARA COMPUESTO                  */
		/*************************************************************************/
		
		
	
	
	
		