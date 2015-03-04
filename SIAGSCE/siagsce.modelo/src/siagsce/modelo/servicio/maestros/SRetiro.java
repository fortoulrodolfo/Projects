package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.data.maestros.Retiro;
import siagsce.modelo.repositorio.maestros.IRetiroRepositorio;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Retiro}
* 
* @author Iterator
*/
@Service("sretiro")
public class SRetiro {
	
	@Autowired
	IRetiroRepositorio retiroRepositorio;
	
	
	/**
	 * Busca todos Retiros pertenecientes a un motivo
	 * @param motivo, el cual es el motivo por el cual se retira un
	 * estudiante de SCE
	 * @return una lista de retiros según un motivo dado
	 */
	public List<Retiro> buscarPorMotivo(Motivo motivo){
		return retiroRepositorio.findByMotivoRetiro(motivo);
	}

	
	/**
	 * Guarda en la base de datos un registro de Actividad 
	 * @param retiro, entidad del registro a ser guardado
	 */
	public void guardar(Retiro retiro){
		retiroRepositorio.save(retiro);
	}
}
