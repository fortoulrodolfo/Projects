package siagsce.viewmodel.maestros;

import java.util.List;

import org.aspectj.lang.annotation.Around;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.ExecutionParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.viewmodel.transacciones.VMActividadEstatus;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMAgregarActividad {
	@WireVariable
	private SActividad sactividad;
	@WireVariable
	private SProyecto sproyecto;
	
	private ListModelList<Profesor> modeloProfesorPIncluir;
	private ListModelList<VMActividadEstatus>modeloActividadMoEstatus;
	
	private String nombreActividadMo;
	private String descripcionActividadMo;
	private String participantesMo;
	private String codigo;
	private Proyecto proyecto;
	private int duracionActividadMo;
	
	@Wire
	Window win;

	
	@Wire
	private Intbox txtDuracionActividadMo;

	
	
	
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public ListModelList<VMActividadEstatus> getModeloActividadMoEstatus() {
		return modeloActividadMoEstatus;
	}
	public void setModeloActividadMoEstatus(
			ListModelList<VMActividadEstatus> modeloActividadMoEstatus) {
		this.modeloActividadMoEstatus = modeloActividadMoEstatus;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombreActividadMo() {
		return nombreActividadMo;
	}
	public void setNombreActividadMo(String nombreActividadMo) {
		this.nombreActividadMo = nombreActividadMo;
	}
	public String getDescripcionActividadMo() {
		return descripcionActividadMo;
	}
	public void setDescripcionActividadMo(String descripcionActividadMo) {
		this.descripcionActividadMo = descripcionActividadMo;
	}
	public String getParticipantesMo() {
		return participantesMo;
	}
	public void setParticipantesMo(String participantesMo) {
		this.participantesMo = participantesMo;
	}
	public int getDuracionActividadMo() {
		return duracionActividadMo;
	}
	public void setDuracionActividadMo(int duracionActividadMo) {
		this.duracionActividadMo = duracionActividadMo;
	}

	@Init
	public void init(
			@ContextParam(ContextType.COMPONENT) Component win,
			@ExecutionArgParam("actividad") ListModelList<VMActividadEstatus> modeloActividadMoEstatusP,
			@ExecutionArgParam("proyecto") Proyecto proyecto) {
		this.win = (Window) win;
		
		this.modeloActividadMoEstatus = modeloActividadMoEstatusP;
		modeloProfesorPIncluir = new ListModelList<Profesor>();
		this.proyecto=proyecto;
	}
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);	
	}
	
	@SuppressWarnings("unchecked")
	@Command
	@NotifyChange({ "participantesMo" })
	public void mostrarCatalogoProfAcreditadosPIncluir() {
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoProfesoresAcreditados.zul",
						null, null);
		comp.doModal();
		List<Profesor> listAcreditados = (List<Profesor>) comp
				.getAttribute("seleccion");

		modeloProfesorPIncluir.clear();
		if(listAcreditados!=null){
			modeloProfesorPIncluir.addAll(listAcreditados);
			getParticipantesIncluirString(modeloProfesorPIncluir);
		} 

	}
	/**
	 * Blanquea los campos de Actividad Nueva a Agregar
	 */

	@NotifyChange({ "nombreActividadMo", " duracionActividadMo",
			"participantesMo", "descripcionActividadMo" })
	@Command
	public void cancelarIncluirActividad() {
		nombreActividadMo = "";
		participantesMo = "";
		descripcionActividadMo = "";
		txtDuracionActividadMo.setValue(0);

	}

	/**
	 * Concatena nombre y apellido de todos los profesores responsables de un
	 * proyecto a Modificar para ser visualizados dentro de un campo de texto
	 * @param lista de profesor responsables que se concatenara
	 * */
	@NotifyChange({ "participantesMo" })
	public void getParticipantesIncluirString(ListModelList<Profesor> aux) {
		String cadena = "";
		if (aux.isEmpty()) {
			System.out.print("esta vacia");
			participantesMo = "";
		}

		else {
			cadena = aux.get(0).getProfesorNombre() + " "
					+ aux.get(0).getProfesorApellido();
			for (int i = 1; i < aux.size(); i++) {
				cadena += "," + aux.get(i).getProfesorNombre() + " "
						+ aux.get(i).getProfesorApellido();
			}
		}
		participantesMo = cadena;
	}

	@Command
	public void aceptar(){
		MensajesEmergentes mensaje=new MensajesEmergentes();
		if(nombreActividadMo != "" && duracionActividadMo != 0
				&& participantesMo != "" && descripcionActividadMo != ""
				&& !(modeloProfesorPIncluir.isEmpty())){
				System.out.println(proyecto.getProyectoCodigo());
			
			if(actividadExistenteMo(nombreActividadMo)==true){
				mensaje.advertenciaActividadExistente();
			}else{
				Actividad actividad = new Actividad();
				actividad.setActividadNombre(nombreActividadMo);
				actividad.setActividadDescripcion(descripcionActividadMo);
				actividad.setActividadCantidadHoras(duracionActividadMo);
				actividad.setListaprofesoresparticipantes(modeloProfesorPIncluir);
				actividad.setProyectoa(proyecto);
				win.setAttribute("actividad", actividad);
				salir();
			}
		}else{
			mensaje.advertenciaLlenarCampos();
		}	
	}
	/**
	 * Verifica si un Actividad ha sido duplicada
	 * @param nombre de la actividad a verificar
	 * @return verdadero en caso de conseguir uno existente
	 * 			falso en caso contrario
	 * */
	public boolean actividadExistenteMo(String nombre){
		boolean valor=false;
		for(VMActividadEstatus act:modeloActividadMoEstatus){
			if(act.getActividad().getActividadNombre().contains(nombre)){
				valor=true;
				break;
			}
			
		}
		return valor;
	}
	@Command
	public void salir(){
		win.detach();
	}
}
