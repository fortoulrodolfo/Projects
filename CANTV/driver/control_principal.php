<?php
	session_start();
		include '../modelo/Class_sql.php';
		include_once '../modelo/Usuario.php';

		$u=new Usuario();
			if(isset($_POST['accion'])){
				if ($_POST['accion']=="login"){
					if ($u->login()){
						if($_SESSION['tipo'] == '1'){
							echo "<table class='perfil'>
								<tbody>
									<thead>
										<tr>
											<th class='head'>Perfil De Usuario</th>
										</tr>
                    				</thead>";
            							echo $u->mostrar_perfil_admin();
                        				echo "
								</tbody>
                			 </table>";
							}
						else{
						echo
							"<table class='perfil'>
								<tbody>
									<thead>
										<tr>
											<th class='head'>Perfil De Usuario</th>
										</tr>
                    				</thead>";
            							echo $u->mostrar_perfil1();
                        				echo "
								</tbody>
                			 </table>";
						}
        			}else{
            			echo
							"<aside id='contenedor_todo'>
                    		 	<div id='contenedor_login'>
                        			<h2>Iniciar Sesión</h2>
								</div>
								<div class='contenido_inicio'>
									<fieldset id='inputs'>
										<label for='usuario'></label>
											<input name='usuario' id='usuario' type='text' title='Introduzca Su Cuenta' placeholder='Usuario' required='required'/>
                            			<label for='usuario'></label>
											<input name='contraseña' id='contraseña' type='password' title='Introduzca Contraseña' placeholder='Contraseña' required='required'/>
                        			</fieldset>
                        			<fieldset id='actions'>¡Contraseña Invalida!
										<input type='button' name='inicio_sesion' id='inicio_sesion' value='Entrar' onclick='javascript:entrar();'/>
                        			</fieldset>
                        			&nbsp;
                        			<ul><a href='#'>¿No Puedes Acceder A Tu Cuenta?</a></ul>
                    			</div>
							 </aside>";
            			}
    	}
	}
?>