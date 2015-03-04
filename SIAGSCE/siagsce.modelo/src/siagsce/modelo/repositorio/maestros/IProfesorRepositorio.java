package siagsce.modelo.repositorio.maestros;

import java.util.List;


import siagsce.modelo.data.maestros.Profesor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz Profesor, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Profesor.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Profesor profesor),delete(Profesor profesor),
 * findAll(),findOne(Integer profesor),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IProfesorRepositorio extends JpaRepository<Profesor,String> {
	
	/**
	 * Busca un profesor  dada una cedula.
	 * @return profesor 
	 * @param cedula, cedula profesor.
	 */
	public Profesor findByProfesorCedula(String cedula);
	/**
	 * Busca una lista de profesores   dado un status.
	 * @return Lista de profesores.
	 * @param status, status del profesor .
	 */
	public List<Profesor> findByProfesorStatus( String status);
	/**
	 * Busca una lista de profesores   dado un nombre.
	 * @return Lista de profesores.
	 * @param nombre, nombre del profesor .
	 */
	public List<Profesor> findByProfesorNombre(String nombre);
	/**
	 * Busca una lista de profesores  si el idusuario es null.
	 * @return Lista de profesores.
	 */
	public List<Profesor> findByIdusuarioIsNull();
	
	/**
	 * Busca una lista de profesores  que son aptos y no son directores de programa.
	 * @return Lista de profesores.
	 */
	@Query("select pro from Profesor as pro where  pro.profesorCedula not in(select dir.profesor.profesorCedula FROM DirectorPrograma as dir where dir.directorProgramaStatus='Activo')")
	public List<Profesor> buscarTodosAptosNoSonDirectores();

}
