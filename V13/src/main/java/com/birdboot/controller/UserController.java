package com.birdboot.controller;

import com.birdboot.entity.User;
import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * @Description User
 * @ClassName UserController
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/19 17:40
 * @Version 1.0
 */
public class UserController {
    private static final File userDir;

    static {
        userDir = new File("userDir");
        if (!userDir.isDirectory()) {
            userDir.mkdirs();
        }
    }

    public void reg(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("正在注册...");
        String username = request.getParameters("username");
        String password = request.getParameters("password");
        String nickname = request.getParameters("nickname");
        String ageStr = request.getParameters("age");

        if (username == null || username.isEmpty() || password == null || password.isEmpty() ||
                nickname == null || nickname.isEmpty() || ageStr == null || !ageStr.matches("[\\d]+")) {
            response.sendRedirect("/reg_illegal_input.html");
            return;
        }

        int age = Integer.parseInt(ageStr);
        File file = new File(userDir, username + ".obj");
        if (file.isFile()) {
            response.sendRedirect("reg_user_exist.html");
        } else {
            User user = new User(username, password, nickname, age);
            try (
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos)
            ) {
                oos.writeObject(user);
                response.sendRedirect("/reg_success.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
