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
import org.zkoss.zhtml.Messagebox;
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
import siagsce.modelo.data.reportes.abiertos.ListaConsultadeEstatusdeProyectos;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.reportes.abiertos.SConsultadeEstatusdeProyectos;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.viewmodel.seguridad.SecurityUtil;



/**
 * ViewModel para la vista que muestra Consulta de Proyectos por estatus,Dirección de Programa, Estatus
 * para el Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteConsultadeEstatusdeProyectos {
	
	
	/**
	 * Declaracion de las Variables para los metodos del wielmodel
	 * 
	*/
	private String codigo;
	private String nombre;
	private String descripcion;
	private String nombreDireccion;
	private String texto;
	
	ReportType reportType = new ReportType("PDF", "pdf");
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/abiertos/reporteConsultaProyectoEstatus.jasper";
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */

	
	@Wire
	Textbox txtFiltroEstudiante;
	@Wire
	private Popup popOpciones;
	@Wire
	private Button Exportar;
	@Wire
	private Button btnCancelar;
	
	@Wire
	private Combobox cmbPrograma;
	
	@Wire
	private Combobox cmbEstatu;
	
	/** 
	 * Declaración de listas y otras estructuras de datos
	 * */
	private String seleccion;
	private List<String> valores;
	private String nombrefiltro;
	List<ListaConsultadeEstatusdeProyectos> listaproyecto= new ArrayList<ListaConsultadeEstatusdeProyectos>();
	

	/**
	 * Lista que me permite llenar el combo para elegir el formato 
	 * */
		private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
				Arrays.asList(
						new ReportType("PDF", "pdf"),
						new ReportType("HTML", "html"), 
						new ReportType("Word (RTF)", "rtf"), 
						new ReportType("Excel", "xls"), 
						new ReportType("Excel (JXL)", "jxl"), 
						new ReportType("CSV", "csv"), 
						new ReportType("OpenOffice (ODT)", "odt")));
		private Window win;
		
		
		/**
		 * Declaracion de servicios del ViewModel
		 * */
		@WireVariable
		SDireccionPrograma sdireccionPrograma;
		@WireVariable
		 SConsultadeEstatusdeProyectos sListadoConsultadeEstatusdeProyectos;
		@WireVariable
		SRepresentanteProfesoral smiembroCoordinacion ;
		@WireVariable
		SDirectorPrograma sdirector ;
		@WireVariable
		SProfesor sprofesor ;
		@WireVariable
		SProyecto sproyecto ;
		@WireVariable
		SActividad sactividad ;
		
		/**
		 * Declaracion de listas y otras estructuras de datos
		 * */
		private ListModelList<DireccionPrograma> modeloPrograma;
		private List<DireccionPrograma> programas;
		private ListModelList<ListaConsultadeEstatusdeProyectos> modeloproyectoestatud;
		//private List<ListaConsultadeEstatusdeProyectos> listaproyecto;
		
		/** 
		 *Declaracion de variables donde sera guardada las selecciones
		 **/
		private DireccionPrograma selectedPrograma;
		private Estudiante selectedEstatus;

		@WireVariable
	    private String estatud= "";



		
	
		/**
		 * Método que inicializa los modelos
		 *  y carga el programa desde la BD y el combo de programas
		 * @param win,ventana la cual esta asociada a este viewmodel
		 */
		@Init
		public void init(@ContextParam(ContextType.COMPONENT) Component win) {
			this.win = (Window) win;
			programas=buscarDireccionesXRol();
			modeloPrograma = new ListModelList<DireccionPrograma>(programas);	
		    modeloproyectoestatud = new ListModelList<ListaConsultadeEstatusdeProyectos>();
		    valores = new ArrayList<String>();
			valores.add("Código");
			valores.add("Nombre");
			valores.add("Descripción");			
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
		 * de búsqueda en nombre,inhabilita los botones esportar y cancelar.
		 * @param view,ventana la cual esta asociada a este viewmodel
		 */	
		@AfterCompose
		public void AfterCompose(@ContextParam(ContextType.VIEW) Component win) {
			Selectors.wireComponents(win,this, false);
			txtFiltroEstudiante.setPlaceholder("Nombre");
			Exportar.setDisabled(true); 
			btnCancelar.setDisabled(true);
		}
		
		/**
		 * Método que llena los datos de forma dinámica
		 **/	
		@Command("llenarListaDinamica")
		@NotifyChange({"selectedPrograma","estatud"})
		public void llenarListaDinamica() {
			modeloproyectoestatud.clear();
	        String condicion="where pp.direccion_codigo = dp.direccion_codigo and p.proyecto_codigo = pp.proyecto_codigo";
			
	        
	        /**
	 		 * Guarda la Seleccion que haga el usuario en la variable direccion_codigo
	 		 **/
	        if(selectedPrograma!=null){
	        	condicion=condicion+" and dp.direccion_codigo="+ "'"+Integer.toString(selectedPrograma.getDireccionCodigo())+"'";
	        	desbloquear();
	        }
	        
	        /**
	 		 * Guarda la Seleccion que haga el usuario del combo estatico de ListEst.zul
	 		 **/
			
	        if(estatud!=""){
	        	condicion=condicion+" and p.proyecto_status ="+ "'"+estatud+"'";
	        	desbloquear();
	        }
		   listaproyecto = sListadoConsultadeEstatusdeProyectos.buscarProyectoDireccionProgramaDinamico(condicion);
		     
			if(listaproyecto.size()!=0){
				modeloproyectoestatud.addAll(listaproyecto);
				win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
				Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
				tab.setSelected(true);
				System.out.println(condicion);
			}else{
				Exportar.setDisabled(true);
			}   
		   
		}

		
		
		/** 
		 *Método que carga el reporte
		 **/
		 @Command("showReport")
			@NotifyChange({"reportConfig","prueba"})
			public void showReport(){
			  if(codigo==""  ){
					Messagebox.show("Seleccione al menos un Criterio de Busqueda ", "Mensaje",
							Messagebox.OK, Messagebox.INFORMATION);
				}else{
				reportConfig = new ReportConfig(ruta);
				reportConfig.setType(reportType);
				reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloproyectoestatud));
				Exportar.setDisabled(true);
			}	
		  }   
		 
		 /** 
		  *Método que cierra la ventana
		  **/
			@Command
			public void salir() {
				win.detach();
			}
			
			@Command
			public void cerrar() {
				win.detach();
			}
			
			
			/** 
			 *Método que limpia cada combo en el .zul
			 **/
			
			/*@Command
			public void limpiarPrograma() {
				modeloPrograma.clear();
			}*/

			
			@Command
			public void limpiarmodelo() {
				modeloproyectoestatud.clear();
			}
			
			
			/** 
			 *Método que cancela el proceso
			 **/
				@Command
				@NotifyChange({ "codigo", "nombre", "descripcion",
						"estatud"})
				public void cancelar() {
					codigo ="";
					nombre="";
					descripcion ="";
					estatud="";
					limpiarmodelo();
					cmbPrograma.setText("");
					cmbEstatu.setText("");
					win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
					//win.getFellow("tab").getFellow("tabs").getFellow("pestana1").setVisible(true);
					Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
					tab.setSelected(true);
					
				}
				
	
				/** 
				 *Método que desbloquea los botones
				 **/
				
				@Command
				public void desbloquear() {
					Exportar.setDisabled(false);
					btnCancelar.setDisabled(false);
				}
				
				/** 
				 *Método para filtrar por código,nombre,descripción
				 **/
				
				@Command
				@NotifyChange({ "modeloproyectoestatud" })
				public void filtrarProyecto() {
					List<ListaConsultadeEstatusdeProyectos> aux = new ArrayList<ListaConsultadeEstatusdeProyectos>();
					aux = listaproyecto;
					modeloproyectoestatud.clear();

					if (seleccion == null || seleccion == "") {
						if (nombrefiltro == "")
							modeloproyectoestatud.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								System.out.println(nombrefiltro.toLowerCase());
								if (aux.get(i).getNombre().toLowerCase()
										
										.contains(nombrefiltro.toLowerCase())) {
									modeloproyectoestatud.add(aux.get(i));

								}
							}
						}

					} else {
						if (seleccion == "Código") {
							if (nombrefiltro == "")
								modeloproyectoestatud.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getCodigo().toLowerCase()
											.contains(nombrefiltro.toLowerCase())) {
										modeloproyectoestatud.add(aux.get(i));

									}
								}
							}
						} else {
							if (seleccion == "Nombre") {
								if (nombrefiltro == "")
									modeloproyectoestatud.addAll(aux);
								else {
									for (int i = 0; i < aux.size(); i++) {
										if (aux.get(i).getNombre().toLowerCase()
												.contains(nombrefiltro.toLowerCase())) {
											modeloproyectoestatud.add(aux.get(i));

										}
									}
								}
							} else {
								if (seleccion == "Descripción") {
									if (nombrefiltro == "")
										modeloproyectoestatud.addAll(aux);
									else {
										for (int i = 0; i < aux.size(); i++) {
											if (aux.get(i).getDescripcion()
													.toLowerCase().contains(nombrefiltro.toLowerCase())) {
												modeloproyectoestatud.add(aux.get(i));

											}
										}
									}
								} else {
									if (seleccion.toLowerCase() == "Nombre de la Direccion") {
										if (nombrefiltro == "")
											modeloproyectoestatud.addAll(aux);
										else {
											for (int i = 0; i < aux.size(); i++) {
												if (aux.get(i).getNombreDireccion().toLowerCase()
														.contains(nombrefiltro.toLowerCase())) {
													modeloproyectoestatud.add(aux.get(i));

												}
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
					System.out.print(seleccion);
					if (seleccion == "Código") {
						txtFiltroEstudiante.setPlaceholder("Código");
						popOpciones.close();
					} else {
						if (seleccion == "Nombre") {
							txtFiltroEstudiante.setPlaceholder("Nombre");
							popOpciones.close();
						} else {
							if (seleccion == "Descripción") {
								txtFiltroEstudiante.setPlaceholder("Descripción");
								popOpciones.close();
							
							
						}
					}

				}
			}
				  /**
				   * Métodos set y get
				  * */
			  

				public String getNombreDireccion() {
					return nombreDireccion;
				}

				public String getNombrefiltro() {
				return nombrefiltro;
			}

			public void setNombrefiltro(String nombrefiltro) {
				this.nombrefiltro = nombrefiltro;
			}

				public void setNombreDireccion(String nombreDireccion) {
					this.nombreDireccion = nombreDireccion;
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

			public DireccionPrograma getSelectedPrograma() {
				return selectedPrograma;
			}

				public String getRuta() {
				return ruta;
			}

			public void setRuta(String ruta) {
				this.ruta = ruta;
			}

			public String getCodigo() {
				return codigo;
			}

			public void setCodigo(String codigo) {
				this.codigo = codigo;
			}

			public String getNombre() {
				return nombre;
			}

			public void setNombre(String nombre) {
				this.nombre = nombre;
			}

			public String getDescripcion() {
				return descripcion;
			}

			public void setDescripcion(String descripcion) {
				this.descripcion = descripcion;
			}

			public Textbox getTxtFiltroEstudiante() {
				return txtFiltroEstudiante;
			}

			public void setTxtFiltroEstudiante(Textbox txtFiltroEstudiante) {
				this.txtFiltroEstudiante = txtFiltroEstudiante;
			}

			public Popup getPopOpciones() {
				return popOpciones;
			}

			public void setPopOpciones(Popup popOpciones) {
				this.popOpciones = popOpciones;
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

			public Combobox getCmbPrograma() {
				return cmbPrograma;
			}

			public void setCmbPrograma(Combobox cmbPrograma) {
				this.cmbPrograma = cmbPrograma;
			}

			public SDireccionPrograma getSdireccionPrograma() {
				return sdireccionPrograma;
			}

			public void setSdireccionPrograma(SDireccionPrograma sdireccionPrograma) {
				this.sdireccionPrograma = sdireccionPrograma;
			}

			public SConsultadeEstatusdeProyectos getsListadoConsultadeEstatusdeProyectos() {
				return sListadoConsultadeEstatusdeProyectos;
			}

			public void setsListadoConsultadeEstatusdeProyectos(
					SConsultadeEstatusdeProyectos sListadoConsultadeEstatusdeProyectos) {
				this.sListadoConsultadeEstatusdeProyectos = sListadoConsultadeEstatusdeProyectos;
			}

			public List<ListaConsultadeEstatusdeProyectos> getListaproyecto() {
				return listaproyecto;
			}

			public void setListaproyecto(
					List<ListaConsultadeEstatusdeProyectos> listaproyecto) {
				this.listaproyecto = listaproyecto;
			}

			public void setReportConfig(ReportConfig reportConfig) {
				this.reportConfig = reportConfig;
			}

			public void setReportTypesModel(ListModelList<ReportType> reportTypesModel) {
				this.reportTypesModel = reportTypesModel;
			}

				public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
					this.selectedPrograma = selectedPrograma;
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

				public ListModelList<ListaConsultadeEstatusdeProyectos> getModeloproyectoestatud() {
					return modeloproyectoestatud;
				}

				public void setModeloproyectoestatud(
						ListModelList<ListaConsultadeEstatusdeProyectos> modeloproyectoestatud) {
					this.modeloproyectoestatud = modeloproyectoestatud;
				}

				public String getEstatud() {
					return estatud;
				}

				public void setEstatud(String estatud) {
					this.estatud = estatud;
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
				

				
				


