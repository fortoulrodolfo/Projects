package siagsce.modelo.servicio.reportes.abiertos;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaProyectoxProfResponsable}
* @author Iterator
*/
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import siagsce.modelo.data.reportes.abiertos.ListaProyectoxProfResponsable;

@Service("SListadoProyectoxProfResponsable")
public class SListadoProyectoxProfResponsable {

	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;


	/**
	 * Listado de proyectos por profesor responsable
	 * @param condicion, paràmetro a utilizar para realizar la bùsqueda de los datos
	 * @return una lista de proyectos, segùn la condiciòn ingresada. 
	 */
	public List<ListaProyectoxProfResponsable> buscarProyectoxProfResponsableDinamico(String condicion) {
		String queryStatement = "select distinct pr.proyecto_codigo, pr.proyecto_descripcion, pr.proyecto_nombre " 
				+ " "
				+ "from proyecto as pr, profesor_responsable as prf "+condicion;
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaProyectoxProfResponsable> results = new ArrayList<ListaProyectoxProfResponsable>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaProyectoxProfResponsable((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2]));
		}
		return results;
	}
}
