/** @Modelo EstudianteInscritoPorTaller - Reporte Especial
 * Modelo usado en la generación del reporte Actividades
 * asignadas a estudiantes en el SCE
 * @author Iterator
 */


package siagsce.modelo.data.reportes.especiales;


/**
 * Declaración de variables.
 */

public class EstudianteInscritoPorTaller {
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String direccion;
	private Integer codigoDriceccionPrograma;
	private Integer codigotaller;
	private String nombreprograma;
	
	/**
	 * Declaración del constructor
	 */
	public EstudianteInscritoPorTaller(String cedula, String nombre, String apellido,String email,String telefono, String direccion, String nombreprograma) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email=email;
		this.telefono= telefono;
		this.direccion= direccion;
		this.nombreprograma= nombreprograma;
	}
	
	
	/**
	 * Declaración de set y get
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCodigoDriceccionPrograma() {
		return codigoDriceccionPrograma;
	}

	public void setCodigoDriceccionPrograma(Integer codigoDriceccionPrograma) {
		this.codigoDriceccionPrograma = codigoDriceccionPrograma;
	}

	public Integer getCodigotaller() {
		return codigotaller;
	}

	public void setCodigotaller(Integer codigotaller) {
		this.codigotaller = codigotaller;
	}


	public String getNombreprograma() {
		return nombreprograma;
	}


	public void setNombreprograma(String nombreprograma) {
		this.nombreprograma = nombreprograma;
	}

}
