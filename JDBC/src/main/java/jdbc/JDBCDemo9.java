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
 * @className JDBCDemo9
 * @date 2023/04/28 11:16
 */
public class JDBCDemo9 {
    public static void main(String[] args) {
        try (
                Connection connection = DBUtil.getConnection()
        ) {
            do {
                String sql2 = "SELECT * FROM userinfo WHERE username = ?";
                PreparedStatement ps2 = connection.prepareStatement(sql2);
                Scanner scanner = new Scanner(System.in);
                System.out.print("请输入用户名:");
                String username = scanner.nextLine();
                System.out.print("请输入密码:");
                String password = scanner.nextLine();
                System.out.print("请输入昵称:");
                String nickname = scanner.nextLine();
                System.out.print("请输入年龄:");
                int age = scanner.nextInt();
                ps2.setString(1, username);
                ResultSet resultSet = ps2.executeQuery();
                if (resultSet.next()) {
                    System.out.println("用户名重复，请重新尝试!");
                } else {
                    String sql = "INSERT INTO userinfo (username, password, nickname, age) " +
                            "VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setString(3, nickname);
                    ps.setInt(4, age);
                    int num = ps.executeUpdate();
                    if (num > 0) {
                        System.out.println("注册成功!");
                    } else {
                        System.out.println("注册失败，请重新尝试!");
                    }
                    break;
                }
            } while (true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
