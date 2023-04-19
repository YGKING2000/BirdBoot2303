package com.birdboot.http;

/**
 * @Description
 * @ClassName Father
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/19 08:29
 * @Version 1.0
 */
public class Father {
    public String name;
    public static String str = "Father";

    public Father(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println("Father:Say");
    }

    public static void show() {
        System.out.println("Father:show");
    }

    public static void main(String[] args) {
        Father father = new Father("Father23");
        Son son = new Son("Son3");
        System.out.println(Father.str);// Father
        System.out.println(Son.str);// Son
        System.out.println(father.str);// Father
        System.out.println(son.str);// Son
        System.out.println(((Father) son).str);// Father

        System.out.println(father.name);// Father23
        System.out.println(son.name);// Son3
        System.out.println(((Father) son).name);// Son3

        father.show();// Father:show
        son.show();// Son:show
        ((Father) son).show();// Father:show

        father.say();// Father:say
        son.say();// Son:say
        ((Father) son).say();// Son:say
    }

}

class Son extends Father {
    public static String str = "Son";

    public void say() {
        System.out.println("Son:Say");
    }

    public static void show() {
        System.out.println("Son:show");
    }

    public Son(String name) {
        super(name);
    }
}
