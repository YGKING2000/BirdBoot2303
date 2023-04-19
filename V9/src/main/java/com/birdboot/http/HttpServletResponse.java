package com.birdboot.http;

import com.birdboot.core.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Description
 * @ClassName HttpServletResponse
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/18 13:13
 * @Version 1.0
 */
public class HttpServletResponse {
    private final Socket socket;
    // 状态行信息
    private int statusCode = 200;
    private String statusReason = "OK";

    // 响应头相关信息
    private final Map<String, String> headers = new HashMap<>();

    // 响应正文相关信息
    private File contentFile;

    public HttpServletResponse(Socket socket) {
        this.socket = socket;
    }

    /**
     * @Description 发送全部响应给客户端的方法
     * @Return void
     * @Param File file
     * @Param String code
     * @Author YGKING
     * @Date 2023/04/18 13:37:13
     */
    public void response() throws IOException {
        // 1.发送响应行
        sendStatusLine();
        // 2.发送响应头
        sendHeaders();
        // 3.发送响应正文
        sendContent();
    }

    // 1.发送响应行的方法
    private void sendStatusLine() throws IOException {
        println("HTTP/1.1 " + statusCode + " " + statusReason);
    }

    // 2.发送响应头的方法
    private void sendHeaders() throws IOException {
        Set<Map.Entry<String, String>> entrySet = headers.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String name = entry.getKey();
            String value = entry.getValue();
            println(name + ": " + value);
        }

        // 调用方法时传空字符串，表示响应头部分发送结束
        println("");
    }

    // 3.发送响应正文的方法
    private void sendContent() throws IOException {
        OutputStream out = socket.getOutputStream();
        FileInputStream fis = new FileInputStream(contentFile);
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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public File getContentFile() {
        return contentFile;
    }

    public void setContentFile(File contentFile) {
        this.contentFile = contentFile;
    }

    /**
     * @Description 添加一个响应头
     * @Return void
     * @Param String name
     * @Param String value
     * @Author YGKING
     * @Date 2023/04/19 10:41:42
     */
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }
}
