package siagsce.viewmodel.maestros;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;
import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;

/**
 * ViewModel para la vista q muestra el catalogo de Direcciones 
 * de Programas existentes
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoDireccionPrograma {
	/** 
	 * Declaracion de servicio del ViewModel
	 * */
	@WireVariable
	private SDireccionPrograma sdireccionPrograma;
	
	/** 
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private List<DireccionPrograma> listDireccionPrograma;
	private ListModelList<DireccionPrograma> modeloDireccionPrograma;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private DireccionPrograma selectedDireccionPrograma;
	
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	private Window win;
	
	/**
	 * Setter y Getter
	 * */
	public ListModelList<DireccionPrograma> getModeloDireccionPrograma() {
		return modeloDireccionPrograma;
	}

	public void setModeloDireccionPrograma(
			ListModelList<DireccionPrograma> modeloDireccionPrograma) {
		this.modeloDireccionPrograma = modeloDireccionPrograma;
	}

	public List<DireccionPrograma> getListDireccionPrograma() {
		return listDireccionPrograma;
	}

	public void setListDireccionPrograma(
			List<DireccionPrograma> listDireccionPrograma) {
		this.listDireccionPrograma = listDireccionPrograma;
	}

	public DireccionPrograma getSelectedDireccionPrograma() {
		return selectedDireccionPrograma;
	}

	public void setSelectedDireccionPrograma(
			DireccionPrograma selectedDireccionPrograma) {
		this.selectedDireccionPrograma = selectedDireccionPrograma;
	}
	
	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * @param view componente de tipo vista 
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		listDireccionPrograma = sdireccionPrograma.buscarTodo();
		modeloDireccionPrograma = new ListModelList<DireccionPrograma>(
				listDireccionPrograma);
	}

	/** 
	* Metodo utilizado para seleccionar un elemento del listbox y llevarlo a la
	* pantalla anterior
	**/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void aceptar() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		if (selectedDireccionPrograma != null) {
			List<DireccionPrograma> listdireccion = new ArrayList<DireccionPrograma>();
			listdireccion.add(selectedDireccionPrograma);
			win.setAttribute("seleccionado", listdireccion);
			cerrarVentana();
		} else {
				mensajeEmergente.advertenciaSeleccionarOpcion();
		}
	}

	/**
	 *cierra la ventana  
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}

}
