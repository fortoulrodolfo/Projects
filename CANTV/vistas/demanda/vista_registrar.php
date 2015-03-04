<?php
	session_start();
		include '../../config.php';
		include '../../modelo/Class_Demanda.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Demanda();
			
		if(isset($_POST['validar2'])){
   			$de->registrar();
			
			}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Registro Demanda : : .</title>
        <link href="../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script src="../../controladores/demanda/contrl_demanda.js" type="text/javascript"></script>
        <script src="../../shared/libreria/jquery.js"></script>
        <script src="../../shared/libreria/teclas restri/restringir_tecla.js"></script>
        <script src="../../controladores/perfil/lsdAjax.js"></script>
        <script type="text/javascript">
			$(function() {
				var menu_ul = $('.menu_interno2 > li > ul'),
				menu_a = $('.menu_interno2 > li > a');
				menu_ul.hide();
				menu_a.click(function(e){
					e.preventDefault();
					if(!$(this).hasClass('active')){
						menu_a.removeClass('active');
						menu_ul.filter(':visible').slideUp('normal');
						$(this).addClass('active').next().stop(true,true).slideDown('normal');
						} else {
							$(this).removeClass('active');
							$(this).next().stop(true,true).slideUp('normal');
							}
						});
					});
		</script>
        <script type="text/javascript">
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
			
			function cargar_tipos(){
                accion_lsd("../../driver/control_demanda", "cargas", [get_lsd("servicio")], "tipo_servicio1")
				}
             
            function cargar_subtipos(){
                accion_lsd("../../driver/control_demanda", "cargass", [get_lsd("tipo_servicio")], "subtipo_servicio1")
				}
            
            function cargar_plan(){
                accion_lsd("../../driver/control_demanda", "cargap", [get_lsd("servicio"),get_lsd("tipo_servicio"),get_lsd("subtipo_servicio")], "plan1")
				}
			
			function cargar_dis_central(){
				
                accion_lsd("../../driver/control_demanda", "cargardis", [get_lsd("central")], "puertos_libres1")
				}
				
		</script>
        
    </head>
	<body>
    	<header>
        	<hgroup>
       	  		<h1></h1>
                <marquee direction="right" behavior="scroll">
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
        <aside id="contenedor_todo3">
        	<ul class="menu_interno2">
            	<li class="item1"><a href="#">Info. Centrales</a>
                	<ul>
                    	<li class="subitem2"><a href="../../manuales/guias/Centrales.pdf" target="_blank">Adobe Reader</a></li>
                    </ul>
				</li>
                <li class="item1"><a href="#">Cartera Inst.</a>
                	<ul>
                    	<li class="subitem2"><a href="">Microsoft Excel</a></li>
                    </ul>
				</li>
                <li class="item1"><a href="#">Cartera Productos</a>
                	<ul>
                    	<li class="subitem2"><a href="">Microsoft Excel</a></li>
                    </ul>
				</li>
			</ul>
        </aside>
        <aside id="contenedor_tabla">
        	<table class="disponibilidad">
            	<tbody>
                    <tr>
                    	<div id="puertos_libres1" style="display: inline">
                        	<input name="puertos_libres" id="puertos_libres" type="number" readonly="readonly" hidden=""/>
                        </div>
                    </tr>
                </tbody>
            </table>
        </aside>
        <form id="id_form1" method="post" name="form1" enctype="multipart/form-data">
        
            <article id="portada12">
            	<div id="solo_usuario">
					<?php echo $_SESSION['usu']?>
        		</div>
        		<p class="planilla2">Datos De Instituciones</p>
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
                                <input name="rif" id="rif" type="text" size="10px" style="font:bold 16px sans-serif; color:#000; padding:3px; text-align:center; border:none; border-radius:5px;" readonly="readonly" />
                            </td>
                        </tr>
                    </table>
                     <table class="contenedor_demanda" id="locali" hidden="true">
                    <tr>
                    	<td>Localidades:</td>
                        <td>
                        	<select name="localidad2" size="1" id="localidad2" onchange="mostrar_localidad()">
                            	<option selected="selected" value="0">Seleccione</option>
                            </select>
                        </td>
                   	</tr>
                </table>
                 
                <p class="planilla2">Datos De Localidad </p>
                
                <table class="contenedor_demanda2" id="nomb1">
                	<tr>
                        	<td class="combos_demandas">Nombre Sede:</td>
                            <td>
                                 <input  name="nombre_sede" type="text" id="nombre_sede" required="required"/>
                            </td>
                </table>
                    <table class="contenedor_demanda2" id="resd1">
                        <tr>
                            <td class="combos_demandas">Region:</td>
                            <td>
                            
                                <select name="region" size="1" id="region" onchange="carga_es()">
                               <option selected="selected" value="">Seleccione</option>
                                    <?= $de->combo_region(); ?>
                                    
                                </select>
                            </td>
                            <td class="combos_demandas">Estado:</td>
                            <td>
                                <div id="estado1" style="display:inline">
                                 <select name="estado" size="1" id="estado" disabled>
                                        <option selected="selected" value="">Seleccione</option>
                                       
                                    </select>
                                    
                               </div>
                            </td>
                    	</tr>
                    </table>
                     <table class="contenedor_demanda2" id="resd2" hidden="true">
                	<tbody>
                    	<tr>
                        	<td class="combos_demandas">Region:</td>
                            <td>
                                <select name="reg3" size="1" id="reg3" style="width:150px;" disabled="disabled">
                                                                       
                                </select>
                            </td>
                            <td class="combos_demandas">Estado:</td>
                            <td>
                                <div id="estado1" style="display:inline">
                                    <select name="est3" size="1" id="est3" style="width:150px;" disabled>
 
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                    <table class="contenedor_demanda2jj" id="munn">
                        <tr>
                        	<td class="combos_demandas">Municipio:</td>
                            <td>
                                <div id="municipio1" style="display: inline">
                                   <select name='municipio' id='municipio' onchange='carga_ce()' disabled>
                                       <option selected='selected' value="">Seleccione</option>
                                       </select>
				
                                </div>
                            </td>
                        </tr>
                    </table>
                     <table class="contenedor_demanda2jj" id="mun" style=" padding-left:80px;" hidden="true">
                	<tbody>
                    	<tr>
                            <td class="combos_demandas">Municipio:</td>
                            <td>
                                <div id="municipio1" style="display: inline">
                                      <select name="mun3" size="1" id="mun3" style="width:200px;" disabled>
 
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                  	<table class="contenedor_demanda2j" id="centt">
                        <tr>
                        	<td class="combos_demandas">Central:</td>
                            <td>
                                <div id="central1" style="display: inline">
                                    <select name='central' size="1" id='central' disabled="disabled">
									  <option selected='selected' value="">Seleccione</option>
                                      
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </table>
                     <table class="contenedor_demanda2j" id="cent" hidden="true">
                    <tbody>
                    	<tr>    
                            <td class="combos_demandas">Central:</td>
                            <td>
                                <div id="central1" style="display: inline">
                                    <select name="cent3" size="1" id="cent3" style="width:200px;" disabled>
 
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                    <table class="contenedor_demanda2jjj">
                    	<tr>     
                       		<td class="combos_demandas">CCCC:</td>
                            <td>
                                
                                <input type="text" readonly="readonly" name="cccc" id="cccc" required="required" size="13" style="font:bold 14px sans-serif; color:#000; padding:3px; text-align:center; border:none; border-radius:5px;"/>
                                 
                            </td>
                            <td>
                             <table id="modi" style="padding-left:15px;" hidden="true">
                    	<tr> 
                        <td><a href="#" onclick="modificar_data();"><img src='../../shared/img/img modificar/Sin título.png' width='16' tittle='modificar' style='cursor:pointer'/>Modificar</a></td>    
                          </tr>
                    </table>
                            </td>
                        </tr>
                    </table>
                    
                    
                    
                     <table class="tabla_data" id="tab_ser" style="margin-top:25px; margin-left:-1px;" hidden="true">
                	<tbody>
                    	<tr class='title'>
                        	<td>Serv. Datos</td>
                            <td>Serv. Internet</td>
                            <td>Serv. Telefonia Fija</td>
                            <td>Serv. TI</td>
                            <td>Serv. Moviles</td>
                            <td>Serv. Equipamiento</td>
                        </tr>
                        <tr>
                        	<td>
                            	<input name="servicios_datos" type="text" id="servicios_datos" size="2" maxlength="4"  disabled="disabled"/>
                            </td>
                            <td>
                            	<input name="servicios_internet" type="text" id="servicios_internet" size="2" maxlength="4" disabled="disabled"/>
                            </td>
                            <td>
                            	<input name="servicios_telf_fija" type="text" id="servicios_telf_fija" size="2" maxlength="4" disabled="disabled"/>
                            </td>
                            <td>
                            	<input name="servicios_ti" type="text" id="servicios_ti" size="2" maxlength="4"  disabled="disabled"/>
                            </td>
                            <td>
                            	<input name="servicios_moviles" type="text" id="servicios_moviles" size="2" maxlength="4" disabled="disabled"/>
                            </td>
                            <td>
                            	<input name="servicios_equipamento" type="text" id="servicios_equipamento" size="2" maxlength="4" disabled="disabled"/>
                        </tr>
                    </tbody>
                </table>
                <p class="planilla2">Datos De Servicios</p>
                    <table class="contenedor_demanda2">
                        <tr>
                            <td class="combos_demandas">Servicios:</td>
                            <td>
                                <select name="servicio" size="1" id="servicio" onchange="cargar_tipos()">
                                    <option selected="selected" value="">Seleccione</option>
                                    <?= $de->combo_servicio();?>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <table class="contenedor_demanda2jj">
                        <tr>
                       		<td class="combos_demandas" style="margin-left:205px;">Tipos Servicios:</td>
                            <td>
                            	<div id="tipo_servicio1">
                                   	<select name="tipo_servicio" size="1" id="tipo_servicio" disabled="disabled">
                                       	<option selected="selected" value="">Seleccione</option>
                                   	</select>
                                </div>
                            </td>
                        </tr>
                    </table>
                    
                  	<table class="contenedor_demanda2j">
                        <tr>
                            <td class="combos_demandas">Sub-Tipos Servicios:</td>
                            <td>
                            	<div id="subtipo_servicio1">
                                	<select name="subtipo_servicio" size="1" id="subtipo_servicio" disabled="disabled">
                                    	<option selected="selected" value="">Seleccione</option>
                                   	</select>
                                </div>
                            </td>
                        </tr>
                   	</table>
                    <table class="contenedor_demanda2jj">
                   		<tr>
                        	<td class="combos_demandas">Plan:</td>
                            <td>
                            	<div id="plan1">
                                	<select name="plan" size="1" id="plan" disabled="disabled">
                                    	<option selected="selected" value="">Seleccione</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </table>
                   
              <table class="contenedor_demanda2">
                   		<tr>
                        <td class="combos_demandas">Trimestre:</td>
                            	<td>
                                    <div id="trimestre1">
                                        <select name="trimestre" size="1" id="trimestre">
                                            <option selected="selected" value="">Seleccione</option>
                                            <?= $de->combo_trimestre();?>
                                        </select>
                                	</div>
                                </td>
                            </td>
                        	
                        </tr>
                    </table>
                    
                     <table class="contenedor_demanda2jjj" style="margin-left:280px;">
                    	<tr>
                             <td class="combos_demandas">Año:</td>
                            	<td>
                                	<select name="ano" size="1" id="ano">
                                    	<option selected="selected" value="">Seleccione</option>
										<?php
                                            for($i=2013; $i<=2100; $i++){
                                                echo "<option value=\"$i\">".$i;
                                                }
                                        ?>
									</select>
                                </td>
                            </td>
                      </tr>
              </table>
               <table class="contenedor_demanda2jj">
                    	<tr>
                            <td class="combos_demandas">Cantidad:</td>
                            <td>
                            	<input name="cantidad" type="text" id="cantidad" size="5" 
                                onkeypress="return keyRestrict(event,'1234567890-/')" required="required"/>  </td>
                           </tr>
              </table>
              
                     <input type="hidden" id="validar2" name="validar2" value="Registrar"/>
                    <div id="botones">
                        <input type="button" name="registrar" id="registrar" value="Registrar" class="guardar" onclick="javascript:registrar_d();"/>
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