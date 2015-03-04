package siagsce.modelo.repositorio.transacciones;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.ActividadAsignada;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
/**
 * Interfaz Actividad Asignada, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo ActividadAsignada.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(ActividadAsignada actividadAsignada),delete(ActividadAsignada actividadAsignada),findAll(),findOne(Integer actividadAsignadaCodigo),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IActividadAsignadaRepositorio extends JpaRepository<ActividadAsignada,String>{
	
	/**
	 * Metodo que busca una lista de actividades asignadas que 
	 * concuerde con la actividad pasada por parametro.
	 * @param actividad 
	 * @return lista de actividades asignadas.
	 */ 
	@Query("select aa from ActividadAsignada as aa where aa.actividad=?1")
	public List<ActividadAsignada> buscarActividadesAsignadas(Actividad actividad);
	
	/**
	 * Metodo que busca una lista de incripciones al proyecto 
	 * pertenecientes a la actividad pasada por parametro.
	 * @param actividad 
	 * @return lista de inscripciones de proyecto asociadas a la actividad.
	 */
	@Query("select aa.inscripcionProyecto from ActividadAsignada as aa where aa.actividad=?1")
	public List<InscripcionProyecto> findByActividad(Actividad actividad);
	/**
	 * Metodo que busca una lista de incripciones al proyecto 
	 * pertenecientes a la actividad pasada por parametro,retorna solo las inscripciones de proyecto donde la actividad no haya sido ejecutada.
	 * @param actividad 
	 * @return lista de inscripciones de proyecto asociadas a la actividad.
	 */
	@Query("select aa.inscripcionProyecto from ActividadAsignada as aa where aa.actividad=?1 and aa.actividadAsignadaCodigo not in (select ae.actividadasignada from ActividadEjecutada as ae)")
	public List<InscripcionProyecto> findByActividadAsignadaPeroNoEjecutada(Actividad actividad);
	/**
	 * Metodo que retorna la actividad asignada que concuerde con el codigo de la actividad.
	 * @param Codigo de la actividad asignada
	 * @return La actividad asignada.
	 * */
	public ActividadAsignada findByactividadAsignadaCodigo (Integer codigo);
	/**
	 * Busca la lista de actividades que fueron asignadas
	 * a un estudiante en determinado proyecto.
	 * @param est Estudiante al que se le ha asignado las actividades.
	 * @param proyecto donde ha inscrito el estudiante.
	 * @return Lista de actividades asignadas. 
	 */
	@Query("select aa from ActividadAsignada as aa, InscripcionProyecto ip, PreInscripcionProyecto as pp " +
			"where aa.inscripcionProyecto=ip.inscripcionCodigo and ip.preInscripcion=pp.preinscripcionCodigo" +
			" and pp.proyectop=?2 and pp.estudiante=?1")
	public List<ActividadAsignada> buscarPorEstudianteProyecto(Estudiante est, Proyecto pro);
	/**
	 * Busca todas las actividades que fueron asignadas a un estudiante.
	 * @param est Estudiante al que se le ha asignado las actividades.
	 * @return Lista de actividades asignadas.
	 */
	@Query("select aa from ActividadAsignada as aa, InscripcionProyecto ip, PreInscripcionProyecto as pp " +
			"where aa.inscripcionProyecto=ip.inscripcionCodigo and ip.preInscripcion=pp.preinscripcionCodigo" +
			" and pp.estudiante=?1")
	public List<ActividadAsignada> buscarPorEstudiante(Estudiante est);
	/**
	 * Busca todas las actividades asignadas que no han sido ejecutadas
	 * por un estudiante.
	 * @return Lista de actividades asignadas.
	 * @param est Estudiante al que se le ha asignado las actividades.
	 */
	@Query("select DISTINCT(aa) from ActividadAsignada as aa, PreInscripcionProyecto as pp, InscripcionProyecto as i, ActividadEjecutada as ae " +
			"where pp.preinscripcionCodigo = i.preInscripcion and i.inscripcionCodigo = aa.inscripcionProyecto and" +
			" pp.estudiante=?1 and aa.actividadAsignadaCodigo not in (select ae.actividadasignada from ActividadEjecutada as ae)")
	public List<ActividadAsignada> buscarPorEstudianteSinEjecutar(Estudiante est);
	/**
	 * Busca todas las actividades asignadas dado una inscripcion de proyecto.
	 * @return Lista de actividades asignadas.
	 * @param inscripcionProyecto Inscripcion de proyecto.
	 */
	public List<ActividadAsignada> findByInscripcionProyecto (InscripcionProyecto inscripcionProyecto);
	@Query("select ins from InscripcionProyecto as ins where ins.proyectoi=?2 and ins not in (select act.inscripcionProyecto from ActividadAsignada as act where act.actividad=?1)")
	public List<InscripcionProyecto>buscarInscripcionActividadNoAsignada(Actividad actividad,Proyecto proyecto);
}
