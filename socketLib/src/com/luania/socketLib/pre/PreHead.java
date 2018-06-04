package com.luania.socketLib.pre;

import com.luania.socketLib.contract.Head;

public class PreHead implements Head {
    private String command;
    private int bodyLength;

    public PreHead(String command, int bodyLength) {
        this.command = command;
        this.bodyLength = bodyLength;
    }

    public PreHead(String command) {
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