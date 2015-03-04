package siagsce.modelo.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;
/**
 * Interfaz Inscripción proyecto, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo InscripcionProyecto.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(InscripcionProyecto inscripcionProyecto),delete(InscripcionProyecto inscripcionProyecto),findAll(),findOne(Integer inscripcionCodigo),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IInscripcionProyectoRepositorio extends JpaRepository<InscripcionProyecto,String>{
	/**
	 * Metodo que retorna la lista de las inscripciones de proyecto dado el proyecto.
	 * @param proyecto
	 * @return lista de inscripciones de proyecto.
	 */ 
	@Query("select ins from InscripcionProyecto as ins where ins.proyectoi=?1 and ins.inscripcionProyectoStatus='Activo'")
	public List<InscripcionProyecto> buscarPreinscripciones(Proyecto proyecto);
	/**
	 * Metodo que retorna la lista de las inscripciones de proyecto que ha realizado un estudiante.
	 * @param estudiante.
	 * @return lista de inscripciones de proyecto.
	 */
	@Query("select ins from InscripcionProyecto as ins , PreInscripcionProyecto as pre where ins.preInscripcion=pre.preinscripcionCodigo and pre.estudiante=?1 and ins.inscripcionProyectoStatus=?2 ")
	public List<InscripcionProyecto> buscarEstudianteInscritoYStatus(Estudiante estudiante, String status);
	/**
	 * Metodo que retorna la lista de las inscripciones de proyecto que ha realizado un estudiante.
	 * @param estudiante.
	 * @return lista de inscripciones de proyecto.
	 */
	@Query("select ins from InscripcionProyecto as ins , PreInscripcionProyecto as pre where ins.preInscripcion=pre.preinscripcionCodigo and pre.estudiante=?1")
	public List<InscripcionProyecto> buscarEstudianteInscrito(Estudiante estudiante);
	/**
	 * Metodo que retorna una inscripción de proyecto dado la preinscripcion y el estatus.
	 * @param preinscripcion del proyecto.
	 * @param estatus.
	 * @return lista de inscripciones de proyecto.
	 */
	public InscripcionProyecto findByPreInscripcionAndInscripcionProyectoStatus(PreInscripcionProyecto pre, String status);
	/**
	 * Metodo que retorna la inscripción a un proyecto dado el codigo de la inscripcion.
	 * @param codigo de la inscripción.
	 * @return Inscripcion del proyecto.
	 */
	public InscripcionProyecto findByInscripcionCodigo(Integer cod);
	/**
	 * Metodo que retorna una lista de inscripciones a un cierto proyecto 
	 * donde su estatus concuerde con el estatus pasado por parametro.
	 * @param proyecto donde se han inscrito.
	 * @param estatus(Activo, innactivo, enProceso).
	 * @return Inscripciones de proyecto.
	 */
	public List<InscripcionProyecto> findByProyectoiAndInscripcionProyectoStatus(Proyecto proyecto, String status);
	/**
	 * Metodo que retorna una inscripción a un proyecto que concuerde 
	 * con el codigo del proyecto y el estatus pasado por parametro.
	 * @param cod codigo del proyecto.
	 * @param status estado del proyecto(Activo, Inactivo, EnProceso).
	 * @return Inscripciones de proyecto.
	 */
	public InscripcionProyecto findByInscripcionCodigoAndInscripcionProyectoStatus(Integer cod, String status);
	/**
	 * Metodo que retorna una inscripción a un proyecto 
	 * que la halla realizado el estudiante pasado por parametro.
	 * @param estudiante Estudiante que realizo la inscripción en el proyecto.
	 * @return Inscripcion de proyecto.
	 */
	@Query("select ins from InscripcionProyecto as ins , PreInscripcionProyecto as pre where ins.preInscripcion=pre.preinscripcionCodigo and ins.inscripcionProyectoStatus='Activo' and pre.estudiante=?1 ")
	public InscripcionProyecto buscarInscripcionActiva(Estudiante estudiante);
	
}
