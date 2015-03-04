package siagsce.modelo.servicio.reportes.especiales;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.especiales.EstatusEstudiantePorCarreraEstatus;
import siagsce.modelo.data.reportes.especiales.ListaActividadesAsignadasEstudiantes;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaActividadesAsignadasEstudiantes}
* @author Iterator
*/

@Service("sActividadesAsignadasEstudiantes")
public class SListaActividadesAsignadasEstusdiantes {
	
	/**
	 * Declaración de variable
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Método que realiza la bùsqueda de los estudiantes 
	 * a los que se les ha asignado actividades a cumplir en el 
	 * servicio comunitario
	 * @param condicion :parámetro que guarda la condición 
	 * mediante la cual se realiza la búsqueda de los datos
	 * @return lista de estudiantes, según la condición necesaria
    */

	public List<ListaActividadesAsignadasEstudiantes> buscarEstudiantesporActividadesAsignadas(String condicion) {
		String queryStatement = "select distinct e.estudiante_cedula, e.estudiante_apellido, e.estudiante_nombre," 
		+""		
        +"a.actividad_descripcion, a.actividad_cantidad_horas"
		+""
		+ " "
		+ "from estudiante as e,direccion_programa as dp,proyecto as p,actividad as a,actividad_asignada as aa,preinscripcion_proyecto as pp,inscripcion_proyecto as ip"+condicion; 
		
				
		System.out.println(queryStatement);
	    Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListaActividadesAsignadasEstudiantes> results = new ArrayList<ListaActividadesAsignadasEstudiantes>();
		for (Object[] resultRow : resultset) {

		results.add(new ListaActividadesAsignadasEstudiantes((String) resultRow[0],(String) resultRow[1],(String) resultRow[2],(String) resultRow[3],(Integer) resultRow[4]));
		}
		return results;
		}
	
}
