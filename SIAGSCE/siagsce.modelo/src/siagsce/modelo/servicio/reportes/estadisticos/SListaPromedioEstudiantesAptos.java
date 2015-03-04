package siagsce.modelo.servicio.reportes.estadisticos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;


import siagsce.modelo.data.reportes.especiales.EstudianteInscritoPorTaller;
import siagsce.modelo.data.reportes.estadisticos.ListaPromedioEstudiantesAptos;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaPromedioEstudiantesAptos}
* @author Iterator
*/

@Service("sListaPromedioEstudianteAptos")
public class SListaPromedioEstudiantesAptos {
	
	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Cantidades de estudiantes aptos
	 * @return cantidad de estudiantes,
	 */
	public List<ListaPromedioEstudiantesAptos> buscarCantidades() {
		String queryStatement = "SELECT DISTINCT count(e.estudiante_cedula) AS catidadEstudiantes from estudiante as e where e.direccion_codigo='1'" ;
				
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaPromedioEstudiantesAptos> results = new ArrayList<ListaPromedioEstudiantesAptos>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaPromedioEstudiantesAptos((BigDecimal) resultRow[0]));
		}
		return results;
	
	}
	

}
