

	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function buscarPaciente(){
		
		var codigo_paciente=document.getElementById("codigo_paciente").value;
		var tipo_id=document.getElementById("tipo_id").value;
		paciente = new Array();
		$.ajax({
		type: "GET",
		url: "consultas/enviarPaciente.php?",
		data: 'tipo_id='+tipo_id+'&codigo_paciente='+codigo_paciente,
		success: function(response){
			
			paciente=eval("(" + response + ")");
			
			if(paciente.length>0){
				
					document.getElementById("tipo_id").value=paciente[0];
					document.getElementById("codigo_paciente").value=paciente[1];
					document.getElementById("nombre_paciente").value=paciente[2];
					document.getElementById("apellido1_paciente").value=paciente[3];
					document.getElementById("apellido2_paciente").value=paciente[4];
					document.getElementById("fnac_paciente").value= paciente[6];
					document.getElementById("telf_paciente").value=paciente[7];
					
					for(k=0 ; k<document.getElementById("sexo_paciente").length ; k++){
						var v = document.getElementById("sexo_paciente").options[k].value;
						if(v==paciente[5]){
							document.getElementById("sexo_paciente").options[k].selected=true;
							}
						};
					
					if (document.getElementById("operacionPaciente").value=="Actualizar"){
						document.getElementById("codigo_paciente").readOnly=true;
						document.getElementById("nombre_paciente").disabled=false;
						document.getElementById("validar_equipo").disabled=false;
					}
					
					if (document.getElementById("operacionPaciente").value=="Eliminar"){
						document.getElementById("codigo_paciente").readOnly=true;
					}
			
			}
			else {
				
					alert("El paciente asociado a ese codigo no existe");
					document.getElementById("codigo_paciente").value="";
					document.getElementById("codigo_paciente").focus();
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
	function mostrarFormularioPaciente(id){

				if (id=="registrarPaciente"){
					document.getElementById('titulo').innerHTML="Registrar Paciente";
					document.getElementById("btnGuardar").value="Guardar";
					document.getElementById('operacionPaciente').value="Registrar";
					document.getElementById("btnBuscar").hidden=true;
					document.getElementById("nombre_paciente").disabled=false;
					document.getElementById("validar_equipo").disabled=false;
					limpiarPaciente();
					}
				if (id=="modificarPaciente"){
						document.getElementById('titulo').innerHTML="Actualizar Paciente";
						document.getElementById("btnGuardar").value="Guardar";
						document.getElementById('operacionPaciente').value="Actualizar";
						document.getElementById("btnBuscar").hidden=false;
						limpiarPaciente();
						}
				 if (id=="eliminarPaciente"){
						 document.getElementById('titulo').innerHTML="Eliminar Paciente";
						 document.getElementById("btnGuardar").value="Eliminar";
						 document.getElementById('operacionPaciente').value="Eliminar";
						 document.getElementById("btnBuscar").hidden=false;
						 limpiarPaciente();
						 }
					
		}			 					

	
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function validarVacioPaciente(formulario){
		
		if(formulario.tipo_id.value== "" || formulario.codigo_paciente.value== "" || formulario.nombre_paciente.value== "" || 
		formulario.apellido1_paciente.value== "" || formulario.apellido2_paciente.value== "" || 
		formulario.sexo_paciente.value== "" || formulario.fnac_paciente.value== "" || formulario.telf_paciente.value== "" )
		
		{
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
	function limpiarPaciente(){
		
		document.getElementById("codigo_paciente").value="";
		document.getElementById("nombre_paciente").value="";
		document.getElementById("tipo_id").options[0].selected=true;
		document.getElementById("codigo_paciente").readOnly=false;
		
		if(document.getElementById("operacionPaciente").value != "Registrar"){
			document.getElementById("nombre_paciente").disabled=true;
			document.getElementById("validar_equipo").disabled=true;
			}
					
		}
		
	
	function getURLParameter(name){
		
		return decodeURI(		
		(RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
		);
   		
		}	
		
	/* Función para obtener los parámetros pasados por el url.
	 * En esta se puede chequear si la cuenta se encuentra cerrada, producto de la
	 * aplicación de honorarios médicos.
	 */
	function registrarPacienteCita(){
		
		for(k=0 ; k<document.getElementById("tipo_id").length ; k++){
						var v = document.getElementById("tipo_id").options[k].value;
						if(v==getURLParameter("tipo_id")){
							document.getElementById("tipo_id").options[k].selected=true;
							}
						};
						
		var codigo=getURLParameter("codigo_paciente");
		if(codigo!="null"){
			document.getElementById("codigo_paciente").value=getURLParameter("codigo_paciente");
			}
		document.getElementById("fecha_cita").value=getURLParameter("fecha_cita");
					
		}
		
	function buscarPacienteSERVINTE(codigo, tipo){
		
		if(document.getElementById("tipo_id").value!="" && document.getElementById("codigo_paciente").value!=""){
			
				paciente = new Array();
				$.ajax({
				type: "GET",
				url: "../consultas/consultasSERVINTE.php?",
				data: 'buscarPaciente=cargar&tipo_id='+tipo+'&id='+codigo,
				success: function(response){
		
					paciente=eval("(" + response + ")");
					
						if(paciente.length > 0){
							
							document.getElementById("nombre_paciente").value=paciente[4];
							document.getElementById("apellido1_paciente").value=paciente[2];
							document.getElementById("apellido2_paciente").value=paciente[3];
							document.getElementById("fnac_paciente").value= paciente[6];
							document.getElementById("telf_paciente").value=paciente[7];
					
							for(k=0 ; k<document.getElementById("sexo_paciente").length ; k++){
								var v = document.getElementById("sexo_paciente").options[k].value;
								if(v==paciente[5]){
									document.getElementById("sexo_paciente").options[k].selected=true;
									}
								};
									
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
		
				 					


	