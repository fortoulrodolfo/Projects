/**
 * VMEditarPerfil es el viewmodel encargado de la gestionar la edicion del perfil de un usuario
 * a traves de la vista EditarPerfil.zul
 * @author ITERATOR
 */


package siagsce.viewmodel.maestros;



import java.io.FileInputStream;
import java.io.IOException;

import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.general.Archivo;
import siagsce.modelo.general.EnviarCorreo;
import siagsce.modelo.general.UtilidadesSiagsce;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.seguridad.ServicioUsuario;
import siagsce.viewmodel.seguridad.SecurityUtil;
import siagsce.viewmodel.seguridad.VMContactTreeRenderer3;
import siagsce.viewmodel.seguridad.VMPortalAplicacion;

import org.apache.commons.io.IOUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)

public class VMRecuperarContrasena {


		@Wire
		private Window winRecuperarContrasena;
		private @WireVariable SProfesor sprofesor;
		private @WireVariable ServicioUsuario su;
	
		private String cedula="";
		
		/**Getters and Setters
	      * @author ITERATOR
	      * @version: 03/03/14
	    */
		
	

		public String getCedula() {
			return cedula;
		}

		public void setCedula(String cedula) {
			this.cedula = cedula;
		}

		
		
		/**
		 * metodo que carga en los campos los datos del usuario que desea editar su perfil
		 * @param win es la ventana con la cual esta enlazada el viewmodel
		 * @author ITERATOR
		 * @version: 05/03/14
		 * @throws IOException 
		 */

		@Init
		public void init(@ContextParam(ContextType.COMPONENT) Window win) throws IOException {
			this.winRecuperarContrasena=win;
			Usuario user = su.encontrarUsuarioPorNombreUsuario(SecurityUtil.nombreUsuario);
			
		   
		}

		
		/**guardar el perfil modificado
		 * @author ITERATOR
		 * @version: 03/03/14
		 */
		@Command
		 public void recuperarContrasena() throws IOException {
			
			MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
			EnviarCorreo enviarCorreo=new EnviarCorreo();
			if(cedula != ""){
				Profesor profesor = sprofesor.buscarPorCedula(cedula);
				if(profesor!=null){
					Usuario usuario = su.encontrarUsuarioPorNombreUsuario(cedula);
					mensajeEmergente.informacionCorreoEnviadoExitosamente(profesor.getProfesorNombre()+" "+profesor.getProfesorApellido());
				    enviarCorreo.sendEmailOlvidoContrasena(profesor.getProfesorEmail(), usuario.getNombreUsuario(), usuario.getClave());	
				    salir();
				}else{
					mensajeEmergente.advertenciaIngresarCedulaValido();
				}
			}
			
	    }
		
	
		/**
		 * metodo que permite la conexion entre pantalla y viewmodel
		 * @param winEditarPerfil pantalla con la cual se conecta el viewmodel
		 * @author ITERATOR
		 * @version: 03/03/14
		 * 
		 */
		@AfterCompose
		public void afterCompose(@ContextParam(ContextType.VIEW) Component winEditarPerfil) {
			Selectors.wireComponents(winEditarPerfil, this, false);
			}
		
		/**metodo que limpia todos los campos
		 * @author ITERATOR
		 * @version: 03/03/14
		 * @throws IOException 
		 */
	
		
		/**metodo para salir de la pantalla
		  * @author ITERATOR
		  * @version: 03/03/14
		 */
		@Command
		public void salir() {
			winRecuperarContrasena.detach();
		}
		
	
	}
