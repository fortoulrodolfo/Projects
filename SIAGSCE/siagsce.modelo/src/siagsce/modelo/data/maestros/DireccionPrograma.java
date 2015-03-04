package siagsce.modelo.data.maestros;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * La clase DirecionPrograma representa las datos de las direcciones
 * de programa existentes en el Decanato de Ciencias y Tecnología.
 * 
 * @author Iterator
 * 
*/


@Entity
@Table(name = "direccion_programa")
public class DireccionPrograma {

	/**
	 * Codigo Identificador único de la direccion de programa, generado autommáticamente.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "direccion_codigo", nullable = false)
	private Integer direccionCodigo;
	
	/**
	 * Nombre perteneciente a la direccion de programa 
	 */
	@Column(name = "direccion_nombre", length = 100, nullable = false)
	private String direccionNombre;

	/**
	 * Unidades de credito totales de una direccion de programa (Carga academica total)
	 */
	@Column(name = "direccion_unidades_credito", length = 10, nullable = false)
	private Integer direccionUnidadesCredito;

	/**
	 * Proyectos pertenecientes a la direccion de programa 
	 */
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "direccionPrograma")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Proyecto> proyectos;
	
	/**
	 * Director de programa perteneciente a la direccion de programa 
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "direccionPrograma")
	private List<DirectorPrograma> directorprograma=new LinkedList<DirectorPrograma>();

	/**
	 * Estudiante perteneciente a la direccion de programa 
	 */
	@OneToMany( mappedBy = "direccionProgramae")
	private List<Estudiante> estudiante;

	/**
	 * miembros de la coordinscion perteneciente a la direccion de programa 
	 */
	@OneToMany( mappedBy = "direccionProgramam")
	private List<RepresentanteProfesoral> miembroscoordinacion;

	/**
	 * Construye una nueva Direccion de Programa
	 */
	public DireccionPrograma() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye una nueva Direccion de Programa con parametros
	 */
	public DireccionPrograma( String direccionNombre,
			Integer direccionUnidadesCredito) {
		super();
		this.direccionNombre = direccionNombre;
		this.direccionUnidadesCredito = direccionUnidadesCredito;
	}

	/** 
	 * Retorna el codigo identificador unico de la Direccion de Programa
	 * @return codigo de Direccion de Programa
	 */
	public Integer getDireccionCodigo() {
		return direccionCodigo;
	}

	/**
	 * Establece el codigo identificador unico de la Direccion de Programa
	 * 
	 * @param  codigo de Direccion de Programa  
	 */
	public void setDireccionCodigo(Integer direccionCodigo) {
		this.direccionCodigo = direccionCodigo;
	}

	/** 
	 * Retorna el nombre de la direccion de programa
	 * @return nombre de la direccion de programa
	 */
	public String getDireccionNombre() {
		return direccionNombre;
	}

	/**
	 * Establece el nombre de la direccion de programa
	 * 
	 * @param nombre de la direccion de programa
	 */
	public void setDireccionNombre(String direccionNombre) {
		this.direccionNombre = direccionNombre;
	}

	/** 
	 * Retorna las unidades de credito del programa
	 * @return unidades de credito del programa
	 */
	public Integer getDireccionUnidadesCredito() {
		return direccionUnidadesCredito;
	}

	/**
	 * Establece las unidades de credito del programa
	 * 
	 * @param unidades de credito de la direccion de programa
	 */
	public void setDireccionUnidadesCredito(Integer direccionUnidadesCredito) {
		this.direccionUnidadesCredito = direccionUnidadesCredito;
	}

	
	/** 
	 * Retorna a los proyectos pertenecientes a la Direccion de programa
	 * @return proyectos de la Direccion de programa
	 */
	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	/**
	 * Establece a los proyectos pertenecientes a la Direccion de programa
	 * 
	 * @param proyectos de la Direccion de programa
	 */
	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	/** 
	 * Retorna al Director al que pertenece la Direccion de programa
	 * @return Director de la Direccion de programa
	 */
	public List<DirectorPrograma> getDirectorprograma() {
		return directorprograma;
	}

	/**
	 * Establece al Director al que pertenece la Direccion de programa
	 * 
	 * @param Director de la Direccion de programa 
	 */
	public void setDirectorprograma(List<DirectorPrograma> directorprograma) {
		this.directorprograma = directorprograma;
	}

	/** 
	 * Retorna al estudiante que pertenece la Direccion de programa 
	 * @return estudiante de la Direccion
	 */
	public List<Estudiante> getEstudiante() {
		return estudiante;
	}

	/**
	 * Establece estudiante que pertenece la Direccion de programa 
	 * 
	 * @param estudiante de la Direccion 
	 */
	public void setEstudiante(List<Estudiante> estudiante) {
		this.estudiante = estudiante;
	}

	/** 
	 * Retorna los miembros la coordinacion de SCE que pertenecen la Direccion de programa
	 * @return miembros  de la Coordinacion
	 */
	public List<RepresentanteProfesoral> getMiembroscoordinacion() {
		return miembroscoordinacion;
	}

	/**
	 * Establece los miembros la coordinacion de SCE que pertenecen la Direccion de programa
	 * 
	 * @param miembros de la Coordinacion
	 */
	public void setMiembroscoordinacion(
			List<RepresentanteProfesoral> miembroscoordinacion) {
		this.miembroscoordinacion = miembroscoordinacion;
		
	}

}
