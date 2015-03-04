package siagsce.viewmodel.seguridad;

import org.zkoss.zk.ui.event.Event;


import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import siagsce.modelo.data.seguridad.Nodo;

/**
 * ViewModel encargado de gestionar los eventos que se generan 
 * por el primer arbol de opciones en registrar grupo
 * (arbol que contiene todas las opciones).
 * @author Iterator
 */
public class VMContactTreeRenderer implements TreeitemRenderer<VMContactTreeNode> {
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
		treeItem.setOpen(ctn.isOpen());
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
					
					VMContactTreeNode padre=null;
					if(clickedNodeValue.getParent().getData()!=null){
							padre=obtenePadres((VMContactTreeNode)clickedNodeValue.getParent(),clickedNodeValue);
							}else{
								padre=clickedNodeValue;
							}
					VMRegistrarGrupo dc=new VMRegistrarGrupo();
					if(dc.getRoot2().getChildCount()==0)
						dc.getRoot2().add(padre);
					else{
					this.agregarNodo(padre,dc.getRoot2());
					}
				}

				
				/**
				 * Metodo que obtiene los padres del nodo que se le hizo click.
				 *  @param nodo en donde se realizo click.
				 * @param padre es el padre del nodo en donde se realizo click.
				 * @return la rama completa teniendo como 
				 * nodo terminal el nodo seleccionado 
				 *  
				 */
				private VMContactTreeNode obtenePadres(VMContactTreeNode padre,VMContactTreeNode hijo) {
					VMContactTreeNode aux=null;
					VMContactTreeNode padre2=null;
					if(padre.getParent().getData()!=null){
						if(padre.getChildCount()==1){
						aux=padre;	
						}else{
							aux = new VMContactTreeNode(padre.getData(),null);		
							aux.add(hijo);
						}
						padre2=obtenePadres((VMContactTreeNode)padre.getParent(),aux);
									
					}else{
						    padre2 = new VMContactTreeNode(padre.getData(),null);
						    padre2.add(hijo);
						    if(padre.getChildCount()==0)
						    padre.removeFromParent();
					}
					return padre2;
				}
				/**
				 * Dado un nodo hoja lo inserta en el arbol de acuerdo al padre.
				 * @param nodo representa el nodo hoja.
				 * @param root representa el arbol donde se va a insertar el nodo hoja.
				 */
				private void agregarNodo(VMContactTreeNode nodo,VMContactTreeNode root) { 
					boolean encontro=false;
					 for(int j=0;j< root.getChildCount();j++){
						    if(root.getChildAt(j).getData().getNombrefuncion().equals(nodo.getData().getNombrefuncion())){
						    	 for(int i=0;i< nodo.getChildCount();i++){
						    	 if(nodo.getChildCount()==1) 
						    	 agregarNodo((VMContactTreeNode) nodo.getChildAt(0),(VMContactTreeNode) root.getChildAt(j));
						    	 else{
						         VMContactTreeNode aux = new VMContactTreeNode(nodo.getChildAt(i).getData(),null);	
						    	 agregarNodo(aux,(VMContactTreeNode) root.getChildAt(j));	 
						    	 }
						    	 }
						    	encontro=true;
						    }
					 }
					 if(!encontro)
						 root.add(nodo);
						 
				
				}
				
			});
			

			
	}

}
