<?php
session_start();
		include '../../config.php';
		include '../../modelo/Class_Data.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
				
	require('../../shared/plugins/fpdf/fpdf.php');
	include_once('../../modelo/Class_sql.php');
	$sql = "select idsucursales, nombre_sede, direccion, observaciones, servicios_datos, servicios_internet, servicios_telf_fija,
		servicios_ti, servicios_moviles, servicios_equipamento, rif_institucion,
		(select nombre_institucion from institucion where sucursales.rif_institucion = institucion.rif_institucion) AS nombre_institucion,
		(select nombre_region from region where sucursales.idregion = region.idregion) AS nombre_region,
		(select nombre_estado from estado where sucursales.idestado = estado.idestado) AS nombre_estado,
		(select nombre_municipio from municipio where sucursales.idmunicipio = municipio.idmunicipio) AS nombre_municipio,
		(select nombre_central from central where sucursales.idcentral = central.idcentral) AS nombre_central,
		(select usu.nombre from usuario usu where usu.idcedula=sucursales.idusuario) AS nombre_consultor
		FROM sucursales, usuario
		WHERE sucursales.idusuario=usuario.idcedula and usuario.idgerencia='$_SESSION[idgerencia]' and usuario.idsector='$_SESSION[idsector]' and sucursales.status!= '2'
		ORDER BY  sucursales.rif_institucion";
		
								 
		$pdf = new FPDF('L', 'mm', 'A4');
		$pdf->AddPage();
		$pdf->SetFont('Arial','B',12);
			$pdf->Image('../../shared/img/img cabecera/cabecera_gobenlinea.jpg',0,0,282);
			$pdf->Cell(260,40,$_SESSION['usu'],0,0,'C');
			$pdf->Ln();
		$pdf->SetFont('Arial','B',8);
			$pdf->Cell(16,7,"RIF",1,0,"C");
			$pdf->Cell(60,7,"Institucion",1,0,"C");
			$pdf->Cell(25,7,"Nombre Sede",1,0,"C");
			$pdf->Cell(13,7,"Region",1,0,"C");
			$pdf->Cell(15,7,"Estado",1,0,"C");
			$pdf->Cell(17,7,"Direccion",1,0,"C");
			$pdf->Cell(28,7,"Central",1,0,"C");
			$pdf->Cell(15,7,"Datos",1,0,"C");
			$pdf->Cell(15,7,"Internet",1,0,"C");
			$pdf->Cell(18,7,"Telf. Fija",1,0,"C");
			$pdf->Cell(10,7,"TI",1,0,"C");
			$pdf->Cell(15,7,"Moviles",1,0,"C");
			$pdf->Cell(15,7,"Equip",1,0,"C");
			$pdf->Cell(25,7,"Contacto",1,0,"C");
		$pdf->Ln();
	$a = mysql_query($sql);
	$pdf->SetFont("Arial","",8);
		while($da = mysql_fetch_assoc($a)){
			$pdf->Cell(17,7,$da['rif_institucion'],0,0,"C");
			$pdf->Cell(58,7,substr($da['nombre_institucion'],0,32),0,0,"C");
			$pdf->Cell(24,7,$da['nombre_sede'],0,0,"C");
			$pdf->Cell(15,7,$da['nombre_region'],0,0,"C");
			$pdf->Cell(15,7,$da['nombre_estado'],0,0,"C");
			$pdf->Cell(17,7,$da['direccion'],0,0,"C");
			$pdf->Cell(30,7,$da['nombre_central'],0,0,"C");
			$pdf->Cell(16,7,$da['servicios_datos'],0,0,"C");
			$pdf->Cell(14,7,$da['servicios_internet'],0,0,"C");
			$pdf->Cell(15,7,$da['servicios_telf_fija'],0,0,"C");
			$pdf->Cell(12,7,$da['servicios_ti'],0,0,"C");
			$pdf->Cell(16,7,$da['servicios_moviles'],0,0,"C");
			$pdf->Cell(14,7,$da['servicios_equipamento'],0,0,"C");
			$pdf->Cell(26,7,$da['observaciones'],0,0,"C");
			$pdf->Ln();
			
			
			
			}
	$pdf->Ln();
	$pdf->Output();
?>

        