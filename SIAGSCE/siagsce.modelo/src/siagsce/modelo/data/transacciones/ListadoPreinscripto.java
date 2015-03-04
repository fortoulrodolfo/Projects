package siagsce.modelo.data.transacciones;

import java.math.BigInteger;

public class ListadoPreinscripto {
	private String estudianteCedula;
	private String estudianteNombre;
	private String estudianteApellido;
	private Integer estudianteUnidadesAprobadas;
	private BigInteger horasAcumuladas;
	private Integer preinscripcion;
	
	public Integer getPreinscripcion() {
		return preinscripcion;
	}
	public void setPreinscripcion(Integer preinscripcion) {
		this.preinscripcion = preinscripcion;
	}
	public ListadoPreinscripto(String estudianteCedula,
			String estudianteNombre, String estudianteApellido,
			Integer preinscripcion,   Integer estudianteUnidadesAprobadas,
			BigInteger horasAcumuladas) {
		super();
		this.estudianteCedula = estudianteCedula;
		this.estudianteNombre = estudianteNombre;
		this.estudianteApellido = estudianteApellido;
		this.estudianteUnidadesAprobadas = estudianteUnidadesAprobadas;
		this.horasAcumuladas = horasAcumuladas;
		this.preinscripcion=preinscripcion;
	}
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
	public String getEstudianteApellido() {
		return estudianteApellido;
	}
	public void setEstudianteApellido(String estudianteApellido) {
		this.estudianteApellido = estudianteApellido;
	}
	public Integer getEstudianteUnidadesAprobadas() {
		return estudianteUnidadesAprobadas;
	}
	public void setEstudianteUnidadesAprobadas(Integer estudianteUnidadesAprobadas) {
		this.estudianteUnidadesAprobadas = estudianteUnidadesAprobadas;
	}
	public BigInteger getHorasAcumuladas() {
		return horasAcumuladas;
	}
	public void setHorasAcumuladas(BigInteger horasAcumuladas) {
		this.horasAcumuladas = horasAcumuladas;
	}
	
	
	
}
