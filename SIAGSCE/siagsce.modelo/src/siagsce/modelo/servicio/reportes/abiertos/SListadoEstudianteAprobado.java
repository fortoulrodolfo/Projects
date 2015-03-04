package siagsce.modelo.servicio.reportes.abiertos;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import siagsce.modelo.data.reportes.abiertos.ListaEstudianteAprobado;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaEstudianteAprobado}
* @author Iterator
*/
@Service("sListadoEstudiantesAprobado")
public class SListadoEstudianteAprobado {
    
	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;
    
	/**
	 * Listado de estudiantes aprobados en el SCE 
	 * @param condicion, paràmetro a utilizar para realizar la bùsqueda de los datos
	 * @return una lista de estudiantes, segùn la condiciòn ingresada. 
	 */
	public List<ListaEstudianteAprobado> buscarEstudiantesAprobadoDinamico(String condicion) {
		String queryStatement = "select distinct e.estudiante_cedula, e.estudiante_nombre, e.estudiante_apellido" 
				+ " "
				+ "from estudiante as e, direccion_programa as aa "+condicion;
		System.out.println(" Elvis"+queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaEstudianteAprobado> results = new ArrayList<ListaEstudianteAprobado>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaEstudianteAprobado((String) resultRow[0],
					(String) resultRow[1],(String) resultRow[2]));
		}
		return results;
	}


}
