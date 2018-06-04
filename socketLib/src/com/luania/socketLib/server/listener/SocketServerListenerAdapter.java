package com.luania.socketLib.server.listener;

import com.luania.socketLib.contract.Head;

public class SocketServerListenerAdapter<H extends Head> implements SocketServerListener<H> {
    @Override
    public void serverStart(int port) {

    }

    @Override
    public void serverError(Exception e) {

    }

    @Override
    public void serverKillError(Exception e) {

    }

    @Override
    public void serverKill() {

    }

    @Override
    public void beforeExcute() {

    }

    @Override
    public void excuteError() {

    }

    @Override
    public void excuteReceived(H head, String body) {

    }

    @Override
    public void excuteFinal() {

    }

    @Override
    public void beforeRespond(String send) {

    }

    @Override
    public void afterRespond(String send) {

    }
}
