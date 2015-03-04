package siagsce.viewmodel.maestros;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Box;




public class showView extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
    @Wire
    private Box ini;
    @Wire
    private Box direccion;
    @Wire
    private Box director;
    @Wire
    private Box profesores;
    @Wire
    private Box coordinacion;
    @Wire
    private Box estudiantes;
    @Wire
    private Box miembros;
    @Wire
    private Box acreditarProfe;
    @Wire
    private Box exhonerados;
    @Wire
    private Box planificarTaller;
    @Wire
    private Box inscripcionTaller;
    @Wire
    private Box evaluacionTaller;
    @Wire
    private Box proyectos;
    @Wire
    private Box evaluarPreinscripcion;
    @Wire
    private Box inscripcionEstudiante;
    @Wire
    private Box asignarActividades;
    @Wire
    private Box registrarPrestacion;
  
    
    
    @Listen("onClick = #direccion2")
    public void search1(){
        ini.setVisible(false);
        direccion.setVisible(true);
        director.setVisible(false);
        registrarPrestacion.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
        
    }
    @Listen("onClick = #director2")
    public void search2(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(true);
        registrarPrestacion.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #profesores2")
    public void search3(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(true);
        registrarPrestacion.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #coordinacion2")
    public void search4(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(true);
        registrarPrestacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #estudiantes2")
    public void search5(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(true);
        registrarPrestacion.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #miembros2")
    public void search6(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(true);
        registrarPrestacion.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #acreditarProfe2")
    public void search7(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(true);
        registrarPrestacion.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #exhonerados2")
    public void search8(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(true);
        registrarPrestacion.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #planificarTaller2")
    public void search9(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(true);
        registrarPrestacion.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #inscripcionTaller2")
    public void search10(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(true);
        registrarPrestacion.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #evaluacionTaller2")
    public void search11(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(true);
        registrarPrestacion.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #proyectos2")
    public void search12(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(true);
        registrarPrestacion.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #evaluarPreinscripcion2")
    public void search13(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(true);
        registrarPrestacion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #inscripcionEstudiante2")
    public void search14(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(true);
        registrarPrestacion.setVisible(false);
        asignarActividades.setVisible(false);
    }
    @Listen("onClick = #asignarActividades2")
    public void search15(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(true);
        registrarPrestacion.setVisible(false);
    }
    
    @Listen("onClick = #registrarPrestacion2")
    public void search16(){
    	ini.setVisible(false);
        direccion.setVisible(false);
        director.setVisible(false);
        profesores.setVisible(false);
        coordinacion.setVisible(false);
        estudiantes.setVisible(false);
        miembros.setVisible(false);
        acreditarProfe.setVisible(false);
        exhonerados.setVisible(false);
        planificarTaller.setVisible(false);
        inscripcionTaller.setVisible(false);
        evaluacionTaller.setVisible(false);
        proyectos.setVisible(false);
        evaluarPreinscripcion.setVisible(false);
        inscripcionEstudiante.setVisible(false);
        asignarActividades.setVisible(false);
        registrarPrestacion.setVisible(true);
        
    }
    
}
