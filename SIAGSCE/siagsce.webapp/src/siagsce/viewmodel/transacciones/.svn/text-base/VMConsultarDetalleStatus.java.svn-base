package siagsce.viewmodel.transacciones;

import java.util.HashMap;
import java.util.List;

import javassist.compiler.ast.Variable;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.data.transacciones.ActividadAsignada;
import siagsce.modelo.data.transacciones.ActividadEjecutada;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SInscripcionTaller;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.transacciones.SActividadAsignada;
import siagsce.modelo.servicio.transacciones.SActividadEjecutada;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.modelo.servicio.transacciones.SPreInscripcionProyecto;

/**
 * ViewModel para la vista que muestra 
 * el detalle de las actividades asignadas y ejecutadas por el
 * estudiante en un proyecto especifico
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConsultarDetalleStatus {

	/**
	 * Declaracion de servicios del ViewModel
	 * */
	@Wire
	private Window win;
	private @WireVariable
	SInscripcionProyecto sinscripcionProyecto;
	private @WireVariable
	SActividadEjecutada sactividadEjecutada;
	private @WireVariable
	SActividadAsignada sactividadAsignada;
	
	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private List<ActividadAsignada> listaActividades;
	private ListModelList<ActividadAsignada> modeloActividades;
	private List<ActividadEjecutada> listaActividadesEje;
	private ListModelList<ActividadEjecutada> modeloActividadesEje;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private String proyectoNombre;
	private String proyectoDescripcion;
	private InscripcionProyecto selectedInscripcionProyecto;
	private Proyecto selectedProyecto;
	
	/**
	 * Setter y Getter
	 * */
	public String getProyectoNombre() {
		return proyectoNombre;
	}

	public void setProyectoNombre(String proyectoNombre) {
		this.proyectoNombre = proyectoNombre;
	}

	public String getProyectoDescripcion() {
		return proyectoDescripcion;
	}

	public void setProyectoDescripcion(String proyectoDescripcion) {
		this.proyectoDescripcion = proyectoDescripcion;
	}

	public Proyecto getSelectedProyecto() {
		return selectedProyecto;
	}

	public void setSelectedProyecto(Proyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}

	public List<ActividadAsignada> getListaActividades() {
		return listaActividades;
	}

	public void setListaActividades(List<ActividadAsignada> listaActividades) {
		this.listaActividades = listaActividades;
	}

	public ListModelList<ActividadAsignada> getModeloActividades() {
		return modeloActividades;
	}

	public void setModeloActividades(
			ListModelList<ActividadAsignada> modeloActividades) {
		this.modeloActividades = modeloActividades;
	}

	public List<ActividadEjecutada> getListaActividadesEje() {
		return listaActividadesEje;
	}

	public void setListaActividadesEje(
			List<ActividadEjecutada> listaActividadesEje) {
		this.listaActividadesEje = listaActividadesEje;
	}

	public ListModelList<ActividadEjecutada> getModeloActividadesEje() {
		return modeloActividadesEje;
	}

	public void setModeloActividadesEje(
			ListModelList<ActividadEjecutada> modeloActividadesEje) {
		this.modeloActividadesEje = modeloActividadesEje;
	}
	
	/**
	 * Metodo de inicializacion.Carga la lista con actividades asiganadas y ejecutadas.
	 * @param win ventana la cual esta asociada a este viewmodel
	 * @param inscripcion el codigo de la inscripcion del estudiante
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("inscripcion") InscripcionProyecto ip1,
			@ContextParam(ContextType.COMPONENT) Component win) {

		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		this.selectedInscripcionProyecto = ip1;
		this.buscarProyecto();
		this.buscarActividad();
		this.buscarActividadEje();
		
	}
	
	/**
	 * Metodo que busca el proyecto al cual esta asociado la inscripcion.
	 */
	@NotifyChange({"selectedInscripcionProyecto", "selectedProyecto", "proyectoNombre" , "proyectoDescripcion" })
	@Command
	public void buscarProyecto() {
		
		this.selectedProyecto = this.selectedInscripcionProyecto.getProyectoi();
		this.proyectoNombre =this.selectedProyecto.getProyectoNombre();
		this.proyectoDescripcion=this.selectedProyecto.getProyectoDescripcion();
		
	}
	
	/**
	 * Metodo que busca el las actividades asignadas para el estudiante en el proyecto.
	 */
	@NotifyChange({"listaActividades" , "modeloActividades" , "selectedInscripcionProyecto" })
	@Command
	public void buscarActividad() {
		
		//this.listaActividades = sactividadAsignada.buscarPorEstudianteProyecto(this.selectedInscripcionProyecto.getPreInscripcion().getEstudiante(), this.selectedInscripcionProyecto.getPreInscripcion().getProyectop());
		this.listaActividades = sactividadAsignada.buscarPorInscripcionProyecto(selectedInscripcionProyecto);
		this.modeloActividades = new ListModelList<ActividadAsignada>(listaActividades);
		
	}
	
	/**
	 * Metodo que busca las actividades ejecutadas por el estudiante en el proyecto
	 */
	@NotifyChange({"listaActividadesEje" , "modeloActividadesEje" , "selectedInscripcionProyecto" })
	@Command
	public void buscarActividadEje() {
		
		//this.listaActividadesEje = sactividadEjecutada.buscarporEstudianteProyecto(this.selectedInscripcionProyecto.getPreInscripcion().getEstudiante(),this.selectedInscripcionProyecto.getPreInscripcion().getProyectop());
		this.listaActividadesEje = sactividadEjecutada.buscarPorInscripcionProyecto(selectedInscripcionProyecto);
		this.modeloActividadesEje = new ListModelList<ActividadEjecutada>(listaActividadesEje);
		
	}
	
	/**
	 *Cierra la ventana
	 **/
	@Command
	public void cerrarVentana() {
		win.setVisible(false);
	}
}
