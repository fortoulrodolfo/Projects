package siagsce.modelo.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.ActividadAsignada;
import siagsce.modelo.data.transacciones.ActividadEjecutada;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
/**
 * Interfaz Actividad Ejecutada, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo ActividadEjecutada.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(ActividadEjecutada actividadEjecutada),delete(ActividadEjecutada actividadEjecutada),findAll(),findOne(Integer actividadEjecutadaCodigo),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IActividadEjecutadaRepositorio extends JpaRepository<ActividadEjecutada,String>{
	/**
	 * Metodo que retorna todas las actividades ejecutadas 
	 * por un estudiante en un determinado proyecto.
	 * @param estudiante
	 * @param proyecto
	 * @return lista de actividades ejecutadas.
	 */ 
	@Query("select ae from ActividadEjecutada as ae, ActividadAsignada as aa, InscripcionProyecto ip, PreInscripcionProyecto as pp " +
			"where ae.actividadasignada=aa.actividadAsignadaCodigo and aa.inscripcionProyecto=ip.inscripcionCodigo and ip.preInscripcion=pp.preinscripcionCodigo" +
			" and pp.proyectop=?2 and pp.estudiante=?1")
	public List<ActividadEjecutada> buscarPorEstudianteProyecto(Estudiante est , Proyecto pro);

	@Query("select distinct ins from InscripcionProyecto as ins,ActividadAsignada as acta, ActividadEjecutada as actej"
			+ " "   +" where ins.inscripcionCodigo=acta.inscripcionProyecto"
		     + " "  +" and acta in (select act.actividadasignada from ActividadEjecutada as act)"
		     + " "	+" and ins.inscripcionProyectoStatus='Activo' "
		     + " "	+" and ins.proyectoi=?1 "
		     + " "  + "and  ins not in (select distinct ins from InscripcionProyecto as ins,ActividadAsignada as acta, ActividadEjecutada as actej"
			+ " "   +" where ins.inscripcionCodigo=acta.inscripcionProyecto"
		     + " "  +" and acta not in (select act.actividadasignada from ActividadEjecutada as act)"
		     + " "	+" and ins.inscripcionProyectoStatus='Activo' "
		     + " "	+" and ins.proyectoi=?1 )")
	
	public List<InscripcionProyecto>EstudiantesCulminados(Proyecto proyecto);
	
	@Query("select distinct ae from ActividadEjecutada as ae, ActividadAsignada as aa, InscripcionProyecto ip " +
			"where ae.actividadasignada=aa.actividadAsignadaCodigo and aa.inscripcionProyecto=?1")
	public List<ActividadEjecutada> buscarPorInscripcionProyecto(InscripcionProyecto ins);
}
