<?php
	session_start();
		include('../../../modelo/Class_sql.php');
		include '../../../modelo/Usuario.php';
			$as = new Usuario();
		if(isset($_POST['registrar'])){
			$as->crear_usuario();
		}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>. : : Registro Usuario : : .</title>
        <link href="../../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../../controladores/cerrar/ctrl_cerrar.js"></script>
        <script src="../../../shared/libreria/validar tecla/validar_tecla.js"></script>
        <script src="../../../shared/libreria/registro_u/ctrl_registro.js"></script>
        <script type="text/javascript" src="../../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script src="../../../controladores/perfil/lsdAjax.js"></script>
        <script type="text/javascript">
			function cargar_sec(){
                accion_lsd("../../../driver/control_usuario", "cargarse", [get_lsd("gerencia")], "sector1")
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
        <form name="form1" id="form1" method="post" enctype="multipart/form-data">
        <article id="portada11">
        	<p class="planilla">Registro Usuario</p>
            	<table class="contenedor_usuario">
                	<tr>
                    	<td class="nombres">Nombres:</td>
                    	<td class="cajas">
                        	<input type="text" name="nombre" id="nombre" size="40" onkeypress="return keyRestrict(event,'abcdefghijklmnñopqrstuvwxyz')" placeholder="Escribe Tu Nombre" required="required" x-moz-errormessage="Coloca Tu Nombre"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="nombres">Apellidos:</td>
                       	<td class="cajas">
                           		<input type="text" name="apellido" id="apellido" size="40" onkeypress="return keyRestrict(event,'abcdefghijklmnñopqrstuvwxyz')" placeholder="Escribe Tu Apellido" required="required" x-moz-errormessage="Coloca Tu Apellido"/>
                        </td>
                    </tr>
                    <tr>
                   		<td class="identidad">C.I:</td>
                        <td class="caja_identidad">
                        	<select name="identidad" size="1" id="identidad" required="required" x-moz-errormessage="">
                            	<option selected="selected"></option>
                        		<option value="V" required="required">V</option>
                        		<option value="E" required="required">E</option>
                        	</select> -
                        	<input name="cedula" type="text" id="cedula" size="15" maxlength="8" onkeypress="return keyRestrict(event,'1234567890')" placeholder="Cedula" required="required" x-moz-errormessage="Coloca Tu Cedula"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="nombres">Telefono:</td>
                       	<td class="cajas">
                        	<select name="codigo" id="codigo" size="1" required="required">
                          		<option selected="selected"></option>
                                <option value="0412">0412</option>
                                <option value="0414">0414</option>
                                <option value="0416">0416</option>
                                <option value="0424">0424</option>
                                <option value="0416">0426</option>
                               	<option value="0251">0251</option>
                            </select> -
                            <input name="telefono_movil" type="telefono_movil" id="numero" size="6" maxlength="7" onkeypress="return keyRestrict(event,'1234567890')" required="required" x-moz-errormessage="Coloca Tu Telefono"/>
                        </td>
                   	</tr>
                    <tr>
                    	<td class="nombres">E-mail:</td>
                        <td class="cajas">
                        	<input type="email" name="email" id="email" size="65" placeholder="ejemplo@ejemplo.com" required="required" x-moz-errormessage="Coloca Tu E-mail"/>
                        </td>
                   	</tr>
                    <tr>
                    	<td class="nombres">Gerencia:</td>
                    	<td class="cajas">
                    		<select name="gerencia" size="1" id="gerencia" onchange="cargar_sec()" required x-moz-errormessage="Selecciona Gerencia">
                           		<option value="">Seleccione</option>
                           		<?php $as->listar_g(); ?>
                        	</select>
                    	</td>
                    </tr>
                    	<td class="nombres">Sector:</td>
                        <td class="cajas">
                        	<div id="sector1" style="display:inline">
                        		<select name="sector" id="sector" required x-moz-errormessage="Selecciona Sector">
                                    <option value="">Seleccione</option>
                                </select>
                            </div>
                       	</td>
                    </tr>
                    </tr>
                    	<td class="nombres">Region:</td>
                        <td class="cajas">
                        	<select name="region" id="region" required x-moz-errormessage="Selecciona Region">
                          		<option value="">Seleccione</option>
                          		<?php $as->listar_region()?>
                        	</select>
                       	</td>
                    </tr>
                    </tr>
                    	<td class="nombres">Tipo usuario:</td>
                        <td class="cajas">
                        	<select name="tipo_usuario" id="tipo_usuario" required x-moz-errormessage="Selecciona Tipo Usuario">
                          		<option value="">Seleccione</option>
                				<?php $as->listar_tipou()?>
                        	</select>
                       	</td>
                    </tr>
                    <tr>
                    	<td class="nombres">Contraseña:</td>
                        <td class="cajas">
                        	<input name="contraseña" type="password" id="contraseña" maxlength="45" placeholder="Contraseña" required="required" x-moz-errormessage="Coloca Tu Contraseña"/>
                       	</td>
                    </tr>
                    <tr>
                    	<td class="nombres">Confirmar Contraseña:</td>
                		<td class="cajas">
                        	<input name="confirmar_contraseña" type="password" id="confirmar_contraseña" maxlength="45" onblur="javascript:validar_contraseña();" placeholder="Confirma Contraseña" required="required" x-moz-errormessage="Confirma Tu Contraseña"/>
                        </td>
                    </tr>
                </table>
                <div id="botones">
                	<input type="submit" name="registrar" value="Registrar" class="guardar" onclick=""/>
                    <input type="button" name="limpiar" value="Limpiar" class="cancelar" onclick="window.location.reload();"/>
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