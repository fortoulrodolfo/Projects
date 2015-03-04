package siagsce.modelo.general;

public enum StatusPreinscripcionProyecto {
	Activo("Activo"), Inactivo("Inactivo"),EnEspera("En Espera"),Retirado("Retirado");	
	StatusPreinscripcionProyecto(String descripcion) {
		this.descripcion = descripcion;
	}

	private final String descripcion;
	
	@Override
	public String toString() {
		return descripcion;
	}
}
