package jdbc;

import java.sql.*;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className Test
 * @date 2023/05/05 21:16
 */
public class Demo2 {
    public static void main(String[] args) throws SQLException {
        //注册驱动程序
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //获得连接
        String url = "jdbc:mysql://localhost:3306/tedu?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true&allowMultiQueries=true";
        Connection con = DriverManager.getConnection(url, "root", "123456");
        System.out.println("Connection established......");
        //将自动提交设置为false
        con.setAutoCommit(false);
        //将可保存性设置为CLOSE_CURSORS_AT_COMMIT
        con.setHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT);
        //创建一个Statement对象
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //检索数据
        ResultSet resultSet = statement.executeQuery("SELECT * FROM myplayers");
        System.out.println("Contents of the table");
        while(resultSet.next()) {
            System.out.print("ID: "+resultSet.getString("ID")+", ");
            System.out.print("First_Name: "+resultSet.getString("First_Name")+", ");
            System.out.print("Last_Name: "+resultSet.getString("Last_Name"));
            System.out.print("Date_Of_Birth: "+resultSet.getString("Date_Of_Birth")+", ");
            System.out.print("Place_Of_Birth: "+resultSet.getString("Place_Of_Birth"));
            System.out.print("Country: "+resultSet.getString("Country")+"\n");
        }
        //插入新行
        resultSet.moveToInsertRow();
        resultSet.updateInt(1, 8);
        resultSet.updateString(2, "Ishaan");
        resultSet.updateString(3, "Sharma");
        resultSet.updateDate(4, new Date(904694400000L));
        resultSet.updateString(5, "Delhi");
        resultSet.updateString(6, "India");
        // resultSet.insertRow();
        //提交事务
        con.commit();
        boolean flag = resultSet.isClosed();
        if(flag) {
            System.out.println("ResultSet object 已经死了");
        } else {
            System.out.println("ResultSet object 还活着");
        }
        int count = 0;
        for (int i = 1; i <= 7; i++) {
            resultSet.absolute(i);
            String name = resultSet.getString("First_Name");
            System.out.println(++count + "\t" +name);
        }
        // while (resultSet.next()) {
        //     String name = resultSet.getString("First_Name");
        //     System.out.println(++count + "\t" +name);
        // }
    }

    /*@Test
    public void test() {
        try {
            int count = 0;
            *//*while (resultSet.next()) {
                String name = resultSet.getString("First_Name");
                System.out.println(++count + "\t" +name);
            }*//*
            for (int i = 1; i < 5; i++) {
                resultSet.absolute(i);
                String name = resultSet.getString("First_Name");
                System.out.println(++count + "\t" +name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
