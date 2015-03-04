package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import siagsce.modelo.data.maestros.Causa;
import siagsce.modelo.data.maestros.Motivo;

/**
 * Interfaz Motivo, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Motivo.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Motivo motivo),delete(Motivo motivo),
 * findAll(),findOne(Integer motivo),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IMotivo extends JpaRepository<Motivo, Integer> {

	
	/**
	 * Busca un motivo  dado un codigo.
	 * @return motivo 
	 * @param codigo, codigo motivo.
	 */
	public Motivo findByMotivoCodigo(Integer codigo);
	/**
	 * Busca una lista de motivos   dado un causa.
	 * @return Lista de motivos.
	 * @param causaMotivo, causa .
	 */
	public List<Motivo> findByCausaMotivo (Causa causaMotivo);
	
}
