package com.luania.socketLib.client.listener;

import com.luania.socketLib.contract.Head;

import java.io.IOException;

public class SocketClientListenerAdapter<H extends Head> implements SocketClientListener<H> {
    @Override
    public void onStart() {

    }

    @Override
    public void connSuccess() {

    }

    @Override
    public void connFail(Exception e) {

    }

    @Override
    public void sendBegin(String body) {

    }

    @Override
    public void success(H head, String body) {

    }

    @Override
    public void ioFail(IOException e) {

    }

    @Override
    public void onFinal() {

    }
}
