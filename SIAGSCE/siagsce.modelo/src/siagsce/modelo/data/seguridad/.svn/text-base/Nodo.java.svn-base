package siagsce.modelo.data.seguridad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Modelo de Nodo, representa los diferentes nodos(funcionalidades) del arbol
 * que se muestran a cada profesor dependiendo del rol que posean.
 * @author Iterator
 */
@Entity
@Table(name = "nodo")
public class Nodo {
	
	//Definición de variables y campos en la base de datos.
	@Id
	@Column(name="nodo_codigo", unique = true , nullable=false)
	private Integer id;
	
	@Column(name="nodo_status", length = 15, nullable = false)
	private String estado;
	
	
	@Column(name="nodo_tipo", nullable = false)
	private String tipo;
	
	@Column(name="nodo_funcion", nullable = false)
    private String nombrefuncion;
	
	@Column(name="nodo_padre", nullable = false)
	private Integer padre;
	
	@Column(name="nodo_vinculo")
    private String vinculo;
   
	@Column(name="nodo_prioridad", nullable = false)
    private int prioridad;
	
	@Column(name="nodo_categoria", nullable = false)
    private int categoria;
    
	
	/**
	 * Constructor por defecto
	 */
	public Nodo() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor con campos.
	 * @param id clave principal.
	 * @param tipo de nodo(Modulo o Funcion).
	 * @param nombrefuncion label que se muestra en el arbol.
	 * @param vinculo contiene la ruta donde se encuentra el archivo .zul.
	 * @param estado(Activo o Inactivo).
	 * @param padre id del nodo padre.
	 * @param prioridad numero entero que denota la posicion en el arbol. 
	 */
	public Nodo(Integer id, String tipo, String nombrefuncion,
			String vinculo, String estado, Integer padre,int prioridad) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nombrefuncion = nombrefuncion;
		this.vinculo = vinculo;
		this.estado = estado;
		this.padre = padre;
		this.prioridad =prioridad;
	}
	/**
	 * A continuacion se declaran todos los getter y setter de las variables.
	*/
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Nodo(Integer id, String estado, String tipo, String nombrefuncion,
			Integer padre, String vinculo, int prioridad, int categoria) {
		super();
		this.id = id;
		this.estado = estado;
		this.tipo = tipo;
		this.nombrefuncion = nombrefuncion;
		this.padre = padre;
		this.vinculo = vinculo;
		this.prioridad = prioridad;
		this.categoria = categoria;
	}
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getNombrefuncion() {
		return nombrefuncion;
	}
	public void setNombrefuncion(String nombrefuncion) {
		this.nombrefuncion = nombrefuncion;
	}
	
	
	public Integer getPadre() {
		return padre;
	}
	
	public void setPadre(Integer padre) {
		this.padre = padre;
	}
	
	
	public String getVinculo() {
		return vinculo;
	}
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}
	 
    public int getPrioridad() {
			return prioridad;
		}

	public void setPrioridad(int prioridad) {
			this.prioridad = prioridad;
		}
	
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	/**
	 * Retorna true si el nodo es de tipo modulo.
	*/
	public boolean isModulo() {
		return tipo.equals("M");
	}
	
}

