package siagsce.modelo.data.maestros;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La clase Inscripcion al Taller representa las datos de la inscripcion al taller de induccion
 * de los estudiantes aptos para prestar su SCE en el Decanato de Ciencias y Tecnologia 
 * 
 * @author Iterator
 * 
*/

@Entity
@Table(name = "inscripcion_taller")
public class InscripcionTaller {

	/**
	 * Codigo Identificador único de la Inscripcion al Taller, generado autommáticamente.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "inscripcion_taller_codigo", nullable = false)
	private Integer inscripcionTallerCodigo;
	
	/**
	 * Fecha en que el estudiante realizo la inscripcion al taller
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "inscripcion_taller_fecha" ,nullable = false)
	private Date inscripcionTallerFecha;
	
	/** 
	 * Retorna el estatus de la inscripcion al taller
	 * @return estatus inscripcion
	 */
	public String getInscripcionTallerStatus() {
		return inscripcionTallerStatus;
	}

	/**
	 * Establece el estatus de la inscripcion al taller
	 * @param estatus inscripcion
	 */
	public void setInscripcionTallerStatus(String inscripcionTallerStatus) {
		this.inscripcionTallerStatus = inscripcionTallerStatus;
	}

	/**
	 * Status de la Inscripcion al Taller
	 * 
	 */
	@Column(name = "inscripcion_taller_status",length = 15,  nullable = false)
	private String inscripcionTallerStatus;


	/**
	 * Construye la nueva Inscripcion al Taller con parametros
	 * 
	 */
	public InscripcionTaller(Integer inscripcionTallerCodigo,
			Date inscripcionTallerFecha, String inscripcionTallerStatus) {
		super();
		this.inscripcionTallerCodigo = inscripcionTallerCodigo;
		this.inscripcionTallerFecha = inscripcionTallerFecha;
		this.inscripcionTallerStatus = inscripcionTallerStatus;
	}

	/**
	 * Estudiante inscrito en el taller
	 * 
	 */
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "estudiante_cedula", referencedColumnName = "estudiante_cedula")
	private Estudiante estudiante;

	/**
	 * Taller asociado a dicha inscripcion al taller
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "taller_codigo", referencedColumnName = "taller_codigo")
	private Taller taller;

	/**
	 * Construye la nueva Inscripcion al Taller
	 * 
	 */
	public InscripcionTaller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye la nueva Inscripcion al Taller con parametros
	 * 
	 */
	public InscripcionTaller(Estudiante estudiante, Taller taller) {
		super();
		
		this.estudiante = estudiante;
		this.taller = taller;
	}

	/** 
	 * Retorna el codigo identificador unico de la inscripcion al taller
	 * @return codigo de inscripcion al taller
	 */
	public Integer getInscripcionTallerCodigo() {
		return inscripcionTallerCodigo;
	}

	/**
	 * Establece el codigo identificador unico de la inscripcion al taller
	 * @param codigo de inscripcion al taller
	 */
	public void setInscripcionTallerCodigo(Integer inscripciontallerCodigo) {
		this.inscripcionTallerCodigo = inscripciontallerCodigo;
	}

	/** 
	 * Retorna el estudiante inscrito en el taller
	 * @return inscripcion al taller del estudiante
	 */
	public Estudiante getEstudiante() {
		return estudiante;
	}

	/**
	 * Establece el estudiante inscrito en el taller
	 * @param inscripcion al taller del estudiante
	 */
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	/** 
	 * Retorna el taller asociado a la inscripcion al taller
	 * @return taller de la inscripcion
	 */
	public Taller getTaller() {
		return taller;
	}
	/**
	 * Establece el taller asociado a la inscripcion al taller
	 * @param taller de la inscripcion
	 */
	public void setTaller(Taller taller) {
		this.taller = taller;
	}

	/** 
	 * Retorna la fecha en que se realizo la inscripcion al taller
	 * @return fecha inscripcion
	 */
	public Date getInscripcionTallerFecha() {
		return inscripcionTallerFecha;
	}

	/**
	 * Establece la fecha en que se realizo la inscripcion al taller
	 * @param la fecha en que se realizo la inscripcion al taller
	 */
	public void setInscripcionTallerFecha(Date inscripcionTallerFecha) {
		this.inscripcionTallerFecha = inscripcionTallerFecha;
	}

	
}
