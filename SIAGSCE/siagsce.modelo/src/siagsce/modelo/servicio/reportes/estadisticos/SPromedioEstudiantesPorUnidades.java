package siagsce.modelo.servicio.reportes.estadisticos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import siagsce.modelo.data.reportes.estadisticos.PorcentajeEstudiantesPorUnidades;
import siagsce.modelo.data.reportes.estadisticos.TopCincoInscripcionProyecto;


/**
 * Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
 * del modelo {@link CalcularPorcentajes}
 * @author Iterator
 */
@Service("spromedioEstudiantesPorUnidades")
public class SPromedioEstudiantesPorUnidades {

	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;


	/**
	 * Promedio de duarciòn de los estudiantes en SCE
	 * @param unidadesDeCredito,direccion, paràmetros para realizar la bùsqueda de los datos
	 * @return porcentajes de estudiantes por unidades de crèdito.
	 */
	public List<PorcentajeEstudiantesPorUnidades> CalcularPorcentajes(Integer unidadesDeCredito,Integer direccion,Date fechaInicio,Date fechaFinal) {

		String queryStatement ="SELECT cast(count(e.direccion_codigo) as Integer),(SELECT count(e.direccion_codigo) "+
				"FROM public.estudiante e, public.direccion_programa dp WHERE e.direccion_codigo = "+ "'"+direccion+"' "+ 
				"AND e.estudiante_status = 'Apto' and dp.direccion_codigo=e.direccion_codigo "+
				"and e.estudiante_fecha BETWEEN " + "'"+fechaInicio+"'"+" and "+"'"+fechaFinal+"' "+
				"AND e.estudiante_unidades_aprobadas BETWEEN ("+ "'"+unidadesDeCredito+"'*0.5) "+ 
				"AND ("+ "'"+unidadesDeCredito+"'*0.599) ) as cincuenta, "+
                "(SELECT count(e.direccion_codigo) FROM public.estudiante e, public.direccion_programa dp WHERE e.direccion_codigo = "+ "'"+direccion+"' "+
				"AND e.estudiante_status = 'Apto' and dp.direccion_codigo=e.direccion_codigo "+
				"and e.estudiante_fecha BETWEEN " + "'"+fechaInicio+"'"+" and "+"'"+fechaFinal+"' "+
				"AND e.estudiante_unidades_aprobadas BETWEEN ("+ "'"+unidadesDeCredito+"'*0.6) "+ 
				"AND ("+ "'"+unidadesDeCredito+"'*0.699) ) as secenta, "+
                "(SELECT count(e.direccion_codigo) FROM public.estudiante e, public.direccion_programa dp WHERE e.direccion_codigo = "+ "'"+direccion+"' "+
				"AND e.estudiante_status = 'Apto' and dp.direccion_codigo=e.direccion_codigo "+
				"and e.estudiante_fecha BETWEEN " + "'"+fechaInicio+"'"+" and "+"'"+fechaFinal+"' "+
				"AND e.estudiante_unidades_aprobadas BETWEEN ("+ "'"+unidadesDeCredito+"'*0.7) "+ 
				"AND ("+ "'"+unidadesDeCredito+"'*0.799) ) as setenta, "+
                "(SELECT count(e.direccion_codigo) FROM public.estudiante e, public.direccion_programa dp WHERE e.direccion_codigo = "+ "'"+direccion+"' "+ 
				"AND e.estudiante_status = 'Apto' and dp.direccion_codigo=e.direccion_codigo "+
				"and e.estudiante_fecha BETWEEN " + "'"+fechaInicio+"'"+" and "+"'"+fechaFinal+"' "+
				"AND e.estudiante_unidades_aprobadas BETWEEN ("+ "'"+unidadesDeCredito+"'*0.8) "+ 
				"AND ("+ "'"+unidadesDeCredito+"'*0.899) ) as ochenta, "+
				"(SELECT count(e.direccion_codigo) FROM public.estudiante e, public.direccion_programa dp WHERE e.direccion_codigo = "+ "'"+direccion+"' "+ 
				"AND e.estudiante_status = 'Apto' and dp.direccion_codigo=e.direccion_codigo "+
				"and e.estudiante_fecha BETWEEN " + "'"+fechaInicio+"'"+" and "+"'"+fechaFinal+"' "+
				"AND e.estudiante_unidades_aprobadas BETWEEN ("+ "'"+unidadesDeCredito+"'*0.9) "+ 
				"AND ("+ "'"+unidadesDeCredito+"'*0.999) ) as noventa, "+
				"(SELECT count(e.direccion_codigo) FROM public.estudiante e, public.direccion_programa dp WHERE e.direccion_codigo = "+ "'"+direccion+"' "+ 
				"AND e.estudiante_status = 'Apto' and dp.direccion_codigo=e.direccion_codigo "+
				"and e.estudiante_fecha BETWEEN " + "'"+fechaInicio+"'"+" and "+"'"+fechaFinal+"' "+
				"AND e.estudiante_unidades_aprobadas= dp.direccion_unidades_credito) as cien "+
                "FROM public.estudiante e,public.direccion_programa dp WHERE dp.direccion_codigo=e.direccion_codigo and e.direccion_codigo = "+ "'"+direccion+"' "+
				"AND e.estudiante_status = 'Apto' and e.estudiante_fecha BETWEEN " + "'"+fechaInicio+"'"+" and "+"'"+fechaFinal+"'";




		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<PorcentajeEstudiantesPorUnidades> results = new ArrayList<PorcentajeEstudiantesPorUnidades>();
		for (Object[] resultRow : resultset) {

			results.add(new PorcentajeEstudiantesPorUnidades((Integer)resultRow[0],(BigInteger)resultRow[1],(BigInteger) resultRow[2],(BigInteger) resultRow[3],(BigInteger)resultRow[4],(BigInteger)resultRow[5],(BigInteger)resultRow[6]));
		}
		return results;
		}	

	}




