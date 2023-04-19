package com.birdboot.http;

import com.birdboot.core.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @Description
 * @ClassName HttpServletResponse
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/18 13:13
 * @Version 1.0
 */
public class HttpServletResponse {
    private final Socket socket;

    public HttpServletResponse(Socket socket, String path) throws IOException, URISyntaxException {
        this.socket = socket;
        // 获取项目
        // File fir = new File(ClientHandler.class.getClassLoader(".").getResource.toURI);
        File dir = new File(Objects.requireNonNull(
                ClientHandler.class.getClassLoader().getResource(".")).toURI());
        File staticDir = new File(dir, "static");

        String[] data = path.split("/");
        boolean exist = true;
        String code = "200 OK";
        File file = new File(staticDir, path);
        if (data.length == 0) {
            // 响应正文所代表的文件
            file = new File(staticDir, "index.html");
            exist = false;
        } else {
            if (file.exists()) {
                exist = false;
            }
        }
        if (exist) {
            file = new File(staticDir, "404.html");
            code = "404 NotFound";
        }

        responseBrowser(file, code);
    }

    /**
     * @Description 发送全部响应给客户端的方法
     * @Return void
     * @Param File file
     * @Param String code
     * @Author YGKING
     * @Date 2023/04/18 13:37:13
     */
    private void responseBrowser(File file, String code) throws IOException {
        // 1.发送响应行
        println("HTTP/1.1 " + code);

        // 2.发送响应头
        println("Content-Type: text/html");
        println("Content-Length: " + file.length());
        // 调用方法时传空字符串，表示响应头部分发送结束
        println("");

        // 3.发送响应正文
        OutputStream out = socket.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        int d;
        byte[] buf = new byte[1024 * 10];
        while ((d = fis.read(buf)) != -1) {
            out.write(buf, 0, d);
        }
    }

    /**
     * @Description 向客户端发送一行字符串的方法
     * @Return void
     * @Param String line
     * @Author YGKING
     * @Date 2023/04/18 11:23:16
     */
    private void println(String line) throws IOException {
        OutputStream out = socket.getOutputStream();
        byte[] data = line.getBytes(StandardCharsets.ISO_8859_1);
        out.write(data);
        out.write(13);// 发送回车符
        out.write(10);// 发送换行符
    }
}
