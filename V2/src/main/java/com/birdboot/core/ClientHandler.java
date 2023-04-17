package com.birdboot.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @Description
 * @ClassName ClientHandler
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/17 10:21
 * @Version 1.0
 */
public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            int message;
            int count = 0;
            StringBuilder line = new StringBuilder();
            while ((message = in.read()) != -1) {
                // System.out.print((char) message);
                line.append((char) message);
                if (count == 1 && message == 10) {
                    break;
                } else {
                    count = 0;
                }
                if (message == 13) {
                    count++;
                }
            }
            System.out.println(line.toString().trim());
            String[] list = line.toString().split("\\s");
            String method = list[0];
            String url = list[1];
            String protocol = list[2];
            System.out.println(method);
            System.out.println(url);
            System.out.println(protocol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
