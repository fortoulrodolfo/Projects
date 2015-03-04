package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.repositorio.maestros.IDireccionProgramaRepositorio;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link DireccionPrograma}
* 
* @author Iterator
*/
@Service("sdireccionPrograma")
public class SDireccionPrograma {
	@Autowired
	IDireccionProgramaRepositorio direccionProgramaRepositorio;
	
	
	
	/**
	 * Guarda en la base de datos un registro de Actividad 
	 * 
	 * @param dir, entidad del registro a ser guardado
	 */
	public void guardar(DireccionPrograma dir){
		direccionProgramaRepositorio.save(dir);
	}
	
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param dir, entidad del registro  a ser modificado
	 */
	public void modificar(DireccionPrograma dir){
		direccionProgramaRepositorio.save(dir);
	}
	
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param dir, entidad del registro  a ser eliminado
	 */
	public void eliminar(DireccionPrograma dir){
		direccionProgramaRepositorio.delete(dir);
	}
	
	
	/**
	 * Busca todas las Direciones de Programas registradas 
	 * @return una lista de todas las Direcciones de Programas registradas 
	 */
	public List<DireccionPrograma> buscarTodo(){
		return direccionProgramaRepositorio.findAll();
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param codigo, entidad del registro buscado
	 * @return código de la Dirección de Programa correspondiente
	 */
	public DireccionPrograma buscarPorCodigo(Integer codigo){
		return direccionProgramaRepositorio.findByDireccionCodigo(codigo);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param nombre, entidad del registro buscado
	 * @return nombre de la Dirección de Programa correspondiente
	 */
	public DireccionPrograma buscarPorNombre(String nombre){
		return direccionProgramaRepositorio.findByDireccionNombre(nombre);
	}



}
