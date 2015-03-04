package siagsce.viewmodel.reportes.abiertos;

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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.reportes.abiertos.ListaProyecto;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SCoordinadorSce;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.reportes.abiertos.SListadoProfesor;
import siagsce.modelo.servicio.reportes.abiertos.SListadoProyecto;
import siagsce.viewmodel.seguridad.SecurityUtil;

/**
 * ViewModel para la vista del reporte de Consulta de Proyectos Activos,
 * Filtrado por Dirección de Programa.
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteProyectosActivos {
	
	/**
	 * Declaración de variables
	 */
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private String ruta = "/WEB-INF/siagsce/reportes/abiertos/ListadoProyectosActivos.jasper";
	
	/**
	 * Lista que permite llenar el combo para elegir el formato
	 */
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(
					new ReportType("Word (RTF)", "rtf"),  
					new ReportType("CSV", "csv"),
					new ReportType("OpenOffice (ODT)", "odt")));

	/**
	 * Declaración de los servicios
	 */
	@WireVariable
	SDireccionPrograma sdireccionPrograma ;

	@WireVariable
	SProyecto sproyecto ;

	@WireVariable
	SActividad sactividad;

	@WireVariable
	SProfesor sprofesor;

	@WireVariable
	SDirectorPrograma sdirector;

	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;

	@WireVariable
	SCoordinadorSce scoordinacorSce;


	@WireVariable
	SListadoProyecto sListadoProyectos;
	
	/**
	 * Declaración de los componenetes de la vista
	 */
	@Wire
	private Button btnExportar;
	private Button btnCancelar;

	@Wire
	private List<String> valores;
	private String seleccion;
	private String texto;
	@Wire
	private Textbox txtFiltro;
	@Wire
	private Popup popOpciones;
	private Window win;
	
	/**Declaración de los modelos a ser utilizados en los listados y combos
	 */
	private ListModelList<DireccionPrograma> modeloPrograma;
	private List<DireccionPrograma> programas;
	private ListModelList<ListaProyecto> modeloListaProyecto;

	/**Declaración de variables donde sera guardada las selecciones(filtro)
	 * 
	 */
	private DireccionPrograma selectedPrograma;


	/**
	 * Método que inicializa los modelos
	 *  y carga el programa,proyecto desde la BD y el combo de programas
	 * @param win,ventana la cual esta asociada a este viewmodel
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		programas = buscarDireccionesXRol();
		modeloPrograma = new ListModelList<DireccionPrograma>(programas);
		modeloListaProyecto = new ListModelList<ListaProyecto>();
		valores = new ArrayList<String>();
		valores.add("Código");
		valores.add("Descripción");
		seleccion=null;

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
	 * Método utilizado para inicializar los componentes gráficos
	 * de la vista .zul, en este caso coloca en marca de agua el txt del filtro
	 * de búsqueda en código.
	 * @param view,ventana la cual esta asociada a este viewmodel
	 */	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {

		Selectors.wireComponents(view, this, false);
		txtFiltro.setPlaceholder("Código");

	}

	/**
	 * Método que realiza la búsqueda de forma dinámica
	 */
	@Command("llenarListaDinamica")
	@NotifyChange({ "selectedPrograma" })
	public void llenarListaDinamica() {
		btnExportar = (Button) win.getFellow("btnExportar");
		btnExportar.setDisabled(false);
		btnCancelar = (Button) win.getFellow("btnCancelar");
		btnCancelar.setDisabled(false);
		modeloListaProyecto.clear();

		Integer condicion = -1;
		if (selectedPrograma != null) {
			condicion = selectedPrograma.getDireccionCodigo();
		}


		List<ListaProyecto> listaproyectos = sListadoProyectos
				.buscarProyectosActivosDireccionPrograma(condicion);
		if (listaproyectos.size()==0){
			btnExportar.setDisabled(true);}
		if (seleccion == null || seleccion == "") {
			if (texto == ""  ||texto == null ){
				System.out.println(texto);
				modeloListaProyecto.addAll(listaproyectos);}
			else {
				for (int i = 0; i < listaproyectos.size(); i++) {

					if (listaproyectos.get(i).getCodigo().toLowerCase()
							.contains(texto.toLowerCase())) {
						modeloListaProyecto.add(listaproyectos.get(i));

					}else{
						btnExportar.setDisabled(true);}
				}
			}

		} else {
			if (seleccion == "Descripción") {
				if (texto == ""  ||texto == null ){

					modeloListaProyecto.addAll(listaproyectos);}
				else {
					for (int i = 0; i < listaproyectos.size(); i++) {

						if (listaproyectos.get(i).getDescripcion()
								.contains(texto.toLowerCase())) {
							modeloListaProyecto.add(listaproyectos.get(i));

						}else{
							btnExportar.setDisabled(true);}
					}
				}
			} else {
				if (seleccion == "Código") {
					if (texto == ""  ||texto == null ){

						modeloListaProyecto.addAll(listaproyectos);}
					else {
						for (int i = 0; i < listaproyectos.size(); i++) {
							if (listaproyectos.get(i).getCodigo().toLowerCase()
									.contains(texto.toLowerCase())) {

								modeloListaProyecto.add(listaproyectos.get(i));

							}else{
								btnExportar.setDisabled(true);}
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
		System.out.println(condicion);
	}

	@Command
	public void seleccionFiltro() {
		System.out.print(seleccion);
		if (seleccion == "Código") {
			txtFiltro.setPlaceholder("Código");
			popOpciones.close();
		} else {
			if (seleccion == "Descripción") {
				txtFiltro.setPlaceholder("Descripción");
				popOpciones.close();
			}
		}

	}

	/**Limpieza automatica
	 * 
	 */
	public void limpiezaAutomatica() {
		texto = "";

	}

	/**
	 * Método que carga la lista de estudiantes asignado a la Direccion de
	 * programa que se seleccione en el combo y le pasa al componete
	 * jasperReport lo necesariopara pintarse en la pantalla ListEst.zul
	 */
	@Command("showReport")
	@NotifyChange({ "reportConfig", "prueba" })
	public void showReport() {
		reportConfig = new ReportConfig(ruta);
		reportConfig.setType(reportType);
		reportConfig.setDataSource(new JRBeanCollectionDataSource(
				modeloListaProyecto));

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
	 */
	@Command
	public void cancelar() {
		modeloListaProyecto.clear();
		limpiezaAutomatica();
		Combobox ComboProgramass = (Combobox) win
				.getFellow("cmbPrograma");
		ComboProgramass.setSelectedIndex(-1);
		selectedPrograma = null;
		btnExportar.setDisabled(true);
		txtFiltro.setValue("");
		txtFiltro.setPlaceholder("Código");
		win.getFellow("tab").getFellow("tabs").getFellow("pestana2")
		.setVisible(false);
		Tab tab = (Tab) win.getFellow("tab").getFellow("tabs")
				.getFellow("pestana1");
		tab.setSelected(true);

		btnCancelar.setDisabled(true);


	}
	

	/**Métodos Set y Get
	 * 
	 */
	public List<String> getValores() {
		return valores;
	}

	public void setValores(List<String> valores) {
		this.valores = valores;
	}

	public List<DireccionPrograma> getProgramas() {
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

	public ListModelList<ListaProyecto> getModeloListaProyecto() {
		return modeloListaProyecto;
	}

	public void setModeloListaProyecto(
			ListModelList<ListaProyecto> modeloListaProyecto) {
		this.modeloListaProyecto = modeloListaProyecto;
	}

	public DireccionPrograma getSelectedPrograma() {
		return selectedPrograma;
	}

	public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
		this.selectedPrograma = selectedPrograma;
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

	/**
	 * Métodos set y get que me permite manipular la .zul
	 */
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
