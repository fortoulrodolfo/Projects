package siagsce.viewmodel.reportes.abiertos;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.zk.ui.Sessions;
/**
 *Clase declara para la inicialización de variables e implementación 
 *de los parámetros necesarios para el diseño gráfico de la carta de
 *culminación de Servicicio Comunitario Estudiantil 
 * 
 * @author Iterator
 */

public class ReportConfigCartaCulminacion {
	
	/**
	 * Declaración de variables
	 */
	private String source;
	private Map<String, Object> parametros;
	private JRBeanCollectionDataSource dataSource;
	private ReportType type;
	
	
	/**
	 * Método constructor con parámetro en el cual se le asignan las ruta de ubicación
	 * al parámetro de la imagen usada en el encabezado de la carta de culminación
	 * de Servicio Comunitario Estudiantil
	 * @param ruta, ubicación de la imagen usada para el encabezado de carta de culminación
	 * de SCE
	 */
	public ReportConfigCartaCulminacion(String ruta) {
		parametros = new HashMap<String, Object>();
		parametros.put("encabezado", "../../images/reportes/EncabezadoSC.jpg");
		source = Sessions.getCurrent().getWebApp().getRealPath(ruta);
	}
	
	
	/**
	 * Métodos set y get
	 */
	public ReportType getType() {
		return type;
	}

	public void setType(ReportType selectedReportType) {
		this.type = selectedReportType;
	}

	public String getSource() {
		return source;
	}

	public Map<String, Object> getParameters() {
		return parametros;
	}

	public JRBeanCollectionDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(JRBeanCollectionDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
