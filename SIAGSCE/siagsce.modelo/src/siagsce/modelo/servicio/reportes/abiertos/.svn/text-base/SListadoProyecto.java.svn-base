
package siagsce.modelo.servicio.reportes.abiertos;
/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaProyecto}
* @author Iterator
*/

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.abiertos.ListaProyecto;

@Service("sListadoProyectos")
public class SListadoProyecto {
    
	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;

	
	/**
	 * Listado de proyectos disponibles en SCE
	 * @param condicion, paràmetro a utilizar para realizar la bùsqueda de los datos
	 * @return una lista de proyectos, segùn la condiciòn ingresada. 
	 */
	public List<ListaProyecto> buscarProyectosActivosDireccionPrograma(
			Integer codigoPrograma) {

		String queryStatement = "SELECT proyecto.proyecto_codigo AS proyecto_proyecto_codigo, proyecto.proyecto_descripcion AS proyecto_proyecto_descripcion  "
				+ "  FROM proyecto INNER JOIN proyectos_por_programa proyectos_por_programa ON proyecto.proyecto_codigo = proyectos_por_programa.proyecto_codigo INNER JOIN direccion_programa direccion_programa ON proyectos_por_programa.direccion_codigo = direccion_programa.direccion_codigo  "
				+ "  WHERE  proyecto.proyecto_status='Activo' and proyectos_por_programa.direccion_codigo = "
				+ "'" + codigoPrograma + "';";

		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaProyecto> results = new ArrayList<ListaProyecto>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaProyecto((String) resultRow[0],
					(String) resultRow[1]));
		}
		return results;
	}
}
