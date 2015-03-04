package siagsce.modelo.general;

public enum StatusProfesor {

	Apto("Apto"), Acreditado("Acreditado");	
	StatusProfesor(String descripcion) {
		this.descripcion = descripcion;
	}

	private final String descripcion;
	
	@Override
	public String toString() {
		return descripcion;
	}
}
