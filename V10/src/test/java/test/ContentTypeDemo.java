package test;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

/**
 * @Description
 * @ClassName ContentTypeDemo
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/19 11:37
 * @Version 1.0
 */
public class ContentTypeDemo {
    public static void main(String[] args) {
        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        File file = new File("demo.png");
        String contentType = fileTypeMap.getContentType(file);
        System.out.println(contentType);
    }
}
