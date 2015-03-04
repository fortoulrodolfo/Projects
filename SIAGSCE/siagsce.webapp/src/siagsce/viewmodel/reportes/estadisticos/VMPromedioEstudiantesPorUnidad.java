package siagsce.viewmodel.reportes.estadisticos;

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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;

import org.zkoss.zul.Window;


import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.reportes.estadisticos.PorcentajeEstudiantesPorUnidades;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.reportes.estadisticos.SPromedioEstudiantesPorUnidades; 
import siagsce.viewmodel.reportes.abiertos.ReportConfig;
import siagsce.viewmodel.reportes.abiertos.ReportType;
import siagsce.viewmodel.seguridad.SecurityUtil;

/**
 * ViewModel para la vista que muestra 
 * el promedio de estudiantes por unidades 
 * de cr�dito aprobadas.
 *  
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMPromedioEstudiantesPorUnidad {

	/**
	 * Declaraci�n de variables
	 */
	private Date fechaInicio;
	private Date fechaCulminacion;
	ReportType reportType = new ReportType("PDF", "pdf");
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/estadisticos/reportePromedioEstudiantePorUnidad.jasper";
	private Window win;
	@Wire
	private Datebox dtbFechaInicio;
	@Wire
	private Datebox dtbFechaCulminacion;
	/**
	 * Lista que permite llenar el combo para elegir el formato 
	 */
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(
					new ReportType("HTML", "html"), 
					new ReportType("Word (RTF)", "rtf"), 
					new ReportType("Excel", "xls"), 
					new ReportType("Excel (JXL)", "jxl"), 
					new ReportType("CSV", "csv"), 
					new ReportType("OpenOffice (ODT)", "odt")));




	/**
	 * Declaraci�n de los servicios
	 */
	@WireVariable
	SDireccionPrograma sdireccionPrograma;

	@WireVariable
	SPromedioEstudiantesPorUnidades spromedioEstudiantesPorUnidades;
	
	@WireVariable
	SDirectorPrograma sdirector;
	
	@WireVariable
	SProfesor sprofesor;
	
	@WireVariable
	SActividad sactividad;
	
	@WireVariable
	SProyecto sproyecto ;
	
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;
	
	/**
	 * ESTO PARA FORMATO DE FECHA Y PARA OBTENER LA DEL SISTEMA
	 * */
	Date fechaActual = new Date();
	SimpleDateFormat formateador;


	/**
	 * Declaraci�n de los modelos a ser utilizados en los listados y combos
	 */
	private ListModelList<DireccionPrograma> modeloPrograma;
	private ListModelList<PorcentajeEstudiantesPorUnidades> modeloPromedioEstudiantesPorUnidad;
	private List<DireccionPrograma> programas;
	/**
	 * Declaraci�n de variables donde se guardan las selecciones
	 */
	private DireccionPrograma selectedPrograma;

	/**
	 * M�todo que inicializa los modelos y carga el programa desde la BD
	 * y el combo de programa
	 * @param view,ventana a la cual hace referencia este viewmodel
	 * @param win, ventana a la cual hace referencia este viewmodel
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		programas=buscarDireccionesXRol();
		modeloPrograma = new ListModelList<DireccionPrograma>(programas);		
		modeloPromedioEstudiantesPorUnidad = new ListModelList<PorcentajeEstudiantesPorUnidades>();
		fechaInicio=new Date();
		fechaCulminacion=new Date();
		dtbFechaInicio=new Datebox();
		dtbFechaCulminacion=new Datebox();
		formateador = new SimpleDateFormat("yyyyMMdd");
	}
	/**
	 * M�todo que aplica la seguridad funcional en el reporte
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

	/**
	 * Metodo para validar que la fecha de culminaci�n seleccionada 
	 * no sea menor que la fecha inicial.
	 */
	@NotifyChange({ "fechaCulminacion"})
	@Command
	public void validarFechaCulminacion() {
		dtbFechaCulminacion.setConstraint("after "+String.valueOf(formateador.format(fechaInicio)));
	}

	/**
	 * Metodo para validar que la fecha de inicio seleccionada 
	 * no sea mayor que la fecha inicial de ejecuci�n.
	 */
	@NotifyChange({ "fechaInicio"})
	@Command
	public void validarFechaInicio() {
		dtbFechaInicio.setConstraint("before "+String.valueOf(formateador.format(fechaCulminacion)));
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
		this.win = (Window) win;
		Selectors.wireComponents(view, this, false);
	}
	/**
	 * M�todo que ejecuta el SQL necesario para generar el reporte 
	 */
	@Command("obtenerGrafico")
	@NotifyChange("selectedPrograma")
	public void obtenerGrafico() {
		modeloPromedioEstudiantesPorUnidad.clear();

		/**
		 * Validaci�n al selecionar un programa
		 */
		if(selectedPrograma!=null){	  
			List<PorcentajeEstudiantesPorUnidades> lista = spromedioEstudiantesPorUnidades.CalcularPorcentajes(selectedPrograma.getDireccionUnidadesCredito(),selectedPrograma.getDireccionCodigo(),fechaInicio,fechaCulminacion);
			modeloPromedioEstudiantesPorUnidad.addAll(lista);

		} else{
			MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
			mensajeEmergente.advertenciaSeleccionarOpcion();

		};

	}


	/**
	 * M�todo que env�a los par�metros al reporte
	 */
	@Command("showReport")
	@NotifyChange({"reportConfig"})
	public void showReport() {
		obtenerGrafico();
		if(!modeloPromedioEstudiantesPorUnidad.get(0).getTotalestudiantes().equals(0)){
			reportConfig = new ReportConfig(ruta);
			reportConfig.getParameters().put("programa", selectedPrograma.getDireccionNombre());
			reportConfig.getParameters().put("unidades", selectedPrograma.getDireccionUnidadesCredito());
			reportConfig.getParameters().put("lista", new JRBeanCollectionDataSource(modeloPromedioEstudiantesPorUnidad));
			reportConfig.setType(reportType);
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloPromedioEstudiantesPorUnidad));
		}
	
	};

	public ListModelList<DireccionPrograma> getModeloPrograma(){
		return modeloPrograma;
	}

	public void setModeloPrograma(ListModelList<DireccionPrograma> modeloPrograma){
		this.modeloPrograma = modeloPrograma;
	}

	public ListModelList<PorcentajeEstudiantesPorUnidades> getModeloPromedioEstudiantesPorUnidad() {
		return modeloPromedioEstudiantesPorUnidad;
	}

	public void setModeloPromedioEstudiantesPorUnidad(
			ListModelList<PorcentajeEstudiantesPorUnidades> modeloPromedioEstudiantesPorUnidad) {
		this.modeloPromedioEstudiantesPorUnidad = modeloPromedioEstudiantesPorUnidad;
	}

	public List<DireccionPrograma> getPrograma() {
		return programas;
	}

	public void setPrograma(List<DireccionPrograma> programas) {
		this.programas = programas;
	}

	public DireccionPrograma getSelectedPrograma() {
		return selectedPrograma;
	}

	public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
		this.selectedPrograma = selectedPrograma;

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
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Window getWin() {
		return win;
	}

	public void setWin(Window win) {
		this.win = win;
	}



	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaCulminacion() {
		return fechaCulminacion;
	}

	public void setFechaCulminacion(Date fechaCulminacion) {
		this.fechaCulminacion = fechaCulminacion;
	}

	public Datebox getDtbFechaInicio() {
		return dtbFechaInicio;
	}

	public void setDtbFechaInicio(Datebox dtbFechaInicio) {
		this.dtbFechaInicio = dtbFechaInicio;
	}

	public Datebox getDtbFechaCulminacion() {
		return dtbFechaCulminacion;
	}

	public void setDtbFechaCulminacion(Datebox dtbFechaCulminacion) {
		this.dtbFechaCulminacion = dtbFechaCulminacion;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public SimpleDateFormat getFormateador() {
		return formateador;
	}

	public void setFormateador(SimpleDateFormat formateador) {
		this.formateador = formateador;
	}


	/**
	 * M�todo que cierra la ventana
	 */
	@Command
	public void cerrar() {
		win.detach();
	}


}
