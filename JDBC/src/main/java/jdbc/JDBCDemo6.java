package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className JDBCDemo6
 * @date 2023/04/28 09:30
 */
public class JDBCDemo6 {
    public static void main(String[] args) {
        try (
                Connection connection = DBUtil.getConnection()
        ) {
            Statement statement = connection.createStatement();
            String sql = "SELECT COUNT(*) sum " +
                    "FROM class c, student s " +
                    "WHERE c.id = s.class_id " +
                    "AND c.name = '1年级1班'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int sum = resultSet.getInt("sum");
                System.out.println("1年级1班人数为:" + sum);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
