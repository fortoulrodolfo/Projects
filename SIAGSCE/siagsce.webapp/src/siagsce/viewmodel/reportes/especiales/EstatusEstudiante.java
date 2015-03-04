package siagsce.viewmodel.reportes.especiales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *Clase declara para uso en el reporte de historial de desempe�o de los estudiantes 
 * en el Servicio Comunitario Estudiantil utilizada en el viewModelHistorialEstudiante
 * 
 * @author Iterator
 */

public class EstatusEstudiante {
	
	/**
	 * Declaraci�n de una Lista estatus de estudiante en SCE, 
	 * la cual contiene de manera est�tica  los estatus de los estudiantes
	 * dentro de SCE.
	 */
	private static List<String> estudianteEstatus = new ArrayList<String>();
	
	static{
		estudianteEstatus.add("Apto");
		estudianteEstatus.add("Acreditado");
		estudianteEstatus.add("Aprobado");
	
	}
	
	/**
	 * M�todo get de la lista de estatus de estudiantes
	 * @return, lista de estatus de estudiantes
	 */
	public static final List<String> getEstudianteEstatus() {
		return new ArrayList<String>(estudianteEstatus);
	}
}
