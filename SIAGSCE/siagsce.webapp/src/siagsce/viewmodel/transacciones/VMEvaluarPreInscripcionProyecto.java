package siagsce.viewmodel.transacciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.compiler.ast.Variable;

import siagsce.herramientas.*;

import org.zkoss.bind.BindUtils;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.ListadoPreinscripto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;
import siagsce.modelo.general.StatusPreinscripcionProyecto;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.transacciones.SListadoPreinscripto;
import siagsce.modelo.servicio.transacciones.SPreInscripcionProyecto;
import siagsce.viewmodel.maestros.VMRegistrarProyecto;
import siagsce.viewmodel.seguridad.SecurityUtil;


/**
 * ViewModel para la vista de Evaluacion de la Preinscripcion de un 
 * alumno en un proyecto del 
 * Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEvaluarPreInscripcionProyecto {

	/** 
	 * Declaracion de servicios del ViewModel
	 * */	
	@WireVariable
	private SProyecto sproyecto;
	@WireVariable
	private SProfesor sprofesor;	
	@WireVariable
	private SPreInscripcionProyecto spreinscripcionproyecto;
	@WireVariable
	private SListadoPreinscripto sListadoPreinscripto; 

	/** 
	 * Declaracion de Componentes de la vista
	 * */
	
	@Wire
	private Window win;
	@Wire
	private Textbox txtFiltroPreInscritos;
	@Wire
	private Popup popOpciones;
	@Wire
	private Combobox cmbProyecto;

	/** 
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private ListModelList<Proyecto> modeloProyecto;
	private ListModelList<ListadoPreinscripto> modeloPreinscritos;
	private ListModelList<ListadoPreinscripto> modeloPreinscritosAsig;
	private List<ListadoPreinscripto> listPreinscritos;
	private List<Proyecto>listproyecto;
	private Set<ListadoPreinscripto>selectedpreinscritos;
	private List<String> valores;

	/** 
	 * Declaracion de Variables del ViewModel
	 * */
	private Proyecto selectedProyecto;
	private PreInscripcionProyecto preinscripcion;
	private String codigo;
	private String nombreProyecto;
	private String descripcion;
	private String responsable;
	private String programa;
	private String seleccion;
	private String texto;

	/**
	 * Setter y Getter
	 **/
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
	public PreInscripcionProyecto getPreinscripcion() {
		return preinscripcion;
	}
	public void setPreinscripcion(PreInscripcionProyecto preinscripcion) {
		this.preinscripcion = preinscripcion;
	}
	public ListModelList<Proyecto> getModeloProyecto() {
		return modeloProyecto;
	}
	public void setModeloProyecto(ListModelList<Proyecto> modeloProyecto) {
		this.modeloProyecto = modeloProyecto;
	}
	public ListModelList<ListadoPreinscripto> getModeloPreinscritos() {
		return modeloPreinscritos;
	}
	public void setModeloPreinscritos(
			ListModelList<ListadoPreinscripto> modeloPreinscritos) {
		this.modeloPreinscritos = modeloPreinscritos;
	}
	public ListModelList<ListadoPreinscripto> getModeloPreinscritosAsig() {
		return modeloPreinscritosAsig;
	}
	public void setModeloPreinscritosAsig(
			ListModelList<ListadoPreinscripto> modeloPreinscritosAsig) {
		this.modeloPreinscritosAsig = modeloPreinscritosAsig;
	}
	public Proyecto getSelectedProyecto() {
		return selectedProyecto;
	}
	public void setSelectedProyecto(Proyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public Set<ListadoPreinscripto> getSelectedpreinscritos() {
		return selectedpreinscritos;
	}
	public void setSelectedpreinscritos(
			Set<ListadoPreinscripto> selectedpreinscritos) {
		this.selectedpreinscritos = selectedpreinscritos;
	}


	public List<Proyecto> getListproyecto() {
		return listproyecto;	
	}
	public void setListproyecto(List<Proyecto> listproyecto) {
		this.listproyecto = listproyecto;
	}
	

	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win){
		this.win=(Window)win;
		MensajeBox.doSetTemplate();		
		modeloPreinscritos= new ListModelList<ListadoPreinscripto>();
		modeloPreinscritosAsig=new ListModelList<ListadoPreinscripto>();
		modeloPreinscritos.setMultiple(true);
		modeloPreinscritosAsig.setMultiple(true);
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");

	}
	
	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */

	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		//cmbProyecto.setPlaceholder("Seleccione");
	}


	/**
	 * Muestra un catalogo de los proyecto disponibles para evaluar la preinscripcion de los estudiantes y el proyecto
	 * seleccionado lo envia a la funcion proyectoSeleccionado()
	 * @link {@link VMEvaluacionPreInscripcionProyecto}{@link #proyectoSeleccionado()}
	 */
	@NotifyChange({"codigo","nombreProyecto","descripcion","responsable","programa","modeloPreinscritos","selectedProyecto"})
	@Command
	public void mostrarProyecto() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("proyecto", "EvaluacionPreinscripcionProyecto");
		selectedProyecto = null;
		//cancelarMo();
		Window comp = (Window) Executions.createComponents(
				"/WEB-INF/vista/view/view.maestros/CatalogoProyectos.zul",
				null, map);
		comp.doModal();
		selectedProyecto = (Proyecto) comp.getAttribute("proyecto");
		if (selectedProyecto != null)
			proyectoSeleccionado();
	}

	/**
	 **Metodo que carga los proyectos
	 */
	

	
	/**
	 * Metodo que asigna  la informacion de los  proyectos
	 * y carga los  alumnos preinscritos
	 * */
	
	@NotifyChange({"codigo","nombreProyecto","descripcion","responsable","programa","modeloPreinscritos","selectedProyecto"})
	@Command
	public void proyectoSeleccionado(){
		codigo=selectedProyecto.getProyectoCodigo();
		nombreProyecto = selectedProyecto.getProyectoNombre();
		descripcion=selectedProyecto.getProyectoDescripcion();
		responsable=selectedProyecto.getResponsableString();
		programa=selectedProyecto.getProgramaString();
		cargarPreinscritos();
	}

	
	/**
	 * Metodo  que carga los  alumnos  preinscritos
	 **/
	
	public void cargarPreinscritos(){

		String codigo=(String)selectedProyecto.getProyectoCodigo();
		modeloPreinscritos.clear();
		listPreinscritos=sListadoPreinscripto.buscarPreinscrito(codigo);
		modeloPreinscritos.addAll(listPreinscritos);
	}

	
	/**
	 * metodo  que permite enviar  los  preinscritos
	 * a la  lista de los  inscritos
	 **/
	@NotifyChange({"modeloPreinscritosAsig","modeloPreinscritos", "selectedpreinscritos" })
	@Command
	public void asignarPreinscritos(){
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		try {
			modeloPreinscritosAsig.addAll(selectedpreinscritos);
			modeloPreinscritos.removeAll(selectedpreinscritos);
			selectedpreinscritos.clear();
			
		} catch (NullPointerException e) {
			mensajeEmergente.advertenciaSeleccionarEstudiante();
		}
		
		
		
	}

 	
	
	/**
	 * metodo   que  devuelve un  alumno de la  lista
	 * inscritos  a preinscritos
	 **/

	@NotifyChange({"modeloPreinscritosAsig","modeloPreinscritos", "selectedpreinscritos" })
	@Command
	public void sacarPreinscritos(){
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		try {
			modeloPreinscritos.addAll(selectedpreinscritos);
			modeloPreinscritosAsig.removeAll(selectedpreinscritos);
			selectedpreinscritos.clear();
			
		} catch (NullPointerException e) {
			mensajeEmergente.advertenciaSeleccionarEstudiante();
		}
		
	}


	/**
	 * Metodo  que  guarda el alumno  que se acepto
	 **/
	
	@NotifyChange({"codigo", "descripcion","responsable","programa", "modeloProyecto", "modeloPreinscritosAsig"})
	@Command
	public void evaluar(){
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if(modeloPreinscritosAsig.isEmpty())
			mensajeEmergente.advertenciaSeleccionarOpcion();
		else {
			Integer codigo;
			
			for(int i=0;i<modeloPreinscritosAsig.size();i++){			
				codigo=modeloPreinscritosAsig.get(i).getPreinscripcion();

				preinscripcion=spreinscripcionproyecto.buscarPreinscripcion(codigo);
				preinscripcion.setPreinscripcionStatus(StatusPreinscripcionProyecto.EnEspera.toString());
				spreinscripcionproyecto.guardar(preinscripcion);
				
				

			}
			
			mensajeEmergente.informacionRegistroCorrecto();
			this.codigo = null;
			this.descripcion = null;
			this.responsable = null;
			this.programa = null;
			cancelar();
			
		}
	}

	
	/**
	 * Metodo que limpia los datos de la ventana
	 **/
	
	@NotifyChange({"codigo","nombreProyecto","descripcion","responsable","programa","modeloPreinscritos","modeloPreinscritosAsig",
	"modeloProyecto"})
	@Command
	public void cancelar(){
		//modeloProyecto.clear();
		modeloPreinscritos.clear();
		modeloPreinscritosAsig.clear();
		codigo="";
		nombreProyecto = "";
		descripcion="";
		responsable="";
		programa="";
	}



	/**
	 * Metodo  que  permite filtrar un estudiante en el catalogo
	 **/
	
	@Command
	@NotifyChange({ "modeloPreinscritos" })
	public void filtrarEstudiante() {
		try {
			List<ListadoPreinscripto> aux = new ArrayList<ListadoPreinscripto>();
			aux =CargarListaPreinscrito();
			modeloPreinscritos.clear();

			if (seleccion == null || seleccion == "") {
				if (texto == "")
					modeloPreinscritos.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloPreinscritos.add(aux.get(i));

						}
					}
				}

			} else {
				if (seleccion== "Cédula") {
					if (texto == "")
						modeloPreinscritos.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getEstudianteCedula()
									.contains(texto.toLowerCase())) {
								modeloPreinscritos.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion== "Nombre") {
						if (texto == "")
							modeloPreinscritos.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getEstudianteNombre().toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloPreinscritos.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Apellido") {
							if (texto == "")
								modeloPreinscritos.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getEstudianteApellido()
											.toLowerCase().contains(texto.toLowerCase())) {
										modeloPreinscritos.add(aux.get(i));

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
	 * Metodo  que  permite filtrar un estudiante en el catalogo
	 **/
	@Command
	public void seleccionFiltro() {
		System.out.print(seleccion);
		if (seleccion == "Nombre") {
			txtFiltroPreInscritos.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroPreInscritos.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido") {
					txtFiltroPreInscritos.setPlaceholder("Apellido");
					popOpciones.close();
				} else {
					if (seleccion == "Programa") {
						txtFiltroPreInscritos.setPlaceholder("Programa");
						popOpciones.close();
					}
				}
			}
		}

	}
	/**
	 * LLena una lista con todas aquellas Preinscripciones
	 * que no estan en la lista de preinscripciones Asignadas
	 * */
	public List<ListadoPreinscripto> CargarListaPreinscrito(){
		List<ListadoPreinscripto>aux=listPreinscritos;
		List<ListadoPreinscripto>preinscritos= new ArrayList<ListadoPreinscripto>();
		if(modeloPreinscritosAsig.isEmpty())
			preinscritos=aux;
		else{
			for(int i=0;i<aux.size();i++){
				if(!(modeloPreinscritosAsig.contains(aux.get(i)))){
					preinscritos.add(aux.get(i));
				}
			}
		}
		return preinscritos;
	}
	
	/**
	 * Metodo cierra la  ventana
	 **/

	@Command
	public void salir(){
		win.detach();
	}

}
