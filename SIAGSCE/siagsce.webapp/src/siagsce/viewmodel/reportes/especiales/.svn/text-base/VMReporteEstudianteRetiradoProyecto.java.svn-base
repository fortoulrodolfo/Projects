package siagsce.viewmodel.reportes.especiales;

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
import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.reportes.abiertos.ListaConsultadeEstatusdeProyectos;
import siagsce.modelo.data.reportes.especiales.ListaEstudianteRetiradoProyecto;
import siagsce.modelo.general.StatusCoordinadorSce;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SCoordinadorSce;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SMotivo;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.reportes.especiales.SListadoEstudianteRetiradoProyecto;
import siagsce.viewmodel.reportes.abiertos.ReportConfig;
import siagsce.viewmodel.reportes.abiertos.ReportType;
import siagsce.viewmodel.seguridad.SecurityUtil;

/**
 * ViewModel para la vista que muestra 
 * los estudiantes retirados por proyecto
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)

public class VMReporteEstudianteRetiradoProyecto {

	/**	
	 *Declaración de las variables del viewModel
	 **/
	private String nombres;
	private String seleccion;
	private String texto;
	private List<String> valores;
	private String Nombre;

	/**	
	 *Declaración de los componentes de la Vista Estudiantes Retitado de un Proyecto
	 **/
	MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
	@Wire
	private Combobox cmbPrograma;
	@Wire
	private Combobox cmbMotivo;
	@Wire
	private Button Exportar;
	@Wire
	private Button btnCancelarConsutaEstudianteH;
	@Wire
	Textbox txtFiltroEstudiante;
	@Wire
	private Popup popOpciones;
	@Wire
	private Window win;

	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/especiales/ReporteEstudiantesRetiradosProyecto.jasper";



	/**	
	 *Lista que permite llenar el combo para elegir el formato 
	 **/		
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(
					new ReportType("Word (RTF)", "rtf"), 
					new ReportType("CSV", "csv"), 
					new ReportType("OpenOffice (ODT)", "odt")));




	/**	
	 *Declaración de los servicios del viewModel

	 **/
	@WireVariable
	SDireccionPrograma sdireccionPrograma;
	@WireVariable
	SProyecto sproyecto;
	@WireVariable
	SMotivo smotivo;
	@WireVariable
	SListadoEstudianteRetiradoProyecto sListadoEstudianteRetiradoProyecto;
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion ;
	@WireVariable
	SDirectorPrograma sdirector ;
	@WireVariable
	SProfesor sprofesor ;
	@WireVariable
	SActividad sactividad;
	@WireVariable
	SCoordinadorSce scoordinacorSce;


	/**
	 * Declaración de los modelos a ser utilizados en los listados y combos
     *
	 **/		
	private ListModelList<DireccionPrograma> modeloPrograma;
	private List<DireccionPrograma> programas;
	private ListModelList<Proyecto> modeloProyecto;
	private List<Proyecto> proyecto;
	private ListModelList<Motivo> modeloMotivo;
	private List<Motivo> motivo;
	private ListModelList<ListaEstudianteRetiradoProyecto> modeloEstudianteRetirado;
	private List<ListaEstudianteRetiradoProyecto> listestudianteretirado;
	/**		
	 *Declaración de variables donde sera guardada las selecciones
     *
	 **/
	private DireccionPrograma selectedPrograma;
	private Proyecto selectedProyecto;
	private Motivo selectedCodigoMotivo;

	
	/**
	 * Método que inicializa los modelos,carga el programa y el proyecto desde la BD 
	 * y llena los combos respectivamente
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {

		this.win = (Window) win;
		 

		modeloProyecto = new ListModelList<Proyecto>();
		String cedulaProf = SecurityUtil.nombreUsuario;
		Profesor profesor = sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
		programas = buscarDireccionesXRol();
	    modeloPrograma = new ListModelList<DireccionPrograma>(programas);
		modeloEstudianteRetirado = new ListModelList<ListaEstudianteRetiradoProyecto>();
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		modeloEstudianteRetirado = new ListModelList<ListaEstudianteRetiradoProyecto>();
		motivo = smotivo.buscarTodo();
		modeloMotivo = new ListModelList<Motivo>(motivo);	
		modeloEstudianteRetirado = new ListModelList<ListaEstudianteRetiradoProyecto>();			
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
	 * Vincula elementos de la interfaz gráfica con este ViewModel,inhabilita los botones exportar y 
	 * cancelar,coloca al inicio marca de agua en el filtro nombre
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
		this.win = (Window) win;
		Selectors.wireComponents(view, this, false);
		Exportar.setDisabled(true); 
		btnCancelarConsutaEstudianteH.setDisabled(true);
		txtFiltroEstudiante.setPlaceholder("Nombre");
	}


	/**
	 * Método que filtra los datos del estudiante por 
	 * cedula, nombre y apellido
	 */			
	@Command
	@NotifyChange({ "modeloEstudianteRetirado" })
	public void filtrarEstudiante() {
		try {
			List<ListaEstudianteRetiradoProyecto> aux = new ArrayList<ListaEstudianteRetiradoProyecto>();
			aux = listestudianteretirado;
			modeloEstudianteRetirado.clear();

			if (seleccion == null || seleccion == "") {
				if (texto == "")
					modeloEstudianteRetirado.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstudianteRetirado.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Cédula") {
					if (texto == "")
						modeloEstudianteRetirado.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getCedula().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloEstudianteRetirado.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Nombre") {
						if (texto == "")
							modeloEstudianteRetirado.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getNombre().toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloEstudianteRetirado.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Apellido") {
							if (texto == "")
								modeloEstudianteRetirado.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getApellido()
											.toLowerCase().contains(texto.toLowerCase())) {
										modeloEstudianteRetirado.add(aux.get(i));

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
			txtFiltroEstudiante.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroEstudiante.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido") {
					txtFiltroEstudiante.setPlaceholder("Apellido");
					popOpciones.close();
				} 
			}
		}
	}


	/**
	 * Método que llena los datos de forma dinámica
	 * de acuerdo a la selección.
	 */			
	@Command("llenarListaDinamica")
	@NotifyChange({"selectedPrograma","selectedProyecto","selectedCodigoMotivo"})
	public void llenarListaDinamica() {
		modeloEstudianteRetirado.clear();
		
		
		String condicion=" where   e.direccion_codigo = dp.direccion_codigo AND pp.estudiante_cedula = e.estudiante_cedula AND ip.preinscripcion_proyecto_codigo = pp.preinscripcion_proyecto_codigo AND ip.inscripcion_proyecto_codigo = r.inscripcion_proyecto_codigo AND py.proyecto_codigo = ip.proyecto_codigo AND r.motivo_codigo = m.motivo_codigo"; 
		/**
		 * Guarda la Seleccion que haga el usuario en la variable codigoDireccionPrograma
		 */
		if(selectedPrograma!=null){
			condicion=condicion+" and dp.direccion_codigo="+ "'"+Integer.toString(selectedPrograma.getDireccionCodigo())+"'";
			desbloquear();
		}

		/**
		 * Guarda la Seleccion que haga el usuario en la variable codigoProyecto
		 */
		if(selectedProyecto!=null){
			condicion=condicion+" and py.proyecto_codigo="+ "'"+(selectedProyecto.getProyectoCodigo())+"'";
			desbloquear();
		}
		if(selectedCodigoMotivo!=null){
			condicion=condicion+" and m.motivo_codigo  ="+ "'"+Integer.toString(selectedCodigoMotivo.getMotivoCodigo())+ "'";
			desbloquear();
		}

		listestudianteretirado = sListadoEstudianteRetiradoProyecto.buscarEstudiantesRetiradoProyecto(condicion);
		
		if(listestudianteretirado.size()!=0){
			modeloEstudianteRetirado.addAll(listestudianteretirado);
			win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
			Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
			tab.setSelected(true);
			System.out.println("condicion "+condicion);
		}else{
			Exportar.setDisabled(true);
		}
		
		
	}

	/**			
	 *Método que carga el reporte			
	 **/
	@Command("showReport")
	@NotifyChange({"reportConfig","prueba"})
	public void showReport() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if(selectedPrograma==null && selectedProyecto==null && selectedCodigoMotivo==null){
			mensajeEmergente.advertenciaCriterioBusqueda();
		}else{
			reportConfig = new ReportConfig(ruta);
			if(selectedProyecto!=null){
				reportConfig.getParameters().put("proyecto", selectedProyecto.getProyectoNombre());
			}else{
				reportConfig.getParameters().put("proyecto", " ");
			}
			reportConfig.setType(reportType);
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloEstudianteRetirado));
			Exportar.setDisabled(true);

		}
	}

	/**				
	 * Método que Carga en el combo de proyecto de acuerdo al programa que se seleccione			
	 **/
	@NotifyChange({ "modeloProyecto" })
	@Command
	public void cargarProyecto() {
		selectedProyecto=null;
		selectedCodigoMotivo=null;
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
	 * Método que Carga el combo motivo			
	 **/
	@NotifyChange({ "modeloMotivo" })
	@Command
	public void cargarMotivo() {
		modeloMotivo.clear();
		List<Motivo> listamotivo = smotivo.buscarTodo();
		modeloMotivo.addAll(listamotivo);
		llenarListaDinamica();
	}

	/**				
	 * Método que cierra la ventana			
	 **/		
	@Command
	public void cerrar() {
		win.detach();
	}

	/**				
	 *Método que limpia cada combo en el .zul

	 **/				
	@Command
	public void limpiarPrograma() {
		modeloPrograma.clear();
	}
	@Command
	public void limpiarMotivo() {
		modeloMotivo.clear();
	}

	@Command
	public void limpiarProyecto() {
		modeloProyecto.clear();
	}
	@Command
	public void limpiarmodelo() {
		modeloEstudianteRetirado.clear();
	}



	/**			
	 *Método que cancela el proceso

	 **/		
	@Command
	@NotifyChange({"cedula", "nombre", "apellido",
		"email","telefono"})
	public void cancelar() {
		selectedPrograma=null;
		selectedProyecto=null;
		selectedCodigoMotivo=null;
		cmbPrograma.setText("");
		cmbMotivo.setText("");
		limpiarProyecto();
		limpiarmodelo();
		win.getFellow("tab").getFellow("tabs").getFellow("pestana1").setVisible(true);
		Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
		tab.setSelected(true);
		Exportar.setDisabled(true);
	}

	/**			
	 *Método que desbloquea los botones				
	 **/
	@Command
	public void desbloquear() {
		Exportar.setDisabled(false);
		btnCancelarConsutaEstudianteH.setDisabled(false);
	}
	
	/**				
	 *	Métodos set y get
     *
	 **/			
	public ReportType getReportType() {
		return reportType;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
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
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}
	public Button getExportar() {
		return Exportar;
	}
	public void setExportar(Button exportar) {
		Exportar = exportar;
	}
	public Button getBtnCancelarConsutaEstudianteH() {
		return btnCancelarConsutaEstudianteH;
	}
	public void setBtnCancelarConsutaEstudianteH(
			Button btnCancelarConsutaEstudianteH) {
		this.btnCancelarConsutaEstudianteH = btnCancelarConsutaEstudianteH;
	}
	public Textbox getTxtFiltroEstudiante() {
		return txtFiltroEstudiante;
	}
	public void setTxtFiltroEstudiante(Textbox txtFiltroEstudiante) {
		this.txtFiltroEstudiante = txtFiltroEstudiante;
	}

	public List<ListaEstudianteRetiradoProyecto> getListestudianteretirado() {
		return listestudianteretirado;
	}
	public void setListestudianteretirado(
			List<ListaEstudianteRetiradoProyecto> listestudianteretirado) {
		this.listestudianteretirado = listestudianteretirado;
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
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public ListModelList<ReportType> getReportTypesModel() {
		return reportTypesModel;
	}
	public void setReportTypesModel(ListModelList<ReportType> reportTypesModel) {
		this.reportTypesModel = reportTypesModel;
	}
	public SDireccionPrograma getSdireccionPrograma() {
		return sdireccionPrograma;
	}
	public void setSdireccionPrograma(SDireccionPrograma sdireccionPrograma) {
		this.sdireccionPrograma = sdireccionPrograma;
	}
	public SProyecto getSproyecto() {
		return sproyecto;
	}
	public void setSproyecto(SProyecto sproyecto) {
		this.sproyecto = sproyecto;
	}
	public SMotivo getSmotivo() {
		return smotivo;
	}
	public void setSmotivo(SMotivo smotivo) {
		this.smotivo = smotivo;
	}
	public SListadoEstudianteRetiradoProyecto getsListadoEstudianteRetiradoProyecto() {
		return sListadoEstudianteRetiradoProyecto;
	}
	public void setsListadoEstudianteRetiradoProyecto(
			SListadoEstudianteRetiradoProyecto sListadoEstudianteRetiradoProyecto) {
		this.sListadoEstudianteRetiradoProyecto = sListadoEstudianteRetiradoProyecto;
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
	public void setProgramas(List<DireccionPrograma> programas) {
		this.programas = programas;
	}
	public ListModelList<ListaEstudianteRetiradoProyecto> getModeloEstudianteRetirado() {
		return modeloEstudianteRetirado;
	}
	public void setModeloEstudianteRetirado(
			ListModelList<ListaEstudianteRetiradoProyecto> modeloEstudianteRetirado) {
		this.modeloEstudianteRetirado = modeloEstudianteRetirado;
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
	public ListModelList<Motivo> getModeloMotivo() {
		return modeloMotivo;
	}
	public void setModeloMotivo(ListModelList<Motivo> modeloMotivo) {
		this.modeloMotivo = modeloMotivo;
	}
	public List<Motivo> getMotivo() {
		return motivo;
	}
	public void setMotivo(List<Motivo> motivo) {
		this.motivo = motivo;
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
	public Motivo getSelectedCodigoMotivo() {
		return selectedCodigoMotivo;
	}
	public void setSelectedCodigoMotivo(Motivo selectedCodigoMotivo) {
		this.selectedCodigoMotivo = selectedCodigoMotivo;
	}



}



