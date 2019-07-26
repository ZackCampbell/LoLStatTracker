package Main;

import java.net.*;
import java.io.*;

public class NetworkClient {

    private static NetworkClient instance = null;

    private final static String hostAddress = "localhost";      // TODO: Update with IP of server
    private final static int tcpPort = 7000;        // Hardcoded - must match server's port
    private Socket serverTCPSocket;
    private PrintStream tcpOutput;

    private NetworkClient() {
        try {
            serverTCPSocket = new Socket(hostAddress, tcpPort);
            tcpOutput = new PrintStream(serverTCPSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("IOException during Network Client creation");
            System.out.println("Server may not be operational");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                cleanup();
            } catch (IOException e) {
                System.out.println("IOException during Network Client cleanup");
                e.printStackTrace();
            }
        }));
    }

    public static NetworkClient getInstance() {
        if (instance == null) {
            instance = new NetworkClient();
        }

        return instance;
    }


    public void sendBugReport(String header, String bugDescription, String bugEncounter) {
        if (tcpOutput == null)
            return;
        tcpOutput.println(header);
        tcpOutput.flush();
        tcpOutput.println(bugDescription);
        tcpOutput.flush();
        tcpOutput.println(bugEncounter);
        tcpOutput.flush();
    }

    public void sendMessage(String header, String content) {
        if (tcpOutput == null)
            return;
        switch (header) {
            case ("feedback"):
                tcpOutput.println(header);
                tcpOutput.flush();
                tcpOutput.println(content);
                tcpOutput.flush();
                break;
            default:
        }

    }

    private void cleanup() throws IOException {
        if (tcpOutput == null || serverTCPSocket == null)
            return;
        tcpOutput.close();
        serverTCPSocket.close();
    }

}
