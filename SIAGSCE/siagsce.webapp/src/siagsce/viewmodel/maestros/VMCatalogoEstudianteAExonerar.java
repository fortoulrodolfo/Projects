/* @VMCatalogoEstudianteAExonerar
 * Inicialización de Variables e implementación de todas las funcionalidades y métodos
 * de la interfaz <CatalogoEstudianteAExonerar.zul>
 * @author Maryelis Méndez/Jhezir Canela
 * Version 1.0, 29/01/2014
 * Version 1.1, 30/01/2014
 * Version 1.2, 01/02/2014
 * Version 1.3, 10/02/2014

 */



package siagsce.viewmodel.maestros;
import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.servicio.maestros.SEstudiante;

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
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import java.util.Map;
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCatalogoEstudianteAExonerar {
	
	

	//*****************INIC. VARIABLES*****************
	@Wire
	private Window win;
	private @WireVariable
	SEstudiante sestudiante;
	private List<Estudiante> listaEstudiante;
	private ListModelList<Estudiante> modeloEstudiante;
	private Estudiante selectedEstudiantes;
	private String texto;
	
	
	//*****METODO QUE CARGA ELEMENTOS EN LA VENTANA*****
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ContextParam(ContextType.COMPONENT) Component win) {
		Selectors.wireComponents(view,this, false);
		this.win = (Window) win;
		listaEstudiante = sestudiante.buscarPorStatus("Apto");
		modeloEstudiante = new ListModelList<Estudiante>(listaEstudiante);
		modeloEstudiante.setMultiple(true);
	}
	
	
	//******METODO QUE TOMA DATOS DEL ESTUDIANTE DEL*******
	//******CATALOGO Y LOS CARGA EN LA VENTANA(GRID)*******
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void guardar() {
		 
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		 
		try {
		 Map args = new HashMap();
	        args.put("returnvalue1",this.selectedEstudiantes.getEstudianteCedula());
	        args.put("returnvalue2",this.selectedEstudiantes.getEstudianteNombre());
	        args.put("returnvalue3",this.selectedEstudiantes.getEstudianteApellido());
	        args.put("returnvalue4",this.selectedEstudiantes.getEstudianteTelefono());
	        args.put("returnvalue5",this.selectedEstudiantes.getEstudianteDireccion());
	        args.put("returnvalue6",this.selectedEstudiantes.getDireccionProgramae().getDireccionNombre());
	    
	        BindUtils.postGlobalCommand(null, null, "obtenerEstudiantesExonerarEstudiante", args);
	        salir();
		} catch (NullPointerException e) {
			mensajeEmergente.advertenciaSeleccionarOpcion();
		}
	}
	
	
	
	//***METODO QUE FILTRA LOS DATOS DEL LISTADO***
	@Command
	@NotifyChange({ "modeloEstudiante" })
	public void filtrarEstudiante() {
		List<Estudiante> aux = new ArrayList<Estudiante>();
		aux = listaEstudiante;
		try {
			modeloEstudiante.clear();
			if (texto == "")
				modeloEstudiante.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getEstudianteNombre().toLowerCase().contains(texto)) {
						modeloEstudiante.add(aux.get(i));
					}
				}
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
	}
	

	
	//***METODO QUE LIMPIA DATOS Y CIERRA LA VENTANA***
		@Command
		public void salir() {
			 win.detach();
		}
		
	//Inicio*********GETTERS AND SETTERS***************
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
//fin***********GETTERS AND SETTERS****************

}