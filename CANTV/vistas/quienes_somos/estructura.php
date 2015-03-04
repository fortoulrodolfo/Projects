<?php
	session_start();
	include_once '../../config.php';
	$nas=new Usuario();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" oncontextmenu="return false" onkeydown="return false">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Estructura Organizativa : : .</title>
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
        <script language="javascript">
			$(document).ready(function(){
				$("body").niceScroll();
				$("container_aquisolamente")({cursorborder:"", cursorcolor:"#000, boxzoom:true"});
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
		<article id="portada2">
        	<ul class="menu">
            	<li></li>
            	<li tabindex="1">
                	<span class="title">Gerente Corporativo Consultoria, Servicios Y Soluciones</span>
                    <div class="content">Jose Gregorio Vielma
                    	<div id="container">
                    		<div class="mainButton6">Administrador De Soporte
                            	<div class="dropConten1">
                                	<ul>Franklin Biarrieta</ul>
                                </div>
                       		</div>
                    	</div>
                        <div id="container">
                    		<div class="mainButton6">Secretaria Ejecutiva
                            	<div class="dropConten1">
                                	<ul>Vacante</ul>
                                </div>
                       		</div>
                    	</div>
                    </div>
    			</li>
    			<li></li>
                <li tabindex="1">
                	<span class="title">Gerente De Sector Desarrollo Energetico Y Minero</span>
                    <div class="content">Eduardo Jose Hernandez
                    	<div id="container">
                    		<div class="mainButton">Lider De Sector Desarrollo<br/>Energetico Y Minero<br/>(Capital)
                            	<div class="dropConten">
                                	<ul>Giuseppe Bellosi</ul>
                                    	<div class="mainButton1">Consultor De servicios Y Soluciones
                                        	<div class="dropConten">
                                                <ul>Rodolfo Polanco</ul>
                                                <ul>Claudia Bullones</ul>
                                                <ul>Miguel Morantes</ul>
                                                <ul>Freddy Perez</ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
                    	<div id="container">
                    		<div class="mainButton">Lider De Sector Desarrollo<br/>Energetico Y Minero<br/>(Occidente)
                            	<div class="dropConten">
                                	<ul>Evelyn Soto</ul>
                                    	<div class="mainButton1">Consultor De servicios Y Soluciones
                                        	<div class="dropConten">
                                                <ul>Juan Carlos Adrianza</ul>
                                                <ul>Adelhaidy Peley</ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
                        <div id="container">
                    		<div class="mainButton">Lider De Sector Desarrollo<br/>Energetico Y Minero<br/>(Oriente)
                            	<div class="dropConten">
                                	<ul>Maria Nieves Villaroel</ul>
                                    	<div class="mainButton1">Consultor De servicios Y Soluciones
                                        	<div class="dropConten">
                                        		<ul>Francisco Rojas</ul>
                                                <ul>Francisco Rodriguez</ul>
                                                <ul>Cristian Escobar</ul>
                                                <ul>Elimar Estaba</ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
                    </div>
    			</li>
    			<li tabindex="1">
                	<span class="title2">Gerente De Sector Desarrollo Regional Y Local</span> 
      				<div class="content">Francisco Cardozo
                    	<div id="container">
                    		<div class="mainButton7">Lider De Sector Desarrollo<br/>Regional Y Local<br/>(Andes)
                            	<div class="dropConten2">
                                	<ul>Maria Aranguren</ul>
                                    	<div class="mainButton9">Consultor De servicios Y Soluciones
                                        	<div class="dropConten2">
                                                <ul>Giovanni Davila</ul>
                                                <ul>Aida Camacho</ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
                    	<div id="container">
                    		<div class="mainButton7">Lider De Sector Desarrollo<br/>Regional Y Local<br/>(Occidente)
                            	<div class="dropConten2">
                                	<ul>Alfredo Machado</ul>
                                    	<div class="mainButton9">Consultor De servicios Y Soluciones
                                        	<div class="dropConten2">
                                                <ul>Robert Morillo</ul>
                                                <ul>Blanca Martinez</ul>
                                                <ul>David Jesus Higuera</ul>
                                            </div>
                                        </div>
                               </div>
                       		</div>
                    	</div>
                        <div id="container">
                    		<div class="mainButton8">Lider De Sector Desarrollo<br/>Regional Y Local<br/>(Centro Occidente)
                            	<div class="dropConten2">
                                	<ul>Ferney Velasco</ul>
                                    	<div class="mainButton9">Consultor De servicios Y Soluciones
                                        	<div class="dropConten2">
                                        		<ul>Luis Bastida</ul>
                                                <ul>Maria Gonzalez</ul>
                                                <ul>Tiunary Perez</ul>
                                                <ul>
                                                	<div class="mainButton9">Administrador De Soporte
                                                    	<div class="dropConten2">
                                        					<ul>Erika Freites</ul>
                                                        </div>
                                                    </div>
                                                </ul>
                                            </div>
                                        </div>
                                </div>
                            </div>
                    	</div>
                        <div id="container">
                    		<div class="mainButton7">Lider De Sector Desarrollo<br/>Regional Y Local<br/>(Centro)
                            	<div class="dropConten2">
                                	<ul>Maria Z. Hernandez</ul>
                                    	<div class="mainButton9">Consultor De servicios Y Soluciones
                                        	<div class="dropConten2">
                                        		<ul>Rodolfo De Leon</ul>
                      		                    <ul>Maria A. Guevara</ul>
                                                <ul>Sandra Losada</ul>
                                                <ul>
                                                	<div class="mainButton9">Administrador De Soporte
                                                    	<div class="dropConten2">
                                        					<ul>Monica Gutierrez</ul>
                                                        </div>
                                                    </div>
                                                </ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
                        <div id="container">
                    		<div class="mainButton7">Lider De Sector Desarrollo<br/>Regional Y Local<br/>(Oriente)
                            	<div class="dropConten2">
                                	<ul>Ronald Ruiz</ul>
                                    	<div class="mainButton9">Consultor De servicios Y Soluciones
                                        	<div class="dropConten2">
                                        		<ul>Jose Sierra</ul>
                                                <ul>Bethzabeth Ugas</ul>
                                                <ul>Jose Naranjo</ul>
                                                <ul>Juan Carlos Matute</ul>
                                                <ul>Lesly Blanco</ul>
                                                <ul>
                                                	<div class="mainButton9">Administrador De Soporte
                                                    	<div class="dropConten2">
                                        					<ul>Mercedes Mirabal</ul>
                                                       	</div>
                                                    </div>
                                                </ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
                	</div>
    			</li>
    			<li tabindex="1">
                	<span class="title">Gerente De Sector Desarrollo Economico, Comercial E Industrial</span> 
      				<div class="content">Pablo Moya
                    	<div id="container">
                    		<div class="mainButton6">Lider De Sector<br/>Desarrollo Banca
                            	<div class="dropConten1">
                                	<ul>Jesus Andrade</ul>
                                    	<div class="mainButton3">Consultor De servicios Y Soluciones
                                        	<div class="dropConten1">
                                                <ul>Johana Gonzalez</ul>
                                                <ul>Marbella Rojas</ul>
                                                <ul>Yasmir Rivas</ul>
                                                <ul>Julio Guillen</ul>
                                                <ul>Elvira Gonzalez</ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
                        <div id="container">
                    		<div class="mainButton6">Lider De Sector Desarrollo<br/>Comercial E Industrial
                            	<div class="dropConten1">
                                	<ul>Beatriz Jaimes</ul>
                                    	<div class="mainButton3">Consultor De servicios Y Soluciones
                                        	<div class="dropConten1">
                                                <ul>Patricia Canos</ul>
                                                <ul>Pablo Rodriguez</ul>
                                                <ul>Carlos Pimentel</ul>
                                                <ul>Scarlett Rodriguez</ul>
                                                <ul>Elvira Gonzalez</ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
                    </div>
    			</li>
    			<li tabindex="1">
                	<span class="title">Gerente De Sector Desarrollo Social Y Calidad De Vida</span>
      				<div class="content">Vacante
                    	<div id="container">
                    		<div class="mainButton2">Lider De Sector Desarrollo<br/>Salud, Alimentacion E Infraestrctura
                            	<div class="dropConten1">
                                	<ul>Barbara Loreto</ul>
                                    	<div class="mainButton3">Consultor De servicios Y Soluciones
                                        	<div class="dropConten1">
                                                <ul>Deyamira Ramirez</ul>
                                                <ul>Saul Parra</ul>
                                                <ul>Darrelson Marquez</ul>
                                                <ul>Gisela Florillo</ul>
                                                <ul>Rossana Peralta</ul>
                                                <ul>Yamileth Melendez</ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
                        <div id="container">
                    		<div class="mainButton4">Lider De Sector Educacion<br/>Innovacion Y Ambiente
                            	<div class="dropConten1">
                                	<ul>Mario Suarez</ul>
                                    	<div class="mainButton5">Consultor De servicios Y Soluciones
                                        	<div class="dropConten1">
                                                <ul>Keyla Mendez</ul>
                                                <ul>Mayerling Bustamante</ul>
                                                <ul>Angela Zapata</ul>
                                                <ul>Yusmary Lezama</ul>
                                                <ul>Ana Balza</ul>
                                                <ul>Nelson Condore</ul>
                                                <ul>Maria Piñero</ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
					</div>
    			</li>
    			<li tabindex="1">
                	<span class="title3">Gerente De Sector Desarrollo Alto Gobierno, Poderes P, Seguridad Y Defensa</span>
      				<div class="content">Carlos Brito
                    <div id="container">
                    		<div class="mainButton">Lider De Sector Desarrollo<br/>Seguridad Y Defensa
                            	<div class="dropConten">
                                	<ul>Jorge Guaremata</ul>
                                    	<div class="mainButton1">Consultor De servicios Y Soluciones
                                        	<div class="dropConten">
                                        		<ul>Rafael Camacho</ul>
                                                <ul>Luis Guerrero</ul>
                                                <ul>Pedro Torrealba</ul>
                                            </div>
                                        </div>
                                </div>
                       		</div>
                    	</div>
                    	<div id="container">
                    		<div class="mainButton">Lider De Sector Desarrollo<br/>Poderes Publicos
                            	<div class="dropConten">
                                	<ul>Jesus Villamarin</ul>
                                    	<div class="mainButton1">Consultor De servicios Y Soluciones
                                        	<div class="dropConten">
                                        		<ul>Kennet Vershuren</ul>
                                                <ul>Rosa Guzman</ul>
                                                <ul>Alexandre Sanchez</ul>
                                            </div>
                                        </div>
        						</div>
                       		</div>
                    	</div>
                        <div id="container">
                    		<div class="mainButton">Lider De Sector Desarrollo<br/>Alto Gobierno
                            	<div class="dropConten">
                                	<ul>Jose Daniel Gutierrez</ul>
                                    	<div class="mainButton1">Consultor De servicios Y Soluciones
                                        	<div class="dropConten">
                                        		<ul>Ronny Hernandez</ul>
                                                <ul>Arcelis	Diaz</ul>
                                                <ul>Yusmary Laguna</ul>
                                            </div>
                                        </div>
                               	</div>
                       		</div>
                    	</div>
                    </div>
    			</li>
    			<li tabindex="1">
                	<span class="title1">Cantv</span>
      					<div class="content">Reseña Historica
                        	<div id="container_aquisolamente"><img src="../../shared/img/img organigrama/1.png" class="img_organigrama1"/>La Compañía Anónima Nacional Teléfonos de Venezuela (Cantv), fue fundada e inscrita de manera formal en el Registro de Comercio en el año de 1930, con una concesión otorgada por el Ministro de Fomento, Gumersindo Torres al comerciante venezolano Félix A. Guerrero, al abogado Alfredo Damirón y a otro comerciante. Dicha concesión tenía como fin construir y explotar una red telefónica en el Distrito Federal y los llamados Estados de la Unión.<br/><br/>

Ese mismo año adquiere la Compañía de Teléfonos de Maracaibo, las instalaciones telefónicas de Ciudad Bolívar y la Venezuelan Telephone and Electrical Appliances Company Limited, empresa de origen inglés que proveía servicios de teléfonos desde Caracas hasta las poblaciones de Puerto Cabello, San Juan de Los Morros, Ocumare del Tuy y Macuto. También se inaugura la primera central Strowge, con lo cual se inicia la automatización del servicio telefónico y la multiplicación de centrales, debido al incremento de suscriptores.<br/><br/>

Consecutivamente, en 1931, el Ministerio de Fomento declara abierto el servicio Radiotelefónico Internacional. La empresa alemana Telefunken era la responsable del funcionamiento de la estación radio–eléctrica, con la cual se establecía comunicación directa entre Maracay, Miami, Estados Unidos y Europa. Al año siguiente, el general Eleazar López Contreras crea el Ministerio de Comunicaciones e incluye la Dirección de Telecomunicaciones, y se promulga la Ley de Telecomunicaciones que deroga la Ley de Teléfonos y Telégrafos Federales vigente desde 1918. El 29 de julio de 1940 se promulga la nueva Ley de Telecomunicaciones que le asigna al Estado la administración de estos servicios.<br/><br/>

En 1946, se produce un cambio en materia de servicios telefónicos, ya que otorgar concesiones para que fueran explotados por particulares. En ese momento, el Estado comienza a contratar y administrar directamente las redes de telecomunicaciones. <img src="../../shared/img/img organigrama/2.png" class="img_organigrama2"/>Un año después, por medio de la Dirección de Telecomunicaciones, contrata la empresa Ericsson, la instalación de un sistema telefónico de 1.150 líneas automáticas y 420 manuales para las poblaciones del estado Táchira. Al asumir la explotación directa de los servicios de telefonía, el Estado comienza a desplazar a Cantv como principal prestatario privado de los servicios telefónicos en Venezuela.<br/><br/>

Para el año 1951, Cantv desarrolla un plan de expansión y modernización de sus líneas que le permitirían, corregir las deficiencias del servicio y ampliar su red. El Ejecutivo Nacional designó una comisión que se encargaría de analizar el referido proyecto, pero esta misma decide rechazar las solicitudes y esta decisión abre paso a la nacionalización de Cantv.<br/><br/>

En 1953, la nación adquiere las acciones ordinarias de Cantv (20.000). El objetivo era crear una red telefónica independiente y utilizar las partes aprovechables de la empresa anterior. En este proceso, la compañía Telephone Properties LTD, mantuvo 4.895 acciones que fueron posteriormente adquiridas por el Estado. Es así, como se inicia un proceso de adquisiciones de empresas telefónicas que finaliza con la compra de la Compañía de Teléfonos de San Fernando de Apure.<br/><br/>

Para 1955, se celebra una Asamblea Extraordinaria en donde se incrementa el capital social de la empresa, se aumenta el valor nominal de las acciones comunes; se reforman los Estatutos Sociales y se modifica el contrato de concesión suscrito con el Ejecutivo Nacional. Se revisa el Contrato de Concesión otorgado a Félix A. Guerrero, vigente hasta entonces por casi 25 años, a objeto de dotar a Cantv de las atribuciones y facultades que requería para afrontar la modernización del servicio telefónico, la extensión de sus redes, la inversión de sus utilidades en el fomento y mejora del servicio.<br/><br/>

En 1962, el Ejecutivo Nacional le asigna a Cantv la operación, administración y desarrollo de los servicios de telefonía local, larga distancia, télex, radio, facsímil, teléfonos, transmisión de datos y otras facilidades para la transmisión de radiodifusión y televisión. Asimismo, solicita al Fondo Especial de las Naciones Unidas una ayuda para la creación del Centro de Estudios para Técnicos de Telecomunicaciones (CETT), aporte que se concreta en 1964 con la firma del Plan de Operaciones suscrito por el Ministerio de Comunicaciones, el Subsecretario General de la Unión Internacional de Telecomunicaciones (UIT) y representantes del Programa de las Naciones Unidas para el Desarrollo (PNUD).<br/><br/><img src="../../shared/img/img organigrama/3.png" class="img_organigrama3"/><br/><br/>

Posteriormente, Cantv suscribe un contrato con la American Telephone and Telegraph, (AT&T) y la Transoceanic Communications Incorporated para la construcción de un cable submarino, con capacidad de 83 canales, que enlazaría a Venezuela con las Islas Vírgenes para establecer comunicaciones confiables y de calidad con Estados Unidos. En ese período, se introduce el Discado Directo Nacional y la instalación de las primeras centrales télex.<br/><br/>

En 1965 Venezuela firma, como uno de los primeros países asociados, los acuerdos interinos del Consorcio Internacional de Comunicaciones Vía Satélite (INTELSAT). El 29 de noviembre de 1970 se inaugura la Estación Rastreadora Camatagua I, con la cual Venezuela se interconecta con el mundo a través del satélite INTELSAT IVA. En enero de 1973, se crea la empresa Manufacturas Plásticas y Telefónicas MPT (Maplatex), con el propósito de producir 750 mil teléfonos anuales para la industria nacional.<br/><br/>

Finalmente, en 1988 se concretan algunos de los planes previstos, entre ellos: telefonía rural en zonas fronterizas y agropecuarias, y la red pública conmutada de transmisión de datos. Se instalan teléfonos monederos bidireccionales, de tarjeta magnética, teletasa, se adquieren 152.000 líneas digitales de contado y 848.000 en una negociación a tres (3) años.<br/><br/>

<img src="../../shared/img/img organigrama/4.png" class="img_organigrama1"/>En 1990 se vence el Contrato de Concesión que Cantv tiene con el Estado, en ese tiempo, el Estado atraviesa una comprometida situación financiera para afrontar los requerimientos de los servicios de telecomunicaciones y aplaza por seis (6) meses el contrato de concesión. Mientras ocurre todo esto, se nombra una comisión integrada por el Ministerio de Transporte y Comunicaciones, el Fondo de Inversiones de Venezuela y la oficina de Coordinación de Planificación de la Presidencia de la República (Cordiplan) que se pronuncia a favor de privatizar la empresa.<br/><br/>

En tal sentido, se abre una licitación internacional para la venta del 40% de las acciones de Cantv, con lo cual se otorgaron derechos para instalar, desarrollar, mantener y comercializar el servicio de telecomunicaciones del país. En 1991, resulta ganador el Consorcio Ven World Telecom, C.A. liderado por GTE Corporation por el 51% de las acciones de la empresa y lo integraban, además, Telefónica Internacional de España, Electricidad de Caracas, cada uno con 16%, el Consorcio Inversionista Mercantil CIMA con 12% y AT&T Internacional con 5% del capital.<br/><br/>

Durante los primeros seis (6) años como empresa privatizada, se emprende la expansión y modernización de las redes de voz, datos, fijas y móviles. Esta novedosa plataforma tecnológica, cubrió todo el territorio nacional y permitió atender la creciente demanda de telecomunicaciones de los venezolanos. Asimismo, se avanzó en la instalación del cable costero de fibra óptica y entran en servicio los cables submarinos de fibra óptica Américas I, Columbus II y Panamericano, lo cual garantizo la comunicación simultánea digital de voz, datos y video entre Venezuela, Norteamérica, el Caribe, Suramérica y Europa.<br/><br/>

En 1993 se produce el relanzamiento de Caveguías que orienta sus servicios al cliente, moderniza su infraestructura, cambia su imagen y logo, mediante un cambio accionario que eleva el control de Cantv a 80%, con un socio estratégico (Grabados Nacionales del Grupo Capriles), que aportó 20% del capital accionario. En 1995 nace Cantv Servicios, posteriormente convertida en Cantv.net, con el propósito de proveer a los clientes servicios de valor agregado.<br/><br/>

Entre 1998 y 2000, se concreta la transformación de la estructura organizacional de Cantv y se crean las unidades de negocio con un nuevo enfoque estratégico: el cliente. Se inicia así una nueva ruta, con lo cual la cultura corporativa da un giro donde el mercado pasa a dominar la dinámica de la gestión de la organización. <img src="../../shared/img/img organigrama/5.png" class="img_organigrama2"/>Dentro del proceso de expansión comercial, se remozan las Oficinas de Atención al Cliente, las cuales se orientan, hacia la atención y venta. Paralelamente, se introducen puntos de contacto con el cliente, como los Centros de Comunicaciones y las Taquillas de Paso, que además de recaudar comienzan a ofrecer productos y servicios.<br/><br/>

Luego de la aprobación de la Ley Orgánica de Telecomunicaciones y el comienzo de la apertura total del mercado de las telecomunicaciones, Cantv, evoluciona hacia la integración de las empresas del grupo. Este proceso permite ofrecer, productos y servicios integrales, unificar los medios de prepago y fortalecer la cartera de clientes a través de una fuerza de ventas común.<br/><br/>

A partir del 2001, Cantv presenta la tarjeta de servicios prepagados "Ún1ca", verdadero pasaporte de comunicaciones. Este producto puede emplearse para acceder a servicios de telefonía fija, celular, Internet, telefonía pública y llamadas internacionales. A nivel organizativo, se consolidan las Unidades de Apoyo para prestar servicios a toda la Corporación.<br/><br/>

Para el 2004, se hace presente el mercado de banda ancha y las transacciones electrónicas a través de las redes fijas y móviles. Internamente, se fortalecen y actualizan los sistemas tecnológicos, y se establecen procesos flexibles y productivos, basados en la calidad y ejecución. Por medio de la instalación de puertos ABA en la mayoría de las centrales fijas y la capacidad de transmisión de datos a través de la nueva tecnología EvDO, Cantv y Movilnet consolidan un liderazgo absoluto en el mercado de banda ancha e Internet.<br/><br/>

La Cantv del siglo XXI es la insignia de las telecomunicaciones en Venezuela, es una Corporación que aglutina diferentes públicos de interés y que gravita en torno a una actividad en constante expansión y renovación tecnológica. Además, el Gobierno nacional en el 2007, dio un primer paso hacia su nacionalización, consolidando el 70% de participación y se estudia un esquema que permita convertir el 30% restante, en un mecanismo de ahorro nacional para fortalecer la Bolsa de Valores.<br/><br/>

Como aliados estratégicos, cuenta con una red conformada por 809 Centros de Comunicaciones; mientras que Movilnet posee 614 Agentes Autorizados y 260 Agentes Premium. Además, como Ciudadano Corporativo, Cantv participa en un amplio espectro de programas de alto impacto social y cuenta con el apoyo de 2.817 empresas proveedoras de servicios.<br/><br/>

Por otra parte, Cantv sirve a Venezuela con las tecnologías más avanzadas y dispone de una red de fibra óptica interurbana de 7.800 kilómetros de longitud a través de siete gigantescos anillos que proporcionan redundancia, garantizando la confianza y seguridad en el servicio. De igual manera, dispone de la mayor cobertura del servicio de transporte de datos y voz, como es el Frame Relay, el cual permite un uso dinámico del ancho de banda, con velocidad de acceso escalable desde 64 hasta 2.048 kbps con alta disponibilidad.<br/><br/>

Por otro lado, cuenta con una amplia cobertura de puertos ADSL para poder brindar el servicio de acceso a Internet de banda ancha, siguiendo un plan de instalación de 130.000 puertos anuales en la red de IP (Internet Protocol) que ofrezca en promedio de velocidad de 3.448 kbps por cliente, como mínimo. En cuanto a redes móviles, la Corporación se ha posicionado como la operadora de telefonía móvil más amplia con cobertura en el país, al contar con más de mil radio bases CDMA 1X; lo que le proporciona a Movilnet presencia en lugares sin competencia, cubriendo a todas las poblaciones venezolanas.<br/><br/>

Gracias a todas estas etapas, procesos y evoluciones, hoy Cantv es la empresa preferida de los venezolanos porque a través de sus redes fijas, móviles y satelitales, ofrece la posibilidad de estar comunicados, en cualquier momento y lugar, con servicios de voz, datos, internet, video de alta confiabilidad y velocidad de respuesta.<br/><br/><img src="../../shared/img/img organigrama/6.png" class="img_organigrama4"/><br/><br/>
							</div>
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
        <script>
			(function(){
				$('.menu .content').append('<span class="close">x</span>');
				function showContent(elem){
					hideContent();
					elem.find('.content').addClass('expanded');
					elem.addClass('cover');
					}
				function hideContent(){
					$('.menu .content').removeClass('expanded');
					$('.menu li').removeClass('cover');
					}
					$('.menu li').click(function(){
						showContent($(this));
						});
					$('.menu li').keypress(function(e){
						if (e.keyCode == 13){
							showContent($(this));
							}
						});
					$('.menu .close').click(function(e){
						e.stopPropagation();
						hideContent();
						});
					$(document).keyup(function(e){
						if (e.keyCode == 27){
							hideContent();
							}
						});
					})();
		</script>
    </body>
</html>