package com.challenge.springbootchallenge.service;

import com.challenge.springbootchallenge.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static String USER_NAME = "mpanoc6";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "ccsjeklvysdfltaw"; // GMail password
    private static String RECIPIENT = "mchriscoder@gmail.com";

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    @Override
    public void sendNotification(User user, String subject, String msg) {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { user.getEmail() }; // list of recipient email addresses

        sendFromGMail(from, pass, to, subject, msg);
    }
}