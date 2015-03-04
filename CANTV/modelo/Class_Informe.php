<?php


class Class_Informe{
	
	
	function combo_gerencia(){
		$sql = "select * from gerencia";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)){
				$html = "<option value='$da[idgerencia]'>$da[nombre_gerencia]";
			echo $html;
			}
		}
	
	
	function ingresos_ini_pro(){
		
		//Ingresos iniciativas y proyectos.
		
			$sql = "select macro_linea_propuesta.idpropuesta, SUM(macro_linea_propuesta.ingreso_unico_anual) 
			as suma_ingresos from propuesta p, macro_linea_propuesta, usuario u where p.idstatus_proyecto=1 and 
			p.idpropuesta=macro_linea_propuesta.idpropuesta and p.idusuario=u.idcedula and u.idgerencia=$_GET[codg]
			 and p.idtipo_fase_actualizada<12";
							
			$a = mysql_query($sql) or die(mysql_error());
			$da = mysql_fetch_assoc($a);
			
			$sql2 = "select p.idpropuesta, SUM(p.ingreso_real) as suma_ingresos_proyecto
						from propuesta p, usuario u
							where p.idstatus_proyecto=1 and p.idusuario=u.idcedula and u.idgerencia=$_GET[codg]
							 and p.idtipo_fase_actualizada>=12";
							
			$a2 = mysql_query($sql2) or die(mysql_error());
			$da2 = mysql_fetch_assoc($a2);
			
			if($da2['suma_ingresos_proyecto']=="" && $da['suma_ingresos']==""){
				
				$arre[0]=array("ing_tot_ini"=>0,"ing_tot_pro"=>0);
				
				}
			else{
				if($da2['suma_ingresos_proyecto']==""  && $da['suma_ingresos']!=""){
			
					$arre[0]=array("ing_tot_ini"=>round($da['suma_ingresos']),"ing_tot_pro"=>0);
					}
				else{
					if($da2['suma_ingresos_proyecto']!=""  && $da['suma_ingresos']==""){
			
					$arre[0]=array("ing_tot_ini"=>0,"ing_tot_pro"=>round($da2['suma_ingresos_proyecto']));
					}
					else{
						$arre[0]=array("ing_tot_ini"=>round($da['suma_ingresos']),"ing_tot_pro"=>round($da2['suma_ingresos_proyecto']));
						
						}
					}
			}
			
				//Tasa Exito
			$cont_aceptacion=0; $cont_entrega=0; $total_tasa=0; 
			$sql3 = "SELECT idpropuesta, idtipo_fase_actualizada, fecha_entrega, fecha_aceptacion 
					FROM propuesta, usuario
					WHERE idstatus_proyecto=1
						and propuesta.idusuario=usuario.idcedula 
						and usuario.idgerencia=$_GET[codg]";	
						$tasa_e= array();
						$sub_fase=array();
						$fec_acep=array();
			$b = mysql_query($sql3) or die(mysql_error());
				while ($db = mysql_fetch_assoc($b)){
					$tasa_e[]=$db["idpropuesta"];
					$sub_fase[]=$db["idtipo_fase_actualizada"];
					//$fec_ent[]=$db2["fecha_entrega"];
					$fec_acep[]=$db["fecha_aceptacion"];
					}
				$con=count($tasa_e);
				for ($i=0; $i<$con;){
						if($sub_fase[$i] < '12'){
							$cont_entrega = $cont_entrega + 1;
						}
						if($sub_fase[$i] >= '12' && $fec_acep[$i]!=''){
							$cont_aceptacion = $cont_aceptacion + 1;
						}
						$i++;
					}
			if($cont_aceptacion==0 && $cont_entrega==0){
				$total_tasa=0;
				}
			else{
			$total_tasa = ((($cont_aceptacion)*100) / ($cont_entrega+$cont_aceptacion));
			}
			
			$arre[1]=array("tasa_exito"=>round($total_tasa)."%" );
				
			//Calculo Importancias
			$imp_social=0; $imp_soc_si=0; $imp_soc_no=0;
			$imp_politica=0; $imp_poli_si=0; $imp_poli_no=0;
			$imp_comercial=0; $imp_com_si=0; $imp_com_no=0;
			
			$sql4 = "SELECT idpropuesta, importancia_social, importancia_politica, importancia_comercial 
					FROM propuesta, usuario
					WHERE idstatus_proyecto=1
						and propuesta.idusuario=usuario.idcedula 
						and usuario.idgerencia=$_GET[codg]";
						$import=array();
						$i_comercial=array();
						$i_politica=array();
						$i_social=array();
			$c = mysql_query($sql4) or die(mysql_error());
				while ($dc = mysql_fetch_assoc($c)){
					$import[]=$dc["idpropuesta"];
					$i_social[]=$dc["importancia_social"];
					$i_politica[]=$dc["importancia_politica"];
					$i_comercial[]=$dc["importancia_comercial"];
					}
			$con=count($import);
				for ($i=0; $i<$con;){
				if ($i_social[$i]!='No'){
					$imp_soc_si = $imp_soc_si + 1;
					}
				else{
					$imp_soc_no = $imp_soc_no + 1;
					}
					
				if ($i_politica[$i]!='No'){
					$imp_poli_si = $imp_poli_si + 1;
					}
				else {
					$imp_poli_no = $imp_poli_no + 1;
					}
					
				if ($i_comercial[$i]!='No'){
					$imp_com_si = $imp_com_si + 1;
					}
				else{
					$imp_com_no = $imp_com_no + 1;
					}
						$i++;					
				}
				
				$arre[2]=array("imp_soc_si"=>$imp_soc_si, "imp_soc_no"=>$imp_soc_no,
								"imp_poli_si"=>$imp_poli_si, "imp_poli_no"=>$imp_poli_no,
								"imp_com_si"=>$imp_com_si, "imp_com_no"=>$imp_com_no);
								
			//Cantidad Iniciativas
			$cont_iniciativa=0; $cont_proyecto=0; $totalip=0;
			$sql5 = "SELECT idpropuesta, idtipo_fase_actualizada, idstatus_proyecto 
					FROM propuesta, usuario
					WHERE idstatus_proyecto=1 
						and propuesta.idusuario=usuario.idcedula 
						and usuario.idgerencia=$_GET[codg]";	
					$ini_pro=array();
			$d = mysql_query($sql5) or die(mysql_error());
				while ($dd = mysql_fetch_assoc($d)){
					$ini_pro[]=$dd["idpropuesta"];
					}
			$con=count($ini_pro);
				for ($i=0; $i<$con;){
					$d2 = mysql_query($sql5) or die(mysql_error());
				while ($dd2 = mysql_fetch_assoc($d2)){						
					$sub_fase[]=$dd2["idtipo_fase_actualizada"];
					$status[]=$dd2["idstatus_proyecto"];
				if ($sub_fase[$i] < '12' && $status[$i]=='1'){
					$cont_iniciativa = $cont_iniciativa + 1;
					}
				if($sub_fase[$i] >= '12' && $status[$i]=='1'){
					$cont_proyecto = $cont_proyecto + 1;
					}
					$i++;
				}
			
			}
			
			$totalip = $cont_iniciativa + $cont_proyecto;
			$arre[3]=array("cont_iniciativa"=>$cont_iniciativa, "cont_proyecto"=>$cont_proyecto,
							"totalip"=>$totalip);					
			
			$ja=json_encode($arre);
			 echo $ja;
			
	}
	
	
	function cargar_iniciativas(){
				
		//iniciativas por Area.
		
			$alimentacion=0; $educacion=0; $gobierno=0; $salud=0; $seguridad=0;$totala=0;$alimentacionp=0;$educacionp=0;
			$gobiernop=0;$saludp=0;$seguridadp=0;
			$sql = "select idarea from propuesta p, usuario u where p.idstatus_proyecto=1 and p.idusuario=u.idcedula
			 and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada<12";	
			
			$area= array();
			
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$area[]=$da["idarea"];
					}
			$con=count($area);
			
				for ($i=0;$i<$con;){
					$aux=$area[$i];
					
			$sql="select nombre_area from area where idarea='$aux'";
				$b = mysql_query($sql) or die(mysql_error());
					while ($db = mysql_fetch_assoc($b)){
						$nombre[]=$db["nombre_area"];
					if ($nombre[$i]=='Alimentacion'){
						$alimentacion = $alimentacion + 1;
						}
					else if ($nombre[$i]=='Educacion'){
						$educacion = $educacion + 1;
						}
					else if ($nombre[$i]=='Gobierno'){
						$gobierno = $gobierno + 1;
						}
					else if ($nombre[$i]=='Salud'){
						$salud = $salud + 1;
						}
					else if ($nombre[$i]=='Seguridad'){
						$seguridad = $seguridad + 1;
						}
						$i++;
					}
				$totala = $alimentacion + $educacion + $gobierno + $salud + $seguridad;
				$alimentacionp=round(($alimentacion/$totala)*100);
				$educacionp=round(($educacion/$totala)*100);
				$gobiernop=round(($gobierno/$totala)*100);
				$saludp=round(($salud/$totala)*100);
				$seguridadp=round(($seguridad/$totala)*100);
				}
			
			$arre[0]=array("ali"=>$alimentacion,"porcen_ali"=>$alimentacionp."%","edu"=>$educacion,"porcen_edu"=>$educacionp."%",
			"gob"=>$gobierno,"porcen_gob"=>$gobiernop."%","salud"=>$salud,"porcen_salud"=>$saludp."%","segu"=>$seguridad,
			"porcen_segu"=>$seguridadp."%");
			
			
			//inicitaivas por Region.
			
			
			$andes=0; $capital=0; $central=0; $centro_occidente=0; $occidente=0; $oriente=0; $andesp=0; $capitalp=0; $centralp=0;
			$centro_occidentep=0; $occidentep=0; $orientep=0; $totalre=0;
			
			$sql = "select p.idregion from propuesta p, usuario u where p.idstatus_proyecto=1 and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada<12";	
			
			$region=array();
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$region[]=$da["idregion"];
					}
			$con=count($region);
			
				for ($i=0; $i<$con;){
					$aux=$region[$i];
					
					
			$sql="select nombre_region from region where idregion='$aux'";
				$b = mysql_query($sql) or die(mysql_error());
					while ($db = mysql_fetch_assoc($b)){
						$nombre2[]=$db["nombre_region"];
						
					if ($nombre2[$i]=='Andes'){
						$andes = $andes + 1;
						}
					else if ($nombre2[$i]=='Capital'){
						$capital = $capital + 1;
						}
					else if ($nombre2[$i]=='Central'){
						$central = $central + 1;
						}
					else if ($nombre2[$i]=='Centro Occidente'){
						$centro_occidente = $centro_occidente + 1;
						}
					else if ($nombre2[$i]=='Occidente'){
						$occidente = $occidente + 1;
						}
					else if ($nombre2[$i]=='Oriente'){
						$oriente = $oriente + 1;
						}
						$i++;
					}
				$totalre = $andes + $capital + $central + $centro_occidente + $occidente + $oriente;
				
				$andesp=round(($andes/$totalre)*100);
				$capitalp=round(($capital/$totalre)*100);
				$centralp=round(($central/$totalre)*100);
				$centro_occidentep=round(($centro_occidente/$totalre)*100);
				$occidentep=round(($occidente/$totalre)*100);
				$orientep=round(($oriente/$totalre)*100);
				}
				
			
			$arre[1]=array("and"=>$andes,"porcen_and"=>$andesp."%","cap"=>$capital,"porcen_cap"=>$capitalp."%","cen"=>$central,
			"porcen_cen"=>$centralp."%","centro_o"=>$centro_occidente,"porcen_centro_o"=>$centro_occidentep."%",
			"occi"=>$occidente,"porcen_occi"=>$occidentep."%","ori"=>$oriente,"porcen_ori"=>$orientep."%");
		
			
			
			
			//Iniciativas lineas de negocio
			
			
			
			$cableado_estructurado=0; $datos=0; $movilnet=0; $servicios_ti=0; $telco=0; $equipamiento=0; $ing_tot_cab=0; $ing_tot_dat=0; $ing_tot_mov=0; $ing_tot_ser=0; $ing_tot_tel=0; $ing_tot_equ=0; $cableado_estructuradop=0; $datosp=0; $movilnetp=0; $servicios_tip=0; $telcop=0; $equipamientop=0; $totallin=0; $totalli=0; $ing_tot_cabp=0; $ing_tot_datp=0; $ing_tot_movp=0; $ing_tot_serp=0; $ing_tot_telp=0; $ing_tot_equp=0;
			
			$sql = "select p.idpropuesta, macro_linea_propuesta.idlinea_de_negocio,macro_linea_propuesta.ingreso_unico_anual 
				from macro_linea_propuesta, propuesta p, usuario u
					where p.idstatus_proyecto=1 and p.idpropuesta=macro_linea_propuesta.idpropuesta and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada<12";
				
			$a = mysql_query($sql) or die(mysql_error());
			
			$linea_negocio=array();
			
				while ($da = mysql_fetch_assoc($a)){
					$linea_negocio[]=$da["idlinea_de_negocio"];
					$ingreso_li[]=$da["ingreso_unico_anual"];
					}
			$con=count($linea_negocio);
				for ($i=0; $i<$con;){
					$aux=$linea_negocio[$i];
					$sql="select nombre_linea_de_negocio from linea_de_negocio where idlinea_de_negocio='$aux'";
					$b = mysql_query($sql) or die(mysql_error());
						while ($db = mysql_fetch_assoc($b)){
							$nombre3[]=$db["nombre_linea_de_negocio"];
						if ($nombre3[$i]=='Cableado Estructurado'){
							$cableado_estructurado = $cableado_estructurado + 1;
							$ing_tot_cab = $ing_tot_cab + $ingreso_li[$i];
							}
						else if ($nombre3[$i]=='Datos'){
							$datos = $datos + 1;
							$ing_tot_dat = $ing_tot_dat + $ingreso_li[$i];
							}
						else if ($nombre3[$i]=='Movilnet'){
							$movilnet = $movilnet + 1;
							$ing_tot_mov = $ing_tot_mov + $ingreso_li[$i];
							}
						else if ($nombre3[$i]=='Servicios TI'){
							$servicios_ti = $servicios_ti + 1;
							$ing_tot_ser = $ing_tot_ser + $ingreso_li[$i];
							}
						else if ($nombre3[$i]=='Telco'){
							$telco = $telco + 1;
							$ing_tot_tel = $ing_tot_tel + $ingreso_li[$i];
							}
						else if ($nombre3[$i]=='Equipamiento'){
							$equipamiento = $equipamiento + 1;
							$ing_tot_equ = $ing_tot_equ + $ingreso_li[$i];
							}
							$i++;
						}
					$totalli = $ing_tot_cab + $ing_tot_dat + $ing_tot_mov + $ing_tot_ser + $ing_tot_tel + $ing_tot_equ;
					$ing_tot_cabp=($ing_tot_cab/$totalli)*100;
					$ing_tot_datp=($ing_tot_dat/$totalli)*100;
					$ing_tot_movp=($ing_tot_mov/$totalli)*100;
					$ing_tot_serp=($ing_tot_ser/$totalli)*100;
					$ing_tot_telp=($ing_tot_tel/$totalli)*100;
					$ing_tot_equp=($ing_tot_equ/$totalli)*100;
					$ing_tot_equp=round($ing_tot_equp,2);
					$ing_tot_cabp=round($ing_tot_cabp,2);
					$ing_tot_datp=round($ing_tot_datp,2);
					$ing_tot_movp=round($ing_tot_movp,2);
					$ing_tot_serp=round($ing_tot_serp,2);
					$ing_tot_telp=round($ing_tot_telp,2);
					$totallin = $cableado_estructurado + $datos + $movilnet + $servicios_ti + $telco + $equipamiento;		
					$cableado_estructuradop=round(($cableado_estructurado/$totallin)*100);
					$datosp=round(($datos/$totallin)*100);
					$movilnetp=round(($movilnet/$totallin)*100);
					$servicios_tip=round(($servicios_ti/$totallin)*100);
					$telcop=round(($telco/$totallin)*100);
					$equipamientop=round(($equipamiento/$totallin)*100);
					}
			
			$arre[2]=array("totalingre"=>$totalli,"ing_cab"=>$ing_tot_cab,"porcen_cab"=>$ing_tot_cabp."%","ing_dat"=>$ing_tot_dat,
			"porcen_dat"=>$ing_tot_datp."%","ing_mov"=>$ing_tot_mov,"porcen_mov"=>$ing_tot_movp."%","ing_ser"=>$ing_tot_ser,
			"porcen_ser"=>$ing_tot_serp."%","ing_tel"=>$ing_tot_tel,"porcen_tel"=>$ing_tot_telp."%","ing_equ"=>$ing_tot_equ,
			"porcen_equ"=>$ing_tot_equp."%", "totalicant"=>$totallin, "cab"=>$cableado_estructurado,
			"porcen_cab_n"=>$cableado_estructuradop."%","dat"=>$datos,"porcen_dat_n"=>$datosp."%","mov"=>$movilnet,
			"porcen_mov_n"=>$movilnetp."%","serv"=>$servicios_ti,"porcen_serv_n"=>$servicios_tip."%","telco"=>$telco,
			"porcen_telco_n"=>$telcop."%","equ"=>$equipamiento,"porcen_equ_n"=>$equipamientop."%");
			
			
			//Iniciativas Formas de Pago
			
			$recurrente=0; $unico=0; $recurrentep=0; $unicop=0; $totalfp=0;
			$sql = "select p.idforma_de_pago from propuesta p, usuario u where idstatus_proyecto=1 and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada<12";	
			
			$forma_pago=array();
			
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$forma_pago[]=$da["idforma_de_pago"];
					}
			$con=count($forma_pago);
				for ($i=0; $i<$con;){
					$aux=$forma_pago[$i];
			$sql="select nombre_forma_de_pago from forma_de_pago where idforma_de_pago='$aux'";
				$b = mysql_query($sql) or die(mysql_error());
						while ($db = mysql_fetch_assoc($b)){
							$nombre4[]=$db["nombre_forma_de_pago"];
						if ($nombre4[$i]=='Recurrente'){
							$recurrente = $recurrente + 1;
							}
						else if ($nombre4[$i]=='Unico'){
							$unico = $unico + 1;
							}
						$i++;
						}
			$totalfp = $recurrente + $unico;
			$recurrentep=round(($recurrente/$totalfp)*100);
			$unicop=round(($unico/$totalfp)*100);
			}
			
			$arre[3]=array("totalfp"=>$totalfp,"recu"=>$recurrente,"porcen_rec"=>$recurrentep."%","uni"=>$unico,"porcen_uni"=>$unicop."%");
			
			
			//Iniciativas MacroProyecto
			
			
			$macro_proyecto=array();
		
			$alcaldias_digitales = 0; $gis_salud = 0; $modernizacion_del_estado=0; $red_del_saber=0; $seguridad_agro_alimentaria=0; $seguridad_ciudadana=0; $alcaldias_digitalesp=0; $gis_saludp=0;$datosp=0; $modernizacion_del_estadop=0; $red_del_saberp=0; $seguridad_agro_alimentariap=0; $seguridad_ciudadanap=0; $totalmp=0; $ing_tot_alc=0; $ing_tot_sal=0; $ing_tot_moder=0; $ing_tot_red=0; $ing_tot_sega=0; $ing_tot_segci=0; $ing_tot_alcp=0; $ing_tot_salp=0; $ing_tot_moderp=0; $ing_tot_redp=0; $ing_tot_segap=0; $ing_tot_segcip=0; $totalinma=0;
			$sql = "select p.idpropuesta, p.idmacro_proyecto,(select SUM(ml.ingreso_unico_anual) from
			 macro_linea_propuesta ml where p.idpropuesta=ml.idpropuesta and p.idstatus_proyecto=1 and 
			 p.idusuario=u.idcedula and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada<12) as suma_ingresos 
			 from propuesta p, usuario u	where p.idstatus_proyecto=1 and p.idusuario=u.idcedula and u.idgerencia=$_GET[codg]
			  and p.idtipo_fase_actualizada<12";	
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$macro_proyecto[]=$da["idmacro_proyecto"];
					$ingreso_ma[]=$da["suma_ingresos"];
					}
			$con=count($macro_proyecto);
				for ($i=0;$i<$con;){
					$aux=$macro_proyecto[$i];
					$sql="select nombre_macro_proyecto from macro_proyecto where idmacro_proyecto='$aux'";
					$b = mysql_query($sql) or die(mysql_error());
						while ($db = mysql_fetch_assoc($b)){
							$nombre5[]=$db["nombre_macro_proyecto"];
						if ($nombre5[$i]=='Alcaldias Digitales'){
							$alcaldias_digitales = $alcaldias_digitales + 1;
							$ing_tot_alc = $ing_tot_alc + $ingreso_ma[$i];}
						else if($nombre5[$i]=='GIS(Salud)'){
							$gis_salud = $gis_salud + 1;
							$ing_tot_sal = $ing_tot_sal + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Modernizacion del Estado'){
							$modernizacion_del_estado = $modernizacion_del_estado + 1;
							$ing_tot_moder = $ing_tot_moder + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Red del Saber(Educacion)'){
							$red_del_saber = $red_del_saber + 1;
							$ing_tot_red = $ing_tot_red + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Seguridad Agro Alimentaria'){
							$seguridad_agro_alimentaria = $seguridad_agro_alimentaria + 1;
							$ing_tot_sega = $ing_tot_sega + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Seguridad Ciudadana'){
							$seguridad_ciudadana = $seguridad_ciudadana + 1;
							$ing_tot_segci = $ing_tot_segci + $ingreso_ma[$i];
							}
							$i++;
						}
				$totalinma = $ing_tot_alc + $ing_tot_sal + $ing_tot_moder + $ing_tot_red + $ing_tot_sega + $ing_tot_segci;
				$ing_tot_alcp=($ing_tot_alc/$totalinma)*100;
				$ing_tot_salp=($ing_tot_sal/$totalinma)*100;
				$ing_tot_moderp=($ing_tot_moder/$totalinma)*100;
				$ing_tot_redp=($ing_tot_red/$totalinma)*100;
				$ing_tot_segap=($ing_tot_sega/$totalinma)*100;
				$ing_tot_segcip=($ing_tot_segci/$totalinma)*100;
				$ing_tot_alcp= round($ing_tot_alcp,2);
				$ing_tot_salp=round($ing_tot_salp,2);
				$ing_tot_moderp=round($ing_tot_moderp,2);
				$ing_tot_redp=round($ing_tot_redp,2);
				$ing_tot_segap=round($ing_tot_segap,2);
				$ing_tot_segcip=round($ing_tot_segcip,2);
				$totalmp = $alcaldias_digitales + $gis_salud + $modernizacion_del_estado + 
				$red_del_saber + $seguridad_agro_alimentaria + $seguridad_ciudadana;
				$alcaldias_digitalesp=round(($alcaldias_digitales/$totalmp)*100);
				$gis_saludp=round(($gis_salud/$totalmp)*100);
				$modernizacion_del_estadop=round(($modernizacion_del_estado/$totalmp)*100);
				$red_del_saberp=round(($red_del_saber/$totalmp)*100);
				$seguridad_agro_alimentariap=round(($seguridad_agro_alimentaria/$totalmp)*100);
				$seguridad_ciudadanap=round(($seguridad_ciudadana/$totalmp)*100);
				}
			
			
			$arre[4]=array("totalingre"=>$totalinma,"ing_alc"=>$ing_tot_alc,"porcen_alc"=>$ing_tot_alcp."%",
			"ing_sal"=>$ing_tot_sal,"porcen_sal"=>$ing_tot_salp."%","ing_moder"=>$ing_tot_moder,"porcen_moder"=>$ing_tot_moderp."%",
			"ing_red"=>$ing_tot_red,"porcen_red"=>$ing_tot_redp."%","ing_sega"=>$ing_tot_sega,"porcen_sega"=>$ing_tot_segap."%",
			"ing_segci"=>$ing_tot_segci,"porcen_segci"=>$ing_tot_segcip."%","totalmp"=>$totalmp,"alc"=>$alcaldias_digitales,
			"porcen_alc_n"=>$alcaldias_digitalesp."%","sal"=>$gis_salud,"porcen_sal_n"=>$gis_saludp."%",
			"moder"=>$modernizacion_del_estado,"porcen_moder_n"=>$modernizacion_del_estadop."%","red"=>$red_del_saber,
			"porcen_red_n"=>$red_del_saberp."%","sega"=>$seguridad_agro_alimentaria,"porcen_sega_n"=>$seguridad_agro_alimentariap."%",
			"segci"=>$seguridad_ciudadana,"porcen_segci_n"=>$seguridad_ciudadanap."%",);
			
			$ja=json_encode($arre);
			echo $ja;
			
			
	}
	
	
	
	function cargar_proyectos(){
				
		//Proyectos por Area.
		
			$alimentacion=0; $educacion=0; $gobierno=0; $salud=0; $seguridad=0;$totala=0;$alimentacionp=0;$educacionp=0;
			$gobiernop=0;$saludp=0;$seguridadp=0;
			$sql = "select idarea from propuesta p, usuario u where p.idstatus_proyecto=1 and p.idusuario=u.idcedula
			 and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada>=12";	
			
			$area= array();
			
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$area[]=$da["idarea"];
					}
			$con=count($area);
			
				for ($i=0;$i<$con;){
					$aux=$area[$i];
					
			$sql="select nombre_area from area where idarea='$aux'";
				$b = mysql_query($sql) or die(mysql_error());
					while ($db = mysql_fetch_assoc($b)){
						$nombre[]=$db["nombre_area"];
					if ($nombre[$i]=='Alimentacion'){
						$alimentacion = $alimentacion + 1;
						}
					else if ($nombre[$i]=='Educacion'){
						$educacion = $educacion + 1;
						}
					else if ($nombre[$i]=='Gobierno'){
						$gobierno = $gobierno + 1;
						}
					else if ($nombre[$i]=='Salud'){
						$salud = $salud + 1;
						}
					else if ($nombre[$i]=='Seguridad'){
						$seguridad = $seguridad + 1;
						}
						$i++;
					}
				$totala = $alimentacion + $educacion + $gobierno + $salud + $seguridad;
				$alimentacionp=round(($alimentacion/$totala)*100);
				$educacionp=round(($educacion/$totala)*100);
				$gobiernop=round(($gobierno/$totala)*100);
				$saludp=round(($salud/$totala)*100);
				$seguridadp=round(($seguridad/$totala)*100);
				}
			
			$arre[0]=array("ali"=>$alimentacion,"porcen_ali"=>$alimentacionp."%","edu"=>$educacion,"porcen_edu"=>$educacionp."%",
			"gob"=>$gobierno,"porcen_gob"=>$gobiernop."%","salud"=>$salud,"porcen_salud"=>$saludp."%","segu"=>$seguridad,
			"porcen_segu"=>$seguridadp."%");
			
			
			//Proyectos por Region.
			
			
			$andes=0; $capital=0; $central=0; $centro_occidente=0; $occidente=0; $oriente=0; $andesp=0; $capitalp=0; $centralp=0;
			$centro_occidentep=0; $occidentep=0; $orientep=0; $totalre=0;
			
			$sql = "select p.idregion from propuesta p, usuario u where p.idstatus_proyecto=1 and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada>=12";	
			
			$region=array();
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$region[]=$da["idregion"];
					}
			$con=count($region);
			
				for ($i=0; $i<$con;){
					$aux=$region[$i];
					
					
			$sql="select nombre_region from region where idregion='$aux'";
				$b = mysql_query($sql) or die(mysql_error());
					while ($db = mysql_fetch_assoc($b)){
						$nombre2[]=$db["nombre_region"];
						
					if ($nombre2[$i]=='Andes'){
						$andes = $andes + 1;
						}
					else if ($nombre2[$i]=='Capital'){
						$capital = $capital + 1;
						}
					else if ($nombre2[$i]=='Central'){
						$central = $central + 1;
						}
					else if ($nombre2[$i]=='Centro Occidente'){
						$centro_occidente = $centro_occidente + 1;
						}
					else if ($nombre2[$i]=='Occidente'){
						$occidente = $occidente + 1;
						}
					else if ($nombre2[$i]=='Oriente'){
						$oriente = $oriente + 1;
						}
						$i++;
					}
				$totalre = $andes + $capital + $central + $centro_occidente + $occidente + $oriente;
				
				$andesp=round(($andes/$totalre)*100);
				$capitalp=round(($capital/$totalre)*100);
				$centralp=round(($central/$totalre)*100);
				$centro_occidentep=round(($centro_occidente/$totalre)*100);
				$occidentep=round(($occidente/$totalre)*100);
				$orientep=round(($oriente/$totalre)*100);
				}
				
			
			$arre[1]=array("and"=>$andes,"porcen_and"=>$andesp."%","cap"=>$capital,"porcen_cap"=>$capitalp."%","cen"=>$central,
			"porcen_cen"=>$centralp."%","centro_o"=>$centro_occidente,"porcen_centro_o"=>$centro_occidentep."%",
			"occi"=>$occidente,"porcen_occi"=>$occidentep."%","ori"=>$oriente,"porcen_ori"=>$orientep."%");
		
			
			
			
			//Proyectos lineas de negocio
			
			
			
			$cableado_estructurado=0; $datos=0; $movilnet=0; $servicios_ti=0; $telco=0; $equipamiento=0; $ing_tot_cab=0; $ing_tot_dat=0; $ing_tot_mov=0; $ing_tot_ser=0; $ing_tot_tel=0; $ing_tot_equ=0; $cableado_estructuradop=0; $datosp=0; $movilnetp=0; $servicios_tip=0; $telcop=0; $equipamientop=0; $totallin=0; $totalli=0; $ing_tot_cabp=0; $ing_tot_datp=0; $ing_tot_movp=0; $ing_tot_serp=0; $ing_tot_telp=0; $ing_tot_equp=0;
			
			$sql = "select p.idpropuesta, macro_linea_propuesta.idlinea_de_negocio,macro_linea_propuesta.ingreso_unico_anual 
				from macro_linea_propuesta, propuesta p, usuario u
					where p.idstatus_proyecto=1 and p.idpropuesta=macro_linea_propuesta.idpropuesta and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada>=12";
				
			$a = mysql_query($sql) or die(mysql_error());
			
			$linea_negocio=array();
			
				while ($da = mysql_fetch_assoc($a)){
					$linea_negocio[]=$da["idlinea_de_negocio"];
					$ingreso_li[]=$da["ingreso_unico_anual"];
					}
			$con=count($linea_negocio);
				for ($i=0; $i<$con;){
					$aux=$linea_negocio[$i];
					$sql="select nombre_linea_de_negocio from linea_de_negocio where idlinea_de_negocio='$aux'";
					$b = mysql_query($sql) or die(mysql_error());
						while ($db = mysql_fetch_assoc($b)){
							$nombre3[]=$db["nombre_linea_de_negocio"];
						if ($nombre3[$i]=='Cableado Estructurado'){
							$cableado_estructurado = $cableado_estructurado + 1;
							$ing_tot_cab = $ing_tot_cab + $ingreso_li[$i];
							}
						else if ($nombre3[$i]=='Datos'){
							$datos = $datos + 1;
							$ing_tot_dat = $ing_tot_dat + $ingreso_li[$i];
							}
						else if ($nombre3[$i]=='Movilnet'){
							$movilnet = $movilnet + 1;
							$ing_tot_mov = $ing_tot_mov + $ingreso_li[$i];
							}
						else if ($nombre3[$i]=='Servicios TI'){
							$servicios_ti = $servicios_ti + 1;
							$ing_tot_ser = $ing_tot_ser + $ingreso_li[$i];
							}
						else if ($nombre3[$i]=='Telco'){
							$telco = $telco + 1;
							$ing_tot_tel = $ing_tot_tel + $ingreso_li[$i];
							}
						else if ($nombre3[$i]=='Equipamiento'){
							$equipamiento = $equipamiento + 1;
							$ing_tot_equ = $ing_tot_equ + $ingreso_li[$i];
							}
							$i++;
						}
					$totalli = $ing_tot_cab + $ing_tot_dat + $ing_tot_mov + $ing_tot_ser + $ing_tot_tel + $ing_tot_equ;
					$ing_tot_cabp=($ing_tot_cab/$totalli)*100;
					$ing_tot_datp=($ing_tot_dat/$totalli)*100;
					$ing_tot_movp=($ing_tot_mov/$totalli)*100;
					$ing_tot_serp=($ing_tot_ser/$totalli)*100;
					$ing_tot_telp=($ing_tot_tel/$totalli)*100;
					$ing_tot_equp=($ing_tot_equ/$totalli)*100;
					$ing_tot_equp=round($ing_tot_equp,2);
					$ing_tot_cabp=round($ing_tot_cabp,2);
					$ing_tot_datp=round($ing_tot_datp,2);
					$ing_tot_movp=round($ing_tot_movp,2);
					$ing_tot_serp=round($ing_tot_serp,2);
					$ing_tot_telp=round($ing_tot_telp,2);
					$totallin = $cableado_estructurado + $datos + $movilnet + $servicios_ti + $telco + $equipamiento;		
					$cableado_estructuradop=round(($cableado_estructurado/$totallin)*100);
					$datosp=round(($datos/$totallin)*100);
					$movilnetp=round(($movilnet/$totallin)*100);
					$servicios_tip=round(($servicios_ti/$totallin)*100);
					$telcop=round(($telco/$totallin)*100);
					$equipamientop=round(($equipamiento/$totallin)*100);
					}
					
					
			$sql2 = "select SUM(p.ingreso_real) as ingresos_r 
				from propuesta p, usuario u	where p.idstatus_proyecto=1 and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada>=12";
				
			$a2 = mysql_query($sql2) or die(mysql_error());
			$db2 = mysql_fetch_assoc($a2);
			
			$ing_tot_cab=round(($db2['ingresos_r']*$ing_tot_cabp)/100);
			$ing_tot_dat=round(($db2['ingresos_r']*$ing_tot_datp)/100);
			$ing_tot_mov=round(($db2['ingresos_r']*$ing_tot_movp)/100);
			$ing_tot_ser=round(($db2['ingresos_r']*$ing_tot_serp)/100);
			$ing_tot_tel=round(($db2['ingresos_r']*$ing_tot_telp)/100);
			$ing_tot_equ=round(($db2['ingresos_r']*$ing_tot_equp)/100);
			$totalli = $ing_tot_cab + $ing_tot_dat + $ing_tot_mov + $ing_tot_ser + $ing_tot_tel + $ing_tot_equ;
					
					
			
			$arre[2]=array("totalingre"=>$totalli,"ing_cab"=>$ing_tot_cab,"porcen_cab"=>$ing_tot_cabp."%","ing_dat"=>$ing_tot_dat,
			"porcen_dat"=>$ing_tot_datp."%","ing_mov"=>$ing_tot_mov,"porcen_mov"=>$ing_tot_movp."%","ing_ser"=>$ing_tot_ser,
			"porcen_ser"=>$ing_tot_serp."%","ing_tel"=>$ing_tot_tel,"porcen_tel"=>$ing_tot_telp."%","ing_equ"=>$ing_tot_equ,
			"porcen_equ"=>$ing_tot_equp."%", "totalicant"=>$totallin, "cab"=>$cableado_estructurado,
			"porcen_cab_n"=>$cableado_estructuradop."%","dat"=>$datos,"porcen_dat_n"=>$datosp."%","mov"=>$movilnet,
			"porcen_mov_n"=>$movilnetp."%","serv"=>$servicios_ti,"porcen_serv_n"=>$servicios_tip."%","telco"=>$telco,
			"porcen_telco_n"=>$telcop."%","equ"=>$equipamiento,"porcen_equ_n"=>$equipamientop."%");
			
			
			//Proyectos Formas de Pago
			
			$recurrente=0; $unico=0; $recurrentep=0; $unicop=0; $totalfp=0;
			$sql = "select p.idforma_de_pago from propuesta p, usuario u where idstatus_proyecto=1 and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada>=12";	
			
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$forma_pago[]=$da["idforma_de_pago"];
					}
			$con=count($forma_pago);
				for ($i=0; $i<$con;){
					$aux=$forma_pago[$i];
			$sql="select nombre_forma_de_pago from forma_de_pago where idforma_de_pago='$aux'";
				$b = mysql_query($sql) or die(mysql_error());
						while ($db = mysql_fetch_assoc($b)){
							$nombre4[]=$db["nombre_forma_de_pago"];
						if ($nombre4[$i]=='Recurrente'){
							$recurrente = $recurrente + 1;
							}
						else if ($nombre4[$i]=='Unico'){
							$unico = $unico + 1;
							}
						$i++;
						}
			$totalfp = $recurrente + $unico;
			$recurrentep=round(($recurrente/$totalfp)*100);
			$unicop=round(($unico/$totalfp)*100);
			}
			
			$arre[3]=array("totalfp"=>$totalfp,"recu"=>$recurrente,"porcen_rec"=>$recurrentep."%","uni"=>$unico,"porcen_uni"=>$unicop."%");
			
			
			//Proyectos MacroProyecto
			
			
			$macro_proyecto=array();
		
			$alcaldias_digitales = 0; $gis_salud = 0; $modernizacion_del_estado=0; $red_del_saber=0; $seguridad_agro_alimentaria=0; $seguridad_ciudadana=0; $alcaldias_digitalesp=0; $gis_saludp=0;$datosp=0; $modernizacion_del_estadop=0; $red_del_saberp=0; $seguridad_agro_alimentariap=0; $seguridad_ciudadanap=0; $totalmp=0; $ing_tot_alc=0; $ing_tot_sal=0; $ing_tot_moder=0; $ing_tot_red=0; $ing_tot_sega=0; $ing_tot_segci=0; $ing_tot_alcp=0; $ing_tot_salp=0; $ing_tot_moderp=0; $ing_tot_redp=0; $ing_tot_segap=0; $ing_tot_segcip=0; $totalinma=0;
			$sql = "select p.idpropuesta, p.idmacro_proyecto, p.ingreso_real
			 from propuesta p, usuario u where p.idstatus_proyecto=1 and p.idusuario=u.idcedula and u.idgerencia=$_GET[codg]
			  and p.idtipo_fase_actualizada>=12";	
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$macro_proyecto[]=$da["idmacro_proyecto"];
					$ingreso_ma[]=$da["ingreso_real"];
					}
			$con=count($macro_proyecto);
				for ($i=0;$i<$con;){
					$aux=$macro_proyecto[$i];
					$sql="select nombre_macro_proyecto from macro_proyecto where idmacro_proyecto='$aux'";
					$b = mysql_query($sql) or die(mysql_error());
						while ($db = mysql_fetch_assoc($b)){
							$nombre5[]=$db["nombre_macro_proyecto"];
						if ($nombre5[$i]=='Alcaldias Digitales'){
							$alcaldias_digitales = $alcaldias_digitales + 1;
							$ing_tot_alc = $ing_tot_alc + $ingreso_ma[$i];}
						else if($nombre5[$i]=='GIS(Salud)'){
							$gis_salud = $gis_salud + 1;
							$ing_tot_sal = $ing_tot_sal + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Modernizacion del Estado'){
							$modernizacion_del_estado = $modernizacion_del_estado + 1;
							$ing_tot_moder = $ing_tot_moder + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Red del Saber(Educacion)'){
							$red_del_saber = $red_del_saber + 1;
							$ing_tot_red = $ing_tot_red + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Seguridad Agro Alimentaria'){
							$seguridad_agro_alimentaria = $seguridad_agro_alimentaria + 1;
							$ing_tot_sega = $ing_tot_sega + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Seguridad Ciudadana'){
							$seguridad_ciudadana = $seguridad_ciudadana + 1;
							$ing_tot_segci = $ing_tot_segci + $ingreso_ma[$i];
							}
							$i++;
						}
				$totalinma = $ing_tot_alc + $ing_tot_sal + $ing_tot_moder + $ing_tot_red + $ing_tot_sega + $ing_tot_segci;
				$ing_tot_alcp=($ing_tot_alc/$totalinma)*100;
				$ing_tot_salp=($ing_tot_sal/$totalinma)*100;
				$ing_tot_moderp=($ing_tot_moder/$totalinma)*100;
				$ing_tot_redp=($ing_tot_red/$totalinma)*100;
				$ing_tot_segap=($ing_tot_sega/$totalinma)*100;
				$ing_tot_segcip=($ing_tot_segci/$totalinma)*100;
				$ing_tot_alcp= round($ing_tot_alcp,2);
				$ing_tot_salp=round($ing_tot_salp,2);
				$ing_tot_moderp=round($ing_tot_moderp,2);
				$ing_tot_redp=round($ing_tot_redp,2);
				$ing_tot_segap=round($ing_tot_segap,2);
				$ing_tot_segcip=round($ing_tot_segcip,2);
				$totalmp = $alcaldias_digitales + $gis_salud + $modernizacion_del_estado + 
				$red_del_saber + $seguridad_agro_alimentaria + $seguridad_ciudadana;
				$alcaldias_digitalesp=round(($alcaldias_digitales/$totalmp)*100);
				$gis_saludp=round(($gis_salud/$totalmp)*100);
				$modernizacion_del_estadop=round(($modernizacion_del_estado/$totalmp)*100);
				$red_del_saberp=round(($red_del_saber/$totalmp)*100);
				$seguridad_agro_alimentariap=round(($seguridad_agro_alimentaria/$totalmp)*100);
				$seguridad_ciudadanap=round(($seguridad_ciudadana/$totalmp)*100);
				}
				
			$totalinma=round($totalinma); $ing_tot_alc=round($ing_tot_alc); $ing_tot_sal=round($ing_tot_sal);
			 $ing_tot_moder=round($ing_tot_moder); $ing_tot_red=round($ing_tot_red); $ing_tot_sega=round($ing_tot_sega);
			  $ing_tot_segci=round($ing_tot_segci);
				
			
			$arre[4]=array("totalingre"=>$totalinma,"ing_alc"=>$ing_tot_alc,"porcen_alc"=>$ing_tot_alcp."%",
			"ing_sal"=>$ing_tot_sal,"porcen_sal"=>$ing_tot_salp."%","ing_moder"=>$ing_tot_moder,"porcen_moder"=>$ing_tot_moderp."%",
			"ing_red"=>$ing_tot_red,"porcen_red"=>$ing_tot_redp."%","ing_sega"=>$ing_tot_sega,"porcen_sega"=>$ing_tot_segap."%",
			"ing_segci"=>$ing_tot_segci,"porcen_segci"=>$ing_tot_segcip."%","totalmp"=>$totalmp,"alc"=>$alcaldias_digitales,
			"porcen_alc_n"=>$alcaldias_digitalesp."%","sal"=>$gis_salud,"porcen_sal_n"=>$gis_saludp."%",
			"moder"=>$modernizacion_del_estado,"porcen_moder_n"=>$modernizacion_del_estadop."%","red"=>$red_del_saber,
			"porcen_red_n"=>$red_del_saberp."%","sega"=>$seguridad_agro_alimentaria,"porcen_sega_n"=>$seguridad_agro_alimentariap."%",
			"segci"=>$seguridad_ciudadana,"porcen_segci_n"=>$seguridad_ciudadanap."%",);
			
			$ja=json_encode($arre);
			 echo $ja;
			
			
	}
	
	
	function cargar_todo(){
		
		//Todos por Area.
		
			$alimentacion=0; $educacion=0; $gobierno=0; $salud=0; $seguridad=0;$totala=0;$alimentacionp=0;$educacionp=0;
			$gobiernop=0;$saludp=0;$seguridadp=0;
			$sql = "select idarea from propuesta p, usuario u where p.idstatus_proyecto=1 and p.idusuario=u.idcedula
			 and u.idgerencia=$_GET[codg]";	
			
			$area= array();
			
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$area[]=$da["idarea"];
					}
			$con=count($area);
			
				for ($i=0;$i<$con;){
					$aux=$area[$i];
					
			$sql="select nombre_area from area where idarea='$aux'";
				$b = mysql_query($sql) or die(mysql_error());
					while ($db = mysql_fetch_assoc($b)){
						$nombre[]=$db["nombre_area"];
					if ($nombre[$i]=='Alimentacion'){
						$alimentacion = $alimentacion + 1;
						}
					else if ($nombre[$i]=='Educacion'){
						$educacion = $educacion + 1;
						}
					else if ($nombre[$i]=='Gobierno'){
						$gobierno = $gobierno + 1;
						}
					else if ($nombre[$i]=='Salud'){
						$salud = $salud + 1;
						}
					else if ($nombre[$i]=='Seguridad'){
						$seguridad = $seguridad + 1;
						}
						$i++;
					}
				$totala = $alimentacion + $educacion + $gobierno + $salud + $seguridad;
				$alimentacionp=round(($alimentacion/$totala)*100);
				$educacionp=round(($educacion/$totala)*100);
				$gobiernop=round(($gobierno/$totala)*100);
				$saludp=round(($salud/$totala)*100);
				$seguridadp=round(($seguridad/$totala)*100);
				}
			
			$arre[0]=array("ali"=>$alimentacion,"porcen_ali"=>$alimentacionp."%","edu"=>$educacion,"porcen_edu"=>$educacionp."%",
			"gob"=>$gobierno,"porcen_gob"=>$gobiernop."%","salud"=>$salud,"porcen_salud"=>$saludp."%","segu"=>$seguridad,
			"porcen_segu"=>$seguridadp."%");
			
			
			//Todos por Region.
			
			
			$andes=0; $capital=0; $central=0; $centro_occidente=0; $occidente=0; $oriente=0; $andesp=0; $capitalp=0; $centralp=0;
			$centro_occidentep=0; $occidentep=0; $orientep=0; $totalre=0;
			
			$sql = "select p.idregion from propuesta p, usuario u where p.idstatus_proyecto=1 and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg]";	
			
			$region=array();
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$region[]=$da["idregion"];
					}
			$con=count($region);
			
				for ($i=0; $i<$con;){
					$aux=$region[$i];
					
					
			$sql="select nombre_region from region where idregion='$aux'";
				$b = mysql_query($sql) or die(mysql_error());
					while ($db = mysql_fetch_assoc($b)){
						$nombre2[]=$db["nombre_region"];
						
					if ($nombre2[$i]=='Andes'){
						$andes = $andes + 1;
						}
					else if ($nombre2[$i]=='Capital'){
						$capital = $capital + 1;
						}
					else if ($nombre2[$i]=='Central'){
						$central = $central + 1;
						}
					else if ($nombre2[$i]=='Centro Occidente'){
						$centro_occidente = $centro_occidente + 1;
						}
					else if ($nombre2[$i]=='Occidente'){
						$occidente = $occidente + 1;
						}
					else if ($nombre2[$i]=='Oriente'){
						$oriente = $oriente + 1;
						}
						$i++;
					}
				$totalre = $andes + $capital + $central + $centro_occidente + $occidente + $oriente;
				
				$andesp=round(($andes/$totalre)*100);
				$capitalp=round(($capital/$totalre)*100);
				$centralp=round(($central/$totalre)*100);
				$centro_occidentep=round(($centro_occidente/$totalre)*100);
				$occidentep=round(($occidente/$totalre)*100);
				$orientep=round(($oriente/$totalre)*100);
				}
				
			
			$arre[1]=array("and"=>$andes,"porcen_and"=>$andesp."%","cap"=>$capital,"porcen_cap"=>$capitalp."%","cen"=>$central,
			"porcen_cen"=>$centralp."%","centro_o"=>$centro_occidente,"porcen_centro_o"=>$centro_occidentep."%",
			"occi"=>$occidente,"porcen_occi"=>$occidentep."%","ori"=>$oriente,"porcen_ori"=>$orientep."%");
		
			
			
			
			//Todos lineas de negocio
			
		
			$cableado_estructurado=0; $datos=0; $movilnet=0; $servicios_ti=0; $telco=0; $equipamiento=0; $ing_tot_cab=0; $ing_tot_dat=0; $ing_tot_mov=0; $ing_tot_ser=0; $ing_tot_tel=0; $ing_tot_equ=0; $cableado_estructuradop=0; $datosp=0; $movilnetp=0; $servicios_tip=0; $telcop=0; $equipamientop=0; $totallin=0; $totalli=0; $ing_tot_cabp=0; $ing_tot_datp=0; $ing_tot_movp=0; $ing_tot_serp=0; $ing_tot_telp=0; $ing_tot_equp=0;
			
			$sql = "select p.idpropuesta, macro_linea_propuesta.idlinea_de_negocio,macro_linea_propuesta.ingreso_unico_anual 
				from macro_linea_propuesta, propuesta p, usuario u
					where p.idstatus_proyecto=1 and p.idpropuesta=macro_linea_propuesta.idpropuesta and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada>=12";
				
			$a = mysql_query($sql) or die(mysql_error());
			
			$linea_negocio=array();
			
				while ($da = mysql_fetch_assoc($a)){
					$linea_negocio[]=$da["idlinea_de_negocio"];
					$ingreso_li[]=$da["ingreso_unico_anual"];
					}
			$con=count($linea_negocio);
				for ($i=0; $i<$con;){
					$aux=$linea_negocio[$i];
					$sql="select nombre_linea_de_negocio from linea_de_negocio where idlinea_de_negocio='$aux'";
					$b = mysql_query($sql) or die(mysql_error());
						while ($db = mysql_fetch_assoc($b)){
							$nombre6[]=$db["nombre_linea_de_negocio"];
						if ($nombre6[$i]=='Cableado Estructurado'){
							$cableado_estructurado = $cableado_estructurado + 1;
							$ing_tot_cab = $ing_tot_cab + $ingreso_li[$i];
							}
						else if ($nombre6[$i]=='Datos'){
							$datos = $datos + 1;
							$ing_tot_dat = $ing_tot_dat + $ingreso_li[$i];
							}
						else if ($nombre6[$i]=='Movilnet'){
							$movilnet = $movilnet + 1;
							$ing_tot_mov = $ing_tot_mov + $ingreso_li[$i];
							}
						else if ($nombre6[$i]=='Servicios TI'){
							$servicios_ti = $servicios_ti + 1;
							$ing_tot_ser = $ing_tot_ser + $ingreso_li[$i];
							}
						else if ($nombre6[$i]=='Telco'){
							$telco = $telco + 1;
							$ing_tot_tel = $ing_tot_tel + $ingreso_li[$i];
							}
						else if ($nombre6[$i]=='Equipamiento'){
							$equipamiento = $equipamiento + 1;
							$ing_tot_equ = $ing_tot_equ + $ingreso_li[$i];
							}
							$i++;
						}
					$totalli = $ing_tot_cab + $ing_tot_dat + $ing_tot_mov + $ing_tot_ser + $ing_tot_tel + $ing_tot_equ;
					$ing_tot_cabp=($ing_tot_cab/$totalli)*100;
					$ing_tot_datp=($ing_tot_dat/$totalli)*100;
					$ing_tot_movp=($ing_tot_mov/$totalli)*100;
					$ing_tot_serp=($ing_tot_ser/$totalli)*100;
					$ing_tot_telp=($ing_tot_tel/$totalli)*100;
					$ing_tot_equp=($ing_tot_equ/$totalli)*100;
					$ing_tot_equp=round($ing_tot_equp,2);
					$ing_tot_cabp=round($ing_tot_cabp,2);
					$ing_tot_datp=round($ing_tot_datp,2);
					$ing_tot_movp=round($ing_tot_movp,2);
					$ing_tot_serp=round($ing_tot_serp,2);
					$ing_tot_telp=round($ing_tot_telp,2);
					}
					
					
			$sql2 = "select SUM(p.ingreso_real) as ingresos_r 
				from propuesta p, usuario u	where p.idstatus_proyecto=1 and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada>=12";
				
			$a2 = mysql_query($sql2) or die(mysql_error());
			$db2 = mysql_fetch_assoc($a2);
			
			$ing_tot_cab=round(($db2['ingresos_r']*$ing_tot_cabp)/100);
			$ing_tot_dat=round(($db2['ingresos_r']*$ing_tot_datp)/100);
			$ing_tot_mov=round(($db2['ingresos_r']*$ing_tot_movp)/100);
			$ing_tot_ser=round(($db2['ingresos_r']*$ing_tot_serp)/100);
			$ing_tot_tel=round(($db2['ingresos_r']*$ing_tot_telp)/100);
			$ing_tot_equ=round(($db2['ingresos_r']*$ing_tot_equp)/100);
			
			
			$cableado_estructurado2=0; $datos2=0; $movilnet2=0; $servicios_ti2=0; $telco2=0; $equipamiento2=0; $ing_tot_cab2=0; $ing_tot_dat2=0; $ing_tot_mov2=0; $ing_tot_ser2=0; $ing_tot_tel2=0; $ing_tot_equ2=0; $cableado_estructuradop2=0; $datosp2=0; $movilnetp2=0; $servicios_tip2=0; $telcop2=0; $equipamientop2=0; $totallin2=0; $totalli2=0; $ing_tot_cabp2=0; $ing_tot_datp2=0; $ing_tot_movp2=0; $ing_tot_serp2=0; $ing_tot_telp2=0; $ing_tot_equp2=0;
			
			$sql3 = "select p.idpropuesta, macro_linea_propuesta.idlinea_de_negocio,macro_linea_propuesta.ingreso_unico_anual 
				from macro_linea_propuesta, propuesta p, usuario u
					where p.idstatus_proyecto=1 and p.idpropuesta=macro_linea_propuesta.idpropuesta and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada<12";
				
				
			$a3 = mysql_query($sql3) or die(mysql_error());
			
			$linea_negocio2=array();
			
				while ($da3 = mysql_fetch_assoc($a3)){
					$linea_negocio2[]=$da3["idlinea_de_negocio"];
					$ingreso_li2[]=$da3["ingreso_unico_anual"];
					}
			$con2=count($linea_negocio2);
				for ($i=0; $i<$con2;){
					$aux2=$linea_negocio2[$i];
					$sql4="select nombre_linea_de_negocio from linea_de_negocio where idlinea_de_negocio='$aux2'";
					$b4 = mysql_query($sql4) or die(mysql_error());
						while ($db4 = mysql_fetch_assoc($b4)){
							$nombre7[]=$db4["nombre_linea_de_negocio"];
						if ($nombre7[$i]=='Cableado Estructurado'){
							$cableado_estructurado2 = $cableado_estructurado2 + 1;
							$ing_tot_cab2 = $ing_tot_cab2 + $ingreso_li2[$i];
							}
						else if ($nombre7[$i]=='Datos'){
							$datos2 = $datos2 + 1;
							$ing_tot_dat2 = $ing_tot_dat2 + $ingreso_li2[$i];
							}
						else if ($nombre7[$i]=='Movilnet'){
							$movilnet2 = $movilnet2 + 1;
							$ing_tot_mov2 = $ing_tot_mov2 + $ingreso_li2[$i];
							}
						else if ($nombre7[$i]=='Servicios TI'){
							$servicios_ti2 = $servicios_ti2 + 1;
							$ing_tot_ser2 = $ing_tot_ser2 + $ingreso_li2[$i];
							}
						else if ($nombre7[$i]=='Telco'){
							$telco2 = $telco2 + 1;
							$ing_tot_tel2 = $ing_tot_tel2 + $ingreso_li2[$i];
							}
						else if ($nombre7[$i]=='Equipamiento'){
							$equipamiento2 = $equipamiento2 + 1;
							$ing_tot_equ2 = $ing_tot_equ2 + $ingreso_li2[$i];
							}
							$i++;
						}
					
					
					
					$ing_tot_cab3=$ing_tot_cab2+$ing_tot_cab;
					$ing_tot_dat3=$ing_tot_dat2+$ing_tot_dat;
					$ing_tot_mov3=$ing_tot_mov2+$ing_tot_mov;
					$ing_tot_ser3=$ing_tot_ser2+$ing_tot_ser;
					$ing_tot_tel3=$ing_tot_tel2+$ing_tot_tel;
					$ing_tot_equ3=$ing_tot_equ2+$ing_tot_equ;
					
					
					$cableado_estructurado3=$cableado_estructurado+$cableado_estructurado2;
					$datos3=$datos+$datos2;
					$movilnet3=$movilnet+$movilnet2;
					$servicios_ti3=$servicios_ti+$servicios_ti2;
					$telco3=$telco+$telco2;
					$equipamiento3=$equipamiento+$equipamiento2;
					}
			
					$totalli3 = $ing_tot_cab + $ing_tot_dat + $ing_tot_mov + $ing_tot_ser + $ing_tot_tel + 
					$ing_tot_equ + $ing_tot_cab2 + $ing_tot_dat2 + $ing_tot_mov2 + $ing_tot_ser2 + $ing_tot_tel2 + $ing_tot_equ2;
					$ing_tot_cabp3=($ing_tot_cab3/$totalli3)*100;
					$ing_tot_datp3=($ing_tot_dat3/$totalli3)*100;;
					$ing_tot_movp3=($ing_tot_mov3/$totalli3)*100;
					$ing_tot_serp3=($ing_tot_ser3/$totalli3)*100;
					$ing_tot_telp3=($ing_tot_tel3/$totalli3)*100;
					$ing_tot_equp3=($ing_tot_equ3/$totalli3)*100;
					$ing_tot_equp3=round($ing_tot_equp3,2);
					$ing_tot_cabp3=round($ing_tot_cabp3,2);
					$ing_tot_datp3=round($ing_tot_datp3,2);
					$ing_tot_movp3=round($ing_tot_movp3,2);
					$ing_tot_serp3=round($ing_tot_serp3,2);
					$ing_tot_telp3=round($ing_tot_telp3,2);
					
					$totallin3 = $cableado_estructurado + $datos + $movilnet + $servicios_ti + $telco
					 + $equipamiento+ $cableado_estructurado2 + $datos2 + $movilnet2 + $servicios_ti2 + $telco2 + $equipamiento2;
					 
					 $cableado_estructuradop3=round(($cableado_estructurado3/$totallin3)*100);
					$datosp3=round(($datos3/$totallin3)*100);
					$movilnetp3=round(($movilnet3/$totallin3)*100);
					$servicios_tip3=round(($servicios_ti3/$totallin3)*100);
					$telcop3=round(($telco3/$totallin3)*100);
					$equipamientop3=round(($equipamiento3/$totallin3)*100);
					
			
			$arre[2]=array("totalingre"=>$totalli3,"ing_cab"=>$ing_tot_cab3,"porcen_cab"=>$ing_tot_cabp3."%","ing_dat"=>$ing_tot_dat3,
			"porcen_dat"=>$ing_tot_datp3."%","ing_mov"=>$ing_tot_mov3,"porcen_mov"=>$ing_tot_movp3."%","ing_ser"=>$ing_tot_ser3,
			"porcen_ser"=>$ing_tot_serp3."%","ing_tel"=>$ing_tot_tel3,"porcen_tel"=>$ing_tot_telp3."%","ing_equ"=>$ing_tot_equ3,
			"porcen_equ"=>$ing_tot_equp3."%", "totalicant"=>$totallin3, "cab"=>$cableado_estructurado3,
			"porcen_cab_n"=>$cableado_estructuradop3."%","dat"=>$datos3,"porcen_dat_n"=>$datosp3."%","mov"=>$movilnet3,
			"porcen_mov_n"=>$movilnetp3."%","serv"=>$servicios_ti3,"porcen_serv_n"=>$servicios_tip3."%","telco"=>$telco3,
			"porcen_telco_n"=>$telcop3."%","equ"=>$equipamiento3,"porcen_equ_n"=>$equipamientop3."%");
			
			
			//Todos Formas de Pago
			
			$recurrente=0; $unico=0; $recurrentep=0; $unicop=0; $totalfp=0;
			$sql = "select p.idforma_de_pago from propuesta p, usuario u where idstatus_proyecto=1 and p.idusuario=u.idcedula 
			and u.idgerencia=$_GET[codg]";	
			
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$forma_pago[]=$da["idforma_de_pago"];
					}
			$con=count($forma_pago);
				for ($i=0; $i<$con;){
					$aux=$forma_pago[$i];
			$sql="select nombre_forma_de_pago from forma_de_pago where idforma_de_pago='$aux'";
				$b = mysql_query($sql) or die(mysql_error());
						while ($db = mysql_fetch_assoc($b)){
							$nombre4[]=$db["nombre_forma_de_pago"];
						if ($nombre4[$i]=='Recurrente'){
							$recurrente = $recurrente + 1;
							}
						else if ($nombre4[$i]=='Unico'){
							$unico = $unico + 1;
							}
						$i++;
						}
			$totalfp = $recurrente + $unico;
			$recurrentep=round(($recurrente/$totalfp)*100);
			$unicop=round(($unico/$totalfp)*100);
			}
			
			$arre[3]=array("totalfp"=>$totalfp,"recu"=>$recurrente,"porcen_rec"=>$recurrentep."%","uni"=>$unico,"porcen_uni"=>$unicop."%");
			
			
			//Todos MacroProyecto
			
			$macro_proyecto=array();
		
			$alcaldias_digitales = 0; $gis_salud = 0; $modernizacion_del_estado=0; $red_del_saber=0; $seguridad_agro_alimentaria=0; $seguridad_ciudadana=0; $alcaldias_digitalesp=0; $gis_saludp=0;$datosp=0; $modernizacion_del_estadop=0; $red_del_saberp=0; $seguridad_agro_alimentariap=0; $seguridad_ciudadanap=0; $totalmp=0; $ing_tot_alc=0; $ing_tot_sal=0; $ing_tot_moder=0; $ing_tot_red=0; $ing_tot_sega=0; $ing_tot_segci=0; $ing_tot_alcp=0; $ing_tot_salp=0; $ing_tot_moderp=0; $ing_tot_redp=0; $ing_tot_segap=0; $ing_tot_segcip=0; $totalinma=0;
			$sql = "select p.idpropuesta, p.idmacro_proyecto,(select SUM(ml.ingreso_unico_anual) from
			 macro_linea_propuesta ml where p.idpropuesta=ml.idpropuesta and p.idstatus_proyecto=1 and 
			 p.idusuario=u.idcedula and u.idgerencia=$_GET[codg] and p.idtipo_fase_actualizada<12) as suma_ingresos 
			 from propuesta p, usuario u where p.idstatus_proyecto=1 and p.idusuario=u.idcedula and u.idgerencia=$_GET[codg]
			  and p.idtipo_fase_actualizada<12";
			  	
			$a = mysql_query($sql) or die(mysql_error());
				while ($da = mysql_fetch_assoc($a)){
					$macro_proyecto[]=$da["idmacro_proyecto"];
					$ingreso_ma[]=$da["suma_ingresos"];
					}
			$con=count($macro_proyecto);
				for ($i=0;$i<$con;){
					$aux=$macro_proyecto[$i];
					$sql="select nombre_macro_proyecto from macro_proyecto where idmacro_proyecto='$aux'";
					$b = mysql_query($sql) or die(mysql_error());
						while ($db = mysql_fetch_assoc($b)){
							$nombre5[]=$db["nombre_macro_proyecto"];
						if ($nombre5[$i]=='Alcaldias Digitales'){
							$alcaldias_digitales = $alcaldias_digitales + 1;
							$ing_tot_alc = $ing_tot_alc + $ingreso_ma[$i];}
						else if($nombre5[$i]=='GIS(Salud)'){
							$gis_salud = $gis_salud + 1;
							$ing_tot_sal = $ing_tot_sal + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Modernizacion del Estado'){
							$modernizacion_del_estado = $modernizacion_del_estado + 1;
							$ing_tot_moder = $ing_tot_moder + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Red del Saber(Educacion)'){
							$red_del_saber = $red_del_saber + 1;
							$ing_tot_red = $ing_tot_red + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Seguridad Agro Alimentaria'){
							$seguridad_agro_alimentaria = $seguridad_agro_alimentaria + 1;
							$ing_tot_sega = $ing_tot_sega + $ingreso_ma[$i];
							}
						else if($nombre5[$i]=='Seguridad Ciudadana'){
							$seguridad_ciudadana = $seguridad_ciudadana + 1;
							$ing_tot_segci = $ing_tot_segci + $ingreso_ma[$i];
							}
							$i++;
						}
				}
			
			
			$macro_proyecto2=array();
		
			$alcaldias_digitales2 = 0; $gis_salud2 = 0; $modernizacion_del_estado2=0; $red_del_saber2=0; $seguridad_agro_alimentaria2=0; $seguridad_ciudadana2=0; $alcaldias_digitalesp2=0; $gis_saludp2=0;$datosp2=0; $modernizacion_del_estadop2=0; $red_del_saberp2=0; $seguridad_agro_alimentariap2=0; $seguridad_ciudadanap2=0; $totalmp2=0; $ing_tot_alc2=0; $ing_tot_sal2=0; $ing_tot_moder2=0; $ing_tot_red2=0; $ing_tot_sega2=0; $ing_tot_segci2=0; $ing_tot_alcp2=0; $ing_tot_salp2=0; $ing_tot_moderp2=0; $ing_tot_redp2=0; $ing_tot_segap2=0; $ing_tot_segcip2=0; $totalinma2=0;
			$sql1 = "select p.idpropuesta, p.idmacro_proyecto, p.ingreso_real
			 from propuesta p, usuario u where p.idstatus_proyecto=1 and p.idusuario=u.idcedula and u.idgerencia=$_GET[codg]
			  and p.idtipo_fase_actualizada>=12";
			  	
			$a1 = mysql_query($sql1) or die(mysql_error());
				while ($da1 = mysql_fetch_assoc($a1)){
					$macro_proyecto2[]=$da1["idmacro_proyecto"];
					$ingreso_ma2[]=$da1["ingreso_real"];
					}
			$con1=count($macro_proyecto2);
				for ($i=0;$i<$con1;){
					$aux1=$macro_proyecto2[$i];
					$sql7="select nombre_macro_proyecto from macro_proyecto where idmacro_proyecto='$aux1'";
					$b1 = mysql_query($sql7) or die(mysql_error());
						while ($db1 = mysql_fetch_assoc($b1)){
							$nombre1[]=$db1["nombre_macro_proyecto"];
						if ($nombre1[$i]=='Alcaldias Digitales'){
							$alcaldias_digitales2 = $alcaldias_digitales2 + 1;
							$ing_tot_alc2 = $ing_tot_alc2 + $ingreso_ma2[$i];}
						else if($nombre1[$i]=='GIS(Salud)'){
							$gis_salud2 = $gis_salud2 + 1;
							$ing_tot_sal2 = $ing_tot_sal2 + $ingreso_ma2[$i];
							}
						else if($nombre1[$i]=='Modernizacion del Estado'){
							$modernizacion_del_estado2 = $modernizacion_del_estado2 + 1;
							$ing_tot_moder2 = $ing_tot_moder2 + $ingreso_ma2[$i];
							}
						else if($nombre1[$i]=='Red del Saber(Educacion)'){
							$red_del_saber2 = $red_del_saber2 + 1;
							$ing_tot_red2 = $ing_tot_red2 + $ingreso_ma2[$i];
							}
						else if($nombre1[$i]=='Seguridad Agro Alimentaria'){
							$seguridad_agro_alimentaria2 = $seguridad_agro_alimentaria2 + 1;
							$ing_tot_sega2 = $ing_tot_sega2 + $ingreso_ma2[$i];
							}
						else if($nombre1[$i]=='Seguridad Ciudadana'){
							$seguridad_ciudadana2 = $seguridad_ciudadana2 + 1;
							$ing_tot_segci2 = $ing_tot_segci2 + $ingreso_ma2[$i];
							}
							$i++;
						}
				$totalinma = $ing_tot_alc + $ing_tot_sal + $ing_tot_moder + $ing_tot_red + $ing_tot_sega + $ing_tot_segci+
				 $ing_tot_alc2 + $ing_tot_sal2 + $ing_tot_moder2+ $ing_tot_red2 + $ing_tot_sega2 + $ing_tot_segci2;
				 $ing_tot_alc3= $ing_tot_alc2+ $ing_tot_alc;
				 $ing_tot_sal3=$ing_tot_sal2+$ing_tot_sal;
				 $ing_tot_moder3=$ing_tot_moder2+$ing_tot_moder;
				 $ing_tot_red3= $ing_tot_red2+ $ing_tot_red;
				 $ing_tot_sega3= $ing_tot_sega2+ $ing_tot_sega;
				 $ing_tot_segci3=$ing_tot_segci2+$ing_tot_segci;
				$ing_tot_alcp=($ing_tot_alc3/$totalinma)*100;
				$ing_tot_salp=($ing_tot_sal3/$totalinma)*100;
				$ing_tot_moderp=($ing_tot_moder3/$totalinma)*100;
				$ing_tot_redp=($ing_tot_red3/$totalinma)*100;
				$ing_tot_segap=($ing_tot_sega3/$totalinma)*100;
				$ing_tot_segcip=($ing_tot_segci3/$totalinma)*100;
				$ing_tot_alcp= round($ing_tot_alcp,2);
				$ing_tot_salp=round($ing_tot_salp,2);
				$ing_tot_moderp=round($ing_tot_moderp,2);
				$ing_tot_redp=round($ing_tot_redp,2);
				$ing_tot_segap=round($ing_tot_segap,2);
				$ing_tot_segcip=round($ing_tot_segcip,2);
				$totalmp = $alcaldias_digitales + $gis_salud + $modernizacion_del_estado + 
				$red_del_saber + $seguridad_agro_alimentaria + $seguridad_ciudadana +$alcaldias_digitales2 + 
				$gis_salud2 + $modernizacion_del_estado2 + $red_del_saber2 + $seguridad_agro_alimentaria2 + $seguridad_ciudadana2;
				$alcaldias_digitales3=$alcaldias_digitales2+$alcaldias_digitales;
				$gis_salud3=$gis_salud2+$gis_salud;
				$modernizacion_del_estado3=$modernizacion_del_estado2+$modernizacion_del_estado;
				$red_del_saber3=$red_del_saber2+$red_del_saber;
				$seguridad_agro_alimentaria3=$seguridad_agro_alimentaria2+$seguridad_agro_alimentaria;
				$seguridad_ciudadana3=$seguridad_ciudadana2+$seguridad_ciudadana;
				$alcaldias_digitalesp=round(($alcaldias_digitales3/$totalmp)*100);
				$gis_saludp=round(($gis_salud3/$totalmp)*100);
				$modernizacion_del_estadop=round(($modernizacion_del_estado3/$totalmp)*100);
				$red_del_saberp=round(($red_del_saber3/$totalmp)*100);
				$seguridad_agro_alimentariap=round(($seguridad_agro_alimentaria3/$totalmp)*100);
				$seguridad_ciudadanap=round(($seguridad_ciudadana3/$totalmp)*100);
				}
				
			$totalinma=round($totalinma); $ing_tot_alc3=round($ing_tot_alc3); $ing_tot_sal3=round($ing_tot_sal3);
			 $ing_tot_moder3=round($ing_tot_moder3); $ing_tot_red3=round($ing_tot_red3); $ing_tot_sega3=round($ing_tot_sega3);
			  $ing_tot_segci3=round($ing_tot_segci3);
				
			
			$arre[4]=array("totalingre"=>$totalinma,"ing_alc"=>$ing_tot_alc3,"porcen_alc"=>$ing_tot_alcp."%",
			"ing_sal"=>$ing_tot_sal3,"porcen_sal"=>$ing_tot_salp."%","ing_moder"=>$ing_tot_moder3,"porcen_moder"=>$ing_tot_moderp."%",
			"ing_red"=>$ing_tot_red3,"porcen_red"=>$ing_tot_redp."%","ing_sega"=>$ing_tot_sega3,"porcen_sega"=>$ing_tot_segap."%",
			"ing_segci"=>$ing_tot_segci3,"porcen_segci"=>$ing_tot_segcip."%","totalmp"=>$totalmp,"alc"=>$alcaldias_digitales3,
			"porcen_alc_n"=>$alcaldias_digitalesp."%","sal"=>$gis_salud3,"porcen_sal_n"=>$gis_saludp."%",
			"moder"=>$modernizacion_del_estado3,"porcen_moder_n"=>$modernizacion_del_estadop."%","red"=>$red_del_saber3,
			"porcen_red_n"=>$red_del_saberp."%","sega"=>$seguridad_agro_alimentaria3,"porcen_sega_n"=>$seguridad_agro_alimentariap."%",
			"segci"=>$seguridad_ciudadana3,"porcen_segci_n"=>$seguridad_ciudadanap."%",);
			
			$ja=json_encode($arre);
			 echo $ja;
	
		}
	
		
	
		}
?>