/** @Modelo TopCincoInscripcionProyecto - Reporte Estad�stico
 * Modelo usado en la generaci�n del reporte Top Cinco de
 * Proyectos mas demandados para inscribirse en el SCE
 * @author Iterator
 */

package siagsce.modelo.data.reportes.estadisticos;

import java.math.BigInteger;


/**
 * Declaraci�n de variables.
 */
public class TopCincoInscripcionProyecto {

	private BigInteger totalEstudiantesPreinscritos;
	private BigInteger totalEstudiantesInscritos;
	private String codigoProyecto, nombreProyecto;


	/**
	 * Declaraci�n del constructor
	 */
	public TopCincoInscripcionProyecto(BigInteger totalEstudiantesPreinscritos,
			BigInteger totalEstudiantesInscritos, String codigoProyecto,
			String nombreProyecto) {
		super();
		this.totalEstudiantesPreinscritos = totalEstudiantesPreinscritos;
		this.totalEstudiantesInscritos = totalEstudiantesInscritos;
		this.codigoProyecto = codigoProyecto;
		this.nombreProyecto = nombreProyecto;
	}



	/**
	 * Declaraci�n de set y get
	 */
	public BigInteger getTotalEstudiantesPreinscritos() {
		return totalEstudiantesPreinscritos;
	}

	public void setTotalEstudiantesPreinscritos(
			BigInteger totalEstudiantesPreinscritos) {
		this.totalEstudiantesPreinscritos = totalEstudiantesPreinscritos;
	}

	public BigInteger getTotalEstudiantesInscritos() {
		return totalEstudiantesInscritos;
	}

	public void setTotalEstudiantesInscritos(BigInteger totalEstudiantesInscritos) {
		this.totalEstudiantesInscritos = totalEstudiantesInscritos;
	}

	public String getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(String codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}


}
