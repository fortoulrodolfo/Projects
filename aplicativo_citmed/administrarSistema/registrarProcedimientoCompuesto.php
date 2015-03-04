<!DOCTYPE html>

<?php
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	require_once "../extensiones/extensiones.php";
	
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
		<script src="../extensiones/jquery-1.2.6.min.js" type="text/javascript"></script>
        <script src='../js/funcionesAdministrarSistema.js'></script>
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
						<font style="font-size:1.1em; color:#646464;">Registrar Procedimiento Compuesto</font><br>
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
						onClick="window.location.href='<?php echo $link; ?>citmed/administrarSistema/menuOpciones.php';">
					Atras
				</button>
                 <button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="mostrarFormularioCompuesto('registrarCompuesto')">
					Registrar Compuesto
				</button>
                 <button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="mostrarFormularioCompuesto('modificarCompuesto')">
					Actualizar Compuesto
				</button>
                 <button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="mostrarFormularioCompuesto('eliminarCompuesto')">
					Eliminar Compuesto
				</button>
				<button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="window.location='<?php echo $link; ?>sesion/cierreSesion.php'">
					Cerrar Sesión
				</button><br><br><br>
            </center>
            <center>
            	<form action="<?php echo $link; ?>citmed/consultas/enviarProcedimientoCompuesto.php" id="formulario_compuesto" method='post' onSubmit="return validarVacioCompuesto(this);">
                
				<center>
                <font size="5" id="titulo" style="color:#646464;">Registrar Procedimiento Compuesto</font><br><br><br>
                <font size="3" style="color:#646464;">Datos del Procedimiento</font>
				<div id="menuA" align="left" style="height:300px; width:550px; overflow-y: auto;">
                <br>
                <div style="margin-left:10px;">
                
                <table style="width:100%;">
                    <tr>
                        <td width="12%">
                        	<font size="2" style="color:#646464;">Codigo:</font>
                        </td>
                        <td>
                            <input type="text" id="codigo_compuesto" name="codigo_compuesto" value="" size="15" />
                            <a href="#openModal" id="btnBuscar" hidden="true"><img src='<?php echo $link; ?>citmed/images/buscar.png' alt="buscar_compuesto" width="32" height="32"></a>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                        	<font size="2" style="color:#646464;">Nombre:</font>
                        </td>
                        <td>
                            <input type="text" id="nombre_compuesto" name="nombre_compuesto" value="" size="35"/>
                        </td>
                    	 <td>
                        <font size="2" style="color:#646464;">Limpieza:</font>
                         </td> 
                         <td>    
                            <input type="text" id="tiempo_limpieza" name="tiempo_limpieza" value="" size="5" onkeypress="return soloNumeros(event);"/><font size="1" style="color:#646464;">min.</font>
                         </td>
                     </tr>
                     </table>
                     
                     <table border="0" align="center" style="width:95%;">
                        <tr>
                            <td width="40%">
                            <!-- Se cargan los procedimientos basicos de la Unidad, 
                            el select "procedimiento_compuesto2[]" funciona como un arreglo de procedimientos basicos
					  -->
                            	<font size="2" style="color:#646464;">Procedimientos</font><script>cargarProcedimiento('procedimiento_compuesto1');</script>
                                <select style="width:100%" name="procedimiento_compuesto1" id="procedimiento_compuesto1" size="8" multiple="multiple">
                                </select>
                            </td>
                            <td width="20%" align="center" valign="middle">
                                <input type="button" class="link-style" value="Asignar"
                                 onclick="moverSelect(this.form.procedimiento_compuesto1, this.form.procedimiento_compuesto2);" /><br />
                                <input type="button" class="link-style" value="Quitar"
                                 onclick="moverSelect(this.form.procedimiento_compuesto2, this.form.procedimiento_compuesto1);" />
                            </td>
                            <td width="40%">
                            	<font size="2" style="color:#646464;">Proced. Asociados</font>
                                <select multiple="multiple" style="width:100%" name="procedimiento_compuesto2[]" id="procedimiento_compuesto2" value= "" size="8">
                                </select>
                            
                            </td>
                            
                        </tr>
                    </table>
                    
               
                </div>
                </div>
                <!-- Ventana emergente que muestra todos los procedimientos compuestos
                que tiene CITMED, para actualizar o eliminar
					  -->
                <div id="openModal" class="modalDialog">
                <div>
                    <a href="#close" title="Close" class="close">X</a>
                    <h2 id="tituloModal"><font size="3" style="color:#646464;">Procedimientos Compuestos</font></h2>
                    <table class="tabla_compuestos" id="tabla_compuestos" style="width:100%; overflow-y: auto;">
                    <script>cargarCompuestos();</script>
                    </table>
                    <button id="aceptar" type="button" class="link-style" onClick="buscarCompuesto();">Aceptar</button>
                    <button id="cancelar" type="button" class="link-style" onClick="window.location.href='#close';">Cancelar</button>
                </div>
                 </div>
				</center>
					<!-- Variable oculta que indica que tipo de operacion se esta realizando,
                    si es Registrar, Actualizar o Eliminar. Sirve para determinar el comportamiento de
                    la pantalla con las validaciones en el JS.
					  -->
                	<input id="operacionCompuesto" name="operacionCompuesto" value="Registrar" type="hidden"/>
					<!-- Botón de búsqueda y limpiar.
					  -->
					<input type="submit" value="Guardar" id="btnGuardar" class="link-style"/>
                    <button id="limpiar" type="button" class="link-style" onClick="limpiarCompuesto();">Limpiar</button>
				
				<br><br>
				</form>
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
	</body>
</html>
