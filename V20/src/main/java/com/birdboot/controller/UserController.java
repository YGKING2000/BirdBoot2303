package com.birdboot.controller;

import com.birdboot.annotations.Controller;
import com.birdboot.annotations.RequestMapping;
import com.birdboot.entity.User;
import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;
import com.birdboot.util.DBUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description User
 * @ClassName UserController
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/19 17:40
 * @Version 1.0
 */
@Controller
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
    @RequestMapping(value = "/loginUser")
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
                    ObjectInputStream ois = new ObjectInputStream(fis)
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
    @RequestMapping("/regUser")
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

        try (
                Connection connection = DBUtil.getConnection()
        ) {
            String querySql = "SELECT 1 FROM userinfo WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(querySql);
            ps.setString(1, username);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                response.sendRedirect("/reg_user_exist.html");
            } else {
                String insertSql = "INSERT INTO userinfo (username, password, nickname, age) VALUES (?, ?, ?, ?)";
                ps = connection.prepareStatement(insertSql);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, nickname);
                ps.setInt(4, age);
                int num = ps.executeUpdate();
                if (num > 0) {
                    response.sendRedirect("/reg_success.html");
                } else {
                    response.sendRedirect("/reg_illegal_input.html");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
