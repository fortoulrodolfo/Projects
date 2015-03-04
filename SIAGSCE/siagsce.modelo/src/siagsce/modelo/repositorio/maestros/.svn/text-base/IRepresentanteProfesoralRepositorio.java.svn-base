package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;

/**
 * Interfaz RepresentanteProfesoral, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo
 * RepresentanteProfesoral. Nota:Ademas de los metodos que se pueden observar,
 * la interfaz tambien ofrece metodos como save(RepresentanteProfesoral
 * representanteProfesoral),delete(RepresentanteProfesoral
 * representanteProfesoral), findAll(),findOne(Integer
 * representanteProfesoral),etc... por heredar de la clase JpaRepository.
 */
public interface IRepresentanteProfesoralRepositorio extends
		JpaRepository<RepresentanteProfesoral, String> {

	/**
	 * Busca un RepresentanteProfesoral  dado una cedula y un status.
	 * @return Representante Profesoral 
	 * @param cedula y status, cedula y status del representante profesoral.
	 */
	@Query("select rep from RepresentanteProfesoral as rep where rep.profesorm.profesorNombre=?1 and rep.estatus=?2")
	public RepresentanteProfesoral buscarProfesorAndEstatus(String cedula,
			String estatus);

	/**
	 * Busca un RepresentanteProfesoral dado un status y una direccion de programa.
	 * @return Representante Profesoral 
	 * @param  status y DireccionPrograma, status y Direccion Programa del representante profesoral.
	 */
	public RepresentanteProfesoral findByEstatusAndDireccionProgramam(
			String estatus, DireccionPrograma dir);
	/**
	 * Busca una lista de Representantes Profesorales   dado un status.
	 * @return Lista de Representantes Profesorales.
	 * @param estatus, status del representante profesoral.
	 */
	public List<RepresentanteProfesoral> findByEstatus(String estatus);

	/**
	 * Busca un RepresentanteProfesoral dado un status y una direccion de programa.
	 * @return Representante Profesoral 
	 * @param  status y DireccionPrograma, status y Direccion Programa del representante profesoral.
	 */
	public RepresentanteProfesoral findByDireccionProgramamAndEstatus(
			DireccionPrograma direccionProgramam, String estatus);

	/**
	 * Busca un RepresentanteProfesoral dado un status y un profesor.
	 * @return Representante Profesoral 
	 * @param  status y prof, status y profesor que seria el representante profesoral.
	 */
	public RepresentanteProfesoral findByProfesormAndEstatus(Profesor prof,
			String estatus);

	/**
	 * Busca una lista de todos los  Representantes Profesorales 
	 * @return Lista de Representantes Profesorales.
	 */
	@Query("select rep from RepresentanteProfesoral as rep order By rep.direccionProgramam.direccionNombre ASC")
	public List<RepresentanteProfesoral> buscarOrdenado();

	/**
	 * Busca un RepresentanteProfesoral dado una cedula.
	 * @return Representante Profesoral 
	 * @param  cedula, cedula del representante profesoral.
	 */
	 @Query("select rp from RepresentanteProfesoral as rp where estatus = 'Activo' and rp.profesorm.profesorCedula= ?1")
	public RepresentanteProfesoral buscarRepresentantePorC(String cedula);
	 @Query("select rp from RepresentanteProfesoral as rp where rp.profesorm=?1 and rp.direccionProgramam=?2  and rp.estatus=?3")
	 public RepresentanteProfesoral buscarRepresentantePorProgramaEstatus(Profesor profesor,DireccionPrograma programa, String status);
	 

}
