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
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;
import siagsce.modelo.general.StatusInscripcionProyecto;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SInscripcionTaller;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.modelo.servicio.transacciones.SPreInscripcionProyecto;

/**
 * ViewModel para la vista que muestra 
 * el historial del estudiante durante su prestacion de SCE
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConsultarStatusEstudiante {

	/**
	 * Declaracion de servicios del ViewModel
	 * */
	@Wire
	private Window win;
	private @WireVariable
	SProyecto sproyecto;
	private @WireVariable
	SEstudiante sestudiante;
	private @WireVariable
	SInscripcionTaller sinscripciontaller;
	private @WireVariable
	SPreInscripcionProyecto spreinscripcionproyecto;
	private @WireVariable
	SInscripcionProyecto sinscripcionProyecto;
	
	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private List<InscripcionTaller> listaTaller;
	private ListModelList<InscripcionTaller> modeloTaller;
	private List<PreInscripcionProyecto> listaPreInscripcion;
	private ListModelList<PreInscripcionProyecto> modeloPreInscripcion;
	private List<InscripcionProyecto> listaProyecto;
	private ListModelList<InscripcionProyecto> modeloProyecto;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private String estudianteCedula;
	private String estudianteNombre;
	private String estudianteApellido;
	private String estudiantePrograma;
	private String estudianteStatus;
	private Long estudianteHorasAcum;
	private InscripcionProyecto selectedProyecto;
	private Estudiante selectedEstudiante;
	private String nombreTaller;
	
	/**
	 * Setter y Getter
	 * */
	
	public String getEstudianteCedula() {
		return estudianteCedula;
	}


	public void setEstudianteCedula(String estudianteCedula) {
		this.estudianteCedula = estudianteCedula;
	}


	public String getEstudianteNombre() {
		return estudianteNombre;
	}


	public void setEstudianteNombre(String estudianteNombre) {
		this.estudianteNombre = estudianteNombre;
	}


	public String getEstudianteApellido() {
		return estudianteApellido;
	}

	public void setEstudianteApellido(String estudianteApellido) {
		this.estudianteApellido = estudianteApellido;
	}


	public String getEstudiantePrograma() {
		return estudiantePrograma;
	}

	
	public String getEstudianteStatus() {
		return estudianteStatus;
	}

	public void setEstudianteStatus(String estudianteStatus) {
		this.estudianteStatus = estudianteStatus;
	}

	public void setEstudiantePrograma(String estudiantePrograma) {
		this.estudiantePrograma = estudiantePrograma;
	}


	public Long getEstudianteHorasAcum() {
		return estudianteHorasAcum;
	}


	public void setEstudianteHorasAcum(Long estudianteHorasAcum) {
		this.estudianteHorasAcum = estudianteHorasAcum;
	}


	public List<InscripcionTaller> getListaTaller() {
		return listaTaller;
	}

	public void setListaTaller(List<InscripcionTaller> listaTaller) {
		this.listaTaller = listaTaller;
	}

	public ListModelList<InscripcionTaller> getModeloTaller() {
		return modeloTaller;
	}

	public void setModeloTaller(ListModelList<InscripcionTaller> modeloTaller) {
		this.modeloTaller = modeloTaller;
	}

	public InscripcionProyecto getSelectedProyecto() {
		return selectedProyecto;
	}
	
	public Estudiante getSelectedEstudiante() {
		return selectedEstudiante;
	}

	public void setSelectedEstudiante(Estudiante selectedEstudiante) {
		this.selectedEstudiante = selectedEstudiante;
	}

	public void setSelectedProyecto(InscripcionProyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}

	
	public String getNombreTaller() {
		return nombreTaller;
	}


	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}


	public List<PreInscripcionProyecto> getListaPreInscripcion() {
		return listaPreInscripcion;
	}


	public void setListaPreInscripcion(
			List<PreInscripcionProyecto> listaPreInscripcion) {
		this.listaPreInscripcion = listaPreInscripcion;
	}


	public ListModelList<PreInscripcionProyecto> getModeloPreInscripcion() {
		return modeloPreInscripcion;
	}


	public void setModeloPreInscripcion(
			ListModelList<PreInscripcionProyecto> modeloPreInscripcion) {
		this.modeloPreInscripcion = modeloPreInscripcion;
	}


	public List<InscripcionProyecto> getListaProyecto() {
		return listaProyecto;
	}


	public void setListaProyecto(List<InscripcionProyecto> listaProyecto) {
		this.listaProyecto = listaProyecto;
	}


	public ListModelList<InscripcionProyecto> getModeloProyecto() {
		return modeloProyecto;
	}


	public void setModeloProyecto(ListModelList<InscripcionProyecto> modeloProyecto) {
		this.modeloProyecto = modeloProyecto;
	}

	/**
	 * Metodo de inicializacion.Carga el historial del estudiante.
	 * @param win ventana la cual esta asociada a este viewmodel
	 * @param estudiante la cedula del estudiante
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("estudiante") String e1,
			@ContextParam(ContextType.COMPONENT) Component win) {

		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		this.estudianteCedula=e1;
		this.buscarEstudiante();
		this.buscarTaller();
		this.buscarPreInscripcion();
		this.buscarProyecto();
	}
	
	/**
	 * Metodo para buscar el estudiante asociado a la cedula
	 */
	@NotifyChange({"estudianteCedula", "estudianteNombre", "estudianteApellido" , "estudiantePrograma" , "estudianteStatus" , "modeloTaller" , "modeloPreInscripcion" , "estudianteHorasAcum" , "nombreTaller" , "nombrePreInscripcion" })
	@Command
	public void buscarEstudiante() {
		
		this.selectedEstudiante = sestudiante.buscarPorCedula(this.estudianteCedula);
		if(selectedEstudiante != null){
		this.estudianteCedula=selectedEstudiante.getEstudianteCedula();
		this.estudianteNombre=selectedEstudiante.getEstudianteNombre();
		this.estudianteApellido=selectedEstudiante.getEstudianteApellido();
		this.estudianteStatus=selectedEstudiante.getEstudianteStatus();
		DireccionPrograma direccionProg = selectedEstudiante.getDireccionProgramae();
		this.estudiantePrograma=direccionProg.getDireccionNombre();
		Long h1 = 0L , h2 = 0L;
		this.estudianteHorasAcum = sestudiante.buscarHorasAcumuladas(selectedEstudiante);
		if (this.estudianteHorasAcum == null){
			h1 = 0L;
		}
		else {
			h1 = this.estudianteHorasAcum;
		}
		
		this.estudianteHorasAcum = sestudiante.buscarHorasAcumuladasRetirado(selectedEstudiante);
		if (this.estudianteHorasAcum == null){
			h2 = 0L;
		}
		else {
			h2 = this.estudianteHorasAcum;
		}
		
		this.estudianteHorasAcum = h1-h2;
		
		}
	}
	
	/**
	 * Metodo para buscar inscripciones a talleres
	 * asociados al estudiante
	 */
	@NotifyChange({"modeloTaller" , "listaTaller" , "selectedEstudiante" })
	@Command
	public void buscarTaller() {
		
		this.listaTaller = sinscripciontaller.buscarPorEstudiante(selectedEstudiante);
		this.modeloTaller = new ListModelList<InscripcionTaller>(listaTaller);
		
	}
	
	/**
	 * Metodo para buscar preinscripciones a proyectos
	 * asociadas a estudiantes
	 */
	@NotifyChange({"modeloPreInscripcion" , "listaPreInscripcion" , "selectedEstudiante" })
	@Command
	public void buscarPreInscripcion() {
		
		this.listaPreInscripcion = spreinscripcionproyecto.buscarPorEstudiante(selectedEstudiante);
		this.modeloPreInscripcion = new ListModelList<PreInscripcionProyecto>(listaPreInscripcion);
		
		ListModelList<PreInscripcionProyecto> modeloPreInscripcion2 = new ListModelList<PreInscripcionProyecto>(listaPreInscripcion);
		String var = "Usted se preinscribio en el proyecto: ";
		
		for(int i = 0; this.modeloPreInscripcion.size()>i ; i++){
			var +=modeloPreInscripcion2.get(i).getProyectop().getProyectoNombre();
			Proyecto proyectoNom = new Proyecto();
			proyectoNom.setProyectoNombre(var);
			this.modeloPreInscripcion.get(i).setProyectop(proyectoNom);
			proyectoNom=null;
			var = "Usted se preinscribio en el proyecto: ";
			
		}
		modeloPreInscripcion2=null;
	}
	
	/**
	 * Metodo para buscar inscripciones a proyectos
	 * asociado a estudiantes
	 */
	@NotifyChange({"modeloProyecto" , "listaProyecto" , "selectedEstudiante" })
	@Command
	public void buscarProyecto() {
		
		this.listaProyecto = sinscripcionProyecto.buscarPorEstudiante(selectedEstudiante);
		this.modeloProyecto = new ListModelList<InscripcionProyecto>(listaProyecto);
		
		ListModelList<InscripcionProyecto> modeloProyecto2 = new ListModelList<InscripcionProyecto>(listaProyecto);
		String var = "Usted cumple SCE en el proyecto: ";
		
		for(int i = 0; this.modeloProyecto.size()>i ; i++){
			var +=modeloProyecto2.get(i).getProyectoi().getProyectoNombre();
			Proyecto proyectoNom = new Proyecto();
			proyectoNom.setProyectoNombre(var);
			this.modeloProyecto.get(i).setProyectoi(proyectoNom);
			proyectoNom=null;
			var = "Usted cumple SCE en el proyecto: ";
			
		}
		modeloProyecto2=null;
	}
	
	/**
	 * Metodo para mostrar la ventana con el detalle
	 * de las actividades asignadas y ejecutadas en ese proyecto
	 * especifico
	 */
	@NotifyChange({"selectedProyecto" })
	@Command
	public void mostrarActividadesProyecto(@ContextParam(ContextType.COMPONENT) Button componente) {
		
        Integer codigo_ins = (Integer) componente.getAttribute("codigo");
		final HashMap<String, Object> map = new HashMap<String, Object>();
		this.selectedProyecto=sinscripcionProyecto.buscarPorCodigo(codigo_ins);
        map.put("inscripcion", this.selectedProyecto);
		Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.transacciones/ConsultarDetalleStatus.zul", null, map);
		comp.doModal();
	}
	
	/**
	 *Cierra la ventana
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}
}
