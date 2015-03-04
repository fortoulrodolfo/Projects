<!DOCTYPE html>

<?php
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	require_once "extensiones/extensiones.php";
	
	/* Se inicia la sesión.
	 */
	//session_start(); 
	
	header('Content-Type: text/html; charset=utf-8');
	
	/* Se carga el link de acceso a la aplicación, utilizado para las 
	 * redirecciones internas.
	 */
	
	$conexiones = simplexml_load_file('conexiones/conexiones.xml');
	$link = $conexiones->link;
	$version = $conexiones->version;
	/*
	chequearUsuario($link);
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
		<link href="extensiones/style.css" rel="stylesheet" type="text/css" media="all" />
		<script src="extensiones/funciones.js" language="JavaScript"></script>
		<script src="extensiones/jquery-1.2.6.min.js" type="text/javascript"></script>
		<script type="text/javascript">
		
				$(document).ready(function(){
					centraVentana();
					muestraVentana();
					$("#ventanaPopup1Cerrar").click(function(){
						ocultaVentana();
					});
					$("#ventanaPopup1Fondo").click(function(){
						ocultaVentana();
					});
				});
		
			function centraVentana() {
				var windowWidth = document.documentElement.clientWidth;
				var windowHeight = document.documentElement.clientHeight;
				var popupHeight = $("#ventanaPopup1").height();
				var popupWidth = $("#ventanaPopup1").width();
				$("#ventanaPopup1").css({
					"position": "absolute",
					"top": windowHeight/2-popupHeight/2,
					"left": windowWidth/2-popupWidth/2
				});
			 
				$("#ventanaPopup1Fondo").css({
					"height": windowHeight
				});
			}
			 
			function ocultaVentana() {
				$("#ventanaPopup1Fondo").fadeOut("slow");
				$("#ventanaPopup1").fadeOut("slow");
			}
			 
			function muestraVentana() {
				$("#ventanaPopup1Fondo").css({
					"opacity": "0.7"
				});
				$("#ventanaPopup1Fondo").fadeIn("slow");
				$("#ventanaPopup1").fadeIn("slow");
			}
		</script>
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
					<img src="images/HCC.png" width="350" height="135" alt="" />
				</div>
				<div id="col1" style="width:550px; float:left;">
					<center>
						<font style="font-size:1.3em; color:#646464;">Sistema de Aplicaciones Médicas</font><br>
						<font style="font-size:1.1em; color:#646464;">Selección de Operaciones</font><br>
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
						onClick="window.location='<?php echo $link; ?>sesion/cierreSesion.php'">
					Cerrar Sesión
				</button><br><br><br>
			</center>
			
			<center>
				<button id="sesion" type="button" class="link-style" style="width:400px;"
						 onClick="window.location='index2.php'">
					Citas-Medicas
				</button><br><br><br>
			</center>

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
		<div id="ventanaPopup1">
			<a id="ventanaPopup1Cerrar" style="cursor:pointer;">X</a>
			<center>
				<img src="images/HCC.png" width="275" height="100" alt="" /><br><br>
				<font style="font-size:1.5em; color:#646464;">¡NOVEDADES!</font><br><br>
			</center>
			<div style="width:500px;">
				<font style="font-size:1.1em; color:#646464;">
					<ul>
						<li>En las opciones "<i><u>Relación de Pago de Honorarios</u></i>", "<i><u>Libro de 
							Ventas</u></i>", "<i><u>Servicio Prestado por Terceros</u></i>" y "<i><u>Estado de 
							Cuenta por Honorarios</u></i>", se incluyó el botón "<b>Acerca De...</b>", que presenta 
							la definición del contenido de estos reportes.
						</li>
					</ul>
					<center><img src="images/acercaDe.png" width="160" height="30" alt=""/></center>
				</font><br><br>
				<font style="font-size:0.9em; color:#6fa5fd;">
					<center><b><i>"Dirección de Tecnología de la Información, hacemos que todo sea más fácil"</i></b></center>
				</font>
			</div>
		</div>
		<div id="ventanaPopup1Fondo"></div>
	</body>
</html>