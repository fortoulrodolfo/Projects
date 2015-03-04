

package siagsce.viewmodel.maestros;
import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Exonerado;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.general.StatusInscripcionProyecto;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SExonerado;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.viewmodel.transacciones.VMFormalizarInscripcionProyecto;

import org.apache.commons.digester.annotations.rules.AttributeCallParam;
import org.aspectj.lang.annotation.Around;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;

import java.util.Map;

import javax.jws.HandlerChain;


/**
 * Viewmodel Para la vista que muestra el catalogo de los Estudiantes inscritos
 * en un Determinado proyecto
 * @author Personal
 * */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoEstudiantesInscritos {

	/** 
	 * Declaracion de servicios del ViewModel
	 **/
	@WireVariable
	private SExonerado sexonerado;
	@WireVariable
	private SProyecto sproyecto;
	@WireVariable
	SInscripcionProyecto sinscripcionProyecto;
	
	/** 
	 * Declaracion de listas y otras estructuras de datos
	 **/
	private List<Exonerado> listaExonerado;
	private List<Estudiante> listaEstudiante;
	private ListModelList<Estudiante> modeloEstudiante;	
	private ListModelList<Exonerado> modeloExonerado;
	private List<String> valores;
	private ListModelList<InscripcionProyecto> modeloEstudianteInscritos;
	private ListModelList<InscripcionProyecto> modeloEstudiantesIns;
	private List<InscripcionProyecto> listEstudianteInscrito;
	
	/** 
	 * Declaracion de Variables del ViewModel
	 **/
	private InscripcionProyecto selectedInscripcion;
	private Exonerado selectedExonerados;
	public String codigoProyectoIns;
	private String ventana;
	private String texto;
	private String seleccion;
	private Proyecto selectedProyecto;
	private String codigo; 
	VMFormalizarInscripcionProyecto vmFormalizarInsc;
	
	/** 
	 * Declaracion de Componentes de la vista
	 **/
	@Wire
	private Window win;
	@Wire
	Textbox txtFiltroEstudiante;
	@Wire
	private Popup popOpciones;
	@Wire
	private Textbox txtcodigoproyecto;
	
	/**
	 * Setter y Getter
	 **/
	public Textbox getTxtcodigoproyecto() {
		return txtcodigoproyecto;
	}


	public void setTxtcodigoproyecto(Textbox txtcodigoproyecto) {
		this.txtcodigoproyecto = txtcodigoproyecto;
	}


	public String getCodigoProyectoIns() {
		return codigoProyectoIns;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public void setCodigoProyectoIns(String codigoProyectoIns) {
		this.codigoProyectoIns = codigoProyectoIns;
	}


	public ListModelList<InscripcionProyecto> getModeloEstudianteInscritos() {
		return modeloEstudianteInscritos;
	}


	public void setModeloEstudianteInscritos(
			ListModelList<InscripcionProyecto> modeloEstudianteInscritos) {
		this.modeloEstudianteInscritos = modeloEstudianteInscritos;
	}



	public List<InscripcionProyecto> getListEstudianteInscrito() {
		return listEstudianteInscrito;
	}


	public void setListEstudianteInscrito(
			List<InscripcionProyecto> listEstudianteInscrito) {
		this.listEstudianteInscrito = listEstudianteInscrito;
	}


	public InscripcionProyecto getSelectedInscripcion() {
		return selectedInscripcion;
	}


	public void setSelectedInscripcion(InscripcionProyecto selectedInscripcion) {
		this.selectedInscripcion = selectedInscripcion;
	}

	public Proyecto getSelectedProyecto() {
		return selectedProyecto;
	}


	public void setSelectedProyecto(Proyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}



	public VMFormalizarInscripcionProyecto getVmFormalizarInsc() {
		return vmFormalizarInsc;
	}


	public void setVmFormalizarInsc(VMFormalizarInscripcionProyecto vmFormalizarInsc) {
		this.vmFormalizarInsc = vmFormalizarInsc;
	}


	public ListModelList<InscripcionProyecto> getModeloEstudiantesIns() {
		return modeloEstudiantesIns;
	}


	public void setModeloEstudiantesIns(
			ListModelList<InscripcionProyecto> modeloEstudiantesIns) {
		this.modeloEstudiantesIns = modeloEstudiantesIns;
	}
	public SExonerado getSexonerado() {
		return sexonerado;
	}
	public void setSexonerado(SExonerado sexonerado) {
		this.sexonerado = sexonerado;
	}
	public List<Exonerado> getListaExonerado() {
		return listaExonerado;
	}
	public void setListaExonerado(List<Exonerado> listaExonerado) {
		this.listaExonerado = listaExonerado;
	}
	public ListModelList<Exonerado> getModeloExonerado() {
		return modeloExonerado;
	}
	public void setModeloExonerado(ListModelList<Exonerado> modeloExonerado) {
		this.modeloExonerado = modeloExonerado;
	}
	public Exonerado getSelectedExonerados() {
		return selectedExonerados;
	}
	public void setSelectedExonerados(Exonerado selectedExonerados) {
		this.selectedExonerados = selectedExonerados;
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

	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init( @ContextParam(ContextType.COMPONENT) Component win ,
			 @ExecutionArgParam("codigoProyecto") String codigo)  {
		
	
		this.win = (Window) win;
		this.ventana=ventana;
		this.codigoProyectoIns = codigo;
	
	
		modeloEstudianteInscritos = new ListModelList<InscripcionProyecto>();
		
		cargarAlumnosInscritos(this.codigoProyectoIns);
		MensajeBox.doSetTemplate();
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		valores.add("Programa");
	}



	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view,this, false); 
		txtFiltroEstudiante.setPlaceholder("Nombre");
	}

	/**
	 * Inserta en una modelo lista los Estudiantes Inscritos
	 * en un determinado proyecto
	 * @param proyecto  
	 **/
	@NotifyChange({"modeloEstudianteInscritos"})
	public void cargarAlumnosInscritos(String proyecto) {
		
		
		if (proyecto !=""){
			modeloEstudianteInscritos = new ListModelList<InscripcionProyecto>();
			selectedProyecto = sproyecto.buscarPorCodigo(proyecto);				
			modeloEstudianteInscritos.clear();			
			listEstudianteInscrito = sinscripcionProyecto.buscarPorCodigoYEstatus(selectedProyecto, StatusInscripcionProyecto.Activo.toString());
			modeloEstudianteInscritos.addAll(listEstudianteInscrito);
		}
	}


	
	/**
	 * Envia a la vista principal el estudiante seleccionado 
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void guardar(){

		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		System.out.print("ventana");
		System.out.println("IMPRIMO MOISES");
		System.out.println(this.codigoProyectoIns);
		if (this.selectedInscripcion != null) {
			win.setAttribute("inscrito", this.selectedInscripcion);
			
			win.detach();
		} else {
			mensajeEmergente.advertenciaSeleccionarOpcion();
		}

	}
	
	
	

	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de Estudiantes Inscritos con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoEstudiantesInscritos}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "profesormodel" })
	public void filtrarEstudiante() {
		List<InscripcionProyecto> aux = new ArrayList<InscripcionProyecto>();
		aux = listEstudianteInscrito;
		modeloEstudianteInscritos.clear();
		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modeloEstudianteInscritos.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getPreInscripcion().getEstudiante().getEstudianteNombre().toLowerCase()
							.contains(texto.toLowerCase())) {
						modeloEstudianteInscritos.add(aux.get(i));

					}
				}
			}

		} else {
			if (seleccion == "Cédula") {
				if (texto == "")
					modeloEstudianteInscritos.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getPreInscripcion().getEstudiante().getEstudianteCedula().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstudianteInscritos.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion== "Nombre") {
					if (texto == "")
						modeloEstudianteInscritos.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getPreInscripcion().getEstudiante().getEstudianteNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloEstudianteInscritos.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Apellido") {
						if (texto == "")
							modeloEstudianteInscritos.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getPreInscripcion().getEstudiante().getEstudianteApellido()
										.toLowerCase().contains(texto.toLowerCase())) {
									modeloEstudianteInscritos.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Programa") {
							if (texto == "")
								modeloEstudianteInscritos.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getPreInscripcion().getEstudiante().getDireccionProgramae()
											.getDireccionNombre().toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloEstudianteInscritos.add(aux.get(i));

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
	 * Segun la seleccion de un criterio de filtrado
	 * coloca una marca de agua con el nombre de la seleccion
	 * */
	@Command
	public void seleccionFiltro() {
		System.out.print(seleccion);
		if (seleccion == "Nombre") {
			txtFiltroEstudiante.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroEstudiante.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido") {
					txtFiltroEstudiante.setPlaceholder("Apellido");
					popOpciones.close();
				} else {
					if (seleccion == "Programa") {
						txtFiltroEstudiante.setPlaceholder("Programa");
						popOpciones.close();
					}
				}
			}
		}

	}

	/***
	 *Cierra el catalogo 
	 */
	@Command
	public void salir() {
		win.detach();
	}


}