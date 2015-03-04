package siagsce.viewmodel.maestros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.List;




import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
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
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.general.StatusProfesor;
import siagsce.modelo.servicio.maestros.SProfesor;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoProfesorApto {
	/**
	 * inicializacion de variables y listas
	 * 
	 * @author SIAGSCE
	 */
	@WireVariable
	SProfesor sprofesor;
	@Wire
	Window win;
	@Wire
	Textbox txtFiltroProfesor;
	@Wire
	Listbox lbxSeleccionParametro;
	@Wire 
	Popup popOpciones;
	
	ListModelList<Profesor> modeloProfesorNoAcreditado;
	List<Profesor> listprofesor;
	private Profesor selectedProfesor;
	private String texto;
	private String seleccion;
	private List<String> valores;
	/**
	 * Metodo para cargar modelo de profesor no acreditado y darle vida a la ventana
	 * 
	 * @author SIAGSCE
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		listprofesor = sprofesor.buscarStatus(StatusProfesor.Apto.toString());
		modeloProfesorNoAcreditado = new ListModelList<Profesor>(listprofesor);
		modeloProfesorNoAcreditado.setMultiple(true);
		valores = new ArrayList<String>();
		valores.add("cedula");
		valores.add("nombre");
		valores.add("apellido");
		
	}
	
	/**
	 * Metodo para filtrar el profesor apto en el textbox del catalogo
	 * 
	 * @author SIAGSCE
	 */
	@Command
	@NotifyChange({ "modeloProfesorNoAcreditado" })
	public void filtrarProfesor() {
		List<Profesor> aux = new ArrayList<Profesor>();
		aux = listprofesor;
		modeloProfesorNoAcreditado.clear();

		if (seleccion == null || seleccion == "") {
				if (texto == "")
					modeloProfesorNoAcreditado.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesorNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloProfesorNoAcreditado.add(aux.get(i));

						}
					}
				}
			
		} else {
			if (seleccion.toLowerCase() == "cedula") {
				if (texto == "")
					modeloProfesorNoAcreditado.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesorCedula().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloProfesorNoAcreditado.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion.toLowerCase() == "nombre") {
					if (texto == "")
						modeloProfesorNoAcreditado.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProfesorNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloProfesorNoAcreditado.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion.toLowerCase() == "apellido") {
						if (texto == "")
							modeloProfesorNoAcreditado.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProfesorApellido()
										.toLowerCase().contains(texto.toLowerCase())) {
									modeloProfesorNoAcreditado.add(aux.get(i));

								}
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Metodo que permite la conexion entre componentes despues de estar la ventana compuesta
	 * 
	 * @author SIAGSCE
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component win){
		
		Selectors.wireComponents(win, this, false);
	}
	
	/**
	 * Metodo para obtiene el item seleccionado  del combobox para filtrar al profesor
	 * 
	 * @author SIAGSCE
	 */

	@Command
	public void seleccionFiltro() {
		System.out.print(seleccion);
		if (seleccion.toLowerCase() == "nombre") {
			txtFiltroProfesor.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion.toLowerCase() == "cedula") {
				txtFiltroProfesor.setPlaceholder("Cedula");
				popOpciones.close();
			} else {
				if (seleccion.toLowerCase() == "apellido") {
					txtFiltroProfesor.setPlaceholder("Apellido");
					popOpciones.close();
				} 
			}
		}

	}
	/**
	 * Metodo para seleccionar un profesor del catalogo de profesores a acreditar 
	 * y enviarlo a la ventana padre "RegistrarProfesorAcreditado"
	 * @author SIAGSCE
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void aceptar() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		try {
		 Map args = new HashMap();
	       
	        args.put("returnvalue1",this.selectedProfesor.getProfesorCedula());
	        args.put("returnvalue2",this.selectedProfesor.getProfesorNombre());
	        args.put("returnvalue3",this.selectedProfesor.getProfesorApellido());
	        args.put("returnvalue4",this.selectedProfesor.getProfesorTelefono());
	        args.put("returnvalue5",this.selectedProfesor.getProfesorDireccion());
	        args.put("returnvalue6",this.selectedProfesor.getProfesorEmail());
	        
	        
	        BindUtils.postGlobalCommand(null, null, "obtenerProfesorApto", args);
	        salir();
		} catch (NullPointerException e) {
			mensajeEmergente.advertenciaSeleccionarOpcion();
		}
	}
	/**
	 * metodo para cerrar la ventana
	 * 
	 * @author SIAGSCE
	 */
	@Command
	public void salir() {
		win.detach();
	}
	/**
	 * Getter and setters
	 * 
	 * @author SIAGSCE
	 */

	public ListModelList<Profesor> getModeloProfesorNoAcreditado() {
		return modeloProfesorNoAcreditado;
	}

	public void setModeloProfesorNoAcreditado(
			ListModelList<Profesor> modeloProfesorNoAcreditado) {
		this.modeloProfesorNoAcreditado = modeloProfesorNoAcreditado;
	}

	public Profesor getSelectedProfesor() {
		return selectedProfesor;
	}

	public void setSelectedProfesor(Profesor selectedProfesor) {
		this.selectedProfesor = selectedProfesor;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
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

}
