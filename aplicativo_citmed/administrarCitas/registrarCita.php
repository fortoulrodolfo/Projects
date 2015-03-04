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
	chequearCaducado($link);
	$link="";
	$version ="";*/
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
        <link rel='stylesheet' href='../extensiones/fullcalendar/lib/cupertino/jquery-ui.min.css' />
		<script src="../extensiones/funciones.js" language="JavaScript"></script>
		<script src="../extensiones/jquery-1.2.6.min.js" type="text/javascript"></script>
       	<link href='../extensiones/fullcalendar/fullcalendar.css' rel='stylesheet' />
        <link href='../extensiones/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
        <script src='../extensiones/fullcalendar/lib/moment.min.js'></script>
        <script src='../extensiones/fullcalendar/lib/jquery.min.js'></script>
        <script src='../extensiones/fullcalendar/lib/jquery-ui.custom.min.js'></script>
        <script src='../extensiones/fullcalendar.min.js'></script>
        <script src='../extensiones/fullcalendar/lang-all.js'></script>
        <script src='../js/funcionesAdministrarCitas.js'></script>
        <script>
         $(document).ready(function() {
			 
			 /* Se cargan todas las citas almacenadas en BD y tambien las Salas como Columnas del Calendario */
			
				cargarCalendarioCita();
				
		   });
		   
		   /*	Se crean variables globales para el Javascript
		    *	se utilizan para validaciones del Usuario en Sesion
			*/
		   
		   var cedulaSesion = "<?php echo $_SESSION["cedula"]; ?>";
		   var nacSesion = "<?php echo $_SESSION["nac"]; ?>";
		   var autorizacionSesion = "<?php echo $_SESSION["autorizacionOpciones"]; ?>";
		   
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

			<center><br><br>
            	 <button id="sesion" type="button" class="link-style" style="width:170px;"
						onClick="window.location.href='<?php echo $link; ?>citmed/administrarCitas/fechaCalendario.php';">
					Atras
				</button>
                <button id="btnRegistrarCita" type="button" class="link-style" style="width:140px;"
						onClick="mostrarFormularioCita('registrarCita')">
					Registrar Cita
				</button>
				<?php 
                
				/** Se valida si el Usuario en sesion tiene permiso para Registrar Proced. NP
				**/
				
				if ($_SESSION["autorizacionOpciones"][5] != 0){
				echo "<button id='btnRegistrarCitaNP' type='button' class='link-style' style='width:160px;'
						onClick='mostrarFormularioCita('registrarProcedimientoNP')'>
						Procedimiento N/P
						</button>";
				};
				?>
                <button id="btnRegistrarCitaNP" type="button" class="link-style" style="width:160px;"
						onClick="mostrarFormularioCita('registrarProcedimientoNP')">
						Procedimiento N/P
						</button>
                <button id="btnActualizarCita" type="button" class="link-style" style="width:140px;"
						onClick="mostrarFormularioCita('actualizarCita')">
					Actualizar Cita
				</button>
                <button id="btnEliminarCita" type="button" class="link-style" style="width:140px;"
						onClick="mostrarFormularioCita('eliminarCita')">
					Eliminar Cita
				</button>
				<button id="sesion" type="button" class="link-style" style="width:140px;"
						onClick="window.location='<?php echo $link; ?>sesion/cierreSesion.php'">
					Cerrar Sesión
				</button><br><br><br>
               
                <!-- Se crea el formulario con todos los campos requeridos para la Cita  -->
                <!--  El formulario es enviado al Archivo PHP mediante el metodo POST, pero antes
                	hace un llamado a la funcion validarVacioCita() para validar campos
                -->
                
			<form action="<?php echo $link; ?>citmed/consultas/enviarCita.php"
                    			method='post' id="formulario" hidden="true" onSubmit="return validarVacioCita(this);">
				<div id="registrarCita" >
                <center>
                <font size="5" id="titulo" style="color:#646464;">Registrar Cita</font><br><br><br>
                <font size="3" style="color:#646464;">Datos de la Cita</font><br>
				<div id="menuA" align="left" style="height:530px; width:850px; overflow:auto;">
                
                <div style="margin-left:10px;">
                	
                     <table align="right" class="link-style" style="width:230px; margin-right:10px;">
                     <tr>
                  	    <td >
                        <font size="2" style="color:#646464;">Status: </font>
                        </td>
                        <td>
                        <!--  STATUS que puede tener una cita, el Value es almacenado en BD, dependiendo del Status seleccionado Muestra Participantes -->
                         <select id="status_select"  name="status_select" onChange="mostrar_participantes();">
                                                <font size="2">
                                                    <option value="Agendada" style="background-color:blue;" selected>Agendada</option>
                                                    <option value="Confirmada" style="background-color:green;">Confirmada</option>
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
                  	    <td>
                        <font size="2" style="color:#646464;">Ultima Act.: </font>
                        </td>
                        <td>
                        <label id="fecha_status" style="font-size:15px; color:#646464;"></label>
               			 </td>
                    </tr> 
                    </table>
                    <br><br><br><br>
                    <table style="width:100%;">
                    <tr>
                    	<td width="20%">
                        	<font size="2" style="color:#646464;">Sala: </font>
                       </td >
                        <td>
                        	<!-- Se cargan todas las Salas Activas -->
                           <select id="sala_cita"  name="sala_cita"><script>cargarSalas('sala_cita', 'Activa');</script></select>
                        </td>
                        <td>
                           <font size="2" style="color:#646464;">Fecha: </font>
                        </td>
                        <td>
                          <input type="text" id="fecha_inicio_cita" name="fecha_inicio_cita" value="" size="10" readonly/>
               			 </td>
                        <td width="25%">
                        <table  style="width:100%;">
                        	<tr>
                              <font size="2" style="color:#646464;">Hora Inicio: </font>
                              <input type="text" id="hora_inicio_cita" name="hora_inicio_cita" value="" size="6" readonly/><br>
               			    </tr>
                            <tr>
                               <font size="2" style="color:#646464;">Hora Fin: </font>
                              <input type="text" id="hora_fin_cita" style="margin-left:15px;" name="hora_fin_cita" value="" size="6" readonly/>
                          </tr>
                         </table>
               			 </td>
                      	 </tr>
                      <tr>
                    	<td width="20%">
                        	<font size="2" style="color:#646464;">Tipo Estudio: </font>
                       </td>
                        <td>
                        	<!-- Dependiendo del tipo de estudio que se seleccione, se cargan los Procedimientos -->
                           <select id="tipo_estudio_select"  name="tipo_estudio_select" onChange="cargarProcedimientos(this.value,'proced_select');">
                                                <font size="2">
                                                    <option value="" SELECTED></option>
                                                    <option value="Diagnostico">Diagnostico</option>
                                                    <option value="Terapeutico">Terapeutico</option>
                                                  </font>
                            </select>
                        </td>
                        <td>
                           <font size="2" style="color:#646464;">Procedimiento: </font>
                        </td>
                        <td>
                        	<!-- Al seleccionar Procedimiento se carga la hora fin y valida la disponibilidad de Sala y Equipo -->
                           <select id="proced_select"  name="proced_select" onChange="cargarHoraFin(this.value); salaDisponible(); mostrarEquipos(this.value);">
                                                <font size="2">
                                                    <option value="" SELECTED></option>
                                                     </font>
                            </select>
               			 </td>
                        <td width="30%">
                        <!-- Tabla para crear los combos de los equipos, dependiendo del procedimiento seleccionado -->
                        <table id="tabla_equipos"  style="width:100%;">
                        	
                         </table>
                        </td>
                    </tr>
                   
                    </table>
                   
                    <br>
                     <table style="width:100%;">
                    <tr>
                    	<td width="20%">
                        	<font size="2" style="color:#646464;">Cedula Medico: </font>
                       </td>
                       <td width="17%">
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
                           <input type="text" id="id_medico" name="id_medico" onBlur="buscarMedico(document.getElementById('tipo_id_medico').value,this.value);" value="" size="6" />
                        </td>
                        <td>
                           <font size="2" style="color:#646464; margin-right:20px;">Dr(a).:</font>
                           <label id="nombre_medico" name="nombre_medico"></label>
                        </td>
                        </tr>
                    </table>
                    <br>
                     <table style="width:100%;">
                    <tr>
                    	<td width="20%">
                        	<font size="2" style="color:#646464;">Cedula Paciente: </font>
                       </td>
                       <td width="23%">
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
                           <input type="text" id="id_paciente" name="id_paciente" onBlur="buscarPaciente(this.value, document.getElementById('tipo_id').value);" value="" size="10" />
                        </td>
                        <td>
                           <label id="nombre_paciente" name="nombre_paciente"></label>
                        </td>
                        <td >
                           <font size="2" style="color:#646464;">Tipo:</font>
                        </td>
                        <td>
                         <select id="tipo_paciente_select"  name="tipo_paciente_select" onChange="mostrar_hab();">
                                                <font size="2">
                                                    <option value="" SELECTED></option>
                                                    <option value="A">Ambulatorio</option>
                                                    <option value="H">Hospitalizacion</option>
                                                    <option value="U">UCI</option>
                                                    <option value="E">Emergencia</option>
                                                </font>
                          </select>
                          </td>
                          <td>
                          <font size="2" id="hab_font" style="color:#646464; margin-top:2px; display:none;">Hab:</font>
                          </td>
                          <td>
                          <input type="text" id="hab_paciente" name="hab_paciente" style="display:none;" value="" size="1"/>
               			 </td>
                          
                    </tr>
                    
                    </table>
                    <br>
					<table style="width:100%;">
                    <tr>
                    	<td width="20%">
                        	<font size="2" style="color:#646464;">Forma de Pago: </font>
                       </td>
                       <td >
                       		 <select id="forma_pago"  name="forma_pago" >
                                                <font size="1">
                                                    <option value="" SELECTED></option>
                                                    <option value="Carta Aval">Carta Aval</option>
                                                    <option value="Sanitas">Sanitas</option>
                                                    <option value="Particular">Particular</option>
                                                </font>
                            </select>
                        </td>
                        <td>
                           <font size="2" style="color:#646464;">Anestesiologo: </font>
                           <input type="checkbox"  id="anestesiologo" name="anestesiologo" />
                        </td>
                        <td >
                           <font size="2" id="participantes" style="color:#646464; display:none;">Participantes:</font>
                        </td>
                        <td>
                            <input type="text" id="participante1" name="participante1" style="display:none;"  value="" />
                            <input type="text" id="participante2" name="participante2" style="display:none; margin-top:5px;"  value="" />
                          </td>
                        
                    </tr>
                    
                    </table>
                    <br>
                     <table style="width:100%">
                     <tr>
                   		 <td width="20%">
                           <font size="2" style="color:#646464;">Observaciones: </font>
                        </td>
                        <td>
                         <textarea rows="2" cols="60" name="observaciones" id="observaciones"></textarea>
               			 </td>
                   	 </tr> 
                    </table>
						
					</div>
                    </div>
				</center>
				<center>
                <!-- Variables Ocultas elementales para el funcionamiento de la pagina
                	 "operacionCita"= Indica que operacion se esta ejecutando al momento (Registrar, Actualizar, Eliminar, RegistrarNP)
                     					esta variable es utilizada en todo el JS para saber el comportamiento de la pagina.
                     "codigoCita" = Almacena de manera oculta el codigo de la cita, para al momento de Actualizar o Eliminar se pueda enviar
                     				metodo POST.
                     "equiposCita"= Almacena los equipos utilizados en la cita de manera oculta y es enviada por metodo POST.
                 -->
                	<input id="operacionCita" name="operacionCita" value="Registrar" type="hidden"/>
                    <input id="codigo_cita" name="codigo_cita" value="" type="hidden"/>
                    <input id="equipos_cita" name="equipos_cita" value="" type="hidden"/>
					<!-- Botón de búsqueda y limpiar.
					  -->
					<input type="submit" id="btnGuardar" value="Guardar" class="link-style"/>
					<button id="limpiar" type="button" class="link-style" onClick="limpiarCita(); document.getElementById('formulario').hidden=true;
                     document.getElementById('operacionCita').value='Registrar';">Limpiar</button>
				</center>
				<br>
				</div>
               
                
            </form>
            
            <!-- Aqui se muestra el Calendario de las Citas -->
                <table>
               <tr>
               <td><div id='calendar' style="font-size: 11px;" class="calendar"></div></td>
                </tr>
                  </table>
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