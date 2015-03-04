package siagsce.modelo.repositorio.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import siagsce.modelo.data.seguridad.Grupo;
/**
 * Interfaz grupo, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Grupo.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Grupo grupo),delete(Grupo grupo),findAll(),findOne(Integer idGrupo),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IGrupo extends JpaRepository<Grupo, Integer> {
	/**
	 * Encuentra un grupo por su nombre.
	 * @param rol nombre del grupo.
	 */ 
	public Grupo findByNombre(String rol);
	/**
	 * Retorna una lista de grupo ordenados ascendentemente por codigo.
	 * @return lista de grupo.
	 */ 
	@Query("SELECT g FROM Grupo g ORDER BY g.idGrupo ASC")
	public List<Grupo> encontrarTodosOrdenadosPorCodigo();
}
