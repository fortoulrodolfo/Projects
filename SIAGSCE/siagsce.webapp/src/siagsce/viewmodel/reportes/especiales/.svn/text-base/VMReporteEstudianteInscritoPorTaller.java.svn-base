
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
import org.zkoss.zul.Popup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.data.reportes.especiales.EstudianteInscritoPorTaller;
import siagsce.modelo.general.StatusCoordinadorSce;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.general.StatusTaller;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SCoordinadorSce;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.STaller;
import siagsce.modelo.servicio.reportes.especiales.SConsultadeHorasCumplidasporEstudiantes;
import siagsce.modelo.servicio.reportes.especiales.SEstudianteInscritoPorTaller;
import siagsce.viewmodel.reportes.abiertos.ReportConfig;
import siagsce.viewmodel.reportes.abiertos.ReportType;
import siagsce.viewmodel.seguridad.SecurityUtil;

/**
 * ViewModel para la vista de reporte de Estudiantes Incritos por Taller
 * Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteEstudianteInscritoPorTaller {

	/** 
	 * Declaración de las variables del ViewModel
	 * */
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/especiales/reporteEstudianteInscritoPorTaller.jasper";
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String direccion;
	private String nombrePrograma;
	private String nombreTaller;

	/** 
	 * Declaración de Componentes de la vista
	 * */
	@Wire
	private Window win;
	@Wire
	private Button Exportar;
	@Wire
	private Button btnCancelar;
	@Wire
	private Datebox dtbInicio;
	@Wire
	private Datebox dtbFin;
	@Wire
	Combobox cmbTaller;


	/** 
	 * Lista que permite llenar el combo para elegir 
	 * el tipo de formato del archivo del respectivo reporte
	 * */ 
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
	STaller sTaller;
	@WireVariable
	SEstudianteInscritoPorTaller sEstudianteInscritoPorTaller;
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	SCoordinadorSce scoordinacorSce;
	@WireVariable
	SProyecto sproyecto;
	@WireVariable
	SActividad sactividad;
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;
	@WireVariable
	SDirectorPrograma sdirector;

	/** 
	 * Declaración de los modelos a ser utilizados en 
	 * los listados y combos
	 * */
	private ListModelList<DireccionPrograma> modeloPrograma;
	private List<DireccionPrograma> programas;
	private ListModelList<Taller> modeloTaller;
	private List<Taller> talleres;
	private ListModelList<EstudianteInscritoPorTaller> modeloEstudianteInscritoPorTaller;
	private List<EstudianteInscritoPorTaller> listestudianteInscritos;

	/** 
	 * Declaración de variables donde sera guardada las selecciones
	 *
	 * */
	private DireccionPrograma selectedPrograma;
	private Taller selectedTaller;
	private Date fechaInicio;
	private Date fechaFin;
	private List<String> valores;
	private String seleccion;
	private String nombrefiltro;
	@Wire
	private Textbox txtFiltroEstudiantesInscritos;
	@Wire 
	Popup popOpciones;

	/**ESTO PARA FORMATO DE FECHA Y PARA OBTENER LA DEL SISTEMA*/
	private Date dateFormat;
	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	Date fechaActual = new Date();
	String fechaSistema = formateador.format(fechaActual);

	
	/**
	 * Método que inicializa los modelos,carga el programa y el taller desde la BD 
	 * y llena los combos respectivamente
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */    
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		programas =buscarDireccionesXRol();
		modeloPrograma = new ListModelList<DireccionPrograma>(programas);	
		modeloEstudianteInscritoPorTaller = new ListModelList<EstudianteInscritoPorTaller>();

		talleres =sTaller.buscarTodo(StatusTaller.Activo.toString());
		modeloTaller = new ListModelList<Taller>(talleres);	
		modeloEstudianteInscritoPorTaller = new ListModelList<EstudianteInscritoPorTaller>();

		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		valores.add("Programa");
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
	 * de acuerdo a la selección.
	 */
	@Command("llenarListaDinamica")
	@NotifyChange({"selectedPrograma", "selectedTaller","fechaInicio","fechaFin"})
	public void llenarListaDinamica() {

		modeloEstudianteInscritoPorTaller.clear();
		String condicion=" where aa.direccion_codigo =e.direccion_codigo and t.taller_codigo=it.taller_codigo  and it.estudiante_cedula=e.estudiante_cedula ";

		/**
		 * Guarda la Selección que haga el usuario en la variable codigoTaller
		 */

		if(selectedTaller!=null )
		{
			condicion=condicion+" and  t.taller_codigo="+ "'"+Integer.toString(selectedTaller.getTallerCodigo())+"' "; 
			desbloquear();
		}

		/**Guarda la Selección que haga el usuario en las variable fechaInicio y fechaFin        
		 * 
		 */
		/**
		 * VALIDACION LA FECHA INICIAL NO PUEDE SER MAYOR A LA FINAL
		 */
		if(selectedTaller!=null && fechaInicio!=null && fechaFin!=null)
		{
			if(fechaFin.after(fechaInicio))
			{	
				if(fechaFin.before(fechaActual))
					condicion=condicion+" and it.inscripcion_taller_fecha BETWEEN "+"'"+fechaInicio+"'"+" and "+"'"+fechaFin+"'";
				else
				{
					Messagebox.show("La Fecha final no puede ser mayor a la Fecha Actual", "Mensaje",
							Messagebox.OK, Messagebox.INFORMATION);
					   Exportar.setDisabled(true);
				}
			}
			else
			{
				MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
				mensajeEmergente.advertenciaValorFechas();
				Exportar.setDisabled(true);
			}
			System.nanoTime();
			fechaFin=null;
			fechaInicio=null;
		}

		/**
		 * Guarda la Selección que haga el usuario del combo estatico de EstudianteIncritoPorTaller.zul
		 */
		listestudianteInscritos = sEstudianteInscritoPorTaller.buscarEstudianteInscritoPorTalle(condicion);
		if(listestudianteInscritos.size()!=0){
			modeloEstudianteInscritoPorTaller.addAll(listestudianteInscritos);
			win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
			Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
			tab.setSelected(true);
		}else{
			MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
			mensajeEmergente.errorCamposVacios();
			Exportar.setDisabled(true);
		}

	}


	/**
	 * Método que carga la lista de estudiantes Inscritos Por Taller que 
	 * se seleccione en el combo y le pasa 
      al componete jasperReport lo necesario para pintarse en 
      la pantalla EstudianteIncritoPorTaller.zul
	 */
	@Command("showReport")
	@NotifyChange({"reportConfig","prueba"})
	public void showReport() {

		reportConfig = new ReportConfig(ruta);
		reportConfig.setType(reportType);
		reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloEstudianteInscritoPorTaller));

	}


	/**
	 * Métodos que limpian cada combo en el .zul
	 */

	@Command
	public void limpiarTaller() {
		modeloTaller.clear();
	}
	@Command
	public void limpiarmodelo() {
		modeloEstudianteInscritoPorTaller.clear();
	}


	/**
	 * Método que cierra la ventana
	 */
	@Command
	public void cerrar()
	{
		win.detach();
	}


	/**
	 * Método que cancela el proceso de la ventana
	 */	
	@Command
	public void cancelar() {
		cedula ="";
		apellido ="";
		email="";
		telefono="";
		direccion="";
		limpiarmodelo();
		if(cmbTaller.getSelectedItem()!=null){
			cmbTaller.setText("");
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
		Exportar.setDisabled(true);
	}		


	/**
	 * Vincula elementos de la interfaz gráfica con este ViewModel,inhabilita los botones
	 * exportar y canacelar,colocar marca de agua al inicio al filtro nombre
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */	
	@AfterCompose
	public void AfterCompose(@ContextParam(ContextType.VIEW) Component win) {
		Selectors.wireComponents(win, this, false);
		Exportar.setDisabled(true); 
		btnCancelar.setDisabled(true);
		txtFiltroEstudiantesInscritos.setPlaceholder("Nombre");
	}

	/**
	 * Método que desbloquea los botones
	 */		
	@Command
	public void desbloquear() {
		Exportar.setDisabled(false);
		btnCancelar.setDisabled(false);
	}


	/**
	 * Método que desbloquea los date box fecha de la vista
	 */	
	@Command
	public void desbloquearFecha() {
		dtbInicio.setDisabled(false);
		dtbFin.setDisabled(false);
	}

	/**
	 * Métodos para el filtro
	 */
	@Command
	@NotifyChange({ "modeloEstudianteInscritoPorTaller" })
	public void filtrarEstudianteInscritoPorTaller() {
		try {
			List<EstudianteInscritoPorTaller> aux = new ArrayList<EstudianteInscritoPorTaller>();
			aux = listestudianteInscritos;
			modeloEstudianteInscritoPorTaller.clear();

			if (seleccion == null || seleccion == "") {
				if (nombrefiltro == "")
					modeloEstudianteInscritoPorTaller.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getNombre().toLowerCase()
								.contains(nombrefiltro.toLowerCase())) {
							modeloEstudianteInscritoPorTaller.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Cédula") {
					if (nombrefiltro == "")
						modeloEstudianteInscritoPorTaller.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getCedula().toLowerCase()
									.contains(nombrefiltro.toLowerCase())) {
								modeloEstudianteInscritoPorTaller.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Nombre") {
						if (nombrefiltro == "")
							modeloEstudianteInscritoPorTaller.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getNombre().toLowerCase()
										.contains(nombrefiltro.toLowerCase())) {
									modeloEstudianteInscritoPorTaller.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Apellido") {
							if (nombrefiltro == "")
								modeloEstudianteInscritoPorTaller.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getApellido()
											.toLowerCase().contains(nombrefiltro.toLowerCase())) {
										modeloEstudianteInscritoPorTaller.add(aux.get(i));

									}
								}
							}
						} else {
							if (seleccion == "Programa") {
								if (nombrefiltro == "")
									modeloEstudianteInscritoPorTaller.addAll(aux);
								else {
									for (int i = 0; i < aux.size(); i++) {
										if (aux.get(i).getNombreprograma()
												.toLowerCase().contains(nombrefiltro.toLowerCase())) {
											modeloEstudianteInscritoPorTaller.add(aux.get(i));

										}
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
			txtFiltroEstudiantesInscritos.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroEstudiantesInscritos.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido"){
					txtFiltroEstudiantesInscritos.setPlaceholder("Apellido");
				popOpciones.close();
				}else {
					if (seleccion == "Programa")
						txtFiltroEstudiantesInscritos.setPlaceholder("Programa");
					popOpciones.close();
				}
			} 
		}

	}
	/** 
	 * Métodos Set y Get de las variables
	 *
	 * */
	public ListModelList<EstudianteInscritoPorTaller> getModeloEstudianteInscritoPorTaller() {
		return modeloEstudianteInscritoPorTaller;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getNombrePrograma() {
		return nombrePrograma;
	}


	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}


	public String getNombreTaller() {
		return nombreTaller;
	}


	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}


	public Button getExportar() {
		return Exportar;
	}


	public void setExportar(Button exportar) {
		Exportar = exportar;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}


	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
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


	public List<DireccionPrograma> getProgramas() {
		return programas;
	}


	public void setProgramas(List<DireccionPrograma> programas) {
		this.programas = programas;
	}


	public ListModelList<DireccionPrograma> getModeloPrograma() {
		return modeloPrograma;
	}


	public void setModeloPrograma(ListModelList<DireccionPrograma> modeloPrograma) {
		this.modeloPrograma = modeloPrograma;
	}


	public void setModeloEstudianteInscritoPorTaller(
			ListModelList<EstudianteInscritoPorTaller> modeloEstudianteInscritoPorTaller) {
		this.modeloEstudianteInscritoPorTaller = modeloEstudianteInscritoPorTaller;
	}


	public DireccionPrograma getSelectedPrograma() {
		return selectedPrograma;
	}


	public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
		this.selectedPrograma = selectedPrograma;
	}

	public ListModelList<Taller> getModeloTaller() {
		return modeloTaller;
	}


	public void setModeloTaller(ListModelList<Taller> modeloTaller) {
		this.modeloTaller = modeloTaller;
	}


	public List<Taller> getTalleres() {
		return talleres;
	}


	public void setTalleres(List<Taller> talleres) {
		this.talleres = talleres;
	}


	public Taller getSelectedTaller() {
		return selectedTaller;
	}


	public void setSelectedTaller(Taller selectedTaller) {
		this.selectedTaller = selectedTaller;
	}

	public Date getDateFormat() {
		return dateFormat;
	}


	public void setDateFormat(Date dateFormat) {
		this.dateFormat = dateFormat;
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


	public SDireccionPrograma getSdireccionPrograma() {
		return sdireccionPrograma;
	}


	public void setSdireccionPrograma(SDireccionPrograma sdireccionPrograma) {
		this.sdireccionPrograma = sdireccionPrograma;
	}


	public STaller getsTaller() {
		return sTaller;
	}


	public void setsTaller(STaller sTaller) {
		this.sTaller = sTaller;
	}


	public SEstudianteInscritoPorTaller getsEstudianteInscritoPorTaller() {
		return sEstudianteInscritoPorTaller;
	}


	public void setsEstudianteInscritoPorTaller(
			SEstudianteInscritoPorTaller sEstudianteInscritoPorTaller) {
		this.sEstudianteInscritoPorTaller = sEstudianteInscritoPorTaller;
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


	public void setReportConfig(ReportConfig reportConfig) {
		this.reportConfig = reportConfig;
	}


	public void setReportTypesModel(ListModelList<ReportType> reportTypesModel) {
		this.reportTypesModel = reportTypesModel;
	}

	public Combobox getCmbTaller() {
		return cmbTaller;
	}


	public void setCmbTaller(Combobox cmbTaller) {
		this.cmbTaller = cmbTaller;
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


	public Textbox getTxtFiltroEstudiantesInscritos() {
		return txtFiltroEstudiantesInscritos;
	}


	public void setTxtFiltroEstudiantesInscritos(
			Textbox txtFiltroEstudiantesInscritos) {
		this.txtFiltroEstudiantesInscritos = txtFiltroEstudiantesInscritos;
	}


	public Popup getPopOpciones() {
		return popOpciones;
	}


	public void setPopOpciones(Popup popOpciones) {
		this.popOpciones = popOpciones;
	}
	/** 
	 * Métodos set y get que permite manipular la vista 
	 * EstudianteInscritoPorTaller.zul
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


	public List<EstudianteInscritoPorTaller> getListestudianteInscritos() {
		return listestudianteInscritos;
	}


	public void setListestudianteInscritos(
			List<EstudianteInscritoPorTaller> listestudianteInscritos) {
		this.listestudianteInscritos = listestudianteInscritos;
	}


	public SimpleDateFormat getFormateador() {
		return formateador;
	}


	public void setFormateador(SimpleDateFormat formateador) {
		this.formateador = formateador;
	}


	public Date getFechaActual() {
		return fechaActual;
	}


	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}


	public String getFechaSistema() {
		return fechaSistema;
	}


	public void setFechaSistema(String fechaSistema) {
		this.fechaSistema = fechaSistema;
	}



}




