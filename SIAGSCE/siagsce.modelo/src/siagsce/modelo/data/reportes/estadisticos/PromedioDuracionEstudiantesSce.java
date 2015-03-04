/** @Modelo Listado PromedioDuraci�nEstudiantesSce - Reporte Estad�stico
 * Modelo usado en la generaci�n del reporte de promedio de duraci�n de los 
 * estudiantes en el SCE
 * @author Iterator
 */

package siagsce.modelo.data.reportes.estadisticos;

import java.math.BigDecimal;



/**
 * Declaraci�n de variables.
 */
public class PromedioDuracionEstudiantesSce {

	private String direccionPrograma;
	private BigDecimal promedioDias;
	private BigDecimal promedioMeses;

	/**
	 * Declaraci�n del constructor
	 */
	public PromedioDuracionEstudiantesSce(String direccionPrograma,
			BigDecimal promedioDias, BigDecimal promedioMeses) {
		super();
		this.direccionPrograma = direccionPrograma;
		this.promedioDias = promedioDias;
		this.promedioMeses = promedioMeses;
	}

	
	/**
	 * Declaraci�n de set y get
	 */
	
	public String getDireccionPrograma() {
		return direccionPrograma;
	}

	public void setDireccionPrograma(String direccionPrograma) {
		this.direccionPrograma = direccionPrograma;
	}

	public BigDecimal getPromedioDias() {
		return promedioDias;
	}

	public void setPromedioDias(BigDecimal promedioDias) {
		this.promedioDias = promedioDias;
	}


	public BigDecimal getPromedioMeses() {
		return promedioMeses;
	}


	public void setPromedioMeses(BigDecimal promedioMeses) {
		this.promedioMeses = promedioMeses;
	}

}
