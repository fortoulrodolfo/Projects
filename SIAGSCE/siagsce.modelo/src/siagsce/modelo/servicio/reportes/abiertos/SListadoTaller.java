package siagsce.modelo.servicio.reportes.abiertos;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.abiertos.ListaProfesor;
import siagsce.modelo.data.reportes.abiertos.ListaTaller;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaTaller}
* @author Iterator
*/
@Service("sListadoTaller")
public class SListadoTaller {

	/**
	 * Declaraciòn de Variable
	 * 
	 */
	@PersistenceContext
	private EntityManager em;


	/**
	 * Listado de talleres 
	 * @param condicion, paràmetro a utilizar para realizar la bùsqueda de los datos
	 * @return una lista de talleres, segùn la condiciòn ingresada. 
	 */
	public List<ListaTaller> buscarTallerEjecutandose(String condicion) {
		String queryStatement = "SELECT  distinct t.taller_nombre, t.taller_descripcion, t.taller_fecha_culminacion, t.taller_fecha_inicio, t.taller_lugar, t.taller_modalidad" 
				+ " "
				+ "from taller as t, profesor as pr "+condicion;
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaTaller> results = new ArrayList<ListaTaller>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaTaller((String) resultRow[0],
					(String) resultRow[1], (Date) resultRow[2], (Date) resultRow[3], (String) resultRow[4], (String) resultRow[5]));
		}
		return results;
	}

}
