package com.ita.provapp.server.mailsender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailSender {
    private String host;
    private String username;
    private String password;
    private Properties properties;
    private static Logger logger = LoggerFactory.getLogger(MailSender.class);

    public MailSender(String host, int port, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;

        properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", String.valueOf(port));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    public void send(String toAddress, String subject, String text) throws MailSenderException {
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {
            logger.debug(String.format("Email sending from address: [%s] to: [%s], subject: [%s], message: [%s]", host, toAddress, subject, host ));
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            logger.info(String.format("Email massage to: [%s] send successfully", toAddress));
        } catch (MessagingException e) {
            String error = String.format("Error during email message sending to: [%s]: %s", toAddress, e.getMessage());
            logger.info(error);
            throw new MailSenderException(error);
        }
    }
}
