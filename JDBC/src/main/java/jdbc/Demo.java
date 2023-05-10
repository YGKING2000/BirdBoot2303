package jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className Demo
 * @date 2023/05/05 20:55
 */
public class Demo {
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try {
            Connection connection = DBUtil.getConnection();
            connection.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
            Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            String sql = "SELECT name FROM teacher";
            resultSet = statement.executeQuery(sql);
            int count = 0;
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println(++count + "\t" +name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void test() {
        try {
            int count = 0;
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println(++count + "\t" +name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
