package siagsce.viewmodel.maestros;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.ListadoEstudiantesCulminados;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.data.transacciones.ActividadAsignada;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.ListadoEstudiantesProyectoCulminado;
import siagsce.modelo.data.transacciones.ListadoPreinscripto;
import siagsce.modelo.general.StatusEstudiante;
import siagsce.modelo.general.StatusInscripcionProyecto;
import siagsce.modelo.general.StatusProyecto;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
import siagsce.modelo.servicio.seguridad.ServicioUsuario;
import siagsce.modelo.servicio.transacciones.SActividadAsignada;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.modelo.servicio.transacciones.SListadoEstudiantesCulminado;
import siagsce.modelo.servicio.transacciones.SListadoEstudiantesProyectoCulminado;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import java.util.Map;

/**
 * ViewModel para la vista que muestra los
 * estudiantes con mas de 120 horas del Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMProyectoCulminado {

	/**
	 * Declaracion de servicios del ViewModel
	 * */
	@WireVariable
	private SProyecto sproyecto;
	@WireVariable
	private SActividad sactividad;
	private @WireVariable
	SActividadAsignada sactividadAsignada;
	private @WireVariable
	SInscripcionProyecto sinscripcionProyecto;
	private @WireVariable
	SListadoEstudiantesProyectoCulminado sListadoEstudiantesProyectoCulminado;
	@WireVariable
	ServicioUsuario su;

	@WireVariable
	ServicioGrupo sg;
	@Wire
	private Window win;

	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private List<ListadoEstudiantesProyectoCulminado> listaEstudiante;
	private ListModelList<ListadoEstudiantesProyectoCulminado> modeloEstudiante;
	private Set<ListadoEstudiantesProyectoCulminado> selectedEstudiantes;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	@Wire
	private Popup popOpciones;
	private String texto;
	@Wire
	Textbox txtFiltroEstudiante;
	private String seleccion;
	private List<String> valores;
	
	
	private String nombreProyecto;
	private String descripcionProyecto;
	private String codigoProyecto;
	private String responsables;
	private String programa;

	
	/**
	 * Setter y Getter
	 * */
	public Textbox getTxtFiltroEstudiante() {
		return txtFiltroEstudiante;
	}

	public void setTxtFiltroEstudiante(Textbox txtFiltroEstudiante) {
		this.txtFiltroEstudiante = txtFiltroEstudiante;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}

	public List<String> getValores() {
		return valores;
	}

	public void setValores(List<String> valores) {
		this.valores = valores;
	}

	public List<ListadoEstudiantesProyectoCulminado> getListaEstudiante() {
		return listaEstudiante;
	}

	public void setListaEstudiante(List<ListadoEstudiantesProyectoCulminado> listaEstudiante) {
		this.listaEstudiante = listaEstudiante;
	}


	public Set<ListadoEstudiantesProyectoCulminado> getSelectedEstudiantes() {
		return selectedEstudiantes;
	}

	public void setSelectedEstudiantes(Set<ListadoEstudiantesProyectoCulminado> selectedEstudiantes) {
		this.selectedEstudiantes = selectedEstudiantes;
	}


	public ListModelList<ListadoEstudiantesProyectoCulminado> getModeloEstudiante() {
		return modeloEstudiante;
	}

	public void setModeloEstudiante(ListModelList<ListadoEstudiantesProyectoCulminado> modeloEstudiante) {
		this.modeloEstudiante = modeloEstudiante;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getDescripcionProyecto() {
		return descripcionProyecto;
	}

	public void setDescripcionProyecto(String descripcionProyecto) {
		this.descripcionProyecto = descripcionProyecto;
	}

	public String getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(String codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	public String getResponsables() {
		return responsables;
	}

	public void setResponsables(String responsables) {
		this.responsables = responsables;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	/**
	 * Metodo de inicializacion.Carga la lista con los estudiantes con sus respectivas horas.
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win){
		this.win=(Window)win;
		nombreProyecto = "";
		descripcionProyecto = "";
		codigoProyecto = "";
		responsables = "";
		programa = "";
		MensajeBox.doSetTemplate();
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		valores.add("Actividad");

	}
	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		this.win = (Window) win;
		Selectors.wireComponents(view, this, false);
	}
	
	/**
	 * Se registra todos  los estudiantes seleccionados con un status de SCE 
	 * de Aprobado
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@NotifyChange({ "nombreProyecto", "descripcionProyecto",
		"codigoProyecto", "modeloEstudiante", "responsables",
		"programa", "listaEstudiante" })
	@Command
	public void aceptar(){
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		if (codigoProyecto!=""){
		
			if (Messagebox.show("Esta seguro que desea cerrar este Proyecto?  Las actividades asignadas sin ejecutar se eliminaran. ", "Cerrar Proyecto", 
					Messagebox.YES | Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) { 
			
				for(int i=0; modeloEstudiante.size()>i;i++){
					Integer codigoAct;
					codigoAct = modeloEstudiante.get(i).getCodigo_actividad_asignada();
					ActividadAsignada act = sactividadAsignada.buscarActividadAsignada(codigoAct);
					sactividadAsignada.eliminar(act);
				}
				
				Proyecto pro;
				pro = this.sproyecto.buscarPorCodigo(codigoProyecto);
				for(Profesor prof: pro.getListaprofesoresresponsables()){
					List<Proyecto>proyectos=sproyecto.buscarProyectosdelProfesorResponsable(prof);
					if(proyectos.size()==1){
						Usuario usuario1 = su
								.encontrarUsuarioPorNombreUsuario(prof
										.getProfesorCedula());
						List<Grupo> grupos = usuario1.getGrupos();
						int nroGrupos = grupos.size();
						Grupo grupoRemover=null;
						for (Grupo grupo:grupos) {
							if (grupo.getNombre().equals("Responsable Proyecto"))
								grupoRemover=grupo;							// el grupo 1004 es
							// el profesor
						}		usuario1.getGrupos().remove(grupoRemover);// responsable
						su.guardarUsuario(usuario1);	
					}
					
				}
				pro.getListaprofesoresresponsables().clear();
				pro.setProyectoStatus(StatusProyecto.Inactivo.toString());
				sproyecto.guardar(pro);
				
				List<InscripcionProyecto> inscripciones = sinscripcionProyecto.buscarInscriptos(pro);
				for(InscripcionProyecto ins: inscripciones){
					ins.setInscripcionProyectoStatus(StatusInscripcionProyecto.Inactivo.toString());
					sinscripcionProyecto.guardar(ins);	
				}
				
				List<Actividad>actividades=sactividad.buscarporProyecto(sproyecto.buscarPorCodigo(codigoProyecto));
				for(Actividad acti: actividades){
					for(Profesor profe:acti.getListaprofesoresparticipantes()){
						List<Actividad>listactividades=new ArrayList<Actividad>();
						listactividades=sactividad.participanteOtroProyecto(profe, sproyecto.buscarPorCodigo(codigoProyecto));
						if(listactividades.size()==0){
							System.out.println("por aqui");
							Usuario usuario1 = su
									.encontrarUsuarioPorNombreUsuario(profe
											.getProfesorCedula());
							List<Grupo> grupos = usuario1.getGrupos();
							int nroGrupos = grupos.size();
							Grupo grupoRemover=null;
							for (Grupo grupo:grupos) {
								if (grupo.getNombre().equals("Participante Actividad"))
									grupoRemover=grupo;							// el grupo 1004 es
								// el profesor
							}		usuario1.getGrupos().remove(grupoRemover);// responsable
							su.guardarUsuario(usuario1);	
						}
					}
					acti.getListaprofesoresparticipantes().clear();
					sactividad.guardar(acti);
				}
				
				MensajeBox.doSetTemplate();
				mensajeEmergente.informacionCerrarProyectoExito();
				cancelar();
			}
		
		} else{
			MensajeBox.doSetTemplate();
			mensajeEmergente.advertenciaSeleccionarProyecto();
		}
			
	}
	
	
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de estudiantes ,con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * */
	@Command
	@NotifyChange({ "modeloEstudiante" })
	public void filtrarEstudiante() {
		List<ListadoEstudiantesProyectoCulminado> aux = new ArrayList<ListadoEstudiantesProyectoCulminado>();
		aux = listaEstudiante;
		modeloEstudiante.clear();
		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modeloEstudiante.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getNombre().toLowerCase()
							.contains(texto.toLowerCase())) {
						modeloEstudiante.add(aux.get(i));

					}
				}
			}

		} else {
			if (seleccion == "Nombre") {
				if (texto == "")
					modeloEstudiante.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstudiante.add(aux.get(i));

						}
					}
				}
				
			} 
			else {
				if (seleccion == "Cédula") {
					if (texto == "")
						modeloEstudiante.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getCedula().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloEstudiante.add(aux.get(i));

							}
						}
					}
				}
				else{
					if (seleccion == "Apellido") {
						if (texto == "")
							modeloEstudiante.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getApellido().toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloEstudiante.add(aux.get(i));

								}
							}
						}
				   } 
					else{
						if (seleccion == "Actividad") {
							if (texto == "")
								modeloEstudiante.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getNombre_actividad().toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloEstudiante.add(aux.get(i));

									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Segun la seleccion de un criterio de filtrado
	 * coloca una marca de agua con el nombre de la seleccion
	 * */
	@Command
	public void seleccionFiltro() {
		if (seleccion== "Nombre") {
			txtFiltroEstudiante.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion== "Cédula") {
				txtFiltroEstudiante.setPlaceholder("Cédula");
				popOpciones.close();
			}
			else{
				if (seleccion== "Apellido") {
					txtFiltroEstudiante.setPlaceholder("Apellido");
					popOpciones.close();
				}
				else{
					if (seleccion== "Actividad") {
						txtFiltroEstudiante.setPlaceholder("Actividad");
						popOpciones.close();
					}
				}
			}
		  }
		}
	
	
	
	@NotifyChange({ "nombreProyecto", "descripcionProyecto",
		"codigoProyecto", "modeloEstudiante", "responsables",
		"programa", "listaEstudiante" })
	@Command
	public void mostrarProyecto() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("proyecto", "RegistrarProyecto");
		cancelar();
		Window comp = (Window) Executions.createComponents(
				"/WEB-INF/vista/view/view.maestros/CatalogoProyectos.zul",
				null, map);
		comp.doModal();
		Proyecto proyecto = (Proyecto) comp.getAttribute("proyecto");
		if (proyecto != null)
			obtenerProyecto(proyecto);
			listaEstudiante = sListadoEstudiantesProyectoCulminado.buscarEstudiantesActividadAsignada(this.codigoProyecto);
			modeloEstudiante = new ListModelList<ListadoEstudiantesProyectoCulminado>();
			modeloEstudiante.addAll(listaEstudiante);
	}
	
	/**
	 * Muestra la informacion del proyecto seleccionado en cada uno de los
	 * campos vinculados al proyecto seleccionado
	 * @param proyecto  cuya informacion sera mostrada en los campos
	 * */
	private void obtenerProyecto(Proyecto proyecto) {
		nombreProyecto = proyecto.getProyectoNombre();
		descripcionProyecto = proyecto.getProyectoDescripcion();
		codigoProyecto = proyecto.getProyectoCodigo();
		programa = proyecto.getProgramaString();
		responsables = proyecto.getResponsableString();
		
	}
	
	@NotifyChange({ "nombreProyecto", "descripcionProyecto",
		"codigoProyecto", "modeloEstudiante", "responsables",
		 "programa", "listaEstudiante"  })
	@Command
	public void cancelar() {

		nombreProyecto = "";
		descripcionProyecto = "";
		codigoProyecto = "";
		responsables = "";
		programa = "";
		modeloEstudiante = null;
		listaEstudiante = null;
		
	}
	
	
	
	/**
	 *Cierra la ventana
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}
}