<?php
	session_start();
		include '../../config.php';
		include '../../modelo/Class_Demanda.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Demanda();
			
		if(isset($_POST['modificar'])){
   			$de->modificar();
			}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Modificar Demanda : : .</title>
       <link href="../../shared/css/estilo_popup.css" rel="stylesheet"/>
       <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script src="../../controladores/demanda/contrl_demanda.js" type="text/javascript"></script>
        <script src="../../shared/libreria/jquery.js"></script>
        <script src="../../shared/libreria/teclas restri/restringir_tecla.js"></script>
        <script src="../../controladores/perfil/lsdAjax.js"></script>
        <script type="text/javascript">
			
			function cargar_tipos(){
                accion_lsd("../../driver/control_demanda", "cargas", [get_lsd("servicio")], "tipo_servicio1")
				}
             
            function cargar_subtipos(){
				document.getElementById("tipo").value=document.getElementById("tipo_servicio").value;
                accion_lsd("../../driver/control_demanda", "cargass", [get_lsd("tipo_servicio")], "subtipo_servicio1")
				}
            
            function cargar_plan(){
				document.getElementById("subtipo").value=document.getElementById("subtipo_servicio").value;
                accion_lsd("../../driver/control_demanda", "cargap", [get_lsd("servicio"),get_lsd("tipo_servicio"),get_lsd("subtipo_servicio")], "plan1")	
				}
				
			function cargar_etiqueta(){
				document.getElementById("pplan").value=document.getElementById("plan").value;
				}
		</script>
    </head>
	<body>
    	<header>
        <hgroup>
  		  <h1></h1>
                <marquee direction="right" behavior="alternate">
                    	<img src="../../shared/img/img cabecera/Animacion 1. Amarillo & Azul.jpg" width="466"/>
                        <img src="../../shared/img/img cabecera/Animacion 2. Rojo Cantv.jpg" width="466"/>
                </marquee>
            </hgroup>
        </header>
     	</nav>
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
       
    <form id="id_form2" method="post" name="form1" enctype="multipart/form-data">
    <input type="hidden" id="cood" name="cood" value=<?php echo $_GET['cod']?>>
    <input type="hidden" id="tipo" name="tipo" value=<?php echo $_GET['id_tipo']?>>
    <input type="hidden" id="subtipo" name="subtipo" value=<?php echo $_GET['id_subtipo']?>>
    <input type="hidden" id="pplan" name="pplan" value=<?php echo $_GET['idplan']?>>
    
            <article id="portada22">
            	<div id="solo_usuario_modificar" style="width:605px;">
					<?php echo $_SESSION['usu']?>
        		</div>
                <p class="planilla2">Datos De Servicios</p>
                    <table class="contenedor_demanda3">
                        <tr>
                            <td class="combos_demandas">Servicios:</td>
                            <td>
                                <select name="servicio" size="1" id="servicio" onchange="cargar_tipos()">
                                    <option selected="selected" value=<?php echo $_GET['id_serv']?>><?php echo $_GET['serv']?></option>
                                    
                                    <?= $de->combo_servicio();?>
                                </select>
                            </td>
                       		<td class="combos_demandas">Tipos Servicios:</td>
                            <td>
                            	<div id="tipo_servicio1">	
                                <select name="tipo_servicio" size="1" id="tipo_servicio" disabled="disabled">
             				    <option selected="selected" value=<?php echo $_GET['id_tipo']?>><?php echo $_GET['tipo']?></option>
             				
                             </select>
                                </div>
                            </td>
                        </tr>
                    </table>
                  	<table class="contenedor_demanda4">
                        <tr>
                            <td class="combos_demandas">Sub-Tipos Servicios:</td>
                            <td>
                            	<div id="subtipo_servicio1">
                                	<select name="subtipo_servicio" size="1" id="subtipo_servicio" disabled="disabled" >
                                    	<option selected="selected" value=<?php echo $_GET['id_subtipo']?>><?php echo $_GET['sub_tipo']?></option>
                                   	</select>
                                </div>
                            </td>
                        </tr>
                   	</table>
                    <table class="contenedor_demanda4jj">
                   		<tr>
                        	<td class="combos_demandas">Plan:</td>
                            <td>
                            	<div id="plan1">
                                	<select name="plan" size="1" id="plan" disabled="disabled">
                                    	<option selected="selected" value=<?php echo $_GET['idplan']?>><?php echo $_GET['plan']?></option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                    </table>
              <table class="contenedor_demanda4jjj">
                      <tr>
                        <td class="combos_demandas">Cantidad:</td>
                        <td><input name="cantidad" type="text" id="cantidad" size="5" onkeypress="return keyRestrict(event,'1234567890-/')" required="required" value=<?php echo $_GET['cantidad']?>></td>
                      </tr>
                    </table>
                    <table class="contenedor_demanda5">
                    	<tr>
                            <td class="combos_demandas">Trimestre:</td>
                            	<td>
                                    <div id="trimestre1">
                                        <select name="trimestre" size="1" id="trimestre">
                                            <option selected="selected" value=<?php echo $_GET['id_trimestre']?>><?php echo $_GET['tri']?></option>
                                            <?= $de->combo_trimestre();?>
                                        </select>
                                	</div>
                                </td>
                            </td>
                            <td class="combos_demandas">Año:</td>
                            	<td>
                                	<select name="ano" size="1" id="ano">
                                    	<option selected="selected"><?php echo $_GET['ano']?></option>
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
                    <div id="botones">
                        <input type="submit" name="modificar" value="Modificar" class="guardar"/>
                        <input type="reset" name="limpiar" value="Limpiar" class="cancelar" onclick="window.location.reload();"/>
              </div>  
            </article>
    </form>
	</body>
    
</html>