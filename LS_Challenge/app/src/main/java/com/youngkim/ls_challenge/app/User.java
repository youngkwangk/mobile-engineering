package com.youngkim.ls_challenge.app;

/**
 * Created by YoungKwang on 6/7/2014.
 */
public class User {
    private String user_name;
    private String name;
    private Avatar avatar;

    public User(String user_name, String name, Avatar avatar) {
        this.user_name = user_name;
        this.name = name;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_name='" + user_name + '\'' +
                ", name='" + name + '\'' +
                ", avatar=" + avatar +
                '}';
    }

    public String getUser_name() {
        return user_name;
    }

    public String getName() {
        return name;
    }

    public Avatar getAvatar() {
        return avatar;
    }
}
