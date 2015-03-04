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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * La clase DirectorPrograma representa las datos de los directores de programas
 * de cada una de las Direcciones de Programas del  Decanato de Ciencias y Tecnología.
 * 
 * @author Iterator
 * 
*/

@Entity
@Table(name = "director_programa")
public class DirectorPrograma {

	/**
	 * Codigo Identificador único de la director de programa, generado automáticamente.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "director_programa_codigo",  nullable = false)
	private Integer directorProgramaCodigo;

	/**
	 * Fecha inicial del período del director de programa en una Direccion de Programa
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "director_programa_fecha_inicio", nullable = false)
	private Date directorProgramaFechaInicio;

	/**
	 * Fecha culminacion del período del director de programa en una Direccion de Programa
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "director_programa_fecha_culminacion", nullable = true)
	private Date directorProgramaFechaCulminacion;
	
	/**
	 * Status del director de programa. Puede ser: Activo e Inactivo
	 */
	@Column(name = "director_programa_status", length = 15,nullable = false)
	private String directorProgramaStatus;

	/**
	 * Profesor que es Director de programa
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profesor_cedula", nullable = false)
	private Profesor profesor;

	/**
	 * Direccion de Programa a la que pertenece el Director de Programa
	 */
	@ManyToOne
	@JoinColumn(name = "direccion_codigo", nullable = false)
	private DireccionPrograma direccionPrograma;

	/**
	 * Construye un nuevo Director de Programa
	 */
	public DirectorPrograma() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un nuevo Director de Programa con parametros
	 */
	public DirectorPrograma(Date directorProgramaFechaInicio,
			Date directorProgramaFechaCulminacion,
			String directorProgramaStatus, Profesor profesor,
			DireccionPrograma direccionPrograma) {
		super();
		this.directorProgramaFechaInicio = directorProgramaFechaInicio;
		this.directorProgramaFechaCulminacion = directorProgramaFechaCulminacion;
		this.directorProgramaStatus = directorProgramaStatus;
		this.profesor = profesor;
		this.direccionPrograma = direccionPrograma;
	}
	
	/**
	 * Construye un nuevo Director de Programa con parametros
	 */
	public DirectorPrograma(Date directorProgramaFechaInicio,
			Date directorProgramaFechaCulminacion,
			String directorProgramaStatus, Profesor profesor) {
		super();
		this.directorProgramaFechaInicio = directorProgramaFechaInicio;
		this.directorProgramaFechaCulminacion = directorProgramaFechaCulminacion;
		this.directorProgramaStatus = directorProgramaStatus;
		this.profesor = profesor;
	}

	/** 
	 * Retorna el codigo identificador unico del director de programa
	 * @return Codigo del Director de Programa
	 */
	public Integer getDirectorProgramaCodigo() {
		return directorProgramaCodigo;
	}

	/**
	 * Establece el codigo identificador unico del director de programa
	 * @param Codigo del Director de Programa
	 */
	public void setDirectorProgramaCodigo(Integer directorProgramaCodigo) {
		this.directorProgramaCodigo = directorProgramaCodigo;
	}

	/** 
	 * Retorna la Fecha inicial del período del director de programa en una Direccion de Programa
	 * @return Fecha inicial del director
	 */
	public Date getDirectorProgramaFechaInicio() {
		return directorProgramaFechaInicio;
	}

	/**
	 * Establece la Fecha inicial del período del director de programa en una Direccion de Programa
	 * 
	 * @param Fecha inicial del director
	 */
	public void setDirectorProgramaFechaInicio(Date directorProgramaFechaInicio) {
		this.directorProgramaFechaInicio = directorProgramaFechaInicio;
	}

	/** 
	 * Retorna la Fecha culminacion del período del director de programa en una Direccion de Programa
	 * @return Fecha culminacion del director
	 */
	public Date getDirectorProgramaFechaCulminacion() {
		return directorProgramaFechaCulminacion;
	}

	/**
	 * Establece la Fecha culminacion del período del director de programa en una Direccion de Programa
	 * @param Fecha culminacion del director
	 */
	public void setDirectorProgramaFechaCulminacion(
			Date directorProgramaFechaCulminacion) {
		this.directorProgramaFechaCulminacion = directorProgramaFechaCulminacion;
	}

	/** 
	 * Retorna el status del director
	 * @return status del director
	 */
	public String getDirectorProgramaStatus() {
		return directorProgramaStatus;
	}

	/**
	 * Establece el status del director
	 * @param status del director
	 */
	public void setDirectorProgramaStatus(String directorProgramaStatus) {
		this.directorProgramaStatus = directorProgramaStatus;
	}

	/** 
	 * Retorna el profesor como Director de Programa
	 * @return profesor como Director
	 */
	public Profesor getProfesor() {
		return profesor;
	}

	/**
	 * Establece el profesor como Director de Programa
	 * @param profesor como Director
	 */
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	/** 
	 * Retorna la Direccion de Programa al que pertenece el director de programa
	 * @return Direccion de Programa del director
	 */
	public DireccionPrograma getDireccionPrograma() {
		return direccionPrograma;
	}

	/**
	 * Establece la Direccion de Programa al que pertenece el director de programa
	 * @param Direccion de Programa del director
	 */
	public void setDireccionPrograma(DireccionPrograma direccionPrograma) {
		this.direccionPrograma = direccionPrograma;
	}
	
	/**
	 * Retorna la validacion de fecha vigente.
	 *  Esto es, el valor retornado por el metodo <code>cadena</code>
	 * <code>{@link #getDirectorProgramaFechaCulminacion()}</code>.
	 * @return cadena
	 */
	public String getdirectorProgramaFechaCulminacionString(){
		String cadena="";
		if(getDirectorProgramaFechaCulminacion()==null)
			cadena="Actual";
		else
			cadena= directorProgramaFechaCulminacion.toString();
		
		return cadena;
	}

}
