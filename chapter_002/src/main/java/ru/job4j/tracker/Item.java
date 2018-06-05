package ru.job4j.tracker;

import java.util.Date;

/**
 * Заявка.
 */
public class Item {
    private String name;
    private String desc;
    private long created;
    private String[] comments;
    private String id;

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.created = new Date().getTime();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCommetns(String[] comments) {
        this.comments = comments;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public long getCreated() {
        return this.created;
    }

    public String[] getComments() {
        return this.comments;
    }
}
