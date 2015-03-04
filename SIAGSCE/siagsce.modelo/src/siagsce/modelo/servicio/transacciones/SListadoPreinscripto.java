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
 * SListadoPreinscripto ofrece los servicios 
 * necesarios para generar listados de los Estudiantes
 *  Preinscritos en un proyecto de Servicio Comunitario Estudiantil/
 * @author Iterator
 */
@Service("sListadoPreinscripto")
public class SListadoPreinscripto {

	/**
	 *Declaracion de variable de Persistencia
	 **/
	@PersistenceContext
	private EntityManager em;

	/**
	 * Segun el codigo del proyecto seleccionado inserta en una lista mediante una busqueda tipo
	 * SQL los estudiantes preinscritos trayendo a su vez las horas ejecutadas  que ha realizado 
	 * en otros proyectos en el caso de que haya sido asi.
	 * @param codigo , del proyecto del cual se requiere buscar todos los preinscritos
	 * @return lista de las preinscripciones  de los estudiantes 
	 **/
	public List<ListadoPreinscripto> buscarPreinscrito(String codigo) {
		String queryStatement = "select e.estudiante_cedula as cedula, e.estudiante_nombre as nombre, e.estudiante_apellido as apellido,pp.preinscripcion_proyecto_codigo, e.estudiante_unidades_aprobadas as unidades_de_credito,"
				+ " "
				+ "(SELECT SUM (ae.actividad_ejecutada_horas_cumplidas)as horas_Acum from actividad_ejecutada as ae, actividad_asignada as aa, preinscripcion_proyecto as pp, inscripcion_proyecto as i"
				+ " "
				+ "WHERE pp.estudiante_cedula = e.estudiante_cedula and  i.preinscripcion_proyecto_codigo = pp.preinscripcion_proyecto_codigo and aa.inscripcion_proyecto_codigo = i.inscripcion_proyecto_codigo"
				+ " "
				+ "and ae.actividad_asignada_codigo = aa.actividad_asignada_codigo) as Horas_Acum"
				+ " "
				+ "from estudiante as e,preinscripcion_proyecto as pp where"
				+ " "
				+ "e.estudiante_cedula = pp.estudiante_cedula and pp.proyecto_codigo ="+ "'"+codigo+"' and pp.preinscripcion_proyecto_status = 'Activo';";
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<ListadoPreinscripto> results = new ArrayList<ListadoPreinscripto>();
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
			if((BigInteger)resultRow[5] != null){
			h1= (BigInteger)resultRow[5];
			}
			if(resultset2.size()>0){
				h2 = resultset2.get(0);
			}
			
			h3= new BigInteger(h1.subtract(h2).toString());
			results.add(new ListadoPreinscripto((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2],
					(Integer) resultRow[3], (Integer) resultRow[4],
					h3));

		}
		return results;
		}
}






