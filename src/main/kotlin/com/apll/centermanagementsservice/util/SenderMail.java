package com.apll.centermanagementsservice.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class SenderMail {

    public static String sendMail(String receiverMail) throws MessagingException {
        String to = receiverMail;
        String subject = "hello";
        String msg =" Hello...... ";
        final String from ="deeksha4java@gmail.com";
        final  String password ="***********";



        Properties props = new Properties();
        props=System.getProperties();

        //props.setProperty("mail.smtp.user", "name");
        //  props.setProperty("mail.smtp.password", password);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("smtp.gmail.com", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });

        //session.setDebug(true);
        Transport transport = session.getTransport();
        // InternetAddress addressFrom = new InternetAddress(from);

        MimeMessage message = new MimeMessage(session);
        // message.setSender(addressFrom);
        message.setSubject(subject);
        message.setContent(msg, "text/plain");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        transport.connect();
        Transport.send(message);
        transport.close();
        return "Mail Send";
    }

    public static String sendAllMail(List<String> receiversMail) throws MessagingException{
        for(String s:receiversMail){
            sendMail(s);
        }
        return "Mail Send";
    }


}