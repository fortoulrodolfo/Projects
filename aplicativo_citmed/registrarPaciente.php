<!DOCTYPE html>

<?php
	/* Se incluye el archivo de extensiones con las funciones necesarias en el
	 * servidor.
	 */
	require_once "extensiones/extensiones.php";
	
	header('Content-Type: text/html; charset=utf-8');
	
	/* Se carga el link de acceso a la aplicación, utilizado para las 
	 * redirecciones internas.
	 */
	$conexiones = simplexml_load_file('conexiones/conexiones.xml');
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
		<link href="extensiones/style.css" rel="stylesheet" type="text/css" media="all" />
        <link href="extensiones/jquery-ui-1.10.1.min.css" rel="stylesheet" />
		<script src="extensiones/funciones.js" language="JavaScript"></script>
        <script src="extensiones/modernizr-2.6.2.min.js"></script>
		<script src="extensiones/jquery-1.2.6.min.js" type="text/javascript"></script>
        <script src="extensiones/jquery-1.9.1.min.js"></script>
        <script src="extensiones/jquery-ui-1.10.1.min.js"></script>
        <script src='js/funcionesRegistrarPaciente.js'></script>
        <script>
		 $(document).ready(function() {
			 
			 registrarPacienteCita();
			 
		   });
		
		
		$(function() {
			if (!Modernizr.inputtypes['date']) {
				$('input[type=date]').datepicker({
					dateFormat: 'dd-mm-yy'
				});
			}
		});
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
						<font style="font-size:1.1em; color:#646464;">Registrar Paciente</font><br>
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
						onClick="mostrarFormularioPaciente('registrarPaciente')">
					Registrar Paciente
				</button>
                 <button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="mostrarFormularioPaciente('modificarPaciente')">
					Actualizar Paciente
				</button>
                 <button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="mostrarFormularioPaciente('eliminarPaciente')">
					Eliminar Paciente
				</button>
				<button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="window.location='<?php echo $link; ?>sesion/cierreSesion.php'">
					Cerrar Sesión
				</button><br><br><br>
            </center>
            <center>
            	<form action="<?php echo $link; ?>citmed/consultas/enviarPaciente.php" id="formulario_paciente" method='post' onSubmit="return validarVacioPaciente(this);">
                
				<center>
                <font size="5" id="titulo" style="color:#646464;">Registrar Paciente</font><br><br><br>
                <font size="3" style="color:#646464;">Datos del Paciente</font>
				<div id="menuA" align="left" style="height:200px; width:630px; overflow-y: auto;">
                <br>
                <div style="margin-left:10px;">
                
                <table style="width:600px">
                    <tr>
                        <td>
                        	<font size="2" style="color:#646464;">Cedúla:</font>
                        </td>
                        <td>
                            <select id="tipo_id"  name="tipo_id" >
                                                <font size="1">
                                                    <option value="" SELECTED></option>
                                                    <option value="A">A</option>
                                                    <option value="E">E</option>
                                                    <option value="M">M</option>
                                                    <option value="P">P</option>
                                                    <option value="V">V</option>
                                                </font>
                            </select>
                            <input type="text" id="codigo_paciente" name="codigo_paciente" value="" size="15" onBlur="buscarPacienteSERVINTE(this.value,document.getElementById('tipo_id').value);" />
                            <a onClick="buscarPaciente();" id="btnBuscar" hidden="true"><img src='<?php echo $link; ?>citmed/images/buscar.png' alt="buscar_servicio" width="32" height="32"></a>
                        </td>
                    </tr>
                    
                    <tr>
                    	
                        <td>
                        	<font size="2" style="color:#646464;">Nombre:</font>
                       </td>
                        <td>
                            <input type="text" id="nombre_paciente" name="nombre_paciente" value="" size="25"/>
                        </td>
                        <td></td>
                        <td></td>
                     </tr>
                     
                      <tr>
                        <td>
                        	<font size="2" style="color:#646464;">Primer Apellido:</font>
                       </td>
                        <td>
                           <input type="text" id="apellido1_paciente" name="apellido1_paciente" value="" size="10"/>
                        </td>
                        <td >
                           <font size="2" style="color:#646464;">Segundo Apellido:</font>
                        </td>
                        <td>
                          <input type="text" id="apellido2_paciente" name="apellido2_paciente" value="" size="10"/>
               			 </td>
                     </tr>
                     
                      <tr>
                        <td>
                        	 <font size="2" style="color:#646464;">Género:</font>
                       </td>
                        <td>
                          <select id="sexo_paciente"  name="sexo_paciente">
									<font size="2">
										<option value="" SELECTED></option>
										<option value="F">Femenino</option>
										<option value="M">Masculino</option>
									</font>
							</select>	
                        </td>
                        <td>
                          <font size="2" style="color:#646464; ">Fecha de Nac.:</font>
                        </td>
                        <td>
                          <input type="date" id="fnac_paciente" name="fnac_paciente"  size="20"/>
               			 </td>
                     </tr>
                     
                     <tr>
                        <td>
                        	<font size="2" style="color:#646464;">Teléfono:</font>
                       </td>
                        <td>
                             <input type="text" id="telf_paciente" name="telf_paciente" value="" size="10"/>
                        </td>
                        <td></td>
                        <td></td>
                     </tr>
                     
                </table>
                </div>
                </div>
				</center>
				
                	<input id="operacionPaciente" name="operacionPaciente" value="Registrar" type="hidden"/>
                    <input id="fecha_cita" name="fecha_cita" value="" type="hidden" />
					<!-- Botón de búsqueda y limpiar.
					  -->
					<input type="submit" value="Guardar" id="btnGuardar" class="link-style"/>
                    <button id="limpiar" type="button" class="link-style" onClick="limpiarPaciente();">Limpiar</button>
				
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
