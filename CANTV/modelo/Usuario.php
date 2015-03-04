<?php
	Class Usuario{
		private $table;
		private $fields = array();
		var $con;
	
//-----------------Mapping---------------
	private function Mapping(){
		$this->table = "usuario";
		$this->fields["idcedula"] = Array("table" => "idcedula", "flags" => "not_null primary_key", "type" => "string", "len" => "10", "change" => false, "iskey" => 1);
		$this->fields["nombre"] = Array("table" => "nombre", "flags" => "not_null", "type" => "string", "len" => "45", "change" => false, "iskey" => 0);
		$this->fields["telefonoMovil"] = Array("table" => "telefono_movil", "flags" => "not_null", "type" => "string", "len" => "22", "change" => false, "iskey" => 0);
		$this->fields["email"] = Array("table" => "email", "flags" => "not_null", "type" => "string", "len" => "45", "change" => false, "iskey" => 0);
		$this->fields["contrasena"] = Array("table" => "contrasena", "flags" => "not_null", "type" => "string", "len" => "30", "change" => false, "iskey" => 0);
		$this->fields["fechaRegistro"] = Array("table" => "fecha_registro", "flags" => "not_null binary", "type" => "date", "len" => "10", "change" => false, "iskey" => 0);
		$this->fields["idtipoUsuario"] = Array("table" => "idtipo_usuario", "flags" => "multiple_key", "type" => "int", "len" => "20", "change" => false, "iskey" => 0);
		$this->fields["idgerencia"] = Array("table" => "idgerencia", "flags" => "not_null multiple_key", "type" => "int", "len" => "20", "change" => false, "iskey" => 0);
		$this->fields["idsector"] = Array("table" => "idsector", "flags" => "not_null", "type" => "int", "len" => "20", "change" => false, "iskey" => 0);
		$this->fields["idregion"] = Array("table" => "idregion", "flags" => "not_null multiple_key", "type" => "int", "len" => "20", "change" => false, "iskey" => 0);
		$this->fields["idstatusUsuario"] = Array("table" => "idstatus_usuario", "flags" => "not_null multiple_key", "type" => "int", "len" => "20", "change" => false, "iskey" => 0);
	}

//-----------------Constructor---------------
	function Usuario(){
		$this->Mapping();
		}

//-----------------Set Methods---------------
	function setIdcedula($value){
		$value = trim($value);
		$this->fields["idcedula"]["value"] = $value;
		$this->fields["idcedula"]["change"] = true;
		}
	
	function setNombre($value){
		$value = trim($value);
		$this->fields["nombre"]["value"] = $value;
		$this->fields["nombre"]["change"] = true;
		}
	
	function setTelefonoMovil($value){
		$value = trim($value);
		$this->fields["telefonoMovil"]["value"] = $value;
		$this->fields["telefonoMovil"]["change"] = true;
		}
	
	function setEmail($value){
		$value = trim($value);
		$this->fields["email"]["value"] = $value;
		$this->fields["email"]["change"] = true;
		}
		
	function setContraseña($value){
		$value = trim($value);
		$this->fields["contrasena"]["value"] = $value;
		$this->fields["contrasena"]["change"] = true;
		}
		
	function setFechaRegistro($value){
		$this->fields["fechaRegistro"]["value"] = $value;
		$this->fields["fechaRegistro"]["change"] = true;
		}
		
	function setIdtipoUsuario($value){
		$value = intval($value);
		$this->fields["idtipoUsuario"]["value"] = $value;
		$this->fields["idtipoUsuario"]["change"] = true;
		}
		
	function setIdgerencia($value){
		$value = intval($value);
		$this->fields["idgerencia"]["value"] = $value;
		$this->fields["idgerencia"]["change"] = true;
		}
		
	function setIdsector($value){
		$value = intval($value);
		$this->fields["idsector"]["value"] = $value;
		$this->fields["idsector"]["change"] = true;
		}
		
	function setIdregion($value){
		$value = intval($value);
		$this->fields["idregion"]["value"] = $value;
		$this->fields["idregion"]["change"] = true;
		}
		
	function setIdstatusUsuario($value){
		$value = intval($value);
		$this->fields["idstatusUsuario"]["value"] = $value;
		$this->fields["idstatusUsuario"]["change"] = true;
		}
	
//-----------------Get Methods---------------
	function getIdcedula(){
		return $this->fields["idcedula"]["value"];
		}
		
	function getNombre(){
		return $this->fields["nombre"]["value"];
		}
		
	function getTelefonoMovil(){
		return $this->fields["telefonoMovil"]["value"];
		}
		
	function getEmail(){
		return $this->fields["email"]["value"];
		}
		
	function getContraseña(){
		return $this->fields["contraseña"]["value"];
		}
		
	function getFechaRegistro(){
		return $this->fields["fechaRegistro"]["value"];
		}
		
	function getIdtipoUsuario(){
		return $this->fields["idtipoUsuario"]["value"];
		}
		
	function getIdgerencia(){
		return $this->fields["idgerencia"]["value"];
		}
		
	function getIdsector(){
		return $this->fields["idsector"]["value"];
		}
		
	function getIdregion(){
		return $this->fields["idregion"]["value"];
		}
		
	function getIdstatusUsuario(){
		return $this->fields["idstatusUsuario"]["value"];
		}
	
//-----------------Other Methods---------------
	function login(){
		$sql = "select * from usuario u, gerencia g, tipo_usuario t where u.email LIKE '".strtoupper($_POST['v1'])."%' and u.contrasena = '".strtoupper($_POST['v2'])."' and u.idgerencia = g.idgerencia and u.idtipo_usuario = t.idtipo_usuario;";
		$a = mysql_query($sql) or die(mysql_error());
			if ($da = mysql_fetch_assoc($a)){
				$this->setEmail($da['email']);
					$_SESSION['gerencia'] = $da['nombre_gerencia'];
					$_SESSION['idsector'] = $da['idsector'];
					$_SESSION['idgerencia'] = $da['idgerencia'];
					$_SESSION['cargo'] = $da['nombre_tipo_usuario'];
					$_SESSION['cedula'] = $da['idcedula'];
					$_SESSION['tipo'] = $da['idtipo_usuario'];
				$this->setNombre($da['nombre']);
				$this->setTelefonoMovil($da['telefono_movil']);
				$this->setIdcedula($da['idcedula']);
				$this->crear_Session();
					return true;
					} else {
						return false;
						}
					}
				
	function crear_Session(){
		$_SESSION['usu'] = $this->getNombre();
		$_SESSION['email'] = $this->getEmail();
		$_SESSION['telefono'] = $this->getTelefonoMovil();
		$_SESSION['ce'] = $this->getIdcedula();
		}
		
	function mostrar_perfil($url){
		echo utf8_encode("<tr>
							<td class='head'>Informacion Del Usuario</td>
							 </tr>
							 <tr>
								<td>Nombre: $_SESSION[usu]</td>
							 </tr>
							 <tr>
								<td>Correo: $_SESSION[email]</td>
							 </tr>
							 <tr>
								<td>Gerencia: $_SESSION[gerencia]</td>
							 </tr>
							 <tr>
								<td>Cargo: $_SESSION[cargo]</td>
							 </tr>
							 <tr>
								<td><a href='javascript:cerrar()' class='caja_per'>Salir</a></td>
						   </tr>");
		}
		 
	function mostrar_perfil1(){
		echo utf8_encode("<tr>
						  	<td class='head'>Informacion Del Usario</td>
							 </tr>
							 <tr>
								<td>Nombre: $_SESSION[usu]</td>
							 </tr>
							 <tr>
								<td>Correo: $_SESSION[email]</td>
							 </tr>
							 <tr>
								<td>Gerencia: $_SESSION[gerencia]</td>
							 </tr>
							 <tr>
								<td>Cargo: $_SESSION[cargo]</td>
							 </tr>
							 <tr>
								<td><a href='' class='caja_per'>Demanda/Proyecto</a></td>
							 </tr>
							 <tr>
								<td><a href='javascript:cerrar()' class='caja_per'>Salir</a></td>
						  </tr>");
		}
		
		function mostrar_perfil_admin(){
		echo utf8_encode("<tr>
						  	<td class='head'>Informacion Del Usario</td>
							 </tr>
							 <tr>
								<td>Nombre: $_SESSION[usu]</td>
							 </tr>
							 <tr>
								<td>Correo: $_SESSION[email]</td>
							 </tr>
							 <tr>
								<td>Gerencia: $_SESSION[gerencia]</td>
							 </tr>
							 <tr>
								<td>Cargo: $_SESSION[cargo]</td>
							 </tr>
							 <tr>
								<td><a href='administrador/admin.php' class='caja_per'>Administrar</a></td>
							 </tr>
							 <tr>
								<td><a href='javascript:cerrar()' class='caja_per'>Salir</a></td>
						  </tr>");
		}
	 
	function crear_usuario(){
		$sql="insert into usuario (idcedula, nombre, telefono_movil, email, contrasena, fecha_registro, idtipo_usuario, idgerencia, idsector, idregion, idstatus_usuario) values ('$_POST[identidad]$_POST[cedula]', '$_POST[nombre] $_POST[apellido]', '$_POST[codigo]-$_POST[telefono_movil]', '$_POST[email]', '$_POST[contraseña]', '".date('Y-m-d')."', $_POST[tipo_usuario], $_POST[gerencia], $_POST[sector], $_POST[region], 1)";
		$a = mysql_query($sql) or die(mysql_error());
			echo "<script>
					alert('Usuario Registrado Correctamente')
					location.href='../admin.php';
				  </script>";
			}
	
	function listar_g(){
		$sql = "select * from gerencia";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)){
				$html = "<option value='$da[idgerencia]'>$da[nombre_gerencia]</option>";
			echo $html;
			}
		}
	
	function listar_sector(){
		$sql = "select * from sector s, gerencia_sector gs where s.idsector=gs.idsector and gs.idgerencia=$_POST[v1]";
		echo "<select name='sector' id='sector'>
                  <option selected='selected'>Seleccione</option>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idsector]'>" . utf8_encode($da['nombre_sector']) . "</option>";
            echo $html;
				}
        	echo "</select>";
		}

	function listar_region(){
        $sql = "select * from region";
		$a = mysql_query($sql) or die(mysql_error());
        while ($da = mysql_fetch_assoc($a)) {
            $html = "<option value='$da[idregion]'>$da[nombre_region]</option>";
			echo $html;
        	}
		}
    
    function listar_tipou(){
        $sql = "select * from tipo_usuario where tipo=2";
		$a = mysql_query($sql) or die(mysql_error());
        while ($da = mysql_fetch_assoc($a)) {
			$html = "<option value='$da[idtipo_usuario]'>$da[nombre_tipo_usuario]</option>";
        	echo $html;
			}
    	}
		
		function listar_usuarios(){
		$sql = "SELECT usu.idcedula, usu.nombre, usu.telefono_movil, usu.email, 
				(SELECT tu.nombre_tipo_usuario FROM tipo_usuario tu WHERE tu.idtipo_usuario = usu.idtipo_usuario) AS tipo_usuario,
				(SELECT g.nombre_gerencia FROM gerencia g WHERE g.idgerencia = usu.idgerencia) AS idgerencia,
				(SELECT s.nombre_sector FROM sector s WHERE s.idsector = usu.idsector) AS idsector,
				(SELECT r.nombre_region FROM region r WHERE r.idregion = usu.idregion) AS idregion,
				(SELECT st.nombre_status_usuario FROM status_usuario st WHERE st.idstatus_usuario = usu.idstatus_usuario) AS status
				FROM usuario usu
				WHERE usu.idcedula='$_POST[busca_cedula]'";
         echo "<table class='tdem' id='Exportar_a_Excel'>   
            <tr class='title'>
            	<td>Cedula</td>
            	<td>Nombre</td>
            	<td>Telefono</td>
            	<td>Email</td>
            	<td>Tipo Usuario</td>	
            	<td>Gerencia</td>
            	<td>Sector</td>
            	<td>Region</td>
				<td>Status</td>
			</tr>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
        	echo "<tr>
					<td>".utf8_encode($da['idcedula'])."</td>
					<td>".utf8_encode($da['nombre'])."</td>
					<td>".utf8_encode($da['telefono_movil'])."</td>
					<td>".utf8_encode($da['email'])."</td>
					<td>".utf8_encode($da['tipo_usuario'])."</td>
					<td>".utf8_encode($da['idgerencia'])."</td>
					<td>".utf8_encode($da['idsector'])."</td>
					<td>".utf8_encode($da['idregion'])."</td>
					<td>$da[status]</td>
				</tr>";
			}
			echo "</table>";
		}
	}
?>