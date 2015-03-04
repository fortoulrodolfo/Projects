package siagsce.viewmodel.seguridad;

import java.io.FileInputStream;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Box;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Window;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.data.seguridad.Nodo;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.general.UtilidadesSiagsce;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
import siagsce.modelo.servicio.seguridad.ServicioNodo;
import siagsce.modelo.servicio.seguridad.ServicioUsuario;
/**
 * ViewModel que gestiona la vista PortalAplicacion.zul(vista que se observa
 * cuando el profesor es logeado).
 *  @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMPortalAplicacion{
	
		
	private VMAdvancedTreeModel contactTreeModel;
	private static VMContactTreeNode  rootPortalInicial;
	private @WireVariable ServicioNodo snodo;
	private @WireVariable ServicioGrupo sg;
	private @WireVariable ServicioUsuario su;
	private @WireVariable SProfesor sprofesor;
	private AImage imagen;
	@Wire
	private Window windowindex;
	@Wire
	private Tree tree;
	@Wire
	private Box boxcenter;
	private String nombreUsuario;
	Usuario u=null;
   
	/**
	 * Inicializa las variables(arbol donde se mostraran las funcionalidades 
	 * del usuario,datos del profesor).
	 * @exception Exception
	 */
	@Init
	public void Init(Component comp) throws Exception {
		rootPortalInicial = new VMContactTreeNode(null,null);
		SecurityUtil.setNombreUsuario();
		SecurityUtil.asignarRoles();
		Profesor p=sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
		nombreUsuario=p.getProfesorNombre()+" "+p.getProfesorApellido();
		Usuario u=su.encontrarUsuarioPorNombreUsuario(SecurityUtil.nombreUsuario);
		if(!u.getFoto().getNombreArchivo().equals(""))
		imagen=new AImage(u.getFoto().getNombreArchivo(), u.getFoto().getContenido());
		else{
			UtilidadesSiagsce util=new UtilidadesSiagsce();
			FileInputStream myStream = new FileInputStream(Sessions.getCurrent().getWebApp().getRealPath("/images/contact.gif"));
			byte[] imageInBytes = IOUtils.toByteArray(myStream);
			  imagen= new  AImage("foto",imageInBytes);
		}

		
		for(String rol:SecurityUtil.roles){
			Grupo g=sg.buscarGrupoNombre(rol);
			VMContactTreeNode aux=null;
			for(Nodo a:g.getNodos()){	
				aux=new VMContactTreeNode(a);
				VMContactTreeNode ctreenodo= this.cargarPadre(aux);
				if(rootPortalInicial.getChildCount()!=0){
					this.cargarNodos(ctreenodo,rootPortalInicial);
					
				}else{
					rootPortalInicial.add(ctreenodo);
				}	
			    }
			}	

		contactTreeModel = new VMAdvancedTreeModel(rootPortalInicial);
				
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
	 * Inicializa las variables(arbol),
	 * se ejecuto el metodo que permite la coneccion del viewmodel con la vista.
	 * @param windowindex es la ventana con la cual esta enlazada el viewmodel.
	 */
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component windowindex) {
		Selectors.wireComponents(windowindex, this, false);
		tree.setItemRenderer(new VMContactTreeRenderer3());
		tree.setModel(contactTreeModel);
		}
	/**
	 * Metodo que permite actualizar los datos 
	 * del profesor que inicio sesion.
	 * @exception IOException
	 */
	@Command
	@NotifyChange({ "imagen","nombreUsuario"})
	public void editarPerfil() throws IOException{
	
	Window b=(Window) Executions.createComponents("/WEB-INF/vista/view/view.maestros/EditarPerfil.zul",null,null);
	b.setPosition("center,top");
	       b.doModal();
	       u=su.encontrarUsuarioPorNombreUsuario(SecurityUtil.nombreUsuario);
	       if(!u.getFoto().getNombreArchivo().equals("")){
	    	   imagen=new AImage(u.getFoto().getNombreArchivo(), u.getFoto().getContenido());
	       }
	       Profesor profesor = sprofesor.buscarPorCedula(u.getNombreUsuario());
	       nombreUsuario=profesor.getProfesorNombre() +" "+profesor.getProfesorApellido();
	       
	}
	/**
	 * A continuacion se declaran todos los getter y setter de las variables
	 * para poder ser accedidas desde la vista(RegistrarGrupo.zul).
	 */
	 public String getNombreUsuario() {
			return nombreUsuario;
		}


		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}


		public AImage getImagen() {
			return imagen;
		}


		public void setImagen(AImage imagen) {
			this.imagen = imagen;
		}
	    public VMAdvancedTreeModel getContactTreeModel() {
			return contactTreeModel;
		}


		public void setContactTreeModel(VMAdvancedTreeModel contactTreeModel) {
			this.contactTreeModel = contactTreeModel;
		}
		public static VMContactTreeNode getRootPortalInicial() {
			return rootPortalInicial;
		}


		public static void setRootPortalInicial(VMContactTreeNode rootPortalInicial) {
			VMPortalAplicacion.rootPortalInicial = rootPortalInicial;
		}

	
}
