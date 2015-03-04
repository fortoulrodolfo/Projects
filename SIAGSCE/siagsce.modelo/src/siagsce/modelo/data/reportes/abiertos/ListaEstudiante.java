/**
 * @Modelo Lista Estudiantes - Reporte Abierto
 * Modelo usado en la generación del reporte estudiantes que tienen un estatus dentro del SCE.
 * @author Iterator
 */

package siagsce.modelo.data.reportes.abiertos;


/**
 * Declaración de variables
 */
public class ListaEstudiante {
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private Integer codigoDriceccionPrograma;
	public ListaEstudiante() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con campos
	 * @param cedula
	 * @param nombre.
	 * @param apellido.
	 * @param email.
	 * @param telefono.
	 * @param codigoDriceccionPrograma.
	 */
	public ListaEstudiante(String cedula, String nombre, String apellido,
			String email, String telefono, Integer codigoDriceccionPrograma) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.codigoDriceccionPrograma = codigoDriceccionPrograma;
	}
	
	/**
	 * Constructor con campos
	 * @param cedula
	 * @param nombre.
	 * @param apellido.
	 * @param email.
	 * @param telefono.
	 */
	public ListaEstudiante(String cedula, String nombre, String apellido,
			String email, String telefono) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;

	}

	/**
	 * A continuacion se declaran todos los getter y setter de las variables.
	*/
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getCodigoDriceccionPrograma() {
		return codigoDriceccionPrograma;
	}

	public void setCodigoDriceccionPrograma(Integer codigoDriceccionPrograma) {
		this.codigoDriceccionPrograma = codigoDriceccionPrograma;
	}

}
