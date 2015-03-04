package siagsce.modelo.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import siagsce.modelo.data.maestros.CoordinacionSce;
import siagsce.modelo.data.maestros.CoordinadorSce;


/**
 * Interfaz CoordinacioncSce, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo CoordinacioncSce.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(CoordinacioncSce CoordinacioncSce),delete(CoordinacioncSce CoordinacioncSce),
 * findAll(),findOne(Integer CoordinacioncSce),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface ICoordinacioncSceRepositorio extends JpaRepository<CoordinacionSce,String> {

	/**
	 * Busca la coordinacion SCE dado un codigo.
	 * @return la coordinacion de sce.
	 * @param codigo codigo de la coordinacion.
	 */
	public CoordinacionSce findByCoordinacionCodigo(Integer codigo);

}
