package siagsce.modelo.servicio.maestros;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.repositorio.maestros.IEstudianteRepositorio;



/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* de la entidad {@link Estudiante}
* 
* @author Iterator
*/
@Service("sestudiante")
public class SEstudiante {
	@Autowired
	IEstudianteRepositorio estudianteRepositorio;

	
	
	/**
	 * Guarda en la base de datos un registro de Estudiante
	 * @param est, entidad del registro a ser guardado
	 */
	public void guardar(Estudiante est) {
		estudianteRepositorio.save(est);
	}

	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo modifica 
	 * @param est, entidad del registro  a ser modificado
	 */
	public void modificar(Estudiante est) {
		estudianteRepositorio.save(est);
	}

	
	/**
	 * Basado en la clave primaria del objeto entrante, busca su registro
	 * correspondiente en la base de datos y lo elimina
	 * @param est, entidad del registro  a ser eliminado
	 */
	public void eliminar(Estudiante est) {
		estudianteRepositorio.delete(est);
	}

	
	/**
	 * Busca todas los estudiantes registrados 
	 * @return una lista de todas las estudiantes registrados 
	 */
	public List<Estudiante> buscarTodo() {
		return estudianteRepositorio.findAll();
	}
	
	
	/**
	 * Busca todas los estudiantes registrados 
	 * @return una lista de todas las estudiantes registrados 
	 */
	public List<Estudiante> buscarEstudiantesAIncribir() {
		return estudianteRepositorio.buscarEstudiantesAInscribir();
	}

	
	/**
	 * De acuerdo al criterio de filtrado seleccionado
	 * actualiza la lista de Estudiantes  con 
	 * las ocurrencias obtenidas de los valores tipeados 
	 * dentro del campo de texto
	 * */
	public List<Estudiante> filtrarNombre(String texto) {
		List<Estudiante> aux = estudianteRepositorio.findAll();
		List<Estudiante> filtrada = new ArrayList<Estudiante>();

		if (texto == "")
			return aux;
		else {
			for (int i = 0; i < aux.size(); i++) {
				if (aux.get(i).getEstudianteNombre().contains(texto))
					filtrada.add(aux.get(i));
			}
			return filtrada;
		}

	}
	
	
	/**
	 * Busca a un estudiante por la cédula ingresada
	 * @param cedula, el cual es el dato a buscar
	 * @return estudiante al cual le pertenece la cédula ingresada
	 */
	public Estudiante buscarPorCedula(String cedula) {
		return estudianteRepositorio.findByEstudianteCedula(cedula);
	}
	
	
	/**
	 * Busca los estudiantes que tienen asociada una actividad
	 * @param codigo_actividad, el cual es el dato a buscar
	 * @return listado de estudiantes asociados
	 * a la actividad ingresada
	 */
	public List<Estudiante> BuscarEstudiantesActividadesAsiganas(Integer codigo_actividad){
		return estudianteRepositorio.BuscarEstudiantesActividadesAsiganas(codigo_actividad);
	}
	
	
	/**
	 * Busca a un estudiantes según cédula y estatus del SCE
	 * @param cedula y estatus, el cual es el dato a buscar
	 * @return Estudiante, con cédula y estatus correspondiente en el SCE
	 */
	public Estudiante buscarPorCedulaYStatus(String cedula, String status){
		return estudianteRepositorio.findByEstudianteCedulaAndEstudianteStatus(cedula, status);
	}
	
	
	/**
	 * Busca las horas acumuladas en el SCE de un estudiante
	 * @param est, el cual es el dato a buscar
	 * @return Estudiante con las horas acumuladas
	 * en su prestación de SCE
	 */
	public Long buscarHorasAcumuladas(Estudiante est){
		return estudianteRepositorio.buscarHorasAcumuladas(est);
	}

	
	/**
	 * Busca estudiantes, según el estatus ingresado
	 * @param status, el cual es el dato a buscar
	 * @return Listado de estudiantes según el estatus ingresado
	 */
	public List<Estudiante> buscarPorStatus(String status){
		return estudianteRepositorio.findByEstudianteStatus(status);
	}

	
	/**
	 * Busca estudiante, según la direccionPrograma tipeada
	 * @param status, el cual es el dato a buscar
	 * @return Listado de estudiantes según la Direccion de Programa ingresada
	 */
	public List<Estudiante> buscarPorDireccionPrograma(DireccionPrograma direccionPrograma){
		return estudianteRepositorio.findByDireccionProgramae(direccionPrograma);
	}
	/**
	 * Busca las horas acumuladas en el SCE de un estudiante Retirado
	 * @param est, el cual es el dato a buscar
	 * @return Estudiante con las horas acumuladas
	 * en su prestación de SCE
	 */

	public Long buscarHorasAcumuladasRetirado(Estudiante est) {
		return estudianteRepositorio.buscarHorasAcumuladasRetirado(est);
	}
}
