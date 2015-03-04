/* @VMCatalogoEstudianteExonerado
 * Inicialización de Variables e implementación de todas las funcionalidades y métodos
 * de la interfaz <CatalogoEstudianteExonerado.zul>
 * @author Maryelis Méndez/Jhezir Canela
 * Version 1.0, 29/01/2014
 * Version 1.1, 30/01/2014
 * Version 1.2, 01/02/2014
 * Version 1.3, 10/02/2014
 
 */


package siagsce.viewmodel.maestros;
import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Exonerado;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SExonerado;

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
/**
 * ViewModel para la vista que muestra el catalogo de los
 * Exonerados del Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoEstudianteExonerado {
	
	
	/**
	 * Declaracion de servicios del ViewModel
	 * */
	private @WireVariable
	SExonerado sexonerado;
	
	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private List<Exonerado> listaExonerado;
	private List<Estudiante> listaEstudiante;
	private ListModelList<Estudiante> modeloEstudiante;	
	private ListModelList<Exonerado> modeloExonerado;
	private List<String> valores;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private Exonerado selectedExonerados;
	private String ventana;
	private String texto;
	private String seleccion;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	private Window win;
	@Wire
	Textbox txtFiltroEstudiante;
	@Wire
	private Popup popOpciones;
	
	/**
	 * Setter y Getter
	 * */
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
	 * @param ventana variable que trae la informacion de que ventana solicita el catalogo
	 */
	@Init
	public void init( @ContextParam(ContextType.COMPONENT) Component win ,
			@ExecutionArgParam("exonerado") String ventana) {
		this.win = (Window) win;
		this.ventana=ventana;
		listaExonerado = sexonerado.buscarTodo();
		
		modeloExonerado = new ListModelList<Exonerado>(listaExonerado);
		modeloExonerado.setMultiple(true);
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
	 * Envia Al Estudiante Exonerado Seleccionado
	 * a la vista principal
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void guardar(){
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
				System.out.print("ventana");
				if (this.selectedExonerados != null) {
					win.setAttribute("exonerado", this.selectedExonerados);
					win.detach();
				} else {
					mensajeEmergente.advertenciaSeleccionarOpcion();
				}

	}
	
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de Estudiantes Exonerados con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoEstudianteExonerado}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "modeloExonerado" })
	public void filtrarEstudiante() {
		List<Exonerado> aux = new ArrayList<Exonerado>();
		aux = listaExonerado;
		modeloExonerado.clear();
		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modeloExonerado.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteExonerado().getEstudianteNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloExonerado.add(aux.get(i));

						}
					}
				}
			
		} else {
			if (seleccion == "Cédula") {
				if (texto == "")
					modeloExonerado.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteExonerado().getEstudianteCedula().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloExonerado.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Nombre") {
					if (texto == "")
						modeloExonerado.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getEstudianteExonerado().getEstudianteNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloExonerado.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion== "Apellido") {
						if (texto == "")
							modeloExonerado.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getEstudianteExonerado().getEstudianteApellido()
										.toLowerCase().contains(texto.toLowerCase())) {
									modeloExonerado.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion== "Programa") {
							if (texto == "")
								modeloExonerado.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getEstudianteExonerado().getDireccionProgramae()
											.getDireccionNombre().toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloExonerado.add(aux.get(i));

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
		if (seleccion=="Nombre") {
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
					if (seleccion== "Programa") {
						txtFiltroEstudiante.setPlaceholder("programa");
						popOpciones.close();
					}
				}
			}
		}

	}
	
	/**
	 * cierra el catalogo
	 **/
		@Command
		public void salir() {
			 win.detach();
	}

}