package com.youngkim.ls_challenge.app;

/**
 * Created by YoungKwang on 6/7/2014.
 */
public class ListItem {
    private String attr;
    private String desc;
    private String link;
    private String image;
    private User user;

    public ListItem(String attr, String desc, String link, String image, User user) {
        this.attr = attr;
        this.desc = desc;
        this.link = link;
        this.image = image;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "attr='" + attr + '\'' +
                ", desc='" + desc + '\'' +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                ", user=" + user +
                '}';
    }

    public String getAttr() {
        return attr;
    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public User getUser() {
        return user;
    }
}
