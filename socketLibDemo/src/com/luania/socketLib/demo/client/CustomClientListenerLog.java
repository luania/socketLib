package com.luania.socketLib.demo.client;

import com.luania.socketLib.client.listener.SocketClientListener;
import com.luania.socketLib.demo.CustomHead;

import java.io.IOException;

public class CustomClientListenerLog implements SocketClientListener<CustomHead> {
    @Override
    public void onStart() {
        System.out.println("onStart");
    }

    @Override
    public void connSuccess() {
        System.out.println("connSuccess");
    }

    @Override
    public void connFail(Exception e) {
        System.out.println("connFail");
    }

    @Override
    public void sendBegin(String s) {
        System.out.println("sendBegin");
    }

    @Override
    public void success(CustomHead head, String s) {
        System.out.println("success");
    }

    @Override
    public void ioFail(IOException e) {
        System.out.println("ioFail");
    }

    @Override
    public void onFinal() {
        System.out.println("onFinal");
    }
}
