package test;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @Description
 * @ClassName URLDecoderDemo
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/20 15:29
 * @Version 1.0
 */
public class URLDecoderDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "username=%E9%BB%84%E4%BB%81%E4%B8%9C&nickname=YGKING&password=ed&age=23";
        str = URLDecoder.decode(str, "UTF-8");
        System.out.println(str);
    }
}
