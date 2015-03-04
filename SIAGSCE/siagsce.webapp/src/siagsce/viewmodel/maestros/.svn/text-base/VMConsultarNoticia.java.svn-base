package siagsce.viewmodel.maestros;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
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
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

import siagsce.modelo.data.maestros.Descarga;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Noticia;
import siagsce.modelo.servicio.maestros.SDescarga;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SNoticia;

/**
 * ViewModel para la vista de las noticias del Portal 
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMConsultarNoticia {

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
	private Noticia selectedNoticia;
	private String tituloNoticia;
	private String contenidoNoticia;
	private String fechaNoticia;
	private AImage imagenNoticia;
	Integer codi;


	/**
	 * Setter y Getter
	 **/
	public Integer getCodi() {
		return codi;
	}

	public void setCodi(Integer codi) {
		this.codi = codi;
	}

	public AImage getImagenNoticia() {
		return imagenNoticia;
	}

	public void setImagenNoticia(AImage imagenNoticia) {
		this.imagenNoticia = imagenNoticia;
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


	public String getFechaNoticia() {
		return fechaNoticia;
	}

	public void setFechaNoticia(String fechaNoticia) {
		this.fechaNoticia = fechaNoticia;
	}
	
	/**
	 * Método de inicializacion. 
	 * @param win ventana la cual esta asociada a este viewmodel
	 * @param codigo codigo de la noticia a mostrar
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win, @ExecutionArgParam("codigo") Integer cod) throws IOException {
		MensajeBox.doSetTemplate();
		Selectors.wireComponents(view, this, false);
		this.codi=cod;
		this.win = (Window) win;
		this.selectedNoticia = snoticia.buscarPorCodigo(codi);
		this.contenidoNoticia = this.selectedNoticia.getNoticiaContenido();
		this.tituloNoticia = this.selectedNoticia.getNoticiaTitulo();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String resultado = formato.format(this.selectedNoticia.getNoticiaFecha());
		this.fechaNoticia = resultado;
		this.imagenNoticia = new AImage(this.tituloNoticia, this.selectedNoticia.getNoticiaFoto());
		
		
	}
	
	/**
	 * cierra la ventana
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}

}
