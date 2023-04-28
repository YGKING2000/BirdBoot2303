package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description JDBC连接演示
 * @className JDBCDemo
 * @date 2023/04/27 15:12
 */
public class JDBCDemo1 {
    public static void main(String[] args) {
        try {
            // 1.加载驱动: 各个数据库的驱动Driver不同，下面是mysql的驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.与数据库建立连接
            Connection connection = DriverManager.getConnection(
                    // url格式是固定的:         数据库地址 /数据库名称?参数
                    "jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true",
                    "root",
                    "123456"
            );
            System.out.println("成功与数据库建立连接!");
            // 3.
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE userinfo(" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "username VARCHAR(32)," +
                    "password VARCHAR(32)," +
                    "nickname VARCHAR(32)," +
                    "age INT(3)" +
                    ");";
            // 4.
            /*
             * statement提供了多种执行SQL的方法
             * boolean execute(String sql)用于向数据库执行SQL语句
             * 该方法可以执行任何种类的SQL但是实际上执行DML，DOL都有专门的方法
             * 因此该execute通常只用来执行DDL
             * 如果结果是ResultSet对象，则方法execute返回true;如果结果是Java int，则返回false
             */
            statement.execute(sql);
            connection.close();
            System.out.println("表创建成功!");
            // 5.
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
