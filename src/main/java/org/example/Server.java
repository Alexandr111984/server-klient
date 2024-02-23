package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final Integer port = 8080;

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(port)) {// порт можете выбрать любой в доступном диапазоне 0-65536.
            System.out.println("New connection accepted");
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения)
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    final String name = in.readLine();
                    System.out.printf(String.format(" %s, your port is %d", name, clientSocket.getPort()));
                    out.println(clientSocket.getPort());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
