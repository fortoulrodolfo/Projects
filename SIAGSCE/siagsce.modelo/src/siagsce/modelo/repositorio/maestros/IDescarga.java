package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zkoss.zul.ListModelList;

import siagsce.modelo.data.maestros.Actividad;
import siagsce.modelo.data.maestros.Descarga;
import siagsce.modelo.data.maestros.Proyecto;

/**
 * Interfaz Descarga, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Descarga.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Descarga descarga),delete(Descarga descarga),
 * findAll(),findOne(Integer Descarga),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IDescarga extends JpaRepository<Descarga,Integer> {

	/**
	 * Busca una descarga  dado un codigo.
	 * @return la descarga.
	 * @param codigo codigo de la descarga.
	 */
	public Descarga findByDescargaCodigo(Integer codigo);
}
