package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.zkoss.zul.ListModelList;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.repositorio.maestros.IActividadRepositorio;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Actividad}
* 
* @author Iterator
*/
@Service("sactividad")
public class SActividad {
	@Autowired
	IActividadRepositorio actividadRepositorio;

	/**
	 * Guarda en la base de datos un registro de Actividad 
	 * @param act, entidad del registro a ser guardado
	 */
	public void guardar(Actividad act){
		actividadRepositorio.save(act);
	}
	/**
	 * Basado en un nombre busca una actividad
	 * @param nombre de la actividad a buscar
	 * @return Lista de actividades con el nombre
	 * */
	public List<Actividad>buscarPorNombre(String nombre,Proyecto proyecto){
		return actividadRepositorio.findByActividadNombreAndProyectoa(nombre, proyecto);
	}
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param act, entidad del registro  a ser modificado
	 */
	public void modificar(Actividad act){
		actividadRepositorio.save(act);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param act, entidad del registro  a ser eliminado
	 */
	public void eliminar(Actividad act){
		actividadRepositorio.delete(act);
	}
	
	
	/**
	 * Busca todas las actividades registradas 
	 * @return una lista de todas las actividades registradas 
	 */
	public List<Actividad> buscarTodo(){
		return actividadRepositorio.findAll();
	}
	
	
	/**
	 * Busca todas las actividades pertenecientes a un proyecto
	 * @param proyecto, el cual es el proyecto a las que pertenecen
	 * las actividades 
	 * @return una lista de todas las actividades registradas a un proyecto
	 */
	public  List<Actividad> buscarporProyecto(Proyecto proyecto){
		return actividadRepositorio.findByProyectoa(proyecto);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param codigo, entidad del registro buscado
	 * @return código de la actividad correspondiente
	 */
	public Actividad buscarCodigo(Integer codigo){
		return actividadRepositorio.findByActividadCodigo(codigo);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param profesor, entidad del registro buscado
	 * @return Listado de Proyectos pertenecientes a un Profesor Participante
	 */
	public List<Proyecto> buscarProyectosDeUnProfesorParticipante (Profesor profesor){
		return actividadRepositorio.findProyectosDeUnProfesorParticipante(profesor);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param profesor, entidad del registro buscado
	 * @return Listado de Actividades pertenecientes a un Profesor Participante
	 */
	public List<Actividad> buscarActividadesDeUnProfesorParticipanteDeUnProyecto(Profesor profesor, Proyecto proyecto){
		return actividadRepositorio.findActividadesDeUnProfesorParticipanteDeUnProyecto(profesor, proyecto);
	}
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param profesor, entidad del registro buscado
	 * @return Listado de Actividades pertenecientes a un Profesor Participante
	 */
	public List<Profesor>buscarParticipante(String proyecto){
		return actividadRepositorio.buscarporParticipante(proyecto );
	}
	/**
	  * selecciona aquellas actividades pertenecientes a un participante
	  * que no sean del proyecto especificado
	  * @param profesor participante de la actividad
	  * @param proyecto del cual se descriminara las actividaddes
	  * @return lista de actividades 
	  * */
	public List<Actividad>participanteOtroProyecto(Profesor profesor){
		return actividadRepositorio.buscarParticipanteOtroProyecto(profesor);
	}
	/**
	  * selecciona aquellas actividades pertenecientes a un participante
	  * que no sean del proyecto especificado
	  * @param profesor participante de la actividad
	  * @param proyecto del cual se descriminara las actividaddes
	  * @return lista de actividades 
	  * */
	public List<Actividad>participanteOtroProyecto(Profesor profesor, Proyecto proyecto){
		return actividadRepositorio.buscarParticipanteOtroProyecto(profesor,proyecto);
	}
	
	/**
	 * busca si el profesor de la cedula de entrada es participante de un proyecto
	 * @param Profesor al cual buscaremos
	 * @return el profesor resultante de la busqueda
	 * */
	public Profesor buscarParticipanteEx(Profesor profesor){
		return actividadRepositorio.buscarParticipanteExistente(profesor);
	}
	 /** Busca todas las actividades dado profesor participante.
	 * @return lista de actividades.
	 * @param profesor, el profesor participante de esas actividades.
	 */

public List<Proyecto> findProyectosNoInactivosDeUnProfesorParticipante(Profesor profesor){
	return actividadRepositorio.findProyectosNoInactivosDeUnProfesorParticipante(profesor);
}

}
