package com.birdboot.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
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
            /* 1.解析请求 */
            // 1.1 解析请求行
            String line = readLine();
            System.out.println("请求行:" + line);
            String[] list = line.split("\\s");
            String method = list[0];
            String uri = list[1];
            String protocol = list[2];
            System.out.print("请求方式:" + method);
            System.out.print("   请求资源地址:" + uri);
            System.out.print("   协议:" + protocol);

            // 1.2 解析消息头
            System.out.println("\n消息头:");
            Map<String, String> map = new HashMap<>();
            while (!(line = readLine()).isEmpty()) {
                String[] data = line.split("(: )");
                map.put(data[0], data[1]);
            }
            map.forEach((k, v) -> System.out.println(k + " = " + v));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 读取一行客户端发送的消息的方法
     * @Return java.lang.String
     * @Param
     * @Author YGKING
     * @Date 2023/04/17 15:03:23
     */
    // 通常被重用的代码不自己解决异常，而是抛出给使用者
    private String readLine() throws IOException {
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
}
