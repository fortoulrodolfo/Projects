package siagsce.viewmodel.maestros;

import java.util.ArrayList;
import java.util.Date;
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
import siagsce.modelo.servicio.maestros.SNoticia;


/**
 * ViewModel para la vista del Portal 
 * 
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMCargarNoticia {

	
	/** 
	 * Declaración de servicios del ViewModel
	 * */
	private @WireVariable
	SNoticia snoticia;
	@Wire
	Window win;
	

	/** 
	 * Declaración de Listas y Variables del ViewModel
	 * */

	private Media media;
	private Noticia selectedNoticia;
	private String tituloNoticia;
	private String contenidoNoticia;
	private String fotoNoticia;
	private Date noticiaFecha;
	List<Noticia> listNoticia;
	ListModelList<Noticia> modeloNoticia;

	
	/**
	 * Setter y Getter
	 **/
	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Noticia getSelectedNoticia() {
		return selectedNoticia;
	}

	public void setSelectedNoticia(Noticia selectedNoticia) {
		this.selectedNoticia = selectedNoticia;
	}

	public String getTituloNoticia() {
		return tituloNoticia;
	}

	public void setTituloNoticia(String tituloNoticia) {
		this.tituloNoticia = tituloNoticia;
	}

	public String getContenidoNoticia() {
		return contenidoNoticia;
	}

	public void setContenidoNoticia(String contenidoNoticia) {
		this.contenidoNoticia = contenidoNoticia;
	}

	public List<Noticia> getListNoticia() {
		return listNoticia;
	}

	public void setListNoticia(List<Noticia> listNoticia) {
		this.listNoticia = listNoticia;
	}

	public ListModelList<Noticia> getModeloNoticia() {
		return modeloNoticia;
	}

	public void setModeloNoticia(ListModelList<Noticia> modeloNoticia) {
		this.modeloNoticia = modeloNoticia;
	}

	public String getFotoNoticia() {
		return fotoNoticia;
	}

	public void setFotoNoticia(String fotoNoticia) {
		this.fotoNoticia = fotoNoticia;
	}

	public Date getNoticiaFecha() {
		return noticiaFecha;
	}

	public void setNoticiaFecha(Date fotoFecha) {
		this.noticiaFecha = fotoFecha;
	}

	/**
	 * Método de inicializacion. 
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) {
		MensajeBox.doSetTemplate();
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		listNoticia = snoticia.buscarTodo();
		modeloNoticia = new ListModelList<Noticia>(listNoticia);
	}

	/**
	 * Método cargar una noticia
	 * con la respectiva información que contenga
	 */
	@NotifyChange({ "media", "selectedNoticia", "tituloNoticia","contenidoNoticia", "fotoNoticia" })
	@Command
	public void uploadFile(
			@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {

		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		media = event.getMedia();
		if (media != null) {
			if (media instanceof org.zkoss.image.Image) {
		Noticia not = new Noticia();
		not.setNoticiaFoto(media.getByteData());
		not.setNoticiaFotoNombre(media.getName());
		this.fotoNoticia = not.getNoticiaFotoNombre();
		this.selectedNoticia = not;
			}
			 else {
				 mensajeEmergente.errorFormatoNoSoportado();
			 }
		} 
	}
	
	/**
	 * Método guardar en BD una noticia
	 * con la respectiva información que contenga
	 */
	@NotifyChange({ "selectedNoticia", "noticiaFecha", "tituloNoticia",
			"contenidoNoticia", "modeloNoticia", "media","fotoNoticia" })
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void guardar() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		
		if (this.tituloNoticia==null||this.contenidoNoticia==null|| this.noticiaFecha==null|| this.selectedNoticia==null){
			
			mensajeEmergente.advertenciaLlenarCampos();
		}
		else {
		
			this.selectedNoticia.setNoticiaTitulo(tituloNoticia);
			this.selectedNoticia.setNoticiaContenido(contenidoNoticia);
			this.selectedNoticia.setNoticiaFecha(this.noticiaFecha);
			snoticia.guardar(selectedNoticia);
		mensajeEmergente.informacionRegistroCorrecto();
		this.cancelar();
		}
	}
	
	@NotifyChange({ "selectedNoticia", "modeloNoticia"})
	@Command
	public void eliminar(@ContextParam(ContextType.COMPONENT) Button componente) {
		Integer codigo = (Integer) componente.getAttribute("codigo");
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		this.selectedNoticia=snoticia.buscarPorCodigo(codigo);
		snoticia.eliminar(selectedNoticia);
		mensajeEmergente.informacionEliminarCorrecto();
		listNoticia = snoticia.buscarTodo();
		modeloNoticia = new ListModelList<Noticia>(listNoticia);
	}
	
	@NotifyChange({ "media", "selectedNoticia", "tituloNoticia","contenidoNoticia","noticiaFecha", "modeloNoticia" })
	@Command
	public void cancelar() {
		this.selectedNoticia = null;
		this.contenidoNoticia=null;
		this.tituloNoticia=null;
		this.fotoNoticia=null;
		this.noticiaFecha=null;
		media=null;
		listNoticia = snoticia.buscarTodo();
		modeloNoticia = new ListModelList<Noticia>(listNoticia);
	}

	@Command
	public void cerrarVentana() {
		win.detach();
	}

}
