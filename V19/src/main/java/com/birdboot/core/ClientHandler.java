package com.birdboot.core;

import com.birdboot.http.EmptyRequestException;
import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;

import javax.annotation.Resource;
import java.io.*;
import java.lang.annotation.Annotation;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 客户端处理器
 * @ClassName ClientHandler
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/17 10:21
 * @Version 1.0
 */
@Resource
public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 1.创建HTTP服务器请求对象
            HttpServletRequest request = new HttpServletRequest(socket);
            // 2.创建HTTP服务器响应对象
            HttpServletResponse response = new HttpServletResponse(socket);
            // 3.处理请求
            DispatcherServlet servlet = new DispatcherServlet();
            servlet.service(request, response);
            // 4.发送响应
            response.response();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmptyRequestException e) {

        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
