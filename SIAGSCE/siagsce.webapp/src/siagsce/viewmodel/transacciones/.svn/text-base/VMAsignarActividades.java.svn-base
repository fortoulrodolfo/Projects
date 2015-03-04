package siagsce.viewmodel.transacciones;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.ActividadAsignada;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.transacciones.SActividadAsignada;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.modelo.servicio.transacciones.SPreInscripcionProyecto;
import siagsce.viewmodel.seguridad.SecurityUtil;
/**
 * VMAsignarActividades es el viewmodel encargado de gestionar
 *  la asignacion de actividades a los estudiantes que se han inscritos 
 *  en los diferentes proyectos, para que posteriormente sean ejecutadas por los mismos.
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMAsignarActividades {
//*****************************Inicializacion de las variables******************************
	/**Declaracion de las variables de tipo servicio
	 **/
	private @WireVariable SInscripcionProyecto sinscripcionProyecto;
	private @WireVariable SPreInscripcionProyecto spreInscripcionProyecto;
	private @WireVariable SProyecto sproyecto;
	private @WireVariable SActividad sactividad;
	private @WireVariable SActividadAsignada sactividadAsignada;
	private @WireVariable SProfesor sprofesor;
	private @WireVariable SEstudiante sestudiante;
	



	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	private Combobox cmbActividad;
	@Wire
	private Datebox dtbFechaInicio;
	@Wire
	private Datebox dtbFechaCulminacion;
	@Wire
	private Textbox txtFiltroInscritos;
	@Wire
	private Listbox lbxAlumnos;
	@Wire
	private Popup popOpcionesInscritos;
	@Wire
	Window winAsignarActividades;
	SimpleDateFormat format;
	@Wire
	private Combobox cmbProyectos;
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private Proyecto selectedProyecto;
	private Actividad selectedActividad;
	private Set<InscripcionProyecto> selectedEstudiantes;
	private Set<InscripcionProyecto> selectedEstudiantesAsig;
	private ListModelList<Proyecto> modeloproyecto;
	private ListModelList<Actividad> modeloActividad;
	private ListModelList<InscripcionProyecto> modeloEstudianteInscrito;
	private List<InscripcionProyecto> estudiantesInscrito;
	private ListModelList<InscripcionProyecto> modeloEstudianteInscritoRetirarActividad;
	private ListModelList<InscripcionProyecto> modeloEstudianteInscriptoAsig;
	private List<Proyecto> listproyecto;
	private List<String> valores;
	private Date fechaInicio;
	private Date fechaCulminacion;
	private String seleccion;
	private String texto;
	private String nombreActividad, descripcionActividad,nombreProyecto, descripcionProyecto, responsableProyecto;
	private Integer  cantHorasActividad;	
	MensajesEmergentes mensajes=new MensajesEmergentes();
	/**
	  * Metodo que inicializa los modelos de datos(proyectos, actividades, 
	  * estudiantes inscritos en proyectos,
	  * estudiantes asignados a cierta actividad)que 
	  * seran usados por la ventana asignar actividades.
	  */
	@Init
	public void init() {
		listproyecto = sproyecto.buscarTodo();
		Profesor profesor=sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
		Proyecto proyecto=null;
		for(int i=0;i<listproyecto.size();i++){
			proyecto=listproyecto.get(i);
			if(!proyecto.getListaprofesoresresponsables().contains(profesor))
				listproyecto.remove(i);
		}
		estudiantesInscrito=new ArrayList<InscripcionProyecto>();
		modeloproyecto = new ListModelList<Proyecto>(listproyecto);
		modeloActividad = new ListModelList<Actividad>();
		modeloEstudianteInscritoRetirarActividad = new ListModelList<InscripcionProyecto>();
		modeloEstudianteInscrito = new ListModelList<InscripcionProyecto>();
		modeloEstudianteInscriptoAsig = new ListModelList<InscripcionProyecto>();
		modeloEstudianteInscrito.setMultiple(true);
		modeloEstudianteInscriptoAsig.setMultiple(true);
		fechaInicio=new Date();
		fechaCulminacion=new Date();
		dtbFechaInicio=new Datebox();
		dtbFechaCulminacion=new Datebox();
		format = new SimpleDateFormat("yyyyMMdd");
		MensajeBox.doSetTemplate();
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		valores.add("Programa");
	}
	
	/**
	 * Muestra un catalogo de los proyecto disponibles para evaluar la preinscripcion de los estudiantes y el proyecto
	 * seleccionado lo envia a la funcion proyectoSeleccionado()
	 * @link {@link VMEvaluacionPreInscripcionProyecto}{@link #proyectoSeleccionado()}
	 */
	@NotifyChange({ "nombreProyecto","descripcionProyecto","responsableProyecto","cantHorasActividad","descripcionActividad" })
	@Command
	public void mostrarProyecto() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("proyecto", "AsignarActividades");
		selectedProyecto = null;
		//cancelarMo();
		Window comp = (Window) Executions.createComponents(
				"/WEB-INF/vista/view/view.maestros/CatalogoProyectos.zul",
				null, map);
		comp.doModal();
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		selectedProyecto = (Proyecto) comp.getAttribute("proyecto");
		if (selectedProyecto != null){
			System.out.println(selectedProyecto.getProyectoStatus());
			if (!selectedProyecto.getProyectoStatus().contains("En Ejecución")){
				cargarActividad();	
			}
			else{
				cancelar();
				mensajeEmergente.errorProyectoInscripcionesAbiertas();
			}
		}
	}
	
	/**
	  * Metodo para validar que la fecha estipulada para la culminacion de la actividad
	  * no sea menor que la fecha inicial de ejecución.
	  */
	@NotifyChange({ "fechaCulminacion","fechaInicio"})
	@Command
	public void validarFechaCulminacion() {
		dtbFechaCulminacion.setConstraint("after "+String.valueOf(format.format(fechaInicio)));
		if(fechaInicio.after(fechaCulminacion))
			fechaCulminacion=fechaInicio;
			
	}
	@NotifyChange({ "fechaCulminacion","fechaInicio"})
	@Command
	public void validarFechaInicio() {
		dtbFechaInicio.setConstraint("no past, before "+String.valueOf(format.format(fechaCulminacion)));
		if(fechaCulminacion.before(fechaInicio))
			fechaInicio=fechaCulminacion;
			
	}
	/**
	  * Una ves que se selecciona el proyecto se cargan los datos del proyecto seleccionado,
	  * asi como tambien todas las actividades de dicho proyecto.
	  */
	@NotifyChange({ "nombreProyecto","descripcionProyecto","responsableProyecto","cantHorasActividad","descripcionActividad" })
	@Command
	public void cargarActividad() {
		fechaInicio=new Date();
		fechaCulminacion=new Date();
		this.cantHorasActividad=null;
		this.descripcionActividad="";
		modeloActividad.clear();
		modeloEstudianteInscriptoAsig.clear();
		this.selectedActividad=null;
		this.nombreProyecto=selectedProyecto.getProyectoNombre();
		this.descripcionProyecto=selectedProyecto.getProyectoDescripcion();
		this.responsableProyecto=selectedProyecto.getResponsableString();
		List<Actividad> listactividad = sactividad.buscarporProyecto(selectedProyecto);
		modeloActividad.addAll(listactividad);	
		cmbActividad.setSelectedIndex(-1);
		lbxAlumnos.setEmptyMessage("Seleccione una actividad para ver estudiantes");
	}
	/**
	  * Carga los estudiantes incritos en el proyecto seleccionado.
	  */public void cargarInscritos() {
			List<InscripcionProyecto> listestudiante = sinscripcionProyecto.buscarInscriptos(selectedProyecto);
			modeloEstudianteInscrito.addAll(listestudiante);
		}
	 /**
		  * Metodo que permite traer  al seleccionar una actividad
		  * solo aquello estudiantes que no han sido asignado previamente a dicha actividad.
		  */
	@NotifyChange({ "descripcionActividad","cantHorasActividad",})
	@Command
	public void	filtrarEstudiantesNoAsignadoActividad(){
		modeloEstudianteInscrito.clear();
		modeloEstudianteInscriptoAsig.clear();
		if(selectedEstudiantes!=null){
			selectedEstudiantes.clear();
		}
		if(selectedEstudiantesAsig!=null){
			selectedEstudiantesAsig.clear();
		}
		cargarInscritos();
		this.descripcionActividad=selectedActividad.getActividadDescripcion();
		this.cantHorasActividad=selectedActividad.getActividadCantidadHoras();		
		estudiantesInscrito =	sactividadAsignada.buscarInscritosNoAsignadosActividad(selectedActividad, selectedActividad.getProyectoa());
		modeloEstudianteInscrito.clear();
		modeloEstudianteInscrito.addAll(estudiantesInscrito);
		if(modeloEstudianteInscrito.isEmpty())
		lbxAlumnos.setEmptyMessage("No se pueden Asignar estudiantes a esta actividad");
	
	List<InscripcionProyecto> actividadAsignadaNoEjecutada=sactividadAsignada.buscarInscripcionProyectoEstudianteRetirar(selectedActividad);
	modeloEstudianteInscritoRetirarActividad.clear();
	modeloEstudianteInscritoRetirarActividad.addAll(actividadAsignadaNoEjecutada);
	}

	 /**
	  * Metodo que permite Asignar los estudiante a una actividad.
	  */
	@Command
	@NotifyChange({ "modeloEstudianteInscrito", "modeloEstudianteInscriptoAsig" })
	public void asignarInscritos() {
			if(selectedEstudiantes!=null){
				if(selectedActividad!=null){
				   if(!selectedEstudiantes.isEmpty()){
				modeloEstudianteInscriptoAsig.addAll(selectedEstudiantes);
				modeloEstudianteInscrito.removeAll(selectedEstudiantes);
				selectedEstudiantes.clear();
				   }else
					   mensajes.advertenciaSeleccionarEstudiante();
				}else{
					mensajes.advertenciaSeleccionarActividad();
					selectedEstudiantes.clear();
					}
			}else{
			mensajes.advertenciaSeleccionarEstudiante();
		}

	}

	/**
	  * Saca los estudiante que han sido seleccionados previamente
	  *  para que ejecuten cierta actividad, es importante destacar 
	  *  que una vez que los estudiantes hayan sido almacenados en la Base de datos
	  *   no se pueden sacar de la actividad.
	  */
	@Command
	@NotifyChange({ "modeloEstudianteInscrito", "modeloEstudianteInscriptoAsig" })
	public void sacarInscritos() {
		if(selectedEstudiantesAsig!=null){
		if(!selectedEstudiantesAsig.isEmpty()){
			if(selectedActividad!=null){
			modeloEstudianteInscrito.addAll(selectedEstudiantesAsig);
			modeloEstudianteInscriptoAsig.removeAll(selectedEstudiantesAsig);
			selectedEstudiantesAsig.clear();
				}else{
					mensajes.advertenciaSeleccionarActividad();	
			        selectedEstudiantesAsig.clear();}
		}else
			mensajes.advertenciaSeleccionarEstudiante();	
	}else
		mensajes.advertenciaSeleccionarEstudiante();
	}
		/**
	  * Limpia los campos de la ventana, menos la lista de proyecto 
	  * ya que de alli se parte para seleccionar las actividades y estudiantes.
	  */@Command
	@NotifyChange({ "fechaInicio", "fechaCulminacion", "modeloActividad",
			"modeloActividad", "modeloEstudianteInscrito",
			"modeloEstudianteInscriptoAsig","cmbActividad" ,"cmbProyectos","cantHorasActividad","descripcionActividad","nombreProyecto",
			"descripcionProyecto","responsableProyecto"})
	public void cancelar() {
		fechaInicio=new Date();
		fechaCulminacion=new Date();
		this.cantHorasActividad=null;
		this.descripcionActividad="";
		this.nombreProyecto = "";
		this.descripcionProyecto="";
		this.responsableProyecto="";
		modeloEstudianteInscrito.clear();
		modeloEstudianteInscriptoAsig.clear();
		modeloActividad.clear();
		if(selectedEstudiantes!=null){
			selectedEstudiantes.clear();
		}
		if(selectedEstudiantesAsig!=null){
			selectedEstudiantesAsig.clear();
		}
		lbxAlumnos.setEmptyMessage("Seleccione una actividad para ver estudiantes");
		
		cmbActividad.setSelectedIndex(-1);
	}
	  /**
		  * Registra en la BD los estudiantes con sus respectivas actividades
		  * que se les ha asignado.
		  */
	  @NotifyChange({ "fechaInicio", "fechaCulminacion", "modeloActividad",
			"modeloActividad", "modeloEstudianteInscrito",
			"modeloEstudianteInscriptoAsig","cmbActividad" ,"nombreProyecto","cantHorasActividad","descripcionActividad",
			"descripcionProyecto","responsableProyecto"})
	@Command
	public void registrarActividadAsignada() {
		if (modeloActividad.isEmpty() || modeloproyecto.isEmpty()
				|| modeloEstudianteInscriptoAsig.isEmpty()
				|| fechaInicio == null || fechaCulminacion == null)
			mensajes.advertenciaLlenarCampos();
		else {
			for (int i = 0; i < modeloEstudianteInscriptoAsig.size(); i++) {
				ActividadAsignada actasig = new ActividadAsignada(fechaInicio,
						fechaCulminacion, modeloEstudianteInscriptoAsig.get(i),
						selectedActividad);
				sactividadAsignada.guardar(actasig);
				
			}
			mensajes.informacionRegistroCorrecto();
			cancelar();
			
		}

	}
	  @NotifyChange({ "modeloEstudianteInscritoRetirarActividad","modeloEstudianteInscrito"})
	  @Command
		public void retirarEstudianteActividad(@ContextParam(ContextType.COMPONENT) Button componente) {
		  int resp=mensajes.advertenciaEliminarEstudiante();
		  if(resp==1){
		  String cedulaEstudiante = (String) componente.getAttribute("idboton");
		  List<ActividadAsignada> actividadesAsignadas=sactividadAsignada.buscarPorEstudianteSinEjecutar(sestudiante.buscarPorCedula(cedulaEstudiante));
	      for(int i=0;i<actividadesAsignadas.size();i++){
	    	 if(actividadesAsignadas.get(i).getActividad().getActividadCodigo().compareTo(selectedActividad.getActividadCodigo())==0){
	           sactividadAsignada.eliminar(actividadesAsignadas.get(i));
	           break;
	    	 }
	      }
	      for(int i=0;i<modeloEstudianteInscritoRetirarActividad.size();i++){
		    	 if(modeloEstudianteInscritoRetirarActividad.get(i).getPreInscripcion().getEstudiante().getEstudianteCedula().equals(cedulaEstudiante)){
		    		 modeloEstudianteInscrito.add(modeloEstudianteInscritoRetirarActividad.remove(i));
		           break;
		    	 }
		      }
	      mensajes.informacioRetirarCorrecto();
	      }
		 
		  
	  }
	/**
	  * Cierra la ventana
	  */
	@Command
	public void salir() {
		winAsignarActividades.detach();
	}
	
	/**
	  * Se inicializo la variable para el filtro de estudiantes, asi como tambien 
	  * se ejecuto el metodo que permite la coneccion del viewmodel con la vista.
	  */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
		this.winAsignarActividades = (Window) winAsignarActividades;
		Selectors.wireComponents(view, this, false);
		 txtFiltroInscritos.setPlaceholder("Nombre");
	}
	/**
	  * Se filtra la lista de estudiantes que pueden ser asignado a las diferentes
	  * actividades, dicho filtro puede ser por cédula, nombre y apellido.
	  */@Command
		@NotifyChange({ "modeloInscritos" })
		public void filtrarEstudiante() {
			try {
				List<InscripcionProyecto> aux = new ArrayList<InscripcionProyecto>();
				aux = sinscripcionProyecto.buscarInscriptos(selectedProyecto);
				List<InscripcionProyecto> inscripcionProyecto=sactividadAsignada.buscarInscripcionProyecto(selectedActividad);
				for(int i=0;i<aux.size();i++)
					for(int j=0;j<inscripcionProyecto.size();j++){
						if(aux.get(i).getInscripcionCodigo().compareTo(inscripcionProyecto.get(j).getInscripcionCodigo())==0)
							aux.remove(i);
				    }		
				modeloEstudianteInscrito.clear();

				if (seleccion == null || seleccion == "") {
						if (texto == "")
							modeloEstudianteInscrito.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getPreInscripcion().getEstudiante().getEstudianteNombre().toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloEstudianteInscrito.add(aux.get(i));

								}
							}
						}
					
				} else {
					if (seleccion == "Cédula") {
						if (texto == "")
							modeloEstudianteInscrito.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getPreInscripcion().getEstudiante().getEstudianteCedula()
										.contains(texto.toLowerCase())) {
									modeloEstudianteInscrito.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion.toLowerCase() == "Nombre") {
							if (texto == "")
								modeloEstudianteInscrito.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getPreInscripcion().getEstudiante().getEstudianteNombre().toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloEstudianteInscrito.add(aux.get(i));

									}
								}
							}
						} else {
							if (seleccion== "Apellido") {
								if (texto == "")
									modeloEstudianteInscrito.addAll(aux);
								else {
									for (int i = 0; i < aux.size(); i++) {
										if (aux.get(i).getPreInscripcion().getEstudiante().getEstudianteApellido()
												.toLowerCase().contains(texto.toLowerCase())) {
											modeloEstudianteInscrito.add(aux.get(i));

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
	  /**
		  * Gestiona el filtro, dependiendo del parametro para filtrar
		  * actualiza el txtFiltroInscritos para indicar 
		  * que debe insertar(cedula, nombre o apellido). 
		  */
		@Command
		public void seleccionFiltro() {
			if (seleccion == "Nombre") {
				txtFiltroInscritos.setPlaceholder("Nombre");
				popOpcionesInscritos.close();
			} else {
				if (seleccion == "Cédula") {
					txtFiltroInscritos.setPlaceholder("Cédula");
					popOpcionesInscritos.close();
				} else {
					if (seleccion== "Apellido") {
						txtFiltroInscritos.setPlaceholder("Apellido");
						popOpcionesInscritos.close();
					} else {
						if (seleccion== "Programa") {
							txtFiltroInscritos.setPlaceholder("Programa");
							popOpcionesInscritos.close();
						}
					}
				}
			}

		}
		/**
		  * A continuacion se declaran todos los getter y setter de las variables
		  * para poder ser accedidas desde la vista(asignarActividades.zul).
		  */
		public Set<InscripcionProyecto> getSelectedEstudiantesAsig() {
			return selectedEstudiantesAsig;
		}

		public void setSelectedEstudiantesAsig(
				Set<InscripcionProyecto> selectedEstudiantesAsig) {
			this.selectedEstudiantesAsig = selectedEstudiantesAsig;
		}

		public Actividad getSelectedActividad() {
			return selectedActividad;
		}

		public void setSelectedActividad(Actividad selectedActividad) {
			this.selectedActividad = selectedActividad;
		}

		public Date getFechaCulminacion() {
			return fechaCulminacion;
		}

		public void setFechaCulminacion(Date fechaCulminacion) {
			this.fechaCulminacion = fechaCulminacion;
		}

		public Set<InscripcionProyecto> getSelectedEstudiantes() {
			return selectedEstudiantes;
		}

		public void setSelectedEstudiantes(
				Set<InscripcionProyecto> selectedEstudiantes) {
			this.selectedEstudiantes = selectedEstudiantes;
		}

		public Proyecto getSelectedProyecto() {
			return selectedProyecto;
		}

		public void setSelectedProyecto(Proyecto selectedProyecto) {
			this.selectedProyecto = selectedProyecto;
		}

		public ListModelList<Proyecto> getModeloproyecto() {
			return modeloproyecto;
		}

		public void setModeloproyecto(ListModelList<Proyecto> modeloproyecto) {
			this.modeloproyecto = modeloproyecto;
		}

		public ListModelList<Actividad> getModeloActividad() {
			return modeloActividad;
		}

		public void setModeloActividad(ListModelList<Actividad> modeloActividad) {
			this.modeloActividad = modeloActividad;
		}

		public ListModelList<InscripcionProyecto> getModeloEstudianteInscrito() {
			return modeloEstudianteInscrito;
		}

		public void setModeloEstudianteInscrito(
				ListModelList<InscripcionProyecto> modeloEstudianteInscrito) {
			this.modeloEstudianteInscrito = modeloEstudianteInscrito;
		}

		public ListModelList<InscripcionProyecto> getModeloEstudianteInscriptoAsig() {
			return modeloEstudianteInscriptoAsig;
		}

		public void setModeloEstudianteInscriptoAsig(
				ListModelList<InscripcionProyecto> modeloEstudianteInscriptoAsig) {
			this.modeloEstudianteInscriptoAsig = modeloEstudianteInscriptoAsig;
		}

		public Date getFechaInicio() {
			return fechaInicio;
		}

		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		public Integer getCantHorasActividad() {
			return cantHorasActividad;
		}
		public void setCantHorasActividad(Integer cantHorasActividad) {
			this.cantHorasActividad = cantHorasActividad;
		}
		public String getResponsableProyecto() {
			return responsableProyecto;
		}
		public void setResponsableProyecto(String responsableProyecto) {
			this.responsableProyecto = responsableProyecto;
		}
		public String getNombreActividad() {
			return nombreActividad;
		}
		public void setNombreActividad(String nombreActividad) {
			this.nombreActividad = nombreActividad;
		}
		public String getDescripcionActividad() {
			return descripcionActividad;
		}
		public void setDescripcionActividad(String descripcionActividad) {
			this.descripcionActividad = descripcionActividad;
		}
		public String getDescripcionProyecto() {
			return descripcionProyecto;
		}
		public void setDescripcionProyecto(String descripcionProyecto) {
			this.descripcionProyecto = descripcionProyecto;
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
		public String getTexto() {
			return texto;
		}
		public void setTexto(String texto) {
			this.texto = texto;
		}
		public String getNombreProyecto() {
			return nombreProyecto;
		}
		public void setNombreProyecto(String nombreProyecto) {
			this.nombreProyecto = nombreProyecto;
		}
		public ListModelList<InscripcionProyecto> getModeloEstudianteInscritoRetirarActividad() {
			return modeloEstudianteInscritoRetirarActividad;
		}

		public void setModeloEstudianteInscritoRetirarActividad(
				ListModelList<InscripcionProyecto> modeloEstudianteInscritoRetirarActividad) {
			this.modeloEstudianteInscritoRetirarActividad = modeloEstudianteInscritoRetirarActividad;
		}
}
