package siagsce.viewmodel.maestros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Descarga;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Noticia;
import siagsce.modelo.servicio.maestros.SDescarga;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;


/**
 * ViewModel para la vista de Descarga de documentos de 
 * Servicio Comunitario Estudiantil, desde el Portal 
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCargarDescarga {

	
	/** 
	 * Declaración de servicios del ViewModel
	 * */
	private @WireVariable
	SDescarga sdescarga;
	@Wire
	Window win;
	
	/** 
	 * Declaracion de listas y otras estructuras de datos
	 * */
	private Media media;
	private Descarga selectedDocumento;
	private String nombreDocumento;
	private String tipoDocumento;
	private String descargaNombreArchivo;
	List<Descarga> listDescarga;
	ListModelList<Descarga> modeloDescarga;

	
	/**
	 * Setter y Getter
	 **/
	public List<Descarga> getListDescarga() {
		return listDescarga;
	}

	public void setListDescarga(List<Descarga> listDescarga) {
		this.listDescarga = listDescarga;
	}

	public ListModelList<Descarga> getModeloDescarga() {
		return modeloDescarga;
	}

	public void setModeloDescarga(ListModelList<Descarga> modeloDescarga) {
		this.modeloDescarga = modeloDescarga;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Descarga getDocumento() {
		return selectedDocumento;
	}

	public void setDocumento(Descarga documento) {
		this.selectedDocumento = documento;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescargaNombreArchivo() {
		return descargaNombreArchivo;
	}

	public void setDescargaNombreArchivo(String descargaNombreArchivo) {
		this.descargaNombreArchivo = descargaNombreArchivo;
	}

	/**
	 * Método de inicialización, para poder cargar 
	 * un archivo a la Lista de Descarga
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		MensajeBox.doSetTemplate();
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		listDescarga = sdescarga.buscarTodo();
		modeloDescarga = new ListModelList<Descarga>(listDescarga);
	}

	
	/**
	 * Método  para poder cargar 
	 * un archivo a la Lista de Descarga
	 * 
	 */
	@NotifyChange({ "media", "selectedDocumento", "descargaNombreArchivo","nombreDocumento",
			"tipoDocumento" })
	
	@Command
	public void uploadFile(
			@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {

		media = event.getMedia();
		Descarga doc = new Descarga();
		doc.setDescargaNombreArchivo(media.getName());
		doc.setContenidoDocumento(media.getByteData());
		doc.setDescargaTipo(media.getContentType());
		this.descargaNombreArchivo = doc.getDescargaNombreArchivo();
		this.tipoDocumento = doc.getDescargaTipo();
		this.selectedDocumento = doc;
	}


	@NotifyChange({ "selectedDocumento", "modeloDescarga", "descargaNombreArchivo",
			"tipoDocumento" , "nombreDocumento" })
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void guardar() {
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		if (this.selectedDocumento==null||this.descargaNombreArchivo==null|| this.tipoDocumento==null|| this.nombreDocumento==null){
			
			mensajeEmergente.advertenciaLlenarCampos();
		}
		else {
		
		this.selectedDocumento.setDescargaNombre(nombreDocumento);
		sdescarga.guardar(selectedDocumento);
		mensajeEmergente.informacionRegistroCorrecto();
		this.cancelar();
		}
	}
	
	@NotifyChange({ "selectedDocumento", "modeloDescarga"})
	@Command
	public void eliminar(@ContextParam(ContextType.COMPONENT) Button componente) {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		Integer codigo = (Integer) componente.getAttribute("codigo");
		this.selectedDocumento=sdescarga.buscarPorCodigo(codigo);
		sdescarga.eliminar(selectedDocumento);
		mensajeEmergente.informacionEliminarCorrecto();
		listDescarga = sdescarga.buscarTodo();
		modeloDescarga = new ListModelList<Descarga>(listDescarga);
	}
	
	@NotifyChange({ "media", "selectedDocumento", "nombreDocumento","tipoDocumento","descargaNombreArchivo", "modeloDescarga" })
	@Command
	public void cancelar() {
		this.selectedDocumento = null;
		this.nombreDocumento=null;
		this.tipoDocumento=null;
		this.descargaNombreArchivo=null;
		media=null;
		listDescarga = sdescarga.buscarTodo();
		modeloDescarga = new ListModelList<Descarga>(listDescarga);
	}

	@Command
	public void cerrarVentana() {
		win.detach();
	}

}
