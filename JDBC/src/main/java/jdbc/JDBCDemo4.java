package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className JDBCDemo4
 * @date 2023/04/27 17:37
 */
public class JDBCDemo4 {
    public static void main(String[] args) {
        /*try (
                Connection connection = DBUtil.getConnection()
        ) {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, name, title, salary FROM teacher";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String title = resultSet.getString("title");
                int salary = resultSet.getInt("salary");
                if (title.length() > 2) {
                    System.out.println(id + "\t " + name + "\t " + title + "\t " + salary);
                } else {
                    System.out.println(id + "\t " + name + "\t " + title + "\t\t " + salary);
                }
            }
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        }*/
        try (
                Connection connection = DBUtil.getConnection();
        ) {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, name, title, salary FROM teacher";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String title = resultSet.getString("title");
                int salary = resultSet.getInt("salary");
                if(title.length() > 2) {
                    System.out.println(id + "\t " + name + "\t " + title + "\t " + salary);
                } else {
                    System.out.println(id + "\t " + name + "\t " + title + "\t\t " + salary);
                }
            }
        } catch (SQLException troubles) {
            troubles.printStackTrace();
        }
    }
}
