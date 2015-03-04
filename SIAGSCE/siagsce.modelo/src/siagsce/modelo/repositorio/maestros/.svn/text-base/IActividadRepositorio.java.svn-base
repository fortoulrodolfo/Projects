package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zkoss.zul.ListModelList;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;

/**
 * Interfaz Actividad, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Actividad.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Actividad actividad),delete(Actividad actividad),
 * findAll(),findOne(Integer actividad),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IActividadRepositorio extends JpaRepository<Actividad,String> {

	/**
	 * Busca todas las actividades  dado un proyecto.
	 * @return Lista de actividades.
	 * @param proyecto proyecto.
	 */
	 public List<Actividad> findByProyectoa(Proyecto proyecto);
	 
	 /**
		 * Busca una actividad dado un codigo.
		 * @return actividad.
		 * @param codigo, codigo de la actividad.
		 */
	 public Actividad findByActividadCodigo(Integer codigo);
	 
	 /**
		 * Busca una actividad dado un nombre.
		 * @return actividad.
		 * @param nombre, nombre de la actividad.
		 */ 
	 public List<Actividad> findByActividadNombreAndProyectoa(String nombre,Proyecto proyecto);
	 /**
		 * Busca todas las actividades dado profesor participante.
		 * @return lista de actividades.
		 * @param profesor, el profesor participante de esas actividades.
		 */
	 @Query("SELECT DISTINCT actividad.proyectoa FROM Actividad as actividad INNER JOIN actividad.listaprofesoresparticipantes as profesor WHERE profesor = ?1")
	 public List<Proyecto> findProyectosDeUnProfesorParticipante(Profesor profesor);
	 /**
		 * Busca todas las actividades dado profesor participante.
		 * @return lista de actividades.
		 * @param profesor, el profesor participante de esas actividades.
		 */
	 @Query("SELECT DISTINCT actividad.proyectoa FROM Actividad as actividad INNER JOIN actividad.listaprofesoresparticipantes as profesor WHERE profesor = ?1 and actividad.proyectoa.proyectoStatus !='Inactivo'")
	 public List<Proyecto> findProyectosNoInactivosDeUnProfesorParticipante(Profesor profesor);
	 
	 /**
		 * Busca todas las actividades dado profesor participante y un proyecto.
		 * @return lista de actividades.
		 * @param profesor, el profesor participante de esas actividades de un proyecto.
		 */
	 @Query("SELECT actividad FROM Actividad as actividad INNER JOIN actividad.listaprofesoresparticipantes as profesor INNER JOIN actividad.proyectoa as proyecto WHERE profesor = ?1 and proyecto = ?2")
	 public List<Actividad> findActividadesDeUnProfesorParticipanteDeUnProyecto(Profesor profesor, Proyecto proyecto);
	 
	 /**
		 * Busca todas las actividades dado profesor participante .
		 * @return lista de actividades.
		 * @param profesor, el profesor participante de esas actividades de un proyecto.
		 */
	 @Query("select distinct pp from Actividad as act,Proyecto as pro join"
	 		+" " +"act.listaprofesoresparticipantes as pp where act.proyectoa=pro.proyectoCodigo and pro.proyectoCodigo=?1 and "
	 		+" " + " pp not in (select distinct ppr from Actividad as acti join acti.listaprofesoresparticipantes as ppr"
	 		+" " + ",Proyecto as proy where acti.proyectoa=proy.proyectoCodigo and proy.proyectoCodigo!=?1) ")
	 public List<Profesor>buscarporParticipante(String proyecto);
	 /**
	  * selecciona aquellas actividades pertenecientes a un participante
	  * que no sean del proyecto especificado
	  * @param profesor participante de la actividad
	  * @param proyecto del cual se descriminara las actividaddes
	  * @return lista de actividades 
	  * */
	 @Query("select act from Actividad as act join act.listaprofesoresparticipantes as profesor"
	 		+ " "+" where profesor=?1 ")
	 public List<Actividad>buscarParticipanteOtroProyecto(Profesor profesor);
	 /**
	  * selecciona aquellas actividades pertenecientes a un participante
	  * que no sean del proyecto especificado
	  * @param profesor participante de la actividad
	  * @param proyecto del cual se descriminara las actividaddes
	  * @return lista de actividades 
	  * */
	 @Query("select act from Actividad as act join act.listaprofesoresparticipantes as profesor"
	 		+ " "+" where profesor=?1  and act not in (select acti from Actividad as acti where acti.proyectoa=?2 )")
	 public List<Actividad>buscarParticipanteOtroProyecto(Profesor profesor,Proyecto proyecto);
	 /**
		 * busca si el profesor de la cedula de entrada es participante de un proyecto
		 * @param Profesor al cual buscaremos
		 * @return el profesor resultante de la busqueda
		 * */
	 @Query("select profesor from Actividad as act join act.listaprofesoresparticipantes as profesor where profesor=?1")
	 public Profesor buscarParticipanteExistente(Profesor profesor);
}

