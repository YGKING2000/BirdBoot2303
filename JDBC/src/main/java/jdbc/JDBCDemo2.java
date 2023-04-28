package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className JDBCDemo2
 * @date 2023/04/27 16:38
 */
public class JDBCDemo2 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                    "root",
                    "123456");
            Statement statement = connection.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入用户名:");
            String username = scanner.nextLine();
            System.out.print("请输入密码:");
            String password = scanner.nextLine();
            System.out.print("请输入昵称:");
            String nickname = scanner.nextLine();
            System.out.print("请输入年龄:");
            int age = scanner.nextInt();
            String sql = "INSERT INTO userinfo (username, password, nickname, age) " +
                    "VALUES ('胡歌', '123456', '李逍遥', 18)";
            // String sql = "INSERT INTO userinfo (username, password, nickname, age)" +
            //         "VALUES ('" + username + "', '" + password + "', '" + nickname + "', '" + age + "')";
            // int executeUpdate(String sql)
            // 专门用于执行DML语句的，返回值表达执行该DML后影响了表中多少条记录
            int num = statement.executeUpdate(sql);
            if (num == 1) {
                System.out.println("插入数据成功!");
            } else {
                System.out.println("插入数据失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
