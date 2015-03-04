package siagsce.modelo.general;

import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.zkoss.zul.Messagebox;

import com.sun.mail.util.MailSSLSocketFactory;


public class EnviarCorreo {

	private final Properties properties = new Properties();

	private final String username = "siagsce.ucla@gmail.com"; //Nota: este correo emisor está aun sin existencia.
	private final String password = "serviciocomunitario";

	private Session session;

	private void init() {
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.socketFactory", sf);
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.mail.sender",username);
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
	}
	//función informar a los nuevos usuarios su usuario y contraseña
		public void sendEmailOlvidoContrasena(String correoReceptor,String username,String contrasena) {
			init();
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
				message.setSubject("Has solicitado una nueva contraseña para tu cuenta en SIAGSCE");
				message.setText("Usted puede acceder a SIAGSCE  con el siguiente Nombre de usuario y clave:"
						+ "\nNombre de Usuario:"+ username
						+ "\nContraseña: " + contrasena
						+ "\n\nSistema de Apoyo a la Gestión del Servicio Comunitario Estudiantil"
						+ "del Decanato de Ciencia y Tecnologia(UCLA)");
				Transport.send(message);
			} catch (MessagingException me) {
				Messagebox.show("¡No se envió correo al usuario por problemas de conexión!", "¡ADVERTENCIA!",
						Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}

	//función informar a los nuevos usuarios su usuario y contraseña
	public void sendEmail(String correoReceptor,String rol, String username,String contrasena) {
		init();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			message.setSubject("Bienvenido a SIAGSCE");
			message.setText("Usted ha sido acreditado"
					+ " para formar parte de SIAGSCE obteniendo el titulo de :"+rol+" ."
					+ "\nSIAGSCE le informa que ahora podra acceder a nuestro portal:"
					+ "\n\nNombre de Usuario: " + username
					+ "\nContraseña: " + contrasena
					+ "\n\nSistema de Apoyo a la Gestión del Servicio Comunitario Estudiantil"
					+ "del Decanato de Ciencia y Tecnologia(UCLA)");
			Transport.send(message);
		} catch (MessagingException me) {
			Messagebox.show("¡No se envió correo al usuario por problemas de conexión!", "¡ADVERTENCIA!",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	//función informar a los nuevos usuarios su usuario y contraseña
		public void sendEmailInfoDesacreditacion(String correoReceptor,String rol) {
			init();
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
				message.setSubject("infomación de SIAGSCE");
				message.setText("SIAGSCE(Sistema de Apoyo a la Gestión del Servicio Comunitario Estudiantil)"
						+ "\nle informa que su acreditación ha sido revertida. Si tiene alguna inquietud por favor dirigirse a la oficina del SCE. "
						+ "");
				Transport.send(message);
			} catch (MessagingException me) {
				Messagebox.show("¡No se envió correo al usuario por problemas de conexión!", "¡ADVERTENCIA!",
						Messagebox.OK, Messagebox.EXCLAMATION);
			}
		}
		//función informar a los nuevos usuarios su usuario y contraseña
				public void sendEmailInfo(String correoReceptor,String rol) {
					init();
					try {
						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
						message.setSubject("infomación de SIAGSCE");
						message.setText("SIAGSCE(Sistema de Apoyo a la Gestión del Servicio Comunitario Estudiantil)"
								+ "\nle informa que ha dejado de ejercer el rol de "+ rol +" . Si tiene alguna inquietud por favor dirigirse a la oficina del SCE. "
								+ "");
						Transport.send(message);
					} catch (MessagingException me) {
						Messagebox.show("¡No se envió correo al usuario por problemas de conexión!", "¡ADVERTENCIA!",
								Messagebox.OK, Messagebox.EXCLAMATION);
					}
				}
}
