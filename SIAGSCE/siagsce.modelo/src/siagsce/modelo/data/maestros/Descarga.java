package siagsce.modelo.data.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * La clase Descarga representa las datos de las descargas de cualquier información importante
 * que pueda ser descargada y que se realizarán desde el portal
 * 
 * @author Iterator
 * 
*/

@Entity
@Table(name = "descarga")
public class Descarga {

	/**
	 * Codigo Identificador único de la descarga, generado automáticamente.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "descarga_codigo", nullable = false)
	private Integer descargaCodigo;

	/**
	 * Archivo a descargar (formato) 
	 */
	@Column(name = "descarga_archivo", nullable = false)
	private byte[] contenidoDocumento = new byte[] {};

	/**
	 * Nombre del archivo a descargar 
	 */
	@Column(name = "descarga_nombre_archivo", nullable = false)
	private String descargaNombreArchivo;

	/**
	 * Nombre de la descarga 
	 */
	@Column(name = "descarga_nombre", length = 100, nullable = false)
	private String descargaNombre;

	/**
	 * Tipo de descarga 
	 */
	@Column(name = "descarga_tipo", length = 20, nullable = false)
	private String descargaTipo;

	/**
	 * Construye una nueva Descarga
	 */
	public Descarga() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construye una nueva Descarga con parametros
	 */
	public Descarga(byte[] contenidoDocumento, String descargaNombreArchivo,
			String descargaNombre, String descargaTipo) {
		super();
		this.contenidoDocumento = contenidoDocumento;
		this.descargaNombreArchivo = descargaNombreArchivo;
		this.descargaNombre = descargaNombre;
		this.descargaTipo = descargaTipo;
	}

	/** 
	 * Retorna el codigo identificador unico de una descarga
	 * @return codigo de descarga
	 */
	public Integer getDescargaCodigo() {
		return descargaCodigo;
	}

	/**
	 * Establece el codigo identificador unico de una descarga
	 * 
	 * @param codigo de desarga
	 */
	public void setDescargaCodigo(Integer descargaCodigo) {
		this.descargaCodigo = descargaCodigo;
	}

	/** 
	 * Retorna el contenido del documento a descargar
	 * @return contenido del documento a  descargar
	 */
	public byte[] getContenidoDocumento() {
		return contenidoDocumento;
	}

	/**
	 * Establece contenido del documento a descargar
	 * 
	 * @param contenido del documento 
	 */
	public void setContenidoDocumento(byte[] contenidoDocumento) {
		this.contenidoDocumento = contenidoDocumento;
	}

	/** 
	 * Retorna el nombre de la descarga
	 * @return nombre de la descarga
	 */
	public String getDescargaNombre() {
		return descargaNombre;
	}

	/**
	 * Establece el el nombre de la descarga
	 * 
	 * @param nombre de la descarga 
	 */
	public void setDescargaNombre(String descargaNombre) {
		this.descargaNombre = descargaNombre;
	}

	/** 
	 * Retorna el tipo de descarga
	 * @return el tipo de descarga
	 */
	public String getDescargaTipo() {
		return descargaTipo;
	}

	/**
	 * Establece el tipo de descarga
	 * 
	 * @param tipo de descarga
	 */
	public void setDescargaTipo(String descargaTipo) {
		this.descargaTipo = descargaTipo;
	}

	/** 
	 * Retorna el nombre del archivo
	 * @return nombre del archivo
	 */
	public String getDescargaNombreArchivo() {
		return descargaNombreArchivo;
	}

	/**
	 * Establece el nombre del archivo
	 * 
	 * @param el nombre del archivo 
	 */
	public void setDescargaNombreArchivo(String descargaNombreArchivo) {
		this.descargaNombreArchivo = descargaNombreArchivo;
	}

}
