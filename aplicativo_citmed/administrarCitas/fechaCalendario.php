<!DOCTYPE html>

<?php
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	require_once "../extensiones/extensiones.php";
	
	/* Se inicia la sesión.
	 */
	//session_start(); 
	
	header('Content-Type: text/html; charset=utf-8');
	
	/* Se carga el link de acceso a la aplicación, utilizado para las 
	 * redirecciones internas.
	 */
	$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
	$link = $conexiones->link;
	$version = $conexiones->version;
	
	/*chequearUsuario($link);
	chequearCaducado($link);*/
	
?>
 
<html>
	<!-- Se define el head. Título, tipo de contenido, hoja de estilos CSS, 
		 funciones de JAVASCRIPT.
	  -->
	<head>
		<title>Sistema de Aplicaciones Médicas</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link href="../extensiones/style.css" rel="stylesheet" type="text/css" media="all" />
		<script src="../extensiones/funciones.js" language="JavaScript"></script>
        <link rel='stylesheet' href='../extensiones/fullcalendar/lib/cupertino/jquery-ui.min.css' />
		<script src="../extensiones/jquery-1.2.6.min.js" type="text/javascript"></script>
       	<link href='../extensiones/fullcalendar/fullcalendar.css' rel='stylesheet' />
        <link href='../extensiones/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
        <script src='../extensiones/fullcalendar/lib/moment.min.js'></script>
        <script src='../extensiones/fullcalendar/lib/jquery.min.js'></script>
        <script src='../extensiones/fullcalendar/lib/jquery-ui.custom.min.js'></script>
        <script src='../extensiones/fullcalendar/fullcalendar.min.js'></script>
        <script src='../extensiones/fullcalendar/lang-all.js'></script>
        <script src='../js/funcionesAdministrarCitas.js'></script>
        <script>
         $(document).ready(function() {
             cargarFechaCalendario();
         });
        </script>
 
 		<!-- Se establece el tamaño del calendario -->
        
        <style>

			#calendar {
				width: 550px;
				margin: 40px auto;
			}

		</style>
	</head>
	
	<!-- Se define el body (Cuerpo de la página). 
	  -->
	<body onLoad="goforit();">
		<div id="wrapper">
			<br>
			<div id="two-columns" style="width:900px; height:125px;">
				<div id="col2" style="width:350px">
					<!-- Fotografía de la página. Título, subtítulo y bienvenida.
					  -->
					<img src="../images/HCC.png" width="350" height="135" alt="" />
				</div>
				<div id="col1" style="width:550px; float:left;">
					<center>
						<font style="font-size:1.3em; color:#646464;">Sistema de Aplicaciones Médicas</font><br>
						<font style="font-size:1.1em; color:#646464;">Administracion de Citas</font><br>
						<font style="font-size:1.1em; color:#646464;">Bienvenido: </font><br>
						<!-- Reloj a tiempo real. 
						  -->
						<br>
						<font style="font-size:0.9em; color:#646464;">					
							<span id=clock>
								<br>
							</span>
						</font>
					</center>
				</div>
			</div>

			<center>
            	 <button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="window.location.href='<?php echo $link; ?>citmed/index2.php';">
					Atras
				</button>
				<button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="window.location='<?php echo $link; ?>sesion/cierreSesion.php'">
					Cerrar Sesión
				</button><br><br><br>
                
                <!-- Calendario para seleccionar la fecha -->
               <div id='calendar' style="font-size: 10px;"></div>
               
               
			<!-- Pie de página. Acceso a comentarios y sugerencias.
			  -->
			<div id="footer">
				<center>
					<br>
					<font style="font-size:1.1em; color:#646464;"><?php echo $version; ?></font><br>
					<button id="comentarios" type="button" class="link-style"
							onClick="window.open('<?php echo $link; ?>manual/Manual_de_Usuarios.pdf')">
						Manual de Usuarios
					</button>
					<button id="comentarios" type="button" class="link-style"
							onClick="window.location='<?php echo $link; ?>sesion/comentarios.php'">
						Comentarios y Sugerencias
					</button><br><br>
				</center>
			</div>
		</div>
	</body>
</html>