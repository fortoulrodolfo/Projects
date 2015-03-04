package siagsce.modelo.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zkoss.zul.ListModelList;

import siagsce.modelo.data.maestros.Noticia;

/**
 * Interfaz Noticia, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo Noticia.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(Noticia noticia),delete(Noticia noticia),
 * findAll(),findOne(Integer noticia),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface INoticia extends JpaRepository<Noticia,Integer> {

	/**
	 * Busca una noticia  dado un codigo.
	 * @return noticia 
	 * @param codigo, codigo noticia.
	 */
	Noticia findByNoticiaCodigo(Integer codigo);

	
}
