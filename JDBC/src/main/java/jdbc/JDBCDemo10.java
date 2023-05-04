package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className JDBCDemo10
 * @date 2023/04/28 11:34
 */
public class JDBCDemo10 {
    public static void main(String[] args) {
        try (
                Connection connection = DBUtil.getConnection()
        ) {
            Scanner scanner = new Scanner(System.in);
            String username, sql;
            String oldPassword;
            PreparedStatement ps;
            ResultSet resultSet;
            do {
                System.out.print("请输入用户名:");
                username = scanner.nextLine();
                sql = "SELECT * FROM userinfo WHERE username = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, username);
                resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    break;
                } else {
                    System.out.println("用户名不存在，请重新输入!");
                }
            } while (true);
            do {
                System.out.println("你当前修改的账号是:" + username);
                System.out.print("请输入对应的旧密码:");
                oldPassword = scanner.nextLine();
                sql = "SELECT * FROM userinfo WHERE username = ? AND password = ?";
                ps = connection.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, oldPassword);
                resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    break;
                } else {
                    System.out.println("密码错误，请重新输入！");
                }
            } while (true);
            System.out.println("你当前修改的账号是:" + username);
            System.out.print("请输入新密码:");
            String newPassword = scanner.nextLine();
            sql = "UPDATE userinfo SET password = ? WHERE username = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, username);
            int num = ps.executeUpdate();
            if (num > 0) {
                System.out.println("修改成功!");
            } else {
                System.out.println("修改失败!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
