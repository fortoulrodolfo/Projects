package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;

/**
 * Interfaz Proyecto, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Proyecto.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Proyecto proyecto),delete(Proyecto proyecto),
 * findAll(),findOne(Integer proyecto),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IProyectoRepositorio extends JpaRepository<Proyecto,String>{

	/**
	 * Busca una lista de proyecto  dada una direccion de programa.
	 * @return Lista de proyecto.
	 * @param direccionPrograma, direccion de programa.
	 */
	public List<Proyecto> findBydireccionPrograma(DireccionPrograma direccionPrograma);
	/**
	 * Busca una lista de proyecto  dada una direccion de programa y un status.
	 * @return Lista de proyecto.
	 * @param direccionPrograma y sta , direccion de programa y status del proyecto.
	 */
	public List<Proyecto> findByDireccionProgramaAndProyectoStatus(DireccionPrograma direccionPrograma, String sta);
	/**
	 * Busca un proyecto  dado un codigo.
	 * @return proyecto.
	 * @param codigo, codigo de proyecto.
	 */
	public Proyecto findByProyectoCodigo(String codigo);
	/**
	 * Busca una lista de proyecto   dado un status.
	 * @return Lista de proyecto.
	 * @param status, status del proyecot .
	 */
	public List<Proyecto> findByProyectoStatus(String sta);
	/**
	 * de acuerdo a un profesor trae todos los proyectos 
	 * donde este es responsable
	 * @param profesor , al cual se le buscaran los proyectos
	 * @return lista de proyectos donde un profesor es responsable
	 * */
	@Query("select distinct profesor from Proyecto as p  join p.listaprofesoresresponsables as profesor"
			+ " "+"  where p.proyectoCodigo=?1 and profesor not in "
			+ " "+" (select distinct prof from Proyecto pro join pro.listaprofesoresresponsables as prof"
			+ " "+ "where pro.proyectoCodigo!=?1)")
	public List<Profesor> buscarResponsable(String proyecto);
	

	
	public List<Profesor> findByProyectoStatusAndListaprofesoresresponsables(String status, List<Profesor> listprofesores);

	/**
	 * Busca una lista de proyectos activos   dado un objeto profesor.
	 * @return Lista de proyecto.
	 * @param profesor, objeto profesor .
	 */
	
	@Query("select pro from Proyecto as pro join pro.listaprofesoresresponsables as profesor where profesor =?1 and pro.proyectoStatus ='Activo'")
	public List<Proyecto> buscarProyectosActivosResponsable(Profesor profesor);
	
	/**
	 * Busca una lista de proyectos activos o en ejecucion  dado un objeto profesor.
	 * @return Lista de proyecto.
	 * @param profesor, objeto profesor .
	 */
	
	@Query("select pro from Proyecto as pro join pro.listaprofesoresresponsables as profesor where profesor =?1 and ( pro.proyectoStatus ='Activo' or pro.proyectoStatus ='En Ejecución') ")
	public List<Proyecto> buscarProyectosResponsable(Profesor profesor);
	
	/**
	 * Busca una lista de proyectos activos o en ejecucion por programa   dado un objeto profesor.
	 * @return Lista de proyecto.
	 * @param profesor, objeto profesor .
	 */
	@Query("select pro from Proyecto as pro join pro.listaprofesoresresponsables as profesor join pro.direccionPrograma as programa where profesor =?1 and programa=?2 and ( pro.proyectoStatus ='Activo' or pro.proyectoStatus ='En Ejecución') ")
	public List<Proyecto> buscarProyectosResponsablePrograma(Profesor profesor,DireccionPrograma programa);

	/**
	 * Busca una lista de proyectos en ejecucion   dado un objeto profesor.
	 * @return Lista de proyecto.
	 * @param profesor, objeto profesor .
	 */
	
	@Query("select pro from Proyecto as pro join pro.listaprofesoresresponsables as profesor where profesor =?1 and pro.proyectoStatus ='En Ejecución'")
	public List<Proyecto> buscarProyectosEnEjecucionResponsable(Profesor profesor);
	
	
	/**
	 * Busca el proyecto al cual pertenece uana actividad.
	 * @return proyecto.
	 * @param actividad.
	 */
	
	@Query("select pro from Proyecto as pro join pro.actividadp as actividad where actividad =?1")
	public Proyecto busacarProyecto(Actividad actividad);

	/**
	 * busca si el profesor de la cedula de entrada es responsable de un proyecto
	 * @param Profesor al cual buscaremos
	 * @return el profesor resultante de la busqueda
	 * */
	@Query("select distinct profesor from Proyecto as pro join pro.listaprofesoresresponsables as profesor where profesor=?1 and ( pro.proyectoStatus ='En Ejecución' or pro.proyectoStatus ='Activo') ")
	public Profesor buscarResponsableExistente(Profesor Profesor);
	
	@Query("select pro from Proyecto as pro join pro.direccionPrograma as programa where programa=?1 and pro.proyectoStatus !='Inactivo'")
	public List<Proyecto>buscarProyectosNoInactivosPorPrograma(DireccionPrograma programa);
	
	 @Query("select profesor from Proyecto as pro join pro.listaprofesoresresponsables as profesor where profesor=?1 and pro.proyectoCodigo=?2")
	 public Profesor buscarResponsableProyecto(Profesor profesor,String codigoProyecto);
}
