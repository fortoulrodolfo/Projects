package siagsce.modelo.repositorio.maestros;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.Taller;

/**
 * Interfaz Retiro, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Retiro.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Retiro retiro),delete(Retiro retiro),
 * findAll(),findOne(Integer retiro),etc... por heredar
 * de la clase JpaRepository.
 */
public interface ITallerRepositorio extends JpaRepository<Taller,String> {
	/**
	 * Busca una lista de talleres  en orden descendiente por la fecha de culminacion.
	 * @return Lista de talleres.
	 */
	@Query("SELECT t FROM Taller t ORDER BY t.tallerFechaCulminacion DESC")
	public List<Taller> todosOrderFechaCulminacion();
	/**
	 * Busca una lista de talleres  dado un status.
	 * @return Lista de talleres.
	 * @param status, status del taller
	 */
	public List<Taller> findByTallerStatus(String status);
	/**
	 * Busca un taller  dado un codigo.
	 * @return un taller.
	 * @param status, status del taller
	 */
	public Taller findByTallerCodigo(Integer codigo);

	/**
	 * Busca una lista de talleres  activos sin estudiatnes inscritos.
	 * @return Lista de talleres
	 */
	@Query("select t from Taller as t where tallerStatus = 'Activo' and tallerCodigo NOT IN (select distinct taller from InscripcionTaller) order by TallerInscripcionFechaInicio asc")
	public List<Taller> buscarTalleresActivosSinInscritos();
	
	/**
	 * Busca una lista de talleres  dada la fecha actual.
	 * @return Lista de talleres.
	 * @param fechaActual
	 */
	@Query("select t from Taller as t where tallerStatus = 'Activo' and TallerInscripcionFechaFinal >= ?1 and TallerInscripcionFechaInicio <= ?1 order by TallerInscripcionFechaInicio asc")
	public List<Taller> buscarTalleresActivosFechaInscripcionActiva(Date fechaActual);
	
	/**
	 * Busca una lista de talleres  dada la fecha actual, comparandola con los talleres que tiene fecha
	 * ya pasada.
	 * @return Lista de talleres.
	 * @param fechaActual, fecha Actual
	 */
	@Query("select t from Taller as t where tallerFechaCulminacion < ?1 and tallerStatus ='Activo'")
	public List<Taller> buscarTalleresFechaCulminacionPasada(Date fechaActual);
	
	
	/**
	 * Busca una lista de talleres  dada la fecha actual, comparandola con los talleres que tiene fecha
	 * mayor a la actual.
	 * @return Lista de talleres.
	 * @param fechaActual, fecha Actual
	 */
	@Query("select t from Taller as t where taller_inscripcion_fecha_inicio >= ?1")
	public List<Taller> buscarTalleresFechaProximaInscripcion(Date fechaActual);
}
