package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import siagsce.modelo.data.maestros.CoordinadorSce;
import siagsce.modelo.data.maestros.Profesor;

/**
 * Interfaz CoordinadorSce, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo CoordinadorSce.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(CoordinadorSce coordinadorSce),delete(CoordinadorSce coordinadorSce),
 * findAll(),findOne(Integer coordinadorSce),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface ICoordinadorSceRepositorio  extends JpaRepository<CoordinadorSce,Integer>{
 
	/**
	 * Busca el coordinador  dado un status.
	 * @return el coordinador.
	 * @param estatus, estatus del coordinador.
	 */
	public CoordinadorSce findByCoordinadorStatus(String estatus);

	/**
	 * Busca todos los coordinadores de manera ordeanda ascendente.
	 * @return Lista de coordinadores.
	 */
 @Query("select coord from CoordinadorSce as coord order by coordinadorFechaInicio asc")
 public List<CoordinadorSce>buscarOrdenado();
 /**
  * Trae al director activo de acuerdo a la cedula
  * @param profesor, al cual buscaremos
  * @param estatus, por el cual se buscara al director
  * @return Coordinador del Servicio comunitario Estudiantil
  * */
 @Query("select coord from CoordinadorSce as coord where coord.profesorcoordinador=?1 and coordinadorStatus=?2")
 public CoordinadorSce buscarPorProfesorYEstatus(Profesor profesor,String estatus);
 
 public CoordinadorSce findByProfesorcoordinadorAndCoordinadorStatus(Profesor profesor,String estatus);
}
