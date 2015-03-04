package siagsce.viewmodel.maestros;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Parametro;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.servicio.maestros.SParametro;
import siagsce.modelo.servicio.maestros.STaller;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMPlanificacionDelTaller {

	private Window win;

	@WireVariable
	STaller sTaller;
	
	@WireVariable
	SParametro sparametro;

	//Declaracion de componentes que seran manipulados en la vista.
	@Wire 
	private Button btnEditar;
	@Wire 
	private Button btnGuardar;
	@Wire
	private Button btnEliminar;
	@Wire 
	private Textbox txtDescripcion;
	@Wire 
	private Textbox txtCantidadHoras;
	@Wire
	private Datebox dtbInicioInscripcion;
	@Wire
	private Datebox dtbFinInscripcion;
	@Wire
	private Datebox dtbInicioTaller;
	@Wire
	private Datebox dtbFinTaller;
	@Wire
	private Combobox cmbModalidad;
	@Wire 
	private Textbox txtLugar;
	@Wire 
	private Textbox txtCedulaProfesor;
	@Wire 
	private Textbox txtNombreProfesor;
	@Wire 
	private Textbox txtApellidoProfesor;
	@Wire 
	private Textbox txtEmailProfesor;
	@Wire 
	private Textbox txtTelefonoProfesor;
	@Wire
	private Button btnBuscarProfesor;
	
	//Declaracion de variables de taller
	Date inicioInscripcion, finInscripcion, inicioTaller, finTaller;
	String nombre, descripcion, modalidad, lugar;
	Integer cantidadHoras;
	Taller selectedTaller;
	Profesor profesorResponsable;
	
	//Declaracion de variables del profesor responsable
	String cedulaProfesor, nombreProfesor, apellidoProfesor, emailProfesor, telefonoProfesor;

	//Set y Get
	public Date getInicioInscripcion() {
		return inicioInscripcion;
	}
	public void setInicioInscripcion(Date inicioInscripcion) {
		this.inicioInscripcion = inicioInscripcion;
	}
	public Date getFinInscripcion() {
		return finInscripcion;
	}
	public void setFinInscripcion(Date finInscripcion) {
		this.finInscripcion = finInscripcion;
	}
	public Date getInicioTaller() {
		return inicioTaller;
	}
	public void setInicioTaller(Date inicioTaller) {
		this.inicioTaller = inicioTaller;
	}
	public Date getFinTaller() {
		return finTaller;
	}
	public void setFinTaller(Date finTaller) {
		this.finTaller = finTaller;
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
	public Integer getCantidadHoras() {
		return cantidadHoras;
	}
	public void setCantidadHoras(Integer cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}
	public Taller getSelectedTaller() {
		return selectedTaller;
	}
	public void setSelectedTaller(Taller selectedTaller) {
		this.selectedTaller = selectedTaller;
	}
	public String getCedulaProfesor() {
		return cedulaProfesor;
	}
	public void setCedulaProfesor(String cedulaProfesor) {
		this.cedulaProfesor = cedulaProfesor;
	}
	public String getNombreProfesor() {
		return nombreProfesor;
	}
	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}
	public String getApellidoProfesor() {
		return apellidoProfesor;
	}
	public void setApellidoProfesor(String apellidoProfesor) {
		this.apellidoProfesor = apellidoProfesor;
	}
	public String getEmailProfesor() {
		return emailProfesor;
	}
	public void setEmailProfesor(String emailProfesor) {
		this.emailProfesor = emailProfesor;
	}
	public String getTelefonoProfesor() {
		return telefonoProfesor;
	}
	public void setTelefonoProfesor(String telefonoProfesor) {
		this.telefonoProfesor = telefonoProfesor;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Profesor getProfesorResponsable() {
		return profesorResponsable;
	}
	public void setProfesorResponsable(Profesor profesorResponsable) {
		this.profesorResponsable = profesorResponsable;
	}

	// Metodo de inicializar
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		selectedTaller = null;
		Parametro parametro = sparametro.buscarPorNombre("HorasTaller");
		cantidadHoras = parametro.getParametroInterger();
	}

	//metodo que permite acceder a los componentes de la vista e inicializar los necesarios
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		dtbInicioTaller.setDisabled(true);
		dtbFinInscripcion.setDisabled(true);
		dtbFinTaller.setDisabled(true);
		btnEliminar.setDisabled(true);
		validarFechaInicioInscripcion();
	}

		//Metodo que muestra el catalogo de taller.
	 	@Command
	 	@SuppressWarnings({ "unchecked", "rawtypes" })
	 	 @NotifyChange({"nombre", "descripcion", "cantidadHoras", "inicioTaller",
				"finTaller", "inicioInscripcion", "finInscripcion","lugar", "modalidad",
				"cedulaProfesor", "nombreProfesor","apellidoProfesor", "telefonoProfesor", "emailProfesor", "selectedTaller"})
	    public void MostrarCatalogoTaller()
	    {
	     	Map map= new HashMap<String,Object>();
	     	//Le dice al catalogo de taller que la pantalla que lo solicita es PlanificarTaller
			map.put("taller", "PlanificarTaller");
	 		Window comp = (Window) Executions
					.createComponents(
							"/WEB-INF/vista/view/view.maestros/CatalogoTaller.zul",
							null, map);
	 		comp.doModal();
	 		Taller taller=  (Taller)comp.getAttribute("taller");
	 		ObtenerTallerPlanificacionTaller(taller);
	    }
	 
	 	//Metodo que obtiene el taller seleccionado en el catalogo de taller.
	 	@Command
	    @NotifyChange({"nombre", "descripcion", "cantidadHoras", "inicioTaller",
			"finTaller", "inicioInscripcion", "finInscripcion","lugar", "modalidad",
			"cedulaProfesor", "nombreProfesor","apellidoProfesor", "telefonoProfesor", "emailProfesor", "selectedTaller"})
	    public void ObtenerTallerPlanificacionTaller( Taller taller)
	    {
	 		if (taller != null) {
				btnEditar.setDisabled(false);
				btnEliminar.setDisabled(false);
				btnGuardar.setDisabled(true);
				btnBuscarProfesor.setDisabled(true);
				deshabilitarCampos();
				descripcion = taller.getTallerDescripcion();
				cantidadHoras = taller.getTallerCantidadHoras();
				inicioTaller = taller.getTallerFechaInicio();
				finTaller = taller.getTallerFechaCulminacion();
				inicioInscripcion = taller.getTallerInscripcionFechaInicio();
				finInscripcion = taller.getTallerInscripcionFechaFinal();
				lugar = taller.getTallerLugar();
				modalidad = taller.getTallerModalidad();
				cedulaProfesor = taller.getTallerProfesorResponsable().getProfesorCedula();
				nombreProfesor = taller.getTallerProfesorResponsable().getProfesorNombre();
				apellidoProfesor = taller.getTallerProfesorResponsable().getProfesorApellido();
				telefonoProfesor = taller.getTallerProfesorResponsable().getProfesorTelefono();
				emailProfesor = taller.getTallerProfesorResponsable().getProfesorEmail();
				profesorResponsable = taller.getTallerProfesorResponsable();
				selectedTaller = taller;
			}
	    }
	 	
	 	//Metodo que abre el catalogo de profesor acreditados coordinacion
	 	@Command
	 	@NotifyChange({"nombre", "descripcion", "cantidadHoras", "inicioTaller",
			"finTaller", "inicioInscripcion", "finInscripcion","lugar", "modalidad",
			"cedulaProfesor", "nombreProfesor","apellidoProfesor", "telefonoProfesor", "emailProfesor", "selectedTaller"})
	    public void MostrarCatalogoProfesorAcreditado()
	    {
	 		String ventana = "PlanificacionTaller";
	 		final HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("profesor", ventana );
	        Window comp = (Window)Executions.createComponents("/WEB-INF/vista/view/view.maestros/CatalogoProfesoresAcreditadosCoordinacion.zul", null, map);
	        comp.doModal();
	        Profesor profesor =(Profesor) comp.getAttribute("profesor");
	        if (profesor != null){
	        	 ObtenerProfesoresAcreditadosPlanificarTaller(profesor);
	        }
	       
	    }
	 	
	 	//Metodo que obtiene el profesor acreditado del catalogo.
	    @NotifyChange({"nombre", "descripcion", "cantidadHoras", "inicioTaller",
			"finTaller", "inicioInscripcion", "finInscripcion","lugar", "modalidad",
			"cedulaProfesor", "nombreProfesor","apellidoProfesor", "telefonoProfesor", "emailProfesor", "selectedTaller"})
	    public void ObtenerProfesoresAcreditadosPlanificarTaller( Profesor profesor)
	    {
			cedulaProfesor = profesor.getProfesorCedula();
			nombreProfesor = profesor.getProfesorNombre();
			apellidoProfesor = profesor.getProfesorApellido();
			telefonoProfesor = profesor.getProfesorTelefono();
			emailProfesor = profesor.getProfesorEmail();
			profesorResponsable = profesor;
	    }
	 	
	// Metodo que guarda el taller en base de datos
	@Command
	@NotifyChange({ "nombre", "descripcion", "cantidadHoras","inicioInscripcion","finInscripcion", "inicioTaller","finTaller","lugar","modalidad",
		 "cedulaProfesor", "nombreProfesor", "apellidoProfesor", "telefonoProfesor", "emailProfesor" })
	public void guardar() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		if (validarCamposVacios()){
			
			//Si trajo un taller del catalogo hace Update
			if (selectedTaller != null) {
				selectedTaller.setTallerDescripcion(descripcion);
				selectedTaller.setTallerCantidadHoras(cantidadHoras);
				selectedTaller.setTallerInscripcionFechaInicio(inicioInscripcion);
				selectedTaller.setTallerInscripcionFechaFinal(finInscripcion);
				selectedTaller.setTallerFechaInicio(inicioTaller);
				selectedTaller.setTallerFechaCulminacion(finTaller);
				selectedTaller.setTallerLugar(lugar);
				selectedTaller.setTallerModalidad(modalidad);
				selectedTaller.setTallerProfesorResponsable(profesorResponsable);
				sTaller.guardar(selectedTaller);
				selectedTaller = null;
				limpiar();
				mensajeEmergente.informacionActualizarDatos();
			} else {
				//Si es un taller nuevo hace Insert
				sTaller.guardar(new Taller("Realidad Comunitaria",inicioTaller,finTaller,descripcion,inicioInscripcion,finInscripcion,lugar,modalidad, profesorResponsable,"Activo",cantidadHoras));
				limpiar();
				mensajeEmergente.informacionRegistroCorrecto();
			}
		}
		else mensajeEmergente.advertenciaLlenarCampos();		
	}
	
	//metodo que limpia los campos de la ventana
	@Command
	@NotifyChange({ "nombre", "descripcion", "cantidadHoras", "inicioTaller","lugar","modalidad",
			"finTaller", "inicioInscripcion", "finInscripcion", "cedulaProfesor", "nombreProfesor", "apellidoProfesor", "telefonoProfesor", "emailProfesor" })
	public void limpiar() {
		selectedTaller = null;
		nombre = "";
		descripcion = "";
		cantidadHoras = 16;
		inicioTaller = null;
		finTaller = null;
		inicioInscripcion = null;
		finInscripcion = null;
		cedulaProfesor = null;
		nombreProfesor = null;
		apellidoProfesor = null;
		telefonoProfesor = null;
		emailProfesor = null;
		modalidad = null;
		lugar = null;
		cmbModalidad.setValue(null);
		habilitarCampos();
		btnGuardar.setDisabled(false);
		btnEditar.setDisabled(true);
		btnEliminar.setDisabled(true);
		dtbFinInscripcion.setDisabled(true);
		dtbFinTaller.setDisabled(true);
		dtbInicioTaller.setDisabled(true);
		btnBuscarProfesor.setDisabled(false);
		dtbInicioInscripcion.setDisabled(false);
		txtLugar.setReadonly(false);
	}

	//metodo que cierra la ventana
	@Command
	public void cerrarVentana() {
		win.detach();
	}

	//metodo que desabilita los campos
	@Command
	public void deshabilitarCampos() {
		txtDescripcion.setReadonly(true);
		cmbModalidad.setDisabled(true);
		dtbInicioTaller.setDisabled(true);
		dtbInicioInscripcion.setDisabled(true);
		txtLugar.setReadonly(true);
	}
	
	//metodo que habilita los campos
	@Command
	public void habilitarCampos(){
		txtDescripcion.setReadonly(false);
		cmbModalidad.setDisabled(false);
		txtLugar.setReadonly(false);
		dtbInicioInscripcion.setDisabled(false);
	}
	
	//metodo que valida que la fecha de culminacion de inscripcion de taller sea menor que la fecha de inicio de inscripcion de taller 
	@NotifyChange({ "finInscripcion", "inicioTaller", "finTaller"})
	@Command
	public void validarFechaInscripcion() {
		dtbFinInscripcion.setDisabled(false);
		finInscripcion = null;
		inicioTaller = null;
		finTaller = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		dtbFinInscripcion.setConstraint("after "+format.format(dtbInicioInscripcion.getValue()));
	}
	
	//metodo que valida que la fecha de inicio del taller sea menor que la fecha de culminacion de taller
	@NotifyChange({ "finTaller"})
	@Command
	public void validarFechaEjecucion(){
		dtbFinTaller.setDisabled(false);
		finTaller = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		dtbFinTaller.setConstraint("after "+format.format(dtbInicioTaller.getValue()));
	}
	
	@NotifyChange({ "inicioTaller"})
	@Command
	public void validarFechaInicioTaller(){
		dtbInicioTaller.setDisabled(false);
		inicioTaller = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		dtbInicioTaller.setConstraint("after "+format.format(dtbFinInscripcion.getValue()));
	}
	
	@NotifyChange({ "inicioInscripcion"})
	@Command
	public void validarFechaInicioInscripcion(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date fechaActual = new Date();
		dtbInicioInscripcion.setConstraint("after "+format.format(fechaActual));
	}
	
	//Metodo que se ejecuta cuando presiona el boton edicion
	@Command
	public void edicion(){
		habilitarCampos();
		btnEditar.setDisabled(true);
		btnBuscarProfesor.setDisabled(false);
		btnGuardar.setDisabled(false);
		btnEliminar.setDisabled(true);
	}
	
	//Metodo que elimina el taller seleccionado
	@NotifyChange({ "nombre", "descripcion", "cantidadHoras", "inicioTaller","lugar","modalidad",
		"finTaller", "inicioInscripcion", "finInscripcion", "cedulaProfesor", "nombreProfesor", "apellidoProfesor", "telefonoProfesor", "emailProfesor" })
	@Command
	public void eliminar(){
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		if (selectedTaller.getInscripcionTaller().isEmpty()){
			sTaller.eliminar(selectedTaller);
			mensajeEmergente.informacionEliminarCorrecto();
			limpiar();
		}
	}
	
	//Metodo que valida que no existan campos vacios
	@Command
	public boolean validarCamposVacios(){
		if (descripcion == null || cantidadHoras == null || inicioInscripcion == null || finInscripcion == null || inicioTaller == null || finTaller == null || modalidad == null || lugar == null || cedulaProfesor == null ){
			return false;
		}
		else if (descripcion.isEmpty() || cantidadHoras == null || inicioInscripcion == null || finInscripcion == null || inicioTaller == null || finTaller == null || modalidad.isEmpty() || lugar.isEmpty() || cedulaProfesor.isEmpty()){
		return false;	
		}
			else return true;
		}
	}


	