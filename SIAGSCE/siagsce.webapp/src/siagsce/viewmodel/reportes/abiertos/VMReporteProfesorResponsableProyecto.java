package siagsce.viewmodel.reportes.abiertos;

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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.reportes.abiertos.ListaProfesor;
import siagsce.modelo.general.StatusCoordinadorSce;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SCoordinadorSce;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.reportes.abiertos.SListadoEstudiante;
import siagsce.modelo.servicio.reportes.abiertos.SListadoProfesor;
import siagsce.viewmodel.reportes.abiertos.ReportConfig;
import siagsce.viewmodel.reportes.abiertos.ReportType;
import siagsce.viewmodel.seguridad.SecurityUtil;

/**
 * ViewModel para la vista del reporte de Consulta de Profesor responsable,
 * Filtrado por Dirección de Programa, Proyecto.
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteProfesorResponsableProyecto {

	/**
	 * Declaración de Variables del ViewModel
	 * */
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;

	/**
	 * Declaración de Componentes de la vista
	 * */
	@Wire
	private Button Exportar;
	@Wire
	private Button btnCancelarConsutaEstudianteH;
	@Wire
	private Textbox txtFiltroProfesorResponsable;
	@Wire
	Combobox cmbPrograma;
	@Wire
	Combobox cmbProyectos;
	@Wire
	Popup popOpciones;
	private Window win;

	/**
	 * Declaración de listas y otras estructuras de datos
	 * */
	List<ListaProfesor> listprofesor = new ArrayList<ListaProfesor>();
	private List<String> valores;
	private String seleccion;
	private String nombrefiltro;

	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private String ruta = "/WEB-INF/siagsce/reportes/abiertos/reporteProfesorResponsableProyecto.jasper";
	private String proyecto_param;

	/**
	 * Lista que permite llenar el combo para elegir el formato
	 * */
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(new ReportType("Word (RTF)", "rtf"), new ReportType(
					"CSV", "csv"), new ReportType("OpenOffice (ODT)", "odt")));

	/**
	 * Declaración de los servicios
	 * */
	@WireVariable
	SDireccionPrograma sdireccionPrograma;

	@WireVariable
	SProyecto sproyecto;

	@WireVariable
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
	SActividad sactividad;

	/**
	 * Declaración de los modelos a ser utilizados en los listados y combos
	 * */
	private ListModelList<DireccionPrograma> modeloPrograma;
	private List<DireccionPrograma> programas;
	private ListModelList<ListaProfesor> modeloProfesorResponsable;
	private ListModelList<Proyecto> modeloProyecto;
	private List<Proyecto> proyecto;

	/**
	 * Declaración de variables donde sera guardada las selecciones
	 * */
	private DireccionPrograma selectedPrograma;
	private Proyecto selectedProyecto;

	
	/**
	 * Método que inicializa los modelos y carga el programa,proyecto desde la
	 * BD y el combo de programas
	 * 
	 * @param win
	 *            ,ventana la cual esta asociada a este viewmodel
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		programas = buscarDireccionesXRol();
		modeloPrograma = new ListModelList<DireccionPrograma>(programas);
		modeloProyecto = new ListModelList<Proyecto>();
		modeloProfesorResponsable = new ListModelList<ListaProfesor>();
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
	 * Método que llena los datos de forma dinámica
	 * */
	@Command("llenarListaDinamica")
	@NotifyChange({ "selectedPrograma", "selectedProyecto" })
	public void llenarListaDinamica() {
		modeloProfesorResponsable.clear();
		String condicion = " where prf.profesor_cedula = prfr.profesor_cedula and prf.profesor_status= 'Acreditado' and pp.direccion_codigo= dp.direccion_codigo ";
		/**
		 * Guarda la Selección que haga el usuario en la variable
		 * codigoDireccionPrograma
		 */
		if (selectedPrograma != null) {
			condicion = condicion + " and dp.direccion_codigo=" + "'"
					+ Integer.toString(selectedPrograma.getDireccionCodigo())
					+ "'";
			desbloquear();
		}

		/**
		 * Guarda la Selección que haga el usuario del combo proyecto
		 */
		if (selectedProyecto != null) {
			condicion = condicion + " and pp.proyecto_codigo  =" + "'"
					+ selectedProyecto.getProyectoCodigo() + "'"
					+ " and prfr.proyecto_codigo  =" + "'"
					+ selectedProyecto.getProyectoCodigo() + "'";
			proyecto_param = selectedProyecto.getProyectoNombre();
			desbloquear();
		}
		listprofesor = sListadoProfesor.buscarProfesorResponsable(condicion);
		if (listprofesor.size() != 0) {
			modeloProfesorResponsable.addAll(listprofesor);
			win.getFellow("tab").getFellow("tabs").getFellow("pestana2")
					.setVisible(false);
			Tab tab = (Tab) win.getFellow("tab").getFellow("tabs")
					.getFellow("pestana1");
			tab.setSelected(true);
		} else {
			Exportar.setDisabled(true);
		}
	}

	/**
	 * Método que carga el reporte
	 * */
	@Command("showReport")
	@NotifyChange({ "reportConfig", "prueba" })
	public void showReport() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if (selectedPrograma == null && selectedProyecto == null) {
			mensajeEmergente.advertenciaCriterioBusqueda();
		} else {
			reportConfig = new ReportConfig(ruta);
			if (selectedProyecto != null) {
				reportConfig.getParameters().put("proyecto",
						selectedProyecto.getProyectoNombre());
			} else {
				reportConfig.getParameters().put("proyecto", " ");
			}
			reportConfig.setType(reportType);
			reportConfig.setDataSource(new JRBeanCollectionDataSource(
					modeloProfesorResponsable));
			Exportar.setDisabled(true);
		}
	}

	/**
	 * Método que carga el combo proyectos de acuerdo a la dirección de programa
	 * seleccionada
	 */
	@NotifyChange({ "modeloProyecto" })
	@Command
	public void cargarProyecto() {
		obtenerProyectosXProgramaYProfesor();
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
	/**
	 * Método que cierra la ventana
	 */
	@Command
	public void cerrar() {
		win.detach();
	}

	/**
	 * Método que limpia cada combo en el .zul
	 * */
	@Command
	public void limpiarPrograma() {
		modeloPrograma.clear();
	}

	@Command
	public void limpiarProyecto() {
		modeloProyecto.clear();
	}

	@Command
	public void limpiarmodelo() {
		modeloProfesorResponsable.clear();
	}

	/**
	 * Método que cancela el proceso
	 * */
	@Command
	@NotifyChange({ "direccionNombre", "proyectoNombre", "cedula", "nombre",
			"apellido", "email", "telefono" })
	public void cancelar() {
		cedula = "";
		nombre = "";
		apellido = "";
		email = "";
		telefono = "";
		limpiarmodelo();
		cmbPrograma.setText("");
		if (cmbProyectos.getSelectedItem() != null) {
			cmbProyectos.setText("");
		}
		win.getFellow("tab").getFellow("tabs").getFellow("pestana1")
				.setVisible(true);
		Tab tab = (Tab) win.getFellow("tab").getFellow("tabs")
				.getFellow("pestana1");
		tab.setSelected(true);
	}

	/**
	 * Método utilizado para inicializar los componentes gráficos de la vista
	 * .zul, en este caso coloca en marca de agua el txt del filtro de búsqueda
	 * en nombre, inhabilita los botones exportar y cancelar.
	 * 
	 * @param view
	 *            ,ventana la cual esta asociada a este viewmodel
	 */
	@AfterCompose
	public void AfterCompose(@ContextParam(ContextType.VIEW) Component win) {
		Selectors.wireComponents(win, this, false);
		txtFiltroProfesorResponsable.setPlaceholder("Nombre");
		Exportar.setDisabled(true);
		btnCancelarConsutaEstudianteH.setDisabled(true);
	}

	/**
	 * Método que desbloquea los botones
	 * */
	@Command
	public void desbloquear() {
		Exportar.setDisabled(false);
		btnCancelarConsutaEstudianteH.setDisabled(false);
	}

	/**
	 * Métodos para el filtro
	 * */
	@Command
	@NotifyChange({ "modeloProfesorResponsable" })
	public void filtrarProfesorResponsable() {
		try {
			List<ListaProfesor> aux = new ArrayList<ListaProfesor>();
			aux = listprofesor;
			modeloProfesorResponsable.clear();

			if (seleccion == null || seleccion == "") {
				if (nombrefiltro == "")
					modeloProfesorResponsable.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getNombre().toLowerCase()
								.contains(nombrefiltro.toLowerCase())) {
							modeloProfesorResponsable.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Cédula") {
					if (nombrefiltro == "")
						modeloProfesorResponsable.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getCedula().toLowerCase()
									.contains(nombrefiltro.toLowerCase())) {
								modeloProfesorResponsable.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Nombre") {
						if (nombrefiltro == "")
							modeloProfesorResponsable.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getNombre().toLowerCase()
										.contains(nombrefiltro.toLowerCase())) {
									modeloProfesorResponsable.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Apellido") {
							if (nombrefiltro == "")
								modeloProfesorResponsable.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i)
											.getApellido()
											.toLowerCase()
											.contains(
													nombrefiltro.toLowerCase())) {
										modeloProfesorResponsable.add(aux
												.get(i));

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
		if (seleccion == "Nombre") {
			txtFiltroProfesorResponsable.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroProfesorResponsable.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido")
					txtFiltroProfesorResponsable.setPlaceholder("Apellido");
				popOpciones.close();
			}
		}

	}
	
	/**
	 * Métodos set y get
	 * */
	public List<DireccionPrograma> getPrograma() {
		return programas;
	}

	public void setProgramas(List<DireccionPrograma> programas) {
		this.programas = programas;
	}

	public ListModelList<DireccionPrograma> getModeloPrograma() {
		return modeloPrograma;
	}

	public void setModeloPrograma(
			ListModelList<DireccionPrograma> modeloPrograma) {
		this.modeloPrograma = modeloPrograma;
	}

	public List<Proyecto> getProyecto() {
		return proyecto;
	}

	public void setProyecto(List<Proyecto> proyecto) {
		this.proyecto = proyecto;
	}

	public ListModelList<ListaProfesor> getModeloProfesorResponsable() {
		return modeloProfesorResponsable;
	}

	public void setModeloProfesorResponsable(
			ListModelList<ListaProfesor> modeloProfesorResponsable) {
		this.modeloProfesorResponsable = modeloProfesorResponsable;
	}

	public Textbox getTxtFiltroProfesorResponsable() {
		return txtFiltroProfesorResponsable;
	}

	public void setTxtFiltroProfesorResponsable(
			Textbox txtFiltroProfesorResponsable) {
		this.txtFiltroProfesorResponsable = txtFiltroProfesorResponsable;
	}

	public Combobox getCmbPrograma() {
		return cmbPrograma;
	}

	public void setCmbPrograma(Combobox cmbPrograma) {
		this.cmbPrograma = cmbPrograma;
	}

	public Combobox getCmbProyectos() {
		return cmbProyectos;
	}

	public void setCmbProyectos(Combobox cmbProyectos) {
		this.cmbProyectos = cmbProyectos;
	}

	public Popup getPopOpciones() {
		return popOpciones;
	}

	public void setPopOpciones(Popup popOpciones) {
		this.popOpciones = popOpciones;
	}

	public List<ListaProfesor> getListprofesor() {
		return listprofesor;
	}

	public void setListprofesor(List<ListaProfesor> listprofesor) {
		this.listprofesor = listprofesor;
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

	public String getNombrefiltro() {
		return nombrefiltro;
	}

	public void setNombrefiltro(String nombrefiltro) {
		this.nombrefiltro = nombrefiltro;
	}

	/**
	 * Métodos set y get que me permite manipular la vista ListEst.zul
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

	public Proyecto getSelectedProyecto() {
		return selectedProyecto;
	}

	public void setSelectedProyecto(Proyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}

	public DireccionPrograma getSelectedPrograma() {
		return selectedPrograma;
	}

	public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
		this.selectedPrograma = selectedPrograma;
	}

	public ListModelList<Proyecto> getModeloProyecto() {
		return modeloProyecto;
	}

	public void setModeloProyecto(ListModelList<Proyecto> modeloProyecto) {
		this.modeloProyecto = modeloProyecto;
	}

}
