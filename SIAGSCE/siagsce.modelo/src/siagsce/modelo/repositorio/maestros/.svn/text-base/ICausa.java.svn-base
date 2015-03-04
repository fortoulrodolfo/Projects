package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import siagsce.modelo.data.maestros.Causa;

/**
 * Interfaz Causa, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Causa.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Causa causa),delete(Causa causa),etc... por heredar de la clase JpaRepository.
 */
public interface  ICausa extends JpaRepository<Causa, Integer> {
	
	/**
	 * Busca la causa   dado un nombre.
	 * @return Causa.
	 * @param nombre nombre de la causa.
	 */
	public Causa findByCausaNombre(String nombre);
	
	
	
}
