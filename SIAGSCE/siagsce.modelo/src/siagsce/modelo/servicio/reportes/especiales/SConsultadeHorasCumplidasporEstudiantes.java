package siagsce.modelo.servicio.reportes.especiales;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.especiales.ListaConsultadeHorascumplidasporEstudiantes;


/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link ListaConsultadeHorascumplidasporEstudiantes}
* @author Iterator
*/
@Service("sEstudianteHorasC")
public class SConsultadeHorasCumplidasporEstudiantes {
	
	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;
	/**
	 * Listado de estudiantes dado una condiciòn
	 * @param condicion, paràmetro a utilizar
	 * @return una lista de estudiantes, segùn la condiciòn ingresada
	 */
	
	
	public List<ListaConsultadeHorascumplidasporEstudiantes> buscarEstudiantesporHorasCumplidas(String condicion) {
			String queryStatement = "select DISTINCT(e.estudiante_cedula) as cedula, e.estudiante_nombre as nombre, "
				+ "e.estudiante_apellido as apellido, d.direccion_nombre as direccion_pro, "
				+ " "
				+ "(SELECT SUM (ae.actividad_ejecutada_horas_cumplidas)as horas_Acum from actividad_ejecutada as ae, actividad_asignada as aa, preinscripcion_proyecto as pp, inscripcion_proyecto as i "
				+ " "
				+ "WHERE pp.estudiante_cedula = e.estudiante_cedula and  i.preinscripcion_proyecto_codigo = pp.preinscripcion_proyecto_codigo and aa.inscripcion_proyecto_codigo = i.inscripcion_proyecto_codigo "
				+ " "
				+ "and ae.actividad_asignada_codigo = aa.actividad_asignada_codigo) as Horas_Acum "
				+ " "
				+ "from estudiante as e, direccion_programa as d,preinscripcion_proyecto as pp, inscripcion_proyecto as i where "
				+ " "
				+ "e.direccion_codigo = d.direccion_codigo and e.estudiante_status='Acreditado' and pp.estudiante_cedula=e.estudiante_cedula and  i.preinscripcion_proyecto_codigo = pp.preinscripcion_proyecto_codigo"+condicion; 
			
		    Query query = em.createNativeQuery(queryStatement);
			@SuppressWarnings("unchecked")
			List<Object[]> resultset = query.getResultList();
			
			
			List<ListaConsultadeHorascumplidasporEstudiantes> results = new ArrayList<ListaConsultadeHorascumplidasporEstudiantes>();
			for (Object[] resultRow : resultset) {
            if(resultRow[4]==null){
            	resultRow[4]=BigInteger.ZERO;
            	}
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
			h1= (BigInteger)resultRow[4];
			if(resultset2.size()>0){
				h2 = resultset2.get(0);
			}
			
			h3= h1.subtract(h2);
			results.add(new ListaConsultadeHorascumplidasporEstudiantes((String) resultRow[0],(String) resultRow[1],(String) resultRow[2],(String) resultRow[3],h3));
			}
			return results;
			}
			}
