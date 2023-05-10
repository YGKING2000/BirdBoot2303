package com.birdboot.core;

import com.birdboot.annotations.Controller;
import com.birdboot.annotations.RequestMapping;
import com.birdboot.controller.UserController;
import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Description 服务器分发器
 * @ClassName DispatcherServlet
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/18 17:05
 * @Version 1.0
 */
public class DispatcherServlet {
    private static File baseDir;

    static {
        try {
            baseDir = new File(Objects.requireNonNull(
                    DispatcherServlet.class.getClassLoader().getResource("./static")).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) {
        // 请求资源的路径(不包含参数)
        String path = request.getRequestURI();
        Method method = HandlerMapping.getMethod(path);
        if (method != null) {
            try {
                Object obj = method.getDeclaringClass().newInstance();
                method.invoke(obj, request, response);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                response.setStatusCode(500);
                response.setStatusReason("Internal Server Error");
            }
        } else {
            // 创建消息正文所需要的文件对象
            File file = new File(baseDir, path);
            if (!file.isFile()) {
                // 设置状态行的状态码、状态说明
                response.setStatusCode(404);
                response.setStatusReason("NotFound");
                // 修改响应正文所指向的文件
                file = new File(baseDir, "404.html");
                // 设置响应正文
            }
            response.setContentFile(file);
        }
    }
}
