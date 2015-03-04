package siagsce.modelo.servicio.reportes.estadisticos;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link PromediodeEstudiantesProgramaEstatus}
* @author Iterator
*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.estadisticos.PromedioDuracionEstudiantesSce;
import siagsce.modelo.data.reportes.estadisticos.PromedioEstudiantesProgramaEstatus;

@Service("sPromedioEstudiantes")
public class SPromedioEstudiantesProgramaEstatus {

	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Promedio de estudiantes por programa y estatus
	 * @param condicion1,condicion2,condicion3,condicion4,condicion5 paràmetros para realizar la bùsqueda de los datos
	 * @return promedio de estudiantes, promedio de estudiantes por programa y estatus
	 */
	public List<PromedioEstudiantesProgramaEstatus> buscarPromedioEstudiantesProgramaEstatus(String condicion1, String condicion2, String condicion3, String condicion4, String condicion5) {
		
		String queryStatement = "SELECT (SELECT DISTINCT direccion_nombre" + " " + " from direccion_programa" +  " " + condicion1 + ") AS PROGRAMA," +
		
	
		"(SELECT COUNT(estudiante_status) " + " " + " FROM estudiante as a, direccion_programa as b " + " " + condicion2 + ") AS APTOS," +

		"(SELECT COUNT(estudiante_status) " + " " + " FROM estudiante as a, direccion_programa as b " + " " + condicion3 + ") AS ACREDITADOS," +
		
		"(SELECT COUNT(estudiante_status) " + " " + " FROM estudiante as a, direccion_programa as b " + " " + condicion4 + ") AS APROBADOS," +
		
		"(SELECT COUNT(*)"					+ " " + " FROM estudiante as a, direccion_programa as b " + " " + condicion5 + ") AS TOTAL;";
		

		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<PromedioEstudiantesProgramaEstatus> results = new ArrayList<PromedioEstudiantesProgramaEstatus>();
		for (Object[] resultRow : resultset) {

			results.add(new PromedioEstudiantesProgramaEstatus((String) resultRow[0] ,
					(BigInteger) resultRow[1], (BigInteger) resultRow[2], (BigInteger) resultRow[3], (BigInteger) resultRow[4]));
		}
		
	
		return results;
		
	}
	
	
	
	
	
}
