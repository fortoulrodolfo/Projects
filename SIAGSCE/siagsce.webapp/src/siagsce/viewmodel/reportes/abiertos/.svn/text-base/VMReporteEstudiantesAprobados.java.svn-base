package siagsce.viewmodel.reportes.abiertos;

import java.util.ArrayList;
import java.util.List;

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
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.reportes.abiertos.ListaEstudianteAprobado;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.reportes.abiertos.SListadoEstudianteAprobado;
import siagsce.viewmodel.seguridad.SecurityUtil;

/**
 * ViewModel para la vista de Carta de culminación en el 
 * Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteEstudiantesAprobados {
	
	/**
	 * Declaración de variables del viewModel
	 */
	ReportType reportType = new ReportType("PDF", "pdf");
	private ReportConfigCartaCulminacion reportConfigCartaCulminacion = null;
	private String ruta="/WEB-INF/siagsce/reportes/abiertos/CartaCulminacion.jasper";
	@Wire
	private Textbox txtFiltroEstudiantesAprobados;
	@Wire 
	Combobox cmbPrograma;
	@Wire 
	Popup popOpciones;
	private Window win;
	List<ListaEstudianteAprobado> listestudiante= new ArrayList<ListaEstudianteAprobado>();
	private List<String> valores;
	private String seleccion;
	private String nombre;
	
	/**
	 * Declaración de los servicios
	 */
	@WireVariable
	SDireccionPrograma sdireccionPrograma;
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	SDirectorPrograma sdirector;
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;
	@WireVariable
	SEstudiante sestudiante;
	@WireVariable
	SProyecto sproyecto ;
	@WireVariable
	SActividad sactividad ;
	@WireVariable
	SListadoEstudianteAprobado sListadoEstudiantesAprobado;
	
	/**
	 *Declaración de los modelos a ser utilizados en los listados y combos 
	 */
	private ListModelList<DireccionPrograma> modeloPrograma;
	private List<DireccionPrograma> programas;
	private ListModelList<ListaEstudianteAprobado> modeloEstudianteAprobado;
	
	/**
	 * Declaración de variables donde sera guardada las selección
	 */
	private DireccionPrograma selectedPrograma;
	
    
	
	/**
	 * Método que inicializa los modelos
	 *  y carga el programa desde la BD y el combo de programas
	 * @param win,ventana la cual esta asociada a este viewmodel
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		programas =buscarDireccionesXRol();
		modeloPrograma = new ListModelList<DireccionPrograma>(programas);	
		modeloEstudianteAprobado = new ListModelList<ListaEstudianteAprobado>();
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
	 * Método utilizado para inicializar los componentes gráficos
	 * de la vista .zul, en este caso coloca en marca de agua el txt del filtro
	 * de búsqueda en nombre.
	 * @param view,ventana la cual esta asociada a este viewmodel
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
		this.win = (Window) win;
		Selectors.wireComponents(view, this, false);
		txtFiltroEstudiantesAprobados.setPlaceholder("Nombre");
	}
	
	/**
	 * Métodos para realizar la búsqueda de datos a tráves del filtro
	 * por campos como cédula, nombre y apellido.
	 */
	@Command
	@NotifyChange({ "modeloEstudianteAprobado" })
	public void filtrarEstudianteAprobado() {
		try {
		List<ListaEstudianteAprobado> aux = new ArrayList<ListaEstudianteAprobado>();
		aux = listestudiante;
		modeloEstudianteAprobado.clear();

		if (seleccion == null || seleccion == "") {
			if (nombre == "")
				modeloEstudianteAprobado.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getNombre().toLowerCase()
							.contains(nombre.toLowerCase())) {
						modeloEstudianteAprobado.add(aux.get(i));

					}
				}
			}
		} else {
			if (seleccion == "Cédula") {
				if (nombre == "")
					modeloEstudianteAprobado.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getCedula().toLowerCase()
								.contains(nombre.toLowerCase())) {
							modeloEstudianteAprobado.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Nombre") {
					if (nombre == "")
						modeloEstudianteAprobado.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getNombre().toLowerCase()
									.contains(nombre.toLowerCase())) {
								modeloEstudianteAprobado.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Apellido") {
						if (nombre == "")
							modeloEstudianteAprobado.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getApellido()
										.toLowerCase().contains(nombre.toLowerCase())) {
									modeloEstudianteAprobado.add(aux.get(i));

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
			txtFiltroEstudiantesAprobados.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroEstudiantesAprobados.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido")
					txtFiltroEstudiantesAprobados.setPlaceholder("Apellido");
					popOpciones.close();
			}
		}

	}
	
	/**
	 * Método que realiza la búsqueda de los datos de acuerdo a la selección
	 * realizada en el combo.
	 */
	@Command
	@NotifyChange("selectedPrograma")
	public void llenarListaDinamica() {
		modeloEstudianteAprobado.clear();
        String condicion="where e.estudiante_status='Aprobado' and aa.direccion_codigo = e.direccion_codigo";
        
		/**
		 * Guarda la Seleccion que haga el usuario en la variable 
		 * codigoDireccionPrograma
		 */
        if(selectedPrograma!=null){
        	condicion=condicion+" and aa.direccion_codigo="+ "'"+Integer.toString(selectedPrograma.getDireccionCodigo())+"'";
        }
		
		  listestudiante = sListadoEstudiantesAprobado.buscarEstudiantesAprobadoDinamico(condicion);
				
	     
		modeloEstudianteAprobado.addAll(listestudiante);
		win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
		Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
		tab.setSelected(true);
	}
	
	/**
	 * Método que carga la lista de estudiantes asignado a la Direccion de programa 
	 * que se seleccione en el combo y le pasa al componete jasperReport
	 *  lo necesario para pintarse en la pantalla .zul
	 * @param componente,componente de la vista a tráves del cual se pinta el reporte.
	 */
		@Command("showReport")
		@NotifyChange({"reportConfigCartaCulminacion","prueba"})
		public void showReport(@ContextParam(ContextType.COMPONENT) Button componente) {
			
			String cedulaEstudiante = (String) componente.getAttribute("idboton");
			Estudiante est=sestudiante.buscarPorCedula(cedulaEstudiante);
			reportConfigCartaCulminacion = new ReportConfigCartaCulminacion(ruta);
			reportConfigCartaCulminacion.setType(reportType);
			reportConfigCartaCulminacion.getParameters().put("cedula", est.getEstudianteCedula());
			reportConfigCartaCulminacion.getParameters().put("nombre", est.getEstudianteNombre());
			reportConfigCartaCulminacion.getParameters().put("apellido", est.getEstudianteApellido());
			System.out.println(reportConfigCartaCulminacion.getParameters());
				
			Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana2");
			tab.setSelected(true);
			tab.setVisible(true);

		}
		
		/**
		 * Método que cierra la ventana
		 */
		@Command
	    public void cerrarVentana() {	
			win.detach();
	    }
		
		/**
		 * Método que cancela el proceso
		 * @param view,ventana a la cual hace referencia el viewModel
		 */
		@Command
		@NotifyChange( "modeloEstudianteAprobado")
		public void cancelar(@ContextParam(ContextType.VIEW) Component view) {
			this.win = (Window) win;
			modeloEstudianteAprobado.clear();
			cmbPrograma.setText("");
		}	
		
		/**
		 * Métodos set y get de las variables
		 */
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
			

		public ListModelList<ListaEstudianteAprobado> getModeloEstudianteAprobado() {
			return modeloEstudianteAprobado;
		}

		public void setModeloEstudianteAprobado(
				ListModelList<ListaEstudianteAprobado> modeloEstudianteAprobado) {
			this.modeloEstudianteAprobado = modeloEstudianteAprobado;
		}

		public DireccionPrograma getSelectedPrograma() {
			return selectedPrograma;
		}
		
		public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
			this.selectedPrograma = selectedPrograma;
		}
		

		public ReportConfigCartaCulminacion getReportConfigCartaCulminacion() {
				return reportConfigCartaCulminacion;
		}

		
		public void setReportConfigCartaCulminacion(ReportConfigCartaCulminacion reportConfigCartaCulminacion) {
				this.reportConfigCartaCulminacion = reportConfigCartaCulminacion;
		}

		public ReportType getReportType() {
			return reportType;
		}

		public void setReportType(ReportType reportType) {
			this.reportType = reportType;
		}
		

		public List<ListaEstudianteAprobado> getList() {
			return listestudiante;
		}

		public void setList(List<ListaEstudianteAprobado> listestudiante) {
			this.listestudiante = listestudiante;
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

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		

		public Popup getPopOpciones() {
			return popOpciones;
		}

		public void setPopOpciones(Popup popOpciones) {
			this.popOpciones = popOpciones;
		}
		

		public List<ListaEstudianteAprobado> getListestudiante() {
			return listestudiante;
		}

		public void setListestudiante(List<ListaEstudianteAprobado> listestudiante) {
			this.listestudiante = listestudiante;
		}
		

}
