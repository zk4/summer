package org.tomdog;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zk on 26/05/2018.
 */
public class Bootstrap {
    static String CRLF = "\r\n";

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8822);
            ExecutorService executor = Executors.newFixedThreadPool(100);
            while (true) {
                Socket clientSocket = serverSocket.accept();

                Runnable worker = new Runnable() {
                    @Override
                    public void run() {

                        try {

                            Servlet servlet = new ServletImpl();
                            servlet.service(new Request(clientSocket.getInputStream()),
                                    new Response(clientSocket.getOutputStream()));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                };

                executor.execute(worker);

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
