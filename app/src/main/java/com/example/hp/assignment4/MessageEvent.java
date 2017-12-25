package com.example.hp.assignment4;

/**
 * Created by hp on 12/24/2017.
 */

class MessageEvent {
    private int i;
    private String msg;

    public MessageEvent(String msg) {
        this.msg =msg;
    }

    public MessageEvent(int i) {
        this.i = i;
    }

    public String getMsg() {
        return msg;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
