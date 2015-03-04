package siagsce.modelo.data.maestros;

//Clase utilizada para llenar el listbox de estudiantes evaluados en la interfaz de evaluacion taller
public class ListadoEstudiantesEvaluadosTaller {
	
	String cedula, nombre, apellido, calificacion;

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
	public String getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	public ListadoEstudiantesEvaluadosTaller(String cedula, String nombre,
			String apellido, String calificacion) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.calificacion = calificacion;
	}
	
	
}
