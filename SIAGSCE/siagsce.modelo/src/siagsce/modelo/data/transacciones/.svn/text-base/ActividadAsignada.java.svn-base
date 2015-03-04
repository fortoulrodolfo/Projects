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

import siagsce.modelo.data.maestros.Actividad;

/**
 * La clase ActividadAsignada representa las actividades asignadas a los estudiantes
 * inscritos en un proyecto.
 * @author Iterator
 * 
 */
@Entity
@Table(name = "actividad_asignada")
public class ActividadAsignada {

	/**
	 * Codigo identificador unico de la activdad asignada, generado automaticamente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "actividad_asignada_codigo", unique = true, nullable = false)
	private Integer actividadAsignadaCodigo;
   
	/**
	 * Fecha de inicio dada a la actividad asignadad.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "actividad_asignada_fecha_inicio",nullable = false)
	private Date actividadAsignadaFechaInicio;
	
	/**
	 * Fecha de culminacion dada a la actividad asignadad.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "actividad_asignada_fecha_culminacion",nullable = false)
	private Date actividadAsignadaFechaCulminacion;

	/**
	 *Inscripcion al proyecto dada a la actividad asignada.
	 *es decir estudiante inscrito al cual se le asigna la actividad
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "inscripcion_proyecto_codigo", referencedColumnName = "inscripcion_proyecto_codigo",nullable = false)
	private InscripcionProyecto inscripcionProyecto;

	/**
	 * Actividad dada actividad asignadad.
	 * es decir actividad la cual es asignada
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "actividad_codigo", referencedColumnName = "actividad_codigo",nullable = false)
	private Actividad actividad;

	/**
	 * Actividad asignada que pasara a ser una actividad ejecutada.
	 */
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "actividadasignada")
	private ActividadEjecutada actividadejecutada;

	
	/**
	 * Construye una nueva actividad asignada.
	 */
	public ActividadAsignada() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Construye una actividad asignada con todos sus parametros.
	 */

	public ActividadAsignada(Date actividadAsignadaFechaInicio,
			Date actividadAsignadaFechaCulminacion,
			InscripcionProyecto inscripcionProyecto, Actividad actividad) {
		super();
		this.actividadAsignadaFechaInicio = actividadAsignadaFechaInicio;
		this.actividadAsignadaFechaCulminacion = actividadAsignadaFechaCulminacion;
		this.inscripcionProyecto = inscripcionProyecto;
		this.actividad = actividad;
	}

	/**
	 * Retorna el codigo unico  de la actividad asignada.
	 * 
	 * @return el codigo unico  de la actividad asignada
	 */
	public Integer getActividadAsignadaCodigo() {
		return actividadAsignadaCodigo;
	}
	 /**
	 * Establece el codigo unico  de la actividad asignada.
	 * 
	 * @param actividadAsignadaCodigo  codigo unico de la actividad asignada 
	 */
	public void setActividadAsignadaCodigo(Integer actividadAsignadaCodigo) {
		this.actividadAsignadaCodigo = actividadAsignadaCodigo;
	}

	/**
	 * Retorna la fecha de inicio de la actividad asignada.
	 * 
	 * @return la fecha de inicio de la actividad asignada
	 */
	public Date getActividadAsignadaFechaInicio() {
		return actividadAsignadaFechaInicio;
	}

	/**
	 * Establece la fecha de inicio  de la actividad asignada.
	 * 
	 * @param actividadAsignadaFechaInicio la fecha de inicio de la actividad asignada 
	 */
	public void setActividadAsignadaFechaInicio(
			Date actividadAsignadaFechaInicio) {
		this.actividadAsignadaFechaInicio = actividadAsignadaFechaInicio;
	}
	/**
	 * Retorna la fecha de culminacion de la actividad asignada.
	 * 
	 * @return la fecha de culminacion de la actividad asignada
	 */
	public Date getActividadAsignadaFechaCulminacion() {
		return actividadAsignadaFechaCulminacion;
	}
	/**
	 * Establece la fecha de culminacion  de la actividad asignada.
	 * 
	 * @param actividadAsignadaFechaCulminacion la fecha de culminacion de la actividad asignada 
	 */
	public void setActividadAsignadaFechaCulminacion(
			Date actividadAsignadaFechaCulminacion) {
		this.actividadAsignadaFechaCulminacion = actividadAsignadaFechaCulminacion;
	}
	/**
	 * Retorna la actividad que se converte en una actividad asignada.
	 * 
	 * @return la actividad que se converte en una actividad asignada
	 */
	public Actividad getActividad() {
		return actividad;
	}
	/**
	 * Establece la actividad que se converte en una actividad asignada.
	 * 
	 * @param actividad la actividad que se converte en una actividad asignada 
	 */
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public ActividadEjecutada getActividadejecutada() {
		return actividadejecutada;
	}

	public void setActividadejecutada(ActividadEjecutada actividadejecutada) {
		this.actividadejecutada = actividadejecutada;
	}

	/**
	 * Retorna la inscripcion  a la cual se asigna una actividad asignada.
	 * 
	 * @return la inscripcion  a la cual se asigna una actividad asignada
	 */
	public InscripcionProyecto getInscripcionProyecto() {
		return inscripcionProyecto;
	}
	/**
	 * Establece la inscripcion  a la cual se asignaen una actividad asignada.
	 * 
	 * @param la inscripcion  a la cual se asigna una actividad asignada 
	 */
	public void setInscripcionProyecto(InscripcionProyecto inscripcionProyecto) {
		this.inscripcionProyecto = inscripcionProyecto;
	}

}
