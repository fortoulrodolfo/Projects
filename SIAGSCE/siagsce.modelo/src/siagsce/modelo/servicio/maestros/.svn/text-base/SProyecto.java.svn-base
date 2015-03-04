package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.repositorio.maestros.IProyectoRepositorio;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Proyecto}
* 
* @author Iterator
*/
@Service("sproyecto")
public class SProyecto {
	@Autowired
	IProyectoRepositorio proyectoRepositorio;
	
	
	/**
	 * Guarda en la base de datos un registro de Actividad 
	 * @param pro, entidad del registro a ser guardado
	 */
	public void guardar(Proyecto pro){
		proyectoRepositorio.save(pro);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param pro, entidad del registro  a ser modificado
	 */
	public void modificar(Proyecto pro){
		proyectoRepositorio.save(pro);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param pro, entidad del registro  a ser eliminado
	 */
	public void eliminar(Proyecto pro){
		proyectoRepositorio.delete(pro);
	}
	
	
	/**
	 * Busca todas los proyectos pertenecientes al SCE
	 * @return una lista de todas los proyectos registrados
	 */
	public List<Proyecto> buscarTodo(){
		return proyectoRepositorio.findAll();
	}
	
	
	/**
	 * Busca todas los proyectos de un programa
	 * @param direccionPrograma, el cual es el objeto de la entidad 
	 * @return una lista de todos los Proyectos que pertenbecen
	 * a una Dirección de Programa
	 */
	public List<Proyecto> buscarPorPrograma(DireccionPrograma direccionPrograma) {
		return proyectoRepositorio.findBydireccionPrograma(direccionPrograma);
	}
	
	
	/**
	 * Busca todas los proyectos de un programa
	 * @param direccionPrograma, y estatus el cual es el objeto de la entidad 
	 * @return una lista de todos los Proyectos que pertenecen
	 * a una Dirección de Programa según su estatus
	 */
	public List<Proyecto> buscarPorProgramaYProyectoStatus(DireccionPrograma direccionPrograma, String sta) {
		return proyectoRepositorio.findByDireccionProgramaAndProyectoStatus(direccionPrograma,sta);
	}

	
	/**
	 * Busca todas los proyectos de un programa
	 * @param dcodigo, el cual es el objeto de la entidad 
	 * @return un Proyecto, según el codigo ingresado
	 */
	public Proyecto buscarPorCodigo(String codigo){
		return proyectoRepositorio.findByProyectoCodigo(codigo);
	}
	
	
	/**
	 * Busca todas los proyectos de un programa
	 * @param  estatus el cual es el objeto de la entidad 
	 * @return una lista de todos los Proyectos según su estatus
	 */
	public List<Proyecto> buscarPorStatus(String sta) {
		return proyectoRepositorio.findByProyectoStatus(sta);
	}

	public List<Profesor>buscarResponsable(String proyecto){
		return proyectoRepositorio.buscarResponsable(proyecto);
	}
	
	/**
	 * Busca todas los proyectos activos de un profesor responsable
	 * @param  profesor el cual es el objeto de la entidad 
	 * @return una lista de todos los Proyectos segun un profesor
	 */

	public List<Proyecto>buscarProyectosActivodelProfesorResponsable(Profesor profesor){
		return proyectoRepositorio.buscarProyectosActivosResponsable(profesor);
	}
	
	/**
	 * Busca todas los proyectos en ejecucion de un profesor responsable
	 * @param  profesor el cual es el objeto de la entidad 
	 * @return una lista de todos los Proyectos segun un profesor
	 */

	public List<Proyecto>buscarProyectosEnEjecuciondelProfesorResponsable(Profesor profesor){
		return proyectoRepositorio.buscarProyectosEnEjecucionResponsable(profesor);
	}
	/**
	 * Busca todas los proyectos en ejecucion o activos de un profesor responsable
	 * @param  profesor el cual es el objeto de la entidad 
	 * @return una lista de todos los Proyectos segun un profesor
	 */

	public List<Proyecto>buscarProyectosdelProfesorResponsable(Profesor profesor){
		return proyectoRepositorio.buscarProyectosResponsable(profesor);
	}
	
	
	/**
	 * Retorna el proyecto al que pertenece la actividad dada.
	 * @param  actividad
	 * @return proyecto
	 */

	public Proyecto buscarProyectoPorActividad(Actividad actividad){
		return proyectoRepositorio.busacarProyecto(actividad);
	}
	/**
	 * busca si el profesor de la cedula de entrada es responsable de un proyecto
	 * @param Profesor al cual buscaremos
	 * @return el profesor resultante de la busqueda
	 * */
	public Profesor buscarResponsableExit(Profesor profesor){
		return proyectoRepositorio.buscarResponsableExistente(profesor);
	}
	public List<Proyecto> buscarProyectoNoInactivosPrograma(DireccionPrograma programa){
		return proyectoRepositorio.buscarProyectosNoInactivosPorPrograma(programa);
	}
	/**
	 * Busca una lista de proyectos activos o en ejecucion por programa   dado un objeto profesor.
	 * @return Lista de proyecto.
	 * @param profesor, objeto profesor .
	 */
	public List<Proyecto>buscarProyectoResponsablePrograma(Profesor profesor,DireccionPrograma programa){
		return proyectoRepositorio.buscarProyectosResponsablePrograma(profesor, programa);
	}
	
	/**
	 * busca si el profesor de la cedula de entrada es participante de un proyecto
	 * @param Profesor al cual buscaremos
	 * @return el profesor resultante de la busqueda
	 * */
	public Profesor buscarResponsableProyecto(Profesor profesor,String codigoProyecto){
		return proyectoRepositorio.buscarResponsableProyecto(profesor, codigoProyecto);
	}
}
