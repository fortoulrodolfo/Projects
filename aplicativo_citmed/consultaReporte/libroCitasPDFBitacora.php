<!DOCTYPE html>

<?php
	/* Se incluye la clase de genera los pdf
	 */
    include('../extensiones/ezpdf/Cezpdf.php');
	
	header("Content-type:application/pdf");
	header("Content-Disposition:attachment;filename=Bitacora.pdf");
	
	$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
	$link = $conexiones->link;
	$userBD = $conexiones->sistemas->usuario;
	$claveBD = $conexiones->sistemas->contrasena;
	$servicioBD = $conexiones->sistemas->servicio;
	
	/* Se lleva a cabo la conexión con la base de datos.
		 */
	$conec = oci_connect($userBD, $claveBD, $servicioBD, 'AL32UTF8');

	$pdf =& new Cezpdf('LETTER', 'landscape');
	$pdf->selectFont('fonts/Helvetica.afm');
	$pdf->setColor(0/255,0/255,0/255);
	
	
	/* Setear el número de páginas, y el pie de página.
	 */
	$pdf->addText(330,520,20,"<b>Bitacora</b>\n", 'center');
	$pdf->addText(600,580,8,"<b>Fecha:</b> ".date("d/m/Y"));
	$pdf->addText(600,570,8,"<b>Hora:</b> ".date("H:i:s")."\n\n"); 
	$pdf->ezStartPageNumbers(775,12,10,'right','<b>Página {PAGENUM} de {TOTALPAGENUM}</b>',1);
	$pdf->ezStartPageNumbers(615,12,10,'right','Av. Panteón con calle Alameda, San Bernardino - Caracas - 1010 Teléfonos: (212)508.61.11',1);
	$pdf->ezStartPageNumbers(670,25,10,'right','_____________________________________________________________________________________________',1);
	 
	$datacreator = array (
						'Title'=>'Bitacora',
						'Subject'=>'PDF',
						'Author'=>'HCC',
						);
	$pdf->addInfo($datacreator);
	
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
						
						}
						
						$titles = array('Cod.Cita'=>'<b>Cod.Cita</b>', 'Fecha'=>'<b>Fecha</b>', 'Inicio Cita'=>'<b>Inicio Cita</b>' , 'Final Cita'=>'<b>Final Cita</b>' , 'Status'=>'<b>Status</b>' , 'Sala'=>'<b>Sala</b>' , 'Procedimiento'=>'<b>Procedimiento</b>' ,
	'Pago'=>'<b>Pago</b>', 'Aneste.'=>'<b>Aneste.</b>', 'Medico'=>'<b>Medico</b>', 'Paciente'=>'<b>Paciente</b>', 'Tipo'=>'<b>Tipo</b>', 'Fecha Status'=>'<b>Fecha Status</b>', 'Usuario'=>'<b>Usuario</b>');
	 
	}
	
	$pdf->ezImage('../images/HCCInforme.png',0, 130, 'none', 'left', '');
	
	$options = array( 
                'shadeCol'=>array(0.9,0.9,0.9), 
                'xOrientation'=>'center', 
                'width'=>740, 
                'fontSize'=>7 
            ); 
	$pdf->ezText("\n\n\n",3);
	$pdf->ezTable($citas,$titles,'',$options );
	 
	$pdf->ezText("\n\n\n",10);
	
	ob_end_clean();
	$pdf->ezStream();
?>