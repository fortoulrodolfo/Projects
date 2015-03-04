package siagsce.viewmodel.maestros;

import org.zkoss.zul.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Causa;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.Exonerado;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.data.maestros.Retiro;
import siagsce.modelo.servicio.maestros.SCausa;
import siagsce.modelo.servicio.maestros.SExonerado;
import siagsce.modelo.servicio.maestros.SMotivo;
import siagsce.modelo.servicio.maestros.SRetiro;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkex.license.PrivacyGuard;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;



/**
 * ViewModel para la vista de Motivo de un retiro 
 * o un exonerado del 
 * Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMMotivo {

		
	/** 
	 * Declaracion de Componentes de la vista
	 * */
	
	@Wire
	Window win;
	
	/** 
	 * Declaracion de servicios del ViewModel
	 * */
	private @WireVariable SMotivo smotivo;
	private @WireVariable SCausa scausa;
	private @WireVariable SExonerado sexonerado;
	private @WireVariable SRetiro sretiro;

	/** 
	 * Declaracion de Variables del ViewModel
	 * */
	
	private String codigo;
	private Integer codigos;
	private String nombre;
	private String descripcion;
	private String causa;
	private String nombreEditar;
	private String causaEditar;
	private String descripcionEditar;
	private Causa selectedCausa;
	private boolean modificar = false;
	private boolean	btnmodificar = true;
	private boolean nombreMotivo = true;
	private boolean  descripcionMotivo = true;
	private boolean btnGuardarMotivo = false;
	private boolean btnEliminarMotivo = true;

	
	/** 
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private ListModelList<Causa> modeloCausa;
	private List<Causa> listCausa;
	

	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */

	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Window win) {
		Selectors.wireComponents(win, this, false);
		this.win = (Window) win;	
		this.nombreMotivo = false;
		this.descripcionMotivo = false;
		this.btnmodificar = true;
		this.btnEliminarMotivo = true;
		cargarTipo();
		MensajeBox.doSetTemplate();
	}


	/**
	 * Metodo que carga el tipo de la causa
	 */
	
	public void cargarTipo(){
		listCausa = scausa.buscarTodo();
		modeloCausa = new ListModelList<Causa>(listCausa);
	}

	
	/**
	 * Metodo que carga el catalogo de motivo
	 */
	
	@Command
	public void mostrarCatalogoMotivos(@ContextParam(ContextType.VIEW) Component view){
		Executions.createComponents("/WEB-INF/vista/view/view.maestros/CatalogoMotivo.zul", null, null);
	}

	/**
	 * Metodo que obtiene los  valores  del catalogo de motivo
	 */
	
	@GlobalCommand
	@NotifyChange({"codigo","codigos", "nombre", "descripcion","causa", "nombreMotivo",
		"btnGuardarMotivo", "btnmodificar", "btnEliminarMotivo", "modificar", "descripcionMotivo"})
	public void obtenerMotivo(
			@BindingParam("returnvalue1") Integer str1,
			@BindingParam("returnvalue2") String  str2,
			@BindingParam("returnvalue3") String  str3,
			@BindingParam("returnvalue4") String str4
			)
	{

		this.codigos = str1;
		this.codigo = str1.toString();
		this.nombre = str2;
		this.descripcion = str3;
		this.causa = str4;
		this.nombreMotivo = true;
		this.descripcionMotivo = true;
		this.btnGuardarMotivo= true;
		this.btnEliminarMotivo = false;
		this.btnmodificar = false;
	}

	/**
	 * Metodo que permite guardar un nuevo motivo y actualizar uno existente
	 */

	@Command
	@NotifyChange({"codigo", "nombre", "descripcion","causa","modeloCausa",
		"modificar", "btnmodificar", "btnGuardarMotivo", "btnEliminarMotivo",
		"selectedCausa", "nombreMotivo" })

	public void guardarMotivo(){

		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		if (this.nombre == null || this.descripcion  == null || this.causa == null
				|| this.nombre == "" || this.descripcion  == "" || this.causa == "" ){
			mensajeEmergente.advertenciaLlenarCampos();

		}
		else{


			if(this.nombre != null && this.descripcion != null && this.causa != null ){
				if(this.nombre != "" && this.descripcion != "" && this.causa != "" ){

					if(this.modificar == false){
						/** Metodo que guarda un motivo nuevo */						
						
						Motivo motivo = new Motivo();										
						motivo.setMotivoNombre(this.nombre);
						motivo.setMotivoDescripcion(this.descripcion);
						motivo.setCausaMotivo(selectedCausa);
						smotivo.guardar(motivo);
						mensajeEmergente.informacionRegistroCorrecto();

						cancelar();
					}
					else{

						if(this.modificar == true){
							/** Metodo que mofifica un motivo  */
							
							Motivo motivoNuevo = smotivo.buscarPorCodigo(this.codigos);
							
							
							if(motivoNuevo != null){
								String cambioDescripcion;
								String cambioCausa;
								
								String descripcionVieja = motivoNuevo.getMotivoDescripcion();
								
								
								
								motivoNuevo.setMotivoNombre(this.nombre);
								motivoNuevo.setMotivoDescripcion(this.descripcion);

								if(selectedCausa != null){
									motivoNuevo.setCausaMotivo(selectedCausa);
								}
								else{
									motivoNuevo.setCausaMotivo(scausa.buscarPorNombre(this.causa));
								}
							
								
								
								if (descripcionVieja.equals(this.descripcion)){
									cambioDescripcion = "no";
								}
								else{
									cambioDescripcion = "si";
								}
								
								if (selectedCausa == null){
									cambioCausa = "no";
								}
								else{
									cambioCausa = "si";
								}
								
								if(cambioDescripcion.equals("si") && cambioCausa.equals("si") ){
									smotivo.guardar(motivoNuevo);
									cancelar();	
									this.btnmodificar = true;
									this.btnGuardarMotivo = false;
									this.btnEliminarMotivo = true;
									this.nombre = "";
									this.causa = "";
									this.descripcion = "";
									this.selectedCausa = null;
									this.modeloCausa.clear();
									this.nombreMotivo = false;
									this.modificar = false;
									cargarTipo();
									mensajeEmergente.informacionActualizarDatos();									
									
									
						
								}
								else{
									if(cambioDescripcion.equals("no") && cambioCausa.equals("no") ){
										cancelar();	
										this.btnmodificar = true;
										this.btnGuardarMotivo = false;
										this.btnEliminarMotivo = true;
										this.nombre = "";
										this.causa = "";
										this.descripcion = "";
										this.selectedCausa = null;
										this.modeloCausa.clear();
										this.nombreMotivo = false;
										this.modificar = false;
										cargarTipo();
										mensajeEmergente.advertenciaNoSeEfectuoModificacion();
										
									}
									else{
										if(cambioDescripcion.equals("si") && cambioCausa.equals("no")){
											smotivo.guardar(motivoNuevo);
											cancelar();	
											this.btnmodificar = true;
											this.btnGuardarMotivo = false;
											this.btnEliminarMotivo = true;
											this.nombre = "";
											this.causa = "";
											this.descripcion = "";
											this.selectedCausa = null;
											this.modeloCausa.clear();
											this.nombreMotivo = false;
											this.modificar = false;
											cargarTipo();
											mensajeEmergente.informacionActualizarDatos();	
										}
										else{
											smotivo.guardar(motivoNuevo);
											cancelar();	
											this.btnmodificar = true;
											this.btnGuardarMotivo = false;
											this.btnEliminarMotivo = true;
											this.nombre = "";
											this.causa = "";
											this.descripcion = "";
											this.selectedCausa = null;
											this.modeloCausa.clear();
											this.nombreMotivo = false;
											this.modificar = false;
											cargarTipo();
											mensajeEmergente.informacionActualizarDatos();	
											
										}
									}
								}
								
							
								
								
							}	
						}
					}
				}
				else{
					
					mensajeEmergente.advertenciaLlenarCampos();
				}
			}

			else{
				
				mensajeEmergente.advertenciaLlenarCampos();
			}
		}


	}


	
	/**
	 * Metodo que habilita los  botones para modificar un motivo
	 */

	@NotifyChange({ "btnmodificar",	"btnGuardarMotivo", "btnEliminarMotivo", "modificar", "descripcionMotivo"})
	@Command				
	public void modificarMotivo(){

		
		this.btnmodificar = true;
		this.btnGuardarMotivo = false;
		this.btnEliminarMotivo = true;
		this.modificar = true;
		this.descripcionMotivo = false;

	}

	
	/**
	 * Metodo que eliminia los datos de un motivo de la tabla de
	 * datos Motivos de la Base de Datos
	 */
	
	@NotifyChange({"nombre", "causa", "descripcion", "selectedCausa", "modeloCausa", "btnmodificar",
		"btnGuardarMotivo", "btnEliminarMotivo", "nombreMotivo", "descripcionMotivo "})
	@Command
	public void eliminarMotivo(){

		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		if(this.nombre != null && this.descripcion != null && this.causa != null ){
			if(this.nombre != "" && this.descripcion != "" && this.causa != "" ){

				Motivo motivo = smotivo.buscarPorCodigo(this.codigos);
				if (motivo != null){
					List<Retiro> retiros = new ArrayList<Retiro>();
					retiros = sretiro.buscarPorMotivo(motivo);
					if(retiros.size() == 0){
						/** Valida que no exista un motivo en la  tabla de retiro  */
						
						List<Exonerado> exonerados = new ArrayList<Exonerado>();
						exonerados = sexonerado.buscarPorMotivo(motivo);
						if(exonerados.size() == 0){
							/** Valida que no exista un motivo en la  tabla de exonerado */
						
							smotivo.eliminar(motivo);
							mensajeEmergente.informacionEliminarCorrecto();
							cancelar();	
							this.btnmodificar = true;
							this.btnGuardarMotivo = false;
							this.btnEliminarMotivo = true;
							this.nombre = "";
							this.causa = "";
							this.descripcion = "";
							this.selectedCausa = null;
							this.modeloCausa.clear();
							this.nombreMotivo = false;
							this.descripcionMotivo = false;
							cargarTipo();
						}
						else{
							/** no se puede  elimimar porque existen exonerados asociados  */
							
							mensajeEmergente.errorImposibleEliminar();
							
						}

					}
					else{
						/** no se  puede eliminar porque  existen retiros  asociados  */
					
						mensajeEmergente.errorImposibleEliminar();
					}

				}
				else{
					/**no encontro el motivo  */
					
				}

			}
			else{
				/**existen campos  vacios  */
				
				mensajeEmergente.advertenciaLlenarCampos();
			}
		}
		else{
			/**existen campos null  */
			
			mensajeEmergente.advertenciaLlenarCampos();
		}
	}




	/**
	 * Metodo que  limpia los  valores de las  variables
	 * dentro de la ventana de motivo
	 **/

	@NotifyChange({  "nombre", "descripcion","modeloCausa" ,"causa","nombreEditar", "descripcionEditar",
		"causaEditar", "nombreMotivo", "btnGuardarMotivo", "btnmodificar", "btnEliminarMotivo", "descripcionMotivo"})

	@Command
	public void cancelar() {

		this.nombre = "";
		this.descripcion = "";
		this.causa = "";
		this.nombreEditar ="";
		this.descripcionEditar ="";
		this.causaEditar ="";
		this.nombreMotivo = false;
		this.modeloCausa.clear();
		this.nombreMotivo = false;

		this.btnEliminarMotivo = true;
		this.btnGuardarMotivo = false;
		this.btnmodificar = true;
		this.descripcionMotivo = false;

		cargarTipo();


	}

	
	/**
	 * Metodo que  cierra  la ventana de motivo
	 **/
	
	@Command
	public void salir() {
		win.detach();
	}


	/**
	 * Setter y Getter
	 **/
	public boolean isModificar() {
		return modificar;
	}

	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		this.causa = causa;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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


	public ListModelList<Causa> getModeloCausa() {
		return modeloCausa;
	}

	public void setModeloCausa(ListModelList<Causa> modeloCausa) {
		this.modeloCausa = modeloCausa;
	}

	public List<Causa> getListCausa() {
		return listCausa;
	}

	public void setListCausa(List<Causa> listCausa) {
		this.listCausa = listCausa;
	}

	public Causa getSelectedCausa() {
		return selectedCausa;
	}

	public void setSelectedCausa(Causa selectedCausa) {
		this.selectedCausa = selectedCausa;
	}




	public boolean isBtnEliminarMotivo() {
		return btnEliminarMotivo;
	}

	public void setBtnEliminarMotivo(boolean btnEliminarMotivo) {
		this.btnEliminarMotivo = btnEliminarMotivo;
	}

	public String getNombreEditar() {
		return nombreEditar;
	}

	public void setNombreEditar(String nombreEditar) {
		this.nombreEditar = nombreEditar;
	}

	public String getCausaEditar() {
		return causaEditar;
	}

	public void setCausaEditar(String causaEditar) {
		this.causaEditar = causaEditar;
	}

	public String getDescripcionEditar() {
		return descripcionEditar;
	}

	public void setDescripcionEditar(String descripcionEditar) {
		this.descripcionEditar = descripcionEditar;
	}

	public boolean isNombreMotivo() {
		return nombreMotivo;
	}

	public void setNombreMotivo(boolean nombreMotivo) {
		this.nombreMotivo = nombreMotivo;
	}

	public boolean isDescripcionMotivo() {
		return descripcionMotivo;
	}

	public void setDescripcionMotivo(boolean descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
	}



	public Integer getCodigos() {
		return codigos;
	}

	public void setCodigos(Integer codigos) {
		this.codigos = codigos;
	}

	public boolean isBtnmodificar() {
		return btnmodificar;
	}

	public void setBtnmodificar(boolean btnmodificar) {
		this.btnmodificar = btnmodificar;
	}



	public boolean isBtnGuardarMotivo() {
		return btnGuardarMotivo;
	}

	public void setBtnGuardarMotivo(boolean btnGuardarMotivo) {
		this.btnGuardarMotivo = btnGuardarMotivo;
	}

	public SMotivo getSmotivo() {
		return smotivo;
	}

	public void setSmotivo(SMotivo smotivo) {
		this.smotivo = smotivo;
	}

	public SCausa getScausa() {
		return scausa;
	}

	public void setScausa(SCausa scausa) {
		this.scausa = scausa;
	}



}


