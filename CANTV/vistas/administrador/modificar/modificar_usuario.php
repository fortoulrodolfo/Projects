<?php
	session_start();
		include '../../../config.php';
		include_once '../../../modelo/Usuario.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../../index.php" : "";
				header($url);
			$de = new Usuario();

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>. : : Modificar Usuarios : : .</title>
        <link href="../../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script type="text/javascript" src="../../../controladores/cerrar/ctrl_cerrar.js"></script>
        <script type="text/javascript" src="../../../shared/libreria/excel/jquery.js"></script>
        <script type="text/javascript" src="../../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
    </head>
	<body>
    	<header>
        	<hgroup>
       	  		<h1></h1>
                <marquee direction="right" behavior="alternate">
                    	<img src="../../../shared/img/img cabecera/Animacion 1. Amarillo & Azul.jpg"/>
                        <img src="../../../shared/img/img cabecera/Animacion 2. Rojo Cantv.jpg"/>
                </marquee>
            </hgroup>
        </header>
        <nav id="menu">
        	<li><a href="../admin.php">Inicio</a></li>
			<li><a href="#">Noticias</a>
				<ul>
					<li><a href="../noticias/ins_publicas.php">Instituciones P&uacute;blicas</a></li>
                    <?php if(isset($_SESSION['usu'])) { ?>
                    	<li><a href="../noticias/corpo.php">Corporativo</a></li>
            		<?php } ?>
                </ul>
			</li>
                       <?php if(isset($_SESSION['usu'])) { ?>
		<!-- ------------------------------------------------------------------------------------------------------------ -->				
						<!-- Acceso al Menu de Usuario : ADMINISTRADOR -->
							<?php if(($_SESSION['tipo']) == "1") :?>
								<li><a href="#">Registrar</a>
									<ul>
										<li><a href="../../administrador/registrar/registrar_usuario.php">Registrar Usuario</a></li>
                                        <li><a href="../../administrador/registrar/registrar_institucion.php">Registrar Instituciones P&uacute;blicas</a></li>
                                        <li><a href="../../administrador/registrar/registrar_central.php">Registrar Central</a></li>
									</ul>
								</li>
								<li><a href="#">Modificar</a>
									<ul>
                                    	<li><a href="../../administrador/modificar/modificar_usuario.php">Modificar Usuario</a></li>
                                        <li><a href="../../administrador/modificar/modificar_cartera.php">Modificar Cartera de Clientes</a></li>
									</ul>
								</li>
                                <li><a href="#">Reportes</a>
                                    <ul>
                                        <li><a href="../../reportes/reporte_demanda.php">Reportes Demandas</a></li>
                                        <li><a href="../../reportes/reporte_proyecto.php">Reportes Proyectos</a></li>
                                        <li><a href="../../reportes/reporte_dataip.php">Reportes Data I.P.</a></li>
                                    </ul>
								</li>
								<li><a href="../../administrador/importar/importar.php">Actualizar Disp. Central</a></li>
								<li><a href="../../../driver/ishorcut.php">Salir</a></li>
				<?php endif; ?>
            <?php } ?>
     	</nav>
        <article id="portada11">
        	<form name="form1" method="post" action="" id="FormularioExportacion">
                <input type="hidden" name="op" id="op" value="" />
                <input type="hidden" id="cod" value=""/>
                <p class="planilla"><strong>Listado de Usuarios</strong></p>
                <input type="text" name="busca_cedula" id="busca_cedula" />
 				<input type="submit" name="buscar" id="buscar" value="Buscar"/>
                <?php if(isset($_POST['busca_cedula'])){
					$de->listar_usuarios();	
				} ?> 
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