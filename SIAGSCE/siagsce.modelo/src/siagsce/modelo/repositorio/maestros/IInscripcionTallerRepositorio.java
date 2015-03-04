package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.Taller;

/**
 * Interfaz Inscripcion al Taller, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo InscripcionTaller.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(InscripcionTaller inscripcionTaller),delete(InscripcionTaller inscripcionTaller),
 * findAll(),findOne(Integer inscripcionTaller),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IInscripcionTallerRepositorio extends JpaRepository<InscripcionTaller,String> {

	/**
	 * Busca una lista de estudiantes.
	 * @return Lista de estudiantes.
	 * @param est, estudiante.
	 */
	@Query("select inst.estudiante from InscripcionTaller as inst   where inst.estudiante=?1")
	public List<Estudiante> buscarEstudiantes(Estudiante est);
	/**
	 * Busca una lista inscripciones taller dado un estudiante.
	 * @return Lista de inscripciones al taller.
	 * @param est, estudiante.
	 */
	public List<InscripcionTaller>findByEstudiante(Estudiante est);
	/**
	 * Busca una lista de estudiantes dado un taller.
	 * @return Lista de estudiantes.
	 * @param taller, taller .
	 */
	@Query("select inst.estudiante from InscripcionTaller as inst where inst.taller=?1 and inst.inscripcionTallerStatus='Activo'") 
	public List<Estudiante> buscarEstudiantesDeUnTaller(Taller taller);
	
	/**
	 * Busca una inscripciones al taller  dado un estudiante y un taller.
	 * @return inscripcion al taller.
	 * @param est y taller, estudiante y taller .
	 */
	@Query("select inst from InscripcionTaller as inst   where inst.estudiante=?1 and inst.taller=?2")
	public InscripcionTaller findByEstudianteAndTaller(Estudiante est,Taller taller);
	
	/**
	 * Busca una lista de estudiantes dado un taller.
	 * @return Lista de estudiantes.
	 * @param taller, taller .
	 */
	//*Busqueda de una lista de estudiantes pertenecientes a un taller inactivo*
	@Query("select inst.estudiante from InscripcionTaller as inst where inst.taller=?1 and inst.inscripcionTallerStatus!='Activo'") 
	public List<Estudiante> buscarEstudiantesDeUnTallerInactivo(Taller taller);
	
	/**
	 * Busca una lista inscripciones taller dado un taller.
	 * @return Lista de inscripciones al taller.
	 * @param taller;
	 */
	public List<InscripcionTaller> findByTaller(Taller taller);
		
}