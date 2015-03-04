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
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.reportes.abiertos.ListaProfesor;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.reportes.abiertos.SListadoProfesor;

/**
 * ViewModel para la vista del reporte de Consulta de Profesores
 * Filtrado por Estatus
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteProfesorPorEstatus {

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
	private Button btnExportar;
	@Wire
	private Button btnCancelar;
	@Wire
	private Textbox txtFiltroProfesor;
	@Wire 
	Popup popOpciones;
	@Wire
	Combobox cmbEstatus;
	private Window win;

	/** 
	 * Declaración de listas y otras estructuras de datos
	 * */
	List<ListaProfesor> listProfesor= new ArrayList<ListaProfesor>();
	private List<String> valores;
	private String seleccion;
	private String nombrefiltro;

	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/abiertos/ReporteProfesorPorEstatus.jasper";


	/** 
	 * Lista que permite llenar el combo para elegir el formato 
	 * */
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList( 
					new ReportType("Word (RTF)", "rtf"), 
					new ReportType("CSV", "csv"), 
					new ReportType("OpenOffice (ODT)", "odt")));

	/** 
	 * Declaración de servicios
	 * */
	@WireVariable
	SListadoProfesor sListadoProfesor;

	@WireVariable
	SProfesor sProfesor;
	/** 
	 * Declaración del modelo a ser utilizados en el listado
	 * */
	private ListModelList<Profesor> modeloProfesor;
	private List<Profesor> profesor;
	private ListModelList<ListaProfesor> modeloProfesorPorEstatus;
	
	/** 
	 *Declaración de variable donde se guardada la selección
	 **/
	private Profesor selectedEstatus;
	@WireVariable
	private String status="";
	
	
	/**
	 * Método que inicializa los modelos,carga el estatus desde la BD 
	 * y llena los combo respectivamente
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;	
	    modeloProfesorPorEstatus = new ListModelList<ListaProfesor>();
	    valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");			
	}
	
	/**
	 * Método utilizado para inicializar los componentes gráficos
	 * de la vista .zul, en este caso coloca en marca de agua el txt del filtro
	 * de búsqueda en nombre, inhabilita los botones exportar y cancelar.
	 * @param win,ventana la cual esta asociada a este viewmodel
	 */	
	@AfterCompose
	public void AfterCompose(@ContextParam(ContextType.VIEW) Component win) {
		Selectors.wireComponents(win,this, false);
		txtFiltroProfesor.setPlaceholder("Nombre");
		btnExportar.setDisabled(true); 
		btnCancelar.setDisabled(true);
	}
	
	/**
	 * Método que llena los datos de forma dinámica
	 * de acuerdo a la selección.
	 */	
	@Command("llenarListaDinamica")
	@NotifyChange({"selectedPrograma","estatud"})
	public void llenarListaDinamica() {
		modeloProfesorPorEstatus.clear();
		String condicion="";
		/**
		 * Guarda la Selección que haga el usuario del combo del .zul
		 */
        if(status!=""){
        	condicion=" where prf.profesor_status ="+ "'"+status+"' ";
        	desbloquear();
        }
	   listProfesor = sListadoProfesor.buscarProfesoresPorEstatus(condicion);
	     
		modeloProfesorPorEstatus.addAll(listProfesor);
		win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
		Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
		tab.setSelected(true);
	}
	
	/** 
	 *Método que carga el reporte
	 **/
	 @Command("showReport")
		@NotifyChange({"reportConfig","prueba"})
		public void showReport(){
		 MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		  if(status==""  ){
			  mensajeEmergente.advertenciaCriterioBusqueda();
			}else{
			reportConfig = new ReportConfig(ruta);
			reportConfig.setType(reportType);
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloProfesorPorEstatus));
			btnExportar.setDisabled(true);
		}	
	  }   

	
	 /** 
	  *Método que cierra la ventana
	  **/
		@Command
		public void cerrar() {
			win.detach();
		}
		
		/** 
		 *Método que limpia el combo en el .zul
		 **/
		
		@Command
		public void limpiarmodelo() {
			modeloProfesorPorEstatus.clear();
		}
		
		/** 
		 *Método que cancela el proceso
		 **/
			@Command
			@NotifyChange({ "cedula", "nombre", "apellido", "email", "telefono",
					"status"})
			public void cancelar() {
				cedula ="";
				nombre="";
				apellido="";
				email="";
				telefono="";
				status="";
				limpiarmodelo();
				cmbEstatus.setText("");
				win.getFellow("tab").getFellow("tabs").getFellow("pestana1").setVisible(false);
				Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
				tab.setSelected(true);
				btnExportar.setDisabled(true);
				
			}

			/** 
			 *Método que desbloquea los botones
			 **/
			
			@Command
			public void desbloquear() {
				btnExportar.setDisabled(false);
				btnCancelar.setDisabled(false);
			}
			
			/** 
			 *Método para filtrar por código,nombre,descripción
			 **/
			
			@Command
			@NotifyChange({ "modeloProfesorPorEstatus" })
			public void filtrarProfesorEstatus() {
				List<ListaProfesor> aux = new ArrayList<ListaProfesor>();
				aux = listProfesor;
				modeloProfesorPorEstatus.clear();

				if (seleccion == null || seleccion == "") {
					if (nombrefiltro == "")
						modeloProfesorPorEstatus.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getNombre().toLowerCase()
									
									.contains(nombrefiltro.toLowerCase())) {
								modeloProfesorPorEstatus.add(aux.get(i));

							}
						}
					}

				} else {
					if (seleccion == "Cédula") {
						if (nombrefiltro == "")
							modeloProfesorPorEstatus.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getCedula().toLowerCase()
										.contains(nombrefiltro.toLowerCase())) {
									modeloProfesorPorEstatus.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Nombre") {
							if (nombrefiltro == "")
								modeloProfesorPorEstatus.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getNombre().toLowerCase()
											.contains(nombrefiltro.toLowerCase())) {
										modeloProfesorPorEstatus.add(aux.get(i));

									}
								}
							}
						} else {
							if (seleccion == "Apellido") {
								if (nombrefiltro == "")
									modeloProfesorPorEstatus.addAll(aux);
								else {
									for (int i = 0; i < aux.size(); i++) {
										if (aux.get(i).getApellido()
												.toLowerCase().contains(nombrefiltro.toLowerCase())) {
											modeloProfesorPorEstatus.add(aux.get(i));

										}
									}
								}
							} 
						}
					}
				}
			}
			
			@Command
			public void seleccionFiltro() {
				if (seleccion == "Cédula") {
					txtFiltroProfesor.setPlaceholder("Cédula");
					popOpciones.close();
				} else {
					if (seleccion == "Nombre") {
						txtFiltroProfesor.setPlaceholder("Nombre");
						popOpciones.close();
					} else {
						if (seleccion == "Apellido") {
							txtFiltroProfesor.setPlaceholder("Apellido");
							popOpciones.close();
						
						
					}
				}

			}
		}	
			
			/**
			 * Métodos set y get
			 * 
			 */
			public Profesor getSelectedEstatus() {
				return selectedEstatus;
			}
			public void setSelectedEstatus(Profesor selectedEstatus) {
				this.selectedEstatus = selectedEstatus;
			}
			public ListModelList<Profesor> getModeloProfesor() {
				return modeloProfesor;
			}
			public void setModeloProfesor(ListModelList<Profesor> modeloProfesor) {
				this.modeloProfesor = modeloProfesor;
			}
			public List<Profesor> getProfesor() {
				return profesor;
			}
			public void setProfesor(List<Profesor> profesor) {
				this.profesor = profesor;
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
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			public Button getBtnExportar() {
				return btnExportar;
			}
			public void setBtnExportar(Button btnExportar) {
				this.btnExportar = btnExportar;
			}
			public Button getBtnCancelar() {
				return btnCancelar;
			}
			public void setBtnCancelar(Button btnCancelar) {
				this.btnCancelar = btnCancelar;
			}
			public Textbox getTxtFiltroProfesor() {
				return txtFiltroProfesor;
			}
			public void setTxtFiltroProfesor(Textbox txtFiltroProfesor) {
				this.txtFiltroProfesor = txtFiltroProfesor;
			}
			public Popup getPopOpciones() {
				return popOpciones;
			}
			public void setPopOpciones(Popup popOpciones) {
				this.popOpciones = popOpciones;
			}
			public Combobox getCmbEstatus() {
				return cmbEstatus;
			}
			public void setCmbEstatus(Combobox cmbEstatus) {
				this.cmbEstatus = cmbEstatus;
			}
			public Window getWin() {
				return win;
			}
			public void setWin(Window win) {
				this.win = win;
			}
			public List<ListaProfesor> getListProfesor() {
				return listProfesor;
			}
			public void setListProfesor(List<ListaProfesor> listProfesor) {
				this.listProfesor = listProfesor;
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
			public SListadoProfesor getsListadoProfesor() {
				return sListadoProfesor;
			}
			public void setsListadoProfesor(SListadoProfesor sListadoProfesor) {
				this.sListadoProfesor = sListadoProfesor;
			}
			public SProfesor getsProfesor() {
				return sProfesor;
			}
			public void setsProfesor(SProfesor sProfesor) {
				this.sProfesor = sProfesor;
			}
			public ListModelList<ListaProfesor> getModeloProfesorPorEstatus() {
				return modeloProfesorPorEstatus;
			}
			public void setModeloProfesorPorEstatus(
					ListModelList<ListaProfesor> modeloProfesorPorEstatus) {
				this.modeloProfesorPorEstatus = modeloProfesorPorEstatus;
			}

			
			
}
