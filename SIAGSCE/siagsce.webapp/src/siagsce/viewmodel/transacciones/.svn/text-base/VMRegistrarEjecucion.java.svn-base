package siagsce.viewmodel.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import siagsce.herramientas.*;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.transacciones.ActividadAsignada;
import siagsce.modelo.data.transacciones.ActividadEjecutada;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.ListadoEstudiantesActividadAsignada;
import siagsce.modelo.general.StatusInscripcionProyecto;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.transacciones.SActividadAsignada;
import siagsce.modelo.servicio.transacciones.SActividadEjecutada;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.modelo.servicio.transacciones.SListadoEstudiantesActividadAsignada;
import siagsce.viewmodel.seguridad.SecurityUtil;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarEjecucion {

	private Window win;

	// Declaracion de los servicios
	@WireVariable
	SProyecto sproyecto;

	@WireVariable
	SActividad sactividad;

	@WireVariable
	SActividadAsignada sactividadAsignada;

	@WireVariable
	SActividadEjecutada sactividadEjecutada;

	@WireVariable
	SListadoEstudiantesActividadAsignada sListadoEstudiantesActividadAsignada;
	
	@WireVariable
	SProfesor sprofesor;
	
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;
	
	private @WireVariable SInscripcionProyecto sinscripcionProyecto;
	

	@Wire
	private Combobox cmbProyecto;
	@Wire
	private Combobox cmbActividad;
	

	// Declaracion de los modelos a ser utilizados en los listados y combos
	private ListModelList<Proyecto> modeloProyecto;
	private ListModelList<Actividad> modeloActividad;
	private ListModelList<ListadoEstudiantesActividadAsignada> modeloEstudianteActividadAsignada;
	private ListModelList<InscripcionProyecto>modeloEstudiantesCulminados;
	private List<Proyecto> proyectos;

	// Declaracion de variables donde sera guardada las selecciones
	private Proyecto selectedProyecto;
	private Actividad selectedActividad;
	private ListadoEstudiantesActividadAsignada selectedEstudiante;
	private Profesor profesorParticipante;
	private Profesor profesor;

	// Declaracion de variables comunes
	private String nombreActividad, descripcionActividad, nombreProyecto, descripcionProyecto,
			responsableProyecto;
	private String selectedHora = "";
	private Date selectedFecha = null;
	private Integer codigoActividad, cantHorasActividad;
	private boolean representante, responsable, participante;

	// Set y Get
	public List<Proyecto> getProyectos() {
		return proyectos;
	}
	
	public ListModelList<InscripcionProyecto> getModeloEstudiantesCulminados() {
		return modeloEstudiantesCulminados;
	}

	public void setModeloEstudiantesCulminados(
			ListModelList<InscripcionProyecto> modeloEstudiantesCulminados) {
		this.modeloEstudiantesCulminados = modeloEstudiantesCulminados;
	}

	public ListModelList<ListadoEstudiantesActividadAsignada> getModeloEstudianteActividadAsignada() {
		return modeloEstudianteActividadAsignada;
	}
	public void setModeloEstudianteActividadAsignada(
			ListModelList<ListadoEstudiantesActividadAsignada> modeloEstudianteActividadAsignada) {
		this.modeloEstudianteActividadAsignada = modeloEstudianteActividadAsignada;
	}
	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	public ListModelList<Proyecto> getModeloProyecto() {
		return modeloProyecto;
	}
	public void setModeloProyecto(ListModelList<Proyecto> modeloProyecto) {
		this.modeloProyecto = modeloProyecto;
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
	public Integer getCantHorasActividad() {
		return cantHorasActividad;
	}
	public void setCantHorasActividad(Integer cantHorasActividad) {
		this.cantHorasActividad = cantHorasActividad;
	}
	public Integer getCodigoActividad() {
		return codigoActividad;
	}
	public void setCodigoActividad(Integer codigoActividad) {
		this.codigoActividad = codigoActividad;
	}
	public ListModelList<Actividad> getModeloActividad() {
		return modeloActividad;
	}
	public void setModeloActividad(ListModelList<Actividad> modeloActividad) {
		this.modeloActividad = modeloActividad;
	}
	public Proyecto getSelectedProyecto() {
		return selectedProyecto;
	}
	public void setSelectedProyecto(Proyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}
	public Actividad getSelectedActividad() {
		return selectedActividad;
	}
	public void setSelectedActividad(Actividad selectedActividad) {
		this.selectedActividad = selectedActividad;
	}
	public String getSelectedHora() {
		return selectedHora;
	}
	public void setSelectedHora(String selectedHora) {
		this.selectedHora = selectedHora;
	}
	public Date getSelectedFecha() {
		return selectedFecha;
	}
	public void setSelectedFecha(Date selectedFecha) {
		this.selectedFecha = selectedFecha;
	}
	public ListadoEstudiantesActividadAsignada getSelectedEstudiante() {
		return selectedEstudiante;
	}
	public void setSelectedEstudiante(
			ListadoEstudiantesActividadAsignada selectedEstudiante) {
		this.selectedEstudiante = selectedEstudiante;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public String getDescripcionProyecto() {
		return descripcionProyecto;
	}
	public void setDescripcionProyecto(String descripcionProyecto) {
		this.descripcionProyecto = descripcionProyecto;
	}
	public String getResponsableProyecto() {
		return responsableProyecto;
	}
	public void setResponsableProyecto(String responsableProyecto) {
		this.responsableProyecto = responsableProyecto;
	}
	public Profesor getProfesorParticipante() {
		return profesorParticipante;
	}
	public void setProfesorParticipante(Profesor profesorParticipante) {
		this.profesorParticipante = profesorParticipante;
	}
	public boolean isRepresentante() {
		return representante;
	}
	public void setRepresentante(boolean representante) {
		this.representante = representante;
	}
	public boolean isResponsable() {
		return responsable;
	}
	public void setResponsable(boolean responsable) {
		this.responsable = responsable;
	}
	public boolean isParticipante() {
		return participante;
	}
	public void setParticipante(boolean participante) {
		this.participante = participante;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	
	// Metodo que inicializa los modelos y carga los proyectos en el combo de
	// proyectos
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;

//		String cedulaProfesor = SecurityUtil.nombreUsuario;
//		profesorParticipante = sprofesor
//				.buscarPorCedula(cedulaProfesor);
//		proyectos = sactividad
//				.buscarProyectosDeUnProfesorParticipante(profesorParticipante);
//		modeloProyecto = new ListModelList<Proyecto>(proyectos);
		modeloProyecto = new ListModelList<Proyecto>();
		modeloActividad = new ListModelList<Actividad>();
		modeloEstudianteActividadAsignada = new ListModelList<ListadoEstudiantesActividadAsignada>();
		MensajeBox.doSetTemplate();
		
		profesor = sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
		List <String> roles = SecurityUtil.roles;
		representante = false;
		responsable = false;
		participante = false;
		
		for (int i = 0; i<roles.size(); i++){
			if (roles.get(i).contains("Representante Profesoral")){
				representante = true;
			}
			else if (roles.get(i).contains("Responsable Proyecto")){
					responsable = true;
				}
				else if (roles.get(i).contains("Participante Actividad")){
					participante = true;
				}
		}
	}

	// metodo que permite acceder a los componentes de la vista e inicializar
	// los necesarios
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		cmbActividad.setPlaceholder("Seleccione");
	}
	
	/**
	 * Muestra un catalogo de los proyecto disponibles para evaluar la preinscripcion de los estudiantes y el proyecto
	 * seleccionado lo envia a la funcion proyectoSeleccionado()
	 * @link {@link VMEvaluacionPreInscripcionProyecto}{@link #proyectoSeleccionado()}
	 */
	@NotifyChange({ "modeloActividad","nombreProyecto", "descripcionProyecto",
		"modeloEstudiantePrueba", "responsableProyecto",
		"descripcionActividad", "cantHorasActividad", "proyectos","modeloEstudiantesCulminados"})
	@Command
	public void mostrarProyecto() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("proyecto", "RegistrarEjecucion");
		selectedProyecto = null;
		Window comp = (Window) Executions.createComponents(
				"/WEB-INF/vista/view/view.maestros/CatalogoProyectos.zul",
				null, map);
		comp.doModal();
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		selectedProyecto = (Proyecto) comp.getAttribute("proyecto");
		if (selectedProyecto != null){
			cargarCulminados();
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
	@NotifyChange("modeloEstudiantesCulminados")
public void cargarCulminados(){
	List<InscripcionProyecto>aux=sactividadEjecutada.buscarInscripcionesCulminadas(selectedProyecto);
//	System.out.println(selectedProyecto);
//	System.out.println(aux.get(0).getInscripcionCodigo());
	modeloEstudiantesCulminados=new ListModelList<InscripcionProyecto>();
		modeloEstudiantesCulminados.addAll(aux);
//		System.out.println(modeloEstudiantesCulminados.get(0).getPreInscripcion().getEstudiante().getEstudianteNombre());
}
	
	@Command
		public void culminar(@BindingParam("Inscripcion") InscripcionProyecto insc){
			InscripcionProyecto inscripcion =sinscripcionProyecto.buscarPorCodigo(insc.getInscripcionCodigo());
			inscripcion.setInscripcionProyectoStatus(StatusInscripcionProyecto.Culminado.toString());
			modeloEstudiantesCulminados.remove(insc);
			sinscripcionProyecto.guardar(inscripcion);
			
		}

	// Metodo que Carga en el combo de actividad de acuerdo al proyecto que se
	// seleccione
	@NotifyChange({ "modeloActividad","nombreProyecto", "descripcionProyecto",
			"modeloEstudiantePrueba", "responsableProyecto" })
	@Command
	public void cargarActividad() {
		modeloActividad.clear();
		modeloEstudianteActividadAsignada.clear();
		List<Actividad> listactividad = new ArrayList<>();
		
		//Si es representante
		if (representante){
			RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
			//si el proyecto que seleccione pertenese a la direccion que representa carga todas las actividades
			if (selectedProyecto.getProgramaString().contains(representatantep.getDireccionProgramam().getDireccionNombre())){
				listactividad = sactividad.buscarporProyecto(selectedProyecto);
			}
			//si es responsable
			else if (responsable){
					boolean exito = false;
					for(int i=0;i<selectedProyecto.getListaprofesoresresponsables().size();i++){
						//si el proyecto que seleccione es donde ejerce como responsable
						if(selectedProyecto.getListaprofesoresresponsables().get(i).getProfesorCedula().equals(profesor.getProfesorCedula())){
							listactividad = sactividad.buscarporProyecto(selectedProyecto);
							exito = true;
							break;
						}
					}
					//si el proyecto que seleccione no es donde ejerce como responsable carga las actividades del proyecto donde participa
					if (!exito){
						listactividad = sactividad
								.buscarActividadesDeUnProfesorParticipanteDeUnProyecto(
										profesor, selectedProyecto);
					}
					}
				else{
					listactividad = sactividad
							.buscarActividadesDeUnProfesorParticipanteDeUnProyecto(
									profesor, selectedProyecto);
				}
				}
			//si no es representante pero si responsable
		else if (responsable){
				boolean exito = false;
				for(int i=0;i<selectedProyecto.getListaprofesoresresponsables().size();i++){
					//si el proyecto que seleccione es donde ejerce como responsable
					if(selectedProyecto.getListaprofesoresresponsables().get(i).getProfesorCedula().equals(profesor.getProfesorCedula())){
						listactividad = sactividad.buscarporProyecto(selectedProyecto);
						exito = true;
						break;
					}
				}
				if (!exito){
					//carga las actividades del proyecto seleccionado donde participa
					listactividad = sactividad
							.buscarActividadesDeUnProfesorParticipanteDeUnProyecto(
									profesor, selectedProyecto);
				}
			}
			//si no es ni representante ni responsable carga las actividades del proyecto seleccionado donde participa
			else{
				listactividad = sactividad
						.buscarActividadesDeUnProfesorParticipanteDeUnProyecto(
								profesor, selectedProyecto);
			}
		modeloActividad.addAll(listactividad);
		cargarInfoProyecto();
	}

	// Metodo que carga la lista de estudiantes asignado a la actividad que se
	// seleccione en el combo
	@Command
	@NotifyChange({ "cantHorasActividad", "descripcionActividad",
			"modeloEstudiantePrueba" })
	public void cargarInscritos() {
		modeloEstudianteActividadAsignada.clear();
		String codigoActividad = Integer.toString(selectedActividad
				.getActividadCodigo());
		List<ListadoEstudiantesActividadAsignada> listestudiante = sListadoEstudiantesActividadAsignada
				.buscarEstudiantesActividadAsignada(codigoActividad);
		modeloEstudianteActividadAsignada.addAll(listestudiante);
		cargarInfoActividad();
	}

	@Command
	@NotifyChange({ "nombreProyecto","descripcionProyecto", "responsableProyecto" })
	public void cargarInfoProyecto() {
		nombreProyecto = selectedProyecto.getProyectoNombre();
		descripcionProyecto = selectedProyecto.getProyectoDescripcion();
		responsableProyecto = selectedProyecto.getResponsableString();
	}

	@NotifyChange({ "cantHorasActividad", "descripcionActividad" })
	public void cargarInfoActividad() {
		cantHorasActividad = selectedActividad.getActividadCantidadHoras();
		descripcionActividad = selectedActividad.getActividadDescripcion();
	}

	// Asigna las horas que se le coloca al estudiante en el textbox
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void asignarHorasAlEstudiante(
			@ContextParam(ContextType.COMPONENT) Textbox componente) {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		String valor = componente.getValue();
		if (valor != "") {
			Integer horas = Integer.parseInt(valor);
			// Si la hora colocada en el textbox es menor o igual a la cantidad
			// de horas de la actividad se procede a asignarla
			if (horas <= cantHorasActividad) {
				String cedulaHora = (String) componente
						.getAttribute("cedulaHora");
				ListadoEstudiantesActividadAsignada estudiante;
				for (int i = 0; i < modeloEstudianteActividadAsignada.size(); i++) {
					estudiante = modeloEstudianteActividadAsignada.get(i);
					if (estudiante.getCedula() == cedulaHora) {
						modeloEstudianteActividadAsignada.get(i)
								.setHorasEjecutadas(horas);
						System.out.println("El estudiante tiene horas: "
								+ modeloEstudianteActividadAsignada.get(i)
										.getHorasEjecutadas());
					}

				}
			} else {
				// si la hora colocada en el textbox es mayor a la cantidad de
				// horas de la actividad muestra mensaje
				mensajeEmergente.errorHorasEjecutadas(cantHorasActividad);
			}
		}

	}

	// Asigna las horas que se le coloca al estudiante en el textbox
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void asignarFechaEjecucionAlEstudiante(
			@ContextParam(ContextType.COMPONENT) Datebox componente) {

		Date fechaEjecucion = componente.getValue();
		if (fechaEjecucion != null) {
			String cedulaHora = (String) componente.getAttribute("cedulaFecha");
			ListadoEstudiantesActividadAsignada estudiante;
			for (int i = 0; i < modeloEstudianteActividadAsignada.size(); i++) {
				estudiante = modeloEstudianteActividadAsignada.get(i);
				if (estudiante.getCedula() == cedulaHora) {
					modeloEstudianteActividadAsignada.get(i).setFechaEjecucion(
							fechaEjecucion);
					System.out.println("El estudiante tiene fecha: "
							+ modeloEstudianteActividadAsignada.get(i)
									.getFechaEjecucion());
				}
			}
		}
	}

	// Metodo que guarda la actividad ejecutada en la base de datos
	@Command
	@NotifyChange({ "descripcionActividad", "cantHorasActividad",
			"descripcionProyecto", "descripcionActividad",
			"cantHorasActividad", "descripcionProyecto", "responsableProyecto",
			"proyectos" })
	public void Guardar() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		if (!modeloEstudianteActividadAsignada.isEmpty()) {
			if (ValidarHorasyFechas()) {
				int cont = 0;
				for (int i = 0; i < modeloEstudianteActividadAsignada.size(); i++) {

					if (modeloEstudianteActividadAsignada.get(i)
							.getHorasEjecutadas() != null) {
						cont = 1;
						Date fecha = modeloEstudianteActividadAsignada.get(i)
								.getFechaEjecucion();
						Integer horas = modeloEstudianteActividadAsignada
								.get(i).getHorasEjecutadas();
						Integer codigoActividadAsignada = modeloEstudianteActividadAsignada
								.get(i).getCodigo_actividad_asignada();
						ActividadAsignada actividadAsignada = sactividadAsignada
								.buscarActividadAsignada(codigoActividadAsignada);
						ActividadEjecutada actividadEjecutada = new ActividadEjecutada(
								horas, fecha, actividadAsignada);
						sactividadEjecutada.guardar(actividadEjecutada);
					}
				}
				if (cont == 0) {
					mensajeEmergente.advertenciaLlenarCampos();
				} else {
					mensajeEmergente.informacionRegistroCorrecto();
					cancelar();
				}

			}
		} else {
			mensajeEmergente.errorCamposVacios();
		}

	}

	@Command
	public boolean ValidarHorasyFechas() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		boolean validarFecha = false;
		boolean exito = false;
		int i = 0;

		while (!validarFecha) {

			if (i < modeloEstudianteActividadAsignada.size()) {
				if (modeloEstudianteActividadAsignada.get(i)
						.getHorasEjecutadas() != null
						&& modeloEstudianteActividadAsignada.get(i)
								.getFechaEjecucion() == null) {
					mensajeEmergente.advertenciaFechaEjecucion();
					validarFecha = true;
				} else {
					i++;
				}
			} else {
				validarFecha = true;
				exito = true;
			}

		}
		return exito;
	}

	// metodo que cierra la ventana
	@Command
	public void salir() {
		win.detach();
	}

	// metodo que limpia los campos de la ventana
	@Command
	@NotifyChange({ "descripcionActividad", "cantHorasActividad","nombreProyecto",
			"descripcionProyecto", "responsableProyecto", "proyectos","modeloEstudiantesCulminados" })
	public void cancelar() {
		modeloEstudianteActividadAsignada.clear();
		modeloActividad.clear();
		descripcionActividad = "";
		nombreProyecto = "";
		descripcionProyecto = "";
		cantHorasActividad = null;
		responsableProyecto = "";
		modeloEstudiantesCulminados.clear();
	}

}
