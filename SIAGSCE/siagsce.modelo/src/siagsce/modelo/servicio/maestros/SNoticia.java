package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zul.ListModelList;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Descarga;
import siagsce.modelo.data.maestros.Noticia;
import siagsce.modelo.repositorio.maestros.INoticia;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Noticia}
* 
* @author Iterator
*/
@Service("snoticia")
public class SNoticia {
	@Autowired
	INoticia noticia;
	
	
	
	/**
	 * Busca todas las noticias registradas 
	 * @return una lista de todas las noticias registradas 
	 */
	public List<Noticia> buscarTodo(){
		return noticia.findAll();
	}
	
	
	/**
	 * Guarda en la base de datos un registro de Noticia
	 * @param des, entidad del registro a ser guardado
	 */
	public void guardar(Noticia des){
		noticia.save(des);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param des, entidad del registro  a ser eliminado
	 */
	public void eliminar(Noticia des) {
		noticia.delete(des);
	}
	
	
	/**
	 * Busca una Noticia, de acuerdo a un código tipeado
	 * @param codigo, se refiere al código de la noticia
	 * @return una Noticia según el código dado
	 */
	public Noticia buscarPorCodigo(Integer codigo){
		return noticia.findByNoticiaCodigo(codigo);
	}
	
	

}
