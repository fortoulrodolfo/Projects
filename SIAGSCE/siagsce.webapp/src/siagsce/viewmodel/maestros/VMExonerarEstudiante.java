/**
 * ViewModel para la vista de Exonerar y 
 * Revertir exoneracion del estudiante del 
 * Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */


package siagsce.viewmodel.maestros;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;

import siagsce.modelo.data.maestros.Causa;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Exonerado;
import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.servicio.maestros.SCausa;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SExonerado;
import siagsce.modelo.servicio.maestros.SMotivo;
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMExonerarEstudiante {


	/** 
	 * Declaracion de servicios del ViewModel
	 * */
	
	@WireVariable
	SEstudiante sestudiante;
	@WireVariable
	SExonerado sexonerado;
	@WireVariable
	SMotivo smotivo;
	@WireVariable
	SCausa scausa;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	Window win;
	@Wire 
	private Combobox cmbMotivoExonerar;
	private Combobox cmbMotivoRevertirEx;
	
	/** 
	 * Declaracion de Variables del ViewModel
	 **/
	private String cedula;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private String programa;
	private String cedulaex;
	private String nombreex;
	private String apellidoex;
	private String direccionex;
	private String telefonoex;
	private String programaex;
	private String motivoex;
	private Estudiante selectedEstudiante;
	private Exonerado selectedExonerado;
	private Motivo selectedMotivo;
	private Motivo selectedMotivoEx;
	private Date exoneradoFecha;
	private Integer codigoex;
	
	
	/** 
	 * Declaracion de listas y otras estructuras de datos
	 **/
	private List<Estudiante> listEstudiante;
	private List<Exonerado> listExonerado;
	private ListModelList<Motivo> modeloMotivo;
	private List<Motivo> listMotivo;
	ListModelList<Estudiante> modeloEstudianteEx;
	ListModelList<Exonerado> modeloExonerados;
	

	
	/**
	 * Setter y Getter 
	 **/

	public String getNombre() {
		return nombre;
	}
	
	public Date getExoneradoFecha() {
		return exoneradoFecha;
	}
	
	public void setExoneradoFecha(Date exoneradoFecha) {
		this.exoneradoFecha = exoneradoFecha;
	}
	
	public Estudiante getSelectedEstudiante() {
		return selectedEstudiante;
	}
	
	public void setSelectedEstudiante(Estudiante selectedEstudiante) {
		this.selectedEstudiante = selectedEstudiante;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public SEstudiante getSestudiante() {
		return sestudiante;
	}
	
	public SExonerado getSexonerado() {
		return sexonerado;
	}
	
	public void setSestudiante(SEstudiante sestudiante) {
		this.sestudiante = sestudiante;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getPrograma() {
		return programa;
	}
	
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	public ListModelList<Estudiante> getModeloEstudianteEx() {
		return modeloEstudianteEx;
	}
	
	public void setModeloEstudianteEx(
			ListModelList<Estudiante> modeloEstudianteEx) {
		this.modeloEstudianteEx = modeloEstudianteEx;
	}
	
	public void setSexonerado(SExonerado sexonerado) {
		this.sexonerado = sexonerado;
	}
	
	public String getCedula() {
		return cedula;
	}
	
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public List<Estudiante> getListEstudiante() {
		return listEstudiante;
	}
	
	public void setListEstudiante(List<Estudiante> listEstudiante) {
		this.listEstudiante = listEstudiante;
	}
	
	public SMotivo getSmotivo() {
		return smotivo;
	}
	
	public void setSmotivo(SMotivo smotivo) {
		this.smotivo = smotivo;
	}
	
	public ListModelList<Motivo> getModeloMotivo() {
		return modeloMotivo;
	}
	
	public void setModeloMotivo(ListModelList<Motivo> modeloMotivo) {
		this.modeloMotivo = modeloMotivo;
	}
	
	public List<Motivo> getListMotivo() {
		return listMotivo;
	}
	
	public void setListMotivo(List<Motivo> listMotivo) {
		this.listMotivo = listMotivo;
	}
	
	public Motivo getSelectedMotivo() {
		return selectedMotivo;
	}
	
	public void setSelectedMotivo(Motivo selectedMotivo) {
		this.selectedMotivo = selectedMotivo;
	}
	
	public String getCedulaex() {
		return cedulaex;
	}
	
	public void setCedulaex(String cedulaex) {
		this.cedulaex = cedulaex;
	}
	
	public String getNombreex() {
		return nombreex;
	}
	
	public void setNombreex(String nombreex) {
		this.nombreex = nombreex;
	}
	
	public String getApellidoex() {
		return apellidoex;
	}
	
	public void setApellidoex(String apellidoex) {
		this.apellidoex = apellidoex;
	}
	
	public String getDireccionex() {
		return direccionex;
	}
	
	public void setDireccionex(String direccionex) {
		this.direccionex = direccionex;
	}
	
	public String getTelefonoex() {
		return telefonoex;
	}
	
	public void setTelefonoex(String telefonoex) {
		this.telefonoex = telefonoex;
	}
	
	public String getProgramaex() {
		return programaex;
	}
	
	public void setProgramaex(String programaex) {
		this.programaex = programaex;
	}
	
	public String getMotivoex() {
		return motivoex;
	}
	
	public void setMotivoex(String motivoex) {
		this.motivoex = motivoex;
	}
	
	public List<Exonerado> getListExonerado() {
		return listExonerado;
	}
	
	public void setListExonerado(List<Exonerado> listExonerado) {
		this.listExonerado = listExonerado;
	}
	
	public Exonerado getSelectedExonerado() {
		return selectedExonerado;
	}
	
	public void setSelectedExonerado(Exonerado selectedExonerado) {
		this.selectedExonerado = selectedExonerado;
	}
	
	public ListModelList<Exonerado> getModeloExonerados() {
		return modeloExonerados;
	}
	
	public void setModeloExonerados(ListModelList<Exonerado> modeloExonerados) {
		this.modeloExonerados = modeloExonerados;	
	}
	
	public Motivo getSelectedMotivoEx() {
		return selectedMotivoEx;
	}
	
	public void setSelectedMotivoEx(Motivo selectedMotivoEx) {
		this.selectedMotivoEx = selectedMotivoEx;
	}
	
	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 */
	
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(win, this, false);
		this.win = (Window) win;
		Causa cau= scausa.buscarPorNombre("Exoneracion");
		listMotivo = cau.getCausaMotivo();
		modeloMotivo = new ListModelList<Motivo>(listMotivo);
		modeloEstudianteEx = new ListModelList<Estudiante>();
		modeloExonerados = new ListModelList<Exonerado>();

	}
	
	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		cmbMotivoExonerar.setPlaceholder("Seleccione");
		//cmbMotivoRevertirEx.setPlaceholder("Seleccione");

	}

	
	/** Metodo que abre la ventana del catalogo de estudiantes aptos y
	 *	obtiene el estudiante que se seleccione
	 **/
	 @Command
	 @NotifyChange({ "modeloEstudianteEx", "cedula", "nombre", "apellido","direccion", "telefono", "programa" })
	    public void mostrarCatalogo(){
		 final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("estudiante", "Exonerar");
			Window comp = (Window) Executions.createComponents(
					"/WEB-INF/vista/view/view.maestros/CatalogoEstudianteApto.zul",
					null, map);
			comp.doModal();
			Estudiante estudiante = (Estudiante) comp.getAttribute("estudiante");
			if(estudiante!=null)
				obtenerEstudiantesExonerarEstudiante(estudiante);
			}
	
	 
	 
	   /** Metodo que abre la ventana del catalogo de estudiantes exonerados y
		* obtiene el estudiante que se seleccione
		**/
	 @NotifyChange({ "modeloExonerados", "cedulaex", "nombreex", "apellidoex","direccionex", "telefonoex", "programaex", "motivoex" ,"codigoex"})	
	 @Command
		    public void mostrarCatalogoExonerados(){
			 final HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("exonerado", "Exonerado");  
				Window comp = (Window) Executions.createComponents("/WEB-INF/vista/view/view.maestros/CatalogoEstudianteExonerado.zul", null, null);
				comp.doModal();
				Exonerado exonerado= (Exonerado) comp.getAttribute("exonerado");
				if(exonerado!=null)
				obtenerExonerados(exonerado);
		 }
	 
	 
	 /** Metodo que obtiene los datos del estudiante apto seleccionado en la BD y
	  *	los muestra en la interfaz
	  **/
	 
	@NotifyChange({ "modeloEstudianteEx", "cedula", "nombre", "apellido","direccion", "telefono", "programa" })
    public void obtenerEstudiantesExonerarEstudiante(Estudiante estudiante)
       {
           this.cedula = 	estudiante.getEstudianteCedula();
           this.nombre=  	estudiante.getEstudianteNombre();
           this.apellido= 	estudiante.getEstudianteApellido();
           this.telefono=  	estudiante.getEstudianteTelefono();
           this.direccion= 	estudiante.getEstudianteDireccion();
           this.programa = 	estudiante.getDireccionProgramae().getDireccionNombre();
       }
   
	
	/**
	 * Metodo que se encarga de guardar el registro en la base de datos del
	 * estudiante a exonerar es decir exonerar estudiante
	 */
	@Command
	@NotifyChange({ "cedula", "nombre", "apellido", "direccion", "telefono",
		"programa" , "modeloMotivo" })
	public void guardar() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		if (cedula==null){
			mensajeEmergente.advertenciaSeleccionarOpcion();		
		}	
			else{
			       if(selectedMotivo==null){
			    	   mensajeEmergente.advertenciaSeleccionarMotivo();
					}
			       	else {
					exoneradoFecha = new Date();
					Estudiante estudiante = sestudiante.buscarPorCedula(cedula);
					Exonerado exo = new Exonerado(exoneradoFecha, estudiante, selectedMotivo);	
					estudiante.setEstudianteStatus("Exonerado");
					sexonerado.guardar(exo);
					sestudiante.guardar(estudiante);
					mensajeEmergente.informacionRegistroCorrecto();
					cancelar();
			       	}
			     }
		
	}
	
	
	/** Metodo que obtiene los datos del estudiante exonerado seleccionado en la BD y
	 *	los muestra en la interfaz
	 **/
		@GlobalCommand 
		@NotifyChange({ "modeloExonerados", "cedulaex", "nombreex", "apellidoex","direccionex", "telefonoex", "programaex", "motivoex" ,"codigoex"})
	    public void obtenerExonerados(
	    		Exonerado exonerado)
	            
	       {
	           this.cedulaex   = exonerado.getEstudianteExonerado().getEstudianteCedula();
	           this.nombreex   = exonerado.getEstudianteExonerado().getEstudianteNombre();
	           this.apellidoex = exonerado.getEstudianteExonerado().getEstudianteApellido();
	           this.telefonoex = exonerado.getEstudianteExonerado().getEstudianteTelefono();
	           this.direccionex= exonerado.getEstudianteExonerado().getEstudianteDireccion();
	           this.programaex = exonerado.getEstudianteExonerado().getDireccionProgramae().getDireccionNombre();
	           this.motivoex   = exonerado.getMotivoExonerado().getMotivoNombre();
	           this.codigoex   = exonerado.getExoneradoCodigo();
	           
	       }
	   
		/** Metodo que edita los datos del estudiante exonerado seleccionado en la BD y
		  *	los actualiza en la interfaz
		  **/
		@Command
		@NotifyChange({ "cedulaex", "nombreex", "apellidoex", "direccionex", "telefonoex",
			"programaex" , "motivoex", "modeloMotivo" })
		public void editarex() {
			
			MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
			
			if (cedulaex==null){
				mensajeEmergente.advertenciaSeleccionarOpcion();
			}
			else{	
				if (selectedMotivoEx==null){ 
					mensajeEmergente.advertenciaSeleccionarMotivo();
				}
				else {
				exoneradoFecha = new Date();			
				Exonerado exonerado = sexonerado.buscarPorCodigo(this.codigoex);
				exonerado.setMotivoExonerado(selectedMotivoEx);
				sexonerado.guardar(exonerado);
				mensajeEmergente.informacionActualizarDatos();
				cancelarex();	
			}
			}
			
		}
	

		/**Metodo que limpia los datos de la ventana en "exonerar estudiante"*/
		
	@Command
	@NotifyChange({ "cedula", "nombre", "apellido", "direccion", "telefono",
			"programa" , "modeloMotivo", "selectedMotivo", "modeloEstudianteEx" })
	
	public void cancelar() {
		this.cedula = null;
		this.nombre = null;
		this.apellido = null;
		this.direccion = null;
		this.telefono = null;
		this.programa = null;
		this.modeloMotivo.clear();
		this.selectedMotivo = null;
		cmbMotivoExonerar.setValue("");
		Causa caus =  scausa.buscarPorNombre("Exoneracion");
		listMotivo = caus.getCausaMotivo();
		modeloMotivo = new ListModelList<Motivo>(listMotivo);
		
	}
	
	
	/**Metodo que limpia los datos de la ventana en "revertir exonerado"*/

	@Command
	@NotifyChange({ "cedulaex", "nombreex", "apellidoex", "direccionex", "telefonoex",
			"programaex" , "modeloMotivo", "motivoex", "modeloEstudianteEx" })
	
	public void cancelarex() {
		this.cedulaex = null;
		this.nombreex = null;
		this.apellidoex = null;
		this.direccionex = null;
		this.telefonoex = null;
		this.programaex = null;
		this.modeloMotivo.clear();
		//cmbMotivoRevertirEx.setValue("");
		this.motivoex = null;
		Causa causa =  scausa.buscarPorNombre("Exoneracion");
		listMotivo = causa.getCausaMotivo();
		modeloMotivo = new ListModelList<Motivo>(listMotivo);
	}
	
	
	
	/**Metodo que elimina los datos del estudiante exonerado de la tabla exonerados en la BD en revertir exonerado*/

	@Command
	@NotifyChange({ "cedulaex", "nombreex", "apellidoex", "direccionex", "telefonoex",
		"programaex" , "motivoex", "modeloMotivo" })
	public void eliminarex() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		
		if (cedulaex==null){
			mensajeEmergente.advertenciaSeleccionarOpcion();
		}
		else {		
			Estudiante estudiante = sestudiante.buscarPorCedula(cedulaex);
			Exonerado exon = sexonerado.buscarPorCodigo(codigoex);
			estudiante.setEstudianteStatus("Apto");
			sestudiante.guardar(estudiante);
			sexonerado.eliminar(exon);
			mensajeEmergente.informacionEliminarCorrecto();
		}
		cancelarex();
	}
	

	/**Metodo que limpia los datos y cierra la ventana*/

	@Command
	public void cerrarVentana() {
		win.setVisible(false);
	}
	
	

}
