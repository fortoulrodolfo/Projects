<?php
class Class_Demanda{
	function Class_Demanda(){
		}

    function registrar(){
		
		
        $sql = "insert into institucion_servicios (rif_institucion, idservicio, idtipo_servicio, idsubtipo_servicio, idplan, idcentral, cantidad, idtrimestre, ano, idusuario,fecha,status) values ('$_POST[instituciones]', $_POST[servicio], $_POST[tipo_servicio], $_POST[subtipo_servicio], $_POST[plan], (select idcentral from central where central.cccc = '$_POST[cccc]'), '$_POST[cantidad]', $_POST[trimestre], $_POST[ano], '$_SESSION[ce]', NOW(), '1')";
        $a = mysql_query($sql) or die(mysql_error());
		
		if ($_POST['validar2']=="Registrar"){
			
			$sql2 = "insert into sucursales (nombre_sede,rif_institucion, idregion, idestado, idmunicipio, idcentral, idusuario, status) values ('$_POST[nombre_sede]','$_POST[instituciones]', $_POST[region], $_POST[estado], $_POST[municipio], (select idcentral from central where central.cccc = '$_POST[cccc]'), '$_SESSION[ce]', '1')";
		$a2 = mysql_query($sql2) or die(mysql_error());
			}
		
		if ($a != ""){
			
            echo "<script>
					alert('¡Demanda Registrada Con Exito!')
        		  </script>";
				  }
				  
			}

    function listar_demandas(){
		$sql = "select 
			idinstitucion_servicios, 
			rif_institucion, 
			(select nombre_institucion from institucion where institucion_servicios.rif_institucion = institucion.rif_institucion) AS nombre_institucion,
			(select nombre_servicio from servicio where institucion_servicios.idservicio = servicio.idservicio) AS nombre_servicio,
			(select idservicio from servicio where institucion_servicios.idservicio = servicio.idservicio) AS id_servicio,
			(select nombre_tipo_servicio from tipo_servicio where institucion_servicios.idtipo_servicio = tipo_servicio.idtipo_servicio) AS nombre_tipo_servicio,
			(select idtipo_servicio from tipo_servicio where institucion_servicios.idtipo_servicio = tipo_servicio.idtipo_servicio) AS id_tipo_servicio,
			(select nombre_subtipo_servicio from subtipo_servicio where institucion_servicios.idsubtipo_servicio = subtipo_servicio.idsubtipo_servicio) AS nombre_subtipo_servicio,
			(select idsubtipo_servicio from subtipo_servicio where institucion_servicios.idsubtipo_servicio = subtipo_servicio.idsubtipo_servicio) AS id_subtipo_servicio,
			(select nombre_plan from plan where institucion_servicios.idplan = plan.idplan) AS nombre_plan,
			(select idplan from plan where institucion_servicios.idplan = plan.idplan) AS id_plan,
			(select nombre_central from central where institucion_servicios.idcentral = central.idcentral) AS nombre_central,
			(select nombre_trimestre from trimestre where institucion_servicios.idtrimestre = trimestre.idtrimestre) AS nombre_trimestre,
			(select idtrimestre from trimestre where institucion_servicios.idtrimestre = trimestre.idtrimestre) AS id_trimestre,
			ano,
			cantidad
		from institucion_servicios where institucion_servicios.idusuario = '$_SESSION[ce]' and (institucion_servicios.status = '1' OR institucion_servicios.status = '2') ";
         echo "<table class='tdem' id='Exportar_a_Excel'>
		 	<tr>
				<th colspan='15'>Listado De Demandas Usuario: $_SESSION[usu]</th>
			</tr>    
            <tr class='title'>
            	<td>Codigo</td>
            	<td>R.I.F</td>
            	<td>Institucion</td>
            	<td>Servicio</td>
            	<td>Tipo</td>
            	<td>Sub-Tipo</td>	
            	<td>Plan</td>
            	<td>Central</td>
            	<td>Trimestre</td>
            	<td>Año</td>
				<td>Cantidad</td>
			</tr>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
				$serv= '"' . $da['nombre_servicio'] . '"';
				$tipo= '"' . $da['nombre_tipo_servicio'] . '"';
				$sub_tipo= '"' . $da['nombre_subtipo_servicio'] . '"';
				$plan= '"' . $da['nombre_plan'] . '"';
				$tri= '"' . $da['nombre_trimestre'] . '"';
        	echo "<tr>
					<td>$da[idinstitucion_servicios]</td>
					<td>$da[rif_institucion]</td>
					<td>$da[nombre_institucion]</td>
					<td>".utf8_encode($da['nombre_servicio'])."</td>
					<td>".utf8_encode($da['nombre_tipo_servicio'])."</td>
					<td>".utf8_encode($da['nombre_subtipo_servicio'])."</td>
					<td>".utf8_encode($da['nombre_plan'])."</td>
					<td>".utf8_encode($da['nombre_central'])."</td>
					<td>".utf8_encode($da['nombre_trimestre'])."</td>
					<td>$da[ano]</td>
					<td>".utf8_encode($da['cantidad'])."</td>
					<td><img src='../../shared/img/img eliminar/icono_eliminar.gif' width='15' tittle='eliminar' style='cursor:pointer' onclick='javascript:eliminar2($da[idinstitucion_servicios])'/></td>
					<td><a href='#' onclick='javascript:modificar2($da[idinstitucion_servicios],$da[id_servicio],$serv,$da[id_tipo_servicio],$tipo,$da[id_subtipo_servicio],$sub_tipo,$da[id_plan],$plan,$da[id_trimestre],$tri,$da[ano],$da[cantidad]);'><img src='../../shared/img/img modificar/Sin título.png' width='15' tittle='modificar' style='cursor:pointer' '/></a></td>
				</tr>";
			}
			echo "</table>";
		}
		
		 function listar_demandas_lider(){
			
			 
		$sql = "select 
			idinstitucion_servicios, 
			rif_institucion, 
			(select nombre_institucion from institucion where institucion_servicios.rif_institucion = institucion.rif_institucion) AS nombre_institucion,
			(select nombre_servicio from servicio where institucion_servicios.idservicio = servicio.idservicio) AS nombre_servicio,
			(select idservicio from servicio where institucion_servicios.idservicio = servicio.idservicio) AS id_servicio,
			(select nombre_tipo_servicio from tipo_servicio where institucion_servicios.idtipo_servicio = tipo_servicio.idtipo_servicio) AS nombre_tipo_servicio,
			(select idtipo_servicio from tipo_servicio where institucion_servicios.idtipo_servicio = tipo_servicio.idtipo_servicio) AS id_tipo_servicio,
			(select nombre_subtipo_servicio from subtipo_servicio where institucion_servicios.idsubtipo_servicio = subtipo_servicio.idsubtipo_servicio) AS nombre_subtipo_servicio,
			(select idsubtipo_servicio from subtipo_servicio where institucion_servicios.idsubtipo_servicio = subtipo_servicio.idsubtipo_servicio) AS id_subtipo_servicio,
			(select nombre_plan from plan where institucion_servicios.idplan = plan.idplan) AS nombre_plan,
			(select idplan from plan where institucion_servicios.idplan = plan.idplan) AS id_plan,
			(select nombre_central from central where institucion_servicios.idcentral = central.idcentral) AS nombre_central,
			(select nombre_trimestre from trimestre where institucion_servicios.idtrimestre = trimestre.idtrimestre) AS nombre_trimestre,
			(select idtrimestre from trimestre where institucion_servicios.idtrimestre = trimestre.idtrimestre) AS id_trimestre,
			(select u.nombre from usuario u where u.idcedula=institucion_servicios.idusuario) AS nombre_consultor,
			ano,cantidad,idcedula
			FROM institucion_servicios,usuario
			WHERE institucion_servicios.idusuario=usuario.idcedula
					and (institucion_servicios.status = '1' OR institucion_servicios.status = '2')
					and usuario.idgerencia='$_SESSION[idgerencia]' and usuario.idsector= '$_SESSION[idsector]'
			GROUP BY idinstitucion_servicios";
         echo "<table class='tdem' id='Exportar_a_Excel'>
		 	<tr>
				<th colspan='15'>Listado De Demandas Usuario: $_SESSION[usu]</th>
			</tr>    
            <tr class='title'>
            	<td>Codigo</td>
            	<td>R.I.F</td>
            	<td>Institucion</td>
            	<td>Servicio</td>
            	<td>Tipo</td>
            	<td>Sub-Tipo</td>	
            	<td>Plan</td>
            	<td>Central</td>
            	<td>Trimestre</td>
            	<td>Año</td>
				<td>Cantidad</td>
				<td>Consultor</td>
			</tr>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
				$serv= '"' . $da['nombre_servicio'] . '"';
				$tipo= '"' . $da['nombre_tipo_servicio'] . '"';
				$sub_tipo= '"' . $da['nombre_subtipo_servicio'] . '"';
				$plan= '"' . $da['nombre_plan'] . '"';
				$tri= '"' . $da['nombre_trimestre'] . '"';
				$consul= $da['nombre_consultor'];
        	echo "<tr>
					<td>$da[idinstitucion_servicios]</td>
					<td>$da[rif_institucion]</td>
					<td>$da[nombre_institucion]</td>
					<td>".utf8_encode($da['nombre_servicio'])."</td>
					<td>".utf8_encode($da['nombre_tipo_servicio'])."</td>
					<td>".utf8_encode($da['nombre_subtipo_servicio'])."</td>
					<td>".utf8_encode($da['nombre_plan'])."</td>
					<td>".utf8_encode($da['nombre_central'])."</td>
					<td>".utf8_encode($da['nombre_trimestre'])."</td>
					<td>$da[ano]</td>
					<td>".utf8_encode($da['cantidad'])."</td>
					<td>$consul</td>
				</tr>";
			}
			echo "</table>";
		}
		
		
		function listar_demandas_gerente(){
		$sql = "select 
			idinstitucion_servicios, 
			rif_institucion, 
			(select nombre_institucion from institucion where institucion_servicios.rif_institucion = institucion.rif_institucion) AS nombre_institucion,
			(select nombre_servicio from servicio where institucion_servicios.idservicio = servicio.idservicio) AS nombre_servicio,
			(select idservicio from servicio where institucion_servicios.idservicio = servicio.idservicio) AS id_servicio,
			(select nombre_tipo_servicio from tipo_servicio where institucion_servicios.idtipo_servicio = tipo_servicio.idtipo_servicio) AS nombre_tipo_servicio,
			(select idtipo_servicio from tipo_servicio where institucion_servicios.idtipo_servicio = tipo_servicio.idtipo_servicio) AS id_tipo_servicio,
			(select nombre_subtipo_servicio from subtipo_servicio where institucion_servicios.idsubtipo_servicio = subtipo_servicio.idsubtipo_servicio) AS nombre_subtipo_servicio,
			(select idsubtipo_servicio from subtipo_servicio where institucion_servicios.idsubtipo_servicio = subtipo_servicio.idsubtipo_servicio) AS id_subtipo_servicio,
			(select nombre_plan from plan where institucion_servicios.idplan = plan.idplan) AS nombre_plan,
			(select idplan from plan where institucion_servicios.idplan = plan.idplan) AS id_plan,
			(select nombre_central from central where institucion_servicios.idcentral = central.idcentral) AS nombre_central,
			(select nombre_trimestre from trimestre where institucion_servicios.idtrimestre = trimestre.idtrimestre) AS nombre_trimestre,
			(select idtrimestre from trimestre where institucion_servicios.idtrimestre = trimestre.idtrimestre) AS id_trimestre,
			(select u.nombre from usuario u where u.idcedula=institucion_servicios.idusuario) AS nombre_consultor,
			ano,cantidad,idcedula
			FROM institucion_servicios,usuario
			WHERE institucion_servicios.idusuario=usuario.idcedula
					and (institucion_servicios.status = '1' OR institucion_servicios.status = '2') and usuario.idgerencia='$_SESSION[idgerencia]'
			GROUP BY idinstitucion_servicios";
         echo "<table class='tdem' id='Exportar_a_Excel'>
		 	<tr>
				<th colspan='15'>Listado De Demandas Usuario: $_SESSION[usu]</th>
			</tr>    
            <tr class='title'>
            	<td>Codigo</td>
            	<td>R.I.F</td>
            	<td>Institucion</td>
            	<td>Servicio</td>
            	<td>Tipo</td>
            	<td>Sub-Tipo</td>	
            	<td>Plan</td>
            	<td>Central</td>
            	<td>Trimestre</td>
            	<td>Año</td>
				<td>Cantidad</td>
				<td>Consultor</td>
			</tr>";
        $a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
				$serv= '"' . $da['nombre_servicio'] . '"';
				$tipo= '"' . $da['nombre_tipo_servicio'] . '"';
				$sub_tipo= '"' . $da['nombre_subtipo_servicio'] . '"';
				$plan= '"' . $da['nombre_plan'] . '"';
				$tri= '"' . $da['nombre_trimestre'] . '"';
				$consul= $da['nombre_consultor'];
        	echo "<tr>
					<td>$da[idinstitucion_servicios]</td>
					<td>$da[rif_institucion]</td>
					<td>$da[nombre_institucion]</td>
					<td>".utf8_encode($da['nombre_servicio'])."</td>
					<td>".utf8_encode($da['nombre_tipo_servicio'])."</td>
					<td>".utf8_encode($da['nombre_subtipo_servicio'])."</td>
					<td>".utf8_encode($da['nombre_plan'])."</td>
					<td>".utf8_encode($da['nombre_central'])."</td>
					<td>".utf8_encode($da['nombre_trimestre'])."</td>
					<td>$da[ano]</td>
					<td>".utf8_encode($da['cantidad'])."</td>
					<td>$consul</td>
				</tr>";
			}
			echo "</table>";
		}
		
    function eliminar_demanda(){
        $sql = "UPDATE institucion_servicios SET status = '0', fecha = NOW() WHERE idinstitucion_servicios = $_POST[op]";
		$a = mysql_query($sql) or die(mysql_error());
        	echo "<script>alert('¡Demanda Eliminada Con exito!')</script>";
			}
	
	function modificar(){
		$sql="UPDATE institucion_servicios SET idservicio = $_POST[servicio], idtipo_servicio = $_POST[tipo], idsubtipo_servicio = $_POST[subtipo], idplan = $_POST[pplan], idtrimestre = $_POST[trimestre], cantidad = $_POST[cantidad], ano = $_POST[ano], status = '2', fecha = NOW() WHERE institucion_servicios.idusuario = '$_SESSION[ce]' AND institucion_servicios.idinstitucion_servicios = '$_POST[cood]'";
		$a = mysql_query($sql) or die(mysql_error());
			echo "<script>alert('¡Demanda Modificada Con exito!')
			window.opener.location= 'vista_ver.php';
			window.close();
			</script>";
			}

    function combo_instituciones(){
		$sql = "select *from institucion i , cartera_instituciones c where i.rif_institucion = c.rif_institucion and c.idcedula = '".$_SESSION['cedula']."' ORDER BY nombre_institucion";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[rif_institucion]'>$da[nombre_institucion]</option>";
            echo $html;
			}
    	}
		
	 function ultima_institucion(){
		if(isset($_POST['instituciones'])){
			
		$sql = "select *from institucion i  where i.rif_institucion ='".$_POST['instituciones']."'";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[rif_institucion]'>$da[nombre_institucion]</option>";
            echo $html;
			}
    	}
		else{
			echo $html="<option value=''>Seleccione</option>";	
		}					
	 }
	 
		
	 function ultimo_rif(){
		 
		 if(isset($_POST['instituciones'])){
		 
		$sql = "select *from institucion i  where i.rif_institucion ='".$_POST['instituciones']."'";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "$da[rif_institucion]";
            echo $html;
			}
    	}
		else{
			echo $html="";
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
        $sql = "select * from estado e, region_estado r where e.idestado=r.idestado and r.idregion=$_POST[v1] ORDER BY nombre_estado";
		echo "<select name='estado' id='estado' onchange='carga_mun()'>
              <option selected='selected' value=''>Seleccione</option>";
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
              <option selected='selected' value=''>Seleccione</option>";
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
              	<option selected='selected' value=''>Seleccione</option>";
		$a = mysql_query($sql) or die(mysql_error());
        while ($da = mysql_fetch_assoc($a)){
            $html="<option value='$da[cccc]'>".utf8_encode($da['nombre_central'])."</option>";
			echo $html;
				}
			echo "</select>";
			}
	
	function combo_dis_central(){
        $sql = "select cccc, disp_voz, disp_abangn, disp_aba, disp_metro, disp_atmfr from central c where c.cccc <> c.disp_voz AND c.cccc = '$_POST[v1]'";
		$a = mysql_query($sql) or die(mysql_error());
        while ($da = mysql_fetch_assoc($a)){
            $html = "<table border=1 cellspacing=1 cellpadding=2 style=font-size: 6pt>
						<tr>
							<td>Voz</td>
							<td>$da[disp_voz]</td>
						</tr>
						<tr>
							<td>ABA NGN</td>
							<td>$da[disp_abangn]</td>
						</tr>
						<tr>
							<td>ABA</td>
							<td>$da[disp_aba]</td>
						</tr>
						<tr>
							<td>Metro-ATM</td>
							<td>$da[disp_metro]</td>
						</tr>
					</table>";
            echo $html;
			}
    	}
	
	function combo_servicio(){
        $sql = "select * from servicio ORDER BY nombre_servicio";
		$a = mysql_query($sql) or die(mysql_error());
        while ($da = mysql_fetch_assoc($a)){
            $html = "<option value='$da[idservicio]'>$da[nombre_servicio]</option>";
            echo $html;
				}
			}

    function combo_tiposer(){
        $sql = "select * from servicio_tipo_servicio ts, tipo_servicio t where t.idtipo_servicio = ts.idtipo_servicio and ts.idservicio = $_POST[v1] ORDER BY nombre_tipo_servicio ";
        echo "<select name='tipo_servicio' id='tipo_servicio' onchange='cargar_subtipos()'>
              <option selected='selected'>Seleccione</option>";
		$a = mysql_query($sql) or die(mysql_error());
        while ($da = mysql_fetch_assoc($a)){
            $html = "<option value='$da[idtipo_servicio]'>$da[nombre_tipo_servicio]</option>";
            echo $html;
        		}
        	echo "</select>";
    	}
		

    function combo_subtiposer(){
        $sql = "select * from servicio_tipo_servicio_subtipo_servicio sts,subtipo_servicio st where st.idsubtipo_servicio = sts.idsubtipo_servicio and sts.idtipo_servicio = $_POST[v1] ORDER BY nombre_subtipo_servicio";
        echo "<select name='subtipo_servicio' id='subtipo_servicio' onchange='cargar_plan()'>
            <option selected='selected'>Seleccione</option>";
		echo $sql;
		$a = mysql_query($sql) or die(mysql_error());
        while ($da = mysql_fetch_assoc($a)){
            $html = "<option value='$da[idsubtipo_servicio]'>$da[nombre_subtipo_servicio]</option>";
            echo $html;
        		}
        	echo "</select>";
    	}

    function combo_plan(){
        $sql = "select * from servicio_tipo_servicio_subtipo_servicio_plan stsp, plan p where p.idplan = stsp.idplan and stsp.idtipo_servicio = $_POST[v2] and stsp.idsubtipo_servicio = $_POST[v3]";
        echo "<select name='plan' id='plan' onchange='cargar_etiqueta()'>
              <option selected='selected'>Seleccione</option>";
		$a = mysql_query($sql) or die(mysql_error());
        	while ($da = mysql_fetch_assoc($a)) {
            	$html = "<option value='$da[idplan]'>$da[nombre_plan]</option>";
            echo $html;
        		}
        	echo "</select>";
    	}
	
	function combo_trimestre() {
        $sql = "select * from trimestre";
		$a = mysql_query($sql) or die(mysql_error());
        while ($da = mysql_fetch_assoc($a)) {
            $html = "
                <option value='$da[idtrimestre]'>$da[nombre_trimestre]</option>
                ";
            echo $html;
			}
    	}
	}
?>