package siagsce.viewmodel.seguridad;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.data.seguridad.Nodo;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
import siagsce.modelo.servicio.seguridad.ServicioNodo;
/**
 * VMRegistrarGrupo es el viewmodel encargado de gestionar
 *  el registro de los diferentes grupos o roles que se van a menejar en el sistema 
 *  asi como tambien editar los ya existentes.
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarGrupo {
	@Wire
	private Window demoWindowRegistrarGrupo;
	@Wire
	private Tree tree;
	@Wire
	private Button btnGuardarModificarGrupo;
	@Wire
	private Button btnGuardarRegistrarGrupo;
	@Wire
	private Tree tree2;
	
	private @WireVariable ServicioNodo snodo;
	private @WireVariable ServicioGrupo sg;
	private VMAdvancedTreeModel contactTreeModel;
	private VMAdvancedTreeModel contactTreeModel2;
	private static VMContactTreeNode  root;
	private static VMContactTreeNode  root2;
	private String nombre="";
    private String descripcion="";
    private String estado;
	private ListModelList<Nodo> modelonodos;
	Grupo grupo=null;
	MensajesEmergentes mensajes= new MensajesEmergentes();

	/**
	 * Guarda o modifica el grupo dependiendo si el id 
	 * del grupo existia o no en la BD, el grupo se guarda con 
	 * todas las funcionalidades que se le han asignado a dicho grupo.
	 * Nota:esas funcionalidades son vistas a traves del arbol.
	 	 */
	@NotifyChange({"root","root2","tree","tree2","descripcion","nombre","idGrupo"})
	@Command
	public void guardarGrupo(){
		if(nombre!="" && descripcion!=""){
		if(root2.getChildCount()!=0){
		Grupo grupo=sg.buscarGrupoNombre(nombre);
		if(grupo==null){
			grupo=new Grupo(nombre,descripcion,"Activo");
			if(root2.getChildCount()>0){
				for(TreeNode<Nodo> a2:root2.getChildren()){
				cargarHijosGrupo(a2,grupo);
				}	
			}
			sg.guardarGrupo(grupo);
			mensajes.informacionRegistroCorrecto();
		}else{
			mensajes.advertenciaNoSeEfectuoModificacion();
		}
		cancelar();
		}else{
			mensajes.advertenciaSeleccionFuncionArbol();
		}
		}else{
			mensajes.advertenciaLlenarCampos();
		}
	}
	/**
	 * Cierra la ventana.
	 	 */
	@Command
	public void salir(){
		demoWindowRegistrarGrupo.detach();
	}
	/**
	 * Muestra el catalogo de los grupos que se encuentran registrados, 
	 * para realizar futuras modificaciones agregandole o 
	 * quitandole funcionalidades al grupo si es necesario.
	 	 */
	@SuppressWarnings("unchecked")
	@Command
	@NotifyChange({ "descripcion","nombre","idGrupo","root","root2","tree2","tree" })
	public void mostrarCatalogoGrupos(
			@ContextParam(ContextType.VIEW) Component view) {
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoGrupo.zul",
						null, null);
		comp.doModal();
		 grupo= (Grupo) comp
				.getAttribute("grupo");
		 if(grupo!=null){
		this.descripcion=grupo.getDescripcion();
		this.nombre=grupo.getNombre();
		cargarGrupo();
		 }
		

	}
	/**
	 * Inicializa las variables y desactiva el boton de modificar,
	 * carga el arbol de inicio con todas sus funcionalidades
	 * se ejecuto el metodo que permite la coneccion del viewmodel con la vista.
	 * @param demoWindowRegistrarGrupo es la ventana con la cual esta enlazada el viewmodel.
	 * 	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component demoWindowRegistrarGrupo) {
		Selectors.wireComponents(demoWindowRegistrarGrupo, this, false);
		btnGuardarModificarGrupo.setDisabled(true);
		cargarArbol();	
		contactTreeModel = new VMAdvancedTreeModel(root);
		root2 = new VMContactTreeNode(null,null);
		contactTreeModel2 = new VMAdvancedTreeModel(root2);
		tree.setItemRenderer(new VMContactTreeRenderer());
		tree2.setItemRenderer(new VMContactTreeRenderer2());
		tree.setModel(contactTreeModel);
		tree2.setModel(contactTreeModel2);
	}
	/**
	 * Carga el arbol con todas las funcionalidades posibles,
	 *  lista para ser asignadas a nuevos grupos.
	 	 */
	public void cargarArbol(){
		VMContactTreeNode aux=null;
		root = new VMContactTreeNode(null,null);
		for(Nodo a:snodo.buscarPadre(0)){
			aux=new VMContactTreeNode(a,null);
		    this.cargarHijos(aux,a);
			root.add(aux);
		    }
		quitarNodoModuloSinHijos(root);
		  
	}
	
	
	/**
	 * Quita del arbol 1(root1) los que concuerde con el arbol 2(root2)
	 * @param root1 representa el arbol de donde se quitaran los nodos que coincidan con el arbol 2(root2)
	 * @param root2 representa las hojas que van hacer quitados del arbol 1. 
	 * 	 	 */	
	private void quitarNodoModuloSinHijos(VMContactTreeNode root1) { 
		 for(int i=0;i< root1.getChildCount();i++){
				  if(root1.getChildAt(i).getData().isModulo()){
					  if(root1.getChildAt(i).getChildCount()!=0){
						  quitarNodoModuloSinHijos((VMContactTreeNode)root1.getChildAt(i));
						  if(root1.getChildAt(i).getChildCount()==1){
								if(root1.getChildAt(i).getChildAt(0).getData().getTipo().equals("M")){
									 root1.remove(i);
								}
							  }
					      VMContactTreeNode aux=(VMContactTreeNode)root1.getChildAt(i);
					  if(aux!=null){
					  if(aux.getChildCount()==0)
						  aux.removeFromParent();
					  }
					  }
					  else{
						  root1.remove(i);
						  if(root1.getChildCount()==0){
							 root1.removeFromParent(); 
						  }
						  
					 }
				  }
			 }  
		 }
		
	

	/**
	 * Agrega al arbol el nodo a1 con todos sus nodos hijos respectivos.
	 * ese nodo se agrega a m1 que representa el arbol en contrucción.
	 * @param m1 representa el arbol donde sera agragado a1
	 * @param a1 representa el nodo que va hacer agregado al arbol
	 * 
	 	 */
	public void cargarHijos(VMContactTreeNode m1, Nodo a1) {
		VMContactTreeNode m2 = null;
			for(Nodo a2:snodo.buscarPadre(a1.getId())){
			if(a2.getTipo().equals("M"))
		    m2= new VMContactTreeNode(a2,null);
			else{
			m2= new VMContactTreeNode(a2);
			}
			if(!a2.getTipo().equals("M") && a2.getCategoria()==1){
		    m1.add(m2);
			}else if(a2.getTipo().equals("M")){
			m1.add(m2);
			}
		    if(!snodo.buscarPadre(a2.getId()).isEmpty())
		    cargarHijos(m2,a2);
			}
	}
	/**
	 * Agrega las funcionalidades al grupo
	 * dicha funcionalidades son obtenidas del arbol 
	 * que se construyo previamente.
	 * @param root representa el arbol de donde se obtendran las funcionalidades
	 * @param grupo representa el grupo en donde se almacenaran las funcionalidades 
	 * 	 	 */
	public void cargarHijosGrupo(TreeNode<Nodo> root,Grupo grupo) {
		if(root.getChildCount()>0){
			for(TreeNode<Nodo> a2:root.getChildren()){
				if(a2.getData().getTipo().equals("M")){
					cargarHijosGrupo(a2,grupo);
				}else{
					grupo.addNodos(a2.getData());
					}
			}
		
			}
	}
	/**
	 * Dado un id de grupo carga todos sus datos 
	 * juntamente con todas sus funcionalidades.
	 * 	 	 */
	@NotifyChange({"root","root2","tree2","tree","nombre","descripcion"})
	@Command
	public void cargarGrupo(){
		if(nombre!=null){
		if(grupo!=null){
            if(!nombre.equals("Basico") && !nombre.equals("Administrador") && !nombre.equals("Coordinacion SCE") && !nombre.equals("Representante Profesoral") && !nombre.equals("Responsable Proyecto") && !nombre.equals("Participante Actividad") && !nombre.equals("Administrador 2") && !nombre.equals("Director")){
            	btnGuardarModificarGrupo.setDisabled(false);
    			btnGuardarRegistrarGrupo.setDisabled(true);
			}else{
				btnGuardarModificarGrupo.setDisabled(true);
				btnGuardarRegistrarGrupo.setDisabled(true);
			}
			this.descripcion=grupo.getDescripcion();
			this.nombre=grupo.getNombre();
			
			cargarArbol();	
			VMContactTreeNode aux=null;
			root2 = new VMContactTreeNode(null,null);
			for(Nodo a:grupo.getNodos()){	
			aux=new VMContactTreeNode(a);
			VMContactTreeNode ctreenodo= this.cargarPadre(aux);
			if(root2.getChildCount()!=0){
				this.cargarNodos(ctreenodo,root2);				
				}else{
				root2.add(ctreenodo);
				}	
		    }
			contactTreeModel2 = new VMAdvancedTreeModel(root2);
		
			tree2.setModel(contactTreeModel2);
			
			this.quitarNodo(root,root2);
			
			contactTreeModel = new VMAdvancedTreeModel(root);
			tree.setModel(contactTreeModel);
			}else{
				if(root2.getChildCount()!=0){
				cargarArbol();	
				int nrohijos=root2.getChildCount();
				for(int i=0;i<nrohijos;i++){
					root2.remove(0);
				}	
				contactTreeModel = new VMAdvancedTreeModel(root);
				contactTreeModel2 = new VMAdvancedTreeModel(root2);
				tree.setModel(contactTreeModel);
				tree2.setModel(contactTreeModel2);
				}
			}
		}	
	}		
	/**
	 * Quita del arbol 1(root1) los que concuerde con el arbol 2(root2)
	 * @param root1 representa el arbol de donde se quitaran los nodos que coincidan con el arbol 2(root2)
	 * @param root2 representa las hojas que van hacer quitados del arbol 1. 
	 * 	 	 */	
	private void quitarNodo(VMContactTreeNode root1,VMContactTreeNode root2) { 
		 for(int i=0;i< root1.getChildCount();i++){
			 for(int j=0;j< root2.getChildCount();j++){
				 if(root1.getChildAt(i)!=null){
				  if(root1.getChildAt(i).getData().getId().compareTo(root2.getChildAt(j).getData().getId())==0){
					  if(root1.getChildAt(i).getChildCount()!=0){
					  quitarNodo((VMContactTreeNode)root1.getChildAt(i),(VMContactTreeNode)root2.getChildAt(j));
					  VMContactTreeNode aux=(VMContactTreeNode)root1.getChildAt(i);
					  if(aux!=null){
					  if(aux.getChildCount()==0)
						  aux.removeFromParent();
					  }
					  }
					  else{
						  root1.remove(i);
						  if(root1.getChildCount()==0){
							 root1.removeFromParent(); 
						  }
						  
					 }
				  }
			    }
			 }
		 }
		
			 
	
	}

	/**
	 * Dado un nodo hoja busca todos sus ancestros, es decir
	 * genera la rama completa del arbol dado una hoja.
	 * @param nodo representa el nodo terminal
	 * @return retorna el nodo junto con sus ancestros 	 */
public VMContactTreeNode cargarPadre(VMContactTreeNode nodo) {
	VMContactTreeNode padre=null;
	if(!(nodo.getData().getPadre().compareTo(0)==0)){
			Nodo npadre=snodo.buscarNodo(nodo.getData().getPadre());
			padre=new VMContactTreeNode(npadre,null);
			padre.add(nodo);
			nodo=cargarPadre(padre);
			}
		return nodo;
}
/**
 * Dado un nodo hoja lo inserta en el arbol de acuerdo al padre.
 * @param nodo representa el nodo hoja.
 * @param root representa el arbol donde se va a insertar el nodo hoja.
 */
private void cargarNodos(VMContactTreeNode nodo,VMContactTreeNode root) { 
	boolean encontro=false;
	 for(int j=0;j< root.getChildCount();j++){
		  if(root.getChildAt(j).getData().getId().compareTo(nodo.getData().getId())==0){
			  for(int i=0;i< nodo.getChildCount();i++){
			    if(nodo.getChildCount()==1)
				cargarNodos((VMContactTreeNode)nodo.getChildAt(0),(VMContactTreeNode) root.getChildAt(j));  
			    else{
			    	 VMContactTreeNode aux = new VMContactTreeNode(nodo.getChildAt(i).getData(),null);
			         cargarNodos(aux,(VMContactTreeNode) root.getChildAt(j));
			     }
			  }
			    encontro=true;
		    }
	 }
	 if(!encontro)
		 root.add(nodo);
		 

}
/**
 * Inicializa las variables
 *  */
@NotifyChange({"root","root2","tree","tree2","descripcion","nombre","idGrupo"})
@Command
public void cancelar(){
	btnGuardarModificarGrupo.setDisabled(true);
	btnGuardarRegistrarGrupo.setDisabled(false);
	int nrohijos=root2.getChildCount();
	for(int i=0;i<nrohijos;i++){
		root2.remove(0);
	}	
	grupo=null;
	contactTreeModel2 = new VMAdvancedTreeModel(root2);
	tree2.setModel(contactTreeModel2);
	this.descripcion="";
	this.nombre="";
	cargarArbol();	
	contactTreeModel = new VMAdvancedTreeModel(root);
	tree.setModel(contactTreeModel);	
}
/**
 * A continuacion se declaran todos los getter y setter de las variables
 * para poder ser accedidas desde la vista(RegistrarGrupo.zul).
 */
public ServicioNodo getsnodo() {
	return snodo;
}


public void setsnodo(ServicioNodo snodo) {
	this.snodo = snodo;
}


public Tree getTree() {
	return tree;
}


public void setTree(Tree tree) {
	this.tree = tree;
}


public Tree getTree2() {
	return tree2;
}


public void setTree2(Tree tree2) {
	this.tree2 = tree2;
}


public VMAdvancedTreeModel getContactTreeModel() {
	return contactTreeModel;
}


public void setContactTreeModel(VMAdvancedTreeModel contactTreeModel) {
	this.contactTreeModel = contactTreeModel;
}


public VMAdvancedTreeModel getContactTreeModel2() {
	return contactTreeModel2;
}


public void setContactTreeModel2(VMAdvancedTreeModel contactTreeModel2) {
	this.contactTreeModel2 = contactTreeModel2;
}


public VMContactTreeNode getRoot2() {
	return root2;
}


public void setRoot2(VMContactTreeNode root2) {
	this.root2 = root2;
}


public void setRoot(VMContactTreeNode root) {
	this.root = root;
}


public VMContactTreeNode getRoot() {
	return root;
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


public ListModelList<Nodo> getModeloGrupo() {
	return modelonodos;
}


public void setModeloGrupo(ListModelList<Nodo> modeloNodo) {
	this.modelonodos = modeloNodo;
}
	
}
