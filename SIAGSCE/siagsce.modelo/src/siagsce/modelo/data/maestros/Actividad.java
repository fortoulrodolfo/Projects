package siagsce.modelo.data.maestros;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import siagsce.modelo.data.transacciones.ActividadAsignada;

/**
 * La clase Actividad representa las datos de las actividades que se asociarán
 * a los proyectos ofertados para el cumplimiento del Servicio 
 * Comunitario Estudiantil del  Decanato de Ciencias y Tecnología.
 * 
 * @author Iterator
 * 
*/

@Entity
@Table(name = "actividad")
public class Actividad {
	/**
	 * Codigo Identificador único de la actividad, generado autommáticamente.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "actividad_codigo", unique = true, nullable = false)
	private Integer actividadCodigo;
	
	/**
	 * Nombre de la actividad 
	 */
	@Column(name = "actividad_nombre",length = 50, nullable = false)
	private String actividadNombre;
	
	/**
	 *  Cantidad de horas que representa el cumplimiento total de la actividad 
	 */
	@Column(name = "actividad_cantidad_horas", nullable = false)
	private Integer actividadCantidadHoras;

	/**
	 * Descripción detallada y simple de lo que representa cumplir la actividad 
	 */
	@Column(name = "actividad_descripcion", nullable = false)
	private String actividadDescripcion;

	/**
	 * Profesor acreditado que participará en el desarrollo de la actividad
	 */
	@ManyToMany(fetch=FetchType.EAGER) 
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "profesor_participante", joinColumns = { @JoinColumn(name = "actividad_codigo", referencedColumnName = "actividad_codigo") }, inverseJoinColumns = { @JoinColumn(name = "profesor_cedula", referencedColumnName = "profesor_cedula") })
	private List<Profesor> listaprofesoresparticipantes;

	
	/**
	 * Corresponde a la relación que existe con la clase Proyecto
	 * La actividad será asociada a un proyecto existente 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proyecto_codigo",nullable = false)
	private Proyecto proyectoa;
	
	/**
	 * Corresponde a la relación que existe con la clase ActividadAsignada
	 * Las actividades pueden estar o no asignadas a uno o varios estudiantes
	 */
	@OneToMany(mappedBy = "actividad")
	private List<ActividadAsignada> actividadAsignada;

	/**
	 * Construye una nueva Actividad.
	 */
	public Actividad() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construye una nueva Actividad con todos sus parametros.
	 */
	public Actividad(String actividadNombre, Integer actividadCantidadHoras,
			String actividadDescripcion,
			List<Profesor> listaprofesoresparticipantes) {
		super();
		this.actividadNombre = actividadNombre;
		this.actividadCantidadHoras = actividadCantidadHoras;
		this.actividadDescripcion = actividadDescripcion;
		this.listaprofesoresparticipantes = listaprofesoresparticipantes;
	}
	
	
	/** 
	 * Retorna el nombre de la actividad
	 * @return nombre de la actividad
	 */
	public String getActividadNombre() {
		return actividadNombre;
	}

	/**
	 * Establece el nombre de la actividad 
	 * @param actividadNombre  nombre de la actividad 
	 */
	public void setActividadNombre(String actividadNombre) {
		this.actividadNombre = actividadNombre;
	}
	
	/** 
	 * Retorna el código identificador unico de la actividad
	 * @return código de la actividad
	 */
	public Integer getActividadCodigo() {
		return actividadCodigo;
	}
	
	/**
	 * Establece el codigo identificador unico de la actividad
	 * 
	 * @param actividadCodigo  codigo de la actividad 
	 */
	public void setActividadCodigo(Integer actividadCodigo) {
		this.actividadCodigo = actividadCodigo;
	}

	/** 
	 * Retorna la cantidad de horas de la actividad
	 * @return cantidad de horas de la actividad
	 */
	public Integer getActividadCantidadHoras() {
		return actividadCantidadHoras;
	}

	/**
	 * Establece la cantidad de horas de cumplimiento de la actividad
	 * 
	 * @param actividadCantidadHoras  cantidad de horas de la actividad 
	 */
	public void setActividadCantidadHoras(Integer actividadCantidadHoras) {
		this.actividadCantidadHoras = actividadCantidadHoras;
	}
	
	
	/**
	 * Retorna la descripción de la actividad
	 * @return descripción de la actividad
	 */
	public String getActividadDescripcion() {
		return actividadDescripcion;
	}

	/**
	 * Establece la descripción de la actividad
	 * 
	 * @param actividadDescripcion  descripcion de la actividad 
	 */
	public void setActividadDescripcion(String actividadDescripcion) {
		this.actividadDescripcion = actividadDescripcion;
	}

	/** 
	 * Retorna los proyectos asociados a la actividad
	 * @return proyectos asociados a la actividad
	 */
	public Proyecto getProyectoa() {
		return proyectoa;
	}

	/**
	 * Establece el proyecto asociado a la actividad
	 * 
	 * @param proyectoa  proyecto asociado a la actividad 
	 */
	public void setProyectoa(Proyecto proyectoa) {
		this.proyectoa = proyectoa;
	}

	/** Retorna las actividades asignadas a los estudiantes
	 * @return actividades asignadas a los estudiantes
	 */
	public List<ActividadAsignada> getActividadAsignada() {
		return actividadAsignada;
	}
	
	/**
	 * Establece el codigo identificador unico de la actividad
	 * 
	 * @param actividadCodigo  codigo de la actividad 
	 */
	public void setActividadAsignada(List<ActividadAsignada> actividadAsignada) {
		this.actividadAsignada = actividadAsignada;
	}

	/** Retorna los profesores participantes
	 * @return profesores participantes
	 */
	public List<Profesor> getListaprofesoresparticipantes() {
		return listaprofesoresparticipantes;
	}

	/**
	 * Establece el o los profesores participantes de las actividades
	 * 
	 * @param profesores participantes
	 */
	public void setListaprofesoresparticipantes(
			List<Profesor> listaprofesoresparticipantes) {
		this.listaprofesoresparticipantes = listaprofesoresparticipantes;
	}
	
	/** Retorna las actividades asignadas a los estudiantes
	 * @return actividades asignadas a los estudiantes
	 */
	public List<ActividadAsignada> getActividadesAsignada() {
		return actividadAsignada;
	}
	
	/**
	 * Establece el o las actividades que han sido asignadas a los estudiantes
	 * 
	 *@param actividades asignadas
	 */
	public void setActividadesAsignadas(
			List<ActividadAsignada> actividadesAsignada) {
		this.actividadAsignada = actividadesAsignada;
	}

	/**
	 * Retorna la descripción del o los profesores participantes.
	 *  Esto es, el valor retornado por el metodo <code>cadena</code>
	 * <code>{@link #getListaprofesoresparticipantes())}</code>.
	 * @return la descripcion del o los profesores participantes
	 */
	public String getActividadString() {
		String cadena = "";
		if (listaprofesoresparticipantes.isEmpty()) {
			System.out.print("esta vacia");
			return cadena;
		} else {
			cadena = listaprofesoresparticipantes.get(0).getProfesorNombre()
					+ " "
					+ listaprofesoresparticipantes.get(0).getProfesorApellido();
			for (int i = 1; i < listaprofesoresparticipantes.size(); i++) {
				cadena += ","
						+ listaprofesoresparticipantes.get(i)
								.getProfesorNombre()
						+ " "
						+ listaprofesoresparticipantes.get(i)
								.getProfesorApellido();
			}
		}
		return cadena;

	}

}
