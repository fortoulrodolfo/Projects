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
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.servicio.maestros.SMotivo;
/**
 * ViewModel para la vista que muestra el catalogo de Retiro y
 * Exoneracion del  Servicio Comunitario Estudiantil
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoMotivo {
	
	/**
	 * Declaracion de servicios del ViewModel
	 * */
	@WireVariable
	SMotivo smotivo;
	
	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	List<Motivo> listMotivo;
	ListModelList<Motivo> modeloMotivo;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private Motivo selectedMotivo;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	Window win;
	
	/** 
	 *Setter y Getter
	 **/
	public ListModelList<Motivo> getModeloMotivo() {
		return modeloMotivo;
	}
	public void setModeloMotivo(
			ListModelList<Motivo> modeloMotivo) {
		this.modeloMotivo = modeloMotivo;
	}
	public List<Motivo> getListMotivo() {
		return listMotivo;
	}
	public void setListMotivo(
			List<Motivo> listMotivo) {
		this.listMotivo = listMotivo;
	}
	
	public Motivo getSelectedMotivo() {
		return selectedMotivo;
	}
	public void setSelectedMotivo(
			Motivo selectedMotivo) {
		this.selectedMotivo = selectedMotivo;
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
		listMotivo = smotivo.buscarTodo();
		modeloMotivo = new ListModelList<Motivo>(listMotivo);
	}
	
	/**Metodo utilizado para seleccionar un elemento del listbox y llevarlo a la pantalla anterior*/
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Command
		public void aceptar() {
			
			MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
			
			try { 	
				Map args = new HashMap();
				args.put("returnvalue1", this.selectedMotivo.getMotivoCodigo());
				args.put("returnvalue2", this.selectedMotivo.getMotivoNombre());
				args.put("returnvalue3",this.selectedMotivo.getMotivoDescripcion());
				args.put("returnvalue4", this.selectedMotivo.getCausaMotivo().getCausaNombre());
				
			BindUtils.postGlobalCommand(null, null, "obtenerMotivo", args);
			cerrarVentana();
			} catch(NullPointerException e){
				mensajeEmergente.advertenciaSeleccionarOpcion();
				
				}
			}
		

		/**
		 * cierra el catalogo
		 **/
		@Command
		public void cerrarVentana(){
			win.detach();
		}

}
