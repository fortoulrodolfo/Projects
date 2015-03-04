package siagsce.viewmodel.reportes.abiertos;

import java.util.Arrays;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Window;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.reportes.abiertos.ListaEstudiante;
import siagsce.modelo.data.reportes.abiertos.ListaProyectoxProfResponsable;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.reportes.abiertos.SListadoEstudiante;
import siagsce.modelo.servicio.reportes.abiertos.SListadoProyectoxProfResponsable;

/**
 * ViewModel para la vista del reporte de proyectos por profesor responsable,
 * Filtrado por Dirección de Programa, Proyecto.
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteProyectoProfResponsable {
	
	/**
	 * Declaración de variables
	 */
	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/abiertos/ReportProfResponsable.jasper";
	private Window win;
	
	
	/**
	 * Lista que permite llenar el combo para elegir el formato 
	 */
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(
					new ReportType("Word (RTF)", "rtf"),  
					new ReportType("CSV", "csv"), 
					new ReportType("OpenOffice (ODT)", "odt")));

	/**
	 * Declaración de los servicios
	 */
	@WireVariable
	SProfesor sprofesor;
	
	
	@WireVariable
	SListadoProyectoxProfResponsable SListadoProyectoxProfResponsable;

	
	/**
	 * Declaración de los modelos a ser utilizados en los listados y combos
	 */
	private ListModelList<Profesor> modeloProfesor;
	private List<Profesor> profesor;
	private ListModelList<ListaProyectoxProfResponsable> modeloProyectoProfResponsable;
	
	/**
	 * Declaración de variables donde sera guardada las selecciones
	 */
	private Profesor selectedProfesor;
	
		
	
	
	/**
	 * Método que inicializa los modelos
	 *  y carga el programa desde la BD y el combo de programas
	 * @param win,ventana la cual esta asociada a este viewmodel
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		profesor =sprofesor.buscarTodo();
		modeloProfesor = new ListModelList<Profesor>(profesor);	
		modeloProyectoProfResponsable = new ListModelList<ListaProyectoxProfResponsable>();
	}
	
	/**
	 *Método que realiza la búsqueda de forma dinámica
	 */
	@Command("llenarListaDinamica")
	@NotifyChange({"selectedProfesor"})
	public void llenarListaDinamica() {
		modeloProyectoProfResponsable.clear();
        String condicion="where pr.proyecto_codigo = prf.proyecto_codigo";
		//Guarda la Seleccion que haga el usuario en la variable codigoDireccionPrograma
        if(selectedProfesor!=null){
        	
        	condicion=condicion+" and prf.profesor_cedula="+"'"+selectedProfesor.getProfesorCedula()+"'";
        }
		List<ListaProyectoxProfResponsable> listaProyectoxProfResponsables = SListadoProyectoxProfResponsable.buscarProyectoxProfResponsableDinamico(condicion);
	     
		modeloProyectoProfResponsable.addAll(listaProyectoxProfResponsables);
		win.getFellow("tab").getFellow("tabs").getFellow("pestana2").setVisible(false);
		Tab tab=(Tab) win.getFellow("tab").getFellow("tabs").getFellow("pestana1");
		tab.setSelected(true);


	}
	
	/**
	 * Metodo que carga la lista de estudiantes asignado a la Direccion de programa 
	 * que se seleccione en el combo y le pasa al componete jasperReport lo 
	 * necesariopara pintarse en la pantalla .zul*/
		@Command("showReport")
		@NotifyChange({"reportConfig","prueba"})
		public void showReport() {
			reportConfig = new ReportConfig(ruta);
			reportConfig.setType(reportType);
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloProyectoProfResponsable));

		}
		
		/**
		 * Método que cierra la ventana
		 */
		@Command
		public void cerrar() {
			win.detach();
		}
		
		/**
		 * Métodos set y get que me permite manipular la vista .zul
		 * @return
		 */
		public ListModelList<ReportType> getReportTypesModel() {
			return reportTypesModel;
		}

		public SProfesor getSproferos() {
			return sprofesor;
		}

		public void setSproferos(SProfesor sproferos) {
			this.sprofesor = sproferos;
		}

		public SListadoProyectoxProfResponsable getsListadoProyectoxProfResponsable() {
			return SListadoProyectoxProfResponsable;
		}

		public void setsListadoProyectoxProfResponsable(
				SListadoProyectoxProfResponsable sListadoProyectoxProfResponsable) {
			this.SListadoProyectoxProfResponsable = SListadoProyectoxProfResponsable;
		}

		public ListModelList<Profesor> getModeloProfesor() {
			return modeloProfesor;
		}

		public void setModeloProfesor(ListModelList<Profesor> modeloProfesor) {
			this.modeloProfesor = modeloProfesor;
		}

		public List<Profesor> getProfesor() {
			return profesor;
		}

		public void setProfesor(List<Profesor> profesor) {
			this.profesor = profesor;
		}

		public ListModelList<ListaProyectoxProfResponsable> getModeloProyectoProfResponsable() {
			return modeloProyectoProfResponsable;
		}

		public void setModeloProyectoProfResponsable(
				ListModelList<ListaProyectoxProfResponsable> modeloProyectoProfResponsable) {
			this.modeloProyectoProfResponsable = modeloProyectoProfResponsable;
		}

		public Profesor getSelectedProfesor() {
			return selectedProfesor;
		}

		public void setSelectedProfesor(Profesor selectedProfesor) {
			this.selectedProfesor = selectedProfesor;
		}

		public ReportConfig getReportConfig() {
			return reportConfig;
		}
		
		public ReportType getReportType() {
			return reportType;
		}

		public void setReportType(ReportType reportType) {
			this.reportType = reportType;
		}


}
