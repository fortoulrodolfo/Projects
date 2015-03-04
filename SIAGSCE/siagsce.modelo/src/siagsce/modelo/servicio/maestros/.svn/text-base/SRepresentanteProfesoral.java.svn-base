package siagsce.modelo.servicio.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.repositorio.maestros.IRepresentanteProfesoralRepositorio;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link RepresentanteProfesoral}
* 
* @author Iterator
*/

@Service("smiembroCoordinacion")
public class SRepresentanteProfesoral {
	@Autowired
	IRepresentanteProfesoralRepositorio miembrosCoordinacionrRepositorio;
	
	
	/**
	 * Guarda en la base de datos un registro de Actividad 
	 * @param mic, entidad del registro a ser guardado
	 */
	public void guardar(RepresentanteProfesoral mic){
		 miembrosCoordinacionrRepositorio.save(mic);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param mic, entidad del registro  a ser modificado
	 */
	public void modificar(RepresentanteProfesoral mic){
		 miembrosCoordinacionrRepositorio.save(mic);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param mic, entidad del registro  a ser eliminado
	 */
	public void eliminar(RepresentanteProfesoral mic){
		 miembrosCoordinacionrRepositorio.delete(mic);
	}
	
	
	/**
	 * Busca todas los Representantes profesorales 
	 * @return una lista de todos los Representantes Profesoralkes de SCE
	 */
	public List<RepresentanteProfesoral> buscarTodo(){
		return  miembrosCoordinacionrRepositorio.findAll();
	}

	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param cedula y estatus entidades del registro buscado
	 * @return Profesor que sea representante Profesoral según su estatus
	 */
	public RepresentanteProfesoral buscarEstatusYProfesor(String cedula,String estatus){
		return miembrosCoordinacionrRepositorio.buscarProfesorAndEstatus(cedula, estatus);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param estatus y dir entidades del registro buscado
	 * @return Representante profesoral según el estatus de una Dirección de Programa
	 */
	public RepresentanteProfesoral buscarEstatusPrograma(String estatus,DireccionPrograma dir){
		return miembrosCoordinacionrRepositorio.findByEstatusAndDireccionProgramam(estatus, dir);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param direccionProgramam y estatus entidades del registro buscado
	 * @return Representante Profesoral, según su estatus y la Dirección 
	 * de Progrtama a la que pertenece
	 */
	public RepresentanteProfesoral buscarRepresentante(DireccionPrograma direccionProgramam,String estatus){
		return miembrosCoordinacionrRepositorio.findByDireccionProgramamAndEstatus(direccionProgramam,estatus);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param estatus entidad del registro buscado
	 * @return un Listado de Representantes Profesorales según su estatus
	 */
	public List<RepresentanteProfesoral> buscarRepresentatesPorEstatus(String estatus){
		return miembrosCoordinacionrRepositorio.findByEstatus(estatus);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param prof y estatus entidades del registro buscado
	 * @return Representante Profesoral, según su cédula
	 */
	public RepresentanteProfesoral buscarRepresentantePorCedula(Profesor prof,String estatus){
		return miembrosCoordinacionrRepositorio.findByProfesormAndEstatus(prof, estatus);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param cedula entidad del registro buscado
	 * @return Representante Profesoral, según su cédula
	 */
	public RepresentanteProfesoral buscarRepresentantePorC(String cedula){
		return miembrosCoordinacionrRepositorio. buscarRepresentantePorC(cedula);
	}
	
	
	/**
	 * Busca todas los Representantes profesorales 
	 * @return una lista de todos los Representantes Profesorales de SCE de forma ordenada
	 */
	public List<RepresentanteProfesoral> buscarOrdenado(){
		return miembrosCoordinacionrRepositorio.buscarOrdenado();
	}
	/**
	 * Busca todas los Representantes profesorales 
	 * @return una lista de todos los Representantes Profesorales de SCE de forma ordenada
	 */
	public RepresentanteProfesoral  buscarRepresentantePorProgramaEstatus(Profesor profesor,DireccionPrograma programa,String estatus){
		return  miembrosCoordinacionrRepositorio.buscarRepresentantePorProgramaEstatus(profesor, programa, estatus);
	}
}

