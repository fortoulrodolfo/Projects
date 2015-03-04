package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zul.ListModelList;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.CoordinacionSce;
import siagsce.modelo.data.maestros.Descarga;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.repositorio.maestros.IActividadRepositorio;
import siagsce.modelo.repositorio.maestros.IDescarga;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Descarga}
* 
* @author Iterator
*/
@Service("sdescarga")
public class SDescarga {
	@Autowired
	IDescarga descarga;

	
	/**
	 * Busca todas los archivos que pertenecen a 
	 * la opción de Descarga  
	 * @return una lista de todas los archivos de interés de SCE
	 * para descragarlos
	  */
	public List<Descarga> buscarTodo(){
		return descarga.findAll();
	}
	
	
	/**
	 * Guarda en la base de datos un registro de Coordinación SCE
	 * @param des, entidad del registro a ser guardado
	 */

	public void guardar(Descarga des){
		descarga.save(des);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param des, entidad del registro  a ser eliminado
	 */
	public void eliminar(Descarga des) {
		descarga.delete(des);
	}
	
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param codigo, entidad del registro buscado
	 * @return código de la descarga correspondiente
	 */
	public Descarga buscarPorCodigo(Integer codigo){
		return descarga.findByDescargaCodigo(codigo);
	}
	

}
