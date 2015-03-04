/** @Modelo Listado PorcentajeEstudiantesPorUnidades - Reporte Estadístico
 * Modelo usado en la generación del reporte de porcentajes de 
 * estudiantes por unidades de crédito el SCE por dirección de programa
 * @author Iterator
 */

package siagsce.modelo.data.reportes.estadisticos;

import java.math.BigInteger;


/**
 * Declaración de variables.
 */
public class PorcentajeEstudiantesPorUnidades {

	private Integer totalestudiantes;
	private BigInteger promedio5060, promedio6070, promedio7080,promedio8090, promedio90100,promedio100;



	/**
	 * Declaración del constructor
	 * 
	 */
	public PorcentajeEstudiantesPorUnidades(Integer totalestudiantes,
			BigInteger promedio5060, BigInteger promedio6070,
			BigInteger promedio7080, BigInteger promedio8090,
			BigInteger promedio90100, BigInteger promedio100) {
		super();
		this.totalestudiantes = totalestudiantes;
		this.promedio5060 = promedio5060;
		this.promedio6070 = promedio6070;
		this.promedio7080 = promedio7080;
		this.promedio8090 = promedio8090;
		this.promedio90100 = promedio90100;
		this.promedio100 = promedio100;
	}

	/**
	 * Declaración de set y get
	 */

	public Integer getTotalestudiantes() {
		return totalestudiantes;
	}

	public void setTotalestudiantes(Integer totalestudiantes) {
		this.totalestudiantes = totalestudiantes;
	}



	public BigInteger getPromedio5060() {
		return promedio5060;
	}



	public void setPromedio5060(BigInteger promedio5060) {
		this.promedio5060 = promedio5060;
	}



	public BigInteger getPromedio6070() {
		return promedio6070;
	}



	public void setPromedio6070(BigInteger promedio6070) {
		this.promedio6070 = promedio6070;
	}



	public BigInteger getPromedio7080() {
		return promedio7080;
	}



	public void setPromedio7080(BigInteger promedio7080) {
		this.promedio7080 = promedio7080;
	}



	public BigInteger getPromedio8090() {
		return promedio8090;
	}



	public void setPromedio8090(BigInteger promedio8090) {
		this.promedio8090 = promedio8090;
	}



	public BigInteger getPromedio90100() {
		return promedio90100;
	}



	public void setPromedio90100(BigInteger promedio90100) {
		this.promedio90100 = promedio90100;
	}

	public BigInteger getPromedio100() {
		return promedio100;
	}

	public void setPromedio100(BigInteger promedio100) {
		this.promedio100 = promedio100;
	}

	


}
