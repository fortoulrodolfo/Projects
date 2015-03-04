package siagsce.viewmodel.maestros;


import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.servicio.maestros.SProfesor;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import java.util.Map;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoProf_DirectorPrograma {
	
	
	private Window win;
	private @WireVariable SProfesor sprofesor;
	private List<Profesor> listaProfesor;
	private ListModelList<Profesor> modeloProfesor;
	private Profesor selectedProfesor;
	
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		listaProfesor = sprofesor.buscarTodo();
		modeloProfesor = new ListModelList<Profesor>(listaProfesor);
		
	}
	
	
	public List<Profesor> getListaProfesor() {
		return listaProfesor;
	}


	public void setListaProfesor(List<Profesor> listaProfesor) {
		this.listaProfesor = listaProfesor;
	}


	public Profesor getSelectedProfesor() {
		return selectedProfesor;
	}

	public void setSelectedProfesor(Profesor selectedProfesor) {
		this.selectedProfesor = selectedProfesor;
	}

	public SProfesor getServicioProfesor() {
		return sprofesor;
	}

	public void setServicioProfesor(SProfesor sprofesor) {
		this.sprofesor = sprofesor;
	}

	public ListModelList<Profesor> getModeloProfesor() {
		return modeloProfesor;
	}

	public void setModeloProfesor(ListModelList<Profesor> modeloProfesor) {
		this.modeloProfesor = modeloProfesor;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void save() {
		 	Map args = new HashMap();
	        args.put("returnvalue1", this.selectedProfesor.getProfesorCedula());
	        args.put("returnvalue2", this.selectedProfesor.getProfesorNombre());
	        args.put("returnvalue3", this.selectedProfesor.getProfesorApellido());
	        args.put("returnvalue4", this.selectedProfesor.getProfesorTelefono());
	        BindUtils.postGlobalCommand(null, null, "refreshvalues", args);
	        win.detach();
	    }
	
	@Command
    public void cerrarVentana() {
		  win.detach();
    }
	

}
