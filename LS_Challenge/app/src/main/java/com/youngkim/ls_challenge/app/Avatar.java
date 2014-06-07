package com.youngkim.ls_challenge.app;

/**
 * Created by YoungKwang on 6/7/2014.
 */
public class Avatar {
    private int height;
    private int width;
    private String image;

    public Avatar(int height, int width, String image) {
        this.height = height;
        this.width = width;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "height=" + height +
                ", width=" + width +
                ", image='" + image + '\'' +
                '}';
    }
}
