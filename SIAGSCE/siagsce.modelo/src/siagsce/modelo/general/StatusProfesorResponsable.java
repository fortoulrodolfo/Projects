package siagsce.modelo.general;

public enum StatusProfesorResponsable {
	Activo("Activo"), Inactivo("Inactivo");	
	StatusProfesorResponsable(String descripcion) {
		this.descripcion = descripcion;
	}

	private final String descripcion;
	
	@Override
	public String toString() {
		return descripcion;
	}
}
