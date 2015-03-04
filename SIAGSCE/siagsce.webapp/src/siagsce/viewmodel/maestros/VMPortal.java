package siagsce.viewmodel.maestros;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Descarga;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.ListadoNoticias;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Noticia;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.servicio.maestros.SDescarga;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SNoticia;


/**
 * ViewModel para la vista del Portal 
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMPortal {
	
	
	/** 
	 * Declaración de servicios del ViewModel
	 * */
	@Wire
	Window win;
	
	private @WireVariable
	SEstudiante sestudiante;
	
	private @WireVariable
	SDescarga sdescarga;
	
	private @WireVariable
	SNoticia snoticia;
	
	/** 
	 * Declaración de Variables del ViewModel
	 * */
	
	private Descarga selectedDescarga;
	
	private Noticia selectedNoticia;
	
	private Estudiante selectedEstudiante;
	
	private String estudianteCedula;
	
	private String contenidoAyuda;
	
	
	/** 
	 * Declaración de listas 
	 * */
	
	List<Descarga> listDescarga;
	ListModelList<Descarga> modeloDescarga;
	
	List<Noticia> listNoticia;
	ListModelList<Noticia> modeloNoticia;
	
	List<ListadoNoticias> listNoticias;
	ListModelList<ListadoNoticias> modeloNoticias;
	

	/**
	 * Setter y Getter
	 **/
	public Noticia getSelectedNoticia() {
		return selectedNoticia;
	}

	public void setSelectedNoticia(Noticia selectedNoticia) {
		this.selectedNoticia = selectedNoticia;
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

	public List<ListadoNoticias> getListNoticias() {
		return listNoticias;
	}

	public void setListNoticias(List<ListadoNoticias> listNoticias) {
		this.listNoticias = listNoticias;
	}

	public ListModelList<ListadoNoticias> getModeloNoticias() {
		return modeloNoticias;
	}

	public void setModeloNoticias(ListModelList<ListadoNoticias> modeloNoticias) {
		this.modeloNoticias = modeloNoticias;
	}

	public Estudiante getSelectedEstudiante() {
		return selectedEstudiante;
	}

	public void setSelectedEstudiante(Estudiante selectedEstudiante) {
		this.selectedEstudiante = selectedEstudiante;
	}

	public String getEstudianteCedula() {
		return estudianteCedula;
	}

	public void setEstudianteCedula(String estudianteCedula) {
		this.estudianteCedula = estudianteCedula;
	}

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

	public Descarga getSelectedDescarga() {
		return selectedDescarga;
	}

	public void setSelectedDescarga(Descarga selectedDescarga) {
		this.selectedDescarga = selectedDescarga;
	}

	public String getContenidoAyuda() {
		return contenidoAyuda;
	}

	public void setContenidoAyuda(String contenidoAyuda) {
		this.contenidoAyuda = contenidoAyuda;
	}

	/**
	 * Método de inicializacion. 
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ContextParam(ContextType.COMPONENT) Component win) throws IOException {
		
		Selectors.wireComponents(view, this, false);
		this.win = (Window) win;
		MensajeBox.doSetTemplate();
		this.listDescarga = sdescarga.buscarTodo();
		this.modeloDescarga = new ListModelList<Descarga>(listDescarga);
		this.listNoticia= snoticia.buscarTodo();
		this.modeloNoticia = new ListModelList<Noticia>(listNoticia);
		
		if(this.listNoticia != null){
			
			this.listNoticias = new ArrayList<ListadoNoticias>();
			
			for(int i=0; listNoticia.size()>i; i++){
				AImage foto = new AImage (listNoticia.get(i).getNoticiaTitulo(),listNoticia.get(i).getNoticiaFoto());
				ListadoNoticias noti = new ListadoNoticias();
				noti.setCodigo(listNoticia.get(i).getNoticiaCodigo());
				noti.setTitulo(listNoticia.get(i).getNoticiaTitulo());
				noti.setContenido(listNoticia.get(i).getNoticiaContenido());
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				String resultado = formato.format(listNoticia.get(i).getNoticiaFecha());
				noti.setFecha(resultado);
				noti.setFoto(foto);
				this.listNoticias.add(noti);
		}
	
		this.modeloNoticias = new ListModelList<ListadoNoticias>(listNoticias);
		}
	}
	
	/**
	 * Metodo que permite acceder a los componentes de la vista e inicializar los necesarios
	 * **/
		@AfterCompose
		public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
			Selectors.wireComponents(view, this, false);
		}
	
	/**
	 * LLamado del Método Pre-Inscripción Proyecto en el ViewModel
	 */
	@Command
    public void MostrarPreInscripcion(@ContextParam(ContextType.VIEW) Component view){
	    	 	Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.transacciones/PreinscripcionProyecto.zul", null, null);
	    		comp.doModal();	
	    }
	
	/**
	 * Médoto Buscar estudiante, según la cédula ingresada
	 */
	@NotifyChange({"estudianteCedula"})
	@Command
	public void buscarEstudiante(@ContextParam(ContextType.VIEW) Component view){
		
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
	
		if(this.estudianteCedula == "" || this.estudianteCedula == null ){
			
			mensajeEmergente.advertenciaIngresarCedula();
		}
		else{
			
		this.selectedEstudiante = sestudiante.buscarPorCedula(this.estudianteCedula);
		if(selectedEstudiante != null){
		final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("estudiante", this.estudianteCedula);
		Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.transacciones/ConsultarStatusEstudiante.zul", null, map);
		comp.doModal();
		this.estudianteCedula="";
		}
		else{
			MensajeBox.doSetTemplate();
			mensajeEmergente.errorCedulaNoApta();
			this.estudianteCedula="";
		}
		
		}
	}
	
	/**
	 * LLamado del  Método DescargarDocumento en el ViewModel
	 */
	@Command
	public void DescargarDocumento(@ContextParam(ContextType.COMPONENT) Component componente)
	{
		Integer codigo = (Integer) componente.getAttribute("codigo");
		this.selectedDescarga=sdescarga.buscarPorCodigo(codigo);
		Filedownload.save(selectedDescarga.getContenidoDocumento(),
				selectedDescarga.getDescargaTipo(),
				selectedDescarga.getDescargaNombre());
	}
	
	/**
	 * Llamado de método MostrarNoticias en el ViewModel
	 */
	@Command
	public void MostrarNoticia(@ContextParam(ContextType.COMPONENT) Component componente)
	{
		Integer codigo = (Integer) componente.getAttribute("codigoNoti");
		Map map= new HashMap<String,Object>();
		map.put("codigo", codigo);
 		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/ConsultarNoticia.zul",
						null, map);
 		comp.doModal();
	}
	
	/**
	 * Llamado de método MostrarFechaTaller  en el ViewModel
	 */
	@Command
    public void MostrarFechaTaller(@ContextParam(ContextType.VIEW) Component view){
	    	 	Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.maestros/ConsultarFechaTaller.zul", null, null);
	    		comp.doModal();	
	    }
	
	/**
	 * Llamado de método MostrarEstudiantesAcreditados en el ViewModel
	 */
	@Command
    public void MostrarEstudiantesAcreditados(@ContextParam(ContextType.VIEW) Component view){
	    	 	Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.maestros/ConsultarResultadosTaller.zul", null, null);
	    		comp.doModal();	
	    }
	
	/**
	 * Llamado de método MostrarProyectosOfertados en el ViewModel
	 */
	@Command
    public void MostrarProyectosOfertados(@ContextParam(ContextType.VIEW) Component view){
	    	 	Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.maestros/ConsultarProyectosOfertados.zul", null, null);
	    		comp.doModal();	
	    }
	
	/**
	 * Llamado de método MostrarDescarga en el ViewModel
	 */
	@Command
    public void MostrarDescarga(@ContextParam(ContextType.VIEW) Component view){
	    	 	Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.maestros/ConsultarDescargas.zul", null, null);
	    		comp.doModal();	
	    }
	
	/**
	 * Llamado de método MostrarRequisitos (Ventana Emergente) en el ViewModel
	 */
	@Command
    public void MostrarRequisitosSCE(@ContextParam(ContextType.VIEW) Component view){
	    	 	Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.transacciones/ConsultarRequisitosSCE.zul", null, null);
	    		comp.doModal();	
	    }
	
	/**
	 * Llamado de método MostrarNormativasSCE (Ventana Emergente) en el ViewModel
	 */
	@Command
    public void MostrarNormativaSCE(@ContextParam(ContextType.VIEW) Component view){
	    	 	Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.transacciones/ConsultarNormativaSCE.zul", null, null);
	    		comp.doModal();	
	    }
	
	/**
	 * Llamado de método MostrarReglamentosSCE (Ventana Emergente) en el ViewModel
	 */
	@Command
    public void MostrarReglamentoSCE(@ContextParam(ContextType.VIEW) Component view){
	    	 	Window comp= (Window)Executions.createComponents("/WEB-INF/vista/view/view.transacciones/ConsultarReglamentoSCE.zul", null, null);
	    		comp.doModal();	
	    }
	
	@Command
	public void recuperarContrasena(@ContextParam(ContextType.COMPONENT) Component componente)
	{
 		Window comp = (Window) Executions
				.createComponents(
						"/WEB-INF/vista/view/view.maestros/RecuperarContrasena.zul",
						null,null);
 		comp.doModal();
	}
	
	@NotifyChange({"contenidoAyuda"})
	@Command
	public void mostrarAyuda(@BindingParam("window")  Window window, @ContextParam(ContextType.COMPONENT) Component componente)
	{
		String titulo = (String) componente.getAttribute("titulo");
		String contenido = (String) componente.getAttribute("contenido");
		window.setTitle(titulo);
		this.contenidoAyuda=contenido;
		window.setVisible(true);
	}
	
	/**
	 *Cierra la ventana
	 **/
	@Command
	public void cerrarVentana() {
		win.detach();
	}
}
