package siagsce.modelo.data.maestros;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * La clase Parametro representa los parametros usandos por para realizar ciertas operaciones en el sistema
 * 
 * @author Iterator
 * 
*/
@Entity
@Table(name = "parametro")
public class Parametro {

	/**
	 * Codigo identificador unico del parametro, generado automaticamente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "parametro_codigo", unique = true, nullable = false)
	private Integer parametroCodigo;

	/**
	 * Nombre dado parametro.
	 */

	@Column(name = "parametro_nombre", nullable = false, length = 30)
	private String parametroNombre;

	/**
	 * Descripcion dada al parametro.
	 */

	@Column(name = "parametro_descripcion", nullable = false)
	private String parametroDescripcion;

	/**
	 * Tipo del parametro que se usara  para un objetivo.
	 * ejemplo (numero de horas del taller o cantidad maxima de horas
	 * para cumplir con la ley)
	 */
	@Column(name = "parametro_interger", nullable = true)
	private Integer parametroInterger;

	/**
	 * Tipo del parametro que se usara  para un objetivo.
	 * ejemplo (numero de horas del taller o cantidad maxima de horas
	 * para cumplir con la ley)
	 */
	@Column(name = "parametro_string", nullable = true)
	private String parametroString;

	/**
	 * Construye un nuevo parametro.
	 */
	public Parametro() {
		super();
	}

	/**
	 * Construye un parametro con todos sus parametros.
	 */
	public Parametro(String parametroNombre, String parametroDescripcion,
			Integer parametroInterger, String parametroString) {
		super();
		this.parametroNombre = parametroNombre;
		this.parametroDescripcion = parametroDescripcion;
		this.parametroInterger = parametroInterger;
		this.parametroString = parametroString;
	}
	
	/**
	 * Retorna el codigo identificardo unico del parametro.
	 * 
	 * @return Codigo identificador del parametro
	 */ Integer getParametroCodigo() {
		return parametroCodigo;
	}

	 /**
		 * Establece el codigo Identificador unico del parametro.
		 * 
		 * @param parametroCodigo  codigo identificador del parametro 
		 */
	public void setParametroCodigo(Integer parametroCodigo) {
		this.parametroCodigo = parametroCodigo;
	}

	/**
	 * Retorna el nombre del parametro.
	 * 
	 * @return nombre del parametro
	 */
	public String getParametroNombre() {
		return parametroNombre;
	}

	/**
	 * Establece el nombre del parametro.
	 * 
	 * @param parametroNombre  es el nombre  del parametro
	 */
	public void setParametroNombre(String parametroNombre) {
		this.parametroNombre = parametroNombre;
	}

	/**
	 * Retorna la descripcion del parametro.
	 * 
	 * @return descripcion del parametro
	 */
	public String getParametroDescripcion() {
		return parametroDescripcion;
	}

	/**
	 * Establece la descripcion del parametro.
	 * 
	 * @param parametroDescripcion  descripcion del parametro
	 */
	public void setParametroDescripcion(String parametroDescripcion) {
		this.parametroDescripcion = parametroDescripcion;
	}

	/**
	 * Retorna el interger del parametro.
	 * 
	 * @return integer del parametro
	 */
	public Integer getParametroInterger() {
		return parametroInterger;
	}
	/**
	 * Establece el interger del parametro.
	 * 
	 * @param parametroInterger  del parametro 
	 */

	public void setParametroInterger(Integer parametroInterger) {
		this.parametroInterger = parametroInterger;
	}

	/**
	 * Retorna el String del parametro.
	 * 
	 * @return String del parametro
	 */
	public String getParametroString() {
		return parametroString;
	}
	/**
	 * Establece String del parametro.
	 * 
	 * @param parametroString  del parametro
	 */

	public void setParametroString(String parametroString) {
		this.parametroString = parametroString;
	}

}
