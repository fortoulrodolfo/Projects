package siagsce.viewmodel.maestros;

import java.util.ArrayList;

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

import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.servicio.maestros.SProyecto;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConsultarProyectosOfertados {
//**************************************************************************
	// **************inicializacion de variables**************
	@WireVariable
	SProyecto sproyecto;
	@Wire
	Window win;
	Proyecto proyecto;
	ListModelList<Proyecto> modeloProyectosOfertados;
	List<Proyecto> listaProyecto;
	private List<String> valores;
	private String texto;
	private String seleccion;
	@Wire
	Textbox txtFiltroProyecto;
	@Wire
	private Popup popOpciones;
	
		
//****************************************************************************

//Metodo para cargar elementos al darle vida a la ventana
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		listaProyecto = sproyecto.buscarPorStatus("Activo");
		modeloProyectosOfertados = new ListModelList<Proyecto>(listaProyecto);
		modeloProyectosOfertados.setMultiple(true);
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

//************************************************************************************
	// **********************Inicio********Getter and setters******

	public ListModelList<Proyecto> getModeloProyectosOfertados() {
		return modeloProyectosOfertados;
	}

	public void setModeloProyectosOfertados(
			ListModelList<Proyecto> modeloProyectosOfertados) {
		this.modeloProyectosOfertados = modeloProyectosOfertados;
	}
	
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public List<Proyecto> getListaProyecto() {
		return listaProyecto;
	}

	public void setListaProyecto(List<Proyecto> listaProyecto) {
		this.listaProyecto = listaProyecto;
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
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de proyectos ,con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * */
	@Command
	@NotifyChange({ "modeloProyectosOfertados" })
	public void filtrarProyecto() {
		List<Proyecto> aux = new ArrayList<Proyecto>();
		aux = listaProyecto;
		modeloProyectosOfertados.clear();
		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modeloProyectosOfertados.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getProyectoNombre().toLowerCase()
							.contains(texto.toLowerCase())) {
						modeloProyectosOfertados.add(aux.get(i));

					}
				}
			}

		} else {
			if (seleccion == "Nombre") {
				if (texto == "")
					modeloProyectosOfertados.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProyectoNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloProyectosOfertados.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Descripción") {
					if (texto == "")
						modeloProyectosOfertados.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProyectoDescripcion().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloProyectosOfertados.add(aux.get(i));

							}
						}
					}
				}
				else{
					if (seleccion == "Programa") {
						if (texto == "")
							modeloProyectosOfertados.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProgramaString().toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloProyectosOfertados.add(aux.get(i));

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

	//***********************Metodo que cierra la ventana************************
	@Command
    public void cerrarVentana() {	
		win.detach();
    }
}
