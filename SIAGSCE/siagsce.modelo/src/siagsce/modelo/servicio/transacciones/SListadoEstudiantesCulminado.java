package siagsce.modelo.servicio.transacciones;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.ListadoEstudiantesCulminados;
import siagsce.modelo.data.transacciones.ListadoPreinscripto;

/**
 * SListadoEstudiantesCulminado ofrece los servicios 
 * necesarios para generar listados de estudiantes con SCE por Aprobar.
 * @author Iterator
 */
@Service("sListadoEstudiantesCulminado")
public class SListadoEstudiantesCulminado {
	
	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo que busca estudiantes con sus respectivas horas ejecutadas acumuladas.
	 * @return lista de estudiantes con sus respectivos datos y la cantidad de horas ejecutadas
	 * en el SCE
	 * 
	 * 
	 */
	public List<ListadoEstudiantesCulminados> buscarCulminados() {
		String queryStatement = "select e.estudiante_cedula as cedula, e.estudiante_nombre as nombre,"
				+ "e.estudiante_apellido as apellido,e.estudiante_direccion as direccion, e.estudiante_telefono as telefono, d.direccion_nombre as direccion_pro,"
				+ " "
				+ "(SELECT SUM (ae.actividad_ejecutada_horas_cumplidas)as horas_Acum from actividad_ejecutada as ae, actividad_asignada as aa, preinscripcion_proyecto as pp, inscripcion_proyecto as i"
				+ " "
				+ "WHERE pp.estudiante_cedula = e.estudiante_cedula and  i.preinscripcion_proyecto_codigo = pp.preinscripcion_proyecto_codigo and aa.inscripcion_proyecto_codigo = i.inscripcion_proyecto_codigo"
				+ " "
				+ "and ae.actividad_asignada_codigo = aa.actividad_asignada_codigo) as Horas_Acum"
				+ " "
				+ "from estudiante as e, direccion_programa as d where"
				+ " "
				+ "e.direccion_codigo = d.direccion_codigo and"
				+ " "
				+ "(SELECT SUM (ae.actividad_ejecutada_horas_cumplidas)as horas_Acum from actividad_ejecutada as ae, actividad_asignada as aa, preinscripcion_proyecto as pp, inscripcion_proyecto as i"
				+ " "
				+ "WHERE pp.estudiante_cedula = e.estudiante_cedula and  i.preinscripcion_proyecto_codigo = pp.preinscripcion_proyecto_codigo and aa.inscripcion_proyecto_codigo = i.inscripcion_proyecto_codigo"
				+ " "
				+ "and ae.actividad_asignada_codigo = aa.actividad_asignada_codigo) >= 120 and e.estudiante_status='Acreditado';";

		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		
		List<ListadoEstudiantesCulminados> results = new ArrayList<ListadoEstudiantesCulminados>();
		for (Object[] resultRow : resultset) {
			
			String cedula = (String) resultRow[0];
			String queryStatement2 = "SELECT SUM (ae.actividad_ejecutada_horas_cumplidas) from actividad_ejecutada as ae, " +
					"actividad_asignada as aa, inscripcion_proyecto as i, preinscripcion_proyecto as pp, " +
					"estudiante as e, retiro as r  where ae.actividad_asignada_codigo=aa.actividad_asignada_codigo and " +
					"aa.inscripcion_proyecto_codigo=i.inscripcion_proyecto_codigo and i.preinscripcion_proyecto_codigo=pp.preinscripcion_proyecto_codigo " +
					"and pp.estudiante_cedula='"+ cedula +"' and i.inscripcion_proyecto_codigo=r.inscripcion_proyecto_codigo and" +
					" r.retiro_status= 'No Contable' GROUP BY e.estudiante_cedula;";
			
			Query query2 = em.createNativeQuery(queryStatement2);
			@SuppressWarnings("unchecked")
			List<BigInteger> resultset2 = query2.getResultList();
			
			BigInteger h1=new BigInteger("00000000");
			BigInteger h2=new BigInteger("00000000");
			BigInteger h3=new BigInteger("00000000");
			h1= (BigInteger)resultRow[6];
			if(resultset2.size()>0){
				h2 = resultset2.get(0);
			}
			
			h3= new BigInteger(h1.subtract(h2).toString());
			results.add(new ListadoEstudiantesCulminados((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2],
					(String) resultRow[3], (String) resultRow[4],
					(String) resultRow[5], h3));
		
		}
		return results;
	}

}
