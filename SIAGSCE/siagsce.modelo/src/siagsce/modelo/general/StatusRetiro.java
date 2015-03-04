package siagsce.modelo.general;

public enum StatusRetiro {
	Contable("Contable"), NoContable("No Contable");	
	StatusRetiro(String descripcion) {
		this.descripcion = descripcion;
	}

	private final String descripcion;
	
	@Override
	public String toString() {
		return descripcion;
	}
}
