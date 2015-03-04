<!DOCTYPE html>

<?php
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	require_once "extensiones/extensiones.php";
	
	/* Se inicia la sesión.
	 */
	session_start();
	
	header('Content-Type: text/html; charset=utf-8');
	
	/* Se carga el link de acceso a la aplicación, utilizado para las 
	 * redirecciones internas.
	 */
	$conexiones = simplexml_load_file('../conexiones/conexiones.xml');
	$link = $conexiones->link;
	
	chequearUsuario($link);
	chequearCaducado($link);
?>

<html>
	<!-- Se define el head. Título, tipo de contenido, hoja de estilos CSS, 
	  -- funciones de JAVASCRIPT.
	  -->
	<head>
		<title>Trackex</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<link href="extensiones/style.css" rel="stylesheet" type="text/css" media="all"/>
		<script src="extensiones/funciones.js" language="JavaScript"></script>
		<script src="extensiones/md5.js" language="JavaScript"></script>
	</head>

	<!-- Se define el body (Cuerpo de la página). 
	  -->
	<body>
		<div id="wrapper">
			<br>
			<div id="two-columns" style="width:900px; height:150px;">
				<div id="col2" style="width:350px">
					<!-- Fotografía de la página. Título, subtítulo y bienvenida.
					  -->
					<img src="images/HCC.png" width="350" height="135" alt="" />
				</div>
				<div id="col1" style="width:550px; float:left;">
					<center>
						<font style="font-size:1.3em; color:#646464;">Trackex</font><br>
						<font style="font-size:1.1em; color:#646464;">Cambio de Contraseña</font><br>
						<font style="font-size:1.1em; color:#646464;">Bienvenido: <?php echo $_SESSION["nombre"] ?></font><br>
						<!-- Cierre de sesión. 
						  -->
						<button id="sesion" type="button" class="link-style" style="width:150px;"
									onClick="window.location='<?php echo $link; ?>sesion/cierreSesion.php'">
							Cerrar Sesión
						</button><br><br>
						<!-- Reloj a tiempo real. 
						  -->
						<font style="font-size:0.9em; color:#646464;">					
							<span id=clock>
								<br>
							</span>
						</font>
					</center>
				</div>
			</div>

			<!-- División de tres columnas para la colocación de los datos de 
				 inicio de sesión.
			  -->
			<div id="three-columns" style="padding: 0px 0px 0px 0px;">
				<div class="content">
					<div id="column1">
						<h2 style="color:#FFFFFF;">.</h2>
						<ul class="list-style1"></ul>
					</div>

					<div id="column2">
						<center>
							<!-- Título del sistema. 
							  -->
							<font size="4" style="color:#646464;">Trackex</font><br>
							<font size="3" color="#FF0000">Contraseña Caducada<?php nuevaCont(); ?></font>
						</center>
						
						<ul class="list-style1">
							<li>
								<center>
									<div id="menuA" align="left" style="height:320px; width:300px;">
										<center>
											<!-- Se define el formulario de cambio de contraseña
												 (Contraseña Anterior/Contraseña Nueva/Cambiar).
											  -->
											<form action='<?php echo $link; ?>sesion/guardarNueva.php' 
												  method='post'>
												<br>
												<font size="3" style="color:#646464;">Contraseña Anterior:</font><br>
												<input type="password" name="anterior" id="anterior" value="" size="30"/>
												<br><br>
												<font size="3" style="color:#646464;">Nueva Contraseña:</font><br>
												<input type="password" name="nueva" id="nueva" value="" size="30"
													   onChange="cuantosCaracteres()"/>	
												<br><br>
												<font size="3" style="color:#646464;">Repita la Nueva Contraseña:</font><br>
												<input type="password" name="nuevaAg" id="nuevaAg" value="" size="30"/>	
												<br><br>
												<input type="submit" id="cambiar" value="Cambiar" class="link-style" DISABLED
													   onClick="chequearClave(document.getElementById('anterior'));
																chequearClave(document.getElementById('nueva'));
																chequearClave(document.getElementById('nuevaAg'));"/>
											</form>
										</center>
									</div>
								</center>
							</li>
						</ul>
					</div>

					<div id="column3">
						<h2 style="color:#FFFFFF;">.</h2>
						<ul class="list-style1"></ul>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>