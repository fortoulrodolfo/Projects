package siagsce.modelo.data.maestros;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.data.transacciones.InscripcionProyecto;

/**
 * La clase Retiro representa a una transaccion en el sistema, se usa para
 * retirar aun estudiante de un proyecto o para exonerar a un estudiante del
 * servicio comunitario estudiantil.
 * 
 * @author Iterator
 * 
 */
@Entity
@Table(name = "retiro")
public class Retiro {

	/**
	 * Codigo identificador unico del retiro, generado automaticamente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "retiro_codigo", unique = true, nullable = false)
	private Integer retiroCodigo;

	/**
	 * Status dado al retiro este puede tomar valores como Contable y No
	 * Contable. esto es para el caso de cuando un estudiante es retirado sus
	 * horas en ese proyecto pueden ser contadas o no.
	 */
	@Column(name = "retiro_status", length = 15, nullable = false)
	private String retiroStatus;

	/**
	 * Fecha dada al retiro.
	 */

	@Temporal(TemporalType.DATE)
	@Column(name = "retiro_fecha", nullable = false)
	private Date retiroFecha;

	/**
	 * Motivo dado al retiro.
	 */
	@OneToOne
	@JoinColumn(name = "motivo_codigo", nullable = false)
	private Motivo motivoRetiro;

	/**
	 * Inscripcion del proyecto el dado al retiro. es decir la inscripcion del
	 * proyecto que se va a retirar
	 */
	@OneToOne
	@JoinColumn(name = "inscripcion_proyecto_codigo", nullable = false)
	private InscripcionProyecto inscripcionProyectoRetiro;

	/**
	 * Construye un nuevo retiro.
	 */
	public Retiro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un retiro con todos sus parametros.
	 */
	public Retiro(String retiroStatus, Date retiroFecha, Motivo motivoRetiro,
			InscripcionProyecto inscripcionProyectoRetiro) {
		super();
		this.retiroStatus = retiroStatus;
		this.retiroFecha = retiroFecha;
		this.motivoRetiro = motivoRetiro;
		this.inscripcionProyectoRetiro = inscripcionProyectoRetiro;
	}

	/**
	 * Retorna el codigo unico  del retiro.
	 * 
	 * @return el codigo unico  del retiro
	 */

	public Integer getRetiroCodigo() {
		return retiroCodigo;
	}

	 /**
	 * Establece el codigo unico  del retiro.
	 * 
	 * @param retiroCodigo  codigo unico  del retiro 
	 */
	public void setRetiroCodigo(Integer retiroCodigo) {
		this.retiroCodigo = retiroCodigo;
	}

	/**
	 * Retorna el motivo  del retiro.
	 * 
	 * @return el motivo  del retiro
	 */
	public Motivo getMotivoRetiro() {
		return motivoRetiro;
	}

	 /**
		 * Establece el motivo  del retiro.
		 * 
		 * @param motivoRetiro  motivo del retiro 
		 */
	public void setMotivoRetiro(Motivo motivoRetiro) {
		this.motivoRetiro = motivoRetiro;
	}
	/**
	 * Retorna la inscripcion del proyecto  del retiro.
	 * 
	 * @return la inscripcion del proyecto   del retiro
	 */
	public InscripcionProyecto getInscripcionProyectoRetiro() {
		return inscripcionProyectoRetiro;
	}
	 /**
	 * Establece la inscripcion del proyecto  del retiro.
	 * 
	 * @param inscripcionProyectoRetiro,  la inscripcion del proyecto  del retiro 
	 */
	public void setInscripcionProyectoRetiro(
			InscripcionProyecto inscripcionProyectoRetiro) {
		this.inscripcionProyectoRetiro = inscripcionProyectoRetiro;
	}
	/**
	 * Retorna el status del retiro que puede tomar valores(Contable y No Contable).
	 * 
	 * @return el status  del retiro
	 */
	public String getRetiroStatus() {
		return retiroStatus;
	}
	 /**
	 * Establece el status  del retiro.
	 * 
	 * @param retiroStatus,  status del retiro 
	 */
	public void setRetiroStatus(String retiroStatus) {
		this.retiroStatus = retiroStatus;
	}
	/**
	 * Retorna la fecha  del retiro.
	 * 
	 * @return la fecha del retiro
	 */
	public Date getRetiroFecha() {
		return retiroFecha;
	}
	 /**
	 * Establece la fecha  del retiro.
	 * 
	 * @param retiroFecha  la fecha del retiro 
	 */
	public void setRetiroFecha(Date retiroFecha) {
		this.retiroFecha = retiroFecha;
	}

}
