package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.CoordinacionSce;
import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.repositorio.maestros.ICoordinadorSceRepositorio;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link CoordinadorSce}
* 
* @author Iterator
*/
@Service("scoordinacorSce")
public class SCoordinadorSce {
	@Autowired
	ICoordinadorSceRepositorio coordinadorSceRepositorio;
	
	
	/**
	 * Guarda en la base de datos un registro de Coordinador SCE
	 * @param cosce, entidad del registro a ser guardado
	 */
	public void guardar(CoordinadorSce cosce){
		coordinadorSceRepositorio.save(cosce);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param cosce, entidad del registro  a ser modificado
	 */
	public void modificar(CoordinadorSce cosce){
		coordinadorSceRepositorio.save(cosce);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param cosce, entidad del registro  a ser eliminado
	 */
	public void eliminar(CoordinadorSce cosce){
		coordinadorSceRepositorio.delete(cosce);
	}
	
	
	/**
	 * Busca todas las profesores que han pertenecido a 
	 * Coordinación de SCE  registradas 
	 * @return una lista de todas las profesores que han ocupado el 
	 * puesto de Coordinador de SCE 
	 */
	public List<CoordinadorSce> buscarTodo(){
		return coordinadorSceRepositorio.findAll();
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos 
	 * @param estatus, entidad del registro a ser buscado
	 */
	 public CoordinadorSce buscarEstatus(String estatus){
		 return coordinadorSceRepositorio.findByCoordinadorStatus(estatus);		 
	 }
	 
	 
	 /**
		 * Busca todas las profesores que han pertenecido a 
		 * Coordinación de SCE  ordenador por fecha
		 * @return una lista de todas las profesores que han ocupado el 
		 * puesto de Coordinador de SCE ordenador
		 * por fecha de manera ascendente 
		 */
	 public List<CoordinadorSce>buscarOrdenado(){
		  return coordinadorSceRepositorio.buscarOrdenado();
	 }
	 /**
	  * Trae al director activo de acuerdo a la cedula
	  * @param profesor, al cual buscaremos
	  * @param estatus, por el cual se buscara al director
	  * @return Coordinador del Servicio comunitario Estudiantil
	  * */
	 public CoordinadorSce buscarPorProfesorYEstatus(Profesor profesor,String estatus){
		 return coordinadorSceRepositorio.findByProfesorcoordinadorAndCoordinadorStatus(profesor, estatus);
	 }
}