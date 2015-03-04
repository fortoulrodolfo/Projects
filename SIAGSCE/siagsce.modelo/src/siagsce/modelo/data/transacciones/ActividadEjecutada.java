package siagsce.modelo.data.transacciones;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La clase Taller representa a el taller creado para permitir que los
 * estudiantes tenga la oportunidad de acreditarse en el Servicio Comunitario
 * Estudiantil.
 * 
 * @author Iterator
 * 
 */
@Entity
@Table(name = "actividad_ejecutada")
public class ActividadEjecutada {

	/**
	 * Codigo identificador unico de la actividda ejecutada, generado
	 * automaticamente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "actividad_ejecutada_codigo", unique = true, nullable = false)
	private Integer actividadEjecutadaCodigo;

	/**
	 * Horas cumplidas de la actividad asignada dada a la actividda ejecutada.
	 */
	@Column(name = "actividad_ejecutada_horas_cumplidas", nullable = false)
	private Integer actividadEjecutadaHorasCumplidas;

	/**
	 * Fecha de ejecucion dada a la actividda ejecutada.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "actividad_ejecutada_fecha", nullable = false)
	private Date actividadEjecutadaFecha;

	/**
	 * Actividad asignada  dada a la actividda ejecutada.
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "actividad_asignada_codigo", nullable = false)
	private ActividadAsignada actividadasignada;

	/**
	 * Construye un nuevo proyecto.
	 */
	public ActividadEjecutada() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un proyecto con todos sus parametros.
	 */
	public ActividadEjecutada(Integer actividadEjecutadaHorasCumplidas,
			Date actividadEjecutadaFecha, ActividadAsignada actividadasignada) {
		super();
		this.actividadEjecutadaHorasCumplidas = actividadEjecutadaHorasCumplidas;
		this.actividadEjecutadaFecha = actividadEjecutadaFecha;
		this.actividadasignada = actividadasignada;
	}

	/**
	 * Retorna la fecha de ejecucion de la actividad.
	 * 
	 * @return la fecha de ejecucion de la actividad.
	 */
	public Date getActividadEjecutadaFecha() {
		return actividadEjecutadaFecha;
	}
	/**
	 * Establece la fecha de ejecucion de la actividad.
	 * 
	 * @param actividadEjecutadaFecha, la fecha de ejecucion de la actividad.
	 */
	public void setActividadEjecutadaFecha(Date actividadEjecutadaFecha) {
		this.actividadEjecutadaFecha = actividadEjecutadaFecha;
	}
	/**
	 * Retorna el codigo unico  de la actividad ejecutada.
	 * 
	 * @return el codigo unico  de la actividad ejecutada
	 */
	public Integer getActividadEjecutadaCodigo() {
		return actividadEjecutadaCodigo;
	}
	 /**
		 * Establece el codigo unico  de la actividad ejecutada.
		 * 
		 * @param actividadEjecutadaCodigo  codigo unico de la actividad ejecutada 
		 */
	public void setActividadEjecutadaCodigo(Integer actividadEjecutadaCodigo) {
		this.actividadEjecutadaCodigo = actividadEjecutadaCodigo;
	}

	/**
	 * Retorna la cantidad de horas que ejecuto el estudiante  de la actividad ejecutada.
	 * 
	 * @return la cantidad de horas que ejecuto el estudiante de la actividad ejecutada
	 */
	public Integer getActividadEjecutadaHorasCumplidas() {
		return actividadEjecutadaHorasCumplidas;
	}
	/**
	 * Establece la cantidad de horas que ejecuto el estudiante de la actividad ejecutada.
	 * 
	 * @param actividadEjecutadaHorasCumplidas  la cantidad de horas que ejecuto el estudiante actividad ejecutada 
	 */
	public void setActividadEjecutadaHorasCumplidas(
			Integer actividadEjecutadaHorasCumplidas) {
		this.actividadEjecutadaHorasCumplidas = actividadEjecutadaHorasCumplidas;
	}

	/**
	 * Retorna la actividad asignada que pasa a ser actividad ejecutada.
	 * 
	 * @return la actividad asignada que pasa a ser actividad ejecutada
	 */
	public ActividadAsignada getActividadasignada() {
		return actividadasignada;
	}

	/**
	 * Establece la actividad asignada que pasa a ser actividad ejecutada.
	 * 
	 * @param actividadasignada  la actividad asignada que pasa a ser  actividad ejecutada 
	 */
	public void setActividadasignada(ActividadAsignada actividadasignada) {
		this.actividadasignada = actividadasignada;
	}



}
