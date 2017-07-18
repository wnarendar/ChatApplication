package com.example.narendar.chatapplication.model;

/**
 * Created by Naren Wadhwa on 7/15/2017.
 */

public class Message {

    private String message = "";
    private String isSender = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsSender() {
        return isSender;
    }

    public void setIsSender(String isSender) {
        this.isSender = isSender;
    }
}
