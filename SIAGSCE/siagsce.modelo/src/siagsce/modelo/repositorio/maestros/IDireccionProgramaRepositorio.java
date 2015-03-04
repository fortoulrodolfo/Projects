package siagsce.modelo.repositorio.maestros;

import siagsce.modelo.data.maestros.DireccionPrograma;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz DireccionPrograma, contiene los metodos para interactuar con
 * la base de datos gestionando asi los datos del modelo DireccionPrograma.
 * Nota:Ademas de los metodos que se pueden observar, la interfaz tambien
 * ofrece metodos como save(DireccionPrograma direccionPrograma),delete(DireccionPrograma direccionPrograma),
 * findAll(),findOne(Integer direccionPrograma),etc... por heredar
 * de la clase JpaRepository.
 */ 
public interface IDireccionProgramaRepositorio extends JpaRepository<DireccionPrograma,Integer> {
	
	/**
	 * Busca una Direccion de Programa  dado un codigo.
	 * @return la Direccion de Programa.
	 * @param codigo codigo de la Direccion de Programa.
	 */
	public DireccionPrograma findByDireccionCodigo(Integer codigo);
	
	/**
	 * Busca una Direccion de Programa  dado un nombre.
	 * @return la Direccion de Programa.
	 * @param nombre codigo de la Direccion de Programa.
	 */
	public DireccionPrograma findByDireccionNombre(String nombre);

}
