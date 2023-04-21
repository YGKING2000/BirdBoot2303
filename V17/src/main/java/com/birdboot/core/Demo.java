package com.birdboot.core;

import java.io.File;
import java.util.Objects;

/**
 * @Description
 * @ClassName Demo
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/21 17:50
 * @Version 1.0
 */
public class Demo {
    public static void main(String[] args) {
        // System.out.println(Demo.class.getClassLoader().getResource("."));
        // System.out.println(Demo.class.getClassLoader().getResource("/"));

        Package p = Demo.class.getPackage();
        Package p2 = Package.getPackage("com.birdboot.core");
        System.out.println(p.getName());
        System.out.println(p2.getName());
    }
}
