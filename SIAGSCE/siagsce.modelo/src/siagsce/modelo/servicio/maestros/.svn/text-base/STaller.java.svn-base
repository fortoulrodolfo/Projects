package siagsce.modelo.servicio.maestros;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.repositorio.maestros.ITallerRepositorio;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Taller}
* 
* @author Iterator
*/
@Service("sTaller")
public class STaller {

	@Autowired
	ITallerRepositorio tallerRepositorio;
	
	
	/**
	 * Guarda en la base de datos un registro de Taller 
	 * @param ta, entidad del registro a ser guardado
	 */
	public void guardar(Taller ta){
		tallerRepositorio.save(ta);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param ta, entidad del registro  a ser modificado
	 */
	public void modificar(Taller ta){
		tallerRepositorio.save(ta);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param ta, entidad del registro  a ser eliminado
	 */
	public void eliminar(Taller ta){
		tallerRepositorio.delete(ta);
	}
	
	
	/**
	 * Busca todas los talleres de SCE
	 * @param status, objeto de la entidad 
	 * @return una lista de todos los talleres de SCE
	 * según su status
	 */
	public List<Taller> buscarTodo(String status){
		return tallerRepositorio.findByTallerStatus(status);
	}
	
	
	
	/**
	 * Busca el último taller realizado en SCE
	 * @return Último taller realizado por SCE
	 */
	public Taller ultimoTaller() {
		Taller t= null;
		
		if (!tallerRepositorio.todosOrderFechaCulminacion().isEmpty())
		t = tallerRepositorio.todosOrderFechaCulminacion().get(0);
		
		return t;
	}
	
	
	/**
	 * Busca un taller según el código tipeado
	 * @param codigo, objeto de la entidad 
	 * @return un Taller, según el código dado
	 */
	public Taller buscarPorCodigo(Integer codigo){
		return tallerRepositorio.findByTallerCodigo(codigo);
	}
	
	
	/**
	 * Busca Talleres Activos, sin estudiantes inscritos
	 * @return un Listado de Talleres activos, sin estudiantes inscritos
	 */
	public List<Taller> buscarTalleresActivosSinInscritos(){
		return tallerRepositorio.buscarTalleresActivosSinInscritos();
	}
	
	
	
	/**
	 * Busca todos los talleres de SCE
	 * @return un Listado de Talleres 
	 */
	public List<Taller> buscarTodoV() {
		return tallerRepositorio.findAll();
	}

	
	
	/**
	 * Busca talleres finalizados
	 * @param fechaActual, objeto de la entidad 
	 * @return Listado de Talleres finalizados
	 */
	public List<Taller> buscarTalleresFechaCulminacionPasada(Date fechaActual){
		return tallerRepositorio.buscarTalleresFechaCulminacionPasada(fechaActual);
	}
	
	
	
	/**
	 * Busca talleres proximos a realizarse
	 * @param fechaActual, objeto de la entidad 
	 * @return Listado de Talleres proximos a realizarse
	 */
	public List<Taller> buscarTalleresFechaProximaInscripcion(Date fechaActual){
		return tallerRepositorio.buscarTalleresFechaProximaInscripcion(fechaActual);
	}
	
	/**
	 * Busca una lista de talleres  dada la fecha actual.
	 * @return Lista de talleres.
	 * @param fechaActual, fecha Actual
	 */
	public List<Taller> buscarTalleresActivosConFechaInscripcionValida(Date fechaActual){
		return tallerRepositorio.buscarTalleresActivosFechaInscripcionActiva(fechaActual);
	}
	
	
}