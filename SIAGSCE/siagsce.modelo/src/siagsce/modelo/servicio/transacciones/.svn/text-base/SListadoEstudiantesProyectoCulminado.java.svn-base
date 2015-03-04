package siagsce.modelo.servicio.transacciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.transacciones.ListadoEstudiantesActividadAsignada;
import siagsce.modelo.data.transacciones.ListadoEstudiantesProyectoCulminado;
/**
 * SListadoEstudiantesActividadAsignada ofrece los servicios 
 * necesarios para generar listados de actividades
 *  asignadas con cierto grado de complejidad.
 * @author Iterator
 */
@Service("sListadoEstudiantesProyectoCulminado")
public class SListadoEstudiantesProyectoCulminado {

@PersistenceContext
private EntityManager em;
/**
 * Metodo que busca estudiantes con sus respectiva actividad asignada.
 * @param ActividadAsignadaCodigo es el codigo de la actividad asignada a buscar.
 * @return lista de actividad con cada unos de los 
 * estudiantes inscritos en dicha actividad, solo trae las actividad con sus estudiantes
 * sino ha sido ejecutada.
 * 
 * 
 */
public List<ListadoEstudiantesProyectoCulminado> buscarEstudiantesActividadAsignada(String Proyecto) {
String queryStatement = "select e.estudiante_cedula, e.estudiante_nombre, e.estudiante_apellido, a.actividad_nombre, aa.actividad_asignada_codigo" 
+ " "
+ "from estudiante e inner join preinscripcion_proyecto as pp on e.estudiante_cedula = pp.estudiante_cedula"
+ " "
+ "inner join inscripcion_proyecto as i on pp.preinscripcion_proyecto_codigo = i.preinscripcion_proyecto_codigo"
+ " "
+"inner join actividad_asignada as aa on i.inscripcion_proyecto_codigo = aa.inscripcion_proyecto_codigo"
+" "
+"inner join actividad as a on aa.actividad_codigo = a.actividad_codigo"
+" "
+"where i.proyecto_codigo = "+ "'"+Proyecto+"'  and aa.actividad_asignada_codigo not in (select ae.actividad_asignada_codigo from actividad_ejecutada ae);";

Query query = em.createNativeQuery(queryStatement);
@SuppressWarnings("unchecked")
List<Object[]> resultset = query.getResultList();
List<ListadoEstudiantesProyectoCulminado> results = new ArrayList<ListadoEstudiantesProyectoCulminado>();
for (Object[] resultRow : resultset) {

results.add(new ListadoEstudiantesProyectoCulminado((String) resultRow[0],
(String) resultRow[1], (String) resultRow[2],(String) resultRow[3] , (Integer) resultRow[4]));
}
return results;
}
}


