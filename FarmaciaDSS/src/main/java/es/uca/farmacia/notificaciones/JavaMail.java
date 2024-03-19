package es.uca.farmacia.notificaciones;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class JavaMail 
{
	public static void sendEmail(String to, String subject, String body)
	{
		try
		{
			Properties props = new Properties();

			// Nombre del host de correo, es smtp.gmail.com
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			// TLS si está disponible
			props.setProperty("mail.smtp.starttls.enable", "true");
			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port","587");
			// Nombre del usuario
			props.setProperty("mail.smtp.user", "farmaciadss.uca@gmail.com");
			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);
			
			MimeMessage message = new MimeMessage(session);
			
			// Quien envia el correo
			message.setFrom(new InternetAddress("farmaciadss.uca@gmail.com"));
			// A quien va dirigido
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(body);

			Transport t = session.getTransport("smtp");
			t.connect("farmaciadss.uca@gmail.com","mqzfkqfjotiqraio");
			t.sendMessage(message,message.getAllRecipients());
			t.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
