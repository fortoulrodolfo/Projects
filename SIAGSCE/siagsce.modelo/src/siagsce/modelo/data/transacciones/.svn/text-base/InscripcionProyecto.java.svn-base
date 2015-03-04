package siagsce.modelo.data.transacciones;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import siagsce.modelo.data.maestros.Proyecto;

/**
 * La clase InscripcionProyecto representa a la inscripcion del estudiante en un proyecto.
 * 
 * @author Iterator
 * 
 */


@Entity
@Table(name = "inscripcion_proyecto")
public class InscripcionProyecto {

	/**
	 * Codigo identificador unico de la Inscripcion en el proyecto, generado
	 * automaticamente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "inscripcion_proyecto_codigo", unique = true, nullable = false)
	private Integer inscripcionCodigo;

	/**
	 * Fecha dada a la incripcion proyecto.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "inscripcion_proyecto_fecha")
	private Date inscripcionProyectoFecha;

	/**
	 * Status dado a la incripcion proyecto que puede tomar valores Activo, Retirado e Inactivo.
	 */
	@Column(name = "inscripcion_proyecto_status", length = 15)
	private String inscripcionProyectoStatus;

	/**
	 * Preinscripcion al proyecto dada a la incripcion proyecto.
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "preinscripcion_proyecto_codigo",unique=true)
	private PreInscripcionProyecto preInscripcion;

	/**
	 * Actividades asignadas  a la incripcion proyecto.
	 */
	@OneToMany(mappedBy = "inscripcionProyecto")
	private List<ActividadAsignada> actividadAsignada;

	/**
	 * Proyecot dado a la incripcion proyecto.
	 */
	@ManyToOne
	@JoinColumn(name = "proyecto_codigo")
	private Proyecto proyectoi;

	/**
	 * Construye una nueva inscripcion al proyecto.
	 */
	public InscripcionProyecto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construye una nueva  inscripcion al proyecto con todos sus parametros.
	 */
	public InscripcionProyecto(Date inscripcionProyectoFecha,
			String inscripcionProyectoStatus,
			PreInscripcionProyecto preInscripcion, Proyecto proyectoi) {
		super();
		this.inscripcionProyectoFecha = inscripcionProyectoFecha;
		this.inscripcionProyectoStatus = inscripcionProyectoStatus;
		this.preInscripcion = preInscripcion;
		this.proyectoi = proyectoi;
	}

	/**
	 * Construye una nueva  inscripcion al proyecto con su parametro.
	 */
	public InscripcionProyecto(Integer inscripcionCodigo) {
		super();
		this.inscripcionCodigo = inscripcionCodigo;
	}

	
	/**
	 * Retorna el codigo unico  de la inscripcion proyecto.
	 * 
	 * @return el codigo unico  de la inscripcion proyecto
	 */
	public Integer getInscripcionCodigo() {
		return inscripcionCodigo;
	}
	/**
	 * Establece el codigo unico  de la inscripcion proyecto.
	 * 
	 * @param inscripcionCodigo  codigo unico  de la inscripcion proyecto
	 */
	public void setInscripcionCodigo(Integer inscripcionCodigo) {
		this.inscripcionCodigo = inscripcionCodigo;
	}

	/**
	 * Retorna la preinscripcion  de la inscripcion proyecto.
	 * 
	 * @return la preinscripcion de la inscripcion proyecto
	 */
	public PreInscripcionProyecto getPreInscripcion() {
		return preInscripcion;
	}

	/**
	 * Establece la preinscripcion de la inscripcion proyecto.
	 * 
	 * @param preInscripcion  la preinscripcion  de la inscripcion proyecto
	 */
	public void setPreInscripcion(PreInscripcionProyecto preInscripcion) {
		this.preInscripcion = preInscripcion;
	}
	/**
	 * Retorna la actividad asignada   de la inscripcion proyecto.
	 * 
	 * @return la actividad asignada de la inscripcion proyecto
	 */
	public List<ActividadAsignada> getActividadAsignada() {
		return actividadAsignada;
	}
	/**
	 * Establece la actividad asignada  dde la inscripcion proyecto.
	 * 
	 * @param actividadAsignada  la actividad asignada de la inscripcion proyecto
	 */
	public void setActividadAsignada(List<ActividadAsignada> actividadAsignada) {
		this.actividadAsignada = actividadAsignada;
	}

	/**
	 * Retorna el proyecto   de la inscripcion proyecto.
	 * 
	 * @return el proyecto  la inscripcion proyecto
	 */
	public Proyecto getProyectoi() {
		return proyectoi;
	}
	/**
	 * Establece el proyecto de la inscripcion proyecto.
	 * 
	 * @param proyectoi  el proyecto  de la inscripcion proyecto
	 */
	public void setProyectoi(Proyecto proyectoi) {
		this.proyectoi = proyectoi;
	}

	/**
	 * Retorna la fecha  de la inscripcion proyecto.
	 * 
	 * @return la fecha la inscripcion proyecto
	 */
	public Date getInscripcionProyectoFecha() {
		return inscripcionProyectoFecha;
	}
	/**
	 * Establece la fecha de la inscripcion proyecto.
	 * 
	 * @param inscripcionProyectoFecha  la fecha  de la inscripcion proyecto
	 */
	public void setInscripcionProyectoFecha(Date inscripcionProyectoFecha) {
		this.inscripcionProyectoFecha = inscripcionProyectoFecha;
	}
	/**
	 * Retorna el status  de la inscripcion proyecto toma valores(Activa,Inactiva y Retirado).
	 * 
	 * @return  el status  la inscripcion proyecto
	 */
	public String getInscripcionProyectoStatus() {
		return inscripcionProyectoStatus;
	}
	/**
	 * Establece  el status  de la inscripcion proyecto.
	 * 
	 * @param inscripcionProyectoStatus   el status   de la inscripcion proyecto
	 */
	public void setInscripcionProyectoStatus(String inscripcionProyectoStatus) {
		this.inscripcionProyectoStatus = inscripcionProyectoStatus;
	}
	
	
	

}
