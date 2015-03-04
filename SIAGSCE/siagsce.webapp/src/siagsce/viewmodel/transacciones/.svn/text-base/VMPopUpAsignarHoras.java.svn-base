package siagsce.viewmodel.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.PopUpAsignarHoras;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)

public class VMPopUpAsignarHoras {
	
	@Wire
	Window win;
	
	String hora;
	Date fecha;
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
	
	}

	// Metodo utilizado para seleccionar un elemento del listbox y llevarlo a la
		// pantalla anterior
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Command
		public void aceptar() {
			List<PopUpAsignarHoras> fechayhora = new ArrayList<PopUpAsignarHoras>();
			PopUpAsignarHoras datos = new PopUpAsignarHoras();
			datos.setHoras(hora);
			datos.setFehca(fecha);
			fechayhora.add(datos);
			win.setAttribute("seleccionado", fechayhora);
			win.detach();

		}

    }
	



