/** @Modelo PorcentajeFinalizacionSCE - Reporte Estadístico
 * Modelo usado en la generación del reporte de porcentajes de 
 * estudiantes que finalizaron SCE en las diferentes direcciones
 * de programa.
 * @author Iterator
 */
package siagsce.modelo.data.reportes.estadisticos;


public class PorcentajesFinalizacionSCE {
	
	/**
	 * Declaración de variables.
	 */
	private Integer codigoCarrera,totalEstudiantesFinalizaronSCE;
	private String nombreCarrera;
	private Number porcentajeEstudiantesFinalizaronSCE;

	/**
	 * Declaración del constructor
	 */
	public PorcentajesFinalizacionSCE(Integer codigoCarrera,
			 String nombreCarrera,Integer totalEstudiantesFinalizaronSCE,
			Number porcentajeEstudiantesFinalizaronSCE) {
		super();
		this.codigoCarrera = codigoCarrera;
		this.totalEstudiantesFinalizaronSCE = totalEstudiantesFinalizaronSCE;
		this.nombreCarrera = nombreCarrera;
		this.porcentajeEstudiantesFinalizaronSCE = porcentajeEstudiantesFinalizaronSCE;
	}
	
	/**
	 * Declaración de set y get
	 */
	public Integer getCodigoCarrera() {
		return codigoCarrera;
	}
	public void setCodigoCarrera(Integer codigoCarrera) {
		this.codigoCarrera = codigoCarrera;
	}
	public Integer getTotalEstudiantesFinalizaronSCE() {
		return totalEstudiantesFinalizaronSCE;
	}
	public void setTotalEstudiantesFinalizaronSCE(
			Integer totalEstudiantesFinalizaronSCE) {
		this.totalEstudiantesFinalizaronSCE = totalEstudiantesFinalizaronSCE;
	}
	public String getNombreCarrera() {
		return nombreCarrera;
	}
	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}
	public Number getPorcentajeEstudiantesFinalizaronSCE() {
		return porcentajeEstudiantesFinalizaronSCE;
	}
	public void setPorcentajeEstudiantesFinalizaronSCE(
			Number porcentajeEstudiantesFinalizaronSCE) {
		this.porcentajeEstudiantesFinalizaronSCE = porcentajeEstudiantesFinalizaronSCE;
	}

}
