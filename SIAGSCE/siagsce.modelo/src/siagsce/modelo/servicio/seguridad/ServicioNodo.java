package siagsce.modelo.servicio.seguridad;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siagsce.modelo.data.seguridad.Nodo;
import siagsce.modelo.repositorio.seguridad.INodo;
import siagsce.modelo.repositorio.seguridad.IUsuario;
/**
 * ServicioNodo ofrece los servicios necesarios para gestionar los
 * datos de los nodos.
 * Nota: El ServicioNodo es una capa intermedia entre la interface INodo
 * y los viewmodels que necesiten de dicha interfaz.
 * @author Iterator
 */
@Service("snodo") //Definiendo la variable servicio
public class ServicioNodo{
	private @Autowired INodo ia; 

	/**
	  * Retorna una lista de nodos dado el padre.
	  * @param padre de los nodos.
	  * @link {@link INodo}
	  */
	public List<Nodo> buscarPadre(Integer padre) {
		List<Nodo> children=ia.findByPadreOrderByPrioridadAsc(padre);
	    return children ;
	}
	/**
	  * Retorna un nodo.
	  * @param idNodo representa el ID del nodo a buscar.
	  * @return Nodo.
	  * @link {@link INodo}
	  */
	public Nodo buscarNodo(Integer idNodo) {
		Nodo nodo=ia.findOne(idNodo);
	    return nodo ;
	}
	
	
}

	
