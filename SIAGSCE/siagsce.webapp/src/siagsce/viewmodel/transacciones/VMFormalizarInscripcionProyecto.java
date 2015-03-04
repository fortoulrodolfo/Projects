package siagsce.viewmodel.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import siagsce.herramientas.*;

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

import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Popup;

import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import siagsce.modelo.data.maestros.Causa;

import siagsce.modelo.data.maestros.Estudiante;
import siagsce.modelo.data.maestros.MensajeBox;
import siagsce.modelo.data.maestros.Motivo;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.Retiro;
import siagsce.modelo.data.transacciones.ActividadAsignada;
import siagsce.modelo.data.transacciones.InscripcionProyecto;

import siagsce.modelo.data.transacciones.PreInscripcionProyecto;
import siagsce.modelo.general.StatusEstudiante;
import siagsce.modelo.general.StatusInscripcionProyecto;
import siagsce.modelo.general.StatusPreinscripcionProyecto;
import siagsce.modelo.general.StatusProyecto;
import siagsce.modelo.general.StatusRetiro;
import siagsce.modelo.servicio.maestros.SCausa;

import siagsce.modelo.servicio.maestros.SMotivo;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRetiro;
import siagsce.modelo.servicio.transacciones.SActividadAsignada;
import siagsce.modelo.servicio.transacciones.SInscripcionProyecto;
import siagsce.modelo.servicio.transacciones.SPreInscripcionProyecto;
import siagsce.viewmodel.maestros.VMCatalogoEstudiantesInscritos;

/**
 * ViewModel para la vista de Formalizar Inscripcion de un alumno en un proyecto
 * del Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMFormalizarInscripcionProyecto {

	/**
	 * Declaracion de las variables de tipo servicio
	 **/
	@WireVariable
	SProyecto sproyecto;
	@WireVariable
	SPreInscripcionProyecto spreinscripcionproyecto;
	@WireVariable
	SProfesor sprofesor;
	@WireVariable
	SInscripcionProyecto sinscripcionProyecto;
	@WireVariable
	SActividadAsignada sactividadAsignada;
	@WireVariable
	SCausa scausa;
	@WireVariable
	SMotivo smotivo;
	@WireVariable
	SRetiro sretiro;

	/**
	 * Declaracion de listas y otras estructuras de datos
	 **/
	ListModelList<PreInscripcionProyecto> modeloEstudiantePreInscrito;
	ListModelList<InscripcionProyecto> modeloEstudianteInscritos;
	ListModelList<Proyecto> modeloProyecto;
	List<PreInscripcionProyecto> listEstudiantePreInscrito;
	List<InscripcionProyecto> listEstudianteInscrito;
	List<ActividadAsignada> listActividadAsignada;
	List<Proyecto> listProyecto;
	List<Profesor> listaProfesoresResponsable;
	private List<String> valores;
	private List<Motivo> listMotivo;
	private ListModelList<Motivo> modeloMotivo;
	/**
	 * Declaracion de Variables del ViewModel
	 * */
	private String proyecto;
	private String codigoProyecto;
	private String codigoProy;
	private String descripcionProyecto;
	private String responsableProyecto;
	private String programaProyecto;
	private String cedulaPreinscripcion;
	private String nombrePreinscripcion;
	private String apellidoPreinscripcion;
	private String emailPreinscripcion;
	private String direccionProgramaPreinscripcion;
	private String proyectoInscrito;
	private Integer codigoInscripcion;
	private String cedulaInscripcion;
	private String nombreInscripcion;
	private String apellidoInscripcion;
	private String emailInscripcion;
	private String direccionProgramaInscripcion;
	private String seleccion;
	private String texto;
	private String seleccionCerrar;
	private Motivo selectedMotivo;
	private String motivo;
	private Proyecto selectedProyecto;
	private PreInscripcionProyecto selectedPreinscripcion;
	private InscripcionProyecto selectedInscripcion;
	private Boolean reabrir = false;
	private Boolean cerrar = false;

	VMCatalogoEstudiantesInscritos vmEstudiantesIns;

	private Boolean lblcedulaRetiro;
	private Boolean lblNombreRetiro;
	private Boolean lblApellidoRetiro;
	private Boolean lblEmailRetiro;
	private Boolean lblProyectoRetiro;
	private Boolean lblProgramaRetiro;
	private Boolean cmbMotivoRetiro;
	private Boolean cmbContabilizarRetiro;
	private Boolean btnCatalogoEstudiantes;
	private Boolean cambio;
	private Boolean focusTab;
	private Boolean focusTabInscripcion;
	/**
	 * Declaracion de Componentes de la vista
	 * */
	@Wire
	private Window win;

	@Wire
	private Textbox txtproyectoinscrito;

	@Wire
	private Textbox txtFiltroPreInscritos;

	@Wire
	private Textbox txtFiltroPreInscritosRetirar;

	@Wire
	private Textbox txtFiltroInscritos;

	@Wire
	private Popup popOpciones;

	@Wire
	private Popup popOpcionesRetirar;

	@Wire
	private Popup popOpcionesIns;

	@Wire
	private Combobox cmbValores;
	@Wire
	private Combobox cmbValoresAbrir;
	@Wire
	private Combobox cmbcontabilizar;

	/**
	 * Metodo de inicializacion. Establece la entidad a ser editada por la vista
	 * de edicion
	 * 
	 * @param win
	 *            ventana la cual esta asociada a este viewmodel
	 * 
	 */

	@Init
	public void init(@ContextParam(ContextType.COMPONENT) Component win) {
		this.win = (Window) win;

		modeloEstudiantePreInscrito = new ListModelList<PreInscripcionProyecto>();
		modeloEstudianteInscritos = new ListModelList<InscripcionProyecto>();
		cargarMotivo();
		MensajeBox.doSetTemplate();
		valores = new ArrayList<String>();
		valores.add("Cédula");
		valores.add("Nombre");
		valores.add("Apellido");
		valores.add("Programa");
		lblcedulaRetiro = true;
		lblNombreRetiro = true;
		lblApellidoRetiro = true;
		lblEmailRetiro = true;
		lblProyectoRetiro = true;
		lblProgramaRetiro = true;
		cmbMotivoRetiro = true;
		cmbContabilizarRetiro = true;
		btnCatalogoEstudiantes = false;

	}

	/**
	 * Vincula elementos de la interfaz grafica con este ViewModel.
	 * 
	 * @param view
	 *            la vista cuyos elementos se van a vincular a este ViewModel
	 */

	@AfterCompose
	public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		txtFiltroPreInscritos.setPlaceholder("Nombre");

	}

	/**
	 * metodo que permite cargar el combo de motivo
	 */

	public void cargarMotivo() {
		Causa causa = scausa.buscarPorNombre("Retiro");
		if (causa != null) {
			listMotivo = smotivo.buscarPorCausa(causa);
			modeloMotivo = new ListModelList<Motivo>(listMotivo);

		}

	}

	/**
	 * Muestra el catalogo de proyecto y obtiene el proyecto seleccionado
	 */

	@Command
	@NotifyChange({ "codigoProyecto", "descripcionProyecto",
			"programaProyecto", "responsableProyecto", "codigoProy",
			"cedulaInscripcion", "nombreInscripcion", "apellidoInscripcion",
			"emailInscripcion", "direccionProgramaInscripcion", "modeloMotivo",
			"cmbcontabilizar", "proyecto", "lblcedulaRetiro",
			"lblNombreRetiro", "lblApellidoRetiro", "lblEmailRetiro",
			"lblProyectoRetiro", "lblProgramaRetiro", "cmbMotivoRetiro",
			"cmbContabilizarRetiro", "btnCatalogoEstudiantes", "cambio", "reabrir" })
	public void MostrarCatalogoProyecto(
			@ContextParam(ContextType.VIEW) Component view) {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("proyecto", "FormalizarInscripcion");
		map.put("reabrir", reabrir);
		map.put("cerrar", cerrar);

		Window comp = (Window) Executions.createComponents(
				"/WEB-INF/vista/view/view.maestros/CatalogoProyectos.zul",
				null, map);
		comp.doModal();
		Proyecto proyecto = (Proyecto) comp.getAttribute("proyecto");
		if (proyecto != null) {
			obtenerProyecto(proyecto);

			this.cedulaInscripcion = null;
			this.nombreInscripcion = null;
			this.apellidoInscripcion = null;
			this.emailInscripcion = null;
			this.direccionProgramaInscripcion = null;
			this.proyecto = null;
		}

	}

	/**
	 * Metodo que obtiene los datos del catalogo de proyecto
	 * 
	 * @param proyecto
	 *            con el cual se asocia cada atributo del proyecto
	 * */

	@NotifyChange({ "codigoProyecto", "descripcionProyecto",
			"programaProyecto", "responsableProyecto", "codigoProy",
			"modeloEstudianteInscritos", "cedulaInscripcion",
			"nombreInscripcion", "apellidoInscripcion", "emailInscripcion",
			"direccionProgramaInscripcion", "modeloMotivo", "cmbcontabilizar" })
	public void obtenerProyecto(Proyecto proyecto) {

		this.codigoProyecto = proyecto.getProyectoCodigo();
		this.codigoProy = proyecto.getProyectoCodigo();
		this.descripcionProyecto = proyecto.getProyectoDescripcion();
		this.programaProyecto = proyecto.getProgramaString();
		this.responsableProyecto = proyecto.getResponsableString();
		this.proyectoInscrito = proyecto.getProyectoCodigo();

		cargarPreinscritos(proyecto.getProyectoCodigo());

	}

	/**
	 * Muestra el catalogo de estudiantes inscritos en un proyecto
	 */

	@Command
	@NotifyChange({ "cedulaInscripcion", "nombreInscripcion",
			"apellidoInscripcion", "emailInscripcion",
			"direccionProgramaInscripcion", "codigoProyecto", "proyecto",
			"lblcedulaRetiro", "lblNombreRetiro", "lblApellidoRetiro",
			"lblEmailRetiro", "lblProyectoRetiro", "lblProgramaRetiro",
			"cmbMotivoRetiro", "cmbContabilizarRetiro",
			"btnCatalogoEstudiantes" })
	public void mostrarCatalogoEstudiantesInscritos() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if (this.codigoProyecto != null && this.codigoProyecto != "") {

			lblcedulaRetiro = true;
			lblNombreRetiro = true;
			lblApellidoRetiro = true;
			lblEmailRetiro = true;
			lblProyectoRetiro = true;
			lblProgramaRetiro = true;
			cmbMotivoRetiro = false;
			cmbContabilizarRetiro = false;
			btnCatalogoEstudiantes = false;

			final HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("codigoProyecto", codigoProyecto);

			Window comp = (Window) Executions
					.createComponents(
							"/WEB-INF/vista/view/view.maestros/CatalogoEstudiantesInscritos.zul",
							null, map);

			comp.doModal();
			InscripcionProyecto listInscrito = (InscripcionProyecto) comp
					.getAttribute("inscrito");

			if (listInscrito != null) {
				obtenerInscritos(listInscrito);

			}
		} else {
			lblcedulaRetiro = true;
			lblNombreRetiro = true;
			lblApellidoRetiro = true;
			lblEmailRetiro = true;
			lblProyectoRetiro = true;
			lblProgramaRetiro = true;
			cmbMotivoRetiro = true;
			cmbContabilizarRetiro = true;
			btnCatalogoEstudiantes = false;

			mensajeEmergente.advertenciaSeleccionarUnProyecto();
		}
	}

	/**
	 * Metodo que obtiene los datos del catalogo estudiantes inscritos en un
	 * proyecto
	 * 
	 * @param inscripcion
	 *            con el cual se asocia acada atributo del estudiante de esa
	 *            inscripcion
	 * */

	@NotifyChange({ "cedulaInscripcion", "nombreInscripcion",
			"apellidoInscripcion", "emailInscripcion",
			"direccionProgramaInscripcion", "codigoInscripcion", "proyecto" })
	public void obtenerInscritos(InscripcionProyecto inscripcion) {
		this.proyecto = inscripcion.getProyectoi().getProyectoNombre();
		this.codigoInscripcion = inscripcion.getInscripcionCodigo();
		this.cedulaInscripcion = inscripcion.getPreInscripcion()
				.getEstudiante().getEstudianteCedula();
		this.nombreInscripcion = inscripcion.getPreInscripcion()
				.getEstudiante().getEstudianteNombre();
		this.apellidoInscripcion = inscripcion.getPreInscripcion()
				.getEstudiante().getEstudianteApellido();
		this.emailInscripcion = inscripcion.getPreInscripcion().getEstudiante()
				.getEstudianteEmail();
		this.direccionProgramaInscripcion = inscripcion.getPreInscripcion()
				.getEstudiante().getDireccionProgramae().getDireccionNombre();

	}

	/**
	 * Metodo para que funcione el filtrar en catalogo de  estudiantes 
	 * que estan Preinscritos que se van a inscribir
	 * */

	@Command
	@NotifyChange({ "modeloEstudiantePreInscrito" })
	public void filtrarEstudiantePre() {
		if (selectedProyecto != null) {
			try {
				List<PreInscripcionProyecto> aux = new ArrayList<PreInscripcionProyecto>();
				aux = spreinscripcionproyecto
						.buscarPorProyectopYPreinscripcionStatus(
								selectedProyecto,
								StatusPreinscripcionProyecto.EnEspera
										.toString());
				modeloEstudiantePreInscrito.clear();

				if (seleccion == null || seleccion == "") {
					if (texto == "")
						modeloEstudiantePreInscrito.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getEstudiante()
									.getEstudianteNombre().toLowerCase()
									.contains(texto.toLowerCase())) {
								modeloEstudiantePreInscrito.add(aux.get(i));

							}
						}
					}

				} else {
					if (seleccion.toLowerCase() == "cedula") {
						if (texto == "")
							modeloEstudiantePreInscrito.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getEstudiante()
										.getEstudianteCedula()
										.contains(texto.toLowerCase())) {
									modeloEstudiantePreInscrito.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion.toLowerCase() == "nombre") {
							if (texto == "")
								modeloEstudiantePreInscrito.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getEstudiante()
											.getEstudianteNombre()
											.toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloEstudiantePreInscrito.add(aux
												.get(i));

									}
								}
							}
						} else {
							if (seleccion.toLowerCase() == "apellido") {
								if (texto == "")
									modeloEstudiantePreInscrito.addAll(aux);
								else {
									for (int i = 0; i < aux.size(); i++) {
										if (aux.get(i).getEstudiante()
												.getEstudianteApellido()
												.toLowerCase()
												.contains(texto.toLowerCase())) {
											modeloEstudiantePreInscrito.add(aux
													.get(i));

										}
									}
								}
							}
						}
					}
				}
			} catch (NullPointerException e) {

			}

		}
	}

	/**
	 * Metodo para que funcione el filtrar en catalogo de  estudiantes 
	 * que estan Preinscritos que se van a inscribir
	 * */

	@Command
	@NotifyChange({ "modeloEstudiantePreInscrito" })
	public void filtrarEstudiantePreIns() {
		try {
			List<InscripcionProyecto> aux = new ArrayList<InscripcionProyecto>();
			aux = listEstudianteInscrito;
			modeloEstudianteInscritos.clear();

			if (seleccion == null || seleccion == "") {
				if (texto == "")
					modeloEstudianteInscritos.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getPreInscripcion().getEstudiante()
								.getEstudianteNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstudianteInscritos.add(aux.get(i));

						}
					}
				}

			} else {
				if (seleccion == "Cédula") {
					if (texto == "")
						modeloEstudianteInscritos.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getPreInscripcion().getEstudiante()
									.getEstudianteCedula()
									.contains(texto.toLowerCase())) {
								modeloEstudianteInscritos.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Nombre") {
						if (texto == "")
							modeloEstudianteInscritos.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getPreInscripcion()
										.getEstudiante().getEstudianteNombre()
										.toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloEstudianteInscritos.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Apellido") {
							if (texto == "")
								modeloEstudianteInscritos.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getPreInscripcion()
											.getEstudiante()
											.getEstudianteApellido()
											.toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloEstudianteInscritos.add(aux
												.get(i));

									}
								}
							}
						}
					}
				}
			}
		} catch (NullPointerException e) {

		}
	}

	/**
	 * Metodo para que funcione el filtrar en catalogo de  estudiantes 
	 * que estan inscritos que se van a retirar
	 * */

	@Command
	@NotifyChange({ "modeloEstudiantePreInscrito" })
	public void filtrarEstudiantePreRe() {
		try {
			List<InscripcionProyecto> aux = new ArrayList<InscripcionProyecto>();
			aux = listEstudianteInscrito;
			modeloEstudianteInscritos.clear();

			if (seleccion == null || seleccion == "") {
				if (texto == "")
					modeloEstudianteInscritos.addAll(aux);
				else {
					for (int i = 0; i < aux.size(); i++) {
						if (aux.get(i).getPreInscripcion().getEstudiante()
								.getEstudianteNombre().toLowerCase()
								.contains(texto.toLowerCase())) {
							modeloEstudianteInscritos.add(aux.get(i));

						}
					}
				}

			} else {
				if (seleccion == "Cédula") {
					if (texto == "")
						modeloEstudianteInscritos.addAll(aux);
					else {
						for (int i = 0; i < aux.size(); i++) {
							if (aux.get(i).getPreInscripcion().getEstudiante()
									.getEstudianteCedula()
									.contains(texto.toLowerCase())) {
								modeloEstudianteInscritos.add(aux.get(i));

							}
						}
					}
				} else {
					if (seleccion == "Nombre") {
						if (texto == "")
							modeloEstudianteInscritos.addAll(aux);
						else {
							for (int i = 0; i < aux.size(); i++) {
								if (aux.get(i).getPreInscripcion()
										.getEstudiante().getEstudianteNombre()
										.toLowerCase()
										.contains(texto.toLowerCase())) {
									modeloEstudianteInscritos.add(aux.get(i));

								}
							}
						}
					} else {
						if (seleccion == "Apellido") {
							if (texto == "")
								modeloEstudianteInscritos.addAll(aux);
							else {
								for (int i = 0; i < aux.size(); i++) {
									if (aux.get(i).getPreInscripcion()
											.getEstudiante()
											.getEstudianteApellido()
											.toLowerCase()
											.contains(texto.toLowerCase())) {
										modeloEstudianteInscritos.add(aux
												.get(i));

									}
								}
							}
						}
					}
				}
			}
		} catch (NullPointerException e) {

		}
	}

	/**
	 * Metodo para que funcione el filtrar pro nombre, cedula, apellido o programa.
	 * */

	@Command
	public void seleccionFiltro() {
		System.out.print(seleccion);
		if (seleccion == "Nombre") {
			txtFiltroPreInscritos.setPlaceholder("Nombre");
			popOpciones.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroPreInscritos.setPlaceholder("Cédula");
				popOpciones.close();
			} else {
				if (seleccion == "Apellido") {
					txtFiltroPreInscritos.setPlaceholder("Apellido");
					popOpciones.close();
				} else {
					if (seleccion == "Programa") {
						txtFiltroPreInscritos.setPlaceholder("Programa");
						popOpciones.close();
					}
				}
			}
		}

	}

	/**
	 * Metodo para que funcione el filtrar en catalogo de  estudiantes 
	 * que estan inscritos que se van a retirar
	 * */

	@Command
	public void seleccionFiltroIns() {
		System.out.print(seleccion);
		if (seleccion == "nombre") {
			txtFiltroInscritos.setPlaceholder("Nombre");
			popOpcionesIns.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroInscritos.setPlaceholder("Cédula");
				popOpcionesIns.close();
			} else {
				if (seleccion == "Apellido") {
					txtFiltroInscritos.setPlaceholder("Apellido");
					popOpcionesIns.close();
				} else {
					if (seleccion == "Programa") {
						txtFiltroInscritos.setPlaceholder("Programa");
						popOpcionesIns.close();
					}
				}
			}
		}

	}

	/**
	 * Metodo para que funcione el filtrar en catalogo de  estudiantes 
	 * que estan inscritos que se van a retirar
	 * */

	@Command
	public void seleccionFiltroInsRe() {
		System.out.print(seleccion);
		if (seleccion == "Nombre") {
			txtFiltroPreInscritosRetirar.setPlaceholder("Nombre");
			popOpcionesRetirar.close();
		} else {
			if (seleccion == "Cédula") {
				txtFiltroPreInscritosRetirar.setPlaceholder("Cédula");
				popOpcionesRetirar.close();
			} else {
				if (seleccion == "Apellido") {
					txtFiltroPreInscritosRetirar.setPlaceholder("Apellido");
					popOpcionesRetirar.close();
				} else {
					if (seleccion == "Programa") {
						txtFiltroPreInscritosRetirar.setPlaceholder("Programa");
						popOpcionesRetirar.close();
					}
				}
			}
		}

	}

	/**
	 * Metodo que carga los alumnos inscritos en un proyecto
	 * 
	 * @param ProyectoCodigo
	 *            es el codigo del proyecto al cual permitira hacer la busqueda
	 *            de los alumnos inscritos en ese proyecto
	 * */

	@NotifyChange({ "modeloEstudianteInscritos" })
	public void cargarAlumnosInscritos(String proyectoCodigo) {
		selectedProyecto = sproyecto.buscarPorCodigo(proyectoCodigo);
		modeloEstudianteInscritos.clear();
		listEstudianteInscrito = sinscripcionProyecto.buscarPorCodigoYEstatus(
				selectedProyecto, StatusInscripcionProyecto.Activo.toString());
		modeloEstudianteInscritos.addAll(listEstudianteInscrito);

	}

	/**
	 * Metodo que carga los alumnos pre inscritos en un proyecto
	 * 
	 * @param ProyectoCodigo
	 *            es el codigo del proyecto al cual permitira hacer la busqueda
	 *            de los alumnos pre inscritos en ese proyecto
	 * */

	@NotifyChange({ "modeloEstudiantePreInscrito" })
	public void cargarPreinscritos(String proyectoCodigo) {
		selectedProyecto = sproyecto.buscarPorCodigo(proyectoCodigo);
		modeloEstudiantePreInscrito.clear();
		listEstudiantePreInscrito = spreinscripcionproyecto
				.buscarPorProyectopYPreinscripcionStatus(selectedProyecto,
						StatusPreinscripcionProyecto.EnEspera.toString());
		modeloEstudiantePreInscrito.addAll(listEstudiantePreInscrito);

	}

	/**
	 * Metodo que permite seleccionar los alumnos preinscritos del proyecto
	 **/

	@NotifyChange({ "cedulaPreinscripcion", "nombrePreinscripcion",
			"emailPreinscripcion", "apellidoPreinscripcion",
			"direccionProgramaPreinscripcion" })
	@Command
	public void seleccionarPreinscrito() {
		cedulaPreinscripcion = selectedPreinscripcion.getEstudiante()
				.getEstudianteCedula();
		nombrePreinscripcion = selectedPreinscripcion.getEstudiante()
				.getEstudianteNombre();
		apellidoPreinscripcion = selectedPreinscripcion.getEstudiante()
				.getEstudianteApellido();
		emailPreinscripcion = selectedPreinscripcion.getEstudiante()
				.getEstudianteEmail();

		direccionProgramaPreinscripcion = selectedPreinscripcion
				.getEstudiante().getDireccionProgramae().getDireccionNombre();

	}

	/**
	 * Metodo que permite inscribir a los alumnos en un proyecto.
	 **/

	@NotifyChange({ "codigoProyecto", "descripcionProyecto",
			"programaProyecto", "responsableProyecto",
			"modeloEstudiantePreInscrito", "modeloEstudianteInscritos",
			"selectedPreinscripcion" })
	@Command
	public void inscribir() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		try {
			if (selectedPreinscripcion != null) {

				Estudiante estudiante = selectedPreinscripcion.getEstudiante();
				InscripcionProyecto inscripcionActiva;
				inscripcionActiva = sinscripcionProyecto
						.buscarInscripcionActiva(estudiante);

				if (inscripcionActiva == null) {

					selectedPreinscripcion
							.setPreinscripcionStatus(StatusPreinscripcionProyecto.Inactivo
									.toString());
					spreinscripcionproyecto.guardar(selectedPreinscripcion);
					Date fecha = new Date();
					InscripcionProyecto inscripcion = new InscripcionProyecto(
							fecha, StatusInscripcionProyecto.Activo.toString(),
							selectedPreinscripcion, selectedProyecto);

					sinscripcionProyecto.guardar(inscripcion);

					this.codigoProyecto = "";
					this.descripcionProyecto = "";
					this.responsableProyecto = "";
					this.programaProyecto = "";
					this.selectedPreinscripcion = null;
					this.modeloEstudianteInscritos.clear();
					this.modeloEstudiantePreInscrito.clear();

					mensajeEmergente.informacionRegistroCorrecto();
				} else {
					mensajeEmergente.advertenciaInscripcionActiva();

				}
			}

			else {
				mensajeEmergente.advertenciaSeleccionarOpcion();
			}

		} catch (NullPointerException e) {
			mensajeEmergente.advertenciaSeleccionarOpcion();
		}

	}

	/**
	 * Metodo que retira a los alumnos en un el proyecto,es decir
	 * retira al alumno de un proyecto, con un motivo del por que el alumno se retira
	 * y a eso le agrega si las horas que el alumno hasta ahora ha cumplido son contabilizadas o no
	 **/

	@NotifyChange({ "codigoProyecto", "descripcionProyecto",
			"programaProyecto", "responsableProyecto", "selectedInscripcion",
			"modeloEstudiantePreInscrito", "modeloEstudianteInscritos",
			"cedulaInscripcion", "nombreInscripcion", "apellidoInscripcion",
			"emailInscripcion", "direccionProgramaInscripcion", "modeloMotivo",
			"cmbcontabilizar", "proyecto", "lblcedulaRetiro",
			"lblNombreRetiro", "lblApellidoRetiro", "lblEmailRetiro",
			"lblProyectoRetiro", "lblProgramaRetiro", "cmbMotivoRetiro",
			"cmbContabilizarRetiro", "btnCatalogoEstudiantes", "cambio",
			"focusTab", "focusTabInscripcion", "selectedMotivo",
			"cmbcontabilizar", "cmbValoresAbrir", "cmbValores","reabrir " })
	
	@Command
	public void retirar() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();

		if (this.cedulaInscripcion != null && this.nombreInscripcion != null
				&& this.apellidoInscripcion != null
				&& this.emailInscripcion != null
				&& this.direccionProgramaInscripcion != null) {
			if (this.cedulaInscripcion != "" && this.nombreInscripcion != ""
					&& this.apellidoInscripcion != ""
					&& this.emailInscripcion != ""
					&& this.direccionProgramaInscripcion != "") {
				if (selectedMotivo != null) {

					if (this.cmbcontabilizar.getValue() != "") {

						InscripcionProyecto inscripcion = sinscripcionProyecto
								.buscarPorCodigoInscripcionYEstatus(
										this.codigoInscripcion,
										StatusInscripcionProyecto.Activo
												.toString());

						if (inscripcion != null) {

							inscripcion
									.setInscripcionProyectoStatus(StatusInscripcionProyecto.Retirado
											.toString());

							if (this.cmbcontabilizar.getValue().equals("Si")) {
								Date fecha = new Date();
								Retiro retiro = new Retiro(
										StatusRetiro.Contable.toString(),
										fecha, selectedMotivo, inscripcion);
								sretiro.guardar(retiro);
								sinscripcionProyecto.guardar(inscripcion);
								mensajeEmergente.informacioRetirarCorrecto();
								cancelar();

								this.proyecto = "";
								this.cedulaInscripcion = "";
								this.nombreInscripcion = "";
								this.apellidoInscripcion = "";
								this.emailInscripcion = "";
								this.direccionProgramaInscripcion = "";
								this.cmbcontabilizar.setValue(null);
								this.selectedMotivo = null;
								modeloMotivo.clear();
								cargarMotivo();
								lblcedulaRetiro = true;
								lblNombreRetiro = true;
								lblApellidoRetiro = true;
								lblEmailRetiro = true;
								lblProyectoRetiro = true;
								lblProgramaRetiro = true;
								cmbMotivoRetiro = true;
								cmbContabilizarRetiro = true;
								btnCatalogoEstudiantes = false;

							} else {
								if (this.cmbcontabilizar.getValue()
										.equals("No")) {
									Date fecha = new Date();
									Retiro retiro = new Retiro(
											StatusRetiro.NoContable.toString(),
											fecha, selectedMotivo, inscripcion);
									sretiro.guardar(retiro);
									sinscripcionProyecto.guardar(inscripcion);
									mensajeEmergente
											.informacioRetirarCorrecto();
									cancelar();

									this.proyecto = "";
									this.cedulaInscripcion = "";
									this.nombreInscripcion = "";
									this.apellidoInscripcion = "";
									this.emailInscripcion = "";
									this.direccionProgramaInscripcion = "";
									this.selectedMotivo = null;
									this.cmbcontabilizar.setValue(null);
									modeloMotivo.clear();
									cargarMotivo();
									lblcedulaRetiro = true;
									lblNombreRetiro = true;
									lblApellidoRetiro = true;
									lblEmailRetiro = true;
									lblProyectoRetiro = true;
									lblProgramaRetiro = true;
									cmbMotivoRetiro = true;
									cmbContabilizarRetiro = true;
									btnCatalogoEstudiantes = false;

								}

							}
						}

					} else {

						mensajeEmergente
								.advertenciaSeleccionarOpcionContabilizar();
						;

					}
				} else {

					mensajeEmergente.advertenciaSeleccionarMotivo();

				}

			}

			else {

				mensajeEmergente.advertenciaLlenarCampos();

			}
		} else {

			mensajeEmergente.advertenciaSeleccionarOpcion();
			limpiarRetirar();
		}

	}

	/**
	 * Metodo que permite cerrar la inscripcion de un proyecto, es decir el
	 * proyecto ya no esta disponible para pre inscripciones e inscripciones.
	 **/

	@NotifyChange({ "codigoProyecto", "descripcionProyecto",
		"responsableProyecto", "programaProyecto", "cedulaPreinscripcion",
		"nombrePreinscripcion", "apellidoPreinscripcion",
		"direccionProgramaPreinscripcion", "selectedInscripcion",
		"selectedPreinscripcion", "cmbValores", "cedulaInscripcion",
		"nombreInscripcion", "apellidoInscripcion", "emailInscripcion",
		"direccionProgramaInscripcion", "modeloMotivo", "cmbcontabilizar",
		"lblcedulaRetiro", "lblNombreRetiro", "lblApellidoRetiro",
		"lblEmailRetiro", "lblProyectoRetiro", "lblProgramaRetiro",
		"cmbMotivoRetiro", "cmbContabilizarRetiro",
		"btnCatalogoEstudiantes", "cmbValoresAbrir", "cmbValores","reabrir " })	
	@Command
	public void cerrarInscripcion() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if (cmbValores.getValue().equals("Si")) {
			if (this.codigoProyecto != null && this.descripcionProyecto != null
					&& this.responsableProyecto != null
					&& this.programaProyecto != null) {

				if (this.codigoProyecto != "" && this.descripcionProyecto != ""
						&& this.responsableProyecto != ""
						&& this.programaProyecto != "") {

					Proyecto proyecto = sproyecto
							.buscarPorCodigo(this.codigoProyecto);
					if (proyecto != null) {
						proyecto.setProyectoStatus(StatusProyecto.EnEjecucion
								.toString());
						sproyecto.guardar(proyecto);
						this.cmbValores.setValue(null);
						mensajeEmergente.informacionCerrarExito();
						cancelar();

					}

				} else {
					mensajeEmergente.advertenciaSeleccionarProyecto();
				}

			} else {
				mensajeEmergente.advertenciaSeleccionarProyecto();
			}

		}

		else {
			if (cmbValores.getValue().equals("No")) {

				if (this.codigoProyecto != null
						&& this.descripcionProyecto != null
						&& this.responsableProyecto != null
						&& this.programaProyecto != null) {

					if (this.codigoProyecto != ""
							&& this.descripcionProyecto != ""
							&& this.responsableProyecto != ""
							&& this.programaProyecto != "") {

						mensajeEmergente.advertenciaNoSeEfectuoModificacionEnInscripcion();
						cancelar();

					} else {
						mensajeEmergente.advertenciaSeleccionarProyecto();
					}
				} else {
					mensajeEmergente.advertenciaSeleccionarProyecto();
				}

			}

			else {

				mensajeEmergente.advertenciaSeleccionarOpcionCerrarProyecto();
			}
		}

	}

	/**
	 * Metodo que permite abrir los proyectos activos.
	 **/
	
	@NotifyChange({ "cedulaInscripcion", "nombreInscripcion",
		"apellidoInscripcion", "emailInscripcion",
		"direccionProgramaInscripcion", "modeloMotivo", "cmbcontabilizar",
		"proyecto", "selectedMotivo" })
	@Command
	public void abrirProyectosActivos(){
		this.reabrir = false;
		this.cerrar = false;
		limpiarRetirar();
	}
	
	
	/**
	 * Metodo que permite abrir los proyectos activos para cerrarlos.
	 **/
	@NotifyChange({ "codigoProyecto", "descripcionProyecto",
		"responsableProyecto", "programaProyecto", "cedulaPreinscripcion",
		"nombrePreinscripcion", "apellidoPreinscripcion",
		"direccionProgramaPreinscripcion", "selectedInscripcion",
		"selectedPreinscripcion", "cmbValores", "cedulaInscripcion",
		"nombreInscripcion", "apellidoInscripcion", "emailInscripcion",
		"direccionProgramaInscripcion", "modeloMotivo", "cmbcontabilizar",
		"lblcedulaRetiro", "lblNombreRetiro", "lblApellidoRetiro",
		"lblEmailRetiro", "lblProyectoRetiro", "lblProgramaRetiro",
		"cmbMotivoRetiro", "cmbContabilizarRetiro",
		"btnCatalogoEstudiantes", "cmbValoresAbrir", "cmbValores","cerrar" })	
	@Command
	public void abrirProyectosActivosParaCerrarlos(){
		this.cerrar = true;
		this.reabrir = false;
		cancelar();
		
	}
	
	
	/**
	 * Metodo que permite abrir los proyectos en ejecucion.
	 **/
	@NotifyChange({ "codigoProyecto", "descripcionProyecto",
		"responsableProyecto", "programaProyecto", "cedulaPreinscripcion",
		"nombrePreinscripcion", "apellidoPreinscripcion",
		"direccionProgramaPreinscripcion", "selectedInscripcion",
		"selectedPreinscripcion", "cmbValores", "cedulaInscripcion",
		"nombreInscripcion", "apellidoInscripcion", "emailInscripcion",
		"direccionProgramaInscripcion", "modeloMotivo", "cmbcontabilizar",
		"lblcedulaRetiro", "lblNombreRetiro", "lblApellidoRetiro",
		"lblEmailRetiro", "lblProyectoRetiro", "lblProgramaRetiro",
		"cmbMotivoRetiro", "cmbContabilizarRetiro",
		"btnCatalogoEstudiantes", "cmbValoresAbrir", "cmbValores","reabrir " })	
	@Command
	public void abrirProyectosEnEjecucion(){
		this.reabrir = true;
		this.cerrar = false;
		cancelar();
		
	}
	
	
	/**
	 * Metodo que permite abrir la inscripcion de un proyecto que se ha cerrado, 
	 * es decir el proyecto  volvera a estar disponible para pre inscripciones e inscripciones.
	 **/

	@NotifyChange({ "codigoProyecto", "descripcionProyecto",
		"responsableProyecto", "programaProyecto", "cedulaPreinscripcion",
		"nombrePreinscripcion", "apellidoPreinscripcion",
		"direccionProgramaPreinscripcion", "selectedInscripcion",
		"selectedPreinscripcion", "cmbValores", "cedulaInscripcion",
		"nombreInscripcion", "apellidoInscripcion", "emailInscripcion",
		"direccionProgramaInscripcion", "modeloMotivo", "cmbcontabilizar",
		"lblcedulaRetiro", "lblNombreRetiro", "lblApellidoRetiro",
		"lblEmailRetiro", "lblProyectoRetiro", "lblProgramaRetiro",
		"cmbMotivoRetiro", "cmbContabilizarRetiro",
		"btnCatalogoEstudiantes", "cmbValoresAbrir", "cmbValores" })
	@Command
	public void abrirInscripcion() {
		MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
		if (cmbValoresAbrir.getValue().equals("Si")) {
			if (this.codigoProyecto != null && this.descripcionProyecto != null
					&& this.responsableProyecto != null
					&& this.programaProyecto != null) {

				if (this.codigoProyecto != "" && this.descripcionProyecto != ""
						&& this.responsableProyecto != ""
						&& this.programaProyecto != "") {

					Proyecto proyecto = sproyecto
							.buscarPorCodigo(this.codigoProyecto);
					if (proyecto != null) {
						proyecto.setProyectoStatus(StatusProyecto.Activo
								.toString());
						sproyecto.guardar(proyecto);
						this.cmbValoresAbrir.setValue(null);
						mensajeEmergente.informacionAbrirExito();
						cancelar();

					}

				} else {
					mensajeEmergente.advertenciaSeleccionarProyecto();
				}

			} else {
				mensajeEmergente.advertenciaSeleccionarProyecto();
			}

		}

		else {
			if (cmbValoresAbrir.getValue().equals("No")) {

				if (this.codigoProyecto != null
						&& this.descripcionProyecto != null
						&& this.responsableProyecto != null
						&& this.programaProyecto != null) {

					if (this.codigoProyecto != ""
							&& this.descripcionProyecto != ""
							&& this.responsableProyecto != ""
							&& this.programaProyecto != "") {

						mensajeEmergente.advertenciaNoSeEfectuoModificacionEnInscripcion();
						cancelar();

					} else {
						mensajeEmergente.advertenciaSeleccionarProyecto();
					}
				} else {
					mensajeEmergente.advertenciaSeleccionarProyecto();
				}

			}

			else {

				mensajeEmergente.advertenciaSeleccionarOpcionAbrirProyecto();
				}
		}
		
	}
	
	
	/**
	 * Metodo que limpia los valores de las variables y limpia los valores de la
	 * ventana
	 **/

	@NotifyChange({ "codigoProyecto", "descripcionProyecto",
			"responsableProyecto", "programaProyecto", "cedulaPreinscripcion",
			"nombrePreinscripcion", "apellidoPreinscripcion",
			"direccionProgramaPreinscripcion", "selectedInscripcion",
			"selectedPreinscripcion", "cmbValores", "cedulaInscripcion",
			"nombreInscripcion", "apellidoInscripcion", "emailInscripcion",
			"direccionProgramaInscripcion", "modeloMotivo", "cmbcontabilizar",
			"lblcedulaRetiro", "lblNombreRetiro", "lblApellidoRetiro",
			"lblEmailRetiro", "lblProyectoRetiro", "lblProgramaRetiro",
			"cmbMotivoRetiro", "cmbContabilizarRetiro",
			"btnCatalogoEstudiantes", "cmbValoresAbrir", "cmbValores" })
	@Command
	public void cancelar() {
		codigoProyecto = "";
		descripcionProyecto = "";
		responsableProyecto = "";
		programaProyecto = "";
		modeloEstudianteInscritos.clear();
		modeloEstudiantePreInscrito.clear();
		selectedInscripcion = null;
		selectedPreinscripcion = null;

		cedulaPreinscripcion = "";
		nombrePreinscripcion = "";
		apellidoPreinscripcion = "";
		direccionProgramaPreinscripcion = "";
		this.cmbValores.setValue(null);
		this.cmbValoresAbrir.setValue(null);

		lblcedulaRetiro = true;
		lblNombreRetiro = true;
		lblApellidoRetiro = true;
		lblEmailRetiro = true;
		lblProyectoRetiro = true;
		lblProgramaRetiro = true;
		cmbMotivoRetiro = true;
		cmbContabilizarRetiro = true;
		btnCatalogoEstudiantes = false;
		selectedProyecto=null;

	}

	/**
	 * Metodo que limpia los valores de las variables dentro de la pestaña
	 * retirar iscripción
	 **/

	@NotifyChange({ "cedulaInscripcion", "nombreInscripcion",
			"apellidoInscripcion", "emailInscripcion",
			"direccionProgramaInscripcion", "modeloMotivo", "cmbcontabilizar",
			"proyecto", "selectedMotivo" })
	@Command
	public void limpiarRetirar() {

		this.proyecto = null;
		this.cedulaInscripcion = null;
		this.nombreInscripcion = null;
		this.apellidoInscripcion = null;
		this.emailInscripcion = null;
		selectedProyecto=null;
		this.direccionProgramaInscripcion = null;
		this.cmbcontabilizar.setValue(null);
		this.selectedMotivo = null;
		modeloMotivo.clear();
		cargarMotivo();

	}

	/**
	 * Metodo que permite cerrar la ventana
	 **/
	@Command
	public void salir() {
		win.detach();
	}

	/**
	 * Setter y Getter
	 **/

	public Integer getCodigoInscripcion() {
		return codigoInscripcion;
	}

	public Boolean getCambio() {
		return cambio;
	}

	public void setCambio(Boolean cambio) {
		this.cambio = cambio;
	}

	public Boolean getFocusTab() {
		return focusTab;
	}

	public void setFocusTab(Boolean focusTab) {
		this.focusTab = focusTab;
	}

	public void setCodigoInscripcion(Integer codigoInscripcion) {
		this.codigoInscripcion = codigoInscripcion;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public Combobox getCmbcontabilizar() {
		return cmbcontabilizar;
	}

	public void setCmbcontabilizar(Combobox cmbcontabilizar) {
		this.cmbcontabilizar = cmbcontabilizar;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public List<Motivo> getListMotivo() {
		return listMotivo;
	}

	public void setListMotivo(List<Motivo> listMotivo) {
		this.listMotivo = listMotivo;
	}

	public ListModelList<Motivo> getModeloMotivo() {
		return modeloMotivo;
	}

	public void setModeloMotivo(ListModelList<Motivo> modeloMotivo) {
		this.modeloMotivo = modeloMotivo;
	}

	public Motivo getSelectedMotivo() {
		return selectedMotivo;
	}

	public void setSelectedMotivo(Motivo selectedMotivo) {
		this.selectedMotivo = selectedMotivo;
	}

	public String getProyectoInscrito() {
		return proyectoInscrito;
	}

	public void setProyectoInscrito(String proyectoInscrito) {
		this.proyectoInscrito = proyectoInscrito;
	}

	public List<String> getValores() {
		return valores;
	}

	public void setValores(List<String> valores) {
		this.valores = valores;
	}

	public String getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(String seleccion) {
		this.seleccion = seleccion;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Proyecto getSelectedProyecto() {
		return selectedProyecto;
	}

	public void setSelectedProyecto(Proyecto selectedProyecto) {
		this.selectedProyecto = selectedProyecto;
	}

	public PreInscripcionProyecto getSelectedPreinscripcion() {
		return selectedPreinscripcion;
	}

	public void setSelectedPreinscripcion(
			PreInscripcionProyecto selectedPreinscripcion) {
		this.selectedPreinscripcion = selectedPreinscripcion;
	}

	public ListModelList<PreInscripcionProyecto> getModeloEstudiantePreInscrito() {
		return modeloEstudiantePreInscrito;
	}

	public void setModeloEstudianteInscrito(
			ListModelList<PreInscripcionProyecto> modeloEstudiantePreInscrito) {
		this.modeloEstudiantePreInscrito = modeloEstudiantePreInscrito;
	}

	public ListModelList<Proyecto> getModeloProyecto() {
		return modeloProyecto;
	}

	public void setModeloProyecto(ListModelList<Proyecto> modeloProyecto) {
		this.modeloProyecto = modeloProyecto;
	}

	public String getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(String codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	public String getDescripcionProyecto() {
		return descripcionProyecto;
	}

	public void setDescripcionProyecto(String descripcionProyecto) {
		this.descripcionProyecto = descripcionProyecto;
	}

	public String getResponsableProyecto() {
		return responsableProyecto;
	}

	public void setResponsableProyecto(String responsableProyecto) {
		this.responsableProyecto = responsableProyecto;
	}

	public String getProgramaProyecto() {
		return programaProyecto;
	}

	public void setProgramaProyecto(String programaProyecto) {
		this.programaProyecto = programaProyecto;
	}

	public String getCedulaPreinscripcion() {
		return cedulaPreinscripcion;
	}

	public void setCedulaPreinscripcion(String cedulaPreinscripcion) {
		this.cedulaPreinscripcion = cedulaPreinscripcion;
	}

	public String getNombrePreinscripcion() {
		return nombrePreinscripcion;
	}

	public void setNombrePreinscripcion(String nombrePreinscripcion) {
		this.nombrePreinscripcion = nombrePreinscripcion;
	}

	public String getApellidoPreinscripcion() {
		return apellidoPreinscripcion;
	}

	public void setApellidoPreinscripcion(String apellidoPreinscripcion) {
		this.apellidoPreinscripcion = apellidoPreinscripcion;
	}

	public String getDireccionProgramaPreinscripcion() {
		return direccionProgramaPreinscripcion;
	}

	public void setDireccionProgramaPreinscripcion(
			String direccionProgramaPreinscripcion) {
		this.direccionProgramaPreinscripcion = direccionProgramaPreinscripcion;
	}

	public SProyecto getSproyecto() {
		return sproyecto;
	}

	public void setSproyecto(SProyecto sproyecto) {
		this.sproyecto = sproyecto;
	}

	public SPreInscripcionProyecto getSpreinscripcionproyecto() {
		return spreinscripcionproyecto;
	}

	public void setSpreinscripcionproyecto(
			SPreInscripcionProyecto spreinscripcionproyecto) {
		this.spreinscripcionproyecto = spreinscripcionproyecto;
	}

	public SProfesor getSprofesor() {
		return sprofesor;
	}

	public void setSprofesor(SProfesor sprofesor) {
		this.sprofesor = sprofesor;
	}

	public List<PreInscripcionProyecto> getListEstudiantePreInscrito() {
		return listEstudiantePreInscrito;
	}

	public VMCatalogoEstudiantesInscritos getVmEstudiantesIns() {
		return vmEstudiantesIns;
	}

	public void setVmEstudiantesIns(
			VMCatalogoEstudiantesInscritos vmEstudiantesIns) {
		this.vmEstudiantesIns = vmEstudiantesIns;
	}

	public String getSeleccionCerrar() {
		return seleccionCerrar;
	}

	public void setSeleccionCerrar(String seleccionCerrar) {
		this.seleccionCerrar = seleccionCerrar;
	}

	public void setListEstudiantePreInscrito(
			List<PreInscripcionProyecto> listEstudiantePreInscrito) {
		this.listEstudiantePreInscrito = listEstudiantePreInscrito;
	}

	public List<Proyecto> getListProyecto() {
		return listProyecto;
	}

	public void setListProyecto(List<Proyecto> listProyecto) {
		this.listProyecto = listProyecto;
	}

	public List<Profesor> getListaProfesoresResponsable() {
		return listaProfesoresResponsable;
	}

	public void setListaProfesoresResponsable(
			List<Profesor> listaProfesoresResponsable) {
		this.listaProfesoresResponsable = listaProfesoresResponsable;
	}

	public void setModeloEstudiantePreInscrito(
			ListModelList<PreInscripcionProyecto> modeloEstudiantePreInscrito) {
		this.modeloEstudiantePreInscrito = modeloEstudiantePreInscrito;
	}

	public ListModelList<InscripcionProyecto> getModeloEstudianteInscritos() {
		return modeloEstudianteInscritos;
	}

	public void setModeloEstudianteInscritos(
			ListModelList<InscripcionProyecto> modeloEstudianteInscritos) {
		this.modeloEstudianteInscritos = modeloEstudianteInscritos;
	}

	public List<InscripcionProyecto> getListEstudianteInscrito() {
		return listEstudianteInscrito;
	}

	public void setListEstudianteInscrito(
			List<InscripcionProyecto> listEstudianteInscrito) {
		this.listEstudianteInscrito = listEstudianteInscrito;
	}

	public InscripcionProyecto getSelectedInscripcion() {
		return selectedInscripcion;
	}

	public void setSelectedInscripcion(InscripcionProyecto selectedInscripcion) {
		this.selectedInscripcion = selectedInscripcion;
	}

	public String getCodigoProy() {
		return codigoProy;
	}

	public void setCodigoProy(String codigoProy) {
		this.codigoProy = codigoProy;
	}

	public String getCedulaInscripcion() {
		return cedulaInscripcion;
	}

	public void setCedulaInscripcion(String cedulaInscripcion) {
		this.cedulaInscripcion = cedulaInscripcion;
	}

	public String getNombreInscripcion() {
		return nombreInscripcion;
	}

	public void setNombreInscripcion(String nombreInscripcion) {
		this.nombreInscripcion = nombreInscripcion;
	}

	public String getApellidoInscripcion() {
		return apellidoInscripcion;
	}

	public void setApellidoInscripcion(String apellidoInscripcion) {
		this.apellidoInscripcion = apellidoInscripcion;
	}

	public String getEmailInscripcion() {
		return emailInscripcion;
	}

	public Combobox getCmbValores() {
		return cmbValores;
	}

	public void setCmbValores(Combobox cmbValores) {
		this.cmbValores = cmbValores;
	}

	public void setEmailInscripcion(String emailInscripcion) {
		this.emailInscripcion = emailInscripcion;
	}

	public String getDireccionProgramaInscripcion() {
		return direccionProgramaInscripcion;
	}

	public void setDireccionProgramaInscripcion(
			String direccionProgramaInscripcion) {
		this.direccionProgramaInscripcion = direccionProgramaInscripcion;
	}

	public String getEmailPreinscripcion() {
		return emailPreinscripcion;
	}

	public void setEmailPreinscripcion(String emailPreinscripcion) {
		this.emailPreinscripcion = emailPreinscripcion;
	}

	public List<ActividadAsignada> getListActividadAsignada() {
		return listActividadAsignada;
	}

	public void setListActividadAsignada(
			List<ActividadAsignada> listActividadAsignada) {
		this.listActividadAsignada = listActividadAsignada;
	}

	public Boolean getLblcedulaRetiro() {
		return lblcedulaRetiro;
	}

	public void setLblcedulaRetiro(Boolean lblcedulaRetiro) {
		this.lblcedulaRetiro = lblcedulaRetiro;
	}

	public Boolean getLblNombreRetiro() {
		return lblNombreRetiro;
	}

	public void setLblNombreRetiro(Boolean lblNombreRetiro) {
		this.lblNombreRetiro = lblNombreRetiro;
	}

	public Boolean getLblApellidoRetiro() {
		return lblApellidoRetiro;
	}

	public void setLblApellidoRetiro(Boolean lblApellidoRetiro) {
		this.lblApellidoRetiro = lblApellidoRetiro;
	}

	public Boolean getLblEmailRetiro() {
		return lblEmailRetiro;
	}

	public void setLblEmailRetiro(Boolean lblEmailRetiro) {
		this.lblEmailRetiro = lblEmailRetiro;
	}

	public Boolean getLblProyectoRetiro() {
		return lblProyectoRetiro;
	}

	public void setLblProyectoRetiro(Boolean lblProyectoRetiro) {
		this.lblProyectoRetiro = lblProyectoRetiro;
	}

	public Boolean getLblProgramaRetiro() {
		return lblProgramaRetiro;
	}

	public void setLblProgramaRetiro(Boolean lblProgramaRetiro) {
		this.lblProgramaRetiro = lblProgramaRetiro;
	}

	public Boolean getCmbMotivoRetiro() {
		return cmbMotivoRetiro;
	}

	public void setCmbMotivoRetiro(Boolean cmbMotivoRetiro) {
		this.cmbMotivoRetiro = cmbMotivoRetiro;
	}

	public Boolean getCmbContabilizarRetiro() {
		return cmbContabilizarRetiro;
	}

	public void setCmbContabilizarRetiro(Boolean cmbContabilizarRetiro) {
		this.cmbContabilizarRetiro = cmbContabilizarRetiro;
	}

	public Boolean getBtnCatalogoEstudiantes() {
		return btnCatalogoEstudiantes;
	}

	public void setBtnCatalogoEstudiantes(Boolean btnCatalogoEstudiantes) {
		this.btnCatalogoEstudiantes = btnCatalogoEstudiantes;
	}

	public Boolean getFocusTabInscripcion() {
		return focusTabInscripcion;
	}

	public void setFocusTabInscripcion(Boolean focusTabInscripcion) {
		this.focusTabInscripcion = focusTabInscripcion;
	}

	public Boolean getReabrir() {
		return reabrir;
	}

	public void setReabrir(Boolean reabrir) {
		this.reabrir = reabrir;
	}
	
}