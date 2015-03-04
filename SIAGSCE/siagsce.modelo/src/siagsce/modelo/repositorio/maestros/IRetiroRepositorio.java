package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.data.maestros.Retiro;
/**
 * Interfaz Retiro, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Retiro.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Retiro retiro),delete(Retiro retiro),
 * findAll(),findOne(Integer retiro),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IRetiroRepositorio extends JpaRepository<Retiro, Integer> {

	/**
	 * Busca una lista de retiros   dado un motivo.
	 * @return Lista de retiros.
	 * @param motivo, motivo .
	 */
	public List<Retiro> findByMotivoRetiro(Motivo motivo);
	
}
