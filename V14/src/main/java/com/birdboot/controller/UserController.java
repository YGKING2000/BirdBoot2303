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

    /**
     * @Description 用户登录的方法
     * @Return void
     * @Param HttpServletRequest request
     * @Param HttpServletResponse response
     * @Author YGKING
     * @Date 2023/04/20 10:44:57
     * @Version 1.0
     */
    public void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameters("username");
        String password = request.getParameters("password");
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("/login_illegal_input.html");
            return;
        }
        File file = new File(userDir, username + ".obj");
        if (file.isFile()) {
            try (
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                User user = (User) ois.readObject();
                if (password.equals(user.getPassword())) {
                    response.sendRedirect("/login_success.html");
                } else {
                    response.sendRedirect("/login_pwd_err.html");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("login_user_not_exist.html");
        }
    }

    /**
     * @Description 用户注册的方法
     * @Return void
     * @Param HttpServletRequest request
     * @Param HttpServletResponse response
     * @Author YGKING
     * @Date 2023/04/20 10:44:38
     * @Version 1.0
     */
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
