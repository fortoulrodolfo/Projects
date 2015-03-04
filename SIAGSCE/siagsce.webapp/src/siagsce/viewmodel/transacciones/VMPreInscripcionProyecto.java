package siagsce.viewmodel.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javassist.compiler.ast.Variable;
import siagsce.herramientas.*;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;

import org.zkoss.zul.Window;

import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;
import siagsce.modelo.general.StatusPreinscripcionProyecto;
import siagsce.modelo.general.StatusProyecto;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.modelo.servicio.transacciones.SPreInscripcionProyecto;


/**
 * ViewModel para la vista de Evaluacion de  Inscripcion de un 
 * alumno en un proyecto del 
 * Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMPreInscripcionProyecto {


	/** 
	 * Declaracion de servicios del ViewModel
	 * */

	@Wire
	private Window win;
	private @WireVariable
	SProyecto sproyecto;
	private @WireVariable
	SEstudiante sestudiante;
	private @WireVariable
	SInscripcionProyecto sinscripcionProyecto;
	private @WireVariable
	SPreInscripcionProyecto spreinscripcionproyecto;
	
	/** 
	 * Declaracion de Variables del ViewModel
	 * */
	
	private String estudianteCedula;
	private String estudianteNombre;
	private String estudianteApellido;
	private String estudiantePrograma;
	private Proyecto selectedProyecto;
	private Estudiante selectedEstudiante;
	private InscripcionProyecto inscripcion;
	private PreInscripcionProyecto preinscripcion;
	private String proyectoCodigo;
	private String proyectoNombre;
	private String proyectoDescripcion;
	private List<String> valores;
	private String texto;
	private String seleccion;
	@Wire
	Textbox txtFiltroProyecto;
	@Wire
	private Popup popOpciones;
	
	
	/** 
	 * Declaracion de listas y otras estructuras de datos
	 **/
	
	private List<Proyecto> listaProyecto;
	private ListModelList<Proyecto> modeloProyecto;

	/**
	 * Setter y Getter
	 **/


	public String getEstudianteCedula() {
		return estudianteCedula;
	}


	public void setEstudianteCedula(String estudianteCedula) {
		this.estudianteCedula = estudianteCedula;
	}


	public String getEstudianteNombre() {
		return estudianteNombre;
	}


	public void setEstudianteNombre(String estudianteNombre) {
		this.estudianteNombre = estudianteNombre;
	}


	public String getEstudianteApellido() {
		return estudianteApellido;
	}


	public void setEstudianteApellido(String estudianteApellido) {
		this.estudianteApellido = estudianteApellido;
	}


	public String getEstudiantePrograma() {
		return estudiantePrograma;
	}


	public void setEstudiantePrograma(String estudiantePrograma) {
		this.estudiantePrograma = estudiantePrograma;
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

	public Estudiante getSelectedEstudiante() {
		return selectedEstudiante;
	}


	public void setSelectedEstudiante(Estudiante selectedEstudiante) {
		this.selectedEstudiante = selectedEstudiante;
	}


	public void setSelectedProyecto(Proyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
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

	public void setProyectoDescripcion(String proyectoDescripcion) {
		this.proyectoDescripcion = proyectoDescripcion;
	}
	
	public InscripcionProyecto getInscripcion() {
		return inscripcion;
	}


	public void setInscripcion(InscripcionProyecto inscripcion) {
		this.inscripcion = inscripcion;
	}


	public PreInscripcionProyecto getPreinscripcion() {
		return preinscripcion;
	}


	public void setPreinscripcion(PreInscripcionProyecto preinscripcion) {
		this.preinscripcion = preinscripcion;
	}


	public List<String> getValores() {
		return valores;
	}


	public void setValores(List<String> valores) {
		this.valores = valores;
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


	public Textbox getTxtFiltroProyecto() {
		return txtFiltroProyecto;
	}


	public void setTxtFiltroProyecto(Textbox txtFiltroProyecto) {
		this.txtFiltroProyecto = txtFiltroProyecto;
	}


	public Popup getPopOpciones() {
		return popOpciones;
	}


	public void setPopOpciones(Popup popOpciones) {
		this.popOpciones = popOpciones;
	}


	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {


		listaProyecto = sproyecto.buscarPorStatus(StatusProyecto.Activo.toString());
		modeloProyecto = new ListModelList<Proyecto>(listaProyecto);
		MensajeBox.doSetTemplate();
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		valores = new ArrayList<String>();
		valores.add("Nombre");
		valores.add("Descripción");
		valores.add("Programa");

	}
	
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		txtFiltroProyecto.setPlaceholder("Nombre");
	}

	
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de proyectos ,con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * */
	@Command
	@NotifyChange({ "modeloProyecto" })
	public void filtrarProyecto() {
		List<Proyecto> aux = new ArrayList<Proyecto>();
		aux = listaProyecto;
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
			if (seleccion == "Nombre") {
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
				if (seleccion == "Descripción") {
					if (texto == "")
						modeloProyecto.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProyectoDescripcion().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloProyecto.add(aux.get(i));

							}
						}
					}
				}
				else{
					if (seleccion == "Programa") {
						if (texto == "")
							modeloProyecto.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProgramaString().toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloProyecto.add(aux.get(i));

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
		if (seleccion== "Nombre") {
			txtFiltroProyecto.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion== "Descripción") {
				txtFiltroProyecto.setPlaceholder("Descripción");
				popOpciones.close();
			}
			else{
				if (seleccion== "Programa") {
					txtFiltroProyecto.setPlaceholder("Programa");
					popOpciones.close();
				}
			}
		}
	}


	/**
	 * metodo que permite registrar la pre inscripcion del alumno en el 
	 * proyecto que ha  seleccionado
	 */
	
	@NotifyChange({"selectedEstudiante" , "selectedProyecto" , "estudianteCedula", "estudianteNombre", "estudianteApellido" , "estudiantePrograma" , "modeloProyecto" , "selectedProyecto" , "selectedEstudiante"})
	@Command
	public void registrarPreInscripcion(){
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if(this.selectedEstudiante != null && this.selectedProyecto != null){
			this.inscripcion = sinscripcionProyecto.buscarInscripcionActiva(this.selectedEstudiante);
			this.preinscripcion = spreinscripcionproyecto.buscarPorEstudianteProyectoYPreinscripcionStatus(selectedEstudiante, selectedProyecto, StatusPreinscripcionProyecto.Activo.toString());
			if(inscripcion == null){
				if(preinscripcion == null){
				PreInscripcionProyecto preInscripcion = new PreInscripcionProyecto();
				preInscripcion.setEstudiante(this.selectedEstudiante);
				preInscripcion.setProyectop(this.selectedProyecto);
				Date fecha= new Date();
				preInscripcion.setPreinscripcionProyectoFecha(fecha);
				preInscripcion.setPreinscripcionStatus(StatusPreinscripcionProyecto.Activo.toString());
				spreinscripcionproyecto.guardar(preInscripcion);
				mensajeEmergente.informacionRegistroCorrecto();
				win.detach();
				}
				else {
					mensajeEmergente.errorPreinscripcionProyectoActiva();
					this.cancelar();
				}
			}
			else {
				mensajeEmergente.errorPreinscripcionProyecto();
				this.cancelar();
			}
		}
		else {
			mensajeEmergente.advertenciaLlenarCampos();
			this.cancelar();
		}

	}

	/**
	 * metodo que permite buscar la información del alumno para poder
	 * pre inscribirlo en el proyecto que ha  seleccionado
	 */
	
	@NotifyChange({"estudianteCedula", "estudianteNombre", "estudianteApellido" , "estudiantePrograma" , "modeloProyecto", "selectedEstudiante"})
	@Command
	public void buscarEstudiante() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		if(this.estudianteCedula == "" || this.estudianteCedula == null ){

			mensajeEmergente.advertenciaIngresarCedula();
			this.cancelar();

		}
		else{

			this.selectedEstudiante = sestudiante.buscarPorCedula(this.estudianteCedula);
			if(selectedEstudiante != null){
				if(selectedEstudiante.getEstudianteStatus().contains("Acreditado")){
					this.estudianteCedula=selectedEstudiante.getEstudianteCedula();
					this.estudianteNombre=selectedEstudiante.getEstudianteNombre();
					this.estudianteApellido=selectedEstudiante.getEstudianteApellido();
					DireccionPrograma direccionProg = selectedEstudiante.getDireccionProgramae();
					this.estudiantePrograma=direccionProg.getDireccionNombre();
					listaProyecto = sproyecto.buscarPorProgramaYProyectoStatus(direccionProg, StatusProyecto.Activo.toString());
					modeloProyecto = new ListModelList<Proyecto>(listaProyecto);

				}
				else{
					mensajeEmergente.errorCedulaNoAcreditado();
					this.cancelar();
				}
			}
			else{
				mensajeEmergente.errorCedulaNoApta();
				this.cancelar();
			}
		}
	}

	/**
	 * Metodo que  limpia los  valores de las  variables
	 * y limpia los  valores de la  ventana
	 **/
	
	@NotifyChange({"estudianteCedula", "estudianteNombre", "estudianteApellido" , "estudiantePrograma" , "modeloProyecto" , "selectedProyecto" , "selectedEstudiante"})
	@Command
	public void cancelar() {
		this.estudianteApellido="";
		this.estudianteCedula="";
		this.estudianteNombre="";
		this.estudiantePrograma="";
		this.selectedProyecto=null;
		this.selectedEstudiante=null;
		listaProyecto = sproyecto.buscarTodo();
		modeloProyecto = new ListModelList<Proyecto>(listaProyecto);

	}
	
	/**
	 * Metodo que  permite cerrar la ventana
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}
}
