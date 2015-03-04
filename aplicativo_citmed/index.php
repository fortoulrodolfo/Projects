<!DOCTYPE html>

<?php
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	require_once "extensiones/extensiones.php";
	
	session_start();
	
	header('Content-Type: text/html; charset=utf-8');
	
	/* Se destruye cualquier sesión previamente creada por parte del usuario.
	 */
	session_destroy();
	
	/* Se carga el link de acceso a la aplicación, utilizado para las 
	 * redirecciones internas.
	 */
	$conexiones = simplexml_load_file('conexiones/conexiones.xml');
	$link = $conexiones->link;
	
?>

<html>
	<!-- Se define el head. Título, tipo de contenido, hoja de estilos CSS, 
	  -- funciones de JAVASCRIPT.
	  -->
	<head>
		<title>CITMED</title>
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
			<center>
				<!-- Fotografía de la página.
				  -->
				<img src="images/HCC.png" width="400" height="175" alt="" />
				
				<br><br>
				
				<!-- Menú principal. Acceso a la página web de la institución. 
				  -->
				<div id="menu">
					<ul>
						<li>
							<a href="http://www.clinicaracas.com">
								<font size="3">HCC (Página Web)</font>
							</a>
						</li>
					</ul>
				</div>
			</center>

			<!-- División de tres columnas para la colocación de los datos de 
				 inicio de sesión.
			  -->
			<div id="three-columns">
				<div class="content">
					<div id="column1">
						<h2 style="color:#FFFFFF;">.</h2>
						<ul class="list-style1"></ul>
					</div>

					<div id="column2">
						<center>
							<!-- Título del sistema. 
							  -->
							<font size="4" style="color:#646464;">CITMED</font><br>
							<?php
								/* Se hace el chequeo de la existencia de errores en el inicio de
								 * sesión.
								 */
								errores();
							?>
						</center>
						
						<ul class="list-style1">
							<li>
								<center>
									<div id="menuA" align="left" style="height:240px; width:300px;">
										<center>
											<!-- Se define el formulario de inicio de sesión
												 (Usuario/Contraseña/Aceptar).
											  -->
											<form action='<?php echo $link; ?>citmed/sesion/inicioSesion.php' 
												  method='post' id='formulario' name='formulario'>
												<br>
												<font size="3" style="color:#646464;">Nombre de Usuario:</font><br>
												<input type="text" name="usuario" id="usuario" value="" size="30" 
													   style="text-transform:lowercase;" />
												<br><br>
												<font size="3" style="color:#646464;">Contraseña:</font><br>
												<input type="password" id="clave" name="clave" value="" size="30"/>	
												<br><br>
												<input type="submit" value="Iniciar Sesión" class="link-style" onClick="chequearClave(document.getElementById('clave'));"/>
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