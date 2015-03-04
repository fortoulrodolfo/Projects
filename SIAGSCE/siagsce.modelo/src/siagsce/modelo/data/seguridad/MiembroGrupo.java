package siagsce.modelo.data.seguridad;


	import java.io.Serializable;
	import javax.persistence.Access;
	import javax.persistence.AccessType;
	import javax.persistence.AssociationOverride;
	import javax.persistence.AssociationOverrides;
	import javax.persistence.CascadeType;
	import javax.persistence.Column;
	import javax.persistence.EmbeddedId;
	import javax.persistence.Entity;
	import javax.persistence.JoinColumn;
	import javax.persistence.ManyToOne;
	import javax.persistence.Table;

	import siagsce.modelo.data.seguridad.Grupo;
	import siagsce.modelo.data.seguridad.Usuario;

	/**
	 * Miembro Grupo, describe los diferentes profesores 
	 * con sus respectivos roles.
	 * @author Iterator
	 */
	@Entity
	@Access(AccessType.FIELD)
	@Table(name="miembro_grupo")
	public class MiembroGrupo implements Serializable {
		private static final long serialVersionUID = 1L;
		//Definición de variables y campos en la base de datos.
		@EmbeddedId
		private MiembroGrupoPK id;

		//bi-directional many-to-one association to Grupo
		@ManyToOne
		@JoinColumn(name="grupo_codigo", nullable=false, insertable=false, updatable=false)
		private Grupo grupo;

		//bi-directional many-to-one association to Usuario
		@ManyToOne(cascade={CascadeType.ALL})
		@JoinColumn(name="usuario_codigo", nullable=false, insertable=false, updatable=false)
		private Usuario usuario;

		
		/**
		 * Constructor con campos
		 * @param id del miembro grupo que se forma por
		 *  la clave primaria de usuario y la clave primaria de grupo.
		 * @param grupo.
		 * @param usuario.
		 */
		public MiembroGrupo(MiembroGrupoPK id, Grupo grupo, Usuario usuario) {
			super();
			this.id = id;
			this.grupo = grupo;
			this.usuario = usuario;
		}
		/**
		 * Constructor por defecto.
		 */
		public MiembroGrupo() {
			super();
		}
		/**
		 * A continuacion se declaran todos los getter y setter de las variables.
		*/
		public MiembroGrupoPK getId() {
			return this.id;
		}
		
		public void setId(MiembroGrupoPK id) {
			this.id = id;
		}

		public Grupo getGrupo() {
			return grupo;
		}

		public void setGrupo(Grupo grupo) {
			this.grupo = grupo;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}


}
