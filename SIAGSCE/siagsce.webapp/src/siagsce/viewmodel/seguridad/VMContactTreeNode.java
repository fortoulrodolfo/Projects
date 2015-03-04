package siagsce.viewmodel.seguridad;

import java.util.List;


import org.zkoss.zul.DefaultTreeNode;
import siagsce.modelo.data.seguridad.Nodo;

/**
 * ViewModel para gestionar los nodos del arbol.
 * 
 * @author Iterator
 */
public class VMContactTreeNode extends DefaultTreeNode<Nodo> {
	private static final long serialVersionUID = -7012663776755277499L;
	
	private boolean open = false;

	/**
	 * Constructor para crear un nodo con hijos.
	 * @param data representa el nodo nuevo.
	 * @param children representa los hijos del nodo
	 */
	public VMContactTreeNode(Nodo data, DefaultTreeNode<Nodo>[] children) {
		super(data, children);
	}
	/**
	 * Constructor para crear un nodo con hijos y define si va a estar abierto o cerrado.
	 * @param data representa el nodo nuevo.
	 * @param children representa los hijos del nodo
	 * @param open true si deseamos que el nodo este abierto
	 * 
	 */
	public VMContactTreeNode(Nodo data, DefaultTreeNode<Nodo>[] children, boolean open) {
		super(data, children);
		setOpen(open);
	}
	/**
	 * Constructor para crear un nodo.
	 * @param data representa el nodo nuevo.
	 */
	public VMContactTreeNode(Nodo data) {
		super(data);

	}
	/**
	 * Metodo para saber si un nodo esta abierto o cerrado.
	 * @return true si el nodo esta abierto
	 */
	public boolean isOpen() {
		return open;
	}
	/**
	 * Metodo asignarle la propiedad de abierto al nodo.
	 * @return true si el nodo esta abierto
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}



}
