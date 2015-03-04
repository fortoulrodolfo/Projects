package siagsce.modelo.repositorio.seguridad;



import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import  siagsce.modelo.data.seguridad.Nodo;

/**
 * Interfaz Nodo, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Nodo.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Nodo nodo),delete(Nodo nodo),findAll(),findOne(Integer idNodo),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface INodo extends JpaRepository<Nodo,Integer> {
	/**
	 * Retorna una lista de nodos dado el padre,
	 * dichos nodos estaran ordenados por prioridad.
	 * @param padre representa el padre de los nodos a obtener.
	 * @return lista de nodos.
	 */ 
public List<Nodo> findByPadreOrderByPrioridadAsc(int padre);
}