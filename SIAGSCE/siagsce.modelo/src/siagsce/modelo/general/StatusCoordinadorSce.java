package siagsce.modelo.general;

public enum StatusCoordinadorSce {
	Activo("Activo"), Inactivo("Inactivo");
	StatusCoordinadorSce(String descripcion) {
		this.descripcion = descripcion;
	}

	private final String descripcion;

	@Override
	public String toString() {
		return descripcion;
	}
}
