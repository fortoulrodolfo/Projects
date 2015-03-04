<?php
	session_start();
		include '../../config.php';
		include '../../modelo/Class_Seguimiento.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Seguimiento();	
		if(isset($_POST['actualizar'])){
   			$de->actualizar_segui();
			}
?>
<!DOCTYPE HTML>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Seguimiento De Proyecto : : .</title>
        <link href="../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <script src="../../controladores/cerrar/ctrl_cerrar.js"></script>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <link href="../../shared/libreria/development-bundle/themes/base/jquery.ui.all.css" rel="stylesheet"/>
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../controladores/proyecto/contrl_proyecto.js" type="text/javascript"></script>
        <script src="../../shared/libreria/jquery.js"></script>
        <script src="../../shared/libreria/development-bundle/ui/jquery.ui.core.js"></script>
        <script src="../../shared/libreria/development-bundle/ui/jquery.ui.datepicker.js"></script>
        <script src="../../shared/libreria/development-bundle/ui/i18n/jquery.ui.datepicker-es.js"></script>
		<script src="../../shared/libreria/teclas restri/restringir_tecla.js"></script>
        <script src="../../shared/libreria/validar tecla/validar_tecla.js"></script>
    <style>
        .contendor_proyecto22{
	margin-top: 10px;
	font-size: 13px;
	font-family: Arial, Helvetica, sans-serif;
	margin-left: 54px;
	width: 267px;
	position: absolute;
	color: #666;
	font-weight: bolder;
	left: 642px;
	top: 50px;
	}
	
	#pwidget{
		
		background-image:-webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff), to(#bbb));
   		background-image:-moz-linear-gradient(0% 100% 90deg, #BBBBBB, #FFFFFF);
   		background-color:#fff;
		width:785px;
		padding:2px;
		-moz-border-radius:3px;
		border-radius:3px;
		text-align:left;
		border:1px solid gray;
		}
	
	#progressbar{
		width:780px;
		padding:1px;
		background-color:white;
		border:1px solid black;
		height:28px;
		
		}
		
	#indicator{
		width:0px;
		background:#840003;
		background:-moz-linear-gradient(#E60005, #840003);	
		background:-webkit-gradient(linear, left top, left bottom, from(#E60005), to(#840003));
		background:-webkit-linear-gradient(#E60005, #840003);
		background:-o-linear-gradient(#E60005, #840003);
		background:-ms-linear-gradient(#E60005, #840003);
		background:linear-gradient(#E60005, #840003);
		height:28px;
		margin:0;
	}	
        </style>  
        
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
        <form id="form1" method="post" name="form1">	
        <input type="hidden" name="id_fase_anterior" id="id_fase_anterior" value="">
        <input type="hidden" name="idtipo_fase_anterior" id="idtipo_fase_anterior" value="">
        <input type="hidden" name="fecha_nueva" id="fecha_nueva" value="">
        <input type="hidden" name="fecha_acep" id="fecha_acep" value="">
        <input type="hidden" name="fecha_entre" id="fecha_entre" value="">
        <input type="hidden" name="fecha_cul" id="fecha_cul" value="">
        <input type="hidden" name="idfase2" id="idfase2" value="">
        
            <article id="portada11">
            	<div id="solo_usuario2"> <?php echo $_SESSION['usu']  ?></div>
           	  <p class="planilla2">Seguimiento Proyectos</p>
            	<table width="516" border="1" style="border-color:lightgray;" class="tabla_seguimiento">
                	<tr>
                    	<td>Codigo ARC:</td>
                        <td>
                       
                       	<select name="codigo_arc" size="1"  style="width:300px; text-align:right; margin-left:10px" id="codigo_arc" onchange="obtener_codigo();">
                            	<option selected="selected" value="0" >Seleccione</option>
                                <?php $de->codigo_arc(); ?>
                           	</select>
                        </td>
                    </tr>
                    <tr>
                    	<td>Institucion:</td>
                        <td>
                        	<input name="inst" id="inst" size="50" type="text" style="font:bold 15px sans-serif; color:#000000; text-align:center; border:none; border-radius:5px;" readonly/>
                        </td>
                   	</tr>
                    <tr>
                    	<td>R.I.F:</td>
                        <td>
                        	<input name="rif" id="rif" size="50" type="text" style="font:bold 15px sans-serif; color:#000000; text-align:center; border:none; border-radius:5px;" readonly/>
                        </td>
                    </tr>
                    <tr>
                    	<td>Nombre Proyecto:</td>
                        <td>
                        	<input name="nombre" id="nombre" size="50" type="text" style="font:bold 15px sans-serif; color:#000000; text-align:center; border:none; border-radius:5px;" readonly/>
                        </td>
                    </tr>
                </table>
          
                 <table class="contendor_proyecto22">
                   		<tr>
                        	<td class="combos_demandas">Status Proyecto:</td>
                        </tr>
                        <tr>
                            <td>
                            	<div id="plan1">
                                	<select name="sta" size="1" id="sta" disabled>
                                    	<option selected="selected">Seleccione</option>
                                       <?php $de->status(); ?>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </table>
                
            	<table class="tabla_seguimiento" border="1" style="border-color:lightgray;">
                	<tr>
                       <td><div id="alert"><img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'></div></td>
                      <td> Fase:  </td>
                        <td>
                        <select id="idfase" name="idfase" disabled>
                        <option selected="selected"></option>
                        <?php $de->combo_fase(); ?>
                        </select>
                        
                        	
                        </td>
                        <td>  Sub-Fase:  </td>
                        <td>
                        	<select name="fas" size="1" id="fas" onChange="mover_fase();" disabled>
                            <option selected="selected" value="0"></option>
                                       <?php $de->combo_subfase(); ?>
                             </select>
                        </td>
                        <td>Alerta</td>
                        <td>
                        	<div id="alert2"><img src='../../shared/img/img alertas/Bombillo-Blanco.jpg' width='26' height='26'></div>
                       	</td>
                  </tr>
                  
              </table>
              <table id="tabcost" class="tabla_seguimiento" border="1" style="border-color:lightgray;" hidden="">
              <tr>
             
             <td>Costo Venta:</td>
                        <td>
                        	<input name="cost" id="cost" size="25" type="text" style="font:bold 15px sans-serif; color:#000000; text-align:center; border:none; border-radius:5px;"/>
              </td>
              
              <td>Ingresos Unico/Anual:</td>
                        <td>
                        	<input name="ing" id="ing" size="25" type="text" style="font:bold 15px sans-serif; color:#000000; text-align:center; border:none; border-radius:5px;"/>
              
              </tr>
               <tr>
             
             <td>Costo Venta Real:</td>
                        <td>
                        	<input name="costReal" id="costReal" size="25" type="text" style="font:bold 15px sans-serif; color:#000000; text-align:center; border:none; border-radius:5px;" value="0"/>
              </td>
              
              <td>Ingresos Unico/Anual Real:</td>
                        <td>
                        	<input name="ingReal" id="ingReal" size="25" type="text" style="font:bold 15px sans-serif; color:#000000; text-align:center; border:none; border-radius:5px;" value="0" />
              
              </tr>
              </table>
              <br>
             <table class="tabla_seguimiento" style="font-family:Arial, Helvetica, sans-serif; color:#666;">
             <tr>
 			 <td>Progreso del Proyecto:</td>
             </tr>
             <tr>
              <td width="700" height="50">
                  <div id="pwidget">
                  <div id="progressbar">
                  <div id="indicator"></div>
                  </div>
                  <div id="progresnum" align="center" style="color:#BD0F1C; font-family:Arial, Helvetica, sans-serif; text-shadow:0px 1px 0px #CCC; font-weight:bold;">0</div>
                  </div>
                 
                  </td>
                  </tr>
                 <table>
             
                <div id="botones_fases">
                	<input type="submit" name="actualizar" value="Actualizar" class="guardar" onclick="javascript:actualizar_segui();"/>
                    <input type="button" name="limpiar" value="Limpiar" class="cancelar" onclick="window.location.reload();"/>
               	</div>
            </article>
        </form>
        <footer>
        	<p>Copyleft 2013 - Gerencia De Instituciones Públicas- Todos Los Derechos Reservados |
            	<a href="../../manuales/manual usuario/Cantv. Manual Usuario.docx" target="_blank">Ayuda</a>
                |
                <a href="../contacto/contacto.php">Contacto</a>
            </p>
        </footer>
	</body>
</html>