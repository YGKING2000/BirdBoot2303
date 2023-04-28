package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className JDBCDemo
 * @date 2023/04/27 17:13
 */
public class JDBCDemo3 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                    "root", "123456");
            Statement statement = connection.createStatement();
            String sql = "UPDATE userinfo SET password = '123456789' WHERE username = '刘亦菲'";
            int num = statement.executeUpdate(sql);
            if (num > 0) {
                System.out.println("修改成功!");
            } else {
                System.out.println("修改失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
