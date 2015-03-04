package siagsce.viewmodel.maestros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
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
import org.zkoss.zul.ListModelList;
import siagsce.herramientas.*;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.InscripcionTaller;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SEstudiante;
import siagsce.modelo.servicio.maestros.SInscripcionTaller;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRegistrarEstudianteApto {

	//Declaracion de los servicios a utilizar
	@WireVariable
	SEstudiante sestudiante;

	@WireVariable
	SDireccionPrograma sdireccionPrograma;
	
	@WireVariable
	SInscripcionTaller sinscripciontaller;

	//Declaracion de los componentes de la vista que seran manipulados por el WiewModel
	@Wire
	Window win;
	@Wire
	private Textbox txtNombreEstudiante;

	@Wire
	private Textbox txtCedulaEstudiante;

	@Wire
	private Textbox txtApellidoEstudiante;

	@Wire
	private Textbox txtTelefonoEstudiante;

	@Wire
	private Textbox txtDireccionEstudiante;

	@Wire
	private Textbox txtEmailEstudiante;

	@Wire
	private Button btnEliminarEstudiante;

	@Wire
	private Button btnModificarEstudiante;

	@Wire
	Textbox txtFiltroEstudiante;
	@Wire
	private Popup popOpciones;

	private Document document; // Se Usa para poder leer el Xml, instancia la
	// libreria Jdom-2.0.3 y poder usar sus
	// propiedades

	
	//Declaracion de variables comunes
	private Integer tamanoXML, codigoDireccion, estudianteUnidadesAprobadas;
	private String textoXML;
	private String estudianteCedula;
	private String estudianteNombre;
	private String estudianteApellido;
	private String estudianteEmail;
	private String estudianteTelefono;
	private String estudianteDireccion;
	private String estudianteStatus;
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String direccion;
	ListModelList<Estudiante> modeloEstudiante;
	List<Estudiante> listestudiante;
	private String seleccion;
	private List<String> valores;

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

	private String texto;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
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

	public ListModelList<Estudiante> getModeloEstudiante() {
		return modeloEstudiante;
	}

	public void setModeloEstudiante(ListModelList<Estudiante> modeloEstudiante) {
		this.modeloEstudiante = modeloEstudiante;
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
	
	//========================================================================
	//								METODOS
	//========================================================================

	//Metodo que permite inicializar y manipular componentes de la vista
	@AfterCompose
	public void AfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		btnEliminarEstudiante.setDisabled(true);
	}
	
	// Metodo para inicializar lo que sea necesario al momento de cargar la ventana
	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;
		modeloEstudiante = new ListModelList<Estudiante>();
		MensajeBox.doSetTemplate();
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		valores.add("Programa");
	}

	//Metodo que permite abrir el catalogo de estudiantes aptos y traer el estudiante que se seleccione
	@Command
	@NotifyChange({ "modeloEstudiante", "cedula", "nombre", "apellido",
			"email", "telefono", "direccion" })
	public void MostrarCatalogoEstudiante() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("estudiante", "RegistrarEstudianteApto");
		Window comp = (Window) Executions.createComponents(
				"/WEB-INF/vista/view/view.maestros/CatalogoEstudianteApto.zul",
				null, map);
		comp.doModal();
		Estudiante estudiante = (Estudiante) comp.getAttribute("estudiante");
		try {
			cedula = estudiante.getEstudianteCedula();
			nombre = estudiante.getEstudianteNombre();
			apellido = estudiante.getEstudianteApellido();
			email = estudiante.getEstudianteEmail();
			telefono = estudiante.getEstudianteTelefono();
			direccion = estudiante.getEstudianteDireccion();
			btnEliminarEstudiante.setDisabled(false);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

	}

	//Metodo que permite eliminar el estudiante seleccionado
	@Command
	@NotifyChange({ "profesorModel", "cedula", "nombre", "apellido", "email",
			"telefono", "direccion" })
	public void eliminarEstudiante() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
			Estudiante est = new Estudiante();
			est = sestudiante.buscarPorCedula(cedula);
			List<InscripcionTaller> inscripciones = sinscripciontaller.buscarPorEstudiante(est);
			if (!inscripciones.isEmpty()) {
				mensajeEmergente.errorImposibleEliminar();
			} else {
				sestudiante.eliminar(est);
				cancelar2();
				mensajeEmergente.informacionEliminarCorrecto();
			
			}
	}

	// Metodo para buscar un XML y cargarlo
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
				System.out.println("extension :" + filtro.getExtensions());
			} else {
				mensajeEmergente.errorFormatoNoSoportado();
				System.out.println("extension :" + filtro.getExtensions());
			}
		}
	}

	// Metodo para leer el archivo XML y mostrarlo en la lista
	@Command
	@NotifyChange({ "texto", "tamanoXML", "modeloEstudiante" })
	public String leerXml(File f) {
		String datos = "";
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			document = saxBuilder.build(f);
			XMLOutputter output = new XMLOutputter();
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("Estudiante");
			tamanoXML = list.size();
			listestudiante = new ArrayList<Estudiante>();

			for (int i = 0; i < list.size(); i++) {
				Element node = (Element) list.get(i);
				estudianteCedula = node.getAttribute("estudiante_cedula")
						.getValue();
				estudianteNombre = node.getChildText("estudiante_nombre");
				estudianteApellido = node.getChildText("estudiante_apellido");
				estudianteEmail = node.getChildText("estudiante_email");
				estudianteTelefono = node.getChildText("estudiante_telefono");
				estudianteDireccion = node.getChildText("estudiante_direccion");
				estudianteStatus = node.getChildText("estudiante_status");
				estudianteUnidadesAprobadas = Integer.parseInt(node
						.getChildText("estudiante_unidades_aprobadas"));
				codigoDireccion = Integer.parseInt(node
						.getChildText("direccionProgramae"));

				DireccionPrograma direccion = sdireccionPrograma
						.buscarPorCodigo(codigoDireccion);
				Estudiante estudiante = new Estudiante(estudianteCedula,
						estudianteNombre, estudianteApellido, estudianteEmail,
						estudianteTelefono, estudianteDireccion,
						estudianteStatus, estudianteUnidadesAprobadas,
						direccion);
				Date fechaActual = new Date();
				estudiante.setEstudianteFecha(fechaActual);
				listestudiante.add(estudiante);

			}

			textoXML = (output.outputString(document));
			modeloEstudiante.addAll(listestudiante);

		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}

	// Guarda los profesores mostrados en la lista
	@Command
	public void guardar() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		String cedula = "";
		if (!modeloEstudiante.isEmpty()) {
			for (int i = 0; i < modeloEstudiante.size(); i++) {
				cedula = modeloEstudiante.get(i).getEstudianteCedula();
				Estudiante estudiante = sestudiante.buscarPorCedula(cedula);
				if (estudiante != null) {
					estudiante.setEstudianteUnidadesAprobadas(modeloEstudiante
							.get(i).getEstudianteUnidadesAprobadas());
					sestudiante.guardar(estudiante);
				} else {
					sestudiante.guardar(modeloEstudiante.get(i));
				}
			}
			mensajeEmergente.informacionRegistroCorrecto();
			modeloEstudiante.clear();
		} else {
			mensajeEmergente.advertenciaCargarDatos();
		}
	}

	// metodo de cerrar ventanas
	@Command
	public void salir() {
		win.detach();
	}

	//Metodo que limpia el contenido de la primera pestaña
	@Command
	public void cancelar(){
		modeloEstudiante.clear();		
	}
	
	//Metodo que limpia el contenido de la segunda pestaña
	@NotifyChange({ "modeloEstudiante", "cedula", "nombre", "apellido",
			"email", "telefono", "direccion" })
	@Command
	public void cancelar2() {
		cedula = "";
		nombre = "";
		apellido = "";
		direccion = "";
		email = "";
		telefono = "";
		btnEliminarEstudiante.setDisabled(true);
	}
	
	//Metodo de filtrado
	@Command
	@NotifyChange({ "profesormodel" })
	public void filtrarEstudiante() {
		List<Estudiante> aux = new ArrayList<Estudiante>();
		aux = listestudiante;
		modeloEstudiante.clear();

		if (seleccion == null || seleccion == "") {
			if (texto == "")
				modeloEstudiante.addAll(aux);
			else {
				for (int i = 0; i < aux.size(); i++) {
					if (aux.get(i).getEstudianteNombre().toLowerCase()
							.contains(texto.toLowerCase())) {
						modeloEstudiante.add(aux.get(i));

					}
				}
			}

		} else {
			if (seleccion == "Cédula") {
				if (texto == "")
					modeloEstudiante.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getEstudianteCedula().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstudiante.add(aux.get(i));

						}
					}
				}
			} else {
				if (seleccion == "Nombre") {
					if (texto == "")
						modeloEstudiante.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getEstudianteNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloEstudiante.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion== "Apellido") {
						if (texto == "")
							modeloEstudiante.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getEstudianteApellido()
										.toLowerCase().contains(texto.toLowerCase())) {
									modeloEstudiante.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Programa") {
							if (texto == "")
								modeloEstudiante.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getDireccionProgramae()
											.getDireccionNombre().toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloEstudiante.add(aux.get(i));

									}
								}
							}
						}
					}
				}
			}
		}
	}

	//Metodo para colocar una marca de agua al filtro de acuerdo a la opcion que se seleccione
	@Command
	public void seleccionFiltro() {
		System.out.print(seleccion);
		if (seleccion == "Nombre") {
			txtFiltroEstudiante.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroEstudiante.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido") {
					txtFiltroEstudiante.setPlaceholder("Apellido");
					popOpciones.close();
				} else {
					if (seleccion == "Programa") {
						txtFiltroEstudiante.setPlaceholder("Programa");
						popOpciones.close();
					}
				}
			}
		}

	}
}
