package siagsce.viewmodel.seguridad;

import java.util.HashMap;



import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Box;
import org.zkoss.zul.Center;
import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;
import siagsce.modelo.data.seguridad.Nodo;
/**
 * ViewModel encargado de gestionar(organizar y capturar los eventos de los nodos ) del arbol mostrado al iniciar sesion. 
 * @author Iterator
 */
public final class VMContactTreeRenderer3 implements TreeitemRenderer<VMContactTreeNode> {
	
	Window w=null;
	/**
	 * Metodo de la interfaz TreeitemRenderer encargada de gestionar la organizacion
	 * de los nodos del arbol asi como tambien
	 * los eventos que se generan en cada uno de ellos.
	 * @param treeItem representa el item que va a ser agregado en el arbol.
	 * @param treeNode representa la informacion que va a contener el nodo.
	 * @param index representa el indice donde va hacer colocado el nodo.
	 */
	@Override
	public void render(final Treeitem treeItem, VMContactTreeNode treeNode, int index) throws Exception {
		VMContactTreeNode ctn = treeNode;
		Nodo contact = (Nodo) ctn.getData();
		Treerow dataRow = new Treerow();
		dataRow.setParent(treeItem);
		treeItem.setValue(ctn);
		if(!contact.isModulo()){ 
			Hlayout hl = new Hlayout();
			hl.appendChild(new Label(contact.getNombrefuncion()));
			hl.setSclass("h-inline-block");
			Treecell treeCell = new Treecell();
			treeCell.appendChild(hl);
			dataRow.appendChild(treeCell);
			/**
			 * Evento que se ejecuta al hacer click al nodo
			 */
			dataRow.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					VMContactTreeNode clickedNodeValue = (VMContactTreeNode) ((Treeitem) event.getTarget().getParent())
							.getValue();
					if(w!=null){
						w.detach();
						}
						Executions.getCurrent().getDesktop().getFirstPage().getFellow("windowindex").getFellow("borderprincipal").getFellow("bordercenter").getFellow("divcenter").getFellow("boxcenter2").setVisible(false);
						Executions.getCurrent().getDesktop().getFirstPage().getFellow("windowindex").getFellow("borderprincipal").getFellow("bordercenter").getFellow("divcenter").getFellow("boxcenter").setVisible(true);
						Box box=(Box) Executions.getCurrent().getDesktop().getFirstPage().getFellow("windowindex").getFellow("borderprincipal").getFellow("bordercenter").getFellow("divcenter").getFellow("boxcenter");
						w=(Window) Executions.createComponents(clickedNodeValue.getData().getVinculo(),box,null);

							
				}
			});
		} else {
			dataRow.appendChild(new Treecell(contact.getNombrefuncion()));
			dataRow.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					if(treeItem.isOpen())
					treeItem.setOpen(false);
					else
						treeItem.setOpen(true);
				}
			});
		}		
				
	

			
	}

}

