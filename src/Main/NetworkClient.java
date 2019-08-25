package Main;

import java.net.*;
import java.io.*;

public class NetworkClient {

    private static NetworkClient instance = null;

    private final static String hostAddress = "69.212.49.71";      // Zack's Laptop Public IP
    private final static int tcpPort = 7000;        // Hardcoded - must match server's port
    private Socket serverTCPSocket;
    private PrintStream tcpOutput;

    private NetworkClient() {
    }

    public static NetworkClient getInstance() {
        if (instance == null) {
            instance = new NetworkClient();
        }

        return instance;
    }


    public void sendBugReport(String header, String bugDescription, String bugEncounter) {
        openConnection();
        if (tcpOutput == null)
            return;
        tcpOutput.println(header);
        tcpOutput.println(bugDescription);
        tcpOutput.println(bugEncounter);
        tcpOutput.flush();
        closeConnection();
    }

    public void sendMessage(String header, String content) {
        openConnection();
        if (tcpOutput == null)
            return;
        switch (header) {
            case ("feedback"):
                tcpOutput.println(header);
                tcpOutput.println(content);
                tcpOutput.flush();
                break;
            default:
        }
        closeConnection();
    }

    private void openConnection() {
        try {
            serverTCPSocket = new Socket(InetAddress.getLocalHost(), tcpPort);
            tcpOutput = new PrintStream(serverTCPSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("IOException during Network Client creation");
            System.out.println("Server may not be operational");
        }
    }


    private void closeConnection() {
        try {
            cleanup();
        } catch (IOException e) {
            System.out.println("IOException during cleanup - unable to close printstream and/or socket");
        }
    }

    private void cleanup() throws IOException {
        if (tcpOutput == null || serverTCPSocket == null)
            return;
        tcpOutput.close();
        serverTCPSocket.close();
    }

}
