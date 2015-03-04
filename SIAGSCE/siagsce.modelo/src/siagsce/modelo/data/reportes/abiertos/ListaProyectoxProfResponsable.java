/**
 * Lista para el ViewModel de consulta de proyectos por profesor responsable en
 * el Servicio Comunitario Estudiantil
 * @author Iterator
 */

package siagsce.modelo.data.reportes.abiertos;

/**
 * Declaración de Variables.
 */
public class ListaProyectoxProfResponsable {
	private String codigo;
	private String nombre;
	private String descripcion;
	
	
	/**
	 * Constructor por defecto.
	 */
	public ListaProyectoxProfResponsable() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * Creación del constructor
	 */
	public ListaProyectoxProfResponsable(String codigo, String nombre,
			String descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	
	/**
	 * Declaración de set y get
	 */
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
