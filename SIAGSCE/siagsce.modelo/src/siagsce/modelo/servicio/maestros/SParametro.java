package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Parametro;
import siagsce.modelo.repositorio.maestros.IParametro;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Parametro}
* 
* @author Iterator
*/
@Service("sparametro")
public class SParametro {
	
	@Autowired
	IParametro parametroRepositorio;
	
	/**
	 * Guarda en la base de datos un registro de Parametro 
	 * @param param, entidad del registro a ser guardado
	 */
	public void guardar(Parametro param){
		parametroRepositorio.save(param);
	}

	public Parametro buscarPorNombre(String nombre){
		return parametroRepositorio.findByParametroNombre(nombre);
	}
	
	public List<Parametro> buscarTodos(){
		return parametroRepositorio.findAll();
	}
}
