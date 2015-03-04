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

public class VMEditarPerfil {


		@Wire
		private Window winEditarPerfil;
		private @WireVariable SProfesor sprofesor;
		private @WireVariable ServicioUsuario su;
	
		private String cedula="";
		private String nombre="";
		private String apellido="";
		private String telefono="";
		private String email="";
		private String direccion="";
		private String password ="";
		private String aux;
		private String password2 = "";
		private String password3 ="";
      	private Integer idusuario;
		private String estado;
		private AImage imagen;
		private Media media;
		private Archivo foto = new Archivo();
		private @Wire Groupbox groupboxCambiarClave;
		private @Wire Checkbox checkcambiarClave;
		
		/**Getters and Setters
	      * @author ITERATOR
	      * @version: 03/03/14
	    */
		
		public Archivo getFoto() {
			return foto;
		}

		public void setFoto(Archivo foto) {
			this.foto = foto;
		}

		public AImage getImagen() {
			return imagen;
		}

		public void setImagen(AImage imagen) {
			this.imagen = imagen;
		}

		public Media getMedia() {
			return media;
		}

		public void setMedia(Media media) {
			this.media = media;
		}

		public String getAux() {
			return aux;
		}

		public void setAux(String aux) {
			this.aux = aux;
		}

		public String getCedula() {
			return cedula;
		}

		public void setCedula(String cedula) {
			this.cedula = cedula;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		public String getPassword2() {
			return password2;
		}

		public void setPassword2(String password2) {
			this.password2 = password2;
		}

		public String getPassword3() {
			return password3;
		}

		public void setPassword3(String password3) {
			this.password3 = password3;
		}

		public Integer getIdusuario() {
			return idusuario;
		}

		public void setIdusuario(Integer idusuario) {
			this.idusuario = idusuario;
		}

		public String isEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
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
			this.winEditarPerfil=win;
			Usuario user = su.encontrarUsuarioPorNombreUsuario(SecurityUtil.nombreUsuario);
			Profesor profe = sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
		    cedula = profe.getProfesorCedula();
		    nombre = profe.getProfesorNombre();
		    apellido = profe.getProfesorApellido();
		    direccion = profe.getProfesorDireccion();
		    email = profe.getProfesorEmail();
			telefono = profe.getProfesorTelefono();
		    aux = user.getClave();
		    idusuario = user.getIdUsuario();
		    estado = user.getEstado();	
		    if(!user.getFoto().getNombreArchivo().equals(""))
				imagen=new AImage(user.getFoto().getNombreArchivo(), user.getFoto().getContenido());
				else{
					UtilidadesSiagsce util=new UtilidadesSiagsce();
					FileInputStream myStream = new FileInputStream(util.obtenerDirectorio()+"siagsce.webapp/WebContent/images/contact.gif");
					byte[] imageInBytes = IOUtils.toByteArray(myStream);
					  imagen= new  AImage("foto",imageInBytes);
				}
		}

		
		/**guardar el perfil modificado
		 * @author ITERATOR
		 * @version: 03/03/14
		 */
		@Command
		@NotifyChange({"cedula","nombre","apellido","telefono","direccion", "email", "status", 
			         "nombreUsuario","idusuario","estado", "password", "password2", "password3", "aux", "foto", "imagen","media"})
	    public void guardarPerfil() throws IOException {
			
			MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
			if(nombre != "" || apellido!="" || telefono != "" || direccion!="" || email != ""){
				if(!checkcambiarClave.isChecked()){
					  Profesor profesor = sprofesor.buscarPorCedula(cedula);
			          profesor.setProfesorDireccion(direccion);
			          profesor.setProfesorEmail(email);
			          profesor.setProfesorTelefono(telefono);
                      sprofesor.guardar(profesor);
                      Usuario usuario = su.encontrarUsuario(idusuario);                           
                      usuario.setFoto(foto);
                      su.guardarUsuario(usuario);
                      VMPortalAplicacion vmpa= new VMPortalAplicacion();
                      vmpa.setImagen(imagen);
                      vmpa.setNombreUsuario(nombre+" "+apellido);
                      mensajeEmergente.informacionActualizarDatos();
                      salir();
                      
				}else{
			if(password != ""){
				if(password.equals(aux)){
				
					if (password2 != ""  || password3 != ""){
		
						if(password3.equals(password2)){
			
					   		  Profesor profesor = sprofesor.buscarPorCedula(cedula);
					          profesor.setProfesorDireccion(direccion);
					          profesor.setProfesorEmail(email);
					          profesor.setProfesorTelefono(telefono);
                              sprofesor.guardar(profesor);
                              Usuario usuario = su.encontrarUsuario(idusuario);
                              usuario.setClave(password2);                             
                              usuario.setFoto(foto);
                              su.guardarUsuario(usuario);
                              mensajeEmergente.informacionActualizarDatos();
                              imagen=new AImage(usuario.getFoto().getNombreArchivo(), usuario.getFoto().getContenido());
                              VMPortalAplicacion vmpa= new VMPortalAplicacion();
                              vmpa.setImagen(imagen);
                              salir();
                              
						}				
				   else{
					   
					   		mensajeEmergente.errorContrasenasNoCoinciden();
					          this.password="";
					          this.password2="";
					          this.password3="";
				   }
			   }else{
				  mensajeEmergente.advertenciaIntroduzcaNuevaClave();
	          this.password="";
	          this.password2="";
	          this.password3="";
			  }
			}			
			else{
				mensajeEmergente.advertenciaClaveIncorrecta();
				this.password="";
				this.password2="";
				this.password3="";	
			}		
			}else{
				mensajeEmergente.advertenciaIntroducirClaveActual();
				this.password="";
				this.password2="";
				this.password3="";	
			}
			}
				
			}
			
	    }
		
		/**metodo que permite cargar una imagen
		 * @param event evento que permite cargar la imagen
		 * @author ITERATOR
		 * @version: 03/03/14
		 */
		
		
		@Command
		@NotifyChange("imagen")
		public void cargarImagen(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
			
			MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
			
			media = event.getMedia();
			if (media != null) {
				if (media instanceof org.zkoss.image.Image) {
					foto.setNombreArchivo(media.getName());
					foto.setTipo(media.getContentType());
					foto.setContenido(media.getByteData());
			
					imagen = (AImage) media;
				} else {
					mensajeEmergente.errorFormatoNoSoportado();
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
		@Command
		@NotifyChange({"cedula","nombre","apellido","telefono","direccion", "email", "status", 
			         "nombreusuario","password","password2", "password3", "foto", "imagen"})
	    public void cancelar() throws IOException {
			Usuario user = su.encontrarUsuarioPorNombreUsuario(SecurityUtil.nombreUsuario);
			Profesor profe = sprofesor.buscarPorCedula(SecurityUtil.nombreUsuario);
		    cedula = profe.getProfesorCedula();
		    nombre = profe.getProfesorNombre();
		    apellido = profe.getProfesorApellido();
		    direccion = profe.getProfesorDireccion();
		    email = profe.getProfesorEmail();
			telefono = profe.getProfesorTelefono();
		    aux = user.getClave();
		    idusuario = user.getIdUsuario();
		    estado = user.getEstado();	
		    if(!user.getFoto().getNombreArchivo().equals(""))
				imagen=new AImage(user.getFoto().getNombreArchivo(), user.getFoto().getContenido());
				else{
					UtilidadesSiagsce util=new UtilidadesSiagsce();
					FileInputStream myStream = new FileInputStream(util.obtenerDirectorio()+"siagsce.webapp/WebContent/images/contact.gif");
					byte[] imageInBytes = IOUtils.toByteArray(myStream);
					  imagen= new  AImage("foto",imageInBytes);
				}
			this.password="";
			this.password2="";
			this.password3="";
	    }
		
		/**metodo para salir de la pantalla
		  * @author ITERATOR
		  * @version: 03/03/14
		 */
		@Command
		public void salir() {
			winEditarPerfil.detach();
		}
		@Command
		public void cambiarClave() {
			if(checkcambiarClave.isChecked())
			groupboxCambiarClave.setVisible(true);
			else{
			groupboxCambiarClave.setVisible(false);
			}
		}
		

	}
