package siagsce.viewmodel.seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import siagsce.modelo.general.Archivo;
import siagsce.modelo.general.EnviarCorreo;

import org.hibernate.Session;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
import siagsce.modelo.servicio.seguridad.ServicioUsuario;
/**
 * VMUsuario es el viewmodel encargado 
 * de gestionar la vista EditarPerfil.zul
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMUsuario {

	@WireVariable
	private ServicioUsuario su;
	@WireVariable
	private ServicioGrupo sg;
	@WireVariable
	private SProfesor sprofesor;
	private AImage imagen;
	private Media media;
	private Archivo foto = new Archivo();

	/**
	 * Actualiza la foto del usuario.
	 */
	@Command
	public void save() {
		Usuario u = su
				.encontrarUsuarioPorNombreUsuario(SecurityUtil.nombreUsuario);
		u.setFoto(foto);

		if (u.getFoto().getTamano() > 0) {
			try {
				imagen = new AImage(u.getFoto().getNombreArchivo(), u.getFoto()
						.getContenido());
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		} else
			imagen = null;
		su.guardarUsuario(u);
	}
	/**
	 * Carga una imagen y se le asigna al usuario
	 */
	@Command
	@NotifyChange("imagen")
	public void cargarImagen(
			@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event) {
		media = event.getMedia();
		if (media != null) {
			if (media instanceof org.zkoss.image.Image) {
				foto.setNombreArchivo(media.getName());
				foto.setTipo(media.getContentType());
				foto.setContenido(media.getByteData());

				imagen = (AImage) media;
			} else {
				Messagebox.show("El archivo: " + media.getName()
						+ " no es una imagen valida", "Error", Messagebox.OK,
						Messagebox.ERROR);
			}
		}
	}
	public AImage getImagen() {
		return imagen;
	}

	public void setImagen(AImage imagen) {
		this.imagen = imagen;
	}

	public Archivo getFoto() {
		return foto;
	}

	public void setFoto(Archivo foto) {
		this.foto = foto;
	}

}