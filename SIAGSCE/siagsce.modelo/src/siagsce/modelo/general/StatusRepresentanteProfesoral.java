package siagsce.modelo.general;

public enum StatusRepresentanteProfesoral {
	Activo("Activo"), Inactivo("Inactivo");	
	StatusRepresentanteProfesoral(String descripcion) {
		this.descripcion = descripcion;
	}

	private final String descripcion;
	
	@Override
	public String toString() {
		return descripcion;
	}
}
