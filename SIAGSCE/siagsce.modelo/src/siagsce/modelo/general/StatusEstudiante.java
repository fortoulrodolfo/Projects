package siagsce.modelo.general;

public enum StatusEstudiante {

Apto("Apto"), Acreditado("Acreditado"), Exonerado("Exonerado"), Aprobado("Aprobado"), Culminado("Culminado");	
	StatusEstudiante(String descripcion) {
		this.descripcion = descripcion;
	}

	private final String descripcion;
	
	@Override
	public String toString() {
		return descripcion;
	}
}
