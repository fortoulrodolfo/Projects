
package siagsce.modelo.servicio.reportes.especiales;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.especiales.ListaActividadesCumplidasEstudiante;
import siagsce.modelo.data.reportes.especiales.ListaEstudianteRetiradoProyecto;
/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaEstudianteRetiradoProyecto}
* @author Iterator
*/

@Service("sListadoEstudianteRetiradoProyecto")
public class SListadoEstudianteRetiradoProyecto {
	
	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;


	/**
	 * Listado de estudiantes retirados por proyecto
	 * @param condicion, paràmetro a utilizar
	 * para realizar la bùsqueda de los datos
	 * @return una lista de estudiantes, segùn la condiciòn ingresada. 
	 */
	public List<ListaEstudianteRetiradoProyecto> buscarEstudiantesRetiradoProyecto(String condicion) {
		String queryStatement = "select e.estudiante_cedula, e.estudiante_nombre, e.estudiante_apellido, e.estudiante_email, e.estudiante_telefono" 
				+ " "
				+ "from estudiante as e, direccion_programa as dp,retiro as r,motivo as m,proyecto as py,inscripcion_proyecto as ip,preinscripcion_proyecto as pp "+condicion;
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaEstudianteRetiradoProyecto> results = new ArrayList<ListaEstudianteRetiradoProyecto>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaEstudianteRetiradoProyecto((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2], (String) resultRow[3], (String) resultRow[4]));
		}
		return results;
	}

}



