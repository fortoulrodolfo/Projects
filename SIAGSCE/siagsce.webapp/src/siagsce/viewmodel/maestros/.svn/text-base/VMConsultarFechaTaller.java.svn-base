/*@VMConsultarFechaTaller
 * Inicialización de Variables e implementación de todas las funcionalidades y métodos
 * de la interfaz <ConsultarFechaTaller.zul>
 * @author Delba López -->
 * @Modificado por: Delba López -->
 * Version 1.0, 26/01/2014 -->
 * Version 1.1, 19/02/2014
 * */

package siagsce.viewmodel.maestros;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.servicio.maestros.STaller;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConsultarFechaTaller {
	
	@WireVariable
	STaller sTaller;
	@Wire
	Window win;
	Taller taller;
	ListModelList<Taller> modeloTaller;
	private List<Taller> listaTaller;
	private Taller selectedTaller;
	
	private List<String> valores;
	private String texto;
	private String seleccion;
	@Wire
	Textbox txtFiltroTaller;
	@Wire
	private Popup popOpciones;


	
	public ListModelList<Taller> getModeloTaller() {
		return modeloTaller;
	}

	public void setModeloTaller(ListModelList<Taller> modeloTaller) {
		this.modeloTaller = modeloTaller;
	}

	public Taller getSelectedTaller() {
		return selectedTaller;
	}

	public void setSelectedTaller(Taller selectedTaller) {
		this.selectedTaller = selectedTaller;
	}
	
	public List<Taller> getListaTaller() {
		return listaTaller;
	}

	public void setListaTaller(List<Taller> listaTaller) {
		this.listaTaller = listaTaller;
	}
	
	public Taller getTaller() {
		return taller;
	}

	public void setTaller(Taller taller) {
		this.taller = taller;
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

	public Textbox getTxtFiltroTaller() {
		return txtFiltroTaller;
	}

	public void setTxtFiltroTaller(Textbox txtFiltroTaller) {
		this.txtFiltroTaller = txtFiltroTaller;
	}

	public Popup getPopOpciones() {
		return popOpciones;
	}

	public void setPopOpciones(Popup popOpciones) {
		this.popOpciones = popOpciones;
	}

	//* Metodo que trae una lista de talleres con fecha de inscripcion mayor a la actual*
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
					@ContextParam(ContextType.COMPONENT) Component win) {
			Selectors.wireComponents(view, this, false);
			this.win = (Window) win;	
			Date date = new Date();
			listaTaller = sTaller.buscarTalleresFechaProximaInscripcion(date);
			modeloTaller = new ListModelList<Taller>(listaTaller);
			valores = new ArrayList<String>();
			valores.add("Modalidad");
			valores.add("Descripción");
			valores.add("Responsable");
		
	}
	
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		txtFiltroTaller.setPlaceholder("Descripción");
	}
	
	
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de talleres ,con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoTaller}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "modeloTaller" })
	public void filtrarTaller() {
		List<Taller> aux = new ArrayList<Taller>();
		aux = listaTaller;
		modeloTaller.clear();
		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modeloTaller.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getTallerNombre().toLowerCase()
							.contains(texto.toLowerCase())) {
						modeloTaller.add(aux.get(i));

					}
				}
			}

		} else {
			if (seleccion == "Descripción") {
				if (texto == "")
					modeloTaller.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getTallerDescripcion().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloTaller.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Modalidad") {
					if (texto == "")
						modeloTaller.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getTallerModalidad().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloTaller.add(aux.get(i));

							}
						}
					}
				}
				else{
					if (seleccion == "Responsable") {
						if (texto == "")
							modeloTaller.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getTallerProfesorResponsable().getProfesorNombre().toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloTaller.add(aux.get(i));

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
		if (seleccion== "Descripción") {
			txtFiltroTaller.setPlaceholder("Descripción");
			popOpciones.close();
		} else {
			if (seleccion== "Modalidad") {
				txtFiltroTaller.setPlaceholder("Modalidad");
				popOpciones.close();
			}
			else{
				if (seleccion== "Responsable") {
					txtFiltroTaller.setPlaceholder("Responsable");
					popOpciones.close();
				}
			}
		}
	}
	
	//*Metodo utilizado para seleccionar un elemento del listbox y llevarlo a la pantalla anterior*
			@Command
			public void aceptar() {
				 	List<Taller> listTaller= new ArrayList<Taller>();
				 	listTaller.add(selectedTaller);	
			        win.setAttribute("seleccionados", listTaller);
			        win.detach();
			    }
			
			
			//Metodo que cierra la ventana
			@Command
		    public void cerrarVentana() {	
				win.detach();
		    }


}
