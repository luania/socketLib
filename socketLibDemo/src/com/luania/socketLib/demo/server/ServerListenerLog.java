package com.luania.socketLib.demo.server;

import com.luania.socketLib.server.listener.SocketServerListener;
import com.luania.socketLib.demo.CustomHead;

public class ServerListenerLog implements SocketServerListener<CustomHead> {
    @Override
    public void serverStart(int i) {
        System.out.println("serverStart");
    }

    @Override
    public void serverError(Exception e) {
        System.out.println("serverError");
    }

    @Override
    public void serverKillError(Exception e) {
        System.out.println("serverKillError");
    }

    @Override
    public void serverKill() {
        System.out.println("serverKill");
    }

    @Override
    public void beforeExcute() {
        System.out.println("beforeExcute");
    }

    @Override
    public void excuteError() {
        System.out.println("excuteError");
    }

    @Override
    public void excuteReceived(CustomHead head, String s) {
        System.out.println("excuteReceived");
    }

    @Override
    public void beforeRespond(String s) {
        System.out.println("beforeRespond");
    }

    @Override
    public void afterRespond(String s) {
        System.out.println("afterRespond");
    }

    @Override
    public void excuteFinal() {
        System.out.println("excuteFinal");
    }
}
