package siagsce.modelo.data.maestros;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "taller")
public class Taller {

	/**
	 * Codigo identificador unico del RepresentanteProfesoral, generado
	 * automaticamente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "taller_codigo", unique = true, length = 10, nullable = false)
	private Integer tallerCodigo;

	/**
	 * Nombre dado al taller.
	 */
	@Column(name = "taller_nombre", length = 30, nullable = false)
	private String tallerNombre;

	/**
	 * Fecha de inicio dada al taller.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "taller_fecha_inicio", length = 50, nullable = false)
	private Date tallerFechaInicio;

	/**
	 * Fecha de culminacion dada al taller.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "taller_fecha_culminacion", length = 10, nullable = false)
	private Date tallerFechaCulminacion;

	/**
	 * Descripcion dada al taller.
	 */
	@Column(name = "taller_descripcion", length = 255, nullable = false)
	private String tallerDescripcion;

	
	/**
	 * Fecha de inicio de la inscripcion al taller, dada al taller.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "taller_inscripcion_fecha_inicio", nullable = false)
	private Date TallerInscripcionFechaInicio;

	/**
	 * Fecha de culminacion de la inscripcion al taller, dada al taller.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "taller_inscripcion_fecha_final", nullable = false)
	private Date TallerInscripcionFechaFinal;

	/**
	 * Lugar dado al taller.
	 */
	@Column(name = "taller_lugar", length = 255, nullable = false)
	private String tallerLugar;

	/**
	 * Modalidad dada al taller.
	 */
	@Column(name = "taller_modalidad", length = 255, nullable = false)
	private String tallerModalidad;
	/**
	 * Profesor responsable dado al taller.
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profesor_cedula", referencedColumnName = "profesor_cedula")
	private Profesor tallerProfesorResponsable;

	/**
	 * Status dado al taller, puede tomar valores como Activo e Inactivo.
	 */
	@Column(name = "taller_status", length = 15, nullable = false)
	private String tallerStatus;

	@Column(name = "taller_cantidad_horas", length = 25, nullable = false)
	private Integer tallerCantidadHoras;
	
	/**
	 * Inscripciones en el taller.
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "taller")
	private List<InscripcionTaller> InscripcionTaller;

	/**
	 * Construye un taller nuevo
	 */
	public Taller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye un taller nuevo, con todos sus parametros
	 */
	
	public Taller(String tallerNombre, Date tallerFechaInicio,
			Date tallerFechaCulminacion, String tallerDescripcion,
			Date tallerInscripcionFechaInicio,
			Date tallerInscripcionFechaFinal, String tallerLugar,
			String tallerModalidad, Profesor tallerProfesorResponsable,
			String tallerStatus, Integer tallerCantidadHoras) {
		super();
		this.tallerNombre = tallerNombre;
		this.tallerFechaInicio = tallerFechaInicio;
		this.tallerFechaCulminacion = tallerFechaCulminacion;
		this.tallerDescripcion = tallerDescripcion;
		TallerInscripcionFechaInicio = tallerInscripcionFechaInicio;
		TallerInscripcionFechaFinal = tallerInscripcionFechaFinal;
		this.tallerLugar = tallerLugar;
		this.tallerModalidad = tallerModalidad;
		this.tallerProfesorResponsable = tallerProfesorResponsable;
		this.tallerStatus = tallerStatus;
		this.tallerCantidadHoras = tallerCantidadHoras;
	}

	/**
	 * Retorna el lugar  del taller.
	 * 
	 * @return el lugar  del taller
	 */
	public String getTallerLugar() {
		return tallerLugar;
	}
	 /**
	 * Establece el lugar  del taller.
	 * 
	 * @param tallerLugar  lugar  del taller 
	 */
	public void setTallerLugar(String tallerLugar) {
		this.tallerLugar = tallerLugar;
	}
	/**
	 * Retorna la modalidad del taller.
	 * 
	 * @return la modalidad  del taller
	 */
	public String getTallerModalidad() {
		return tallerModalidad;
	}
	 /**
		 * Establece la modalidad  del taller.
		 * 
		 * @param tallerModalidad  la modalidad  del taller 
		 */
	public void setTallerModalidad(String tallerModalidad) {
		this.tallerModalidad = tallerModalidad;
	}
	/**
	 * Retorna el profesor responsable  del taller.
	 * 
	 * @return el profesor responsable  del taller
	 */
	public Profesor getTallerProfesorResponsable() {
		return tallerProfesorResponsable;
	}
	 /**
		 * Establece el profesor responsable  del taller.
		 * 
		 * @param tallerProfesorResponsable  profesor responsable  del taller 
		 */
	public void setTallerProfesorResponsable(Profesor tallerProfesorResponsable) {
		this.tallerProfesorResponsable = tallerProfesorResponsable;
	}
	/**
	 * Retorna el nombre  del taller.
	 * 
	 * @return el nombre  del taller
	 */
	public String getTallerNombre() {
		return tallerNombre;
	}
	 /**
		 * Establece el nombre  del taller.
		 * 
		 * @param tallerNombre  nombre  del taller 
		 */
	public void setTallerNombre(String tallerNombre) {
		this.tallerNombre = tallerNombre;
	}
	/**
	 * Retorna la fecha de inscripcion inicio   del taller.
	 * 
	 * @return la fecha de inscripcion inicio del taller
	 */
	public Date getTallerInscripcionFechaInicio() {
		return TallerInscripcionFechaInicio;
	}
	 /**
		 * Establece la fecha de inscripcion inicio  del taller.
		 * 
		 * @param tallerInscripcionFechaInicio la fecha de inscripcion inicio  del taller 
		 */
	public void setTallerInscripcionFechaInicio(
			Date tallerInscripcionFechaInicio) {
		TallerInscripcionFechaInicio = tallerInscripcionFechaInicio;
	}
	/**
	 * Retorna la fecha de inscripcion final  del taller.
	 * 
	 * @return la fecha de inscripcion final  del taller
	 */
	public Date getTallerInscripcionFechaFinal() {
		return TallerInscripcionFechaFinal;
	}
	 /**
		 * Establece la fecha de inscripcion final  del taller.
		 * 
		 * @param tallerInscripcionFechaFinal  la fecha de inscripcion final  del taller 
		 */
	public void setTallerInscripcionFechaFinal(Date tallerInscripcionFechaFinal) {
		TallerInscripcionFechaFinal = tallerInscripcionFechaFinal;
	}
	/**
	 * Retorna el codigo unico  del taller.
	 * 
	 * @return el  codigo unico   del taller
	 */
	public Integer getTallerCodigo() {
		return tallerCodigo;
	}
	 /**
		 * Establece el  codigo unico   del taller.
		 * 
		 * @param tallerCodigo   codigo unico   del taller 
		 */
	public void setTallerCodigo(Integer tallerCodigo) {
		this.tallerCodigo = tallerCodigo;
	}
	/**
	 * Retorna la fecha inicio  del taller.
	 * 
	 * @return la fecha inicio  del taller
	 */
	public Date getTallerFechaInicio() {
		return tallerFechaInicio;
	}
	 /**
		 * Establece la fecha inicio  del taller.
		 * 
		 * @param tallerFechaInicio  la fecha inicio  del taller 
		 */
	public void setTallerFechaInicio(Date tallerFechaInicio) {
		this.tallerFechaInicio = tallerFechaInicio;
	}
	/**
	 * Retorna la fecha culminacion  del taller.
	 * 
	 * @return la fecha culminacion  del taller
	 */
	public Date getTallerFechaCulminacion() {
		return tallerFechaCulminacion;
	}
	 /**
		 * Establecela fecha culminacion  del taller.
		 * 
		 * @param tallerFechaCulminacion la fecha culminacion  del taller 
		 */
	public void setTallerFechaCulminacion(Date tallerFechaCulminacion) {
		this.tallerFechaCulminacion = tallerFechaCulminacion;
	}
	/**
	 * Retorna la descripcion  del taller.
	 * 
	 * @return la descripcion  del taller
	 */
	public String getTallerDescripcion() {
		return tallerDescripcion;
	}
	 /**
		 * Establece la descripcion  del taller.
		 * 
		 * @param tallerDescripcion  la descripcion  del taller 
		 */
	public void setTallerDescripcion(String tallerDescripcion) {
		this.tallerDescripcion = tallerDescripcion;
	}
	
	/**
	 * Retorna las  inscripciones  del taller.
	 * 
	 * @return las  inscripciones  del taller
	 */
	public List<InscripcionTaller> getInscripcionTaller() {
		return InscripcionTaller;
	}
	 /**
		 * Establece las  inscripciones  del taller.
		 * 
		 * @param inscripcionTaller  las  inscripciones del taller 
		 */
	public void setInscripcionTaller(List<InscripcionTaller> inscripcionTaller) {
		InscripcionTaller = inscripcionTaller;
	}
	/**
	 * Retorna el status  del taller puede tomar valores(ACtivo Inactivo).
	 * 
	 * @return el status  del taller
	 */
	public String getTallerStatus() {
		return tallerStatus;
	}
	 /**
		 * Establece el status  del taller.
		 * 
		 * @param tallerStatus  status  del taller 
		 */
	public void setTallerStatus(String tallerStatus) {
		this.tallerStatus = tallerStatus;
	}

	/**
	 * Retorna el profesor nombre y apellido responsable  del taller.
	 * 
	 * @return  el profesor nombre y apellido responsable  del taller
	 */
	public String getTallerNombreCompletoResponsable() {
		return tallerProfesorResponsable.getProfesorNombre() + " "
				+ tallerProfesorResponsable.getProfesorApellido();
	}

	/**
	 * Retorna la cantidad de horas   del taller.
	 * 
	 * @return  la cantidad de horas   del taller
	 */
	public Integer getTallerCantidadHoras() {
		return tallerCantidadHoras;
	}

	/**
	 * Establece la cantidad de horas   del taller.
	 * 
	 * @param tallerCantidadHoras,  cantidad de horas   del taller 
	 */
	public void setTallerCantidadHoras(Integer tallerCantidadHoras) {
		this.tallerCantidadHoras = tallerCantidadHoras;
	}

	
}
