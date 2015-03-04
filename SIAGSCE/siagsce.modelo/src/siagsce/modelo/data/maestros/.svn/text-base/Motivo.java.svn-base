package siagsce.modelo.data.maestros;

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

import siagsce.modelo.data.maestros.Retiro;

/**
 * La clase Motivo representa las datos de los motivos de exonerados y retiros de estudiantes del SCE. 
 * 
 * @author Iterator
 * 
*/

@Entity
@Table(name = "motivo")
public class Motivo {

	/**
	 * Codigo Identificador único del Motivo, generado autommáticamente.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "motivo_codigo", unique = true, nullable = false)
	private Integer motivoCodigo;

	/**
	 * Nombre del motivo
	 * 
	 */
	@Column(name = "motivo_nombre", length = 30, nullable = false)
	private String motivoNombre;

	/**
	 * Descripcion del motivo
	 * 
	 */
	@Column(name = "motivo_descripcion", length = 150, nullable = false)
	private String motivoDescripcion;

	/**
	 * Motivo de Exoneracion de estudiante
	 * 
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "motivoExonerado")
	private Exonerado exonerado;

	/**
	 * Motivo de Retiro de estudiante
	 * 
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "motivoRetiro")
	private Retiro retiroMotivo;
	

	/**
	 * Causa del Motivo, ya sea por retiro o exoneracion
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "causa_codigo", nullable = false)
	private Causa causaMotivo;
	

	/**
	 * Construye un nuevo motivo con parametros
	 * 
	 */
	
	public Motivo(String motivoNombre, String motivoDescripcion,
			Causa causaMotivo) {
		super();
		this.motivoNombre = motivoNombre;
		this.motivoDescripcion = motivoDescripcion;
		this.causaMotivo = causaMotivo;
	}

	/**
	 * Construye un nuevo motivo
	 * 
	 */
	public Motivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** 
	 * Retorna el Nombre del motivo
	 * @return nombre del motivo
	 */
	public String getMotivoNombre() {
		return motivoNombre;
	}

	/**
	 * Estableceel nombre del motivo
	 * @param nombre del motivo
	 */
	public void setMotivoNombre(String motivoNombre) {
		this.motivoNombre = motivoNombre;
	}

	/** 
	 * Retorna la descripcion del motivo
	 * @return descripcion del motivo
	 */
	public String getMotivoDescripcion() {
		return motivoDescripcion;
	}

	/**
	 * Establece la descripcion del motivo
	 * @param descripcion del motivo
	 */
	public void setMotivoDescripcion(String motivoDescripcion) {
		this.motivoDescripcion = motivoDescripcion;
	}

	/** 
	 * Retorna el Motivo para el retiro
	 * @return retiro motivo
	 */
	public Retiro getRetiroMotivo() {
		return retiroMotivo;
	}

	/**
	 * Establece el Motivo para el retiro
	 * @param retiroMotivo
	 */
	public void setRetiroMotivo(Retiro retiroMotivo) {
		this.retiroMotivo = retiroMotivo;
	}

	/** 
	 * Retorna el codigo identificador unico del motivo
	 * @return codigo del motivo
	 */
	public Integer getMotivoCodigo() {
		return motivoCodigo;
	}

	/**
	 * Establece el codigo identificador unico del motivo
	 * @param codigo del motivo
	 */
	public void setMotivoCodigo(Integer motivoCodigo) {
		this.motivoCodigo = motivoCodigo;
	}

	/** 
	 * Retorna el Motivo para la exoneracion de estudiante
	 * @return exonerado
	 */
	public Exonerado getExonerado() {
		return exonerado;
	}

	/**
	 * Establece el motivo exonerado motivo
	 * @param exonerado
	 */
	public void setExonerado(Exonerado exonerado) {
		this.exonerado = exonerado;
	}

	/** 
	 * Retorna el causa del motivo
	 * @return causa del motivo
	 */
	public Causa getCausaMotivo() {
		return causaMotivo;
	}

	/**
	 * Establece la causa del motivo
	 * @param causa del motivo
	 */
	public void setCausaMotivo(Causa causaMotivo) {
		this.causaMotivo = causaMotivo;
	}

	
}
