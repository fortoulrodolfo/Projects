	/*************************************************************************/
	/*                               FUNCIONES JS                            */
	/*************************************************************************/
	
	
	
	
	/*************************************************************************/
	/*                INICIO FUNCIONES DE JS PARA LIBRO DE CITAS             */
	/*************************************************************************/
	
	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function cargarCitas(){
		
			 var tableRef = document.getElementById('tabla_citas');
			 while ( tableRef.rows.length > 0 )
			 {
			  tableRef.deleteRow(0);
			 }
			
			var fecha_inicio = document.getElementById("fecha_inicio").value;
			var fecha_final = document.getElementById("fecha_final").value;
			var status = document.getElementById("status_select").value;
			var sala = document.getElementById("sala_cita_reporte").value;
			var procedimiento = document.getElementById("proced_select_reporte").value;
			var equipo = document.getElementById("equipo_select").value;
			var tipo_id_medico = document.getElementById("tipo_id_medico").value;
			var medico = document.getElementById("id_medico").value;
			var forma = document.getElementById("forma_pago").value;
			var aneste = document.getElementById("anestesiologo").value;
			var tipo_id = document.getElementById("tipo_id").value;
			var paciente = document.getElementById("id_paciente").value;
			var tipo_paciente = document.getElementById("tipo_paciente_select").value;
			
			cita= new Array();
			$.ajax({
				type: "GET",
				url: "../consultas/enviarCita.php?",
				data: 'cargarLibro=cargar&fecha_inicio='+fecha_inicio+'&fecha_final='+fecha_final+'&status_cita='+status+'&sala='+sala+'&proced='+procedimiento
				+'&equipo='+equipo+'&forma='+forma+'&aneste='+aneste+'&tipo_id_medico='+tipo_id_medico+'&medico='+medico+'&tipo_id='+tipo_id+'&paciente='+paciente+'&tipo_paciente='+tipo_paciente,
				success: function(response){
					
					cita=eval("(" + response + ")");
					
					if(cita.length > 0){
						
						var titulos = Object.keys(cita[0]);
						var tabla   = document.getElementById("tabla_citas");
						var tblThead = document.createElement("thead");
						var tblBody = document.createElement("tbody");
						
						var hilera = document.createElement("tr");
						
							for(var j=0; j<titulos.length; ++j){
								
								  var celda1 = document.createElement("th");
								  celda1.setAttribute("align","center");
								  celda1.style.backgroundColor="#ACC1E0";
								  var textoCelda1 = titulos[j];
								  celda1.innerHTML = textoCelda1;
								  hilera.appendChild(celda1);
								  
							}
						
						tblThead.appendChild(hilera);
			
						for(var i=0; i<cita.length; ++i){
							// Crea las hileras de la tabla
							var hilera = document.createElement("tr");
							
								for(var k=0; k<titulos.length; ++k){
										  
										  var celda2 = document.createElement("td");
										  celda2.setAttribute("align","center");
										  var textoCelda2 = document.createTextNode(cita[i][titulos[k]]);
										  celda2.appendChild(textoCelda2);
										  hilera.appendChild(celda2);
								}
								  
								// agrega la hilera al final de la tabla (al final del elemento tblbody)
								  tblBody.appendChild(hilera);
							}
						
						
						// posiciona el <tbody> debajo del elemento <table>
						  tabla.appendChild(tblThead);
						  tabla.appendChild(tblBody);
							}
				
					else {
							alert("No hay registros de Citas disponibles para esa busqueda.");
							limpiarLibro();
							cargarCitas();
						}
						
					},
					error:function (xhr, ajaxOptions, thrownError){
					alert(xhr.status);
					alert(thrownError);
						
					},
					
					async: false,
				});
	}
	
	
	
	function cargarCitasBitacora(){
		
			 var tableRef = document.getElementById('tabla_citas');
			 while ( tableRef.rows.length > 0 )
			 {
			  tableRef.deleteRow(0);
			 }
			var fecha_inicio = document.getElementById("fecha_inicio").value;
			var fecha_final = document.getElementById("fecha_final").value;
			var status = document.getElementById("status_select").value;
			var sala = document.getElementById("sala_cita_reporte").value;
			var procedimiento = document.getElementById("proced_select_reporte").value;
			var tipo_id_medico = document.getElementById("tipo_id_medico").value;
			var medico = document.getElementById("id_medico").value;
			var forma = document.getElementById("forma_pago").value;
			var aneste = document.getElementById("anestesiologo").value;
			var tipo_id = document.getElementById("tipo_id").value;
			var paciente = document.getElementById("id_paciente").value;
			var tipo_paciente = document.getElementById("tipo_paciente_select").value;
			
			cita= new Array();
			$.ajax({
				type: "GET",
				url: "../consultas/enviarCita.php?",
				data: 'cargarLibroBitacora=cargar&fecha_inicio='+fecha_inicio+'&fecha_final='+fecha_final+'&status_cita='+status+'&sala='+sala+'&proced='+procedimiento
				+'&forma='+forma+'&aneste='+aneste+'&tipo_id_medico='+tipo_id_medico+'&medico='+medico+'&tipo_id='+tipo_id+'&paciente='+paciente+'&tipo_paciente='+tipo_paciente,
				success: function(response){
					
					cita=eval("(" + response + ")");
					
					if(cita.length > 0){
						
						var titulos = Object.keys(cita[0]);
						var tabla   = document.getElementById("tabla_citas");
						var tblThead = document.createElement("thead");
						var tblBody = document.createElement("tbody");
						
						var hilera = document.createElement("tr");
						
							for(var j=0; j<titulos.length; ++j){
								
								  var celda1 = document.createElement("th");
								  celda1.setAttribute("align","center");
								  celda1.style.backgroundColor="#ACC1E0";
								  var textoCelda1 = titulos[j];
								  celda1.innerHTML = textoCelda1;
								  hilera.appendChild(celda1);
								  
							}
						
						tblThead.appendChild(hilera);
			
						for(var i=0; i<cita.length; ++i){
							// Crea las hileras de la tabla
							var hilera = document.createElement("tr");
							
								for(var k=0; k<titulos.length; ++k){
										  
										  var celda2 = document.createElement("td");
										  celda2.setAttribute("align","center");
										  var textoCelda2 = document.createTextNode(cita[i][titulos[k]]);
										  celda2.appendChild(textoCelda2);
										  hilera.appendChild(celda2);
								}
								  
								// agrega la hilera al final de la tabla (al final del elemento tblbody)
								  tblBody.appendChild(hilera);
							}
						
						
						// posiciona el <tbody> debajo del elemento <table>
						  tabla.appendChild(tblThead);
						  tabla.appendChild(tblBody);
							}
				
					else {
							alert("No hay registros de Citas disponibles para esa busqueda.");
							limpiarLibroBitacora();
							cargarCitasBitacora();
						}
						
					},
					error:function (xhr, ajaxOptions, thrownError){
					alert(xhr.status);
					alert(thrownError);
						
					},
					
					async: false,
				});
	}
	
	
	
	function cargarLibroPDF(){
		
			var fecha_inicio = document.getElementById("fecha_inicio").value;
			var fecha_final = document.getElementById("fecha_final").value;
			var status = document.getElementById("status_select").value;
			var sala = document.getElementById("sala_cita_reporte").value;
			var procedimiento = document.getElementById("proced_select_reporte").value;
			var equipo = document.getElementById("equipo_select").value;
			var medico = document.getElementById("id_medico").value;
			var forma = document.getElementById("forma_pago").value;
			var aneste = document.getElementById("anestesiologo").value;
			var tipo_id = document.getElementById("tipo_id").value;
			var paciente = document.getElementById("id_paciente").value;
			var tipo_paciente = document.getElementById("tipo_paciente_select").value;
			
			
			url= '../consultaReporte/libroCitasPDF.php?cargarLibro=reporteLibro&fecha_inicio='+fecha_inicio+'&fecha_final='+fecha_final+'&status_cita='+status+'&sala='+sala+'&proced='+procedimiento
				+'&equipo='+equipo+'&forma='+forma+'&aneste='+aneste+'&medico='+medico+'&tipo_id='+tipo_id+'&paciente='+paciente+'&tipo_paciente='+tipo_paciente;
				
			window.location.href=url;
					
					}
					
		function cargarLibroExcel(){
		
			var fecha_inicio = document.getElementById("fecha_inicio").value;
			var fecha_final = document.getElementById("fecha_final").value;
			var status = document.getElementById("status_select").value;
			var sala = document.getElementById("sala_cita_reporte").value;
			var procedimiento = document.getElementById("proced_select_reporte").value;
			var equipo = document.getElementById("equipo_select").value;
			var medico = document.getElementById("id_medico").value;
			var forma = document.getElementById("forma_pago").value;
			var aneste = document.getElementById("anestesiologo").value;
			var tipo_id = document.getElementById("tipo_id").value;
			var paciente = document.getElementById("id_paciente").value;
			var tipo_paciente = document.getElementById("tipo_paciente_select").value;
			
			
			url= '../consultaReporte/libroCitasExcel.php?cargarLibro=reporteLibro&fecha_inicio='+fecha_inicio+'&fecha_final='+fecha_final+'&status_cita='+status+'&sala='+sala+'&proced='+procedimiento
				+'&equipo='+equipo+'&forma='+forma+'&aneste='+aneste+'&medico='+medico+'&tipo_id='+tipo_id+'&paciente='+paciente+'&tipo_paciente='+tipo_paciente;
				
			window.location.href=url;
					
					}
					
		
		function cargarLibroPDFBitacora(){
		
			var fecha_inicio = document.getElementById("fecha_inicio").value;
			var fecha_final = document.getElementById("fecha_final").value;
			var status = document.getElementById("status_select").value;
			var sala = document.getElementById("sala_cita_reporte").value;
			var procedimiento = document.getElementById("proced_select_reporte").value;
			var medico = document.getElementById("id_medico").value;
			var forma = document.getElementById("forma_pago").value;
			var aneste = document.getElementById("anestesiologo").value;
			var tipo_id = document.getElementById("tipo_id").value;
			var paciente = document.getElementById("id_paciente").value;
			var tipo_paciente = document.getElementById("tipo_paciente_select").value;
			
			
			url= '../consultaReporte/libroCitasPDFBitacora.php?cargarLibro=reporteLibro&fecha_inicio='+fecha_inicio+'&fecha_final='+fecha_final+'&status_cita='+status+'&sala='+sala+'&proced='+procedimiento
				+'&forma='+forma+'&aneste='+aneste+'&medico='+medico+'&tipo_id='+tipo_id+'&paciente='+paciente+'&tipo_paciente='+tipo_paciente;
				
			window.location.href=url;
					
					}
					
		function cargarLibroExcelBitacora(){
		
			var fecha_inicio = document.getElementById("fecha_inicio").value;
			var fecha_final = document.getElementById("fecha_final").value;
			var status = document.getElementById("status_select").value;
			var sala = document.getElementById("sala_cita_reporte").value;
			var procedimiento = document.getElementById("proced_select_reporte").value;
			var medico = document.getElementById("id_medico").value;
			var forma = document.getElementById("forma_pago").value;
			var aneste = document.getElementById("anestesiologo").value;
			var tipo_id = document.getElementById("tipo_id").value;
			var paciente = document.getElementById("id_paciente").value;
			var tipo_paciente = document.getElementById("tipo_paciente_select").value;
			
			
			url= '../consultaReporte/libroCitasExcelBitacora.php?cargarLibro=reporteLibro&fecha_inicio='+fecha_inicio+'&fecha_final='+fecha_final+'&status_cita='+status+'&sala='+sala+'&proced='+procedimiento
				+'&forma='+forma+'&aneste='+aneste+'&medico='+medico+'&tipo_id='+tipo_id+'&paciente='+paciente+'&tipo_paciente='+tipo_paciente;
				
			window.location.href=url;
					
					}
				
		
		
		function cargarSalas(id){
		
			sala = new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarSala.php?",
			data: 'salas=cargar',
			success: function(response){
				
				sala=eval("(" + response + ")");
				
					
					if(sala.length > 0){
						
						document.getElementById(id).options.length=0;
						document.getElementById(id).options[0]=new Option(" ", "");
						document.getElementById(id).options[0].selected=true;
			
						for(var i=0; sala.length>i;++i){
							
							document.getElementById(id).options[i+1]=new Option(sala[i].name, sala[i].id);
							}
						
						}
				
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: false
			});
		
		}		
	
	function cargarProcedimientos(id){
		
		procedimiento = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarProcedimiento.php?",
		data: 'cargarProcedimientos=cargar',
		success: function(response){
			
			procedimiento=eval("(" + response + ")");
			
				if(procedimiento.length > 0){
					
					document.getElementById(id).options.length=0;
					document.getElementById(id).options[0]=new Option(" ",  "");
					document.getElementById(id).options[0].selected=true;
					
					for(var i=0; procedimiento.length>i;++i){
			  			
			  			document.getElementById(id).options[i+1]=new Option( procedimiento[i].nombre,  procedimiento[i].id);
						
						}
				cargarProcedimientosCompuestos(id);	
					
					}
				else{
					
							document.getElementById(id).options.length=0;
							document.getElementById(id).options[0]=new Option(" ",  " ");
							document.getElementById(id).options[0].selected=true;
							cargarProcedimientosCompuestos(id);
							
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
		
		function cargarEquipos(id){
		
			equipo= new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarEquipo.php?",
			data: 'cargarEquipos=equipos',
			success: function(response){
				
				equipo=eval("(" + response + ")");
				
				if(equipo.length > 0){
					
					
					document.getElementById(id).options.length=0;
					document.getElementById(id).options[0]=new Option(" ",  "");
					document.getElementById(id).options[0].selected=true;
					
					for(var i=0; equipo.length>i;++i){
			  			
			  			document.getElementById(id).options[i+1]=new Option( equipo[i].nombre, equipo[i].id);
						
						}
					
					
					}
					
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: false,
			});
				
				
		}			
		
		
		function limpiarLibro(){
				
			document.getElementById("fecha_inicio").value="";
			document.getElementById("fecha_final").value="";
			document.getElementById("status_select").options[0].selected=true;
			cargarSalas('sala_cita_reporte');
			cargarProcedimientos('proced_select_reporte');
			cargarEquipos('equipo_select');
			document.getElementById("id_medico").value="";
			document.getElementById("forma_pago").options[0].selected=true;
			document.getElementById("anestesiologo").options[0].selected=true;
			document.getElementById("tipo_id").options[0].selected=true;
			document.getElementById("id_paciente").value="";
			document.getElementById("tipo_paciente_select").options[0].selected=true;
			cargarCitas();
			
			}		 
			
		function limpiarLibroBitacora(){
				
			document.getElementById("fecha_inicio").value="";
			document.getElementById("fecha_final").value="";
			document.getElementById("status_select").options[0].selected=true;
			cargarSalas('sala_cita_reporte');
			cargarProcedimientos('proced_select_reporte');
			cargarEquipos('equipo_select');
			document.getElementById("id_medico").value="";
			document.getElementById("forma_pago").options[0].selected=true;
			document.getElementById("anestesiologo").options[0].selected=true;
			document.getElementById("tipo_id").options[0].selected=true;
			document.getElementById("id_paciente").value="";
			document.getElementById("tipo_paciente_select").options[0].selected=true;
			cargarCitasBitacora();
			
			}							
		
				 					


	