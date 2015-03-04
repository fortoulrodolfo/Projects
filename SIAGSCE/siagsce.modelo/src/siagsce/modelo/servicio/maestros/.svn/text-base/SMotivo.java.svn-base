package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Causa;
import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.repositorio.maestros.IMotivo;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Motivo}
* 
* @author Iterator
*/
@Service("smotivo")
public class SMotivo {
	@Autowired
	IMotivo motivoRepositorio;
	
	
	/**
	 * Guarda en la base de datos un registro de Motivo
	 * @param mot, entidad del registro a ser guardado
	 */
	public void guardar(Motivo mot){
		motivoRepositorio.save(mot);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param mot, entidad del registro  a ser modificado
	 */
	public void modificar(Motivo mot){
		motivoRepositorio.save(mot);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param mot, entidad del registro  a ser eliminado
	 */
	public void eliminar(Motivo mot){
		motivoRepositorio.delete(mot);
	}
	
	
	/**
	 * Busca todas los motivos registrados
	 * @return una lista de todos los motivos registrados
	 */
	public List<Motivo> buscarTodo(){
	 return motivoRepositorio.findAll();
	}
	
	
	/**
	 * Busca todos los motivos por código
	 * @param código, es el código perteneciente a un motivo registrado
	 * @return Motivo, según código tipeado
	 */
	public Motivo buscarPorCodigo(Integer codigo){
		return motivoRepositorio.findByMotivoCodigo(codigo);	
	}
	
	
	/**
	 * Busca los motivos pertenecientes a una causa tipeada
	 * @param causa, es la causa perteneciente a un motivo
	 * @return una lista de los motivos pertenecientes a una causa
	 */
	public List<Motivo> buscarPorCausa(Causa causa){
		return motivoRepositorio.findByCausaMotivo(causa);
	}
	
}
