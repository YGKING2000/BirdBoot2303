package test;

import java.util.Arrays;

/**
 * @Description split(String regex, int limit)
 * @ClassName SplitDemo
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/19 17:05
 * @Version 1.0
 */
public class SplitDemo {
    public static void main(String[] args) {
        String line = "1=2=3=4=rtg=t4t  = f = aa=====        ===";
        System.out.println(Arrays.toString(line.split("=",2)));
        // [1, 2=3=4=rtg=t4t  = f = aa=====      ===]

        System.out.println(Arrays.toString(line.split("=",10)));
        // [1, 2, 3, 4, rtg, t4t  ,  f ,  aa, , ===        ===]

        System.out.println(Arrays.toString(line.split("=",100)));
        // [1, 2, 3, 4, rtg, t4t  ,  f ,  aa, , , , ,         , , , ]

        System.out.println(Arrays.toString(line.split("=",0)));
        // [1, 2, 3, 4, rtg, t4t  ,  f ,  aa, , , , ,         ]

        System.out.println(Arrays.toString(line.split("=")));
        // [1, 2, 3, 4, rtg, t4t  ,  f ,  aa, , , , ,         ]

        System.out.println(Arrays.toString(line.split("=", -1)));
        // [1, 2, 3, 4, rtg, t4t  ,  f ,  aa, , , , ,         , , , ]
    }
}
