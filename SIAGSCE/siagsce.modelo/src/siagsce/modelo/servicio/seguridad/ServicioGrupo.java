    package siagsce.modelo.servicio.seguridad;

	import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.repositorio.seguridad.IGrupo;
import siagsce.modelo.repositorio.seguridad.IUsuario;
/**
 * ServicioGrupo ofrece los servicios necesarios para gestionar los
 * datos de los grupos.
 * Nota: El ServicioGrupo es una capa intermedia entre la interface IGrupo
 * y los viewmodels que necesiten de dicha interfaz.
 * @author Iterator
 */
		@Service("sg")
		public class ServicioGrupo {
			
			 @Autowired
			private IGrupo gru;
			 /**
			  * Guarda un grupo.
			  * @param grupo a guardar.
			  * @link {@link IGrupo}.
			  */
			public void guardarGrupo(Grupo grupo){
				gru.save(grupo);	
			}
			/**
			  * Dado un idGrupo retorna el grupo asociado.
			  * @param idgrupo representa el ID del grupo.
			  * @link {@link IGrupo}.
			  */
			public Grupo buscarGrupo(Integer idgrupo){
				return gru.findOne(idgrupo);
				
			}
			/**
			  * Retorna todos los grupos registrados ordenados ascendentemente por codigo.
			  * @return lista de grupo.
			  * @link {@link IGrupo}.
			  */
			public List<Grupo> buscarTodos(){
				return gru.encontrarTodosOrdenadosPorCodigo();	
			}
			/**
			  * Retorna el grupo asociado al nombre.
			  * @param nombre del grupo.
			  * @return grupo asociado al nombre.
			  * @link {@link IGrupo}
			  */
			public Grupo buscarGrupoNombre(String nombre){	
				return gru.findByNombre(nombre);
				
			}
			
			
			}


