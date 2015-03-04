package siagsce.viewmodel.reportes.abiertos;

/**
 * Clase declarada para la inicialización de variables e implementación
 * de las mismas para el manejo de diferentes formatos de archivos en la 
 * generación de los reportes
 * @author Iterator
 *
 */
public class ReportType {
	
	/**
	 * Declaración de variables
	 */
	private String value;
	private String label;
	
	/**
	 * Método constructor con parámetros que inicializa 
	 * dichos parámetros utilizados para diferentes tipos de formatos de archivos 
	 * @param label
	 * @param value
	 */
	public ReportType(String label, String value) {
		super();
		this.value = value;
		this.label = label;
	}

	/**
	 * Métodos set y get 
	 */
	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
}