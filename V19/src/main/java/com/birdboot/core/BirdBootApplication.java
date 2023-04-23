package com.birdboot.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 主启动类
 * @ClassName BirdBootApplication
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/17 09:41
 * @Version 1.0
 */
public class BirdBootApplication {
    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    public BirdBootApplication() {
        try {
            System.out.println("服务器正在启动...");
            // 在创建服务器BirdBootApplication对象时，为服务器创建套接字以及指定端口
            serverSocket = new ServerSocket(8088);
            threadPool = Executors.newFixedThreadPool(50);
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
                Thread thread = new Thread(new ClientHandler(socket));
                // 将新建的线程添加进入线程池
                threadPool.execute(thread);
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

