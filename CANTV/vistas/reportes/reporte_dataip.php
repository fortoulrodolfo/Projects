<?php
	session_start();
	include_once '../../config.php';
	include_once '../../modelo/Class_Reporte.php';
	$nas=new Usuario();
	
	$de=new Class_Reporte();
	
	
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Reportes DataIP : : .</title>
        <link href="../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="../../shared/plugins/admin/jquery.easing.1.3.js"></script>
        <style type="text/css" title="currentStyle">
			/*@import "../../shared/plugins/media/css/demo_page.css";*/
			@import "../../shared/plugins/media/css/jquery.dataTables.css";
			@import "../../shared/plugins/TableTools/media/css/TableTools.css";
		</style>
		<script type="text/javascript" language="javascript" src="../../shared/plugins/media/js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="../../shared/plugins/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" language="javascript" src="../../shared/plugins/TableTools/media/js/TableTools.min.js"></script>
        <script type="text/javascript" language="javascript" src="../../shared/plugins/TableTools/media/js/ZeroClipboard.js"></script>
		<script>
		(function($) {

		$.fn.dataTableExt.oApi.fnGetColumnData = function ( oSettings, iColumn, bUnique, bFiltered, bIgnoreEmpty ) {
    		// check that we have a column id
    	if ( typeof iColumn == "undefined" ) return new Array();
     
    	// by default we only want unique data
    	if ( typeof bUnique == "undefined" ) bUnique = true;
     
    	// by default we do want to only look at filtered data
    	if ( typeof bFiltered == "undefined" ) bFiltered = true;
     
    	// by default we do not want to include empty values
    	if ( typeof bIgnoreEmpty == "undefined" ) bIgnoreEmpty = true;
     
    	// list of rows which we're going to loop through
   		 var aiRows;
     
    	// use only filtered rows
    	if (bFiltered == true) aiRows = oSettings.aiDisplay;
    	// use all rows
    	else aiRows = oSettings.aiDisplayMaster; // all row numbers
 
	    // set up data array   
    	var asResultData = new Array();
     
    	for (var i=0,c=aiRows.length; i<c; i++) {
        	iRow = aiRows[i];
        	var aData = this.fnGetData(iRow);
        	var sValue = aData[iColumn];
         
        	// ignore empty values?
        	if (bIgnoreEmpty == true && sValue.length == 0) continue;
 
        	// ignore unique values?
        	else if (bUnique == true && jQuery.inArray(sValue, asResultData) > -1) continue;
         
        	// else push the value onto the result data array
        	else asResultData.push(sValue);
    	}
     
    	return asResultData;
	}}(jQuery));
 
 
	function fnCreateSelect( aData )
	{
    	var r='<select id=aData.index();><option value=""></option>', i, iLen=aData.length;
    	for ( i=0 ; i<iLen ; i++ )
    	{
       	 r += '<option value="'+aData[i]+'">'+aData[i]+'</option>';
    	}
    	return r+'</select>';
	}
 
 
	$(document).ready(function() {
    	/* Initialise the DataTable */
    	var oTable = $('#example').dataTable( {
			"sDom": 'T<"clear">lfrtip',
			"oTableTools": {
				"sSwfPath": "../../shared/plugins/TableTools/media/swf/copy_csv_xls_pdf.swf",
				"aButtons":[
				{
					"sExtends": "csv",
					"sButtonText" : "XLS",
					"mColumns" : "visible",
					"bFooter" : false,
					"bShowAll" : true
					},
				{
					"sExtends": "pdf",
					"sButtonText" : "PDF",
					"mColumns" : "visible",
					"bFooter" : false,
					"sPdfOrientation" : "landscape",
					"bShowAll" : true
					},
				{
					"sExtends": "print",
					"sButtonText" : "Imprimir",
					"sInfo" : "<h6> Vista Previa </h6><center><p>Por favor usa la funcion de imprimir de tu explorador para imprimir esta tabla. Presiona escape para salir</p></center>",
					"bShowAll" : true,
					}
				]
			},
			"aoColumns": [
					{"sWidth": "6%" },
					{"sWidth": "15%" },
					{"sWidth": "7%" },
					{"sWidth": "12%" },
					{"sWidth": "9%"  },
					{"sWidth": "8%"  },
					{"sWidth": "10%" },
					{"sWidth": "7%"  },
					{"sWidth": "7%"  },
					{"sWidth": "7%"  },
					{"sWidth": "7%"  },
					{"sWidth": "7%"  },	
					{"sWidth": "7%"  },
					{"sWidth": "9%"  },
					{"sWidth": "7%"  },
					{"sWidth": "7%"  },
		],
		
		"bAutoWidth": false,
      	"oLanguage": {
			"sLengthMenu": "Mostrar _MENU_ registros por pagina",
            "sZeroRecords": "Nothing found - sorry",
            "sInfo": "Mostrando _START_ de _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando 0 de 0 de 0 regisros",
            "sInfoFiltered": "(filtrado de _MAX_ registros total)",
       	     "sSearch": "Buscar:",
			 }
    	} );
     
    	/* Add a select menu for each TH element in the table footer */
    	$("tfoot th").each( function ( i ) {
        	this.innerHTML = fnCreateSelect( oTable.fnGetColumnData(i) );
        	$('select', this).change( function () {
           	 oTable.fnFilter( $(this).val(), i );
        	} );
			
    	} );
		
		fnShowHide(5);
		fnShowHide(6);
		fnShowHide(7);
		fnShowHide(14);
		fnShowHide(15);
		verificar_cantidad();
	} );
    
		function fnShowHide( iCol )
	{
  	  /* Get the DataTables object again - this is not a recreation, just a get of the object */
  	  var oTable = $('#example').dataTable();
   	  
   		 var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
   		 oTable.fnSetColumnVis( iCol, bVis ? false : true );
	}
	
	function verificar_cantidad(cod)
	{
		var contar=0;
		
		for(i=0 ; i<16 ; i++){
			if(document.getElementById("check"+i).checked){
				contar=contar+1;
				};
				
		};
		
		if (contar>11){
			fnShowHide(cod);
			document.getElementById("check"+cod).checked=false;
			}
		
	}
	
	/*				*/
    
    </script>
	
    	<style>
        .check{
			font-family:Arial, Helvetica, sans-serif;
			font-size:15px;
			margin-top:30px;
  		    border-radius:5px;
		  	color:#BD0F1C;
   		    display:block;
   			padding:5px;
    		text-shadow:0px 1px 0px #CCC;
    		width:auto;
			text-align:center;
			}
			
		@media only print{
			BODY { width:80%;}
			}
		
        </style>
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
       <p class="planilla">Reportes Data Ip</p>
        <article id="portada111" name="portada111">
        	<?php $de->reporte_dataip() ?>
           
        </article>
        <footer>
        	<p>Copyright © 2012 Gerencia De Instituciones Publicas - Todos Los Derechos Reservados |
            	<a href="../../manuales/manual usuario/Usuario.pdf" target="_blank">Ayuda</a>
                |
                <a href="../contacto/contacto.php">Contacto</a>
            </p>
        </footer>
	</body>
</html>