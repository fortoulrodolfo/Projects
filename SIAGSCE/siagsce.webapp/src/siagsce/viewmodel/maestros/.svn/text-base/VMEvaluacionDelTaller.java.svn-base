package siagsce.viewmodel.maestros;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.ListadoEstudiantesEvaluadosTaller;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.data.transacciones.ListadoEstudiantesActividadAsignada;
import siagsce.modelo.data.transacciones.ListadoEstudiantesRegistrarEjecucion;
import siagsce.modelo.general.StatusEstudiante;
import siagsce.modelo.general.StatusInscripcionTaller;
import siagsce.modelo.general.StatusTaller;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SInscripcionTaller;
import siagsce.modelo.servicio.maestros.STaller;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMEvaluacionDelTaller {
	
	private Window win;
	
	@WireVariable
	STaller sTaller;
	
	@WireVariable
	SEstudiante sestudiante;
	
	@WireVariable
	SInscripcionTaller sinscripciontaller;
	
	@Wire
	private Button btnGuardar;
	
	//Declaracion de variables de taller
	String nombreTaller, descripcionTaller, lugar, modalidad, responsable;
	Integer codigoTaller,compararCodigo;
	Integer cantidadHorasTaller;
	Date inicioTaller, finTaller;
	
	//Declaracion de variables de estudiante inscrito
	
	private List<Estudiante> estudiantesInscritosTaller;
	Taller selectedTaller;
	private ListModelList<ListadoEstudiantesEvaluadosTaller> modeloEstudianteEvaluado;
	
	public Taller getSelectedTaller() {
		return selectedTaller;
	}
	public void setSelectedTaller(Taller selectedTaller) {
		this.selectedTaller = selectedTaller;
	}
	public Integer getCodigoTaller() {
		return codigoTaller;
	}
	public void setCodigoTaller(Integer codigoTaller) {
		this.codigoTaller = codigoTaller;
	}
	public String getNombreTaller() {
		return nombreTaller;
	}
	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}
	public String getDescripcionTaller() {
		return descripcionTaller;
	}
	public void setDescripcionTaller(String descripcionTaller) {
		this.descripcionTaller = descripcionTaller;
	}
	public Integer getCantidadHorasTaller() {
		return cantidadHorasTaller;
	}
	public void setCantidadHorasTaller(Integer cantidadHorasTaller) {
		this.cantidadHorasTaller = cantidadHorasTaller;
	}
	public Date getInicioTaller() {
		return inicioTaller;
	}
	public void setInicioTaller(Date inicioTaller) {
		this.inicioTaller = inicioTaller;
	}
	public Date getFinTaller() {
		return finTaller;
	}
	public void setFinTaller(Date finTaller) {
		this.finTaller = finTaller;
	}
	public Integer getCompararCodigo() {
		return compararCodigo;
	}
	public void setCompararCodigo(Integer compararCodigo) {
		this.compararCodigo = compararCodigo;
	}
	public List<Estudiante> getEstudiantesInscritosTaller() {
		return estudiantesInscritosTaller;
	}
	public void setEstudiantesInscritosTaller(
			List<Estudiante> estudiantesInscritosTaller) {
		this.estudiantesInscritosTaller = estudiantesInscritosTaller;
	}
	public ListModelList<ListadoEstudiantesEvaluadosTaller> getModeloEstudianteEvaluado() {
		return modeloEstudianteEvaluado;
	}
	public void setModeloEstudianteEvaluado(
			ListModelList<ListadoEstudiantesEvaluadosTaller> modeloEstudianteEvaluado) {
		this.modeloEstudianteEvaluado = modeloEstudianteEvaluado;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	
	//Metodo que inicializa las variables
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		MensajeBox.doSetTemplate();
		modeloEstudianteEvaluado = new ListModelList<ListadoEstudiantesEvaluadosTaller>();
		codigoTaller = null;
		compararCodigo = null;
	}
	
	//metodo que permite acceder a los componentes de la vista e inicializar los necesarios
		@AfterCompose
		public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
			Selectors.wireComponents(view, this, false);
			
		}
	
	//Metodo que abre la ventana del taller
	@Command
    @NotifyChange({"nombreTaller", "descripcionTaller", "codigoTaller", "cantidadHorasTaller", "inicioTaller", "finTaller","lugar","modalidad","responsable","estudiantesInscritosTaller","modeloEstudianteEvaluado"})
    public void MostrarCatalogoTaller()
    {
 		String ventana = "EvaluacionDelTaller";
 		final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("taller", ventana );
       Window comp= (Window) Executions.createComponents("/WEB-INF/vista/view/view.maestros/Catalogotaller.zul", null, map);
       comp.doModal();
       Taller taller = (Taller) comp.getAttribute("taller");
       if(taller!=null)
       obtenerTallerEvaluacionDelTaller(taller);
       
    }
	
	//Metodo que obtiene el taller del catalogo

    @NotifyChange({"nombreTaller", "descripcionTaller", "codigoTaller", "cantidadHorasTaller", "inicioTaller", "finTaller","lugar","modalidad","responsable","estudiantesInscritosTaller","modeloEstudianteEvaluado"})
    public void obtenerTallerEvaluacionDelTaller(Taller taller)
    {
 		nombreTaller = taller.getTallerNombre();
 		descripcionTaller = taller.getTallerDescripcion();
 		codigoTaller = taller.getTallerCodigo();
 		cantidadHorasTaller = taller.getTallerCantidadHoras();
 		inicioTaller = taller.getTallerFechaInicio();
 		finTaller = taller.getTallerFechaCulminacion();
 		lugar = taller.getTallerLugar();
 		modalidad = taller.getTallerModalidad();
 		responsable = taller.getTallerNombreCompletoResponsable();
 		selectedTaller = taller;
 		cargarInscritos();
 		if (modeloEstudianteEvaluado.isEmpty()){
 			btnGuardar.setLabel("Cerrar Taller");
 		}
    }
 	
 	//Carga los estudiantes inscritos en el taller que se seleccione
 	@Command
 	@NotifyChange({"estudiantesInscritosTaller","modeloEstudianteEvaluado"})
 	public void cargarInscritos(){
 		modeloEstudianteEvaluado.clear();
 		String cedula, nombre, apellido;
 		estudiantesInscritosTaller = sinscripciontaller.buscarEstudiantesDeUnTaller(selectedTaller);
 		for (int i=0; i<estudiantesInscritosTaller.size(); i++){
 			cedula = estudiantesInscritosTaller.get(i).getEstudianteCedula();
 			nombre = estudiantesInscritosTaller.get(i).getEstudianteNombre();
 			apellido = estudiantesInscritosTaller.get(i).getEstudianteApellido();
 			ListadoEstudiantesEvaluadosTaller estudiante = new ListadoEstudiantesEvaluadosTaller(cedula, nombre, apellido, null);
 			modeloEstudianteEvaluado.add(estudiante);
 		}
		
 	}
 	
 	//Asigna la calificacion que se le asigna al estudiante en el combobox
 	@SuppressWarnings({ "unchecked", "rawtypes" })
 	@Command
 	public void asignarHorasAlEstudiante(@ContextParam(ContextType.COMPONENT) Combobox componente){

 		String calificacion = componente.getValue();
 		String cedula = (String) componente.getAttribute("cedula");
 		ListadoEstudiantesEvaluadosTaller estudiante;
 		for (int i=0; i<modeloEstudianteEvaluado.size();i++){
 			estudiante = modeloEstudianteEvaluado.get(i);
 			if(estudiante.getCedula() == cedula){
 				modeloEstudianteEvaluado.get(i).setCalificacion(calificacion);
 			}
 			
 		}
 	}
 	
		// valida que no existan estudiantes sin calificacion y los guarda
		@Command
		@NotifyChange({"codigoTaller","nombreTaller","descripcionTaller","cantidadHorasTaller","inicioTaller","finTaller","lugar","modalidad","responsable","modeloEstudianteEvaluado","selectedTaller","estudiantesInscritosTaller"})
		public void guardar(){
			
			MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
			
			if(selectedTaller != null){
				//Si el modelo contiene estudiantes
				if (!modeloEstudianteEvaluado.isEmpty()){
					
					boolean validarCalificacion = false;
					boolean exito = false;
					int i=0;
					//ciclo que valida que no existan estudiantes sin calificar
					while (!validarCalificacion) {
						if (i < modeloEstudianteEvaluado.size()){
							if (modeloEstudianteEvaluado.get(i).getCalificacion() == null){
								validarCalificacion = true;
							}
							else i++;
						}
						else{
						validarCalificacion = true;
						exito = true;
						}
					}
					
					//Si no existen estudiantes sin calificar, guarda
					if (exito){
						Estudiante estudiante;
						InscripcionTaller inscripcion;
						for (int j=0; j<modeloEstudianteEvaluado.size();j++){
							// si el estudiante es aprobado actualizo su estatus a acreditado, actualizo su inscripcion al taller a aprobado
							if (modeloEstudianteEvaluado.get(j).getCalificacion().equals("Aprobado")){
								estudiante = sestudiante.buscarPorCedula(modeloEstudianteEvaluado.get(j).getCedula());
								estudiante.setEstudianteStatus(StatusEstudiante.Acreditado.toString());
								inscripcion = sinscripciontaller.buscarPorEstudianteAndTaller(estudiante, selectedTaller);
								inscripcion.setInscripcionTallerStatus(StatusInscripcionTaller.Aprobado.toString());
								sinscripciontaller.guardar(inscripcion);
								sestudiante.guardar(estudiante);
							}
								//si el estudiante es reprobado actualizo su inscripcion al taller a reprobado
							else if(modeloEstudianteEvaluado.get(j).getCalificacion().equals("Reprobado")){
								estudiante = sestudiante.buscarPorCedula(modeloEstudianteEvaluado.get(j).getCedula());
								inscripcion = sinscripciontaller.buscarPorEstudianteAndTaller(estudiante, selectedTaller);
								inscripcion.setInscripcionTallerStatus(StatusInscripcionTaller.Reprobado.toString());
								sinscripciontaller.guardar(inscripcion);
							}
							//si el estudiante es reprobado actualizo su inscripcion al taller a retirado
							else{
								estudiante = sestudiante.buscarPorCedula(modeloEstudianteEvaluado.get(j).getCedula());
								inscripcion = sinscripciontaller.buscarPorEstudianteAndTaller(estudiante, selectedTaller);
								inscripcion.setInscripcionTallerStatus(StatusInscripcionTaller.Retirado.toString());
								sinscripciontaller.guardar(inscripcion);
							}
						}
						//Cambia el estatus del taller a Inactivo y muestra mensaje de exito
						selectedTaller.setTallerStatus(StatusTaller.Inactivo.toString());
						sTaller.guardar(selectedTaller);
						cancelar();
						mensajeEmergente.informacionRegistroCorrecto();
					}
					//Si existen estudiantes sin calificar
					else mensajeEmergente.advertenciaLlenarCampos();
				}
				//Si el modelo de estudiantes esta vacio
				
				else {
					selectedTaller.setTallerStatus(StatusTaller.Inactivo.toString());
					sTaller.guardar(selectedTaller);
					cancelar();
					mensajeEmergente.informacionCerrarExito();
				}
			}
			else mensajeEmergente.advertenciaSeleccionarOpcion();
			
				
				
		}
		
		//Metodo que limpia la ventana
		@NotifyChange({"codigoTaller","nombreTaller","descripcionTaller","cantidadHorasTaller","inicioTaller","finTaller", "lugar", "modalidad","responsable","modeloEstudianteEvaluado","selectedTaller","estudiantesInscritosTaller"})
		@Command
		public void cancelar(){
			codigoTaller = null;
			nombreTaller = null;
			descripcionTaller = null;
			cantidadHorasTaller = null;
			inicioTaller = null;
			finTaller = null;
			lugar = null;
			modalidad = null;
			responsable = null;
			modeloEstudianteEvaluado.clear();
			selectedTaller = null;
			estudiantesInscritosTaller.clear();
			btnGuardar.setLabel("Guardar");
		}
		
		@Command
		public void cerrarVentana() {
			win.detach();
		}
}