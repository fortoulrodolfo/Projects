package siagsce.modelo.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.transacciones.ActividadAsignada;
import siagsce.modelo.data.transacciones.ActividadEjecutada;
import siagsce.modelo.data.transacciones.InscripcionProyecto;
import siagsce.modelo.repositorio.transacciones.IActividadEjecutadaRepositorio;

@Service("sactividadEjecutada")
public class SActividadEjecutada {
	@Autowired
	IActividadEjecutadaRepositorio actividadAsignadaRepositorio;
	
	public void guardar(ActividadEjecutada actej){
		actividadAsignadaRepositorio.save(actej);
	}
	public void modificar(ActividadEjecutada actej){
		actividadAsignadaRepositorio.save(actej);
	}
	public void eliminar(ActividadEjecutada actej){
		actividadAsignadaRepositorio.delete(actej);
	}
	public List<ActividadEjecutada> buscarTodo(){
		return actividadAsignadaRepositorio.findAll();
	}

	public List<ActividadEjecutada> buscarporEstudianteProyecto(Estudiante est, Proyecto pro){
		return actividadAsignadaRepositorio.buscarPorEstudianteProyecto(est,pro);
	}

	public List<InscripcionProyecto>buscarInscripcionesCulminadas(Proyecto proyecto){
		return actividadAsignadaRepositorio.EstudiantesCulminados(proyecto);
	}
	/**
	 * Busca todas las actividades asignadas dado una inscripcion de proyecto(estudiante).
	 * @return Lista de actividades asignadas.
	 * @param inscripcionProyecto Inscripcion de proyecto(estudiante).
	 */
	public List<ActividadEjecutada> buscarPorInscripcionProyecto(InscripcionProyecto inscripcionProyecto){
		return actividadAsignadaRepositorio.buscarPorInscripcionProyecto(inscripcionProyecto);
	}
}
