package siagsce.viewmodel.reportes.especiales;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
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
import siagsce.modelo.servicio.reportes.especiales.SListadoActividadesCumplidasEstudiante;
import siagsce.viewmodel.reportes.abiertos.ReportConfig;
import siagsce.viewmodel.reportes.abiertos.ReportType;
import siagsce.viewmodel.seguridad.SecurityUtil;
/**
 * ViewModel usado en la vista del reporte actividades culminadas por
 * los estudiantes en SCE
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteActividadesCumplidasEstudiante {
	
	/**
	 * Declaración de variables del viewModel
	 */
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private String ruta = "/WEB-INF/siagsce/reportes/especiales/ListadoActiviaesCulminadas.jasper";
	
	/**
	 * Declaración de los componentes de la vista
	 */
	@Wire
	private Button btnExportar;
	private Button btnCancelar;
	private Date fechaInicio;
	private Date fechaFin;
	private List<String> valores;
	private String seleccion;
	private String texto;
	@Wire
	private Textbox txtFiltro;
	@Wire
	Popup popOpciones;
	private Window win;
	private Profesor profesor;
	MensajesEmergentes mensajes=new MensajesEmergentes();
	/**	
     * Lista que permite llenar el combo para elegir el forma
     **/
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(
					new ReportType("Word (RTF)", "rtf"),  
					new ReportType("CSV","csv"), 
					new ReportType("OpenOffice (ODT)", "odt")));
	
	/**	
    * Declaración de los servicios
    **/
	// 
	@WireVariable
	SDireccionPrograma sdireccionPrograma;

	@WireVariable
	SProyecto sproyecto;
	
	@WireVariable
	SProfesor sprofesor;

	@WireVariable
	SActividad sactividad;
	
	@WireVariable
	SDirectorPrograma sdirector;
	
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;
	
	@WireVariable
	SCoordinadorSce scoordinacorSce;

	@WireVariable
	SListadoActividadesCumplidasEstudiante sListadoActividadesCumplidas;
	private List<Proyecto> proyectos= new ArrayList<Proyecto>();
	
	/**	
    *Declaración de los modelos a ser utilizados en los listados y combos
    **/
	private ListModelList<DireccionPrograma> modeloPrograma;
	private List<DireccionPrograma> programas;
	private ListModelList<Actividad> modeloActividad;
	private List<Actividad> actividades;
	private ListModelList<ListaActividadesCumplidasEstudiante> modeloActividadesCumplidasEstudiantes;
	private ListModelList<Proyecto> modeloProyecto;

	/**
	 * Método que inicializa los modelos
	 *  y carga el programa,proyecto,actividad desde la BD y los combos
	 * @param win,ventana la cual esta asociada a este viewmodel
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		 profesor = sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
		 programas=buscarDireccionesXRol();
		 modeloPrograma = new ListModelList<DireccionPrograma>(programas);	
		 List<Proyecto> proyectosDelParticipante=new ArrayList<Proyecto>();
		 actividades=new ArrayList<Actividad>();
		 modeloProyecto = new ListModelList<Proyecto>();
		 modeloActividad = new ListModelList<Actividad>();
		 modeloActividadesCumplidasEstudiantes = new ListModelList<ListaActividadesCumplidasEstudiante>();
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
	public List<DireccionPrograma>  buscarDireccionesRolResponsable(){
		List<DireccionPrograma> direcciones=new ArrayList<DireccionPrograma>();
		List<Proyecto> proyectos=sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
		for(Proyecto proyecto:proyectos){
			if(direcciones.isEmpty()){
				direcciones.addAll(proyecto.getDireccionPrograma());
			}else{
				verificarExistenciaDireccion(proyecto,direcciones);
			}
		}
		return direcciones;
		
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
	 * Método utilizado para inicializar los componentes gráficos
	 * de la vista .zul, en este caso coloca en marca de agua el txt del filtro
	 * de búsqueda en nombre.
	 * @param view,ventana la cual esta asociada a este viewmodel
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		txtFiltro.setPlaceholder("Nombre");

	}
	@Command
	public void seleccionFiltro() {
		if (seleccion == "Nombre") {
			popOpciones.close();
			txtFiltro.setPlaceholder("Nombre");
			
		} else {
			if (seleccion == "Cédula") {
				popOpciones.close();
				txtFiltro.setPlaceholder("Cédula");
				
			} else {
				if (seleccion == "Apellido") {
					popOpciones.close();
					txtFiltro.setPlaceholder("Apellido");
					
				}
			}
		}

	}

	/**	
    * Limpieza automatica
    **/ 
	public void limpiezaAutomatica() {
		selectedActividad = null;
		selectedProyecto = null;
		
		texto = "";

	}

	/**
	 * Método que carga la lista de estudiantes asignado a la Direccion de
	 * programa que se seleccione en el combo y le pasa al componete
	 * jasperReport lo necesariopara pintarse en la pantalla .zul
	 */
	@Command("showReport")
	@NotifyChange({ "reportConfig", "prueba" })
	public void showReport() {
		if (selectedPrograma == null && selectedProyecto == null) {
			Messagebox.show("Seleccione al menos un Criterio de Busqueda ",
					"Mensaje", Messagebox.OK, Messagebox.INFORMATION);
		} else {

			reportConfig = new ReportConfig(ruta);
			reportConfig.setType(reportType);
			reportConfig.setDataSource(new JRBeanCollectionDataSource(
					modeloActividadesCumplidasEstudiantes));
		}
	}

	/**	
    * Método que Carga en el combo de proyecto de acuerdo al programa que se
	* seleccione
    **/
	@NotifyChange({ "modeloProyecto" })
	@Command
	public void cargarProyecto() {
		limpiezaAutomatica();
		modeloActividad.clear();
		obtenerProyectosXProgramaYProfesor();
		fechaInicio=null;
		fechaFin=null;
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
	public void filtrarProyectosPorDireccion(ListModelList<Proyecto> proyectos,List<Proyecto> proyectosDireccion){
		boolean existe;
		int i=0,nroProyectos=proyectos.size();
		while(i<nroProyectos){
			existe=false;
			for(int j=0;j<proyectosDireccion.size();j++){
				if(proyectos.get(i).getProyectoCodigo().equals(proyectosDireccion.get(j).getProyectoCodigo())){
					existe=true;
				}
			}
			if(!existe){
				proyectos.remove(i);
				nroProyectos--;
				continue;
			}
			i++;
		}
	}
	
	/**	
    * Método que Carga en el combo de actividad de acuerdo al proyecto que se
	* seleccione
    **/
	@NotifyChange({ "modeloActividad" })
	@Command
	public void cargarActividad() {
		selectedActividad = null;
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
	

	/**
	 * Método que cierra la ventana
	 */
	@Command
	public void cerrar() {
		win.detach();
	}

	/**
	 * Método que cancela el proceso
	 **/
	@Command
	public void cancelar() {
		modeloActividad.clear();
		modeloProyecto.clear();	
		modeloActividadesCumplidasEstudiantes.clear();
		Datebox datebox = (Datebox) win.getFellow("dtbfechaFin");
		datebox.setValue(null);
		datebox = (Datebox) win.getFellow("dtbfechaIni");
		datebox.setValue(null);
		limpiezaAutomatica();
		Combobox ComboProgramass = (Combobox) win
				.getFellow("cmbPrograma");
		ComboProgramass.setSelectedIndex(-1);
		selectedPrograma = null;
		fechaFin = null;
		fechaInicio = null;
		btnExportar.setDisabled(true);
		txtFiltro.setPlaceholder("Nombre");
		win.getFellow("tab").getFellow("tabs").getFellow("pestana2")
		.setVisible(false);
		win.getFellow("tab").getFellow("tabs").getFellow("pestana1").setVisible(true);
		Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
		tab.setSelected(true);
		btnCancelar.setDisabled(true);
	}
	/**	
	    * Declaración de variables donde sera guardada las selecciones
	    **/
		private DireccionPrograma selectedPrograma;
		private Proyecto selectedProyecto;
		private Actividad selectedActividad;

		/**	
	    * Métodos Set y Get
	    **/
		public Popup getPopOpciones() {
			return popOpciones;
		}

		public void setPopOpciones(Popup popOpciones) {
			this.popOpciones = popOpciones;
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

		public ListModelList<DireccionPrograma> getModeloPrograma() {
			return modeloPrograma;
		}

		public void setModeloPrograma(
				ListModelList<DireccionPrograma> modeloPrograma) {
			this.modeloPrograma = modeloPrograma;
		}

		public List<DireccionPrograma> getProgramas() {
			return programas;
		}

		public void setProgramas(List<DireccionPrograma> programas) {
			this.programas = programas;
		}

		public ListModelList<Actividad> getModeloActividad() {
			return modeloActividad;
		}

		public void setModeloActividad(ListModelList<Actividad> modeloActividad) {
			this.modeloActividad = modeloActividad;
		}

		public List<Actividad> getActividad() {
			return actividades;
		}

		public void setActividad(List<Actividad> actividades) {
			this.actividades = actividades;
		}

		public ListModelList<ListaActividadesCumplidasEstudiante> getModeloActividadesCumplidasEstudiantes() {
			return modeloActividadesCumplidasEstudiantes;
		}

		public void setModeloActividadesCumplidasEstudiantes(
				ListModelList<ListaActividadesCumplidasEstudiante> modeloActividadesCumplidasEstudiantes) {
			this.modeloActividadesCumplidasEstudiantes = modeloActividadesCumplidasEstudiantes;
		}

		public ListModelList<Proyecto> getModeloProyecto() {
			return modeloProyecto;
		}

		public void setModeloProyecto(ListModelList<Proyecto> modeloProyecto) {
			this.modeloProyecto = modeloProyecto;
		}

		public List<Proyecto> getProyectos() {
			return proyectos;
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

		public void setProyectos(List<Proyecto> proyectos) {
			this.proyectos = proyectos;
		}

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

	

		/**
		 * Método que llena los datos de forma dinámica y filtro de búsqueda de los mismos.
		 */
		@Command("llenarListaDinamica")
		@NotifyChange({ "selectedPrograma", "selectedProyecto",
				"selectedActividad", "fechaInicio", "fechaFin" })
		public void llenarListaDinamica() {

			modeloActividadesCumplidasEstudiantes.clear();
			String condicion = "";
			/**	
		     *Selecciona El Programa
		     **/
			if (selectedPrograma != null) {
				btnExportar = (Button) win.getFellow("btnExportar");
				btnExportar.setDisabled(false);
				btnCancelar = (Button) win.getFellow("btnCancelar");
				btnCancelar.setDisabled(false);
				condicion = condicion
						+ "where proyectos_por_programa.direccion_codigo=" + "'"
						+ Integer.toString(selectedPrograma.getDireccionCodigo())
						+ "'";
			}
			/**	
		     * Selecciona El Proyecto
		     **/
			if (selectedProyecto != null) {
				condicion = condicion + " and proyecto.proyecto_codigo  =" + "'"
						+ selectedProyecto.getProyectoCodigo() + "'";
			}
			/**	
		     * Selecciona la Actividad
		     **/
			if (selectedActividad != null) {
				condicion = condicion + " and actividad.actividad_codigo  =" + "'"
						+ Integer.toString(selectedActividad.getActividadCodigo())
						+ "'";

			}

			/**	
		     * Establece Fecha de inicio y fin de la busqueda
		     **/
			if (fechaInicio != null && fechaFin != null) {
				if (fechaFin.compareTo(fechaInicio) < 0) {
					fechaFin = null;
					Messagebox
							.show("La fecha HASTA debe ser Mayor a la fecha DESDE");
				} else {
					condicion = condicion
							+ " and actividad_ejecutada.actividad_ejecutada_fecha BETWEEN "
							+ "'" + fechaInicio + "'" + " and " + "'" + fechaFin
							+ "'";
				}
			}

			else if (fechaInicio != null) {
				btnExportar = (Button) win.getFellow("btnExportar");
				btnExportar.setDisabled(false);
				condicion = condicion
						+ " and actividad_ejecutada.actividad_ejecutada_fecha>= "
						+ "'" + fechaInicio + "'";
			} else if (fechaFin != null) {
				btnExportar = (Button) win.getFellow("btnExportar");
				btnExportar.setDisabled(false);
				condicion = condicion
						+ " and actividad_ejecutada.actividad_ejecutada_fecha <="
						+ "'" + fechaFin + "'";
			}

			List<ListaActividadesCumplidasEstudiante> listaActividades = sListadoActividadesCumplidas
					.buscarActividadesCumplidas(condicion);
			if (listaActividades.size()==0){
				btnExportar.setDisabled(true);
				mensajes.errorCamposVacios();
				}
			if (seleccion == null || seleccion == "") {
				if (texto == ""  ||texto == null ){
					modeloActividadesCumplidasEstudiantes.addAll(listaActividades);}
				else {
					for (int i = 0; i < listaActividades.size(); i++) {
		
						if (listaActividades.get(i).getNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloActividadesCumplidasEstudiantes
									.add(listaActividades.get(i));

						}
						else{
							btnExportar.setDisabled(true);}
					}
				}

			} else {
				if (seleccion == "Cédula") {
					if (texto == ""  ||texto == null ){
						modeloActividadesCumplidasEstudiantes
								.addAll(listaActividades);}
					else {
				
						for (int i = 0; i < listaActividades.size(); i++) {
							if (listaActividades.get(i).getCedula()
									.contains(texto.toLowerCase())) {
								modeloActividadesCumplidasEstudiantes
										.add(listaActividades.get(i));

							}else{
								btnExportar.setDisabled(true);}
						}
					}
				} else {
					if (seleccion == "Nombre") {
						if (texto == ""  ||texto == null ){
							modeloActividadesCumplidasEstudiantes
									.addAll(listaActividades);}
						else {
							for (int i = 0; i < listaActividades.size(); i++) {
						
								if (listaActividades.get(i).getNombre()
										.toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloActividadesCumplidasEstudiantes
											.add(listaActividades.get(i));

								}else{
									btnExportar.setDisabled(true);}
							}
						}
					} else {
						if (seleccion == "Apellido") {
							if (texto == ""  ||texto == null ){
								modeloActividadesCumplidasEstudiantes
										.addAll(listaActividades);}
							else {
								
								for (int i = 0; i < listaActividades.size(); i++) {
									if (listaActividades.get(i).getApellido()
											.toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloActividadesCumplidasEstudiantes
												.add(listaActividades.get(i));

									}else{
										btnExportar.setDisabled(true);}
								}
							}
						}
					}
				}
			}

			win.getFellow("tab").getFellow("tabs").getFellow("pestana2")
					.setVisible(false);
			Tab tab = (Tab) win.getFellow("tab").getFellow("tabs")
					.getFellow("pestana1");
			tab.setSelected(true);
		}


}
