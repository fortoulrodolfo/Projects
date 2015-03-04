package siagsce.modelo.servicio.reportes.abiertos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import  siagsce.modelo.data.reportes.abiertos.ListaConsultadeEstatusdeProyectos;



/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaConsultadeEstatusdeProyectos}
* @author Iterator
*/


@Service("sListadoConsultadeEstatusdeProyectos")
public class SConsultadeEstatusdeProyectos {
	
	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Listado de estudiantes dado una condiciòn
	 * @param condicion, paràmetro a utilizar
	 * @return una lista de proyectos, segùn la condiciòn ingresada
	 */
	
	public List<ListaConsultadeEstatusdeProyectos> buscarProyectoDireccionProgramaDinamico(String condicion) {
		String queryStatement = "select distinct p.proyecto_codigo,p.proyecto_descripcion,p.proyecto_nombre,dp.direccion_nombre"
		+ " "
		+ "from proyecto as p, direccion_programa as dp, proyectos_por_programa as pp "+condicion;
		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaConsultadeEstatusdeProyectos> results = new ArrayList<ListaConsultadeEstatusdeProyectos>();
		for (Object[] resultRow : resultset) {

			results.add(new ListaConsultadeEstatusdeProyectos((String) resultRow[0],
					(String) resultRow[1],(String) resultRow[2],(String) resultRow[3]));
					}
					return results;
					}
		
	}

	

