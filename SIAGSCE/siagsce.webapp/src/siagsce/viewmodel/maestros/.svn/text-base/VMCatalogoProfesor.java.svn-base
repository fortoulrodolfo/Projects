package siagsce.viewmodel.maestros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusProfesor;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;

/**
 * ViewModel para la vista que muestra el catalogo de Profesores Aptos
 * para la Acreditacion al Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoProfesor {
	
	/**
	 * Declaracion de servicios del ViewModel
	 * */
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	SDirectorPrograma sdirector;

	/**
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private ListModelList<Profesor> modeloProfesor;
	private List<Profesor> listProfesor;
	private List<String> valores;
	
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private Profesor selectedProfesor;
	private String nombre;
	private String ventana;
	private String seleccion;
	
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	Window win;
	@Wire
	Textbox txtFiltroProfesor;

	@Wire
	Listbox lbxSeleccionParametro;
	
	@Wire 
	Popup popOpciones;
	
	/** 
	 *Setter y Getter
	 **/
	public List<String> getValores() {
		return valores;
	}

	public void setValores(List<String> valores) {
		this.valores = valores;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}

	public String getVentana() {
		return ventana;
	}

	public void setVentana(String ventana) {
		this.ventana = ventana;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Profesor getselectedProfesor() {
		return selectedProfesor;
	}

	public void setselectedProfesor(Profesor selectedProfesor) {
		this.selectedProfesor = selectedProfesor;
	}

	public ListModelList<Profesor> getModeloProfesor() {
		return modeloProfesor;
	}

	public void setModeloProfesor(ListModelList<Profesor> modeloProfesor) {
		this.modeloProfesor = modeloProfesor;
	}
	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * @param ventana variable que trae la informacion de que ventana solicita el catalogo
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win,
			@ExecutionArgParam("profesor") String ventana) {
		this.win = (Window) win;
		this.ventana = ventana;
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		MensajeBox.doSetTemplate();

		 if(ventana.equals("configurarOpcionesUsuario")){
		       	listProfesor = sprofesor.buscarTodo();
		       }else{
		listProfesor =CargarProfesorApto();
		       }
		modeloProfesor = new ListModelList<Profesor>(listProfesor);
//		modeloProfesor.setMultiple(true);
	}

	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		txtFiltroProfesor.setPlaceholder("Nombre");
		

	}
	/**
	 * Envia Un Profesor Apto seleccionado
	 * a la ventana principal
	 * */
		 public List<Profesor> CargarProfesorApto(){
			 List<Profesor>profesores=new ArrayList<Profesor>();
			 List<DirectorPrograma>directores=sdirector.buscarTodo();
			 List<Profesor>aux=sprofesor.buscarStatus(StatusProfesor.Apto.toString());
			 DirectorPrograma dir;
			 switch (ventana) {
			case "RegistrarProfesores":{
				                          if(directores.size()!=0){
				                        	 for(Profesor prof:aux) {
				                        		 boolean valido=false;
					                        	  for(DirectorPrograma director:directores){
					                        		  
					                        		 if(director.getProfesor().getProfesorCedula().contains(prof.getProfesorCedula())){
					                        			 valido=true;
					                        		 } 
					                        	  }	
					                        	  if(!valido){
					                        		
					                        		  profesores.add(prof);
					                        	  }
					                        	 }
				                        	 }else{
				                        		 	profesores=aux;
				                        	 }
				                          }
				break;
			default: profesores=aux;
				break;
			}
			 return profesores;
		 }
		 /**
		 * Envia Un Profesor Apto seleccionado
		 * a la ventana principal
		 * */	 
	@Command
	public void aceptar() {
		MensajesEmergentes mensajes= new MensajesEmergentes();
		switch (ventana) {
		case "RegistrarProfesores":
			if (this.selectedProfesor != null) {
				win.setAttribute("profesor", this.selectedProfesor);
				win.detach();
			} else {
				mensajes.advertenciaSeleccionarOpcion();
			}
			break;
		case "AcreditarProfesor":
			if (this.selectedProfesor != null) {
				win.setAttribute("profesor", this.selectedProfesor);
				win.detach();
			} else {
				mensajes.advertenciaSeleccionarOpcion();
			}
			break;
		case "configurarOpcionesUsuario":
			if (this.selectedProfesor != null) {
				win.setAttribute("profesor", this.selectedProfesor);
				win.detach();
			} else {
				mensajes.advertenciaSeleccionarOpcion();
			}
			break;
		default:
			Messagebox.show(
					"La ventana que solicita el catalogo no esta definida",
					"¡Informacion!", Messagebox.OK, Messagebox.INFORMATION);
			break;
		}
	}
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de profesores acreditados con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * @link {@link VMCatalogoProfesor}{@link #seleccionFiltro()}
	 * */
	@Command
	@NotifyChange({ "profesormodel" })
	public void filtrarProfesor() {
		List<Profesor> aux = new ArrayList<Profesor>();
		aux = listProfesor;
		modeloProfesor.clear();

		if (seleccion == null || seleccion == "") {
			if (nombre == "")
				modeloProfesor.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getProfesorNombre().toLowerCase()
							.contains(nombre.toLowerCase())) {
						modeloProfesor.add(aux.get(i));
					}
				}
			}
		} else {
			if (seleccion == "Cédula") {
				if (nombre == "")
					modeloProfesor.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesorCedula().toLowerCase()
								.contains(nombre.toLowerCase())) {
							modeloProfesor.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Nombre") {
					if (nombre == "")
						modeloProfesor.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProfesorNombre().toLowerCase()
									.contains(nombre.toLowerCase())) {
								modeloProfesor.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion== "Apellido") {
						if (nombre == "")
							modeloProfesor.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProfesorApellido()
										.toLowerCase().contains(nombre.toLowerCase())) {
									modeloProfesor.add(aux.get(i));

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
			txtFiltroProfesor.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion== "Cédula") {
				txtFiltroProfesor.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion== "Apellido")
					txtFiltroProfesor.setPlaceholder("Apellido");
					popOpciones.close();
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
