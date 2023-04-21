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
    private static File dir;
    private static File baseDir;

    static {
        try {
            dir = new File(Objects.requireNonNull(
                    DispatcherServlet.class.getClassLoader().getResource(".")).toURI());
            baseDir = new File(dir, "static");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) {
        // 请求资源的路径(不包含参数)
        String path = request.getRequestURI();


        File directory = new File(new File(dir, "/com"), "/birdboot/controller");
        File[] files = directory.listFiles(file -> file.getName().endsWith(".class"));
        System.out.println(Arrays.toString(files));
        assert files != null;
        for (File file : files) {
            String className = file.getName().replace(".class", "");
            try {
                Class cls = Class.forName("com.birdboot.controller." + className);
                if (cls.isAnnotationPresent(Controller.class)) {
                    Method[] methods = cls.getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String reqPath = requestMapping.value();
                            if (reqPath.equals(path)) {
                                Object obj = cls.newInstance();
                                method.invoke(obj, request, response);
                                return;
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
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
