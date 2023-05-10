package jdbc;

import java.sql.*;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className Demo03
 * @date 2023/05/06 08:38
 */
public class Demo03 {
    public static void main(String[] args) throws SQLException {
        // 注册驱动程序
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        // 获得连接
        String url = "jdbc:mysql://localhost/tedu";
        Connection con = DriverManager.getConnection(url, "root", "123456");
        System.out.println("Connection established......");
        // 将自动提交设置为false
        con.setAutoCommit(false);
        // 将可保存性设置为CLOSE_CURSORS_AT_COMMIT
        con.setHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT);
        // 创建一个Statement对象
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        // 检索数据
        ResultSet rs = stmt.executeQuery("SELECT * FROM MyPlayers");
        System.out.println("Contents of the table");
        while (rs.next()) {
            System.out.print("ID: " + rs.getString("ID") + ", ");
            System.out.print("First_Name: " + rs.getString("First_Name") + ", ");
            System.out.print("Last_Name: " + rs.getString("Last_Name"));
            System.out.print("Date_Of_Birth: " + rs.getString("Date_Of_Birth") + ", ");
            System.out.print("Place_Of_Birth: " + rs.getString("Place_Of_Birth"));
            System.out.println("Country: " + rs.getString("Country"));
        }
        // 插入新行
        /*rs.moveToInsertRow();
        rs.updateInt(1, 10);
        rs.updateString(2, "Ishaan");
        rs.updateString(3, "Sharma");
        rs.updateDate(4, new Date(904694400000L));
        rs.updateString(5, "Delhi");
        rs.updateString(6, "India");
        rs.insertRow();*/
        // 提交事务
        con.commit();
        boolean bool = rs.isClosed();
        if (bool) {
            System.out.println("ResultSet object已经死了");
        } else {
            System.out.println("ResultSet object还活着");
        }
    }
}
