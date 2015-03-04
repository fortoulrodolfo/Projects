package siagsce.viewmodel.maestros;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.servicio.maestros.SEstudiante;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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
import java.util.Iterator;
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
public class VmCatalogoEstudiantes {

	/**
	 * Declaracion de servicios del ViewModel
	 * */
	private @WireVariable
	SEstudiante sestudiante;
	
	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private List<String> valores;
	private List<Estudiante> listaEstudiante;
	private ListModelList<Estudiante> modeloEstudiante;
	private Set<Estudiante> selectedEstudiantes;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private String texto;
	private String ventana;
	private String seleccion;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	private Window win;
	@Wire
	private Popup popOpciones;
	@Wire
	Textbox txtFiltroEstudiante;
	public Textbox getTxtFiltroEstudiante() {
		return txtFiltroEstudiante;
	}
	/**
	 * Setter y Getter
	 * */
	public void setTxtFiltroEstudiante(Textbox txtFiltroEstudiante) {
		this.txtFiltroEstudiante = txtFiltroEstudiante;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getVentana() {
		return ventana;
	}

	public void setVentana(String ventana) {
		this.ventana = ventana;
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

	public List<Estudiante> getListaEstudiante() {
		return listaEstudiante;
	}

	public void setListaEstudiante(List<Estudiante> listaEstudiante) {
		this.listaEstudiante = listaEstudiante;
	}

	

	public SEstudiante getSestudiante() {
		return sestudiante;
	}

	public void setSestudiante(SEstudiante sestudiante) {
		this.sestudiante = sestudiante;
	}

	

	public Set<Estudiante> getSelectedEstudiantes() {
		return selectedEstudiantes;
	}

	public void setSelectedEstudiantes(Set<Estudiante> selectedEstudiantes) {
		this.selectedEstudiantes = selectedEstudiantes;
	}

	public SEstudiante getServicioEstudiante() {
		return sestudiante;
	}

	public void setServicioEstudiante(SEstudiante sestudiante) {
		this.sestudiante = sestudiante;
	}

	public ListModelList<Estudiante> getModeloEstudiante() {
		return modeloEstudiante;
	}

	public void setModeloEstudiante(ListModelList<Estudiante> modeloEstudiante) {
		this.modeloEstudiante = modeloEstudiante;
	}

	
	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * @param ventana variable que trae la informacion de que ventana solicita el catalogo
	 * @param estudiantesSeleccionados a inscribirse en una taller
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win,
			@ExecutionArgParam("estudiantesSeleccionadosAinscribir") 
			ListModelList<Estudiante> estudiantesSeleccionados) {
		this.win = (Window) win;
		listaEstudiante = sestudiante.buscarEstudiantesAIncribir();
		for(Estudiante est:estudiantesSeleccionados){
			for(int i=0;i<listaEstudiante.size();i++){
				Estudiante estudiante=listaEstudiante.get(i);
		        if(est.getEstudianteCedula().equals(estudiante.getEstudianteCedula()))
		    	   listaEstudiante.remove(i);
			}
		}
		modeloEstudiante = new ListModelList<Estudiante>(listaEstudiante);
		modeloEstudiante.setMultiple(true);
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
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		this.win = (Window) win;
		Selectors.wireComponents(view, this, false);
	}

	/**
	 * inserta en una lista a los Estudiante  Seleccionado
	 * y los envia  a la vista principal
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
    @Command
    public void aceptar() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		if(selectedEstudiantes!=null){
        List<Estudiante> listEstudiante = new ArrayList<Estudiante>();
		listEstudiante.addAll(selectedEstudiantes);	
        win.setAttribute("estudiantesSeleccionados",listEstudiante);
        win.detach();
        }else{
        	mensajeEmergente.advertenciaSeleccionarOpcion();
        }
    }
	
	

	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de Estudiantes  con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoEstudiante}{@link #seleccionFiltro()}
	 * */
	@Command
	public void filtrarEstudiante() {
		List<Estudiante> aux = new ArrayList<Estudiante>();
		aux = listaEstudiante;
		modeloEstudiante.clear();

		if (seleccion == null || seleccion == "") {
				if (texto == "")
					modeloEstudiante.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstudiante.add(aux.get(i));

						}
					}
				}
			
		} else {
			if (seleccion == "Cédula") {
				if (texto == "")
					modeloEstudiante.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteCedula().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstudiante.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Nombre") {
					if (texto == "")
						modeloEstudiante.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getEstudianteNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloEstudiante.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion== "Apellido") {
						if (texto == "")
							modeloEstudiante.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getEstudianteApellido()
										.toLowerCase().contains(texto.toLowerCase())) {
									modeloEstudiante.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Programa") {
							if (texto == "")
								modeloEstudiante.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getDireccionProgramae()
											.getDireccionNombre().toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloEstudiante.add(aux.get(i));

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
		if (seleccion.toLowerCase() == "Nombre") {
			txtFiltroEstudiante.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion.toLowerCase() == "Cédula") {
				txtFiltroEstudiante.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion.toLowerCase() == "Apellido") {
					txtFiltroEstudiante.setPlaceholder("Apellido");
					popOpciones.close();
				} else {
					if (seleccion.toLowerCase() == "Programa") {
						txtFiltroEstudiante.setPlaceholder("Programa");
						popOpciones.close();
					}
				}
			}
		}

	}
	/**
	 *Cierra el catalogo
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}
}