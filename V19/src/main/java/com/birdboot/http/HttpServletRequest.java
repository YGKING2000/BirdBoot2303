package com.birdboot.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Description HTTP服务器请求
 * @ClassName HttpServletRequest
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/17 17:23
 * @Version 1.0
 */
public class HttpServletRequest {
    private final Socket socket;
    private String method;// 请求方式
    private String uri;// 抽象路径
    private String protocol;// 协议版本
    // 解析请求头存进headers的key和value
    private final Map<String, String> headers = new HashMap<>();

    private String requestURI;// 保存uri左侧的请求路径部分
    private String queryString;// 保存uri右侧的请求参数部分
    // 解析请求参数存进parameters的key和value
    private final Map<String, String> parameters = new HashMap<>();

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
     * @Date 2023/04/24 21:13:52
     * @Version 1.0
     */
    private void parseRequestLine() throws IOException, EmptyRequestException {
        String line = readLine();
        if (line.isEmpty()) {
            throw new EmptyRequestException();
        }
        // System.out.println(line);
        String[] list = line.split("\\s");
        method = list[0];
        uri = list[1];
        protocol = list[2];
        // 进一步解析uri
        parseURI();

        // parameters.forEach((k, v) -> System.out.println(k + ": " + v));
        // System.out.println("method: " + method);
        // System.out.println("uri: " + uri);
        // System.out.println("protocol: " + protocol);
    }

    /**
     * @Description 进一步解析uri的方法
     * @Return void
     * @Param
     * @Author YGKING
     * @Date 2023/04/19 15:22:07
     * @Version 1.0
     */
    private void parseURI() {
        String[] data = uri.split("\\?");
        requestURI = data[0];
        if (data.length > 1) {
            queryString = data[1];
            parseParameters(queryString);
        }
        // System.out.println("请求路径:" + requestURI);
        // System.out.println("请求参数字符串:" + queryString);
        // System.out.println("请求参数:" + parameters);
    }

    /**
     * @Description
     * @Return void
     * @Param String line
     * @Author YGKING
     * @Date 2023/04/24 21:14:07
     * @Version 1.0
     */
    private void parseParameters(String line) {
        try {
            line = URLDecoder.decode(line, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] data = line.split("&");
        for (String item : data) {
            String[] k_v = item.split("=", 2);
            parameters.put(k_v[0], k_v[1]);
        }
    }

    /**
     * @Description 解析请求头的方法
     * @Return void
     * @Param
     * @Author YGKING
     * @Date 2023/04/19 15:22:15
     * @Version 1.0
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
     * @Date 2023/04/19 15:22:23
     * @Version 1.0
     */
    private void parseContent() throws IOException {
        if (headers.containsKey("Content-Length")) {
            int contentLength = Integer.parseInt(headers.get("Content-Length"));
            byte[] contentData = new byte[contentLength];
            InputStream in = socket.getInputStream();
            in.read(contentData);

            String contentType = headers.get("Content-Type");
            if ("application/x-www-form-urlencoded".equals(contentType)) {
                String line = new String(contentData, StandardCharsets.ISO_8859_1);
                // System.out.println(line);
                parseParameters(line);
            }
        }
    }

    /**
     * @description 读取一行客户端发送的消息的方法
     * @return java.lang.String
     * @author YGKING
     * @date 2023/04/27 09:53:35
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
     * @Date 2023/04/19 15:22:41
     * @Version 1.0
     */
    public String getHeaders(String name) {
        return headers.get(name);
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getParameters(String name) {
        return parameters.get(name);
    }
}
