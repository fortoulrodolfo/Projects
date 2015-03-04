package siagsce.viewmodel.transacciones;

import java.util.ArrayList;
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

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.servicio.maestros.SActividad;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoActividadesDeUnProyecto {

	@WireVariable
	SActividad sactividad;

	@Wire
	Window win;

	List<Actividad> listActividad;
	ListModelList<Actividad> modeloActividad;

	private Actividad selectedActividad;

	public List<Actividad> getListActividad() {
		return listActividad;
	}

	public void setListActividad(List<Actividad> listActividad) {
		this.listActividad = listActividad;
	}

	public ListModelList<Actividad> getModeloActividad() {
		return modeloActividad;
	}

	public void setModeloActividad(ListModelList<Actividad> modeloActividad) {
		this.modeloActividad = modeloActividad;
	}

	public Actividad getSelectedActividad() {
		return selectedActividad;
	}

	public void setSelectedActividad(Actividad selectedActividad) {
		this.selectedActividad = selectedActividad;
	}

	// metodo que inicializa el catalogo con las actividades cargadas
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		listActividad = sactividad.buscarTodo();
		modeloActividad = new ListModelList<Actividad>(listActividad);
	}

	// Metodo utilizado para seleccionar un elemento del listbox y llevarlo a la
	// pantalla anterior
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void aceptar() {
		List<Actividad> listActividad = new ArrayList<Actividad>();
		listActividad.add(selectedActividad);
		win.setAttribute("seleccionado", listActividad);
		win.detach();

	}

}
