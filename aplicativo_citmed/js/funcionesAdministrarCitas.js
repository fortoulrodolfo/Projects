
	var fechaCita;
	var horaInicio;
	var horaFin;

	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function cargarFechaCalendario(){
	
	
			$('#calendar').fullCalendar({
				 	theme: true,
                    editable: true,
                    firstDay: 1,
                    defaultView: 'month',
                    header: {
                        left: 'prev,next',
                        center: 'title',
                        right: 'month'
                       },
                    lang: 'es',
                    minTime: '06:00',
                    maxTime: '18:00',
                    allDaySlot: false,
                    month: 'ddd',
                    week: 'ddd dd/MM',
                    day: 'dddd M/d',
                    events: {
                        url: '',
                        error: function() {
                            
                        }
                    },
                      
					selectable: true,
					selectHelper: true,
					select: function(start, end, allDay) {
					
						var start =  moment(start).format('DD-MM-YYYY');
						window.location="registrarCita.php?fecha="+ start +"";	   
		
					},
					 });
					 
					 }
	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function cargarCalendarioCita(){
				
				var fecha = getURLParameter("fecha").split("-");
				var date = new Date(fecha[2],fecha[1]-1,fecha[0]);

				$('#calendar').fullCalendar({
					  theme: true,
					  header: {
								left: 'prev,next today',
								center: 'title',
								right: 'month,agendaWeek,resourceDay'
							  },
					  buttonText: {
								today: 'hoy',
								month: 'mes',
								week: 'semana',
								day: 'dia'
								},
					  monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio','Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
					  monthNameShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun','Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
					  dayNames: ['Domingo', 'Lunes', 'Martes', 'Miercoles','Jueves', 'Viernes', 'Sabado'],
					  dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
					  editable: false,
					  firstDay: 1,
					  defaultView: 'resourceDay',
					  lang: 'es',
					 // slotMinutes: 5,
                      minTime: '06:00',
                      maxTime: '18:00',
                      allDaySlot: false,
					  droppable: true,
					  resources: '../consultas/enviarSala.php?salas=cargar&activa=Activa',
					  events: '../consultas/enviarCita.php?citas=cargar',
					  selectable: true,
                      selectHelper: true,
					  
					  select: function(start, end, resources) {
						 
						  	if($('#calendar').fullCalendar('getView').name=="resourceDay"){
									
								if(document.getElementById("operacionCita").value=="Registrar"){
									
									mostrarFormularioCita('registrarCita');
								}
								
								if(document.getElementById("operacionCita").value=="Actualizar"){
									document.getElementById("proced_select").options.length=0;
									document.getElementById("proced_select").options[0]=new Option(" ",  " ");
									document.getElementById("proced_select").options[0].selected=true;
									document.getElementById("tipo_estudio_select").options[0].selected=true;
									document.getElementById("proced_select").disabled=true;
									var tableRef = document.getElementById('tabla_equipos');
									 while ( tableRef.rows.length > 0 )
									 {
									  tableRef.deleteRow(0);
									 }
								}
								
								var fecha = moment(start).format('DD-MM-YYYY');
								var hora_inicio=moment(start).format('HH:mm:ss');
								document.getElementById("fecha_inicio_cita").value= moment($('#calendar').fullCalendar('getDate')).format('DD-MM-YYYY');   
								document.getElementById("hora_inicio_cita").value=hora_inicio;
								cargarSalas('sala_cita','activa');
								
							}
							else{
								document.getElementById('formulario').hidden=true;
								}
						  	
						},
						
						
						 eventClick: function(calEvent, jsEvent, view) {
							buscarCita(calEvent.id);
							//alert('Paciente: ' + calEvent.id);
							//alert('Sala: ' + calEvent.resources);
							// change the border color just for fun
							//$(this).css('border-color', 'red');
					
						},
						
						dayClick: function(date, view) {
							if($('#calendar').fullCalendar('getView').name=="agendaWeek" || $('#calendar').fullCalendar('getView').name=="month"){
									
								$('#calendar').fullCalendar('changeView', 'resourceDay');
								$('#calendar').fullCalendar('gotoDate', date);
							}
						},
						
					  
					/* select: function(start, end, allDay, resources) {
						//var title = prompt('event title:');
						alert('event moved to '+start+' to '+resources.id);
						if (title) {
							$('#calendar').fullCalendar('renderEvent',
								{
									title: title,
									start: start,
									end: end,
									allDay: allDay,
									resources: resources.id
								},
								true // make the event "stick"
							);
						}
						$('#calendar').fullCalendar('unselect');
					},
					resourceRender: function(resources, element, view) {
						// this is triggered when the resource is rendered, just like eventRender
					},
					eventDrop: function(event) { 
						alert('event moved to '+event.start+' to '+event.resources);
					},
					 eventAfterRender: function(event, element, view) {
						resources=event.resources;
						alert(resources);
					},*/
						
					});
			 
			  
			  $('#calendar').fullCalendar("gotoDate",date);
			 
         }
		 
		 
	function getURLParameter(name){
		
		return decodeURI(		
		(RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
		);
   		
		}	
		
	function mostrarFormularioCita(id){
		
			
				if (id=="registrarCita"){
					document.getElementById('formulario').hidden=false;
					document.getElementById('titulo').innerHTML="Registrar Cita";
					document.getElementById("btnGuardar").value="Guardar";
					document.getElementById('operacionCita').value="Registrar";
					document.getElementById("codigo_cita").value="";
					habilitar_campos(false);
					limpiarCita();
					document.getElementById("proced_select").disabled=true;
					
					}
				if (id=="registrarProcedimientoNP"){
						document.getElementById('formulario').hidden=false;
						document.getElementById('titulo').innerHTML="Registrar Procedimiento N/P";
						document.getElementById("btnGuardar").value="Guardar";
						document.getElementById('operacionCita').value="RegistrarNP";
						habilitar_campos(false);
						limpiarCita();
						document.getElementById("proced_select").disabled=true;
						document.getElementById("status_select").disabled=true;
						document.getElementById("status_select").options[6].selected=true;
						}
				 if (id=="actualizarCita"){
						 document.getElementById('formulario').hidden=false;
						 document.getElementById('titulo').innerHTML="Actualizar Cita";
						 document.getElementById("btnGuardar").value="Guardar";
						 document.getElementById('operacionCita').value="Actualizar";
						 habilitar_campos(true);
						 }
				 if(id=="eliminarCita"){
							document.getElementById('formulario').hidden=false;
							document.getElementById('titulo').innerHTML="Eliminar Cita";
							document.getElementById("btnGuardar").value="Eliminar";
							document.getElementById('operacionCita').value="Eliminar";
							habilitar_campos(true);
							var rows = document.getElementById("tabla_equipos").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
							for(var i=0; i<rows.length; ++i){
								document.getElementById('equipo_select'+(i+1)).disabled=true;
								}
						 }
					
		}		
		
	function cargarSalas(id,activa){
		
		sala = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarSala.php?",
		data: 'salas=cargar&activa='+activa,
		success: function(response){
			
			sala=eval("(" + response + ")");
			
				
				if(sala.length > 0){
					
					document.getElementById(id).options.length=0;
					document.getElementById(id).options[0]=new Option(" ", " ");
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
	
			

	function mostrar_hab(){
			 if(document.getElementById("tipo_paciente_select").value=="H")
				 {
					 document.getElementById('hab_paciente').style.display= 'block';
					 document.getElementById("hab_paciente").value="";
					 document.getElementById('hab_font').style.display= 'block';
				 }
			 else{
				  document.getElementById("hab_paciente").style.display= 'none';
				  document.getElementById("hab_paciente").value="";
				  document.getElementById('hab_font').style.display= 'none';
				 };
			 }
			 
	function mostrar_participantes(){
			 if(document.getElementById("status_select").value=="PATendido")
				 {
					 document.getElementById('participantes').style.display= 'block';
					 document.getElementById('participante1').style.display= 'block';
					 document.getElementById('participante2').style.display= 'block';
					 document.getElementById('participante1').value="";
					 document.getElementById('participante2').value="";
				 }
			 else{
				  document.getElementById('participantes').style.display= 'none';
				  document.getElementById('participante1').style.display= 'none';
				  document.getElementById('participante2').style.display= 'none';
				  document.getElementById('participante1').value="";
				  document.getElementById('participante2').value="";
				 };
			 }
			 
	function buscarCita(codigo_cita){
		
		cita = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarCita.php?",
		data: 'buscar='+codigo_cita,
		success: function(response){
			cita=eval("(" + response + ")");
			
			if(cita.datos[0]!=null){
			
				if(cita.datos[3]!='ProcedNP'){
				
					if(validarCita(cita.datos[24],cita.datos[9])==true){
						
						fecha_inicio= cita.datos[1].split(" ");
						fecha_final= cita.datos[2].split(" ");
						fecha_actual= moment().format('DD-MM-YYYY');
						if(fecha_inicio[0]>=fecha_actual){
						mostrarFormularioCita('actualizarCita');
						document.getElementById("codigo_cita").value=cita.datos[0];
						document.getElementById("fecha_inicio_cita").value=fecha_inicio[0];  
						document.getElementById("hora_inicio_cita").value=fecha_inicio[1]; 
						document.getElementById("hora_fin_cita").value=fecha_final[1];
						document.getElementById("fecha_status").innerHTML=cita.datos[4];
						
						
						for(k=0 ; k<document.getElementById("status_select").length ; k++){
							var v = document.getElementById("status_select").options[k].value;
							if(v==cita.datos[3]){
								document.getElementById("status_select").options[k].selected=true;
								}
							};
						
						for(k=0 ; k<document.getElementById("tipo_paciente_select").length ; k++){
							var v = document.getElementById("tipo_paciente_select").options[k].value;
							if(v==cita.datos[5]){
								document.getElementById("tipo_paciente_select").options[k].selected=true;
								}
							};
							
						mostrar_hab();
						if(cita.datos[6]!=null){
						document.getElementById("hab_paciente").value=cita[6];
						}
						
						for(k=0 ; k<document.getElementById("tipo_estudio_select").length ; k++){
							var v = document.getElementById("tipo_estudio_select").options[k].value;
							if(v==cita.datos[8]){
								document.getElementById("tipo_estudio_select").options[k].selected=true;
								}
							};
							
						cargarProcedimientos(cita.datos[8], 'proced_select');
						
						for(k=0 ; k<document.getElementById("proced_select").length ; k++){
							var v = document.getElementById("proced_select").options[k].value;
							if(v==cita.datos[7]){
								document.getElementById("proced_select").options[k].selected=true;
								}
							};
						
						document.getElementById("id_medico").value=cita.datos[9];
						document.getElementById("nombre_medico").innerHTML=cita.datos[10] + " " + cita.datos[11];
						
						for(k=0 ; k<document.getElementById("tipo_id_medico").length ; k++){
							var v = document.getElementById("tipo_id_medico").options[k].value;
							if(v==cita.datos[24]){
								document.getElementById("tipo_id_medico").options[k].selected=true;
								}
							};
						
						for(k=0 ; k<document.getElementById("tipo_id").length ; k++){
							var v = document.getElementById("tipo_id").options[k].value;
							if(v==cita.datos[23]){
								document.getElementById("tipo_id").options[k].selected=true;
								}
							};
							
						document.getElementById("id_paciente").value=cita.datos[12];
						document.getElementById("nombre_paciente").innerHTML=cita.datos[13] + " " + cita.datos[14];
						
						for(k=0 ; k<document.getElementById("sala_cita").length ; k++){
							var v = document.getElementById("sala_cita").options[k].value;
							if(v==cita.datos[15]){
								document.getElementById("sala_cita").options[k].selected=true;
								}
							};
						if(cita.datos[17]!=null){
						document.getElementById("observaciones").value=cita.datos[17];
						}
						
						for(k=0 ; k<document.getElementById("forma_pago").length ; k++){
							var v = document.getElementById("forma_pago").options[k].value;
							if(v==cita.datos[19]){
								document.getElementById("forma_pago").options[k].selected=true;
								}
							};
							
						if(cita.datos[20]!=null){
							document.getElementById("anestesiologo").checked=true;
							}
						
						mostrar_participantes();
						if(cita.datos[21]!=null){
							document.getElementById("participante1").value=cita.datos[21];
							}
							
						if(cita.datos[22]!=null){
							document.getElementById("participante2").value=cita.datos[22];
							}
								
						if (document.getElementById("operacionCita").value=="Actualizar"){
							habilitar_campos(false);
							document.getElementById("id_medico").disabled=true;
							document.getElementById("id_paciente").disabled=true;
							document.getElementById("tipo_id").disabled=true;
						}
						if (document.getElementById("operacionCita").value=="Eliminar"){
							habilitar_campos(true);
						}
						mostrarEquipos(cita.datos[7]);
						
						for(var k=0; k<cita.equipos.length; ++k){
							
							for(var i=0; i<document.getElementById("equipo_select"+(k+1)).options.length; ++i){
								var v = document.getElementById("equipo_select"+(k+1)).options[i].value;
								if(v==cita.equipos[k].id){
									document.getElementById("equipo_select"+(k+1)).options[i].selected=true;
									}
								}
							}
						}else{
						alert("No se pueden Modificar o Elminar citas en Fechas pasadas");
						}
					}
				}
			}
			else
			{
				alert("No posee permiso para modificar esa Cita");
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
		function limpiarCita(){
			
			if(document.getElementById("operacionCita").value == "Registrar"){
				document.getElementById("codigo_cita").value="";
				}
			var tableRef = document.getElementById('tabla_equipos');
			 while ( tableRef.rows.length > 0 )
			 {
			  tableRef.deleteRow(0);
			 }
			document.getElementById("sala_cita").options.length=0;
			cargarSalas('sala_cita','activa');
			document.getElementById("sala_cita").options[0].selected=true;
			document.getElementById("status_select").options[0].selected=true;
			document.getElementById("fecha_status").innerHTML="";
			document.getElementById("fecha_inicio_cita").value="";
			document.getElementById("hora_fin_cita").value="";
			document.getElementById("hora_inicio_cita").value="";
			document.getElementById("tipo_estudio_select").options[0].selected=true;
			document.getElementById("proced_select").options.length=0;
			document.getElementById("id_medico").value="";
			document.getElementById("nombre_medico").innerHTML="";
			document.getElementById("tipo_id_medico").options[0].selected=true;
			document.getElementById("tipo_id").options[0].selected=true;
			document.getElementById("id_paciente").value="";
			document.getElementById("nombre_paciente").innerHTML="";
			document.getElementById("tipo_paciente_select").options[0].selected=true;
			mostrar_hab();
			document.getElementById("hab_paciente").value="";
			document.getElementById("forma_pago").options[0].selected=true;
			document.getElementById("anestesiologo").checked=false;
			mostrar_participantes();
			document.getElementById("participante1").value="";
			document.getElementById("participante2").value="";
			document.getElementById("observaciones").value=""	
			habilitar_campos(false);
			}
			
		/* Función para obtener los parámetros pasados por el url.
		 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
		 * aplicación de honorarios médicos.
		 */
		function habilitar_campos(condicion){
			
			document.getElementById("sala_cita").disabled=condicion;
			document.getElementById("status_select").disabled=condicion;
			document.getElementById("tipo_estudio_select").disabled=condicion;
			document.getElementById("proced_select").disabled=condicion;
			document.getElementById("id_medico").disabled=condicion;
			document.getElementById("tipo_id").disabled=condicion;
			document.getElementById("id_paciente").disabled=condicion;
			document.getElementById("tipo_paciente_select").disabled=condicion;
			document.getElementById("hab_paciente").disabled=condicion;
			document.getElementById("forma_pago").disabled=condicion;
			document.getElementById("anestesiologo").disabled=condicion;
			document.getElementById("participante1").disabled=condicion;
			document.getElementById("participante2").disabled=condicion;
			document.getElementById("observaciones").disabled=condicion;
				
			}
			
			
		/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	 
	function validarVacioCita(formulario){
		
		var respuesta;
		
		if(formulario.sala_cita.value == " " || formulario.fecha_inicio_cita.value== "" || formulario.hora_inicio_cita.value== "" || formulario.hora_fin_cita.value== ""
		|| formulario.tipo_estudio_select.value== "" || formulario.proced_select.value== "" || formulario.id_medico.value== ""
		|| formulario.tipo_id.value== "" || formulario.id_paciente.value== "" || formulario.tipo_paciente_select.value== "" || formulario.forma_pago.value== ""
		 ){
		    alert('Debes completar todos los campos') ; 
			
			 return false ; 
			}
		else {
			document.getElementById('tipo_id').disabled = false; 
			document.getElementById('status_select').disabled = false;
			document.getElementById("id_medico").disabled=false;
			document.getElementById("id_paciente").disabled=false;
			arra = new Array();
			var rows = document.getElementById("tabla_equipos").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
			for(var i=0; i<rows.length; ++i){
				arra[i]=document.getElementById('equipo_select'+(i+1)).value;
				}
			document.getElementById("equipos_cita").value=arra;
			}
			
			for(var i=0; i<arra.length; ++i){
				if(arra[i]==" "){
					alert('Debes completar todos los campos') ; 
					return false;
					}
				}
			
			if(document.getElementById("operacionCita").value=="Registrar" || document.getElementById("operacionCita").value=="Actualizar"){
				salaDisponible();
				for(var i=0; i<rows.length; ++i){
					//equipoDisponible("equipo_select"+(i+1),arra[i]);
					}
				buscarMedico(formulario.tipo_id_medico.value, formulario.id_medico.value);
				buscarPaciente(formulario.id_paciente.value, formulario.tipo_id.value);
				
				if(formulario.sala_cita.value == " " || formulario.fecha_inicio_cita.value== "" || formulario.hora_inicio_cita.value== "" || formulario.hora_fin_cita.value== ""
					|| formulario.tipo_estudio_select.value== "" || formulario.proced_select.value== "" || formulario.id_medico.value== ""
					|| formulario.tipo_id.value== "" || formulario.id_paciente.value== "" || formulario.tipo_paciente_select.value== "" || formulario.forma_pago.value== ""
					 ){
						 location.reload();
						 return false ; 
						}
			}
			
			if(document.getElementById("operacionCita").value=="RegistrarNP"){
				fecha_actual= moment().format('DD-MM-YYYY');
					if(document.getElementById("fecha_inicio_cita").value>fecha_actual){
						alert("No se pueden registrar Proced. No Planificados para fechas futuras");
						return false;
					}
				}
			return true
		}	
		
	function buscarMedico(tipo_id, codigo){

		if(document.getElementById("fecha_inicio_cita").value!="" && document.getElementById("hora_fin_cita").value!=""){
			
			medico = new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarMedico.php?",
			data: 'buscar=cargar&tipo_id='+tipo_id+"&codigo="+codigo,
			success: function(response){
				medico=eval("(" + response + ")");
				
					if(medico.length > 0){
						
						if(validarCita(tipo_id,codigo) == true){
							
								nombre=medico[2] + " " + medico[4];
								if(document.getElementById("operacionCita").value != "RegistrarNP"){
										medicoDisponible(tipo_id, codigo, nombre);
								}
								else{
										document.getElementById("nombre_medico").innerHTML=nombre;
									}
						}
						else
						{
							alert("Usted no esta esta relacionado con ese Medico");
							document.getElementById("id_medico").value="";
							document.getElementById("nombre_medico").innerHTML="";
							
							}
						
					}
					else
						{
							alert("El codigo de ese medico no existe, introduzca otro");
							document.getElementById("id_medico").value="";
							document.getElementById("nombre_medico").innerHTML="";
							
							}
				
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: false,
			});
			
		}
		else{
				alert("Debe seleccionar primero un rango de tiempo para la cita (Hora Inicio / Hora Fin)");
				document.getElementById("id_medico").value="";
				document.getElementById("nombre_medico").innerHTML="";
			}
	
	}
	
	
	function buscarPacienteSERVINTE(codigo, tipo){
		
		if(document.getElementById("fecha_inicio_cita").value!="" && document.getElementById("hora_fin_cita").value!=""){
			
				paciente = new Array();
				$.ajax({
				type: "GET",
				url: "../consultas/consultasSERVINTE.php?",
				data: 'buscarPaciente=cargar&tipo_id='+tipo+'&id='+codigo,
				success: function(response){
		
					paciente=eval("(" + response + ")");
					
						if(paciente.length > 0){
							paciente2 = new Array();
							$.ajax({
							type: "GET",
							url: "../consultas/enviarPaciente.php?",
							data: 'registrar=cargar&tipo_id='+paciente[0]+'&codigo_paciente='+paciente[1]+'&nombre_paciente='+paciente[4]+'&apellido1_paciente='+paciente[2]+'&apellido2_paciente='+paciente[3]+'&sexo_paciente='+paciente[5]+'&fnac_paciente='+paciente[6]+'&telf_paciente='+paciente[7],
							success: function(response){
								paciente2=eval("(" + response + ")");
								if(paciente2!=null){
									buscarPaciente(paciente[1], paciente[0])
									}
								
								},
								error:function (xhr, ajaxOptions, thrownError){
								alert(xhr.status);
								alert(thrownError);
									
								},
								
								async: true,
							});
							}
						else
							{
								if(codigo!=""){
									if(confirm("La cedula de ese paciente no existe, desea agregarlo?")){
										window.location.href="../registrarPaciente.php?tipo_id="+tipo+"&codigo_paciente="+codigo+"&fecha_cita="+getURLParameter("fecha");
									}
									else{
										document.getElementById("id_paciente").value="";
										document.getElementById("tipo_id").options[0].selected=true;
										document.getElementById("nombre_paciente").innerHTML="";
										}
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
		else{
				alert("Debe seleccionar primero un rango de tiempo para la cita (Hora Inicio / Hora Fin)");
				document.getElementById("id_paciente").value="";
			    document.getElementById("tipo_id").options[0].selected=true;
				document.getElementById("nombre_paciente").innerHTML="";
			}
	}
	
	function buscarPaciente(codigo, tipo){
		
		if(document.getElementById("fecha_inicio_cita").value!="" && document.getElementById("hora_fin_cita").value!=""){
			
				paciente = new Array();
				$.ajax({
				type: "GET",
				url: "../consultas/enviarPaciente.php?",
				data: 'tipo_id='+tipo+'&codigo_paciente='+codigo,
				success: function(response){
					paciente=eval("(" + response + ")");
					
						if(paciente.length > 0){
								nombre=paciente[2] + " " + paciente[3];
								if(document.getElementById("operacionCita").value != "RegistrarNP"){
									pacienteDisponible(tipo, codigo, nombre);
								}
								else{
									document.getElementById("nombre_paciente").innerHTML=nombre;
								}
							}
						else
							{
								buscarPacienteSERVINTE(codigo, tipo);
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
				alert("Debe seleccionar primero un rango de tiempo para la cita (Hora Inicio / Hora Fin)");
				document.getElementById("id_paciente").value="";
			    document.getElementById("tipo_id").options[0].selected=true;
				document.getElementById("nombre_paciente").innerHTML="";
			}
	}
	
	
	function cargarProcedimientos(estudio, id){
		
		if(estudio!=""){
		
			procedimiento = new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarProcedimiento.php?",
			data: 'cargarEstudio='+estudio,
			success: function(response){
				
				procedimiento=eval("(" + response + ")");
				
					if(procedimiento.length > 0){
						document.getElementById(id).disabled=false;
						document.getElementById(id).options.length=0;
						document.getElementById(id).options[0]=new Option(" ",  " ");
						document.getElementById(id).options[0].selected=true;
						
						for(var i=0; procedimiento.length>i;++i){
							
							document.getElementById(id).options[i+1]=new Option( procedimiento[i].nombre,  procedimiento[i].id);
							
							}
							
						cargarProcedimientosCompuestos(id);
						
						}
					else
						{
							document.getElementById(id).options.length=0;
							document.getElementById(id).options[0]=new Option(" ",  " ");
							document.getElementById(id).options[0].selected=true;
							document.getElementById(id).disabled=false;
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
		else{
							document.getElementById(id).options.length=0;
							document.getElementById(id).options[0]=new Option(" ",  " ");
							document.getElementById(id).options[0].selected=true;
							document.getElementById(id).disabled=true;
			}
	
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
						++j;
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
	
	
	function cargarHoraFin(id){
		
			procedimiento = new Array();
			if(document.getElementById("hora_inicio_cita").value!=""){
				
					$.ajax({
					type: "GET",
					url: "../consultas/enviarProcedimiento.php?",
					data: 'buscarCita='+id,
					success: function(response){
						
						procedimiento=eval("(" + response + ")");
						
							if(procedimiento.length > 0){
								
								tiempo=parseInt(procedimiento[3]) + parseInt(procedimiento[2]);
								
								var hora_inicio= document.getElementById("hora_inicio_cita").value.split(":");
								var hours_inicio=hora_inicio[0]*3600;
								var minutes_inicio=hora_inicio[1]*60;
								
								tiempo_duracion = tiempo * 60;

								tiempo_duracion2=parseInt(hora_inicio[2])+hours_inicio+minutes_inicio;

								tiempo_total=tiempo_duracion+tiempo_duracion2;
								
								var hours = Math.floor( tiempo_total/ 3600 );  
								var minutes = Math.floor( (tiempo_total % 3600) / 60 );
								var seconds = tiempo_total % 60;
								
								//Anteponiendo un 0 a los minutos si son menos de 10 
								hours = hours < 10 ? '0' + hours : hours;
								
								//Anteponiendo un 0 a los minutos si son menos de 10 
								minutes = minutes < 10 ? '0' + minutes : minutes;
								 
								//Anteponiendo un 0 a los segundos si son menos de 10 
								seconds = seconds < 10 ? '0' + seconds : seconds;
								 
								hora_final= hours + ":" + minutes  + ":" + seconds; 
								
								document.getElementById("hora_fin_cita").value=hora_final;	
								
								}
						
						},
						error:function (xhr, ajaxOptions, thrownError){
						alert(xhr.status);
						alert(thrownError);
							
						},
						
						async: false,
					});
			}
			else {
						alert("Debe seleccionar primero un horario de inicio de la cita");
						document.getElementById("proced_select").options.length=0;
						document.getElementById("proced_select").options[0]=new Option(" ",  " ");
						document.getElementById("proced_select").options[0].selected=true;
						document.getElementById("tipo_estudio_select").options[0].selected=true;
						document.getElementById("proced_select").disabled=true;
				}
		
		}
	
	
	
	
	
	function salaDisponible(){
		
		if(document.getElementById("operacionCita").value != "RegistrarNP"){
		
			if(document.getElementById("sala_cita").value!=" " ){
					var minTime = $('#calendar').fullCalendar('option', 'minTime');
					var maxTime = $('#calendar').fullCalendar('option', 'maxTime');
					sala = new Array();
					$.ajax({
					type: "GET",
					url: "../consultas/enviarCita.php?",
					data: 'salasCitas=cargar&sala_cita='+ document.getElementById("sala_cita").value +'&fecha=' + document.getElementById("fecha_inicio_cita").value + '&hora_inicio='+minTime+'&hora_fin='+maxTime,
					success: function(response){
					
						sala=eval("(" + response + ")");
						
							if(sala.length > 0){
								
								if(document.getElementById("operacionCita").value=="Actualizar"){
										
										for(var i = sala.length; i--;){
											
												if (sala[i].id == document.getElementById("codigo_cita").value) sala.splice(i, 1);
											}
									}
								
								var fecha_inicio=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_inicio_cita").value;
								var fecha_fin=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_fin_cita").value;
								
								disponibilidad = true;
								
								for(var i=0; sala.length>i;++i){
										
											if((fecha_inicio >= sala[i].start && fecha_inicio <= sala[i].end) || (fecha_fin <= sala[i].end && fecha_fin >= sala[i].start) || (fecha_inicio <= sala[i].start && fecha_fin >= sala[i].end) ){
												
												disponibilidad=false;
												}
											
										}
								
								
								if(disponibilidad==false){
									alert("No hay disponibilidad en "+ document.getElementById("sala_cita").options[document.getElementById("sala_cita").selectedIndex].text + " a esa hora para aplicar el Procedimiento");
									document.getElementById("proced_select").options.length=0;
									document.getElementById("proced_select").options[0]=new Option(" ",  " ");
									document.getElementById("proced_select").options[0].selected=true;
									document.getElementById("tipo_estudio_select").options[0].selected=true;
									document.getElementById("proced_select").disabled=true;
									document.getElementById("hora_fin_cita").value="";
									
									}
								if(document.getElementById("operacionCita").value=="Actualizar"){
									medicoDisponible(document.getElementById("tipo_id_medico").value,document.getElementById("id_medico").value, document.getElementById("nombre_medico").innerHTML);
									pacienteDisponible(document.getElementById("tipo_id").value, document.getElementById("id_paciente").value, document.getElementById("nombre_paciente").innerHTML);
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
				else
				{
					alert("Debe seleccionar primero una sala para el inicio de la cita");
					document.getElementById("proced_select").options[0].selected=true;
					document.getElementById("hora_fin_cita").value="";
					}
			}
		
		}
	
	function equipoDisponible(id,proced, fecha, horaI, horaF){
		
		
		if(document.getElementById("operacionCita").value != "RegistrarNP" && validarEquipoServicio(document.getElementById("sala_cita").value)==true){
		
					document.getElementById(id).options.length=0;
					document.getElementById(id).options[0]=new Option(" ",  " ");
					document.getElementById(id).options[0].selected=true;
				
					var minTime = $('#calendar').fullCalendar('option', 'minTime');
					var maxTime = $('#calendar').fullCalendar('option', 'maxTime');
					equipo = new Array();
					
					$.ajax({
					type: "GET",
					url: "../consultas/enviarCita.php?",
					data: 'salasEquipo=cargar&sala_cita='+ document.getElementById("sala_cita").value+'&codigo_proced='+ proced +'&fecha=' + document.getElementById("fecha_inicio_cita").value + '&hora_inicio='+minTime+'&hora_fin='+maxTime,
					success: function(response){
					
						equipo=eval("(" + response + ")");
						
						if(document.getElementById("operacionCita").value=="Actualizar"){
											for(var i = equipo.citas.length; i--;){
												if (equipo.citas[i].cita == document.getElementById("codigo_cita").value) equipo.citas.splice(i, 1);
												}
										}
					
						
						if(equipo.sala_fijo.length>0){
							
							var fecha_inicio=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_inicio_cita").value;
							var fecha_fin=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_fin_cita").value;
									
								for(var i=0; equipo.sala_fijo.length>i; ++i){
									
									if(equipo.citas.length>0){
										disponible=true;
																
										for(var j=0; equipo.citas.length>j;++j){
																			
											 if(equipo.sala_fijo[i].id==equipo.citas[j].equipo){
																				 	
													if((fecha_inicio >= equipo.citas[j].inicio && fecha_inicio <= equipo.citas[j].fin) || (fecha_fin <= equipo.citas[j].fin && fecha_fin >= equipo.citas[j].inicio) || (fecha_inicio <= equipo.citas[j].inicio && fecha_fin >= equipo.citas[j].fin) ){
														disponible=false;
													}
												}
											}
																			
										if(disponible==true){
											document.getElementById(id).options[i+1]=new Option(equipo.sala_fijo[i].nombre, equipo.sala_fijo[i].id);					
											}	
									}
									else{
										document.getElementById(id).options[i+1]=new Option(equipo.sala_fijo[i].nombre, equipo.sala_fijo[i].id);					
										}
								}
							}
							
							
							
							if(document.getElementById(id).options.length<=1){
								
								if(equipo.sala_movil.length>0){
										
											var fecha_inicio=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_inicio_cita").value;
											var fecha_fin=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_fin_cita").value;
													
												for(var i=0; equipo.sala_movil.length>i; ++i){
													
														if(equipo.citas.length>0){
															
																		disponible=true;
																		
																		for(var j=0; equipo.citas.length>j;++j){
																			
																			 if(equipo.sala_movil[i].id==equipo.citas[j].equipo){
																				 	
																					if((fecha_inicio >= equipo.citas[j].inicio && fecha_inicio <= equipo.citas[j].fin) || (fecha_fin <= equipo.citas[j].fin && fecha_fin >= equipo.citas[j].inicio) || (fecha_inicio <= equipo.citas[j].inicio && fecha_fin >= equipo.citas[j].fin) ){
																							disponible=false;
																					}
																				 }
																			}
																			
																		if(disponible==true){
																			document.getElementById(id).options[i+1]=new Option(equipo.sala_movil[i].nombre, equipo.sala_movil[i].id);					
																			}	
																	}
																else{
																	document.getElementById(id).options[i+1]=new Option(equipo.sala_movil[i].nombre, equipo.sala_movil[i].id);					
																	}
												}
									}
							}
							
							
								if(document.getElementById(id).options.length<=1){
								
										if(equipo.movil.length>0){
												
													var fecha_inicio=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_inicio_cita").value;
													var fecha_fin=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_fin_cita").value;
															
														for(var i=0; equipo.movil.length>i; ++i){
															
																if(equipo.citas.length>0){
																		disponible=true;
																		
																		for(var j=0; equipo.citas.length>j;++j){
																			
																			 if(equipo.movil[i].id==equipo.citas[j].equipo){
																				 	
																					if((fecha_inicio >= equipo.citas[j].inicio && fecha_inicio <= equipo.citas[j].fin) || (fecha_fin <= equipo.citas[j].fin && fecha_fin >= equipo.citas[j].inicio) || (fecha_inicio <= equipo.citas[j].inicio && fecha_fin >= equipo.citas[j].fin) ){
																							disponible=false;
																						}
																				 }
																			}
																			
																		if(disponible==true){
																			document.getElementById(id).options[i+1]=new Option(equipo.movil[i].nombre, equipo.movil[i].id);					
																			}	
																	}
																else{
																	document.getElementById(id).options[i+1]=new Option(equipo.movil[i].nombre, equipo.movil[i].id);					
																	}
														}
											}
										}
								
							
							if(document.getElementById(id).options.length<=1){
									alert("No hay disponibilidad de equipos para el procedimiento en "+ document.getElementById("sala_cita").options[document.getElementById("sala_cita").selectedIndex].text );
									}
									
						},
						error:function (xhr, ajaxOptions, thrownError){
						alert(xhr.status);
						alert(thrownError);
							
						},
						
						async: false,
					});
				
			}
			else{
				
					document.getElementById(id).options.length=0;
					document.getElementById(id).options[0]=new Option(" ",  " ");
					document.getElementById(id).options[0].selected=true;
				
					var minTime = $('#calendar').fullCalendar('option', 'minTime');
					var maxTime = $('#calendar').fullCalendar('option', 'maxTime');
					equipo = new Array();
					
					$.ajax({
					type: "GET",
					url: "../consultas/enviarCita.php?",
					data: 'salasEquipoNP=cargar&codigo_proced='+ proced,
					success: function(response){
						
						equipo=eval("(" + response + ")");
				
						for(var i=0; equipo.length>i; ++i){
							document.getElementById(id).options[i+1]=new Option(equipo[i].nombre, equipo[i].id);
							}
					
					
						},
						error:function (xhr, ajaxOptions, thrownError){
						alert(xhr.status);
						alert(thrownError);
							
						},
						
						async: false,
					});
				}
		
		}
		
	function medicoDisponible(tipo_id, codigo_medico, nombre_medico){
		
			var minTime = $('#calendar').fullCalendar('option', 'minTime');
			var maxTime = $('#calendar').fullCalendar('option', 'maxTime');
			medico = new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarCita.php?",
			data: 'salasCitasMedico=cargar&tipo_id='+tipo_id+'&codigo_medico='+ codigo_medico +'&fecha=' + document.getElementById("fecha_inicio_cita").value + '&hora_inicio='+minTime+'&hora_fin='+maxTime,
			success: function(response){
				medico=eval("(" + response + ")");
				disponibilidad = true;
				
					if(medico.length > 0){
						
						if(document.getElementById("operacionCita").value=="Actualizar"){
										for(var i = medico.length; i--;){
												if (medico[i].id === document.getElementById("codigo_cita").value) medico.splice(i, 1);
											}
									}
								
						var fecha_inicio=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_inicio_cita").value;
						var fecha_fin=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_fin_cita").value;
									
							for(var i=0; medico.length>i;++i){
										
										if((fecha_inicio >= medico[i].start && fecha_inicio <= medico[i].end) || (fecha_fin <= medico[i].end && fecha_fin >= medico[i].start) || (fecha_inicio <= medico[i].start && fecha_fin >= medico[i].end) ){
												
											disponibilidad=false;
											}
									}
						}
								
							if(disponibilidad==false){
								
								alert("Dr(a): " + nombre_medico + " ya posee una cita en ese rango de tiempo, no puede registrarse");
									if(document.getElementById("operacionCita").value=="Registrar"){
										
										document.getElementById("id_medico").value="";
										document.getElementById("nombre_medico").innerHTML="";
									}
									
								}
							else{
								
								document.getElementById("nombre_medico").innerHTML=nombre_medico;
								}
									
				
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: false,
			});
		
	}
	
	function pacienteDisponible(tipo_id, codigo_paciente, nombre_paciente){
		
			var minTime = $('#calendar').fullCalendar('option', 'minTime');
			var maxTime = $('#calendar').fullCalendar('option', 'maxTime');
			paciente = new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarCita.php?",
			data: 'salasCitasPaciente=cargar&tipo_id='+tipo_id+'&codigo_paciente='+ codigo_paciente +'&fecha=' + document.getElementById("fecha_inicio_cita").value + '&hora_inicio='+minTime+'&hora_fin='+maxTime,
			success: function(response){
				
				paciente=eval("(" + response + ")");
				disponibilidad = true;
				
					if(paciente.length > 0){
						
						if(document.getElementById("operacionCita").value=="Actualizar"){
										for(var i = paciente.length; i--;){
												if (paciente[i].id === document.getElementById("codigo_cita").value) paciente.splice(i, 1);
											}
									}
								
						var fecha_inicio=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_inicio_cita").value;
						var fecha_fin=document.getElementById("fecha_inicio_cita").value + " " + document.getElementById("hora_fin_cita").value;
									
							for(var i=0; paciente.length>i;++i){
										
										if((fecha_inicio >= paciente[i].start && fecha_inicio <= paciente[i].end) || (fecha_fin <= paciente[i].end && fecha_fin >= paciente[i].start) || (fecha_inicio <= paciente[i].start && fecha_fin >= paciente[i].end) ){
												
											disponibilidad=false;
											}
									}
						}
								
							if(disponibilidad==false){
								
								alert("El paciente " + nombre_paciente + " ya posee una cita en ese rango de tiempo, no puede registrarse");
									if(document.getElementById("operacionCita").value=="Registrar"){
										document.getElementById("id_paciente").value="";
										document.getElementById("tipo_id").options[0].selected=true;
										document.getElementById("nombre_paciente").innerHTML="";
									}
									
								}
							else{
								
								document.getElementById("nombre_paciente").innerHTML=nombre_paciente;
								}
									
				
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
					
				},
				
				async: false,
			});
	
	}
	
	function mostrarEquipos(proced){
		
		var tableRef = document.getElementById('tabla_equipos');
			 while ( tableRef.rows.length > 0 )
			 {
			  tableRef.deleteRow(0);
			 }
		
		equipo = new Array();
		$.ajax({
		type: "GET",
		url: "../consultas/enviarProcedimiento.php?",
		data: 'buscarEquipoCita='+proced,
		success: function(response){
			
			equipo=eval("(" + response + ")");
			
			if(equipo[0].id!=null){
				
						tabla = document.getElementById('tabla_equipos');
						var j=1
						for(var i=0; i<equipo.length; ++i){
							
							tr = tabla.insertRow(tabla.rows.length);
							td = tr.insertCell(tr.cells.length);
							td.innerHTML = "<font size='2' style='color:#646464;'>Equipo "+ equipo[i].nombre.slice(0,6)+": </font><select id='equipo_select"+j+"'  name='equipo_select"+j+"'>" +
											"<font size='2'>" +
											"<option value='' SELECTED></option> </font>" +
											"</select><br>";
							td = tr.insertCell(tr.cells.length);
					
							++j;
							}	
							validarTabla(equipo);
			}
			else{
						tabla = document.getElementById('tabla_equipos');
						tr = tabla.insertRow(tabla.rows.length);
						td = tr.insertCell(tr.cells.length);
						td.innerHTML = "<font size='2' style='color:#646464;'>Equipo "+ equipo[1].slice(0,6)+": </font><select id='equipo_select1'  name='equipo_select1'>" +
                                        "<font size='2'>" +
                                        "<option value='' SELECTED></option> </font>" +
                            			"</select><br>";
						td = tr.insertCell(tr.cells.length);
						equipoDisponible('equipo_select1',equipo[0]);
							}
			
			},
			error:function (xhr, ajaxOptions, thrownError){
            alert(xhr.status);
            alert(thrownError);
				
			},
			
			async: false,
		});
		
		
		}
	
	function validarTabla(equipo){
		
		fechaCita=document.getElementById("fecha_inicio_cita").value;
		horaInicio=document.getElementById("hora_inicio_cita").value;
		horaFin=document.getElementById("hora_fin_cita").value;
		
		var rows = document.getElementById("tabla_equipos").getElementsByTagName('tbody')[0].getElementsByTagName('tr');
		for(var i=0; i<rows.length; ++i){
			equipoDisponible("equipo_select"+(i+1),equipo[i].id, fechaCita, horaInicio, horaFin);
			}
		}
		
	function validarCita(tipo, id){
		
			var retorno;
		
			if(nacSesion==tipo && cedulaSesion==id){
				retorno=true;
				}
			else{
				if(autorizacionSesion[2]==1){
					retorno=true;
					}
				else{
					secretaria = new Array();
					$.ajax({
					type: "GET",
					url: "../consultas/enviarSecretaria.php?",
					data: 'buscarMedico=buscar&tipo_medico='+tipo+'&id_medico='+id+'&tipo_secre='+nacSesion+'&id_secre='+cedulaSesion,
					success: function(response){
						
						secretaria=eval("(" + response + ")");
						if(secretaria.length>0){
							
							retorno=true;
							
							}
						else{
							retorno= false;
							}
						
						},
						error:function (xhr, ajaxOptions, thrownError){
						alert(xhr.status);
						alert(thrownError);
							
						},
						
						async: false,
					});
					
					
					}
				}
				
				return retorno;
		}
		
		function validarEquipoServicio(sala_id){
		
			var validar;
		
			servicio = new Array();
			$.ajax({
			type: "GET",
			url: "../consultas/enviarServicio.php?",
			data: 'buscarValidarEquipo='+sala_id,
			success: function(response){
						
				servicio=eval("(" + response + ")");
				if(servicio.length>0){
							
					if(servicio[2]=="Activo"){
						validar=true;
					}
					else{
						validar= false;
						}
					}
				
				},
				error:function (xhr, ajaxOptions, thrownError){
				alert(xhr.status);
				alert(thrownError);
							
				},
						
				async: false,
			});
			
			return validar;
			
		}
		
		
		
		
		
		
	