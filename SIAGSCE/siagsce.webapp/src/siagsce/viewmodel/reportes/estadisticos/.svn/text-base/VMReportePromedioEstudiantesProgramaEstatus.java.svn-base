package siagsce.viewmodel.reportes.estadisticos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Window;


import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.data.reportes.estadisticos.PromedioEstudiantesProgramaEstatus;
import siagsce.modelo.data.maestros.DireccionPrograma;
import siagsce.modelo.data.maestros.DirectorPrograma;
import siagsce.modelo.data.maestros.Profesor;
import siagsce.modelo.data.maestros.Proyecto;
import siagsce.modelo.data.maestros.RepresentanteProfesoral;
import siagsce.modelo.general.StatusDirectorPrograma;
import siagsce.modelo.general.StatusRepresentanteProfesoral;
import siagsce.modelo.servicio.maestros.SActividad;
import siagsce.modelo.servicio.maestros.SDireccionPrograma;
import siagsce.modelo.servicio.maestros.SDirectorPrograma;
import siagsce.modelo.servicio.maestros.SProfesor;
import siagsce.modelo.servicio.maestros.SProyecto;
import siagsce.modelo.servicio.maestros.SRepresentanteProfesoral;
import siagsce.modelo.servicio.reportes.estadisticos.SPromedioEstudiantesProgramaEstatus; 
import siagsce.viewmodel.reportes.abiertos.ReportConfig;
import siagsce.viewmodel.reportes.abiertos.ReportType;
import siagsce.viewmodel.seguridad.SecurityUtil;

/**
 * ViewModel para la vista que muestra 
 * el promedio de estudiantes por programa y estatus
 * en Servicio Comunitario Estudiantil
 * 
 * @author Iterator
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReportePromedioEstudiantesProgramaEstatus {
    
	/**
	 * Declaración de variables
	 */
	ReportType reportType = new ReportType("PDF", "pdf");
	private ReportConfig reportConfig = null;
	private String ruta="/WEB-INF/siagsce/reportes/estadisticos/PromedioEstudiantesProgramaEstatus.jasper";
	private Window win;
	
	/**
	 * Lista que permite llenar el combo para elegir el formato 
	 */
			private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
					Arrays.asList(
							new ReportType("HTML", "html"), 
							new ReportType("Word (RTF)", "rtf"), 
							new ReportType("Excel", "xls"), 
							new ReportType("Excel (JXL)", "jxl"), 
							new ReportType("CSV", "csv"), 
							new ReportType("OpenOffice (ODT)", "odt")));
			
			/**Declaración de los servicios
			 * 
			 */
			@WireVariable
			SDireccionPrograma sdireccionPrograma;
			
			@WireVariable
			SDirectorPrograma sdirector;
			
			@WireVariable
			SProfesor sprofesor;
			
			@WireVariable
			SActividad sactividad;
			
			@WireVariable
			SProyecto sproyecto ;
			
			@WireVariable
			SRepresentanteProfesoral smiembroCoordinacion;
			
			@WireVariable
			SPromedioEstudiantesProgramaEstatus sPromedioEstudiantes;
			
			
			/**
			 * Declaración de los modelos a ser utilizados en los listados y combos
			 */
			private ListModelList<DireccionPrograma> modeloPrograma;
			private ListModelList<PromedioEstudiantesProgramaEstatus> modeloPromedioEstudiantes;
			private List<DireccionPrograma> programas;
			
			 /**
			 * Declaración de variables donde se guardan las selecciones
			 */
			private DireccionPrograma selectedPrograma;
			private Date selectedFechaInicio;
			private Date selectedFechaFin;
			
			/**
			 * ESTO PARA FORMATO DE FECHA Y PARA OBTENER LA DEL SISTEMA
			 */
			private Date dateFormat;
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaActual = new Date();
	        String fechaSistema = formateador.format(fechaActual);
			
	        /**
	         * Métodos set y get
	         */
			public ListModelList<DireccionPrograma> getModeloPrograma(){
				return modeloPrograma;
			}
			
			public void setModeloPrograma(ListModelList<DireccionPrograma> modeloPrograma){
				this.modeloPrograma = modeloPrograma;
			}
			
			public ListModelList<PromedioEstudiantesProgramaEstatus> getModelopromedioestudiantes() {
				return modeloPromedioEstudiantes;
			}

			public void setModelopromedioestudiantes(
					ListModelList<PromedioEstudiantesProgramaEstatus> modeloPromedioEstudiantes) {
				this.modeloPromedioEstudiantes = modeloPromedioEstudiantes;
			}
			
			public List<DireccionPrograma> getProgramas() {
				return programas;
			}

			public void setProgramas(List<DireccionPrograma> programas) {
				this.programas = programas;
			}
			
			
			public DireccionPrograma getSelectedPrograma() {
				return selectedPrograma;
			}

			public void setSelectedPrograma(DireccionPrograma selectedPrograma) {
				this.selectedPrograma = selectedPrograma;
			
			}
			
			public Date getSelectedFechaInicio(){
				return selectedFechaInicio;
			}
			
			public void setSelectedFechaInicio(Date selectedFechaInicio){
				this.selectedFechaInicio = selectedFechaInicio;
			}
			
			public Date getSelectedFechaFin(){
				return selectedFechaFin;
			}
			
			public void setSelectedFechaFin(Date selectedFechaFin){
				this.selectedFechaFin = selectedFechaFin;
			}
			
			
			public ListModelList<ReportType> getReportTypesModel() {
				return reportTypesModel;
			}

			public ReportConfig getReportConfig() {
				return reportConfig;
			}
			
			public ReportType getReportType() {
				return reportType;
			}

			public void setReportType(ReportType reportType) {
				this.reportType = reportType;
			}
			public String getRuta() {
				return ruta;
			}


			public void setRuta(String ruta) {
				this.ruta = ruta;
			}


			public Window getWin() {
				return win;
			}


			public void setWin(Window win) {
				this.win = win;
			}
			
			public Date getDateFormat() {
				return dateFormat;
			}


			public void setDateFormat(Date dateFormat) {
				this.dateFormat = dateFormat;
			}
			
			
			/**
			 * Método que inicializa los modelos,carga el programa desde la BD 
			 * y llena el combo respectivamente
			 * @param win, ventana la cual esta asociada a este viewmodel
			 * @param view, ventana la cual esta asociada a este viewmodel
			 */
			@Init
			public void init(@ContextParam(ContextType.VIEW) Component view,
					@ContextParam(ContextType.COMPONENT) Component win) {
				Selectors.wireComponents(view, this, false);
				this.win = (Window) win;
				programas=buscarDireccionesXRol();
				modeloPrograma = new ListModelList<DireccionPrograma>(programas);		
			    modeloPromedioEstudiantes = new ListModelList<PromedioEstudiantesProgramaEstatus>();
				modeloPromedioEstudiantes = new ListModelList<PromedioEstudiantesProgramaEstatus>();
			}
			
			/**
			 * Método que aplica la seguridad funcional en el reporte
			 * para buscar las direcciones de programa dado el rol que posea el usuario.
			 * @return lista de direccion de programa
			 **/
			

			public List<DireccionPrograma> buscarDireccionesXRol(){
				List<DireccionPrograma>programas=new ArrayList<DireccionPrograma>();
				String cedula= SecurityUtil.nombreUsuario;
				Profesor profesor=sprofesor.buscarPorCedula(cedula);
				if(SecurityUtil.verificarRol("Coordinacion SCE")){
					programas=sdireccionPrograma.buscarTodo();
				}
				else{
					if(SecurityUtil.verificarRol("Director")){
						DirectorPrograma director= sdirector.buscarPorProfesorYEstatus(profesor, StatusDirectorPrograma.Activo.toString());
						programas.add(director.getDireccionPrograma());
					}
						
					if(SecurityUtil.verificarRol("Representante Profesoral")){
						RepresentanteProfesoral repre=smiembroCoordinacion.buscarRepresentantePorCedula(profesor, StatusRepresentanteProfesoral.Activo.toString());
						programas.add(repre.getDireccionProgramam());
						}
					if(SecurityUtil.verificarRol("Responsable Proyecto")){
						Profesor resp=sproyecto.buscarResponsableExit(profesor);
						List<Proyecto>proyectoR=sproyecto.buscarProyectosdelProfesorResponsable(resp);
						for(Proyecto proyecto:proyectoR){
							programas.addAll(proyecto.getDireccionPrograma());
						}
					}
					if(SecurityUtil.verificarRol("Participante Actividad")){
						Profesor part=sactividad.buscarParticipanteEx(profesor);
						List<Proyecto>proyectoP=sactividad.buscarProyectosDeUnProfesorParticipante(part);
						for(Proyecto proyectoA:proyectoP){
							programas.addAll(proyectoA.getDireccionPrograma());
						}
					}
				} 
				programas=eliminarRepetidos(programas);
				return programas;
			}
			/**
			 * elimina los repetidos de la lista de direcciones de programa
			 * @param lista a la cual se le eliminara las duplicidades
			 * @return lista filtrada 
			 * */
			public List<DireccionPrograma>eliminarRepetidos(List<DireccionPrograma>aux){
				List<DireccionPrograma>auxprogramas=new ArrayList<DireccionPrograma>();
				for(DireccionPrograma dir:aux){
					if(!(auxprogramas.contains(dir))){
						auxprogramas.add(dir);
					}
				}
				return auxprogramas;
			}
		
			/**
			 * VARIBLE QUE CONTROLA QUE TODOS LOS PARAMETROS SEAN ENVIADOS EXITOSAMENTE
			 * FALSE: NO EJECUTA LA CONSULTA
			 *TRUE: EJECUTA LA CONSULTA
			 */
			boolean aceptar= false;
			
			
			/**Método que ejecuta el SQL necesario para generar el reporte
			 * 
			 */
			@Command("obtenerGrafico")
			@NotifyChange({"selectedPrograma", "selectedFechaInicio", "selectedFechaFin"})
			public void obtenerGrafico() {
				MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
				modeloPromedioEstudiantes.clear();
				String condicion1=" "; String condicion2=" "; String condicion3=" "; String condicion4=" "; String condicion5="";
				String condicion= " WHERE b.direccion_codigo = a.direccion_codigo";
				
				/**
				 * VALIDACION SELECCIONAR UN PROGRAMA
				 */
			   if(selectedPrograma!=null){
				   /**
				    * VALIDACION SELECCIONAR UN RANGO DE FEHCA
				    */
				  if(selectedFechaInicio!=null && selectedFechaFin!=null){
					  /**
					   * VALIDACION LA FECHA INICIAL NO PUEDE SER MAYOR A LA FINAL
					   */
					  if(selectedFechaFin.after(selectedFechaInicio)){
						  /**
						   * VALIDACION LA FECHA FINAL NO PUEDE SER MAYOR A LA DEL SISTEMA
						   */
						  if(selectedFechaFin.before(fechaActual)){
						
				   aceptar = true;
				   condicion1 = "WHERE direccion_nombre=" + "'" + (selectedPrograma.getDireccionNombre()) + "'";
				   
				   condicion2 = condicion + " AND estudiante_status='Apto'" + " " + " AND b.direccion_nombre=" + "'" + selectedPrograma.getDireccionNombre() + "'" + 
				   " AND a.direccion_codigo=" + "'" + Integer.toString(selectedPrograma.getDireccionCodigo())+"'"+
				   "AND estudiante_fecha BETWEEN" + "'"+selectedFechaInicio+"'"+" AND "+"'"+selectedFechaFin+"'";
				   
				   
				   condicion3 = condicion + " AND estudiante_status='Acreditado'" + " " + " AND b.direccion_nombre=" + "'" + selectedPrograma.getDireccionNombre() + "'" + 
				   " AND a.direccion_codigo=" + "'" + Integer.toString(selectedPrograma.getDireccionCodigo())+"'" +
				   "AND estudiante_fecha BETWEEN" + "'"+selectedFechaInicio+"'"+" AND"+"'"+selectedFechaFin+"'";
				   
				   
				   condicion4 = condicion + " AND estudiante_status='Aprobado'" + " " + " AND b.direccion_nombre=" + "'" + selectedPrograma.getDireccionNombre() + "'" + 
				   " AND a.direccion_codigo=" + "'" + Integer.toString(selectedPrograma.getDireccionCodigo())+"'"+
				   "AND estudiante_fecha BETWEEN" + "'"+selectedFechaInicio+"'"+" AND"+"'"+selectedFechaFin+"'";
				   
				   condicion5 = condicion +  " AND b.direccion_nombre=" + "'" + selectedPrograma.getDireccionNombre() + "'" + 
					" AND a.direccion_codigo=" + "'" + Integer.toString(selectedPrograma.getDireccionCodigo())+"'" +
					"AND estudiante_fecha BETWEEN" + "'"+selectedFechaInicio+"'"+" AND"+"'"+selectedFechaFin+"'";	   
				   
				   List<PromedioEstudiantesProgramaEstatus> lista = sPromedioEstudiantes.buscarPromedioEstudiantesProgramaEstatus(condicion1,condicion2,condicion3,condicion4,condicion5);
				   modeloPromedioEstudiantes.addAll(lista);
					
				   }else{
					   mensajeEmergente.advertenciaValorFechas();
					   aceptar=false;
					   
				   };
				   }else{
					mensajeEmergente.advertenciaValorFechas();
					aceptar=false;
				   };
				   
				   }else{ 
					 mensajeEmergente.advertenciaLlenarCampos();
				     aceptar=false;
				   };
				   
						   
			   }else{
				   mensajeEmergente.advertenciaSeleccionarOpcion();
				   aceptar=false;
				   };
			   
			}
			 

			/**Método que envía los parámetros al reporte
			 * 
			 */
				@Command("showReport")
				@NotifyChange({"reportConfig"})
				public void showReport() {
					obtenerGrafico();
					
					if(aceptar==true){
						
						
						if(modeloPromedioEstudiantes.get(0).getCantidadTotalEstudiantes().toString()!="0"){
							
						
						reportConfig = new ReportConfig(ruta);
						reportConfig.getParameters().put("programa", selectedPrograma.getDireccionNombre());
						reportConfig.getParameters().put("fecha_inicio", selectedFechaInicio);
						reportConfig.getParameters().put("fecha_fin", selectedFechaFin);
						reportConfig.getParameters().put("lista", new JRBeanCollectionDataSource(modeloPromedioEstudiantes));
						reportConfig.setType(reportType);
						reportConfig.setDataSource(new JRBeanCollectionDataSource(modeloPromedioEstudiantes));
						
						}
						else
						{
							MensajesEmergentes mensajeEmergente = new MensajesEmergentes();
							mensajeEmergente.errorCamposVacios();
						}
						};
				}
				
			/**
			 * Método que cierra la ventana	
			 */
			@Command
			public void cerrar() {
				win.detach();
			}

	
}
