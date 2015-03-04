package siagsce.modelo.servicio.reportes.abiertos;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Exonerado;
import siagsce.modelo.data.reportes.abiertos.ListaEstudiante;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaEstudiante}
* @author Iterator
*/
@Service("sListadoEstudiantesDireccionPrograma")
public class SListadoEstudiante {
    
	/**
	 * Declaraciòn de Variable
	 * 
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Listado de estudiantes dado una condiciòn
	 * @param condicion, paràmetro a utilizar
	 * @return una lista de estudiantes, segùn la condiciòn ingresada
	 */
	public List<ListaEstudiante> buscarEstudiantesDireccionProgramaDinamico(String condicion) {
		String queryStatement = "select e.estudiante_cedula, e.estudiante_nombre, e.estudiante_apellido, e.estudiante_email, e.estudiante_telefono, aa.direccion_codigo" 
				+ " "
				+ "from estudiante as e, direccion_programa as aa "+condicion;
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaEstudiante> results = new ArrayList<ListaEstudiante>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaEstudiante((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2], (String) resultRow[3], (String) resultRow[4], (Integer) resultRow[5]));
		}
		return results;
	}

	/**
	 * Listado de estudiantes dado una condicion
	 * @param condicion, parametro a utilizar
	 * @return una lista de estudiantes, segun la condicion ingresada
	 */
	public List<ListaEstudiante> buscarEstudiantesDireccionProgramaProyectoDinamico(String condicion) {
		String queryStatement = "select distinct e.estudiante_cedula, e.estudiante_nombre, e.estudiante_apellido, e.estudiante_email, e.estudiante_telefono" 
				+ " "
				+ "from estudiante as e, direccion_programa as aa, proyecto as pr, inscripcion_proyecto as inp, preinscripcion_proyecto as pro, proyectos_por_programa as pp "+condicion;
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaEstudiante> results = new ArrayList<ListaEstudiante>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaEstudiante((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2], (String) resultRow[3], (String) resultRow[4]));
		}
		return results;
	}

	/**
	 * Listado de estudiantes dado una condicion
	 * @param condicion, parametro a utilizar
	 * @return una lista de estudiantes, segun la condicion ingresada
	 */
	public List<ListaEstudiante> buscarEstudiantesPreinscritosProyectoDinamico(String condicion) {
		String queryStatement = "select distinct e.estudiante_cedula, e.estudiante_nombre, e.estudiante_apellido, e.estudiante_email, e.estudiante_telefono" 
				+ " "
				+ "from estudiante as e, direccion_programa as aa, proyecto as pr, preinscripcion_proyecto as pro, proyectos_por_programa as pp "+condicion;
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaEstudiante> results = new ArrayList<ListaEstudiante>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaEstudiante((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2], (String) resultRow[3], (String) resultRow[4]));
		}
		return results;
	}

}
