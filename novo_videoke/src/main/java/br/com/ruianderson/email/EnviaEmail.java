package br.com.ruianderson.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviaEmail {
	
	private static Session criarSessionMail() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.ruianderson.com.br");
		//props.put("mail.smtp.socketFactory.port", 465);
		//props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.port", 587);
		
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("videoke@ruianderson.com.br",
								"rc42301886");
					}
				});
		session.setDebug(true);
		return session;
	}

	@SuppressWarnings("unused")
	public  void enviarEmail(String destinatario, String nome) throws AddressException, MessagingException {
		
		String msg = "Prezado "+nome+" para ativar seu cadastro clique no link abaixo!";
		String assunto = "Videoke ativação de cadastro.";
		
		String email = destinatario;
		String remetente = "videoke@ruianderson.com.br";
		
		Message message = new MimeMessage(criarSessionMail());
		message.setFrom(new InternetAddress(remetente)); 
		
		Address[] toUser = InternetAddress.parse(email.trim().toLowerCase());
		
		message.setRecipients(Message.RecipientType.TO, toUser);
        message.setSubject(assunto);// Assunto
        message.setContent(msg, "text/html");
          
         /** Método para enviar a mensagem criada */ 
		Transport.send(message); 
		

          
	}

}
