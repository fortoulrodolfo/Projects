package siagsce.viewmodel.maestros;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.ListadoEstudiantesCulminados;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.ActividadAsignada;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.ListadoPreinscripto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;
import siagsce.modelo.general.StatusEstudiante;
import siagsce.modelo.general.StatusInscripcionProyecto;
import siagsce.modelo.general.StatusPreinscripcionProyecto;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.transacciones.SActividadAsignada;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.modelo.servicio.transacciones.SListadoEstudiantesCulminado;
import siagsce.modelo.servicio.transacciones.SPreInscripcionProyecto;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
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
public class VMEstudiantesCulminado {

	/**
	 * Declaracion de servicios del ViewModel
	 * */
	private @WireVariable
	SEstudiante sestudiante;
	private @WireVariable
	SInscripcionProyecto sinscripcionProyecto;
	private @WireVariable
	SPreInscripcionProyecto spreinscripcionproyecto;
	private @WireVariable
	SActividadAsignada sactividadAsignada;
	private @WireVariable
	SListadoEstudiantesCulminado sListadoEstudiantesCulminado;
	@Wire
	private Window win;

	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private List<ListadoEstudiantesCulminados> listaEstudiante;
	private ListModelList<ListadoEstudiantesCulminados> modeloEstudiante;
	private Set<ListadoEstudiantesCulminados> selectedEstudiantes;
	
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

	public List<ListadoEstudiantesCulminados> getListaEstudiante() {
		return listaEstudiante;
	}

	public void setListaEstudiante(List<ListadoEstudiantesCulminados> listaEstudiante) {
		this.listaEstudiante = listaEstudiante;
	}

	public SEstudiante getSestudiante() {
		return sestudiante;
	}

	public void setSestudiante(SEstudiante sestudiante) {
		this.sestudiante = sestudiante;
	}

	public Set<ListadoEstudiantesCulminados> getSelectedEstudiantes() {
		return selectedEstudiantes;
	}

	public void setSelectedEstudiantes(Set<ListadoEstudiantesCulminados> selectedEstudiantes) {
		this.selectedEstudiantes = selectedEstudiantes;
	}

	public SEstudiante getServicioEstudiante() {
		return sestudiante;
	}

	public void setServicioEstudiante(SEstudiante sestudiante) {
		this.sestudiante = sestudiante;
	}

	public ListModelList<ListadoEstudiantesCulminados> getModeloEstudiante() {
		return modeloEstudiante;
	}

	public void setModeloEstudiante(ListModelList<ListadoEstudiantesCulminados> modeloEstudiante) {
		this.modeloEstudiante = modeloEstudiante;
	}

	/**
	 * Metodo de inicializacion.Carga la lista con los estudiantes con sus respectivas horas.
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win){
		this.win=(Window)win;
		listaEstudiante = sListadoEstudiantesCulminado.buscarCulminados();
		modeloEstudiante = new ListModelList<ListadoEstudiantesCulminados>();
		modeloEstudiante.setMultiple(true);
		modeloEstudiante.addAll(listaEstudiante);
		MensajeBox.doSetTemplate();
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		valores.add("Programa");

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
	@NotifyChange({"selectedEstudiantes" , "modeloEstudiante", "listaEstudiante"})
	@Command
	public void aceptar(){
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		try {
			
			List<ListadoEstudiantesCulminados> listEstudiante = new ArrayList<ListadoEstudiantesCulminados>();
			listEstudiante.addAll(selectedEstudiantes);
			
		for(int i=0; listEstudiante.size()>i;i++){
			String cedula = listEstudiante.get(i).getCedula();
			Estudiante estudiante = sestudiante.buscarPorCedula(cedula);
			estudiante.setEstudianteStatus(StatusEstudiante.Aprobado.toString());
			sestudiante.guardar(estudiante);
			
			List<PreInscripcionProyecto> listPreInscripcion =  new ArrayList<PreInscripcionProyecto>();
			listPreInscripcion = spreinscripcionproyecto.buscarPorEstudiante(estudiante);
			for(int g=0; listPreInscripcion.size()>g; g++){
				PreInscripcionProyecto preInsPro = listPreInscripcion.get(g);
				preInsPro.setPreinscripcionStatus(StatusPreinscripcionProyecto.Inactivo.toString());
				spreinscripcionproyecto.guardar(preInsPro);
			}
			
			List<InscripcionProyecto> listInscripcion =  new ArrayList<InscripcionProyecto>();
			listInscripcion = sinscripcionProyecto.buscarPorEstudianteYStatus(estudiante, StatusInscripcionProyecto.Activo.toString());
			for(int j=0; listInscripcion.size()>j; j++){
				InscripcionProyecto insPro = listInscripcion.get(j);
				insPro.setInscripcionProyectoStatus(StatusInscripcionProyecto.Inactivo.toString());
				sinscripcionProyecto.guardar(insPro);
			}
			List<ActividadAsignada> listAsignada = new ArrayList<ActividadAsignada>();
			listAsignada = sactividadAsignada.buscarPorEstudianteSinEjecutar(estudiante);
			for(int k=0; listAsignada.size()>k; k++){
				ActividadAsignada actAsig = listAsignada.get(k);
				sactividadAsignada.eliminar(actAsig);
			}
			
		}
		MensajeBox.doSetTemplate();
		mensajeEmergente.informacionCulminacionSCE();
		listEstudiante.clear();
		this.selectedEstudiantes.clear();
		listaEstudiante = sListadoEstudiantesCulminado.buscarCulminados();
		modeloEstudiante = new ListModelList<ListadoEstudiantesCulminados>();
		modeloEstudiante.setMultiple(true);
		modeloEstudiante.addAll(listaEstudiante);
		
		} catch (NullPointerException e){
			MensajeBox.doSetTemplate();
			mensajeEmergente.advertenciaSeleccionarOpcion();
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
		List<ListadoEstudiantesCulminados> aux = new ArrayList<ListadoEstudiantesCulminados>();
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
				   } else{
						if (seleccion == "Programa") {
							if (texto == "")
								modeloEstudiante.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getPrograma().toLowerCase()
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
		System.out.print(seleccion);
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
					if (seleccion== "Programa") {
						txtFiltroEstudiante.setPlaceholder("Programa");
						popOpciones.close();
					}
				}
			}
		  }
		}
	
	/**
	 *Cierra la ventana
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}
}