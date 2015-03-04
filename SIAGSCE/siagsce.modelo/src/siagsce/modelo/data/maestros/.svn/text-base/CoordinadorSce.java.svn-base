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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * La clase CoordinadorSce, representa los datos del coordinador (y su respectivo histórico)
 * del Servicio Comunitario Estudiantil del Decanato de Ciencias y Tecnología
 * 
 * @author Iterator
 * 
 */


@Entity
@Table(name = "coordinador_sce")
public class CoordinadorSce {

	/**
	 * Codigo Identificador único del coordinador del Sce generado autommáticamente.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "coordinador_sce_codigo",unique = true, nullable = false)
	private Integer coordinadorCodigo;

	/**
	 * Fecha correspondiente al inicio del periodo como coordinador en la coordinación de SCE 
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "coordinador_sce_fecha_inicio", nullable = false)
	private Date coordinadorFechaInicio;

	/**
	 * Fecha correspondiente al final del periodo como coordinador en la coordinación de SCE 
	 * 
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "coordinador_sce_fecha_culminacion",nullable = true)
	private Date coordinadorFechaCulminacion;

	/**
	 * Status correspondiente al coordinador de SCE. Puede ser Activo o Inactivo 
	 * 
	 */
	@Column(name = "coordinador_sce_status", length = 15, nullable = false)
	private String coordinadorStatus;

	/**
	 * Profesor asociado a la coordinación de SCE como coordinador 
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profesor_cedula", nullable = false)
	private Profesor profesorcoordinador;

	/**
	 *  Coordinación asociada al coordinador
	 * 
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coordinacion_codigo", nullable = false)
	private CoordinacionSce coordinacionsce;

	
	/**
	 * Construye un nuevo coordinador en la coordinación de SCE 
	 * 
	 */
	public CoordinadorSce() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un nuevo coordinador en la coordinación de SCE con sus parámetros
	 * 
	 */
	
	public CoordinadorSce(Date coordinadorFechaInicio,
			Date coordinadorFechaCulminacion, String coordinadorStatus,
			Profesor profesorcoordinador, CoordinacionSce coordinacionsce) {
		super();
		this.coordinadorFechaInicio = coordinadorFechaInicio;
		this.coordinadorFechaCulminacion = coordinadorFechaCulminacion;
		this.coordinadorStatus = coordinadorStatus;
		this.profesorcoordinador = profesorcoordinador;
		this.coordinacionsce = coordinacionsce;
	}

	/**
	 * Retorna la fecha de culminación del periodo del coordinador en la coordinación de SCE.
	 * 
	 * @return Fecha de Culminacion de Coordinador
	 */
	public Date getCoordinadorFechaCulminacion() {
		return coordinadorFechaCulminacion;
	}
	
	/**
	 * Establece la fecha de culminación del periodo del coordinador en la coordinación de SCE.
	 * 
	 * @param coordinadorFechaCulminacion
	 */
	public void setCoordinadorFechaCulminacion(Date coordinadorFechaCulminacion) {
		this.coordinadorFechaCulminacion = coordinadorFechaCulminacion;
	}

	/**
	 * Establece la fecha de inicio del periodo del coordinador en la coordinación de SCE.
	 * 
	 * @param coordinadorFechaInicio
	 */
	public void setCoordinadorFechaInicio(Date coordinadorFechaInicio) {
		this.coordinadorFechaInicio = coordinadorFechaInicio;
	}

	/**
	 * Retorna la fecha de culminación del periodo del coordinador en la coordinación de SCE.
	 * 
	 * @return Fecha de Inicio del coordinador
	 */
	public Date getCoordinadorFechaInicio() {
		return coordinadorFechaInicio;
	}

	
	/**
	 * Establece la fecha de inicio del periodo del coordinador en la coordinación de SCE.
	 * 
	 * @param coordinadorFechaInicio
	 */
	public void setCoordinadorFecha(Date coordinadorFechaInicio) {
		this.coordinadorFechaInicio = coordinadorFechaInicio;
	}

	/**
	 * Construye un nuevo coordinador en la coordinación de SCE con sus parámetros
	 * 
	 */
	public CoordinadorSce(Date coordinadorFechaCulminacion,
			String coordinadorStatus) {
		super();
		this.coordinadorFechaCulminacion = coordinadorFechaCulminacion;
		this.coordinadorStatus = coordinadorStatus;
	}
	
	/**
	 * Retorna el codigo identificador unico del coordinador
	 * 
	 * @return Codigo del coordinador
	 */	
	public Integer getCoordinadorCodigo() {
		return coordinadorCodigo;
	}

	/**
	 * Establece el codigo identificador unico del coordinador
	 * 
	 * @param coordinadorCodigo
	 */
	public void setCoordinadorCodigo(Integer coordinadorCodigo) {
		this.coordinadorCodigo = coordinadorCodigo;
	}

	/**
	 * Retorna al profesor como coordinador.
	 * 
	 * @return profesor como coordinador
	 */
	public Profesor getProfesorcoordinador() {
		return profesorcoordinador;
	}

	/**
	 * Establece al profesor como coordinador.
	 * 
	 * @param profesorcoordinador
	 */
	public void setProfesorcoordinador(Profesor profesorcoordinador) {
		this.profesorcoordinador = profesorcoordinador;
	}

	/**
	 * Retorna  la coordinacion al que pertenece un coordinador.
	 * 
	 * @return coordinacion del coordinador
	 */
	public CoordinacionSce getCoordinacionsce() {
		return coordinacionsce;
	}

	/**
	 * Establece la coordinacion al que pertenece un coordinador.
	 * 
	 * @param coordinacionsce
	 */
	public void setCoordinacionsce(CoordinacionSce coordinacionsce) {
		this.coordinacionsce = coordinacionsce;

	}

	/**
	 * Retorna el status de un coordinador
	 * 
	 * @return status de un coordinador 
	 */
	public String getCoordinadorStatus() {
		return coordinadorStatus;
	}

	/**
	 * Establece el status de un coordinador
	 * 
	 * @param coordinadorstatus
	 */
	public void setCoordinadorStatus(String coordinadorstatus) {
		this.coordinadorStatus = coordinadorstatus;
	}
	
	/**
	 * Retorna al coordinador cuyo período este hasta la fecha vigente.
	 *  Esto es, el valor retornado por el metodo <code>cadena</code>
	 * <code>{@link #getCoordinadorFechaCulminacion()}</code>.
	 * @return coordinador periodo vigente
	 */
	public String getcoordinadorFechaCulminacionString(){
		String cadena="";
		if(getCoordinadorFechaCulminacion()==null)
			cadena="Actual";
		else
			cadena=getCoordinadorFechaCulminacion().toString();
		
		return cadena;
	}

}
