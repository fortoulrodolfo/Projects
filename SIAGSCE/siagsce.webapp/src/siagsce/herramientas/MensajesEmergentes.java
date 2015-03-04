/* @MensajesEmergentes
 * Para implementaci�n de los mensajes de informaci�n, error y advertencia (messagebox)
 * @author Delba L�pez/Maryelis M�ndez/Jhezir Canela
 * Version 1.0, 24/02/2014
 */
/* @MensajesEmergentes
 * Para implementaci�n de los mensajes de informaci�n, error y advertencia (messagebox)
 * @author Delba L�pez/Maryelis M�ndez/Jhezir Canela
 * Version 1.0, 24/02/2014
 */


package siagsce.herramientas;

import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

/**
 * Debe declarar una variable tipo MensajesEmergentes como est�ndar 
 * mensajeEmergente(MensajesEmergentes mensajeEmergente = new MensajesEmergentes();) 
 * en donde usar� los mensajes (su clase viewmodels), 
 * primero importe el paquete herramientas
 * Cuando vaya a usar alg�n mensaje coloca mensajeEmergente.NombreDelMetodo();
 * por ejemplo: mensajeEmergente.advertenciaLlenarCampos();
 * Si necesita alg�n otro mensaje agr�guelo a esta clase, en orden.
 * por ejemplo: si es de advertencia, debajo del �ltimo de advertencia.
 * @author Iterator
 * */

public class MensajesEmergentes {
	
	/** Mensajes de advertencia*/
	
	public void advertenciaCargarDatos() {
		Messagebox.show("�Debe cargar los datos para continuar!",
				"�ADVERTENCIA!", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public int advertenciaEliminarEstudiante() {
		int respuesta=Messagebox.show("�Desea retirar el estudiante de la Actividad!",
				"�ADVERTENCIA!", Messagebox.OK, Messagebox.EXCLAMATION);
		return respuesta;
	}
	public void advertenciaSeleccionarOpcion() {
		Messagebox.show("�Debe seleccionar algun registro para continuar!",
				"�ADVERTENCIA!", Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaLlenarCampos() {
		Messagebox.show("�Debe completar todos los campos para continuar!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaSeleccionarMotivo() {
		Messagebox.show("�Debe seleccionar un motivo!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaDirectorInactivo() {
		Messagebox.show("No se encuentra director de programa activo.", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaIngresarCedula() {
		Messagebox.show("�Debe ingresar un n�mero de c�dula!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaFechaEjecucion() {
		Messagebox.show("Los estudiantes con horas de asignadas deben indicar fecha de ejecuci�n.", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaCriterioBusqueda() {
		Messagebox.show("Seleccione al menos un critero de b�squeda.", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaValorFechas() {
		Messagebox.show("La fecha inicial debe ser menor a la fecha final.", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaSeleccionFuncionArbol() {
		Messagebox.show("Seleccione al menos una funcionalidad del �rbol", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaIngresarCedulaValido() {
		Messagebox.show("�Debe ingresar un n�mero de c�dula valido!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaSeleccionarTaller() {
		Messagebox.show("�Debe seleccionar un taller!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaSeleccionarEstudiante() {
		Messagebox.show("�Debe seleccionar un estudiante!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void informacionEliminarEstudiantes() {
	Messagebox.show("Los estudiantes selecionados ha sido retirados exitosamente",
			"�INFORMACI�N!", Messagebox.OK, Messagebox.INFORMATION);
	}
	public void advertenciaSeleccionarActividad() {
		Messagebox.show("�Debe seleccionar una actividad!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaSeleccionarDestinoRespaldo() {
		Messagebox.show("�Debe seleccionar el destino del respaldo!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void informacionRespaldoNoExitosa() {
		Messagebox.show("El respaldo de la BD no se efectuo",
				"�INFORMACI�N!", Messagebox.OK, Messagebox.INFORMATION);
		}
	public void informacionRespaldoExitoso() {
		Messagebox.show("El respaldo de la BD se efectuo exitosamente",
				"�INFORMACI�N!", Messagebox.OK, Messagebox.INFORMATION);
		}
	public void InformacionRestauracionEnProceso() {
		Messagebox.show("Restauraci�n en proceso",
				"�INFORMACI�N!", Messagebox.OK, Messagebox.INFORMATION);
		}
	public void advertenciaSeleccionarUbicacionRestauracion() {
		Messagebox.show("�Debe seleccionar la ruta donde se encuentra el respaldo!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaCodigoProyectoDuplicado() {
		Messagebox.show("�El c�digo del Proyecto ya esta registrado!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaSeleccionarUnProyecto() {
		Messagebox.show("�Debe seleccionar un proyecto para poder retirar a un estudiante!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaInscripcionActiva() {
		Messagebox.show("�No se puede inscribir porque el estudiante se encuentra inscrito en un proyecto!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaNoSeEfectuoModificacion() {
		Messagebox.show("�Los datos no se han modificado!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaNoSeEfectuoModificacionEnInscripcion() {
		Messagebox.show("�No se modifico la inscripcion!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaSeleccionarProyecto() {
		Messagebox.show("�Debe seleccionar un proyecto!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaSeleccionarOpcionContabilizar() {
		Messagebox.show("�Debe seleccionar una opcion para contabilizar o no las horas del alumno!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaSeleccionarOpcionCerrarProyecto() {
		Messagebox.show("�Debe seleccionar una opcion para cerrar o no el proyecto!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaSeleccionarOpcionAbrirProyecto() {
		Messagebox.show("�Debe seleccionar una opcion para abrir o no el proyecto!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void advertenciaSinActividad() {
		Messagebox.show("�Un proyecto debe tener al menos una actividad!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaNoExisteGrupo(String grupo) {
		Messagebox.show("�No existe el grupo "+grupo+"!", "�ADVERTENCIA!",
				Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaIntroduzcaNuevaClave(){
	 Messagebox.show("Introduzca nueva clave", "�ADVERTENCIA!",
             Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaClaveIncorrecta(){
		Messagebox.show("Clave actual incorrecta", "�ADVERTENCIA!",
		       Messagebox.OK, Messagebox.EXCLAMATION);
	}
	public void advertenciaIntroducirClaveActual(){
		Messagebox.show("Introduzca la clave actual", "�ADVERTENCIA!",
		       Messagebox.OK, Messagebox.EXCLAMATION);
	}
	/** Mensajes de informaci�n*/
	public void informacionActualizarDatos() {
		Messagebox.show("Los datos se han actualizado exitosamente.",
				"�INFORMACI�N!", Messagebox.OK, Messagebox.INFORMATION);
	}
	public void informacionRegistroCorrecto() {
		Messagebox.show("Los datos se han registrado exitosamente.", "�INFORMACI�N!",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	public void informacionRevertirCorrecto() {
		Messagebox.show("Se ha revertido el estudiante exitosamente", "�INFORMACI�N!",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionEliminarCorrecto() {
		Messagebox.show("El registro se ha eliminado exitosamente.", "�INFORMACI�N!",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	public void informacioRetirarCorrecto() {
		Messagebox.show("El registro se ha retirado exitosamente.", "�INFORMACI�N!",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionCerrarExito() {
		Messagebox.show("Se ha cerrado la inscripcion al proyecto  exitosamente.", "�INFORMACI�N!",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionCerrarProyectoExito() {
		Messagebox.show("Se ha cerrado el proyecto  exitosamente.", "�INFORMACI�N!",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionAbrirExito() {
		Messagebox.show("Se ha aperturado la inscripcion al proyecto exitosamente.", "�INFORMACI�N!",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public void informacionCulminacionSCE() {
		Messagebox.show("El estudiante ha culminado SCE exitosamente.", "�INFORMACI�N!",
				Messagebox.OK, Messagebox.INFORMATION);
	}
	public  void informacionCorreoEnviadoExitosamente(String nombreCompleto) {
		Messagebox.show(nombreCompleto+" revisa tu correo para recuperar tu contrase�a","�INFORMACI�N!",
				Messagebox.OK, Messagebox.INFORMATION);
	}
/** Mensajes de error*/
	public void errorCamposVacios() {
		Messagebox.show("No hay datos para mostrar", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
	}
	public void errorImposibleEliminar() {
		Messagebox.show("Imposible eliminar. Dependencias en el sistema", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
	}
	public void errorImposibleModificar() {
		Messagebox.show("Imposible modificar. Dependencias en el sistema", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
	}
	public void errorProfesorIntegranteSCE() {
		Messagebox.show("El profesor seleccionado existe como integrante de la coordinaci�n SCE", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
	}
	public void errorContrasenasNoCoinciden() {
		Messagebox.show("Las contrase�as no coinciden", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
	}
	public void errorCedulaNoApta() {
		Messagebox.show("La c�dula no pertenece a un estudiante", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
	}
	public void errorCedulaNoAcreditado() {
		Messagebox.show("La c�dula no pertenece a un estudiante acreditado", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
	}
	public void errorFormatoNoSoportado() {
		Messagebox.show("El formato no es soportado", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
	}
	public void errorPreinscripcionProyecto() {
		Messagebox.show("Estudiante inscrito en un proyecto. Debe finalizarlo antes", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
	}
	public void errorHorasEjecutadas(int cantHorasActividad) {
		Messagebox.show("Las horas ejecutadas deben ser menor o igual a "+cantHorasActividad, "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
		
	}
	public void advertenciaDireccionYaExiste(){
        Messagebox.show("�No se puede registrar, existe una direcci�n con ese nombre!", "�ADVERTENCIA!",
                Messagebox.OK, Messagebox.EXCLAMATION);
    }
    
    public void advertenciaSeleccionarDirector() {
        Messagebox.show("�Debe seleccionar un director para continuar!",
                "�ADVERTENCIA!", Messagebox.OK, Messagebox.EXCLAMATION);
    }
    public void advertenciaActividadExistente() {
        Messagebox.show("�Ya existe este nombre para una actividad asignada!",
                "�ADVERTENCIA!", Messagebox.OK, Messagebox.EXCLAMATION);
    }

    public void advertenciaCargarDatosDirecccion() {
        Messagebox.show("�Debe cargar los datos de la direcci�n para continuar!",
                "�ADVERTENCIA!", Messagebox.OK, Messagebox.EXCLAMATION);
    }
    public void errorPreinscripcionProyectoActiva() {
		Messagebox.show("Estudiante ya esta pre-inscrito en ese proyecto.", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
	}
    public void errorProyectoInscripcionesAbiertas(){
    	Messagebox.show("Proyecto con inscripciones abiertas, debe cerrar las inscripcioines para continuar.", "�ERROR!",
				Messagebox.OK, Messagebox.ERROR);
    }
}
