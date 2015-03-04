package siagsce.modelo.servicio.maestros;

import java.util.List;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.repositorio.maestros.IProfesorRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Profesor}
* 
* @author Iterator
*/
@Service("sprofesor")
public class SProfesor {
	@Autowired
	IProfesorRepositorio profesorRepositorio;
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param cedula, entidad del registro buscado
	 * @return Profesor, según la cédula correspondiente
	 */
	public Profesor buscarPorCedula(String cedula) {
		return profesorRepositorio.findByProfesorCedula(cedula);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @param status, entidad del registro buscado
	 * @return Listado de Profesores según su estatus en SCE
	 */
	public List<Profesor>buscarStatus(String status){
		return profesorRepositorio.findByProfesorStatus(status);
	}
	
	
	/**
	 * Guarda en la base de datos un registro de Actividad 
	 * @param prof, entidad del registro a ser guardado
	 */
	public void guardar(Profesor prof){
		profesorRepositorio.save(prof);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param prof, entidad del registro  a ser modificado
	 */
	public void modificar(Profesor prof){
		profesorRepositorio.save(prof);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param prof, entidad del registro  a ser eliminado
	 */
	public void eliminar(Profesor prof){
		profesorRepositorio.delete(prof);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @return Listado de Profesores pertenecientes a  SCE
	 */
	public List<Profesor> buscarTodo(){
		return profesorRepositorio.findAll();
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @return Listado de Profesores sin usuarios en el sistema
	 */
	public List<Profesor> buscarProfesoresSinUsuario(){
		return profesorRepositorio.findByIdusuarioIsNull();
	}
	
	
	/**
	 * Busca los profesores por nombres 
	 * @param nombre, objeto de la entidad a utilizar 
	 * @return una lista de los profesores, según el nombre tipeado
	 */
	public List<Profesor> buscarNombre(String nombre){
		return profesorRepositorio.findByProfesorNombre(nombre);
	}
	
	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos
	 * @return Listado de Profesores Aptos, pero no son Directores
	 */
	public List<Profesor> buscarTodosAptosNoSonDirectores(){
		return profesorRepositorio.buscarTodosAptosNoSonDirectores();
	}

}
