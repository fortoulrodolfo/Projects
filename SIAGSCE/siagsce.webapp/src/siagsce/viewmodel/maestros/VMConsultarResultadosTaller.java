/*@VMConsultarResultadosTaller
 * Inicialización de Variables e implementación de todas las funcionalidades y métodos
 * de la interfaz <ConsultarResultadosTaller.zul>
 * @author Delba López
 * Version 1.0, 02/02/2014
 * Version 1.1, 20/02/2014
 * */

package siagsce.viewmodel.maestros;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
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

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.ListadoEstudiantesCulminados;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SInscripcionTaller;
import siagsce.modelo.servicio.maestros.STaller;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConsultarResultadosTaller {
//**************************************************************************
	// **************Inicializacion de variables**************
	@WireVariable
	SEstudiante sestudiante;
	
	@WireVariable
	STaller staller;
	
	@WireVariable
	SInscripcionTaller sinscripciontaller;
	@Wire
	Window win;
	@Wire
	private String nombreTaller;
	@Wire
	private String descripcionInscripcionTaller;
	@Wire
	private String fechaInicioTaller;
	@Wire
	private String fechaCulminacionTaller;
	@Wire
	private Popup popOpciones;
	private String texto;
	@Wire
	Textbox txtFiltroEstudiante;
	private String seleccion;
	private List<String> valores;
	
	
	//Estudiantes
	Estudiante estudiante;
	ListModelList<Estudiante> modeloEstudianteTaller;
	List<Estudiante> listaEstudiante;
	private Estudiante selectedEstudiante;
	
	ListModelList<InscripcionTaller> modeloInscripcionTaller;
	List<InscripcionTaller> listaInscripcion;

	
	//Talleres
	Taller taller;
	ListModelList<Taller> modeloTalleres;
	List<Taller> listaTalleres;
	
//****************************************************************************
	SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
	
//Metodo para cargar elementos al darle vida a la ventana
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;		
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		valores.add("Programa");

	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		this.win = (Window) win;
		Selectors.wireComponents(view, this, false);
	}

//************************************************************************************
	// **********************Inicio********Getter and setters******

	public ListModelList<Estudiante> getmodeloEstudianteTaller() {
		return modeloEstudianteTaller;
	}

	public void setmodeloEstudianteTaller(
			ListModelList<Estudiante> modeloEstudianteTaller) {
		this.modeloEstudianteTaller = modeloEstudianteTaller;
	}

	public Estudiante getSelectedEstudiante() {
		return selectedEstudiante;
	}

	public void setSelectedEstudiante(Estudiante selectedEstudiante) {
		this.selectedEstudiante = selectedEstudiante;
	}
	public String getNombreTaller() {
		return nombreTaller;
	}

	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}

	public String getFechaInicioTaller() {
		return fechaInicioTaller;
	}

	public void setFechaInicioTaller(String fechaInicioTaller) {
		this.fechaInicioTaller = fechaInicioTaller;
	}
	public String getFechaCulminacionTaller() {
		return fechaCulminacionTaller;
	}

	public void setFechaCulminacionTaller(String fechaCulminacionTaller) {
		this.fechaCulminacionTaller = fechaCulminacionTaller;
	}
	public String getDescripcionInscripcionTaller() {
		return descripcionInscripcionTaller;
	}

	public void setDescripcionInscripcionTaller(String descripcionInscripcionTaller) {
		this.descripcionInscripcionTaller = descripcionInscripcionTaller;
	}
	
	
	
	// ***********************Fin**********Getter and setters********************

	public ListModelList<InscripcionTaller> getModeloInscripcionTaller() {
		return modeloInscripcionTaller;
	}

	public void setModeloInscripcionTaller(
			ListModelList<InscripcionTaller> modeloInscripcionTaller) {
		this.modeloInscripcionTaller = modeloInscripcionTaller;
	}

	public List<InscripcionTaller> getListaInscripcion() {
		return listaInscripcion;
	}

	public void setListaInscripcion(List<InscripcionTaller> listaInscripcion) {
		this.listaInscripcion = listaInscripcion;
	}

	public Popup getPopOpciones() {
		return popOpciones;
	}

	public void setPopOpciones(Popup popOpciones) {
		this.popOpciones = popOpciones;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Textbox getTxtFiltroEstudiante() {
		return txtFiltroEstudiante;
	}

	public void setTxtFiltroEstudiante(Textbox txtFiltroEstudiante) {
		this.txtFiltroEstudiante = txtFiltroEstudiante;
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

	//Metodos
	@Command
 	@NotifyChange({"modeloEstudianteTaller", "nombreTaller", "fechaInicioTaller", "fechaCulminacionTaller", "descripcionInscripcionTaller"})
	
	//*Metodo que permite cargar el catalogo de talleres*
	public void MostrarCatalogoTaller(){
		 String ventana="ConsultarResultadosTaller";
		 final HashMap<String, Object> map = new HashMap<String, Object>();
	     map.put("taller",ventana);
	     Window comp=(Window) Executions.createComponents("/WEB-INF/vista/view/view.maestros/CatalogoTaller.zul", null, map);
	     comp.doModal();
	     Taller taller = (Taller)comp.getAttribute("taller");
	     if(taller!=null){
			  obtenerTallerInscritosConsulta(taller, sinscripciontaller.buscarEstudiantesDeUnTallerInactivo(taller));
	     }
	     }
	
 	@NotifyChange({"modeloEstudianteTaller", "nombreTaller", "fechaInicioTaller", "fechaCulminacionTaller", "descripcionInscripcionTaller"})
    public void obtenerTallerInscritosConsulta( Taller taller, List<Estudiante> estudiantesInscritosTaller)
    {
 		this.taller=taller;
		nombreTaller = taller.getTallerNombre();
 		descripcionInscripcionTaller = taller.getTallerDescripcion();
 		fechaInicioTaller =  sd.format(taller.getTallerFechaInicio());
 		fechaCulminacionTaller =sd.format(taller.getTallerFechaCulminacion());
		listaEstudiante = estudiantesInscritosTaller;
		/*listaInscripcion = sinscripciontaller.buscarPorTaller(this.taller);
		modeloInscripcionTaller = new ListModelList<InscripcionTaller>(listaInscripcion);*/
		modeloEstudianteTaller = new ListModelList<Estudiante>(listaEstudiante);
		modeloEstudianteTaller.setMultiple(true);
		
 	
    }	

	//*Metodo que cierra la ventana*
	@Command
    public void cerrarVentana() {	
		win.detach();
    }
	
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de estudiantes ,con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * */
	@Command
	@NotifyChange({ "modeloEstudianteTaller" })
	public void filtrarEstudiante() {
		List<Estudiante> aux = new ArrayList<Estudiante>();
		aux = listaEstudiante;
		modeloEstudianteTaller.clear();
		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modeloEstudianteTaller.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getEstudianteNombre().toLowerCase()
							.contains(texto.toLowerCase())) {
						modeloEstudianteTaller.add(aux.get(i));

					}
				}
			}

		} else {
			if (seleccion == "Nombre") {
				if (texto == "")
					modeloEstudianteTaller.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstudianteTaller.add(aux.get(i));

						}
					}
				}
				
			} 
			else {
				if (seleccion == "Cédula") {
					if (texto == "")
						modeloEstudianteTaller.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getEstudianteCedula().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloEstudianteTaller.add(aux.get(i));

							}
						}
					}
				}
				else{
					if (seleccion == "Apellido") {
						if (texto == "")
							modeloEstudianteTaller.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getEstudianteApellido().toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloEstudianteTaller.add(aux.get(i));

								}
							}
						}
				   } else{
						if (seleccion == "Programa") {
							if (texto == "")
								modeloEstudianteTaller.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getDireccionProgramae().getDireccionNombre().toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloEstudianteTaller.add(aux.get(i));

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
}
