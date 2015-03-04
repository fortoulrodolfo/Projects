<?php
	session_start();
		include '../../../config.php';
		include '../../../modelo/Class_Central.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../../index.php" : "";
				header($url);
			$de = new Class_Central();
		if(isset($_POST['registrar'])){
   			$de->registrar();
			}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Registrar Central : : .</title>
        <link href="../../../shared/css/estilos_cantv.css" rel="stylesheet"/>
         <link href="../../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../../controladores/perfil/lsdAjax.js"></script>
        <script src="../../../shared/libreria/jquery.js"></script>
        <script src="../../../shared/libreria/teclas restri/restringir_tecla.js"></script>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="../../../controladores/admin/ctrl_admin.js"></script>
        <script type="text/javascript">
			function carga_es(){
                accion_lsd("../../../driver/control_central", "cargae", [get_lsd("region")], "estado1")
				}
				
            function carga_mun(){
                accion_lsd("../../../driver/control_demanda", "cargam", [get_lsd("estado")], "municipio1")
				}
        </script>
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
        <form id="form1" method="post" name="form1">
            <article id="portada11">
                <p class="planilla">Registrar Nueva Central</p>
                    <table class="contenedor_usuario">
                        <tr>
                            <td>Central:</td>
                            <td><input name="central" type="text" id="central" size="100" onkeypress="return keyRestrict(event,'abcdefghijklmnñopqrstuvwxyz')" required="required"/></td>
                        </tr>
                        <tr>
                            <td>CCCC:</td>
                            <td><input name="cccc" type="text" id="cccc" size="40" onkeypress="return keyRestrict(event,'1234567890,')" required="required"/></td>
                        </tr>
                        <tr>
                            <td>UBCC:</td>
                            <td><input name="ubcc" type="text" id="ubcc" size="40" onkeypress="return keyRestrict(event,'1234567890,')" required="required"/></td>
                        </tr>
                        <tr>
                            <td>Seriales:</td>
                            <td><input name="seriales" type="text" id="seriales" size="40" onkeypress="return keyRestrict(event,'1234567890,')" required="required"/></td>
                        </tr>
                        <tr>
                    		<td>Region:</td>
                        	<td>
                        		<select name="region" id="region" required x-moz-errormessage="Selecciona Region" onchange="carga_es()">
                          			<option value="">Seleccione</option>
                          			<?php $de->combo_region()?>
                        		</select>
                       		</td>
                    	</tr>
                        <tr>
                        	<td>Estado:</td>
                            <td>
                        		<div id="estado1" style="display:inline">
                                    <select name="estado" size="1" maxlength="16" id="estado" disabled>
                                        <option selected="selected">Seleccione</option>
                                    </select>
                                </div>
                       		</td>
                        </tr>
                        <tr>
                            <td>Municipio:</td>
                            <td>
                            	<div id="municipio1" style="display: inline">
                                    <select name="municipio" size="1" id="municipio" disabled>
                                        <option selected="selected">Seleccione</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <div id="botones">
                        <input type="submit" name="registrar" value="Registrar" class="guardar" onclick="javascript:registrar_cent();"/>
                        <input type="reset" name="limpiar" value="Limpiar" class="cancelar" onclick="javascript:limpiar();"/>
                    </div> 
            </article>
        </form>
        <footer>
        	<p>Copyright © 2012 Gerencia De Instituciones Publicas - Todos Los Derechos Reservados |
            	<a href="../../../manuales/manual usuario/Cantv. Manual Usuario.pdf" target="_blank">Ayuda</a>
                |
                <a href="../contacto/contacto.php">Contacto</a>
            </p>
        </footer>
	</body>
</html>