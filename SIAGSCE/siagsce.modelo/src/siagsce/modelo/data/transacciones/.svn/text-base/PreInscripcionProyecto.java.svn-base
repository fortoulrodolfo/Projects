package siagsce.modelo.data.transacciones;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Proyecto;

/**
 * La clase PreinscripcionProyecto representa a la Preinscripcion del estudiante
 * en un proyecto.
 * 
 * @author Iterator
 * 
 */

@Entity
@Table(name = "preinscripcion_proyecto")
public class PreInscripcionProyecto {

	/**
	 * Codigo identificador unico de la Prenscripcion en el proyecto, generado
	 * automaticamente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "preinscripcion_proyecto_codigo", unique = true, nullable = false)
	private Integer preinscripcionCodigo;

	/**
	 * Fecha dada a la preincripcion proyecto.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "preinscripcion_proyecto_fecha", nullable = false)
	private Date preinscripcionProyectoFecha;

	/**
	 * Status dado a la preincripcion proyecto que puede tomar valores Activo,
	 * En Espera e Inactivo.
	 */
	@Column(name = "preinscripcion_proyecto_status", length = 15, nullable = false)
	private String preinscripcionStatus;

	/**
	 * Estudiante dado a la preincripcion proyecto.
	 */
	@JoinColumn(name = "estudiante_cedula", referencedColumnName = "estudiante_cedula", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Estudiante estudiante;

	/**
	 * preincripcion proyecto que despues se puede convertir en una inscripcio
	 * al proyecto.
	 */
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "preInscripcion")
	private InscripcionProyecto inscripcionProyecto;

	/**
	 * Proyecto dado a la preincripcion proyecto.
	 */
	@ManyToOne
	@JoinColumn(name = "proyecto_codigo", nullable = false)
	private Proyecto proyectop;

	/**
	 * Construye una nueva inscripcion al proyecto.
	 */
	public PreInscripcionProyecto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye una nueva inscripcion al proyecto con todos sus parametros.
	 */
	public PreInscripcionProyecto(Date preinscripcionProyectoFecha,
			String preinscripcionStatus, Estudiante estudiante,
			Proyecto proyectop) {
		super();
		this.preinscripcionProyectoFecha = preinscripcionProyectoFecha;
		this.preinscripcionStatus = preinscripcionStatus;
		this.estudiante = estudiante;
		this.proyectop = proyectop;
	}

	public String getPreinscripcionStatus() {
		return preinscripcionStatus;
	}

	public void setPreinscripcionStatus(String preinscripcionStatus) {
		this.preinscripcionStatus = preinscripcionStatus;
	}

	/**
	 * Retorna el codigo unico  de la preinscripcion proyecto.
	 * 
	 * @return el codigo unico  de la preinscripcion proyecto
	 */
	public Integer getPreinscripcionCodigo() {
		return preinscripcionCodigo;
	}
	/**
	 * Establece el codigo unico  dde la preinscripcion proyecto.
	 * 
	 * @param preinscripcionCodigo  codigo unico  de la preinscripcion proyecto
	 */
	public void setPreinscripcionCodigo(Integer preinscripcionCodigo) {
		this.preinscripcionCodigo = preinscripcionCodigo;
	}

	/**
	 * Retorna el proyecto   de la preinscripcion proyecto.
	 * 
	 * @return el proyecto  la preinscripcion proyecto
	 */
	public Proyecto getProyectop() {
		return proyectop;
	}
	/**
	 * Establece el proyecto de la preinscripcion proyecto.
	 * 
	 * @param proyectop  el proyecto  de la preinscripcion proyecto
	 */
	public void setProyectop(Proyecto proyectop) {
		this.proyectop = proyectop;
	}

	/**
	 * Retorna el estudiante   de la preinscripcion proyecto.
	 * 
	 * @return el estudiante  la preinscripcion proyecto
	 */
	public Estudiante getEstudiante() {
		return estudiante;
	}

	/**
	 * Establece el estudiante de la preinscripcion proyecto.
	 * 
	 * @param estudiante  el estudiante  de la preinscripcion proyecto
	 */
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	/**
	 * Retorna la inscripcion  de la preinscripcion proyecto.
	 * 
	 * @return la inscripcion de la preinscripcion proyecto
	 */
	public InscripcionProyecto getInscripcionProyecto() {
		return inscripcionProyecto;
	}
	/**
	 * Establece la inscripcion de la preinscripcion proyecto.
	 * 
	 * @param inscripcionProyecto  la preinscripcion  de la preinscripcion proyecto
	 */
	public void setInscripcionProyecto(InscripcionProyecto inscripcionProyecto) {
		this.inscripcionProyecto = inscripcionProyecto;
	}
	/**
	 * Retorna la fecha  de la preinscripcion proyecto.
	 * 
	 * @return la fecha la preinscripcion proyecto
	 */
	public Date getPreinscripcionProyectoFecha() {
		return preinscripcionProyectoFecha;
	}
	/**
	 * Establece la fecha de la preinscripcion proyecto.
	 * 
	 * @param preinscripcionProyectoFecha  la fecha  de la preinscripcion proyecto
	 */
	public void setPreinscripcionProyectoFecha(Date preinscripcionProyectoFecha) {
		this.preinscripcionProyectoFecha = preinscripcionProyectoFecha;
	}

}
