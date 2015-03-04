package siagsce.viewmodel.maestros;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import siagsce.herramientas.*;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;
import siagsce.modelo.general.EnviarCorreo;
import siagsce.modelo.general.StatusProyecto;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
import siagsce.modelo.servicio.seguridad.ServicioUsuario;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.modelo.servicio.transacciones.SPreInscripcionProyecto;
import siagsce.viewmodel.transacciones.VMActividadEstatus;
import siagsce.viewmodel.transacciones.VMAsignarActividades;

/**
 * ViewModel para la vista de registro y modificacion de un Proyecto del
 * Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarProyecto {

	/** 
	 * Declaracion de servicios del ViewModel
	 * */
	@WireVariable
	private SProyecto sproyecto;
	@WireVariable
	private SActividad sactividad;
	@WireVariable
	private SProfesor sProfesor;
	@WireVariable
	private SPreInscripcionProyecto spreinscripcionproyecto;
	@WireVariable
	private SDireccionPrograma sdireccionPrograma;

	@WireVariable
	ServicioUsuario su;

	@WireVariable
	ServicioGrupo sg;
	/** 
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private ListModelList<Profesor> modeloProfesorP;
	private ListModelList<Profesor> modeloProfesorPIncluir;
	private ListModelList<Profesor> modeloProfesorRMo;
	private ListModelList<Profesor> modeloProfesorPMo;
	private ListModelList<Profesor> modeloProfesorR;
	private ListModelList<DireccionPrograma> modeloPrograma;
	private ListModelList<DireccionPrograma> modeloProgramaMo;
	private ListModelList<DireccionPrograma> modeloProgramaAsig;
	private ListModelList<Actividad> modeloActividad;
	private ListModelList<Actividad> modeloActividadAg;
	private ListModelList<Actividad> modeloActividadMo;
	private ListModelList<VMActividadEstatus> modeloActividadMoEstatus;
	private ListModelList<VMActividadEstatus> modeloActividadMoEstatusEliminar;
	private List<Profesor>listParticipantesExistentes;
	private List<Profesor>listResponsableExistentes;

	/** 
	 * Declaracion de Variables del ViewModel
	 * */
	private DireccionPrograma selectedPrograma;
	private String nombreProyecto;
	private String descripcionProyecto;
	private String codigoProyecto;
	private String responsables;
	private String nombreProyectoMo;
	private String descripcionProyectoMo;
	private String codigoProyectoMo;
	private String responsablesMo;
	private String nombreActividad;
	private String descripcionActividad;
	private String participantes;
	private String nombreActividadMo;
	private String descripcionActividadMo;
	private String participantesMo;
	private String programa;
	private int duracionActividad;
	private int duracionActividadMo;
	private boolean opcion = false;
	private boolean ver = false;
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	private Window win;
	@Wire
	private Combobox cmbPrograma;
	@Wire
	private Textbox txtCodigoRegistrarProyectoMo;
	@Wire
	private Textbox txtNombreRegistrarProyectoMo;
	@Wire
	private Textbox txtDescripcionRegistrarProyectoMo;
	@Wire
	private Textbox txtResponsableRegistrarProyectoMo;
	@Wire
	private Textbox txtCodigoRegistrarProgramaMo;
	@Wire
	private Intbox txtDuracionActividadMo;
	@Wire
	private Button btnCatalogoResponsableMo;
	@Wire
	private Button btnEditarProyecto;
	@Wire
	private Button btnGuardarRegistrarProyectoMo;
	@Wire
	private Button btnCatalogoProgramaMo;
	@Wire
	private Button btnIncluirActividad;

	/**
	 * Setter y Getter
	 **/

	public String getNombreActividadMo() {
		return nombreActividadMo;
	}

	public List<Profesor> getListResponsableExistentes() {
		return listResponsableExistentes;
	}

	public void setListResponsableExistentes(
			List<Profesor> listResponsableExistentes) {
		this.listResponsableExistentes = listResponsableExistentes;
	}

	public List<Profesor> getListParticipantesExistentes() {
		return listParticipantesExistentes;
	}

	public void setListParticipantesExistentes(
			List<Profesor> listParticipantesExistentes) {
		this.listParticipantesExistentes = listParticipantesExistentes;
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

	public boolean isVer() {
		return ver;
	}

	public void setVer(boolean ver) {
		this.ver = ver;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public ListModelList<DireccionPrograma> getModeloProgramaMo() {
		return modeloProgramaMo;
	}

	public void setModeloProgramaMo(
			ListModelList<DireccionPrograma> modeloProgramaMo) {
		this.modeloProgramaMo = modeloProgramaMo;
	}

	public ListModelList<Profesor> getModeloProfesorRMo() {
		return modeloProfesorRMo;
	}

	public void setModeloProfesorRMo(ListModelList<Profesor> modeloProfesorRMo) {
		this.modeloProfesorRMo = modeloProfesorRMo;
	}

	public ListModelList<Profesor> getModeloProfesorPMo() {
		return modeloProfesorPMo;
	}

	public void setModeloProfesorPMo(ListModelList<Profesor> modeloProfesorPMo) {
		this.modeloProfesorPMo = modeloProfesorPMo;
	}

	public ListModelList<VMActividadEstatus> getModeloActividadMoEstatus() {
		return modeloActividadMoEstatus;
	}

	public void setModeloActividadMoEstatus(
			ListModelList<VMActividadEstatus> modeloActividadMoEstatus) {
		this.modeloActividadMoEstatus = modeloActividadMoEstatus;
	}

	public boolean isOpcion() {
		return opcion;
	}

	@NotifyChange({ "VMActividadEstatus", "opcion" })
	public void setOpcion(boolean opcion) {
		this.opcion = opcion;
	}

	public ListModelList<Actividad> getModeloActividadMo() {
		return modeloActividadMo;
	}

	public void setModeloActividadMo(ListModelList<Actividad> modeloActividadMo) {
		this.modeloActividadMo = modeloActividadMo;
	}

	public String getNombreProyectoMo() {
		return nombreProyectoMo;
	}

	public void setNombreProyectoMo(String nombreProyectoMo) {
		this.nombreProyectoMo = nombreProyectoMo;
	}

	public String getDescripcionProyectoMo() {
		return descripcionProyectoMo;
	}

	public void setDescripcionProyectoMo(String descripcionProyectoMo) {
		this.descripcionProyectoMo = descripcionProyectoMo;
	}

	public String getCodigoProyectoMo() {
		return codigoProyectoMo;
	}

	public void setCodigoProyectoMo(String codigoProyectoMo) {
		this.codigoProyectoMo = codigoProyectoMo;
	}

	public String getResponsablesMo() {
		return responsablesMo;
	}

	public void setResponsablesMo(String responsablesMo) {
		this.responsablesMo = responsablesMo;
	}

	public String getResponsables() {
		return responsables;
	}

	public void setResponsables(String responsables) {
		this.responsables = responsables;
	}

	public String getParticipantes() {
		return participantes;
	}

	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}

	public DireccionPrograma getSelectedPrograma() {
		return selectedPrograma;
	}

	public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
		this.selectedPrograma = selectedPrograma;
	}

	public ListModelList<Actividad> getModeloActividadAg() {
		return modeloActividadAg;
	}

	public void setModeloActividadAg(ListModelList<Actividad> modeloActividadAg) {
		this.modeloActividadAg = modeloActividadAg;
	}

	public ListModelList<Profesor> getModeloProfesorP() {
		return modeloProfesorP;
	}

	public void setModeloProfesorP(ListModelList<Profesor> modeloProfesorP) {
		this.modeloProfesorP = modeloProfesorP;
	}

	public ListModelList<DireccionPrograma> getModeloProgramaAsig() {
		return modeloProgramaAsig;
	}

	public void setModeloProgramaAsig(
			ListModelList<DireccionPrograma> modeloProgramaAsig) {
		this.modeloProgramaAsig = modeloProgramaAsig;
	}

	public ListModelList<Actividad> getModeloActividad() {
		return modeloActividad;
	}

	public void setModeloActividad(ListModelList<Actividad> modeloActividad) {
		this.modeloActividad = modeloActividad;
	}

	public ListModelList<Profesor> getModeloProfesorR() {
		return modeloProfesorR;
	}

	public void setModeloProfesorR(ListModelList<Profesor> modeloProfesorR) {
		this.modeloProfesorR = modeloProfesorR;
	}

	public ListModelList<DireccionPrograma> getModeloPrograma() {
		return modeloPrograma;
	}

	public void setModeloPrograma(
			ListModelList<DireccionPrograma> modeloPrograma) {
		this.modeloPrograma = modeloPrograma;
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

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public int getDuracionActividad() {
		return duracionActividad;
	}

	public void setDuracionActividad(int duracionActividad) {
		this.duracionActividad = duracionActividad;
	}

	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */

	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		List<DireccionPrograma> listprograma = sdireccionPrograma.buscarTodo();
		modeloProfesorP = new ListModelList<Profesor>();
		listParticipantesExistentes=new ArrayList<Profesor>();
		listResponsableExistentes=new ArrayList<Profesor>();
		modeloProgramaAsig = new ListModelList<DireccionPrograma>();
		modeloPrograma = new ListModelList<DireccionPrograma>(listprograma);
		modeloProfesorR = new ListModelList<Profesor>();
		modeloProfesorRMo = new ListModelList<Profesor>();
		modeloProfesorPMo = new ListModelList<Profesor>();
		modeloProfesorPIncluir = new ListModelList<Profesor>();
		modeloActividadAg = new ListModelList<Actividad>();
		modeloActividad = new ListModelList<Actividad>();
		modeloActividadMo = new ListModelList<Actividad>();
		modeloProgramaMo = new ListModelList<DireccionPrograma>();
		modeloActividadMoEstatus = new ListModelList<VMActividadEstatus>();
		modeloActividadMoEstatusEliminar = new ListModelList<VMActividadEstatus>();
		MensajeBox.doSetTemplate();
	}

	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */

	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		cmbPrograma.setVisible(true);
		txtCodigoRegistrarProyectoMo.setReadonly(true);
		txtNombreRegistrarProyectoMo.setReadonly(true);
		txtDescripcionRegistrarProyectoMo.setReadonly(true);
		txtResponsableRegistrarProyectoMo.setReadonly(true);
		txtCodigoRegistrarProgramaMo.setReadonly(true);
		btnCatalogoResponsableMo.setVisible(false);
		btnCatalogoProgramaMo.setVisible(false);
		btnEditarProyecto.setDisabled(true);
		btnGuardarRegistrarProyectoMo.setDisabled(true);
		btnIncluirActividad.setVisible(false);
		cmbPrograma.setPlaceholder("Seleccione");
	}

	/**
	 * Muestra el catalogo de aquellos profesores acreditados que pueden ser
	 * participantes en una actividad y los inserta en el modelo lista de
	 * profesores participantes para ser contatenados como un texto en el campo
	 * "Participantes"
	 * @link {@link VMRegistrarProyecto}{@link #getParticipantesString(ListModelList)}
	 */
	@SuppressWarnings("unchecked")
	@Command
	@NotifyChange({ "participantes" })
	public void mostrarCatalogoProfAcreditadosP() {
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoProfesoresAcreditados.zul",
						null, null);
		comp.doModal();
		List<Profesor> listAcreditados = (List<Profesor>) comp
				.getAttribute("seleccion");

		modeloProfesorP.clear();
		if(listAcreditados!=null) {
			modeloProfesorP.addAll(listAcreditados);
			getParticipantesString(modeloProfesorP);
		} 

	}

	/**
	 * Muestra el catalogo de aquellos profesores acreditados que pueden ser
	 * responsables en un proyecto y los inserta en el modelo lista de
	 * profesores responsables para ser contatenados como un texto en el campo
	 * "responsables"
	 * @link {@link VMRegistrarProyecto}{@link #getResponsablesString(ListModelList)}
	 */
	@SuppressWarnings("unchecked")
	@Command
	@NotifyChange({ "responsables" })
	public void mostrarCatalogoProfAcreditadosR() {
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoProfesoresAcreditados.zul",
						null, null);
		comp.doModal();
		List<Profesor> listAcreditados = (List<Profesor>) comp
				.getAttribute("seleccion");

		modeloProfesorR.clear();
		if(listAcreditados != null) {
			modeloProfesorR.addAll(listAcreditados);
			getResponsablesString(modeloProfesorR);
		}

	}

	/**
	 * Construye un objeto con lo tipeo en los campos asociados a una actividad
	 * y lo inserta en modelo lista de Actividades de un proyecto
	 */
	@Command
	@NotifyChange({ "nombreActividad", "descripcionActividad",
		"duracionActividad", "nombreProyecto", "descripcionProyecto",
		"codigoProyecto", "responsables", "participantes" })
	public void agregarActividad() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		ListModelList<Profesor> modeloProfesorPaux = new ListModelList<Profesor>();
		if (nombreActividad == "" || duracionActividad == 0
				|| descripcionActividad == "" || modeloProfesorP.isEmpty())
			mensajeEmergente.advertenciaLlenarCampos();
		else {
			if(actividadExistente(nombreActividad)==true)
				mensajeEmergente.advertenciaActividadExistente();
			else{
				modeloProfesorPaux.addAll(modeloProfesorP);
				Actividad act = new Actividad(nombreActividad, duracionActividad,
						descripcionActividad, modeloProfesorPaux);
				modeloActividad.add(act);
				modeloActividadAg.add(act);
				System.out.println("modelo ACTIVIDAD " + modeloActividadAg);
				limpiarActividad();
			}
		}
	}
	/**
	 * Verifica si un Actividad ha sido duplicada
	 * @param nombre de la actividad a verificar
	 * @return verdadero en caso de conseguir uno existente
	 * 			falso en caso contrario
	 * */
	public boolean actividadExistente(String nombre){
		boolean valor=false;
		for(Actividad act:modeloActividadAg){
			if(act.getActividadNombre().contains(nombre))
				valor=true;
		}
		return valor;
	}
	/**
	 * Inserta en la lista de programas de un proyecto el programa seleccionado
	 * del combo
	 */
	@Command
	public void agregarPrograma() {
		if (!(modeloProgramaAsig.contains(selectedPrograma)))
			modeloProgramaAsig.add(selectedPrograma);

	}

	/** Blanquea los campos asociados a Actividad */
	@Command
	@NotifyChange({ "nombreActividad", "descripcionActividad",
		"duracionActividad", "participantes" })
	public void limpiarActividad() {
		descripcionActividad = "";
		nombreActividad = "";
		duracionActividad = 0;
		participantes = "";
	}

	/** Blanquea la lista de programas de un proyecto */
	@Command
	public void limpiarPrograma() {
		selectedPrograma = null;
		modeloProgramaAsig.clear();
		cmbPrograma.setText("");
	}

	/** Blanquea la lista de profesores responsables de un proyecto */
	@Command
	public void limpiarResponsable() {
		modeloProfesorR.clear();
	}

	/** Blanquea los campos sobre informacion referente a un proyecto */
	@Command
	@NotifyChange({ "nombreActividad", "descripcionActividad",
		"duracionActividad", "nombreProyecto", "descripcionProyecto",
		"codigoProyecto", "responsables", "participantes" })
	public void limpiarProyecto() {
		nombreProyecto = "";
		descripcionProyecto = "";
		codigoProyecto = "";
		responsables = "";
		participantes = "";
		limpiarResponsable();
		limpiarPrograma();
		limpiarActividad();
		modeloActividadAg.clear();

	}

	/**
	 * Valida que todos los campos esten llenos de ser asi guarda toda la
	 * informacion relacionada a un proyecto y asigna usuario y password a los
	 * profesores acreditados seleccionados como responsables de un proyecto y
	 * participantes de una actividad, los cuales se les notificara de la
	 * creacion de sus roles via E-mail
	 * @link {@link VMRegistrarProyecto}{@link #guardarActividad(Proyecto)}
	 * */
	@Command
	@NotifyChange({ "nombreActividad", "descripcionActividad",
		"duracionActividad", "nombreProyecto", "descripcionProyecto",
		"codigoProyecto", "responsables", "participantes" })
	public void guardarProyecto() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		Date fechaRegistro = new Date();
		Proyecto proy= sproyecto.buscarPorCodigo(codigoProyecto);
		if (nombreProyecto == "" || descripcionProyecto == ""
				|| codigoProyecto == "" || modeloProfesorP.isEmpty()
				|| modeloProfesorR.isEmpty() || modeloActividadAg.isEmpty()
				|| modeloProgramaAsig.isEmpty())
			mensajeEmergente.advertenciaLlenarCampos();
		else {
			if(proy==null){
				Proyecto pro = new Proyecto();
				pro.setProyectoCodigo(codigoProyecto);
				pro.setProyectoNombre(nombreProyecto);
				pro.setProyectoDescripcion(descripcionProyecto);
				pro.setDireccionPrograma(modeloProgramaAsig);
				pro.setListaprofesoresresponsables(modeloProfesorR);
				pro.setActividadp(modeloActividadAg);
				pro.setProyectoFechaRegistro(fechaRegistro);
				pro.setProyectoStatus(StatusProyecto.Activo.toString());


				sproyecto.guardar(pro);
				guardarActividad(pro);

				EnviarCorreo enviarCorreo = new EnviarCorreo();
				// envia correo a todos los prof. que han sido selelccionado para
				// ser prof. responsable
				for (Profesor profesor : modeloProfesorR) {
					Usuario usuario = su.encontrarUsuarioPorNombreUsuario(profesor
							.getProfesorCedula());
					boolean existia=false;
					for(Grupo grupo:usuario.getGrupos()){
						if(grupo.getNombre().equals("Responsable Proyecto"))
							existia=true;
					}
					if(!existia){
						usuario.addGrupo(sg.buscarGrupoNombre("Responsable Proyecto"));// el grupo 1003
						su.guardarUsuario(usuario);						// representa a los prof
					}										// que son responsables

					enviarCorreo.sendEmail(profesor.getProfesorEmail(),
							"Profesor Responsable de " + nombreProyecto,
							usuario.getNombreUsuario(), usuario.getClave());

				}
				for (Actividad actividad : modeloActividadAg) {
					for (Profesor profesor : actividad
							.getListaprofesoresparticipantes()) {
						Usuario usuario = su
								.encontrarUsuarioPorNombreUsuario(profesor
										.getProfesorCedula());
						boolean existia=false;
						for(Grupo grupo:usuario.getGrupos()){
							if(grupo.getNombre().equals("Participante Actividad"))
								existia=true;
						}
						if(!existia){
							usuario.addGrupo(sg.buscarGrupoNombre("Participante Actividad"));// el grupo 1005
							su.guardarUsuario(usuario);									// representa a los
						}										// prof
						// participantes en
						// las diferentes
						// actividades

						enviarCorreo.sendEmail(profesor.getProfesorEmail(),
								"Profesor Participante en la actividad "
										+ actividad.getActividadNombre()
										+ " del Proyecto " + nombreProyecto,
										usuario.getNombreUsuario(), usuario.getClave());
					}
				}
				mensajeEmergente.informacionRegistroCorrecto();
				limpiarActividad();
				limpiarProyecto();

			}
			else{
				mensajeEmergente.advertenciaCodigoProyectoDuplicado();
			}
		}
	}

	/**
	 * Guarda todas las actividades contenidas en la lista de actividades
	 * agregadas
	 * @param proyecto   con la cual se asociara cada una de las actividades a guardar
	 * */
	@Command
	public void guardarActividad(Proyecto pro) {

		for (int i = 0; i < modeloActividadAg.size(); i++) {

			Actividad act = new Actividad(modeloActividadAg.get(i)
					.getActividadNombre(), modeloActividadAg.get(i)
					.getActividadCantidadHoras(), modeloActividadAg.get(i)
					.getActividadDescripcion(), modeloActividadAg.get(i).getListaprofesoresparticipantes());
			act.setProyectoa(pro);
			sactividad.guardar(act);
		}
	}

	/**
	 * Concatena nombre y apellido de todos los profesores responsables de un
	 * proyecto para ser visualizados dentro de un campo de texto
	 * @param lista   de profesor responsables que se concatenara
	 * */
	@NotifyChange({ "responsables" })
	public void getResponsablesString(ListModelList<Profesor> aux) {
		String cadena = "";

		if (aux.isEmpty()) {
			System.out.print("esta vacia");
			responsables = "";
		}

		else {
			cadena = aux.get(0).getProfesorNombre() + " "
					+ aux.get(0).getProfesorApellido();
			for (int i = 1; i < aux.size(); i++) {
				cadena += "," + aux.get(i).getProfesorNombre() + " "
						+ aux.get(i).getProfesorApellido();
			}
		}
		responsables = cadena;
	}

	/**
	 * Concatena nombre de todos los programas de un proyecto para ser
	 * visualizados dentro de un campo de texto
	 * @param lista de programas que se concatenara
	 * */
	@NotifyChange({ "programa" })
	public void getProgramaString(ListModelList<DireccionPrograma> aux) {
		String cadena = "";

		if (aux.isEmpty()) {
			System.out.print("esta vacia");
			responsables = "";
		}

		else {
			cadena = aux.get(0).getDireccionNombre();
			for (int i = 1; i < aux.size(); i++) {
				cadena += "," + aux.get(i).getDireccionNombre();
			}
		}
		programa = cadena;
	}

	/**
	 * Concatena nombre y apellido de todos los participantes responsables de un
	 * actividad para ser visualizados dentro de un campo de texto
	 * @param lista de profesor responsables que se concatenara
	 * */
	@NotifyChange({ "participantes" })
	public void getParticipantesString(ListModelList<Profesor> aux) {
		String cadena = "";
		if (aux.isEmpty()) {
			System.out.print("esta vacia");
			participantes = "";
		}

		else {
			cadena = aux.get(0).getProfesorNombre() + " "
					+ aux.get(0).getProfesorApellido();
			for (int i = 1; i < aux.size(); i++) {
				cadena += "," + aux.get(i).getProfesorNombre() + " "
						+ aux.get(i).getProfesorApellido();
			}
		}
		participantes = cadena;
	}

	/**
	 * remueve una actividad recien Ingresada en la lista de actividades de un
	 * proyecto
	 * @param actividad la cual se removera de la lista
	 * */
	@Command
	@NotifyChange({ "modeloActividad", "modeloActividadAg" })
	public void eliminarActividadAgregada(
			@BindingParam("Actividad") Actividad actividad) {
		modeloActividad.remove(actividad);
		modeloActividadAg.remove(actividad);
	}

	/*
	 * ------------------------------------------------------------------------------------------
	 * 											 Modificar Proyecto 
	 *  -------------------------------------------------------------------------------------------
	 */

	/**
	 * Concatena nombre y apellido de todos los profesores responsables de un
	 * proyecto a Modificar para ser visualizados dentro de un campo de texto
	 * @param lista de profesor responsables que se concatenara
	 * */
	@NotifyChange({ "responsablesMo" })
	public void getResponsablesMoString(ListModelList<Profesor> aux) {
		String cadena = "";
		if (aux.isEmpty()) {
			System.out.print("esta vacia");
			responsablesMo = "";
		}

		else {
			cadena = aux.get(0).getProfesorNombre() + " "
					+ aux.get(0).getProfesorApellido();
			for (int i = 1; i < aux.size(); i++) {
				cadena += "," + aux.get(i).getProfesorNombre() + " "
						+ aux.get(i).getProfesorApellido();
			}
		}
		responsablesMo = cadena;
	}

	/**
	 * Muestra un catalogo de los proyecto disponibles a modificar y el proyecto
	 * seleccionado lo envia a la funcion ObtenerProyecto
	 * @link {@link VMRegistrarProyecto}{@link #obtenerProyecto(Proyecto)}
	 */
	@NotifyChange({ "nombreProyectoMo", "descripcionProyectoMo",
		"codigoProyectoMo", "modeloActividadMo", "responsablesMo",
		"programa", "opcion" })
	@Command
	public void mostrarProyecto() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("proyecto", "RegistrarProyecto");
		cancelarMo();
		Window comp = (Window) Executions.createComponents(
				"/WEB-INF/vista/view/view.maestros/CatalogoProyectos.zul",
				null, map);
		comp.doModal();
		Proyecto proyecto = (Proyecto) comp.getAttribute("proyecto");
		if (proyecto != null)
			obtenerProyecto(proyecto);

	}

	/**
	 * Muestra el catalogo de aquellos profesores acreditados que pueden ser
	 * participantes en una actividad y los inserta en el modelo lista de
	 * profesores participantes para ser contatenados como un texto en el campo
	 * "Participantes" al Momento de Modificar un Proyecto
	 * @param Actividad  la lista con la cual esta asociado el campo "participantes"
	 * @link {@link VMRegistrarProyecto}{@link #getParticipantesMo()}
	 */
	@SuppressWarnings("unchecked")
	@Command
	public void mostrarCatalogoProfAcreditadosPMo(
			@BindingParam("Actividad") VMActividadEstatus actividad) {
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoProfesoresAcreditados.zul",
						null, null);
		comp.doModal();
		List<Profesor> listAcreditados = new ArrayList<Profesor>();
		listAcreditados = (List<Profesor>) comp
				.getAttribute("seleccion");


		// modeloProfesorPMo.clear();
		if(listAcreditados!=null){
			modeloProfesorPMo.addAll(listAcreditados);
			actividad.getActividad().setListaprofesoresparticipantes(
					listAcreditados);
			modeloActividadMoEstatus.set(
					modeloActividadMoEstatus.indexOf(actividad), actividad);

			getParticipantesString(modeloProfesorPMo);
		}

	}

	/**
	 * Muestra el catalogo de aquellos profesores acreditados que pueden ser
	 * responsables en un proyecto y los inserta en el modelo lista de
	 * profesores responsables para ser contatenados como un texto en el campo
	 * "Responsables"
	 * @link {@link VMRegistrarProyecto}{@link #cargarResponsable(List)}
	 */
	@SuppressWarnings("unchecked")
	@Command
	@NotifyChange({ "responsablesMo" })
	public void mostrarCatalogoProfAcreditadosRMo() {
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoProfesoresAcreditados.zul",
						null, null);
		comp.doModal();

		List<Profesor>listacreditados = (List<Profesor>) comp
				.getAttribute("seleccion");

		if(!(listacreditados==null)) {
			cargarResponsable(listacreditados);
		} 

	}

	/**
	 * Muestra un catalogo de programas disponibles para modificar y el programa
	 * seleccionado lo envia para el metodo CargarPrograma
	 * @link {@link VMRegistrarProyecto}{@link #cargarPrograma(List)}
	 * */
	@SuppressWarnings("unchecked")
	@NotifyChange({ "programa" })
	@Command
	public void mostrarProgramaMo() {
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoDireccionProgramas.zul",
						null, null);
		comp.doModal();
		List<DireccionPrograma> programas = (List<DireccionPrograma>) comp
				.getAttribute("seleccionado");
		if(programas!=null) {
			cargarPrograma(programas);
		}  


	}

	/**
	 * Inserta la lista de profesores acreditados seleccionados en el modelo
	 * lista de profesores Responsables de un proyecto a Modificar
	 * @param lista de profesores acreditados seleccionados
	 * @link {@link VMRegistrarProyecto}{@link #getResponsablesString(ListModelList)}
	 * */
	public void cargarResponsable(List<Profesor> listAcreditados) {
		modeloProfesorRMo.clear();
		modeloProfesorRMo.addAll(listAcreditados);
		getResponsablesMoString(modeloProfesorRMo);
	}

	/**
	 * Inserta la lista de programas seleccionados en el modelo lista de
	 * programas de un proyecto a Modificar
	 * @param lista de programas seleccionados
	 *  @link {@link VMRegistrarProyecto}{@link #getProgramaString(ListModelList)}
	 * */
	public void cargarPrograma(List<DireccionPrograma> programas) {
		modeloProgramaMo.clear();
		modeloProgramaMo.addAll(programas);
		getProgramaString(modeloProgramaMo);
	}

	/**
	 * Muestra la informacion del proyecto seleccionado en cada uno de los
	 * campos vinculados al proyecto seleccionado
	 * @param proyecto  cuya informacion sera mostrada en los campos
	 * */
	private void obtenerProyecto(Proyecto proyecto) {
		modeloActividadMoEstatus.clear();
		nombreProyectoMo = proyecto.getProyectoNombre();
		descripcionProyectoMo = proyecto.getProyectoDescripcion();
		codigoProyectoMo = proyecto.getProyectoCodigo();
		programa = proyecto.getProgramaString();
		cargarResponsable(proyecto.getListaprofesoresresponsables());
		cargarPrograma(proyecto.getDireccionPrograma());
		listParticipantesExistentes=sactividad.buscarParticipante(proyecto.getProyectoNombre());
		listResponsableExistentes=sproyecto.buscarResponsable(proyecto.getProyectoNombre());
		List<Actividad> auxact = sactividad.buscarporProyecto(proyecto);
		modeloActividadMoEstatus.addAll(generarActividadEstatus(auxact));
		btnEditarProyecto.setDisabled(false);
		for(Profesor prof:listParticipantesExistentes){
			System.out.println(prof.getProfesorNombre());
		}
	}

	/**
	 * Valida si los campos de la actividad editada no esten vacios en dicho
	 * caso cambia a " no editable" la actividad y confirma el cambio realizado
	 * @param Actividad  editada dentro de la lista de actividades de un proyecto
	 * */
	@Command
	@NotifyChange({"actividad"})
	public void confirmar(
			@BindingParam("Actividad") VMActividadEstatus actividad) {
		MensajesEmergentes mensajesEmergentes = new MensajesEmergentes();
		if (actividad.getActividad().getActividadNombre() != ""
				&& actividad.getActividad().getActividadDescripcion() != ""
				&& actividad.getActividad().getActividadCantidadHoras()!=null
				&& !(actividad.getActividad().getListaprofesoresparticipantes()
						.isEmpty())) {
			if(validarNombre(actividad.getActividad().getActividadNombre())==false){
				cambiarValor(actividad);
				refreshRowTemplate(actividad);

			}else{
				mensajesEmergentes.advertenciaActividadExistente();
			}
		}
		else{
			mensajesEmergentes.advertenciaLlenarCampos();
		}
	}
	/**
	 *verifica si en la lista hay una actividad 
	 *con el mismo nombre de ser asi retorna verdadero
	 *@param nombre , de la actividad a buscar 
	 *@return booleano  
	 **/
	@NotifyChange({"actividad"})
	@Command
	public boolean validarNombre(String nombre){
		boolean valor=false;
		int contador=0;
		for(VMActividadEstatus act:modeloActividadMoEstatus){
			if(act.getActividad().getActividadNombre().contains(nombre)){
				contador=contador+1;
			}
		}
		if(contador>1)
			valor=true;

		return valor;
	}
	/**
	 * Cambia el estatus de una actividad de la lista de actividades de un
	 * proyecto en " Editable " o "No Editable"
	 * @Param Actividad editada dentro de la lista de actividades de un proyecto
	 * */
	@Command
	public void cambiarValor(
			@BindingParam("Actividad") VMActividadEstatus actividad) {
		actividad.setEstatus(!actividad.isEstatus());
		refreshRowTemplate(actividad);
	}

	/**
	 * Instancia las objetos de la clase VMActividadEstatus la cual se usa para
	 * agregar campo especial llamado "estatus" a cada actividad de un proyecto
	 * @param Lista de Actividades de un proyecto
	 * @return Lista de VMActividadEstatus
	 * @see VMActividadEstatus
	 * */
	public List<VMActividadEstatus> generarActividadEstatus(List<Actividad> aux) {
		List<VMActividadEstatus> actividadEstatus = new ArrayList<VMActividadEstatus>();
		for (int i = 0; i < aux.size(); i++) {
			actividadEstatus.add(new VMActividadEstatus(false, aux.get(i)));
		}
		return actividadEstatus;
	}

	/**
	 * Notifica el cambio de estatus de una actividad a la plantilla de la lista
	 * de la vista
	 * @param Actividad a la cual se le cambio el estatus
	 * */
	public void refreshRowTemplate(VMActividadEstatus act) {

		BindUtils.postNotifyChange(null, null, act, "estatus");
	}

	/**
	 * Si un proyecto ha sido seleccionado habilita campos y botones para entrar
	 * en modo de edicion
	 * */
	@NotifyChange({ "opcion" })
	@Command
	public void editarProyecto() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if (codigoProyectoMo != null) {

			Proyecto proyecto = sproyecto.buscarPorCodigo(codigoProyectoMo);
			List<PreInscripcionProyecto> ins = spreinscripcionproyecto
					.buscarProyecto(proyecto);
			if (ins.isEmpty()) {
				cmbPrograma.setVisible(true);
				txtNombreRegistrarProyectoMo.setReadonly(false);
				txtDescripcionRegistrarProyectoMo.setReadonly(false);
				btnCatalogoResponsableMo.setVisible(true);
				btnGuardarRegistrarProyectoMo.setDisabled(false);
				btnCatalogoProgramaMo.setVisible(true);
				btnIncluirActividad.setVisible(true);
				opcion = true;
			} else {
				mensajeEmergente.errorImposibleModificar();
			}
		}
	}

	/**
	 * Blanquea todos los campos relacionados con el Proyecto a modificar 
	 * */

	@NotifyChange({ "nombreProyectoMo", "descripcionProyectoMo",
		"codigoProyectoMo", "modeloActividadMo", "responsablesMo",
		"opcion", "programa", "nombreActividadMo", " duracionActividadMo",
		"participantesMo", "descripcionActividadMo", "ver" })
	@Command
	public void cancelarMo() {

		modeloActividadMoEstatus.clear();
		nombreProyectoMo = "";
		descripcionProyectoMo = "";
		codigoProyectoMo = "";
		responsablesMo = "";
		programa = "";
		btnEditarProyecto.setDisabled(true);
		txtCodigoRegistrarProyectoMo.setReadonly(true);
		txtNombreRegistrarProyectoMo.setReadonly(true);
		txtDescripcionRegistrarProyectoMo.setReadonly(true);
		txtResponsableRegistrarProyectoMo.setReadonly(true);
		btnCatalogoResponsableMo.setVisible(false);
		btnCatalogoProgramaMo.setVisible(false);
		btnGuardarRegistrarProyectoMo.setDisabled(true);
		btnEditarProyecto.setDisabled(true);
		btnIncluirActividad.setVisible(false);
		opcion = false;
		ver = false;
	}

	/**
	 * Verifica que no haya campos vacios de no haberlos hace las modificaciones
	 * realizadas a los datos del proyecto como sus actividades , responsables y
	 * programas
	 * @link {@link VMRegistrarProyecto}{@link #modificarActividad(Proyecto)}
	 */
	@NotifyChange({ "nombreProyectoMo", "descripcionProyectoMo",
		"codigoProyectoMo", "modeloActividadMo", "responsablesMo",
		"opcion", "programa", "nombreActividadMo", " duracionActividadMo",
		"participantesMo", "descripcionActividadMo", "ver" })
	@Command
	public void modificarProyecto() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		Proyecto proyecto = sproyecto.buscarPorCodigo(codigoProyectoMo);
		if (proyecto != null) {

			if (nombreProyectoMo != "" && descripcionProyectoMo != ""
					&& programa != "" && responsablesMo != ""
					&& !(modeloProfesorRMo.isEmpty())
					&& !(modeloProfesorRMo.isEmpty())
					&& !(modeloProgramaMo.isEmpty())) {
				retirarResponsable();
				proyecto.setProyectoNombre(nombreProyectoMo);
				proyecto.setListaprofesoresresponsables(modeloProfesorRMo);
				proyecto.setProyectoDescripcion(descripcionProyectoMo);
				proyecto.setDireccionPrograma(modeloProgramaMo);
				sproyecto.guardar(proyecto);
				EnviarCorreo enviarCorreo = new EnviarCorreo();
				// envia correo a todos los prof. que han sido selelccionado para
				// ser prof. responsable
				for (Profesor profesor : modeloProfesorRMo) {
					Usuario usuario = su.encontrarUsuarioPorNombreUsuario(profesor
							.getProfesorCedula());
					boolean existia=false;
					for(Grupo grupo:usuario.getGrupos()){
						if(grupo.getNombre().equals("Responsable Proyecto"))
							existia=true;
					}
					if(!existia){
						usuario.addGrupo(sg.buscarGrupoNombre("Responsable Proyecto"));			// el grupo 1004
						su.guardarUsuario(usuario);						// representa a los prof
																		// que son responsables
					}	
					enviarCorreo.sendEmail(profesor.getProfesorEmail(),
							"Profesor Responsable de " + nombreProyecto,
							usuario.getNombreUsuario(), usuario.getClave());
				}
				modificarActividad(proyecto);
				mensajeEmergente.informacionRegistroCorrecto();
				cancelarMo();

			} else {
				mensajeEmergente.advertenciaLlenarCampos();
			}
		}
	}
	/**
	 * Retira profesor responsable de un proyecto
	 * */
	public void retirarResponsable(){
		if (!(listResponsableExistentes.isEmpty())
				&& !(modeloProfesorRMo.isEmpty())) {
			for (Profesor profesor : listResponsableExistentes) {
				boolean valor = false;
				for (Profesor responsable : modeloProfesorRMo) {
					if (responsable.getProfesorCedula().contains(
							profesor.getProfesorCedula())) {
						valor = true;
						break;
					}
				}
				if (valor == false) {
					System.out.println("retirar");
					Usuario usuario1 = su
							.encontrarUsuarioPorNombreUsuario(profesor
									.getProfesorCedula());
					System.out.println(usuario1.getGrupos().get(0).getNombre());
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
		}
	}
	/**
	 * Guarda las modificaciones realizada a cada una de las actividades de un
	 * proyecto
	 * @param proyecto  el cual se asociara cada actividad
	 * @link {@link VMRegistrarProyecto#eliminarActividad(VMActividadEstatus)}
	 * */
	public void modificarActividad(Proyecto proyecto) {
		Usuario usuario=null;
		EnviarCorreo enviarCorreo = new EnviarCorreo();
		List<Actividad> listActividades=sactividad.buscarporProyecto(proyecto);
		for(int i = 0; i < listActividades.size(); i++) {
			Actividad actividad=listActividades.get(i);
			for(Profesor profesor: actividad.getListaprofesoresparticipantes()) {
				usuario=su.encontrarUsuario(profesor.getIdusuario());
				for(int j = 0; j < usuario.getGrupos().size(); j++) {
					if(usuario.getGrupos().get(j).getNombre().equals("Participante Actividad")){
						List<Actividad> aux = sactividad.participanteOtroProyecto(profesor);
						if (aux.size()==1) {
							usuario.getGrupos().remove(j);
						}
					}
				}
				su.guardarUsuario(usuario);
			}
			sactividad.eliminar(actividad);
		}
		boolean existia;
		for (int i = 0; i < modeloActividadMoEstatus.size(); i++) {
			Actividad actividad=modeloActividadMoEstatus.get(i).getActividad();
			for(Profesor profesor:actividad.getListaprofesoresparticipantes()){
				usuario=su.encontrarUsuario(profesor.getIdusuario());
				existia=false;
				for(int j = 0; j < usuario.getGrupos().size(); j++) {
					if(usuario.getGrupos().get(j).getNombre().equals("Participante Actividad")){
						existia=true;
					}
				}
				if(!existia){
					usuario.getGrupos().add(sg.buscarGrupoNombre("Participante Actividad"));
					su.guardarUsuario(usuario); 
					}
				enviarCorreo.sendEmail(profesor.getProfesorEmail(),
						"Profesor Participante en la actividad "
								+ actividad.getActividadNombre()
								+ " del Proyecto " + nombreProyecto,
						usuario.getNombreUsuario(), usuario.getClave());
			
			}
			sactividad.guardar(actividad);	
		}
	}
	/**
	 * Eliminar los profesores participantes de una actividad
	 * @param actividad de la cual se removera particpante
	 * */
	public void EliminarParticipante(Actividad actividad) {
		if (!(listParticipantesExistentes.isEmpty())) {
			for (Profesor profesor : actividad
					.getListaprofesoresparticipantes()) {
				Proyecto proyecto = sproyecto.buscarPorCodigo(codigoProyectoMo);
				List<Actividad> aux = sactividad.participanteOtroProyecto(
						profesor);
				if (aux.size()==1) {
					System.out.println("entro por valor");
					Usuario usuario1 = su
							.encontrarUsuarioPorNombreUsuario(profesor
									.getProfesorCedula());
					List<Grupo> grupos = usuario1.getGrupos();
					Grupo grupoRemover = null;
					int nroGrupos = grupos.size();
					for (Grupo grupo : grupos) {
						if (grupo.getNombre().equals("Participante Actividad")) {
							grupoRemover = grupo;
							// el grupo 1005 es el
						} // profesor participante

					}
					usuario1.getGrupos().remove(grupoRemover);
					su.guardarUsuario(usuario1);
				}else{
					System.out.println("no se puede eliminar");
				}
			}
		}
	}
	/**
	 * Retira profesores participantes de una actividad
	 * @ actividad de la cual se retirara participantes
	 * */
	public void retirarParticipante(Actividad actividad){

		if(!(listParticipantesExistentes.isEmpty())){
			for (Profesor profesor : listParticipantesExistentes) {
				boolean valor = false;
				for (Profesor participante : actividad
						.getListaprofesoresparticipantes()) {
					if (participante.getProfesorCedula().contains(profesor
							.getProfesorCedula())){
						valor = true;

					}
				}
				if (valor == false) {
					System.out.println("entro por valor");
					Usuario usuario1 = su.encontrarUsuarioPorNombreUsuario(profesor
							.getProfesorCedula());
					List<Grupo> grupos = usuario1.getGrupos();
					Grupo grupoRemover=null;
					int nroGrupos = grupos.size();
					for (Grupo grupo : grupos) {
						System.out.println("entro por grupo");
						if (grupo.getNombre().equals("Participante Actividad")){
							grupoRemover=grupo;
							// el grupo 1005 es el
						}								// profesor participante

					}
					usuario1.getGrupos().remove(grupoRemover);
					su.guardarUsuario(usuario1);
				}
			}

		}
	}

	/**
	 * Inserta las Actividades seleccionadas para eliminar en la lista de
	 * actividades a eliminar
	 * @param actividad  seleccionada para eliminar
	 * @see VMActividadEstatus
	 */
	@Command
	public void eliminarActividad(
			@BindingParam("Actividad") VMActividadEstatus actividad) {
		MensajesEmergentes mensaje= new  MensajesEmergentes();
		if(modeloActividadMoEstatus.size()>1){
			modeloActividadMoEstatus.remove(actividad);
			modeloActividadMoEstatusEliminar.add(actividad);
		}else{
			mensaje.advertenciaSinActividad();
		}
	}

	/**
	 * Muestra Los Campos de Actividad Nueva a Agregar que estan invisibles
	 */
	@Command
	@NotifyChange({ "ver" })
	public void incluirActividad() {
		setVer(!ver);
	}

	/**
	 * Verifica que no hayan campos vacios de no ser asi construye con la
	 * informacion tipea objeto Actividad y lo inserta En la lista de
	 * actividades Del proyecto
	 * @see VMActividadEstatus
	 * 
	 */
	@NotifyChange({ "nombreActividadMo", " duracionActividadMo",
		"participantesMo", "descripcionActividadMo", "ver" })
	@Command
	public void agregarActividadNueva(Actividad actividadnew) {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if(actividadExistenteMo(actividadnew.getActividadNombre())==true){
			mensajeEmergente.advertenciaActividadExistente();	
		}
		else{

			modeloActividadMoEstatus.add(new VMActividadEstatus(false,
					actividadnew));
			ver = false;
		}

	}
	@Command
	public void mostrarActividadNueva(){
		Proyecto proyecto=sproyecto.buscarPorCodigo(codigoProyectoMo);
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("actividad", modeloActividadMoEstatus);
		map.put("proyecto", proyecto);
		Window comp= (Window) Executions.createComponents("/WEB-INF/vista/view/view.maestros/AgregarActividad.zul", null,map);
		comp.doModal();
		Actividad actividad= (Actividad)comp.getAttribute("actividad");
		if(actividad!=null){
			agregarActividadNueva(actividad);
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
	/**
	 * Muestra el catalogo de profesores Acreditados que pueden ser
	 * Participantes y lo seleccionado lo inserta en la lista de profesores
	 * participantes a incluir
	 * @link {@link VMRegistrarProyecto#getParticipantesIncluirString(ListModelList)}
	 * */

	/** cierra la ventana */
	@Command
	public void cerrar() {
		win.detach();
	}

}
