package com.birdboot.core;

import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description 客户端处理器
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
            // 创建HTTP服务器请求对象
            HttpServletRequest request = new HttpServletRequest(socket);

            // 1.创建HTTP服务器响应对象
            HttpServletResponse response = new HttpServletResponse(socket);
            // 2.处理请求
            DispatcherServlet servlet = new DispatcherServlet();
            servlet.service(request, response);
            // 3.发送响应
            response.response();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
