<?php
	session_start();
	include_once '../../config.php';
	$nas=new Usuario();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>. : : Principal Administrador : : .</title>
        <link href="../../shared/css/estilos_cantv.css" rel="stylesheet"/>
        <link href="../../shared/css/style.css" rel="stylesheet" type="text/css" />
        <link href="../../shared/img/img cabecera/favicon.png" rel="shortcut icon"/>
        <script type="text/javascript" src="../../shared/plugins/navegadores/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="../../shared/plugins/admin/jquery.easing.1.3.js"></script>
        <script type="text/javascript" src="../../shared/js/jquery.js"></script>
		<script type="text/javascript" src="../../shared/js/interface.js"></script>
        <script type="text/javascript">
            $(function() {
                $('#sdt_menu > li').bind('mouseenter',function(){
					var $elem = $(this);
					$elem.find('img')
						 .stop(true)
						 .animate({
							'width':'170px',
							'height':'170px',
							'left':'0px'
						 },400,'easeOutBack')
						 .andSelf()
						 .find('.sdt_wrap')
					     .stop(true)
						 .animate({'top':'140px'},500,'easeOutBack')
						 .andSelf()
						 .find('.sdt_active')
					     .stop(true)
						 .animate({'height':'170px'},300,function(){
						var $sub_menu = $elem.find('.sdt_box');
						if($sub_menu.length){
							var left = '170px';
							if($elem.parent().children().length == $elem.index()+1)
								left = '-170px';
							$sub_menu.show().animate({'left':left},200);
						}	
					});
				}).bind('mouseleave',function(){
					var $elem = $(this);
					var $sub_menu = $elem.find('.sdt_box');
					if($sub_menu.length)
						$sub_menu.hide().css('left','0px');
					
					$elem.find('.sdt_active')
						 .stop(true)
						 .animate({'height':'0px'},300)
						 .andSelf().find('img')
						 .stop(true)
						 .animate({
							'width':'0px',
							'height':'0px',
							'left':'85px'},400)
						 .andSelf()
						 .find('.sdt_wrap')
						 .stop(true)
						 .animate({'top':'25px'},500);
				});
            });
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
             <li style="margin-left:380px;"><a href="#">Funciones Administrador</a></li>
        </nav>
        <article id="portada112">
        	<p class="p2">Bienvenido...</p>
        	<p class="planilla">Funciones Administrador</p>
   			<body2>
            <div class="dock" id="dock2">
  			<div class="dock-container2">
  				<a class="dock-item2" href="admin.php"><span>Inicio</span><img src="../../shared/img/img admin/img menu admin/inicio.ico"/></a>
  				<a class="dock-item2" href="registrar/registrar_usuario.php"><span>Registrar Usuario</span><img src="../../shared/img/img admin/img menu admin/registrar/reg_usuario.ico"/></a> 
  				<a class="dock-item2" href="registrar/registrar_institucion.php"><span>Registrar I.Publica</span><img src="../../shared/img/img admin/img menu admin/registrar/reg_institucion.png"/></a> 
  				<a class="dock-item2" href="registrar/registrar_central.php"><span>Registrar Central</span><img src="../../shared/img/img admin/img menu admin/registrar/reg_central.png"/></a>
                <a class="dock-item2" href="modificar/modificar_usuario.php"><span>Modificar Usuario</span><img src="../../shared/img/img admin/img menu admin/modificar/mod_usuario.ico"/></a> 
                <a class="dock-item2" href="modificar/modificar_cartera.php"><span>Modificar Cartera</span><img src="../../shared/img/img admin/img menu admin/modificar/mod_cartera.ico"/></a>  
  				<a class="dock-item2" href="noticias/corporativas.php"><span>Noticias Corporativas</span><img src="../../shared/img/img admin/img menu admin/noticias/not_corpo.ico"/></a> 
  				<a class="dock-item2" href="noticias/ins_publicas.php"><span>Noticias I.Publicas</span><img src="../../shared/img/img admin/img menu admin/noticias/not_publica.ico"/></a>
  				<a class="dock-item2" href="importar/importar.php"><span>Actualizar Disp. Central</span><img src="../../shared/img/img admin/img menu admin/excel.png"/></a> 
                <a class="dock-item2" href="../../driver/ishorcut.php"><span>Salir</span><img src="../../shared/img/img admin/img menu admin/salir.ico"/></a> 
                <!--
                    <a class="dock-item2" href="modificar/ver_central.php"><span>Modificar Central</span><img src="../../shared/img/img admin/img menu admin/history.png"/></a> 
                <a class="dock-item2" href="modificar/ver_institucion.php"><span>Modificar I.Publica</span><img src="../../shared/img/img admin/img menu admin/history.png"/></a> 
                <a class="dock-item2" href="modificar/ver_usuario.php"><span>Modificar Usuario</span><img src="../../shared/img/img admin/img menu admin/history.png"/></a> 
                -->
  </div>
</div>
<script type="text/javascript">	
	$(document).ready(
		function()
		{
			$('#dock2').Fisheye(
				{
					maxWidth: 70,
					items: 'a',
					itemsText: 'span',
					container: '.dock-container2',
					itemWidth: 60,
					proximity: 70,
					alignment : 'left',
					valign: 'bottom',
					halign : 'center'
				}
			)
		}
	);
</script>
</body>
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