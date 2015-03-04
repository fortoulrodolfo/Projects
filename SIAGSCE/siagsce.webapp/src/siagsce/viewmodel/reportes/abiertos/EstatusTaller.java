package siagsce.viewmodel.reportes.abiertos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *Clase declara para uso en el reporte de talleres en Ejecuatados y Ejecutandose
 * en el Servicio Comunitario Estudiantil utilizada en el viewModelTalleres
 * 
 * @author Iterator
 */
public class EstatusTaller {
	
	/**
	 * Declaración de una Lista estatus de taller, la cual contiene de manera
	 * estática  los estatus Ejecutados y Ejecutandose.
	 */
	private static List<String> estatusTaller = new ArrayList<String>();
	
	static{
		estatusTaller.add("Ejecutados");
		estatusTaller.add("Ejecutándose ");
	
	}
	
	/**
	 * Método get de la lista de estatus de taller
	 * @return, lista de estatus de taller
	 */
	public static final List<String> getTallerEstatus() {
		return new ArrayList<String>(estatusTaller);
	}
}
