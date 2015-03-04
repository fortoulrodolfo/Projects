package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.repositorio.maestros.IInscripcionTallerRepositorio;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link InscripcionTaller}
* 
* @author Iterator
*/
@Service("sinscripciontaller")
public class SInscripcionTaller {

	@Autowired
	IInscripcionTallerRepositorio inscripcionTallerRepositorio;
	
	
	/**
	 * Guarda en la base de datos un registro de Inscripción al taller 
	 * @param insp, entidad del registro a ser guardado
	 */
	public void guardar(InscripcionTaller insp){
		inscripcionTallerRepositorio.save(insp);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param ispt, entidad del registro  a ser modificado
	 */
	public void modificar(InscripcionTaller insp){
		inscripcionTallerRepositorio.save(insp);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param insp, entidad del registro  a ser eliminado
	 */
	public void eliminar(InscripcionTaller insp){
		inscripcionTallerRepositorio.delete(insp);
	}
	
	
	/**
	 * Busca todas las inscripciones pertenecientes a un taller 
	 * @return una lista con todas las inscripciones pertenecientes
	 * a un taller 
	 */
	public List<InscripcionTaller> buscarTodo(){
		return inscripcionTallerRepositorio.findAll();
	}
	
	
	/**
	 * Busca estudiantes inscritos en un taller 
	 * @param est, son los estudiantes que pertenecen a un taller 
	 * @return una lista de estudiantes inscritos en un taller 
	 */
	public List<InscripcionTaller> buscarPorEstudiante(Estudiante est){
		return inscripcionTallerRepositorio.findByEstudiante(est);
	}
	
	
	/**
	 * Busca una inscripción según estudiante y taller ingresado
	 * @param est y taller, entidades a usar en la búsqueda
	 * @return inscripción del taller  según estudiante y taller ingresado 
	 */
	public InscripcionTaller buscarPorEstudianteYTaller(Estudiante est,Taller taller){
		return inscripcionTallerRepositorio.findByEstudianteAndTaller(est,taller);
	}
	
	
	/**
	 * Busca estudiantes inscritos en un taller
	 * @param taller, entidad a usar en la búsqueda
	 * @return Listado de estudiantes inscritos en un taller
	 */
	public List<Estudiante> buscarEstudiantesDeUnTaller(Taller taller){
		return inscripcionTallerRepositorio.buscarEstudiantesDeUnTaller(taller);
	}
	
	
	/**
	 * Busca una inscripción según estudiante y taller ingresado
	 * @param est y taller, entidades a usar en la búsqueda
	 * @return inscripción del taller  según estudiante y taller ingresado 
	 */
	public InscripcionTaller buscarPorEstudianteAndTaller(Estudiante estudiante, Taller taller){
		return inscripcionTallerRepositorio.findByEstudianteAndTaller(estudiante, taller);
	}
	
	
	/**
	 * Busca estudiantes inscritos en un taller inactivo
	 * @param taller, entidades a usar en la búsqueda
	 * @return Listado de estudiantes inscritos en un taller inactivo 
	 */
	public List<Estudiante> buscarEstudiantesDeUnTallerInactivo(Taller taller){
		return inscripcionTallerRepositorio.buscarEstudiantesDeUnTallerInactivo(taller);
	}
	
	/**
	 * Busca todas las inscripciones pertenecientes a un taller especifico
	 * @param taller, entidades a usar en la búsqueda
	 * @return una lista con todas las inscripciones pertenecientes
	 * a un taller especifico
	 */
	public List<InscripcionTaller> buscarPorTaller(Taller taller){
		return inscripcionTallerRepositorio.findByTaller(taller);
	}
}
