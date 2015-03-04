package siagsce.viewmodel.reportes.abiertos;

import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Sessions;
import  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 *Clase declara para la inicialización de variables e implementación 
 *de los parámetros necesarios para el diseño gráfico de los reportes
 * 
 * @author Iterator
 */

public class ReportConfig {
	
	/**
	 * Declaración de variables
	 */
	private String source;
	private Map<String, Object> parametros;
	private JRBeanCollectionDataSource dataSource;
	private ReportType type;

	/**
	 * Método constructor con parámetro en el cual se le asignan las rutas de ubicación
	 * a cada uno de los parámetros como imágenes utilizadas en el diseño de los
	 * reportes
	 * @param ruta,localización de los parametros utilizados en el diseño de 
	 * los reportes
	 */
	public ReportConfig(String ruta) {
		parametros = new HashMap<String, Object>();
	    parametros.put("ICON_LEFT_HEADER", "../../images/reportes/LogoDCyT.jpg");
		parametros.put("ICON_RIGHT_HEADER", "../../images/reportes/logosce.JPG");
	    parametros.put("ICON_FOOTER", "../../images/reportes/LogoSIAGSCEconNombre.png");
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
