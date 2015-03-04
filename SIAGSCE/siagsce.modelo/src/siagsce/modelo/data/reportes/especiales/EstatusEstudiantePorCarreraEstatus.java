/** @Modelo EstatusEstudiantePorCarrera - Reporte Especial
 * Modelo usado en la generación del reporte Actividades
 * asignadas a estudiantes en el SCE
 * @author Iterator
 */

package siagsce.modelo.data.reportes.especiales;


/**
 * Declaración de variables.
 */

public class EstatusEstudiantePorCarreraEstatus {
	
	private String estudianteCedula,estudianteNombre,esudianteApellido;
    private Number estudiantesUnidadesAprobadas,porcentajeUnidadesCreditosAprobadas,estudianteHorasPrestadas,porcentajeHorasPrestadas;
	
    
    /**
	 * Declaración del constructor
	 */
    public EstatusEstudiantePorCarreraEstatus(String estudianteCedula,
			String estudianteNombre, String esudianteApellido,
			Number estudiantesUnidadesAprobadas,
			Number porcentajeUnidadesCreditosAprobadas,
			Number estudianteHorasPrestadas, Number porcentajeHorasPrestadas) {
		super();
		this.estudianteCedula = estudianteCedula;
		this.estudianteNombre = estudianteNombre;
		this.esudianteApellido = esudianteApellido;
		this.estudiantesUnidadesAprobadas = estudiantesUnidadesAprobadas;
		this.porcentajeUnidadesCreditosAprobadas = porcentajeUnidadesCreditosAprobadas;
		this.estudianteHorasPrestadas = estudianteHorasPrestadas;
		this.porcentajeHorasPrestadas = porcentajeHorasPrestadas;
	}
    
    
    /**
	 * Declaración de set y get
	 */
	public String getEstudianteCedula() {
		return estudianteCedula;
	}
	public void setEstudianteCedula(String estudianteCedula) {
		this.estudianteCedula = estudianteCedula;
	}
	public String getEstudianteNombre() {
		return estudianteNombre;
	}
	public void setEstudianteNombre(String estudianteNombre) {
		this.estudianteNombre = estudianteNombre;
	}
	public String getEsudianteApellido() {
		return esudianteApellido;
	}
	public void setEsudianteApellido(String esudianteApellido) {
		this.esudianteApellido = esudianteApellido;
	}
	public Number getEstudiantesUnidadesAprobadas() {
		return estudiantesUnidadesAprobadas;
	}
	public void setEstudiantesUnidadesAprobadas(Number estudiantesUnidadesAprobadas) {
		this.estudiantesUnidadesAprobadas = estudiantesUnidadesAprobadas;
	}
	public Number getPorcentajeUnidadesCreditosAprobadas() {
		return porcentajeUnidadesCreditosAprobadas;
	}
	public void setPorcentajeUnidadesCreditosAprobadas(
			Number porcentajeUnidadesCreditosAprobadas) {
		this.porcentajeUnidadesCreditosAprobadas = porcentajeUnidadesCreditosAprobadas;
	}
	public Number getEstudianteHorasPrestadas() {
		return estudianteHorasPrestadas;
	}
	public void setEstudianteHorasPrestadas(Number estudianteHorasPrestadas) {
		this.estudianteHorasPrestadas = estudianteHorasPrestadas;
	}
	public Number getPorcentajeHorasPrestadas() {
		return porcentajeHorasPrestadas;
	}
	public void setPorcentajeHorasPrestadas(Number porcentajeHorasPrestadas) {
		this.porcentajeHorasPrestadas = porcentajeHorasPrestadas;
	}
	
}
