<?php
	session_start();
		include '../../config.php';
		include '../../modelo/Class_Proyecto.php';
			$url=!isset($_SESSION['usu']) ? "Location:../../index.php" : "";
				header($url);
			$de = new Class_Proyecto();
		if(isset($_POST['validar2'])){
   			$de->actualizar_proyecto();
			}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Modificar Proyecto : : .</title>
        <link href="../../shared/css/estilo_popup.css" rel="stylesheet"/>
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script src="../../controladores/cerrar/ctrl_cerrar.js"></script>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <link href="../../shared/libreria/development-bundle/themes/base/jquery.ui.all.css" rel="stylesheet"/>
        <script src="../../controladores/proyecto/contrl_proyecto.js" type="text/javascript"></script>
        <script src="../../shared/libreria/jquery.js"></script>
        <script src="../../shared/libreria/development-bundle/ui/jquery.ui.core.js"></script>
        <script src="../../shared/libreria/development-bundle/ui/jquery.ui.datepicker.js"></script>
        <script src="../../shared/libreria/development-bundle/ui/i18n/jquery.ui.datepicker-es.js"></script>
		<script src="../../shared/libreria/teclas restri/restringir_tecla.js"></script>
        <script src="../../shared/libreria/validar tecla/validar_tecla.js"></script>
        <script type="text/javascript">
			function montar_rif(d){
				a=d.value;
					document.getElementById("rif_institucion").value=a;
				}
				 $(function(){
					var pickerOpts = {
						minDate: new Date(),
						maxDate: "",
                        changeMonth: true,
                        changeYear: true
						};
					$("#fecha_solicitud").datepicker(pickerOpts);
				});
		</script>
        <script>
		
		$(document).ready(function(){
				
			for(var i=1; i<document.getElementById("arr_separar").value ; i++){
			document.getElementById('crear').onclick();
			};
			
			
			if (document.getElementById("imp_comercial").value=='Si'){
				document.getElementById("imp_comer_si").checked="true";
				}
			else{document.getElementById("imp_comer_no").checked="true";}
				
			if (document.getElementById("imp_politica").value=='Si'){
				document.getElementById("imp_poli_si").checked="true";
				}
			else{document.getElementById("imp_poli_no").checked="true";}
			
			if (document.getElementById("imp_comercial").value=='Si'){
				document.getElementById("imp_soci_si").checked="true";
				}
			else{document.getElementById("imp_soci_no").checked="true";}
				
			montar_capas();
			montar_costos(document.getElementById("cod_pro").value);
		})
		
		function montar_capas(){
			
			var dat=document.getElementById("valores2").value;
			var n= dat.split(" / ");
			
			for(j=0 ; j<n.length ; j++){	
				h=j+1;
			for(k=0 ; k<document.getElementById("linea_de_negocio"+h).length ; k++){
				
				var g=document.getElementById("linea_de_negocio"+h).options[k].text;
				if(g==n[j]){
					document.getElementById("linea_de_negocio"+h).options[k].selected=true;
					}
				
				};
				
			}
			
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
        
        <form id="id_form3" method="post" name="id_form3" enctype="multipart/form-data">
        <?php
		$values = explode("/", $_GET["linea_d"]);
		$ja=sizeof($values);
		
		?>
		
        <input type="hidden" id="arr_separar" name="arr_separar" value=<?php echo $ja?>>
        <input type="hidden" id="cod_arc" name="cod_arc" value=<?php echo $_GET["arc"]?>>
        <input type="hidden" id="cod_pro" name="cod_pro" value=<?php echo $_GET["idprop"]?>>
        <input type="hidden" id="imp_comercial" name="imp_comercial" value=<?php echo $_GET["imp_com"]?> />
        <input type="hidden" id="imp_politica" name="imp_politica" value=<?php echo $_GET["imp_pol"]?> />
        <input type="hidden" id="imp_social" name="imp_social" value=<?php echo $_GET["imp_soc"]?> />
        <textarea hidden="true" id="valores2"><?php echo $_GET["linea_d"]?></textarea>
  
                  <article id="portada_modp">
            	<div id="solo_usuario_modificar">
					<?php echo $_SESSION['usu']?>
        		</div>    
              <p class="planilla2">Datos Del Proyecto</p>    
              <table width="862" class="contendor_proyecto53">
                        <tr>
                            <td width="315" class="proyecto_efecto">Macro Proyecto: 
                                <select name="macro_proyecto" size="1" id="macro_proyecto" required="required">
                                    <option selected="selected" value="<?php echo $_GET['idmac_proy']?>"><?php echo $_GET['mac_proy']?></option>
                                    <?= $de->combo_macroproyecto() ; ?>
                                </select>
                            </td>
                        	<td width="136">Descripcion:</td>
                            	<td width="496">
                                <textarea name="descripcion_macro_proyecto" cols="60" rows="1" id="descripcion_macro_proyecto"><?php echo $_GET['desc']?>
                                </textarea>
                                </td>
                        </tr>
              </table>
              <table class="contendor_proyecto51">    
                        <tr>
                        	<td width="161" class="proyecto_efecto">Area:
                                <select name="area" size="1" id="area">
                                    <option selected="selected" value=<?php echo $_GET['idarea']?>><?php echo $_GET['area']?></option>
                                    <?= $de->combo_area(); ?>
                                </select>
                            </td>
                            <td width="214" class="proyecto_efecto">Prioridad:
                                <select name="prioridad" size="1" id="prioridad">
                                    <option selected="selected" value=<?php echo $_GET['idpriori']?>><?php echo $_GET['priori']?></option>
                                    <?= $de->combo_prioridad(); ?>
                                </select>
                            </td>
                            <td width="161" class="proyecto_efecto">Unidad:
                                <select name="unidad" size="1" id="unidad">
                                    <option selected="selected" value=<?php echo $_GET['iduni']?>><?php echo $_GET['uni']?></option>
                                    <?= $de->combo_unidad(); ?>
                                </select>
                            </td>
                          <td width="204" class="proyecto_efecto">Forma De Pago:
                                <select name="forma_de_pago" size="1" id="forma_de_pago">
                                    <option selected="selected" value=<?php echo $_GET['idf_pago']?>><?php echo $_GET['f_pago']?></option>
									<?= $de->combo_pago(); ?>
                                </select>
                          </td>
                        </tr>
              </table>
              <table class="contendor_proyecto52" id="exp">
                  
                        <tr>
                        	<td width="144" class="proyecto_efecto">Linea De Negocio</td>
                            	<td width="175" class="proyecto_efecto">
                                	<select name="linea_de_negocio1" size="1" id="linea_de_negocio1">
                                       <!-- <option selected="selected">Seleccione</option> -->
                                       <option selected="selected" value=""></option>
										<?= $de->combo_linea(); ?>
                                       
                                    </select>
                                </td>
                            <td width="177" class="proyecto_efecto">Costo De Venta:</td>
                            	<td width="58" class="proyecto_efecto">
                                	<input name="costo_venta1" id="costo_venta1" type="text" size="20" onkeypress="return keyRestrict(event,'1234567890,.')" value="">
                                </td>
                            <td width="197" class="proyecto_efecto">Ingresos Único/Anual:</td>
                            	<td width="58" class="proyecto_efecto">
                                	<input name="ingresos_unico_anual1" id="ingresos_unico_anual1" size="20" type="text" onkeypress="return keyRestrict(event,'1234567890,.')" value="">
                               	</td>
                            <td width="42">
                            	<input name="iter" id="iter" value="2" type="hidden"/>
                                
                            	<img src="../../shared/img/img boton +/icontexto-webdev-add-032x032.png" id="crear" onclick="javascript:crear_capa();" class="boton_mas"/>
                                
                          </td>
                            <td width="78">
                           	  <img src="../../shared/img/img eliminar/icono_eliminar.gif" onclick="javascript:eliminar_capa();" class="boton_mas" />
                            </td>
                      </tr>
              </table>
              <table class="contendor_proyecto5">
                        <tr>
                            <td width="183">Importancia Comercial:
                            	<td width="60">Si
                                	<label for="Si"></label>
                                    <input type="radio" name="importancia_comercial" id="imp_comer_si" value="Si"/>
                                </td>
                                <td width="88">No
                                	<label for="No"></label>
                                    <input type="radio" name="importancia_comercial" id="imp_comer_no" value="No"/>
                               </td>
                            <td width="162">Importancia Politica:
                                <td width="53">Si
                                	<label for="Si"></label>
                                    <input type="radio" name="importancia_politica" id="imp_poli_si" value="Si"/>
                               </td>
                               <td width="97">No
                                	<label for="No"></label>
                                    <input type="radio" name="importancia_politica" id="imp_poli_no" value="No"/>
                               </td>
                            <td width="153">Importancia Social:
                                <td width="60">Si
                                    <input type="radio" name="importancia_social" id="imp_soci_si" value="Si"/>
                                    <label for="Si"></label>
                                </td>
                                <td width="57">No
                                    <input type="radio" name="importancia_social" id="imp_soci_no" value="No"/>
                                    <label for="No"></label>
                               </td>
                        </tr>
              </table>
              
              <input type="hidden" id="tamano_tabla" name="tamano_tabla" value="1" />
                   
          		  <input type="hidden" id="validar2" name="validar2" value="Registrar"/>
              <div id="botones">
                <p>&nbsp;                  </p>
                  <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>
                    <input type="button" name="registrar" value="Modificar" class="guardar" onclick="javascript:registrar2();"/>
                  <input type="button" name="limpiar" value="Limpiar" class="cancelar" onclick="window.location.reload();"/>
                  </p>
              </div>  
          </article>
        </form>
          
	</body>
</html>