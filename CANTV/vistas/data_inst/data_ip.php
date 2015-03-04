<?php
	session_start();
		include '../../config.php';
		include '../../modelo/Class_Data.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Data();
			
		if(isset($_POST['registrar'])){
			
			if($_POST['validar']=="Registrar"){
   			$de->registrar();
			}
			
			if($_POST['validar']=="Actualizar"){
   			$de->actualizar();
			}
			
			if($_POST['validar']=="Guardar"){
   			$de->modificar();
			}
			
			}
			
			if(isset($_GET['cod'])){		
			$valor=$_GET['rif'];
			$id=$_GET['cod'];
			}
			else{
			$id="";
			$valor="";
			}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Registro Data I.P. : : .</title>
        <link href="../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../controladores/cerrar/ctrl_cerrar.js"></script>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script src="../../controladores/proyecto/contrl_proyecto.js" type="text/javascript"></script>
        <script src="../../shared/libreria/teclas restri/restringir_tecla.js"></script>
        <script src="../../controladores/perfil/lsdAjax.js"></script>
        <script type="text/javascript">
			
			$(document).ready(function() {
				var a1=document.getElementById("id1").value;
				var a2=document.getElementById("id2").value;
				
				if (a1!=""){
						for(h=0 ; h<document.getElementById("instituciones").length ; h++)
							{
								var n=document.getElementById("instituciones").options[h].value;
								if(n==a2){
									document.getElementById("instituciones").options[h].selected=true;
									}
							};
					document.getElementById("rif").value=a2;
					obtener_localidades(a1);
					
					}
				
				})
		
			function montar_rif(d){
				a=d.value;
				document.getElementById("rif").value=a;
				}
			
			function carga_es(){
                accion_lsd("../../driver/control_demanda", "cargae", [get_lsd("region")], "estado1")
				}
				
            function carga_mun(){
                accion_lsd("../../driver/control_demanda", "cargam", [get_lsd("estado")], "municipio1")
				}
            
			function carga_ce(){
                accion_lsd("../../driver/control_demanda", "cargac", [get_lsd("municipio")], "central1")
				}

            function carga_ccc(){
                document.getElementById('cccc').value=document.getElementById('central').value;
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
        <form id="form1" method="post" name="form1" enctype="multipart/form-data">
        <article id="portada_data">
            <div id="solo_usuario2">
				<?php echo $_SESSION['usu']?>
            </div>
            <p class="planilla2">Datos De Instituciones</p>
            <input type="hidden" id="id1" value="<?php echo $id ?>" >
            <input type="hidden" id="id2" value="<?php echo $valor ?>" >
            	<table class="contenedor_demanda">
                	<tr>
                    	<td>Instituciones:</td>
                        <td>
                        	<select name="instituciones" size="1" id="instituciones" onchange="montar_rif(this);obtener_localidades()">
                            	<option selected="selected" value="">Seleccione</option>
								<?= $de->combo_instituciones(); ?>
                            </select>
                        </td>
                        <td>R.I.F:</td>
                        <td>
                        	<input name="rif" id="rif" type="text" size="10px" style="font:bold 16px sans-serif; color:#000; padding:3px; text-align:center; border:none; border-radius:5px;" readonly="readonly"/>
                        </td>
                   	</tr>
                    </table>
                    <table class="contenedor_demanda" id="locali" hidden="true">
                    <tr>
                    	<td>Localidades:</td>
                        <td>
                        	<select name="localidad" size="1" id="localidad" onchange="mostrar_localidad()">
                            	<option selected="selected" value="0">Seleccione</option>
                            </select>
                        </td>
                   	</tr>
                </table>
                <p class="planilla2">Datos de Localidad</p>
                 <table class="contenedor_data1" id="nomb1">
                	<tbody>
                    	<tr>
                        	<td class="combos_demandas">Nombre Sede:</td>
                            <td>
                                 <input  name="nombre_sede" type="text" id="nombre_sede" required="required"/>
                            </td>
                    </tbody>
                </table>
                <table class="contenedor_data1" id="resd1">
                	<tbody>
                    	<tr>
                        	<td class="combos_demandas">Region:</td>
                            <td>
                                <select name="region" size="1" id="region" onchange="carga_es();">
                                    <option selected="selected">Seleccione</option>
                                    <?= $de->combo_region(); ?>
                                </select>
                            </td>
                            <td class="combos_demandas">Estado:</td>
                            <td>
                                <div id="estado1" style="display:inline">
                                    <select name="estado" size="1" id="estado" disabled>
                                        <option selected="selected">Seleccione</option>
                                       
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class="contenedor_data1" id="resd2" hidden="true">
                	<tbody>
                    	<tr>
                        	<td class="combos_demandas">Region:</td>
                            <td>
                                <select name="reg2" size="1" id="reg2" style="width:150px;" disabled="disabled">
                                                                       
                                </select>
                            </td>
                            <td class="combos_demandas">Estado:</td>
                            <td>
                                <div id="estado1" style="display:inline">
                                    <select name="est2" size="1" id="est2" style="width:150px;" disabled>
 
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class="contenedor_data2" id="munn">
                	<tbody>
                    	<tr>
                            <td class="combos_demandas">Municipio:</td>
                            <td>
                                <div id="municipio1" style="display: inline">
                                    <select name="municipio" size="1" id="municipio" disabled>
                                        <option selected="selected">Seleccione</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class="contenedor_data2" style=" padding-left:80px;" id="mun" hidden="true">
                	<tbody>
                    	<tr>
                            <td class="combos_demandas">Municipio:</td>
                            <td>
                                <div id="municipio1" style="display: inline">
                                      <select name="mun2" size="1" id="mun2" style="width:200px;" disabled>
 
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class="contenedor_data3" style="margin-top:20px;" id="centt">
                    <tbody>
                    	<tr>    
                            <td class="combos_demandas">Central:</td>
                            <td>
                                <div id="central1" style="display: inline">
                                    <select name="central" size="1" id="central" disabled="disabled">
                                        <option selected="selected">Seleccione</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class="contenedor_data3" style="margin-top:20px;" id="cent" hidden="true">
                    <tbody>
                    	<tr>    
                            <td class="combos_demandas">Central:</td>
                            <td>
                                <div id="central1" style="display: inline">
                                    <select name="cent2" size="1" id="cent2" style="width:200px;" disabled>
 
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class="contenedor_data4" style="margin-top:-27px;">
                	<tbody>
                    	<tr>
                            <td class="combos_demandas">CCCC:</td>
                            <td>
                                <input type="text" readonly="readonly" name="cccc" id="cccc" required="required" size="13" style="font:bold 14px sans-serif; color:#000; padding:3px; text-align:center; border:none; border-radius:5px;"/>
                            </td>
                        	<td class="combos_demandas">Direccion:</td>
                            	<td>
                                <textarea name="direccion" cols="30" rows="1" id="direccion" placeholder="Direccion" required="required"></textarea>
                                </td>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class="tabla_data" style="margin-top:25px;">
                	<tbody>
                    	<tr class='title'>
                        	<td>Servicios Datos</td>
                            <td>Servicios Internet</td>
                            <td>Servicios Telefonia Fija</td>
                            <td>Servicios TI</td>
                            <td>Servicios Moviles</td>
                            <td>Servicios Equipamiento</td>
                        </tr>
                        <tr>
                        	<td>
                            	<input name="servicios_datos" type="text" id="servicios_datos" size="2" maxlength="3" onkeypress="return keyRestrict(event,'1234567890-/')" required="required"/>
                            </td>
                            <td>
                            	<input name="servicios_internet" type="text" id="servicios_internet" size="2" maxlength="3" onkeypress="return keyRestrict(event,'1234567890-/')" required="required"/>
                            </td>
                            <td>
                            	<input name="servicios_telf_fija" type="text" id="servicios_telf_fija" size="2" maxlength="3" onkeypress="return keyRestrict(event,'1234567890-/')" required="required"/>
                            </td>
                            <td>
                            	<input name="servicios_ti" type="text" id="servicios_ti" size="2" maxlength="3" onkeypress="return keyRestrict(event,'1234567890-/')" required="required"/>
                            </td>
                            <td>
                            	<input name="servicios_moviles" type="text" id="servicios_moviles" size="2" maxlength="4" onkeypress="return keyRestrict(event,'1234567890-/')" required="required"/>
                            </td>
                            <td>
                            	<input name="servicios_equipamento" type="text" id="servicios_equipamento" size="2" maxlength="3" onkeypress="return keyRestrict(event,'1234567890-/')" required="required"/>
                        </tr>
                    </tbody>
                </table>
                <table class="contenedor_data4" style="margin-top:25px; margin-left:250px;">
                	<tbody>
                    	<tr>
                        	<td>Personas de Contacto - Telf.: </td>
                            <td>
                            	<textarea name="observaciones" cols="30" rows="1" id="observaciones"></textarea>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <input type="hidden" id="validar" name="validar" value="Registrar"/>
                    
                <div id="botones">
                	<input type="submit" id="registrar" name="registrar" value="Registrar" class="guardar" onclick="javascript:registrar();"/>
                    <input type="button" id="modificar" name="modificar" value="Modificar" class="guardar" 
                    onclick="javascript:modificar_dataip();" hidden="true"/>
        			<input type="button" name="limpiar" value="Limpiar" class="cancelar"
                     onclick="window.location.assign('../../vistas/data_inst/data_ip.php');"/>
                </div>  
            </article>
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