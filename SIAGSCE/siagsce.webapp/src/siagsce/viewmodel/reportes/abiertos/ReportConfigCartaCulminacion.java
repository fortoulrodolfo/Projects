package siagsce.viewmodel.reportes.abiertos;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.zk.ui.Sessions;
/**
 *Clase declara para la inicializaci�n de variables e implementaci�n 
 *de los par�metros necesarios para el dise�o gr�fico de la carta de
 *culminaci�n de Servicicio Comunitario Estudiantil 
 * 
 * @author Iterator
 */

public class ReportConfigCartaCulminacion {
	
	/**
	 * Declaraci�n de variables
	 */
	private String source;
	private Map<String, Object> parametros;
	private JRBeanCollectionDataSource dataSource;
	private ReportType type;
	
	
	/**
	 * M�todo constructor con par�metro en el cual se le asignan las ruta de ubicaci�n
	 * al par�metro de la imagen usada en el encabezado de la carta de culminaci�n
	 * de Servicio Comunitario Estudiantil
	 * @param ruta, ubicaci�n de la imagen usada para el encabezado de carta de culminaci�n
	 * de SCE
	 */
	public ReportConfigCartaCulminacion(String ruta) {
		parametros = new HashMap<String, Object>();
		parametros.put("encabezado", "../../images/reportes/EncabezadoSC.jpg");
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
