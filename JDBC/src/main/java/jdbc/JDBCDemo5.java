package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className JDBCDemo5
 * @date 2023/04/28 09:21
 */
public class JDBCDemo5 {
    public static void main(String[] args) {
        try (
                Connection connection = DBUtil.getConnection()
        ) {
            Statement statement = connection.createStatement();
            String sql = "SELECT t.name, t.gender, c.name, c.floor " +
                    "FROM teacher t, class c " +
                    "WHERE c.teacher_id = t.id " +
                    "AND t.name = '王克晶'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String tName = resultSet.getString("t.name");
                String tGender = resultSet.getString("t.gender");
                String cName = resultSet.getString("c.name");
                int floor = resultSet.getInt("c.floor");
                System.out.println(tName + ", " + tGender + ", " + cName + ", " + floor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
