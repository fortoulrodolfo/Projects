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
 * La clase RepresentanteProfesoral seria un profesor el cual representa una
 * direccion de programa en la coordiancion de servicio comunitario estudiantil.
 * un profesor por direccion de programa, (ejemplo: Fernando Colmerazes
 * representa a Ing. Informatica)
 * 
 * @author Iterator
 * 
 */
@Entity
@Table(name = "representante_profesoral")
public class RepresentanteProfesoral {

	/**
	 * Codigo identificador unico del RepresentanteProfesoral, generado automaticamente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "representante_profesoral_codigo")
	private Integer representanteProfesoralCodigo;

	/**
	 * Direccion de programa dada el profesor.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "direccion_codigo", referencedColumnName = "direccion_codigo")
	private DireccionPrograma direccionProgramam;

	/**
	 * Profesor dado a la direccion de programa.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profesor_cedula", referencedColumnName = "profesor_cedula")
	private Profesor profesorm;
	/**
	 * Fecha dada al representante profesoral cuando inicia su rol como
	 * representante.
	 */

	@Temporal(TemporalType.DATE)
	@Column(name = "representante_profesoral_fecha_inicio")
	private Date representanteProfesoralFechaInicio;

	/**
	 * Fecha dada al representante profesoral cuando termina su rol como
	 * representante.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "representante_profesoral_fecha_culminacion")
	private Date representanteProfesoralFechaCulminacion;

	/**
	 * Status dado al representante profesoral toma valores como Activo e
	 * Inactivo.
	 */
	@Column(name = "representante_profesoral_status", length = 15, nullable = false)
	private String estatus;

	/**
	 * Construye un nuevo representante profesoral.
	 */
	public RepresentanteProfesoral() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un representante profesoral con todos sus parametros.
	 */
	public RepresentanteProfesoral(DireccionPrograma direccionProgramam,
			Profesor profesorm, Date representanteProfesoral_fechaInicio,
			Date representanteProfesoral_fechaCulminacion, String estatus) {
		super();
		this.estatus = estatus;
		this.direccionProgramam = direccionProgramam;
		this.profesorm = profesorm;
		this.representanteProfesoralFechaInicio = representanteProfesoral_fechaInicio;
		this.representanteProfesoralFechaCulminacion = representanteProfesoral_fechaInicio;
	}

	/**
	 * Metodo usado para obtener el profesor representante activo
	 * 
	 * @return retorna un string con la fecha de culminacion si no es activo y
	 *         si es activo retorna "vigente"
	 */
	public String getRepresentanteProfesoralFechaCulminacionString() {
		String cadena = "";
		if (getRepresentanteProfesoralFechaCulminacion() == null)
			cadena = "Actual";
		else
			cadena = getRepresentanteProfesoralFechaCulminacion().toString();

		return cadena;
	}

	/**
	 * Retorna el codigo unico  del representante profesoral.
	 * 
	 * @return el codigo unico  del representante profesoral
	 */
	public Integer getRepresentanteProfesoralCodigo() {
		return representanteProfesoralCodigo;
	}
	/**
	 * Establece el codigo unico  del representante profesoral.
	 * 
	 * @param representanteProfesoralCodigo  codigo unico  del representante profesoral 
	 */
	public void setRepresentanteProfesoralCodigo(
			Integer representanteProfesoralCodigo) {
		this.representanteProfesoralCodigo = representanteProfesoralCodigo;
	}

	/**
	 * Retorna la direccion de programa  del representante profesoral.
	 * 
	 * @return la direccion de programa del representante profesoral
	 */
	public DireccionPrograma getDireccionProgramam() {
		return direccionProgramam;
	}
	/**
	 * Establece la direccion de programa del representante profesoral.
	 * 
	 * @param direccionProgramam  la direccion de programa del representante profesoral 
	 */
	public void setDireccionProgramam(DireccionPrograma direccionProgramam) {
		this.direccionProgramam = direccionProgramam;
	}

	/**
	 * Retorna el profesor que es  representante profesoral.
	 * 
	 * @return el profesor que es representante profesoral
	 */
	public Profesor getProfesorm() {
		return profesorm;
	}
	/**
	 * Establece el profesor que es representante profesoral.
	 * 
	 * @param profesorm  el profesor que es representante profesora l
	 */
	public void setProfesorm(Profesor profesorm) {
		this.profesorm = profesorm;

	}
	/**
	 * Retorna la fecha de inicio  del representante profesoral.
	 * 
	 * @return la fecha de inicio del representante profesoral
	 */
	public Date getRepresentanteProfesoralFechaInicio() {
		return representanteProfesoralFechaInicio;
	}
	/**
	 * Establece la fecha de inicio   del representante profesoral.
	 * 
	 * @param representanteProfesoral_fechaInicio la fecha de inicio  del representante profesoral 
	 */
	public void setRepresentanteProfesoralFechaInicio(
			Date representanteProfesoral_fechaInicio) {
		this.representanteProfesoralFechaInicio = representanteProfesoral_fechaInicio;
	}
	/**
	 * Retorna la fecha de culminacion  del representante profesoral.
	 * 
	 * @return la fecha de culminacion del representante profesoral
	 */
	public Date getRepresentanteProfesoralFechaCulminacion() {
		return representanteProfesoralFechaCulminacion;
	}
	/**
	 * Establece la fecha de culminacion  del representante profesoral.
	 * 
	 * @param representanteProfesoral_fechaCulminacion  la fecha de culminacion  del representante profesoral 
	 */
	public void setRepresentanteProfesoralFechaCulminacion(
			Date representanteProfesoral_fechaCulminacion) {
		this.representanteProfesoralFechaCulminacion = representanteProfesoral_fechaCulminacion;
	}
	/**
	 * Retorna el status del representante profesora que puede tomar valores como (Activo, Inactivo).
	 * 
	 * @return el status   del representante profesora
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * Establece el status  del representante profesora.
	 * 
	 * @param estatus,  status  del representante profesora 
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
}
