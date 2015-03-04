package siagsce.modelo.servicio.reportes.especiales;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.reportes.especiales.EstatusEstudiantePorCarreraEstatus;
import siagsce.modelo.data.reportes.especiales.ListaConsultadeHorascumplidasporEstudiantes;

/**
* Especificación de las operaciones de la capa <i>DAO</i> (Data Access Object)
* del modelo {@link EstatusEstudiantePorCarreraEstatus}
* @author Iterator
*/
@Service("sEstadoEstudiantesPorCarreraEstatus")
public class SEstadoEstudiantesPorCarreraEstatus {
    
	/**
	 * Declaraciòn de variable
	 */
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Listado de estudiantes aptos
	 * @param direccion,fechaInicio,fechaFinal paràmetros a utilizar
	 * para realizar la bùsqueda de los datos
	 * @return una lista de estudiantes, segùn la condiciòn ingresada. 
	 */
public List<EstatusEstudiantePorCarreraEstatus> EstadoEstudiantesAptos(Integer direccion,Date fechaInicio,Date fechaFinal) {	
	String condicion=" ";
	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	if(fechaInicio!=null && fechaFinal!=null){
		condicion="AND estudiante_fecha BETWEEN "+"'"+formateador.format(fechaInicio)+"'"+" AND "+"'"+formateador.format(fechaFinal)+"'";
	}
     String queryStatement = "SELECT estudiante.estudiante_cedula, estudiante.estudiante_nombre, "+
     "estudiante.estudiante_apellido, estudiante.estudiante_unidades_aprobadas, "+
     "ROUND(cast((estudiante.estudiante_unidades_aprobadas) as Decimal)*100/direccion_programa.direccion_unidades_credito,2)as total "+
     "FROM public.estudiante, public.direccion_programa WHERE estudiante.direccion_codigo = direccion_programa.direccion_codigo AND "+
     "estudiante.estudiante_status = 'Apto' AND estudiante.direccion_codigo ="+ "'"+direccion+"' " + condicion +" order by total desc";
    
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultset = query.getResultList();
		List<EstatusEstudiantePorCarreraEstatus> results = new ArrayList<EstatusEstudiantePorCarreraEstatus>();
		for (Object[] resultRow : resultset) {

		results.add(new EstatusEstudiantePorCarreraEstatus((String)resultRow[0],(String)resultRow[1],(String) resultRow[2],(Number)resultRow[3],(Number)resultRow[4],0,0));
		
		}
		return results;
		}

/**
 * Listado de estudiantes por estatus y programa
 * @param direccion,fechaInicio,fechaFinal,status
 * paràmetros a utilizar para realizar la bùsqueda de los datos
 * @return una lista de estudiantes, segùn la condiciòn ingresada. 
 */	
public List<EstatusEstudiantePorCarreraEstatus> EstadoEstudiantesAcreditadosAprobados(Integer direccion,String status,Date fechaInicio,Date fechaFinal) {	
	String condicion=" ";
	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	if(fechaInicio!=null && fechaFinal!=null){
		condicion=" AND estudiante_fecha BETWEEN "+"'"+formateador.format(fechaInicio)+"'"+" AND "+"'"+formateador.format(fechaFinal)+"' ";
	}
	String queryStatement = "SELECT estudiante.estudiante_cedula, "+
      "estudiante.estudiante_nombre, estudiante.estudiante_apellido, estudiante.estudiante_unidades_aprobadas, "+
      "ROUND(cast((estudiante.estudiante_unidades_aprobadas) as Decimal)*100/direccion_programa.direccion_unidades_credito,2)as porcentajeUnidadesCreditosAprobadas, "+
      "sum(actividad_ejecutada_horas_cumplidas) as totalHorasAcumuladas, "+
      "ROUND(cast(sum(actividad_ejecutada_horas_cumplidas) as Decimal)*100/120,2) as porcentajeHorasAcumuladas "+
      "FROM public.actividad_ejecutada, public.actividad_asignada, public.inscripcion_proyecto, "+
      "public.preinscripcion_proyecto, public.estudiante, public.direccion_programa WHERE "+
      "estudiante.direccion_codigo = direccion_programa.direccion_codigo AND actividad_asignada.actividad_asignada_codigo = actividad_ejecutada.actividad_asignada_codigo AND "+
      "inscripcion_proyecto.inscripcion_proyecto_codigo = actividad_asignada.inscripcion_proyecto_codigo AND "+
      "preinscripcion_proyecto.preinscripcion_proyecto_codigo = inscripcion_proyecto.preinscripcion_proyecto_codigo AND "+
      "preinscripcion_proyecto.estudiante_cedula = estudiante.estudiante_cedula  AND "+
      "estudiante.estudiante_status ="+ "'"+status+"' and direccion_programa.direccion_codigo="+ "'"+direccion+"'"+ condicion +" group by estudiante.estudiante_cedula ,estudiante.estudiante_nombre, "+
      "estudiante.estudiante_apellido,estudiante.estudiante_unidades_aprobadas,direccion_programa.direccion_unidades_credito order by porcentajeUnidadesCreditosAprobadas desc, porcentajeHorasAcumuladas asc";
							
				Query query = em.createNativeQuery(queryStatement);
				@SuppressWarnings("unchecked")
				List<Object[]> resultset = query.getResultList();
				List<EstatusEstudiantePorCarreraEstatus> results = new ArrayList<EstatusEstudiantePorCarreraEstatus>();
				for (Object[] resultRow : resultset) {

				results.add(new EstatusEstudiantePorCarreraEstatus((String)resultRow[0],(String)resultRow[1],(String) resultRow[2],(Number)resultRow[3],(Number)resultRow[4],(Number)resultRow[5],(Number)resultRow[6]));
				
				}
				return results;
				}

/**
 * Listado de estudiantes de acuerdo al avance en SCE
 * @param direccion,status,cedula
 * paràmetros a utilizar para realizar la bùsqueda de los datos
 * @return una lista de estudiantes, segùn la condiciòn ingresada. 
 */	
public List<EstatusEstudiantePorCarreraEstatus> avanceEstudiante(Integer direccion,String status,String cedula) {	
		  String queryStatement = "SELECT estudiante.estudiante_cedula, "+
		  "estudiante.estudiante_nombre, estudiante.estudiante_apellido, estudiante.estudiante_unidades_aprobadas, "+
		  "ROUND(cast((estudiante.estudiante_unidades_aprobadas) as Decimal)*100/direccion_programa.direccion_unidades_credito,2)as porcentajeUnidadesCreditosAprobadas, "+
		  "sum(actividad_ejecutada_horas_cumplidas) as totalHorasAcumuladas, "+
		  "ROUND(cast(sum(actividad_ejecutada_horas_cumplidas) as Decimal)*100/120,2) as porcentajeHorasAcumuladas "+
		  "FROM public.actividad_ejecutada, public.actividad_asignada, public.inscripcion_proyecto, "+
		  "public.preinscripcion_proyecto, public.estudiante, public.direccion_programa WHERE "+
		  "estudiante.direccion_codigo = direccion_programa.direccion_codigo AND actividad_asignada.actividad_asignada_codigo = actividad_ejecutada.actividad_asignada_codigo AND "+
		  "inscripcion_proyecto.inscripcion_proyecto_codigo = actividad_asignada.inscripcion_proyecto_codigo AND "+
		  "preinscripcion_proyecto.preinscripcion_proyecto_codigo = inscripcion_proyecto.preinscripcion_proyecto_codigo AND "+
		  "preinscripcion_proyecto.estudiante_cedula = estudiante.estudiante_cedula  AND estudiante.estudiante_cedula="+ "'"+cedula+"' AND "+
		  "estudiante.estudiante_status ="+ "'"+status+"' and direccion_programa.direccion_codigo="+ "'"+direccion+"' group by estudiante.estudiante_cedula ,estudiante.estudiante_nombre, "+
		  "estudiante.estudiante_apellido,estudiante.estudiante_unidades_aprobadas,direccion_programa.direccion_unidades_credito order by porcentajeUnidadesCreditosAprobadas desc, porcentajeHorasAcumuladas asc";
		
		 
						Query query = em.createNativeQuery(queryStatement);
						@SuppressWarnings("unchecked")
						List<Object[]> resultset = query.getResultList();
						List<EstatusEstudiantePorCarreraEstatus> results = new ArrayList<EstatusEstudiantePorCarreraEstatus>();
						for (Object[] resultRow : resultset) {

						results.add(new EstatusEstudiantePorCarreraEstatus((String)resultRow[0],(String)resultRow[1],(String) resultRow[2],(Number)resultRow[3],(Number)resultRow[4],(Number)resultRow[5],(Number)resultRow[6]));
						
						}
						return results;
						}
	}


