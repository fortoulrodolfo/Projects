<?php
session_start();
		include '../../config.php';
		include '../../modelo/Class_Demanda.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
	require('../../shared/plugins/fpdf/fpdf.php');
	include_once('../../modelo/Class_sql.php');
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
			(select u.nombre from usuario u where u.idcedula=institucion_servicios.idusuario) AS nombre_consultor,
			ano,cantidad,idcedula
			FROM institucion_servicios,usuario
			WHERE institucion_servicios.idusuario=usuario.idcedula
					and (institucion_servicios.status = '1' OR institucion_servicios.status = '2') and usuario.idgerencia='$_SESSION[idgerencia]'
			GROUP BY idinstitucion_servicios";
		$pdf = new FPDF('L', 'mm', 'A4');
		$pdf->AddPage();
		$pdf->SetFont('Arial','B',12);
			$pdf->Image('../../shared/img/img cabecera/cabecera_gobenlinea.jpg',0,0,282);
			$pdf->Cell(260,40,$_SESSION['usu'],0,0,'C');
			$pdf->Ln();
		$pdf->SetFont('Arial','B',8);
			$pdf->Cell(8,7,"Cod",1,0,"C");
			$pdf->Cell(21,7,"R.I.F",1,0,"C");
			$pdf->Cell(70,7,"Institucion",1,0,"C");
			$pdf->Cell(25,7,"Servicio",1,0,"C");
			$pdf->Cell(20,7,"Tipo",1,0,"C");
			$pdf->Cell(30,7,"Sub-Tipo",1,0,"C");
			$pdf->Cell(15,7,"Plan",1,0,"C");
			$pdf->Cell(45,7,"Central",1,0,"C");
			$pdf->Cell(20,7,"Trimestre",1,0,"C");
			$pdf->Cell(12,7,utf8_decode('Año'),1,0,"C");
			$pdf->Cell(20,7,"Cantidad",1,0,"C");
		$pdf->Ln();
	$a = mysql_query($sql);
	$pdf->SetFont("Arial","",8);
		while($da = mysql_fetch_assoc($a)){
			$pdf->Cell(8,7,$da['idinstitucion_servicios'],0,0,"C");
			$pdf->Cell(21,7,$da['rif_institucion'],0,0,"C");
			$pdf->Cell(70,7,substr($da['nombre_institucion'],0,32),0,0,"C");
			$pdf->Cell(25,7,$da['nombre_servicio'],0,0,"C");
			$pdf->Cell(20,7,substr($da['nombre_tipo_servicio'],0,12),0,0,"C");
			$pdf->Cell(30,7,$da['nombre_subtipo_servicio'],0,0,"C");
			$pdf->Cell(15,7,$da['nombre_plan'],0,0,"C");
			$pdf->Cell(45,7,$da['nombre_central'],0,0,"C");
			$pdf->Cell(20,7,$da['nombre_trimestre'],0,0,"C");
			$pdf->Cell(12,7,utf8_decode($da['ano']),0,0,"C");
			$pdf->Cell(20,7,$da['cantidad'],0,0,"C");
			$pdf->Ln();
			}
	$pdf->Ln();
	$pdf->Output();
?>