package com.luania.socketLib.demo;

import com.luania.socketLib.contract.Head;

public class CustomHead implements Head {
    private String command;
    private int bodyLength;

    public CustomHead(String command, int bodyLength) {
        this.command = command;
        this.bodyLength = bodyLength;
    }

    public CustomHead(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }
}