package siagsce.modelo.servicio.reportes.estadisticos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import siagsce.modelo.data.reportes.estadisticos.TopCincoInscripcionProyecto;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link TopCincoInscripcionProyecto}
* @author Iterator
*/
@Service("sTop5ProyectosSolicitados")
public class STop5ProyectosSolicitados {

	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;
	

	/**
	 * Los cincos proyectos màs demandados por direcciòn de programa
	 * @param direccion,paràmetro para realizar la bùsqueda de los datos
	 * @return cincos proyectos màs demandaos, los cincos proyectos 
	 * màs demandados por direcciòn de programa
	 */
	public List<TopCincoInscripcionProyecto> CalcularPorcentajes(Integer direccion,Date fechaInicio,Date fechaFinal) {
		
  String queryStatement ="SELECT count(pp.preinscripcion_proyecto_codigo)as totalpreinscritos," +
  		  "count(DISTINCT ip.inscripcion_proyecto_codigo)as totalinscritos, "+
		  "p.proyecto_nombre, p.proyecto_codigo FROM  public.direccion_programa dp inner join proyectos_por_programa ppp on (dp.direccion_codigo=ppp.direccion_codigo) "+
		  "inner join public.proyecto p on (ppp.proyecto_codigo=p.proyecto_codigo) left outer join  public.preinscripcion_proyecto pp on (pp.proyecto_codigo = p.proyecto_codigo) "+
		  "full outer join public.inscripcion_proyecto ip on (pp.preinscripcion_proyecto_codigo = ip.preinscripcion_proyecto_codigo) "+
		  "where proyecto_fecha_registro between" + "'"+fechaInicio+"'"+" and "+"'"+fechaFinal+"' and dp.direccion_codigo= "+ "'"+direccion+"' group by  p.proyecto_codigo, p.proyecto_nombre "+
		  "order by totalpreinscritos desc limit 5";
			
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<TopCincoInscripcionProyecto> results = new ArrayList<TopCincoInscripcionProyecto>();
		for (Object[] resultRow : resultset) {

		results.add(new TopCincoInscripcionProyecto((BigInteger)resultRow[0],(BigInteger)resultRow[1],(String)resultRow[2],(String) resultRow[3]));
		}
		return results;
		}
		
	}


