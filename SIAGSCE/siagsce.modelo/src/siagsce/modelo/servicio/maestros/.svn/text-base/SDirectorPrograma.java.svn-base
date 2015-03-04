package siagsce.modelo.servicio.maestros;

import java.util.List;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.repositorio.maestros.IDirectorProgramaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Actividad}
* 
* @author Iterator
*/
@Service("sdirector")
public class SDirectorPrograma {
	@Autowired
	IDirectorProgramaRepositorio directorProgramaRepositorio;
	
	
	/**
	 * Guarda en la base de datos un registro de Actividad 
	 * @param dir, entidad del registro a ser guardado
	 */
	public void guardar(DirectorPrograma director){
		
		directorProgramaRepositorio.save(director);
	}
	
	
	/**
	 * Busca todas los Directores de Programa 
	 * @return una lista de todos los Directores de Programa registrados 
	 */
	public List<DirectorPrograma> buscarTodo(){
		return directorProgramaRepositorio.findAll();
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param directorPrograma, entidad del registro  a ser eliminado
	 */
	public void eliminar(DirectorPrograma directorPrograma){
		
		directorProgramaRepositorio.delete(directorPrograma);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param estatus y programa entidades del registro buscado
	 * @return Director por programa con su respectivo estatus
	 */
	public DirectorPrograma buscarDirectorPorProgramaYEstatus(String estatus,DireccionPrograma programa){
		return directorProgramaRepositorio.findByDirectorProgramaStatusAndDireccionPrograma(estatus, programa);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param  programa entidad del registro buscado
	 * @return Director por programa
	 */
	public DirectorPrograma buscarPorPrograma(DireccionPrograma programa){
		return directorProgramaRepositorio.findByDireccionPrograma(programa);
	}
	
	
	/**
	 * Busca todas los Directores de Programa 
	 * @return una lista de todos los Directores de Programa registraddos
	 * de forma ordenada
	 */
	public List<DirectorPrograma>buscarOrdenado(){
		return directorProgramaRepositorio.buscarTodosOrdenados();
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param estatus, entidad del registro buscado
	 * @return Listado de Directores de Programa por estatus
	 */
	public List<DirectorPrograma> buscarPorEstatus(String estatus){
		return directorProgramaRepositorio.findByDirectorProgramaStatus(estatus);
				
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param prof, entidad del registro buscado
	 * @return Si el profesor pertenece o no a una Dirección de Programa
	 */
	public DirectorPrograma buscarPorProfesor(Profesor prof){
		return directorProgramaRepositorio.findByProfesor(prof);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param codigoDireccionPrograma, entidad del registro buscado
	 * @return Listado de Directores por Dirección de Programa
	 */
	public List<DirectorPrograma>  buscarTodosPorDireccion(Integer codigoDireccionPrograma){
		return directorProgramaRepositorio.buscarTodosPorDireccion(codigoDireccionPrograma);
	}

	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param profesor y estatus, entidades del registro buscado
	 * @return Profesor que pertenece a una Dirección de Programa 
	 * con su respectivo estatus
	 */
	public DirectorPrograma buscarPorProfesorYEstatus(Profesor profesor, String status){
		return directorProgramaRepositorio.findByProfesorAndDirectorProgramaStatus(profesor, status);
	}

	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param programa y estatus, entidades del registro buscado
	 * @return Director que pertenece a una Dirección de Programa 
	 * con su respectivo estatus
	 */
	public DirectorPrograma buscarPorProgramaYEstatus(DireccionPrograma programa , String status){
		return directorProgramaRepositorio.findByDireccionProgramaAndDirectorProgramaStatus(programa, status);
	}
	/**
	 * Busca un Director de Programa  dado el profesor y el status del director de programa.
	 * @return el director de programa.
	 * @param profesor y directorProgramaStatus, profesor y el status.
	 */
	public DirectorPrograma buscarDirectorProgramaEstatus(Profesor profesor,DireccionPrograma programa ,String estatus){
		return directorProgramaRepositorio.buscarDirectorProgramaEstatus(profesor, programa, estatus);
	}


}


