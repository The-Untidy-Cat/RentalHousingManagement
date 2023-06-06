/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.theuntidycat.rhm.controller;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author SHeroAnh
 */
public class SendEmail {
    AppPropertiseController appConfig = new AppPropertiseController();
    final String fromEmail = appConfig.getData("email");
    final String password = appConfig.getData("password");
    final String smtpHost = appConfig.getData("smtp_host");
    final String smtpPort = appConfig.getData("port");
    final String ssl = appConfig.getData("ssl");
    Properties props = new Properties();

    public SendEmail () {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", ssl);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort );
    }
    public void notificationInvoice(String toEmail) {

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromEmail));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // Send the actual HTML message, as big as you like
            message.setContent(
                    "<h1>This is actual message embedded in HTML tags</h1>",
                    "text/html");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
