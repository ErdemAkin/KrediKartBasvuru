package com.deneme1.erel.kredikartbasvuru.model;

/**
 * Created by Melike on 25.12.2016.
 */
public class PushNotification {

    private String title;
    private String message;

    public PushNotification(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
