package siagsce.modelo.servicio.reportes.especiales;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.especiales.EstatusEstudiantePorCarreraEstatus;
import siagsce.modelo.data.reportes.especiales.EstudianteInscritoPorTaller;

/**
 * Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
 * del modelo {@link EstudianteInscritoPorTaller}
 * @author Iterator
 */

@Service("sEstudianteInscritoPorTaller")
public class SEstudianteInscritoPorTaller {

	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;


	/**
	 * Listado de estudiantes inscritos por taller
	 * @param condicion, paràmetro a utilizar para realizar la bùsqueda de los datos
	 * para realizar la bùsqueda de los datos
	 * @return una lista de estudiantes, segùn la condiciòn ingresada. 
	 */
	public List<EstudianteInscritoPorTaller> buscarEstudianteInscritoPorTalle(String condicion) {
		String queryStatement = "select distinct e.estudiante_cedula, e.estudiante_nombre, e.estudiante_apellido, e.estudiante_email, e.estudiante_telefono, e.estudiante_direccion," 
				+ " (select aa.direccion_nombre from  direccion_programa as aa where e.direccion_codigo=aa.direccion_codigo) "
				+ "from estudiante as e, direccion_programa as aa, taller as t, inscripcion_taller as it "+condicion;

		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<EstudianteInscritoPorTaller> results = new ArrayList<EstudianteInscritoPorTaller>();
		for (Object[] resultRow : resultset) {

			results.add(new EstudianteInscritoPorTaller((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2], (String) resultRow[3],(String) resultRow[4],(String) resultRow[5],(String) resultRow[6]));
		}
		return results;
	}
}






