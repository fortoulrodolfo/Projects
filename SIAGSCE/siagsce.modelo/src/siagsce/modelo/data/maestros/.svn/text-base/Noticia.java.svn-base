
package siagsce.modelo.data.maestros;


import java.util.Date;

import javax.persistence.*;

/**
 * La clase Noticia representa las noticias con sus atributos que estaran en el portal
 * 
 * @author Iterator
 * 
*/

@Entity
@Table(name = "noticia")
public class Noticia {
	
	/**
	 * Codigo identificador unico de la noticia, generado automaticamente.
	 */	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "noticia_codigo", unique = true , nullable=false)
    private Integer noticiaCodigo;
	
	/**
	 * Titulo que llevara la noticia.
	 */	
	
	@Column(name = "noticia_titulo",length = 30, nullable=false)
    private String noticiaTitulo;
	
	/**
	 * Contenido de la noticia.
	 */	
	@Column(name = "noticia_contenido", nullable=false)
    private String noticiaContenido;
	
	/**
	 * Foto que se usara la noticia en el portal.
	 */	
	@Column(name = "noticia_foto", nullable=false)
	private byte[] noticiaFoto = new byte[] {};


	/**
	 * fecha que se usara la noticia en el portal.
	 */	
	@Temporal(TemporalType.DATE)
	@Column(name = "noticia_fecha", nullable = false)
	private Date noticiaFecha;
	
	/**
	 * nombre de la foto que se usara la noticia en el portal.
	 */	
	@Column(name = "noticia_foto_nombre", nullable=false)
    private String noticiaFotoNombre;
	
	/**
	 * Construye una noticia con todos sus parametros.
	 */	

	public Noticia(Integer noticiaCodigo, String noticiaTitulo,
			String noticiaContenido, Date noticiaFecha,
			String noticiaFotoNombre, byte[] noticiaFoto) {
		super();
		this.noticiaCodigo = noticiaCodigo;
		this.noticiaTitulo = noticiaTitulo;
		this.noticiaContenido = noticiaContenido;
		this.noticiaFecha = noticiaFecha;
		this.noticiaFotoNombre = noticiaFotoNombre;
		this.noticiaFoto = noticiaFoto;
	}
	/**
	 * Construye una nueva noticia.
	 */
	public Noticia() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * Retorna el codigo identificardo unico de la noticia.
	 * 
	 * @return Codigo identificador de la noticia
	 */

	public Integer getNoticiaCodigo() {
		return noticiaCodigo;
	}
	/**
	 * Establece el codigo Identificador unico de la noticia.
	 * 
	 * @param noticiaCodigo  codigo identificador de la noticia 
	 */
	public void setNoticiaCodigo(Integer noticiaCodigo) {
		this.noticiaCodigo = noticiaCodigo;
	}

	/**
	 * Retorna el titulo de la noticia.
	 * 
	 * @return titulo  de la noticia
	 */
	public String getNoticiaTitulo() {
		return noticiaTitulo;
	}
	/**
	 * Establece el titulo de la noticia.
	 * 
	 * @param titulo de la noticia 
	 */
	public void setNoticiaTitulo(String noticiaTitulo) {
		this.noticiaTitulo = noticiaTitulo;
	}
	/**
	 * Retorna el contenido de la noticia.
	 * 
	 * @return contenido  de la noticia
	 */
	public String getNoticiaContenido() {
		return noticiaContenido;
	}
	/**
	 * Establece el contenido de la noticia.
	 * 
	 * @param contenido de la noticia 
	 */
	public void setNoticiaContenido(String noticiaContenido) {
		this.noticiaContenido = noticiaContenido;
	}
	/**
	 * Retorna la fecha de la noticia.
	 * 
	 * @return la fecha   de la noticia
	 */
	public Date getNoticiaFecha() {
		return noticiaFecha;
	}
	/**
	 * Establece la fecha  de la noticia.
	 * 
	 * @param noticiaFecha, Fecha fecha de la noticia 
	 */
	public void setNoticiaFecha(Date noticiaFecha) {
		this.noticiaFecha = noticiaFecha;
	}
	/**
	 * Retorna nombre de la foto de la noticia.
	 * 
	 * @return nombre de la foto  de la noticia
	 */
	public String getNoticiaFotoNombre() {
		return noticiaFotoNombre;
	}
	/**
	 * Establece el nombre foto de la noticia.
	 * 
	 * @param noticiaFotoNombre,  el nombre foto de la noticia 
	 */
	public void setNoticiaFotoNombre(String noticiaFotoNombre) {
		this.noticiaFotoNombre = noticiaFotoNombre;
	}
	/**
	 * Retorna la foto de la noticia.
	 * 
	 * @return foto de la noticia  de la noticia
	 */
	public byte[] getNoticiaFoto() {
		return noticiaFoto;
	}
	/**
	 * Establece la foto de la noticia.
	 * 
	 * @param foto de la noticia 
	 */
	public void setNoticiaFoto(byte[] noticiaFoto) {
		this.noticiaFoto = noticiaFoto;
	}

	
  
}

	
    

	
	
	

	