package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    while (!str.isEmpty()) {
                        if (str.contains("=Exit ")) {
                            server.close();
                        } else if (str.contains("=Hello ")) {
                            out.write("Hello".getBytes());
                        } else if (str.contains("=")) {
                            out.write("What".getBytes());
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}