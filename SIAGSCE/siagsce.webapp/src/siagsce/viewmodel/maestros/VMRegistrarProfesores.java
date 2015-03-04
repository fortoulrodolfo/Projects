package siagsce.viewmodel.maestros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.seguridad.Grupo;
import siagsce.modelo.data.seguridad.Usuario;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.seguridad.ServicioGrupo;
import siagsce.modelo.servicio.seguridad.ServicioUsuario;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.zkoss.bind.annotation.AfterCompose;
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
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;

import siagsce.herramientas.*;

import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarProfesores {

	// declaracion de los servicios usados
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	ServicioUsuario su;
	@WireVariable
	SDirectorPrograma  sdirector;
	@WireVariable
	ServicioGrupo sg;

	//declaracion de los componentes de la vista que seran manipulados por el WiewModel
	@Wire
	private Textbox txtCedulaProfesor;
	@Wire
	private Textbox txtNombreProfesor;
	@Wire
	private Textbox txtApellidoProfesor;
	@Wire
	private Textbox txtEmailProfesor;
	@Wire
	private Textbox txtTelefonoProfesor;
	@Wire
	private Textbox txtDireccionProfesor;
	@Wire
	private Button btnEliminarProfesores;
	@Wire
	private Window win;
	@Wire
	private Button btnModificarProfesores;
	@Wire
	Textbox txtFiltroProfesor;
	@Wire
	private Popup popOpciones;

	// declaracion de List y ListModelList utilizados para el listado de
	// profesores
	List<Profesor> listprofesor;
	ListModelList<Profesor> profesorModel;
	ListModelList<Profesor> profesorModelo;

	private Document document; // Se Usa para poder leer el Xml, instancia la
								// libreria Jdom-2.0.3 y poder usar sus
								// propiedades
	
	//Declaracion de variables comunes
	private Integer tamanoXML;
	private String texto;
	private String profesor_cedula;
	private String profesor_nombre;
	private String profesor_apellido;
	private String profesor_email;
	private String profesor_telefono;
	private String profesor_direccion;
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String direccion;
	private String profesor_status;
	private String textoXML;
	private String seleccion;
	private List<String> valores;
	MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
	
	
	//========================================================================
	//								METODOS
	//========================================================================
	
	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * @param view la vista cuyos elementos se van a vincular a este ViewModel
	 */
	@AfterCompose
	public void AfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		txtFiltroProfesor.setPlaceholder("Nombre");
		btnEliminarProfesores.setDisabled(true);
	}

	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * @param win ventana la cual esta asociada a este viewmodel
	 * 
	 */
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component view) {
		this.win = (Window) view;
		profesorModel = new ListModelList<Profesor>();
		profesorModelo = new ListModelList<Profesor>();
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
	}

	/**
	 * Metodo que carga el archivo con extension XML.
	 * 
	 */
	@Command
	@NotifyChange({ "textoXML", "tamanoXML" })
	public void carga() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		JFileChooser selector = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter(
				"Archivos XML", "xml");
		selector.setFileFilter(filtro);
		int r = selector.showOpenDialog(null);
		if (r == JFileChooser.APPROVE_OPTION) {
			if (selector.getFileFilter() == filtro) {
				leerXml(selector.getSelectedFile());
			} else {
				mensajeEmergente.errorFormatoNoSoportado();
			}
		}
	}

	/**
	 * Metodo para leer el archivo XML y llenado una lista de profesores,
	 * con los datos suministrados a traves del XML.
	 * @exception JDOMException | IOException e  cuando no puede leer el documento.
	 * 
	 */
	@Command
	@NotifyChange({ "texto", "tamanoXML", "profesorModel" })
	public String leerXml(File f) {
		String datos = "";
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			document = saxBuilder.build(f);
			XMLOutputter output = new XMLOutputter();
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("Estudiante");
			// System.out.println(list.size());
			tamanoXML = list.size();
			listprofesor = new ArrayList<Profesor>();

			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				profesor_cedula = node.getAttribute("profesor_cedula")
						.getValue();
				profesor_nombre = node.getChildText("profesor_nombre");
				profesor_apellido = node.getChildText("profesor_apellido");
				profesor_email = node.getChildText("profesor_email");
				profesor_telefono = node.getChildText("profesor_telefono");
				profesor_direccion = node.getChildText("profesor_direccion");
				profesor_status = node.getChildText("profesor_status");
				Profesor profesor = new Profesor(profesor_cedula,
						profesor_nombre, profesor_apellido, profesor_email,
						profesor_telefono, profesor_direccion, profesor_status);

				listprofesor.add(profesor);

			}

			textoXML = (output.outputString(document));
			profesorModel.addAll(listprofesor);
			profesorModelo.addAll(listprofesor);

		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}

	/**
	 * Metodoque abre el catalogo de profesor y permite traer 
	 * el profesor que se seleccione en el catalogo.
	 * @exception NullPointerException cuando el objeto obtenido del catalogo es nulo.
	 * 
	 */
	@Command
	@NotifyChange({ "cedula", "nombre", "apellido", "email", "telefono",
			"direccion" })
	public void MostrarCatalogoProfesor() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
	     map.put("profesor","RegistrarProfesores");
		Window comp = (Window) Executions.createComponents(
				"/WEB-INF/vista/view/view.maestros/CatalogoProfesor.zul",
				null, map);
		comp.doModal();
		Profesor profesor = (Profesor)comp.getAttribute("profesor");
		try {
			cedula = profesor.getProfesorCedula();
			nombre = profesor.getProfesorNombre();
			apellido = profesor.getProfesorApellido();
			email = profesor.getProfesorEmail();
			telefono =profesor.getProfesorTelefono();
			direccion = profesor.getProfesorDireccion();
			if (profesor!=null)
				desbloquearProfesor();

		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		
	}
	
	/**
	 * desbloquea los textbox de la pestaña de eliminacion.
	 * 
	 */
	public void desbloquearProfesor() {
		txtNombreProfesor.setDisabled(false);
		txtApellidoProfesor.setDisabled(false);
		txtTelefonoProfesor.setDisabled(false);
		txtDireccionProfesor.setDisabled(false);
		txtEmailProfesor.setDisabled(false);
		btnEliminarProfesores.setDisabled(false);
		btnModificarProfesores.setDisabled(false);
	}

	/**
	 * Metodo para eliminar el profesor seleccionado.
	 * 
	 */
	@Command
	@NotifyChange({ "profesorModel", "cedula", "nombre", "apellido", "email",
			"telefono", "direccion" })
	public void eliminarProfesor() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if (cedula == "" || nombre == "" || apellido == "" || direccion == ""
				|| telefono == "" || email == "")
			mensajeEmergente.advertenciaLlenarCampos();
		else {
			Profesor prof = new Profesor();
			prof = sprofesor.buscarPorCedula(cedula);
			DirectorPrograma direc =new DirectorPrograma();
			direc=sdirector.buscarPorProfesorYEstatus(prof, StatusDirectorPrograma.Activo.toString());
			if (prof.getProfesorStatus() == "Acreditado"|| direc!=null) {
				mensajeEmergente.errorImposibleEliminar();
			} else {
			
				sprofesor.eliminar(prof);
				su.eliminarUsuario(su.encontrarUsuario(prof.getIdusuario()));
				cancelar2();
				mensajeEmergente.informacionEliminarCorrecto();
			}
		}

	}

	 
	/**
	 * Guarda los profesores mostrados en la lista en la BD.
	 * 
	 */
	@Command
	@NotifyChange({ "profesorModel", "profesorModelo" })
	public void guardar() {
		Grupo grupo=sg.buscarGrupoNombre("Basico");
		if(grupo!=null){
		if(!profesorModelo.isEmpty()){
			for (int i = 0; i < profesorModelo.size(); i++) {
				Profesor profesor=profesorModelo.get(i);
				if(sprofesor.buscarPorCedula(profesor.getProfesorCedula())==null)
				sprofesor.guardar(profesorModelo.get(i));
			}
			crearUsuarios(grupo);
			cancelar();
			mensajeEmergente.informacionRegistroCorrecto();
		}
		else{
			mensajeEmergente.advertenciaCargarDatos();
		     }
		}else{
			mensajeEmergente.advertenciaNoExisteGrupo("Basico");
		}
	}
	/**
	 * Crea los usuario de los profesores.
	 * Nota: Al momento de registrar el profesor en ese mismo instante se crea el usuario.
	 * @param grupo representa el grupo base con el cual todos los profesores tendran 
	 * la oportunidad de ingresar al sistema.Nota: no tendran ninguna opción en el arbol por ahora.
	 */
	public void crearUsuarios(Grupo grupo) {
		Usuario usuario = null;
		for (Profesor profesor : sprofesor.buscarProfesoresSinUsuario()) {
			String password = "";
			password = getCadenaAlfanumAleatoria();
			usuario = new Usuario(null, profesor.getProfesorCedula(), password, "True");
			usuario.addGrupo(grupo);
			su.guardarUsuario(usuario);
			int idUsuario = su.encontrarUsuarioPorNombreUsuario(
					profesor.getProfesorCedula()).getIdUsuario();
			profesor.setIdusuario(idUsuario);
			sprofesor.guardar(profesor);
		    }
		}

	/**
	 * Genera una cadena alfanumerica aleatoria con una longitud de 8 caracteres.
	 * @return cadenaAleatoria cadena alfanumerica aleatoria.
	 */
	private String getCadenaAlfanumAleatoria() {
		String cadenaAleatoria = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while (i < 8) {
			char c = (char) r.nextInt(255);
			if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
				cadenaAleatoria += c;
				i++;
			}
		}
		return cadenaAleatoria;
	}

	/**
	 * Metodo que permite filtrar profesores de la lista.
	 */
	@Command
	@NotifyChange({ "profesormodel" })
	public void filtrarProfesor() {
		List<Profesor> aux = new ArrayList<Profesor>();
		aux = listprofesor;
		profesorModel.clear();

		if (seleccion == null || seleccion == "") {
			if (nombre == "")
				profesorModel.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getProfesorNombre().toLowerCase()
							.contains(nombre.toLowerCase())) {
						profesorModel.add(aux.get(i));

					}
				}
			}
		} else {
			if (seleccion== "Cédula") {
				if (nombre == "")
					profesorModel.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getProfesorCedula().toLowerCase()
								.contains(nombre.toLowerCase())) {
							profesorModel.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion== "Nombre") {
					if (nombre == "")
						profesorModel.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getProfesorNombre().toLowerCase()
									.contains(nombre.toLowerCase())) {
								profesorModel.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion== "Apellido") {
						if (nombre == "")
							profesorModel.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getProfesorApellido()
										.toLowerCase().contains(nombre.toLowerCase())) {
									profesorModel.add(aux.get(i));

								}
							}
						}
					}
				}
			}
		}

	}

	/**
	 * Metodo para colocar una marca de agua al filtro de acuerdo a la opcion que se seleccione.
	 */
	@Command
	public void seleccionFiltro() {
		if (seleccion== "Nombre") {
			txtFiltroProfesor.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion== "Cédula") {
				txtFiltroProfesor.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido")
					txtFiltroProfesor.setPlaceholder("Apellido");
					popOpciones.close();
			}
		}

	}

	/**
	 * Limpia el contenido de la primera pestaña.
	 */
	@NotifyChange({ "profesorModel", "profesorModelo" })
	@Command
	public void cancelar(){
		profesorModel.clear();
		profesorModelo.clear();
	}
	
	/**
	 * Limpia el contenido de la segunda pestaña.
	 */
		@Command
		@NotifyChange({ "cedula", "nombre", "apellido", "email",
				"telefono", "direccion", "cedula" })
		public void cancelar2() {

			cedula = "";
			nombre = "";
			apellido = "";
			email = "";
			telefono = "";
			direccion = "";
			txtNombreProfesor.setDisabled(true);
			txtApellidoProfesor.setDisabled(true);
			txtTelefonoProfesor.setDisabled(true);
			txtDireccionProfesor.setDisabled(true);
			txtEmailProfesor.setDisabled(true);
			btnEliminarProfesores.setDisabled(true);
		}

		/**
		 * Cierra la ventana.
		 */
	@Command
	public void salir() {
		win.detach();
	}
	
	//Set y Get
		public String getSeleccion() {
			return seleccion;
		}

		public void setSeleccion(String seleccion) {
			this.seleccion = seleccion;
		}

		public List<String> getValores() {
			return valores;
		}

		public void setValores(List<String> valores) {
			this.valores = valores;
		}

		public String getCedula() {
			return cedula;
		}

		public void setProfesorCedula(String cedula) {
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}

		public List<Profesor> getListprofesor() {
			return listprofesor;
		}

		public void setListprofesor(List<Profesor> listprofesor) {
			this.listprofesor = listprofesor;
		}

		public ListModelList<Profesor> getProfesorModel() {
			return profesorModel;
		}

		public void setProfesorModel(ListModelList<Profesor> profesorModel) {
			this.profesorModel = profesorModel;
		}

		public ListModelList<Profesor> getProfesorModelo() {
			return profesorModelo;
		}

		public void setProfesorModelo(ListModelList<Profesor> profesorModelo) {
			this.profesorModelo = profesorModelo;
		}

		public String getTextoXML() {
			return textoXML;
		}

		public void setTextoXML(String textoXML) {
			this.textoXML = textoXML;
		}

		public Integer getTamanoXML() {
			return tamanoXML;
		}

		public void setTamanoXML(Integer tamanoXML) {
			this.tamanoXML = tamanoXML;
		}
		

}
