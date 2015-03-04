package siagsce.modelo.servicio.transacciones;

import java.util.List;

import org.aspectj.apache.bcel.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;

import siagsce.modelo.repositorio.transacciones.IInscripcionProyectoRepositorio;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link InscripcionProyecto}
* 
* @author Iterator
*/

@Service("sinscripcionProyecto")
public class SInscripcionProyecto {
	@Autowired
	IInscripcionProyectoRepositorio inscripcionProyectoRepositorio;
	
	/**
	 * Guarda en la base de datos un registro de Inscripcion Proyecto 
	 * en la Base de Datp
	 * @param insp, entidad del registro a ser guardado
	 */
	
	public void guardar(InscripcionProyecto insp){
		inscripcionProyectoRepositorio.save(insp);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param insp, entidad del registro  a ser modificado
	 */
	
	public void modificar(InscripcionProyecto insp){
		inscripcionProyectoRepositorio.save(insp);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param insp, entidad del registro  a ser eliminado
	 */
	
	public void eliminar(InscripcionProyecto insp){
		inscripcionProyectoRepositorio.delete(insp);
	}
	
	/**
	 * Busca todas las Inscripciones de Proyecto registradas 
	 * @return una lista de todas las Inscripciones de Proyecto registradas 
	 */
	
	public List<InscripcionProyecto> buscarTodo(){
		return inscripcionProyectoRepositorio.findAll();
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param proyecto,  entidad del registro buscado
	 * @return una lista de todas las Inscripciones de Proyecto registradas 
	 */
	
	public List<InscripcionProyecto> buscarInscriptos(Proyecto pro){
		return inscripcionProyectoRepositorio.buscarPreinscripciones(pro);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param estudiante,  entidad del registro buscado
	 * @return una lista de todas las Inscripciones de Proyecto registradas 
	 */
	
	
	public List<InscripcionProyecto> buscarPorEstudianteYStatus(Estudiante estudiante, String status){
		return inscripcionProyectoRepositorio.buscarEstudianteInscritoYStatus(estudiante, status);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param estudiante,  entidad del registro buscado
	 * @return una lista de todas las Inscripciones de Proyecto registradas 
	 */
	
	
	public List<InscripcionProyecto> buscarPorEstudiante(Estudiante estudiante){
		return inscripcionProyectoRepositorio.buscarEstudianteInscrito(estudiante);
	}
	
	
	/**
	 * Basado en la clave primaria de los  objetos entrante, busca su registro
	 * correspondiente en la base de datos de acuedo a la preinscripcion y el
	 * estatus 
	 * @param PreInscripcionProyecto,  entidad del registro buscado
	 * @param Estatus,  entidad del registro buscado
	 * @return una lista de todas las Inscripciones de Proyecto registradas 
	 */
	
	public InscripcionProyecto buscarPorPreInscripcionYProyectoStatus(PreInscripcionProyecto pre, String status){
		return inscripcionProyectoRepositorio.findByPreInscripcionAndInscripcionProyectoStatus(pre, status);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param Cod (codigo de la inscripcion),  entidad del registro buscado
	 * @return una inscripcion registradas con ese codigo correspondiente
	 */
	
	public InscripcionProyecto buscarPorCodigo(Integer cod){
		return inscripcionProyectoRepositorio.findByInscripcionCodigo(cod);
	}
	
	/**
	 * Basado en la clave primaria de los  objetos entrante, busca su registro
	 * correspondiente en la base de datos de acuedo a un proyecto y el
	 * estatus 
	 * @param Proyecto,  entidad del registro buscado
	 * @param Estatus,  entidad del registro buscado
	 * @return una lista de todas las Inscripciones de Proyecto registradas 
	 */
	public List<InscripcionProyecto> buscarPorCodigoYEstatus(Proyecto proyecto, String status){
		return inscripcionProyectoRepositorio.findByProyectoiAndInscripcionProyectoStatus(proyecto, status);
	}
	
	/**
	 * Basado en la clave primaria de los  objetos entrante, busca su registro
	 * correspondiente en la base de datos de acuedo al codigo de la 
	 * inscripción y el estatus 
	 * @param codigo,  entidad del registro buscado
	 * @param Estatus,  entidad del registro buscado
	 * @return una inscripcion de proyecto registradas 
	 */
	public InscripcionProyecto buscarPorCodigoInscripcionYEstatus(Integer codigo, String status){
		return inscripcionProyectoRepositorio.findByInscripcionCodigoAndInscripcionProyectoStatus(codigo, status);
	}
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param estudiante ,  entidad del registro buscado
	 * @return una inscripcion registradas con ese estudiante correspondiente
	 */
	public InscripcionProyecto buscarInscripcionActiva(Estudiante estudiante){
		return inscripcionProyectoRepositorio.buscarInscripcionActiva(estudiante);
	}
}
