package siagsce.modelo.repositorio.seguridad;

import org.springframework.data.jpa.repository.JpaRepository;
import  siagsce.modelo.data.seguridad.Usuario;
/**
 * Interfaz Usuario, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Usuario.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Usuario usuario),delete(Usuario usuario),findAll(),findOne(Integer idUsuario),etc... por heredar
 * de la clase JpaRepository.
 */
public interface IUsuario extends JpaRepository<Usuario, Integer> {
	/**
	 * retorna un usuario dado el nombre de usuario(cédula).
	 * @param nombreUsuario
	 * @return Usuario que concuerda con la cédula. 
	 */
	public Usuario findByNombreUsuario(String nombreUsuario);
}
