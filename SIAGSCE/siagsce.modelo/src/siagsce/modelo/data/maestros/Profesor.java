package siagsce.modelo.data.maestros;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * La clase Profesor, es como el nombre de lo dice un profesor que estara en siendo usuario del sistema
 *
 * 
 * @author Iterator
 * 
 */
@Entity
@Table(name = "profesor")
public class Profesor {

	/**
	 * Codigo identificador unico del profesor, el cual es su cedula.
	 */
	@Id
	@Column(name = "profesor_cedula", unique = true, nullable = false)
	private String profesorCedula;

	/**
	 * Nombre dado al profesor.
	 */
	@Column(name = "profesor_nombre", length = 100, nullable = false)
	private String profesorNombre;
	/**
	 * Apellido dado al profesor.
	 */

	@Column(name = "profesor_apellido", length = 100, nullable = false)
	private String profesorApellido;

	/**
	 * Email dado al profesor.
	 */
	@Column(name = "profesor_email", length = 50, nullable = false)
	private String profesorEmail;

	/**
	 * Telefono dado al profesor.
	 */
	@Column(name = "profesor_telefono", length = 25, nullable = false)
	private String profesorTelefono;

	/**
	 * Direccion dado al profesor.
	 */
	@Column(name = "profesor_direccion", length = 255, nullable = false)
	private String profesorDireccion;

	/**
	 * Status dado al profesor, este atributo toma valores como Apto y Acreditado.
	 */
	@Column(name = "profesor_status",length = 15, nullable = false)
	private String profesorStatus;
	
	/**
	 * Usuario asignado al profesor.
	 */
	@Column(name = "usuario_codigo")
	private Integer idusuario=null;

	/**
	 * Construye un profesor con todos sus parametros.
	 */

	public Profesor(String profesorCedula, String profesorNombre,
			String profesorApellido, String profesorEmail,
			String profesorTelefono, String profesorDireccion,
			String profesorStatus) {
		super();
		this.profesorCedula = profesorCedula;
		this.profesorNombre = profesorNombre;
		this.profesorApellido = profesorApellido;
		this.profesorEmail = profesorEmail;
		this.profesorTelefono = profesorTelefono;
		this.profesorDireccion = profesorDireccion;
		this.profesorStatus = profesorStatus;
	}

	/**
	 * Proyecto en el cual un profesor es responsable.
	 */
	@ManyToMany(mappedBy ="listaprofesoresresponsables")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Proyecto> proyectos;

	/**
	 * Actividad  en el cual un profesor es participe.
	 */
	@ManyToMany(mappedBy ="listaprofesoresparticipantes")
	private List<Actividad> actividad;

	/**
	 * Direccion de programa en el cual un profesor es director.
	 */
	@OneToMany( mappedBy = "profesor")
	private List<DirectorPrograma> directorprograma;

	/**
	 * Representante profesoral, es un profesor que forma parte de la coordinacion
	 * es un solo representante por direccion de programa.
	 */
	@OneToMany( mappedBy = "profesorm")
	private List<RepresentanteProfesoral> miebroscoordinacion;

	/**
	 * Direccion de programa en el cual un profesor es director.
	 */
	@OneToMany( mappedBy = "profesorcoordinador")
	private List<CoordinadorSce> coordinadorsce;

	/**
	 * Construye una nuevo profesor.
	 */
	public Profesor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retorna el Status del profesor.
	 * 
	 * @return Status del profesor
	 */

	public String getProfesorStatus() {
		return profesorStatus;
	}

	 /**
	 * Establece el status del profesor.
	 * 
	 * @param profesorStatus  status del profesor 
	 */
	public void setProfesorStatus(String profesorStatus) {
		this.profesorStatus = profesorStatus;
	}
	
	/**
	 * Retorna la cedula del profesor.
	 * 
	 * @return cedula del profesor
	 */
	public String getProfesorCedula() {
		return profesorCedula;
	}

	 /**
		 * Establece la cedula  del profesor.
		 * 
		 * @param profesorCedula  cedula del profesor 
		 */
	public void setProfesorCedula(String profesorCedula) {
		this.profesorCedula = profesorCedula;
	}

	/**
	 * Retorna el nombre del profesor.
	 * 
	 * @return nombre del profesor
	 */
	public String getProfesorNombre() {
		return profesorNombre;
	}

	 /**
		 * Establece el nombre del profesor.
		 * 
		 * @param profesorNombre  nombre del profesor 
		 */
	public void setProfesorNombre(String profesorNombre) {
		this.profesorNombre = profesorNombre;
	}

	/**
	 * Retorna el apellido del profesor.
	 * 
	 * @return apellido del profesor
	 */
	public String getProfesorApellido() {
		return profesorApellido;
	}

	 /**
		 * Establece el apellido del profesor.
		 * 
		 * @param profesorApellido  apellido del profesor 
		 */
	public void setProfesorApellido(String profesorApellido) {
		this.profesorApellido = profesorApellido;
	}

	/**
	 * Retorna el email del profesor.
	 * 
	 * @return email del profesor
	 */
	public String getProfesorEmail() {
		return profesorEmail;
	}

	 /**
		 * Establece el email del profesor.
		 * 
		 * @param profesorEmail email del profesor 
		 */
	public void setProfesorEmail(String profesorEmail) {
		this.profesorEmail = profesorEmail;
	}

	/**
	 * Retorna el telefono del profesor.
	 * 
	 * @return telefono del profesor
	 */
	public String getProfesorTelefono() {
		return profesorTelefono;
	}

	 /**
		 * Establece el telefono del profesor.
		 * 
		 * @param profesorTelefono  telefono del profesor 
		 */
	public void setProfesorTelefono(String profesorTelefono) {
		this.profesorTelefono = profesorTelefono;
	}

	/**
	 * Retorna el Status del profesor.
	 * 
	 * @return Status del profesor
	 */
	public String getProfesorDireccion() {
		return profesorDireccion;
	}

	 /**
		 * Establece el status del profesor.
		 * 
		 * @param profesorStatus  status del profesor 
		 */
	public void setProfesorDireccion(String profesorDireccion) {
		this.profesorDireccion = profesorDireccion;
	}

	/**
	 * Retorna el Status del profesor.
	 * 
	 * @return Status del profesor
	 */
	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	 /**
		 * Establece el status del profesor.
		 * 
		 * @param profesorStatus  status del profesor 
		 */
	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	/**
	 * Retorna el Status del profesor.
	 * 
	 * @return Status del profesor
	 */
	public List<Actividad> getActividad() {
		return actividad;
	}

	 /**
		 * Establece el status del profesor.
		 * 
		 * @param profesorStatus  status del profesor 
		 */
	public void setActividad(List<Actividad> actividad) {
		this.actividad = actividad;
	}

	/**
	 * Retorna los directores de programa.
	 * 
	 * @return un objeto con los directores de programa
	 */
	public List<DirectorPrograma> getDirectorprograma() {
		return directorprograma;
	}

	 /**
		 * Establece los directores de programa.
		 * 
		 * @param directorprograma  objeto con directores de programa 
		 */
	public void setDirectorprograma(List<DirectorPrograma> directorprograma) {
		this.directorprograma = directorprograma;
	}

	/**
	 * Retorna el representante profesoral .
	 * 
	 * @return representante profesoral 
	 */
	public List<RepresentanteProfesoral> getMiebroscoordinacion() {
		return miebroscoordinacion;
	}

	 /**
		 * Establece representante profesoral.
		 * 
		 * @param representante profesoral
		 */
	public void setMiebroscoordinacion(
			List<RepresentanteProfesoral> miebroscoordinacion) {
		this.miebroscoordinacion = miebroscoordinacion;
	}

	/**
	 * Retorna coordinador de la coordinacion.
	 * 
	 * @return coordinador
	 */
	public List<CoordinadorSce> getCoordinadorsce() {
		return coordinadorsce;
	}

	 /**
		 * Establece coordinador de la coordinacion.
		 * 
		 * @param coordinadorsce 
		 */
	public void setCoordinadorsce(List<CoordinadorSce> coordinadorsce) {
		this.coordinadorsce = coordinadorsce;
	}

	/**
	 * Retorna el codigo de usuario del profesor.
	 * 
	 * @return Codigo usuario del profesor
	 */
	public Integer getIdusuario() {
		return idusuario;
	}
	
	 /**
		 * Establece el codigo de usuario del profesor.
		 * 
		 * @param idusuario  codigo de usuario del profesor 
		 */
	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	
	/**
	 * Retorna la descripciion del profesor. Esto es, el valor retornado por el metodo
	 * <code>{@link #getProfesorApellido()}</code>.
	 * <code>{@link #getProfesorNombre()}</code>.
	 * @return la descripcion del profesor
	 */
	@Override
	public String toString() {
		return this.profesorNombre + " "  + this.profesorApellido + " " + this.profesorEmail;
	}
	

}
