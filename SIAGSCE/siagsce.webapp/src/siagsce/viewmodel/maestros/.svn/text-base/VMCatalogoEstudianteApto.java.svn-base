package siagsce.viewmodel.maestros;

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
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.general.StatusEstudiante;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SInscripcionTaller;

/**
 * ViewModel para la vista que muestra el catalogo de Profesores Acreditados
 * para el Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoEstudianteApto {

	
	/**
	 * Declaracion de servicios del ViewModel
	 * */
	@WireVariable
	private SEstudiante sestudiante;
	@WireVariable
	private SInscripcionTaller sinscripciontaller;
	
	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private List<Estudiante> listaEstudiante;
	private ListModelList<Estudiante> modeloEstudiante;
	private List<String> valores;

	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	
	private Estudiante selectedEstudiantes;
	private String texto;
	private String ventana;
	private String seleccion;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	private Window winCatalogoEstudianteApto;
	@Wire
	Textbox txtFiltroEstudiante;
	@Wire
	private Popup popOpciones;
	@Wire
	private Window win;

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

	public String getVentana() {
		return ventana;
	}

	public void setVentana(String ventana) {
		this.ventana = ventana;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	public List<Estudiante> getListaEstudiante() {
		return listaEstudiante;
	}

	public void setListaEstudiante(List<Estudiante> listaEstudiante) {
		this.listaEstudiante = listaEstudiante;
	}

	public SEstudiante getSestudiante() {
		return sestudiante;
	}

	public void setSestudiante(SEstudiante sestudiante) {
		this.sestudiante = sestudiante;
	}

	public Estudiante getSelectedEstudiantes() {
		return selectedEstudiantes;
	}

	public void setSelectedEstudiantes(Estudiante selectedEstudiantes) {
		this.selectedEstudiantes = selectedEstudiantes;
	}

	public SEstudiante getServicioEstudiante() {
		return sestudiante;
	}

	public void setServicioEstudiante(SEstudiante sestudiante) {
		this.sestudiante = sestudiante;
	}

	public ListModelList<Estudiante> getModeloEstudiante() {
		return modeloEstudiante;
	}

	public void setModeloEstudiante(ListModelList<Estudiante> modeloEstudiante) {
		this.modeloEstudiante = modeloEstudiante;
	}
	
	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * @param ventana variable que trae la informacion de que ventana solicita el catalogo
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component win,
			@ExecutionArgParam("estudiante") String ventana) {
		this.win = (Window) win;
		this.ventana = ventana;
		listaEstudiante =cargarEstudianteApto();
		modeloEstudiante = new ListModelList<Estudiante>(listaEstudiante);
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
	public void afterCompose(
			@ContextParam(ContextType.VIEW) Component winCatalogoEstudianteApto) {

		Selectors.wireComponents(winCatalogoEstudianteApto, this, false);
	}
	/**
	 * inserta en una lista todos aquellos estudiantes aptos 
	 * @return lista de estudiantes actos 
	 **/
	public List<Estudiante>cargarEstudianteApto(){
		List<Estudiante>estudiantes= new ArrayList<Estudiante>();
		List<Estudiante>aux=sestudiante.buscarPorStatus(StatusEstudiante.Apto.toString());
		List<InscripcionTaller> ins=new ArrayList<InscripcionTaller>();
		List<InscripcionTaller> inscripciones=sinscripciontaller.buscarTodo();
		switch (ventana) {
		case "RegistrarEstudianteApto":{
										if(inscripciones.size()!=0){
											for(Estudiante est:aux){
												System.out.println("aux");
												ins=sinscripciontaller.buscarPorEstudiante(est);
												if(ins.size()==0){
													System.out.println("add");
													estudiantes.add(est);
												}
											}
										}else{
											estudiantes=aux;
										}
										
									}
			
			break;
		case "Exonerar":{
				estudiantes=aux;
				}
			
			break;
			
		default: estudiantes=aux;
			break;
		}
		return estudiantes;
	}

	/**
	 * Envia el Estudiante Apto seleccionado
	 * a la ventana principal 
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void aceptar() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		if (ventana == "RegistrarEstudianteApto") {
			System.out.print("ventana");
			if (this.selectedEstudiantes != null) {
				win.setAttribute("estudiante", this.selectedEstudiantes);
				win.detach();
			} else {
				mensajeEmergente.advertenciaSeleccionarOpcion();
			}
		}
		else{
			if (ventana == "Exonerar") {
				System.out.print("ventana");
				if (this.selectedEstudiantes != null) {
					win.setAttribute("estudiante", this.selectedEstudiantes);
					win.detach();
				} else {
					mensajeEmergente.advertenciaSeleccionarOpcion();
				}
			}
		}

	}
	
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de Estudiantes Aptos con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoEstudianteApto}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "modeloEstudiante" })
	public void filtrarEstudiante() {
		List<Estudiante> aux = new ArrayList<Estudiante>();
		aux = listaEstudiante;
		modeloEstudiante.clear();

		if (seleccion == null || seleccion == "") {
				if (texto == "")
					modeloEstudiante.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstudiante.add(aux.get(i));

						}
					}
				}
			
		} else {
			if (seleccion == "Cédula") {
				if (texto == "")
					modeloEstudiante.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteCedula().toLowerCase()
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
							if (aux.get(i).getEstudianteNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloEstudiante.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Apellido") {
						if (texto == "")
							modeloEstudiante.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getEstudianteApellido()
										.toLowerCase().contains(texto.toLowerCase())) {
									modeloEstudiante.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Programa") {
							if (texto == "")
								modeloEstudiante.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getDireccionProgramae()
											.getDireccionNombre().toLowerCase()
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
		if (seleccion == "Nombre") {
			txtFiltroEstudiante.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroEstudiante.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido") {
					txtFiltroEstudiante.setPlaceholder("Apellido");
					popOpciones.close();
				} else {
					if (seleccion== "Programa") {
						txtFiltroEstudiante.setPlaceholder("Programa");
						popOpciones.close();
					}
				}
			}
		}

	}

	/**
	 * cierra el catalogo
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}
}
