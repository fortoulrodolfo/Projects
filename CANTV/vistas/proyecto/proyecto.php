<?php
	session_start();
		include '../../config.php';
		include '../../modelo/Class_Proyecto.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Proyecto();
		if(isset($_POST['validar2'])){
   			$de->registrar();
			}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Registro Proyecto : : .</title>
        <link href="../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../controladores/cerrar/ctrl_cerrar.js"></script>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <link href="../../shared/libreria/development-bundle/themes/base/jquery.ui.all.css" rel="stylesheet"/>
        <script src="../../controladores/proyecto/contrl_proyecto.js" type="text/javascript"></script>
        <script src="../../shared/libreria/jquery.js"></script>
        <script src="../../shared/libreria/development-bundle/ui/jquery.ui.core.js"></script>
        <script src="../../shared/libreria/development-bundle/ui/jquery.ui.datepicker.js"></script>
        <script src="../../shared/libreria/development-bundle/ui/i18n/jquery.ui.datepicker-es.js"></script>
		<script src="../../shared/libreria/teclas restri/restringir_tecla.js"></script>
        <script src="../../shared/libreria/validar tecla/validar_tecla.js"></script>
        <script type="text/javascript">
			function montar_rif(d){
				a=d.value;
					document.getElementById("rif_institucion").value=a;
				}
				 $(function(){
					var pickerOpts = {
						minDate: new Date(),
						maxDate: "",
                        changeMonth: true,
                        changeYear: true
						};
					$("#fecha_solicitud").datepicker(pickerOpts);
				});
		</script>
    </head>
	<body>
    	<header>
        	<hgroup>
       	  		<h1></h1>
                <marquee direction="right" behavior="alternate">
                    	<img src="../../shared/img/img cabecera/Animacion 1. Amarillo & Azul.jpg"/>
                        <img src="../../shared/img/img cabecera/Animacion 2. Rojo Cantv.jpg"/>
                </marquee>
            </hgroup>
        </header>
           <nav id="menu">
        	<li><a href="../vista_inicio.php">Inicio</a></li>
			<li><a href="#">Noticias</a>
				<ul>
					<li><a href="../noticias/inst_publicas.php">Instituciones Publicas</a></li>
                    <?php if(isset($_SESSION['usu'])) { ?>
                    	<li><a href="../noticias/corpo.php">Corporativo</a></li>
               		<?php } ?>
                </ul>
			</li>
            <?php if(isset($_SESSION['usu'])) { ?>
				<!-- Acceso al Menu de Usuario : CONSULTOR -->
					<?php if(($_SESSION['tipo']) == "4") :?>
                    	<li><a href="#">¿Quienes Somos?</a>
                            <ul>
                                <li><a href="../quienes_somos/mision_vision.php">Mision & Vision</a></li>
                                <li><a href="../quienes_somos/valores.php">Nuestros Valores</a></li>
                                <li><a href="../quienes_somos/estructura.php">Estructura Organizativa</a></li>
                            </ul>
						</li>
						<li><a href="../servicios/servicioslimpio.php">Servicios</a></li>
					<li><a href="#">Demanda</a>
						<ul>
							<li><a href="../demanda/vista_registrar.php">Registro Demanda</a></li>
							<li><a href="../demanda/vista_ver.php">Listado Demanda</a></li>
						</ul>
					</li>
					<li><a href="#">Proyecto</a>
						<ul>
							<li><a href="../proyecto/proyecto.php">Registro Proyecto</a></li>
							<li><a href="../proyecto/ver_proyecto.php">Listado De Proyecto</a></li>
							<li><a href="../proyecto/informe_mes.php">Panel De Control</a></li>
							<li><a href="../proyecto/fases_proyecto.php">Seguimiento De Proyecto</a></li>
						</ul>
					</li>
					<li><a href="#">Data I.P.</a>
						<ul>
							<li><a href="../data_inst/data_ip.php">Registro Inst. P&uacute;blicas</a></li>
							<li><a href="../data_inst/lis_dataip.php">Listado Inst. P&uacute;blicas</a></li>
						</ul>
					</li>
					<li><a href='javascript:cerrar()'>Salir</a></li>
		<!-- ------------------------------------------------------------------------------------------------------------ -->
					<!-- Acceso al Menu de Usuario : GERENTE -->
					<?php elseif(($_SESSION['tipo']) == "3") :?>
                    	<li><a href="#">¿Quienes Somos?</a>
                            <ul>
                                <li><a href="../quienes_somos/mision_vision.php">Mision & Vision</a></li>
                                <li><a href="../quienes_somos/valores.php">Nuestros Valores</a></li>
                                <li><a href="../quienes_somos/estructura.php">Estructura Organizativa</a></li>
                            </ul>
						</li>
						<li><a href="#">Demanda</a>
							<ul>
								<li><a href="../demanda/vista_ver.php">Listado Demanda</a></li>
							</ul>
						</li>
						<li><a href="#">Proyecto</a>
							<ul>
								<li><a href="../proyecto/ver_proyecto.php">Listado De Proyecto</a></li>
								<li><a href="../proyecto/informe_mes.php">Panel De Control</a></li>
							</ul>
						</li>
						<li><a href="#">Data I.P.</a>
							<ul>
								<li><a href="../data_inst/lis_dataip.php">Listado Inst. P&uacute;blicas</a></li>
							</ul>
						</li>
                        <li><a href="#">Reportes</a>
							<ul>
								<li><a href="../reportes/reporte_demanda.php">Reportes Demandas</a></li>
                                <li><a href="../reportes/reporte_proyecto.php">Reportes Proyectos</a></li>
                                <li><a href="../reportes/reporte_dataip.php">Reportes Data I.P.</a></li>
							</ul>
						</li>
						<li><a href='javascript:cerrar()'>Salir</a></li>
		<!-- ------------------------------------------------------------------------------------------------------------ -->				
						<!-- Acceso al Menu de Usuario : LIDER -->
						<?php elseif(($_SESSION['tipo']) == "2") :?>
                        	<li><a href="#">Consultor</a>
								<ul>
									<li><a href="../administrador/modificar/modificar_cartera.php">Modificar Cartera Consultor</a></li>
								</ul>
							</li>
							<li><a href="#">Demanda</a>
								<ul>
									<li><a href="../demanda/vista_ver.php">Listado Demanda</a></li>
								</ul>
							</li>
							<li><a href="#">Proyecto</a>
								<ul>
									<li><a href="../proyecto/ver_proyecto.php">Listado De Proyecto</a></li>
									<li><a href="../proyecto/informe_mes.php">Panel De Control</a></li>
								</ul>
							</li>
							<li><a href="#">Data I.P.</a>
								<ul>
									<li><a href="../data_inst/lis_dataip.php">Listado Inst. P&uacute;blicas</a></li>
								</ul>
							</li>
                            <li><a href="#">Reportes</a>
                                <ul>
                                    <li><a href="../reportes/reporte_demanda.php">Reportes Demandas</a></li>
                                    <li><a href="../reportes/reporte_proyecto.php">Reportes Proyectos</a></li>
                                    <li><a href="../reportes/reporte_dataip.php">Reportes Data I.P.</a></li>
                                </ul>
							</li>
							<li><a href='javascript:cerrar()'>Salir</a></li>
		<!-- ------------------------------------------------------------------------------------------------------------ -->
							<!-- Acceso al Menu de Usuario : ADMINISTRADOR -->
							<?php elseif(($_SESSION['tipo']) == "1") :?>
								<li><a href="#">Registrar</a>
									<ul>
										<li><a href="../administrador/registrar/registrar_usuario.php">Registrar Usuario</a></li>
                                        <li><a href="../administrador/registrar/registrar_institucion.php">Registrar Instituciones P&uacute;blicas</a></li>
                                        <li><a href="../administrador/registrar/registrar_central.php">Registrar Central</a></li>
									</ul>
								</li>
								<li><a href="#">Modificar</a>
									<ul>
                                    	<li><a href="../administrador/modificar/modificar_usuario.php">Modificar Usuario</a></li>
                                        <li><a href="../administrador/modificar/modificar_cartera.php">Modificar Cartera de Clientes</a></li>
									</ul>
								</li>
                                <li><a href="#">Reportes</a>
                                    <ul>
                                        <li><a href="../reportes/reporte_demanda.php">Reportes Demandas</a></li>
                                        <li><a href="../reportes/reporte_proyecto.php">Reportes Proyectos</a></li>
                                        <li><a href="../reportes/reporte_dataip.php">Reportes Data I.P.</a></li>
                                    </ul>
								</li>
								<li><a href="#">Importar Excel</a></li>
								<li><a href='javascript:cerrar()'>Salir</a></li>
				<?php endif; ?>
            <?php } ?>
     	</nav>
        <form id="id_form2" method="post" name="id_form2" enctype="multipart/form-data">
            <article id="portada_p">
            	<div id="solo_usuario2">
					<?php echo $_SESSION['usu']?>
        		</div>
                <p class="planilla2">Datos De Instituciones</p>
                    <table class="contendor_proyecto">
                        <tr>
                        	<td class="proyecto_efecto">Codigo ARC:
                            	<td class="proyecto_efecto">
                                	<input name="codigo_arc" id="codigo_arc"  value="" type="text" required="required" onkeypress="return keyRestrict(event,'abcdefghijklmnñopqrstuvwxyz1234567890.-/')"/>
                                </td>
                            </td>
                        </tr>
                        <tr>
                            <td class="proyecto_efecto">Instituciones:
                            	<td class="proyecto_efecto">
                                	<select name="instituciones" size="0" id="instituciones" onchange="montar_rif(this)">
                                		<option selected="selected">Seleccione</option>
                                    	<?= $de->combo_instituciones(); ?>
                                	</select>
                                <td>
                            </td>
                        </tr>
                        <tr>
                        	<td class="proyecto_efecto">R.I.F:
                            	<td class="proyecto_efecto">
                                    <input  name="rif_institucion" id="rif_institucion" type="text" style="font:bold 16px sans-serif; color:#000; padding:3px; text-align:center; border:none; border-radius:5px;" readonly="readonly"/>
                                <td>
                            </td>
                        </tr>
                        <tr>
                        	<td class="proyecto_efecto">Región:
                            	<td class="proyecto_efecto">
                                	<select name="region" size="1" id="region">
                                		<option selected="selected" value="">Seleccione</option>
										<?= $de->combo_region(); ?>
                                	</select>
                                </td>
                           	</td>
                        </tr>
                    </table>
                <p class="planilla2">Datos Del Proyecto</p>    
                    <table class="contendor_proyecto5">
                        <tr>
                            <td>Macro Proyecto:
                                <select name="macro_proyecto" size="1" id="macro_proyecto" required="required">
                                    <option selected="selected" value="" >Seleccione</option>
                                    <?= $de->combo_macroproyecto() ; ?>
                                </select>
                            </td>
                        	<td>Descripcion:</td>
                            	<td>
                                <textarea name="descripcion_macro_proyecto" cols="70" rows="1" id="descripcion_macro_proyecto" placeholder="Describe Brevemente Tu Proyecto" required="required"></textarea>
                                </td>
                            </td>
                        </tr>
                    </table>
                    <table class="contendor_proyecto511">    
                        <tr>
                        	<td class="proyecto_efecto">Area:
                                <select name="area" size="1" id="area">
                                    <option selected="selected" value="">Seleccione</option>
                                    <?= $de->combo_area(); ?>
                                </select>
                            </td>
                            <td class="proyecto_efecto">Prioridad:
                                <select name="prioridad" size="1" id="prioridad">
                                    <option selected="selected" value="">Seleccione</option>
                                    <?= $de->combo_prioridad(); ?>
                                </select>
                            </td>
                            <td class="proyecto_efecto">Unidad:
                                <select name="unidad" size="1" id="unidad">
                                    <option selected="selected" value="">Seleccione</option>
                                    <?= $de->combo_unidad(); ?>
                                </select>
                            </td>
                            <td class="proyecto_efecto">Forma De Pago:
                                <select name="forma_de_pago" size="1" id="forma_de_pago">
                                    <option selected="selected" value="">Seleccione</option>
									<?= $de->combo_pago(); ?>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <table class="contendor_proyecto5" id="exp">
                    
                        <tr>
                        	<td class="proyecto_efecto">Linea De Negocio:</td>
                            	<td class="proyecto_efecto">
                                	<select name="linea_de_negocio1" size="1" id="linea_de_negocio1">
                                        <option selected="selected" value="" >Seleccione</option>
										<?= $de->combo_linea(); ?>
                                       
                                    </select>
                                </td>
                            </td>
                        	<td class="proyecto_efecto">Costo De Venta:</td>
                            	<td class="proyecto_efecto">
                                	<input name="costo_venta1" value="" id="costo_venta1" type="text" size="20" onkeypress="return keyRestrict(event,'1234567890,.')"/>
                                </td>
                            </td>
							<td class="proyecto_efecto">Ingresos Único/Anual:</td>
                            	<td class="proyecto_efecto">
                                	<input name="ingresos_unico_anual1" id="ingresos_unico_anual1" value="" size="20" type="text" onkeypress="return keyRestrict(event,'1234567890,.')"/>
                               	</td>
                            </td>
                            <td>
                            	<input name="iter" id="iter" value="2" type="hidden"/>
                                
                            	<img src="../../shared/img/img boton +/icontexto-webdev-add-032x032.png" onclick="javascript:crear_capa();" class="boton_mas"/>
                                
                          </td>
                            <td>
                           	  <img src="../../shared/img/img eliminar/icono_eliminar.gif" onclick="javascript:eliminar_capa();" class="boton_mas" />
                            </td>
                      </tr>
                    </table>
                    <table class="contendor_proyecto5">
                        <tr>
                            <td>Importancia Comercial:
                            	<td width="60">Si
                                	<label for="Si"></label>
                                    <input type="radio" name="importancia_comercial" id="imp_comer_si" value="Si"/>
                                </td>
                                <td width="90">No
                                	<label for="No"></label>
                                    <input type="radio" name="importancia_comercial" id="imp_comer_no" value="No"/>
                               </td>
                            </td>
                        	<td>Importancia Politica:
                                <td width="60">Si
                                	<label for="Si"></label>
                                    <input type="radio" name="importancia_politica" id="imp_poli_si" value="Si"/>
                               </td>
                               <td width="90">No
                                	<label for="No"></label>
                                    <input type="radio" name="importancia_politica" id="imp_poli_no" value="No"/>
                               </td>
                            </td>
                        	<td>Importancia Social:
                                <td width="60">Si
                                    <input type="radio" name="importancia_social" id="imp_soci_si" value="Si"/>
                                    <label for="Si"></label>
                                </td>
                                <td>No
                                    <input type="radio" name="importancia_social" id="imp_soci_no" value="No"/>
                                    <label for="No"></label>
                               </td>
                            </td>
                        </tr>
                    </table>
                    <input type="hidden" id="tamano_tabla" name="tamano_tabla" value="1" />
                   
          		  <input type="hidden" id="validar2" name="validar2" value="Registrar"/>
            
                    <div id="botones">
                        <input type="button" name="registrar" id="registrar" value="Registrar" class="guardar" onclick="javascript:registrar_p();"/>
                        <input type="button" name="limpiar" value="Limpiar" class="cancelar" onclick="window.location.reload();"/>
                    </div>  
            </article>
        </form>
        <footer>
        	<p>Copyleft 2013 - Gerencia De Instituciones Públicas- Todos Los Derechos Reservados |
            	<a href="../../manuales/manual usuario/Cantv. Manual Usuario.pdf" target="_blank">Ayuda</a>
                |
                <a href="../contacto/contacto.php">Contacto</a>
            </p>
        </footer>
	</body>
</html>