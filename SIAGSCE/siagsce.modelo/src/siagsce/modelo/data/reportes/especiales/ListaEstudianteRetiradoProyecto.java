
/** @Modelo Lirado Proyectoista Estudiantes Ret - Reporte Especial
 * Modelo usado en la generación del reporte Lista de Estudiantes
 * Retirados de un Proyecto  en el SCE
 * @author Iterator
 */

package siagsce.modelo.data.reportes.especiales;


/**
 * Declaración de variables.
 */
public class ListaEstudianteRetiradoProyecto {
	
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	
	
	/**
	 * Declaración del constructor
	 */
	public ListaEstudianteRetiradoProyecto(String cedula, String nombre,
			String apellido, String email, String telefono) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}
	
	
	/**
	 * Declaración del constructor
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

}
