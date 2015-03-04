package siagsce.modelo.data.maestros;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;

/**
 * La clase Proyecto representa los proyectos creados en la gestion del SCE para
 * la prestacion de los estudiante.
 * 
 * @author Iterator
 * 
 */
@Entity
@Table(name = "proyecto")
public class Proyecto {

	/**
	 * Codigo identificador unico del proyecto.
	 */
	@Id
	@Column(name = "proyecto_codigo", unique = true, nullable = false)
	private String proyectoCodigo;

	/**
	 * Nombre dado al proyecto.
	 */

	@Column(name = "proyecto_nombre", nullable = false)
	private String proyectoNombre;

	/**
	 * Descripcion dado al proyecto.
	 */
	@Column(name = "proyecto_descripcion", nullable = false)
	private String proyectoDescripcion;

	/**
	 * Status dado al proyecto, este puede tomar valores entre Activo,En
	 * Ejecucion y Culminado.
	 */
	@Column(name = "proyecto_status", length = 15, nullable = false)
	private String proyectoStatus;

	/**
	 * Fecha dada al proyecto cuando se registra.
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "proyecto_fecha_registro", nullable = false)
	private Date ProyectoFechaRegistro;

	/**
	 * Direccion de programa a el cual pertenece un proyecto.
	 */

	@ManyToMany
	@JoinTable(name = "proyectos_por_programa", joinColumns = { @JoinColumn(name = "proyecto_codigo", referencedColumnName = "proyecto_codigo") }, inverseJoinColumns = { @JoinColumn(name = "direccion_codigo", referencedColumnName = "direccion_codigo") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DireccionPrograma> direccionPrograma;

	/**
	 * Profesores responsables del proyecto.
	 */
	@ManyToMany
	@JoinTable(name = "profesor_responsable", joinColumns = { @JoinColumn(name = "proyecto_codigo", referencedColumnName = "proyecto_codigo") }, inverseJoinColumns = { @JoinColumn(name = "profesor_cedula", referencedColumnName = "profesor_cedula") })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Profesor> listaprofesoresresponsables;

	/**
	 * Actividades pertenecientes al proyecto.
	 */
	@OneToMany(mappedBy = "proyectoa")
	private List<Actividad> actividadp;

	/**
	 * Preinscripciones pertenecientes al proyecto.
	 */

	@OneToMany(mappedBy = "proyectop")
	private List<PreInscripcionProyecto> preinscripcion;

	/**
	 * Inscripciones pertenecientes al proyecto.
	 */
	@OneToMany(mappedBy = "proyectoi")
	private List<InscripcionProyecto> inscripcion;

	/**
	 * Construye un proyecto con todos sus parametros.
	 */

	public Proyecto(String proyectoCodigo, String proyectoNombre,
			String proyectoDescripcion, String proyectoStatus,
			List<DireccionPrograma> direccionPrograma,
			List<Profesor> listaprofesoresresponsables,
			List<Actividad> actividadp) {
		super();
		this.proyectoCodigo = proyectoCodigo;
		this.proyectoNombre = proyectoNombre;
		this.proyectoDescripcion = proyectoDescripcion;
		this.proyectoStatus = proyectoStatus;
		this.direccionPrograma = direccionPrograma;
		this.listaprofesoresresponsables = listaprofesoresresponsables;
		this.actividadp = actividadp;
	}

	/**
	 * Construye un nuevo proyecto.
	 */
	public Proyecto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Retorna la fecha de registro del proyecto.
	 * 
	 * @return fecha de registro del proyecto
	 */

	public Date getProyectoFechaRegistro() {
		return ProyectoFechaRegistro;
	}

	 /**
	 * Establece la fecha de registro del proyecto.
	 * 
	 * @param proyectoFechaRegistro  fecha de registro del proyecto 
	 */
	public void setProyectoFechaRegistro(Date proyectoFechaRegistro) {
		ProyectoFechaRegistro = proyectoFechaRegistro;
	}
	/**
	 * Retorna el codigo unico  del proyecto.
	 * 
	 * @return el codigo unico  del proyecto
	 */
	public String getProyectoCodigo() {
		return proyectoCodigo;
	}
	 /**
		 * Establece el codigo unico  del proyecto.
		 * 
		 * @param proyectoCodigo  codigo unico  del proyecto 
		 */
	public void setProyectoCodigo(String proyectoCodigo) {
		this.proyectoCodigo = proyectoCodigo;
	}
	/**
	 * Retorna el nombre del proyecto.
	 * 
	 * @return el nombre del proyecto
	 */
	public String getProyectoNombre() {
		return proyectoNombre;
	}

	 /**
		 * Establece el nombre del proyecto.
		 * 
		 * @param proyectoNombre nombre del proyecto 
		 */
	public void setProyectoNombre(String proyectoNombre) {
		this.proyectoNombre = proyectoNombre;
	}

	/**
	 * Retorna la descripcion del proyecto.
	 * 
	 * @return la descripcion del proyecto
	 */
	
	public String getProyectoDescripcion() {
		return proyectoDescripcion;
	}
	 /**
		 * Establece la descripcion del proyecto.
		 * 
		 * @param proyectoDescripcion  la descripcion del proyecto 
		 */
	public void setProyectoDescripcion(String proyectoDescripcion) {
		this.proyectoDescripcion = proyectoDescripcion;
	}
	/**
	 * Retorna el status del proyecto puede tomar valores (Activo,Inactivo,En Ejecucion).
	 * 
	 * @return el status del proyecto
	 */
	public String getProyectoStatus() {
		return proyectoStatus;
	}
	 /**
		 * Establece el status del proyecto.
		 * 
		 * @param proyectoStatus,  el status del proyecto 
		 */
	public void setProyectoStatus(String proyectoStatus) {
		this.proyectoStatus = proyectoStatus;
	}
	/**
	 * Retorna la preinsripcion del proyecto.
	 * 
	 * @return la preinsripcion del proyecto
	 */
	public List<PreInscripcionProyecto> getPreinscripcion() {
		return preinscripcion;
	}
	 /**
		 * Establece la preinsripcion del proyecto.
		 * 
		 * @param preinscripcion, la preinsripcion del proyecto 
		 */
	public void setPreinscripcion(List<PreInscripcionProyecto> preinscripcion) {
		this.preinscripcion = preinscripcion;
	}
	/**
	 * Retorna la inscripcion del proyecto.
	 * 
	 * @return la inscripcion del proyecto
	 */

	public List<InscripcionProyecto> getInscripcion() {
		return inscripcion;
	}
	 /**
		 * Establece la inscripcion del proyecto.
		 * 
		 * @param inscripcion,  inscripcion del proyecto 
		 */
	public void setInscripcion(List<InscripcionProyecto> inscripcion) {
		this.inscripcion = inscripcion;
	}
	/**
	 * Retorna la direccion programa del proyecto.
	 * 
	 * @return la direccion programa del proyecto
	 */
	public List<DireccionPrograma> getDireccionPrograma() {
		return direccionPrograma;
	}
	 /**
		 * Establece la direccion programa del proyecto.
		 * 
		 * @param direccionPrograma,  la direccion programa del proyecto 
		 */
	public void setDireccionPrograma(List<DireccionPrograma> direccionPrograma) {
		this.direccionPrograma = direccionPrograma;
	}
	/**
	 * Retorna los profesores responsables del proyecto.
	 * 
	 * @return los profesores responsables del proyecto
	 */
	public List<Profesor> getListaprofesoresresponsables() {
		return listaprofesoresresponsables;
	}
	 /**
		 * Establece los profesores responsables del proyecto.
		 * 
		 * @param listaprofesoresresponsables,  los profesores responsables del proyecto 
		 */
	public void setListaprofesoresresponsables(
			List<Profesor> listaprofesoresresponsables) {
		this.listaprofesoresresponsables = listaprofesoresresponsables;
	}
	/**
	 * Retorna las actividades  del proyecto.
	 * 
	 * @return las actividades del proyecto
	 */
	public List<Actividad> getActividadp() {
		return actividadp;
	}
	 /**
		 * Establece las actividades del proyecto.
		 * 
		 * @param actividadp,  las actividades del proyecto 
		 */
	public void setActividadp(List<Actividad> actividadp) {
		this.actividadp = actividadp;
	}

	/**
	 * Metodo usado para obtener el profesor responsable del proyecto
	 * 
	 * @return retorna un string con la informacion del profesor
	 */

	public String getResponsableString() {
		String cadena = "";
		if (listaprofesoresresponsables.isEmpty()) {
			System.out.print("esta vacia");
			return cadena;
		} else {
			cadena = listaprofesoresresponsables.get(0).getProfesorNombre()
					+ " "
					+ listaprofesoresresponsables.get(0).getProfesorApellido();
			for (int i = 1; i < listaprofesoresresponsables.size(); i++) {
				cadena += ","
						+ listaprofesoresresponsables.get(i)
								.getProfesorNombre()
						+ " "
						+ listaprofesoresresponsables.get(i)
								.getProfesorApellido();
			}
		}
		return cadena;

	}

	/**
	 * Metodo usado para obtener el la direcicon de programa a el cual pertence
	 * el proyecto
	 * 
	 * @return retorna un string con la informacion de la direccio de programa a
	 *         el cual pertecene el proyecto
	 */

	public String getProgramaString() {
		String cadena = "";
		if (direccionPrograma.isEmpty()) {
			System.out.print("esta vacia");
			return cadena;
		}

		else {
			cadena = direccionPrograma.get(0).getDireccionNombre();
			for (int i = 1; i < direccionPrograma.size(); i++) {
				cadena += "," + direccionPrograma.get(i).getDireccionNombre();
			}
		}
		return cadena;
	}

}
