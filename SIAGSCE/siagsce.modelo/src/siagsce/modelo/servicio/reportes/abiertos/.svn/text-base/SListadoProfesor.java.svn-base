package siagsce.modelo.servicio.reportes.abiertos;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.abiertos.ListaEstudiante;
import siagsce.modelo.data.reportes.abiertos.ListaProfesor;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaProfesor}
* @author Iterator
*/

@Service("sListadoProfesor")
public class SListadoProfesor {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Listado de profesores que son responsables de un proyecto
	 * @param condicion, paràmetro a utilizar para realizar la bùsqueda de los datos
	 * @return una lista de profesores,segùn la condiciòn ingresada
	 */
	public List<ListaProfesor> buscarProfesorResponsable(String condicion) {
		String queryStatement = "SELECT distinct prf.profesor_cedula, prf.profesor_nombre, prf.profesor_apellido, prf.profesor_email, prf.profesor_telefono" 
				+ " "
				+ "from profesor_responsable as prfr, profesor as prf, direccion_programa as dp, proyectos_por_programa as pp, proyecto as pr "+condicion;
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaProfesor> results = new ArrayList<ListaProfesor>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaProfesor((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2], (String) resultRow[3], (String) resultRow[4]));
		}
		return results;
	}

	/**
	 * Listado de profesores que son Participantes de actividades en uno o varios proyectos
	 * @param condicion, paràmetro a utilizar para realizar la bùsqueda de los datos
	 * @return una lista de profesores, segùn la condicion ingresada
	 */
	public List<ListaProfesor> buscarProfesorParticipante(String condicion) {
		String queryStatement = "SELECT distinct prf.profesor_cedula, prf.profesor_nombre, prf.profesor_apellido, prf.profesor_email, prf.profesor_telefono" 
				+ " "
				+ "from actividad as ac, profesor as prf, profesor_participante as prfp, proyecto as pr, proyectos_por_programa as pp, direccion_programa as dp "+condicion;
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaProfesor> results = new ArrayList<ListaProfesor>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaProfesor((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2], (String) resultRow[3], (String) resultRow[4]));
		}
		return results;
	}
	
	/**
	 * Listado de profesores por el estatus que poseen
	 * @param condicion, paràmetro a utilizar para realizar la bùsqueda de los datos
	 * @return una lista de profesores, segùn la condicion ingresada.
	 */
	public List<ListaProfesor> buscarProfesoresPorEstatus(String condicion) {
		String queryStatement = "SELECT distinct prf.profesor_cedula, prf.profesor_nombre, prf.profesor_apellido, prf.profesor_email, prf.profesor_telefono" 
				+ " "
				+ "from profesor as prf"+condicion;
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaProfesor> results = new ArrayList<ListaProfesor>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaProfesor((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2], (String) resultRow[3], (String) resultRow[4]));
		}
		return results;
	}

}
