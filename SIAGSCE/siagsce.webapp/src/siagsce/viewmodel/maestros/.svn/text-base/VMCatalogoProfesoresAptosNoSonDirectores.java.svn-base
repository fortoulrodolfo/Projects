package siagsce.viewmodel.maestros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.general.StatusProfesor;
import siagsce.modelo.servicio.maestros.SProfesor;

/**
 * ViewModel para la vista que muestra el catalogo de Profesores Aptos
 * para ser Director de Programas del DCYT
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoProfesoresAptosNoSonDirectores {
	
	/**
	 * Declaracion de servicio del ViewModel
	 * */
	@WireVariable
	SProfesor sprofesor;
	
	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	ListModelList<Profesor> modeloProfesorNoAcreditado;
	List<Profesor> listprofesor;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private Profesor selectedProfesor;
	private String texto;
	private String seleccion;
	private List<String> valores;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	Window win;
	@Wire
	Textbox txtFiltroProfesor;
	@Wire
	Listbox lbxSeleccionParametro;
	@Wire 
	Popup popOpciones;
	
	/** 
	 *Setter y Getter
	 **/
	public SProfesor getSprofesor() {
		return sprofesor;
	}


	public void setSprofesor(SProfesor sprofesor) {
		this.sprofesor = sprofesor;
	}


	public Textbox getTxtFiltroProfesor() {
		return txtFiltroProfesor;
	}


	public void setTxtFiltroProfesor(Textbox txtFiltroProfesor) {
		this.txtFiltroProfesor = txtFiltroProfesor;
	}


	public Listbox getLbxSeleccionParametro() {
		return lbxSeleccionParametro;
	}


	public void setLbxSeleccionParametro(Listbox lbxSeleccionParametro) {
		this.lbxSeleccionParametro = lbxSeleccionParametro;
	}


	public Popup getPopOpciones() {
		return popOpciones;
	}


	public void setPopOpciones(Popup popOpciones) {
		this.popOpciones = popOpciones;
	}


	public ListModelList<Profesor> getModeloProfesorNoAcreditado() {
		return modeloProfesorNoAcreditado;
	}


	public void setModeloProfesorNoAcreditado(
			ListModelList<Profesor> modeloProfesorNoAcreditado) {
		this.modeloProfesorNoAcreditado = modeloProfesorNoAcreditado;
	}


	public List<Profesor> getListprofesor() {
		return listprofesor;
	}


	public void setListprofesor(List<Profesor> listprofesor) {
		this.listprofesor = listprofesor;
	}


	public Profesor getSelectedProfesor() {
		return selectedProfesor;
	}


	public void setSelectedProfesor(Profesor selectedProfesor) {
		this.selectedProfesor = selectedProfesor;
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
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		listprofesor = sprofesor.buscarTodosAptosNoSonDirectores();
				
		modeloProfesorNoAcreditado = new ListModelList<Profesor>(listprofesor);
//		modeloProfesorNoAcreditado.setMultiple(true);
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
	public void afterCompose(@ContextParam(ContextType.VIEW) Component win){
		
		Selectors.wireComponents(win, this, false);
	}

	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de profesores Aptos con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoProfesoresAptosNoSonDirectores}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "modeloProfesorNoAcreditado" })
	public void filtrarProfesor() {
		List<Profesor> aux = new ArrayList<Profesor>();
		aux = listprofesor;
		modeloProfesorNoAcreditado.clear();

		if (seleccion == null || seleccion == "") {
				if (texto == "")
					modeloProfesorNoAcreditado.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesorNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloProfesorNoAcreditado.add(aux.get(i));

						}
					}
				}
			
		} else {
			if (seleccion == "Cédula") {
				if (texto == "")
					modeloProfesorNoAcreditado.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesorCedula().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloProfesorNoAcreditado.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion== "Nombre") {
					if (texto == "")
						modeloProfesorNoAcreditado.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProfesorNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloProfesorNoAcreditado.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion== "Apellido") {
						if (texto == "")
							modeloProfesorNoAcreditado.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProfesorApellido()
										.toLowerCase().contains(texto.toLowerCase())) {
									modeloProfesorNoAcreditado.add(aux.get(i));

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
			txtFiltroProfesor.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroProfesor.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion== "apellido") {
					txtFiltroProfesor.setPlaceholder("Apellido");
					popOpciones.close();
				} 
			}
		}

	}
	/**
	 * Inserta en una lista los profesores aptos seleccionados y
	 *  los envia a la vista principal
	 * */
	 @SuppressWarnings({ "unchecked", "rawtypes" })
		@Command
		public void aceptar(){
		 
		  MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		                  if (selectedProfesor != null){
		                   List<Profesor> listaProfesor = new ArrayList<Profesor>();
		                   listaProfesor.add(selectedProfesor);
		                   win.setAttribute("seleccionado",listaProfesor);
		                   win.detach();
		                  }
		                  else{
		                          mensajeEmergente.advertenciaSeleccionarOpcion();
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
