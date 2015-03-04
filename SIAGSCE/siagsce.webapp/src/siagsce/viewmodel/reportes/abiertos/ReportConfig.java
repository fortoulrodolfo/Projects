package siagsce.viewmodel.reportes.abiertos;

import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Sessions;
import  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 *Clase declara para la inicializaci�n de variables e implementaci�n 
 *de los par�metros necesarios para el dise�o gr�fico de los reportes
 * 
 * @author Iterator
 */

public class ReportConfig {
	
	/**
	 * Declaraci�n de variables
	 */
	private String source;
	private Map<String, Object> parametros;
	private JRBeanCollectionDataSource dataSource;
	private ReportType type;

	/**
	 * M�todo constructor con par�metro en el cual se le asignan las rutas de ubicaci�n
	 * a cada uno de los par�metros como im�genes utilizadas en el dise�o de los
	 * reportes
	 * @param ruta,localizaci�n de los parametros utilizados en el dise�o de 
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
	 * M�todos set y get
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
