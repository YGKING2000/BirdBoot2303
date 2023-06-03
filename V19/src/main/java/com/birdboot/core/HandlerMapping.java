package com.birdboot.core;

import com.birdboot.annotations.Controller;
import com.birdboot.annotations.RequestMapping;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description
 * @ClassName HandlerMapping
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/23 09:44
 * @Version 1.0
 */
public class HandlerMapping {
    private static final Map<String, Method> mapping = new HashMap<>();
    private static File controllerDir;

    static {
        try {
            controllerDir = new File(Objects.requireNonNull(
                    HandlerMapping.class.getClassLoader().getResource("./com/birdboot/controller")).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        initMapping();
    }

    private static void initMapping() {
        try {
            File[] files = controllerDir.listFiles(file -> file.getName().endsWith(".class"));
            assert files != null;
            for (File file : files) {
                String fileName = file.getName().replace(".class", "");
                Class<?> cls = Class.forName("com.birdboot.controller." + fileName);
                if (cls.isAnnotationPresent(Controller.class)) {
                    Method[] methods = cls.getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping req = method.getAnnotation(RequestMapping.class);
                            mapping.put(req.value(), method);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Method getMethod(String path) {
        return mapping.get(path);
    }
}
