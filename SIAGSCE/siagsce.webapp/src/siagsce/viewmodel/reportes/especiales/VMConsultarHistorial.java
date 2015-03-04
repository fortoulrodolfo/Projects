package siagsce.viewmodel.reportes.especiales;

import java.util.HashMap;
import siagsce.viewmodel.reportes.abiertos.ReportConfig;
import siagsce.viewmodel.reportes.abiertos.ReportType;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Window;


import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.Proyecto;

import siagsce.modelo.data.reportes.especiales.ActividadesEstudiante;
import siagsce.modelo.data.reportes.especiales.EstatusEstudiantePorCarreraEstatus;

import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;

import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SInscripcionTaller;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.reportes.especiales.SActividadesEstudianteSCE;
import siagsce.modelo.servicio.reportes.especiales.SEstadoEstudiantesPorCarreraEstatus;
import siagsce.modelo.servicio.transacciones.SActividadAsignada;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.modelo.servicio.transacciones.SPreInscripcionProyecto;

/**
 * ViewModel que recibe los parámetros de la vista principal de la Consulta del
 * Historial de los estudiantes en SCE.
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConsultarHistorial {


	/**
	 * Declaración de variables del ViewModel
	 */
	private List<ActividadesEstudiante> listaActividadesAsignadas;

	private List<ActividadesEstudiante> listaActividadesEjecutadas;

	private String estudianteCedula;

	private String estudianteNombre;

	private String estudianteApellido;

	private String estudiantePrograma;

	private Integer programaCodigo;

	private String estudianteStatus;

	private Long estudianteHorasAcum;

	private List<InscripcionTaller> listaTaller;

	private ListModelList<InscripcionTaller> modeloTaller;

	private List<PreInscripcionProyecto> listaPreInscripcion;

	private ListModelList<PreInscripcionProyecto> modeloPreInscripcion;

	private List<InscripcionProyecto> listaProyecto;

	private ListModelList<ActividadesEstudiante> modeloActividadesAsignadas;

	private ListModelList<ActividadesEstudiante> modeloActividadesEjecutadas;

	private ListModelList<InscripcionProyecto> modeloProyecto;
	
	private ListModelList<EstatusEstudiantePorCarreraEstatus> modeloEstudiante;

	private List<EstatusEstudiantePorCarreraEstatus> estudianteAvance;

	private InscripcionProyecto selectedProyecto;

	private Proyecto proyecto;

	private String proyectoNombre;

	private String proyectoDescripcion;

	private Estudiante selectedEstudiante;

	private DireccionPrograma estudiante;

	private String nombreTaller;

	/**
	 * Declaración de Componentes de la vista .zul
	 */
	@Wire
	private Window win;
	
	@Wire
	private Button btnExportar;

	private @WireVariable
	SProyecto sproyecto;

	private @WireVariable
	SEstudiante sestudiante;

	private @WireVariable
	SInscripcionTaller sinscripciontaller;

	private @WireVariable
	SPreInscripcionProyecto spreinscripcionproyecto;

	private @WireVariable
	SInscripcionProyecto sinscripcionProyecto;

	private @WireVariable
	SActividadAsignada sactividadAsignada;

	private @WireVariable
	SActividadesEstudianteSCE sActividadesEstudianteSCE;

    @WireVariable
	SEstadoEstudiantesPorCarreraEstatus sEstadoEstudiantesPorCarreraEstatus;
    
	private String ruta="/WEB-INF/siagsce/reportes/especiales/ReporteConsultarHistorial.jasper";

	ReportType reportType = new ReportType("PDF", "pdf");


	/**
	 * Métodos set y get
	 *
	 */
	public String getRuta() {
		return ruta;
	}


	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public ReportType getReportType() {
		return reportType;
	}


	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}


	public ReportConfig getReportConfig() {
		return reportConfig;
	}


	public void setReportConfig(ReportConfig reportConfig) {
		this.reportConfig = reportConfig;
	}
	private ReportConfig reportConfig = null;


	public Proyecto getProyecto() {
		return proyecto;
	}


	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}


	public String getProyectoNombre() {
		return proyectoNombre;
	}


	public void setProyectoNombre(String proyectoNombre) {
		this.proyectoNombre = proyectoNombre;
	}



	public String getProyectoDescripcion() {
		return proyectoDescripcion;
	}


	public void setProyectoDescripcion(String proyectoDescripcion) {
		this.proyectoDescripcion = proyectoDescripcion;
	}


	public List<ActividadesEstudiante> getListaActividadesAsignadas() {
		return listaActividadesAsignadas;
	}


	public void setListaActividadesAsignadas(
			List<ActividadesEstudiante> listaActividadesAsignadas) {
		this.listaActividadesAsignadas = listaActividadesAsignadas;
	}


	public ListModelList<ActividadesEstudiante> getModeloActividadesAsignadas() {
		return modeloActividadesAsignadas;
	}


	public void setModeloActividadesAsignadas(
			ListModelList<ActividadesEstudiante> modeloActividadesAsignadas) {
		this.modeloActividadesAsignadas = modeloActividadesAsignadas;
	}

	public List<ActividadesEstudiante> getListaActividadesEjecutadas() {
		return listaActividadesEjecutadas;
	}


	public void setListaActividadesEjecutadas(
			List<ActividadesEstudiante> listaActividadesEjecutadas) {
		this.listaActividadesEjecutadas = listaActividadesEjecutadas;
	}


	public ListModelList<ActividadesEstudiante> getModeloActividadesEjecutadas() {
		return modeloActividadesEjecutadas;
	}


	public void setModeloActividadesEjecutadas(
			ListModelList<ActividadesEstudiante> modeloActividadesEjecutadas) {
		this.modeloActividadesEjecutadas = modeloActividadesEjecutadas;
	}


	public List<EstatusEstudiantePorCarreraEstatus> getEstudianteAvance() {
		return estudianteAvance;
	}


	public void setEstudianteAvance(
			List<EstatusEstudiantePorCarreraEstatus> estudianteAvance) {
		this.estudianteAvance = estudianteAvance;
	}


	public ListModelList<EstatusEstudiantePorCarreraEstatus> getModeloEstudiante() {
		return modeloEstudiante;
	}


	public void setModeloEstudiante(
			ListModelList<EstatusEstudiantePorCarreraEstatus> modeloEstudiante) {
		this.modeloEstudiante = modeloEstudiante;
	}


	public DireccionPrograma getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(DireccionPrograma estudiante) {
		this.estudiante = estudiante;
	}



	public Integer getProgramaCodigo() {
		return programaCodigo;
	}


	public void setProgramaCodigo(Integer programaCodigo) {
		this.programaCodigo = programaCodigo;
	}

	
	public Button getBtnExportar() {
		return btnExportar;
	}


	public void setBtnExportar(Button btnExportar) {
		this.btnExportar = btnExportar;
	}
	
	public String getEstudianteCedula() {
		return estudianteCedula;
	}


	public void setEstudianteCedula(String estudianteCedula) {
		this.estudianteCedula = estudianteCedula;
	}


	public String getEstudianteNombre() {
		return estudianteNombre;
	}


	public void setEstudianteNombre(String estudianteNombre) {
		this.estudianteNombre = estudianteNombre;
	}


	public String getEstudianteApellido() {
		return estudianteApellido;
	}

	public void setEstudianteApellido(String estudianteApellido) {
		this.estudianteApellido = estudianteApellido;
	}


	public String getEstudiantePrograma() {
		return estudiantePrograma;
	}


	public String getEstudianteStatus() {
		return estudianteStatus;
	}

	public void setEstudianteStatus(String estudianteStatus) {
		this.estudianteStatus = estudianteStatus;
	}

	public void setEstudiantePrograma(String estudiantePrograma) {
		this.estudiantePrograma = estudiantePrograma;
	}


	public Long getEstudianteHorasAcum() {
		return estudianteHorasAcum;
	}


	public void setEstudianteHorasAcum(Long estudianteHorasAcum) {
		this.estudianteHorasAcum = estudianteHorasAcum;
	}


	public List<InscripcionTaller> getListaTaller() {
		return listaTaller;
	}

	public void setListaTaller(List<InscripcionTaller> listaTaller) {
		this.listaTaller = listaTaller;
	}

	public ListModelList<InscripcionTaller> getModeloTaller() {
		return modeloTaller;
	}

	public void setModeloTaller(ListModelList<InscripcionTaller> modeloTaller) {
		this.modeloTaller = modeloTaller;
	}

	public InscripcionProyecto getSelectedProyecto() {
		return selectedProyecto;
	}

	public Estudiante getSelectedEstudiante() {
		return selectedEstudiante;
	}

	public void setSelectedEstudiante(Estudiante selectedEstudiante) {
		this.selectedEstudiante = selectedEstudiante;
	}

	public void setSelectedProyecto(InscripcionProyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}


	public String getNombreTaller() {
		return nombreTaller;
	}


	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}


	public List<PreInscripcionProyecto> getListaPreInscripcion() {
		return listaPreInscripcion;
	}


	public void setListaPreInscripcion(
			List<PreInscripcionProyecto> listaPreInscripcion) {
		this.listaPreInscripcion = listaPreInscripcion;
	}


	public ListModelList<PreInscripcionProyecto> getModeloPreInscripcion() {
		return modeloPreInscripcion;
	}


	public void setModeloPreInscripcion(
			ListModelList<PreInscripcionProyecto> modeloPreInscripcion) {
		this.modeloPreInscripcion = modeloPreInscripcion;
	}


	public List<InscripcionProyecto> getListaProyecto() {
		return listaProyecto;
	}


	public void setListaProyecto(List<InscripcionProyecto> listaProyecto) {
		this.listaProyecto = listaProyecto;
	}


	public ListModelList<InscripcionProyecto> getModeloProyecto() {
		return modeloProyecto;
	}


	public void setModeloProyecto(ListModelList<InscripcionProyecto> modeloProyecto) {
		this.modeloProyecto = modeloProyecto;
	}

	/** Método de inicialización que carga el desempeño del estudiante en SCE en la ventana detalle,
	 * cuando el usuario pulsa el botón "historial" de algún estudiante en la pantalla principal de la consulta, 
	 * a través de los criterios de búsqueda seleccionados, todo mediante su cédula de identidad. 
	 * @param view,ventana a la cual está asociada este viewmodel
	 * @param e1,cédula de identidad del estudiante seleccionado para ver
	 * su historial de desempeño dentro de SCE 
	 *  @param win,ventana a la cual esta asociada a este viewmodel */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("estudiante") String e1,
			@ContextParam(ContextType.COMPONENT) Component win) {

		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		this.estudianteCedula=e1;
		this.buscarEstudiante();
		this.buscarTaller();
		this.buscarPreInscripcion();
		this.buscarProyecto();
		this.buscarActividadesAsignadas();
		this.buscarActividadesEjecutadas();
		this.avanceDelEstudiante();
	}


	/**
	 * Método que vincula los elementos de la interfaz gráfica con este ViewModel.
	 * @param view, vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){

		this.win = (Window) win;
		Selectors.wireComponents(view, this, false);
		btnExportar.setDisabled(false); 
	}

	/**
	 * Método que realiza la búsqueda de los datos del estudiante seleccionado en la pantalla principal
	 * de consulta, mediante su cédula de identidad, así de esta forma obteniendo sus datos personales y horas
	 * acumuladas dentro del SCE si las posee. 
	 */
	@NotifyChange({"estudianteCedula", "estudianteNombre", "estudianteApellido" , "estudiantePrograma" , "estudianteStatus" , "modeloTaller" , "modeloPreInscripcion" , "estudianteHorasAcum" , "nombreTaller" , "nombrePreInscripcion" })
	@Command
	public void buscarEstudiante() {

		this.selectedEstudiante = sestudiante.buscarPorCedula(this.estudianteCedula);
		if(selectedEstudiante != null){
			this.estudianteCedula=selectedEstudiante.getEstudianteCedula();
			this.estudianteNombre=selectedEstudiante.getEstudianteNombre();
			this.estudianteApellido=selectedEstudiante.getEstudianteApellido();
			this.estudianteStatus=selectedEstudiante.getEstudianteStatus();
			DireccionPrograma direccionProg = selectedEstudiante.getDireccionProgramae();
			this.estudiantePrograma=direccionProg.getDireccionNombre();
			this.programaCodigo=direccionProg.getDireccionCodigo();
			this.estudianteHorasAcum=sestudiante.buscarHorasAcumuladas(selectedEstudiante);
			Long h1 = 0L , h2 = 0L;
			this.estudianteHorasAcum = sestudiante.buscarHorasAcumuladas(selectedEstudiante);
			if (this.estudianteHorasAcum == null){
				h1 = 0L;
			}
			else {
				h1 = this.estudianteHorasAcum;
			}
			
			this.estudianteHorasAcum = sestudiante.buscarHorasAcumuladasRetirado(selectedEstudiante);
			if (this.estudianteHorasAcum == null){
				h2 = 0L;
			}
			else {
				h2 = this.estudianteHorasAcum;
			}
			
			this.estudianteHorasAcum = h1-h2;
			
			}

		}

	/**
	 * Método que realiza la búsqueda del taller en el cual se encuentra inscrito el estudiante
	 * seleccionado,esto dependiendo del estatus que posea dentro de SCE.
	 */
	@NotifyChange({"modeloTaller" , "listaTaller" , "selectedEstudiante" })
	@Command
	public void buscarTaller() {

		this.listaTaller = sinscripciontaller.buscarPorEstudiante(selectedEstudiante);
		this.modeloTaller = new ListModelList<InscripcionTaller>(listaTaller);	
	}
	
	
    /**
     * Método que realiza la búsqueda del proyecto en el cual 
     * fue preinscrito el estudiante seleccionado.
     */
	@NotifyChange({"modeloPreInscripcion" , "listaPreInscripcion" , "selectedEstudiante" })
	@Command
	public void buscarPreInscripcion() {

		this.listaPreInscripcion = spreinscripcionproyecto.buscarPorEstudiante(selectedEstudiante);
		this.modeloPreInscripcion = new ListModelList<PreInscripcionProyecto>(listaPreInscripcion);

		ListModelList<PreInscripcionProyecto> modeloPreInscripcion2 = new ListModelList<PreInscripcionProyecto>(listaPreInscripcion);
		String var = "";

		for(int i = 0; this.modeloPreInscripcion.size()>i ; i++){
			var = "Usted se preinscribió en el proyecto: " + modeloPreInscripcion2.get(i).getProyectop().getProyectoNombre();
			Proyecto proyectoNom = new Proyecto();
			proyectoNom.setProyectoNombre(var);
			this.modeloPreInscripcion.get(i).getProyectop().setProyectoNombre(var);
			proyectoNom=null;
		}
		modeloPreInscripcion2=null;
	}

	/**
	 * Método que realiza la búsqueda del proyecto en el cual 
	 * fue formalizada la inscripción del estudiante seleccionado
	 */
	@NotifyChange({"modeloProyecto" , "listaProyecto" , "selectedEstudiante" })
	@Command
	public void buscarProyecto() {

		this.listaProyecto = sinscripcionProyecto.buscarPorEstudiante(selectedEstudiante);
		this.modeloProyecto = new ListModelList<InscripcionProyecto>(listaProyecto);

		ListModelList<InscripcionProyecto> modeloProyecto2 = new ListModelList<InscripcionProyecto>(listaProyecto);
		String var = "";

		for(int i = 0; this.modeloProyecto.size()>i ; i++){
			var = "Usted cumple SCE en el proyecto: " + modeloProyecto2.get(i).getProyectoi().getProyectoNombre();
			Proyecto proyectoNom = new Proyecto();
			proyectoNom.setProyectoNombre(var);
			this.modeloProyecto.get(i).getProyectoi().setProyectoNombre(var);
			proyectoNom=null;
		}
		modeloProyecto2=null;

	}

	/**
	 * Método que realiza la búsqueda de las actividades asociadas al proyecto en el cual 
	 * se encuentra prestando SCE el estudiante seleccionado, dicha información 
	 * obtenida al pulsar el botón "detalle".
	 */
	@NotifyChange({"selectedProyecto" })
	@Command
	public void mostrarActividadesProyecto(@ContextParam(ContextType.COMPONENT) Button componente) {
		Integer codigo_ins = (Integer) componente.getAttribute("codigo");
		final HashMap<String, Object> map = new HashMap<String, Object>();
		this.selectedProyecto=sinscripcionProyecto.buscarPorCodigo(codigo_ins);
		map.put("inscripcion", this.selectedProyecto);
		Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.transacciones/ConsultarDetalleStatus.zul", null, map);
		comp.doModal();
	}

	/**
	 * Método que realiza la búsqueda de las actividades asignadas al estudiante seleccionado.
	 */
	@NotifyChange({"listaActividadesAsignadas" , "modeloActividadesAsignadas"})
	@Command
	public void buscarActividadesAsignadas() {
		this.listaActividadesAsignadas = sActividadesEstudianteSCE.actividadesAsignadasEstudiante(estudianteCedula);
		this.modeloActividadesAsignadas = new ListModelList<ActividadesEstudiante>(listaActividadesAsignadas);

	}	

	/**
	 * Método que realiza la búsqueda de las actividades que han sido ejecutadas por el estudiante seleccionado.
	 */
	@NotifyChange({"listaActividadesEjecutadas" , "modeloActividadesEjecutadas"})
	@Command
	public void buscarActividadesEjecutadas() {

		this.listaActividadesEjecutadas = sActividadesEstudianteSCE.actividadesEjecutadasEstudiante(estudianteCedula);
		this.modeloActividadesEjecutadas = new ListModelList<ActividadesEstudiante>(listaActividadesEjecutadas);

	}

	/**
	 * Método que realiza la búsqueda de los datos mostrados en forma resumen en el historial del estudiante
	 * tales como: unidades de crédito aprobadas y el porcentaje que representa de acuerdo a la dirección de programa,
	 * cantidad de horas acumuladas y su porcentaje.
	 */
	@NotifyChange({"estudianteAvance" , "modeloEstudiante"})
	@Command
	public void avanceDelEstudiante() {
		this.estudianteAvance = sEstadoEstudiantesPorCarreraEstatus.avanceEstudiante(programaCodigo, estudianteStatus, estudianteCedula);
		this.modeloEstudiante = new ListModelList<EstatusEstudiantePorCarreraEstatus>(estudianteAvance);
	}

	/**
	 * Método que carga el reporte de acuerdo al desempeño que posea
	 * el estudiante seleccionado en SCE.
	 * @param componente,componente de la vista asociado a este viewModel
	 */
	@Command("showReport")
	@NotifyChange("reportConfig")
	public void showReport(@ContextParam(ContextType.COMPONENT) Button componente) {
		if (modeloTaller.isEmpty()){
			reportConfig = new ReportConfig(ruta);
			reportConfig.getParameters().put("estudiantePrograma", this.estudiantePrograma);
			reportConfig.getParameters().put("estudianteApellido", this.estudianteApellido);
			reportConfig.getParameters().put("estudianteNombre", this.estudianteNombre);
			reportConfig.getParameters().put("estudianteStatus", this.estudianteStatus);
			reportConfig.getParameters().put("cedulaEstudiante", this.estudianteCedula);
			reportConfig.getParameters().put("nombreTaller","No esta en ningun Taller");
			reportConfig.getParameters().put("nombrePreinscripcion", "No esta preinscrito en ningun Proyecto");
			reportConfig.getParameters().put("nombreProyecto", "No esta inscrito en ningun Proyecto");
			reportConfig.getParameters().put("unidadesAprobadas","Deber prestar SCE");
			reportConfig.getParameters().put("porcentajeUA", "Deber prestar SCE");
			reportConfig.getParameters().put("horasPrestadas", "Deber prestar SCE");
			reportConfig.getParameters().put("PorcentajeHP", "Deber prestar SCE");
			reportConfig.getParameters().put("PorcentajeHP", "Deber prestar SCE");
			btnExportar.setDisabled(true);
		}else if(modeloPreInscripcion.isEmpty() && !modeloTaller.isEmpty()){
			reportConfig = new ReportConfig(ruta);
			reportConfig.getParameters().put("estudiantePrograma", this.estudiantePrograma);
			reportConfig.getParameters().put("estudianteApellido", this.estudianteApellido);
			reportConfig.getParameters().put("estudianteNombre", this.estudianteNombre);
			reportConfig.getParameters().put("estudianteStatus", this.estudianteStatus);
			reportConfig.getParameters().put("cedulaEstudiante", this.estudianteCedula);
         	reportConfig.getParameters().put("nombrePreinscripcion", "No esta preinscrito en ningun Proyecto");
			reportConfig.getParameters().put("nombreProyecto", "No esta inscrito en ningun Proyecto");
			reportConfig.getParameters().put("unidadesAprobadas","Deber prestar SCE");
			reportConfig.getParameters().put("porcentajeUA", "Deber prestar SCE");
			reportConfig.getParameters().put("horasPrestadas", "Deber prestar SCE");
			reportConfig.getParameters().put("PorcentajeHP", "Deber prestar SCE");
			reportConfig.getParameters().put("PorcentajeHP", "Deber prestar SCE");
			reportConfig.getParameters().put("nombreTaller", "Taller: "+modeloTaller.get(0).getTaller().getTallerNombre());
			reportConfig.getParameters().put("listaTaller", new JRBeanCollectionDataSource(modeloTaller));
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloTaller));
			btnExportar.setDisabled(true);
		}else if(modeloProyecto.isEmpty() && !modeloPreInscripcion.isEmpty() && !modeloTaller.isEmpty()){
			reportConfig = new ReportConfig(ruta);
			reportConfig.getParameters().put("estudiantePrograma", this.estudiantePrograma);
			reportConfig.getParameters().put("estudianteApellido", this.estudianteApellido);
			reportConfig.getParameters().put("estudianteNombre", this.estudianteNombre);
			reportConfig.getParameters().put("estudianteStatus", this.estudianteStatus);
			reportConfig.getParameters().put("cedulaEstudiante", this.estudianteCedula);
			reportConfig.getParameters().put("nombreProyecto", "No esta inscrito en ningun Proyecto");
			reportConfig.getParameters().put("unidadesAprobadas","Deber prestar SCE");
			reportConfig.getParameters().put("porcentajeUA", "Deber prestar SCE");
			reportConfig.getParameters().put("horasPrestadas", "Deber prestar SCE");
			reportConfig.getParameters().put("PorcentajeHP", "Deber prestar SCE");
			reportConfig.getParameters().put("PorcentajeHP", "Deber prestar SCE");
			reportConfig.getParameters().put("nombreTaller", "Taller: "+modeloTaller.get(0).getTaller().getTallerNombre());
			reportConfig.getParameters().put("listaTaller", new JRBeanCollectionDataSource(modeloTaller));
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloTaller));
			reportConfig.getParameters().put("listaProyecto", new JRBeanCollectionDataSource(modeloProyecto));
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloProyecto));	
			btnExportar.setDisabled(true);
		}else{
			reportConfig = new ReportConfig(ruta);
			reportConfig.getParameters().put("nombreTaller", "Taller: "+modeloTaller.get(0).getTaller().getTallerNombre());
			reportConfig.getParameters().put("nombrePreinscripcion", modeloPreInscripcion.get(0).getProyectop().getProyectoNombre());
			reportConfig.getParameters().put("nombreProyecto", modeloProyecto.get(0).getProyectoi().getProyectoNombre());
			reportConfig.getParameters().put("listaTaller", new JRBeanCollectionDataSource(modeloTaller));
			reportConfig.getParameters().put("listaPreinscripcion", new JRBeanCollectionDataSource(modeloPreInscripcion));
			reportConfig.getParameters().put("listaProyecto", new JRBeanCollectionDataSource(modeloProyecto));
			reportConfig.getParameters().put("listaActividadesAsignadas", new JRBeanCollectionDataSource(modeloActividadesAsignadas));
			reportConfig.getParameters().put("listaActividadesEjecutadas", new JRBeanCollectionDataSource(modeloActividadesEjecutadas));
			reportConfig.getParameters().put("estudiantePrograma", this.estudiantePrograma);
			reportConfig.getParameters().put("estudianteApellido", this.estudianteApellido);
			reportConfig.getParameters().put("estudianteNombre", this.estudianteNombre);
			reportConfig.getParameters().put("estudianteStatus", this.estudianteStatus);
			reportConfig.getParameters().put("cedulaEstudiante", this.estudianteCedula);
			reportConfig.getParameters().put("unidadesAprobadas", modeloEstudiante.get(0).getEstudiantesUnidadesAprobadas());
			reportConfig.getParameters().put("porcentajeUA", modeloEstudiante.get(0).getPorcentajeUnidadesCreditosAprobadas());
			reportConfig.getParameters().put("horasPrestadas", modeloEstudiante.get(0).getEstudianteHorasPrestadas());
			reportConfig.getParameters().put("PorcentajeHP", modeloEstudiante.get(0).getPorcentajeHorasPrestadas());
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloTaller));
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloPreInscripcion));
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloProyecto));
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloActividadesAsignadas));
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloActividadesEjecutadas));
			btnExportar.setDisabled(true);
		
		}

		Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana2");
		tab.setSelected(true);
		tab.setVisible(true);

	};


	/**
	 * Método que cierra la ventana
	 */
	@Command
	public void cerrarVentana() {
		win.detach();
	}
}
