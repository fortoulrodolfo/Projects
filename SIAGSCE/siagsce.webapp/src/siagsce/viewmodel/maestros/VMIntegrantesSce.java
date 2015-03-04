package siagsce.viewmodel.maestros;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Basic;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.CoordinacionSce;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.data.seguridad.MiembroGrupo;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.general.EnviarCorreo;
import siagsce.modelo.general.StatusCoordinadorSce;
import siagsce.modelo.general.StatusPreinscripcionProyecto;
import siagsce.modelo.general.StatusProfesor;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.SCoordinacionSce;
import siagsce.modelo.servicio.maestros.SCoordinadorSce;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
import siagsce.modelo.servicio.seguridad.ServicioUsuario;
import siagsce.viewmodel.seguridad.SecurityUtil;
/**
 * ViewModel para la vista que permite asignar y reemplazar
 * El coordinador y Representante Profesoral del
 * Servicio Comunitario Estudiantil
 * @author Iterator 
 **/
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMIntegrantesSce {

	/**
	 * Declaracion de servicios del ViewModel
	 * */
	@WireVariable
	SCoordinacionSce scoordinacionSce;
	@WireVariable
	SCoordinadorSce scoordinacorSce;
	@WireVariable
	ServicioUsuario su;
	@WireVariable
	ServicioGrupo sg;
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;
	@WireVariable
	SDireccionPrograma sdireccionPrograma;
	@WireVariable
	SProfesor sprofesor;

	

	/**
	 * Declaracion de listas y otras estructuras de datos
	 **/
	
	private List<String> valoresRepresentante;
	private List<String> valores;
	private List<DireccionPrograma> direcciones;
	private ListModelList<DireccionPrograma> modeloDireccion;
	private ListModelList<RepresentanteProfesoral> modeloRepresentante;
	private List<RepresentanteProfesoral> listRepresentantesExistentes;
	private ListModelList<CoordinadorSce> modelocoordinadorhistorial;
	private ListModelList<RepresentanteProfesoral> modeloRepresentanteHistorial;

	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private String cedulaCoordinador;
	private String nombreCoordinador ;
	private	String apellidoCoordinador; 
	private String telefonoCoordinador ;
	private String emailCoordinador; 
	private String direccionCoordinador ;
	private String cedulaRepresentante ;
	private String nombreRepresentante ;
	private String apellidoRepresentante ; 
	private String telefonoRepresentante;
	private String emailRepresentante ; 
	private String direccionRepresentante;
	private String texto;
	private String seleccion;
	private String textoRepresentante;
	private String seleccionRepresentante;
	private DireccionPrograma selectedDireccion;
	private boolean marcar = false;
	boolean existia;
	boolean vcoord=false;
	boolean vrepre=false;
	boolean vhcoord=false;
	boolean vhrepre=false;
	
	EnviarCorreo enviarCorreo=new EnviarCorreo();
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
 	@Wire
	 private Window win;
	 @Wire
	 private Textbox txtcedulaCoordinador;
	 @Wire
	 private Textbox txtNombreCoordinador;
	 @Wire
	 private Textbox txtApellidoCoordinador;
	 @Wire
	 private Textbox txtTelefonoCoordinador;
	 @Wire
	 private Textbox txtEmailCoordinador;

	 @Wire
	 private Textbox txtcedulaRepresentante;
	 @Wire
	 private Textbox txtNombreRepresentante;
	 @Wire
	 private Textbox txtApellidoRepresentante;
	 @Wire
	 private Textbox txtTelefonoRepresentante;
	 @Wire
	 private Textbox txtEmailRepresentante;
	 @Wire
	 private Textbox txtFiltroCoordinador;
	 @Wire
	 private Textbox txtFiltroRepresentante;

	@Wire
	private Combobox cmbProgramaRepresentante;
	@Wire
	private Button btnModificarRepresentante;
	@Wire
	private Button btnGuardarRepresentante;
	@Wire
	private Button btnModificarCoordinador;

	@Wire
	private Button btnGuardarCoordinador;
	@Wire
	private Popup popOpcionesCoordinador;
	@Wire
	private Popup popOpcionesRepresentante;

	
	
	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Window win) {
		this.win = win;
		direcciones = sdireccionPrograma.buscarTodo();
		modeloDireccion = new ListModelList<DireccionPrograma>(direcciones);
		modeloRepresentante = new ListModelList<RepresentanteProfesoral>();
		modelocoordinadorhistorial = new ListModelList<CoordinadorSce>();
		valores = new ArrayList<String>();
		valoresRepresentante = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		valoresRepresentante.add("Cédula");
		valoresRepresentante.add("Nombre");
		valoresRepresentante.add("Apellido");
		valoresRepresentante.add("Programa");
		//tbCoordinador.setVisible(false);
		buscarCoordinador();
		VisiblePorRol();
		//buscarRepresentantesExistentes();
				
		cargarHistorialCoordinador();
		cargarHistorialRepresentante();
		MensajeBox.doSetTemplate();

	}

	public void VisiblePorRol() {
		boolean valor=false;
		String cedula = SecurityUtil.nombreUsuario;
		Profesor profesor = sprofesor.buscarPorCedula(cedula);
		CoordinadorSce coordinador = scoordinacorSce.buscarPorProfesorYEstatus(
				profesor, StatusCoordinadorSce.Activo.toString());
		Usuario usuario= su.encontrarUsuarioPorNombreUsuario(profesor.getProfesorCedula());
		List<Grupo>grupos= usuario.getGrupos();
		int size=grupos.size();
		for(int i=0;i<size;i++){
			if(grupos.get(i).getNombre().equals("Administrador")||grupos.get(i).getNombre().equals("Administrador 2")){
				valor=true;
				break;
			}
		}
		if(valor==true){
			setVhrepre(true);
			setVrepre(true);
			setVcoord(true);
			setVhcoord(true);
		}else{
			
				if(coordinador!=null ){
					setVhrepre(true);
					setVrepre(true);
				}

		}
				
	}
	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		txtcedulaCoordinador.setReadonly(true);
		txtNombreCoordinador.setReadonly(true);
		txtApellidoCoordinador.setReadonly(true);
		txtTelefonoCoordinador.setReadonly(true);
		txtEmailCoordinador.setReadonly(true);
		cmbProgramaRepresentante.setReadonly(true);
		btnModificarRepresentante.setDisabled(true);
		cmbProgramaRepresentante.setPlaceholder("Seleccione");
		
		HabilitarBotonesCoordinador();

	}
	/**
	 * Inserta en un modelo lista todos los representantes profesorales 
	 * que han ejercido a lo largo de el tiempo
	 * */
	public void cargarHistorialRepresentante() {
		List<RepresentanteProfesoral> aux = smiembroCoordinacion
				.buscarOrdenado();
		modeloRepresentanteHistorial = new ListModelList<RepresentanteProfesoral>(
				aux);
	}
	/**
	 * Inserta en un modelo lista todos los Coordinadores
	 * que han ejercido a lo largo de el tiempo
	 * */
	public void cargarHistorialCoordinador() {
		List<CoordinadorSce> aux = scoordinacorSce.buscarOrdenado();
		modelocoordinadorhistorial.clear();
		modelocoordinadorhistorial.addAll(aux);
		setModelocoordinadorhistorial(modelocoordinadorhistorial);
	}

	/**
	 * Busca al coordinador que esta en ejercicio y muestra sus datos
	 * */
	@NotifyChange({ "cedulaCoordinador", "nombreCoordinador",
			"apellidoCoordinador", "telefonoCoordinador", "emailCoordinador",
			"direccionCoordinador" })
	@Command
	public void buscarCoordinador() {

		CoordinadorSce coordinador = scoordinacorSce
				.buscarEstatus(StatusCoordinadorSce.Activo.toString());

		System.out.print(coordinador);
		if (coordinador != null) {

			cedulaCoordinador = coordinador.getProfesorcoordinador()
					.getProfesorCedula();
			nombreCoordinador = coordinador.getProfesorcoordinador()
					.getProfesorNombre();
			apellidoCoordinador = coordinador.getProfesorcoordinador()
					.getProfesorApellido();
			telefonoCoordinador = coordinador.getProfesorcoordinador()
					.getProfesorTelefono();
			emailCoordinador = coordinador.getProfesorcoordinador()
					.getProfesorEmail();
			direccionCoordinador = coordinador.getProfesorcoordinador()
					.getProfesorDireccion();
			System.out.print(cedulaCoordinador);
		}
	}



	/** Metodo que abre la ventana del catalogo de profesores acreditados y
	 *	obtiene el profesor que se seleccione
	 **/
	@NotifyChange({ "cedulaCoordinador", "nombreCoordinador",
			"apellidoCoordinador", "telefonoCoordinador", "emailCoordinador",
			"direccionCoordinador" })
	@Command
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void MostrarCatalogoProfesoresAcreditadosCoordinacion(
			@ContextParam(ContextType.VIEW) Component view) {
		Map map = new HashMap<String, Object>();
		map.put("profesor", "RegistrarCoordinador");
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoProfesoresAcreditadosCoordinacion.zul",
						null, map);
		comp.doModal();
		Profesor prof = (Profesor) comp.getAttribute("profesor");
		if(prof!=null){
			cedulaCoordinador = prof.getProfesorCedula();
			nombreCoordinador = prof.getProfesorNombre();
			apellidoCoordinador = prof.getProfesorApellido();
			telefonoCoordinador = prof.getProfesorTelefono();
			emailCoordinador = prof.getProfesorEmail();
			direccionCoordinador = prof.getProfesorDireccion();
	
		}
		
	}
		/**
		 * habilita los campos de Coordinador para la edicion 
		 **/
	public void desbloquearCoordinador() {
		txtcedulaCoordinador.setReadonly(false);
		txtNombreCoordinador.setReadonly(false);
		txtApellidoCoordinador.setReadonly(false);
		txtTelefonoCoordinador.setReadonly(false);
		txtEmailCoordinador.setReadonly(false);
	}
	/**
	 * habilita los campos de Representante Profesoral para la edicion 
	 **/
	public void desbloquearRepresentante() {
		txtNombreRepresentante.setReadonly(false);
		txtApellidoRepresentante.setReadonly(false);
		txtTelefonoRepresentante.setReadonly(false);
		txtEmailRepresentante.setReadonly(false);
		cmbProgramaRepresentante.setDisabled(false);
		btnModificarRepresentante.setDisabled(false);
	}
	/**
	 * Si no existe Coordinador activo habilita el boton Guardar
	 * de lo contrario Habilita el boton Modificar
	 * */
		public void HabilitarBotonesCoordinador(){
			CoordinadorSce coordinador= scoordinacorSce.buscarEstatus(StatusCoordinadorSce.Activo.toString());
			if(coordinador==null){
				btnGuardarCoordinador.setDisabled(false);
				btnModificarCoordinador.setDisabled(true);
			}
			else{
				btnGuardarCoordinador.setDisabled(true);
				btnModificarCoordinador.setDisabled(false);
			}
		}
	/** Metodo que abre la ventana del catalogo de profesores acreditados y
	* obtiene el profesor que se seleccione
	**/
	@NotifyChange({ "cedulaRepresentante", "nombreRepresentante",
			"apellidoRepresentante", "telefonoRepresentante",
			"emailRepresentante", "direccionRepresentante" })
	@GlobalCommand
	@Command
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void MostrarCatalogoProfesoresAcreditadosRepresentante(
			@ContextParam(ContextType.VIEW) Component view) {
		Map map = new HashMap<String, Object>();
		map.put("profesor", "RegistrarRepresentante");
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoProfesoresAcreditadosCoordinacion.zul",
						null, map);
		comp.doModal();
		Profesor prof = (Profesor) comp.getAttribute("profesor");
		if(prof!=null){
			cedulaRepresentante = prof.getProfesorCedula();
			nombreRepresentante = prof.getProfesorNombre();
			apellidoRepresentante = prof.getProfesorApellido();
			telefonoRepresentante = prof.getProfesorTelefono();
			emailRepresentante = prof.getProfesorEmail();
			direccionRepresentante = prof.getProfesorDireccion();
	
		}
		
	}
	/**
	 * Si No hay un Representante Activo para ese programa ni el profesor
	 * seleccionado es ya un representante profesoral , lo asigna como 
	 *  representante profesoral del programa seleccionado , creandole su 
	 *  usuario y password los cuales seran notificados via E-mail al profesor
	 * 	@see SRepresentanteProfesoral
	 * */
	@Command
	@NotifyChange({ "cedulaCoordinador", "nombreCoordinador",
			"apellidoCoordinador", "telefonoCoordinador", "emailCoordinador",
			"direccionCoordinador", "cedulaRepresentante",
			"nombreRepresentante", "apellidoRepresentante",
			"telefonoRepresentante", "emailRepresentante",
			"direccionRepresentante", "modeloRepresentanteHistorial" })
	public void registrarRepresentante() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		Date fecha = new Date();
		if (selectedDireccion == null || cedulaRepresentante == ""
				|| nombreRepresentante == "" || apellidoRepresentante == ""
				|| telefonoRepresentante == "" || emailRepresentante == ""
				|| direccionRepresentante == "") {
			mensajeEmergente.advertenciaLlenarCampos();
		} else {
			RepresentanteProfesoral repre = smiembroCoordinacion
					.buscarEstatusPrograma(
							StatusRepresentanteProfesoral.Activo.toString(),
							selectedDireccion);
			Profesor prof = new Profesor(cedulaRepresentante,
					nombreRepresentante, apellidoRepresentante,
					telefonoRepresentante, emailCoordinador,
					direccionRepresentante,
					StatusProfesor.Acreditado.toString());
			RepresentanteProfesoral representante = new RepresentanteProfesoral();
			representante.setDireccionProgramam(selectedDireccion);
			representante.setProfesorm(prof);
			representante.setRepresentanteProfesoralFechaInicio(fecha);
			representante.setRepresentanteProfesoralFechaCulminacion(null);
			representante.setEstatus(StatusRepresentanteProfesoral.Activo
					.toString());
			RepresentanteProfesoral represen = smiembroCoordinacion
					.buscarRepresentantePorC(cedulaRepresentante);
			if (prof != null)
				if (representante != null)
					if (selectedDireccion != null)

						if (represen == null) {
							smiembroCoordinacion.guardar(representante);
						    Usuario usuario=su.encontrarUsuarioPorNombreUsuario(representante.getProfesorm().getProfesorCedula());	
							 existia=false;
								for(int j = 0; j < usuario.getGrupos().size(); j++) {
									if(usuario.getGrupos().get(j).getNombre().equals("Representante Profesoral")){
										existia=true;
										}
									}
								if(!existia){
									usuario.addGrupo(sg.buscarGrupoNombre("Representante Profesoral"));//el grupo 1003 representa el representante profesoral de una determinada direccion de programa
									su.guardarUsuario(usuario);
								}
								mensajeEmergente.informacionRegistroCorrecto();
							    enviarCorreo.sendEmail(representante.getProfesorm().getProfesorEmail(),"Representante del SCE de la Direccion"+representante.getDireccionProgramam().getDireccionNombre(),usuario.getNombreUsuario(),usuario.getClave());
							
							
							cargarHistorialRepresentante();

							cancelarRepresentante();
							//buscarRepresentantesExistentes();
						} else {
							mensajeEmergente.errorProfesorIntegranteSCE();
							cancelarRepresentante();
						}
		}
	}
	/**
	 * Si existe un Representante para dicho programa es sustituido 
	 * por el profesor seleccionado cambiandole al representante saliente
	 * el estatus a Inactivo ,agregandole como fecha de culminacion la actual
	 * y desligando el usuario de el rol de Representante Profesoral, mientras 
	 * que al nuevo vigente se le asigna a su usuario su nuevo rol 
	 * @see SRepresentanteProfesoral
	 * */
	@Command
	@NotifyChange({ "cedulaCoordinador", "nombreCoordinador",
			"apellidoCoordinador", "telefonoCoordinador", "emailCoordinador",
			"direccionCoordinador", "cedulaRepresentante",
			"nombreRepresentante", "apellidoRepresentante",
			"telefonoRepresentante", "emailRepresentante",
			"direccionRepresentante", "modeloRepresentanteHistorial" })
	public void modificarRepresentante() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		Date fechaCulminacion = new Date();
		Date fechaInicio = new Date();
		if (selectedDireccion == null || cedulaRepresentante == ""
				|| nombreRepresentante == "" || apellidoRepresentante == ""
				|| telefonoRepresentante == "" || emailRepresentante == ""
				|| direccionRepresentante == "") {
			mensajeEmergente.advertenciaLlenarCampos();
		} else {

			RepresentanteProfesoral repre = smiembroCoordinacion
					.buscarEstatusPrograma(
							StatusRepresentanteProfesoral.Activo.toString(),
							selectedDireccion);
					Profesor prof = sprofesor.buscarPorCedula(cedulaRepresentante);
			RepresentanteProfesoral representanteNuevo = new RepresentanteProfesoral();
			representanteNuevo.setDireccionProgramam(selectedDireccion);
			representanteNuevo.setProfesorm(prof);
			representanteNuevo
					.setRepresentanteProfesoralFechaInicio(fechaInicio);
			representanteNuevo.setRepresentanteProfesoralFechaCulminacion(null);
			representanteNuevo.setEstatus(StatusRepresentanteProfesoral.Activo
					.toString());
			RepresentanteProfesoral represen = smiembroCoordinacion
					.buscarRepresentantePorC(cedulaRepresentante);
			if (prof != null)
				if (repre != null)
					if (representanteNuevo != null)
						if (selectedDireccion != null)
							if (repre
									.getProfesorm()
									.getProfesorCedula()
									.equals(representanteNuevo.getProfesorm()
											.getProfesorCedula())) {
								mensajeEmergente.errorProfesorIntegranteSCE();
							} else {
								if (represen == null) {
									repre.setEstatus(StatusRepresentanteProfesoral.Inactivo
											.toString());
									repre.setRepresentanteProfesoralFechaCulminacion(fechaCulminacion);
									smiembroCoordinacion.guardar(repre);
									 Usuario usuario1=su.encontrarUsuarioPorNombreUsuario(repre.getProfesorm().getProfesorCedula());
							            List<Grupo> grupos=usuario1.getGrupos();
							            for(int i=0;i<grupos.size();i++){
							            	if(grupos.get(i).getNombre().equals("Representante Profesoral"))
									    usuario1.getGrupos().remove(i);//el grupo 1003 es el representante profesoral
									   }
									    su.guardarUsuario(usuario1);
										smiembroCoordinacion
											.guardar(representanteNuevo);
										 Usuario usuario=su.encontrarUsuarioPorNombreUsuario(representanteNuevo.getProfesorm().getProfesorCedula());
										 usuario.addGrupo(sg.buscarGrupoNombre("Representante Profesoral"));//el grupo 1003 representa el representante profesoral de una determinada direccion de programa
										 su.guardarUsuario(usuario);
										 mensajeEmergente.informacionRegistroCorrecto();
										 enviarCorreo.sendEmailInfo(repre.getProfesorm().getProfesorEmail(),"Representante profesoral de"+repre.getDireccionProgramam().getDireccionNombre());											
										 enviarCorreo.sendEmail(representanteNuevo.getProfesorm().getProfesorEmail(),"Representante del SCE de la Direccion"+representanteNuevo.getDireccionProgramam().getDireccionNombre(),usuario.getNombreUsuario(),usuario.getClave());					
										  
									cargarHistorialRepresentante();
									cancelarRepresentante();
									//buscarRepresentantesExistentes();
								} else {
									mensajeEmergente.errorProfesorIntegranteSCE();
									cancelarRepresentante();
								}
							}
		}
		

	}
	/**
	 *Si No hay un coordinador Activo , lo asigna como 
	 *nuevo Coordinador , creandole su  usuario y 
	 *password los cuales seran notificados via E-mail al profesor en caso
	 *de que  exista un Coordinador  es sustituido  por el profesor seleccionado
	 *cambiandole al coordinador saliente el estatus a Inactivo ,agregandole
	 *como fecha de culminacion la actual y desligando el usuario de el rol de
	 *Coordinador, mientras que al nuevo vigente se le asigna a 
	 *su usuario su nuevo rol 
	 * 	@see SCoordinadorSce
	 * */
	@Command
	@NotifyChange({ "cedulaCoordinador", "nombreCoordinador",
			"apellidoCoordinador", "telefonoCoordinador", "emailCoordinador",
			"direccionCoordinador", "cedulaRepresentante",
			"nombreRepresentante", "apellidoRepresentante",
			"telefonoRepresentante", "emailRepresentante",
			"direccionRepresentante", "modelocoordinadorhistorial" })
	public void registrarCoordinador() {
		CoordinacionSce coordinacion=scoordinacionSce.buscarCodigo(1001);
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		Date fechaInicio = new Date();
		Date fechaCulminacion = new Date();
		if (cedulaCoordinador == "" || nombreCoordinador == ""
				|| apellidoCoordinador == "" || telefonoCoordinador == ""
				|| emailCoordinador == "" || direccionCoordinador == "") {
			mensajeEmergente.advertenciaLlenarCampos();
		} else {
			CoordinadorSce coord = scoordinacorSce
					.buscarEstatus(StatusCoordinadorSce.Activo.toString());

			Profesor prof = sprofesor.buscarPorCedula(cedulaCoordinador);
			CoordinadorSce coordi = new CoordinadorSce(fechaInicio, null,
					StatusCoordinadorSce.Activo.toString(), prof,coordinacion);
			if (coord == null) {
				scoordinacorSce.guardar(coordi);
				 Usuario usuario=su.encontrarUsuarioPorNombreUsuario(coordi.getProfesorcoordinador().getProfesorCedula());
				 existia=false;
					for(int j = 0; j < usuario.getGrupos().size(); j++) {
						if(usuario.getGrupos().get(j).getNombre().equals("Coordinacion SCE")){
							existia=true;
							}
						}
					if(!existia){
						 usuario.addGrupo(sg.buscarGrupoNombre("Coordinacion SCE"));//el grupo 1002 representa la coorddinacion del servicio comunitario estudiantil
						 su.guardarUsuario(usuario);
					}
					 mensajeEmergente.informacionRegistroCorrecto();
				     enviarCorreo.sendEmail(coordi.getProfesorcoordinador().getProfesorEmail(),"Coordinador del SCE ",usuario.getNombreUsuario(),usuario.getClave());
				
				
				cargarHistorialCoordinador();
				cancelarCoordinador();
				HabilitarBotonesCoordinador();

			} else {
				if (coord
						.getProfesorcoordinador()
						.getProfesorCedula()
						.equals(coordi.getProfesorcoordinador()
								.getProfesorCedula()))
					mensajeEmergente.errorProfesorIntegranteSCE();
				else {
					coord.setCoordinadorStatus(StatusCoordinadorSce.Inactivo
							.toString());
					coord.setCoordinadorFechaCulminacion(fechaCulminacion);
					scoordinacorSce.guardar(coord);
				    Usuario usuario1=su.encontrarUsuarioPorNombreUsuario(coord.getProfesorcoordinador().getProfesorCedula());
					   
		            List<Grupo> grupos=usuario1.getGrupos();
		            for(int i=0;i<grupos.size();i++){
		             if(grupos.get(i).getNombre().equals("Coordinacion SCE"))
				    usuario1.getGrupos().remove(i);//el grupo 1002 representa la coorddinacion del servicio comunitario estudiantil
				   }
				    su.guardarUsuario(usuario1);
					scoordinacorSce.guardar(coordi);
			        Usuario usuario=su.encontrarUsuarioPorNombreUsuario(coordi.getProfesorcoordinador().getProfesorCedula());			  
					 existia=false;
						for(int j = 0; j < usuario.getGrupos().size(); j++) {
							if(usuario.getGrupos().get(j).getNombre().equals("Coordinacion SCE")){
								existia=true;
								}
							}
						if(!existia){
							    usuario.addGrupo(sg.buscarGrupoNombre("Coordinacion SCE"));//el grupo 1002 representa la coorddinacion del servicio comunitario estudiantil
								su.guardarUsuario(usuario);
						}
						mensajeEmergente.informacionRegistroCorrecto();
						enviarCorreo.sendEmailInfo(coord.getProfesorcoordinador().getProfesorEmail(),"Coordinador de"+ coord.getCoordinacionsce().getCoordinacionNombre());//cuando intento acceder al objeto no lo carga
					    enviarCorreo.sendEmail(coordi.getProfesorcoordinador().getProfesorEmail(),"Coordinador del SCE ",usuario.getNombreUsuario(),usuario.getClave());		
					cargarHistorialCoordinador();
					cancelarCoordinador();
					HabilitarBotonesCoordinador();
				}
			}
		}

	}



	/**
	 *Blanquea todos los campos relacionados con Coordinador 
	 **/
	@Command
	@NotifyChange({ "cedulaCoordinador", "nombreCoordinador",
			"apellidoCoordinador", "telefonoCoordinador", "emailCoordinador",
			"direccionCoordinador" })
	public void cancelarCoordinador() {
		cedulaCoordinador = "";
		nombreCoordinador = "";
		apellidoCoordinador = "";
		telefonoCoordinador = "";
		emailCoordinador = "";
		direccionCoordinador = "";

	}

	/**
	 * Al seleccionar un programa , busca si existe un 
	 * Representante Profesoral activo para el mismo
	 * de ser asi se mostrara la informacion en los campos 
	 * de textos relacionados a representante  y habilita
	 * los botones y campos para entrar en modo de edicion
	 * en caso contrario se habilita el boton guardar para
	 * ingresar un nuevo representante
	 * */
	@NotifyChange({ "cedulaRepresentante", "nombreRepresentante",
			"apellidoRepresentante", "telefonoRepresentante",
			"emailRepresentante", "direccionRepresentante" })
	@Command
	public void programaSeleccionado() {
		cedulaRepresentante = "";
		nombreRepresentante = "";
		apellidoRepresentante = "";
		telefonoRepresentante = "";
		emailRepresentante = "";
		direccionRepresentante = "";
		RepresentanteProfesoral representanteProf = smiembroCoordinacion
				.buscarRepresentante(selectedDireccion,
						StatusRepresentanteProfesoral.Activo.toString());
		if (representanteProf != null) {
			cedulaRepresentante = representanteProf.getProfesorm()
					.getProfesorCedula();
			nombreRepresentante = representanteProf.getProfesorm()
					.getProfesorNombre();
			apellidoRepresentante = representanteProf.getProfesorm()
					.getProfesorApellido();
			telefonoRepresentante = representanteProf.getProfesorm()
					.getProfesorTelefono();
			emailRepresentante = representanteProf.getProfesorm()
					.getProfesorEmail();
			direccionRepresentante = representanteProf.getProfesorm()
					.getProfesorDireccion();

			btnGuardarRepresentante.setDisabled(true);
			btnModificarRepresentante.setDisabled(false);
		}

		else {
			btnModificarRepresentante.setDisabled(true);
			btnGuardarRepresentante.setDisabled(false);
		}
	}

	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza el historial de representantes profesorales con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMIntegrantesSce}{@link #seleccionFiltroRepresentante()}
	 * */
	@Command
	@NotifyChange({ "modeloRepresentanteHistorial" })
	public void filtrarRepresentante() {
		List<RepresentanteProfesoral> aux = new ArrayList<RepresentanteProfesoral>();
		aux = CargarListaRepresetante();
		modeloRepresentanteHistorial.clear();

		if (seleccionRepresentante == null || seleccionRepresentante == "") {
			if (textoRepresentante == "")
				modeloRepresentanteHistorial.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getProfesorm().getProfesorNombre()
							.toLowerCase()
							.contains(textoRepresentante.toLowerCase())) {
						modeloRepresentanteHistorial.add(aux.get(i));

					}
				}
			}

		} else {
			if (seleccionRepresentante == "Cédula") {
				if (textoRepresentante == "")
					modeloRepresentanteHistorial.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesorm().getProfesorCedula()
								.toLowerCase()
								.contains(textoRepresentante.toLowerCase())) {
							modeloRepresentanteHistorial.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccionRepresentante== "Nombre") {
					if (textoRepresentante == "")
						modeloRepresentanteHistorial.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProfesorm().getProfesorNombre()
									.toLowerCase()
									.contains(textoRepresentante.toLowerCase())) {
								modeloRepresentanteHistorial.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccionRepresentante == "Apellido") {
						if (textoRepresentante == "")
							modeloRepresentanteHistorial.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i)
										.getProfesorm()
										.getProfesorApellido()
										.toLowerCase()
										.contains(
												textoRepresentante
														.toLowerCase())) {
									modeloRepresentanteHistorial
											.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccionRepresentante== "Programa") {
							if (textoRepresentante == "")
								modeloRepresentanteHistorial.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i)
											.getDireccionProgramam()
											.getDireccionNombre()
											.toLowerCase()
											.contains(
													textoRepresentante
															.toLowerCase())) {
										modeloRepresentanteHistorial.add(aux
												.get(i));

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
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza el historial de coordinadores  con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMIntegrantesSce}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "modelocoordinadorhistorial" })
	public void filtrarCoordinador() {
		List<CoordinadorSce> aux = new ArrayList<CoordinadorSce>();
		aux = scoordinacorSce.buscarTodo();
		modelocoordinadorhistorial.clear();

		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modelocoordinadorhistorial.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getProfesorcoordinador().getProfesorNombre()
							.toLowerCase().contains(texto.toLowerCase())) {
						modelocoordinadorhistorial.add(aux.get(i));

					}
				}
			}

		} else {
			if (seleccion == "Cédula") {
				if (texto == "")
					modelocoordinadorhistorial.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesorcoordinador()
								.getProfesorCedula().toLowerCase()
								.contains(texto.toLowerCase())) {
							modelocoordinadorhistorial.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Nombre") {
					if (texto == "")
						modelocoordinadorhistorial.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProfesorcoordinador()
									.getProfesorNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modelocoordinadorhistorial.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Apellido") {
						if (texto == "")
							modelocoordinadorhistorial.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProfesorcoordinador()
										.getProfesorApellido().toLowerCase()
										.contains(texto.toLowerCase())) {
									modelocoordinadorhistorial.add(aux.get(i));

								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Segun la seleccion de un criterio de filtrado
	 * coloca una marca de agua con el nombre de la seleccion
	 * */
	@Command
	public void seleccionFiltro() {
		System.out.print(seleccion);
		if (seleccion == "Nombre") {
			txtFiltroCoordinador.setPlaceholder("Nombre");
			popOpcionesCoordinador.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroCoordinador.setPlaceholder("Cedula");
				popOpcionesCoordinador.close();
			} else {
				if (seleccion== "Apellido") {
					txtFiltroCoordinador.setPlaceholder("Apellido");
					popOpcionesCoordinador.close();
				}
			}
		}

	}
	/**
	 * Segun la seleccion de un criterio de filtrado
	 * coloca una marca de agua con el nombre de la seleccion
	 * */
	@Command
	public void seleccionFiltroRepresentante() {
		System.out.print(seleccion);
		if (seleccionRepresentante == "Nombre") {
			txtFiltroRepresentante.setPlaceholder("Nombre");
			popOpcionesRepresentante.close();
		} else {
			if (seleccionRepresentante== "Cédula") {
				txtFiltroRepresentante.setPlaceholder("Cédula");
				popOpcionesRepresentante.close();
			} else {
				if (seleccionRepresentante == "Apellido") {
					txtFiltroRepresentante.setPlaceholder("Apellido");
					popOpcionesRepresentante.close();
				} else {
					if (seleccionRepresentante == "Programa") {
						txtFiltroRepresentante.setPlaceholder("Programa");
						popOpcionesRepresentante.close();
					}
				}
			}
		}

	}
	/**
	 *De acuerdo a la seleccion filtra la lista  con representantes
	 *profesorales que estan en ejercicio
	 **/
	@Command
	public void cargarVigente() {
		List<RepresentanteProfesoral> aux = smiembroCoordinacion.buscarOrdenado();
		setMarcar(!marcar);
		modeloRepresentanteHistorial.clear();
		txtFiltroRepresentante.setText("");
		if (marcar == true) {
			for (int i = 0; i < aux.size(); i++) {
				if (aux.get(i).getRepresentanteProfesoralFechaCulminacion() == null) {
				
					modeloRepresentanteHistorial.add(aux.get(i));
				}
			}
		} else {
					modeloRepresentanteHistorial.addAll(smiembroCoordinacion
					.buscarOrdenado());
		}

	}
	/**
	 * De acuerdo a la seleccion  carga la lista de representantes con 
	 * Representantes Profesorales en ejercicio o todos
	 * @return lista de representantesProfesorales
	 * */
	 public List<RepresentanteProfesoral>CargarListaRepresetante(){
		List<RepresentanteProfesoral>listRepresentante=new ArrayList<RepresentanteProfesoral>();
		List<RepresentanteProfesoral> aux = smiembroCoordinacion.buscarOrdenado();
		if (marcar == true) {
			for (int i = 0; i < aux.size(); i++) {
				if (aux.get(i).getRepresentanteProfesoralFechaCulminacion() == null) {
				
					listRepresentante.add(aux.get(i));
				}
			}
		} else {
					listRepresentante.addAll(smiembroCoordinacion
					.buscarOrdenado());
		}

		return listRepresentante;
	}
	
	/**
	 * Blanquea los campos relacionados con representante profesoral
	 * */
	@Command
	@NotifyChange({ "cedulaRepresentante", "nombreRepresentante",
			"apellidoRepresentante", "telefonoRepresentante",
			"emailRepresentante", "direccionRepresentante", "selectedDireccion" })
	public void cancelarRepresentante() {
		cedulaRepresentante = "";
		nombreRepresentante = "";
		apellidoRepresentante = "";
		telefonoRepresentante = "";
		emailRepresentante = "";
		direccionRepresentante = "";
		cmbProgramaRepresentante.setText("");
		cmbProgramaRepresentante.setPlaceholder("Seleccione");
		selectedDireccion=null;
		btnModificarRepresentante.setDisabled(true);

	}
	/**
	 * Cierra la ventana 
	 **/
		@Command
		public void salir() {
			win.detach();
		}
		/**
		 * Setter y Getter 
		 **/
		
		public boolean isMarcar() {
			return marcar;
		}

		public boolean isVcoord() {
			return vcoord;
		}
		public void setVcoord(boolean vcoord) {
			this.vcoord = vcoord;
		}
		public boolean isVrepre() {
			return vrepre;
		}
		public void setVrepre(boolean vrepre) {
			this.vrepre = vrepre;
		}
		public boolean isVhcoord() {
			return vhcoord;
		}
		public void setVhcoord(boolean vhcoord) {
			this.vhcoord = vhcoord;
		}
		public boolean isVhrepre() {
			return vhrepre;
		}
		public void setVhrepre(boolean vhrepre) {
			this.vhrepre = vhrepre;
		}
		public void setMarcar(boolean marcar) {
			this.marcar = marcar;
		}

		public ListModelList<CoordinadorSce> getModelocoordinadorhistorial() {
			return modelocoordinadorhistorial;
		}

		public String getTextoRepresentante() {
			return textoRepresentante;
		}

		public void setTextoRepresentante(String textoRepresentante) {
			this.textoRepresentante = textoRepresentante;
		}

		public String getSeleccionRepresentante() {
			return seleccionRepresentante;
		}

		public void setSeleccionRepresentante(String seleccionRepresentante) {
			this.seleccionRepresentante = seleccionRepresentante;
		}

		public List<String> getValoresRepresentante() {
			return valoresRepresentante;
		}

		public void setValoresRepresentante(List<String> valoresRepresentante) {
			this.valoresRepresentante = valoresRepresentante;
		}

		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}

		public String getSeleccion() {
			return seleccion;
		}

		public void setSeleccion(String seleccion) {
			this.seleccion = seleccion;
		}

		public List<String> getValores() {
			return valores;
		}

		public void setValores(List<String> valores) {
			this.valores = valores;
		}

		public ListModelList<RepresentanteProfesoral> getModeloRepresentanteHistorial() {
			return modeloRepresentanteHistorial;
		}

		public void setModeloRepresentanteHistorial(
				ListModelList<RepresentanteProfesoral> modeloRepresentanteHistorial) {
			this.modeloRepresentanteHistorial = modeloRepresentanteHistorial;
		}

		public void setModelocoordinadorhistorial(
				ListModelList<CoordinadorSce> modelocoordinadorhistorial) {
			this.modelocoordinadorhistorial = modelocoordinadorhistorial;
		}

		public String getCedulaCoordinador() {
			return cedulaCoordinador;
		}

		public ListModelList<RepresentanteProfesoral> getModeloRepresentante() {
			return modeloRepresentante;
		}

		public void setModeloRepresentante(
				ListModelList<RepresentanteProfesoral> modeloRepresentante) {
			this.modeloRepresentante = modeloRepresentante;
		}

		public void setCedulaCoordinador(String cedulaCoordinador) {
			this.cedulaCoordinador = cedulaCoordinador;
		}

		public String getNombreCoordinador() {
			return nombreCoordinador;
		}

		public void setNombreCoordinador(String nombreCoordinador) {
			this.nombreCoordinador = nombreCoordinador;
		}

		public String getApellidoCoordinador() {
			return apellidoCoordinador;
		}

		public void setApellidoCoordinador(String apellidoCoordinador) {
			this.apellidoCoordinador = apellidoCoordinador;
		}

		public String getTelefonoCoordinador() {
			return telefonoCoordinador;
		}

		public void setTelefonoCoordinador(String telefonoCoordinador) {
			this.telefonoCoordinador = telefonoCoordinador;
		}

		public String getEmailCoordinador() {
			return emailCoordinador;
		}

		public void setEmailCoordinador(String emailCoordinador) {
			this.emailCoordinador = emailCoordinador;
		}

		public String getDireccionCoordinador() {
			return direccionCoordinador;
		}

		public void setDireccionCoordinador(String direccionCoordinador) {
			this.direccionCoordinador = direccionCoordinador;
		}

		public String getCedulaRepresentante() {
			return cedulaRepresentante;
		}

		public void setCedulaRepresentante(String cedulaRepresentante) {
			this.cedulaRepresentante = cedulaRepresentante;
		}

		public String getNombreRepresentante() {
			return nombreRepresentante;
		}

		public void setNombreRepresentante(String nombreRepresentante) {
			this.nombreRepresentante = nombreRepresentante;
		}

		public String getApellidoRepresentante() {
			return apellidoRepresentante;
		}

		public void setApellidoRepresentante(String apellidoRepresentante) {
			this.apellidoRepresentante = apellidoRepresentante;
		}

		public String getTelefonoRepresentante() {
			return telefonoRepresentante;
		}

		public void setTelefonoRepresentante(String telefonoRepresentante) {
			this.telefonoRepresentante = telefonoRepresentante;
		}

		public String getEmailRepresentante() {
			return emailRepresentante;
		}

		public void setEmailRepresentante(String emailRepresentante) {
			this.emailRepresentante = emailRepresentante;
		}

		public String getDireccionRepresentante() {
			return direccionRepresentante;
		}

		public void setDireccionRepresentante(String direccionRepresentante) {
			this.direccionRepresentante = direccionRepresentante;
		}

		public List<DireccionPrograma> getDirecciones() {
			return direcciones;
		}

		public void setDirecciones(List<DireccionPrograma> direcciones) {
			this.direcciones = direcciones;
		}

		public ListModelList<DireccionPrograma> getModeloDireccion() {
			return modeloDireccion;
		}

		public void setModeloDireccion(
				ListModelList<DireccionPrograma> modeloDireccion) {
			this.modeloDireccion = modeloDireccion;
		}

		public DireccionPrograma getSelectedDireccion() {
			return selectedDireccion;
		}

		public void setSelectedDireccion(DireccionPrograma selectedDireccion) {
			this.selectedDireccion = selectedDireccion;
		}

}
