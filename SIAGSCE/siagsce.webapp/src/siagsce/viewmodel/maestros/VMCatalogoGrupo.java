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
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.servicio.maestros.SMotivo;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
/**
 * VMCatalogoGrupo es el viewmodel encargado de gestionar
 *  el catalogo que muestran todos los grupos que han sido registrados.
 *  @author SIAGSCE
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoGrupo {
	
	
	@WireVariable ServicioGrupo  sg;
	@Wire
	Window win;
	List<Grupo> listGrupo;
	ListModelList<Grupo> modeloGrupo;
	private Grupo selectedGrupo;
	MensajesEmergentes mensajes= new MensajesEmergentes();
	
	
	/**
	 * Carga la lista de grupos,model encargado de gestionar
	 * se ejecuto el metodo que permite la coneccion del viewmodel con la vista.
	 * @param win es la ventana con la cual esta enlazada el viewmodel.
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		listGrupo = sg.buscarTodos();
		modeloGrupo = new ListModelList<Grupo>(listGrupo);
	}
	/**
	 * Una vez que se selecciona el grupo del catalogo,
	 *  guarda el grupo seleccionado como un atributo de la ventana.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void aceptar() {
		if (selectedGrupo != null) {
			win.setAttribute("grupo", selectedGrupo);
			cerrarVentana();
		} else {
			mensajes.advertenciaSeleccionarOpcion();
			}
	}
	/**
	 * Cierra la ventana.
	 */
	@Command
	public void cerrarVentana() {
		win.detach();
	}
	/**
	 * A continuacion se declaran todos los getter y setter de las variables
	 * para poder ser accedidas desde la vista(CatalogoGrupo.zul).
	 */
	public List<Grupo> getListGrupo() {
		return listGrupo;
	}

	public void setListGrupo(List<Grupo> listGrupo) {
		this.listGrupo = listGrupo;
	}

	public ListModelList<Grupo> getModeloGrupo() {
		return modeloGrupo;
	}

	public void setModeloGrupo(ListModelList<Grupo> modeloGrupo) {
		this.modeloGrupo = modeloGrupo;
	}

	public Grupo getSelectedGrupo() {
		return selectedGrupo;
	}

	public void setSelectedGrupo(Grupo selectedGrupo) {
		this.selectedGrupo = selectedGrupo;
	}

}
