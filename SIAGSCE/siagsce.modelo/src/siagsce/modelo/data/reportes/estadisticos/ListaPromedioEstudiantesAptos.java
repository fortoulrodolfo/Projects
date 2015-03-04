/** @Modelo Listado PromedioEstudiantesAptos - Reporte Estad�stico
 * Modelo usado en la generaci�n del reporte de Promedio de estudiantes 
 * aptos en el SCE
 * @author Iterator
 */

package siagsce.modelo.data.reportes.estadisticos;

import java.math.BigDecimal;


/**
 * Declaraci�n de variables.
 */
public class ListaPromedioEstudiantesAptos {
	private BigDecimal  catidadEstudiantes;
 
	
	/**
	 * Declaraci�n del constructor
	 */
	public ListaPromedioEstudiantesAptos(BigDecimal  catidadEstudiantes) {
		super();
		this.catidadEstudiantes = catidadEstudiantes;
	}

	
	/**
	 * Declaraci�n de set y get
	 */
	public BigDecimal  getCatidadEstudiantes() {
		return catidadEstudiantes;
	}

	public void setCatidadEstudiantes(BigDecimal  catidadEstudiantes) {
		this.catidadEstudiantes = catidadEstudiantes;
	}
	

}
