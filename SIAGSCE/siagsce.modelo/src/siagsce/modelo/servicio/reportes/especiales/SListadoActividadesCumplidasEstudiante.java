
package siagsce.modelo.servicio.reportes.especiales;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.especiales.EstatusEstudiantePorCarreraEstatus;
import siagsce.modelo.data.reportes.especiales.ListaActividadesCumplidasEstudiante;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaActividadesCumplidasEstudiante}
* @author Iterator
*/
@Service("sListadoActividadesCumplidas")
public class SListadoActividadesCumplidasEstudiante {
    
	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Listado de estudiantes por actividades cumplidas
	 * @param condicion, paràmetro a utilizar para realizar la bùsqueda de los datos
	 * para realizar la bùsqueda de los datos
	 * @return una lista de estudiantes, segùn la condiciòn ingresada. 
	 */
	public List<ListaActividadesCumplidasEstudiante> buscarActividadesCumplidas(
			String condicion) {

		String queryStatement = "SELECT DISTINCT "
				+ "estudiante.estudiante_cedula AS estudiante_estudiante_cedula, "
				+ "estudiante.estudiante_nombre AS estudiante_estudiante_nombre, "
				+ "estudiante.estudiante_apellido AS estudiante_estudiante_apellido, "
				+ "actividad_ejecutada.actividad_ejecutada_horas_cumplidas AS actividad_ejecutada_actividad_ejecutada_horas_cumplidas, "
				+ "actividad_ejecutada.actividad_ejecutada_fecha AS actividad_ejecutada_actividad_ejecutada_fecha "
				+ " FROM "
				+ "estudiante INNER JOIN  preinscripcion_proyecto ON estudiante.estudiante_cedula = preinscripcion_proyecto.estudiante_cedula "
				+ " INNER JOIN  proyecto ON preinscripcion_proyecto.proyecto_codigo = proyecto.proyecto_codigo "
				+ " INNER JOIN  proyectos_por_programa ON proyectos_por_programa.proyecto_codigo = proyecto.proyecto_codigo "
				+ " INNER JOIN actividad ON proyecto.proyecto_codigo = actividad.proyecto_codigo "
				+ " INNER JOIN actividad_asignada ON actividad.actividad_codigo = actividad_asignada.actividad_codigo "
				+ " INNER JOIN actividad_ejecutada ON actividad_asignada.actividad_asignada_codigo = actividad_ejecutada.actividad_asignada_codigo "
				+ condicion;

		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaActividadesCumplidasEstudiante> results = new ArrayList<ListaActividadesCumplidasEstudiante>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaActividadesCumplidasEstudiante(
					(String) resultRow[0], (String) resultRow[1],
					(String) resultRow[2], (Integer) resultRow[3],
					(Date) resultRow[4]));
		}

		return results;
	}
}
