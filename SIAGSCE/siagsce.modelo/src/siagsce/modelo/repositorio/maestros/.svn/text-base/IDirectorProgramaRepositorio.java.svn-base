package siagsce.modelo.repositorio.maestros;

import java.util.List;


import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Profesor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz DirectorPrograma, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo DirectorPrograma.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(DirectorPrograma directorPrograma),delete(DirectorPrograma directorPrograma),
 * findAll(),findOne(Integer directorPrograma),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IDirectorProgramaRepositorio extends
		JpaRepository<DirectorPrograma, Integer> {

	/**
	 * Busca un Director de Programa  dado el status del director de programa  y la direccion de programa .
	 * @return el director de programa.
	 * @param directorProgramaStatus y DireccionPrograma, status y direccion de programa.
	 */
	public DirectorPrograma findByDirectorProgramaStatusAndDireccionPrograma(String directorProgramaStatus, DireccionPrograma DireccionPrograma);
	
	/**
	 * Busca un Director de Programa  dada la direccion de programa .
	 * @return el director de programa.
	 * @param  DireccionPrograma,  direccion de programa.
	 */
	public DirectorPrograma findByDireccionPrograma(DireccionPrograma programa);
	
	/**
	 * Busca un Director de Programa  dada la direccion de programa y el status del director de programa.
	 * @return el director de programa.
	 * @param DireccionPrograma y directorProgramaStatus, status y direccion de programa.
	 */
	public DirectorPrograma findByDireccionProgramaAndDirectorProgramaStatus(DireccionPrograma programa, String status);
	
	/**
	 * Busca una lista de todos los Directores de Programa de manera ordenada.
	 * @return una lista de todos los Directores de Programa.
	 */
	@Query(" select dir from DirectorPrograma as dir order by dir.direccionPrograma , dir.directorProgramaFechaInicio ASC ")
	public List<DirectorPrograma> buscarTodosOrdenados();


	/**
	 * Busca un Director de Programa  dado codigo.
	 * @return el director de programa.
	 * @param  codigodireccionPrograma,  codigo del director de programa.
	 */
	@Query("select dir from DirectorPrograma as dir where direccion_codigo=?1")
	public List<DirectorPrograma> buscarTodosPorDireccion(Integer codigodireccionPrograma);
	
	/**
	 * Busca una lista de todos los Directores de Programa por status.
	 * @return una lista de todos los Directores de Programa.
	 * @param directorProgramaStatus, status del director de programa.
	 */
	public List<DirectorPrograma> findByDirectorProgramaStatus (String estatus);
	
	/**
	 * Busca un  Director de Programa dado el profesor.
	 * @return un Director de Programa.
	 * @param prof, profesor.
	 */
	public DirectorPrograma findByProfesor(Profesor prof);
	
	/**
	 * Busca un Director de Programa  dado el profesor y el status del director de programa.
	 * @return el director de programa.
	 * @param profesor y directorProgramaStatus, profesor y el status.
	 */
	public DirectorPrograma findByProfesorAndDirectorProgramaStatus(Profesor profesor, String status);
	
	/**
	 * Busca un Director de Programa  dado el profesor y el status del director de programa.
	 * @return el director de programa.
	 * @param profesor y directorProgramaStatus, profesor y el status.
	 */
	@Query("select direct from DirectorPrograma as direct where direct.profesor=?1 and direct.direccionPrograma=?2 and direct.directorProgramaStatus=?3")
	public DirectorPrograma buscarDirectorProgramaEstatus(Profesor profesor,DireccionPrograma programa ,String estatus);

}

