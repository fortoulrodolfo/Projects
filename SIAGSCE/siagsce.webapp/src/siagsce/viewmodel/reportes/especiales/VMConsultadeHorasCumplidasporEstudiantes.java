package siagsce.viewmodel.reportes.especiales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

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


import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.reportes.especiales.ListaConsultadeHorascumplidasporEstudiantes;
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
import siagsce.modelo.servicio.reportes.especiales.SConsultadeHorasCumplidasporEstudiantes;
import siagsce.viewmodel.reportes.abiertos.ReportConfig;
import siagsce.viewmodel.reportes.abiertos.ReportType;
import siagsce.viewmodel.seguridad.SecurityUtil;



/**
 * ViewModel para la vista que muestra Consulta de Horas cumplidas por Estudiantes,Dirección de Programa, Proyecto
 * para el Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConsultadeHorasCumplidasporEstudiantes {
	
	/**
	 * Declaracion de las Variables para los metodos del wielmodel
	 * 
	*/
	private String cedula;
	private String nombre;
	private String apellido;
	private String descripcionActvidad;
	private String horasCumplidas;
	private String texto;
	private Profesor profesor;

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
	
	/** 
	 * Declaración de listas y otras estructuras de datos
	 * */
	List<ListaConsultadeHorascumplidasporEstudiantes> listestudiantehorasc= new ArrayList<ListaConsultadeHorascumplidasporEstudiantes>();
	private List<String> valores;
	private String nombrefiltro;
	private String seleccion;
	
	
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/especiales/reporteConsultadeHorasCumplidasporEstudiante.jasper";
	
	
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
		SProfesor sprofesor;
		
		@WireVariable
		SProyecto sproyecto;
		
		@WireVariable
		SActividad sactividad;
		
		@WireVariable
		SCoordinadorSce scoordinacorSce;
		
		@WireVariable
		SConsultadeHorasCumplidasporEstudiantes sEstudianteHorasC;
		
		@WireVariable
		SRepresentanteProfesoral smiembroCoordinacion;
		@WireVariable
		SDirectorPrograma sdirector;
		/**
		 * Declaracion de listas y otras estructuras de datos
		 * */		
		private ListModelList<DireccionPrograma> modeloPrograma;
		private ListModelList<Proyecto> modeloProyecto;
		private ListModelList<Actividad> modeloActividad;
		
		private List<Proyecto> proyectosResponsable= new ArrayList<Proyecto>();
		private List<Proyecto> proyectosParticipante= new ArrayList<Proyecto>();
		private List<Actividad> actividades;
		private ListModelList<ListaConsultadeHorascumplidasporEstudiantes> modeloEstudianteHorasC;
		//private List<ListaConsultadeHorascumplidasporEstudiantes> listestudiantehorasc;
		
		/** 
		 *Declaracion de variables donde sera guardada las selecciones
		 **/

		private DireccionPrograma selectedPrograma;
		private Proyecto selectedProyecto;
	
		private Actividad selectedActividad;
		//private ListaConsultadeHorascumplidasporEstudiantes selectedEstudinateH;
		
		
		
		
		/**
		 * Método que inicializa los modelos
		 *  y carga el programa desde la BD y el combo de programas
		 * @param win,ventana la cual esta asociada a este viewmodel
		 */
		
		@Init
		public void init(@ContextParam(ContextType.COMPONENT) Component win) {
			this.win = (Window) win;
		    profesor = sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
			List<DireccionPrograma> programas;
			actividades=new ArrayList<Actividad>();
			programas = buscarDireccionesXRol();
			modeloPrograma = new ListModelList<DireccionPrograma>(programas);	
			modeloEstudianteHorasC = new ListModelList<ListaConsultadeHorascumplidasporEstudiantes>();
			valores = new ArrayList<String>();
			valores.add("Cédula");
			valores.add("Nombre");
			valores.add("Apellido");
			modeloActividad = new ListModelList<Actividad>();	
			modeloProyecto = new ListModelList<Proyecto>();	
			modeloEstudianteHorasC = new ListModelList<ListaConsultadeHorascumplidasporEstudiantes>();
			
	
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
		public void insertarActividadesResponsableProyecto(List<Actividad> actividades,List<Proyecto> proyectosResponsable){
			boolean existe;
			for(Proyecto proyectoResponsable:proyectosResponsable){
				List<Actividad> listActividades=sactividad.buscarporProyecto(proyectoResponsable);
				for(int j=0;j<listActividades.size();j++){
					existe=false;
				for(int i=0;i<actividades.size();i++){
					if(listActividades.get(j).getActividadCodigo().compareTo(actividades.get(i).getActividadCodigo())==0){
						existe=true;
					}
				}
				if(!existe)
					actividades.add(listActividades.get(j));
			  }
			}
		}
		public void insertarproyectosParticipante(List<Proyecto> proyectos,List<Proyecto> proyectosDelParticipante){
			boolean existe;
			for(Proyecto proyectoParticipante:proyectosDelParticipante){
				existe=false;
				for(int i=0;i<proyectos.size();i++){
					if(proyectos.get(i).getProyectoCodigo().equals(proyectoParticipante.getProyectoCodigo())){
						existe=true;
					}
				}
				if(!existe)
					proyectos.add(proyectoParticipante);
			}
		}
		public List<DireccionPrograma>  buscarDireccionesRolResponsable(){
			List<DireccionPrograma> direcciones=new ArrayList<DireccionPrograma>();
			List<Proyecto> proyectos=sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
			for(Proyecto proyecto:proyectos){
				if(direcciones.isEmpty()){
					direcciones.addAll(proyecto.getDireccionPrograma());
				}else{
					verificarExistenciaDireccion(proyecto,direcciones);
				}
			}
			return direcciones;
			
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
		/*

		@AfterCompose
		public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
			this.win = (Window) win;
			Selectors.wireComponents(view, this, false);
			txtFiltroEstudiante.setPlaceholder("nombre");
		}*/

		/**
		 * Método que llena los datos de forma dinámica
		 **/
				
		@Command("llenarListaDinamica")
		@NotifyChange({"selectedPrograma","selectedProyecto","selectedActividad"})
		public void llenarListaDinamica() {
			modeloEstudianteHorasC.clear();
	         String condicion="";
			//Guarda la Seleccion que haga el usuario en la variable codigoDireccionPrograma
	     	/**
	 		 * Guarda la Seleccion que haga el usuario en la variable direccion_codigo
	 		 **/
	         
	         if(selectedPrograma!=null){
	        	condicion=condicion+" and d.direccion_codigo="+ "'"+Integer.toString(selectedPrograma.getDireccionCodigo())+"'";
	        	desbloquear();
	        }
	        
	        
	         /**
		 		 * Guarda la Seleccion que haga el usuario en la variable proyecto_codigo
		 		 **/
	        if(selectedProyecto!=null){
	        	condicion=condicion+" and i.proyecto_codigo="+ "'"+(selectedProyecto.getProyectoCodigo())+"'";
	        	desbloquear();
	        }
	        
			 listestudiantehorasc = sEstudianteHorasC.buscarEstudiantesporHorasCumplidas(condicion);
		     
			 if(listestudiantehorasc.size()!=0){
				 	modeloEstudianteHorasC.addAll(listestudiantehorasc);
					win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
					Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
					tab.setSelected(true);
					System.out.println(condicion);
				}else{
					Exportar.setDisabled(true);
				}  
			 
		}
		
		
		/** 
		 *Metodo carga el reporte
		 **/
		
		
		  @Command("showReport")
			@NotifyChange({"reportConfig","prueba"})
			public void showReport(){
			  if(cedula=="" || nombre == "" || apellido== "" || descripcionActvidad== "" || horasCumplidas== ""){
					Messagebox.show("Seleccione al menos un Criterio de Búsqueda ", "Mensaje",
							Messagebox.OK, Messagebox.INFORMATION);
				}else{
				reportConfig = new ReportConfig(ruta);
				reportConfig.setType(reportType);
				reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloEstudianteHorasC));
				Exportar.setDisabled(true);
			}	
		  }    
		
			@Command
			public void cerrar() {
				win.detach();
			}
			
			/** 
			 *Metodo que Carga en el combo de proyecto de acuerdo al programa que se seleccione
			 **/
					
			@NotifyChange({ "modeloProyecto","modeloActividad"})
			@Command
			public void cargarProyecto() {
				selectedProyecto=null;
				selectedActividad=null;
				obtenerProyectosXProgramaYProfesor();
				btnCancelar.setDisabled(false);
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
			public void filtrarActividadesPorDireccion(ListModelList<Actividad> actividades,DireccionPrograma direccionPrograma){
				boolean existe;
				int i=0,nroActividades=actividades.size();
				while(i<nroActividades){
					existe=false;
					List<DireccionPrograma> direcciones=sproyecto.buscarProyectoPorActividad(actividades.get(i)).getDireccionPrograma();
					for(int j=0;j<direcciones.size();j++){
						if(direcciones.get(j).getDireccionCodigo().compareTo(direccionPrograma.getDireccionCodigo())==0){
							existe=true;
						}
					}
					if(!existe){
						actividades.remove(i);
						nroActividades--;
						continue;
					}
					i++;
				}
			}
			
			
			@NotifyChange({ "modeloActividad" })
			@Command
			public void cargarActividad() {
				modeloActividad.clear();
				modeloActividad.addAll( buscarActividadesXRol());
				llenarListaDinamica();
		   }
			/**
			 * Método que aplica la seguridad funcional en el reporte
			 * para buscar las actividades dado el rol que posea el usuario.
			 * @return lista de activi de programa
			 **/
			public List<Actividad> buscarActividadesXRol(){
				List<Actividad> actividades=new ArrayList<Actividad>();
				String cedula= SecurityUtil.nombreUsuario;
				Profesor profesor=sprofesor.buscarPorCedula(cedula);	
				Profesor resp=sproyecto.buscarResponsableProyecto(profesor,selectedProyecto.getProyectoCodigo());
				if(SecurityUtil.verificarRol("Coordinacion SCE") || SecurityUtil.verificarRol("Director") || SecurityUtil.verificarRol("Representante Profesoral")){
					actividades=sactividad.buscarporProyecto(selectedProyecto);
				}
				else{
					if(resp!=null){
						actividades=sactividad.buscarporProyecto(selectedProyecto);
					}else if(SecurityUtil.verificarRol("Participante Actividad")){
						actividades=sactividad.buscarActividadesDeUnProfesorParticipanteDeUnProyecto(profesor,selectedProyecto);
					}
				} 
				return actividades;
			}
			

			
		

			/** 
			 *Metodo que limpia cada combo en el .zul
			 **/

							
			/*@Command
			public void limpiarPrograma() {
				modeloPrograma.clear();
			}*/

			@Command
			public void limpiarProyecto() {
				modeloProyecto.clear();
			}
			@Command
			public void limpiarActividad() {
				modeloActividad.clear();
			}
			@Command
			public void limpiarmodelo() {
				modeloEstudianteHorasC.clear();
			}
			
			
			/** 
			 *Metodo que cancela el proceso
			 **/

	
			@Command
			@NotifyChange({ "direccionNombre", "proyectoNombre",
					"cedula", "nombres", "apellido",
					"descripcionActvidad","horasCumplidas"})
			public void cancelar() {
				cedula ="";
				nombre="";
				apellido ="";
				descripcionActvidad="";
				horasCumplidas="";
				//limpiarPrograma();
				limpiarProyecto();
				limpiarmodelo();
				limpiarActividad();
				cmbPrograma.setText("");
				win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
				//win.getFellow("tab").getFellow("tabs").getFellow("pestana1").setVisible(true);
				Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
				tab.setSelected(true);
				Exportar.setDisabled(true);
				
			}
			
			/** 
			 *Metodo que desbloquea los botones
			 **/
			
			@Command
			public void desbloquear() {
				Exportar.setDisabled(false);
				btnCancelar.setDisabled(false);
			}
			
			/** 
			 *Metodo para filtrar
			 **/
			
			@Command
			@NotifyChange({ "modeloEstudianteHorasC" })
			public void filtrarEstudiante() {
				try {
				List<ListaConsultadeHorascumplidasporEstudiantes> aux = new ArrayList<ListaConsultadeHorascumplidasporEstudiantes>();
				aux = listestudiantehorasc;
				modeloEstudianteHorasC.clear();

				if (seleccion == null || seleccion == "") {
					if (nombrefiltro  == "")
						modeloEstudianteHorasC.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							//System.out.println(nombrefiltro .toLowerCase());
							if (aux.get(i).getNombre().toLowerCase()
									
									.contains(nombrefiltro.toLowerCase())) {
								modeloEstudianteHorasC.add(aux.get(i));

							}
						}
					}

				} else {
					if (seleccion == "Cédula") {
						if (nombrefiltro  == "")
							modeloEstudianteHorasC.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getCedula().toLowerCase()
										.contains(nombrefiltro .toLowerCase())) {
									modeloEstudianteHorasC.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Nombre") {
							if (nombrefiltro  == "")
								modeloEstudianteHorasC.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getNombre().toLowerCase()
											.contains(nombrefiltro .toLowerCase())) {
										modeloEstudianteHorasC.add(aux.get(i));

									}
								}
							}
						} else {
							if (seleccion == "Apellido") {
								if (nombrefiltro  == "")
									modeloEstudianteHorasC.addAll(aux);
								else {
									for (int i = 0; i < aux.size(); i++) {
										if (aux.get(i).getApellido()
												.toLowerCase().contains(nombrefiltro.toLowerCase())) {
											modeloEstudianteHorasC.add(aux.get(i));

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
				if (seleccion=="Nombre") {
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
			 * Métodos Set y Get
			 **/
			
			
			public String getRuta() {
				return ruta;
			}
			
			
	public String getCedula() {
				return cedula;
			}


			public void setCedula(String cedula) {
				this.cedula = cedula;
			}


			public String getApellido() {
				return apellido;
			}


			public void setApellido(String apellido) {
				this.apellido = apellido;
			}


			public String getDescripcionActvidad() {
				return descripcionActvidad;
			}


			public void setDescripcionActvidad(String descripcionActvidad) {
				this.descripcionActvidad = descripcionActvidad;
			}


			public String getHorasCumplidas() {
				return horasCumplidas;
			}


			public void setHorasCumplidas(String horasCumplidas) {
				this.horasCumplidas = horasCumplidas;
			}


			public String getNombrefiltro() {
				return nombrefiltro;
			}


			public void setNombrefiltro(String nombrefiltro) {
				this.nombrefiltro = nombrefiltro;
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


	public Popup getPopOpciones() {
		return popOpciones;
	}


	public void setPopOpciones(Popup popOpciones) {
		this.popOpciones = popOpciones;
	}





	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}




	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/*
	public List<ListaConsultadeHorascumplidasporEstudiantes> getEstudianteHoras() {
		return EstudianteHoras;
	}


	public void setEstudianteHoras(
			List<ListaConsultadeHorascumplidasporEstudiantes> estudianteHoras) {
		EstudianteHoras = estudianteHoras;
	}
	*/

	public String getSeleccion() {
		return seleccion;
	}


	public List<String> getValores() {
		return valores;
	}


	public void setValores(List<String> valores) {
		this.valores = valores;
	}


	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}


	public SActividad getSactividad() {
		return sactividad;
	}

	/*
	public ListaConsultadeHorascumplidasporEstudiantes getSelectedEstudinateH() {
		return selectedEstudinateH;
	}


	public void setSelectedEstudinateH(
			ListaConsultadeHorascumplidasporEstudiantes selectedEstudinateH) {
		this.selectedEstudinateH = selectedEstudinateH;
	}

	*/
	public void setSactividad(SActividad sactividad) {
		this.sactividad = sactividad;
	}


	public ListModelList<Actividad> getModeloActividad() {
		return modeloActividad;
	}


	public void setModeloActividad(ListModelList<Actividad> modeloActividad) {
		this.modeloActividad = modeloActividad;
	}


	public Actividad getSelectedActividad() {
		return selectedActividad;
	}


	public void setSelectedActividad(Actividad selectedActividad) {
		this.selectedActividad = selectedActividad;
	}


			public List<ListaConsultadeHorascumplidasporEstudiantes> getListestudiantehorasc() {
		return listestudiantehorasc;
	}


	public void setListestudiantehorasc(
			List<ListaConsultadeHorascumplidasporEstudiantes> listestudiantehorasc) {
		this.listestudiantehorasc = listestudiantehorasc;
	}


			public SProyecto getSproyecto() {
				return sproyecto;
			}
			public void setSproyecto(SProyecto sproyecto) {
				this.sproyecto = sproyecto;
			}
			public void setRuta(String ruta) {
				this.ruta = ruta;
			}
			public SDireccionPrograma getSdireccionPrograma() {
				return sdireccionPrograma;
			}
			public void setSdireccionPrograma(SDireccionPrograma sdireccionPrograma) {
				this.sdireccionPrograma = sdireccionPrograma;
			}
			
			public SConsultadeHorasCumplidasporEstudiantes getsEstudianteHorasC() {
				return sEstudianteHorasC;
			}
			public void setsEstudianteHorasC(SConsultadeHorasCumplidasporEstudiantes sEstudianteHorasC) {
				this.sEstudianteHorasC = sEstudianteHorasC;
			}
			public ListModelList<DireccionPrograma> getModeloPrograma() {
				return modeloPrograma;
			}
			public void setModeloPrograma(ListModelList<DireccionPrograma> modeloPrograma) {
				this.modeloPrograma = modeloPrograma;
			}
			public ListModelList<Proyecto> getModeloProyecto() {
				return modeloProyecto;
			}
			public void setModeloProyecto(ListModelList<Proyecto> modeloProyecto) {
				this.modeloProyecto = modeloProyecto;
			}
			public List<Proyecto> getProyecto() {
				return proyectosResponsable;
			}
			
			public void setProyecto(List<Proyecto> proyecto) {
				this.proyectosResponsable = proyecto;
			}
			public ListModelList<ListaConsultadeHorascumplidasporEstudiantes> getModeloEstudianteHorasC() {
				return modeloEstudianteHorasC;
			}
			public void setModeloEstudianteHorasC(
					ListModelList<ListaConsultadeHorascumplidasporEstudiantes> modeloEstudianteHorasC) {
				this.modeloEstudianteHorasC = modeloEstudianteHorasC;
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
			
			
			/**
			 * Metodos set y get que me permite manipular la vista ListEst.zul
			 **/
				
			
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
			public ListModelList<ReportType> getReportTypesModel() {
				return reportTypesModel;
			}
			public void setReportTypesModel(ListModelList<ReportType> reportTypesModel) {
				this.reportTypesModel = reportTypesModel;
			}
			

	public Textbox getTxtFiltroEstudiante() {
				return txtFiltroEstudiante;
			}


			public void setTxtFiltroEstudiante(Textbox txtFiltroEstudiante) {
				this.txtFiltroEstudiante = txtFiltroEstudiante;
			}

		}
