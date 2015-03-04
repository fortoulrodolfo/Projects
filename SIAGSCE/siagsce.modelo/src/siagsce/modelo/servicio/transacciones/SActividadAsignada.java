package siagsce.modelo.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.pdf.AsianFontMapper;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.ActividadAsignada;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.repositorio.transacciones.IActividadAsignadaRepositorio;
/**
 * SActividadAsignadas ofrece los servicios necesarios para que 
 * las actividades sean asignadas a los estudiantes inscritos en 
 * un proyecto.
 * @author Iterator
 */
@Service("sactividadAsignada")
public class SActividadAsignada {
	
	@Autowired
	IActividadAsignadaRepositorio actividadAsignadaRepositorio;
	/**
	 * Guarda en la base de datos(BD)la actividad junto con el estudiante que la va a ejecutar.
	 * @param actisg es la actividad asignada a guardar.
	 */
	public void guardar(ActividadAsignada actisg){
		actividadAsignadaRepositorio.save(actisg);
	}
	/**
	 * Modifica en la BD los datos de la actividad asignada.
	 * @param actisg es la actividad asignada a modificar.
	 */
	public void modificar(ActividadAsignada actisg){
		actividadAsignadaRepositorio.save(actisg);
	}
	/**
	 * Elimina la actividad asignada de la BD.
	 * @param actisg es la actividad asignada a guardar.
	 */
	public void eliminar(ActividadAsignada actisg){
		actividadAsignadaRepositorio.delete(actisg);
	}
	/**
	 * Busca todas las actividades asignadas guardadas en la BD.
	 * @return lista de todas las actividades asignadas.
	 */
	public List<ActividadAsignada> buscarTodo(){
		return actividadAsignadaRepositorio.findAll();
	}
	/**
	 * Busca todos los estudiantes que se ha inscritos en una actividad.
	 * nota:se trae los datos de la inscripcion en un proyecto, 
	 * dicha inscripcion contiene los datos del estudiante.
	 * @return lista de todas los estudiantes que se han inscrito en la actividad.
	 * @param actividad representa la actividad que fue asignada a los diferentes
	 * estudiantes que se van a obtener en la lista.
	 */
	public List<InscripcionProyecto> buscarInscripcionProyecto(Actividad actividad){
		return actividadAsignadaRepositorio.findByActividad(actividad);
	}
	/**
	 * Busca todos los estudiantes que se ha inscritos en una actividad.
	 * nota:se trae los datos de la inscripcion en un proyecto, 
	 * dicha inscripcion contiene los datos del estudiante.
	 * @return lista de todas los estudiantes que se han inscrito en la actividad, la actividad no debe estar ejecutada.
	 * @param actividad representa la actividad que fue asignada a los diferentes
	 * estudiantes que se van a obtener en la lista.
	 */
	public List<InscripcionProyecto> buscarInscripcionProyectoEstudianteRetirar(Actividad actividad){
		return actividadAsignadaRepositorio.findByActividadAsignadaPeroNoEjecutada(actividad);
	}
	
	/**
	 * Busca todos los estudiantes con su respectiva actividad asignada.
	 * @return lista de todos los estudiantes con sus actividades asignadas.
	 * @param actividad representa la actividad que fue asignada 
	 * a los diferentes estudiantes que se van a obtener en la lista.
	 */
	public List<ActividadAsignada> buscarActividadesAsignadas(Actividad act){
		return actividadAsignadaRepositorio.buscarActividadesAsignadas(act);
	}
	/**
	 * Busca la actividad asignada que concuerde con el
	 *  codigo de la actividad pasada por parametro.
	 * @return La actividad asignada.
	 * @param Codigo de la actividad a obtener.
	 */
	public ActividadAsignada buscarActividadAsignada(Integer codigo){
		return actividadAsignadaRepositorio.findByactividadAsignadaCodigo(codigo);
	}
	/**
	 * Busca la lista de actividades que fueron asignadas
	 * a un estudiante en determinado proyecto.
	 * @return Lista de actividades asignadas.
	 * @param est Estudiante al que se le ha asignado las actividades.
	 * @param proyecto donde ha inscrito el estudiante.
	 */
	public List<ActividadAsignada> buscarPorEstudianteProyecto(Estudiante est, Proyecto pro){
		return actividadAsignadaRepositorio.buscarPorEstudianteProyecto(est,pro);
	}
	/**
	 * Busca todas las actividades asignadas dado un estudiante.
	 * @return Lista de actividades asignadas.
	 * @param est Estudiante al que se le ha asignado las actividades.
	 */
	public List<ActividadAsignada> buscarPorEstudiante(Estudiante est){
		return actividadAsignadaRepositorio.buscarPorEstudiante(est);
	}
	/**
	 * Busca todas las actividades asignadas que no han sido ejecutadas
	 * por un estudiante.
	 * @return Lista de actividades asignadas.
	 * @param est Estudiante al que se le ha asignado las actividades.
	 */
	public List<ActividadAsignada> buscarPorEstudianteSinEjecutar(Estudiante est){
		return actividadAsignadaRepositorio.buscarPorEstudianteSinEjecutar(est);
	}
	/**
	 * Busca todas las actividades asignadas dado una inscripcion de proyecto(estudiante).
	 * @return Lista de actividades asignadas.
	 * @param inscripcionProyecto Inscripcion de proyecto(estudiante).
	 */
	public List<ActividadAsignada> buscarPorInscripcionProyecto(InscripcionProyecto inscripcionProyecto){
		return actividadAsignadaRepositorio.findByInscripcionProyecto(inscripcionProyecto);
	}
	
	public List<InscripcionProyecto> buscarInscritosNoAsignadosActividad(Actividad actividad,Proyecto proyecto){
		System.out.println(actividad.getActividadNombre()+proyecto.getProyectoCodigo());
		return actividadAsignadaRepositorio.buscarInscripcionActividadNoAsignada(actividad, proyecto);
	}

}
