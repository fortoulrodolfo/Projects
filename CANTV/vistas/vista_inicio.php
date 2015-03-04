<?php
	session_start();
	include_once '../config.php';
	$nas=new Usuario();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>. : : Cantv - Instituciones P&uacute;blicas : : .</title>
        <link href="../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../controladores/perfil/lsdAjax.js"></script>
        <script src="../controladores/inicio_sesion/ctrl_inicio_sesion.js"></script>
        <script type="text/javascript" src="../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script>
			!function(d,s,id){
				var js,fjs=d.getElementsByTagName(s)[0];
				if(!d.getElementById(id)){
					js=d.createElement(s);
					js.id=id;
					js.src="//platform.twitter.com/widgets.js";
					fjs.parentNode.insertBefore(js,fjs);
					}
				}
			(document,"script","twitter-wjs");
        </script>
        <script>alertas_consultores()</script>
    </head>
	<body>
    	<header>
        	<hgroup>
       	  		<h1></h1>
                <marquee direction="right" behavior="scroll">
                    	<img src="../shared/img/img cabecera/Animacion 1. Amarillo & Azul.jpg"/>
                        <img src="../shared/img/img cabecera/Animacion 2. Rojo Cantv.jpg"/>
                </marquee>
            </hgroup>
        </header>
        <nav id="menu">
        	<li><a href="vista_inicio.php">Inicio</a></li>
			<li><a href="#">Noticias</a>
				<ul>
					<li><a href="noticias/inst_publicas.php">Instituciones P&uacute;blicas</a></li>
                    <?php if(isset($_SESSION['usu'])) { ?>
                    	<li><a href="noticias/corpo.php">Corporativo</a></li>
            		<?php } ?>
                </ul>
			</li>
            <?php if(isset($_SESSION['usu'])) { ?>
				<!-- Acceso al Menu de Usuario : CONSULTOR -->
					<?php if(($_SESSION['tipo']) == "4") :?>
                    <li><a href="#">¿Quienes Somos?</a>
                        <ul>
                            <li><a href="quienes_somos/mision_vision.php">Misi&oacute;n & Visi&oacute;n</a></li>
                            <li><a href="quienes_somos/valores.php">Nuestros Valores</a></li>
                            <li><a href="quienes_somos/estructura.php">Estructura Organizativa</a></li>
                        </ul>
                    </li>
					<li><a href="servicios/servicioslimpio.php">Servicios</a></li>
					<li><a href="#">Demanda</a>
						<ul>
							<li><a href="demanda/vista_registrar.php">Registro Demanda</a></li>
							<li><a href="demanda/vista_ver.php">Listado Demanda</a></li>
						</ul>
					</li>
					<li><a href="#">Proyecto</a>
						<ul>
							<li><a href="proyecto/proyecto.php">Registro Proyecto</a></li>
							<li><a href="proyecto/ver_proyecto.php">Listado De Proyecto</a></li>
							<li><a href="proyecto/informe_mes.php">Panel De Control</a></li>
							<li><a href="proyecto/fases_proyecto.php">Seguimiento De Proyecto</a></li>
						</ul>
					</li>
					<li><a href="#">Data I.P.</a>
						<ul>
							<li><a href="data_inst/data_ip.php">Registro Inst. P&uacute;blicas</a></li>
							<li><a href="data_inst/lis_dataip.php">Listado Inst. P&uacute;blicas</a></li>
						</ul>
					</li>
					<li><a href='javascript:cerrar()'>Salir</a></li>
		<!-- ------------------------------------------------------------------------------------------------------------ -->
					<!-- Acceso al Menu de Usuario : GERENTE -->
					<?php elseif(($_SESSION['tipo']) == "3") :?>
                    	<li><a href="#">¿Quienes Somos?</a>
                            <ul>
                                <li><a href="quienes_somos/mision_vision.php">Misi&oacute;n & Visi&oacute;n</a></li>
                                <li><a href="quienes_somos/valores.php">Nuestros Valores</a></li>
                                <li><a href="quienes_somos/estructura.php">Estructura Organizativa</a></li>
                            </ul>
                        </li>
						<li><a href="#">Demanda</a>
							<ul>
								<li><a href="demanda/vista_ver.php">Listado Demanda</a></li>
							</ul>
						</li>
						<li><a href="#">Proyecto</a>
							<ul>
								<li><a href="proyecto/ver_proyecto.php">Listado De Proyecto</a></li>
								<li><a href="proyecto/informe_mes.php">Panel De Control</a></li>
							</ul>
						</li>
						<li><a href="#">Data I.P.</a>
							<ul>
								<li><a href="data_inst/lis_dataip.php">Listado Inst. P&uacute;blicas</a></li>
							</ul>
						</li>
                        <li><a href="#">Reportes</a>
							<ul>
								<li><a href="reportes/reporte_demanda.php">Reportes Demandas</a></li>
                                <li><a href="reportes/reporte_proyecto.php">Reportes Proyectos</a></li>
                                <li><a href="reportes/reporte_dataip.php">Reportes Data I.P.</a></li>
							</ul>
						</li>
						<li><a href='javascript:cerrar()'>Salir</a></li>
		<!-- ------------------------------------------------------------------------------------------------------------ -->				
						<!-- Acceso al Menu de Usuario : LIDER -->
						<?php elseif(($_SESSION['tipo']) == "2") :?>
                        	<li><a href="#">Consultor</a>
								<ul>
									<li><a href="administrador/modificar/modificar_cartera.php">Modificar Cartera Consultor</a></li>
								</ul>
							</li>
							<li><a href="#">Demanda</a>
								<ul>
									<li><a href="demanda/vista_ver.php">Listado Demanda</a></li>
								</ul>
							</li>
							<li><a href="#">Proyecto</a>
								<ul>
									<li><a href="proyecto/ver_proyecto.php">Listado De Proyecto</a></li>
									<li><a href="proyecto/informe_mes.php">Panel De Control</a></li>
								</ul>
							</li>
							<li><a href="#">Data I.P.</a>
								<ul>
									<li><a href="data_inst/lis_dataip.php">Listado Inst. P&uacute;blicas</a></li>
								</ul>
							</li>
                            <li><a href="#">Reportes</a>
                                <ul>
                                    <li><a href="reportes/reporte_demanda.php">Reportes Demandas</a></li>
                                    <li><a href="reportes/reporte_proyecto.php">Reportes Proyectos</a></li>
                                    <li><a href="reportes/reporte_dataip.php">Reportes Data I.P.</a></li>
                                </ul>
							</li>
							<li><a href='javascript:cerrar()'>Salir</a></li>
		<!-- ------------------------------------------------------------------------------------------------------------ -->
							<!-- Acceso al Menu de Usuario : ADMINISTRADOR -->
							<?php elseif(($_SESSION['tipo']) == "1") :?>
								<li><a href="#">Registrar</a>
									<ul>
										<li><a href="administrador/registrar/registrar_usuario.php">Registrar Usuario</a></li>
                                        <li><a href="administrador/registrar/registrar_institucion.php">Registrar Instituciones P&uacute;blicas</a></li>
                                        <li><a href="administrador/registrar/registrar_central.php">Registrar Central</a></li>
									</ul>
								</li>
								<li><a href="#">Modificar</a>
									<ul>
                                    	<li><a href="administrador/modificar/modificar_usuario.php">Modificar Usuario</a></li>
                                        <li><a href="administrador/modificar/modificar_cartera.php">Modificar Cartera de Clientes</a></li>
									</ul>
								</li>
                                <li><a href="#">Reportes</a>
                                    <ul>
                                        <li><a href="reportes/reporte_demanda.php">Reportes Demandas</a></li>
                                        <li><a href="reportes/reporte_proyecto.php">Reportes Proyectos</a></li>
                                        <li><a href="reportes/reporte_dataip.php">Reportes Data I.P.</a></li>
                                    </ul>
								</li>
								<li><a href="administrador/importar/importar.php">Importar Excel</a></li>
								<li><a href='javascript:cerrar()'>Salir</a></li>
				<?php endif; ?>
            <?php } ?>
     	</nav>
        <div class="der"> 
        	<div id="per" >
				<?php if(!isset($_SESSION['usu'])) { ?>
                    <aside id="contenedor_todo">
                        <div id="contenedor_login">
                            <h2>Iniciar Sesi&oacute;n</h2>
                        </div>
                        <div class="contenido_inicio">
                            <fieldset id="inputs">
                                <label for="usuario"></label>
                                    <input name="usuario" id="usuario" type="text" title="Introduzca Su Cuenta" placeholder="Usuario" required="required"/>
                                <label for="usuario"></label>
                                    <input name="contraseña" id="contraseña" type="password" title="Introduzca Contraseña" placeholder="Contraseña" required="required"/>
                            </fieldset>
                            <fieldset id="actions">
                                <input type="button" name="inicio_sesion" class="inicio_sesion" value="Entrar" onclick="javascript:entrar();"/>
                            </fieldset>
                            &nbsp;
                            <ul>
                                <a href="#">¿No Puedes Acceder A Tu Cuenta?</a>
                            </ul>
                        </div>
                <?php } else { ?>
                	<table class="perfil">
                    	<thead></thead>
                        <tbody>
                            <?php 
                                $as=new Usuario();
								
								if($_SESSION['tipo']=="1"){
									$as->mostrar_perfil_admin();
									}
								if($_SESSION['tipo']!="1"){
                                	$as->mostrar_perfil("demanda/");
									}	
                            ?>
                        </tbody>
                    </table>
                <?php } ?>
                	<section id="twitter">
                		<a class="twitter-timeline"  href="https://twitter.com/Inst_Pblicas" data-widget-id="273166535135215616">Tweets por @Inst_Pblicas</a>
                	</section>
        		</aside>
        	</div>
        </div>
        
        <article id="portada"></article>
        
        <footer>
        	<p>Copyleft 2013 - Gerencia De Instituciones P&uacute;blicas- Todos Los Derechos Reservados |
            	<a href="../manuales/manual usuario/Cantv. Manual Usuario.pdf" target="_blank">Ayuda</a>
                |
                <a href="contacto/contacto.php">Contacto</a>
            </p>
        </footer>
    </body>
</html>