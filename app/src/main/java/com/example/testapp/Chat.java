package com.example.testapp;

import java.util.Date;

public class Chat {
    private String message, uid;
    private Date date;

    public Chat () {}

    public Chat(String message, String uid, Date date) {
        this.message = message;
        this.uid = uid;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
