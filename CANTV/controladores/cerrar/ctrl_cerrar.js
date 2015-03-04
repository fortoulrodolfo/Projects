// JavaScript Document
function cerrar(){
	location.href="../../driver/ishorcut.php";
	}


function registrar_p(){
	
	if(validar_vacio_proyecto()==true){
		
			document.getElementById("tamano_tabla").value=document.getElementById("exp").rows.length;
			document.getElementById("id_form2").submit();
		}
		
	}
	

function validar_vacio_proyecto(){
	
	bandera=true;
	codigo_arc=document.getElementById("codigo_arc").value;
	instituciones=document.getElementById('instituciones').value;
	region=document.getElementById("region").value;
	macro_p=document.getElementById("macro_proyecto").value;
	descripcion=document.getElementById("descripcion_macro_proyecto").value;
	area=document.getElementById("area").value;
	prioridad=document.getElementById("prioridad").value;
	unidad=document.getElementById("unidad").value;
	forma_pago=document.getElementById("forma_de_pago").value;
	linea_negocio=document.getElementById("linea_de_negocio1").value;
	costo=document.getElementById("costo_venta1").value;
	ingreso=document.getElementById("ingresos_unico_anual1").value;
	i="";
	
	if(codigo_arc=="" || instituciones=="" || region==""|| macro_p==""|| descripcion==""|| area==""|| prioridad==""|| unidad=="" || forma_pago=="" || linea_negocio=="" || costo=="" || ingreso==""){
		alert("No Dejar Campos En Blanco");
		bandera= false;
		}
		
		
		
	if(document.getElementById("imp_comer_no").checked==false && document.getElementById("imp_comer_si").checked==false){
		alert("Selecciona Importancia Comercial");
		bandera= false;
		}
	
	if (document.getElementById("imp_poli_no").checked==false && document.getElementById("imp_poli_si").checked==false) {
		alert("Selecciona Importancia Politica");
		bandera= false;
		}
	
	if (document.getElementById("imp_soci_no").checked==false && document.getElementById("imp_soci_si").checked==false){
		alert("Selecciona Importancia Social");
		bandera= false;
		}
		
		return bandera;
		
	}


	
	var maxprogress=780;
	var limit=100;
	var actualprogress=0;
	var retorno=0;
	var itv=0;	

function limpiar_barra(){
	
	document.getElementById("tabcost").hidden=true;
	
	clearInterval(itv);
			document.getElementById("indicator").style.width="0px";
			document.getElementById("progresnum").innerHTML="0%";
			document.getElementById("alert").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'>";
			document.getElementById("alert2").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'>";
	}
function prog(){

	if (actualprogress >= maxprogress)
	{
		clearInterval(itv);
		return;
		}
		
	//var progressnum=document.getElementById("progresnum");
	//var indicator= document.getElementById("indicator");
	actualprogress+=1;
	var porcen=Math.round((actualprogress*100)/maxprogress)+"%";
	document.getElementById("indicator").style.width=actualprogress+"px";
	document.getElementById("progresnum").innerHTML=porcen;
	if (actualprogress == limit) clearInterval(itv);
	
	}


function mover_fase(){
	var c=parseInt(document.getElementById("fas").value);
	clearInterval(itv);
	var k=retorno;
	if(c>k){
		
	if(c>=12 && document.getElementById("costReal").value==0){
		
				document.getElementById("tabcost").hidden=false;
				k=c;
				};
	}
	else
	{
		document.getElementById("tabcost").hidden=true;
		};
		
	if(retorno<=c){
	var f=Math.round((c)*(maxprogress/(document.getElementById("fas").length-1)));
	var k=Math.round((retorno)*(maxprogress/(document.getElementById("fas").length-1)));
	actualprogress=k
	limit=f;
	itv=setInterval(prog,10);
	retorno=c;
	
	}
	else
	{
		if(c=="0")
		{
			clearInterval(itv);
			document.getElementById("indicator").style.width="0px";
			document.getElementById("progresnum").innerHTML="0%";
			}
		else
		{
		var f=Math.round((c)*(maxprogress/(document.getElementById("fas").length-1)));
		actualprogress=0;
		limit=f;
		itv=setInterval(prog,0);
		retorno=c;
		}
	};
	
	$.ajax({
		type: "GET",
		url: "../../driver/mover_fase.php?",
		data: 'codf='+c,
		success: function(response){
			
			
			for(j=0 ; j<document.getElementById("idfase").length ; j++){
				var g=document.getElementById("idfase").options[j].value;
				if(g==response){
					document.getElementById("idfase").options[j].selected=true;
					}
				};
			
			
		},
		error: function(response){
			alert("Problemas con el Servidor");
			
		},
		
		async: true,
	});
	
	
	
	
	}


function obtener_codigo(){
	
	var c=document.getElementById("codigo_arc").value;
	document.getElementById("sta").disabled=false;
	document.getElementById("fas").disabled=false;
	proyecto=new Array();
	
	$.ajax({
		type: "GET",
		url: "../../driver/retorno_proyecto.php?",
		data: 'codp='+c,
		success: function(response){
	
			var JSON=response;
			proyecto=eval("(" + JSON + ")");
			
			document.getElementById("inst").value=proyecto[0];
			document.getElementById("rif").value=proyecto[1];
			document.getElementById("nombre").value=proyecto[2];
			document.getElementById("sta").options[0].selected=true;
			
			for(k=0 ; k<document.getElementById("sta").length ; k++){
				var v=document.getElementById("sta").options[k].value;
				if(v==proyecto[3]){
					document.getElementById("sta").options[k].selected=true;
					}
				};
			
			for(j=0 ; j<document.getElementById("idfase").length ; j++){
				var g=document.getElementById("idfase").options[j].value;
				if(g==proyecto[4]){
					document.getElementById("idfase").options[j].selected=true;
					}
				};
				
				document.getElementById("id_fase_anterior").value=proyecto[4];
				document.getElementById("idtipo_fase_anterior").value=proyecto[5];
				document.getElementById("fecha_nueva").value=proyecto[12];
				document.getElementById("fecha_acep").value=proyecto[13];
				document.getElementById("fecha_entre").value=proyecto[14];
				document.getElementById("fecha_cul").value=proyecto[15];
				
				
			if(proyecto[10]==''){
				document.getElementById("alert").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'>";
				}
			if(proyecto[10]=='4'){
				document.getElementById("alert").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'>";
				}
			if(proyecto[10]=='1'){
				
				document.getElementById("alert").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Verde.jpg' width='26' height='26'>";
				
				}
			if(proyecto[10]=='2'){
				document.getElementById("alert").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Amarillo.jpg' width='26' height='26'>";
				}
			if(proyecto[10]=='3'){
				document.getElementById("alert").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Rojo.jpg' width='26' height='26'>";			}
				
				
			if(proyecto[11]==''){
				document.getElementById("alert2").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'>";
				}
			if(proyecto[11]=='4'){
				document.getElementById("alert2").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'>";
				}
			if(proyecto[11]=='1'){
				document.getElementById("alert2").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Verde.jpg' width='26' height='26'>";
				}
			if(proyecto[11]=='2'){
				document.getElementById("alert2").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Amarillo.jpg' width='26' height='26'>";
				}
			if(proyecto[11]=='3'){
				document.getElementById("alert2").innerHTML="<img src='../../shared/img/img alertas/Bombillo-Rojo.jpg' width='26' height='26'>";
				}
			
			for(h=0 ; h<document.getElementById("fas").length ; h++){
				var n=document.getElementById("fas").options[h].value;
				if(n==proyecto[5]){
					retorno=n;
					document.getElementById("fas").options[h].selected=true;
					}
				};
			
			if(proyecto[5]>=12){
				for(i=0 ; i<12 ; i++){
				document.getElementById("fas").options[i].hidden=true;
				}
				}
			else
			{
				for(i=0 ; i<12 ; i++){
				document.getElementById("fas").options[i].hidden=false;
				}
				};	
				
			if(proyecto[5]>=12 && proyecto[8]==0 && proyecto[9]==0){
				document.getElementById("tabcost").hidden=false;
				document.getElementById("cost").value=proyecto[6];
				document.getElementById("ing").value=proyecto[7];
				document.getElementById("costReal").value=proyecto[8];
				document.getElementById("ingReal").value=proyecto[9];
				}
			else{
				document.getElementById("tabcost").hidden=true;
				document.getElementById("cost").value=proyecto[6];
				document.getElementById("ing").value=proyecto[7];
				document.getElementById("costReal").value=proyecto[8];
				document.getElementById("ingReal").value=proyecto[9];
				};
				
			clearInterval(itv);
			
			if(c!="0")	{
			var f=Math.round((proyecto[5])*(maxprogress/(document.getElementById("fas").length-1)));
			actualprogress=0;
			limit=f;
			
			itv=setInterval(prog,10);
			}
			else{
				clearInterval(itv);
				document.getElementById("indicator").style.width="0px";
				document.getElementById("progresnum").innerHTML="0%";
				
				}
			
		},
		error: function(response){
			alert("Problemas con el Servidor");
			
		},
		
		async: true,
	});
	
	
	}
	
function cargar_informe(){
	var c=document.getElementById("gerencia").value;
	
	if (c!='0'){
	document.getElementById("filtro").disabled=false;
	document.getElementById("filtro").options[0].selected=true;
	cargar_filtro(document.getElementById("filtro"));
	}
	else{
		document.getElementById("filtro").disabled=true;
		
	document.getElementById("filtro").options[0].selected=true;
	cargar_filtro(document.getElementById("filtro"));
		}
	$.ajax({
		type: "GET",
		url: "../../driver/cargar_informe.php?",
		data: 'codg='+c,
		success: function(response){
		
			var JSON=response;
			var obj=jQuery.parseJSON(JSON);
			
				//Ingresos
				document.getElementById("ingresos_estimados").value=parseInt(obj[0].ing_tot_ini);
				document.getElementById("ingresos_asociados").value=parseInt(obj[0].ing_tot_pro);
				document.getElementById("total_ingresos").value=parseInt(obj[0].ing_tot_ini)+parseInt(obj[0].ing_tot_pro);
				
				//Tasa Exito
				document.getElementById("tasa_exito").value=obj[1].tasa_exito;
				//Importancias
				document.getElementById("importancia_social1").value=parseInt(obj[2].imp_soc_si);
				document.getElementById("importancia_social2").value=parseInt(obj[2].imp_soc_no);
				document.getElementById("importancia_politica1").value=parseInt(obj[2].imp_poli_si);
				document.getElementById("importancia_politica2").value=parseInt(obj[2].imp_poli_no);
				document.getElementById("importancia_comercial1").value=parseInt(obj[2].imp_com_si);
				document.getElementById("importancia_comercial2").value=parseInt(obj[2].imp_com_no);
				//Iniciativas
				document.getElementById("cantidad_iniciativas").value=parseInt(obj[3].cont_iniciativa);
				document.getElementById("cantidad_proyectos").value=parseInt(obj[3].cont_proyecto);
				document.getElementById("total_iniciativas").value=parseInt(obj[3].totalip);		
					
				
		},
		error: function(response){
			alert("Problemas con el Servidor");
			
		},
		
		async: true,
	});
	
	
	}
	
	
function cargar_filtro(cod){
	var c=document.getElementById("gerencia").value;
	var f=cod.value;

	if (f=='0'){
		c='0';
		f='1';
		}

	
	$.ajax({
		type: "GET",
		url: "../../driver/cargar_filtro.php?",
		data: 'codg='+c+'&codf='+f,
		success: function(response){
			
			var JSON=response;
			var obj=jQuery.parseJSON(JSON);	
					
					
						//Areas
				document.getElementById("alimentacion").value=parseInt(obj[0].ali);
				document.getElementById("alimentacionp").value=obj[0].porcen_ali;
				document.getElementById("educacion").value=parseInt(obj[0].edu);
				document.getElementById("educacionp").value=obj[0].porcen_edu;
				document.getElementById("gobierno").value=parseInt(obj[0].gob);
				document.getElementById("gobiernop").value=obj[0].porcen_gob;
				document.getElementById("salud").value=parseInt(obj[0].salud);
				document.getElementById("saludp").value=obj[0].porcen_salud;
				document.getElementById("seguridad").value=parseInt(obj[0].segu);
				document.getElementById("seguridadp").value=obj[0].porcen_segu;
				//Region
				document.getElementById("andes").value=parseInt(obj[1].and);
				document.getElementById("andesp").value=obj[1].porcen_and;
				document.getElementById("capital").value=parseInt(obj[1].cap);
				document.getElementById("capitalp").value=obj[1].porcen_cap;
				document.getElementById("central").value=parseInt(obj[1].cen);
				document.getElementById("centralp").value=obj[1].porcen_cen;
				document.getElementById("centro_occidente").value=parseInt(obj[1].centro_o);
				document.getElementById("centro_occidentep").value=obj[1].porcen_centro_o;
				document.getElementById("occidente").value=parseInt(obj[1].occi);
				document.getElementById("occidentep").value=obj[1].porcen_occi;
				document.getElementById("oriente").value=parseInt(obj[1].ori);
				document.getElementById("orientep").value=obj[1].porcen_ori;
				//Linea de Negocio
			
				document.getElementById("cableado_estructurado1").value=obj[2].ing_cab;
				document.getElementById("cableado_estructurado2").value=obj[2].porcen_cab;
				document.getElementById("cableado_estructurado3").value=obj[2].cab;
				document.getElementById("cableado_estructurado4").value=obj[2].porcen_cab_n;
				
				document.getElementById("equipamiento1").value=obj[2].ing_equ;
				document.getElementById("equipamiento2").value=obj[2].porcen_equ;
				document.getElementById("equipamiento3").value=obj[2].equ;
				document.getElementById("equipamiento4").value=obj[2].porcen_equ_n;
				
				document.getElementById("datos1").value=obj[2].ing_dat;
				document.getElementById("datos2").value=obj[2].porcen_dat;
				document.getElementById("datos3").value=obj[2].dat;
				document.getElementById("datos4").value=obj[2].porcen_dat_n;
				
				document.getElementById("movilnet1").value=obj[2].ing_mov;
				document.getElementById("movilnet2").value=obj[2].porcen_mov;
				document.getElementById("movilnet3").value=obj[2].mov;
				document.getElementById("movilnet4").value=obj[2].porcen_mov_n;
				
				document.getElementById("servicios1").value=obj[2].ing_ser;
				document.getElementById("servicios2").value=obj[2].porcen_ser;
				document.getElementById("servicios3").value=obj[2].serv;
				document.getElementById("servicios4").value=obj[2].porcen_serv_n;
				
				document.getElementById("telco1").value=obj[2].ing_tel;
				document.getElementById("telco2").value=obj[2].porcen_tel;
				document.getElementById("telco3").value=obj[2].telco;
				document.getElementById("telco4").value=obj[2].porcen_telco_n;
				
				document.getElementById("total1").value=obj[2].totalingre;
				document.getElementById("total2").value=obj[2].totalicant;
				
				//Forma de pago
				
				document.getElementById("concurrente1").value=obj[3].recu;
				document.getElementById("concurrente2").value=obj[3].porcen_rec;
				document.getElementById("unico1").value=obj[3].uni;
				document.getElementById("unico2").value=obj[3].porcen_uni;
				
				
				//Macro-Proyecto
				
				document.getElementById("acaldias_digitales1").value=obj[4].ing_alc;
				document.getElementById("acaldias_digitales2").value=obj[4].porcen_alc;
				document.getElementById("acaldias_digitales3").value=obj[4].alc;
				document.getElementById("acaldias_digitales4").value=obj[4].porcen_alc_n;
				
				document.getElementById("gis_salud1").value=obj[4].ing_sal;
				document.getElementById("gis_salud2").value=obj[4].porcen_sal;
				document.getElementById("gis_salud3").value=obj[4].sal;
				document.getElementById("gis_salud4").value=obj[4].porcen_sal_n;
				
				document.getElementById("modernizacion_del_estado1").value=obj[4].ing_moder;
				document.getElementById("modernizacion_del_estado2").value=obj[4].porcen_moder;
				document.getElementById("modernizacion_del_estado3").value=obj[4].moder;
				document.getElementById("modernizacion_del_estado4").value=obj[4].porcen_moder_n;
				
				document.getElementById("red_del_saber1").value=obj[4].ing_red;
				document.getElementById("red_del_saber2").value=obj[4].porcen_red;
				document.getElementById("red_del_saber3").value=obj[4].red;
				document.getElementById("red_del_saber4").value=obj[4].porcen_red_n;
				
				document.getElementById("seguridad_agro_alimentaria1").value=obj[4].ing_sega;
				document.getElementById("seguridad_agro_alimentaria2").value=obj[4].porcen_sega;
				document.getElementById("seguridad_agro_alimentaria3").value=obj[4].sega;
				document.getElementById("seguridad_agro_alimentaria4").value=obj[4].porcen_sega_n;
				
				document.getElementById("seguridad_ciudadana1").value=obj[4].ing_segci;
				document.getElementById("seguridad_ciudadana2").value=obj[4].porcen_segci;
				document.getElementById("seguridad_ciudadana3").value=obj[4].segci;
				document.getElementById("seguridad_ciudadana4").value=obj[4].porcen_segci_n;
				
				document.getElementById("totaal1").value=obj[4].totalingre;
				document.getElementById("totaal2").value=obj[4].totalmp;
				
				
				
		},
		error: function(response){
			alert("Problemas con el Servidor");
			
		},
		
		async: true,
	});
	
	}



function registrar2(){
	
	document.getElementById("tamano_tabla").value=document.getElementById("exp").rows.length;
			document.getElementById("id_form3").submit();
		
	}

function actualizar_segui(){
	var f=document.form1;
	document.getElementById("idfase2").value=document.getElementById("idfase").value;
	}


function imprimir(cod){
	if (cod==4){
	window.open("impress.php",true);
	}
	if (cod==2){
	window.open("impress_lider.php",true);
	}
	if (cod==3){
	window.open("impress_gerente.php",true);
	}
	}
	
function imprimir_pro(cod){
	if (cod==4){
	window.open("impress_pro.php",true);
	}
	if (cod==2){
	window.open("impress_pro_lider.php",true);
	}
	if (cod==3){
	window.open("impress_pro_gerente.php",true);
	}
	}

function imprimir_data(cod){
	if (cod==4){
	window.open("impress_data.php",true);
	}
	if (cod==2){
	window.open("impress_data_lider.php",true);
	}
	if (cod==3){
	window.open("impress_data_gerente.php",true);
	}
	}

function eliminar2(cod){
	
	document.getElementById("op").value=cod;
	
		if(confirm){
			document.form1.submit();}
		}
		
function modificar2(cod,id_serv,serv,id_tipo,tipo,id_subtipo,sub_tipo,idplan,plan,id_tri,tri,ano,cantidad){
	window.open("vista_modificar.php?cod="+cod+"&id_serv="+id_serv+"&serv="+serv+"&id_tipo="+id_tipo+"&tipo="+tipo+"&id_subtipo="+id_subtipo+"&sub_tipo="+sub_tipo+"&idplan="+idplan+"&plan="+plan+"&id_trimestre="+id_tri+"&tri="+tri+"&ano="+ano+"&cantidad="+cantidad+"","",'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=0, resizable=no, width=630, height=440, top=180, left=380');
				}
				
				
function modificar_proyecto(idprop,arc,idmac_proy,mac_proy,idarea,area,iduni,uni,idlinea,linea_d,
							idpriori,priori,idf_pago,f_pago,desc,imp_com,imp_pol,imp_soc){
								
	window.open("modificar_proyecto.php?idprop="+idprop+"&arc="+arc+"&idmac_proy="+idmac_proy+"&mac_proy="+mac_proy+"&idarea="+idarea+"&area="+area+"&iduni="+iduni+"&uni="+uni+"&idlinea="+idlinea+"&linea_d="+linea_d+"&idpriori="+idpriori+"&priori="+priori+"&idf_pago="+idf_pago+"&f_pago="+f_pago+"&desc="+desc+"&imp_com="+imp_com+"&imp_pol="+imp_pol+"&imp_soc="+imp_soc,"",'toolbar=no,location=no, directories=no, status=no, menubar=no, scrollbars=0,resizable=no, width=990, height=610, top=40, left=200');
	
	}

	
function obtener_linea_propuesta(){
		alert(document.getElementById("exp").rows.length);
		}
		
function eliminar_capa(){
		i=document.getElementById("iter").value;
		if(i>2){
		document.getElementById("exp").removeChild(document.getElementById("exp").lastChild);
		document.getElementById("iter").value--;
		};
		}
	

function crear_capa(){
	i=document.getElementById("iter").value;
	combo_text=new Array();
	combo_val=new Array();
	
	for(j=0;j <document.getElementById("linea_de_negocio1").length;j++){
	combo_text[j]=document.getElementById("linea_de_negocio1").options[j].text;
	combo_val[j]=document.getElementById("linea_de_negocio1").options[j].value;
	
	}
	//Creacion Filla
		var tr=document.createElement("tr");
	
	//Creacion Columna
		var td1=document.createElement("td");
		var td2=document.createElement("td");
		var td3=document.createElement("td");
		var td4=document.createElement("td");
		var td5=document.createElement("td");
		var td6=document.createElement("td");
		
	
	//Creacion Input
		var input1=document.createElement("input");
		var select2=document.createElement("select");
		var input3=document.createElement("input");
		var input4=document.createElement("input");
		var input5=document.createElement("input");
		var input6=document.createElement("input");
		
		
			input1.setAttribute("name", "");
			input1.setAttribute("size", "0");
			input1.setAttribute("hidden", "0");
			
			select2.setAttribute("name", "linea_de_negocio" + i);
			select2.setAttribute("id", "linea_de_negocio" + i);	
			select2.setAttribute("size", "1");
			for(k=0;k <combo_text.length;k++){
			select2.options.add(new Option(combo_text[k],combo_val[k]));
			}
	//options.add(new Option("Seleccione"))
			input3.setAttribute("name", "" + i);	
			input3.setAttribute("size", "");
			input3.setAttribute("hidden", "0");
			
			input4.setAttribute("name", "costo_venta" + i);	
			input4.setAttribute("id", "costo_venta" + i);
			input4.setAttribute("size", "20");
			
			input5.setAttribute("name", "" + i);	
			input5.setAttribute("size", "");
			input5.setAttribute("hidden", "0");
			
			input6.setAttribute("name", "ingresos_unico_anual" + i);
			input6.setAttribute("id", "ingresos_unico_anual" + i);	
			input6.setAttribute("size", "20");
			
	
	//Agregacion Input Al TD
		td1.appendChild(input1); 
		td2.appendChild(select2);
		td3.appendChild(input3);
		td4.appendChild(input4);
		td5.appendChild(input5);
		td6.appendChild(input6);
	//Agregacion TD Al TR
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);
		tr.appendChild(td5);
		tr.appendChild(td6);
	    tr.setAttribute("id",i);
	//Agregacion TR A La Tabla
		document.getElementById("exp").appendChild(tr);
		
		document.getElementById("iter").value++;
		
	}
	
	
	function obtener_localidades(cod){
		var c = document.getElementById("instituciones").value;
		
	$.ajax({
		type: "GET",
		url: "../../driver/obtener_localidades.php?",
		data: 'codf='+c,
		success: function(response){
			
			
			var JSON=response;
			var obj=jQuery.parseJSON(JSON);	
			
			document.getElementById("localidad").options.length=1;
			
			if(obj.length>0){
				document.getElementById("locali").hidden=false;
				document.getElementById("resd1").hidden=false;
				document.getElementById("resd2").hidden=true;
				document.getElementById("munn").hidden=false;
				document.getElementById("mun").hidden=true;
				document.getElementById("centt").hidden=false;
				document.getElementById("cent").hidden=true;
				document.getElementById("modificar").hidden=true;
				document.getElementById("nombre_sede").disabled=false;
				document.getElementById("nombre_sede").value="";
				document.getElementById("direccion").disabled=false;
				document.getElementById("direccion").value="";
				document.getElementById("observaciones").disabled=false;
				document.getElementById("observaciones").value="";
				document.getElementById("cccc").value="";
				document.getElementById("servicios_datos").value="";
				document.getElementById("servicios_equipamento").value="";
				document.getElementById("servicios_internet").value="";
				document.getElementById("servicios_moviles").value="";
				document.getElementById("servicios_telf_fija").value="";
				document.getElementById("servicios_ti").value="";
				document.getElementById("registrar").value="Registrar";
				document.getElementById("validar").value="Registrar";
				
				}
			else
			{
				document.getElementById("locali").hidden=true;
				document.getElementById("resd1").hidden=false;
				document.getElementById("resd2").hidden=true;
				document.getElementById("munn").hidden=false;
				document.getElementById("mun").hidden=true;
				document.getElementById("centt").hidden=false;
				document.getElementById("cent").hidden=true;
				document.getElementById("modificar").hidden=true;
				document.getElementById("nombre_sede").disabled=false;
				document.getElementById("nombre_sede").value="";
				document.getElementById("direccion").disabled=false;
				document.getElementById("direccion").value="";
				document.getElementById("observaciones").disabled=false;
				document.getElementById("observaciones").value="";
				document.getElementById("cccc").value="";
				document.getElementById("servicios_datos").value="";
				document.getElementById("servicios_equipamento").value="";
				document.getElementById("servicios_internet").value="";
				document.getElementById("servicios_moviles").value="";
				document.getElementById("servicios_telf_fija").value="";
				document.getElementById("servicios_ti").value="";
				document.getElementById("registrar").value="Registrar";
				document.getElementById("validar").value="Registrar";
				
				}
			
			for(i=0 ; i<obj.length ; i++){
				document.getElementById("localidad").options.add(new Option(obj[i].nombre_sede,obj[i].id_sucursales));
				};
				
				
			if(cod!=""){
				for(h=0 ; h<document.getElementById("localidad").length ; h++)
							{
								var n=document.getElementById("localidad").options[h].value;
								if(n==cod){
									document.getElementById("localidad").options[h].selected=true;
									mostrar_localidad();
									}
								
							};
				}
			
		},
		error: function(response){
			alert("Problemas con el Servidor");
			
		},
		
		async: true,
	});
	
	}
	
	
	
	function mostrar_localidad(){
		var c = document.getElementById("localidad").value;
		if (c=='0'){
			
			document.getElementById("resd1").hidden=false;
			document.getElementById("resd2").hidden=true;
			document.getElementById("munn").hidden=false;
			document.getElementById("mun").hidden=true;
			document.getElementById("centt").hidden=false;
			document.getElementById("cent").hidden=true;
			document.getElementById("modificar").hidden=true;
			document.getElementById("nombre_sede").disabled=false;
			document.getElementById("nombre_sede").value="";
			document.getElementById("direccion").disabled=false;
			document.getElementById("direccion").value="";
			document.getElementById("observaciones").disabled=false;
			document.getElementById("observaciones").value="";
			document.getElementById("cccc").value="";
			document.getElementById("servicios_datos").value="";
			document.getElementById("servicios_equipamento").value="";
			document.getElementById("servicios_internet").value="";
			document.getElementById("servicios_moviles").value="";
			document.getElementById("servicios_telf_fija").value="";
			document.getElementById("servicios_ti").value="";
			document.getElementById("registrar").value="Registrar";
			document.getElementById("validar").value="Registrar";
			
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
			document.getElementById("modificar").hidden=false;
			
			document.getElementById("nombre_sede").disabled=true;
			document.getElementById("nombre_sede").value=obj[0].nombre_sede;
			document.getElementById("reg2").options.length=0;
			document.getElementById("est2").options.length=0;
			document.getElementById("mun2").options.length=0;
			document.getElementById("cent2").options.length=0;
			document.getElementById("reg2").options.add(new Option(obj[0].region,obj[0].idregion));
			document.getElementById("est2").options.add(new Option(obj[0].estado,obj[0].idestado));
			document.getElementById("mun2").options.add(new Option(obj[0].municipio,obj[0].idmunicipio));
			document.getElementById("cent2").options.add(new Option(obj[0].central,obj[0].idcentral));
			document.getElementById("cccc").value=obj[0].idcentral;
			document.getElementById("direccion").value=obj[0].direccion;
			document.getElementById("servicios_datos").value=obj[0].serv_datos;
			document.getElementById("servicios_equipamento").value=obj[0].serv_equip;
			document.getElementById("servicios_internet").value=obj[0].serv_internet;
			document.getElementById("servicios_moviles").value=obj[0].serv_moviles;
			document.getElementById("servicios_telf_fija").value=obj[0].serv_telf;
			document.getElementById("servicios_ti").value=obj[0].serv_ti;
			document.getElementById("observaciones").value=obj[0].observaciones;
			document.getElementById("direccion").disabled="true";
			document.getElementById("observaciones").disabled="true";
			
			document.getElementById("registrar").value="Actualizar";
			document.getElementById("validar").value="Actualizar";
			
			
			
			
		},
		
		error: function(response){
			alert("Problemas con el Servidor");
			
		},
		
		async: true,
	});
	
			};
	}
	
	function modificar_dataip(){
		
		
			document.getElementById("resd1").hidden=false;
			document.getElementById("resd2").hidden=true;
			document.getElementById("munn").hidden=false;
			document.getElementById("mun").hidden=true;
			document.getElementById("centt").hidden=false;
			document.getElementById("cent").hidden=true;
			document.getElementById("modificar").hidden=true;
			document.getElementById("nombre_sede").disabled=false;
			document.getElementById("direccion").disabled=false;
			document.getElementById("observaciones").disabled=false;
			document.getElementById("cccc").value="";
			document.getElementById("validar").value="Guardar";
		
		}
		
	function montar_costos(id){
		
		$.ajax({
		type: "GET",
		url: "../../driver/mostrar_costos.php?",
		data: 'codf='+id,
		success: function(response){
			var JSON=response;
			var obj=jQuery.parseJSON(JSON);
			
			for(i=0 ; i<document.getElementById("exp").rows.length ; i++)
			{
				h=i+1;
				
				var g=document.getElementById("linea_de_negocio"+h).value;
				
				for(j=0 ; j<obj.length ; j++){ 
				   
					if(obj[j].idlinea==g){
						document.getElementById("costo_venta"+h).value=obj[j].costo;
						document.getElementById("ingresos_unico_anual"+h).value=obj[j].ingreso;
						}
					}
				
			}
			
		},
		
		error: function(response){
			alert("Problemas con el Servidor");
			
		},
		
		async: true,
	});
	
			};
	
	