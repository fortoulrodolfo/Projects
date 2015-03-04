package siagsce.viewmodel.maestros;

import java.util.HashMap;
import java.util.Map;
import org.zkoss.zk.ui.Sessions;
import  net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportConfig {
	private String source;
	private Map<String, Object> parametros;
	private JRBeanCollectionDataSource dataSource;
	private ReportType type;

	public ReportConfig() {
		parametros = new HashMap<String, Object>();
	 	parametros.put("ICON_LEFT_HEADER", "../../images/reportes/logosce.JPG");
		parametros.put("ICON_RIGHT_HEADER", "../../images/reportes/LogoSIAGSCEconNombre.png");
		parametros.put("ICON_FOOTER", "../../images/reportes/LogoDCyT.jpg");
		Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/siagsce/reportes/maestros/ListadoPersonalizado.jasper");
	
	}
	
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
