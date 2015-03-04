package siagsce.modelo.data.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

/**
 * La clase CoordinacionSce, representa los datos de la coordinación del Servicio
 * Comunitario Estudiantil del Decanato de Ciencias y Tecnología
 *
 * @author Iterator
 * 
 */


@Entity
@Table(name = "coordinacion_sce")
public class CoordinacionSce {

	/**
	 * Codigo Identificador único de la CoordinacionSce generado autommáticamente.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "coordinacion_sce_codigo", unique = true, nullable = false)
	private Integer coordinacionCodigo;
	
	/**
	 * Nombre correspondiente a la Coordinacion de SCE
	 * 
	 */
	@Column(name = "coordinacion_sce_nombre", length = 50, nullable = false)
	private String coordinacionNombre;

	/**
	 * Corresponde a la relación existente con la clase Coordinador SCE.
	 * Profesor coordinador del SCE
	 * 
	 */
	@OneToMany( mappedBy = "coordinacionsce")
	private List<CoordinadorSce> coordinadorscec;

	/**
	 * Construye una nueva Coordinación de SCE
	 */
	public CoordinacionSce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construye una nueva Coordinación de SCE con todos sus parametros
	 */
	public CoordinacionSce(Integer coordinacionCodigo,
			String coordinacionNombre, List<CoordinadorSce> coordinadorscec) {
		super();
		this.coordinacionCodigo = coordinacionCodigo;
		this.coordinacionNombre = coordinacionNombre;
		this.coordinadorscec = coordinadorscec;
	}

	/**
	 * Retorna el codigo identificador unico de la coordinacion.
	 * 
	 * @return Codigo identificador de la coordinacion
	 */
	public Integer getCoordinacionCodigo() {
		return coordinacionCodigo;
	}

	/**
	 * Establece el codigo identificador unico de la coordinación.
	 * 
	 * @param coordinacionCodigo      codigo de la coordinacion
	 */
	public void setCoordinacionCodigo(Integer coordinacionCodigo) {
		this.coordinacionCodigo = coordinacionCodigo;
	}

	/**
	 * Retorna el nombre de la coordinacion.
	 * 
	 * @return nombre de la coordinacion
	 */
	public String getCoordinacionNombre() {
		return coordinacionNombre;
	}

	/**
	 * Establece el nombre de la coordinación.
	 * 
	 * @param nombre de la coordinación del sce
	 */
	public void setCoordinacionNombre(String coordinacionNombre) {
		this.coordinacionNombre = coordinacionNombre;
	}

	/**
	 * Retorna el coordinador de la coordinacion.
	 * 
	 * @return Coordinador de la coordinacion de SCE
	 */
	public List<CoordinadorSce> getCoordinadorscec() {
		return coordinadorscec;
	}

	/**
	 * Establece el coordinador de la coordinación.
	 * 
	 * @param coordinador del sce
	 */
	public void setCoordinadorscec(List<CoordinadorSce> coordinadorscec) {
		this.coordinadorscec = coordinadorscec;
	}

}
