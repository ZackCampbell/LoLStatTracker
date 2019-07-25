package Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

import static Utils.Utils.initializeLogger;

public class MailThread extends Thread {

    private static Logger LOGGER = initializeLogger(MailThread.class.getName());
    private Socket clientSocket;
    private String subject;
    private final String to = "";   // TODO: Add destination email address here
    private final String from = ""; // TODO: Add source email address here
    private Properties properties;
    private Session session;

    public MailThread(Socket socket) {
        this.clientSocket = socket;
        properties = new Properties();
        session = Session.getDefaultInstance(properties, null); // Get the default Session object.

    }

    @Override
    public void run() {
        Scanner scanner;
        try {
            scanner = new Scanner(clientSocket.getInputStream());
            String header = scanner.nextLine();
            switch (header) {
                case ("bugReport"):
                    this.subject = "<insert-app-name-here> - Bug Report";    // TODO: Update with app name
                    break;
                case ("feedback"):
                    this.subject = "<insert-app-name-here> - Feedback";      // TODO: Update with app name
                    break;
                default:
                    System.out.println("Invalid request header");
                    LOGGER.log(Level.WARNING, "Invalid request header for mail thread");
                    return;

            }

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(this.subject);
            MimeMultipart multiPart = new MimeMultipart();

            while (true) {
                String token = scanner.nextLine();
                if (token == null)
                    break;
                MimeBodyPart bodyPart = new MimeBodyPart();
                bodyPart.setText(token);
                multiPart.addBodyPart(bodyPart);
            }
            message.setContent(multiPart);
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
