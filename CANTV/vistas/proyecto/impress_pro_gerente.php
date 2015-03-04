<?php
session_start();
		include '../../config.php';
		include '../../modelo/Class_Proyecto.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
	require('../../shared/plugins/fpdf/fpdf.php');
	include_once('../../modelo/Class_sql.php');
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
								 
		$pdf = new FPDF('L', 'mm', 'A4');
		$pdf->AddPage();
		$pdf->SetFont('Arial','B',12);
			$pdf->Image('../../shared/img/img cabecera/cabecera_gobenlinea.jpg',0,0,282);
			$pdf->Cell(260,40,$_SESSION['usu'],0,0,'C');
			$pdf->Ln();
		$pdf->SetFont('Arial','B',8);
			$pdf->Cell(10,7,"ARC",1,0,"C");
			$pdf->Cell(21,7,"Fecha Solicitud",1,0,"C");
			$pdf->Cell(18,7,"RIF",1,0,"C");
			$pdf->Cell(70,7,"Institucion",1,0,"C");
			$pdf->Cell(15,7,"Region",1,0,"C");
			$pdf->Cell(36,7,"Macro-Proyecto",1,0,"C");
			$pdf->Cell(15,7,"Area",1,0,"C");
			//$pdf->Cell(12,7,"Unidad",1,0,"C");
			$pdf->Cell(66,7,"Linea de Neg.",1,0,"C");
			$pdf->Cell(18,7,utf8_decode('Costos'),1,0,"C");
			$pdf->Cell(20,7,"Ingresos",1,0,"C");
		$pdf->Ln();
	$a = mysql_query($sql);
	$pdf->SetFont("Arial","",8);
		while($da = mysql_fetch_assoc($a)){
			$pdf->Cell(10,7,$da['codigo_arc'],0,0,"C");
			$pdf->Cell(21,7,$da['fecha'],0,0,"C");
			$pdf->Cell(18,7,$da['rif_institucion'],0,0,"C");
			$pdf->Cell(70,7,substr($da['nombre_institucion'],0,32),0,0,"C");
			$pdf->Cell(15,7,$da['nombre_region'],0,0,"C");
			$pdf->Cell(36,7,$da['nombre_macro_proyecto'],0,0,"C");
			$pdf->Cell(15,7,$da['nombre_area'],0,0,"C");
			//$pdf->Cell(12,7,$da['nombre_unidad'],0,0,"C");
			$pdf->Cell(66,7,$da['suma_nombres'],0,0,"C");
			/*$pdf->Cell(18,7,$da['suma_costo'],0,0,"C");
			$pdf->Cell(20,7,$da['suma_ingreso'],0,0,"C");*/
			
			if($da['idtipo_fase_actualizada']< 12){
						
							$pdf->Cell(18,7,$da['suma_costo'],0,0,"C");
							$pdf->Cell(20,7,$da['suma_ingreso'],0,0,"C");
				
							
						}
					else{
						
						$pdf->Cell(18,7,$da['costo_real'],0,0,"C");
						$pdf->Cell(20,7,$da['ingreso_real'],0,0,"C");
							
						}			
			$pdf->Ln();
			
			
			
			}
	$pdf->Ln();
	$pdf->Output();
?>