/** @Modelo Lista Taller - Reporte Abierto
 * Modelo usado en la generación del reporte estudiantes inscritos por taller
 * @author Iterator
 */

package siagsce.modelo.data.reportes.abiertos;

import java.util.Date;


/**
 * Declaración de variables.
 */
public class ListaTaller {
	private String nombre;
	private String descripcion; 
	private Date fecha_culminacion; 
	private Date fecha_inicio; 
	private String lugar; 
	private String modalidad;
	
	/**
	 * Constructor por defecto.
	 */
	public ListaTaller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor con campos
	 * @param nombre.
	 * @param descripcion.
	 * @param fecha_culminacion.
	 * @param fecha_inicio.
	 * @param lugar.
	 * @param modalidad.
	 */
	public ListaTaller(String nombre, String descripcion,
			Date fecha_culminacion, Date fecha_inicio, String lugar,
			String modalidad ) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_culminacion = fecha_culminacion;
		this.fecha_inicio = fecha_inicio;
		this.lugar = lugar;
		this.modalidad = modalidad;
	}

	/**
	 * A continuacion se declaran todos los getter y setter de las variables.
	*/
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_culminacion() {
		return fecha_culminacion;
	}

	public void setFecha_culminacion(Date fecha_culminacion) {
		this.fecha_culminacion = fecha_culminacion;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

}
