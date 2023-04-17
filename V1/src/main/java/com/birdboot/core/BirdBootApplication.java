package com.birdboot.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description 主启动类
 * @ClassName BirdBootApplication
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/17 09:41
 * @Version 1.0
 */
public class BirdBootApplication {
    private ServerSocket serverSocket;

    public BirdBootApplication() {
        try {
            System.out.println("服务器正在启动...");
            serverSocket = new ServerSocket(8088);
            System.out.println("服务器启动完毕!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void start() {
        try {
            System.out.println("一个客户端正在连接...");
            Socket socket = serverSocket.accept();
            System.out.println("一个客户端连接成功!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private final String host;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            this.host = socket.getInetAddress().getHostAddress();
        }

        @Override
        public void run() {
            try {
                InputStream fis = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BirdBootApplication bird = new BirdBootApplication();
        bird.start();
    }
}
