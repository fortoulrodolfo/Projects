package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Estudiante;
/**
 * Interfaz Estudiante, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Estudiante.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Estudiante estudiante),delete(Estudiante estudiante),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IEstudianteRepositorio extends JpaRepository<Estudiante,String> {
	
	/**
	 * Busca una lista de estudiantes dada una actividad asignada.
	 * @return Lista de estudiantes.
	 * @param codigo_actividad codigo de la actividad.
	 */
		@Query("select e from Estudiante as e, ActividadAsignada as aa, InscripcionProyecto as i, PreInscripcionProyecto as pp where aa.actividad = '1' and aa.inscripcionProyecto = i.inscripcionCodigo and i.preInscripcion = pp.preinscripcionCodigo and pp.estudiante = e.estudianteCedula")
		public List<Estudiante> BuscarEstudiantesActividadesAsiganas(Integer codigo_actividad);
		/**
		 * Busca un estudiante dada una cedula.
		 * @return  estudiante.
		 * @param cedula cedula de la actividad.
		 */
		public Estudiante findByEstudianteCedula(String cedula);
		/**
		 * Busca un estudiante dada una cedula.
		 * @return  estudiante.
		 * @param cedula cedula de la actividad.
		 */
		public Estudiante findByEstudianteCedulaAndEstudianteStatus(String cedula, String status);
		
		
		/**
		 * Busca la suma de las horas de actividades ejecutadas por el 
		 * estudiante  dado un estudiante.
		 * @return  la suma de horas. 
		 * @param est estudiante.
		 */
		@Query("SELECT SUM (ae.actividadEjecutadaHorasCumplidas) from ActividadEjecutada as ae, " +
				"ActividadAsignada as aa, InscripcionProyecto as i, PreInscripcionProyecto as pp, " +
				"Estudiante as e where ae.actividadasignada=aa.actividadAsignadaCodigo and " +
				"aa.inscripcionProyecto=i.inscripcionCodigo and i.preInscripcion=pp.preinscripcionCodigo " +
				"and pp.estudiante=?1 GROUP BY e.estudianteCedula")
		public Long buscarHorasAcumuladas(Estudiante est);
		
		
		/**
		 * Busca la suma de las horas de actividades ejecutadas  por el 
		 * estudiante retirado dado un estudiante.
		 * @return  la suma de horas.
		 * @param est estudiante.
		 */
		@Query("SELECT SUM (ae.actividadEjecutadaHorasCumplidas) from ActividadEjecutada as ae, " +
				"ActividadAsignada as aa, InscripcionProyecto as i, PreInscripcionProyecto as pp, " +
				"Estudiante as e, Retiro as r  where ae.actividadasignada=aa.actividadAsignadaCodigo and " +
				"aa.inscripcionProyecto=i.inscripcionCodigo and i.preInscripcion=pp.preinscripcionCodigo " +
				"and pp.estudiante=?1 and i.inscripcionCodigo=r.inscripcionProyectoRetiro and" +
				" r.retiroStatus= 'No Contable' GROUP BY e.estudianteCedula")
		public Long buscarHorasAcumuladasRetirado(Estudiante est);
		
		/**
		 * Busca una lista de estudiantes dado un status.
		 * @return Lista de estudiantes.
		 * @param status status del estudiante.
		 */
		public List<Estudiante> findByEstudianteStatus(String status);
		
		/**
		 * Busca una lista de estudiantes dada una direccionPrograma.
		 * @return Lista de estudiantes.
		 * @param direccionPrograma, direccion de programa.
		 */
		public List<Estudiante> findByDireccionProgramae(DireccionPrograma direccionPrograma);
		
		/**
		 * Busca una lista de estudiantes para inscribirlos.
		 */
		@Query("SELECT est FROM Estudiante as est where estudianteStatus='Apto' and Not est In (Select estudiante From InscripcionTaller)")
		public List<Estudiante> buscarEstudiantesAInscribir();
		
		

}
