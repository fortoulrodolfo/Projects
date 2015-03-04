package siagsce.viewmodel.maestros;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.general.StatusInscripcionTaller;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SInscripcionTaller;
import siagsce.modelo.servicio.maestros.STaller;
/**
 * VMInscripcionAlTaller es el viewmodel encargado de gestionar
 * la inscripcion de estudiantes aptos a los talleres que se encuentran disponibles.
 * @author SIAGSCE
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMInscripcionAlTaller {

	@Wire
	private Window win;

	@WireVariable
	STaller staller;
	@WireVariable
	SInscripcionTaller sinscripciontaller;
	@WireVariable
	SEstudiante sestudiante;
	@Wire
	private Textbox descInscripcionTaller;
	@Wire
	private Button guardarIncripcionTaller;
	@Wire
	private Button btnEditarIncripcionTaller;
	@Wire
	private Button btnEliminarIncripcionTaller;
	@Wire
	private String descripcionInscripcionTaller;
	@Wire
	private String fechaInicioInscripcionTaller;
	@Wire
	private String fechaCulminacionInscripcionTaller;
	@Wire
	private String telfResponsableTaller;
	@Wire
	private String fechaInicioTaller;
	@Wire
	private String fechaCulminacionTaller;
	@Wire
	private String lugarInscripcionTaller;
	@Wire
	private String cedRespInscripcionTaller;	
	@Wire
	private String nombreRespInscripcionTaller;
	private Taller taller;
	private Estudiante estudianteAEliminar;
	private ListModelList<Estudiante> modeloEstudiantesInscribir;
	private ListModelList<Estudiante> modeloEstudiantesAEliminar;
	SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

	MensajesEmergentes mensajes=new MensajesEmergentes();

	/**
	  * Se ejecuto el metodo que permite la coneccion del viewmodel con la vista
	  * y se inicializo la variable ventana.
	  * @param win es la ventana que gestiona el viewmodel
	  */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component win) {
		this.win = (Window) win;
		Selectors.wireComponents(win, this, false);
	}
	/**
	  * Inicializa la lista que va a contener los estudiantes 
	  * que se van a inscribir al taller.
	  
	  */
	@Init
	public void init() {
		modeloEstudiantesInscribir = new ListModelList<Estudiante>();
		MensajeBox.doSetTemplate();

	}
	/**
	  * Muestra el catalogo de estudiantes que van a ser seleccionado para
	  * efectuar el taller 
	  */
	@Command
	@NotifyChange({ "modeloEstudiantesInscribir" })
	public void MostrarCatalogoEstudiante() {
		if (this.taller != null) {
			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("estudiantesSeleccionadosAinscribir",modeloEstudiantesInscribir);
			 Window comp=(Window)Executions
					.createComponents(
							"/WEB-INF/vista/view/view.maestros/CatalogoEstudiantes.zul",
							null, map);
			 comp.doModal();
			 @SuppressWarnings("unchecked")
			 List<Estudiante> listestudiante= (List<Estudiante>) comp.getAttribute("estudiantesSeleccionados");
			 
			 if(listestudiante!=null)
			 this.obtenerEstudiantesInscripcionAlTaller(listestudiante);
			 
		} else {
			mensajes.advertenciaSeleccionarOpcion();
		}
	}
	/**
	  * Inicializa la lista que va a contener los estudiantes 
	  * que se van a inscribir al taller.
	  * @param lista de estudiantes que se van a inscribir al taller
	  */
	@NotifyChange({ "modeloEstudiantesInscribir" })
	public void obtenerEstudiantesInscripcionAlTaller( List<Estudiante> estudiantes) {
		modeloEstudiantesInscribir.addAll(estudiantes);
	}
	/**
	  * Muestra el catalogo de talleres disponibles
	  * (que su fecha final de inscripcion no haya pasado).
	  */
	@Command
	@NotifyChange({ "modeloEstudiantesAEliminar",
			"tabEliminarEstudiantesTaller", "descripcionInscripcionTaller",
			"fechaInicioInscripcionTaller", "fechaInicioTaller",
			"fechaCulminacionTaller", "fechaCulminacionInscripcionTaller",
			"lugarInscripcionTaller", "cedRespInscripcionTaller",
			"nombreRespInscripcionTaller", "telfResponsableTaller" })
	public void MostrarCatalogoTaller() {
		String ventana = "InscripcionAlTaller";
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("taller", ventana);
		Window comp = (Window) Executions.createComponents(
				"/WEB-INF/vista/view/view.maestros/CatalogoTaller.zul", null,
				map);
		comp.doModal();
		Taller taller = (Taller) comp.getAttribute("taller");
		if(taller!=null)
		obtenerTallerInscripcionAlTaller(taller);
	}
	/**
	  * Actualiza la vista mostrando todos los datos del taller
	  * que previamente se ha seleccionado, de igual manera carga los estudiantes 
	  * que se han inscritos con anterioridad para un posible retiro si es necesario.
	  * @param taller seleccionado del catalogo
	  */
	@NotifyChange({ "modeloEstudiantesAEliminar",
			"tabEliminarEstudiantesTaller", "descripcionInscripcionTaller",
			"fechaInicioInscripcionTaller", "fechaInicioTaller",
			"fechaCulminacionTaller", "fechaCulminacionInscripcionTaller",
			"lugarInscripcionTaller", "cedRespInscripcionTaller",
			"nombreRespInscripcionTaller", "telfResponsableTaller" })
	public void obtenerTallerInscripcionAlTaller(Taller taller) {
		descripcionInscripcionTaller = taller.getTallerDescripcion();
		fechaInicioInscripcionTaller = sd.format(taller
				.getTallerInscripcionFechaInicio());

		fechaCulminacionInscripcionTaller = sd.format(taller
				.getTallerInscripcionFechaFinal());
		fechaInicioTaller = sd.format(taller.getTallerFechaInicio());
		fechaCulminacionTaller = sd.format(taller.getTallerFechaCulminacion());
		lugarInscripcionTaller = String.valueOf(taller.getTallerLugar());
		cedRespInscripcionTaller = String.valueOf(taller
				.getTallerProfesorResponsable().getProfesorCedula());
		nombreRespInscripcionTaller = String.valueOf(taller
				.getTallerProfesorResponsable().getProfesorNombre()+" "+taller
				.getTallerProfesorResponsable().getProfesorApellido());
		telfResponsableTaller = taller.getTallerProfesorResponsable()
				.getProfesorTelefono();
		this.taller = taller;
		modeloEstudiantesAEliminar = new ListModelList<Estudiante>(sinscripciontaller.buscarEstudiantesDeUnTaller(this.taller));
		
	}
	/**
	  * Guarda en la BD(Base de datos) la inscripcion de los estudiantes al taller.
	  * @throws ParseException
	  */
	@NotifyChange({ "descripcionInscripcionTaller",
		"fechaInicioInscripcionTaller", "fechaInicioTaller",
		"fechaCulminacionTaller", "fechaCulminacionInscripcionTaller",
		"lugarInscripcionTaller", "cedRespInscripcionTaller",
		"nombreRespInscripcionTaller", "telfResponsableTaller",
		"descInscripcionTaller" })
	@Command
	public void guardarEstudiantesTaller() throws ParseException {
		if (this.taller != null) {
			if (!modeloEstudiantesInscribir.isEmpty()) {
				for (int i = 0; i < modeloEstudiantesInscribir.size(); i++) {
					InscripcionTaller ins = new InscripcionTaller(
							modeloEstudiantesInscribir.get(i), this.taller);
					ins.setInscripcionTallerStatus(StatusInscripcionTaller.Activo
							.toString());
					Date fechaIncripcion = new Date();
					ins.setInscripcionTallerFecha(fechaIncripcion);
					sinscripciontaller.guardar(ins);
				}
				mensajes.informacionRegistroCorrecto();
				cancelar();
			} else {
				mensajes.advertenciaSeleccionarEstudiante();
			}
		} else {
			mensajes.advertenciaSeleccionarTaller();
		}

	}

	/**
	  * Elimina los estudianes que han sido 
	  * seleccionado en ese mismo instante(no han sido guardado en la DB).
	  * Nota:los estudiantes se seleccionan directamente de una lista
	  */
	@Command
	public void eliminarEstudianteLista(
			@ContextParam(ContextType.COMPONENT) Button componente) {
		String cedulaEstudiante = (String) componente.getAttribute("idboton");
		for (int i = 0; i < modeloEstudiantesInscribir.getSize(); i++)
			if (modeloEstudiantesInscribir.get(i).getEstudianteCedula()
					.equals(cedulaEstudiante)) {
				modeloEstudiantesInscribir.remove(i);
				break;
			}
		
		mensajes.informacionEliminarEstudiantes();
	}
	/**
	  * Elimina los estudianes que han sido inscritos anteriormente
	  *  en el taller si existe algun motivo valido
	  * Nota:los estudiantes que estan guardados en la BD).
	  *  los estudiantes se seleccionan directamente de una lista.
	  */
	@Command
	@NotifyChange({"modeloEstudiantesAEliminar"})
	public void eliminarEstudianteTaller(
		@ContextParam(ContextType.COMPONENT) Button componente) {
		String cedulaEstudiante = (String) componente.getAttribute("idboton");
		Estudiante estudiante=sestudiante.buscarPorCedula(cedulaEstudiante);
		InscripcionTaller insTaller=sinscripciontaller.buscarPorEstudianteAndTaller(estudiante,this.taller);
	    sinscripciontaller.eliminar(insTaller);
	    mensajes.informacionEliminarCorrecto();
	    modeloEstudiantesAEliminar = new ListModelList<Estudiante>(sinscripciontaller.buscarEstudiantesDeUnTaller(this.taller));
	}

	/**
	  * limpia la vista e inicializa las variables
	  */
	@NotifyChange({ "descripcionInscripcionTaller",
			"fechaInicioInscripcionTaller", "fechaInicioTaller",
			"fechaCulminacionTaller", "fechaCulminacionInscripcionTaller",
			"lugarInscripcionTaller", "cedRespInscripcionTaller",
			"nombreRespInscripcionTaller", "telfResponsableTaller",
			"descInscripcionTaller" })
	@Command
	public void cancelar() {
		modeloEstudiantesInscribir.clear();
		this.taller = null;
		descripcionInscripcionTaller = "";
		fechaInicioInscripcionTaller = "";
		fechaCulminacionInscripcionTaller = "";
		fechaInicioTaller = "";
		fechaCulminacionTaller = "";
		lugarInscripcionTaller = "";
		cedRespInscripcionTaller = "";
		nombreRespInscripcionTaller = "";
		telfResponsableTaller = "";
		descInscripcionTaller.setFocus(true);
		if(modeloEstudiantesAEliminar!=null)
		modeloEstudiantesAEliminar.clear();

	}
	/**
	  * cierra la ventana
	  */
	@Command
	public void cerrarVentana() {
		win.detach();
	}
	/**
	  * A continuacion se declaran todos los getter y setter de las variables
	  * para poder ser accedidas desde la vista(asignarActividades.zul).
	  */
	public Estudiante getEstudianteAEliminar() {
		return estudianteAEliminar;
	}

	public void setEstudianteAEliminar(Estudiante estudianteAEliminar) {
		this.estudianteAEliminar = estudianteAEliminar;
	}
	
	public String getTelfResponsableTaller() {
		return telfResponsableTaller;
	}

	public void setTelfResponsableTaller(String telfResponsableTaller) {
		this.telfResponsableTaller = telfResponsableTaller;
	}

	public Textbox getDescInscripcionTaller() {
		return descInscripcionTaller;
	}

	public void setDescInscripcionTaller(Textbox descInscripcionTaller) {
		this.descInscripcionTaller = descInscripcionTaller;
	}

	public String getFechaInicioTaller() {
		return fechaInicioTaller;
	}

	public void setFechaInicioTaller(String fechaInicioTaller) {
		this.fechaInicioTaller = fechaInicioTaller;
	}

	public String getFechaCulminacionTaller() {
		return fechaCulminacionTaller;
	}

	public void setFechaCulminacionTaller(String fechaCulminacionTaller) {
		this.fechaCulminacionTaller = fechaCulminacionTaller;
	}
	
	public String getDescripcionInscripcionTaller() {
		return descripcionInscripcionTaller;
	}

	public void setDescripcionInscripcionTaller(
			String descripcionInscripcionTaller) {
		this.descripcionInscripcionTaller = descripcionInscripcionTaller;
	}

	public String getFechaInicioInscripcionTaller() {
		return fechaInicioInscripcionTaller;
	}

	public void setFechaInicioInscripcionTaller(
			String fechaInicioInscripcionTaller) {
		this.fechaInicioInscripcionTaller = fechaInicioInscripcionTaller;
	}

	public String getFechaCulminacionInscripcionTaller() {
		return fechaCulminacionInscripcionTaller;
	}

	public void setFechaCulminacionInscripcionTaller(
			String fechaCulminacionInscripcionTaller) {
		this.fechaCulminacionInscripcionTaller = fechaCulminacionInscripcionTaller;
	}

	public String getLugarInscripcionTaller() {
		return lugarInscripcionTaller;
	}

	public void setLugarInscripcionTaller(String lugarInscripcionTaller) {
		this.lugarInscripcionTaller = lugarInscripcionTaller;
	}

	public String getCedRespInscripcionTaller() {
		return cedRespInscripcionTaller;
	}

	public void setCedRespInscripcionTaller(String cedRespInscripcionTaller) {
		this.cedRespInscripcionTaller = cedRespInscripcionTaller;
	}

	public String getNombreRespInscripcionTaller() {
		return nombreRespInscripcionTaller;
	}

	public void setNombreRespInscripcionTaller(
			String nombreRespInscripcionTaller) {
		this.nombreRespInscripcionTaller = nombreRespInscripcionTaller;
	}
	public ListModelList<Estudiante> getModeloEstudiantesAEliminar() {
		return modeloEstudiantesAEliminar;
	}

	public void setModeloEstudiantesAEliminar(
			ListModelList<Estudiante> modeloEstudiantesAEliminar) {
		this.modeloEstudiantesAEliminar = modeloEstudiantesAEliminar;
	}

	public STaller getStaller() {
		return staller;
	}

	public void setStaller(STaller staller) {
		this.staller = staller;
	}

	public SEstudiante getSestudiante() {
		return sestudiante;
	}

	public void setSestudiante(SEstudiante sestudiante) {
		this.sestudiante = sestudiante;
	}

	public ListModelList<Estudiante> getModeloEstudiantesInscribir() {
		return modeloEstudiantesInscribir;

	}

	public void setModeloEstudiantesInscribir(
			ListModelList<Estudiante> modeloEstudiantesInscribir) {
		this.modeloEstudiantesInscribir = modeloEstudiantesInscribir;
	}


}