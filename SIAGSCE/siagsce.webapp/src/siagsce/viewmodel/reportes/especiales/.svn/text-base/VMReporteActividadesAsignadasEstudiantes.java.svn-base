package siagsce.viewmodel.reportes.especiales;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.zkoss.bind.annotation.AfterCompose; 
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Popup;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.reportes.especiales.ListaActividadesAsignadasEstudiantes;
import siagsce.modelo.data.reportes.especiales.ListaActividadesCumplidasEstudiante;
import siagsce.modelo.general.StatusCoordinadorSce;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusProyecto;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SCoordinadorSce;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.reportes.especiales.SListaActividadesAsignadasEstusdiantes;
import siagsce.modelo.servicio.reportes.especiales.SListadoActividadesCumplidasEstudiante;
import siagsce.viewmodel.reportes.abiertos.ReportConfig;
import siagsce.viewmodel.reportes.abiertos.ReportType;
import siagsce.viewmodel.seguridad.SecurityUtil;

/**
 * ViewModel para la vista que muestra los estudiantes 
 * a los cuales se les ha asignados actividades
 *  en Servicio Comunitario
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteActividadesAsignadasEstudiantes {

	/**	
	 *Declaración de las variables del viewModel
	 **/
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/especiales/ReporteActividadesAsignadasEstudiantes.jasper";
	private String proyecto_param;
	private List<String> valores;
	private String seleccion;
	private String texto;
	private String nombre;
	private String cedula;
	private String apellido;
	private String nombreEstudiante;
	private Date fechaInicio;
	private Date fechaFin;
	/**ESTO PARA FORMATO DE FECHA Y PARA OBTENER LA DEL SISTEMA*/
	private Date format;
	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	Date fechaActual = new Date();
	String fechaSistema = formateador.format(fechaActual);
	private Profesor profesor;
	
	/**	
	 *Declaración de los componentes de la Vista
	 **/
	@Wire
	private Window win;
	@Wire
	Combobox cmbPrograma;
	@Wire
	Combobox cmbProyecto;
	@Wire
	Combobox cmbActividad;
	@Wire
	private Datebox dtbInicio;
	@Wire
	private Datebox dtbFin;
	@Wire
	private Textbox txtFiltro;
	@Wire
	Popup popOpciones;
	@Wire
	private Button btnExportar;
	@Wire
	private Button btnCancelar;

	MensajesEmergentes mensajeEmergente = new MensajesEmergentes();


	/**	
	 *Lista que permite llenar el combo para elegir el tipo de formato del archivo del reporte a generar 
	 **/		
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(
					new ReportType("Pdf", "pdf"),
					new ReportType("Word (RTF)", "rtf"), 
					new ReportType("CSV", "csv"), 
					new ReportType("OpenOffice (ODT)", "odt")));

	/**	
	 *Declaraciòn de los servicios del viewModel

	 **/
	@WireVariable
	SDireccionPrograma sdireccionPrograma;
	@WireVariable
	SProyecto sproyecto;
	@WireVariable
	SActividad sactividad;
	@WireVariable
	SListaActividadesAsignadasEstusdiantes sActividadesAsignadasEstudiantes;
	@WireVariable
	SDirectorPrograma sdirector;
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	SCoordinadorSce scoordinacorSce;

	private List<Proyecto> proyectos= new ArrayList<Proyecto>();
	

	/**
	 * Declaraciòn de los modelos a ser utilizados en los listados y combos
	 *		
	 **/
	private ListModelList<DireccionPrograma> modeloPrograma;
	private List<DireccionPrograma> programas;
	private ListModelList<Proyecto> modeloProyecto;
	private List<Proyecto> proyecto;
	private ListModelList<Actividad> modeloActividad;
	private List<Actividad> actividades;
	private ListModelList<ListaActividadesAsignadasEstudiantes> modeloEstudiantesActividadesAsignadas;
	private List<ListaActividadesAsignadasEstudiantes> listEstudiantesActividadesAsignadas;

	/**		
	 *Declaraciòn de variables donde sera guardada las selecciones de los combos
	 *
	 **/
	private DireccionPrograma selectedPrograma;
	private Proyecto selectedProyecto;
	private Actividad selectedActividad;

	
	/**
	 * Método que inicializa los modelos,carga el programa desde la BD 
	 * y llena los combos respectivamente
	 * @param win, ventana  a la cual esta asociada a este viewmodel
	 * 
	 */    
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		 profesor = sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
		 programas = buscarDireccionesXRol();
		 modeloPrograma = new ListModelList<DireccionPrograma>(programas);	
		 List<Proyecto> proyectosDelParticipante=new ArrayList<Proyecto>();
		 actividades=new ArrayList<Actividad>();
		 modeloActividad = new ListModelList<Actividad>();
		 modeloProyecto = new ListModelList<Proyecto>();
		 modeloEstudiantesActividadesAsignadas = new ListModelList<ListaActividadesAsignadasEstudiantes>();
		 if(SecurityUtil.verificarRol("Coordinacion SCE")){
			proyectos = sproyecto.buscarPorStatus(StatusProyecto.Activo.toString());
			actividades = sactividad.buscarTodo();			
		 }else{
		 proyectos = sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
		 actividades = sactividad.participanteOtroProyecto(profesor);
		 insertarActividadesResponsableProyecto(actividades,proyectos);
		 
		 proyectosDelParticipante=sactividad.buscarProyectosDeUnProfesorParticipante(profesor);
		 insertarproyectosParticipante(proyectos,proyectosDelParticipante);
		 }
		modeloEstudiantesActividadesAsignadas = new ListModelList<ListaActividadesAsignadasEstudiantes>();
		
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");	
	}
	
	/**
	 * Método que aplica la seguridad funcional en el reporte
	 * para buscar las direcciones de programa dado el rol que posea el usuario.
	 * @return lista de direccion de programa
	 **/
	

	public List<DireccionPrograma> buscarDireccionesXRol(){
		List<DireccionPrograma>programas=new ArrayList<DireccionPrograma>();
		String cedula= SecurityUtil.nombreUsuario;
		Profesor profesor=sprofesor.buscarPorCedula(cedula);
		if(SecurityUtil.verificarRol("Coordinacion SCE")){
			programas=sdireccionPrograma.buscarTodo();
		}
		else{
			if(SecurityUtil.verificarRol("Director")){
				DirectorPrograma director= sdirector.buscarPorProfesorYEstatus(profesor, StatusDirectorPrograma.Activo.toString());
				programas.add(director.getDireccionPrograma());
			}
				
			if(SecurityUtil.verificarRol("Representante Profesoral")){
				RepresentanteProfesoral repre=smiembroCoordinacion.buscarRepresentantePorCedula(profesor, StatusRepresentanteProfesoral.Activo.toString());
				programas.add(repre.getDireccionProgramam());
				}
			if(SecurityUtil.verificarRol("Responsable Proyecto")){
				Profesor resp=sproyecto.buscarResponsableExit(profesor);
				List<Proyecto>proyectoR=sproyecto.buscarProyectosdelProfesorResponsable(resp);
				for(Proyecto proyecto:proyectoR){
					programas.addAll(proyecto.getDireccionPrograma());
				}
			}
			if(SecurityUtil.verificarRol("Participante Actividad")){
				Profesor part=sactividad.buscarParticipanteEx(profesor);
				List<Proyecto>proyectoP=sactividad.buscarProyectosDeUnProfesorParticipante(part);
				for(Proyecto proyectoA:proyectoP){
					programas.addAll(proyectoA.getDireccionPrograma());
				}
			}
		} 
		programas=eliminarRepetidos(programas);
		return programas;
	}
	/**
	 * elimina los repetidos de la lista de direcciones de programa
	 * @param lista a la cual se le eliminara las duplicidades
	 * @return lista filtrada 
	 * */
	public List<DireccionPrograma>eliminarRepetidos(List<DireccionPrograma>aux){
		List<DireccionPrograma>auxprogramas=new ArrayList<DireccionPrograma>();
		for(DireccionPrograma dir:aux){
			if(!(auxprogramas.contains(dir))){
				auxprogramas.add(dir);
			}
		}
		return auxprogramas;
	}
	
	public void insertarActividadesResponsableProyecto(List<Actividad> actividades,List<Proyecto> proyectosResponsable){
		boolean existe;
		for(Proyecto proyectoResponsable:proyectosResponsable){
			List<Actividad> listActividades=sactividad.buscarporProyecto(proyectoResponsable);
			for(int j=0;j<listActividades.size();j++){
				existe=false;
			for(int i=0;i<actividades.size();i++){
				if(listActividades.get(j).getActividadCodigo().compareTo(actividades.get(i).getActividadCodigo())==0){
					existe=true;
				}
			}
			if(!existe)
				actividades.add(listActividades.get(j));
		  }
		}
	}
	public void insertarproyectosParticipante(List<Proyecto> proyectos,List<Proyecto> proyectosDelParticipante){
		boolean existe;
		for(Proyecto proyectoParticipante:proyectosDelParticipante){
			existe=false;
			for(int i=0;i<proyectos.size();i++){
				if(proyectos.get(i).getProyectoCodigo().equals(proyectoParticipante.getProyectoCodigo())){
					existe=true;
				}
			}
			if(!existe)
				proyectos.add(proyectoParticipante);
		}
	}
	public List<DireccionPrograma>  buscarDireccionesRolParticipante(List<DireccionPrograma> direcciones){
		List<Proyecto> proyectos=sactividad.buscarProyectosDeUnProfesorParticipante(profesor);
		for(Proyecto proyecto:proyectos){
			if(direcciones.isEmpty()){
				direcciones.addAll(proyecto.getDireccionPrograma());
			}else{
				verificarExistenciaDireccion(proyecto,direcciones);
			}
		}
		return direcciones;
		
	}
	public void verificarExistenciaDireccion(Proyecto proyecto,List<DireccionPrograma> direcciones){
		boolean existe;
		for(DireccionPrograma direccionProyecto:proyecto.getDireccionPrograma()){
			existe=false;
			for(int i=0;i<direcciones.size();i++){
				if(direcciones.get(i).getDireccionCodigo().compareTo(direccionProyecto.getDireccionCodigo())==0){
					existe=true;
				}
			}
			if(!existe)
				direcciones.add(direccionProyecto);
		}
	}
	
	/**
	 * Método que llena los datos de forma dinámica de acuerdo a 
	 * la selección del criterio de los criterios de búsqueda.
	 */
	@Command("llenarListaDinamica")
	@NotifyChange({"selectedPrograma", "selectedProyecto", "selectedActividad", "fechaInicio", "fechaFin"})
	public void llenarListaDinamica() {

		modeloEstudiantesActividadesAsignadas.clear();
		String condicion=" where dp.direccion_codigo = e.direccion_codigo and p.proyecto_codigo = a.proyecto_codigo and a.actividad_codigo = aa.actividad_codigo and pp.estudiante_cedula = e.estudiante_cedula and pp.proyecto_codigo = p.proyecto_codigo and pp.preinscripcion_proyecto_codigo = ip.preinscripcion_proyecto_codigo and ip.proyecto_codigo = a.proyecto_codigo ";
		/**
		 * Guarda la Selección que haga el usuario en la variable codigoDireccionPrograma
		 */
		if(selectedPrograma!=null){        
			condicion=condicion+" and e.direccion_codigo="+ "'"+Integer.toString(selectedPrograma.getDireccionCodigo())+ "' ";
			desbloquear(); 
		}

		/**
		 * Guarda la Selección que haga el usuario en la variable codigoProyecto
		 */
		if(selectedProyecto!=null )
		{
			condicion=condicion+" and  ip.proyecto_codigo="+ "'"+(selectedProyecto.getProyectoCodigo())+"' "; 
			desbloquear();
		}
		/**
		 * Guarda la Selección que haga el usuario en la variable codigoActividad
		 */
		if(selectedActividad!=null )
		{
			condicion=condicion+" and  aa.actividad_codigo="+ "'"+Integer.toString(selectedActividad.getActividadCodigo())+"' "; 
			desbloquear();
		}

		/**Guarda la Selección que haga el usuario en las variable fechaInicio y fechaFin        
		 * 
		 */
		if(selectedActividad!=null && fechaInicio!=null && fechaFin!=null)
		{
			/**
			 * VALIDACION LA FECHA INICIAL NO PUEDE SER MAYOR A LA FINAL
			 */
			if(fechaFin.after(fechaInicio))
			{	
				/**
				 * VALIDACION LA FECHA INICIAL NO PUEDE SER MAYOR A LA ACTUAL
				 */
				if(fechaInicio.before(fechaActual)){
					condicion=condicion+" and aa.actividad_asignada_fecha_inicio BETWEEN "+"'"+fechaInicio+"'"+" and "+"'"+fechaFin+"'";
					desbloquear();
				}
				else
				{
					Messagebox.show("La Fecha Inicio no puede ser mayor a la Fecha Actual", "Mensaje",
							Messagebox.OK, Messagebox.INFORMATION);
					        bloquear();
				}
				
				/**
				 * VALIDACION LA FECHA FINAL NO PUEDE SER MAYOR A LA ACTUAL
				 */
				if(fechaFin.before(fechaActual)){
					condicion=condicion+" and aa.actividad_asignada_fecha_inicio BETWEEN "+"'"+fechaInicio+"'"+" and "+"'"+fechaFin+"'";
					desbloquear();
				}
				else
				{
					Messagebox.show("La Fecha final no puede ser mayor a la Fecha Actual", "Mensaje",
							Messagebox.OK, Messagebox.INFORMATION);
                            bloquear();
				}
			}
			else
			{
				MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
				mensajeEmergente.advertenciaValorFechas();
				btnExportar.setDisabled(true);
			}

		}

		/**
		 * Guarda la Selección que haga el usuario del combo estatico de EstudianteIncritoPorTaller.zul
		 */
		listEstudiantesActividadesAsignadas = sActividadesAsignadasEstudiantes.buscarEstudiantesporActividadesAsignadas(condicion);
		if(!listEstudiantesActividadesAsignadas.isEmpty()){
			modeloEstudiantesActividadesAsignadas.addAll(listEstudiantesActividadesAsignadas);
			win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
			Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
			tab.setSelected(true);
			desbloquear();
		}else{
			MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
			mensajeEmergente.errorCamposVacios();
			btnExportar.setDisabled(true);
			btnCancelar.setDisabled(true);
		}
	}


	/**
	 * Método que vincula elementos de la interfaz gráfica con este ViewModel,
	 * inhabilita los botones exportar y cancelar,
	 * coloca al inicio marca de agua en el filtro nombre
	 * @param view, vista cuyos elementos se van a vincular a este ViewModel.
	 */	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){

		this.win = (Window) win;
		Selectors.wireComponents(view, this, false);
		txtFiltro.setPlaceholder("Nombre");
		btnExportar.setDisabled(true); 
		btnCancelar.setDisabled(true);

	}


	/**			
	 *Método que carga el reporte			
	 **/	
	@Command("showReport")
	@NotifyChange({"reportConfig","prueba"})
	public void showReport() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if(selectedPrograma==null && selectedProyecto==null && selectedActividad==null){
			mensajeEmergente.advertenciaCriterioBusqueda();
		}else{
			reportConfig = new ReportConfig(ruta);
			if(selectedProyecto!=null){
				reportConfig.getParameters().put("proyecto", selectedProyecto.getProyectoNombre());
			}else{
				reportConfig.getParameters().put("proyecto", " ");
			}
			if(selectedActividad!=null){
				reportConfig.getParameters().put("actividad", selectedActividad.getActividadNombre());
				reportConfig.getParameters().put("actividad", selectedActividad.getActividadNombre());
			}else{
				reportConfig.getParameters().put("actividad", " ");
			}
			reportConfig.setType(reportType);
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloEstudiantesActividadesAsignadas));
			btnExportar.setDisabled(true);
		}
	}

	/**			
	 *Método que carga los proyectos en el combo de
	 *acuerdo a la direcciòn de programa que se seleccione			
	 **/
	@NotifyChange({ "modeloProyecto","selectedProyecto","selectedActividad"})
	@Command
	public void cargarProyecto() {
		modeloActividad.clear();
		obtenerProyectosXProgramaYProfesor();
		selectedProyecto=null;
		selectedActividad=null;
		llenarListaDinamica();
	}
	@Command
	public void obtenerProyectosXProgramaYProfesor(){
		modeloProyecto.clear();
		List<Proyecto>proyectos=new ArrayList<Proyecto>();
		String cedula= SecurityUtil.nombreUsuario;
		Profesor profesor=sprofesor.buscarPorCedula(cedula);
		RepresentanteProfesoral repre=smiembroCoordinacion.buscarRepresentantePorProgramaEstatus(profesor, selectedPrograma, StatusRepresentanteProfesoral.Activo.toString());
		DirectorPrograma director= sdirector.buscarDirectorProgramaEstatus(profesor, selectedPrograma, StatusDirectorPrograma.Activo.toString());
		CoordinadorSce coord= scoordinacorSce.buscarPorProfesorYEstatus(profesor, StatusCoordinadorSce.Activo.toString());
		Profesor resp=sproyecto.buscarResponsableExit(profesor);
		Profesor part=sactividad.buscarParticipanteEx(profesor);
		if(coord!=null||repre!=null||director!=null){
			System.out.println("cualquiera");
			proyectos=sproyecto.buscarProyectoNoInactivosPrograma(selectedPrograma);
			System.out.println("cualquiera2");
		}
		else{
			if(resp!=null){
				System.out.println("responsable");
				List<Proyecto>proyectoR=sproyecto.buscarProyectoResponsablePrograma(resp, selectedPrograma);
				proyectos.addAll(proyectoR);
				System.out.println("responsable2");
				
			}
			if(part!=null){
				List<Proyecto>proyectoP=sactividad.findProyectosNoInactivosDeUnProfesorParticipante(part);
				proyectos.addAll(proyectoP);
			}
		}
		
		 proyectos=eliminarRepetidosProyecto(proyectos);
		 modeloProyecto.addAll(proyectos);
	}


	public List<Proyecto>eliminarRepetidosProyecto(List<Proyecto>aux){
		List<Proyecto>auxproyecto=new ArrayList<Proyecto>();
		for(Proyecto proyecto:aux){
			if(!(auxproyecto.contains(proyecto)))
				auxproyecto.add(proyecto);
		}
		return auxproyecto;
	}
	public void filtrarActividadesPorDireccion(ListModelList<Actividad> actividades,DireccionPrograma direccionPrograma){
		boolean existe;
		int i=0,nroActividades=actividades.size();
		while(i<nroActividades){
			existe=false;
			List<DireccionPrograma> direcciones=sproyecto.buscarProyectoPorActividad(actividades.get(i)).getDireccionPrograma();
			for(int j=0;j<direcciones.size();j++){
				if(direcciones.get(j).getDireccionCodigo().compareTo(direccionPrograma.getDireccionCodigo())==0){
					existe=true;
				}
			}
			if(!existe){
				actividades.remove(i);
				nroActividades--;
				continue;
			}
			i++;
		}
	}

	
	/** Mètodo que Carga en el combo de actividad de 
	 * acuerdo al proyecto que se seleccione
	 */
	@NotifyChange({ "modeloActividad" })
	@Command
	public void cargarActividad() {
		modeloActividad.clear();
		modeloActividad.addAll(buscarActividadesXRol());
		llenarListaDinamica();
	}

	/**
	 * Método que aplica la seguridad funcional en el reporte
	 * para buscar las actividades dado el rol que posea el usuario.
	 * @return lista de activi de programa
	 **/
	public List<Actividad> buscarActividadesXRol(){
		List<Actividad> actividades=new ArrayList<Actividad>();
		String cedula= SecurityUtil.nombreUsuario;
		Profesor profesor=sprofesor.buscarPorCedula(cedula);	
		Profesor resp=sproyecto.buscarResponsableProyecto(profesor,selectedProyecto.getProyectoCodigo());
		if(SecurityUtil.verificarRol("Coordinacion SCE") || SecurityUtil.verificarRol("Director") || SecurityUtil.verificarRol("Representante Profesoral")){
			actividades=sactividad.buscarporProyecto(selectedProyecto);
		}
		else{
			if(resp!=null){
				actividades=sactividad.buscarporProyecto(selectedProyecto);
			}else if(SecurityUtil.verificarRol("Participante Actividad")){
				actividades=sactividad.buscarActividadesDeUnProfesorParticipanteDeUnProyecto(profesor,selectedProyecto);
			}
		} 
		return actividades;
	}
	

	
	/** Mètodo que Cierra la ventana
	 */
	@Command
	public void cerrar() {
		win.detach();
	}


	/** Mètodo que limpia los combos en el .zul
	 */				
	@Command
	public void limpiarPrograma() {
		modeloPrograma.clear();
	}
	@Command
	public void limpiarProyecto() {
		modeloProyecto.clear();
	}
	@Command
	public void limpiarActividad() {
		modeloActividad.clear();
	}

	@Command
	public void limpiarmodelo() {
		modeloEstudiantesActividadesAsignadas.clear();
	}


	/** Mètodo que Cancela el proceso
	 */		
	@Command
	@NotifyChange({"cmbPrograma","cmbProyecto","cmbActividad","dtbInicio","dtbFin"})
	public void cancelar() {
		cedula ="";
		nombreEstudiante="";
		apellido ="";
		limpiarmodelo();
		cmbPrograma.setText("");
		if(cmbProyecto.getSelectedItem()!=null){
			cmbProyecto.setText("");
		}
		if(cmbActividad.getSelectedItem()!=null){
			cmbActividad.setText("");
		}
		
		if(dtbInicio.getText()!=""){
			dtbInicio.setText("");
		}
		if(dtbFin.getText()!=""){
			dtbFin.setText("");
		}
		win.getFellow("tab").getFellow("tabs").getFellow("pestana1").setVisible(true);
		Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
		tab.setSelected(true);
		btnExportar.setDisabled(true);
	}

	/**
	 * Método que desbloquea los botones
	 */	
	@Command
	public void desbloquear() {
		btnExportar.setDisabled(false);
		btnCancelar.setDisabled(false);
	}
	
	@Command
	public void bloquear() {
		btnExportar.setDisabled(true);
		btnCancelar.setDisabled(true);
	}

	/**
	 * Métodos que filtra los datos del estudiante por 
	 * cedula, nombre y apellido
	 */			
	@Command
	@NotifyChange({ "modeloEstudiantesActividadesAsignadas" })
	public void filtrarEstudiante() {
		try {
			List<ListaActividadesAsignadasEstudiantes> aux = new ArrayList<ListaActividadesAsignadasEstudiantes>();
			aux = listEstudiantesActividadesAsignadas;
			modeloEstudiantesActividadesAsignadas.clear();

			if (seleccion == null || seleccion == "") {
				if (nombre == "")
					modeloEstudiantesActividadesAsignadas.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteNombre().toLowerCase()
								.contains(nombre.toLowerCase())) {
							modeloEstudiantesActividadesAsignadas.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Cédula") {
					if (nombre == "")
						modeloEstudiantesActividadesAsignadas.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getEstudianteCedula().toLowerCase()
									.contains(nombre.toLowerCase())) {
								modeloEstudiantesActividadesAsignadas.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Nombre") {
						if (nombre == "")
							modeloEstudiantesActividadesAsignadas.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getEstudianteNombre().toLowerCase()
										.contains(nombre.toLowerCase())) {
									modeloEstudiantesActividadesAsignadas.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Apellido") {
							if (nombre == "")
								modeloEstudiantesActividadesAsignadas.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getEstudianteApellido()
											.toLowerCase().contains(nombre.toLowerCase())) {
										modeloEstudiantesActividadesAsignadas.add(aux.get(i));

									}
								}
							}
						}
					}
				}
			}
		} catch (NullPointerException e) {

		}
	}

	@Command
	public void seleccionFiltro() {
		System.out.print(seleccion);
		if (seleccion == "Nombre") {
			txtFiltro.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltro.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido")
					txtFiltro.setPlaceholder("Apellido");
				popOpciones.close();
			}
		}

	}		
	
	/**		
	 *Mètodos set y get de las variables del viewModel
	 *
	 **/
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getProyecto_param() {
		return proyecto_param;
	}

	public void setProyecto_param(String proyecto_param) {
		this.proyecto_param = proyecto_param;
	}


	public List<String> getValores() {
		return valores;
	}

	public void setValores(List<String> valores) {
		this.valores = valores;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public SimpleDateFormat getFormat() {
		return formateador;
	}

	public void setFormat(SimpleDateFormat formateador) {
		this.formateador = formateador;
	}

	public Window getWin() {
		return win;
	}

	public void setWin(Window win) {
		this.win = win;
	}

	public Combobox getCmbPrograma() {
		return cmbPrograma;
	}

	public void setCmbPrograma(Combobox cmbPrograma) {
		this.cmbPrograma = cmbPrograma;
	}

	public Combobox getCmbProyecto() {
		return cmbProyecto;
	}

	public void setCmbProyecto(Combobox cmbProyecto) {
		this.cmbProyecto = cmbProyecto;
	}

	public Combobox getCmbActividad() {
		return cmbActividad;
	}

	public void setCmbActividad(Combobox cmbActividad) {
		this.cmbActividad = cmbActividad;
	}
    
	public Datebox getDtbInicio() {
		return dtbInicio;
	}

	public void setDtbInicio(Datebox dtbInicio) {
		this.dtbInicio = dtbInicio;
	}

	public Datebox getDtbFin() {
		return dtbFin;
	}

	public void setDtbFin(Datebox dtbFin) {
		this.dtbFin = dtbFin;
	}

	public Popup getPopOpciones() {
		return popOpciones;
	}

	public void setPopOpciones(Popup popOpciones) {
		this.popOpciones = popOpciones;
	}

	public Button getBtnExportar() {
		return btnExportar;
	}

	public void setBtnExportar(Button btnExportar) {
		this.btnExportar = btnExportar;
	}

	public Textbox getTxtFiltro() {
		return txtFiltro;
	}

	public void setTxtFiltro(Textbox txtFiltro) {
		this.txtFiltro = txtFiltro;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public MensajesEmergentes getMensajeEmergente() {
		return mensajeEmergente;
	}

	public void setMensajeEmergente(MensajesEmergentes mensajeEmergente) {
		this.mensajeEmergente = mensajeEmergente;
	}


	public void setsActividadesAsignadasEstudiantes(
			SListaActividadesAsignadasEstusdiantes sActividadesAsignadasEstudiantes) {
		this.sActividadesAsignadasEstudiantes = sActividadesAsignadasEstudiantes;
	}

	public ListModelList<DireccionPrograma> getModeloPrograma() {
		return modeloPrograma;
	}

	public void setModeloPrograma(ListModelList<DireccionPrograma> modeloPrograma) {
		this.modeloPrograma = modeloPrograma;
	}

	public List<DireccionPrograma> getProgramas() {
		return programas;
	}

	public void setPrograma(List<DireccionPrograma> programa) {
		this.programas = programas;
	}

	public ListModelList<Proyecto> getModeloProyecto() {
		return modeloProyecto;
	}

	public void setModeloProyecto(ListModelList<Proyecto> modeloProyecto) {
		this.modeloProyecto = modeloProyecto;
	}

	public List<Proyecto> getProyecto() {
		return proyecto;
	}

	public void setProyecto(List<Proyecto> proyecto) {
		this.proyecto = proyecto;
	}

	public ListModelList<Actividad> getModeloActividad() {
		return modeloActividad;
	}

	public void setModeloActividad(ListModelList<Actividad> modeloActividad) {
		this.modeloActividad = modeloActividad;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public ListModelList<ListaActividadesAsignadasEstudiantes> getModeloEstudiantesActividadesAsignadas() {
		return modeloEstudiantesActividadesAsignadas;
	}

	public void setModeloEstudiantesActividadesAsignadas(
			ListModelList<ListaActividadesAsignadasEstudiantes> modeloEstudiantesActividadesAsignadas) {
		this.modeloEstudiantesActividadesAsignadas = modeloEstudiantesActividadesAsignadas;
	}

	public List<ListaActividadesAsignadasEstudiantes> getListEstudiantesActividadesAsignadas() {
		return listEstudiantesActividadesAsignadas;
	}

	public void setListEstudiantesActividadesAsignadas(
			List<ListaActividadesAsignadasEstudiantes> listEstudiantesActividadesAsignadas) {
		this.listEstudiantesActividadesAsignadas = listEstudiantesActividadesAsignadas;
	}

	public DireccionPrograma getSelectedPrograma() {
		return selectedPrograma;
	}

	public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
		this.selectedPrograma = selectedPrograma;
	}

	public Proyecto getSelectedProyecto() {
		return selectedProyecto;
	}

	public void setSelectedProyecto(Proyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}

	public Actividad getSelectedActividad() {
		return selectedActividad;
	}

	public void setSelectedActividad(Actividad selectedActividad) {
		this.selectedActividad = selectedActividad;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setReportConfig(ReportConfig reportConfig) {
		this.reportConfig = reportConfig;
	}

	public void setReportTypesModel(ListModelList<ReportType> reportTypesModel) {
		this.reportTypesModel = reportTypesModel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** 
	 * Métodos set y get que permite manipular la vista 
	 * ReporteActividadesAsignadasEstudiantes.zul
	 * */
	 public ListModelList<ReportType> getReportTypesModel() {
		return reportTypesModel;
	}

	public ReportConfig getReportConfig() {
		return reportConfig;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
	


}
