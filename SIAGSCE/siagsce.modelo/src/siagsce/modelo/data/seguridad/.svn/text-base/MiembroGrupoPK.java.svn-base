package siagsce.modelo.data.seguridad;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the miembro_grupo database table.
 * 
 */
@Embeddable
@Access(AccessType.FIELD)
public class MiembroGrupoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "grupo_codigo", unique = false, nullable = false)
	private Integer idGrupo;

	@Column(name = "usuario_codigo", unique = false, nullable = false, length = 30)
	private Integer idUsuario;

	/**
	 * Constructor con campos
	 * @param idGrupo ID del grupo.
	 * @param idUsuario ID del usuario.
	 */
	public MiembroGrupoPK(Integer idGrupo, Integer idUsuario) {
		super();
		this.idGrupo = idGrupo;
		this.idUsuario = idUsuario;
	}
	/**
	 * Constructor con campos
	 * @param idGrupo ID del grupo.
	 * @param idUsuario ID del usuario.
	 */
	public MiembroGrupoPK() {
		super();	
	}
	/**
	 * A continuacion se declaran todos los getter y setter de las variables.
	*/
	public Integer getIdGrupo() {
		return this.idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * Metodo para verificar si el miembrogrupopk es el mismo pasado por parametro.
	 * @param other objeto que contiene el usuario y grupo.
	 * @return true si es igual.
	*/
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MiembroGrupoPK)) {
			return false;
		}
		MiembroGrupoPK castOther = (MiembroGrupoPK) other;
		return this.idGrupo.equals(castOther.idGrupo)
				&& this.idUsuario.equals(castOther.idUsuario);
	}
	/**
	 * Metodo necesario para que la clase pueda ser embedded (@EmbeddedId)
	*/
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idGrupo.hashCode();
		hash = hash * prime + this.idUsuario.hashCode();

		return hash;
	}
}