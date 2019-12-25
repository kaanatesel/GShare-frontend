package com.example.gshare.Chat;
public class ChatTry {
    String message;
    Boolean user;

    public ChatTry(String text, boolean user) {
        this.message = text;
        this.user = user;
    }
    public String getMessage(){
        return this.message;

    }
    public boolean getUser(){
        return user;
    }
}
