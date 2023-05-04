package homework;

import jdbc.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className Test4
 * @date 2023/04/28 10:08
 */
public class Test4 {
    public static void main(String[] args) {
        try (
                Connection connection = DBUtil.getConnection()
        ) {
            Statement statement = connection.createStatement();
            String sql = "SELECT COUNT(*) num, c.name, t.name " +
                    "FROM teacher t, class c, student s " +
                    "WHERE t.id = c.teacher_id " +
                    "AND c.id = s.class_id " +
                    "GROUP BY c.name, t.name";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("人数\t班级名称\t教师姓名");
            while (resultSet.next()) {
                int num = resultSet.getInt("num");
                String cName = resultSet.getString("c.name");
                String tName = resultSet.getString("t.name");
                System.out.println(num + "\t" + cName + "\t" + tName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
