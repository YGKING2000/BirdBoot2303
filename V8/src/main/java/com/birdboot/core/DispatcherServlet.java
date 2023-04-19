package com.birdboot.core;

import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * @Description
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
        // 请求资源的路径
        String path = request.getUri();
        File file = new File(baseDir, path);
        if (path.matches("/")) {
            file = new File(baseDir, "index.html");
        } else if (!file.isFile()) {
            file = new File(baseDir, "404.html");
            response.setStatusCode(404);
            response.setStatusReason("NotFound");
        }
        response.setContentFile(file);
    }
}
