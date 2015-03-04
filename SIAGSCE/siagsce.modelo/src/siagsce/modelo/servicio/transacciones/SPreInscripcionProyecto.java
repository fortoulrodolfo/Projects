package siagsce.modelo.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.PreInscripcionProyecto;
import siagsce.modelo.repositorio.transacciones.IPreInscripcionProyectoRepositorio;
/**
 * SPreInscripcionProyecto ofrece los servicios necesarios con todo lo que tenga relacion a  
 * las preinscripciones de los proyectos. 
 * un proyecto.
 * @author Iterator
 */
@Service("spreinscripcionproyecto")
public class SPreInscripcionProyecto {
	@Autowired
	IPreInscripcionProyectoRepositorio preInscripcionProyectoRepositorio;
	
	/**
	 * Guarda en la base de datos(BD)la preinscripcion.
	 * @param preins es la preinscripcion a guardar.
	 */
	public void guardar(PreInscripcionProyecto preins){
		preInscripcionProyectoRepositorio.save(preins);
	}
	
	/**
	 * Modifica en la base de datos(BD)la preinscripcion.
	 * @param preins es la preinscripcion a modificar.
	 */
	public void modificar(PreInscripcionProyecto preins){
		preInscripcionProyectoRepositorio.save(preins);
	}
	
	/**
	 * Elimina en la base de datos(BD)la preinscripcion.
	 * @param preins es la preinscripcion a eliminar.
	 */
	public void eliminar(PreInscripcionProyecto preins){
		preInscripcionProyectoRepositorio.delete(preins);
	}
	
	/**
	 * Busca todas las preinscripciones guardadas en la BD.
	 * @return lista de todas las preinscripciones.
	 */
	public List<PreInscripcionProyecto> buscarTodo(){
		return preInscripcionProyectoRepositorio.findAll();
	}
	
	/**
	 * Busca todas las preinscripciones guardadas en la BD de acuerdo a un proyecto especifico.
	 * @param proyecto es el objeto proyecto por el cual se va a buscar
	 * @return lista de todas las preinscripciones.
	 */
	public List<PreInscripcionProyecto> buscarProyecto(Proyecto proyecto){
		return preInscripcionProyectoRepositorio.findByProyectop(proyecto);
	}
	/**
	 * Busca todas las preinscripciones guardadas en la BD de acuerdo a codigo de
	 * preinscripcion especifico.
	 * @param codigo es el codigo de la preinscripcion por la cual se va a buscar
	 * @return lista de todas las preinscripciones.
	 */
	public PreInscripcionProyecto buscarPreinscripcion(Integer codigo){
		return preInscripcionProyectoRepositorio.findByPreinscripcionCodigo(codigo);
	}
	
	/**
	 * Busca todas las preinscripciones guardadas en la BD que posee un estudiante
	 * @param estudiante es el objeto Estudiante de la preinscripcion por la cual se va a buscar
	 * @return lista de todas las preinscripciones de ese estudiante.
	 */
	public List<PreInscripcionProyecto> buscarPorEstudiante(Estudiante estudiante){
		return preInscripcionProyectoRepositorio.findByEstudiante(estudiante);
	}
	
	/**
	 * Busca todas las preinscripciones guardadas en la BD de acuerdo a su status
	 * @param status es el status de la preinscripcion por la cual se va a buscar
	 * @return lista de todas las preinscripciones.
	 */
	public  List<PreInscripcionProyecto> buscarPorStatus(String status){
		return preInscripcionProyectoRepositorio.findByPreinscripcionStatus(status);
	}
	
	/**
	 * Busca todas las preinscripciones guardadas en la BD de acuerdo un proyecto y a su status.
	 * @param proyecto es el objeto proyecto por el cual se va a buscar
	 * @param status es el status de la preinscripcion por la cual se va a buscar
	 * @return lista de todas las preinscripciones.
	 */
	public List<PreInscripcionProyecto> buscarPorProyectopYPreinscripcionStatus(Proyecto proyecto,String status){
		return preInscripcionProyectoRepositorio.findByProyectopAndPreinscripcionStatus(proyecto, status);
				
	}
	
	/**
	 * Busca la preinscripcione guardada en la BD de acuerdo un estudiante y a su status.
	 * @param estudiante es el objeto Estudiante de la preinscripcion por la cual se va a buscar
	 * @param status es el status de la preinscripcion por la cual se va a buscar
	 * @return un objeto preinscripcion.
	 */
	public PreInscripcionProyecto buscarPorEstudianteProyectoYPreinscripcionStatus(Estudiante estudiante,Proyecto proyecto,String status){
		return preInscripcionProyectoRepositorio.buscarPorEstudianteProyectoYStatus(estudiante, proyecto, status);
				
	}

	/**
	 * Busca la preinscripcione guardada en la BD de acuerdo un codigo y a su status.
	 * @param estudiante es el objeto Estudiante de la preinscripcion por la cual se va a buscar
	 * @param codigo es el codigo de la preinscripcion por la cual se va a buscar
	 * @return un objeto preinscripcion.
	 */
	public PreInscripcionProyecto buscarPorCodigoYEstatus(Integer codigo, String status){
		return preInscripcionProyectoRepositorio.findByPreinscripcionCodigoAndPreinscripcionStatus(codigo, status);
	}
}
