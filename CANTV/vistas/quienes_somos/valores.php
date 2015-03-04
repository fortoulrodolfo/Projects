<?php
	session_start();
	include_once '../../config.php';
	$nas=new Usuario();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" oncontextmenu="return false" onkeydown="return false">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Nuestros Valores : : .</title>
        <link href="../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../controladores/cerrar/ctrl_cerrar.js"></script>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
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
            	<li class="item1"><a href="#">¿Quienes Somos?</a>
                	<ul>
                    	<li class="subitem1"><a href="mision_vision.php">Mision & Vision</a></li>
                        <li class="subitem2"><a href="valores.php">Nuestros Valores</a></li>
                        <li class="subitem3"><a href="estructura.php">Estructura Organizativa</a></li>
                   	</ul>
				</li>
			</ul>
        </aside>
        <article id="efecto_valores">
        	<ul>
            	<li><h3>Eficiencia</h3>
                	<div id="imagen1"></div>
                    <div>Nos orientamos al cumplimiento oportuno de nuestros objetivos y metas, enfocándonos en la obtención de resultados basados en la rentabilidad social, Impulsando la optimización de los procesos, profundizando el conocimiento y el autodesarrollo, asegurando de esta manera la Viabilidad económica de la Empresa	
                    </div>
                </li>
                <li><h3>Esfuerzo Colectivo</h3>
                    <div id="imagen2"></div>
                    <div class="verticalaccordion">Practicamos la cooperación y la complementariedad, propiciando el esfuerzo colectivo, como medio fundamental para alcanzar y superar, con pasión, los objetivos y las metas comunes. Nos basamos en el respeto, la confianza y  la comunicación de nuestras ideas, siendo autocríticos, escuchando y compartiendo con humildad las recomendaciones, las oportunidades de mejora y los logros
                    </div>
                </li>
                <li><h3>Ética Socialista</h3>
                    <div id="imagen3"></div>
                    <div class="verticalaccordion">Somos humanistas, orientamos nuestras acciones basados en el amor y el respeto por los semejantes, la justicia social, el desprendimiento, la solidaridad humana y la importancia de lo colectivo. Propiciamos el intercambio de saberes con la sociedad, contribuyendo en el proceso de formación y modelaje de conductas,  facilitando la transferencia de poder y conocimiento para la toma de decisiones por el pueblo. Promovemos nuevas relaciones de producción y de propiedad social
                    </div>
                </li>
                <li><h3>Honestidad</h3>
                    <div id="imagen4"></div>
                    <div class="verticalaccordion">Actuamos con transparencia, facilitando el acceso a información veraz y oportuna del ejercicio de nuestra función pública, a todos los relacionados con las actividades que realizamos, promoviendo el uso responsable, claro y racional de los recursos públicos que disponemos para realizar nuestras funciones
                    </div>
                </li>
                <li><h3>Igualdad</h3>
                    <div id="imagen5"></div>
                    <div class="verticalaccordion">Promovemos la inclusión de todas y todos, sin distinciones de etnia, edad, orientación sexual, salud, género, credo, condición social o política, jerarquía o cualquier otra que menoscabe la dignidad humana, estableciendo así relaciones basadas en la justicia social con nuestras usuarias, usuarios, trabajadoras, trabajadores, jubiladas, jubilados, comunidades, proveedores y aliados. Impulsamos el acceso a las telecomunicaciones de todas y todos como un derecho fundamental
                    </div>
                </li>
                <li><h3>Participación Protagónica</h3>
                    <div id="imagen6"></div>
                    <div class="verticalaccordion">Mantenemos una actitud optimista, creativa, positiva y emprendedora, enfocada en la generación de acciones y/o propuestas que demuestren compromiso y contribuyan con la gestión eficiente de la Organización. Estamos comprometidos con el diseño, desarrollo, ejecución, evaluación y control de las iniciativas y actividades de la Corporación, de manera sistemática y sostenida en el tiempo. Creamos y compartimos espacios directos de comunicación e intercambio para fortalecer la participación popular. Somos corresponsables de la seguridad, defensa y soberanía de la nación
                    </div>
                </li>
                <li><h3>Responsabilidad</h3>
                    <div id="imagen7"></div>
                    <div class="verticalaccordion">Nos enfocamos en el cumplimiento de nuestros objetivos y actividades alineados con la Orientaciones Estratégicos y Planes Operativos, asumiendo con humildad el impacto de nuestras decisiones y las consecuencias de nuestros actos, aprendiendo de ellas con disposición de mejorar y aplicar correctivos inmediatos
                    </div>
                </li>
                <li><h3>Solidaridad</h3>
                    <div id="imagen8"></div>
                    <div class="verticalaccordion">Nos esforzamos en ayudar a otros y actuamos en función del bienestar colectivo. Propiciamos el intercambio con las comunidades para conocer sus necesidades, intereses, sentimientos, preocupaciones y contribuir  a la mejora de su calidad de vida. Valoramos nuestra contribución como trabajadoras y trabajadores al desarrollo y transformación de la sociedad
                    </div>
                </li>
                <li><h3>Vocación De Servicio</h3>
                    <div id="imagen9"></div>
                    <div class="verticalaccordion">Sentimos satisfacción y pasión por brindar la mejor atención y calidad de servicio, teniendo claro nuestro rol como servidores públicos. Nos comprometemos a “entender, atender y resolver” las necesidades de aquellos a los que servimos, orientándonos permanentemente a su satisfacción y a superar sus expectativas. Estamos en constante desarrollo, mejoramiento de nuestras capacidades y abiertos al aprendizaje de nuevos conocimientos, con la finalidad de prestar nuestro mejor servicio
                    </div>
                </li>
            </ul>
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