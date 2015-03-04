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
        <link rel="stylesheet" type="text/css" href="../extensiones/jquery-ui-1.7.2.custom.css" />
		<script type="text/javascript" src="../extensiones/jquery.min.js"></script>
		<script type="text/javascript" src="../extensiones/jquery-ui.min.js"></script>
		<script src="../extensiones/funciones.js" language="JavaScript"></script>
        <script src='../js/funcionesReportes.js'></script>
        <script>
			/* Función para establecer el calendario en español.
			 */
			jQuery(function($){
				$.datepicker.regional['es'] = {
				closeText: 'Cerrar',
				prevText: '&#x3c;Ant',
				nextText: 'Sig&#x3e;',
				currentText: 'Hoy',
				monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
				'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
				monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
				'Jul','Ago','Sep','Oct','Nov','Dic'],
				dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
				dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
				dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
				weekHeader: 'Sm',
				dateFormat: 'dd-mm-yy',
				firstDay: 1,
				isRTL: false,
				showMonthAfterYear: false,
				yearSuffix: ''};
				$.datepicker.setDefaults($.datepicker.regional['es']);
			});
		
			/* Asignar el calendario al cuadro d texto determinado.
			 */
			$(document).ready(function() {
				$("#fecha_inicio").datepicker();
				$("#fecha_final").datepicker();
				cargarCitas();
			});
			
			/* Animación con los botones de exportar.
			 */
			$(function() {	
				$('#export').toggle(function () {
					$('#export').animate({"top":"5px"},"slow");
					$('#pdf').css({visibility: 'visible'});
					$('#xls').css({visibility: 'visible'});
					$('#cvs').css({visibility: 'visible'});
					$('#word').css({visibility: 'visible'});
					$('#xml').css({visibility: 'visible'});
					$('#pdf').animate({opacity: '1.0'}, "slow");
					$('#xls').animate({opacity: '1.0'}, "slow");
					$('#cvs').animate({opacity: '1.0'}, "slow");
					$('#word').animate({opacity: '1.0'}, "slow");
					$('#xml').animate({opacity: '1.0'}, "slow");
					$('#infgen').animate({opacity: '1.0'}, "slow");
				}, function() {
					$('#export').animate({"top":"20px"},"slow");
					$('#pdf').animate({opacity: '0.0'}, "slow");
					$('#xls').animate({opacity: '0.0'}, "slow");
					$('#cvs').animate({opacity: '0.0'}, "slow");
					$('#word').animate({opacity: '0.0'}, "slow");
					$('#xml').animate({opacity: '0.0'}, "slow");
					$('#pdf').css({visibility: 'hidden'});
					$('#xls').css({visibility: 'hidden'});
					$('#cvs').css({visibility: 'hidden'});
					$('#word').css({visibility: 'hidden'});
					$('#xml').css({visibility: 'hidden'});
					$('#infgen').css({visibility: 'hidden'});
				});
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
					<img src="../images/HCC.png" width="350" height="135" alt="" />
				</div>
				<div id="col1" style="width:550px; float:left;">
					<center>
						<font style="font-size:1.3em; color:#646464;">Sistema de Aplicaciones Médicas</font><br>
						<font style="font-size:1.1em; color:#646464;">Libro de Citas</font><br>
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
						onClick="window.location.href='<?php echo $link; ?>citmed/consultaReporte/menuOpciones.php';">
					Atras
				</button>
				<button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="window.location='<?php echo $link; ?>sesion/cierreSesion.php'">
					Cerrar Sesión
				</button><br><br><br>
             </center>
             
             <center>
             	
              		<font size="5" id="titulo" style="color:#646464;">Consulta de Citas</font><br><br><br>
					<div id="menu" style="width:850px">
						<table style="background: #ACC1E0; width:800px">
							<tr>
								<td><font style="font-size:0.7em">Desde: </font></td>
								<td><input type="text" name="fecha_inicio" id="fecha_inicio" value="" size="9" readonly/></td>
                                <td><font style="font-size:0.7em">Hasta: </font></td>
								<td><input type="text" name="fecha_final" id="fecha_final" value="" size="9"  readonly/></td>
                                <td><font style="font-size:0.7em">Status: </font></td>
								<td><select id="status_select"  name="status_select" onClick="mostrar_participantes();">
                                                <font size="2">
                                                	<option value="" SELECTED></option>
                                                    <option value="Agendada">Agendada</option>
                                                    <option value="Confirmada">Confirmada</option>
                                                    <option value="Cancelada">Cancelada</option>
                                                    <option value="PAsistio">Paciente Asistio</option>
                                                    <option value="PATendido">Paciente Atendido</option>
                                                    <option value="PNoasistio">Paciente No Asistio</option>
                                                    <option value="ProcedNP">Procedimiento NP</option>
                                                    
                                                </font>
                            </select>
                                </td>
                                
							</tr>
                            <tr>
                             <td><font style="font-size:0.7em">Sala: </font></td>
							 <td><select id="sala_cita_reporte"  name="sala_cita_reporte"><script>cargarSalas('sala_cita_reporte');</script></select>
                             </td>
                             <td><font style="font-size:0.7em">Procedimiento: </font></td>
							<td><select id="proced_select_reporte"  name="proced_select_reporte"><script>cargarProcedimientos('proced_select_reporte');</script></select>
                            </td>
                            <td><font style="font-size:0.7em">Equipo: </font></td>
							<td> <select id="equipo_select"  name="equipo_select"><script>cargarEquipos('equipo_select');</script>
                            </select>
                            </td>
                            
                            </tr>
                            <tr>
                            <td><font style="font-size:0.7em">Forma de Pago: </font></td>
								<td> <select id="forma_pago"  name="forma_pago" >
                                                <font size="1">
                                                    <option value="" SELECTED></option>
                                                    <option value="Carta Aval">Carta Aval</option>
                                                    <option value="Sanitas">Sanitas</option>
                                                    <option value="Particular">Particular</option>
                                                </font>
                            </select>
                            </td>
                            
                            <td><font style="font-size:0.7em">Anestesiologo: </font></td>
							<td><select id="anestesiologo"  name="anestesiologo" >
                                                <font size="1">
                                                    <option value="" SELECTED></option>
                                                    <option value="on">Si</option>
                                                    <option value="off">No</option>
                                                </font>
                            </select>
                            </td>
                            <td>
                            </td>
                           
                            </tr>
                            <tr>
                            <td><font style="font-size:0.7em">Medico: </font></td>
							<td>
                            <select id="tipo_id_medico"  name="tipo_id_medico" >
                                                    <font size="1">
                                                        <option value="" SELECTED></option>
                                                        <option value="A">A</option>
                                                        <option value="E">E</option>
                                                        <option value="M">M</option>
                                                        <option value="P">P</option>
                                                        <option value="V">V</option>
                                                    </font>
                                </select>
                            <input type="text" name="id_medico" id="id_medico" value="" size="9" /></td>
                            <td><font style="font-size:0.7em">Paciente: </font></td>
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
                               <input type="text" id="id_paciente" name="id_paciente" value="" size="10" />
                        	</td>
                            <td><font style="font-size:0.7em">Tipo Paciente: </font></td>
								<td> <select id="tipo_paciente_select"  name="tipo_paciente_select">
                                                <font size="2">
                                                    <option value="" SELECTED></option>
                                                    <option value="A">Ambulatorio</option>
                                                    <option value="H">Hospitalizacion</option>
                                                    <option value="U">UCI</option>
                                                     <option value="E">Emergencia</option>
                                                </font>
                          </select></td>
                            </tr>
						</table>
					</div>
				</center>
				<center>
					<!-- Botón de búsqueda y limpiar.
					  -->
					<input type="button" value="Buscar" onClick="cargarCitas();" class="link-style"/>
					<button id="limpiar" type="button" onClick="limpiarLibro();" class="link-style">Limpiar</button>
				</center>
				
                <center>
                	<div align="right" style="width:850px; margin-bottom:10px; margin-top:-30px;">
                        	<a id="export" class="front" style="top:20px; cursor:pointer;">Exportar</a><br>
                            <a id="pdf" class="front" style="opacity:0.0; cursor:pointer; visibility:hidden;" 
                            onClick ="cargarLibroPDF();">PDF</a>
                            <a id="xls" class="front" style="opacity:0.0; cursor:pointer; visibility:hidden;" onClick ="cargarLibroExcel();">XLS</a>
                    </div>	
                	<div id="menuA" style="width:900px; height:500px; overflow-y: auto;">
                    	 <table id="tabla_citas" style="width:100%; overflow-y: auto; color:#646464; font-size:13px" >
                       
                        </table>
                    
                    </div>
                    <br>
				</center>
				
             
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