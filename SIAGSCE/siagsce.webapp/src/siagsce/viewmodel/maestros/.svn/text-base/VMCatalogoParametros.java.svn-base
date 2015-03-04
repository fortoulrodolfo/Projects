package siagsce.viewmodel.maestros;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.data.maestros.Parametro;
import siagsce.modelo.servicio.maestros.SParametro;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoParametros {

	/**
	 * Declaracion de servicios del ViewModel
	 * */
	@WireVariable
	SParametro sparametro;
	
	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	List<Parametro> listParametro;
	ListModelList<Parametro> modeloParametro;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private Parametro selectedParametro;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	Window win;
	
	/** 
	 *Setter y Getter
	 **/

	public Parametro getSelectedParametro() {
		return selectedParametro;
	}

	public List<Parametro> getListParametro() {
		return listParametro;
	}

	public void setListParametro(List<Parametro> listParametro) {
		this.listParametro = listParametro;
	}

	public ListModelList<Parametro> getModeloParametro() {
		return modeloParametro;
	}

	public void setModeloParametro(ListModelList<Parametro> modeloParametro) {
		this.modeloParametro = modeloParametro;
	}

	public void setSelectedParametro(Parametro selectedParametro) {
		this.selectedParametro = selectedParametro;
	}
	
	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * @param view ventana la cual esta asociada a este viewmodel
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		listParametro = sparametro.buscarTodos();
		modeloParametro = new ListModelList<Parametro>(listParametro);
	}
	
	/**Metodo que envia a la pantalla anterior el taller que se seleccione.
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void aceptar() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		// si se selecciona un taller
		if (this.selectedParametro != null) {
			win.setAttribute("parametro", this.selectedParametro);
			win.detach();
		} else {
			//sino se selecciona ningun taller
			mensajeEmergente.advertenciaSeleccionarOpcion();
		}

	}
	
	/** Metodo que cierra la ventana
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}

}
