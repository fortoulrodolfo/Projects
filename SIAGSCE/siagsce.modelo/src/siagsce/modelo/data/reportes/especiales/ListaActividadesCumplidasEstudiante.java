/** @Modelo Lista Actividades Cumplidas Estudiante - Reporte Especial
* Modelo usado en la generacion del reporte actividades cumlida estudiante
* @author Iterator
*/

package siagsce.modelo.data.reportes.especiales;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Declaración de variables.
 */
public class ListaActividadesCumplidasEstudiante {
	private String cedula;
	private String nombre;
	private String apellido;
	private Integer horasCumplidas;
	private Date fecha;

	
	/**
	 * Declaración del constructor
	 */
	public ListaActividadesCumplidasEstudiante(String cedula, String nombre,
			String apellido, Integer horasCumplidas, Date resultRow) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.horasCumplidas = horasCumplidas;
		this.fecha = resultRow;
	}

	
	/**
	 * Declaración de set y get
	 */
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

	public Integer getHorasCumplidas() {
		return horasCumplidas;
	}

	public void setHorasCumplidas(Integer horasCumplidas) {
		this.horasCumplidas = horasCumplidas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
