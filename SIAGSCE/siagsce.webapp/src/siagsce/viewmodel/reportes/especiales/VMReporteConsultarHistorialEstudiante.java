package siagsce.viewmodel.reportes.especiales;

/**
 * ViewModel usado en la vista para consultar el Historial de los Estudiantes en SCE
 * a través de la selección de los criterios de Búsqueda.
 * 
 * 
 * @author Iterator
 */
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.modelo.data.reportes.especiales.EstatusEstudiantePorCarreraEstatus;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.general.StatusCoordinadorSce;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SCoordinadorSce;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.reportes.abiertos.SListadoProfesor;
import siagsce.modelo.servicio.reportes.especiales.SEstadoEstudiantesPorCarreraEstatus;
import siagsce.viewmodel.seguridad.SecurityUtil;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteConsultarHistorialEstudiante {
	
	/**
	 * Declaración de variables del viewModel
	 */
	private Date fechaInicio;
	private Date fechaCulminacion;
	Estudiante est;
	private String status;
	private List<String> valores;
	private List<EstatusEstudiantePorCarreraEstatus> listEstudiante= new ArrayList<EstatusEstudiantePorCarreraEstatus>();
	List<EstatusEstudiantePorCarreraEstatus> list;
	private String seleccion;
	private String texto;
	Integer direccionPrograma; 
	String estatusEstudiante;
	String cedulaEstudiante;
	/**
	 * Declaración de componentes de la vista .zul
	 */
	@Wire
	private Combobox cmbPrograma;
	@Wire
	private Combobox cmbEstatus;
	@Wire
	private Datebox dtbFechaInicio;
	@Wire 
	Popup popOpciones;
	private Window win;
	@Wire
	private Datebox dtbFechaCulminacion;
	@Wire
	private Textbox txtFiltroEstudiantesEstatus;

	/**
	 * Declaración de los servicios
	 * */
	@WireVariable
	SEstudiante sestudiante;
	@WireVariable
	SDireccionPrograma sdireccionPrograma;
	@WireVariable
	SEstadoEstudiantesPorCarreraEstatus sEstadoEstudiantesPorCarreraEstatus;
	SListadoProfesor sListadoProfesor;
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	SCoordinadorSce scoordinacorSce;
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;
	@WireVariable
	SDirectorPrograma sdirector;
	@WireVariable
	SProyecto sproyecto;
	@WireVariable
	SActividad sactividad;

	
	/**
	 * Declaración de los modelos a ser utilizados en los listados y combos
	 * */
	private ListModelList<DireccionPrograma> modeloPrograma;
	private ListModelList<EstatusEstudiantePorCarreraEstatus> modeloEstatusEstudiantePorCarreraEstatus;
	private List<DireccionPrograma> programas;

	/**
	 * Declaración de variable donde sera guardada las selección.
	 * */
	private DireccionPrograma selectedPrograma;

	/**
	 * Esto para formato de fecha y para obtener la fecha del sistema.
	 * */
	Date fechaActual = new Date();
	SimpleDateFormat formateador;
	
	/**
	 * Método que me permite obtener el estatus de la clase Estudiante Estatus
	 * */
	public List<String> getEstudianteEstatus() {
		return EstatusEstudiante.getEstudianteEstatus();
	}

	
	
	/**
	 * Método que inicializa los modelos,carga el programa desde la BD 
	 * y llena el combo respectivamente
	 * @param win, ventana  a la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		programas =buscarDireccionesXRol();
		modeloPrograma = new ListModelList<DireccionPrograma>(programas);	
		modeloEstatusEstudiantePorCarreraEstatus = new ListModelList<EstatusEstudiantePorCarreraEstatus>();
		dtbFechaInicio=new Datebox();
		dtbFechaCulminacion=new Datebox();
		formateador = new SimpleDateFormat("yyyyMMdd");
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
	/**
	 * Método para validar que la fecha de culminación seleccionada 
	 * no sea menor que la fecha inicial.
	 */
	@NotifyChange({ "fechaCulminacion"})
	@Command
	public void validarFechaCulminacion() {
		dtbFechaCulminacion.setConstraint("after "+String.valueOf(formateador.format(fechaInicio)));
	}
	
	/**
	 * Método para validar que la fecha de inicio seleccionada 
	 * no sea mayor que la fecha inicial de ejecución.
	 */
	@NotifyChange({ "fechaInicio"})
	@Command
	public void validarFechaInicio() {
		dtbFechaInicio.setConstraint("before "+String.valueOf(formateador.format(fechaCulminacion)));
	}
	
	/**
	 * Vincula elementos de la interfaz gráfica con este ViewModel,
	 * coloca marca de agua al inicio al filtro nombre
	 * @param view, vista cuyos elementos se van a vincular a este ViewModel
	 */	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
		this.win = (Window) win;
		Selectors.wireComponents(view, this, false);
		txtFiltroEstudiantesEstatus.setPlaceholder("Nombre");
	}
	
	/**
	 * Métodos para el filtro de búsqueda.
	 */
	@Command
	@NotifyChange({ "modeloEstatusEstudiantePorCarreraEstatus" })
	public void filtrarEstudianteEstatus() {
		try {
			List<EstatusEstudiantePorCarreraEstatus> aux = new ArrayList<EstatusEstudiantePorCarreraEstatus>();
			aux = listEstudiante;
			modeloEstatusEstudiantePorCarreraEstatus.clear();

			if (seleccion == null || seleccion == "") {
				if (texto == "")
					modeloEstatusEstudiantePorCarreraEstatus.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstatusEstudiantePorCarreraEstatus.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Cédula") {
					if (texto == "")
						modeloEstatusEstudiantePorCarreraEstatus.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getEstudianteCedula().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloEstatusEstudiantePorCarreraEstatus.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Nombre") {
						if (texto == "")
							modeloEstatusEstudiantePorCarreraEstatus.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getEstudianteNombre().toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloEstatusEstudiantePorCarreraEstatus.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Apellido") {
							if (texto == "")
								modeloEstatusEstudiantePorCarreraEstatus.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getEsudianteApellido()
											.toLowerCase().contains(texto.toLowerCase())) {
										modeloEstatusEstudiantePorCarreraEstatus.add(aux.get(i));

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
			txtFiltroEstudiantesEstatus.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroEstudiantesEstatus.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido") {
					txtFiltroEstudiantesEstatus.setPlaceholder("Apellido");
					popOpciones.close();
				} 
			}
		}
	}


	/**
	 * VARIBLE QUE CONTROLA QUE TODOS LOS PARAMETROS SEAN ENVIADOS EXITOSAMENTE
	 *FALSE: NO EJECUTA LA CONSULTA
	 *TRUE: EJECUTA LA CONSULTA
	 **/
	boolean aceptar= false;

	/**
	 * Método que ejecuta la consulta SQL necesaria para generar el reporte
	 * */
	@Command("obtenerGrafico")
	@NotifyChange({"selectedPrograma","status"})
	public void obtenerGrafico() {
		modeloEstatusEstudiantePorCarreraEstatus.clear();
	
		/**
		 * VALIDACION SELECCIONAR UN PROGRAMA
		 * */
		if(selectedPrograma!=null){
			if(status==null){
				status="Apto";
			}
			if(status.equals("Apto")){
				listEstudiante = sEstadoEstudiantesPorCarreraEstatus.EstadoEstudiantesAptos(selectedPrograma.getDireccionCodigo(),fechaInicio,fechaCulminacion);
				modeloEstatusEstudiantePorCarreraEstatus.addAll(listEstudiante);
			}else{
				listEstudiante = sEstadoEstudiantesPorCarreraEstatus.EstadoEstudiantesAcreditadosAprobados(selectedPrograma.getDireccionCodigo(),status,fechaInicio,fechaCulminacion);
				modeloEstatusEstudiantePorCarreraEstatus.addAll(listEstudiante); 
			}
		} else{
			Messagebox.show("Debe Seleccionar una dirección de programa", "Mensaje",
					Messagebox.OK, Messagebox.INFORMATION);
			aceptar=false;
		};

	}


	/**
	 * Método que cancela el proceso
	 * @param view,ventana a la cual hace referencia este viewModel
	 */
	@Command
	@NotifyChange({ "fechaInicio", "fechaCulminacion", "modeloEstatusEstudiantePorCarreraEstatus" })
	public void cancelar(@ContextParam(ContextType.VIEW) Component view) {
		this.win = (Window) win;
		status=null;
		fechaInicio=new Date();
		fechaCulminacion=new Date();
		cmbPrograma.setText("");
		cmbEstatus.setText("");
		modeloEstatusEstudiantePorCarreraEstatus.clear();	
	}


	/**
	 * Método que muestra el desempeño en SCE del estudiante seleccionado
	 * @param componente, componente de la vista a tráves del cual se muestra la información.
	 */
	@NotifyChange({"cedulaEstudiante"})
	@Command
	public void mostrarHistorial(@ContextParam(ContextType.COMPONENT) Button componente) {
		cedulaEstudiante = (String) componente.getAttribute("idboton");
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("estudiante", this.cedulaEstudiante);
		Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.reportes.especiales/ConsultarHistorial.zul", null, map);
		comp.doModal();
	}

   /**
    * Método que cierra la ventana
    */
	@Command
	public void cerrarVentana() {	
		win.detach();
	}

	/**
	 * Métodos set y get
	 */
	public ListModelList<DireccionPrograma> getModeloPrograma(){
		return modeloPrograma;
	}

	public void setModeloPrograma(ListModelList<DireccionPrograma> modeloPrograma){
		this.modeloPrograma = modeloPrograma;
	}

	public ListModelList<EstatusEstudiantePorCarreraEstatus> getModeloEstatusEstudiantePorCarreraEstatus() {
		return modeloEstatusEstudiantePorCarreraEstatus;
	}

	public void setModeloEstatusEstudiantePorCarreraEstatus(
			ListModelList<EstatusEstudiantePorCarreraEstatus> modeloEstatusEstudiantePorCarreraEstatus) {
		this.modeloEstatusEstudiantePorCarreraEstatus = modeloEstatusEstudiantePorCarreraEstatus;
	}

	public List<DireccionPrograma> getProgramas() {
		return programas;
	}

	public void setProgramas(List<DireccionPrograma> programas) {
		this.programas = programas;
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

	public DireccionPrograma getSelectedPrograma() {
		return selectedPrograma;
	}

	public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
		this.selectedPrograma = selectedPrograma;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Popup getPopOpciones() {
		return popOpciones;
	}

	public void setPopOpciones(Popup popOpciones) {
		this.popOpciones = popOpciones;
	}

	public Textbox getTxtFiltroEstudiantesEstatus() {
		return txtFiltroEstudiantesEstatus;
	}

	public void setTxtFiltroEstudiantesEstatus(Textbox txtFiltroEstudiantesEstatus) {
		this.txtFiltroEstudiantesEstatus = txtFiltroEstudiantesEstatus;
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


	public List<String> getValores() {
		return valores;
	}

	public void setValores(List<String> valores) {
		this.valores = valores;
	}


	public List<EstatusEstudiantePorCarreraEstatus> getListEstudiante() {
		return listEstudiante;
	}

	public void setListEstudiante(
			List<EstatusEstudiantePorCarreraEstatus> listEstudiante) {
		this.listEstudiante = listEstudiante;
	}


	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}

	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}


	public Integer getDireccionPrograma() {
		return direccionPrograma;
	}

	public void setDireccionPrograma(Integer direccionPrograma) {
		this.direccionPrograma = direccionPrograma;
	}

	public String getEstatusEstudiante() {
		return estatusEstudiante;
	}

	public void setEstatusEstudiante(String estatusEstudiante) {
		this.estatusEstudiante = estatusEstudiante;
	}

	public Combobox getCmbPrograma() {
		return cmbPrograma;
	}

	public void setCmbPrograma(Combobox cmbPrograma) {
		this.cmbPrograma = cmbPrograma;
	}

	public Combobox getCmbEstatus() {
		return cmbEstatus;
	}

	public void setCmbEstatus(Combobox cmbestatus) {
		this.cmbEstatus = cmbestatus;
	}
}
