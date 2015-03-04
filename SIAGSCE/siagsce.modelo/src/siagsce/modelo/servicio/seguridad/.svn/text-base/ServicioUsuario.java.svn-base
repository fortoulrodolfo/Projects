
package siagsce.modelo.servicio.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.repositorio.seguridad.IUsuario;

/**
 * ServicioUsuario ofrece los servicios necesarios para gestionar los
 * datos de los usuarios.
 * Nota: El ServicioUsuario es una capa intermedia entre la interface IUsuario
 * y los viewmodels que necesiten de dicha interfaz.
 * @author Iterator
 */
	@Service("su")
	public class ServicioUsuario {
		
		 @Autowired
		private IUsuario usu;
		 /**
		  * Guarda un usuario.
		  * @param usuario a guardar.
		  * @link {@link IUsuario}
		  */
		public void guardarUsuario(Usuario usuario){
			usu.save(usuario);	
		}
		 /**
		  * Elimina un usuario.
		  * @param usuario a eliminar.
		  * @link {@link IUsuario}
		  */
		public void eliminarUsuario(Usuario usuario){
			usu.delete(usuario);	
		}
		/**
		 * Retorna un usuario dado el ID de usuario.
		 * @param idusuario.
		 * @return Usuario que concuerda con el Id de usuario. 
		 * @link {@link IUsuario}
		 */
	 	public Usuario encontrarUsuario(Integer idusuario){
			return usu.findOne(idusuario);
			
		}
	 	/**
		 * Retorna un usuario dado el nombre de usuario(cédula).
		 * @param nombreUsuario
		 * @return Usuario que concuerda con la cédula.
		 * @link {@link IUsuario}
		 */
	 	public Usuario encontrarUsuarioPorNombreUsuario(String nombreUsuario){
			return usu.findByNombreUsuario(nombreUsuario);
			
		}
		
		}
			

		
		

	

	
