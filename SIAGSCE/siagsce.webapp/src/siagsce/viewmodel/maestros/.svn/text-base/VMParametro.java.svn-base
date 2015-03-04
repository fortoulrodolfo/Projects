package siagsce.viewmodel.maestros;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Parametro;
import siagsce.modelo.data.maestros.Taller;
import siagsce.modelo.servicio.maestros.SParametro;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)

//ViewModel para guardar, modificar y eliminar un parametro
public class VMParametro {

	@Wire
	Window win;
	
	@Wire
	private Button btnGuardar;
	
	@Wire
	private Button btnEditar;
	
	@Wire
	private Textbox txtNombre;
	
	@Wire
	private Textbox txtDescripcion;
	
	@Wire
	private Textbox txtValor;
	
	//Declaracion de los servicios a usar
	private @WireVariable SParametro sparametro;

	//Declaracion de variables comunes
	private String nombre, descripcion,  valor;
	private Parametro selectedParametro;
	
	//Set y Get
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
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public Parametro getSelectedParametro() {
		return selectedParametro;
	}
	public void setSelectedParametro(Parametro selectedParametro) {
		this.selectedParametro = selectedParametro;
	}
	
	//====================================================================
	//								METODOS
	//====================================================================
	
	
	//Metodo que se ejecutara al momento de la carga de la ventana por primera vez
	
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;	
		MensajeBox.doSetTemplate();
	}
	
	//metodo que permite acceder a los componentes de la vista e inicializar los necesarios
		@AfterCompose
		public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
			Selectors.wireComponents(view, this, false);
			btnEditar.setDisabled(true);
		}
	
	//Metodo que muestra el catalogo de taller.
 	@Command
 	@SuppressWarnings({ "unchecked", "rawtypes" })
 	 @NotifyChange({"nombre", "descripcion", "valor", "selectedParametro"})
    public void MostrarCatalogoParametros()
    {
 		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/CatalogoParametros.zul",
						null, null);
 		comp.doModal();
 		selectedParametro =  (Parametro)comp.getAttribute("parametro");
 		if(selectedParametro != null){
 		nombre = selectedParametro.getParametroNombre();
 		descripcion = selectedParametro.getParametroDescripcion();
 		if(selectedParametro.getParametroInterger() != null){
 			valor = selectedParametro.getParametroInterger().toString();
 		}
 		else valor = selectedParametro.getParametroString();
 		Deshabilitar();
 		}
 		
    }
 	
 	public void Deshabilitar(){
 		btnGuardar.setDisabled(true);
 		btnEditar.setDisabled(false);
 		txtNombre.setReadonly(true);
 		txtDescripcion.setReadonly(true);
 		txtValor.setReadonly(true);
 	}
 	
 	@Command
 	public void Editar(){
 		btnGuardar.setDisabled(false);
 		btnEditar.setDisabled(true);
 		txtNombre.setReadonly(true);
 		txtDescripcion.setReadonly(false);
 		txtValor.setReadonly(false);
 	}
	
	//Metodo que guarda el motivo
	@Command
	@NotifyChange({  "nombre", "descripcion","valor"})
	public void guardar(){
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		if (nombre == "" || descripcion == "" || valor == "" ){
			mensajeEmergente.advertenciaLlenarCampos();
		}
		else if (selectedParametro == null){
			if(esNumerico(valor)){
				Parametro param = new Parametro (nombre, descripcion, Integer.parseInt(valor), null);
				sparametro.guardar(param);
				limpiar();
				mensajeEmergente.informacionRegistroCorrecto();
			}
			else{
			Parametro param = new Parametro (nombre, descripcion, null, valor);
			sparametro.guardar(param);
			limpiar();
			mensajeEmergente.informacionRegistroCorrecto();
		}
		}
		else{
			if(esNumerico(valor)){
				selectedParametro.setParametroNombre(nombre);
				selectedParametro.setParametroDescripcion(descripcion);
				selectedParametro.setParametroInterger(Integer.parseInt(valor));
				selectedParametro.setParametroString(null);
				sparametro.guardar(selectedParametro);
				limpiar();
				mensajeEmergente.informacionActualizarDatos();
			}
			else{
				selectedParametro.setParametroNombre(nombre);
				selectedParametro.setParametroDescripcion(descripcion);
				selectedParametro.setParametroString(valor);
				selectedParametro.setParametroInterger(null);
				sparametro.guardar(selectedParametro);
				limpiar();
				mensajeEmergente.informacionActualizarDatos();
			}
		}	
	}
	
	//Metodo que valida si el valor del parametro es numerico o alfanumerico
	public boolean esNumerico(String valor){
		
		int i = 0;
		if (valor.charAt(0) == '-'){
			if(valor.length() > 1){
				i++;
			}
			else return false;
		}
		for (; i< valor.length(); i++){
			if (!Character.isDigit(valor.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	//Metodo que limpia los campos de la ventana
	@Command
	@NotifyChange({  "nombre", "descripcion","valor","selectedParametro"})
	public void limpiar(){
		
		nombre = "";
		descripcion = "";
		valor = "";
		selectedParametro = null;
		btnGuardar.setDisabled(false);
		btnEditar.setDisabled(true);
		txtNombre.setReadonly(false);
		txtDescripcion.setReadonly(false);
		txtValor.setReadonly(false);
	}
	
	//Metodo que limpia los campos de la ventana
	@Command
	public void salir(){
		win.detach();
	}

}
