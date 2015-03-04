package siagsce.modelo.data.seguridad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
/**
 * Modelo de Grupo, describe los diferentes roles
 * que van a tener los profesores al entrar en el sistem .
 * @author Iterator
 */
@Entity
@Table(name = "grupo")
public class Grupo {

	//Definición de variables y campos en la base de datos.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "grupo_codigo", unique = true, nullable = false)
	private Integer idGrupo;

	@Column(name = "grupo_nombre",length = 50, nullable = false)
	private String nombre;

	@Column(name = "grupo_descripcion", nullable = false)
	private String descripcion;

	@Column(name = "grupo_status", length = 15, nullable = false)
	private String estado;

	//Un grupo tiene muchos nodos(funcionalidades) y un nodo puede estar en muchos grupos.
    //por eso se genera la tabla grupo_funcion
	// en la variable nodos se almacenan todos los nodos del grupo
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "grupo_funcion", joinColumns = { @JoinColumn(name = "grupo_codigo", referencedColumnName = "grupo_codigo") }, inverseJoinColumns = { @JoinColumn(name = "nodo_codigo", referencedColumnName = "nodo_codigo") })
	private List<Nodo> nodos = new ArrayList<Nodo>();

	// bi-directional many-to-one association to MiembroGrupo
	@OneToMany(mappedBy = "grupo")
	private List<MiembroGrupo> miembrosGrupos = new LinkedList<MiembroGrupo>();
	/**
	 * Constructor con campos
	 * @param idGrupo id del grupo.
	 * @param nombre del gripo.
	 * @param descripcion del grupo.
	 * @param estado del grupo.
	 */
	public Grupo(String nombre, String descripcion,
			String estado) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;

	}
	/**
	 * Constructor con campos
	 * @param idGrupo id del grupo.
	 * @param nombre del gripo.
	 */
	public Grupo(Integer idGrupo, String nombre) {
		super();
		this.idGrupo = idGrupo;
		this.nombre = nombre;
	}
	/**
	 * Constructor por defecto
	 */
	public Grupo() {
		super();
		nodos = new ArrayList<Nodo>();
	}

	/**
	 * Metodo para ordenar los nodos del grupo dado la prioridad.
	*/
	private void ordenarNodosPrioridad(){
		Collections.sort(this.nodos, new Comparator<Nodo>() {
			@Override
			public int compare(Nodo p1, Nodo p2) {
				return new Integer(p1.getPrioridad()).compareTo(new Integer(p2.getPrioridad()));
			}

		});
	}
	/**
	 * A continuacion se declaran todos los getter y setter de las variables.
	*/
	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<Nodo> getNodos() {
		ordenarNodosPrioridad();
		return nodos;
	}

	public void setNodos(List<Nodo> nodos) {
		this.nodos = nodos;
	}

	public void addNodos(Nodo nodo) {
		this.nodos.add(nodo);
	}

	@OneToMany(fetch = FetchType.LAZY)
	public List<MiembroGrupo> getMiembrosGrupos() {
		return this.miembrosGrupos;
	}

	public void setMiembrosGrupos(List<MiembroGrupo> miembrosGrupos) {
		this.miembrosGrupos = miembrosGrupos;
	}

	public MiembroGrupo addMiembroGrupo(MiembroGrupo miembrosGrupos) {
		getMiembrosGrupos().add(miembrosGrupos);
		miembrosGrupos.setGrupo(this);
		return miembrosGrupos;
	}

	public MiembroGrupo removeUsuarioGrupo(MiembroGrupo miembrosGrupos) {
		getMiembrosGrupos().remove(miembrosGrupos);
		miembrosGrupos.setGrupo(null);

		return miembrosGrupos;
	}

}
