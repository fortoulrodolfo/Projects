<?php
$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
	$link = $conexiones->link;
	$userBD = $conexiones->sistemas->usuario;
	$claveBD = $conexiones->sistemas->contrasena;
	$servicioBD = $conexiones->sistemas->servicio;
	
	/* Se lleva a cabo la conexión con la base de datos.
		 */
	$conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');
	
	/** Include PHPExcel */
	include '../extensiones/Excel/PHPExcel.php';
	
	
	// Create new PHPExcel object
	$objPHPExcel = new PHPExcel();


	if ($conec){
		
		$query = "SELECT CITMED.T_CITA_BITACORA.ID_CITA_BITACORA,
					TO_CHAR(CITMED.T_CITA_BITACORA.FECHA_INICIO_CITA_BITACORA, 'DD-MM-YYYY HH24:MI:SS'),
					TO_CHAR(CITMED.T_CITA_BITACORA.FECHA_FIN_CITA_BITACORA, 'DD-MM-YYYY HH24:MI:SS'),
					CITMED.T_CITA_BITACORA.ID_PACIENTE_BITACORA,
					CITMED.T_PACIENTE.NOMBRE_PACIENTE,
					CITMED.T_PACIENTE.APELLIDO1_PACIENTE,
					CITMED.T_CITA_BITACORA.ID_MEDICO_BITACORA,
					CITMED.T_MEDICO.NOMBRE1_MEDICO,
					CITMED.T_MEDICO.APELLIDO1_MEDICO,
					CITMED.T_CITA_BITACORA.ID_PROCEDIMIENTO_BITACORA,
					(SELECT CITMED.T_PROCEDIMIENTO_BASICO.NOMBRE_PROCEDIMIENTO FROM CITMED.T_PROCEDIMIENTO_BASICO WHERE CITMED.T_PROCEDIMIENTO_BASICO.ID_PROCEDIMIENTO= CITMED.T_CITA_BITACORA.ID_PROCEDIMIENTO_BITACORA UNION SELECT CITMED.T_PROCEDIMIENTO_COMPUESTO.NOMBRE_PROCEDIMIENTO_COMP FROM CITMED.T_PROCEDIMIENTO_COMPUESTO WHERE CITMED.T_PROCEDIMIENTO_COMPUESTO.ID_PROCEDIMIENTO_COMP= CITMED.T_CITA_BITACORA.ID_PROCEDIMIENTO_BITACORA),
					CITMED.T_CITA_BITACORA.ID_SALA_BITACORA,
					CITMED.T_SALA.NOMBRE_SALA,
					CITMED.T_CITA_BITACORA.STATUS_CITA_BITACORA,
					CITMED.T_CITA_BITACORA.FORMA_PAGO_CITA_BITACORA,
					CITMED.T_CITA_BITACORA.ANESTESIOLOGO_CITA_BITACORA,
					CITMED.T_CITA_BITACORA.STATUS_PACIENTE_CITA_BITACORA,
					CITMED.T_CITA_BITACORA.TIPO_ID_BITACORA,
					CITMED.T_CITA_BITACORA.ID_CITA,
					TO_CHAR(CITMED.T_CITA_BITACORA.FECHA_STATUS_CITA_BITACORA, 'DD-MM-YYYY HH24:MI:SS'),
					CITMED.T_CITA_BITACORA.USUARIO_CITA_BITACORA,
					CITMED.T_CITA_BITACORA.TIPO_ID_MEDICO_BITACORA
					FROM CITMED.T_CITA_BITACORA, CITMED.T_PACIENTE, CITMED.T_MEDICO, CITMED.T_SALA
					WHERE CITMED.T_CITA_BITACORA.TIPO_ID_BITACORA=CITMED.T_PACIENTE.TIPO_ID AND CITMED.T_CITA_BITACORA.TIPO_ID_MEDICO_BITACORA=CITMED.T_MEDICO.TIPO_ID AND CITMED.T_CITA_BITACORA.ID_PACIENTE_BITACORA=CITMED.T_PACIENTE.ID_PACIENTE AND CITMED.T_CITA_BITACORA.ID_SALA_BITACORA=CITMED.T_SALA.ID_SALA  AND CITMED.T_CITA_BITACORA.ID_MEDICO_BITACORA=CITMED.T_MEDICO.ID_MEDICO";		  
					
					if($_GET['fecha_inicio']!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.FECHA_INICIO_CITA_BITACORA >= to_timestamp('".$_GET['fecha_inicio']."', 'DD-MM-YYYY HH24:MI:SS')";
						
						}
						
					if($_GET['fecha_final']!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.FECHA_FIN_CITA_BITACORA <= to_timestamp('".$_GET['fecha_final']."', 'DD-MM-YYYY HH24:MI:SS')";
						
						}
						
					if($_GET["status_cita"]!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.STATUS_CITA_BITACORA = '".$_GET["status_cita"]."'";
						
						}
						
					if($_GET["sala"]!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.ID_SALA_BITACORA = '".$_GET["sala"]."'";
						
						}
						
					if($_GET["proced"]!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.ID_PROCEDIMIENTO_BITACORA = '".$_GET["proced"]."'";
						
						}
						
					if($_GET["forma"]!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.FORMA_PAGO_CITA_BITACORA = '".$_GET["forma"]."'";
						
						}
						
					if($_GET["aneste"]!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.ANESTESIOLOGO_CITA_BITACORA = '".$_GET["aneste"]."'";
						}
						
					if($_GET["medico"]!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.ID_MEDICO_BITACORA = '".$_GET["medico"]."'";
						}
						
					if($_GET["tipo_id"]!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.TIPO_ID_BITACORA = '".$_GET["tipo_id"]."'";
						}
						
					if($_GET["paciente"]!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.ID_PACIENTE_BITACORA = '".$_GET["paciente"]."'";
						}
						
					if($_GET["tipo_paciente"]!=""){
						$query= $query. " AND CITMED.T_CITA_BITACORA.STATUS_PACIENTE_CITA_BITACORA = '".$_GET["tipo_paciente"]."'";
						}
					
					$query=$query." ORDER BY CITMED.T_CITA_BITACORA.FECHA_INICIO_CITA_BITACORA"; 
					
					$stmt = oci_parse($conec, $query);
					oci_execute($stmt);
					$citas = array();
					$i=4;
					while($row = oci_fetch_array ($stmt, OCI_NUM)){
						
						if($row[15]!= "on"){
							$anes="No";
							}
						else
						{
							$anes="Si";
							}
						
						$nombre_paciente= $row[4]." ".$row[5];
						$nombre_medico = $row[7]." ".$row[8]; 
						
						$date1 = date_create($row[1]);
						$date2 = date_create($row[2]);
						$fecha= date_format($date1, 'd-m-Y');
						$fecha2= date_format($date1, 'G:ia');
						$fecha3= date_format($date2, 'G:ia');
						
						$citas[] = array("Cod.Cita"=>$row[18],"Fecha"=>$fecha,"Inicio Cita"=>$fecha2, "Final Cita" => $fecha3, "Status" => $row[13], "Sala" => $row[12],
						 "Procedimiento" => $row[10], "Pago" => $row[14], "Aneste." => $anes,
						  "Medico" => $nombre_medico, "Paciente" => $nombre_paciente, "Tipo" => $row[16], "Fecha Status" => $row[19], "Usuario" => $row[20]);
						
						
						
						$objPHPExcel->setActiveSheetIndex(0)
									->setCellValue('A'.$i,  $row[18])
									->setCellValue('B'.$i,  $fecha)
									->setCellValue('C'.$i,  $fecha2)
									->setCellValue('D'.$i,  $fecha3)
									->setCellValue('E'.$i,  $row[13])
									->setCellValue('F'.$i,  $row[12])
									->setCellValue('G'.$i,  $row[10])
									->setCellValue('H'.$i,  $row[14])
									->setCellValue('J'.$i,  $anes)
									->setCellValue('K'.$i,  $nombre_medico)
									->setCellValue('L'.$i,  $nombre_paciente)
									->setCellValue('M'.$i,  $row[16])
									->setCellValue('N'.$i,  $row[19])
									->setCellValue('O'.$i,  $row[20]);
									$i++;
						
						}
						
						
		date_default_timezone_set('America/Caracas');
		
		
// adding LOGO to the header
$objDrawing = new PHPExcel_Worksheet_HeaderFooterDrawing();
$objDrawing->setPath('../images/HCCInforme.png');
$objPHPExcel->getActiveSheet()->getHeaderFooter()->addImage($objDrawing, PHPExcel_Worksheet_HeaderFooter::IMAGE_HEADER_LEFT);
$objPHPExcel->getActiveSheet()->getHeaderFooter()->setOddHeader('&L&G');


// Se asignan las propiedades del libro
		$objPHPExcel->getProperties()->setCreator("HCC") //Autor
							 ->setLastModifiedBy("HCC") //Ultimo usuario que lo modificÃ³
							 ->setTitle("Bitacora")
							 ->setSubject("Bitacora")
							 ->setDescription("Bitacora")
							 ->setKeywords("Bitacora")
							 ->setCategory("Reporte excel");
							 
		$tituloReporte = "Bitacora";
		$titles = array('Cod. Cita', 'Fecha', 'Inicio Cita', 'Final Cita', 'Status', 'Sala', 'Procedimiento',
				'Equipo','Pago', 'Aneste.', 'Medico', 'Paciente', 'Tipo', 'Fecha Status','Usuario');
	 


/// Se agregan los titulos del reporte
		$objPHPExcel->setActiveSheetIndex(0)
					->setCellValue('A1',$tituloReporte)
        		    ->setCellValue('A3',  $titles[0])
		            ->setCellValue('B3',  $titles[1])
        		    ->setCellValue('C3',  $titles[2])
            		->setCellValue('D3',  $titles[3])
					->setCellValue('E3',  $titles[4])
					->setCellValue('F3',  $titles[5])
					->setCellValue('G3',  $titles[6])
					->setCellValue('H3',  $titles[7])
					->setCellValue('I3',  $titles[8])
					->setCellValue('J3',  $titles[9])
					->setCellValue('K3',  $titles[10])
					->setCellValue('L3',  $titles[11])
					->setCellValue('M3',  $titles[12])
					->setCellValue('N3',  $titles[13])
					->setCellValue('O3',  $titles[14]);



// Rename worksheet
$objPHPExcel->getActiveSheet()->setTitle('Bitacora');

for($i = 'A'; $i <= 'O'; $i++){
			$objPHPExcel->setActiveSheetIndex(0)			
				->getColumnDimension($i)->setAutoSize(TRUE);
		}


// Set active sheet index to the first sheet, so Excel opens this as the first sheet
$objPHPExcel->setActiveSheetIndex(0);


// Redirect output to a client’s web browser (Excel5)
header('Content-Type: application/vnd.ms-excel');
header('Content-Disposition: attachment;filename="Bitacora.xls"');
header('Cache-Control: max-age=0');
// If you're serving to IE 9, then the following may be needed
header('Cache-Control: max-age=1');

// If you're serving to IE over SSL, then the following may be needed
header ('Expires: Mon, 26 Jul 1997 05:00:00 GMT'); // Date in the past
header ('Last-Modified: '.gmdate('D, d M Y H:i:s').' GMT'); // always modified
header ('Cache-Control: cache, must-revalidate'); // HTTP/1.1
header ('Pragma: public'); // HTTP/1.0

$objWriter = PHPExcel_IOFactory::createWriter($objPHPExcel, 'Excel5');
$objWriter->save('php://output');
exit;
	}
