package siagsce.viewmodel.seguridad;

import java.util.HashMap;
import java.util.Set;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeNode;
import org.zkoss.zul.Window;
import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.data.seguridad.Nodo;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
import siagsce.modelo.servicio.seguridad.ServicioNodo;
import siagsce.modelo.servicio.seguridad.ServicioUsuario;
/**
 * VMConfiguraOpcionesUsuario es el viewmodel encargado de modificar las
 * opciones de usuario asignadole o quitando grupos al usuario.
 * Nota:los grupos que son asignados o quitados no pertenecen
 *  a los grupos basicos(director, representante profesoral,
 *   profesor participante, reponsable de proyecto, integrante del SCE,..).
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConfiguraOpcionesUsuario {
	private @WireVariable ServicioNodo snodo;
	private @WireVariable ServicioUsuario su;
	private @WireVariable SProfesor sprofesor;
	private @WireVariable ServicioGrupo sg;
	@Wire
	private Window demoWindowConfigurarOpcionesUsuario;
	@Wire
	private Tree tree;
	private ListModelList<Grupo> modeloGruposPertenece;
	private ListModelList<Grupo> modeloGruposNoPertenece;
	private ListModelList<Nodo> modelonodos;
	private Set<Grupo> selectedGruposPertenece;
	private Set<Grupo> selectedGruposNoPertenece;
	private String cedulaUsuario;
	private String nombre;
    private String telefono;
    private String email;
    MensajesEmergentes mensajes= new MensajesEmergentes();
    /**
     * Muestra un catalogo de todos lo profesores guardados en la BD.
     * al seleccionar el profesor se carga todos sus datos,
     *  entre ello los grupos a los cuales pertenece y a los que no pertenece.
     */
	@NotifyChange({"cedulaUsuario","nombre","telefono","root","contactTreeModel,tree","email"})
	@Command
	public void buscarUsuario(){
		String ventana = "configurarOpcionesUsuario";
 		final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("profesor", ventana );
		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoProfesor.zul",
						null, map);
		comp.doModal();
		 Profesor profesor= (Profesor) comp
				.getAttribute("profesor");
		 if(profesor!=null){
		this.cedulaUsuario=profesor.getProfesorCedula();
        this.nombre=profesor.getProfesorNombre()+" "+profesor.getProfesorApellido();
		this.telefono=profesor.getProfesorTelefono();
		this.email=profesor.getProfesorEmail();
		this.cargarOpciones();
		 }
	}
	/**
	 * Guarda el usuario, actualizando la lista de roles
	 *  o grupos a los que pertenece.
	 */
	@NotifyChange({"cedulaUsuario","nombre","telefono","root","email"})
	@Command
	public void actualizarOpciones(){
		Usuario usuario=su.encontrarUsuarioPorNombreUsuario(cedulaUsuario);	
		usuario.getGrupos().clear();
		for(Grupo grupo:modeloGruposPertenece){
		usuario.addGrupo(grupo);
		}
		su.guardarUsuario(usuario);	
		mensajes.informacionActualizarDatos();
		cancelar();
	}
	/**
	 * Cierra la ventana.
	 */
	@Command
	public void salir(){
		demoWindowConfigurarOpcionesUsuario.detach();
	}
	/**
	 * Inicializa las variables
	 */
	@NotifyChange({"cedulaUsuario","nombre","telefono","root","email"})
	@Command
	public void cancelar(){
		int totalHijos=root.getChildCount();
		for(int i=0;i<totalHijos;i++){
			root.remove(0);
		}
		this.cedulaUsuario=null;
		this.nombre="";
		this.telefono="";
		this.email="";
		if(selectedGruposNoPertenece!=null){
		selectedGruposNoPertenece.clear();}
		if(selectedGruposPertenece!=null){
		selectedGruposPertenece.clear();}
		if(modeloGruposNoPertenece!=null){
		modeloGruposNoPertenece.clear();}
		if(modeloGruposPertenece!=null){
		modeloGruposPertenece.clear();}
		
	}
	
	/**
	 * Inicializa las variables,
	 * se ejecuto el metodo que permite la coneccion del viewmodel con la vista.
	 * @param demoWindowConfigurarOpcionesUsuario es la ventana con la cual esta enlazada el viewmodel.
	 */
	@AfterCompose 
	public void afterCompose(@ContextParam(ContextType.VIEW) Component demoWindowConfigurarOpcionesUsuario) {
		Selectors.wireComponents(demoWindowConfigurarOpcionesUsuario, this, false);
		root = new VMContactTreeNode(null,null);
		tree.setItemRenderer(new VMContactTreeRenderer());
		modeloGruposPertenece = new ListModelList<Grupo>();
		modeloGruposNoPertenece = new ListModelList<Grupo>();
		modeloGruposPertenece.setMultiple(true);
		modeloGruposNoPertenece.setMultiple(true);
	}
	/**
	 * Asigna nuevos grupos al usuario.
	*/
	@NotifyChange({"root","tree"})
	@Command
	public void asignarGrupos(){
		if(selectedGruposNoPertenece!=null){
			   if(!selectedGruposNoPertenece.isEmpty()){
				   modeloGruposPertenece.addAll(selectedGruposNoPertenece);
				   for(Grupo grupo:selectedGruposNoPertenece){
					   cargarGrupo(grupo);
				   }
				   modeloGruposNoPertenece.removeAll(selectedGruposNoPertenece);
				   selectedGruposNoPertenece.clear();
			   }else
				   mensajes.advertenciaSeleccionarOpcion();	
	
			}
		}
	/**
	 * Carga las nuevas funcionalidades del nuevo grupo al arbol.
	 * @param grupo contiene las nuevas funcionalidades del usuario
	 * que se van a cargar al arbol.
	 */
	public void cargarGrupo(Grupo grupo){
			VMContactTreeNode aux=null;
			for(Nodo a:grupo.getNodos()){	
			aux=new VMContactTreeNode(a);
			VMContactTreeNode ctreenodo= this.cargarPadre(aux);
			if(root.getChildCount()!=0){
				this.cargarNodos(ctreenodo,root);
				
				}else{
				root.add(ctreenodo);
				}	
			
			contactTreeModel = new VMAdvancedTreeModel(root);
			tree.setModel(contactTreeModel);
			}
	}
	/**
	 * Retira Grupos de la lista de grupos a los que pertenece el usuario.
	 */
	@NotifyChange({ "contactTreeModel,tree" })
	@Command
	public void sacarGrupos() {
		if(selectedGruposPertenece!=null){
			for(Grupo grupo:selectedGruposPertenece){	
					if(grupo.getIdGrupo().compareTo(1000)>0 && grupo.getIdGrupo().compareTo(1007)<0){
						selectedGruposPertenece.remove(grupo);
					}
				}
			
			   if(!selectedGruposPertenece.isEmpty()){
				   modeloGruposNoPertenece.addAll(selectedGruposPertenece);
				   for(Grupo grupo:selectedGruposPertenece){
						modeloGruposPertenece.remove(grupo);
							removerGrupoEnRoot(root,grupo);
							
				   }
					contactTreeModel = new VMAdvancedTreeModel(root);
					tree.setModel(contactTreeModel);
				   selectedGruposPertenece.clear();
			   }else
				   mensajes.advertenciaSeleccionarOpcion();	
	
			}
		
	}
	/**
	 *  Retira los nodos(funcionalidades) del arbol, 
	 *  que concuerden con el grupo.
	 *  Nota:Si la funcionalidad concuerda con 
	 *  una existente a un grupo base al cual pertenezca el usuario,
	 *  la funcion permanecera.
	 *  @param root arbol que contiene las funcionalides del arbol
	 *  dado el o los grupos a los cuales pertenezca el usuario.
	 *  @param grupo que contiene las funcionalidades que van a ser removidas
	 *  del arbol.
	 */
	private void removerGrupoEnRoot(TreeNode<Nodo> root,Grupo grupo){
		int t=root.getChildCount();
		int i=0;
			while(i<t){
				TreeNode<Nodo> a2=root.getChildAt(i);
				if(a2.getData().getTipo().equals("M")){
					System.out.println(a2.getData().getNombrefuncion());
					removerGrupoEnRoot(a2,grupo);
					if(a2.getChildCount()==0){
						VMContactTreeNode  a3=(VMContactTreeNode) a2;
						a3.removeFromParent();
						t--;
						continue;
					}
				}else{
					boolean removio=false;
					for(Nodo nodo:grupo.getNodos()){
						if(a2.getData().getId().compareTo(nodo.getId())==0){
						if(!this.verificarExistenciaPrevia(nodo)){
							VMContactTreeNode  a3=(VMContactTreeNode) a2;
							a3.removeFromParent();
							t--;
							removio=true;
						}	
						}
					}
					if(removio)
					continue;
				}
				i++;
			}
			
				
				
	}
	/**
	 *  Dado la cedula del profesor caraga todos los datos asociados,
	 *  juntamente con los grupos a los cuales pertenece y a los que no pertenece.
	 */
	@NotifyChange({ "contactTreeModel,tree","nombre","telefono","email" })
	@Command
	public void cargarOpciones(){
		Usuario usuario=su.encontrarUsuarioPorNombreUsuario(cedulaUsuario);
		if(usuario!=null){
			int totalHijos=root.getChildCount();
			for(int i=0;i<totalHijos;i++){
				root.remove(0);
			}
		Profesor profesor=sprofesor.buscarPorCedula(cedulaUsuario);
		 this.nombre=profesor.getProfesorNombre()+" "+profesor.getProfesorApellido();
		 this.telefono=profesor.getProfesorTelefono();
		 this.email=profesor.getProfesorEmail();
			
		modeloGruposPertenece.clear();
		modeloGruposNoPertenece.clear();
		List<Grupo> grupos=sg.buscarTodos();
		for(int i=0;i<grupos.size();i++){ 
			if(grupos.get(i).getIdGrupo().compareTo(1000)<0 || grupos.get(i).getIdGrupo().compareTo(1007)>0){
				modeloGruposNoPertenece.add(grupos.get(i));
			}
		}
		
		
		for(Grupo grupo:usuario.getGrupos()){
			modeloGruposPertenece.add(grupo);
			VMContactTreeNode aux=null;
			for(Nodo nodo:grupo.getNodos()){	
				aux=new VMContactTreeNode(nodo);
				VMContactTreeNode ctreenodo= this.cargarPadre(aux);
				if(root.getChildCount()!=0){
					this.cargarNodos(ctreenodo,root);	
				}else{
					root.add(ctreenodo);
				}	
			    }
			}
		int i=0,nroGruposPertenece=modeloGruposNoPertenece.size();
		
		while(i<nroGruposPertenece){
			boolean encontro=false;
		Grupo grupo2=modeloGruposNoPertenece.get(i);
			for(Grupo grupo:modeloGruposPertenece){
				if(grupo2.getIdGrupo().compareTo(grupo.getIdGrupo())==0){
					modeloGruposNoPertenece.remove(i);	
				    nroGruposPertenece--;
				    encontro=true;
				}
			}
			if(encontro){
				continue;}
			i++;
		}
		contactTreeModel = new VMAdvancedTreeModel(root);
		tree.setModel(contactTreeModel);
		}else{
			mensajes.advertenciaIngresarCedulaValido();	
		}
		
	}
	/**
	 *  Verifica que el nodo a eliminar no pertenezca
	 *   a un grupo base del usuario.
	 *   @param nodoAExtraer nodo al que se va a verificar la existencia previa.
	 *   @return true si existia el nodo.
	 */	
	private boolean verificarExistenciaPrevia(Nodo nodoAExtraer){
		boolean existia=false;
		for(Grupo grupo:su.encontrarUsuarioPorNombreUsuario(cedulaUsuario).getGrupos()){
			if(grupo.getIdGrupo().compareTo(1000)>0 && grupo.getIdGrupo().compareTo(1007)<0){	
			for(Nodo nodo:grupo.getNodos()){
				if(nodo.getId().compareTo(nodoAExtraer.getId())==0){
					existia=true;
					break;
				}
				}
			}
		}
		return existia;
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
 * A continuacion se declaran todos los getter y setter de las variables
 * para poder ser accedidas desde la vista(RegistrarGrupo.zul).
 */

public Set<Grupo> getSelectedGruposPertenece() {
	return selectedGruposPertenece;
}


public void setSelectedGruposPertenece(Set<Grupo> selectedGruposPertenece) {
	this.selectedGruposPertenece = selectedGruposPertenece;
}


public Set<Grupo> getSelectedGruposNoPertenece() {
	return selectedGruposNoPertenece;
}


public void setSelectedGruposNoPertenece(Set<Grupo> selectedGruposNoPertenece) {
	this.selectedGruposNoPertenece = selectedGruposNoPertenece;
}


public ListModelList<Grupo> getModeloGruposPertenece() {
	return modeloGruposPertenece;
}


public void setModeloGruposPertenece(ListModelList<Grupo> modeloGruposPertenece) {
	this.modeloGruposPertenece = modeloGruposPertenece;
}


public ListModelList<Grupo> getModeloGruposNoPertenece() {
	return modeloGruposNoPertenece;
}


public void setModeloGruposNoPertenece(
		ListModelList<Grupo> modeloGruposNoPertenece) {
	this.modeloGruposNoPertenece = modeloGruposNoPertenece;
}

public ServicioNodo getsnodo() {
	return snodo;
}


public void setsnodo(ServicioNodo snodo) {
	this.snodo = snodo;
}

private VMAdvancedTreeModel contactTreeModel;

private static VMContactTreeNode  root;


public Tree getTree() {
	return tree;
}


public void setTree(Tree tree) {
	this.tree = tree;
}


public VMAdvancedTreeModel getContactTreeModel() {
	return contactTreeModel;
}


public void setContactTreeModel(VMAdvancedTreeModel contactTreeModel) {
	this.contactTreeModel = contactTreeModel;
}

public void setRoot(VMContactTreeNode root) {
	this.root = root;
}


public VMContactTreeNode getRoot() {
	return root;
}
public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}	



public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}





public String getCedulaUsuario() {
	return cedulaUsuario;
}


public void setCedulaUsuario(String cedulaUsuario) {
	this.cedulaUsuario = cedulaUsuario;
}


public String getTelefono() {
	return telefono;
}


public void setTelefono(String telefono) {
	this.telefono = telefono;
}


public ListModelList<Nodo> getModeloGrupo() {
	return modelonodos;
}


public void setModeloGrupo(ListModelList<Nodo> modeloNodo) {
	this.modelonodos = modeloNodo;
}
	
}
