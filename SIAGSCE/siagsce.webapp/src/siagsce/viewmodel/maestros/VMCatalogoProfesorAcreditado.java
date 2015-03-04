package siagsce.viewmodel.maestros;

import java.util.ArrayList;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
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

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.general.StatusProfesor;
import siagsce.modelo.servicio.maestros.SProfesor;

/**
 * ViewModel para la vista que muestra el catalogo de Profesores Acreditados
 * para el Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoProfesorAcreditado {

	/**
	 * Declaracion de servicios del ViewModel
	 * */
	@WireVariable
	private SProfesor sprofesor;

	/**
	 * Declaracion de listas y otras estructuras de datos
	 **/
	private ListModelList<Profesor> modeloProfesorAcreditado;
	private List<Profesor> listprofesor;
	private List<String> valores;
	private List<Profesor> selectedProfesor;

	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private String seleccion;
	private String texto;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	private Window win;
	@Wire
	private Textbox txtFiltroProfesorA;
	@Wire
	private Popup popOpciones;
	
	
	/** 
	 *Setter y Getter
	 * */
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
	public List<Profesor> getSelectedProfesor() {
		return selectedProfesor;
	}

	public void setSelectedProfesor(List<Profesor> selectedProfesor) {
		this.selectedProfesor = selectedProfesor;
	}
	public ListModelList<Profesor> getModeloProfesorNoAcreditado() {
		return modeloProfesorAcreditado;
	}

	public void setModeloProfesorNoAcreditado(
			ListModelList<Profesor> modeloProfesorNoAcreditado) {
		this.modeloProfesorAcreditado = modeloProfesorNoAcreditado;
	}

	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		listprofesor = sprofesor.buscarStatus(StatusProfesor.Acreditado
				.toString());

		modeloProfesorAcreditado = new ListModelList<Profesor>(listprofesor);
		modeloProfesorAcreditado.setMultiple(true);

		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		MensajeBox.doSetTemplate();
	}
	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		txtFiltroProfesorA.setPlaceholder("Nombre");
	}

	/**
	 * Inserta en una lista los profesores acreditados seleccionados
	 *  los envia a la vista principal
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void aceptar() {

		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		try {
			List<Profesor> listNoAcreditados = new ArrayList<Profesor>();
			listNoAcreditados.addAll(selectedProfesor);
			win.setAttribute("seleccion", listNoAcreditados);
			salir();
		} catch (NullPointerException e) {
			mensajeEmergente.advertenciaSeleccionarOpcion();
		}
	}

	
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de profesores acreditados con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoProfesorAcreditado}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "profesormodel" })
	public void filtrarProfesor() {
		List<Profesor> aux = new ArrayList<Profesor>();
		aux = listprofesor;
		modeloProfesorAcreditado.clear();

		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modeloProfesorAcreditado.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getProfesorNombre().toLowerCase()
							.contains(texto.toLowerCase())) {
						modeloProfesorAcreditado.add(aux.get(i));

					}
				}
			}
		} else {
			if (seleccion == "Cédula") {
				if (texto == "")
					modeloProfesorAcreditado.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesorCedula().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloProfesorAcreditado.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Nombre") {
					if (texto == "")
						modeloProfesorAcreditado.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProfesorNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloProfesorAcreditado.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Apellido") {
						if (texto == "")
							modeloProfesorAcreditado.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProfesorApellido()
										.toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloProfesorAcreditado.add(aux.get(i));

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
			txtFiltroProfesorA.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroProfesorA.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido")
					txtFiltroProfesorA.setPlaceholder("Apellido");
				popOpciones.close();
			}
		}
	}
	/**
	 * cierra el catalogo
	 * */
	@Command
	public void salir() {
		win.detach();
	}



}
