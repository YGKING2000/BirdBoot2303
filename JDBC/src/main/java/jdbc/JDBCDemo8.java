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
 * @className JDBCDemo8
 * @date 2023/04/28 10:39
 */
public class JDBCDemo8 {
    public static void main(String[] args) {
        try (
                Connection connection = DBUtil.getConnection()
        ) {
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.print("请输入用户名:");
                String username = scanner.nextLine();
                System.out.print("请输入密码:");
                String password = scanner.nextLine();
                String sql = "SELECT * " +
                        "FROM userinfo " +
                        "WHERE username = ? " +
                        "AND password = ?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    String nickname = resultSet.getString("nickname");
                    System.out.println("登录成功，欢迎" + nickname + "!");
                    break;
                } else {
                    System.out.println("登录失败，请重新尝试!");
                }
            } while (true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
