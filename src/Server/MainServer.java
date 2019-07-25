package Server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer {

    private static Socket socket;
    private static final int tcpPort = 7000;

    public static void main(String[] args) {
        ExecutorService tcpThreadPool = Executors.newCachedThreadPool();
        System.out.println("Server Initialized");
        try {
            ServerSocket listener = new ServerSocket(tcpPort);

            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        while ((socket = listener.accept()) != null) {
                            tcpThreadPool.submit(new MailThread(socket));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            System.out.println("Master server thread started");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
