package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Exonerado;
import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.repositorio.maestros.IExonerado;



/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Exonerado}
* 
* @author Iterator
*/
@Service("sexonerado")
public class SExonerado {
	@Autowired
	IExonerado exoneradoRepositorio;
	
	
	/**
	 * Guarda en la base de datos un registro de Estudiante Exonerado
	 * @param exo, entidad del registro a ser guardado
	 */
	public void guardar(Exonerado exo){
		exoneradoRepositorio.save(exo);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param exo, entidad del registro  a ser modificado
	 */
	public void modificar(Exonerado exo){
		exoneradoRepositorio.save(exo);	
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param exo, entidad del registro  a ser eliminado
	 */
	public void eliminar(Exonerado exo){
		exoneradoRepositorio.delete(exo);
	}
	
	
	/**
	 * Busca todos los estudiantes exonerados 
	 * @return una lista de estudiantes exonerados 
	 */
	public List<Exonerado> buscarTodo(){
		return exoneradoRepositorio.findAll();	
	}
	
	
	/**
	 * Busca un estudiante exonerado, ingresando su cédula
	 * @param exonerado, parametro a utilizar
	 * @return estudiante exonerado, según la cédula ingresada
	 */
	public Exonerado buscarPorCedula(Exonerado exonerado) {
		return exoneradoRepositorio.findByEstudianteExonerado(exonerado);
	}
	
	
	/**
	 * Busca un estudiante exonerado, ingresando el código del
	 * motivo de exoneración
	 * @param codigo, parametro a utilizar
	 * @return estudiante exonerado, según lel código del motivo ingresado
	 */
	public Exonerado buscarPorCodigo(Integer codigo) {
		return exoneradoRepositorio.findByExoneradoCodigo(codigo);
	}
	
	
	/**
	 * Listado de estudiantes exonerados por motivo
	 * @param motivo, parametro a utilizar
	 * @return una lista de estudiantes, según el nombre del
	 * motivo ingresado
	 */
	public List<Exonerado> buscarPorMotivo(Motivo motivo){
		return exoneradoRepositorio.findByMotivoExonerado(motivo);
	}
	
	
	
}
