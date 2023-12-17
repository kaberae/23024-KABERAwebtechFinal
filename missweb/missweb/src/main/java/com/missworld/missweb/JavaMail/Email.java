package com.missworld.missweb.JavaMail;

import com.missworld.missweb.Models.Users;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
        private static String USER_NAME = "Nahimanasteven00";
        private static String PASSWORD = "lkradsakajuugtht";
        private static String RECIPIENT = "Nahimanasteven00@gmail.com";

        public void onVotingEmail(Users user,long contId){
            String to [] ={user.getEmail()};
            String body = "Subject: Thank You for Voting in MISS WORLD\n\n" +
                    "Dear " + user.getNames() + ",\n\n" +
                    "We hope this email finds you well. We are writing to express our sincere gratitude for your participation in the MISS WORLD platform and for taking the time to vote for Contestant Number " + contId + ".\n\n" +
                    "Your vote has been successfully registered, and we truly appreciate your commitment and support. Your contribution plays a vital role in shaping the MISS WORLD competition.\n\n" +
                    "If, by any chance, you received this email in error or if you did not vote in the MISS WORLD competition, please disregard this message.\n\n" +
                    "Once again, thank you for being a part of MISS WORLD and for making a difference. If you have any further questions or need assistance, please feel free to reach out to us.\n\n" +
                    "Best Regards,\n" +
                    "MISS WORLD Team";


            String from=USER_NAME;
            String subject="MISS WORLD VOTING";
            Properties props = System.getProperties();
            String host = "smtp.gmail.com";
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", USER_NAME);
            props.put("mail.smtp.password", PASSWORD);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);

            try {
                message.setFrom(new InternetAddress(USER_NAME));
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
                transport.connect(host, USER_NAME, PASSWORD);
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
}
