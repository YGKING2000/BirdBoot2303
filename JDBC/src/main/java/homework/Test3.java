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
 * @description 程序启动后要求输入班级名称，然后列出该班级的id，班级名，所在楼层
 * @className Test3
 * @date 2023/04/27 20:18
 */
public class Test3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入班级名称:");
        String className = scanner.nextLine();
        try (
                Connection connection = DBUtil.getConnection();
        ) {
            Statement statement = connection.createStatement();
            String sql = "SELECT id, name, floor FROM class WHERE name = '" + className + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int floor = resultSet.getInt("floor");
                System.out.println("班级id:" + id + ", 班级名:" + name + ", 所在楼层:" + floor);
            } else {
                System.out.println("未找到你输入的班级!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
