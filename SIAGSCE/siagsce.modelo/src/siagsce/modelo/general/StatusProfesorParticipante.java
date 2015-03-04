package siagsce.modelo.general;

public enum StatusProfesorParticipante {
	Activo("Activo"), Inactivo("Inactivo");	
	StatusProfesorParticipante(String descripcion) {
		this.descripcion = descripcion;
	}

	private final String descripcion;
	
	@Override
	public String toString() {
		return descripcion;
	}
}
