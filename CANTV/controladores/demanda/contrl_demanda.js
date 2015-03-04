/* 
	 * To change this template, choose Tools | Templates
 	* and open the template in the editor.
*/
function registrar_d(){
	if(validar_vacio_demanda()==true){
		
			document.getElementById("id_form1").submit();
		}
		
	}
	

	
function validar_vacio_demanda(){
	bandera=true;
	
	if(document.getElementById("validar2").value=="Actualizar"){
		
	instituciones=document.getElementById('instituciones').value;
	localidad=document.getElementById("localidad2").value;
	nombre=document.getElementById("nombre_sede").value;
	region=document.getElementById("reg3").value;
	estado=document.getElementById("est3").value;
	municipio=document.getElementById("mun3").value;
	central=document.getElementById("cent3").value;
	servicio=document.getElementById("servicio").value;
	tipo_servicio=document.getElementById("tipo_servicio").value;
	sub_servicio=document.getElementById("subtipo_servicio").value;
	plan=document.getElementById("plan").value;
	cantidad=document.getElementById("cantidad").value;
	trimestre=document.getElementById("trimestre").value;
	ano=document.getElementById("ano").value;
	
	if(instituciones=="" || localidad==0 || nombre=="" || region==""|| estado==""|| municipio==""|| central==""|| servicio==""|| tipo_servicio==""|| sub_servicio==""|| plan==""|| cantidad==""|| trimestre==""|| ano==""){
		alert ("No Dejar Campos En Blanco");
		bandera= false;
		}
	
	
	}
		else
		{
		if(document.getElementById("validar2").value=="Registrar"){
			
			instituciones=document.getElementById('instituciones').value;
			nombre=document.getElementById("nombre_sede").value;
			region=document.getElementById("region").value;
			estado=document.getElementById("estado").value;
			municipio=document.getElementById("municipio").value;
			central=document.getElementById("central").value;
			servicio=document.getElementById("servicio").value;
			tipo_servicio=document.getElementById("tipo_servicio").value;
			sub_servicio=document.getElementById("subtipo_servicio").value;
			plan=document.getElementById("plan").value;
			cantidad=document.getElementById("cantidad").value;
			trimestre=document.getElementById("trimestre").value;
			ano=document.getElementById("ano").value;
			
				if(instituciones=="" || nombre=="" || region==""|| estado==""|| municipio==""|| central==""|| servicio==""|| tipo_servicio	==""|| sub_servicio==""|| plan==""|| cantidad==""|| trimestre==""|| ano==""){
			alert ("No Dejar Campos En Blanco");
			bandera= false;
		}
			
			
			}
		}
	
		return bandera;
		}
	
	function cerrar(){
		location.href="../../driver/ishorcut.php";
		}
	
	
	function obtener_localidades(){
			var c = document.getElementById("instituciones").value;
			
			if (c==0){
				document.getElementById("nombre_sede").value="";
				document.getElementById("locali").hidden=true;
				document.getElementById("tab_ser").hidden=true;
				document.getElementById("resd1").hidden=false;
				document.getElementById("resd2").hidden=true;
				document.getElementById("munn").hidden=false;
				document.getElementById("mun").hidden=true;
				document.getElementById("centt").hidden=false;
				document.getElementById("cent").hidden=true;
				document.getElementById("modi").hidden=true;
				document.getElementById("cccc").value="";
				document.getElementById("nombre_sede").disabled=false;
				document.getElementById("validar2").value="Registrar";
				accion_lsd("../../driver/control_demanda", "cargardis", [get_lsd("central")], "puertos_libres1")
				}
			else{

		$.ajax({
			type: "GET",
			url: "../../driver/obtener_localidades.php?",
			data: 'codf='+c,
			success: function(response){
				
				
				var JSON=response;
				var obj=jQuery.parseJSON(JSON);	
				
				document.getElementById("localidad2").options.length=1;
				
				if(obj.length>0){
					document.getElementById("locali").hidden=false;
					document.getElementById("resd1").hidden=false;
					document.getElementById("tab_ser").hidden=true;
					document.getElementById("resd2").hidden=true;
					document.getElementById("munn").hidden=false;
					document.getElementById("mun").hidden=true;
					document.getElementById("centt").hidden=false;
					document.getElementById("cent").hidden=true;
					document.getElementById("modi").hidden=true;
					document.getElementById("nombre_sede").value="";
					document.getElementById("nombre_sede").disabled=false;
					document.getElementById("cccc").value="";
					document.getElementById("validar2").value="Registrar";
					accion_lsd("../../driver/control_demanda", "cargardis", [get_lsd("central")], "puertos_libres1")
					}
				else
				{
					document.getElementById("locali").hidden=true;
					document.getElementById("resd1").hidden=false;
					document.getElementById("resd2").hidden=true;
					document.getElementById("tab_ser").hidden=true;
					document.getElementById("munn").hidden=false;
					document.getElementById("mun").hidden=true;
					document.getElementById("centt").hidden=false;
					document.getElementById("cent").hidden=true;
					document.getElementById("modi").hidden=true;
					document.getElementById("cccc").value="";
					document.getElementById("nombre_sede").value="";
					document.getElementById("nombre_sede").disabled=false;
					document.getElementById("validar2").value="Registrar";
					accion_lsd("../../driver/control_demanda", "cargardis", [get_lsd("central")], "puertos_libres1")
					}
				
				for(i=0 ; i<obj.length ; i++){
					document.getElementById("localidad2").options.add(new Option(obj[i].nombre_sede,obj[i].id_sucursales));
					};
				
			},
			error: function(response){
				alert("Problemas con el Servidor");
				
			},
			
			async: true,
		});
		
			};
		
		}
		
		
		
		function mostrar_localidad(){
			var c = document.getElementById("localidad2").value;
			
			if (c=='0'){
				
				document.getElementById("resd1").hidden=false;
				document.getElementById("resd2").hidden=true;
				document.getElementById("munn").hidden=false;
				document.getElementById("tab_ser").hidden=true;
				document.getElementById("mun").hidden=true;
				document.getElementById("centt").hidden=false;
				document.getElementById("cent").hidden=true;
				document.getElementById("modi").hidden=true;
				document.getElementById("cccc").value="";
				document.getElementById("nombre_sede").value="";
				document.getElementById("nombre_sede").disabled=false;
				document.getElementById("validar2").value="Registrar";
				accion_lsd("../../driver/control_demanda", "cargardis", [get_lsd("central")], "puertos_libres1")
				
				}
				else {
		
		$.ajax({
			type: "GET",
			url: "../../driver/mostrar_localidad.php?",
			data: 'codf='+c,
			success: function(response){
				
				
				var JSON=response;
				var obj=jQuery.parseJSON(JSON);	
				
				document.getElementById("resd1").hidden=true;
				document.getElementById("resd2").hidden=false;
				document.getElementById("munn").hidden=true;
				document.getElementById("mun").hidden=false;
				document.getElementById("centt").hidden=true;
				document.getElementById("cent").hidden=false;
				document.getElementById("modi").hidden=false;
				document.getElementById("nombre_sede").disabled=true;
				document.getElementById("nombre_sede").value=obj[0].nombre_sede;
				document.getElementById("reg3").options.length=0;
				document.getElementById("est3").options.length=0;
				document.getElementById("mun3").options.length=0;
				document.getElementById("cent3").options.length=0;
				document.getElementById("reg3").options.add(new Option(obj[0].region,obj[0].idregion));
				document.getElementById("est3").options.add(new Option(obj[0].estado,obj[0].idestado));
				document.getElementById("mun3").options.add(new Option(obj[0].municipio,obj[0].idmunicipio));
				document.getElementById("cent3").options.add(new Option(obj[0].central,obj[0].idcentral));
				document.getElementById("cccc").value=obj[0].idcentral;
				document.getElementById("tab_ser").hidden=false;
				document.getElementById("servicios_datos").value=obj[0].serv_datos;
				document.getElementById("servicios_equipamento").value=obj[0].serv_equip;
				document.getElementById("servicios_internet").value=obj[0].serv_internet;
				document.getElementById("servicios_moviles").value=obj[0].serv_moviles;
				document.getElementById("servicios_telf_fija").value=obj[0].serv_telf;
				document.getElementById("servicios_ti").value=obj[0].serv_ti;
				document.getElementById("validar2").value="Actualizar";
				accion_lsd("../../driver/control_demanda", "cargardis", [get_lsd("cent3")], "puertos_libres1");
				
				
			},
			
			error: function(response){
				alert("Problemas con el Servidor");
				
			},
			
			async: true,
		});
		
				};
		}
		
		function modificar_data(){
			window.location="../data_inst/data_ip.php?cod="+ document.getElementById("localidad2").value +"&rif="+ document.getElementById("instituciones").value +"";
			}