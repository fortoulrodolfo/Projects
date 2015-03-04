package siagsce.viewmodel.reportes.abiertos;

/**
 * Clase declarada para la inicializaci�n de variables e implementaci�n
 * de las mismas para el manejo de diferentes formatos de archivos en la 
 * generaci�n de los reportes
 * @author Iterator
 *
 */
public class ReportType {
	
	/**
	 * Declaraci�n de variables
	 */
	private String value;
	private String label;
	
	/**
	 * M�todo constructor con par�metros que inicializa 
	 * dichos par�metros utilizados para diferentes tipos de formatos de archivos 
	 * @param label
	 * @param value
	 */
	public ReportType(String label, String value) {
		super();
		this.value = value;
		this.label = label;
	}

	/**
	 * M�todos set y get 
	 */
	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}