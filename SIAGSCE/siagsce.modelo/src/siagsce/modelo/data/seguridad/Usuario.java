
package siagsce.modelo.data.seguridad;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.mapping.Set;

import siagsce.modelo.general.Archivo;
/**
 * Modelo Usuario, representa el usuario que 
 * se le es asignado a los profesores para que ingresen al sistema.
 * @author Iterator
 */
@Entity
@Table(name = "usuario")
public class Usuario {
	
	//Definición de variables y campos en la base de datos.
	
	@Column(name="usuario_nombre",length = 20, nullable = false)
	private String nombreUsuario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "usuario_codigo", unique = true , nullable=false)
    private Integer idUsuario;
	
	@Column(name = "usuario_clave", nullable = false)
    private String clave;
	
	
	@Column(name = "usuario_status", length = 15, nullable = false)
    private String estado;
	
	//la foto es de tipo archivo, el archivo es una clase que contiene
	//la foto, el nombre, la extencion de la foto y el tamaño del archivo.
	@Embedded()
	private Archivo foto = new Archivo();
	
	//la foto es de tipo archivo, el archivo es una clase que contiene
	@ManyToMany(fetch=FetchType.EAGER) 
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "miembro_grupo",
	joinColumns = @JoinColumn(name = "usuario_codigo",referencedColumnName = "usuario_codigo"),
	inverseJoinColumns = @JoinColumn(name = "grupo_codigo", referencedColumnName = "grupo_codigo"))
    private List<Grupo>  grupos = new ArrayList<Grupo>();
	
	/**
	 * Constructor con campos.
	 * @param idUsuario ID del usuario.
	 * @param nombreUsuario es la cedula del profesor asociado al usuario .
	 * @param clave o password.
	 * @param estado(Activo o Inactivo). 
	 */
	public Usuario(Integer idUsuario,String nombreUsuario, String clave, String estado) {
		super();
		this.idUsuario=idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
		this.estado = estado;

	}
	/**
	 * Constructor con campos.
	 * @param idUsuario ID del usuario.
	 * @param nombreUsuario es la cedula del profesor asociado al usuario .
	 * @param clave o password.
	 * @param estado(Activo o Inactivo). 
	 * @param foto del usuario. 
	 */
	public Usuario(Integer idUsuario,String nombreUsuario, String clave, String estado,Archivo foto) {
		super();
		this.idUsuario=idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
		this.estado = estado;
		this.foto = foto;
	
	}

	/**
	 * Constructor por defecto.
	 */
	public Usuario() {
		super();
	}
	
	/**
	 * A continuacion se declaran todos los getter y setter de las variables.
	*/
	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

    public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
   

  public void addGrupo(Grupo grupo){
	  this.grupos.add(grupo);
  }
  
  public Archivo getFoto() {
		return foto;
	}

	public void setFoto(Archivo foto) {
		this.foto = foto;
	}

	
  
}

	
    

	
	
	

	