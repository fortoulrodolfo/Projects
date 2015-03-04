package siagsce.modelo.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.data.maestros.Parametro;

/**
 * Interfaz Parametro, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Parametro.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Parametro parametro),delete(Parametro parametro),
 * findAll(),findOne(Integer parametro),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IParametro extends JpaRepository<Parametro, Integer> {
	/**
	 * Busca un parametro  dado un nombre.
	 * @return parametro 
	 * @param nombre, nombre parametro.
	 */
	public Parametro findByParametroNombre(String nombre);

}
