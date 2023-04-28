package homework;

import jdbc.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @description
 * @className Test2
 * @date 2023/04/27 19:06
 */
// 创建一张表article
// 表结构
// id 整数类型，主键，自增           唯一标识
// title 字符串类型，长度600字节     文章标题
// content 字符串类型，长度3000字节  文章正文
public class Test2 {
    public static void main(String[] args) {
        try (
                Connection connection = DBUtil.getConnection()
        ) {
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS article;CREATE TABLE article(id INT PRIMARY KEY AUTO_INCREMENT, title VARCHAR(600), content VARCHAR(3000));";
            System.out.println("创建表article成功!");
            // 如果结果是ResultSet对象，则方法execute返回true;如果结果是Java int，则返回false
            // Object flag = statement.execute(sql);
            // System.out.println(flag);// false
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
