package siagsce.viewmodel.maestros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import siagsce.herramientas.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.general.EnviarCorreo;
import siagsce.modelo.general.StatusProfesor;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SCoordinadorSce;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
import siagsce.modelo.servicio.seguridad.ServicioUsuario;


/**
 * VMde profesor acreditado, es la transacion de acreditar a un profesor para que
 * este pueda ser usado en cualquier parte de la gestion del servicio comunitario estudiantil
 * 
 * @author ITERATOR
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarProfesorAcreditado {
	

	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private String profesorCedula;
	private String profesorNombre;
	private String profesorApellido;
	private String profesorEmail;
	private String profesorTelefono;
	private String profesorDireccion;
	private String profesorStatus;

	private String profesorCedulaDes;
	private String profesorNombreDes;
	private String profesorApellidoDes;
	private String profesorEmailDes;
	private String profesorTelefonoDes;
	private String profesorDireccionDes;
	private String profesorStatusDes;

	/**Declaracion de las variables de tipo servicio
	 **/
	@WireVariable
	private SProyecto sproyecto;
	
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	private SActividad sactividad;
	@WireVariable
	private SRepresentanteProfesoral smiembroCoordinacion;
	@WireVariable
	private SCoordinadorSce scoordinacorSce;
	@WireVariable
	ServicioUsuario su;

	@WireVariable
	ServicioGrupo sg;
	EnviarCorreo enviarCorreo=new EnviarCorreo();
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	Window win;
	@Wire
	private Textbox txtCedula;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Textbox txtApellido;
	@Wire
	private Textbox txtEmail;
	@Wire
	private Textbox txtTelefono;
	@Wire
	private Textbox txtDireccion;
	ListModelList<Profesor> modeloProfesorA;

	/**
	 * Metodo para cargar el modelo de profesor y darle vida a la ventana
	 * 
	 *@param win, componente de la ventana
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		modeloProfesorA = new ListModelList<Profesor>(new ArrayList<Profesor>());

	}

	/**
	 * Metodo para mostar un catalogo con los profesores acreditados
	 * 
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "profesorCedulaDes", "profesorNombreDes",
		"profesorApellidoDes", "profesorTelefonoDes",
		"profesorDireccionDes", "profesorEmailDes" })
	public void mostrarCatalogoProfesorAcreditado2() {
		String ventana = "AcreditarProfesor";
 		final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("profesor", ventana );
        
        Window comp = (Window)Executions.createComponents("/WEB-INF/vista/view/view.maestros/CatalogoProfesoresAcreditadosCoordinacion.zul", null, map);
        comp.doModal();
        Profesor profesor =(Profesor) comp.getAttribute("profesor");
		 if(profesor!=null)
			 obtenerProfesorAcreditado(profesor);
	}

	/**
	 * Metodo para mostar un catalogo con los  profesores aptos
	 * 
	 * 
	 */
	@Command
	@NotifyChange({ "profesorCedula", "profesorNombre", "profesorApellido",
		"profesorTelefono", "profesorDireccion", "profesorEmail" })
	public void mostrarCatalogoProfesorApto() {
		String ventana = "AcreditarProfesor";
 		final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("profesor", ventana );
        
		Window comp=(Window) Executions.createComponents(
				"/WEB-INF/vista/view/view.maestros/CatalogoProfesor.zul",
				null, map);
		comp.doModal();
		Profesor profesor= (Profesor) comp.getAttribute("profesor");
		if(profesor!=null)
			obtenerProfesorApto(profesor);
	}

	/**
	 * Metodo que se encarga de traer cada uno de los datos del profesor que se
	 * seleccione en el catalogo de profesores Aptos
	 * 
	 * @param profesor, el profesor al que se le traera los datos
	 */
	@NotifyChange({ "profesorCedula", "profesorNombre", "profesorApellido",
			"profesorTelefono", "profesorDireccion", "profesorEmail" })
	public void obtenerProfesorApto(Profesor profesor) {
		cancelar1();
		cancelar2();
		this.profesorCedula = profesor.getProfesorCedula();
		this.profesorNombre = profesor.getProfesorNombre();
		this.profesorApellido = profesor.getProfesorApellido();
		this.profesorTelefono = profesor.getProfesorTelefono();
		this.profesorDireccion = profesor.getProfesorDireccion();
		this.profesorEmail = profesor.getProfesorEmail();
	}

	/**
	 * Metodo que se encarga de traer cada uno de los datos del profesor que se
	 * seleccione en el catalogo de profesores acreditados
	 * 
	 *
	 * @param profesor, el profesor al que se le traera los datos
	 */
	@NotifyChange({ "profesorCedulaDes", "profesorNombreDes",
			"profesorApellidoDes", "profesorTelefonoDes",
			"profesorDireccionDes", "profesorEmailDes" })
	public void obtenerProfesorAcreditado(
			Profesor profesor) {

		this.profesorCedulaDes = profesor.getProfesorCedula();
		this.profesorNombreDes = profesor.getProfesorNombre();
		this.profesorApellidoDes = profesor.getProfesorApellido();
		this.profesorTelefonoDes = profesor.getProfesorTelefono();
		this.profesorDireccionDes = profesor.getProfesorDireccion();
		this.profesorEmailDes = profesor.getProfesorEmail();
		
	}

	/**
	 * Metodo que se encarga de guardar el registro en la base de datos del
	 * profesor a acreditar es decir acredita al profesor
	 * 
	 * 
	 */
	@Command
	@NotifyChange({ "modeloProfesorA", "profesorCedula", "profesorNombre",
			"profesorApellido", "profesorEmail", "profesorTelefono",
			"profesorDireccion" })
	public void guardarProfesorApto() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if (profesorCedula == null || profesorApellido == null
				|| profesorDireccion == null || profesorEmail == null
				|| profesorNombre == null || profesorTelefono == null)
			mensajeEmergente.advertenciaLlenarCampos();
		else {

		
			Profesor profesor = sprofesor.buscarPorCedula(profesorCedula);
			profesor.setProfesorStatus(StatusProfesor.Acreditado.toString());
		    sprofesor.guardar(profesor);
			Usuario usuario=su.encontrarUsuarioPorNombreUsuario(profesor.getProfesorCedula());
			mensajeEmergente.informacionRegistroCorrecto();
			enviarCorreo.sendEmail(profesor.getProfesorEmail(),"Profesor Acreditado ",usuario.getNombreUsuario(),usuario.getClave());		
			cancelar1();
		}

		
	}

	/**
	 * Metodo que se encarga de guardar el registro en la base de datos del
	 * profesor que se le revierte la acreditacion 
	 * 
	 * 
	 */
	@Command
	@NotifyChange({ "modeloProfesorA", "profesorCedulaDes", "profesorNombreDes",
			"profesorApellidoDes", "profesorEmailDes", "profesorTelefonoDes",
			"profesorDireccionDes" })
	public void guardarProfesorDesacreditado() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
				if (profesorCedulaDes == null || profesorApellidoDes == null
						|| profesorDireccionDes == null || profesorEmailDes == null
						|| profesorNombreDes == null || profesorTelefonoDes == null)
					mensajeEmergente.advertenciaLlenarCampos();
				else {
					if(!(buscarcargoProfesorResponsable(profesorCedulaDes)
							||buscarcargoCoordinador(profesorCedulaDes)
							||buscarcargoProfesorParticipante(profesorCedulaDes)
							||buscarcargoRepresentanteProfesoral(profesorCedulaDes))){ 
					
						Profesor profesorDesacreditado = sprofesor.buscarPorCedula(profesorCedulaDes);
						profesorDesacreditado.setProfesorStatus(StatusProfesor.Apto.toString());
					sprofesor.guardar(profesorDesacreditado);
					mensajeEmergente.informacionRevertirCorrecto();
					Usuario usuario=su.encontrarUsuarioPorNombreUsuario(profesorDesacreditado.getProfesorCedula());
				    List<Grupo> grupos=usuario.getGrupos();
		            for(int i=0;i<grupos.size();i++){			 
				    if(grupos.get(i).getIdGrupo().compareTo(1000)!=0){
				    	 usuario.getGrupos().remove(i);//remueve todos los grupos menos el 1000, ya representa el grupo base.
						}
		            }
				 	su.guardarUsuario(usuario);
					enviarCorreo.sendEmailInfoDesacreditacion(profesorDesacreditado.getProfesorEmail(),"Profesor Acreditado ");
				     cancelar2();
					}else{
				    	 mensajeEmergente.errorImposibleEliminar();
				     
					}
				}
		}
	
		
	

	/**
	 * Metodo para buscar al profesor seleccionado dentro de la tabla profesor responsable en el
	 * sistema en tal caso de que existan con un cargo actual el profesor no se le puede 
	 * revertir la acreditacion
	 * 
	 * @param cedula del profesor a buscar
	 * @return true si lo consigue, false si no lo consigue
	 */
	public boolean buscarcargoProfesorResponsable(String cedula){
		Boolean salir=false;
		for (Proyecto pro:sproyecto.buscarTodo()){
			for (Profesor profesor:pro.getListaprofesoresresponsables()){
				if (profesor.getProfesorCedula().equals(cedula)) 
					salir=true;
			}
		}
		return salir;
	}
	
	/**
	 * Metodo para buscar al profesor seleccionado dentro de la tabla coodinador en el
	 * sistema en tal caso de que existan con un cargo actual el profesor no se le puede 
	 * revertir la acreditacion
	 * 
	 * @param cedula del profesor a buscar
	 * @return true si lo consigue, false si no lo consigue
	 */
	public boolean buscarcargoCoordinador(String cedula){
		Boolean salir=false;
		for (CoordinadorSce coord:scoordinacorSce.buscarTodo()){
				if (coord.getProfesorcoordinador().getProfesorCedula().equals(cedula)) 
					salir=true;
		}
		return salir;
		}
	
	/**
	 * Metodo para buscar al profesor seleccionado dentro de la tabla profesor participante en el
	 * sistema en tal caso de que existan con un cargo actual el profesor no se le puede 
	 * revertir la acreditacion
	 * 
	 * @param cedula del profesor a buscar
	 * @return true si lo consigue, false si no lo consigue
	 */
	public boolean buscarcargoProfesorParticipante(String cedula){
		Boolean salir=false;
		for (Actividad actividad:sactividad.buscarTodo()){
			for (Profesor profesorparticipante:actividad.getListaprofesoresparticipantes()){
				if (profesorparticipante.getProfesorCedula().equals(cedula)) 
					salir=true;
			}
		}
		return salir;
	}
	/**
	 * Metodo para buscar al profesor seleccionado dentro de la tabla representante profesoral en el
	 * sistema en tal caso de que existan con un cargo actual el profesor no se le puede 
	 * revertir la acreditacion
	 * 
	 * @param cedula del profesor a buscar
	 * @return true si lo consigue, false si no lo consigue
	 */
	public boolean buscarcargoRepresentanteProfesoral(String cedula){
		Boolean salir=false;
		for (RepresentanteProfesoral representante:smiembroCoordinacion.buscarTodo()){
				if (representante.getProfesorm().getProfesorCedula().equals(cedula)){ 
					salir=true;
			}
		}
		return salir;
	}

	/**
	 * Metodo para realizar limpiar la pantalla de datos, es decir se le asigna
	 * null a los  textbox.
	 */
	@Command
	@NotifyChange({ "modeloProfesorA", "profesorCedula", "profesorNombre",
			"profesorApellido", "profesorEmail", "profesorTelefono",
			"profesorDireccion" })
	public void cancelar1() {
		modeloProfesorA.clear();
		this.profesorCedula = null;
		this.profesorNombre = null;
		this.profesorApellido = null;
		this.profesorEmail = null;
		this.profesorTelefono = null;
		this.profesorDireccion = null;
		
	}
	
	/**
	 * Metodo para realizar limpiar la pantalla de datos, es decir se le asigna
	 * null a los  textbox.
	 */
	@Command
	@NotifyChange({ "modeloProfesorA", "profesorCedulaDes", "profesorNombreDes",
			"profesorApellidoDes", "profesorEmailDes", "profesorTelefonoDes",
			"profesorDireccionDes" })
	public void cancelar2() {
		modeloProfesorA.clear();
		this.profesorCedulaDes = null;
		this.profesorNombreDes = null;
		this.profesorApellidoDes = null;
		this.profesorEmailDes = null;
		this.profesorTelefonoDes = null;
		this.profesorDireccionDes = null;
		
	}

	
	
	/**
	 * Metodo para cerrar la ventana
	 */

	@Command
	public void cerrarVentana() {
		win.setVisible(false);
	}

	/**
	 * Getter and setters
	 */
	public ListModelList<Profesor> getModeloProfesorA() {
		return modeloProfesorA;
	}

	public void setModeloProfesorA(ListModelList<Profesor> modeloProfesorA) {
		this.modeloProfesorA = modeloProfesorA;
	}

	public SProfesor getSprofesor() {
		return sprofesor;
	}

	public void setSprofesor(SProfesor sprofesor) {
		this.sprofesor = sprofesor;
	}

	public String getProfesorCedula() {
		return profesorCedula;
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

	public String getProfesorEmail() {
		return profesorEmail;
	}

	public void setProfesorEmail(String profesorEmail) {
		this.profesorEmail = profesorEmail;
	}

	public String getProfesorTelefono() {
		return profesorTelefono;
	}

	public void setProfesorTelefono(String profesorTelefono) {
		this.profesorTelefono = profesorTelefono;
	}

	public String getProfesorDireccion() {
		return profesorDireccion;
	}

	public void setProfesorDireccion(String profesorDireccion) {
		this.profesorDireccion = profesorDireccion;
	}

	public String getProfesorStatus() {
		return profesorStatus;
	}

	public void setProfesorStatus(String profesorStatus) {
		this.profesorStatus = profesorStatus;
	}

	public String getProfesorCedulaDes() {
		return profesorCedulaDes;
	}

	public void setProfesorCedulaDes(String profesorCedulaDes) {
		this.profesorCedulaDes = profesorCedulaDes;
	}

	public String getProfesorNombreDes() {
		return profesorNombreDes;
	}

	public void setProfesorNombreDes(String profesorNombreDes) {
		this.profesorNombreDes = profesorNombreDes;
	}

	public String getProfesorApellidoDes() {
		return profesorApellidoDes;
	}

	public void setProfesorApellidoDes(String profesorApellidoDes) {
		this.profesorApellidoDes = profesorApellidoDes;
	}

	public String getProfesorEmailDes() {
		return profesorEmailDes;
	}

	public void setProfesorEmailDes(String profesorEmailDes) {
		this.profesorEmailDes = profesorEmailDes;
	}

	public String getProfesorTelefonoDes() {
		return profesorTelefonoDes;
	}

	public void setProfesorTelefonoDes(String profesorTelefonoDes) {
		this.profesorTelefonoDes = profesorTelefonoDes;
	}

	public String getProfesorDireccionDes() {
		return profesorDireccionDes;
	}

	public void setProfesorDireccionDes(String profesorDireccionDes) {
		this.profesorDireccionDes = profesorDireccionDes;
	}

	public String getProfesorStatusDes() {
		return profesorStatusDes;
	}

	public void setProfesorStatusDes(String profesorStatusDes) {
		this.profesorStatusDes = profesorStatusDes;
	}

}
