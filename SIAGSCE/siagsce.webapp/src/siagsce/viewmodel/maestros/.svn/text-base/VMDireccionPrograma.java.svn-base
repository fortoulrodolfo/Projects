package siagsce.viewmodel.maestros;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import bsh.This;
import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Exonerado;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.general.EnviarCorreo;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
import siagsce.modelo.servicio.seguridad.ServicioUsuario;


/**
 * ViewModel para la vista de Direccion y 
 * Director de programa en el 
 * Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDireccionPrograma {

	
	/** 
	 * Declaracion de servicios del ViewModel
	 * */
	private Window win;
	@WireVariable
	SDireccionPrograma sdireccionPrograma;
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	ServicioUsuario su;

	@WireVariable
	ServicioGrupo sg;

	@WireVariable
	SDirectorPrograma sdirector;

	@WireVariable
	SEstudiante sestudiante;

	@WireVariable
	SProyecto sproyecto;
	
	 @Wire
	 private Textbox txtFiltroDirector;
	 @Wire
	private Popup popOpcionesDirector;
	
	/** 
	 * Declaracion de Variables del ViewModel
	 * */
	private Profesor profesor;
	private Integer codigo;
	private String nombre;
	private String profesorCedulaDirector;
	private Integer unidadesCredito;
	private String profesorCedula, profesorNombre, profesorApellido,
			profesorTelefono, profesorEmail, profesorDireccion, profesorStatus;

	private Boolean btnGuardarDireccion = false;
	private Boolean btnEditarDireccion = false;
	private Boolean btnGuardarDirector = false;
	private Boolean btnCambiarDirector = true;
	private Boolean btnEliminarDireccion = true;	
	private boolean nuevoDirector = false;
	private Boolean cambiarDirector = false;
	private Boolean editarDireccion = false;
	private Boolean mostrarCodigo = false;
	private Boolean mostrarNombre = false;
	private Boolean mostrarUnidades = false;
	private Boolean mostrarBotonProfesores = false;
	private boolean exito = false;
	private Boolean mostrarProfesorCedula = true;
	private Boolean mostrarProfesorNombre = true;
	private Boolean mostrarProfesorApellido = true;
	private DireccionPrograma nuevaDireccion;
	private Profesor profeNuevo;
	private DireccionPrograma selectedDireccionPrograma;
	private DireccionPrograma direccionComparar;
	private Boolean marca = false;
	private String textoDirector;
	private String seleccionDirector;

	MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
	EnviarCorreo enviarCorreo=new EnviarCorreo();

	
	/** 
	 * Declaracion de listas y otras estructuras de datos
	 * */
	List<DireccionPrograma> listDireccionPrograma;
	ListModelList<DireccionPrograma> modeloDireccionPrograma;
	private List<Profesor> listprofe;
	ListModelList<DirectorPrograma> modelodirector;
	ListModelList<DirectorPrograma> modelodirectorhistorial;
	private List<String> valoresDirector;


	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		listDireccionPrograma = sdireccionPrograma.buscarTodo();
		modeloDireccionPrograma = new ListModelList<DireccionPrograma>(
				listDireccionPrograma);
		modelodirector = new ListModelList<DirectorPrograma>();
		modelodirectorhistorial = new ListModelList<DirectorPrograma>();
		agregarhistorial();
		valoresDirector = new ArrayList<String>();
		valoresDirector.add("Cédula");
		valoresDirector.add("Nombre");
		valoresDirector.add("Apellido");
		valoresDirector.add("Programa");

		btnGuardarDireccion = false;
		btnEditarDireccion = true;
		btnGuardarDirector = false;
		btnCambiarDirector = true;
		this.btnEliminarDireccion = true;

		 this.nuevoDirector = false;
		cambiarDirector = false;
		editarDireccion = false;

		mostrarCodigo = false;
		mostrarNombre = false;
		mostrarUnidades = false;
		mostrarBotonProfesores = false;
		exito = false;

		mostrarProfesorCedula = true;
		mostrarProfesorNombre = true;
		mostrarProfesorApellido = true;

		
		MensajeBox.doSetTemplate();
	}

	@AfterCompose
	 public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
	}
	/**
	 * Metodo que carga el catalogo de direccion de programa
	 */
	
	@NotifyChange({ "codigo", "nombre", "unidadesCredito", "profesorCedula",
			"profesorNombre", "profesorApellido", "profesorTelefono",
			"profesorEmail", "profesorDireccion", "mostrarNombre",
			"mostrarUnidades", "mostrarProfesorApellido",
			"mostrarProfesorCedula", "mostrarProfesorNombre",
			"direccionComparar", "mostrarBotonProfesore",
			"btnGuardarDireccion", "btnEditarDireccion", "btnCambiarDirector",
			"btnGuardarDirector", "profesorCedulaDirector", "cambiarDirector",
			"editarDireccion","seleccion"})
	@Command
	public void MostrarCatalogoDireccionPrograma(
			@ContextParam(ContextType.VIEW) Component view) {
		String status = StatusDirectorPrograma.Activo.toString();
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoDireccionPrograma.zul",
						null, null);
		comp.doModal();
		try {
			List<DireccionPrograma> listdireccion = (List<DireccionPrograma>) comp
					.getAttribute("seleccionado");
			if (!listdireccion.equals(null)) {
				
				codigo = listdireccion.get(0).getDireccionCodigo();
				nombre = listdireccion.get(0).getDireccionNombre();
				unidadesCredito = listdireccion.get(0)
						.getDireccionUnidadesCredito();

				DirectorEstatusPrograma(
						StatusDirectorPrograma.Activo.toString(),
						listdireccion.get(0));

				this.direccionComparar = listdireccion.get(0);

			
				this.mostrarProfesorApellido = true;
				this.mostrarProfesorCedula = true;
				this.mostrarProfesorNombre = true;
				this.mostrarBotonProfesores = false;
			}
			

		} catch (NullPointerException e) {
			// TODO: handle exception
		}

	}

	
	
	/**
	 * Metodo que carga el catalogo de profesores que  aun no han
	 * sido asignados como directores  de programa
	 */
		
	
	@NotifyChange({ "profesorCedula", "profesorNombre", "profesorApellido",
			"mostrarProfesorApellido", "mostrarProfesorCedula",
			"mostrarProfesorNombre", "profeNuevo", "btnCambiarDirector",
			"btnGuardarDirector", "cambiarDirector", "editarDireccion",	"nuevoDirector"})
	@Command
	public void MostrarCatalogoProfesorAsd(
			@ContextParam(ContextType.VIEW) Component view) {
		this.profeNuevo = null;

		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoProfesoresAptosNoSonDirectores.zul",
						null, null);
		comp.doModal();
		try {
			listprofe = (List<Profesor>) comp.getAttribute("seleccionado");

			this.profesorCedula = listprofe.get(0).getProfesorCedula();
			this.profesorNombre = listprofe.get(0).getProfesorNombre();
			this.profesorApellido = listprofe.get(0).getProfesorApellido();
			this.profesorEmail = listprofe.get(0).getProfesorEmail();
			this.profesorTelefono = listprofe.get(0).getProfesorTelefono();
			this.profesorStatus = listprofe.get(0).getProfesorStatus();

			this.profeNuevo = listprofe.get(0);
			
			if(this.profeNuevo != null &&  this.nuevoDirector == true){
				this.btnCambiarDirector = false;
				this.btnGuardarDirector = true;
				this.nuevoDirector = false;
			}
			else{
				if(this.nuevoDirector == false){
					this.btnCambiarDirector = true;
					this.btnGuardarDirector = false;
				}
			}

			this.mostrarNombre = false;
			this.mostrarUnidades = false;

			
			this.mostrarProfesorApellido = true;
			this.mostrarProfesorCedula = true;
			this.mostrarProfesorNombre = true;

		} catch (NullPointerException e) {
			// TODO: handle exception
		}

	}

	
	/**
	 * Metodo que habilita los botones para que uns  dirección 
	 * de programa sea  editada
	 */	
	
	@NotifyChange({ "mostrarNombre", "mostrarUnidades" , "btnGuardarDireccion", "btnEditarDireccion" })
	@Command
	public void editarDireccion() {
		
		this.mostrarNombre = false;
		this.mostrarUnidades = false;
		this.btnGuardarDireccion = false;
		this.btnEditarDireccion = true;
		
	}

	/**
	 * Metodo que cambia un director de programa en la 
	 * Tabla de Director de  programa  de la  base de dato
	 */	

	@NotifyChange({ "exito", "codigo", "nombre", "unidadesCredito",
			"profesorCedula", "profesorNombre", "profesorApellido",
			"profeNuevo", "editarDireccion", "cambiarDirector",
			"btnCambiarDirector", "btnGuardarDirector", "btnGuardarDireccion",
			"btnEditarDireccion" })
	@Command
	public void cambiarDirector() {

		if (this.cambiarDirector.equals(true)
				&& this.editarDireccion.equals(true)) {
			/** Metodo que permite actualizar el director */				

			if (this.profesorCedula != null) {
				Date fecha = new Date();

				Profesor profesorViejo = new Profesor();
				profesorViejo = sprofesor
						.buscarPorCedula(this.profesorCedulaDirector);
				Usuario usuario=null;
				DirectorPrograma directorViejo = sdirector
						.buscarPorProfesorYEstatus(profesorViejo,
								StatusDirectorPrograma.Activo.toString());

				if (directorViejo != null) {
					directorViejo
							.setDirectorProgramaStatus(StatusDirectorPrograma.Inactivo
									.toString());
					directorViejo.setDirectorProgramaFechaCulminacion(fecha);
					usuario=su.encontrarUsuario(profesorViejo.getIdusuario());
					for(int k=0;k<usuario.getGrupos().size();k++){
						if(usuario.getGrupos().get(k).getNombre().equals("Director"))
							usuario.getGrupos().remove(k);
						    break;
						}
					su.guardarUsuario(usuario);
					sdirector.guardar(directorViejo);
					enviarCorreo.sendEmailInfo(profesorViejo.getProfesorEmail(),"Director de Programa de la carrera "+directorViejo.getDireccionPrograma().getDireccionNombre());
					if (this.profeNuevo != null
							&& this.direccionComparar != null) {
						DirectorPrograma directorNuevo = new DirectorPrograma(
								fecha, null,
								StatusDirectorPrograma.Activo.toString(),
								this.profeNuevo, this.direccionComparar);
						usuario=su.encontrarUsuario(this.profeNuevo.getIdusuario());
						usuario.getGrupos().add(sg.buscarGrupoNombre("Director"));
						su.guardarUsuario(usuario);
						sdirector.guardar(directorNuevo);
						mensajeEmergente.informacionActualizarDatos();
						enviarCorreo.sendEmail(this.profeNuevo.getProfesorEmail(),"Director de Programa de la carrera "+directorViejo.getDireccionPrograma().getDireccionNombre(), usuario.getNombreUsuario(), usuario.getClave());
						
						cancelar();
						this.btnCambiarDirector = true;
						this.btnGuardarDirector = false;
						this.btnGuardarDireccion = false;
						this.btnEditarDireccion = true;

					} else {

						/** no se  ha  elegido el profesor*/				

					}

				} else {
					mensajeEmergente.advertenciaDirectorInactivo();
				}

			} else {

				mensajeEmergente.advertenciaLlenarCampos();
			}

		}

	}

	/**
	 * Metodo que guarda y modifica la direccion de programa
	 * en la  tabla de dirección de programa de la  base de dato
	 */	
	
	@NotifyChange({ "exito", "codigo", "nombre", "unidadesCredito",
			"profesorCedula", "profesorNombre", "profesorApellido",
			"profeNuevo", "editarDireccion", "cambiarDirector",
			"btnCambiarDirector", "btnGuardarDirector", "btnGuardarDireccion",
			"btnEditarDireccion", "selectedDireccionPrograma" })
	@Command
	public void guardar() {

		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		profeNuevo=sprofesor.buscarPorCedula(profesorCedula);
		if (this.nombre != null || this.unidadesCredito != null
				|| this.profeNuevo != null) {
			if (this.cambiarDirector.equals(false)
					&& this.editarDireccion.equals(false)) {
				
				/**
				 * Metodo que guarda la direcciony el director cuando
				 * son nuevos
				 */	
				

				if (this.nombre != null && this.unidadesCredito != null && 
						this.nombre != "" ) {
					if (this.profesorCedula != null) {
						String directorProgramaStatus = StatusDirectorPrograma.Activo
								.toString();
						DireccionPrograma direccion = sdireccionPrograma
								.buscarPorNombre(this.nombre);
						if (direccion == null) {
							this.nuevaDireccion = new DireccionPrograma();
							nuevaDireccion.setDireccionNombre(this.nombre);
							nuevaDireccion
									.setDireccionUnidadesCredito(this.unidadesCredito);
							DirectorPrograma director = new DirectorPrograma(
									new Date(), null, directorProgramaStatus,
									this.profeNuevo, nuevaDireccion);
							nuevaDireccion.getDirectorprograma().add(director);
							sdireccionPrograma.guardar(nuevaDireccion);
							agregarhistorial();

							mensajeEmergente.informacionRegistroCorrecto();
							Usuario usuario = su
									.encontrarUsuarioPorNombreUsuario(this.profeNuevo
											.getProfesorCedula());
							Grupo g = sg.buscarGrupoNombre("Director");
							usuario.addGrupo(g);// el grupo 1007 es el director
												// de programa
							su.guardarUsuario(usuario);
							enviarCorreo.sendEmail(
									this.profeNuevo.getProfesorEmail(),
									"Director de Programa de la carrera "+ this.nuevaDireccion.getDireccionNombre(),
									usuario.getNombreUsuario(),
									usuario.getClave());

							cancelar();
							this.btnCambiarDirector = true;
							this.btnGuardarDirector = false;
							this.btnGuardarDireccion = false;
							this.btnEditarDireccion = true;

						} else {
							/**Mensaje  que  ya  existe  la  direccion con es  nombre */	
							
							mensajeEmergente.advertenciaDireccionYaExiste();
						}

					} else {

						mensajeEmergente.advertenciaSeleccionarDirector();
					}
				}
				else{
					 mensajeEmergente.advertenciaCargarDatosDirecccion();
				}
			}
			else{
				/**Metodo que actualiza la direccion de programa */
			
				if (this.cambiarDirector.equals(true)
						&& this.editarDireccion.equals(true)) {
					

					if (this.nombre != null && this.unidadesCredito != null) {
						
						
						String nombreViejo = direccionComparar.getDireccionNombre() ;
						Integer unidadesVieja = direccionComparar.getDireccionUnidadesCredito();
						String cambioNombre;
						String cambioUnidades;
						if (nombreViejo.equals(this.nombre)){
							cambioNombre = "no";
						}
						else{
							cambioNombre = "si";
						}
						
						if(unidadesVieja == this.unidadesCredito){
							cambioUnidades = "no";
						}
						else{
							cambioUnidades = "si";
							
						}
						
						if(cambioNombre.equals("si") && cambioUnidades.equals("si")){
							this.direccionComparar.setDireccionNombre(this.nombre);
							this.direccionComparar
									.setDireccionUnidadesCredito(this.unidadesCredito);
							DirectorPrograma directorActual=sdirector.buscarDirectorPorProgramaYEstatus(StatusDirectorPrograma.Activo.toString(), direccionComparar);
							Usuario usuario=su.encontrarUsuario(directorActual.getProfesor().getIdusuario());
							for(int k=0;k<usuario.getGrupos().size();k++){
								if(usuario.getGrupos().get(k).getNombre().equals("Director"))
									usuario.getGrupos().remove(k);
								    break;
								}
							su.guardarUsuario(usuario);
							usuario=su.encontrarUsuario(this.profeNuevo.getIdusuario());
							usuario.getGrupos().add(sg.buscarGrupoNombre("Director"));
							su.guardarUsuario(usuario);
							sdireccionPrograma.guardar(direccionComparar);
							mensajeEmergente.informacionActualizarDatos();
							enviarCorreo.sendEmailInfo(directorActual.getProfesor().getProfesorEmail(),"Director de Programa de la carrera "+directorActual.getDireccionPrograma().getDireccionNombre());
							enviarCorreo.sendEmail(this.profeNuevo.getProfesorEmail(),"Director de Programa de la carrera "+direccionComparar.getDireccionNombre(), usuario.getNombreUsuario(), usuario.getClave());
							agregarhistorial();
							cancelar();

							this.btnCambiarDirector = true;
							this.btnGuardarDirector = false;
							this.btnGuardarDireccion = false;
							this.btnEditarDireccion = true;

							
						}
						else{
							if(cambioNombre.equals("no") && cambioUnidades.equals("no")){
								mensajeEmergente.advertenciaNoSeEfectuoModificacion();
								cancelar();

								this.btnCambiarDirector = true;
								this.btnGuardarDirector = false;
								this.btnGuardarDireccion = false;
								this.btnEditarDireccion = true;
							}
							else{
								if(cambioNombre.equals("si") && cambioUnidades.equals("no")){
									this.direccionComparar.setDireccionNombre(this.nombre);
									this.direccionComparar
											.setDireccionUnidadesCredito(this.unidadesCredito);
									DirectorPrograma directorActual=sdirector.buscarDirectorPorProgramaYEstatus(StatusDirectorPrograma.Activo.toString(), direccionComparar);
									Usuario usuario=su.encontrarUsuario(directorActual.getProfesor().getIdusuario());
									for(int k=0;k<usuario.getGrupos().size();k++){
										if(usuario.getGrupos().get(k).getNombre().equals("Director"))
											usuario.getGrupos().remove(k);
										    break;
										}
									su.guardarUsuario(usuario);
									usuario=su.encontrarUsuario(this.profeNuevo.getIdusuario());
									usuario.getGrupos().add(sg.buscarGrupoNombre("Director"));
									su.guardarUsuario(usuario);
									sdireccionPrograma.guardar(direccionComparar);
									mensajeEmergente.informacionActualizarDatos();
									enviarCorreo.sendEmailInfo(directorActual.getProfesor().getProfesorEmail(),"Director de Programa de la carrera "+directorActual.getDireccionPrograma().getDireccionNombre());
									enviarCorreo.sendEmail(this.profeNuevo.getProfesorEmail(),"Director de Programa de la carrera "+direccionComparar.getDireccionNombre(), usuario.getNombreUsuario(), usuario.getClave());
									agregarhistorial();
									cancelar();

									this.btnCambiarDirector = true;
									this.btnGuardarDirector = false;
									this.btnGuardarDireccion = false;
									this.btnEditarDireccion = true;

									
								}
								else{
									this.direccionComparar.setDireccionNombre(this.nombre);
									this.direccionComparar
											.setDireccionUnidadesCredito(this.unidadesCredito);
									DirectorPrograma directorActual=sdirector.buscarDirectorPorProgramaYEstatus(StatusDirectorPrograma.Activo.toString(), direccionComparar);
									Usuario usuario=su.encontrarUsuario(directorActual.getProfesor().getIdusuario());
									for(int k=0;k<usuario.getGrupos().size();k++){
										if(usuario.getGrupos().get(k).getNombre().equals("Director"))
											usuario.getGrupos().remove(k);
										    break;
										}
									su.guardarUsuario(usuario);
									usuario=su.encontrarUsuario(this.profeNuevo.getIdusuario());
									usuario.getGrupos().add(sg.buscarGrupoNombre("Director"));
									su.guardarUsuario(usuario);
									sdireccionPrograma.guardar(direccionComparar);
									mensajeEmergente.informacionActualizarDatos();
									enviarCorreo.sendEmailInfo(directorActual.getProfesor().getProfesorEmail(),"Director de Programa de la carrera "+directorActual.getDireccionPrograma().getDireccionNombre());
									enviarCorreo.sendEmail(this.profeNuevo.getProfesorEmail(),"Director de Programa de la carrera "+direccionComparar.getDireccionNombre(), usuario.getNombreUsuario(), usuario.getClave());
									
									cancelar();

									this.btnCambiarDirector = true;
									this.btnGuardarDirector = false;
									this.btnGuardarDireccion = false;
									this.btnEditarDireccion = true;

								}
							}
						}
						
					
					} else {
						mensajeEmergente.advertenciaLlenarCampos();
					}
				}
			
			
			}
			
			
			
			
		} else {
			mensajeEmergente.advertenciaLlenarCampos();

		}
		agregarhistorial();
	}

	
	
	
	/**
	 * Metodo que  limpia los  valores de las  variables
	 * dentro de la ventana  de dirección de programa y director de programa
	 **/
	
	@Command
	@NotifyChange({ "codigo", "nombre", "unidadesCredito", "profesorCedula",
			"profesorNombre", "profesorApellido", "profesorTelefono",
			"profesorEmail", "profesorDireccion", "mostrarNombre",
			"mostrarUnidades", "mostrarProfesorCedula",
			"mostrarProfesorNombre", "mostrarProfesorApellido",
			"btnCambiarDirector", "btnGuardarDirector", "btnGuardarDireccion",
			"btnEditarDireccion", "modelodirector", "modelodirectorhistorial",
			"cambiarDirector ", "editarDireccion", "btnEliminarDireccion" })
	public void cancelar() {

		this.btnCambiarDirector = true;
		this.btnGuardarDirector = false;
		this.btnGuardarDireccion = false;
		this.btnEditarDireccion = true;
		this.btnEliminarDireccion = true;

		this.cambiarDirector = false;
		this.editarDireccion = false;

		this.modelodirector.clear();
		this.modelodirectorhistorial.clear();

		this.codigo = null;
		this.nombre = "";
		this.unidadesCredito = null;
		this.profesorCedula = "";
		this.profesorNombre = "";
		this.profesorApellido = "";
		this.profesorTelefono = "";
		this.profesorEmail = "";
		this.profesorDireccion = "";

		this.mostrarNombre = false;
		this.mostrarUnidades = false;
		this.mostrarProfesorCedula = false;
		this.mostrarProfesorNombre = false;
		this.mostrarProfesorApellido = false;

	}

	

	/**
	 * Metodo que busca el director de programa por estatus
	 * y por programa
	 **/
	
	@NotifyChange({ "profesorCedula", "profesorNombre", "profesorApellido",
			"profesorTelefono", "profesorEmail", "profesorDireccion",
			"profesorCedulaDirector", "btnGuardarDirector", "cambiarDirector",
			"btnCambiarDirector", "cambiarDirector", "editarDireccion",
			"btnEliminarDireccion", "mostrarNombre", "mostrarUnidades" , "nuevoDirector"})
	public void DirectorEstatusPrograma(String status,
			DireccionPrograma programa) {
		DirectorPrograma director = sdirector
				.buscarDirectorPorProgramaYEstatus(status, programa);

		if (director != null) {
			profesorNombre = director.getProfesor().getProfesorNombre();
			profesorCedula = director.getProfesor().getProfesorCedula();

			this.profesorCedulaDirector = director.getProfesor()
					.getProfesorCedula();

			profesorApellido = director.getProfesor().getProfesorApellido();
			profesorDireccion = director.getProfesor().getProfesorDireccion();
			profesorEmail = director.getProfesor().getProfesorEmail();
			profesorTelefono = director.getProfesor().getProfesorTelefono();

			this.mostrarNombre = true;
			this.mostrarUnidades = true;
			
			this.nuevoDirector = true;
			
			this.btnGuardarDirector = true;
			this.btnGuardarDireccion = true;

			this.btnEditarDireccion = false;
			this.btnCambiarDirector = true;

			this.cambiarDirector = true;
			this.editarDireccion = true;

			this.btnEliminarDireccion = false;

		} else {

			this.btnCambiarDirector = true;
		}
	}

	
	/**
	 * Metodo elimina los  daos  del director y de la dirección
	 * de programa en la  tablas respectivas en  la base de dato
	 **/

	
	@NotifyChange({ "codigo", "nombre", "unidadesCredito", "profesorCedula",
		"profesorNombre", "profesorApellido", "profesorTelefono",
		"profesorEmail", "profesorDireccion", "mostrarNombre",
		"mostrarUnidades", "mostrarProfesorCedula",
		"mostrarProfesorNombre", "mostrarProfesorApellido",
		"btnCambiarDirector", "btnGuardarDirector", "btnGuardarDireccion",
		"btnEditarDireccion", "modelodirector", "modelodirectorhistorial",
		"cambiarDirector ", "editarDireccion", "btnEliminarDireccion" })
	@Command
	public void eliminarDireccion() {

		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		DireccionPrograma direccion = sdireccionPrograma
				.buscarPorNombre(this.nombre);
		
		if (direccion != null) {

			List<Estudiante> listEstudiantes = new ArrayList<Estudiante>();
			listEstudiantes = sestudiante.buscarPorDireccionPrograma(direccion);

			if (listEstudiantes.size() == 0) {
				
				/**
				 * No hay estudiantes con esta direccion la puedo
				 * eliminar.
				 * Debo buscar los  proyectos  a ver si existe uno con esta
				 * direccion asociada
				 **/
				
				List<Proyecto> proyectos = new ArrayList<Proyecto>(
						sproyecto.buscarPorPrograma(direccion));
				if (proyectos.size() == 0) {

					DirectorPrograma director = sdirector
							.buscarPorProgramaYEstatus(direccion,
									StatusDirectorPrograma.Activo.toString());

					if (director != null) {
						sdirector.eliminar(director);
						Usuario usuario=su.encontrarUsuario(director.getProfesor().getIdusuario());
						for(int k=0;k<usuario.getGrupos().size();k++){
						if(usuario.getGrupos().get(k).getNombre().equals("Director"))
							usuario.getGrupos().remove(k);
						    break;
						}
						su.guardarUsuario(usuario);
						sdireccionPrograma.eliminar(direccion);
						mensajeEmergente.informacionEliminarCorrecto();
						enviarCorreo.sendEmailInfo(director.getProfesor().getProfesorEmail(),"Director de Programa de la carrera "+director.getDireccionPrograma().getDireccionNombre());
						
						cancelar();
						this.btnCambiarDirector = true;
						this.btnGuardarDirector = false;
						this.btnGuardarDireccion = false;
						this.btnEditarDireccion = true;
						this.btnEliminarDireccion = true;

					} else {
						/**
						 *Mensaje no puedo eliminar direccion porque
						 *existen proyectos  asociados
						 **/
					
						mensajeEmergente.errorImposibleEliminar();
						cancelar();
						this.btnCambiarDirector = true;
						this.btnGuardarDirector = false;
						this.btnGuardarDireccion = false;
						this.btnEditarDireccion = true;
						this.btnEliminarDireccion = true;

					

					}
				}

			} else {
				/**Mensaje no puedo eliminar direccion **/
				
				mensajeEmergente.errorImposibleEliminar();
				cancelar();
				this.btnCambiarDirector = true;
				this.btnGuardarDirector = false;
				this.btnGuardarDireccion = false;
				this.btnEditarDireccion = true;
				this.btnEliminarDireccion = true;
				
			}
		}

	}

	
	/**
	 * Metodo para  agregar el  historial del Director de programa 
	 * con  etatus activo
	 **/
	@NotifyChange({"modelodirectorhistorial", "marca"})
	@Command
	public void cargarDirectoresActuales() {
		List<DirectorPrograma> directores = sdirector.buscarPorEstatus(StatusDirectorPrograma.Activo.toString());
		modelodirectorhistorial.clear();
		setMarca(!marca);
		if (marca == true) {
			if (directores.size() != 0){
				modelodirectorhistorial.addAll(directores);
			}
			else{
				
				
				agregarhistorial();
			}
		}
		
		else{
			agregarhistorial();
		}
	
	}
	 
	
	
	
	/**
	 * Metodo para  agregar el  historial del Director de programa
	 **/
	@NotifyChange({"modelodirectorhistorial", "marca"})
	@Command
	public void agregarhistorial() {
		
			List<DirectorPrograma> listaux = sdirector.buscarOrdenado();			
			modelodirectorhistorial.clear();
			modelodirectorhistorial.addAll(listaux);
		
	}

	
	/**
	 * Metodo que cierra la ventana de dirección y director de programa
	 **/
	
	@Command
	public void cerrarVentana() {
		win.detach();
	}
	
	
	/**
	 * Setter y Getter
	 **/
	
	public ListModelList<DirectorPrograma> getModelodirectorhistorial() {
		return modelodirectorhistorial;
	}

	public String getTextoDirector() {
		return textoDirector;
	}


	public void setTextoDirector(String textoDirector) {
		this.textoDirector = textoDirector;
	}


	public String getSeleccionDirector() {
		return seleccionDirector;
	}


	public void setSeleccionDirector(String seleccionDirector) {
		this.seleccionDirector = seleccionDirector;
	}


	


	public List<String> getValoresDirector() {
		return valoresDirector;
	}


	public void setValoresDirector(List<String> valoresDirector) {
		this.valoresDirector = valoresDirector;
	}


	public void setModelodirectorhistorial(
			ListModelList<DirectorPrograma> modelodirectorhistorial) {
		this.modelodirectorhistorial = modelodirectorhistorial;
	}

	public ListModelList<DirectorPrograma> getModelodirector() {
		return modelodirector;
	}

	public void setModelodirector(ListModelList<DirectorPrograma> modelodirector) {
		this.modelodirector = modelodirector;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getUnidadesCredito() {
		return unidadesCredito;
	}

	public void setUnidadesCredito(Integer unidadesCredito) {
		this.unidadesCredito = unidadesCredito;
	}

	public String getProfesorCedula() {
		return profesorCedula;
	}

	public String getProfesorCedulaDirector() {
		return profesorCedulaDirector;
	}

	public void setProfesorCedulaDirector(String profesorCedulaDirector) {
		this.profesorCedulaDirector = profesorCedulaDirector;
	}

	public String getProfesorStatus() {
		return profesorStatus;
	}

	public void setProfesorStatus(String profesorStatus) {
		this.profesorStatus = profesorStatus;
	}

	public void setProfesorCedula(String profesorCedula) {
		this.profesorCedula = profesorCedula;
	}

	public String getProfesorNombre() {
		return profesorNombre;
	}

	public void setProfesorNombre(String profesorNombre) {
		this.profesorNombre = profesorNombre;
	}

	public String getProfesorApellido() {
		return profesorApellido;
	}

	public void setProfesorApellido(String profesorApellido) {
		this.profesorApellido = profesorApellido;
	}

	public String getProfesorTelefono() {
		return profesorTelefono;
	}

	public void setProfesorTelefono(String profesorTelefono) {
		this.profesorTelefono = profesorTelefono;
	}

	public String getProfesorEmail() {
		return profesorEmail;
	}

	public void setProfesorEmail(String profesorEmail) {
		this.profesorEmail = profesorEmail;
	}

	public String getProfesorDireccion() {
		return profesorDireccion;
	}

	public void setProfesorDireccion(String profesorDireccion) {
		this.profesorDireccion = profesorDireccion;
	}

	public Boolean getBtnEliminarDireccion() {
		return btnEliminarDireccion;
	}

	public void setBtnEliminarDireccion(Boolean btnEliminarDireccion) {
		this.btnEliminarDireccion = btnEliminarDireccion;
	}

	public Boolean getMostrarBotonProfesores() {
		return mostrarBotonProfesores;
	}

	public void setMostrarBotonProfesores(Boolean mostrarBotonProfesores) {
		this.mostrarBotonProfesores = mostrarBotonProfesores;
	}

	public DireccionPrograma getDireccionComparar() {
		return direccionComparar;
	}

	public void setDireccionComparar(DireccionPrograma direccionComparar) {
		this.direccionComparar = direccionComparar;
	}

	
	public boolean isNuevoDirector() {
		return nuevoDirector;
	}

	public void setNuevoDirector(boolean nuevoDirector) {
		this.nuevoDirector = nuevoDirector;
	}

	public List<Profesor> getListprofe() {
		return listprofe;
	}

	public void setListprofe(List<Profesor> listprofe) {
		this.listprofe = listprofe;
	}

	public DireccionPrograma getNuevaDireccion() {
		return nuevaDireccion;
	}

	public void setNuevaDireccion(DireccionPrograma nuevaDireccion) {
		this.nuevaDireccion = nuevaDireccion;
	}

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public Boolean getMostrarCodigo() {
		return mostrarCodigo;
	}

	public void setMostrarCodigo(Boolean mostrarCodigo) {
		this.mostrarCodigo = mostrarCodigo;
	}

	public Boolean getMostrarNombre() {
		return mostrarNombre;
	}

	public void setMostrarNombre(Boolean mostrarNombre) {
		this.mostrarNombre = mostrarNombre;
	}

	public Boolean getMostrarUnidades() {
		return mostrarUnidades;
	}

	public void setMostrarUnidades(Boolean mostrarUnidades) {
		this.mostrarUnidades = mostrarUnidades;
	}

	public Boolean getMostrarProfesorCedula() {
		return mostrarProfesorCedula;
	}

	public void setMostrarProfesorCedula(Boolean mostrarProfesorCedula) {
		this.mostrarProfesorCedula = mostrarProfesorCedula;
	}

	public Boolean getMostrarProfesorNombre() {
		return mostrarProfesorNombre;
	}

	public void setMostrarProfesorNombre(Boolean mostrarProfesorNombre) {
		this.mostrarProfesorNombre = mostrarProfesorNombre;
	}

	public Boolean getMostrarProfesorApellido() {
		return mostrarProfesorApellido;
	}

	public void setMostrarProfesorApellido(Boolean mostrarProfesorApellido) {
		this.mostrarProfesorApellido = mostrarProfesorApellido;
	}

	public SDireccionPrograma getSdireccionPrograma() {
		return sdireccionPrograma;
	}

	public void setSdireccionPrograma(SDireccionPrograma sdireccionPrograma) {
		this.sdireccionPrograma = sdireccionPrograma;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public DireccionPrograma getSelectedDireccionPrograma() {
		return selectedDireccionPrograma;
	}

	public void setSelectedDireccionPrograma(
			DireccionPrograma selectedDireccionPrograma) {
		this.selectedDireccionPrograma = selectedDireccionPrograma;
	}

	public List<DireccionPrograma> getListDireccionPrograma() {
		return listDireccionPrograma;
	}

	public void setListDireccionPrograma(
			List<DireccionPrograma> listDireccionPrograma) {
		this.listDireccionPrograma = listDireccionPrograma;
	}

	public ListModelList<DireccionPrograma> getModeloDireccionPrograma() {
		return modeloDireccionPrograma;
	}

	public void setModeloDireccionPrograma(
			ListModelList<DireccionPrograma> modeloDireccionPrograma) {
		this.modeloDireccionPrograma = modeloDireccionPrograma;
	}

	public Profesor getProfeNuevo() {
		return profeNuevo;
	}

	public void setProfeNuevo(Profesor profeNuevo) {
		this.profeNuevo = profeNuevo;
	}

	public Boolean getCambiarDirector() {
		return cambiarDirector;
	}

	public void setCambiarDirector(Boolean cambiarDirector) {
		this.cambiarDirector = cambiarDirector;
	}

	public Boolean getEditarDireccion() {
		return editarDireccion;
	}

	public void setEditarDireccion(Boolean editarDireccion) {
		this.editarDireccion = editarDireccion;
	}

	public Boolean getBtnGuardarDireccion() {
		return btnGuardarDireccion;
	}

	public void setBtnGuardarDireccion(Boolean btnGuardarDireccion) {
		this.btnGuardarDireccion = btnGuardarDireccion;
	}

	public Boolean getBtnEditarDireccion() {
		return btnEditarDireccion;
	}

	public void setBtnEditarDireccion(Boolean btnEditarDireccion) {
		this.btnEditarDireccion = btnEditarDireccion;
	}

	public Boolean getBtnGuardarDirector() {
		return btnGuardarDirector;
	}

	public void setBtnGuardarDirector(Boolean btnGuardarDirector) {
		this.btnGuardarDirector = btnGuardarDirector;
	}

	public Boolean getBtnCambiarDirector() {
		return btnCambiarDirector;
	}

	public void setBtnCambiarDirector(Boolean btnCambiarDirector) {
		this.btnCambiarDirector = btnCambiarDirector;
	}


	public Boolean getMarca() {
		return marca;
	}


	public void setMarca(Boolean marca) {
		this.marca = marca;
	}
	
	




	@Command
	public void seleccionFiltroDirector() {
		
		if (seleccionDirector == "Nombre") {
			txtFiltroDirector.setPlaceholder("Nombre");
			popOpcionesDirector.close();
		} else {
			if (seleccionDirector== "Cédula") {
				txtFiltroDirector.setPlaceholder("Cédula");
				popOpcionesDirector.close();
			} else {
				if (seleccionDirector == "Apellido") {
					txtFiltroDirector.setPlaceholder("Apellido");
					popOpcionesDirector.close();
				} else {
					if (seleccionDirector== "Programa") {
						txtFiltroDirector.setPlaceholder("Programa");
						popOpcionesDirector.close();
					}
				}
			}
		}

	}

	
	@Command
	@NotifyChange({ "modelodirectorhistorial" })
	public void filtrarDirector() {
		
		List<DirectorPrograma> aux =  new ArrayList<DirectorPrograma>();
		aux = CargarListaDirectores();
		modelodirectorhistorial.clear();
		//modelodirectorhistorial.addAll(aux);
		
		

		if (seleccionDirector == null || seleccionDirector == "") {
			if (textoDirector == "")
				modelodirectorhistorial.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getProfesor().getProfesorNombre()
							.toLowerCase()
							.contains(textoDirector.toLowerCase())) {
						
						modelodirectorhistorial.add(aux.get(i));	
						

					}
				}
			}

		} else {
			if (seleccionDirector == "Cédula") {
				if (textoDirector == "")
					modelodirectorhistorial.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesor().getProfesorCedula()
								.toLowerCase()
								.contains(textoDirector.toLowerCase())) {
							modelodirectorhistorial.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccionDirector== "Nombre") {
					if (textoDirector == "")
						modelodirectorhistorial.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProfesor().getProfesorNombre()
									.toLowerCase()
									.contains(textoDirector.toLowerCase())) {
								modelodirectorhistorial.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccionDirector == "Apellido") {
						if (textoDirector == "")
							modelodirectorhistorial.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProfesor().getProfesorApellido()
										.toLowerCase()
										.contains(textoDirector.toLowerCase())) {
									modelodirectorhistorial.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccionDirector== "Programa") {
							if (textoDirector == "")
								modelodirectorhistorial.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getDireccionPrograma()
											.getDireccionNombre()
											.toLowerCase()
											.contains(textoDirector.toLowerCase())) {
										modelodirectorhistorial.add(aux.get(i));

									}
								}
							}
						}

					}
				}
			}
		}
	}

	
	/**
	 * De acuerdo a la seleccion  carga la lista de Directores con 
	 * Directores en ejercicio o todos
	 * @return lista de representantesProfesorales
	 * */
	 public List<DirectorPrograma>CargarListaDirectores(){
		List<DirectorPrograma>listDirector=new ArrayList<DirectorPrograma>();
		List<DirectorPrograma> aux = sdirector.buscarOrdenado();
		if (marca == true) {
			for (int i = 0; i < aux.size(); i++) {
				if (aux.get(i).getDirectorProgramaFechaCulminacion()== null) {
				
					listDirector.add(aux.get(i));
				}
			}
		} else {
					listDirector.addAll(sdirector.buscarOrdenado());
		}

		return listDirector;
	}

}