package siagsce.viewmodel.maestros;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.bind.BindUtils;
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

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.general.StatusCoordinadorSce;
import siagsce.modelo.general.StatusProfesor;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SCoordinadorSce;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;

/**
 * ViewModel para la vista que muestra el catalogo de Profesores Acreditados
 * para el Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoProfesorAcreditadoCoordinacion {

	/**
	 * Declaracion de servicios del ViewModel
	 **/
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	SRepresentanteProfesoral smiembroCoordinacion;
	@WireVariable
	SCoordinadorSce scoordinacorSce;
	@WireVariable
	SProyecto sproyecto;
	@WireVariable
	SActividad sactividad;
	
	/**
	 * Declaracion de listas y otras estructuras de datos
	 **/
	private List<Profesor> listprofesor;
	private ListModelList<Profesor> modeloProfesorAcred;
	private List<String> valores;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private Profesor selectedAcreditados;
	private String ventana;
	private String ventanaCoord;
	private String ventanaRepresentante;
	private String seleccion;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	Window win;
	@Wire
	private Textbox txtFiltroProfesorA;
	@Wire
	private Popup popOpciones;
	
	
	
	/** 
	 *Setter y Getter
	 **/
	
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

	private String texto;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getVentanaCoord() {
		return ventanaCoord;
	}

	public void setVentanaCoord(String ventanaCoord) {
		this.ventanaCoord = ventanaCoord;
	}

	public String getVentanaRepresentante() {
		return ventanaRepresentante;
	}

	public void setVentanaRepresentante(String ventanaRepresentante) {
		this.ventanaRepresentante = ventanaRepresentante;
	}

	public Profesor getSelectedAcreditados() {
		return selectedAcreditados;
	}

	public void setSelectedAcreditados(Profesor selectedAcreditados) {
		this.selectedAcreditados = selectedAcreditados;
	}

	public String getVentana() {
		return ventana;
	}

	public void setVentana(String ventana) {
		this.ventana = ventana;
	}
	public ListModelList<Profesor> getModeloProfesorAcred() {
		return modeloProfesorAcred;
	}

	public void setModeloProfesorAcred(
			ListModelList<Profesor> modeloProfesorAcred) {
		this.modeloProfesorAcred = modeloProfesorAcred;
	}

	public void cargarProfesorAcreditado() {
		modeloProfesorAcred = new ListModelList<Profesor>(listprofesor);

	}

	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win,    ventana la cual esta asociada a este viewmodel
	 * @param ventana, variable que trae la informacion de que ventana solicita el catalogo
	 */
	@Init
	public void init(
			@ContextParam(ContextType.COMPONENT) Component win,
			@ExecutionArgParam("profesor") String ventana) {
		this.win = (Window) win;
		this.ventana = ventana;
		valores = new ArrayList<String>();
		listprofesor= new ArrayList<Profesor>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		MensajeBox.doSetTemplate();
		listprofesor.clear();
		listprofesor = cargarProfesorAcreditadoI();
		cargarProfesorAcreditado();
		
	}
	

	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		txtFiltroProfesorA.setPlaceholder("Nombre");
	}
	/**
	 * Verifica que los profesores que se muestra en el catalogo no sea un coordinador 
	 * o representante Profesoral vigente en el Servicio Comunitario Estudiantil 
	 * @return Lista de profesores acreditados que no ejercen cargos en el Servicio
	 * 			Comunitario estudiantil
	 **/
	public List<Profesor> cargarProfesorAcreditadoI() {

		List<RepresentanteProfesoral> profer = smiembroCoordinacion
				.buscarRepresentatesPorEstatus(StatusRepresentanteProfesoral.Activo
						.toString());
		CoordinadorSce Coordinador = scoordinacorSce
				.buscarEstatus(StatusCoordinadorSce.Activo.toString());
		List<Profesor> profesores = new ArrayList<Profesor>();
		List<Profesor> aux = sprofesor.buscarStatus(StatusProfesor.Acreditado
				.toString());
		System.out.print(profer.size());

		switch (ventana) {
		case "RegistrarCoordinador":

			if (Coordinador != null) {
				for (int i = 0; i < aux.size(); i++) {
					if (!(Coordinador.getProfesorcoordinador()
							.getProfesorCedula().contains(aux.get(i)
							.getProfesorCedula()))) {
						profesores.add(aux.get(i));
					}
				}
			} else {
				profesores = aux;
			}
			break;
		case "RegistrarRepresentante":
			listprofesor.clear();
			if (profer.size() != 0) {
				for (Profesor paux : aux) {
					boolean responsable = false;
					for (RepresentanteProfesoral rep : profer) {
						if (rep.getProfesorm().getProfesorCedula()
								.contains(paux.getProfesorCedula())) {
							responsable = true;
						}
					}
					if (!responsable)
						profesores.add(paux);
				}
			} else {
				profesores = aux;
			}
			break;
		
		default:
			profesores = aux;
			break;
		}
		return profesores;

	}

	
	/**
	 * El profesor acreditado seleccionado
	 *  los envia a la vista principal
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void aceptar() {
		
	MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		if(selectedAcreditados!=null){
			if (ventana == "RegistrarCoordinador") {
				win.setAttribute("profesor", this.selectedAcreditados);
				win.detach();
			} else {
				if (ventana == "RegistrarRepresentante") {
					win.setAttribute("profesor", this.selectedAcreditados);
					win.detach();
				} else {
					if (ventana == "PlanificacionTaller") {
						win.setAttribute("profesor", this.selectedAcreditados);
						win.detach();
					}
					else{
						if (ventana == "AcreditarProfesor") {
							win.setAttribute("profesor", this.selectedAcreditados);
							win.detach();
						}
					}
				}
			}
		}
		else{
			mensajeEmergente.advertenciaSeleccionarOpcion();
		}
		

	}
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de profesores acreditados con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoProfesorAcreditadoCoordinacion}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "profesormodel" })
	public void filtrarProfesor() {
		List<Profesor> aux = new ArrayList<Profesor>();
		aux = listprofesor;
		modeloProfesorAcred.clear();

		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modeloProfesorAcred.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getProfesorNombre().toLowerCase()
							.contains(texto.toLowerCase())) {
						modeloProfesorAcred.add(aux.get(i));

					}
				}
			}
		} else {
			if (seleccion == "Cédula") {
				if (texto == "")
					modeloProfesorAcred.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesorCedula().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloProfesorAcred.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Nombre") {
					if (texto == "")
						modeloProfesorAcred.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProfesorNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloProfesorAcred.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Apellido") {
						if (texto == "")
							modeloProfesorAcred.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProfesorApellido()
										.toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloProfesorAcred.add(aux.get(i));

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
		if (seleccion== "nombre") {
			txtFiltroProfesorA.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroProfesorA.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion== "Apellido")
					txtFiltroProfesorA.setPlaceholder("Apellido");
				popOpciones.close();
			}
		}

	}

	/**
	 * cierra el catalogo
	 * */
	@Command
	public void salir() {
		win.detach();
	}
}
