package siagsce.viewmodel.maestros;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.general.StatusTaller;
import siagsce.modelo.servicio.maestros.SInscripcionTaller;
import siagsce.modelo.servicio.maestros.STaller;
/**
 * ViewModel para la vista que muestra el catalogo de los
 * Talleres del curso de induccion del Servicio Comunitario
 *  Estudiantil
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoTaller {

	/**Declaracion de las variables de tipo servicio
	 **/
	@WireVariable
	STaller sTaller;
	@WireVariable
	SInscripcionTaller sinscripciontaller;
	
	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private List<Taller> listTaller;
	private ListModelList<Taller> modeloTaller;
	private List<String> valores;

	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private String ventana;
	private String texto;
	private String seleccion;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	Window win;
	@Wire
	Textbox txtFiltroTaller;
	@Wire
	private Popup popOpciones;

	/**Set y Get
	 **/
	public Textbox getTxtFiltroTaller() {
		return txtFiltroTaller;
	}

	public void setTxtFiltroTaller(Textbox txtFiltroTaller) {
		this.txtFiltroTaller = txtFiltroTaller;
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

	public String getVentana() {
		return ventana;
	}

	public void setVentana(String ventana) {
		this.ventana = ventana;
	}

	private Taller selectedTaller;

	public List<Taller> getListTaller() {
		return listTaller;
	}

	public void setListTaller(List<Taller> listTaller) {
		this.listTaller = listTaller;
	}

	public ListModelList<Taller> getModeloTaller() {
		return modeloTaller;
	}

	public void setModeloTaller(ListModelList<Taller> modeloTaller) {
		this.modeloTaller = modeloTaller;
	}

	public Taller getSelectedTaller() {
		return selectedTaller;
	}

	public void setSelectedTaller(Taller selectedTaller) {
		this.selectedTaller = selectedTaller;
	}
	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * @param ventana variable que trae la informacion de que ventana solicita el catalogo
	 */
	
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win,
			@ExecutionArgParam("taller") String ventana) {
		this.win = (Window) win;
		this.ventana = ventana;
		
		//si la ventana anterior es planificar taller buscar los talleres que no tengan estudiantes inscritos
		if (ventana.equals("PlanificarTaller")){
			listTaller = sTaller.buscarTalleresActivosSinInscritos();
			modeloTaller = new ListModelList<Taller>(listTaller);
		}
		//si la ventana anterior es evaluacion de taller buscar los talleres cuya fecha de culminacion haya pasado
		else if (ventana.equals("EvaluacionDelTaller")){
			Date fechaActual = new Date();
			listTaller = sTaller.buscarTalleresFechaCulminacionPasada(fechaActual);
			modeloTaller = new ListModelList<Taller>(listTaller);
		}
		//si la ventana anterior es consultar resultados taller buscar los talleres con estatus inactivo
		else if (ventana.equals("ConsultarResultadosTaller")){
			listTaller = sTaller.buscarTodo(StatusTaller.Inactivo.toString());
			modeloTaller = new ListModelList<Taller>(listTaller);	
		}
		//si la ventana anterior es inscripcion al taller buscar los talleres donde la fecha de finalizacion de inscripcion no haya pasado
				else if (ventana.equals("InscripcionAlTaller")){
					Date fechaActual = new Date();
					listTaller = sTaller.buscarTalleresActivosConFechaInscripcionValida(fechaActual);
					modeloTaller = new ListModelList<Taller>(listTaller);	
				}
		//sino busca todos los talleres existentes con estatus activo
		else{
			listTaller = sTaller.buscarTodo(StatusTaller.Activo.toString());
			modeloTaller = new ListModelList<Taller>(listTaller);
		}
		
		MensajeBox.doSetTemplate();
		valores = new ArrayList<String>();
		valores.add("Modalidad");
		valores.add("Descripción");
		valores.add("Responsable");
	}

	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		txtFiltroTaller.setPlaceholder("Descripción");
	}
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de talleres ,con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoTaller}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "modeloTaller" })
	public void filtrarTaller() {
		List<Taller> aux = new ArrayList<Taller>();
		aux = listTaller;
		modeloTaller.clear();
		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modeloTaller.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getTallerNombre().toLowerCase()
							.contains(texto.toLowerCase())) {
						modeloTaller.add(aux.get(i));

					}
				}
			}

		} else {
			if (seleccion == "Descripción") {
				if (texto == "")
					modeloTaller.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getTallerDescripcion().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloTaller.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Modalidad") {
					if (texto == "")
						modeloTaller.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getTallerModalidad().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloTaller.add(aux.get(i));

							}
						}
					}
				}
				else{
					if (seleccion == "Responsable") {
						if (texto == "")
							modeloTaller.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getTallerProfesorResponsable().getProfesorNombre().toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloTaller.add(aux.get(i));

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
		if (seleccion== "Descripción") {
			txtFiltroTaller.setPlaceholder("Descripción");
			popOpciones.close();
		} else {
			if (seleccion== "Modalidad") {
				txtFiltroTaller.setPlaceholder("Modalidad");
				popOpciones.close();
			}
			else{
				if (seleccion== "Responsable") {
					txtFiltroTaller.setPlaceholder("Responsable");
					popOpciones.close();
				}
			}
		}
	}

	/**Metodo que envia a la pantalla anterior el taller que se seleccione.
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void aceptar() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		// si se selecciona un taller
		if (this.selectedTaller != null) {
			win.setAttribute("taller", this.selectedTaller);
			win.detach();
		} else {
			//sino se selecciona ningun taller
			mensajeEmergente.advertenciaSeleccionarOpcion();
		}

	}

	/** Metodo que cierra la ventana
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}
}