package siagsce.modelo.data.maestros;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import siagsce.modelo.data.transacciones.ActividadAsignada;

/**
 * La clase Exonerado representa las datos de los estudiantes exonerados del SCE. 
 * 
 * @author Iterator
 * 
*/

@Entity
@Table(name = "exonerado")
public class Exonerado {

	/**
	 * Codigo Identificador único del estudiante exonerado, generado autommáticamente.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "exonerado_codigo", unique = true, nullable = false)
	private Integer exoneradoCodigo;

	/**
	 * Fecha de exoneracion del estudiante
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "exonerado_fecha", nullable = false)
	private Date exoneradoFecha;

	/**
	 * Estudiante exonerado
	 * 
	 */
	@OneToOne
	@JoinColumn(name = "estudiante_cedula", nullable = false)
	private Estudiante estudianteExonerado;
	
	/**
	 * Motivo de exoneracion del estudiante
	 * 
	 */
	@OneToOne
	@JoinColumn(name = "motivo_codigo", nullable = false)
	private Motivo motivoExonerado;

	/**
	 * Construye un nuevo estudiante exonerado
	 * 
	 */
	public Exonerado() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un nuevo estudiante exonerado con parametros
	 * 
	 */
	public Exonerado(Date exoneradoFecha, Estudiante estudianteExonerado,
			Motivo motivoExonerado) {
		super();
		this.exoneradoFecha = exoneradoFecha;
		this.estudianteExonerado = estudianteExonerado;
		this.motivoExonerado = motivoExonerado;
	}

	/** 
	 * Retorna el codigo identificador unico del exonerado
	 * @return codigo de exonerado
	 */
	public Integer getExoneradoCodigo() {
		return exoneradoCodigo;
	}

	/**
	 * Establece el codigo identificador unico del exonerado 
	 * @param codigo de exonerado
	 */
	public void setExoneradoCodigo(Integer exoneradoCodigo) {
		this.exoneradoCodigo = exoneradoCodigo;
	}

	/** 
	 * Retorna el estudiante exonerado
	 * @return estudiante exonerado
	 */
	public Estudiante getEstudianteExonerado() {
		return estudianteExonerado;
	}

	/**
	 * Establece el estudiante exonerado
	 * @param estudiante exonerado
	 */
	public void setEstudianteExonerado(Estudiante estudianteExonerado) {
		this.estudianteExonerado = estudianteExonerado;
	}

	/** 
	 * Retorna el motivo para exonerar a estudiante
	 * @return motivo de exoneracion
	 */
	public Motivo getMotivoExonerado() {
		return motivoExonerado;
	}

	/**
	 * Establece el motivo para exonerar a estudiante
	 * @param motivo de exoneracion
	 */
	public void setMotivoExonerado(Motivo motivoExonerado) {
		this.motivoExonerado = motivoExonerado;
	}

	/** 
	 * Retorna la fecha del en que fue exonerado el estudiante
	 * @return fecha de exonerado
	 */
	public Date getExoneradoFecha() {
		return exoneradoFecha;
	}

	/**
	 * Establece la fecha del en que fue exonerado el estudiante
	 * @param fecha de exonerado
	 */
	public void setExoneradoFecha(Date exoneradoFecha) {
		this.exoneradoFecha = exoneradoFecha;
	}

}
