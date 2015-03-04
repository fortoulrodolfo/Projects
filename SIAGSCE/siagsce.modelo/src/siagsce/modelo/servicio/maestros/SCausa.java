package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Causa;

import siagsce.modelo.repositorio.maestros.ICausa;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Causa}
* 
* @author Iterator
*/

@Service ("scausa")
public class SCausa {
	@Autowired ICausa causaRepositorio;
	
	/**
	 * Guarda en la base de datos un registro de una Causa
	 * 
	 * @param causa, entidad del registro a ser guardado
	 */
	 public void guardar(Causa causa){
	  causaRepositorio.save(causa);	
	}
	 
	 /**
	  	* Basado en la clave primaria del objeto entrante, busca su registro
		 * correspondiente en la base de datos y lo modifica 
		 * @param causa, entidad del registro  a ser modificado
		 */
	public void modificar(Causa causa){
		causaRepositorio.save(causa);	
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param causa, entidad del registro  a ser eliminado
	 */
	public void eliminar(Causa causa){
		causaRepositorio.delete(causa);
	}
	
	/**
	 * Busca todas las causas registradas 
	 * @return una lista de todas las causas registradas 
	 */
	public List<Causa> buscarTodo(){
		return causaRepositorio.findAll();			
	}

	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @return una causa, según el nombre ingresado
	 */
	public Causa buscarPorNombre (String nombre){
		return causaRepositorio.findByCausaNombre(nombre);
		
	}

}
