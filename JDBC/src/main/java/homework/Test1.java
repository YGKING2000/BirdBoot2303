package homework;

import jdbc.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description 删除指定用户:程序启动后要求用户输入用户名，然后将userinfo表中的该用户进行删除
 * @className Test1
 * @date 2023/04/27 18:54
 */
public class Test1 {
    public static void main(String[] args) {
        try (
                Connection connection = DBUtil.getConnection()
        ) {
            Statement statement = connection.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入想要删除的用户名:");
            String username = scanner.nextLine();
            String sql = "SELECT username FROM userinfo WHERE username = '" + username + "'";
            String sql1 = "DELETE FROM userinfo WHERE username = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int result = statement.executeUpdate(sql1);
                if (result > 0) {
                    System.out.println("成功删除用户" + username);
                }
            } else {
                System.out.println("用户" + username + "不存在!");
            }
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        }
    }
}
