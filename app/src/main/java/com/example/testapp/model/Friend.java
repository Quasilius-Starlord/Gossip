package com.example.testapp.model;

public class Friend {
    private String idChatRoom;

    public Friend () {}

    public Friend(String idChatRoom) {
        this.idChatRoom = idChatRoom;
    }

    public String getIdChatRoom() {
        return idChatRoom;
    }

    public void setIdChatRoom(String idChatRoom) {
        this.idChatRoom = idChatRoom;
    }
}
