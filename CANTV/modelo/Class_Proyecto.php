<?php
include 'Class_Alertas.php';
class Class_Proyecto{
	function Class_Proyecto(){
		}
	
	function registrar(){
			$sql = $sql = "insert into propuesta (codigo_arc,idmacro_proyecto, importancia_social, importancia_politica, importancia_comercial, fecha_solicitud, fecha_inicio_fase, fecha_actualizacion_nueva, fecha_consultor, descripcion_macro_proyecto, idprioridad, idforma_de_pago, idarea, idregion, idtipo_fase, idtipo_fase_actualizada, id_fase, id_fase_actualizacion, idunidad, rif_institucion, idstatus_proyecto, idusuario) values ('$_POST[codigo_arc]','$_POST[macro_proyecto]', '$_POST[importancia_social]', '$_POST[importancia_politica]', '$_POST[importancia_comercial]', NOW(), NOW(), NOW(), NOW(), '$_POST[descripcion_macro_proyecto]', '$_POST[prioridad]',  '$_POST[forma_de_pago]', '$_POST[area]', '$_POST[region]', '3','3','2', '2','$_POST[unidad]','$_POST[rif_institucion]', '1',  '$_SESSION[ce]')";
			
			$a = mysql_query($sql) or die(mysql_error());
			$tamano=$_POST["tamano_tabla"];
			for( $j=0; $j < $tamano ; $j++){
				echo "<script>
        					alert($tamano);
        				  </script>";
				$linea[$j]=$_POST["linea_de_negocio" . ($j+1)];
				$ingresos[$j]=$_POST["ingresos_unico_anual" . ($j+1)];
				$costo[$j]=$_POST["costo_venta" . ($j+1)];
				
			$sql = "INSERT into macro_linea_propuesta (idpropuesta, idlinea_de_negocio, ingreso_unico_anual, costo_venta, idusuario) VALUES (last_insert_id(), '$linea[$j]', '$ingresos[$j]', '$costo[$j]', '$_SESSION[ce]')";
			$a = mysql_query($sql) or die(mysql_error());
			
			}
        		if ($a != ""){
            		echo "<script>
        					alert('Proyecto Registrado con Exito');
        				  </script>";
						 }
					}
				
	function listar_proyecto(){
		$sql = "SELECT
					p.idpropuesta,
					p.alerta_fase,
					p.codigo_arc,
					SUM(m.costo_venta) as suma_costo, 
					SUM(m.ingreso_unico_anual) as suma_ingreso, 
					(select CAST(p.fecha_solicitud AS DATE)) as fecha, 
					p.idmacro_proyecto,
					p.idtipo_fase_actualizada,   
					i.rif_institucion,
					i.nombre_institucion,
					r.nombre_region,
					mp.nombre_macro_proyecto,
					GROUP_CONCAT(DISTINCT l.nombre_linea_de_negocio SEPARATOR ' / ') as suma_nombres,
					a.nombre_area,
					u.nombre_unidad,
					p.ingreso_real,
					p.costo_real,
					l.nombre_linea_de_negocio,
					(select pri.nombre_prioridad from prioridad pri where p.idprioridad=pri.idprioridad) as nombre_pri,
					(select fp.nombre_forma_de_pago from forma_de_pago fp where p.idforma_de_pago=fp.idforma_de_pago) as nombre_for,
					p.idregion, p.idarea, p.idunidad, m.idlinea_de_negocio, p.idprioridad, p.idforma_de_pago, p.importancia_social,
					p.importancia_politica, p.importancia_comercial,
					GROUP_CONCAT(m.ingreso_unico_anual SEPARATOR ' / ') as suma_ingresos_linea,
					GROUP_CONCAT(m.costo_venta SEPARATOR ' / ' ) as suma_costo_linea, 
					p.descripcion_macro_proyecto
						FROM
							propuesta p,institucion i, macro_linea_propuesta m,linea_de_negocio l, unidad u, region r, 
							macro_proyecto mp , area a
						WHERE 
								p.idusuario='$_SESSION[ce]'
								and m.idpropuesta=p.idpropuesta
								and p.idregion=r.idregion
								and i.rif_institucion=p.rif_institucion
								and p.idmacro_proyecto=mp.idmacro_proyecto
								and p.idarea=a.idarea
								and m.idlinea_de_negocio=l.idlinea_de_negocio
								and p.idunidad=u.idunidad
								and p.idstatus_proyecto!='2'
								 GROUP BY  p.idpropuesta ORDER BY p.alerta_fase DESC";
		echo "<table class='tpro' id='Exportar_a_Excel2'>
				<tr>
					<th colspan='15'>Listado De Proyectos Usuario: $_SESSION[usu]</th>
				</tr>    
				<tr class='title'>
					<td>Codigo ARC</td>
					<td>Fecha Solicitud</td>
					<td>RIF</td>
					<td>Institucion</td>
					<td>Region</td>
					<td>Macro Proyecto</td>
					<td>Area</td>
					<td>Unidad</td>
					<td>Linea Negocio</td>
					<td>Costo Venta</td>
					<td>Ingresos Único/Anual</td>
					<td>Alerta</td>
				</tr>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
				$instit= '"' . $da['nombre_institucion'] . '"';
				$region= '"' . $da['nombre_region'] . '"';
				$macro_proyecto= '"' . $da['nombre_macro_proyecto'] . '"';
				$area= '"' . $da['nombre_area'] . '"';
				$unidad= '"' . $da['nombre_unidad'] . '"';
				$linea_de_negocio= '"' . $da['nombre_linea_de_negocio'] . '"';
				$prioridad= '"' . $da['nombre_pri'] . '"';
				$forma_de_pago= '"' . $da['nombre_for'] . '"';
				$desc= '"' . $da['descripcion_macro_proyecto'] . '"';
				$imp_com= '"' . $da['importancia_comercial'] . '"';
				$imp_pol= '"' . $da['importancia_politica'] . '"';
				$imp_soc= '"' . $da['importancia_social'] . '"';
				$l_str_json2= json_encode($da['suma_nombres']);
				$alerta=$da['alerta_fase'];
							
			if($alerta=='1'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Verde.jpg' width='26' height='26'>";
			}
			if($alerta=='2'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Amarillo.jpg' width='26' height='26'>";
			}
			if($alerta=='3'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Rojo.jpg' width='26' height='26'>";
			}
			if($alerta=='4'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'>";
			}
        	echo "<tr>
					<td>$da[codigo_arc]</td>
					<td>$da[fecha]</td>
					<td>".utf8_encode($da['rif_institucion'])."</td>
					<td>".utf8_encode($da['nombre_institucion'])."</td>
					<td>".utf8_encode($da['nombre_region'])."</td>
					<td>".utf8_encode($da['nombre_macro_proyecto'])."</td>
					<td>".utf8_encode($da['nombre_area'])."</td>
					<td>".utf8_encode($da['nombre_unidad'])."</td>
					<td>".utf8_encode($da['suma_nombres'])."</td>";
					
					if($da['idtipo_fase_actualizada']< 12){
						echo "
							<td>".utf8_encode($da['suma_costo'])."</td>
							<td>".utf8_encode($da['suma_ingreso'])."</td>
							<td>$alerta</td>
					<td>
						<img src='../../shared/img/img eliminar/icono_eliminar.gif' width='15' tittle='eliminar' onclick='javascript:eliminar2($da[idpropuesta])' style='cursor:pointer'/>
					</td>
					<td><a href='#' onclick='javascript:modificar_proyecto($da[idpropuesta],$da[codigo_arc],$da[idmacro_proyecto],$macro_proyecto,$da[idarea],$area,
					$da[idunidad],$unidad,$da[idlinea_de_negocio],$l_str_json2,$da[idprioridad],
					$prioridad,$da[idforma_de_pago],$forma_de_pago,$desc,$imp_com,$imp_pol,$imp_soc);'>
										<img src='../../shared/img/img modificar/Sin título.png' width='15' tittle='modificar' style='cursor:pointer' '/></a>
					</td>
				  </tr>";
							
						}
					else{
						
						echo "<td>".utf8_encode($da['costo_real'])."</td>
							<td>".utf8_encode($da['ingreso_real'])."</td>
							<td>$alerta</td>
							<td>
						<img src='../../shared/img/img eliminar/icono_eliminar.gif' width='15' tittle='eliminar' onclick='javascript:eliminar2($da[idpropuesta])' style='cursor:pointer'/>
							</td>
							</tr>";
						}			
					
			}
			echo "</table>";
		}
	
	function listar_proyecto_lider(){
		$sql = "SELECT
					p.idpropuesta,
					p.alerta_fase,
					p.codigo_arc,
					SUM(m.costo_venta) as suma_costo, 
					SUM(m.ingreso_unico_anual) as suma_ingreso, 
					(select CAST(p.fecha_solicitud AS DATE)) as fecha, 
					p.idmacro_proyecto,   
					i.rif_institucion,
					i.nombre_institucion,
					p.idtipo_fase_actualizada,
					r.nombre_region,
					mp.nombre_macro_proyecto,
					GROUP_CONCAT(DISTINCT l.nombre_linea_de_negocio SEPARATOR ' / ') as suma_nombres,
					a.nombre_area,
					u.nombre_unidad,
					p.ingreso_real,
					p.costo_real,
					l.nombre_linea_de_negocio,
					(select pri.nombre_prioridad from prioridad pri where p.idprioridad=pri.idprioridad) as nombre_pri,
					(select fp.nombre_forma_de_pago from forma_de_pago fp where p.idforma_de_pago=fp.idforma_de_pago) as nombre_for, (select usu.nombre from usuario usu where usu.idcedula=p.idusuario) AS nombre_consultor,
					p.idregion, p.idarea, p.idunidad, m.idlinea_de_negocio, p.idprioridad, p.idforma_de_pago, p.importancia_social,
					p.importancia_politica, p.importancia_comercial,
					GROUP_CONCAT(m.ingreso_unico_anual SEPARATOR ' / ') as suma_ingresos_linea,
					GROUP_CONCAT(m.costo_venta SEPARATOR ' / ' ) as suma_costo_linea, 
					p.descripcion_macro_proyecto
						FROM
							propuesta p,institucion i, macro_linea_propuesta m,linea_de_negocio l, unidad u, region r, 
							macro_proyecto mp , area a, usuario
						WHERE 
								p.idusuario=usuario.idcedula
								and m.idpropuesta=p.idpropuesta
								and p.idregion=r.idregion
								and i.rif_institucion=p.rif_institucion
								and p.idmacro_proyecto=mp.idmacro_proyecto
								and p.idarea=a.idarea
								and m.idlinea_de_negocio=l.idlinea_de_negocio
								and p.idunidad=u.idunidad
								and p.idstatus_proyecto!='2'
								and usuario.idgerencia='$_SESSION[idgerencia]'
								and usuario.idsector= '$_SESSION[idsector]'
								 GROUP BY  p.idpropuesta ORDER BY p.alerta_fase DESC";
		echo "<table class='tpro' id='Exportar_a_Excel2'>
				<tr>
					<th colspan='100'>Listado De Proyectos Usuario: $_SESSION[usu]</th>
				</tr>    
				<tr class='title'>
					<td>Codigo ARC</td>
					<td>Fecha Solicitud</td>
					<td>RIF</td>
					<td>Institucion</td>
					<td>Region</td>
					<td>Macro Proyecto</td>
					<td>Area</td>
					<td>Unidad</td>
					<td>Linea Negocio</td>
					<td>Costo Venta</td>
					<td>Ingresos Único/Anual</td>
					<td>Consultor</td>
					<td>Alerta</td>
				</tr>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
				$instit= '"' . $da['nombre_institucion'] . '"';
				$region= '"' . $da['nombre_region'] . '"';
				$macro_proyecto= '"' . $da['nombre_macro_proyecto'] . '"';
				$area= '"' . $da['nombre_area'] . '"';
				$unidad= '"' . $da['nombre_unidad'] . '"';
				$linea_de_negocio= '"' . $da['nombre_linea_de_negocio'] . '"';
				$prioridad= '"' . $da['nombre_pri'] . '"';
				$forma_de_pago= '"' . $da['nombre_for'] . '"';
				$desc= '"' . $da['descripcion_macro_proyecto'] . '"';
				$l_str_json2= json_encode($da['suma_nombres']);
				$alerta=$da['alerta_fase'];
				$consul= $da['nombre_consultor'];
							
			if($alerta=='1'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Verde.jpg' width='26' height='26'>";
			}
			if($alerta=='2'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Amarillo.jpg' width='26' height='26'>";
			}
			if($alerta=='3'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Rojo.jpg' width='26' height='26'>";
			}
			if($alerta=='4'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'>";
			}
        	echo "<tr>
					<td>$da[codigo_arc]</td>
					<td>$da[fecha]</td>
					<td>".utf8_encode($da['rif_institucion'])."</td>
					<td>".utf8_encode($da['nombre_institucion'])."</td>
					<td>".utf8_encode($da['nombre_region'])."</td>
					<td>".utf8_encode($da['nombre_macro_proyecto'])."</td>
					<td>".utf8_encode($da['nombre_area'])."</td>
					<td>".utf8_encode($da['nombre_unidad'])."</td>
					<td>".utf8_encode($da['suma_nombres'])."</td>";
					
					if($da['idtipo_fase_actualizada']< 12){
						echo "
							<td>".utf8_encode($da['suma_costo'])."</td>
							<td>".utf8_encode($da['suma_ingreso'])."</td>
							<td>$consul</td>
							<td>$alerta</td>
					
				  </tr>";
							
						}
					else{
						
						echo "<td>".utf8_encode($da['costo_real'])."</td>
							<td>".utf8_encode($da['ingreso_real'])."</td>
							<td>$consul</td>
							<td>$alerta</td>
							</tr>";
						}			
					
			}
			echo "</table>";
		}
		
		
		function listar_proyecto_gerente(){
		$sql = "SELECT
					p.idpropuesta,
					p.alerta_fase,
					p.codigo_arc,
					SUM(m.costo_venta) as suma_costo, 
					SUM(m.ingreso_unico_anual) as suma_ingreso, 
					(select CAST(p.fecha_solicitud AS DATE)) as fecha, 
					p.idmacro_proyecto,   
					i.rif_institucion,
					p.idtipo_fase_actualizada,
					i.nombre_institucion,
					r.nombre_region,
					mp.nombre_macro_proyecto,
					GROUP_CONCAT(DISTINCT l.nombre_linea_de_negocio SEPARATOR ' / ') as suma_nombres,
					a.nombre_area,
					u.nombre_unidad,
					p.ingreso_real,
					p.costo_real,
					l.nombre_linea_de_negocio,
					(select pri.nombre_prioridad from prioridad pri where p.idprioridad=pri.idprioridad) as nombre_pri,
					(select fp.nombre_forma_de_pago from forma_de_pago fp where p.idforma_de_pago=fp.idforma_de_pago) as nombre_for, (select usu.nombre from usuario usu where usu.idcedula=p.idusuario) AS nombre_consultor,
					p.idregion, p.idarea, p.idunidad, m.idlinea_de_negocio, p.idprioridad, p.idforma_de_pago, p.importancia_social,
					p.importancia_politica, p.importancia_comercial,
					GROUP_CONCAT(m.ingreso_unico_anual SEPARATOR ' / ') as suma_ingresos_linea,
					GROUP_CONCAT(m.costo_venta SEPARATOR ' / ' ) as suma_costo_linea, 
					p.descripcion_macro_proyecto
						FROM
							propuesta p,institucion i, macro_linea_propuesta m,linea_de_negocio l, unidad u, region r, 
							macro_proyecto mp , area a, usuario
						WHERE 
								p.idusuario=usuario.idcedula
								and m.idpropuesta=p.idpropuesta
								and p.idregion=r.idregion
								and i.rif_institucion=p.rif_institucion
								and p.idmacro_proyecto=mp.idmacro_proyecto
								and p.idarea=a.idarea
								and m.idlinea_de_negocio=l.idlinea_de_negocio
								and p.idunidad=u.idunidad
								and usuario.idgerencia= '$_SESSION[idgerencia]'
								and p.idstatus_proyecto!='2'
								 GROUP BY  p.idpropuesta ORDER BY p.alerta_fase DESC";
		echo "<table class='tpro' id='Exportar_a_Excel2'>
				<tr>
					<th colspan='100'>Listado De Proyectos Usuario: $_SESSION[usu]</th>
				</tr>    
				<tr class='title'>
					<td>Codigo ARC</td>
					<td>Fecha Solicitud</td>
					<td>RIF</td>
					<td>Institucion</td>
					<td>Region</td>
					<td>Macro Proyecto</td>
					<td>Area</td>
					<td>Unidad</td>
					<td>Linea Negocio</td>
					<td>Costo Venta</td>
					<td>Ingresos Único/Anual</td>
					<td>Consultor</td>
					<td>Alerta</td>
				</tr>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
				$instit= '"' . $da['nombre_institucion'] . '"';
				$region= '"' . $da['nombre_region'] . '"';
				$macro_proyecto= '"' . $da['nombre_macro_proyecto'] . '"';
				$area= '"' . $da['nombre_area'] . '"';
				$unidad= '"' . $da['nombre_unidad'] . '"';
				$linea_de_negocio= '"' . $da['nombre_linea_de_negocio'] . '"';
				$prioridad= '"' . $da['nombre_pri'] . '"';
				$forma_de_pago= '"' . $da['nombre_for'] . '"';
				$desc= '"' . $da['descripcion_macro_proyecto'] . '"';
				$l_str_json2= json_encode($da['suma_nombres']);
				$alerta=$da['alerta_fase'];
				$consul= $da['nombre_consultor'];
							
			if($alerta=='1'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Verde.jpg' width='26' height='26'>";
			}
			if($alerta=='2'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Amarillo.jpg' width='26' height='26'>";
			}
			if($alerta=='3'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Rojo.jpg' width='26' height='26'>";
			}
			if($alerta=='4'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'>";
			}
        	echo "<tr>
					<td>$da[codigo_arc]</td>
					<td>$da[fecha]</td>
					<td>".utf8_encode($da['rif_institucion'])."</td>
					<td>".utf8_encode($da['nombre_institucion'])."</td>
					<td>".utf8_encode($da['nombre_region'])."</td>
					<td>".utf8_encode($da['nombre_macro_proyecto'])."</td>
					<td>".utf8_encode($da['nombre_area'])."</td>
					<td>".utf8_encode($da['nombre_unidad'])."</td>
					<td>".utf8_encode($da['suma_nombres'])."</td>";
					
					if($da['idtipo_fase_actualizada']< 12){
						echo "
							<td>".utf8_encode($da['suma_costo'])."</td>
							<td>".utf8_encode($da['suma_ingreso'])."</td>
							<td>$consul</td>
							<td>$alerta</td>
					
				  </tr>";
							
						}
					else{
						
						echo "<td>".utf8_encode($da['costo_real'])."</td>
							<td>".utf8_encode($da['ingreso_real'])."</td>
							<td>$consul</td>
							<td>$alerta</td>
							</tr>";
						}			
					
			}
			echo "</table>";
		}
	
	function eliminar_proyecto(){
        $sql = "UPDATE propuesta SET idstatus_proyecto='2' where idpropuesta = $_POST[op]";
		$a = mysql_query($sql) or die(mysql_error());
        echo "<script>alert('¡Proyecto Eliminado Con exito!')</script>";
			}
	
	function combo_instituciones(){
		$sql = "select i.* from institucion i, cartera_instituciones c where i.rif_institucion = c.rif_institucion and c.idcedula = '".$_SESSION['cedula']."' ORDER BY nombre_institucion";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[rif_institucion]'>$da[nombre_institucion]</option>";
            echo $html;
			}
    	}

    function combo_region(){
        $sql = "select * from region ORDER BY nombre_region";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idregion]'>$da[nombre_region]</option>";
            echo $html;
			}
    	}
		
	function combo_macroproyecto(){
        $sql = "select * from macro_proyecto ORDER BY nombre_macro_proyecto";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idmacro_proyecto]'>$da[nombre_macro_proyecto]</option>";
            echo $html;
        	}
    	}
	
	 function combo_area(){
        $sql = "select * from area ORDER BY nombre_area";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idarea]'>" . utf8_encode($da['nombre_area']) . "</option>";
            echo $html;
				}
		}
		
	function combo_linea(){
        $sql = "select * from linea_de_negocio ORDER BY nombre_linea_de_negocio";
		$a = mysql_query($sql) or die(mysql_error());
		$i=0;
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idlinea_de_negocio]'>$da[nombre_linea_de_negocio]</option>";
				
            echo $html;
			}
		}		
	
    function combo_unidad(){
        $sql = "select * from unidad ORDER BY nombre_unidad";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idunidad]'>$da[nombre_unidad]</option>";
        	echo $html;
			}
    	}
	
	function combo_prioridad(){
        $sql = "select * from prioridad ORDER BY nombre_prioridad";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idprioridad]'>$da[nombre_prioridad]</option>";
        	echo $html;
        	}
    	}
	
	function combo_status(){
        $sql = "select * from status_proyecto ORDER BY nombre_status_proyecto";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
           		$html = "<option value='$da[idstatus_proyecto]'>$da[nombre_status_proyecto]</option>";
            echo $html;
        	}
    	}
	
	function combo_pago(){
        $sql = "select * from forma_de_pago ORDER BY nombre_forma_de_pago";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
           		$html = "<option value='$da[idforma_de_pago]'>$da[nombre_forma_de_pago]</option>";
            echo $html;
        	}
    	}
	
	function combo_fase(){
        $sql = "select * from tipo_fase Where tipo = 4 ORDER BY nombre_tipo_fase";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
           		$html = "<option value='$da[idtipo_fase]'>$da[nombre_tipo_fase]</option>";
            echo $html;
        	}
    	}
	
	function actualizar_proyecto(){
		$sql="UPDATE propuesta SET idmacro_proyecto = $_POST[macro_proyecto], importancia_social = '$_POST[importancia_social]', importancia_politica = '$_POST[importancia_politica]', importancia_comercial = '$_POST[importancia_comercial]', descripcion_macro_proyecto = '$_POST[descripcion_macro_proyecto]', idprioridad = $_POST[prioridad], idforma_de_pago = $_POST[forma_de_pago], idarea = $_POST[area], idunidad = $_POST[unidad] WHERE idpropuesta = '$_POST[cod_pro]'";
			
			$a = mysql_query($sql) or die(mysql_error());
			
			$sql = "DELETE from macro_linea_propuesta where idpropuesta='$_POST[cod_pro]'";
			$a = mysql_query($sql) or die(mysql_error());
			
			
			$tamano=$_POST["tamano_tabla"];
			for( $j=0; $j < $tamano ; $j++){
				$linea[$j]=$_POST["linea_de_negocio" . ($j+1)];
				$ingresos[$j]=$_POST["ingresos_unico_anual" . ($j+1)];
				$costo[$j]=$_POST["costo_venta" . ($j+1)];
				
			$sql = "INSERT into macro_linea_propuesta (idpropuesta, idlinea_de_negocio, ingreso_unico_anual, costo_venta, idusuario) VALUES ('$_POST[cod_pro]', '$linea[$j]', '$ingresos[$j]', '$costo[$j]', '$_SESSION[ce]')";
			$a = mysql_query($sql) or die(mysql_error());
			
			}
        		if ($a != ""){
            		echo "<script>alert('¡Proyecto Modificado Con exito!')
			window.opener.location= 'ver_proyecto.php';
			window.close();
			</script>";
						 }
			
			
			}
		
	function mostrar_costo(){
        $sql = "select * from macro_linea_propuesta where idpropuesta='$_GET[codf]'";
		 $a = mysql_query($sql) or die(mysql_error());
		 $arre= array();
		 while ($da = mysql_fetch_assoc($a)) {
			 
			 $arre[]=array("idlinea"=>$da['idlinea_de_negocio'],"ingreso"=>$da['ingreso_unico_anual'],"costo"=>$da['costo_venta']);
		 }
		 
		$ja=json_encode($arre);
		echo $ja;
    	}
	}
?>