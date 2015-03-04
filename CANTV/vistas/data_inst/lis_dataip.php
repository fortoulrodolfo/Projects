<?php
	session_start();
		include '../../config.php';
		include '../../modelo/Class_Data.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Data();
		if(isset($_POST['op'])){
				$de->eliminar_dataip();
			}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Listado Instituciones : : .</title>
        <link href="../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../controladores/cerrar/ctrl_cerrar.js"></script>
        <script type="text/javascript" src="../../shared/libreria/excel/jquery.js"></script>
        <script language="javascript">
			$(document).ready(function(){
				$(".botonExcel").click(function(event){
					$("#datos_a_enviar2").val( $("<div>").append( $("#Exportar_a_Excel3").eq(0).clone()).html());
					$("#FormularioExportacion1").submit();
					});
					
				$(".img_descargas2").click(function(event){
					$("#datos_a_enviar3").val( $("<div>").append( $("#Exportar_a_Excel3").eq(0).clone()).html());
					$("#FormularioExportacion3").submit();
					});
				});
		</script>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        
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
        <article style="margin-bottom:150px;">
        <form name="form2" method="post" action="ficheroExcel2.php" id="FormularioExportacion1">
            <input type="hidden" id="datos_a_enviar2" name="datos_a_enviar2"/>
            </form>
            
              
          <form name="form3" method="post" action="ficheroExcel3.php" id="FormularioExportacion3">
            <input type="hidden" id="datos_a_enviar3" name="datos_a_enviar3"/>
            </form>
        
        
        	<form name="form1" method="post" action="" id="FormularioExportacion">
                <input type="hidden" name="op" id="op"/>
                <input type="hidden" name="cod"/>
                <p class="planilla"><strong>Datos Instituciones</strong></p>
               
				<?php if(($_SESSION['tipo']) == "4"){
					echo " <div id=imagenes_ver' style='margin-left:250px;'>
            		<img src='../../shared/img/img iconos descargas/ICONOS2.png' class='botonExcel'/> 
            		<img src='../../shared/img/img iconos descargas/ICONOS.png' class='img_descargas' onclick='imprimir_data(4);'/>
                	<img src='../../shared/img/img iconos descargas/ICONOS3.png' class='img_descargas2'/>
            	</div> ";
					$de->listar_data();
					}
						elseif(($_SESSION['tipo']) == "2"){
							echo " <div id=imagenes_ver' style='margin-left:250px;'>
            		<img src='../../shared/img/img iconos descargas/ICONOS2.png' class='botonExcel'/> 
            		<img src='../../shared/img/img iconos descargas/ICONOS.png' class='img_descargas' onclick='imprimir_data(2);'/>
                	<img src='../../shared/img/img iconos descargas/ICONOS3.png' class='img_descargas2'/>
            	</div> ";
							$de->listar_data_lider();
						}
							elseif(($_SESSION['tipo']) == "3"){
								echo " <div id=imagenes_ver' style='margin-left:250px;'>
            		<img src='../../shared/img/img iconos descargas/ICONOS2.png' class='botonExcel'/> 
            		<img src='../../shared/img/img iconos descargas/ICONOS.png' class='img_descargas' onclick='imprimir_data(3);'/>
                	<img src='../../shared/img/img iconos descargas/ICONOS3.png' class='img_descargas2'/>
            	</div> ";
								$de->listar_data_gerente();
							}
				?>
           	</form>
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