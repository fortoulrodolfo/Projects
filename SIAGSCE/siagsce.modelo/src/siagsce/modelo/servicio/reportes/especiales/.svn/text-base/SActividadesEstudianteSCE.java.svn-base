package siagsce.modelo.servicio.reportes.especiales;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.abiertos.ListaEstudianteAprobado;
import siagsce.modelo.data.reportes.especiales.ActividadesEstudiante;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ActividadEstudiante}
* @author Iterator
*/
@Service("sActividadesEstudianteSCE")
public class SActividadesEstudianteSCE {

	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Listado de estudiantes por actividades 
	 * @param cedulaEstudiante, paràmetro a utilizar para realizar la bùsqueda de los datos
	 * @return una lista de actividades asignadas, segùn el pàrametro. 
	 */
public List<ActividadesEstudiante> actividadesAsignadasEstudiante(String cedulaEstudiante) {
			
  String queryStatement = "SELECT aa.actividad_asignada_fecha_inicio, aa.actividad_asignada_fecha_culminacion, "+
  "a.actividad_nombre, a.actividad_descripcion, p.proyecto_codigo, p.proyecto_nombre "+
  "FROM estudiante e, preinscripcion_proyecto pp, inscripcion_proyecto ip, actividad_asignada aa, actividad a, proyecto p "+
  "WHERE e.estudiante_cedula ="+ "'"+cedulaEstudiante+"' AND "+
  "e.estudiante_cedula = pp.estudiante_cedula AND "+
  "pp.preinscripcion_proyecto_codigo = ip.preinscripcion_proyecto_codigo AND "+
  "ip.inscripcion_proyecto_codigo = aa.inscripcion_proyecto_codigo AND "+
  "a.actividad_codigo = aa.actividad_codigo AND "+
  "a.proyecto_codigo = p.proyecto_codigo order by p.proyecto_codigo ";
  
       
					
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ActividadesEstudiante> results = new ArrayList<ActividadesEstudiante>();
		for (Object[] resultRow : resultset) {

		results.add(new ActividadesEstudiante((Date)resultRow[0],(Date)resultRow[1],(String) resultRow[2],(String)resultRow[3],(String)resultRow[4],
				   (String)resultRow[5]));
		
		
		}
		return results;
		}
	
/**
 * Listado de estudiantes por actividades
 * @param cedulaEstudiante, paràmetro a utilizar para realizar la bùsqueda de los datos
 * @return una lista de actividades ejecutadas, segùn el paràmetro 
 */
	public List<ActividadesEstudiante> actividadesEjecutadasEstudiante(String cedulaEstudiante) {
		
		
		  String queryStatement = "SELECT a.actividad_nombre, a.actividad_descripcion, p.proyecto_codigo, p.proyecto_nombre, "+
		  "ae.actividad_ejecutada_fecha, ae.actividad_ejecutada_horas_cumplidas "+	  
		  "FROM estudiante e, preinscripcion_proyecto pp, inscripcion_proyecto ip, actividad_asignada aa, actividad a, proyecto p, actividad_ejecutada ae "+
		  "WHERE e.estudiante_cedula ="+ "'"+cedulaEstudiante+"' AND "+
		  "e.estudiante_cedula = pp.estudiante_cedula AND "+
		  "pp.preinscripcion_proyecto_codigo = ip.preinscripcion_proyecto_codigo AND "+
		  "ip.inscripcion_proyecto_codigo = aa.inscripcion_proyecto_codigo AND "+
		  "a.actividad_codigo = aa.actividad_codigo AND "+
		  "a.proyecto_codigo = p.proyecto_codigo AND "+
		  "ae.actividad_asignada_codigo = aa.actividad_asignada_codigo "+
		  "order by aa.actividad_asignada_fecha_inicio ";
		  
		  
				Query query = em.createNativeQuery(queryStatement);
				@SuppressWarnings("unchecked")
				List<Object[]> resultset = query.getResultList();
				List<ActividadesEstudiante> results = new ArrayList<ActividadesEstudiante>();
				for (Object[] resultRow : resultset) {

				results.add(new ActividadesEstudiante((String)resultRow[0],(String)resultRow[1],(String) resultRow[2],(String)resultRow[3],(Date)resultRow[4],(Integer)resultRow[5]));
				
				
				}
				return results;
				}
	
	
	}


