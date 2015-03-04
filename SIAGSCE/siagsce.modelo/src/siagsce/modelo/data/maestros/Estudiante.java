package siagsce.modelo.data.maestros;


import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * La clase Estudiante representa las datos de los estudiantes que
 * pertenecen al Decanato de Ciencias y Tecnología.
 * 
 * @author Iterator
 * 
*/

@Entity
@Table(name = "estudiante")
public class Estudiante {

	/**
	 * Codigo Identificador único del estudiante, generado autommáticamente. En este caso su cedula.
	 * 
	 */
	@Id
	@Column(name = "estudiante_cedula", unique = true,  nullable = false)
	private String estudianteCedula;

	/**
	 * Nombre del estudiante 
	 */
	@Column(name = "estudiante_nombre", length = 100, nullable = false)
	private String estudianteNombre;

	/**
	 * Apellido del Estudiante
	 */
	@Column(name = "estudiante_apellido", length = 100, nullable = false)
	private String estudianteApellido;

	/**
	 * E-mail del Estudiante
	 */
	@Column(name = "estudiante_email", length = 50, nullable = false)
	private String estudianteEmail;

	/**
	 * Telefono del Estudiante
	 */
	@Column(name = "estudiante_telefono", length = 25, nullable = false)
	private String estudianteTelefono;

	/**
	 * Direccion del Estudiante
	 */
	@Column(name = "estudiante_direccion", length = 50, nullable = false)
	private String estudianteDireccion;

	/**
	 * Status del Estudiante
	 */
	@Column(name = "estudiante_status", length = 15, nullable = false)
	private String estudianteStatus;

	/**
	 * Unidades Aprobadas del Estudiante (Carga Academica Aprobada)
	 */
	@Column(name = "estudiante_unidades_aprobadas", length = 10, nullable = false)
	private Integer estudianteUnidadesAprobadas;

	/**
	 * Fecha del Estudiante
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "estudiante_fecha",nullable = false)
	private Date estudianteFecha;

	/**
	 * Direccion de PRograma a la que pertenece el Estudiante
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "direccion_codigo", referencedColumnName = "direccion_codigo",nullable = false)
	private DireccionPrograma direccionProgramae;

	/**
	 * Inscripcion al Taller del Estudiante
	 */
	@OneToMany( mappedBy = "estudiante")
	private List<InscripcionTaller> InscripcionTaller;
	
	/**
	 * Exoneracion de Estudiante
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "estudianteExonerado")
	private Exonerado exonerado;

	/**
	 * Construye un nuevo Estudiante
	 */
	public Estudiante() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un nuevo Estudiante con parametros
	 */
	public Estudiante(String estudianteCedula, String estudianteNombre,
			String estudianteApellido, String estudianteEmail,
			String estudianteTelefono, String estudianteDireccion,
			String estudianteStatus, Integer estudianteUnidadesAprobadas,DireccionPrograma direccionProgramae) {
		super();
		this.estudianteCedula = estudianteCedula;
		this.estudianteNombre = estudianteNombre;
		this.estudianteApellido = estudianteApellido;
		this.estudianteEmail = estudianteEmail;
		this.estudianteTelefono = estudianteTelefono;
		this.estudianteDireccion = estudianteDireccion;
		this.estudianteStatus = estudianteStatus;
		this.estudianteUnidadesAprobadas = estudianteUnidadesAprobadas;
		this.direccionProgramae=direccionProgramae;
	}

	/** 
	 * Retorna el status del estudiante
	 * @return status estudiante
	 */
	public String getEstudianteStatus() {
		return estudianteStatus;
	}

	/**
	 * Establece el status del estudiante 
	 * @param status estudiante
	 */
	public void setEstudianteStatus(String estudianteStatus) {
		this.estudianteStatus = estudianteStatus;
	}

	/** 
	 * Retorna la cedula del estudiante
	 * @return cedula del estudiante
	 */
	public String getEstudianteCedula() {
		return estudianteCedula;
	}

	/**
	 * Establece la cedula del estudiante
	 * @param cedula del estudiante 
	 */
	public void setEstudianteCedula(String estudianteCedula) {
		this.estudianteCedula = estudianteCedula;
	}

	/** 
	 * Retorna el nombre del estudiante
	 * @return nombre del estudiante
	 */
	public String getEstudianteNombre() {
		return estudianteNombre;
	}

	/**
	 * Establece el nombre del estudiante
	 * @param nombre del estudiante
	 */
	public void setEstudianteNombre(String estudianteNombre) {
		this.estudianteNombre = estudianteNombre;
	}

	/** 
	 * Retorna el apellido del estudiante
	 * @return apellido del estudiante
	 */
	public String getEstudianteApellido() {
		return estudianteApellido;
	}

	/**
	 * Establece el apellido del estudiante 
	 * @param apellido del estudiante
	 */
	public void setEstudianteApellido(String estudianteApellido) {
		this.estudianteApellido = estudianteApellido;
	}

	/** 
	 * Retorna el email del estudiante
	 * @return email del estudiante
	 */
	public String getEstudianteEmail() {
		return estudianteEmail;
	}

	/**
	 * Establece el email del estudiante
	 * @param email del estudiante
	 */
	public void setEstudianteEmail(String estudianteEmail) {
		this.estudianteEmail = estudianteEmail;
	}

	/** 
	 * Retorna el telefono del estudiante
	 * @return telefono del estudiante
	 */
	public String getEstudianteTelefono() {
		return estudianteTelefono;
	}

	/**
	 * Establece el telefono del estudiante
	 * @param telefono del estudiante
	 */
	public void setEstudianteTelefono(String estudianteTelefono) {
		this.estudianteTelefono = estudianteTelefono;
	}

	/** 
	 * Retorna la direccion del estudiante
	 * @return direccion del estudiante
	 */
	public String getEstudianteDireccion() {
		return estudianteDireccion;
	}

	/**
	 * Establece la direccion del estudiante
	 * @param direccion del estudiante 
	 */
	public void setEstudianteDireccion(String estudianteDireccion) {
		this.estudianteDireccion = estudianteDireccion;
	}

	/** 
	 * Retorna las unidades aprobadas del estudiante
	 * @return unidades aprobadas
	 */
	public Integer getEstudianteUnidadesAprobadas() {
		return estudianteUnidadesAprobadas;
	}

	/**
	 * Establece las unidades aprobadas del estudiante 
	 * @param unidades aprobadas
	 */
	public void setEstudianteUnidadesAprobadas(
			Integer estudianteUnidadesAprobadas) {
		this.estudianteUnidadesAprobadas = estudianteUnidadesAprobadas;
	}

	/** 
	 * Retorna la direccion de programa al que pertenece el estudiante
	 * @return direccion de programa del estudiante
	 */
	public DireccionPrograma getDireccionProgramae() {
		return direccionProgramae;
	}

	/**
	 * Establece la direccion de programa al que pertenece el estudiante
	 * @param direccion de programa del estduiante
	 */
	public void setDireccionProgramae(DireccionPrograma direccionProgramae) {
		this.direccionProgramae = direccionProgramae;
	}

	/** 
	 * Retorna la inscripcion al taller del estudiante
	 * @return inscripcion al taller del estudiante
	 */
	public List<InscripcionTaller> getInscripcionTaller() {
		return InscripcionTaller;
	}

	/**
	 * Establece la inscripcion al taller del estudiante
	 * @param inscripcion al taller del estudiante
	 */
	public void setInscripcionTaller(List<InscripcionTaller> inscripcionTaller) {
		InscripcionTaller = inscripcionTaller;
	}

	/** 
	 * Retorna la fecha del estudiante
	 * @return fecha
	 */
	public Date getEstudianteFecha() {
		return estudianteFecha;
	}

	/**
	 * Establece la fecha del estudiante
	 * @param fecha
	 */
	public void setEstudianteFecha(Date estudianteFecha) {
		this.estudianteFecha = estudianteFecha;
	}

}
