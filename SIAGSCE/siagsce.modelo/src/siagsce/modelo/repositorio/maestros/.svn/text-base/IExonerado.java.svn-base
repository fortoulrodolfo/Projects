package siagsce.modelo.repositorio.maestros;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Exonerado;
import siagsce.modelo.data.maestros.Motivo;

/**
 * Interfaz Exonerado, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Exonerado.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Exonerado exonerado),delete(Exonerado exonerado),
 * findAll(),findOne(Integer exonerado),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IExonerado extends JpaRepository<Exonerado,Integer> {
	
	

	/**
	 * Busca un estudiante  dado un objeto exonerado.
	 * @return estudiante exonerado
	 * @param exonerado, objeto exonerado.
	 */
	public Exonerado findByEstudianteExonerado(Exonerado exonerado);
	
	/**
	 * Busca un estudiante  dado un codigo exonerado.
	 * @return estudiante exonerado
	 * @param codigo, codigo exonerado.
	 */
	public Exonerado findByExoneradoCodigo(Integer codigo);
	
	/**
	 * Busca una lista de estudiantes exonerados  dado un motivo.
	 * @return Lista de estudiantes.
	 * @param motivo, motivo .
	 */
	public List<Exonerado> findByMotivoExonerado (Motivo motivo);
	

}



