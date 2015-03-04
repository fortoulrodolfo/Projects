package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.CoordinacionSce;
import siagsce.modelo.repositorio.maestros.ICoordinacioncSceRepositorio;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link CoordinacionSce}
* 
* @author Iterator
*/
@Service("scoordinacionSce")
public class SCoordinacionSce {
	@Autowired
	ICoordinacioncSceRepositorio coordinacioncSceRepositorio;
	
	/**
	 * Guarda en la base de datos un registro de Coordinación SCE
	 * @param csce, entidad del registro a ser guardado
	 */

	public void guardar(CoordinacionSce csce){
		coordinacioncSceRepositorio.save(csce);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param csce, entidad del registro  a ser modificado
	 */
	public void modificar(CoordinacionSce csce){
		coordinacioncSceRepositorio.save(csce);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param csce, entidad del registro  a ser eliminado
	 */
	public void eliminar(CoordinacionSce csce){
		coordinacioncSceRepositorio.delete(csce);
	}
	
	/**
	 * Busca todas las personas que han pertenecido a 
	 * Coordinación de SCE  registradas 
	 * @return una lista de todas las personas que han ocupado el 
	 * puesto de Coordinación SCE
	 * registradas 
	 */
	public List<CoordinacionSce> buscarTodo(){
		return coordinacioncSceRepositorio.findAll();
	}
	/**
	 * Trae una coordinacion de acuerdo al codigo de entrada
	 * @param codigo, de la coordinacion a buscar
	 * @return Coordinacion retornada de la busqueda 
	 **/
	public CoordinacionSce buscarCodigo(Integer codigo) {
		return coordinacioncSceRepositorio.findByCoordinacionCodigo(codigo);
	}
	


}
