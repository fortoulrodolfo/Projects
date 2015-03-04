package siagsce.viewmodel.maestros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import bsh.This;

import java.util.Set;

import javax.persistence.Column;

import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.general.StatusProyecto;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.viewmodel.seguridad.SecurityUtil;
/**
 * ViewModel para la vista que muestra el catalogo de los 
 * proyectos de Servicio Comunitario Estudiantil
 * disponibles para modificar 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoProyectos {

	
	/** 
	 * Declaracion de servicios del ViewModel
	 * */
	@WireVariable
	private SProyecto sproyecto;
	@WireVariable
	private SProfesor sprofesor;
	@WireVariable 
	SRepresentanteProfesoral smiembroCoordinacion;
	@WireVariable
	SActividad sactividad;
	
	/** 
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private List<Proyecto> listaProyecto;
	private List<Profesor> listaProfesor;
	
	private List<String> valores;
	private ListModelList<Proyecto> modeloProyecto;
	
	/** 
	 * Declaracion de Variables del ViewModel
	 * */
	private Proyecto selectedProyecto;
	private String proyectoCodigo;
	private String proyectoNombre;
	private String proyectoDescripcion;
	private String seleccion;
	private String texto;
	private String ventana;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	private Window win;
	@Wire
	private Textbox txtFiltroProyecto;
	
	@Wire
	private Popup popOpcionesProyecto;
	
	
	/**
	 * Setter y Getter
	 **/
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

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}


	public String getVentana() {
		return ventana;
	}

	public void setVentana(String ventana) {
		this.ventana = ventana;
	}
	
	public String getProyectoCodigo() {
		return proyectoCodigo;
	}
	
	public void setProyectoCodigo(String proyectoCodigo) {
		this.proyectoCodigo = proyectoCodigo;
	}
	
	public String getProyectoNombre() {
		return proyectoNombre;
	}
	
	public void setProyectoNombre(String proyectoNombre) {
		this.proyectoNombre = proyectoNombre;
	}
	
	public String getProyectoDescripcion() {
		return proyectoDescripcion;
	}
	
	public List<Profesor> getListaProfesor() {
		return listaProfesor;
	}

	public void setListaProfesor(List<Profesor> listaProfesor) {
		this.listaProfesor = listaProfesor;
	}

	public void setProyectoDescripcion(String proyectoDescripcion) {
		this.proyectoDescripcion = proyectoDescripcion;
	}
	
	public SProyecto getSproyecto() {
		return sproyecto;
	}
	
	public void setSproyecto(SProyecto sproyecto) {
		this.sproyecto = sproyecto;
	}
	
	public List<Proyecto> getListaProyecto() {
		return listaProyecto;
	}
	
	public void setListaProyecto(List<Proyecto> listaProyecto) {
		this.listaProyecto = listaProyecto;
	}
	
	public ListModelList<Proyecto> getModeloProyecto() {
		return modeloProyecto;
	}
	
	public void setModeloProyecto(ListModelList<Proyecto> modeloProyecto) {
		this.modeloProyecto = modeloProyecto;
	}
	
	public Proyecto getSelectedProyecto() {
		return selectedProyecto;
	}
	
	public void setSelectedProyecto(Proyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}

	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * @param ventana , variable que contiene el valor de cual es la ventana
	 *  	 que llama al catalogo
	 */
	@Init
	public void init(
			@ContextParam(ContextType.COMPONENT) Component win,
			@ExecutionArgParam("proyecto") String ventana,
			@ExecutionArgParam("reabrir") Boolean reabrirValor,
			@ExecutionArgParam("cerrar") Boolean cerrarValor) {
		this.ventana = ventana;
		Boolean reabrir = reabrirValor;
		Boolean cerrar = cerrarValor;
		MensajeBox.doSetTemplate();
		valores = new ArrayList<String>();
		valores.add("Código");
		valores.add("Nombre");

		if (this.ventana == "RegistrarProyecto") {
			listaProyecto = sproyecto.buscarPorStatus(StatusProyecto.Activo.toString());			
		} 
		else if (this.ventana == "EvaluacionPreinscripcionProyecto" || this.ventana == "AsignarActividades"){
			
				Profesor profesor = sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
				List <String> roles = SecurityUtil.roles;
				Boolean representante = false;
				Boolean responsable = false;
				for (int i = 0; i<roles.size(); i++){
					if (roles.get(i).contains("Representante Profesoral")){
						representante = true;
					}
					else if (roles.get(i).contains("Responsable Proyecto")){
							responsable = true;
					}	
				}
			
				if (representante && responsable){
					RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
					//listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "Activo");
					listaProyecto = sproyecto.buscarProyectoNoInactivosPrograma(representatantep.getDireccionProgramam());
					//List<Proyecto> listaResponsable = sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
					List<Proyecto> listaResponsable = sproyecto.buscarProyectosdelProfesorResponsable(profesor);
					for (int i = 0; i<listaResponsable.size();i++){
						if (!listaProyecto.contains(listaResponsable.get(i)))
							listaProyecto.add(listaResponsable.get(i));
						}
				}
				else if (representante){
					RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
					//listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "Activo");
					listaProyecto = sproyecto.buscarProyectoNoInactivosPrograma(representatantep.getDireccionProgramam());
					}
					else {
						//listaProyecto = sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
					 listaProyecto = sproyecto.buscarProyectosdelProfesorResponsable(profesor);
					}
			}
			else if (this.ventana == "RegistrarEjecucion"){
					Profesor profesor = sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
					List <String> roles = SecurityUtil.roles;
					Boolean representante = false;
					Boolean responsable = false;
					Boolean participante = false;
					
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
					//Si el usuario es representante, responsable y participante
					if (representante && responsable && participante){
						RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
						//listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "Activo");
						listaProyecto = sproyecto.buscarProyectoNoInactivosPrograma(representatantep.getDireccionProgramam());
						//List<Proyecto> listaResponsable = sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
						List<Proyecto> listaResponsable = sproyecto.buscarProyectosdelProfesorResponsable(profesor);
						for (int i = 0; i<listaResponsable.size();i++){
							if (!listaProyecto.contains(listaResponsable.get(i)))
								listaProyecto.add(listaResponsable.get(i));
						}
						//List<Proyecto> listaParticipante =  sactividad.buscarProyectosDeUnProfesorParticipante(profesor);
						List<Proyecto> listaParticipante = sactividad.findProyectosNoInactivosDeUnProfesorParticipante(profesor);
						for (int j=0;j<listaParticipante.size();j++){
							if (!listaProyecto.contains(listaParticipante.get(j)))
								listaProyecto.add(listaParticipante.get(j));
						}
					}
					//si el usuario es representante y responsable
					else if (representante && responsable){
							RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
//							listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "Activo");
							listaProyecto = sproyecto.buscarProyectoNoInactivosPrograma(representatantep.getDireccionProgramam());
//							List<Proyecto> listaResponsable = sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
							List<Proyecto> listaResponsable = sproyecto.buscarProyectosdelProfesorResponsable(profesor);
							for (int i = 0; i<listaResponsable.size();i++){
								if (!listaProyecto.contains(listaResponsable.get(i)))
									listaProyecto.add(listaResponsable.get(i));
							}
						}
						//si el usuario es representante y participante
						else if (representante && participante){
								RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
//								listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "Activo");
								listaProyecto = sproyecto.buscarProyectoNoInactivosPrograma(representatantep.getDireccionProgramam());
//								List<Proyecto> listaParticipante =  sactividad.buscarProyectosDeUnProfesorParticipante(profesor);
								List<Proyecto> listaParticipante = sactividad.findProyectosNoInactivosDeUnProfesorParticipante(profesor);
								for (int j=0;j<listaParticipante.size();j++){
									if (!listaProyecto.contains(listaParticipante.get(j)))
										listaProyecto.add(listaParticipante.get(j));
								}
							}
							//si el usuario es responsable y participante
							else if (responsable && participante){
									//listaProyecto = sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
									listaProyecto = sproyecto.buscarProyectosdelProfesorResponsable(profesor);
//									List<Proyecto> listaParticipante =  sactividad.buscarProyectosDeUnProfesorParticipante(profesor);
									List<Proyecto> listaParticipante = sactividad.findProyectosNoInactivosDeUnProfesorParticipante(profesor);
									for (int j=0;j<listaParticipante.size();j++){
										if (!listaProyecto.contains(listaParticipante.get(j)))
											listaProyecto.add(listaParticipante.get(j));
									}
								}
								//si el usuario solo es representante
								else if (representante){
										RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
										//listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "Activo");
										listaProyecto = sproyecto.buscarProyectoNoInactivosPrograma(representatantep.getDireccionProgramam());
									}
									//si el usuario solo es responsable
									else if(responsable){
											//listaProyecto = sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
											listaProyecto = sproyecto.buscarProyectosdelProfesorResponsable(profesor);
										}
										//si el usuario es solo participante
										else{
											//listaProyecto =  sactividad.buscarProyectosDeUnProfesorParticipante(profesor);
											listaProyecto = sactividad.findProyectosNoInactivosDeUnProfesorParticipante(profesor);
										}
				}
				else if(this.ventana == "FormalizarInscripcion") {
			
					Profesor profesor = sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
					List <String> roles = SecurityUtil.roles;
					Boolean representante = false;
					Boolean responsable = false;
					//for para obtener los roles
					for (int i = 0; i<roles.size(); i++){
						if (roles.get(i).contains("Representante Profesoral")){
							representante = true;
						}
						else if (roles.get(i).contains("Responsable Proyecto")){
								responsable = true;
						}	
					}
					if (reabrir){
						if (representante && responsable){
							RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
							listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "En Ejecución");
							List<Proyecto> listaResponsable = sproyecto.buscarProyectosEnEjecuciondelProfesorResponsable(profesor);
							for (int i = 0; i<listaResponsable.size();i++){
								if (!listaProyecto.contains(listaResponsable.get(i)))
									listaProyecto.add(listaResponsable.get(i));
								}
						}
						else if (representante){
							RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
							listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "En Ejecución");
							}
							else {
								listaProyecto = sproyecto.buscarProyectosEnEjecuciondelProfesorResponsable(profesor);
							}
					}
					else if (cerrar){	
						if (representante && responsable){
							RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
							listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "Activo");
							List<Proyecto> listaResponsable = sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
							for (int i = 0; i<listaResponsable.size();i++){
								if (!listaProyecto.contains(listaResponsable.get(i)))
									listaProyecto.add(listaResponsable.get(i));
								}
						}
						else if (representante){
							RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
							listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "Activo");
							}
							else {
								listaProyecto = sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
								//listaProyecto = sproyecto.buscarProyectosEnEjecuciondelProfesorResponsable(profesor);
							}
					}
					else{
						if (representante && responsable){
							RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
							listaProyecto = sproyecto.buscarProyectoNoInactivosPrograma(representatantep.getDireccionProgramam());
							//listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "Activo");
							//List<Proyecto> listaResponsable = sproyecto.buscarProyectosEnEjecuciondelProfesorResponsable(profesor);
							List<Proyecto> listaResponsable = sproyecto.buscarProyectosdelProfesorResponsable(profesor);
							for (int i = 0; i<listaResponsable.size();i++){
								if (!listaProyecto.contains(listaResponsable.get(i)))
									listaProyecto.add(listaResponsable.get(i));
								}
						}
						else if (representante){
							RepresentanteProfesoral representatantep = smiembroCoordinacion.buscarRepresentantePorCedula(profesor, "Activo");
							//listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(representatantep.getDireccionProgramam(), "Activo");
							listaProyecto = sproyecto.buscarProyectoNoInactivosPrograma(representatantep.getDireccionProgramam());
							}
							else {
								//listaProyecto = sproyecto.buscarProyectosActivodelProfesorResponsable(profesor);
								listaProyecto = sproyecto.buscarProyectosdelProfesorResponsable(profesor);
								//listaProyecto = sproyecto.buscarProyectosEnEjecuciondelProfesorResponsable(profesor);
							}
					}

				}
		modeloProyecto = new ListModelList<Proyecto>(listaProyecto);
		this.win = (Window) win;
	}
	
	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		txtFiltroProyecto.setPlaceholder("Nombre");
	
	}
	
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de proyectos con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoProyectos}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "modeloProyecto" })
	public void filtrarProyecto() {
		try {
			List<Proyecto> aux = new ArrayList<Proyecto>();
			aux = sproyecto.buscarPorStatus(StatusProyecto.Activo.toString());
			modeloProyecto.clear();

			if (seleccion == null || seleccion == "") {
				if (texto == "")
					modeloProyecto.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProyectoNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloProyecto.add(aux.get(i));

						}
					}
				}

			} else {
				if (seleccion == "Código") {
					if (texto == "")
						modeloProyecto.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProyectoCodigo()
									.contains(texto.toLowerCase())) {
								modeloProyecto.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion=="Nombre") {
						if (texto == "")
							modeloProyecto.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProyectoNombre()
										.toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloProyecto.add(aux.get(i));

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
	 * Segun la seleccion de un criterio de filtrado
	 * coloca una marca de agua con el nombre de la seleccion
	 * */
	@Command
	public void seleccionFiltro() {
		System.out.print(seleccion);
		if (seleccion.equals("Nombre")) {
			txtFiltroProyecto.setPlaceholder("Nombre");
			popOpcionesProyecto.close();
		} else {
			if (seleccion.equals("Código")) {
				txtFiltroProyecto.setPlaceholder("Código");
				popOpcionesProyecto.close();
			
			}
		}

	}
	/**
	 * Envia El proyecto seleccionado a la ventana principal
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void save() {

		if (selectedProyecto != null) {
				win.setAttribute("proyecto", this.selectedProyecto);
				win.detach();
		} 
		else {
			Messagebox.show(" Seleccione un proyecto", "¡Informacion!",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	/** 
	 *Cierra la ventana 
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}
}
