<?php
	class prueba{
	
		
	function prueba(){
		}
		

	function disp_puertos_aba(){
		
	$archivo = $_FILES['excel']['name'];
	
	$tipo = $_FILES['excel']['type'];
	$destino = "archivos/".$archivo;
	$ext=array_pop(explode(".",$archivo));//obtenemos la extension del archivo
		
	if($ext!="xls" and $ext!="xlsx" and $ext!="ods")//si no es un archivo de excel
    	echo "<script>alert('Debe enviar un archivo de excel o openoffice (xls,xlsx,ods)'); window.reload();</script>";
		
	else{
		
	//cargamos el archivo al servidor con el mismo nombre
	
		if (copy($_FILES['excel']['tmp_name'],$destino)) 
		echo "<script>alert('Archivo Cargado Con Exito')</script>";
		else echo "<script>alert('Error Al Cargar el Archivo')</script>";
		
		
	////////////////////////////////////////////////////////
if (file_exists ("archivos/".$archivo)){
	
	set_include_path('C:/xampp/htdocs/cantv2.0/shared/libreria/excel/Classes/'); 
	require_once ('PHPExcel/IOFactory.php');
    
	if($ext=="xlsx"){
        $objReader = PHPExcel_IOFactory::createReader('Excel2007');//si es excel 2007 cargamos su lector
	}
    else
	{
		if($ext=="xls")
		{
        $objReader =  PHPExcel_IOFactory::createReader('Excel5');//si no, cargamos el lector para archivos xls
		}
		else
		 {$objReader = new PHPExcel_Reader_OOCalc();}//si no, cargamos el lector para archivos ods
	}
	
    $objPHPExcel = $objReader->load("archivos/".$archivo);
   	$objWorksheet = $objPHPExcel->setActiveSheetIndex(0);
    $highestRow = $objWorksheet->getHighestRow(); //Leemos cuantas filas tiene e.g. 10
	$objFecha = new PHPExcel_Shared_Date();

	//conectamos con la base de datos

	$cn = mysql_connect ("localhost","root","") or die ("ERROR EN LA CONEXION");
	$db = mysql_select_db ("demanda_proyecto",$cn) or die ("ERROR AL CONECTAR A LA BD");

	// Llenamos el arreglo con los datos  del archivo xlsx

	if(!$highestRow)//si tiene 0 filas
        die("El archivo de excel no contiene informacion o bien esta no es accesible");
	for ($i=21;$i<=$highestRow-2;$i++){
		$_DATOS_EXCEL[$i]['cod_central'] = $objWorksheet->getCell('D'.$i)->getCalculatedValue();
		$_DATOS_EXCEL[$i]['disponibilidad'] = $objWorksheet->getCell('I'.$i)->getCalculatedValue();

			}
	}
	else{echo "Necesitas primero importar el archivo";}

	$errores=0;

	foreach($_DATOS_EXCEL as $campo => $valor){
			$sql = "INSERT INTO auxiliar VALUES (NULL,'";
			foreach ($valor as $campo2 => $valor2){
			$campo2 == "disponibilidad" ? $sql.= $valor2."');" : $sql.= $valor2."','";
			}
		$result = mysql_query($sql);
			if (!$result){ echo "Error al insertar registro ".$campo;$errores+=1;}
			}	
/////////////////////////////////////////////////////////////////////////

	echo "<script>alert('ARCHIVO IMPORTADO CON EXITO, EN TOTAL $campo REGISTROS Y $errores ERRORES')</script>";
	
	unlink($destino);
		
		$sql2="UPDATE central SET disp_aba=(Select SUM(disponibilidad) from auxiliar where cod_central=central.ubcc GROUP BY cod_central)";
		$a = mysql_query($sql2) or die(mysql_error());
		
		$sql3="TRUNCATE auxiliar";
		$a2 = mysql_query($sql3) or die(mysql_error());

	}
	
	}
		

	function disp_puertos_aba_ngn(){
		
		
	$archivo = $_FILES['excel']['name'];
	$tipo = $_FILES['excel']['type'];
	$destino = "archivos/".$archivo;
	$ext=array_pop(explode(".",$archivo));//obtenemos la extension del archivo
		
	if($ext!="xls" and $ext!="xlsx" and $ext!="ods")//si no es un archivo de excel
    	echo "<script>alert('Debe enviar un archivo de excel o openoffice (xls,xlsx,ods)')</script>";
	else{

//cargamos el archivo al servidor con el mismo nombre

		if (copy($_FILES['excel']['tmp_name'],$destino)) echo "<script>alert('Archivo Cargado Con Exito')</script>";
		else echo "<script>alert('Error Al Cargar el Archivo')</script>";
	
////////////////////////////////////////////////////////

	if (file_exists ("archivos/".$archivo)){
	set_include_path('C:/xampp/htdocs/cantv2.0/shared/libreria/excel/Classes/'); 
	require_once ('PHPExcel/IOFactory.php');
    
	if($ext=="xlsx"){
        $objReader = PHPExcel_IOFactory::createReader('Excel2007');//si es excel 2007 cargamos su lector
	}
    else
	{
		if($ext=="xls")
		{
        $objReader =  PHPExcel_IOFactory::createReader('Excel5');//si no, cargamos el lector para archivos xls
		}
		else
		 {$objReader = new PHPExcel_Reader_OOCalc();}//si no, cargamos el lector para archivos ods
	}
	
   
    $objPHPExcel = $objReader->load("archivos/".$archivo);
   	$objWorksheet = $objPHPExcel->setActiveSheetIndex(1);
    $highestRow = $objWorksheet->getHighestRow(); //Leemos cuantas filas tiene e.g. 10
	$objFecha = new PHPExcel_Shared_Date();

	//conectamos con la base de datos

	$cn = mysql_connect ("localhost","root","") or die ("ERROR EN LA CONEXION");
	$db = mysql_select_db ("demanda_proyecto",$cn) or die ("ERROR AL CONECTAR A LA BD");

	// Llenamos el arreglo con los datos  del archivo xlsx

	if(!$highestRow)//si tiene 0 filas
        die("El archivo de excel no contiene informacion o bien esta no es accesible");
	for ($i=23;$i<=$highestRow-2;$i++){
		$_DATOS_EXCEL[$i]['cod_central'] = $objWorksheet->getCell('D'.$i)->getCalculatedValue();
		$_DATOS_EXCEL[$i]['disponibilidad'] = $objWorksheet->getCell('I'.$i)->getCalculatedValue();

			}
	
	}
	else{echo "Necesitas primero importar el archivo";}

	$errores=0;

	foreach($_DATOS_EXCEL as $campo => $valor){
			$sql = "INSERT INTO auxiliar VALUES (NULL,'";
			foreach ($valor as $campo2 => $valor2){
			$campo2 == "disponibilidad" ? $sql.= $valor2."');" : $sql.= $valor2."','";
			}
		$result = mysql_query($sql);
			if (!$result){ echo "Error al insertar registro ".$campo;$errores+=1;}
			}	
/////////////////////////////////////////////////////////////////////////

	echo "<script>alert('ARCHIVO IMPORTADO CON EXITO, EN TOTAL $campo REGISTROS Y $errores ERRORES')</script>";

		unlink($destino);
		
		$sql2="UPDATE central SET disp_abangn=(Select SUM(disponibilidad) from auxiliar where cod_central=central.ubcc GROUP BY cod_central)";
		$a = mysql_query($sql2) or die(mysql_error());
		
		$sql3="TRUNCATE auxiliar";
		$a2 = mysql_query($sql3) or die(mysql_error());

	}
	}
	
	
	function disp_datos(){
		
		
	$archivo = $_FILES['excel']['name'];
	$tipo = $_FILES['excel']['type'];
	$destino = "archivos/".$archivo;
	$ext=array_pop(explode(".",$archivo));//obtenemos la extension del archivo
		
	if($ext!="xls" and $ext!="xlsx" and $ext!="ods")//si no es un archivo de excel
    	echo "<script>alert('Debe enviar un archivo de excel o openoffice (xls,xlsx,ods)')</script>";
	else{

//cargamos el archivo al servidor con el mismo nombre
		if (copy($_FILES['excel']['tmp_name'],$destino)) echo "<script>alert('Archivo Cargado Con Exito')</script>";
		else echo "<script>alert('Error Al Cargar el Archivo')</script>";
	
////////////////////////////////////////////////////////

	if (file_exists ("archivos/".$archivo)){
	set_include_path('C:/xampp/htdocs/cantv2.0/shared/libreria/excel/Classes/'); 
	require_once ('PHPExcel/IOFactory.php');
    
	if($ext=="xlsx"){
        $objReader = PHPExcel_IOFactory::createReader('Excel2007');//si es excel 2007 cargamos su lector
	}
    else
	{
		if($ext=="xls")
		{
        $objReader =  PHPExcel_IOFactory::createReader('Excel5');//si no, cargamos el lector para archivos xls
		}
		else
		 {$objReader = new PHPExcel_Reader_OOCalc();}//si no, cargamos el lector para archivos ods
	}
	
   
    $objPHPExcel = $objReader->load("archivos/".$archivo);
   	$objWorksheet = $objPHPExcel->setActiveSheetIndex(1);
    $highestRow = $objWorksheet->getHighestRow(); //Leemos cuantas filas tiene e.g. 10
	$objFecha = new PHPExcel_Shared_Date();

	//conectamos con la base de datos

	$cn = mysql_connect ("localhost","root","") or die ("ERROR EN LA CONEXION");
	$db = mysql_select_db ("demanda_proyecto",$cn) or die ("ERROR AL CONECTAR A LA BD");

	// Llenamos el arreglo con los datos  del archivo xlsx

	if(!$highestRow)//si tiene 0 filas
        die("El archivo de excel no contiene informacion o bien esta no es accesible");
	for ($i=3;$i<=$highestRow;$i++){
		$_DATOS_EXCEL[$i]['cod_central'] = $objWorksheet->getCell('F'.$i)->getCalculatedValue();
		$_DATOS_EXCEL[$i]['disponibilidad'] = $objWorksheet->getCell('M'.$i)->getCalculatedValue();

			}
	
	}
	else{echo "Necesitas primero importar el archivo";}

	$errores=0;

	foreach($_DATOS_EXCEL as $campo => $valor){
			$sql = "INSERT INTO auxiliar VALUES (NULL,'";
			foreach ($valor as $campo2 => $valor2){
			$campo2 == "disponibilidad" ? $sql.= $valor2."');" : $sql.= $valor2."','";
			}
		$result = mysql_query($sql);
			if (!$result){ echo "Error al insertar registro ".$campo;$errores+=1;}
			}	
/////////////////////////////////////////////////////////////////////////

	echo "<script>alert('ARCHIVO IMPORTADO CON EXITO, EN TOTAL $campo REGISTROS Y $errores ERRORES')</script>";

		unlink($destino);
		
		$sql2="UPDATE central SET disp_metro=(Select SUM(disponibilidad) from auxiliar where cod_central=central.ubcc GROUP BY cod_central)";
		$a = mysql_query($sql2) or die(mysql_error());
		
		$sql3="TRUNCATE auxiliar";
		$a2 = mysql_query($sql3) or die(mysql_error());

	}
	
	}
	
	function disp_voz(){
		
	$archivo = $_FILES['excel']['name'];
	$tipo = $_FILES['excel']['type'];
	$destino = "archivos/".$archivo;
	$ext=array_pop(explode(".",$archivo));//obtenemos la extension del archivo
		
	if($ext!="xls" and $ext!="xlsx" and $ext!="ods")//si no es un archivo de excel
    	echo "<script>alert('Debe enviar un archivo de excel o openoffice (xls,xlsx,ods)')</script>";
	else{
	//cargamos el archivo al servidor con el mismo nombre
		if (copy($_FILES['excel']['tmp_name'],$destino)) echo "<script>alert('Archivo Cargado Con Exito')</script>";
		else echo "<script>alert('Error Al Cargar el Archivo')</script>";

	
	////////////////////////////////////////////////////////

	if (file_exists ("archivos/".$archivo)){
		
	set_include_path('C:/xampp/htdocs/cantv2.0/shared/libreria/excel/Classes/'); 
	require_once ('PHPExcel/IOFactory.php');
    
	if($ext=="xlsx"){
        $objReader = PHPExcel_IOFactory::createReader('Excel2007');//si es excel 2007 cargamos su lector
	}
    else
	{
		if($ext=="xls")
		{
        $objReader =  PHPExcel_IOFactory::createReader('Excel5');//si no, cargamos el lector para archivos xls
		}
		else
		 {$objReader = new PHPExcel_Reader_OOCalc();}//si no, cargamos el lector para archivos ods
	}
	
   
    $objPHPExcel = $objReader->load("archivos/".$archivo);
   	$objWorksheet = $objPHPExcel->setActiveSheetIndex(0);
    $highestRow = $objWorksheet->getHighestRow(); //Leemos cuantas filas tiene e.g. 10
	$objFecha = new PHPExcel_Shared_Date();

	//conectamos con la base de datos

	$cn = mysql_connect ("localhost","root","") or die ("ERROR EN LA CONEXION");
	$db = mysql_select_db ("demanda_proyecto",$cn) or die ("ERROR AL CONECTAR A LA BD");

	// Llenamos el arreglo con los datos  del archivo xlsx

	if(!$highestRow)//si tiene 0 filas
        die("El archivo de excel no contiene informacion o bien esta no es accesible");
	for ($i=7;$i<=$highestRow;$i++){
		
		$_DATOS_EXCEL[$i]['cod_central'] = $objWorksheet->getCell('B'.$i)->getCalculatedValue();
		$_DATOS_EXCEL[$i]['disponibilidad'] = $objWorksheet->getCell('M'.$i)->getCalculatedValue();

			}
	}
	else{echo "Necesitas primero importar el archivo";}

	$errores=0;


	foreach($_DATOS_EXCEL as $campo => $valor){
			$sql = "INSERT INTO auxiliar VALUES (NULL,'";
			foreach ($valor as $campo2 => $valor2){
			$campo2 == "disponibilidad" ? $sql.= $valor2."');" : $sql.= $valor2."','";
			}
		$result = mysql_query($sql);
			if (!$result){ echo "Error al insertar registro ".$campo;$errores+=1;}
			}	
/////////////////////////////////////////////////////////////////////////

	echo "<script>alert('ARCHIVO IMPORTADO CON EXITO, EN TOTAL $campo REGISTROS Y $errores ERRORES')</script>";

		unlink($destino);
		
		$sql2="UPDATE central SET disp_voz=(Select SUM(disponibilidad) from auxiliar where cod_central=central.ubcc GROUP BY cod_central)";
		$a = mysql_query($sql2) or die(mysql_error());
		
		$sql3="TRUNCATE auxiliar";
		$a2 = mysql_query($sql3) or die(mysql_error());

	}
	}

}


?>
		