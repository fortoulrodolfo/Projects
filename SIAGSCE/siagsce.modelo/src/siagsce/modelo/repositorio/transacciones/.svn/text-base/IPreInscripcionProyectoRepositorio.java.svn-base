package siagsce.modelo.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;
/**
 * Interfaz Pre-Inscripción proyecto, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Pre-InscripcionProyecto.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(PreInscripcionProyecto preInscripcionProyecto),delete(PreInscripcionProyecto preInscripcionProyecto),findAll(),findOne(Integer preinscripcionCodigo),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IPreInscripcionProyectoRepositorio extends
		JpaRepository<PreInscripcionProyecto, String> {
	/**
	 * Metodo que retorna las Preinscripciones de un proyecto.
	 * @param Proyecto asociado a las preincripciones.
	 * @return PreInscripciones al proyecto.
	 */
	public List<PreInscripcionProyecto> findByProyectop(Proyecto proyecto);
	/**
	 * Metodo que retorna la Preinscripción de un proyecto
	 * dado el codigo de esa preinscripción.
	 * @param codigo de la preincripcion.
	 * @return PreInscripción al proyecto.
	 */
	public PreInscripcionProyecto findByPreinscripcionCodigo(Integer codigo);
	/**
	 * Metodo que retorna las Preinscripciones de un proyecto dado un estudiante.
	 * @param estudiante que ha realizado las preincripciones.
	 * @return PreInscripciones al proyecto.
	 */
	public List<PreInscripcionProyecto> findByEstudiante(Estudiante estudiante);
	/**
	 * Metodo que retorna las Preinscripciones dado un estatus.
	 * @param estatus de las preinscripciones.
	 * @return PreInscripciones al proyecto.
	 */
	public List<PreInscripcionProyecto> findByPreinscripcionStatus(String status);
	/**
	 * Metodo que retorna las Preinscripciones qu se han realizado a un proyecto segun su estatus.
	 * @param proyecto donde se han realizado las preincripciones.
	 * @param status(Activo, inactivo, En espera, Retirado).
	 * @return PreInscripciones al proyecto.
	 */
	public List<PreInscripcionProyecto> findByProyectopAndPreinscripcionStatus(Proyecto proyecto,String status);
	
	/**
	 * Metodo que retorna las Preinscripciones que tiene un estudiante en un proyecto con estatus activa
	 * @param proyecto donde se han realizado las preincripciones.
	 * @param status(Activo, inactivo, En espera, Retirado).
	 * @param estudiante.
	 * @return PreInscripciones al proyecto.
	 */
	@Query("select pi from PreInscripcionProyecto as pi where pi.estudiante=?1 and pi.proyectop=?2 and pi.preinscripcionStatus=?3")
	public PreInscripcionProyecto buscarPorEstudianteProyectoYStatus(Estudiante estudiante, Proyecto proyecto, String status);
	
	/**
	 * Metodo que retorna una Preinscripción a un proyecto dado su codigo y estatus.
	 * @param proyecto asociado a la preincripción.
	 * @param status(Activo, inactivo, En espera, Retirado).
	 * @return PreInscripción al proyecto.
	 */
	public PreInscripcionProyecto findByPreinscripcionCodigoAndPreinscripcionStatus(Integer codigo,String status);
}
