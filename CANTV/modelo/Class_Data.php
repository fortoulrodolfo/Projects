<?php
class Class_Data{
	function Class_Data(){
		}
		
	function registrar(){
        $sql = "insert into sucursales (nombre_sede,direccion, observaciones, servicios_datos, servicios_internet, servicios_telf_fija, servicios_ti, servicios_moviles, servicios_equipamento, rif_institucion, idregion, idestado, idmunicipio, idcentral, idusuario, status) values ('$_POST[nombre_sede]','$_POST[direccion]', '$_POST[observaciones]', $_POST[servicios_datos], $_POST[servicios_internet], $_POST[servicios_telf_fija], $_POST[servicios_ti], $_POST[servicios_moviles], $_POST[servicios_equipamento], '$_POST[instituciones]', $_POST[region], $_POST[estado], $_POST[municipio], (select idcentral from central where central.cccc = '$_POST[cccc]'), '$_SESSION[ce]', '1')";
		$a = mysql_query($sql) or die(mysql_error());
        if ($a != ""){
            echo "<script>
					alert('¡Institucion Registrada Con Exito!')
        			location.href='data_ip.php'
        		  </script>";
				  }
			}
			
	function obtener_localidades(){
		 $sql = "select * from sucursales where rif_institucion='$_GET[codf]' and idusuario='$_SESSION[ce]'";
		 $a = mysql_query($sql) or die(mysql_error());
		 $arre= array();
		 while ($da = mysql_fetch_assoc($a)) {
			 $arre[]=array("id_sucursales"=>$da['idsucursales'],"nombre_sede"=>$da['nombre_sede']);
		 }
		 
		$ja=json_encode($arre);
		echo $ja;
		}
		
	function mostrar_localidad(){
		
		 $sql = "select * from sucursales where idsucursales='$_GET[codf]'";
		 $a = mysql_query($sql) or die(mysql_error());
		 $arre= array();
		 while ($da = mysql_fetch_assoc($a)) {
			 
			 $sql2="select r.nombre_region,e.nombre_estado,m.nombre_municipio,c.nombre_central, c.cccc from region r, estado e, municipio m, central c where r.idregion=$da[idregion] and e.idestado=$da[idestado] and m.idmunicipio=$da[idmunicipio] and c.idcentral=$da[idcentral]";
			 $a2 = mysql_query($sql2) or die(mysql_error());
			 while ($da2 = mysql_fetch_assoc($a2)){
			 
			 $arre[]=array("id_sucursales"=>$da['idsucursales'],"nombre_sede"=>$da['nombre_sede'],"direccion"=>$da['direccion'],
			 "observaciones"=>$da['observaciones'], "serv_datos"=>$da['servicios_datos'], 
			 "serv_internet"=>$da['servicios_internet'],"serv_telf"=>$da['servicios_telf_fija'],
			 "serv_ti"=>$da['servicios_ti'],"serv_moviles"=>$da['servicios_moviles'],"serv_equip"=>$da['servicios_equipamento'],"idregion"=>$da['idregion'],"region"=>$da2['nombre_region'],"idestado"=>$da['idestado'],"estado"=>$da2['nombre_estado'],"idmunicipio"=>$da['idmunicipio'],"municipio"=>$da2['nombre_municipio'],"idcentral"=>$da2['cccc'],"central"=>$da2['nombre_central']);
		 }
		 }
		 
		$ja=json_encode($arre);
		echo $ja;
		
		}
	
	function listar_data(){
		$sql = "select idsucursales, nombre_sede, direccion, observaciones, servicios_datos, servicios_internet, servicios_telf_fija, servicios_ti, servicios_moviles, servicios_equipamento, rif_institucion, 
			(select nombre_institucion from institucion where sucursales.rif_institucion = institucion.rif_institucion) AS nombre_institucion,(select nombre_region from region where sucursales.idregion = region.idregion)
				AS nombre_region, (select nombre_estado from estado where sucursales.idestado = estado.idestado)
				AS nombre_estado, (select nombre_municipio from municipio where sucursales.idmunicipio = municipio.idmunicipio)
				AS nombre_municipio, (select nombre_central from central where sucursales.idcentral = central.idcentral)
				AS nombre_central from sucursales where sucursales.idusuario = '$_SESSION[ce]' and sucursales.status!='2'";
		echo "<table class='tdata' id='Exportar_a_Excel3'>
		 	<tr>
				<th colspan='15'>Listado De Data IP Usuario: $_SESSION[usu]</th>
			</tr>    
            <tr class='title'>
            	<td>
					RIF
            	</td>
            	<td>
                	Institucion
            	</td>
            	<td>
                	Nombre Sede
            	</td>
				<td>
                	Region
            	</td>
				<td>
                	Estado
            	</td>
				<td>
                	Municipio
            	</td>
				<td>
                	Direccion
            	</td>
				<td>
                	Central
            	</td>
				<td>
                	Serv. Datos
            	</td>
				<td>
                	Serv. Internet
            	</td>
				<td>
                	Serv. Telf Fija
            	</td>
				<td>
                	Serv. TI
            	</td>
				<td>
                	Serv. Moviles
            	</td>
				<td>
                	Serv. Equip.
            	</td>
				<td>
                	Contacto
            	</td>

			</tr>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
			
        	echo "<tr>
					<td>
						$da[rif_institucion]  
					</td>
					<td>
						$da[nombre_institucion]
					</td>
					<td>
						$da[nombre_sede]
					</td>
					<td>
						".utf8_encode($da['nombre_region'])."
					</td>
					<td>
						".utf8_encode($da['nombre_estado'])."
					</td>
						<td>
						".utf8_encode($da['nombre_municipio'])."
					</td>
					<td>
						".utf8_encode($da['direccion'])."
					</td>
					<td>
						".utf8_encode($da['nombre_central'])."
					</td>
						<td>
						".utf8_encode($da['servicios_datos'])."
					</td>
					<td>
						".utf8_encode($da['servicios_internet'])."
					</td>
					<td>
						".utf8_encode($da['servicios_telf_fija'])."
					</td>
					<td>
						".utf8_encode($da['servicios_ti'])."
					</td>
					<td>
						".utf8_encode($da['servicios_moviles'])."
					</td>
					<td>
						".utf8_encode($da['servicios_equipamento'])."
					</td>
					<td>
						".utf8_encode($da['observaciones'])."
					</td>
					<td>
						<img src='../../shared/img/img eliminar/icono_eliminar.gif' width='15' tittle='eliminar' style='cursor:pointer' onclick='javascript:eliminar2($da[idsucursales])'/>
					</td>
					<td><a href='../data_inst/data_ip.php?cod=$da[idsucursales]&rif=$da[rif_institucion]'>
					<img src='../../shared/img/img modificar/Sin título.png' width='15' tittle='modificar' style='cursor:pointer'/></a>
					
					</td>
				</tr>";
			}
			echo "</table>";
		}	
		
		
	function listar_data_lider(){
		$sql = "select idsucursales, nombre_sede, direccion, observaciones, servicios_datos, servicios_internet, servicios_telf_fija,
		servicios_ti, servicios_moviles, servicios_equipamento, rif_institucion,
		(select nombre_institucion from institucion where sucursales.rif_institucion = institucion.rif_institucion) AS nombre_institucion,
		(select nombre_region from region where sucursales.idregion = region.idregion) AS nombre_region,
		(select nombre_estado from estado where sucursales.idestado = estado.idestado) AS nombre_estado,
		(select nombre_municipio from municipio where sucursales.idmunicipio = municipio.idmunicipio) AS nombre_municipio,
		(select nombre_central from central where sucursales.idcentral = central.idcentral) AS nombre_central,
		(select usu.nombre from usuario usu where usu.idcedula=sucursales.idusuario) AS nombre_consultor
		FROM sucursales, usuario
		WHERE sucursales.idusuario=usuario.idcedula and usuario.idgerencia='$_SESSION[idgerencia]' and usuario.idsector='$_SESSION[idsector]' and sucursales.status!= '2'
		ORDER BY  sucursales.rif_institucion";
		echo "<table class='tdata' id='Exportar_a_Excel3'>
		 	<tr>
				<th colspan='15'>Listado de Data IP Usuario: $_SESSION[usu]</th>
			</tr>    
            <tr class='title'>
            	<td>RIF</td>
            	<td>Institucion</td>
            	<td>Nombre Sede</td>
				<td>Region</td>
				<td>Estado</td>
				<td>Municipio</td>
				<td>Direccion</td>
				<td>Central</td>
				<td>Serv. Datos</td>
				<td>Serv. Internet</td>
				<td>Serv. Telf Fija</td>
				<td>Serv. TI</td>
				<td>Serv. Moviles</td>
				<td>Serv. Equip.</td>
				<td>Consultor</td>
				<td>Contacto</td>
			</tr>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
        	echo "<tr>
					<td>$da[rif_institucion]</td>
					<td>$da[nombre_institucion]</td>
					<td>$da[nombre_sede]</td>
					<td>".utf8_encode($da['nombre_region'])."</td>
					<td>".utf8_encode($da['nombre_estado'])."</td>
					<td>".utf8_encode($da['nombre_municipio'])."</td>
					<td>".utf8_encode($da['direccion'])."</td>
					<td>".utf8_encode($da['nombre_central'])."</td>
					<td>".utf8_encode($da['servicios_datos'])."</td>
					<td>".utf8_encode($da['servicios_internet'])."</td>
					<td>".utf8_encode($da['servicios_telf_fija'])."</td>
					<td>".utf8_encode($da['servicios_ti'])."</td>
					<td>".utf8_encode($da['servicios_moviles'])."</td>
					<td>".utf8_encode($da['servicios_equipamento'])."</td>
					<td>".utf8_encode($da['nombre_consultor'])."</td>
					<td>".utf8_encode($da['observaciones'])."</td>
				</tr>";
			}
			echo "</table>";
		}	
		
	function listar_data_gerente(){
		$sql = "select idsucursales, nombre_sede, direccion, observaciones, servicios_datos, servicios_internet, servicios_telf_fija,
		servicios_ti, servicios_moviles, servicios_equipamento, rif_institucion,
		(select nombre_institucion from institucion where sucursales.rif_institucion = institucion.rif_institucion) AS nombre_institucion,
		(select nombre_region from region where sucursales.idregion = region.idregion) AS nombre_region,
		(select nombre_estado from estado where sucursales.idestado = estado.idestado) AS nombre_estado,
		(select nombre_municipio from municipio where sucursales.idmunicipio = municipio.idmunicipio) AS nombre_municipio, 
		(select nombre_central from central where sucursales.idcentral = central.idcentral) AS nombre_central,
		(select usu.nombre from usuario usu where usu.idcedula=sucursales.idusuario) AS nombre_consultor 
		FROM sucursales, usuario usu
		WHERE sucursales.idusuario=usu.idcedula and usu.idgerencia='$_SESSION[idgerencia]' and sucursales.status!= '2'
		ORDER BY sucursales.rif_institucion";
		echo "<table class='tdata' id='Exportar_a_Excel3'>
		 	<tr>
				<th colspan='15'>Listado De Data IP Usuario: $_SESSION[usu]</th>
			</tr>    
            <tr class='title'>
            	<td>RIF</td>
            	<td>Institucion</td>
            	<td>Nombre Sede</td>
				<td>Region</td>
				<td>Estado</td>
				<td>Municipio</td>
				<td>Direccion</td>
				<td>Central</td>
				<td>Serv. Datos</td>
				<td>Serv. Internet</td>
				<td>Serv. Telf Fija</td>
				<td>Serv. TI</td>
				<td>Serv. Moviles</td>
				<td>Serv. Equip.</td>
				<td>Consultor</td>
				<td>Contacto</td>
			</tr>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
        	echo "<tr>
					<td>$da[rif_institucion]</td>
					<td>$da[nombre_institucion]</td>
					<td>$da[nombre_sede]</td>
					<td>".utf8_encode($da['nombre_region'])."</td>
					<td>".utf8_encode($da['nombre_estado'])."</td>
					<td>".utf8_encode($da['nombre_municipio'])."</td>
					<td>".utf8_encode($da['direccion'])."</td>
					<td>".utf8_encode($da['nombre_central'])."</td>
					<td>".utf8_encode($da['servicios_datos'])."</td>
					<td>".utf8_encode($da['servicios_internet'])."</td>
					<td>".utf8_encode($da['servicios_telf_fija'])."</td>
					<td>".utf8_encode($da['servicios_ti'])."</td>
					<td>".utf8_encode($da['servicios_moviles'])."</td>
					<td>".utf8_encode($da['servicios_equipamento'])."</td>
					<td>".utf8_encode($da['nombre_consultor'])."</td>
					<td>".utf8_encode($da['observaciones'])."</td>
				</tr>";
			}
			echo "</table>";
		}	
		
	function combo_instituciones(){
		$sql = "select * from institucion i, cartera_instituciones c where i.rif_institucion = c.rif_institucion and c.idcedula = '".$_SESSION['cedula']."' ORDER BY nombre_institucion";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[rif_institucion]'>$da[nombre_institucion]</option>";
            echo $html;
			}
    	}
		
    function combo_region(){
        $sql = "select * from region ORDER BY nombre_region";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)){
            	$html = "<option value='$da[idregion]'>$da[nombre_region]</option>";
            echo $html;
			}
    	}

    function combo_estados(){
        $sql = "select * from estado e,region_estado r where e.idestado=r.idestado and r.idregion=$_POST[v1] ORDER BY nombre_estado";
        	echo "<select name='estado' id='estado' onchange='carga_mun()'>
                  <option selected='selected'>Seleccione</option>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idestado]'>".utf8_encode($da['nombre_estado'])."</option>";
            echo $html;
				}
        	echo "</select>";
		}

    function combo_municipio(){
        $sql = "select * from municipio m,region_estado_municipio r where m.idmunicipio=r.idmunicipio and r.idestado=$_POST[v1] ORDER BY nombre_municipio";
        echo "<select name='municipio' id='municipio' onchange='carga_ce()'>
              <option selected='selected'>Seleccione</option>";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)){
            	$html = "<option value='$da[idmunicipio]'>".utf8_encode($da['nombre_municipio'])."</option>";
            echo $html;
				}
        	echo "</select>";
			}

    function combo_central(){
        $sql = "select * from central c where c.idmunicipio = $_POST[v1] ORDER BY nombre_central";
        echo "<select name='central' id='central' onchange='carga_ccc(), cargar_dis_central()'>
              	<option selected='selected'>Seleccione</option>";
		$a = mysql_query($sql) or die(mysql_error());
        while ($da = mysql_fetch_assoc($a)){
            $html="<option value='$da[cccc]'>".utf8_encode($da['nombre_central'])."</option>";
			echo $html;
				}
			echo "</select>";
			}
		
	function actualizar(){
        $sql="UPDATE sucursales SET servicios_datos=$_POST[servicios_datos], servicios_internet=$_POST[servicios_internet], 
		servicios_telf_fija=$_POST[servicios_telf_fija], servicios_ti=$_POST[servicios_ti], servicios_moviles=$_POST[servicios_moviles],
		servicios_equipamento=$_POST[servicios_equipamento]
		 WHERE idsucursales = '$_POST[localidad]'";
		  
		$a = mysql_query($sql) or die(mysql_error());
			echo "<script>alert('¡Servicios Actualizados Con exito!')
			location.href='data_ip.php'
			</script>";
			}
		
	function modificar(){
         $sql="UPDATE sucursales SET nombre_sede='$_POST[nombre_sede]',direccion='$_POST[direccion]',observaciones='$_POST[observaciones]',
		 	idregion=$_POST[region], idestado=$_POST[estado], idmunicipio=$_POST[municipio], 
			idcentral=(SELECT idcentral FROM central WHERE central.cccc = '$_POST[cccc]'),
			servicios_datos=$_POST[servicios_datos], servicios_internet=$_POST[servicios_internet],
			servicios_telf_fija=$_POST[servicios_telf_fija], servicios_ti=$_POST[servicios_ti],
			servicios_moviles=$_POST[servicios_moviles],servicios_equipamento=$_POST[servicios_equipamento] 
			WHERE idsucursales = '$_POST[localidad]'";
		  
		$a = mysql_query($sql) or die(mysql_error());
			echo "<script>alert('¡Data IP Actualizada Con exito!')
			location.href='data_ip.php'
			</script>";
			}
			
	function eliminar_dataip(){
        $sql = "UPDATE sucursales SET status='2' where idsucursales = $_POST[op]";
		$a = mysql_query($sql) or die(mysql_error());
        echo "<script>alert('¡Proyecto Eliminado Con exito!')</script>";
			}
		}
	
?>