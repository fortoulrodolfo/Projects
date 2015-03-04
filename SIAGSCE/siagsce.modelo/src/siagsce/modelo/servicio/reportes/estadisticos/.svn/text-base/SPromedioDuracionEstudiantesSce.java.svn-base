package siagsce.modelo.servicio.reportes.estadisticos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.estadisticos.PromedioDuracionEstudiantesSce;
/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link PromedioDuracionEstudiantesSce}
* @author Iterator
*/


@Service("sPromedioDuracionEstudiantesSce")
public class SPromedioDuracionEstudiantesSce {
	
	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Promedio de duarciòn de los estudiantes en SCE 
	 * en las direcciones de programa
	 * @param fechaInicio,fecha para realizar la búsqueda de los datos
	 * @param fechaFinal,fecha para realizar la búsqueda de los datos
	 * @return promedio, promedio de duraciòn de los estudiantes en SCE en las 
	 * direcciones de programa
	 */
	public List<PromedioDuracionEstudiantesSce> BuscarFechasAlumnosCulminados(Date fechaInicio,Date fechaFinal){
		String queryStatement= 	"select dp.direccion_nombre, Trunc(AVG(aux.promedio_dias)) as Promedio,Trunc(AVG(aux.promedio_meses),2) as promedio_meses from direccion_programa dp , (select e.estudiante_cedula, "+
				"e.direccion_codigo, MAX (ae.actividad_ejecutada_fecha) - MIN(it.inscripcion_taller_fecha) promedio_dias, "+
				"(cast(( MAX (ae.actividad_ejecutada_fecha) - MIN(it.inscripcion_taller_fecha)) as Decimal)/30)as promedio_meses "+
				"from inscripcion_proyecto ip, actividad_asignada aa,actividad_ejecutada ae, estudiante e,preinscripcion_proyecto pp, public.inscripcion_taller it "+ 
				 "where e.estudiante_cedula = it.estudiante_cedula and "+
				 "ip.inscripcion_proyecto_codigo = aa.inscripcion_proyecto_codigo and "+
				 "aa.actividad_asignada_codigo = ae.actividad_asignada_codigo and "+
				 "pp.preinscripcion_proyecto_codigo = ip.preinscripcion_proyecto_codigo and "+
				 "e.estudiante_cedula = pp.estudiante_cedula and e.estudiante_status='Aprobado' and "+
				  "ae.actividad_ejecutada_fecha between" + "'"+fechaInicio+"'"+" and "+"'"+fechaFinal+"' group by e.estudiante_cedula, e.direccion_codigo) aux "+ 
				 "where dp.direccion_codigo = aux.direccion_codigo group by dp.direccion_nombre "+ 
				 "order by dp.direccion_nombre asc";
		
		
			Query query = em.createNativeQuery(queryStatement);
			@SuppressWarnings("unchecked")
			List<Object[]> resultset = query.getResultList();
			List<PromedioDuracionEstudiantesSce> results = new ArrayList<PromedioDuracionEstudiantesSce>();
			for (Object[] resultRow : resultset){
				results.add(new PromedioDuracionEstudiantesSce((String) resultRow[0] ,(BigDecimal) resultRow[1],(BigDecimal) resultRow[2] ));
			}
							
			return results;
			
	
	}

	
}
