<?php
	session_start();
		include '../../config.php';
		include '../../modelo/Class_Informe.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Informe();		
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>. : : Panel De Control : : .</title>
        <link href="../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../controladores/cerrar/ctrl_cerrar.js"></script>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script src="../../shared/libreria/teclas restri/restringir_tecla.js"></script>
        <script src="../../shared/libreria/validar tecla/validar_tecla.js"></script>
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
        <form name="form1" id="form1" method="post" action="">
        <article id="infome_mes">
        	<p class="planilla2">Panel De Control General</p>
            
           	<table class="gerencia_uno">
            	
            	<tr>
                	<td>Gerencia:</td>
                    <td>
            		  <select name="gerencia" size="1" id="gerencia" onchange="javascript:cargar_informe();">
                        	<option selected="selected" value="0">Seleccione</option>
                            <?php $de->combo_gerencia(); ?>
                      </select>
                    </td>
                    <td>Consultar:</td>
                    <td>
            		  <select name="filtro" size="1" id="filtro" onchange="javascript:cargar_filtro(this);" disabled="disabled">
                        	<option selected="selected" value="0">Seleccione</option>
                            <option value="1">Iniciativas</option>
                            <option value="2">Proyectos</option>
                            <option value="3">Todo</option>
                           
                      </select>
                    </td>
               	</tr>
            </table>
            
        	<div id="intruc">
            	
                	<table>
                        <tr>
                            <td id="informe_nombre">Ingresos Estimados Iniciativas</td>
                            <td>
                            	<input class="informe_cajas" name="ingresos_estimados" id="ingresos_estimados" type="text" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Ingresos Asociados Proyectos</td>
                            <td>
                            	<input class="informe_cajas" name="ingresos_asociados" id="ingresos_asociados" type="text" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Total Ingresos (Bs.F):</td>
                            <td>
                            	<input class="informe_cajas" name="total_ingresos" id="total_ingresos" type="text" readonly="readonly" value=""/>
                            </td>
                        </tr>
					</table>
            </div>
            <div id="intruc2">
               
                	<table>
                        <tr>
                            <td id="informe_nombre">Tasa De Éxito (Aceptadas/Entregadas)</td>
                            <td>
                            	<input class="informe_cajas" name="tasa_exito" id="tasa_exito" type="text" size="8" maxlength="4" readonly="readonly" value="">
                            </td>
                        </tr>
                	</table>
           	</div>
            <table>
            <tr>
            <td>
            	<div id="intruc">
                	<table style="margin-left:15px;">
                       
                        <tr>
                            <td id="informe_nombre">Cantidad Iniciativas</td>
                            <td>
                            	<input class="informe_cajas" name="cantidad_iniciativas" id="cantidad_iniciativas" type="text" readonly="readonly" size="8" maxlength="4" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Cantidad Proyectos</td>
                            <td>
                            	<input class="informe_cajas" name="cantidad_proyectos" id="cantidad_proyectos" type="text" readonly="readonly" size="8" maxlength="4" value=""/></td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Total Iniciativas/Proyectos:</td>
                            <td>
                            	<input class="informe_cajas" name="total_iniciativas" id="total_iniciativas" type="text" readonly="readonly" size="8" maxlength="4" value=""/>
                            </td>
                        </tr>
                    </table>
           		</div>
                </td>
                <td>
                <div id="intruc22">
                <table>
                        <tr>
                            <td></td>
                            <td id="informe_nombre">Si</td>
                            <td id="informe_nombre">No</td>
                           
                        </tr>
                            <tr>
                                <td id="informe_nombre">Importancia Social</td>
                                <td>
                                    <input class="informe_cajas" name="importancia_social1" id="importancia_social1" type="text" readonly="readonly" size="8" maxlength="4" value=""/>
                                </td>
                                <td>
                                    <input class="informe_cajas" name="importancia_social2" id="importancia_social2" type="text" readonly="readonly" size="8" maxlength="4" value=""/>
                                </td>
                            </tr>
                            <tr>
                                <td id="informe_nombre">Importancia Política</td>
                                <td>
                                    <input class="informe_cajas" name="importancia_politica1" id="importancia_politica1" type="text" readonly="readonly" size="8" maxlength="4" value=""/></td>
                                <td>
                                    <input class="informe_cajas" name="importancia_politica2" id="importancia_politica2" type="text" readonly="readonly" size="8" maxlength="4" value=""/>
                                </td>
                            </tr>
                            <tr>
                                <td id="informe_nombre">Importancia Comercial</td>
                                <td>
                                    <input class="informe_cajas" name="importancia_comercial1" id="importancia_comercial1" type="text" readonly="readonly" size="8" maxlength="4" value=""/>
                                </td>
                                <td>
                                    <input class="informe_cajas" name="importancia_comercial2" id="importancia_comercial2" type="text" readonly="readonly" size="8" maxlength="4" value=""/>
                                </td>
                           </tr>
                  </table>
                    
                </div>
                </td>
                </tr>
                </table>
                 
                
                 <table>
                 <tr>
                 <td>
                            <p class="planilla4">Distribución Por Area</p>
                       <div class="intruc22">
                	<table style="margin-left:60px;">
                       
                        <tr>
                            <td></td>
                            <td id="informe_nombre">Cantidad</td>
                            <td id="informe_nombre">Porcentaje</td>
                           
                        </tr>
                        <tr>
                            <td id="informe_nombre">Alimentación</td>
                            <td>
                            	<input class="informe_cajas" name="alimentacion" id="alimentacion" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="alimentacionp" id="alimentacionp" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Educación</td>
                            <td>
                            	<input class="informe_cajas" name="educacion" id="educacion" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="educacionp" id="educacionp" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Gobierno</td>
                            <td>
                            	<input class="informe_cajas" name="gobierno" id="gobierno" type="text" size="8" maxlength="4" readonly="readonly" value=""/></td>
                            <td>
                            	<input class="informe_cajas" name="gobiernop" id="gobiernop" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Salud</td>
                            <td>
                            	<input class="informe_cajas" name="salud" id="salud" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="saludp" id="saludp" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Seguridad</td>
                            <td>
                            	<input class="informe_cajas" name="seguridad" id="seguridad" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="seguridadp" id="seguridadp" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                	</table>
                    </div>
                </td>
                <td>
                
                                <p class="planilla5">Distribución Por Región</p>
                   
                <div id="intruc24">
                    <table>
                       <tr>
                            <td></td>
                            <td id="informe_nombre">Cantidad</td>
                            <td id="informe_nombre">Porcentaje</td>
                       
                        </tr>
                        <tr>
                        	<td id="informe_nombre">Andes</td>
                            <td>
                            	<input class="informe_cajas" name="andes" id="andes" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="andesp" id="andesp" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Capital</td>
                            <td>
                            	<input class="informe_cajas" name="capital" id="capital" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="capitalp" id="capitalp" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                           	</td>
                        </tr>
                        <tr>
                        	<td id="informe_nombre">Central</td>
                            <td>
                            	<input class="informe_cajas" name="central" id="central" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="centralp" id="centralp" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Centro Occidente</td>
                            <td>
                            	<input class="informe_cajas" name="centro_occidente" id="centro_occidente" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="centro_occidentep" id="centro_occidentep" type="text" size="8" maxlength="4" readonly value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Occidente</td>
                            <td>
                            	<input class="informe_cajas" name="occidente" id="occidente" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="occidentep" id="occidentep" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Oriente</td>
                            <td>
                            	<input class="informe_cajas" name="oriente" id="oriente" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="orientep" id="orientep" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                	</table>
            </div>
            </td>
            </tr>
            </table>
            
            
            <table>
            <tr>
            <td>
                	 
                            <p class="planilla4" style="width:470px;">Linea De Negocio</p>
                      <div class="intruc22">
                    <table style="margin-left:-5px;">
                       
                        <tr>
                            <td></td>
                            <td id="informe_nombre">Ingresos (Bs.F)</td>
                            <td id="informe_nombre">Porcentaje</td>
                            <td id="informe_nombre">Cantidad</td>
                            <td id="informe_nombre">Porcentaje</td>
                        </tr>
                        <tr>
                            <td id="informe_nombre" >Cableado Estructurado</td>
                            <td>
                            	<input class="informe_cajas" name="cableado_estructurado1" id="cableado_estructurado1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="cableado_estructurado2" id="cableado_estructurado2" type="text" size="8" maxlength="4" readonly value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="cableado_estructurado3" id="cableado_estructurado3" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="cableado_estructurado4" id="cableado_estructurado4" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Equipamiento</td>
                            <td>
                            	<input class="informe_cajas" name="equipamiento1" id="equipamiento1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="equipamiento2" id="equipamiento2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="equipamiento3" id="equipamiento3" size="8" maxlength="4" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="equipamiento4" id="equipamiento4" type="text" size="8" maxlength="4" readonly value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Datos</td>
                            <td>
                            	<input class="informe_cajas" name="datos1" id="datos1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="datos2" id="datos2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="datos3" id="datos3" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="datos4" id="datos4" type="text" size="8" maxlength="4" readonly="readonly" value=""/></td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Movilnet</td>
                            <td>
                            	<input class="informe_cajas" name="movilnet1" id="movilnet1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="movilnet2" id="movilnet2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="movilnet3" id="movilnet3" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="movilnet4" id="movilnet4" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Servicios TI</td>
                            <td>
                            	<input class="informe_cajas" name="servicios1" id="servicios1" type="text"  readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="servicios2" id="servicios2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="servicios3" id="servicios3" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="servicios4" id="servicios4" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Telco</td>
                            <td>
                            	<input class="informe_cajas" name="telco1" id="telco1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="telco2" id="telco2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="telco3" id="telco3" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" style="width:80px;" name="telco4" id="telco4" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Total</td>
                            <td>
                            	<input class="informe_cajas" name="total1" id="total1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td></td>
                            <td>
                            	<input class="informe_cajas" name="total2" id="total2" type="text" size="8" maxlength="4" readonly="readonly" value=""/></td>
                            <td></td>
                        </tr>
               		</table>
            	</div>
              </td>
              
             <td>
                <p class="planilla4" style="margin-top:-50px; margin-left:-40px;">Distribución Por Forma De Pago</p>
                 <div id="intruc22">
                    <table style="margin-left:15px; margin-top:60px;">
                          <tr>
                            <td></td>
                            <td id="informe_nombre">Cantidad</td>
                            <td id="informe_nombre">Porcentaje</td>
                           
                        </tr>
                        <tr>
                            <td id="informe_nombre">Recurrente</td>
                            <td>
                            	<input class="informe_cajas" name="concurrente1" id="concurrente1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="concurrente2" id="concurrente2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td id="informe_nombre">Único</td>
                            <td>
                            	<input class="informe_cajas" name="unico1" id="unico1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="unico2" id="unico2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                           	</td>
                        </tr>
					</table>
              </div>
              </td>
              </tr>
              </table>
            	
                            <p class="planilla3">Transformación Del Estado (Macro Proyecto)</p>
                        <div id="intruc9">
                    <table style="margin-left:-50px;">
                        
                        <tr>
                            <td></td>
                            <td id="informe_nombre">Ingresos (BsF)</td>
                            <td id="informe_nombre">Porcentaje</td>
                            <td id="informe_nombre">Cantidad</td>
                            <td id="informe_nombre">Porcentaje</td>
                           
                        </tr>
                        <tr>
                            <td id="informe_nombre">Acaldias Digitales</td>
                            <td>
                            	<input class="informe_cajas" name="acaldias_digitales1" id="acaldias_digitales1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="acaldias_digitales2" id="acaldias_digitales2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="acaldias_digitales3" id="acaldias_digitales3" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="acaldias_digitales4" id="acaldias_digitales4" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td  id="informe_nombre">GIS (Salud)</td>
                            <td>
                            	<input class="informe_cajas" name="gis_salud1" id="gis_salud1" type="text" readonly value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="gis_salud2" id="gis_salud2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="gis_salud3" id="gis_salud3" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="gis_salud4" id="gis_salud4" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td  id="informe_nombre">Modernización Del Estado</td>
                            <td>
                            	<input class="informe_cajas" name="modernizacion_del_estado1" id="modernizacion_del_estado1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="modernizacion_del_estado2" id="modernizacion_del_estado2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="modernizacion_del_estado3" id="modernizacion_del_estado3" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="modernizacion_del_estado4" id="modernizacion_del_estado4" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td  id="informe_nombre">Red Del Saber</td>
                            <td>
                            	<input class="informe_cajas" name="red_del_saber1" id="red_del_saber1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="red_del_saber2" id="red_del_saber2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="red_del_saber3" id="red_del_saber3" type="text" size="8" maxlength="4" readonly="readonly" value=""/></td>
                            <td>
                            	<input class="informe_cajas" name="red_del_saber4" id="red_del_saber4" type="text" size="8" maxlength="4" readonly="readonly" value=""/></td>
                        </tr>
                        <tr>
                            <td  id="informe_nombre">Seguridad Agro Alimentaria</td>
                            <td>
                            	<input class="informe_cajas" name="seguridad_agro_alimentaria1" id="seguridad_agro_alimentaria1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="seguridad_agro_alimentaria2" id="seguridad_agro_alimentaria2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="seguridad_agro_alimentaria3" id="seguridad_agro_alimentaria3" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="seguridad_agro_alimentaria4" id="seguridad_agro_alimentaria4" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td  id="informe_nombre">Seguridad Ciudadana</td>
                            <td>
                            	<input class="informe_cajas" name="seguridad_ciudadana1" id="seguridad_ciudadana1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="seguridad_ciudadana2" id="seguridad_ciudadana2" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="seguridad_ciudadana3" id="seguridad_ciudadana3" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                            <td>
                            	<input class="informe_cajas" name="seguridad_ciudadana4" id="seguridad_ciudadana4" type="text" size="8" maxlength="4" readonly="readonly" value=""/>
                            </td>
                        </tr>
                        <tr>
                            <td  id="informe_nombre">Total</td>
                            <td>
                            	<input class="informe_cajas" name="totaal1" id="totaal1" type="text" readonly="readonly" value=""/>
                            </td>
                            <td></td>
                            <td>
                            	<input class="informe_cajas" name="totaal2" id="totaal2" type="text" size="8" maxlength="4" readonly="readonly" value=""/></td>
                            <td></td>
                        </tr>
                	</table>
            </div>
            <div id="botones_informe" style="margin-top:300px;">
            	<input type="reset" name="imprimir" value="Imprimir" class="guardar_ver" onclick="javascript:window.print()"/>
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