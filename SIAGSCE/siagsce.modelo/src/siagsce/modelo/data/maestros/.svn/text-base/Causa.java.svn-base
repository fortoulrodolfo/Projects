package siagsce.modelo.data.maestros;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ManyToAny;

import siagsce.modelo.data.transacciones.InscripcionProyecto;


/**
 * La clase Causa, representa los datos de las causas asociadas a los
 * diversos motivos existentes para exonerar o retirar a estudiantes (cambios de estatus del estudiante)
 *
 * @author Iterator
 * 
 */

@Entity
@Table(name = "causa")
public class Causa {
	
	/**
	 * Codigo Identificador único de la Causa generado autommáticamente.
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "causa_codigo", unique = true, nullable = false)
	private Integer causaCodigo;

	/**
	 * Nombre otorgado a la causa
	 * 
	 */
	@Column(name = "causa_nombre",length = 50, nullable = false)
	private String causaNombre;

	/**
	 * Motivo asociado a la causa
	 * 
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "causaMotivo")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Motivo> causaMotivo;

	/**
	 * Construye una nueva Causa
	 */
	public Causa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construye una nueva Causa con todos sus parametros.
	 */
	public Causa(String causaNombre, List<Motivo> causaMotivo) {
		super();
		this.causaNombre = causaNombre;
		this.causaMotivo = causaMotivo;
	}

	/**
	 * Retorna el nombre de la causa.
	 * 
	 * @return Nombre de la causa
	 */
	public String getCausaNombre() {
		return causaNombre;
	}

	/**
	 * Establece el nombre de la causa.
	 * 
	 * @param causaNombre      Nombre de la causa
	 */
	public void setCausaNombre(String causaNombre) {
		this.causaNombre = causaNombre;
	}

	/**
	 * Retorna el codigo identificador unico de la causa.
	 * 
	 * @return Codigo identificador de la causa
	 */
	public Integer getCausaCodigo() {
		return causaCodigo;
	}
	
	/**
	 * Establece el codigo identificador unico de la causa.
	 * 
	 * @param Codigo de la causa 
	 */
	public void setCausaCodigo(Integer causaCodigo) {
		this.causaCodigo = causaCodigo;
	}

	/**
	 * Retorna al motivo de la causa asociada a el.
	 * 
	 * @return Causa del motivo
	 */
	public List<Motivo> getCausaMotivo() {
		return causaMotivo;
	}

	/**
	 * Establece el motivo de la causa asociada a el.
	 * 
	 * @param Causa del motivo 
	 */
	public void setCausaMotivo(List<Motivo> causaMotivo) {
		this.causaMotivo = causaMotivo;
	}

}
