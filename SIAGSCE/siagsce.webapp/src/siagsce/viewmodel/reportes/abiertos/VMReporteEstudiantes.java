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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.reportes.abiertos.ListaEstudiante;
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
import siagsce.viewmodel.seguridad.SecurityUtil;

/**
 * ViewModel para la vista del reporte de Consulta el Estatus de los Estudiantes,
 * Filtrado por Dirección de Programa, Estatus.
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteEstudiantes {
	/** 
	 * Declaración de Variables del ViewModel
	 * */
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String direccionNombre;
	MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

	/** 
	 * Declaración de Componentes de la vista
	 * */
	@Wire
	private Button Exportar;
	@Wire
	private Button btnCancelarConsutaEstudianteH;
	@Wire
	private Textbox txtFiltroEstatusEstudiantes;
	@Wire 
	Combobox cmbPrograma;
	@Wire
	Combobox cmbEstatus;
	@Wire 
	Popup popOpciones;
	private Window win;

	/** 
	 * Declaración de listas y otras estructuras de datos
	 * */
	List<ListaEstudiante> listestudiante= new ArrayList<ListaEstudiante>();
	private List<String> valores;
	private String seleccion;
	private String nombrefiltro;	


	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/abiertos/ListadoPersonalizado.jasper";

	/**
	 * Lista que permite llenar el combo para elegir el formato
	 **/
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(
					new ReportType("Word (RTF)", "rtf"), 
					new ReportType("CSV", "csv"), 
					new ReportType("OpenOffice (ODT)", "odt")));
	/** 
	 * Declaración de servicios del ViewModel
	 * */
	@WireVariable
	SDireccionPrograma sdireccionPrograma;
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	SDirectorPrograma sdirector;
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;
	@WireVariable
	SProyecto sproyecto ;
	@WireVariable
	SActividad sactividad ;
	@WireVariable
	SListadoEstudiante sListadoEstudiantesDireccionPrograma;

	/**
	 * Declaración de los modelos a ser utilizados en los listados y combos
	 **/
	private ListModelList<DireccionPrograma> modeloPrograma;
	private List<DireccionPrograma> programa;
	private ListModelList<ListaEstudiante> modeloEstudianteDireccionPrograma;

	/**
	 * Declaración de los modelos a ser utilizados en los listados y combos
	 **/
	private DireccionPrograma selectedPrograma;

	private Estudiante selectedEstatus;

	@WireVariable
	private String estatus="";

	

	/**
	 * Método que inicializa los modelos
	 *  y carga el programa desde la BD y el combo de programas
	 * @param win,ventana la cual esta asociada a este viewmodel
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		programa =buscarDireccionesXRol();
		modeloPrograma = new ListModelList<DireccionPrograma>(programa);
		modeloEstudianteDireccionPrograma = new ListModelList<ListaEstudiante>();
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
	 **/
	@Command("llenarListaDinamica")
	@NotifyChange({"selectedPrograma","estatus"})
	public void llenarListaDinamica() {
		modeloEstudianteDireccionPrograma.clear();
		String condicion="where aa.direccion_codigo = e.direccion_codigo ";
		/**
		 * Guarda la Seleccion que haga el usuario en la variable codigoDireccionPrograma
		 **/
		if(selectedPrograma!=null){
			condicion=condicion+" and aa.direccion_codigo="+ "'"+Integer.toString(selectedPrograma.getDireccionCodigo())+"'";
			desbloquear();
		}
		/**
		 * Guarda la Seleccion que haga el usuario del combo estatico de ListEst.zul
		 **/
		if(estatus!=""){
			condicion=condicion+" and e.estudiante_status ="+ "'"+estatus+"'";
			desbloquear();
		}
		listestudiante = sListadoEstudiantesDireccionPrograma.buscarEstudiantesDireccionProgramaDinamico(condicion);
		if(listestudiante.size()!=0){
			modeloEstudianteDireccionPrograma.addAll(listestudiante);
			win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
			Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
			tab.setSelected(true);
			System.out.println(condicion);
		}else{
			Exportar.setDisabled(true);
		}

	}

	/**
	 * Método carga el reporte
	 **/
	@Command("showReport")
	@NotifyChange({"reportConfig","prueba"})
	public void showReport() {
		reportConfig = new ReportConfig(ruta);
		reportConfig.setType(reportType);
		reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloEstudianteDireccionPrograma));
		Exportar.setDisabled(true);
	}

	@Command
	public void cerrar() {
		win.detach();
	}

	/**
	 * Método que limpia cada combo en el .zul
	 **/@Command
	 public void limpiarPrograma() {
		 modeloPrograma.clear();
	 }
	 @Command
	 public void limpiarmodelo() {
		 modeloEstudianteDireccionPrograma.clear();
	 }

	 /**
	  * Método que cancela el proceso
	  **/
	 @Command
	 @NotifyChange({ "direccionNombre", "proyectoNombre",
		 "cedula", "nombre", "apellido",
		 "email","telefono"})		
	 public void cancelar() {
		 cedula ="";
		 nombre="";
		 apellido ="";
		 email="";
		 telefono="";
		 limpiarmodelo();
		 cmbPrograma.setText("");
		 if(cmbEstatus.getSelectedItem()!=null){
			 cmbEstatus.setText("");
		 }
		 win.getFellow("tab").getFellow("tabs").getFellow("pestana1").setVisible(true);
		 Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
		 tab.setSelected(true);

	 }

	 /**
	  * Método utilizado para inicializar los componentes gráficos
	  * de la vista .zul, en este caso coloca en marca de agua el txt del filtro
	  * de búsqueda en nombre,inhabilita los botones esportar y cancelar.
	  * @param view,ventana la cual esta asociada a este viewmodel
	  */
	 @AfterCompose
	 public void AfterCompose(@ContextParam(ContextType.VIEW) Component win) {
		 Selectors.wireComponents(win, this, false);
		 txtFiltroEstatusEstudiantes.setPlaceholder("Nombre");
		 Exportar.setDisabled(true); 
		 btnCancelarConsutaEstudianteH.setDisabled(true);
	 }

	 /**
	  * Método que desbloquea los botones
	  **/
	 @Command
	 public void desbloquear() {
		 Exportar.setDisabled(false);
		 btnCancelarConsutaEstudianteH.setDisabled(false);
	 }

	 /**
	  * Métodos para el filtro
	  **/
	 @Command
	 @NotifyChange({ "modeloEstudianteDireccionPrograma" })
	 public void filtrarEstatusEstudiante() {
		 try {
			 List<ListaEstudiante> aux = new ArrayList<ListaEstudiante>();
			 aux = listestudiante;
			 modeloEstudianteDireccionPrograma.clear();

			 if (seleccion == null || seleccion == "") {
				 if (nombrefiltro == "")
					 modeloEstudianteDireccionPrograma.addAll(aux);
				 else {
					 for (int i = 0; i < aux.size(); i++) {
						 if (aux.get(i).getNombre().toLowerCase()
								 .contains(nombrefiltro.toLowerCase())) {
							 modeloEstudianteDireccionPrograma.add(aux.get(i));

						 }
					 }
				 }
			 } else {
				 if (seleccion == "Cédula") {
					 if (nombrefiltro == "")
						 modeloEstudianteDireccionPrograma.addAll(aux);
					 else {
						 for (int i = 0; i < aux.size(); i++) {
							 if (aux.get(i).getCedula().toLowerCase()
									 .contains(nombrefiltro.toLowerCase())) {
								 modeloEstudianteDireccionPrograma.add(aux.get(i));

							 }
						 }
					 }
				 } else {
					 if (seleccion == "Nombre") {
						 if (nombrefiltro == "")
							 modeloEstudianteDireccionPrograma.addAll(aux);
						 else {
							 for (int i = 0; i < aux.size(); i++) {
								 if (aux.get(i).getNombre().toLowerCase()
										 .contains(nombrefiltro.toLowerCase())) {
									 modeloEstudianteDireccionPrograma.add(aux.get(i));

								 }
							 }
						 }
					 } else {
						 if (seleccion == "Apellido") {
							 if (nombrefiltro == "")
								 modeloEstudianteDireccionPrograma.addAll(aux);
							 else {
								 for (int i = 0; i < aux.size(); i++) {
									 if (aux.get(i).getApellido()
											 .toLowerCase().contains(nombrefiltro.toLowerCase())) {
										 modeloEstudianteDireccionPrograma.add(aux.get(i));

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
			 txtFiltroEstatusEstudiantes.setPlaceholder("Nombre");
			 popOpciones.close();
		 } else {
			 if (seleccion == "Cédula") {
				 txtFiltroEstatusEstudiantes.setPlaceholder("Cédula");
				 popOpciones.close();
			 } else {
				 if (seleccion == "Apellido")
					 txtFiltroEstatusEstudiantes.setPlaceholder("Apellido");
				 popOpciones.close();
			 }
		 }

	 }
	 /**
		 * Métodos Set y Get
		 **/
		public ListModelList<ListaEstudiante> getModeloEstudianteDireccionPrograma() {
			return modeloEstudianteDireccionPrograma;
		}

		public String getEstatus() {
			return estatus;
		}

		public void setEstatus(String estatus) {
			this.estatus = estatus;
		}

		public List<DireccionPrograma> getPrograma() {
			return programa;
		}

		public void setPrograma(List<DireccionPrograma> programa) {
			this.programa = programa;
		}

		public ListModelList<DireccionPrograma> getModeloPrograma() {
			return modeloPrograma;
		}

		public void setModeloPrograma(ListModelList<DireccionPrograma> modeloPrograma) {
			this.modeloPrograma = modeloPrograma;
		}

		public void setModeloEstudianteDireccionPrograma(
				ListModelList<ListaEstudiante> modeloEstudianteDireccionPrograma) {
			this.modeloEstudianteDireccionPrograma = modeloEstudianteDireccionPrograma;
		}

		public DireccionPrograma getSelectedPrograma() {
			return selectedPrograma;
		}

		public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
			this.selectedPrograma = selectedPrograma;
		}

		public Estudiante getSelectedEstatus() {
			return selectedEstatus;
		}

		public void setSelectedEstatus(Estudiante selectedEstatus) {
			this.selectedEstatus = selectedEstatus;
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

		public void setCmbEstatus(Combobox cmbEstatus) {
			this.cmbEstatus = cmbEstatus;
		}

		public Popup getPopOpciones() {
			return popOpciones;
		}

		public void setPopOpciones(Popup popOpciones) {
			this.popOpciones = popOpciones;
		}

		public List<ListaEstudiante> getListestudiante() {
			return listestudiante;
		}

		public void setListestudiante(List<ListaEstudiante> listestudiante) {
			this.listestudiante = listestudiante;
		}

		public List<String> getValores() {
			return valores;
		}

		public void setValores(List<String> valores) {
			this.valores = valores;
		}

		public String getNombrefiltro() {
			return nombrefiltro;
		}

		public void setNombrefiltro(String nombrefiltro) {
			this.nombrefiltro = nombrefiltro;
		}

		public String getSeleccion() {
			return seleccion;
		}

		public void setSeleccion(String seleccion) {
			this.seleccion = seleccion;
		}

		/**
		 * Metodos set y get que me permite manipular la vista ListEst.zul
		 **/
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
