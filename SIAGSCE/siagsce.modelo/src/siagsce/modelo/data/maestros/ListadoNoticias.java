package siagsce.modelo.data.maestros;

import java.math.BigInteger;
import java.util.Date;

import org.zkoss.image.AImage;

/**
 * La clase ListadoNoticias representa en forma de lista
 * las noticias con sus atributos que estaran en el portal
 * 
 * @author Iterator
 * 
 * 
*/
public class ListadoNoticias {
	
	/**
	 * Codigo identificador unico de la noticia, generado automaticamente.
	 */	
	Integer codigo;
	
	/**
	 * Titulo y Contenido que llevara la noticia.
	 */	
	String titulo, contenido;
	
	/**
	 * fecha que se usara la noticia en el portal.
	 */	
	String fecha;
	/**
	 * Foto que se usara la noticia en el portal.
	 */	
	AImage foto;
	
	
	/**
	 * Construye el listado de noticia con todos sus parametros.
	 */	
	public ListadoNoticias(Integer codigo, String titulo, String contenido,
			String fecha, AImage foto) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.contenido = contenido;
		this.fecha = fecha;
		this.foto = foto;
	}

	public ListadoNoticias() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * Setter y Getter
	 * */
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public AImage getFoto() {
		return foto;
	}

	public void setFoto(AImage foto) {
		this.foto = foto;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
}
