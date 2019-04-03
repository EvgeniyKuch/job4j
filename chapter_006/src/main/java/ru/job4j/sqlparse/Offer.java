package ru.job4j.sqlparse;

public class Offer {
    private String name;
    private String link;
    private String text;
    private long date;

    public Offer(String name, String link, long date, String text) {
        this.name = name;
        this.link = link;
        this.text = text;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getText() {
        return text;
    }

    public long getDate() {
        return date;
    }

}
