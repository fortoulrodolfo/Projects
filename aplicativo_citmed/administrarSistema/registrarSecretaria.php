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
						<font style="font-size:1.1em; color:#646464;">Registrar Secretaria</font><br>
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
						onClick="mostrarFormularioSecretaria('registrarSecretaria')">
					Registrar Secretaria
				</button>
                 <button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="mostrarFormularioSecretaria('modificarSecretaria')">
					Actualizar Secretaria
				</button>
                 <button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="mostrarFormularioSecretaria('eliminarSecretaria')">
					Eliminar Secretaria
				</button>
				<button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="window.location='<?php echo $link; ?>sesion/cierreSesion.php'">
					Cerrar Sesión
				</button><br><br><br>
            </center>
            <center>
            	<form action="<?php echo $link; ?>citmed/consultas/enviarSecretaria.php" id="formulario_secretaria" method='post' onSubmit="return validarVacioSecretaria(this);">
                
				<center>
                <font size="5" id="titulo" style="color:#646464;">Registrar Secretaria</font><br><br><br>
                <font size="3" style="color:#646464;">Datos del Secretaria</font>
				<div id="menuA" align="left" style="height:300px; width:550px; overflow-y: auto;">
                <br>
                <div style="margin-left:10px;">
                
                <table style="width:100%;">
                    <tr>
                        <td width="12%">
                        	<font size="2" style="color:#646464;">Cedula:</font>
                        </td>
                        <td>
                        	<select id="tipo_id_secre"  name="tipo_id_secre" >
                                                <font size="1">
                                                    <option value="" SELECTED></option>
                                                    <option value="A">A</option>
                                                    <option value="E">E</option>
                                                    <option value="M">M</option>
                                                    <option value="P">P</option>
                                                    <option value="V">V</option>
                                                </font>
                            </select>
                            <input type="text" id="codigo_secretaria" name="codigo_secretaria" value="" size="15" />
                            <!-- Boton que muestra la ventana emergente con las secretarias
					  			-->
                            <a href="#openModal" id="btnBuscar" hidden="true"><img src='<?php echo $link; ?>citmed/images/buscar.png' alt="buscar_Secretaria" width="32" height="32"></a>
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                        	<font size="2" style="color:#646464;">Nombre:</font>
                        </td>
                        <td>
                            <input type="text" id="nombre_secretaria" name="nombre_secretaria" value="" size="25"/>
                        </td>
                    
                        <td>
                        	<font size="2" style="color:#646464;">Apellido:</font>
                        </td>
                        <td>
                         	<input type="text" id="apellido_secretaria" name="apellido_secretaria" value="" size="25"/>
                        </td>
                     </tr>
                     </table>
                     
                     <table style="width:100%;">
                       <tr>
                        <td width="12%">
                        	 <font size="2" style="color:#646464;">Status:</font>
                       </td>
                        <td>
                          <select id="status_secretaria"  name="status_secretaria">
									<font size="2">
										<option value="" SELECTED></option>
										<option value="Activo">Activo</option>
										<option value="Inactivo">Inactivo</option>
									</font>
							</select>	
                        </td>
                     </tr>
               		 </table>
                    
						<!-- Tabla con los medicos disponibles de la clinica y se pueden crear
                        las asociaciones entre medicos y secretarias, el select medico_secretaria2[] funciona como un arreglo
					  			-->
                     <table border="0" align="center" style="width:70%;">
                        <tr>
                            <td width="40%">
                            	<font size="2" style="color:#646464;">Médicos</font>
                                <select style="width:100%" name="medico_secretaria1" id="medico_secretaria1" size="6" multiple="multiple"><script>cargarMedico('medico_secretaria1');</script>
                                </select>
                            </td>
                            <td width="20%" align="center" valign="middle">
                                <input type="button" class="link-style" value="Asignar"
                                 onclick="moverSelect(this.form.medico_secretaria1, this.form.medico_secretaria2);" /><br />
                                <input type="button" class="link-style" value="Quitar"
                                 onclick="moverSelect(this.form.medico_secretaria2, this.form.medico_secretaria1);" />
                            </td>
                            <td width="40%">
                            	<font size="2" style="color:#646464;">Médicos Asociados</font>
                                <select multiple="multiple" style="width:100%" name="medico_secretaria2[]" id="medico_secretaria2" value= "" size="6">
                                </select>
                            
                            </td>
                        </tr>
                    </table>
                    
               
                </div>
                </div>
                <!-- Ventana emergente que muestra todas las secretarias del sistema,
                para Actualizar o Eliminar
					  			-->
                <div id="openModal" class="modalDialog">
                <div>
                    <a href="#close" title="Close" class="close">X</a>
                    <h2 id="tituloModal"><font size="3" style="color:#646464;">Secretarias</font></h2>
                    <table class="tabla_secretarias" id="tabla_secretarias" style="width:100%; overflow-y: auto;">
                    <script>cargarSecretarias();</script>
                    </table>
                    <button id="aceptar" type="button" class="link-style" onClick="buscarSecretaria();">Aceptar</button>
                    <button id="cancelar" type="button" class="link-style" onClick="window.location.href='#close';">Cancelar</button>
                </div>
                 </div>
				</center>
					<!-- Variable oculta que indica que tipo de operacion se esta realizando,
                    si es Registrar, Actualizar o Eliminar. Sirve para determinar el comportamiento de
                    la pantalla con las validaciones en el JS.
					  -->
                	<input id="operacionSecretaria" name="operacionSecretaria" value="Registrar" type="hidden"/>
					<!-- Botón de búsqueda y limpiar.
					  -->
					<input type="submit" value="Guardar" id="btnGuardar" class="link-style"/>
                    <button id="limpiar" type="button" class="link-style" onClick="limpiarSecretaria();">Limpiar</button>
				
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
