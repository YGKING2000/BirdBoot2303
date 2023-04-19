package com.birdboot.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description HTTP服务器请求
 * @ClassName HttpServletRequest
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/17 17:23
 * @Version 1.0
 */
public class HttpServletRequest {
    private final Socket socket;
    private String method;
    private String uri;
    private String protocol;
    private final Map<String, String> headers = new HashMap<>();

    // 将异常抛出给
    public HttpServletRequest(Socket socket) throws IOException, EmptyRequestException {
        this.socket = socket;
        try {
            // 1.解析请求行
            parseRequestLine();
            // 2.解析消息头
            parseRequestHeaders();
            // 3.解析消息正文
            parseContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 解析请求行的方法
     * @Return void
     * @Param
     * @Author YGKING
     * @Date 2023/04/18 09:11:01
     */
    private void parseRequestLine() throws IOException, EmptyRequestException {
        String line = readLine();
        if (line.isEmpty()) {
            throw new EmptyRequestException();
        }
        String[] list = line.split("\\s");
        method = list[0];
        uri = list[1];
        protocol = list[2];
    }

    /**
     * @Description 解析请求头的方法
     * @Return void
     * @Param
     * @Author YGKING
     * @Date 2023/04/18 09:18:57
     */
    private void parseRequestHeaders() throws IOException {
        String line;
        while (!(line = readLine()).isEmpty()) {
            String[] data = line.split("(: )");
            headers.put(data[0], data[1]);
        }
        // headers.forEach((k, v) -> System.out.println("请求头: " + k + " = " + v));
    }

    /**
     * @Description 解析消息正文的方法
     * @Return void
     * @Param
     * @Author YGKING
     * @Date 2023/04/18 09:14:28
     */
    private void parseContent() {}

    /**
     * @Description 读取一行客户端发送的消息的方法
     * @Return java.lang.String
     * @Param
     * @Author YGKING
     * @Date 2023/04/17 15:03:23
     */
    // 通常被重用的代码不自己解决异常，而是抛出给使用者
    public String readLine() throws IOException {
        InputStream in = socket.getInputStream();
        int message;
        int count = 0;
        StringBuilder builder = new StringBuilder();
        while ((message = in.read()) != -1) {
            builder.append((char) message);
            // 当上一个字符为回车符(count为1)且这次为换行符，则不再读取
            if (count == 1 && message == 10) {
                break;
            } else {
                // 当上一字符为回车符，这一次不是换行符时，将count重置为0
                count = 0;
            }
            // 当这一次的字符为回车符时，将count设置为1，方便下一次循环判断
            if (message == 13) {
                count++;
            }
        }
        return builder.toString().trim();
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    /**
     * @Description 根据给定消息头的名称以获取对应的值
     * @Return java.lang.String
     * @Param String name
     * @Author YGKING
     * @Date 2023/04/17 17:47:01
     */
    public String getHeaders(String name) {
        return headers.get(name);
    }
}
