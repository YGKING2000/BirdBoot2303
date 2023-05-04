package com.birdboot.entity;

import java.io.Serializable;

/**
 * @Description
 * @ClassName User
 * @Author YGKING e-mail:hrd18960706057@163.com
 * @Date 2023/04/20 10:17
 * @Version 1.0
 */
public class User implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private int age;

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' + ", age=" + age + '}';
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String nickname, int age) {
        this.nickname = nickname;
        this.age = age;
    }

    public User(String password, String nickname, int age) {
        this.password = password;
        this.nickname = nickname;
        this.age = age;
    }

    public User(String username, String password, String nickname, int age) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
