package com.example.narendar.chatapplication.model;

/**
 * Created by Naren Wadhwa on 7/15/2017.
 */

public class Message {

    private String senderMessage = "";
    private String receiverMessage = "";

    public String getSenderMessage() {
        return senderMessage;
    }

    public void setSenderMessage(String senderMessage) {
        this.senderMessage = senderMessage;
    }

    public String getReceiverMessage() {
        return receiverMessage;
    }

    public void setReceiverMessage(String receiverMessage) {
        this.receiverMessage = receiverMessage;
    }
}
