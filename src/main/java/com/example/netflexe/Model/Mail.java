package com.example.netflexe.Model;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;


public class Mail {

    public Mail(){}

    public void sendMail(Reservation reservation, String mailTo){

        final String username = "netflece@gmail.com";
        final String password = "Netflece01";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("netflece@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse( mailTo)
            );
            message.setSubject("Votre réservation pour: " + reservation.getMovie().getTitle());
            message.setText("Bonjour,"
                    + "\n\n Vous avez réservé une séance pour le film suivant: \n\n" +
                    reservation.getMovie().getTitle()
                    + "\n\n Votre séance a lieu au cinéma : " + reservation.getNomCinema() + "\n Horaire : " + reservation.getHoraire() +"\nDate : " + reservation.getDate()
             + "\n\nOn vous attend en salle,\nL'Equipe Netflece");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

