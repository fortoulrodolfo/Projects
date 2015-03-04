package siagsce.viewmodel.seguridad;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Window;
import siagsce.herramientas.MensajesEmergentes;
import siagsce.modelo.general.UtilidadesSiagsce;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;

/**
 * VMRestaurarInformacionBD es el viewmodel encargado 
 * de gestionar la vista RestaurarInformacion.zul
 * @author Iterator
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMRestaurarInformacionBD {	

	private MensajesEmergentes mensajesAlUsuario = new MensajesEmergentes();
	private String ruta = UtilidadesSiagsce.obtenerDirectorio();
	private String txtPaquetesZip = "";
	private String nombreRespaldo;
	private String selected = "";
	private Listitem respaldoSeleccionado = null;
	//Accediendo a los archivos en el directorio
	private FileInputStream lector;
	private File directorio = null;
	private String[] listaDirectorio2 = null;
	private List<String> listaDirectorio = new ArrayList<String>();
	@Wire
	private Window winRestaurarInformacion;
	@Wire
	private Div divPrincipal;
	
	
	/**
	 * Al iniciar la ventana se carga el directorio donde estan
	 *  guardado todos los backups.
	 */
	@Init
	public void init() {
		// initialization code
		cargarDirectorioSVN();
		
	}
	/**
	 * para poder conectarse con los componentes en la vista
	 */
	 @AfterCompose 
	    public void afterCompose(@ContextParam(ContextType.VIEW) Component winRestaurarInformacion){
	        Selectors.wireComponents(winRestaurarInformacion, this, false);
	        selected="local";
	        
	    }
	 
	@Command
	@NotifyChange({"listaDirectorio2","listaDirectorio","directorio"})
	public void cargarDirectorioSVN() {
		directorio = new File(ruta+"Siagsce.webapp/WebContent/WEB-INF/Siagsce/administracionBaseDatos/respaldosBD");
		listaDirectorio2 = directorio.list();
		for (int i=0; i<listaDirectorio2.length;i++)
		{
			if (!(listaDirectorio2[i].equals(".svn")))
			{
				listaDirectorio.add(listaDirectorio2[i]);
			}
		}
	}
	/**
	 * Nos permite selecionar la ruta donde se encuentra el respaldo.
	 */
	@Command
	@NotifyChange({"txtPaquetesZip"})
	public void seleccionarRuta(){
		JFileChooser chooser = new JFileChooser();
		// Titulo que llevara la ventana
		chooser.setDialogTitle("Examinar...");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showOpenDialog(null);

		// Si seleccionamos algún archivo retornaremos su ubicacion
		if (chooser.getSelectedFile() != null) {
			txtPaquetesZip = chooser.getSelectedFile().getPath();
		}
	}
	/**
	 * Metodo que permite restaurar la base de datos con el backup seleccionado,
	 * @param groupboxDispositivo representa el groupbox donde seleccione el respaldo.
	 * @param backupseleccionado representa la data se que se va a guardar en la BD.
	 */
	@NotifyChange({ "txtPaquetesZip", "ruta","selected","listaDirectorio","listaDirectorio2","directorio","respaldoSeleccionado"})
	@Command
	public void restaurarInformacion(@BindingParam("aplication") WebApp application, @BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo, @BindingParam("divDispositivo") Div divArchivosLocal, @BindingParam("backupseleccionado") Listitem backupseleccionado){
		if (selected.equals("local") || selected.equals("dispositivo")){
			try {
				borrarEsquema("public");
				crearEsquema("public");
				Properties props = new Properties();
			    lector = new java.io.FileInputStream(application.getRealPath("WEB-INF/siagsce/configuracionbd.properties"));
				props.load(lector);
				lector.close();
				Runtime r = Runtime.getRuntime();
				String archivoBD = null;
				if (!(txtPaquetesZip.equals(""))) {
					archivoBD = txtPaquetesZip;
				} else if (backupseleccionado!=null) {
					//Trabajando con el proyecto directamente
					if(!backupseleccionado.getLabel().equals(""))
					archivoBD = ruta + "Siagsce.webapp/WebContent/WEB-INF/Siagsce/administracionBaseDatos/respaldosBD" + "/"+ backupseleccionado.getLabel();
				}
				Process p;
				ProcessBuilder pb;
				r = Runtime.getRuntime();
				List<String> lista = new ArrayList<String>();
				lista.add(props.getProperty("pgrestore"));
				lista.add("-i");
				lista.add("-h");
				lista.add(props.getProperty("host"));
				lista.add("-p");
				lista.add(props.getProperty("puerto"));
				lista.add("-U");
				lista.add(props.getProperty("usuario"));
				lista.add("-d");
				lista.add(props.getProperty("nombrebd"));
				lista.add("-v");
				lista.add(archivoBD);
				pb = new ProcessBuilder(lista);
				pb.environment().put("PGPASSWORD", props.getProperty("contrasenna"));
				pb.redirectErrorStream(true);
				p = pb.start();
				mensajesAlUsuario.InformacionRestauracionEnProceso();
				limpiar();
				divArchivosLocal.setVisible(false);
				groupboxDispositivo.setVisible(false);
			} catch (IOException e) {
				e.getStackTrace();
//				mensajesAlUsuario.informacionRestauracionNoExitosa();
			}
		}else{
			mensajesAlUsuario.advertenciaSeleccionarUbicacionRestauracion();
		}	
	}
	/** Borra el schema que concuerde con el parametro.
	 * @param nombreEsquema es el nombre del esquema que va a ser borrado.
	 */
	public void borrarEsquema(String nombreEsquema) {
		String driver = "org.postgresql.Driver";
		String connectString = "jdbc:postgresql://localhost:5432/bd_siagsce";
		String user = "postgres";
		String password = "postgres";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(connectString, user,password);
			Statement stmt = con.createStatement();			       
			int count = stmt.executeUpdate("DROP SCHEMA " + nombreEsquema + " CASCADE");
			stmt.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
		}
	}
	/** Crea el schema.
	 * @param nombreEsquema es el nombre del esquema que va a ser creado.
	 */
	public void crearEsquema(String nombreEsquema) {
		String driver = "org.postgresql.Driver";
		String connectString = "jdbc:postgresql://localhost:5432/bd_siagsce";
		String user = "postgres";
		String password = "postgres";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(connectString, user,password);
			Statement stmt = con.createStatement();
			int count = stmt.executeUpdate("CREATE SCHEMA " + nombreEsquema + "");
			stmt.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
		}
	}
	
	@NotifyChange({"divPrincipal","winRestaurarInformacion"})
	@Command
	public void habilitarGroupBoxDispotivo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo, @BindingParam("divDispositivo") Div divArchivosLocal) {
		groupboxDispositivo.setVisible(true);
		divArchivosLocal.setVisible(false);
	}
	
	@NotifyChange({"divPrincipal","winRestaurarInformacion"})
	@Command
	public void deshabilitarGroupBoxDispotivo(@BindingParam("groupboxDispositivo") Groupbox groupboxDispositivo, @BindingParam("divDispositivo") Div divArchivosLocal) {
		groupboxDispositivo.setVisible(false);
		divArchivosLocal.setVisible(true);
	}
	/**
	 * Inicializa las variables
	 */
	@NotifyChange({ "txtPaquetesZip","selected","listaDirectorio2","directorio"})
	@Command
	public void limpiar(){
		txtPaquetesZip = "";
		selected = "";
		listaDirectorio2 = null;
		directorio = null;
	}
	
	/**
	 * Cerrar Ventana
	 */
	
	@Command
	public void cerrarVentana(){
		winRestaurarInformacion.detach();
	}
	public String[] getListaDirectorio2() {
		return listaDirectorio2;
	}
	
	public void setListaDirectorio2(String[] listaDirectorio2) {
		this.listaDirectorio2 = listaDirectorio2;
	}

	public String getTxtPaquetesZip() {
		return txtPaquetesZip;
	}

	public void setTxtPaquetesZip(String txtPaquetesZip) {
		this.txtPaquetesZip = txtPaquetesZip;
	}

	public String getNombreRespaldo() {
		return nombreRespaldo;
	}

	public void setNombreRespaldo(String nombreRespaldo) {
		this.nombreRespaldo = nombreRespaldo;
	}
	
	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	public List<String> getListaDirectorio() {
		return listaDirectorio;
	}

	public void setListaDirectorio(List<String> listaDirectorio) {
		this.listaDirectorio = listaDirectorio;
	}
	
	public Listitem getRespaldoSeleccionado() {
		return respaldoSeleccionado;
	}

	public void setRespaldoSeleccionado(Listitem respaldoSeleccionado) {
		this.respaldoSeleccionado = respaldoSeleccionado;
	}
}