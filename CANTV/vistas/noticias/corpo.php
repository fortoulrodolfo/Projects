<?php
	session_start();
	include_once '../../config.php';
	$nas=new Usuario();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" oncontextmenu="return false" onkeydown="return false">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Corporativo : : .</title>
        <link href="../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../controladores/cerrar/ctrl_cerrar.js"></script>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="../../shared/plugins/admin/noticias/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="../../shared/plugins/admin/noticias/jquery-ui-1.9.0.custom.min.js"></script>
		<script type="text/javascript" src="../../shared/plugins/admin/noticias/jquery-ui-tabs-rotate.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
			$("#featured").tabs({fx:{opacity: "toggle"}}).tabs("rotate", 5000, true);
			});
        </script>
        <script language="Javascript">
			function disableselect(e){
				return false
					}
			function reEnable(){
				return true
					}
					document.onselectstart=new Function ("return false")
				if (window.sidebar){
					document.onmousedown=disableselect
					document.onclick=reEnable
				}
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
        <aside id="contenedor_todo2">
        	<ul class="menu_interno">
            	<li class="item1"><a href="#">Noticias</a>
                	<ul>
                    	<li class="subitem1"><a href="inst_publicas.php">Insttituciones Publicas</a></li>
                        <?php if(isset($_SESSION['usu'])) { ?>
							<li class="subitem2"><a href="corpo.php">Corporativo</a></li>
						<?php } ?>
                   	</ul>
				</li>
			</ul>
        </aside>
        <article id="portada2">
        	<div id="example">
				<img src="../../shared/img/img noticias corp/new-ribbon.png" alt="New Ribbon" id="ribbon" height="112" width="112">
        	</div>
            <div id="featured">
            	<ul class="ui-tabs-nav">
                	<li class="ui-tabs-nav-item" id="nav-fragment-1">
                    	<a href="#fragment-1">
                    		<img src="../../shared/img/img noticias corp/image1-small.jpg" alt=""/>
                            	<span>Noticia Nº. 1</span>
                        </a>
                    </li>
                    <li class="ui-tabs-nav-item" id="nav-fragment-2">
                    	<a href="#fragment-2">
                        	<img src="../../shared/img/img noticias corp/image2-small.jpg" alt=""/>
                            	<span>Noticia Nº. 2</span>
                        </a>
                    </li>
                    <li class="ui-tabs-nav-item" id="nav-fragment-3">
                    	<a href="#fragment-3">
                        	<img src="../../shared/img/img noticias corp/image3-small.jpg" alt=""/>
                            	<span>Noticia Nº. 3</span>
                        </a>
                    </li>
                    <li class="ui-tabs-nav-item" id="nav-fragment-4">
                    	<a href="#fragment-4">
                        	<img src="../../shared/img/img noticias corp/image4-small.jpg" alt=""/>
                            	<span>Noticia Nº. 4</span>
                        </a>
                    </li>
				</ul>
                <div id="fragment-1" class="ui-tabs-panel" style="">
                	<img src="../../shared/img/img noticias corp/image1.jpg" alt=""/>
                    	<div class="info">
							<h2><a href="#">Noticia Nº. 1</a></h2>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla tincidunt condimentum lacus. Pellentesque ut diam <a href="#">Seguir Leyendo...</a>
                            </p>
			 			</div>
	    		</div>
                <div id="fragment-2" class="ui-tabs-panel ui-tabs-hide" style="">
					<img src="../../shared/img/img noticias corp/image2.jpg" alt=""/>
			 			<div class="info">
							<h2><a href="#">Noticia Nº. 2</a></h2>
							<p>Vestibulum leo quam, accumsan nec porttitor a, euismod ac tortor. Sed ipsum lorem, sagittis non egestas id, suscipit <a href="#">Seguir Leyendo...</a>
                            </p>
			 			</div>
	    		</div>
                <div id="fragment-3" class="ui-tabs-panel ui-tabs-hide" style="">
					<img src="../../shared/img/img noticias corp/image3.jpg" alt=""/>
			 			<div class="info">
							<h2><a href="#">Noticia Nº. 3</a></h2>
							<p>liquam erat volutpat. Proin id volutpat nisi. Nulla facilisi. Curabitur facilisis sollicitudin ornare <a href="#">Seguir Leyendo...</a>
                            </p>
	         			</div>
	    		</div>
                <div id="fragment-4" class="ui-tabs-panel ui-tabs-hide" style="">
					<img src="../../shared/img/img noticias corp/image4.jpg" alt=""/>
			 			<div class="info">
							<h2><a href="#">Noticia Nº. 4</a></h2>
							<p>Quisque sed orci ut lacus viverra interdum ornare sed est. Donec porta, erat eu pretium luctus, leo augue sodales <a href="#">Seguir Leyendo...</a>
                            </p>
	         			</div>
	    		</div>
			</div>
        </article>
        <footer>
        	<p>Copyleft 2013 - Gerencia De Instituciones Públicas- Todos Los Derechos Reservados |
            	<a href="../../manuales/manual usuario/Cantv. Manual Usuario.pdf" target="_blank">Ayuda</a>
                |
                <a href="../contacto/contacto.php">Contacto</a>
            </p>
        </footer>
	</body>
</html>