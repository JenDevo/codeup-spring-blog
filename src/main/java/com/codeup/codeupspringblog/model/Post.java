package com.codeup.codeupspringblog.model;

public class Post {
    private long id;
    private String title;
    private String body;


//    CONSTRUCTOR
    public Post(){}

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

//    GETTERS AND SETTERS


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
