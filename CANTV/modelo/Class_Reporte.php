<?php
	class Class_Reporte{
		
		function Class_Reporte(){
			}
			
			
		function reporte_demanda(){
			
			$sql = "select 
			idinstitucion_servicios, 
			rif_institucion, 
			(select nombre_institucion from institucion where institucion_servicios.rif_institucion = institucion.rif_institucion) AS nombre_institucion,
			(select nombre_servicio from servicio where institucion_servicios.idservicio = servicio.idservicio) AS nombre_servicio,
			(select idservicio from servicio where institucion_servicios.idservicio = servicio.idservicio) AS id_servicio,
			(select nombre_tipo_servicio from tipo_servicio where institucion_servicios.idtipo_servicio = tipo_servicio.idtipo_servicio) AS nombre_tipo_servicio,
			(select idtipo_servicio from tipo_servicio where institucion_servicios.idtipo_servicio = tipo_servicio.idtipo_servicio) AS id_tipo_servicio,
			(select nombre_subtipo_servicio from subtipo_servicio where institucion_servicios.idsubtipo_servicio = subtipo_servicio.idsubtipo_servicio) AS nombre_subtipo_servicio,
			(select idsubtipo_servicio from subtipo_servicio where institucion_servicios.idsubtipo_servicio = subtipo_servicio.idsubtipo_servicio) AS id_subtipo_servicio,
			(select nombre_plan from plan where institucion_servicios.idplan = plan.idplan) AS nombre_plan,
			(select idplan from plan where institucion_servicios.idplan = plan.idplan) AS id_plan,
			(select nombre_central from central where institucion_servicios.idcentral = central.idcentral) AS nombre_central,
			(select nombre_trimestre from trimestre where institucion_servicios.idtrimestre = trimestre.idtrimestre) AS nombre_trimestre,
			(select idtrimestre from trimestre where institucion_servicios.idtrimestre = trimestre.idtrimestre) AS id_trimestre,
			ano,
			cantidad,
			(select nombre from usuario where institucion_servicios.idusuario = usuario.idcedula) AS nombre_usuario,
			(select nombre_gerencia from gerencia,usuario where institucion_servicios.idusuario =usuario.idcedula AND usuario.idgerencia=gerencia.idgerencia) AS gerencia
		from institucion_servicios where institucion_servicios.status = '1' ";
			
			echo "
			<div class='check'>
            <input type='checkbox' id='check0' href='javascript:void(0);' onclick='fnShowHide(0);verificar_cantidad(0);' checked>R.I.F
			<input type='checkbox' id='check1' href='javascript:void(0);' onclick='fnShowHide(1);verificar_cantidad(1);' checked>Institucion
			<input type='checkbox' id='check2' href='javascript:void(0);' onclick='fnShowHide(2);verificar_cantidad(2);' checked>Servicio
			<input type='checkbox' id='check3' href='javascript:void(0);' onclick='fnShowHide(3);verificar_cantidad(3);' checked>Tipo
			<input type='checkbox' id='check4' href='javascript:void(0);' onclick='fnShowHide(4);verificar_cantidad(4);' >Sub-tipo
			<input type='checkbox' id='check5' href='javascript:void(0);' onclick='fnShowHide(5);verificar_cantidad(5);' >Plan
			<input type='checkbox' id='check6' href='javascript:void(0);' onclick='fnShowHide(6);verificar_cantidad(6);' checked>Central
			<input type='checkbox' id='check7' href='javascript:void(0);' onclick='fnShowHide(7);verificar_cantidad(7);' >Trimestre
			<input type='checkbox' id='check8' href='javascript:void(0);' onclick='fnShowHide(8);verificar_cantidad(8);' checked>Ano
			<input type='checkbox' id='check9' href='javascript:void(0);' onclick='fnShowHide(9);verificar_cantidad(9);' checked>Cantidad
			<input type='checkbox' id='check10' href='javascript:void(0);' onclick='fnShowHide(10);verificar_cantidad(10);' checked>Usuario
			<input type='checkbox' id='check11' href='javascript:void(0);' onclick='fnShowHide(11);verificar_cantidad(11);' checked>Gerencia
			
            <br>
			<p style='font-size:11px;'>Nota: Se acepta un Max. de 9 columnas por reporte!</p>
            <br>
            </div>
			<div id='demo' >
			<table cellpadding='0' cellspacing='0' border='0' class='display' id='example'>
			<thead>
				<tr>
				<th>R.I.F</th>
				<th>Institucion</th>
				<th>Servicio</th>
				<th>Tipo</th>
				<th>Sub-tipo</th>
				<th>Plan</th>
				<th>Central</th>
				<th>Trimestre</th>
				<th>Ano</th>
				<th>Cant</th>
				<th>Usuario</th>
				<th>Gerencia</th>
				</tr>
			</thead>
			 
	<tbody>";
		$a = mysql_query($sql) or die(mysql_error());
		while ($da = mysql_fetch_assoc($a)) {
		echo "
		<tr class='odd gradeX'>
			<td class='center'>$da[rif_institucion]</td>
			<td class='center'>".substr($da['nombre_institucion'],0,32)."</td>
			<td class='center'>".utf8_encode($da['nombre_servicio'])."</td>
			<td class='center'>".utf8_encode($da['nombre_tipo_servicio'])."</td>
			<td class='center'>".utf8_encode($da['nombre_subtipo_servicio'])."</td>
			<td class='center'>".utf8_encode($da['nombre_plan'])."</td>
			<td class='center'>".utf8_encode($da['nombre_central'])."</td>
			<td class='center'>".utf8_encode($da['nombre_trimestre'])."</td>
			<td class='center'>$da[ano]</td>
			<td class='center'>".utf8_encode($da['cantidad'])."</td>
			<td class='center'>".utf8_encode($da['nombre_usuario'])."</td>
			<td class='center'>".substr($da['gerencia'],19,35)."</td>
			</tr>
			
			
		";
		
		}
		//.wordwrap($da['nombre_institucion'],15,".",false).
		
        echo "
		</tbody>
	<tfoot>
		<tr>
				<th>R.I.F</th>
				<th>Institucion</th>
				<th>Servicio</th>
				<th>Tipo</th>
				<th>Sub-tipo</th>
				<th>Plan</th>
				<th>Central</th>
				<th>Trimestre</th>
				<th>Ano</th>
				<th>Cantidad</th>
				<th>Usuario</th>
				<th>Gerencia</th>
		</tr>
	</tfoot>
	</table></div>";
			
			
			
			
			
		}
		
	function reporte_proyecto(){
				
				$sql = "SELECT
					p.idpropuesta,
					p.alerta_fase,
					p.codigo_arc,
					(select CAST(p.fecha_solicitud AS DATE)) as fecha, 
					i.rif_institucion,
					i.nombre_institucion,
					r.nombre_region,
					mp.nombre_macro_proyecto,
					GROUP_CONCAT(DISTINCT l.nombre_linea_de_negocio SEPARATOR ' / ') as suma_nombres,
					a.nombre_area,
					l.nombre_linea_de_negocio,
					(select pri.nombre_prioridad from prioridad pri where p.idprioridad=pri.idprioridad) as nombre_pri,
					(select fp.nombre_forma_de_pago from forma_de_pago fp where p.idforma_de_pago=fp.idforma_de_pago) as nombre_for,
					p.idregion, p.idarea, p.idunidad, m.idlinea_de_negocio, p.idprioridad, p.idforma_de_pago, p.importancia_social,
					p.importancia_politica, p.importancia_comercial,
					p.descripcion_macro_proyecto,
					p.costo_real,
					p.ingreso_real,
					p.idtipo_fase_actualizada,
					(select nombre_tipo_fase from tipo_fase where p.idtipo_fase_actualizada=tipo_fase.idtipo_fase) as tipof,
					(select nombre_fase from fase_proyecto where p.id_fase_actualizacion=fase_proyecto.id_fase) as fase,
					(select nombre from usuario where p.idusuario = usuario.idcedula) AS nombre_usuario,
					(select nombre_gerencia from gerencia,usuario where p.idusuario = usuario.idcedula AND usuario.idgerencia=gerencia.idgerencia)
					 AS gerencia
						FROM
							propuesta p,institucion i, macro_linea_propuesta m,linea_de_negocio l, unidad u, region r, 
							macro_proyecto mp , area a
						WHERE 
								 m.idpropuesta=p.idpropuesta
								and p.idregion=r.idregion
								and i.rif_institucion=p.rif_institucion
								and p.idmacro_proyecto=mp.idmacro_proyecto
								and p.idarea=a.idarea
								and m.idlinea_de_negocio=l.idlinea_de_negocio
								and p.idunidad=u.idunidad
								and p.idstatus_proyecto!='2'
								 GROUP BY  p.idpropuesta";
				
				echo "
				<div class='check'>
				<input type='checkbox' id='check0' href='javascript:void(0);' onclick='fnShowHide(0);verificar_cantidad(0);' checked>Codigo A.R.C.
				<input type='checkbox' id='check1' href='javascript:void(0);' onclick='fnShowHide(1);verificar_cantidad(1);' >Fecha Solicitud
				<input type='checkbox' id='check2' href='javascript:void(0);' onclick='fnShowHide(2);verificar_cantidad(2);' checked>RIF
				<input type='checkbox' id='check3' href='javascript:void(0);' onclick='fnShowHide(3);verificar_cantidad(3);' checked>Institucion
				<input type='checkbox' id='check4' href='javascript:void(0);' onclick='fnShowHide(4);verificar_cantidad(4);' >Region
				<input type='checkbox' id='check5' href='javascript:void(0);' onclick='fnShowHide(5);verificar_cantidad(5);' checked>Macro Proyecto
				<input type='checkbox' id='check6' href='javascript:void(0);' onclick='fnShowHide(6);verificar_cantidad(6);' >Area
				<input type='checkbox' id='check7' href='javascript:void(0);' onclick='fnShowHide(7);verificar_cantidad(7);' checked>Linea de Negocio
				<input type='checkbox' id='check8' href='javascript:void(0);' onclick='fnShowHide(8);verificar_cantidad(8);' checked>Fase
				<br>
				<input type='checkbox' id='check9' href='javascript:void(0);' onclick='fnShowHide(9);verificar_cantidad(9);' >Sub-Fase
				<input type='checkbox' id='check10' href='javascript:void(0);' onclick='fnShowHide(10);verificar_cantidad(10);' >Costos
				<input type='checkbox' id='check11' href='javascript:void(0);' onclick='fnShowHide(11);verificar_cantidad(11);' checked>Ingresos
				<input type='checkbox' id='check12' href='javascript:void(0);' onclick='fnShowHide(12);verificar_cantidad(12);' >Usuario
				<input type='checkbox' id='check13' href='javascript:void(0);' onclick='fnShowHide(13);verificar_cantidad(13);' >Gerencia
				<input type='checkbox' id='check14' href='javascript:void(0);' onclick='fnShowHide(14);verificar_cantidad(14);' checked>Alerta
				<br>
				<p style='font-size:11px;'>Nota: Se acepta un Max. de 8 columnas por reporte!</p>
				<br>
				</div>
				<div id='demo' >
				<table cellpadding='0' cellspacing='0' border='0' class='display' id='example'>
				<thead>
					<tr>
					<th>ARC</th>
					<th>Solicitud</th>
					<th>RIF</th>
					<th>Institucion</th>
					<th>Region</th>
					<th>Macro Proyecto</th>
					<th>Area</th>
					<th>Linea Negocio</th>
					<th>Fase</th>
					<th>Sub-Fase</th>
					<th>Costo</th>
					<th>Ingreso</th>
					<th>Usuario</th>
					<th>Gerencia</th>
					<th>Alerta</th>
					</tr>
				</thead>
				 
		<tbody>";
			$a = mysql_query($sql) or die(mysql_error());
			while ($da = mysql_fetch_assoc($a)) {
				if($da['idtipo_fase_actualizada']>11){
					
					$alerta=$da['alerta_fase'];
							
			if($alerta=='1'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Verde.jpg' width='16' height='16'>";
			}
			if($alerta=='2'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Amarillo.jpg' width='16' height='16'>";
			}
			if($alerta=='3'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Rojo.jpg' width='16' height='16'>";
			}
			if($alerta=='4'){
				$alerta="<img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='16' height='16'>";
			}
			
			echo "
			<tr class='odd gradeX'>
				<td class='center'>$da[codigo_arc]</td>
				<td class='center'>$da[fecha]</td>
				<td class='center'>$da[rif_institucion]</td>
				<td class='center'>".substr($da['nombre_institucion'],0,32)."</td>
				<td class='center'>".utf8_encode($da['nombre_region'])."</td>
				<td class='center'>".utf8_encode($da['nombre_macro_proyecto'])."</td>
				<td class='center'>".utf8_encode($da['nombre_area'])."</td>
				<td class='center'>".utf8_encode($da['suma_nombres'])."</td>
				<td class='center'>".utf8_encode($da['fase'])."</td>
				<td class='center'>".substr($da['tipof'],0,25)."</td>
				<td class='center'>$da[costo_real]</td>
				<td class='center'>$da[ingreso_real]</td>
				<td class='center'>".utf8_encode($da['nombre_usuario'])."</td>
				<td class='center'>".substr($da['gerencia'],29,35)."</td>
				<td class='center'>$alerta</td>
				</tr>
				
				
			";
			
				}
			}
			
			
			echo "
			</tbody>
		<tfoot>
			<tr>
					<th>ARC</th>
					<th>Fecha Solicitud</th>
					<th>RIF</th>
					<th>Institucion</th>
					<th>Region</th>
					<th>Macro Proyecto</th>
					<th>Area</th>
					<th>Linea de Negocio</th>
					<th>Fase</th>
					<th>Sub-Fase</th>
					<th>Costos</th>
					<th>Ingresos</th>
					<th>Usuario</th>
					<th>Gerencia</th>
					
			</tr>
		</tfoot>
		</table></div>";
				
				
				
				
				
			}	
			
			
			function reporte_dataip(){
			
			$sql = "select idsucursales, nombre_sede, direccion, observaciones, servicios_datos, servicios_internet, servicios_telf_fija, servicios_ti, servicios_moviles, servicios_equipamento, rif_institucion, 
			(select nombre_institucion from institucion where sucursales.rif_institucion = institucion.rif_institucion) AS nombre_institucion,(select nombre_region from region where sucursales.idregion = region.idregion)
				AS nombre_region, (select nombre_estado from estado where sucursales.idestado = estado.idestado)
				AS nombre_estado, (select nombre_municipio from municipio where sucursales.idmunicipio = municipio.idmunicipio)
				AS nombre_municipio, (select nombre_central from central where sucursales.idcentral = central.idcentral)
				AS nombre_central,
				(select nombre from usuario where sucursales.idusuario = usuario.idcedula) AS nombre_usuario,
				(select nombre_gerencia from gerencia,usuario where sucursales.idusuario = usuario.idcedula AND usuario.idgerencia=gerencia.idgerencia)
					 AS gerencia
				 from sucursales";
				
				echo "
				<div class='check'>
				<input type='checkbox' id='check0' href='javascript:void(0);' onclick='fnShowHide(0);verificar_cantidad(0);' checked>RIF
				<input type='checkbox' id='check1' href='javascript:void(0);' onclick='fnShowHide(1);verificar_cantidad(1);' checked>Institucion
				<input type='checkbox' id='check2' href='javascript:void(0);' onclick='fnShowHide(2);verificar_cantidad(2);' checked>Nombre Sede
				<input type='checkbox' id='check3' href='javascript:void(0);' onclick='fnShowHide(3);verificar_cantidad(3);' checked>Region
				<input type='checkbox' id='check4' href='javascript:void(0);' onclick='fnShowHide(4);verificar_cantidad(4);' checked>Estado
				<input type='checkbox' id='check5' href='javascript:void(0);' onclick='fnShowHide(5);verificar_cantidad(5);' >Central
				<input type='checkbox' id='check6' href='javascript:void(0);' onclick='fnShowHide(6);verificar_cantidad(6);' >Direccion
				<input type='checkbox' id='check7' href='javascript:void(0);' onclick='fnShowHide(7);verificar_cantidad(7);' >Persona Contacto
				<input type='checkbox' id='check8' href='javascript:void(0);' onclick='fnShowHide(8);verificar_cantidad(8);' checked>Serv.Datos
				<input type='checkbox' id='check9' href='javascript:void(0);' onclick='fnShowHide(9);verificar_cantidad(9);' checked>Serv. Internet
				<br>
				<input type='checkbox' id='check10' href='javascript:void(0);' onclick='fnShowHide(10);verificar_cantidad(10);' checked>Serv. Telf.Fija
				<input type='checkbox' id='check11' href='javascript:void(0);' onclick='fnShowHide(11);verificar_cantidad(11);' checked>Serv. TI
				<input type='checkbox' id='check12' href='javascript:void(0);' onclick='fnShowHide(12);verificar_cantidad(12);' checked>Serv. Moviles
				<input type='checkbox' id='check13' href='javascript:void(0);' onclick='fnShowHide(13);verificar_cantidad(13);' checked>Serv. Equipamento
				<input type='checkbox' id='check14' href='javascript:void(0);' onclick='fnShowHide(14);verificar_cantidad(14);' >Usuario
				<input type='checkbox' id='check15' href='javascript:void(0);' onclick='fnShowHide(15);verificar_cantidad(15);' >Gerencia
				<br>
				<p style='font-size:11px;'>Nota: Se acepta un Max. de 11 columnas por reporte!</p>
				<br>
				</div>
				<div id='demo' >
				<table cellpadding='0' cellspacing='0' border='0' class='display' id='example'>
				<thead>
					<tr>
					<th>RIF</th>
					<th>Institucion</th>
					<th>Sede</th>
					<th>Region</th>
					<th>Estado</th>
					<th>Central</th>
					<th>Direccion</th>
					<th>Contacto</th>
					<th>Datos</th>
					<th>Internet</th>
					<th>Telf. Fija</th>
					<th>TI</th>
					<th>Moviles</th>
					<th>Equip.</th>
					<th>Usuario</th>
					<th>Gerencia</th>
					</tr>
				</thead>
				 
		<tbody>";
			$a = mysql_query($sql) or die(mysql_error());
			while ($da = mysql_fetch_assoc($a)) {
			echo "
			<tr class='odd gradeX'>
				<td class='center'>$da[rif_institucion]</td>
				<td class='center'>".substr($da['nombre_institucion'],0,32)."</td>
				<td class='center'>".utf8_encode($da['nombre_sede'])."</td>
				<td class='center'>".utf8_encode($da['nombre_region'])."</td>
				<td class='center'>".utf8_encode($da['nombre_estado'])."</td>
				<td class='center'>".utf8_encode($da['nombre_central'])."</td>
				<td class='center'>".utf8_encode($da['direccion'])."</td>
				<td class='center'>".utf8_encode($da['observaciones'])."</td>
				<td class='center'>$da[servicios_datos]</td>
				<td class='center'>$da[servicios_internet]</td>
				<td class='center'>$da[servicios_telf_fija]</td>
				<td class='center'>$da[servicios_ti]</td>
				<td class='center'>$da[servicios_moviles]</td>
				<td class='center'>$da[servicios_equipamento]</td>
				<td class='center'>".utf8_encode($da['nombre_usuario'])."</td>
				<td class='center'>".substr($da['gerencia'],29,35)."</td>
				</tr>
				
				
			";
			
			}
			
			
			echo "
			</tbody>
		<tfoot>
			<tr>
					<th>RIF</th>
					<th>Institucion</th>
					<th>Sede</th>
					<th>Region</th>
					<th>Estado</th>
					<th>Central</th>
					<th>Direccion</th>
					<th>Contacto</th>
					<th>Datos</th>
					<th>Internet</th>
					<th>Telf. Fija</th>
					<th>TI</th>
					<th>Moviles</th>
					<th>Equipamento</th>
					<th>Usuario</th>
					<th>Gerencia</th>
					
			</tr>
		</tfoot>
		</table></div>";
			
			}
	}
?>
