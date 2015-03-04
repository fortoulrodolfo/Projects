package siagsce.modelo.servicio.reportes.estadisticos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import siagsce.modelo.data.reportes.estadisticos.PorcentajesFinalizacionSCE;
import siagsce.modelo.data.reportes.estadisticos.PromedioDuracionEstudiantesSce;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link PorcentajesFinalizacionSCE}
* @author Iterator
*/

@Service("sPorcentajeFinalizacionSCE")
public class SPorcentajeFinalizacionSCE {

	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * Porcentaje de estudiantes que finalizaron SCE en las diferentes
	 * direcciones de programa
	 * @param fechaInicio, fecha usada para la búsqueda de los datos
	 * @param fechaFinal, fecha usada para la búsqueda de los datos
	 * @return PorcentajesFinalizacionSCE, porcentaje de estudiantes que finalizaron SCE
	 * en las diferentes direcciones de programa
	 */
	public List<PorcentajesFinalizacionSCE> buscarPorcentajesFinalizacionSCE(Date fechaInicio,Date fechaFinal) {
		
		String queryStatement = "SELECT direccion_programa.direccion_codigo as codigoCarrera, direccion_programa.direccion_nombre as nombreCarrera, "+
"cast(count(direccion_programa.direccion_codigo)as Integer) as totalEstudiantes, "+
"ROUND(cast(count(direccion_programa.direccion_codigo)as Decimal)/(select count(estudiante.estudiante_cedula) from estudiante where estudiante.estudiante_status='Aprobado' AND estudiante_fecha BETWEEN" + "'"+fechaInicio+"'"+" AND "+"'"+fechaFinal+"'),3) as porcentajeEstudiantesAprobados "+
"FROM  public.estudiante, public.direccion_programa "+
"WHERE  estudiante.direccion_codigo = direccion_programa.direccion_codigo AND "+
 "estudiante.estudiante_status = 'Aprobado' AND estudiante_fecha BETWEEN" + "'"+fechaInicio+"'"+" AND "+"'"+fechaFinal+"' group by direccion_programa.direccion_codigo,direccion_programa.direccion_nombre";
		

		System.out.println(queryStatement);
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<PorcentajesFinalizacionSCE> results = new ArrayList<PorcentajesFinalizacionSCE>();
		for (Object[] resultRow : resultset) {
			results.add(new PorcentajesFinalizacionSCE((Integer) resultRow[0] ,
					(String) resultRow[1], (Integer) resultRow[2], (Number)resultRow[3]));
		}
		
	
		return results;
		
	}
	
	
	
	
	
}
