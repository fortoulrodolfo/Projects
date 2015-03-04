package siagsce.viewmodel.reportes.abiertos;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.reportes.abiertos.ListaTaller;
import siagsce.modelo.servicio.maestros.STaller;
import siagsce.modelo.servicio.reportes.abiertos.SListadoTaller;
import siagsce.viewmodel.reportes.abiertos.ReportConfig;
import siagsce.viewmodel.reportes.abiertos.ReportType;

/**
 * ViewModel para la vista del reporte de Consulta de 
 * Talleres Ejecutados y Ejecutandose en SCE.
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReporteTalleres {
	
	/** 
	 * Declaración de Variables del ViewModel
	 * */
	private String nombre;
	private String descripcion; 
	private Date fecha_culminacion; 
	private Date fecha_inicio; 
	private String lugar; 
	private String modalidad;
	private String status=null;
	@Wire
	private Jasperreport report;
	@Wire
	private Window winReporteTalleresEjecutandose;
	@Wire
	private Combobox cmbEstatus;
	MensajesEmergentes mensajes=new MensajesEmergentes();
	/** 
	 * Declaración de lista
	 * */
	private ListModelList<ListaTaller> modeloTalleresEjecuntadoseEjecutados;
	
	/** 
	 * ESTO PARA FORMATO DE FECHA Y PARA OBTENER LA DEL SISTEMA
	 * */
	private Date dateFormat;
	SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
	Date fechaActual = new Date();
    String fechaSistema = formateador.format(fechaActual);
    
    /** 
	 * Declaración de Componentes de la vista
	 * */
	@Wire
	private Button btnCancelarConsutaEstudianteH;
	
	ReportType reportType = new ReportType("PDF", "pdf");
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/abiertos/reportTalleresEjecutados.jasper";
	
	/** 
	 * Lista que permite llenar el combo para elegir el formato
	 * */
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList( 
					new ReportType("Word (RTF)", "rtf"), 
					new ReportType("CSV", "csv"), 
					new ReportType("OpenOffice (ODT)", "odt")));

	/** 
	 * Declaración de los servicios
	 * */
	@WireVariable
	STaller sTaller;

	@WireVariable
	SListadoTaller sListadoTaller;
	
	/** 
	 * Métodos set y get que me permite manipular la vista .zul
	 * */
	public List<String> getTallerEstatus() {
		return EstatusTaller.getTallerEstatus();
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ListModelList<ReportType> getReportTypesModel() {
		return reportTypesModel;
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
		


	public ListModelList<ListaTaller> getModeloTalleresEjecuntadoseEjecutados() {
		return modeloTalleresEjecuntadoseEjecutados;
	}

	public void setModeloTalleresEjecuntadoseEjecutados(
			ListModelList<ListaTaller> modeloTalleresEjecuntadoseEjecutados) {
		this.modeloTalleresEjecuntadoseEjecutados = modeloTalleresEjecuntadoseEjecutados;
	}

	/**
	 * Método que inicializa el modelo
	 * @param win,ventana la cual esta asociada a este viewmodel
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component winReporteTalleresEjecutandose) {
		this.winReporteTalleresEjecutandose = (Window) winReporteTalleresEjecutandose;
		modeloTalleresEjecuntadoseEjecutados = new ListModelList<ListaTaller>();
		
	
	}
	/**
	  * Se inicializa la variable para el filtro de estudiantes, asi como támbien 
	  * se ejecuta el metodo que permite la conexión del viewmodel con la vista.
	  * @param view,ventana la cual esta asociada a este viewmodel
	  */
	@NotifyChange({"report","winReporteTalleresEjecutandose"})
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
		this.winReporteTalleresEjecutandose = (Window) winReporteTalleresEjecutandose;
		Selectors.wireComponents(view, this, false);
		cmbEstatus.setSelectedIndex(-1);
	}
	
	/** 
	 * Método que llena los datos de forma dinámica
	 * */
	@Command("llenarListaTalleres")
	public void llenarListaTalleres() {
		modeloTalleresEjecuntadoseEjecutados.clear();
		 if(status==null){
			   status="Ejecutados";
		   }
		if(status.equals("Ejecutados")){
			String condicion=" where "+"'"+fechaSistema+"'"+" > t.taller_fecha_inicio and "+"'"+fechaSistema+"'"+" >t.taller_fecha_culminacion";
			List<ListaTaller>  listTaller = sListadoTaller.buscarTallerEjecutandose(condicion);
			if(listTaller.size()==0){
				mensajes.errorCamposVacios();	
				}
			modeloTalleresEjecuntadoseEjecutados.addAll(listTaller);
			
			
		}else{
			String condicion=" where "+"'"+fechaSistema+"'"+" BETWEEN t.taller_fecha_inicio and t.taller_fecha_culminacion";
			List<ListaTaller>  listTaller = sListadoTaller.buscarTallerEjecutandose(condicion);
			if(listTaller.size()==0){
				mensajes.errorCamposVacios();	
				}
			System.out.println(condicion);
			modeloTalleresEjecuntadoseEjecutados.addAll(listTaller);
		}
	
	
	}
	
	/** 
	 * Método que carga el reporte
	 * */
		@Command("showReport")
		@NotifyChange({"reportConfig"})
		public void showReport() {
			llenarListaTalleres();
			reportConfig = new ReportConfig(ruta);
			reportConfig.setType(reportType);
			reportConfig.getParameters().put("estatus", this.status);
			if(status.equals("Ejecutados")){
				reportConfig.getParameters().put("tipo", "Ejecutados");	
			}else{
				reportConfig.getParameters().put("tipo", "Ejecutándose");
			}
			reportConfig.getParameters().put("lista", new JRBeanCollectionDataSource(modeloTalleresEjecuntadoseEjecutados));
			reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloTalleresEjecuntadoseEjecutados));
		}
		
		/**
		 * Método que cierra la ventana
		 */
		@Command
		public void cerrar() {
			winReporteTalleresEjecutandose.detach();
		}
	
}
