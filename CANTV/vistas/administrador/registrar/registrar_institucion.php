<?php
	session_start();
		include '../../../config.php';
		include '../../../modelo/Class_Institucion.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../../index.php" : "";
				header($url);
			$de = new Class_Institucion();
		if(isset($_POST['registrar'])){
   			$de->registrar();
			}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Registrar Institucion : : .</title>
        <link href="../../../shared/css/estilos_cantv.css" rel="stylesheet"/>
         <link href="../../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../../controladores/perfil/lsdAjax.js"></script>
        <script src="../../../shared/libreria/jquery.js"></script>
        <script src="../../../shared/libreria/teclas restri/restringir_tecla.js"></script>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="../../../controladores/admin/ctrl_admin.js"></script>
    </head>
	<body>
    	<header>
        	<hgroup>
       	  		<h1></h1>
                <marquee direction="right" behavior="scroll">
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
        <form id="form1" method="post" name="form1">
            <article id="portada11">
                <p class="planilla">Registrar Nueva Institucion</p>
                	<table class="contenedor_usuario">
                        <tr>
                            <td>Institucion:</td>
                            <td><input name="institucion" type="text" id="institucion" size="100" onkeypress="return keyRestrict(event,'abcdefghijklmnñopqrstuvwxyz ')" required="required"/></td>
                        </tr>
                        <tr>
                            <td>R.I.F:</td>
                            <td><input name="rif" type="text" id="rif" size="40" onkeypress="return keyRestrict(event,'abcdefghijklmnopqrstuvwxyz1234567890')" required="required"/></td>
                        </tr>
                        <tr>
                            <td>Usuario:</td>
                            <td>
                            	<select name="usuario" size="1" id="usuario">
                                	<option selected="selected">Seleccione</option>
                                    <?= $de->combo_usuarios(); ?>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <div id="botones">
                        <input type="submit" name="registrar" value="Registrar" class="guardar" onclick="javascript:registrar_inst();"/>
                        <input type="reset" name="limpiar" value="Limpiar" class="cancelar" onclick="javascript:limpiar();"/>
                    </div> 
        	</article>
        </form>
        <footer>
        	<p>Copyright © 2012 Gerencia De Instituciones Publicas - Todos Los Derechos Reservados |
            	<a href="../../manuales/1. La Fortaleza Digital.pdf" target="_blank">Ayuda</a>
                |
                <a href="../contacto/contacto.php">Contacto</a>
            </p>
        </footer>
	</body>
</html>