package com.birdboot.core;

import java.io.IOException;
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
            // 在创建服务器BirdBootApplication对象时，为服务器创建套接字以及指定端口
            serverSocket = new ServerSocket(8088);
            System.out.println("服务器启动完毕!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                System.out.println("正在等待客户端请求...");
                // 等待客户端连接
                Socket socket = serverSocket.accept();
                // 为每个客户端创建分配一个线程
                new Thread(new ClientHandler(socket)).start();
                System.out.println("成功处理客户端!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 创建一个服务器BirdBootApplication对象
        BirdBootApplication bird = new BirdBootApplication();
        // 启动服务器
        bird.start();
    }
}

